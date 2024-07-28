package com.abit.befto.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.abit.befto.dto.TicketDTO;
import com.abit.befto.model.Ticket;
import com.abit.befto.model.Train;
import com.abit.befto.model.TrainStop;
import com.abit.befto.services.TicketService;


@RestController
@RequestMapping("/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /*@ApiOperation(value = "Book a new ticket", notes = "Provides the details to book a new ticket.")
    @ApiResponse(code = 200, message = "Ticket booked successfully.")*/
    @PostMapping
    public ResponseEntity<Ticket> bookTicket(@RequestBody TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        // Convert DTO to Entity
        ticket.setTrain(new Train(ticketDTO.getTrainId()));
        ticket.setTrainStop(new TrainStop(ticketDTO.getTrainStopId()));
        ticket.setPassengerName(ticketDTO.getPassengerName());
        ticket.setJourneyDate(ticketDTO.getJourneyDate());
        ticket.setIdentityId(ticketDTO.getIdentityId());

        Ticket bookedTicket = ticketService.bookTicket(ticket);
        return ResponseEntity.ok(bookedTicket);
    }


    /*@ApiOperation(value = "Get all tickets", notes = "Retrieves a list of all tickets.")
    @ApiResponse(code = 200, message = "List of tickets retrieved successfully.")*/
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    /*@ApiOperation(value = "Get ticket by ID", notes = "Retrieves a ticket by its ID.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ticket retrieved successfully."),
        @ApiResponse(code = 404, message = "Ticket not found.")
    })*/
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    /*@ApiOperation(value = "Update ticket", notes = "Updates the details of an existing ticket.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ticket updated successfully."),
        @ApiResponse(code = 404, message = "Ticket not found.")
    })*/
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        // Convert DTO to Entity
        ticket.setId(id);
        ticket.setTrain(new Train(ticketDTO.getTrainId()));
        ticket.setTrainStop(new TrainStop(ticketDTO.getTrainStopId()));
        ticket.setPassengerName(ticketDTO.getPassengerName());
        ticket.setJourneyDate(ticketDTO.getJourneyDate());
        ticket.setIdentityId(ticketDTO.getIdentityId());

        Ticket updatedTicket = ticketService.updateTicket(id, ticket);
        if (updatedTicket != null) {
            return ResponseEntity.ok(updatedTicket);
        }
        return ResponseEntity.notFound().build();
    }

    
    /*@ApiOperation(value = "Delete ticket", notes = "Deletes a ticket by its ID.")
    @ApiResponse(code = 204, message = "Ticket deleted successfully.")*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}





