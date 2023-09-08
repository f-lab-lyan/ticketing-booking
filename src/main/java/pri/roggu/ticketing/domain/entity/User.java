package pri.roggu.ticketing.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tUser")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingIdx")
    private Booking booking;

    private String userName;
    private String hp;

}