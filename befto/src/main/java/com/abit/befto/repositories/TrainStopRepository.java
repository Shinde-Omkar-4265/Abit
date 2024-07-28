package com.abit.befto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abit.befto.model.TrainStop;

public interface TrainStopRepository extends JpaRepository<TrainStop, Long> {
}
