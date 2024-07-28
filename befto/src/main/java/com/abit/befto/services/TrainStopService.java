package com.abit.befto.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abit.befto.model.TrainStop;
import com.abit.befto.repositories.TrainStopRepository;

import java.util.List;

@Service
public class TrainStopService {

    @Autowired
    private TrainStopRepository trainStopRepository;

    public TrainStop saveTrainStop(TrainStop trainStop) {
        return trainStopRepository.save(trainStop);
    }

    public List<TrainStop> getAllTrainStops() {
        return trainStopRepository.findAll();
    }

    public TrainStop getTrainStopById(Long id) {
        return trainStopRepository.findById(id).orElse(null);
    }

    public TrainStop updateTrainStop(Long id, TrainStop updatedTrainStop) {
        if (trainStopRepository.existsById(id)) {
            updatedTrainStop.setId(id);
            return trainStopRepository.save(updatedTrainStop);
        }
        return null;
    }

    public void deleteTrainStop(Long id) {
        trainStopRepository.deleteById(id);
    }
}
