CREATE TABLE users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   username VARCHAR(255),
   password VARCHAR(255),
   created_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_username UNIQUE (username);

CREATE TABLE roles (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id BIGINT NOT NULL,
   roles_id BIGINT NOT NULL,
   CONSTRAINT pk_users_roles PRIMARY KEY (user_id, roles_id)
);

ALTER TABLE users_roles ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES roles (id);

ALTER TABLE users_roles ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);


