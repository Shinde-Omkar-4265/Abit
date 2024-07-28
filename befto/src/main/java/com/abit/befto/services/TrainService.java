package com.abit.befto.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abit.befto.model.Train;
import com.abit.befto.repositories.TrainRepository;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public Train saveTrain(Train train) {
        return trainRepository.save(train);
    }

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public Train getTrainById(Long id) {
        return trainRepository.findById(id).orElse(null);
    }

    public Train updateTrain(Long id, Train updatedTrain) {
        if (trainRepository.existsById(id)) {
            updatedTrain.setId(id);
            return trainRepository.save(updatedTrain);
        }
        return null;
    }

    public void deleteTrain(Long id) {
        trainRepository.deleteById(id);
    }
    
    
}
