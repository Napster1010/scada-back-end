package com.rasp.scada.response;

public class CurrentResponse {
    private String rPhaseCurrent;

    private String yPhaseCurrent;

    private String bPhaseCurrent;

    public CurrentResponse() {
    }

    public CurrentResponse(String rPhaseCurrent, String yPhaseCurrent, String bPhaseCurrent) {
        this.rPhaseCurrent = rPhaseCurrent;
        this.yPhaseCurrent = yPhaseCurrent;
        this.bPhaseCurrent = bPhaseCurrent;
    }

    @Override
    public String toString() {
        return "CurrentResponse{" +
                "rPhaseCurrent='" + rPhaseCurrent + '\'' +
                ", yPhaseCurrent='" + yPhaseCurrent + '\'' +
                ", bPhaseCurrent='" + bPhaseCurrent + '\'' +
                '}';
    }

    public String getrPhaseCurrent() {
        return rPhaseCurrent;
    }

    public void setrPhaseCurrent(String rPhaseCurrent) {
        this.rPhaseCurrent = rPhaseCurrent;
    }

    public String getyPhaseCurrent() {
        return yPhaseCurrent;
    }

    public void setyPhaseCurrent(String yPhaseCurrent) {
        this.yPhaseCurrent = yPhaseCurrent;
    }

    public String getbPhaseCurrent() {
        return bPhaseCurrent;
    }

    public void setbPhaseCurrent(String bPhaseCurrent) {
        this.bPhaseCurrent = bPhaseCurrent;
    }
}
