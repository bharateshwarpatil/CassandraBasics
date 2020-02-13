package com.cassandra.basics.service;
import com.cassandra.basics.model.RawEvent;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Date;
@Accessor
public interface RawEventAccessor {
    @Query("INSERT INTO training.RawEvent ( "
            + "partyId, "
            + "type, "
            + "rawData, "
            + "tags, "
            + "createdDate "
            + ") "
            + "VALUES( "
            + ":partyId, "
            + ":type, "
            + ":rawData, "
            + ":tags, "
            + ":createdDate "
            + ") USING TTL 86400")
    public void insertrawEvent(
            @Param("partyId")  String partyId,
            @Param("type") String type,
            @Param("tags") String tags,
            @Param("rawData") String rawData,
            @Param("createdDate") Date createdDate);

    @Query("SELECT * FROM training.RawEvent WHERE partyId = :partyId and type=:type and createdDate >= :fromDate and createdDate <= :toDate")
    public  Result<RawEvent>  getRawEventByCriteria(@Param("partyId") String partyid,@Param("type") String type,@Param("fromDate") Date fromsearchDate,@Param("toDate") Date toDate);

    @Query("SELECT * FROM training.RawEvent  WHERE partyId = :partyId and type=:type ")
    public ListenableFuture<RawEvent> getRawEventByID(@Param("partyId") String partyid, @Param("type") String type);

    @Query("SELECT * FROM training.RawEvent  WHERE partyId = :partyId and type=:type ")
    public Result<RawEvent> getRawEventByIDSync(@Param("partyId") String partyid, @Param("type") String type);

    @Query("delete FROM training.RawEvent  WHERE partyId = :partyId and type=:type ")
    public void deleteRawEvent(@Param("partyId") String partyid,@Param("type") String type);

}
