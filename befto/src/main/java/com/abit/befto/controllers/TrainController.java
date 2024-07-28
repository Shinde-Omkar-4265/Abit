package com.abit.befto.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.abit.befto.dto.TicketDTO;
import com.abit.befto.dto.TrainDTO;
import com.abit.befto.model.Ticket;
import com.abit.befto.model.Train;
import com.abit.befto.services.TicketService;
import com.abit.befto.services.TrainService;
import java.util.List;
import java.util.stream.Collectors;

@EnableMethodSecurity //for role base access to perticular method. like: deleteTrain
@RestController
@RequestMapping("/v1/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;
    
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Train> createTrain(@RequestBody TrainDTO trainDTO) {
        Train train = new Train();
        // Convert DTO to Entity
        train.setTrainNumber(trainDTO.getTrainNumber());
        train.setTrainName(trainDTO.getTrainName());
        train.setTotalSeats(trainDTO.getTotalSeats());
        train.setAvailableSeats(trainDTO.getAvailableSeats());
        train.setRoute(trainDTO.getRoute());

        Train createdTrain = trainService.saveTrain(train);
        return ResponseEntity.ok(createdTrain);
    }

    @GetMapping
    public ResponseEntity<List<Train>> getAllTrains() {
        return ResponseEntity.ok(trainService.getAllTrains());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        Train train = trainService.getTrainById(id);
        if (train != null) {
            return ResponseEntity.ok(train);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Train> updateTrain(@PathVariable Long id, @RequestBody TrainDTO trainDTO) {
        Train train = new Train();
        // Convert DTO to Entity
        train.setId(id);
        train.setTrainNumber(trainDTO.getTrainNumber());
        train.setTrainName(trainDTO.getTrainName());
        train.setTotalSeats(trainDTO.getTotalSeats());
        train.setAvailableSeats(trainDTO.getAvailableSeats());
        train.setRoute(trainDTO.getRoute());

        Train updatedTrain = trainService.updateTrain(id, train);
        if (updatedTrain != null) {
            return ResponseEntity.ok(updatedTrain);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable Long id) {
        trainService.deleteTrain(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping("/train/{trainId}")
    public ResponseEntity<List<TicketDTO>> getTicketsByTrainId(@PathVariable Long trainId) {
        List<Ticket> tickets = ticketService.getTicketsByTrainId(trainId);
        if (tickets != null && !tickets.isEmpty()) {
            List<TicketDTO> ticketDTOs = tickets.stream()
                .map(ticket -> new TicketDTO(
                    ticket.getId(),
                    ticket.getTrain().getId(),
                    ticket.getTrainStop().getId(),
                    ticket.getPassengerName(),
                    ticket.getJourneyDate(),
                    ticket.getIdentityId()
                ))
                .collect(Collectors.toList());
            return ResponseEntity.ok(ticketDTOs);
        }
        return ResponseEntity.notFound().build();
    }
}


/*
@RestController
@RequestMapping("/v1/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping
    public ResponseEntity<Train> createTrain(@RequestBody Train train) {
        return ResponseEntity.ok(trainService.saveTrain(train));
    }

    @GetMapping
    public ResponseEntity<List<Train>> getAllTrains() {
        return ResponseEntity.ok(trainService.getAllTrains());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        Train train = trainService.getTrainById(id);
        if (train != null) {
            return ResponseEntity.ok(train);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Train> updateTrain(@PathVariable Long id, @RequestBody Train train) {
        Train updatedTrain = trainService.updateTrain(id, train);
        if (updatedTrain != null) {
            return ResponseEntity.ok(updatedTrain);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable Long id) {
        trainService.deleteTrain(id);
        return ResponseEntity.noContent().build();
    }
}*/
