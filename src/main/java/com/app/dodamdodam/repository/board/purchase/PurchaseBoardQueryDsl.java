package com.app.dodamdodam.repository.board.purchase;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface PurchaseBoardQueryDsl {
//    무한스크롤 10개씩
    Slice<PurchaseBoard> findAllPurchaseBoard(Pageable pageable);
//    판매게시글 각각
    Optional<PurchaseBoard> findPurchaseBoardById(Long id);

//    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    public Page<PurchaseBoard> findPurchaseBoardListByMemberId(Pageable pageable, Long memberId);


}
