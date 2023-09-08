package pri.roggu.ticketing.ticketing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pri.roggu.ticketing.ticketing.service.TicketingService;

@RestController
@RequiredArgsConstructor
public class TicketingController {

    private final TicketingService ticketingService;

}
