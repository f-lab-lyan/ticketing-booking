package pri.roggu.ticketing.ticketing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.roggu.ticketing.ticketing.service.TicketingService;

@RestController
@RequiredArgsConstructor
public class TicketingController {

    private final TicketingService ticketingService;

    @PostMapping(value = "/booking")
    public String booking() {
        return ticketingService.booking();
    }


}