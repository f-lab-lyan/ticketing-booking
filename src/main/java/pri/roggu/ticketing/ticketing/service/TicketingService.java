package pri.roggu.ticketing.ticketing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pri.roggu.ticketing.ticketing.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketingService {

    private final TicketRepository ticketRepository;

    public String booking() {
        return "";
    }

}
