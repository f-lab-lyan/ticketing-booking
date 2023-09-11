package pri.roggu.ticketing.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pri.roggu.ticketing.domain.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
