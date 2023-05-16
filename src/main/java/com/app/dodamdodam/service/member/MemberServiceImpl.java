package com.app.dodamdodam.service.member;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.provider.UserDetail;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import com.app.dodamdodam.type.MemberStatus;
import com.app.dodamdodam.type.MemberType;
import com.app.dodamdodam.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService/*, OAuth2UserService<OAuth2UserRequest, OAuth2User>*/ {

    private final HttpSession httpSession;
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final FreeBoardRepository freeBoardRepository;
    private final PurchaseBoardRepository purchaseBoardRepository;
    private final RecruitmentBoardRepository recruitmentBoardRepository;
    private final BannerRepository bannerRepository;

    /* 로그인 된 유저 정보 */
    @Override
    public Optional<Member> getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberRole(Role.MEMBER);
        memberDTO.setMemberType(MemberType.GENERAL);
        memberDTO.setMemberStatus(MemberStatus.NORMAL);
        memberRepository.save(toMemberEntity(memberDTO));
    }
    /* 내 포인트 내역 */
    @Override
    public List<Point> getMyPointList(Long memberId) {
        return pointRepository.findPointByMemberId_QueryDSL(memberId);
    }

    /* 내가 작성한 자유 게시글 목록 */
    @Override
    public Page<FreeBoard> getMyFreeBoardList(Pageable pageable, Long memberId) {
        return freeBoardRepository.findFreeBoardListByMemberId_QueryDSL(pageable, memberId);
    }

    /* 내가 작성한 자유게시글 개수 */
    @Override
    public Long getMyFreeBoardListCount(Long memberId) {
        return freeBoardRepository.findFreeBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 내가 작성한 판매게시글 개수 */
    @Override
    public Long getMyPurchaseBoardListCount(Long memberId) {
        return purchaseBoardRepository.findPurchaseBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 내가 작성한 모집게시글 개수 */
    @Override
    public Long getMyRecruitmentBoardListCount(Long memberId) {
        return recruitmentBoardRepository.findRecruitmentBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 내가 참가한 모집게시글 개수 */
    @Override
    public Long getMyRecruitmentedBoardListCount(Long memberId) {
        return recruitmentBoardRepository.findRecruitmentedBoardListCountByMemberId_QueryDSL(memberId);
    }

    /* 관리자 멤버 목록*/
    @Override 
    public Page<MemberDTO> showList(Pageable pageable) {
        Page<Member> memberPage = memberRepository.findAllMemberList_QueryDSL(PageRequest.of(1,10));
        List<MemberDTO> memberDTOS = memberPage.get().map(this::toMemberDTO).collect(Collectors.toList());

        return new PageImpl<>(memberDTOS, pageable, memberPage.getTotalElements());
    }

    /* 회원 비활성화 처리*/
    @Override
    public void setMemberStatusById(Long memberId) {
        memberRepository.findById(memberId).ifPresent(member -> member.setMemberStatus(MemberStatus.WITHDRAWAL));
    }

    /* 회원 정보 수정 */
    /* 페이지를 고치던지 2개 따로 만들던지 해야함 */
    @Override
    public void setMemberInfoById(Long memberId, Member memberInfo) {
        memberRepository.findById(memberId).ifPresent(member -> {
            member.setMemberPassword(memberInfo.getMemberPassword());
            member.setAddress(memberInfo.getAddress());
            member.setMemberEmail(memberInfo.getMemberEmail());
            member.setMemberName(memberInfo.getMemberName());
            member.setMemberPhone(memberInfo.getMemberPhone());
        });
    }

    /* 비밀번호 변경 */
    @Override
    public void setMemberPasswordById(Long memberId, String password) {
        memberRepository.findById(memberId).ifPresent(member -> member.setMemberPassword(password));
    }

    /* 배너 신청 */
    @Override
    public void saveBannerApply(Long memberId, BannerApply bannerApply) {
        memberRepository.findById(memberId).ifPresent(member -> bannerApply.setMember(member));
        bannerRepository.save(bannerApply);
    }

    /* 내가 참가한 모집 게시글 전체 */
    @Override
    public List<RecruitmentBoard> getMyRecruitementedBoardList(Long memberId) {
        return recruitmentBoardRepository.findAllRecruitmentedBoardListByMemberId_QueryDSL(memberId);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername 진입");
        log.info(username);
        Member member = memberRepository.findMemberByMemberId_QueryDSL(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        log.info("====================");
        log.info(member.getMemberId());
        log.info(member.getMemberEmail());
        log.info(member.getMemberName());
        log.info(member.getMemberPassword());
        log.info(member.getMemberPhone());
        log.info(member.getMemberRole().toString());
        log.info(member.getMemberStatus().toString());
        log.info(member.getMemberType().toString());
        log.info("====================");

        return UserDetail.builder().id(member.getId())
                .memberId(member.getMemberId())
                .memberPassword(member.getMemberPassword())
                .memberEmail(member.getMemberEmail())
                .memberPhone(member.getMemberPhone())
                .memberName(member.getMemberName())
                .memberRole(member.getMemberRole())
                .memberStatus(member.getMemberStatus())
                .memberType(member.getMemberType())
                .build();
    }

//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
//                .getUserInfoEndpoint().getUserNameAttributeName();
//        log.info(userRequest.getClientRegistration().getProviderDetails().toString());
//        // naver, kakao 로그인 구분
//        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
//
//        Member member = saveOrUpdate(attributes);
//
//        httpSession.setAttribute("name", member.getMemberName()); // name
//        httpSession.setAttribute("email", member.getMemberEmail()); // email
//        httpSession.setAttribute("mobile", member.getMemberPhone()); // phone
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(member.getMemberRole().getSecurityRole())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey());
//    }

//    private Member saveOrUpdate(OAuthAttributes attributes) {
//        Member member = memberRepository.findMemberByMemberEmail_QueryDSL(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getEmail(), attributes.getMobile(), attributes.getName()))
//                .orElse(attributes.toEntity());
//
//        return memberRepository.save(member);
//    }
}
