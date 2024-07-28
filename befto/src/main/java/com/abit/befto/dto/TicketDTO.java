package com.abit.befto.dto;

import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDTO {
	
	@JsonIgnore
    private Long id;
    private Long trainId; // Instead of a Train object
    private Long trainStopId; // Instead of a TrainStop object
    private String passengerName;
    private ZonedDateTime journeyDate;
    private int identityId;

    public TicketDTO(Long id, Long trainId, Long trainStopId, String passengerName, ZonedDateTime journeyDate, int identityId) {
        this.id = id;
        this.trainId = trainId;
        this.trainStopId = trainStopId;
        this.passengerName = passengerName;
        this.journeyDate = journeyDate;
        this.identityId = identityId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getTrainStopId() {
        return trainStopId;
    }

    public void setTrainStopId(Long trainStopId) {
        this.trainStopId = trainStopId;
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
