package com.cassandra.basics;

import com.cassandra.basics.model.RawEvent;
import com.cassandra.basics.service.CassandraConnector;
import com.cassandra.basics.service.RawEventAccessor;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Test {


    public static void main(String[] args) {
        //:9042localhost/127.0.0.1:9042
        Session session = CassandraConnector.connect("localhost", 9042);
        // CassandraService.createKeyspace("training","SimpleStrategy",0,session);
        MappingManager manager = new MappingManager(session);
        Mapper<RawEvent> mapper = new MappingManager(session).mapper(RawEvent.class);
        RawEventAccessor accessor = manager.createAccessor(RawEventAccessor.class);
        Date date= new Date();
        RawEvent rawEvent = new RawEvent();
        rawEvent.setPartyId("100");
        rawEvent.setTags("App");
        rawEvent.setType("alert3");
        rawEvent.setRawData("This is raw event");
        rawEvent.setCreatedDate(date);

        accessor.insertrawEvent(rawEvent.getPartyId(), rawEvent.getType(), rawEvent.getTags(), rawEvent.getRawData(), rawEvent.getCreatedDate());

        accessor.insertrawEvent(rawEvent.getPartyId(), rawEvent.getType(), rawEvent.getTags(), rawEvent.getRawData(), rawEvent.getCreatedDate());


        // Async Call ****************//
        final ListenableFuture<RawEvent> resultSetFuture = accessor.getRawEventByID("100", "alert");

        resultSetFuture.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("Result is ready");
                try {
                    RawEvent event = resultSetFuture.get();
                    System.out.println(event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        }, Executors.newSingleThreadExecutor());

        /// Sync call to the get
        Result<RawEvent> result = accessor.getRawEventByIDSync("100", "alert");
        Iterator<RawEvent> eventIterator = result.iterator();
        while (eventIterator.hasNext()) {
            System.out.println(eventIterator.next());
        }
        //ListenableFuture resultSetFuturetest = session.executeAsync("SELECT * FROM training.RawEvent  WHERE partyId = :partyId and type=:type ");

          //                                      session.execute(" SELECT * FROM training.RawEvent  WHERE partyId = :partyId and type=:type ");

        CassandraConnector.close();
    }

}
