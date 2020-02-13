package com.cassandra.basics.service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraConnector {

    private CassandraConnector(){}

    private static Cluster cluster;

    private static Session session;

    public static Session connect(String node, Integer port) {
        if(session==null){
            synchronized (CassandraConnector.class){
                Cluster.Builder b = Cluster.builder().withClusterName("Test Cluster").addContactPoint(node);
                if (port != null) {
                    b.withPort(port);
                }
                cluster = b.build();
                session = cluster.connect();
            }
        }
        return session;
    }

    public Session getSession() {
        return this.session;
    }

    public static void close() {
        session.close();
        cluster.close();
    }
}

