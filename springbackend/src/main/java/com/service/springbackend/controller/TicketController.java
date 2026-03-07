package com.service.springbackend.controller;

import com.service.springbackend.dto.StatusUpdateRequest;
import com.service.springbackend.dto.TicketRequest;
import com.service.springbackend.dto.TicketResponse;
import com.service.springbackend.model.Ticket;
import com.service.springbackend.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     *
     * @param request
     * @param jwt
     * @return
     */
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest request,
                                                       @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(ticketService.createTicket(request, jwt));
    }

    /**
     *
     * @param jwt
     * @return
     */
    @GetMapping("/my")
    public ResponseEntity<List<TicketResponse>> getMyTickets(@AuthenticationPrincipal Jwt jwt) {
        List<TicketResponse> responses = ticketService.getAllMyTickets(jwt);
        return ResponseEntity.ok(responses);
    }

    /**
     *
     * @param jwt
     * @return
     */
    @GetMapping("/assigned")
    public ResponseEntity<List<TicketResponse>> getAssignedTickets(@AuthenticationPrincipal Jwt jwt) {
        List<TicketResponse> responses = ticketService.getTicketsAssignedToMe(jwt);
        return ResponseEntity.ok(responses);
    }

    /**
     *
     * @param id
     * @param jwt
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable UUID id,
                                                        @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(ticketService.getTicketById(id, jwt));
    }

    /**
     *
     * @param id
     * @param supportUserId
     * @return
     */
    @PatchMapping("/{id}/assign/{supportUserId}")
    public ResponseEntity<TicketResponse> assignTicket(@PathVariable UUID id,
                                               @PathVariable UUID supportUserId) {
        return ResponseEntity.ok(ticketService.assignTicketToUser(id, supportUserId));

    }

    /**
     *
     * @param id
     * @param request
     * @param jwt
     * @return
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<TicketResponse> updateStatus(
            @PathVariable UUID id,
            @RequestBody StatusUpdateRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        return ResponseEntity.ok(ticketService.updateTicketStatus(id, request.status(), jwt));
    }

    /**
     *
     * @param id
     * @param jwt
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID id,
                                             @AuthenticationPrincipal Jwt jwt) {
        ticketService.deleteTicket(id, jwt);
        return ResponseEntity.noContent().build();
    }
}