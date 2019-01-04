package com.rasp.scada.bean;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "RelayInstructionHistory")
@Table(name = "relay_instruction_history")
public class RelayInstructionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "relay_no")
    private String relayNo;

    @Column(name = "new_status")
    private String newStatus;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserDetail userDetail;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    @Override
    public String toString() {
        return "RelayInstructionHistory{" +
                "id=" + id +
                ", relayNo='" + relayNo + '\'' +
                ", newStatus='" + newStatus + '\'' +
                ", userDetail=" + userDetail +
                ", timestamp=" + timestamp +
                '}';
    }

    public RelayInstructionHistory() {
    }

    public RelayInstructionHistory(String relayNo, String newStatus, UserDetail userDetail, Date timestamp) {
        this.relayNo = relayNo;
        this.newStatus = newStatus;
        this.userDetail = userDetail;
        this.timestamp = timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRelayNo(String relayNo) {
        this.relayNo = relayNo;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getRelayNo() {
        return relayNo;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
