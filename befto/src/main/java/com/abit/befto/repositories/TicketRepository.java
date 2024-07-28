package com.abit.befto.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.abit.befto.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	List<Ticket> findByTrainId(Long trainId);
}
