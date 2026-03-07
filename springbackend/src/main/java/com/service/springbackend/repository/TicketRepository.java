package com.service.springbackend.repository;

import com.service.springbackend.model.Status;
import com.service.springbackend.model.Ticket;
import com.service.springbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByCreatedBy(User user);
    List<Ticket> findByStatus(Status status);
    List<Ticket> findByAssignedTo(User user);

}