package pri.roggu.ticketing.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tTicket")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Ticket extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingIdx")
    private Booking booking;

    private String ticketName;
}