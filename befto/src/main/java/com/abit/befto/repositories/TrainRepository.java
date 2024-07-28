package com.abit.befto.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.abit.befto.model.Train;

public interface TrainRepository extends JpaRepository<Train, Long> {
	
	
}

