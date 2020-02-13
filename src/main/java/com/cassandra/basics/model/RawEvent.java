package com.cassandra.basics.model;

import java.io.Serializable;
import java.util.Date;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.annotation.JsonInclude;

@Table(keyspace = "training", name = "RawEvent")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RawEvent implements Serializable {

    @PartitionKey(value = 0)
    @Column(name = "partyId")
    private String partyId;

    @PartitionKey(value = 1)
    @Column(name = "type")
    private String type;

    @Column(name = "rawData")
    private String rawData;

    @Column(name = "tags")
    private String tags;

    @Column(name = "createdDate")
    @ClusteringColumn
    private Date createdDate;

    public RawEvent() {
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public String getRawData() {
        return rawData;
    }

    public String getTags() {
        return tags;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "RawEvent{" +
                "partyId='" + partyId + '\'' +
                ", type='" + type + '\'' +
                ", rawData='" + rawData + '\'' +
                ", tags='" + tags + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}