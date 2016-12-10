-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

CREATE SEQUENCE seq_userId
START WITH 100;
CREATE TABLE users (
	user_id INTEGER DEFAULT NEXTVAL('seq_userId') NOT NULL,
	email varchar(50) NOT NULL,
	hashedPassword varchar(32) NOT NULL,
	salt varchar(256) NOT NULL,
	type integer NOT NULL,
	CONSTRAINT pk_user_user_id PRIMARY KEY (user_id)
);

CREATE TABLE staff_detail (
	user_id integer NOT NULL,
	first_name varchar(45) NOT NULL,
	last_name varchar(45) NOT NULL,
	title varchar(45)
);

CREATE TABLE student_detail(
	user_id integer NOT NULL,
	first_name varchar(45) NOT NULL,
	last_name varchar(45) NOT NULL,
	cohort integer NOT NULL,
	language varchar(30)
);

CREATE TABLE cohorts(
	cohort integer NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	CONSTRAINT pk_cohorts_cohort PRIMARY KEY (cohort)
);

CREATE SEQUENCE seq_employerId
START WITH 100;
CREATE TABLE employer_detail(
	employer_id integer DEFAULT NEXTVAL('seq_employerId') NOT NULL,
	employer_name varchar(45) NOT NULL,
	company_description varchar(400),
	CONSTRAINT pk_employer_detail_employer_id PRIMARY KEY (employer_id)
	
);

CREATE TABLE user_employer(
	user_id integer NOT NULL,
	employer_id integer NOT NULL,
	CONSTRAINT pk_user_employer_user_id_employer_id PRIMARY KEY (user_id, employer_id)
);

CREATE SEQUENCE seq_invitationId
START WITH 100;
CREATE TABLE invitations(
	invitation_id INTEGER DEFAULT NEXTVAL('seq_invitationId') NOT NULL,
	type varchar(20) NOT NULL,
	email varchar(50) NOT NULL,
	time_created timestamp NOT NULL,
	CONSTRAINT pk_invitations_invitation_id PRIMARY KEY (invitation_id)	
);

CREATE SEQUENCE seq_eventId
START WITH 100;

CREATE TABLE events(
	event_id INTEGER DEFAULT NEXTVAL('seq_eventId') NOT NULL,
	day date NOT NULL,
	name varchar(40) NOT NULL,
	start_time time NOT NULL,
	end_time time NOT NULL,
	interview_length integer NOT NULL,
	CONSTRAINT pk_events_event_id PRIMARY KEY (event_id)
);

CREATE SEQUENCE seq_attendeeId
START WITH 100;
CREATE TABLE attendees(
	attendee_id integer DEFAULT NEXTVAL('seq_attendeeId') NOT NULL,
	attendee_name varchar(40) NOT NULL,
	employer_id integer NOT NULL,
	CONSTRAINT pk_attendees_attendee_id PRIMARY KEY (attendee_id)
);

CREATE TABLE events_attendees(
	event_id integer NOT NULL,
	attendee_id integer NOT NULL
);

CREATE TABLE student_preferences(
	user_id integer NOT NULL,
	event_id integer NOT NULL,
	employer_id integer NOT NULL,
	weight integer NOT NULL,
	CONSTRAINT pk_student_preferences_user_id_event_id_employer_id PRIMARY KEY (user_id, event_id, employer_id)
);
-- attendee student event time table
CREATE TABLE interviews(
	
);

INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (4,1,1,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (4,1,2,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (4,1,3,1);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (5,1,2,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (5,1,3,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (5,1,1,1);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (6,1,1,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (6,1,3,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (6,1,2,1);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (7,1,3,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (7,1,2,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (7,1,1,1);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (8,1,2,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (8,1,1,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (8,1,3,1);


INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (5,2,3,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (5,2,4,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (5,2,5,1);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (6,2,1,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (6,2,3,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (6,2,2,1);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (7,2,3,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (7,2,2,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (7,2,5,1);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (8,2,3,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (8,2,1,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (8,2,2,1);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (13,2,4,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (13,2,5,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (13,2,3,1);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (14,2,2,3);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (14,2,5,2);
INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES (14,2,1,1);

INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (0, 'admin@admin.com', '75AuWGxcjLAN5FcCZsf+FQ==', 'SnREeLYiqjfDax352oYbllONuXTEnCIRVBLLjO7OfMnO59Q4q5VEm3ZqU4M/iaD0e677FlxwQ6nCreXpa8IQZJYaMfcfFjVMB0KT+zeWMuZudUWD6ZNPtviwW5b8ysNAdVvIQUu7dkWQ8ME6AwqIDxPTXpF51v2ZM7YFobwTQvk=', 1);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (1, 'staff@techelevator.com', 'BL+14sAsqolI8T4lQ+ffqw==', 'YuKzbh82vgksVeslUcQ1gJiAMzFXGv28blgOk3KgLTVhftbbuqz2SWzwkqv+gLXUEqmdP6tQBS/NxsTJVQhvvNqBfaZ9kJPBlyCZNAuwth23yhCIt3bLU/RwvmG65nSNdM5ARRNNm8HzuTTS9EtyjNslC2MB/cmSdmnVsJhAU4E=', 2);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (2, 'student@student.com', '6/vkycA6BdN4q/I1bWVQ7Q==', 'fuYc0kJ2Fs/g1AF5xv+9CWvSRAAkG/60FzoO1+w26D1rvoc6V/8L1OdkD33mba6IMufmCHudsKZZuX/Cd4PrKP/+wlFFGriEJFgiIZdfgzalsY/0XEjRJiK5KQqbwAbupg3kuK2KO1Lo3eJhDUIUd73jXVNFUIT7gIo3CkJJLnI=', 3);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (3, 'employer@employer.com', 'YtvJSfy3cEB8AGONhku6Xw==', 'Wlj++XwPF6SNmIIxkRbT96Qd93bivw8Gs+TA6IpGWDpEcbhoRj7XmwFm1lZlWNNjBZlt5wbU2cJRyE9SPvxaK9dtZPPicz8TwjjM5Rwz/K8UGnjmEOcjTFVYx58pkYMiOx5j1PR17IZZ4J9j/DUxat8n6/JA7WQ8yqxuJf51NoU=', 4);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (4, 'adam@gmail.com', '6/vkycA6BdN4q/I1bWVQ7Q==', 'fuYc0kJ2Fs/g1AF5xv+9CWvSRAAkG/60FzoO1+w26D1rvoc6V/8L1OdkD33mba6IMufmCHudsKZZuX/Cd4PrKP/+wlFFGriEJFgiIZdfgzalsY/0XEjRJiK5KQqbwAbupg3kuK2KO1Lo3eJhDUIUd73jXVNFUIT7gIo3CkJJLnI=', 3);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (5, 'ben@gmail.com', '6/vkycA6BdN4q/I1bWVQ7Q==', 'fuYc0kJ2Fs/g1AF5xv+9CWvSRAAkG/60FzoO1+w26D1rvoc6V/8L1OdkD33mba6IMufmCHudsKZZuX/Cd4PrKP/+wlFFGriEJFgiIZdfgzalsY/0XEjRJiK5KQqbwAbupg3kuK2KO1Lo3eJhDUIUd73jXVNFUIT7gIo3CkJJLnI=', 3);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (6, 'cassie@gmail.com', '6/vkycA6BdN4q/I1bWVQ7Q==', 'fuYc0kJ2Fs/g1AF5xv+9CWvSRAAkG/60FzoO1+w26D1rvoc6V/8L1OdkD33mba6IMufmCHudsKZZuX/Cd4PrKP/+wlFFGriEJFgiIZdfgzalsY/0XEjRJiK5KQqbwAbupg3kuK2KO1Lo3eJhDUIUd73jXVNFUIT7gIo3CkJJLnI=', 3);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (7, 'dot@gmail.com', '6/vkycA6BdN4q/I1bWVQ7Q==', 'fuYc0kJ2Fs/g1AF5xv+9CWvSRAAkG/60FzoO1+w26D1rvoc6V/8L1OdkD33mba6IMufmCHudsKZZuX/Cd4PrKP/+wlFFGriEJFgiIZdfgzalsY/0XEjRJiK5KQqbwAbupg3kuK2KO1Lo3eJhDUIUd73jXVNFUIT7gIo3CkJJLnI=', 3);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (8, 'earl@gmail.com', '6/vkycA6BdN4q/I1bWVQ7Q==', 'fuYc0kJ2Fs/g1AF5xv+9CWvSRAAkG/60FzoO1+w26D1rvoc6V/8L1OdkD33mba6IMufmCHudsKZZuX/Cd4PrKP/+wlFFGriEJFgiIZdfgzalsY/0XEjRJiK5KQqbwAbupg3kuK2KO1Lo3eJhDUIUd73jXVNFUIT7gIo3CkJJLnI=', 3);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (13, 'fox@gmail.com', '6/vkycA6BdN4q/I1bWVQ7Q==', 'fuYc0kJ2Fs/g1AF5xv+9CWvSRAAkG/60FzoO1+w26D1rvoc6V/8L1OdkD33mba6IMufmCHudsKZZuX/Cd4PrKP/+wlFFGriEJFgiIZdfgzalsY/0XEjRJiK5KQqbwAbupg3kuK2KO1Lo3eJhDUIUd73jXVNFUIT7gIo3CkJJLnI=', 3);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (14, 'gina@gmail.com', '6/vkycA6BdN4q/I1bWVQ7Q==', 'fuYc0kJ2Fs/g1AF5xv+9CWvSRAAkG/60FzoO1+w26D1rvoc6V/8L1OdkD33mba6IMufmCHudsKZZuX/Cd4PrKP/+wlFFGriEJFgiIZdfgzalsY/0XEjRJiK5KQqbwAbupg3kuK2KO1Lo3eJhDUIUd73jXVNFUIT7gIo3CkJJLnI=', 3);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (9, 'april@babbleblab.com', 'YtvJSfy3cEB8AGONhku6Xw==', 'Wlj++XwPF6SNmIIxkRbT96Qd93bivw8Gs+TA6IpGWDpEcbhoRj7XmwFm1lZlWNNjBZlt5wbU2cJRyE9SPvxaK9dtZPPicz8TwjjM5Rwz/K8UGnjmEOcjTFVYx58pkYMiOx5j1PR17IZZ4J9j/DUxat8n6/JA7WQ8yqxuJf51NoU=', 4);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (10, 'brandon@skinder.com', 'YtvJSfy3cEB8AGONhku6Xw==', 'Wlj++XwPF6SNmIIxkRbT96Qd93bivw8Gs+TA6IpGWDpEcbhoRj7XmwFm1lZlWNNjBZlt5wbU2cJRyE9SPvxaK9dtZPPicz8TwjjM5Rwz/K8UGnjmEOcjTFVYx58pkYMiOx5j1PR17IZZ4J9j/DUxat8n6/JA7WQ8yqxuJf51NoU=', 4);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (11, 'cecil@ooba.com', 'YtvJSfy3cEB8AGONhku6Xw==', 'Wlj++XwPF6SNmIIxkRbT96Qd93bivw8Gs+TA6IpGWDpEcbhoRj7XmwFm1lZlWNNjBZlt5wbU2cJRyE9SPvxaK9dtZPPicz8TwjjM5Rwz/K8UGnjmEOcjTFVYx58pkYMiOx5j1PR17IZZ4J9j/DUxat8n6/JA7WQ8yqxuJf51NoU=', 4);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (12, 'darla@kwilith.com', 'YtvJSfy3cEB8AGONhku6Xw==', 'Wlj++XwPF6SNmIIxkRbT96Qd93bivw8Gs+TA6IpGWDpEcbhoRj7XmwFm1lZlWNNjBZlt5wbU2cJRyE9SPvxaK9dtZPPicz8TwjjM5Rwz/K8UGnjmEOcjTFVYx58pkYMiOx5j1PR17IZZ4J9j/DUxat8n6/JA7WQ8yqxuJf51NoU=', 4);
INSERT INTO users (user_id, email, hashedPassword, salt, type) VAlUES (15, 'emily@photolist.com', 'YtvJSfy3cEB8AGONhku6Xw==', 'Wlj++XwPF6SNmIIxkRbT96Qd93bivw8Gs+TA6IpGWDpEcbhoRj7XmwFm1lZlWNNjBZlt5wbU2cJRyE9SPvxaK9dtZPPicz8TwjjM5Rwz/K8UGnjmEOcjTFVYx58pkYMiOx5j1PR17IZZ4J9j/DUxat8n6/JA7WQ8yqxuJf51NoU=', 4);

INSERT INTO student_detail (user_id, first_name, last_name, cohort, language) VALUES(4, 'Adam', 'Merriman', 3, 'Java');
INSERT INTO student_detail (user_id, first_name, last_name, cohort, language) VALUES(5, 'Ben', 'Weinfurtner', 3, 'Java');
INSERT INTO student_detail (user_id, first_name, last_name, cohort, language) VALUES(6, 'Cassie', 'Watson', 3, 'Java');
INSERT INTO student_detail (user_id, first_name, last_name, cohort, language) VALUES(7, 'Dot', 'Eggplant', 3, 'Java');
INSERT INTO student_detail (user_id, first_name, last_name, cohort, language) VALUES(8,  'Earl', 'Chapman', 3, 'C#');
INSERT INTO student_detail (user_id, first_name, last_name, cohort, language) VALUES(13,  'Fox', 'Mulder', 3, 'C#');
INSERT INTO student_detail (user_id, first_name, last_name, cohort, language) VALUES(14,  'Gina', 'Gallant', 3, 'C#');

insert into employer_detail (employer_id, employer_name, company_description) values (1, 'Babbleblab', 'nulla justo aliquam quis turpis eget elit sodales scelerisque mauris sit');
insert into employer_detail (employer_id, employer_name, company_description) values (2, 'Skinder', 'dui luctus rutrum nulla tellus in sagittis dui vel nisl duis');
insert into employer_detail (employer_id, employer_name, company_description) values (3, 'Ooba', 'etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean');
insert into employer_detail (employer_id, employer_name, company_description) values (4, 'Kwilith', 'tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras');
insert into employer_detail (employer_id, employer_name, company_description) values (5, 'Photolist', 'consequat metus sapien ut nunc vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere');
insert into employer_detail (employer_id, employer_name, company_description) values (6, 'Kazu', 'penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque');
insert into employer_detail (employer_id, employer_name, company_description) values (7, 'Photospace', 'blandit lacinia erat vestibulum sed magna at nunc commodo placerat praesent blandit nam nulla integer pede');
insert into employer_detail (employer_id, employer_name, company_description) values (8, 'Zava', 'libero nam dui proin leo odio porttitor id consequat in');
insert into employer_detail (employer_id, employer_name, company_description) values (9, 'Jaloo', 'hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in');
insert into employer_detail (employer_id, employer_name, company_description) values (10, 'Topiczoom', 'adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue');
insert into employer_detail (employer_id, employer_name, company_description) values (11, 'Oyoba', 'urna ut tellus nulla ut erat id mauris vulputate elementum nullam varius');
insert into employer_detail (employer_id, employer_name, company_description) values (12, 'Tagpad', 'at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis');
insert into employer_detail (employer_id, employer_name, company_description) values (13, 'Shufflester', 'nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit vivamus');
insert into employer_detail (employer_id, employer_name, company_description) values (14, 'Yodo', 'pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum');
insert into employer_detail (employer_id, employer_name, company_description) values (15, 'Gigabox', 'ut nulla sed accumsan felis ut at dolor quis odio');
insert into employer_detail (employer_id, employer_name, company_description) values (16, 'Bluezoom', 'pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia');
insert into employer_detail (employer_id, employer_name, company_description) values (17, 'Fanoodle', 'montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa');
insert into employer_detail (employer_id, employer_name, company_description) values (18, 'Browsebug', 'eget eleifend luctus ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in');
insert into employer_detail (employer_id, employer_name, company_description) values (19, 'Blogtag', 'pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id');
insert into employer_detail (employer_id, employer_name, company_description) values (20, 'Wordware', 'eget massa tempor convallis nulla neque libero convallis eget eleifend luctus ultricies eu nibh quisque');
	INSERT INTO staff_detail(user_id, first_name, last_name) VALUES(1, 'Tiffany', 'Lardomita');
INSERT INTO user_employer (user_id, employer_id) VALUES (9, 1);
INSERT INTO user_employer (user_id, employer_id) VALUES (10, 2);
INSERT INTO user_employer (user_id, employer_id) VALUES (11, 3);
INSERT INTO user_employer (user_id, employer_id) VALUES (12, 4);
INSERT INTO user_employer (user_id, employer_id) VALUES (15, 5);
INSERT INTO cohorts (cohort, start_date, end_date) VALUES(0, '2015-09-01', '2015-12-09');
INSERT INTO cohorts (cohort, start_date, end_date) VALUES(1, '2016-01-01', '2016-04-09');
INSERT INTO cohorts (cohort, start_date, end_date) VALUES(2, '2016-05-01', '2016-08-09');
INSERT INTO cohorts (cohort, start_date, end_date) VALUES(3, '2016-09-01', '2016-12-31');
INSERT INTO events(event_id, day, name, start_time, end_time, interview_length) VALUES(1, '12-15-2016', 'Fall Matchmaking Event', '09:00:00', '10:00:00', 25);
INSERT INTO events(event_id, day, name, start_time, end_time, interview_length) VALUES(2, '12-22-2016', 'Fall Matchmaking Bonanza', '09:00:00', '12:00:00', 30);


INSERT INTO attendees(attendee_id, attendee_name, employer_id) VALUES(1, 'employer', 1);
INSERT INTO attendees(attendee_id, attendee_name, employer_id) VALUES(2, 'employer', 2);
INSERT INTO attendees(attendee_id, attendee_name, employer_id) VALUES(3, 'employer', 3);
INSERT INTO attendees(attendee_id, attendee_name, employer_id) VALUES(4, 'employer', 4);
INSERT INTO attendees(attendee_id, attendee_name, employer_id) VALUES(5, 'employer', 5);


INSERT INTO events_attendees(event_id, attendee_id) VALUES(1,1);
INSERT INTO events_attendees(event_id, attendee_id) VALUES(1,2);
INSERT INTO events_attendees(event_id, attendee_id) VALUES(1,3);

INSERT INTO events_attendees(event_id, attendee_id) VALUES(2,1);
INSERT INTO events_attendees(event_id, attendee_id) VALUES(2,2);
INSERT INTO events_attendees(event_id, attendee_id) VALUES(2,3);
INSERT INTO events_attendees(event_id, attendee_id) VALUES(2,4);
INSERT INTO events_attendees(event_id, attendee_id) VALUES(2,5);


ALTER TABLE staff_detail
ADD FOREIGN KEY(user_id)
REFERENCES users(user_id);

ALTER TABLE student_detail
ADD FOREIGN KEY(user_id)
REFERENCES users(user_id);

ALTER TABLE user_employer
ADD FOREIGN KEY(user_id)
REFERENCES users(user_id);

ALTER TABLE user_employer
ADD FOREIGN KEY(employer_id)
REFERENCES employer_detail(employer_id);

ALTER TABLE attendees
ADD FOREIGN KEY(employer_id)
REFERENCES employer_detail(employer_id);

ALTER TABLE events_attendees
ADD FOREIGN KEY(event_id)
REFERENCES events(event_id);

ALTER TABLE events_attendees
ADD FOREIGN KEY(attendee_id)
REFERENCES attendees(attendee_id);

CREATE OR REPLACE FUNCTION pseudo_encrypt(VALUE bigint) returns int AS $$
DECLARE
l1 int;
l2 int;
r1 int;
r2 int;
i int:=0;
BEGIN
 l1:= (VALUE >> 16) & 65535;
 r1:= VALUE & 65535;
 WHILE i < 3 LOOP
   l2 := r1;
   r2 := l1 # ((((1366 * r1 + 150889) % 714025) / 714025.0) * 32767)::int;
   l1 := l2;
   r1 := r2;
   i := i + 1;
 END LOOP;
 RETURN ((r1 << 16) + l1);
END;
$$ LANGUAGE plpgsql strict immutable;

COMMIT;