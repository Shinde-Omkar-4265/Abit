package com.abit.befto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.abit.befto.dto.TrainStopDTO;
import com.abit.befto.model.Train;
import com.abit.befto.model.TrainStop;
import com.abit.befto.services.TrainStopService;


@RestController
@RequestMapping("/v1/train-stops")
public class TrainStopController {

    @Autowired
    private TrainStopService trainStopService;

    @PostMapping
    public ResponseEntity<TrainStop> createTrainStop(@RequestBody TrainStopDTO trainStopDTO) {
        TrainStop trainStop = new TrainStop();
        // Convert DTO to Entity
        trainStop.setTrain(new Train(trainStopDTO.getTrainId()));
        trainStop.setStationName(trainStopDTO.getStationName());
        trainStop.setArrivalTime(trainStopDTO.getArrivalTime());
        trainStop.setDepartureTime(trainStopDTO.getDepartureTime());

        TrainStop createdTrainStop = trainStopService.saveTrainStop(trainStop);
        return ResponseEntity.ok(createdTrainStop);
    }

    @GetMapping
    public ResponseEntity<List<TrainStop>> getAllTrainStops() {
        return ResponseEntity.ok(trainStopService.getAllTrainStops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainStop> getTrainStopById(@PathVariable Long id) {
        TrainStop trainStop = trainStopService.getTrainStopById(id);
        if (trainStop != null) {
            return ResponseEntity.ok(trainStop);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainStop> updateTrainStop(@PathVariable Long id, @RequestBody TrainStopDTO trainStopDTO) {
        TrainStop trainStop = new TrainStop();
        // Convert DTO to Entity
        trainStop.setId(id);
        trainStop.setTrain(new Train(trainStopDTO.getTrainId()));
        trainStop.setStationName(trainStopDTO.getStationName());
        trainStop.setArrivalTime(trainStopDTO.getArrivalTime());
        trainStop.setDepartureTime(trainStopDTO.getDepartureTime());

        TrainStop updatedTrainStop = trainStopService.updateTrainStop(id, trainStop);
        if (updatedTrainStop != null) {
            return ResponseEntity.ok(updatedTrainStop);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainStop(@PathVariable Long id) {
        trainStopService.deleteTrainStop(id);
        return ResponseEntity.noContent().build();
    }
}


/*
@RestController
@RequestMapping("/v1/train-stops")
public class TrainStopController {

    @Autowired
    private TrainStopService trainStopService;

    @PostMapping
    public ResponseEntity<TrainStop> createTrainStop(@RequestBody TrainStop trainStop) {
        return ResponseEntity.ok(trainStopService.saveTrainStop(trainStop));
    }

    @GetMapping
    public ResponseEntity<List<TrainStop>> getAllTrainStops() {
        return ResponseEntity.ok(trainStopService.getAllTrainStops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainStop> getTrainStopById(@PathVariable Long id) {
        TrainStop trainStop = trainStopService.getTrainStopById(id);
        if (trainStop != null) {
            return ResponseEntity.ok(trainStop);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainStop> updateTrainStop(@PathVariable Long id, @RequestBody TrainStop trainStop) {
        TrainStop updatedTrainStop = trainStopService.updateTrainStop(id, trainStop);
        if (updatedTrainStop != null) {
            return ResponseEntity.ok(updatedTrainStop);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainStop(@PathVariable Long id) {
        trainStopService.deleteTrainStop(id);
        return ResponseEntity.noContent().build();
    }
}*/
