--CREATE DATABASE reimbursement;

CREATE TABLE user_roles(
	user_role_id SERIAL PRIMARY KEY NOT NULL,
	user_role VARCHAR(10) NOT NULL
);

--DROP TABLE IF EXISTS users;
CREATE TABLE users(
	user_id SERIAL PRIMARY KEY NOT NULL,
	user_username VARCHAR(50) NOT NULL,
	user_password VARCHAR(50) NOT NULL,
	user_first VARCHAR(100) NOT NULL,
	user_last VARCHAR(100) NOT NULL,
	user_email VARCHAR(150) UNIQUE NOT NULL, 
	user_role_id INTEGER REFERENCES user_roles(user_role_id) NOT NULL
);

CREATE TABLE reimb_status(
	reimb_status_id SERIAL PRIMARY KEY NOT NULL,
	reimb_status VARCHAR(10) NOT NULL
);

CREATE TABLE reimb_type(
	reimb_type_id SERIAL PRIMARY KEY NOT NULL,
	reimb_type VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS reimbursement;
CREATE TABLE reimbursement(
	reimb_id SERIAL PRIMARY KEY NOT NULL,
	reimb_amount NUMERIC(5,2) NOT NULL,
	reimb_submitted TIME NOT NULL,
	reimb_resolved TIME,
	reimb_description VARCHAR(30),
	reimb_author INTEGER REFERENCES users(user_id) NOT NULL,
	reim_resolver INTEGER REFERENCES users(user_id),
	reim_status_id INTEGER REFERENCES reimb_status(reimb_status_id) NOT NULL,
	reim_type_id INTEGER REFERENCES reimb_type(reimb_type_id) NOT NULL	
);


INSERT INTO user_roles (user_role) 
	VALUES ('Employee');
INSERT INTO user_roles (user_role) 
	VALUES ('Manager');

INSERT INTO users (user_username, user_password, user_first, user_last,	user_email, user_role_id) 
	VALUES ('kc2009', 'bestie', 'Kassandra', 'Rodriguez', 'kc2009@gmail.com', 1),
	('liz1988', 'wesley', 'Elizabeth', 'Jimenez', 'liz1988@gmail.com', 2);

INSERT INTO reimb_status (reimb_status) 
	VALUES ('Pending'),('Appoved'),('Denied');

INSERT INTO reimb_type (reimb_type) 
	VALUES ('Lodging'), ('Travel'), ('Food'), ('Other');

--DELETE FROM reimbursement WHERE reimb_id=1;
INSERT INTO reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author,	reim_resolver, reim_status_id,	reim_type_id) 
	VALUES (50.55, get_current_time() , NULL,NULL, 1, 2,1,1);


--selects
SELECT * FROM reimb_status WHERE reimb_status='Pending';

SELECT * FROM reimbursement r
	JOIN reimb_status rs
	ON r.reim_status_id =rs.reimb_status_id 
	WHERE reimb_status= 'Pending';

SELECT * FROM users u 
	JOIN user_roles ur
	ON  u.user_role_id = ur.user_role_id 
	WHERE u.user_id =1;

CREATE OR REPLACE FUNCTION get_current_time() RETURNS TIME WITH TIME ZONE 
	AS $$
	SELECT current_time;
	$$ LANGUAGE SQL;

--call function when doing time resolved or submitted 
