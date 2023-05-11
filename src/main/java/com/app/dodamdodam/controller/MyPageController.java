package com.app.dodamdodam.controller;

import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MemberService memberService;

    private final FreeBoardService freeBoardService;

    /*마이 페이지 메인*/
    @GetMapping("mypage-main")
    public String mypageMain() {
        return"mypage/myPage-Main";
    }

    /* 마이페이지 프로필*/
    @GetMapping("info")
    public String myPageInfo(Model model, HttpSession session) {
        session.setAttribute("memberId", 2L);
        Long memberId =  (Long)session.getAttribute("memberId");
        memberService.getMemberInfo(memberId).ifPresent(member -> model.addAttribute("member", member));

        return"myPage/myPage-Main";
    }

    /* 내가 작성한 자유 게시글*/
    @GetMapping("freeBoard")
    public String myPageFreeBoard(Model model, HttpSession session) {
        log.info("들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Pageable pageable = PageRequest.of(0, 10);
//        Long memberId =  5L;
//        List<FreeBoard> freeBoards = freeBoardService.getFreeBoardsByMemberId(pageable,memberId).getContent();
//        model.addAttribute("freeBoards",freeBoards);

        Long boardId = 201L;
//        List<FreeBoard> freeBoards = freeBoardService.getFreeBoardsByMemberId(pageable, memberId).getContent();
//        log.info(freeBoards.toString());
//        log.info(freeBoardService.getFreeBoardById(boardId).toString());
//        model.addAttribute("freeBoard",freeBoardService.getFreeBoardById(boardId));

//        log.info(freeBoardService.getAllFreeBoards(pageable).toString());
        model.addAttribute("freeBoard",freeBoardService.getAllFreeBoards(pageable));

//        model.addAttribute("freeBoards", freeBoards);

        return"myPage/myPage-Main";  /*테스트로 아무 페이지에나 보내봄*/
    }

}
