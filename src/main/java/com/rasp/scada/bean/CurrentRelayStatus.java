package com.rasp.scada.bean;

import javax.persistence.*;

@Entity(name = "CurrentRelayStatus")
@Table(name = "current_relay_status")
public class CurrentRelayStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "relay_no")
    private String relayNo;

    @Column(name = "status")
    private String status;

    @Override
    public String toString() {
        return "CurrentRelayStatus{" +
                "id=" + id +
                ", relayNo='" + relayNo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public CurrentRelayStatus() {
    }

    public CurrentRelayStatus(String relayNo, String status) {
        this.relayNo = relayNo;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRelayNo(String relayNo) {
        this.relayNo = relayNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getRelayNo() {
        return relayNo;
    }

    public String getStatus() {
        return status;
    }
}
