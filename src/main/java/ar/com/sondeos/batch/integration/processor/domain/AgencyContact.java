package ar.com.sondeos.batch.integration.processor.domain;

import java.io.Serializable;

public class AgencyContact implements Serializable {

    int dni;
    String agencyName;
    int topicId;

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public AgencyContact(int dni, String agencyName) {
        this.dni = dni;
        this.agencyName = agencyName;
    }

    public AgencyContact(int dni, int topicId) {
        this.dni = dni;
        this.topicId = topicId;
    }

    public AgencyContact() {
    }

    @Override
    public String toString() {
        return "dni=" + dni + ",agencyName=" + agencyName + ",topicId=" + topicId;
    }
}
