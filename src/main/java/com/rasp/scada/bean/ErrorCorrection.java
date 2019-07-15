package com.rasp.scada.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ErrorCorrection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String phase;

    private Double startCurrentValue;

    private Double endCurrentValue;

    private Double error;

    @Override
    public String toString() {
        return "ErrorCorrection{" +
                "id=" + id +
                ", phase='" + phase + '\'' +
                ", startCurrentValue=" + startCurrentValue +
                ", endCurrentValue=" + endCurrentValue +
                ", error=" + error +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Double getStartCurrentValue() {
        return startCurrentValue;
    }

    public void setStartCurrentValue(Double startCurrentValue) {
        this.startCurrentValue = startCurrentValue;
    }

    public Double getEndCurrentValue() {
        return endCurrentValue;
    }

    public void setEndCurrentValue(Double endCurrentValue) {
        this.endCurrentValue = endCurrentValue;
    }

    public Double getError() {
        return error;
    }

    public void setError(Double error) {
        this.error = error;
    }
}
