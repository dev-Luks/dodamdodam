package com.app.dodamdodam.entity.recruitment;

import com.app.dodamdodam.entity.reply.Reply;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_RECRUITMENT_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentReply extends Reply {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECRUITMENT_BOARD_ID")
    private RecruitmentBoard recruitmentBoard;

}
