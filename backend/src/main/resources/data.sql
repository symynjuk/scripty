INSERT INTO users(first_name, last_name, email, password, enabled) VALUES ('Misha', 'Symyniuk', 'symyniuk.mykhailo@gmail.com', 'qwerty123', true);
INSERT INTO users(first_name, last_name, email, password, enabled) VALUES ('Leonid', 'Zabidovsky', 'zabidovsky@gmail.com', 'qwerty123', true);
INSERT INTO users(first_name, last_name, email, password, enabled) VALUES ('Vasyl', 'Vasyliv', 'vasia@gmail.com', 'qwerty123', true);

INSERT INTO role(name) VALUES ('Admin');
INSERT INTO role(name) VALUES('User');

INSERT INTO user_roles(user_id, role_id) VALUES (1,1);
INSERT INTO user_roles(user_id, role_id) VALUES (2,1);
INSERT INTO user_roles(user_id, role_id) VALUES (3,2);


