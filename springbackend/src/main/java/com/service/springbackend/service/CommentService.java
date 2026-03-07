package com.service.springbackend.service;

import com.service.springbackend.dto.CommentRequest;
import com.service.springbackend.dto.CommentResponse;
import com.service.springbackend.model.Comment;
import com.service.springbackend.model.Ticket;
import com.service.springbackend.model.User;
import com.service.springbackend.repository.CommentRepository;
import com.service.springbackend.repository.TicketRepository;
import com.service.springbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    CommentService(CommentRepository commentRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CommentResponse createComment(UUID ticketId, CommentRequest request, Jwt jwt) {
        User user = getCurrentUser(jwt);
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket nicht gefunden"));

        Comment comment = new Comment();
        comment.setContent(request.content());
        comment.setTicket(ticket);
        comment.setAuthor(user);


        Comment saved = commentRepository.save(comment);
        return mapToResponse(saved);
    }

    public List<CommentResponse> getCommentsByTicket(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket nicht gefunden"));

        return commentRepository.findByTicketOrderByCreatedAtAsc(ticket)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public void deleteComment(UUID commentId, Jwt jwt) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Kommentar nicht gefunden"));

        if (!comment.getAuthor().getEmail().equals(jwt.getClaimAsString("email"))) {
            throw new RuntimeException("Du darfst nur deine eigenen Kommentare löschen!");
        }

        commentRepository.delete(comment);
    }

    private CommentResponse mapToResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getTicket().getId(),
                comment.getAuthor().getUsername(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }

    private User getCurrentUser(Jwt jwt) {
        return userRepository.findByEmail(jwt.getClaimAsString("email"))
                .orElseThrow(() -> new RuntimeException("User nicht gefunden"));
    }
}