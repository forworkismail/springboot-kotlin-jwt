--Data for users table
insert into users(username, password, created_at) values('earth', '$2a$10$ppX1CG/8ZMcKQV3Y46pQ..Dkx0K8aB5kpzUU7GCZF1IVR3gcneR3.', current_timestamp);
insert into users(username, password, created_at) values('mars', '$2a$10$ppX1CG/8ZMcKQV3Y46pQ..Dkx0K8aB5kpzUU7GCZF1IVR3gcneR3.', current_timestamp);

insert into roles(name) values('admin');
insert into roles(name) values('user');

insert into users_roles(user_id, roles_id) values(1, 1);
insert into users_roles(user_id, roles_id) values(2, 2);