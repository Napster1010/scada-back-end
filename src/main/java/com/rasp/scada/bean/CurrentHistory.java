package com.rasp.scada.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CurrentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double rPhaseCurrent;

    private Double yPhaseCurrent;

    private Double bPhaseCurrent;

    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "CurrentHistory{" +
                "id=" + id +
                ", rPhaseCurrent=" + rPhaseCurrent +
                ", yPhaseCurrent=" + yPhaseCurrent +
                ", bPhaseCurrent=" + bPhaseCurrent +
                ", timestamp=" + timestamp +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getrPhaseCurrent() {
        return rPhaseCurrent;
    }

    public void setrPhaseCurrent(Double rPhaseCurrent) {
        this.rPhaseCurrent = rPhaseCurrent;
    }

    public Double getyPhaseCurrent() {
        return yPhaseCurrent;
    }

    public void setyPhaseCurrent(Double yPhaseCurrent) {
        this.yPhaseCurrent = yPhaseCurrent;
    }

    public Double getbPhaseCurrent() {
        return bPhaseCurrent;
    }

    public void setbPhaseCurrent(Double bPhaseCurrent) {
        this.bPhaseCurrent = bPhaseCurrent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
