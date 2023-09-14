package pri.roggu.ticketing.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tBooking")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Booking extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingIdx;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

}
