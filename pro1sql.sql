--CREATE DATABASE reimbursement;
DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles(
	user_role_id SERIAL PRIMARY KEY NOT NULL,
	user_role VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users(
	user_id SERIAL PRIMARY KEY NOT NULL,
	userusername VARCHAR(50) NOT NULL,
	userpassword VARCHAR(50) NOT NULL,
	user_first VARCHAR(100) NOT NULL,
	user_last VARCHAR(100) NOT NULL,
	user_email VARCHAR(150) UNIQUE NOT NULL, 
	user_role_id INTEGER REFERENCES user_roles(user_role_id) NOT NULL
);

CREATE TABLE reimb_status(
	reimb_status_id SERIAL PRIMARY KEY NOT NULL,
	reimb_status VARCHAR(10) NOT NULL
);
DROP TABLE IF EXISTS reimb_type;
CREATE TABLE reimb_type(
	reimb_type_id SERIAL PRIMARY KEY NOT NULL,
	reimb_type VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS reimbursement;
CREATE TABLE reimbursement(
	reimb_id SERIAL PRIMARY KEY NOT NULL,
	reimb_amount NUMERIC(12,2) NOT NULL,
	reimb_submitted VARCHAR(30) NOT NULL,
	reimb_resolved VARCHAR(30),
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

--change user_role id to match that a user role was created 

UPDATE users
SET user_role_id = 2
WHERE user_username='kc2009';

UPDATE users
SET user_role_id = 3
WHERE user_username='liz1988';

INSERT INTO users (user_username, user_password, user_first, user_last,	user_email, user_role_id) 
	VALUES ('kc2009', 'bestie', 'Kassandra', 'Rodriguez', 'kc2009@gmail.com', 2),
	('liz1988', 'wesley', 'Elizabeth', 'Jimenez', 'liz1988@gmail.com', 3);

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




    select
        user0_.user_id as user_id1_4_,
        user0_.user_email as user_ema2_4_,
        user0_.user_first as user_fir3_4_,
        user0_.user_last as user_las4_4_,
        user0_.user_password as user_pas5_4_,
        user0_.user_role_id as user_rol7_4_,
        user0_.user_username as user_use6_4_ 
    from
        users user0_ 
    where
        user0_.user_username='kc2009';

       select
        reimbursem0_.reimb_id as reimb_id1_2_,
        reimbursem0_.reimb_amount as reimb_am2_2_,
        reimbursem0_.reimb_author as reimb_au6_2_,
        reimbursem0_.reimb_description as reimb_de3_2_,
        reimbursem0_.reimb_resolved as reimb_re4_2_,
        reimbursem0_.reim_resolver as reim_res7_2_,
        reimbursem0_.reim_status_id as reim_sta8_2_,
        reimbursem0_.reimb_submitted as reimb_su5_2_,
        reimbursem0_.reim_type_id as reim_typ9_2_ 
    from
        reimbursement reimbursem0_;

       DELETE FROM users WHERE email='lizzvj@gmail.com' AND id <>1;
       DELETE FROM users WHERE email='pres@gmail.com' AND id<> 2;
      
      
       select
        reimbursem0_.reimb_id as reimb_id1_2_,
        reimbursem0_.reimb_amount as reimb_am2_2_,
        reimbursem0_.reimb_author as reimb_au6_2_,
        reimbursem0_.reimb_description as reimb_de3_2_,
        reimbursem0_.reimb_resolved as reimb_re4_2_,
        reimbursem0_.reim_resolver as reim_res7_2_,
        reimbursem0_.reim_status_id as reim_sta8_2_,
        reimbursem0_.reimb_submitted as reimb_su5_2_,
        reimbursem0_.reim_type_id as reim_typ9_2_ 
    from
        reimbursement reimbursem0_;
       
       
      TRUNCATE reimbursement;
      
DELETE FROM reimbursement WHERE reimb_id =5;
DELETE FROM users WHERE id= 16;
