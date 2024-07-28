package com.abit.befto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.ZonedDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne
    @JoinColumn(name = "train_stop_id", nullable = false)
    private TrainStop trainStop;
    
    private String passengerName;
    private ZonedDateTime journeyDate;
    private int identityId;  // For identity verification

    public Ticket() {
    }
    
    public Ticket(Long trainId, Long trainStopId) {
        this.train = new Train(trainId);
        this.trainStop = new TrainStop(trainStopId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public TrainStop getTrainStop() {
        return trainStop;
    }

    public void setTrainStop(TrainStop trainStop) {
        this.trainStop = trainStop;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public ZonedDateTime getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(ZonedDateTime journeyDate) {
        this.journeyDate = journeyDate;
    }

    public int getIdentityId() {
        return identityId;
    }

    public void setIdentityId(int identityId) {
        this.identityId = identityId;
    }


}
