package com.abit.befto.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.abit.befto.model.Ticket;
import com.abit.befto.model.Train;
import com.abit.befto.model.TrainStop;
import com.abit.befto.repositories.TicketRepository;
import com.abit.befto.repositories.TrainRepository;
import com.abit.befto.repositories.TrainStopRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private TrainStopRepository trainStopRepository;

    @Transactional
    public Ticket bookTicket(Ticket ticket) {
        if (ticket.getTrain() == null || ticket.getTrainStop() == null) {
            throw new IllegalArgumentException("Train or Train Stop cannot be null.");
        }

        // Find the train and train stop
        Optional<Train> trainOptional = trainRepository.findById(ticket.getTrain().getId());
        Optional<TrainStop> trainStopOptional = trainStopRepository.findById(ticket.getTrainStop().getId());

        if (trainOptional.isPresent() && trainStopOptional.isPresent()) {
            Train train = trainOptional.get();

            // Check if the train has enough available seats
            if (train.getAvailableSeats() > 0) {
                // Save the ticket
                Ticket savedTicket = ticketRepository.save(ticket);

                // Update the train's available seats
                train.setAvailableSeats(train.getAvailableSeats() - 1);
                trainRepository.save(train);

                return savedTicket;
            } else {
                throw new IllegalStateException("Not enough seats available on the train.");
            }
        } else {
            throw new IllegalArgumentException("Train or Train Stop not found.");
        }
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }
    
    @Transactional
    public Ticket updateTicket(Long id, Ticket ticket) {
        if (ticketRepository.existsById(id)) {
            ticket.setId(id);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    @Transactional
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
    
    public List<Ticket> getTicketsByTrainId(Long trainId) {
        return ticketRepository.findByTrainId(trainId);
    }
}
