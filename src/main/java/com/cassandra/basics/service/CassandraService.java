package com.cassandra.basics.service;

import com.datastax.driver.core.Session;

public class CassandraService {

    public  static void createKeyspace(
            String keyspaceName, String replicationStrategy, int replicationFactor, Session session) {
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'").append(replicationStrategy)
                        .append("','replication_factor':").append(replicationFactor)
                        .append("};");

        String query = sb.toString();
        session.execute(query);
    }
}
