package com.sbcass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CassandraCrudOperationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CassandraCrudOperationsApplication.class, args);

        final Cluster cluster = Cluster.builder().withoutJMXReporting().addContactPoint("127.0.0.1")
                .build();
        final Session session = cluster.connect("ajavasource");

        System.out.println("**** Cluster Information ****");
        System.out.println("Cluster Name:" + cluster.getClusterName());
        System.out.println("Cluster version:" + cluster.getDriverVersion());
        System.out.println("Cluster Conviguration is:" + cluster.getConfiguration());
        System.out.println("Cluster Metadata is:" + cluster.getMetadata());
        System.out.println("Cluster Metrix is:" + cluster.getMetrics());


        //inserting date to student table
        session.execute("INSERT INTO STUDENT (RNO, NAME, SUBJECT) VALUES( 5,'ROHAN', 'PHYSICS');");
        session.execute("INSERT INTO STUDENT (RNO, NAME, SUBJECT) VALUES( 6,'MOHAN', 'CHEMISTRY');");
        session.execute("INSERT INTO STUDENT (RNO, NAME, SUBJECT) VALUES( 7,'SOHAN', 'MATH');");

        getStudentDetails(session);

        session.execute("update student set subject='BIOLOGY' WHERE RNO=7");
        getStudentDetails(session);

        session.execute("delete from student where rno=7");
    }

        private static void getStudentDetails ( final Session inSession){
            ResultSet results = inSession.execute("SELECT * FROM STUDENT");
            for (Row row : results) {
                System.out.format("%d %s %s\n", row.getInt("RNO"), row.getString("NAME"), row.getString("SUBJECT"));
            }
        }
}