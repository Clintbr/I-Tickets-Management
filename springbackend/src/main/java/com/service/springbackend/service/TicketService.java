package com.service.springbackend.service;

import com.service.springbackend.dto.TicketRequest;
import com.service.springbackend.dto.TicketResponse;
import com.service.springbackend.model.Role;
import com.service.springbackend.model.Status;
import com.service.springbackend.model.Ticket;
import com.service.springbackend.model.User;
import com.service.springbackend.repository.TicketRepository;
import com.service.springbackend.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    // 200 OK: Wird automatisch gesendet, wenn die Liste zurückgegeben wird
    public List<TicketResponse> getAllMyTickets(Jwt jwt) {
        User currentUser = getCurrentUser(jwt);
        return ticketRepository.findByCreatedBy(currentUser)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 404 Not Found: Wenn die ID nicht existiert
    public TicketResponse getTicketById(UUID id, Jwt jwt) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket mit der ID " + id + " wurde nicht gefunden."));
        return mapToResponse(ticket);
    }

    @Transactional
    public TicketResponse createTicket(TicketRequest request, Jwt jwt) {
        User currentUser = getCurrentUser(jwt); // Kann 401/404 werfen

        Ticket ticket = new Ticket();
        ticket.setTitle(request.title());
        ticket.setDescription(request.description());
        ticket.setStatus(Status.OPEN);
        ticket.setPriority(request.priority() != null ? request.priority() : com.service.springbackend.model.Priority.MEDIUM);
        ticket.setCreatedBy(currentUser);

        Ticket saved = ticketRepository.save(ticket);
        // 201 Created: Wird im Controller über @ResponseStatus(HttpStatus.CREATED) gesteuert
        return mapToResponse(saved);
    }

    @Transactional
    public void deleteTicket(UUID id, Jwt jwt) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket nicht gefunden."));

        // 403 Forbidden: Logik für Berechtigungen
        String currentUserEmail = jwt.getClaimAsString("email");
        if (!ticket.getCreatedBy().getEmail().equals(currentUserEmail)) {
            throw new RuntimeException("Zugriff verweigert: Sie sind nicht der Besitzer dieses Tickets.");
        }

        // 204 No Content: Wird im Controller nach erfolgreichem Löschen gesendet
        ticketRepository.delete(ticket);
    }

    // 200 OK: Zeigt dem Support-Mitarbeiter seine zugewiesenen Tickets
    public List<TicketResponse> getTicketsAssignedToMe(Jwt jwt) {
        User currentUser = getCurrentUser(jwt);
        return ticketRepository.findByAssignedTo(currentUser)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public TicketResponse assignTicketToUser(UUID ticketId, UUID supportUserId) {
        // 404 Fehler, falls das Ticket nicht existiert
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket mit der ID " + ticketId + " nicht gefunden."));

        // 404 Fehler, falls der Support-User nicht existiert
        User supportUser = userRepository.findById(supportUserId)
                .orElseThrow(() -> new RuntimeException("Support-Mitarbeiter mit der ID " + supportUserId + " nicht gefunden."));

        ticket.setAssignedTo(supportUser);
        ticket.setStatus(Status.IN_PROGRESS);

        Ticket savedTicket = ticketRepository.save(ticket);

        // Wir geben das gemappte DTO zurück, um den JSON-Error (ByteBuddy/Proxy) zu vermeiden
        return mapToResponse(savedTicket);
    }

    @Transactional
    public TicketResponse updateTicketStatus(UUID id, Status status, Jwt jwt) {
        User user = getCurrentUser(jwt);

        // 403 Forbidden
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.SUPPORT) {
            throw new RuntimeException("Nicht autorisiert: Nur Support oder Admins dürfen Statusänderungen vornehmen.");
        }

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket nicht gefunden."));

        ticket.setStatus(status);
        return mapToResponse(ticketRepository.save(ticket));
    }


    // Hilfsmethode für 401/404
    private User getCurrentUser(Jwt jwt) {
        if (jwt == null) {
            throw new RuntimeException("Nicht authentifiziert: Token fehlt."); // 401
        }
        String email = jwt.getClaimAsString("email");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Benutzerprofil in der Datenbank nicht gefunden.")); // 404
    }

    public TicketResponse mapToResponse(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getCreatedBy().getUsername(),
                ticket.getAssignedTo() != null ? ticket.getAssignedTo().getUsername() : "Nicht zugewiesen",
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }
}