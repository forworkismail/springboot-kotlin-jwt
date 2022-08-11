CREATE TABLE users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   username VARCHAR(255),
   password VARCHAR(255),
   created_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_username UNIQUE (username);

--Data for users table
insert into users(username, password, created_at) values('earth', '$2a$10$ppX1CG/8ZMcKQV3Y46pQ..Dkx0K8aB5kpzUU7GCZF1IVR3gcneR3.', current_timestamp);
insert into users(username, password, created_at) values('mars', '$2a$10$ppX1CG/8ZMcKQV3Y46pQ..Dkx0K8aB5kpzUU7GCZF1IVR3gcneR3.', current_timestamp);