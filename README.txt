1. install and run cassandra 
2. open cmd and type cassandra and wait for to start cassandra
3. open another cmd and create a keyspace and insert a talbe into it.

eg.

CREATE TABLE person(
  first_name TEXT,
  date_of_birth TIMESTAMP,
  person_id UUID,
  last_name TEXT,
  salary DOUBLE,
  PRIMARY KEY ((first_name), date_of_birth, person_id)
) WITH CLUSTERING ORDER BY (date_of_birth ASC, person_id DESC);

4. now open import project, build project and run as spring boot application.