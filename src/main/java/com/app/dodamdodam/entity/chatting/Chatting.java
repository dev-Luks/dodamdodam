package com.app.dodamdodam.entity.chatting;

<<<<<<< HEAD
=======
import com.app.dodamdodam.audit.Period;
>>>>>>> f0b71dec637feb384754940dc9f342c0d4f17a6a
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_CHATTING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String chattingContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;


}
