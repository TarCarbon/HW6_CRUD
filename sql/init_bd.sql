CREATE TABLE worker (id INT PRIMARY KEY AUTO_INCREMENT,
					name VARCHAR(1000) NOT NULL CHECK(LENGTH(name)>=2), 
					birthday DATE CHECK(birthday >= '1900-01-01'), 
					level VARCHAR(7) CHECK(level IN('Trainee', 'Junior', 'Middle', 'Senior')), 
					salary INT NOT NULL CHECK(100<=salary and salary<=100000));

CREATE TABLE client (id INT PRIMARY KEY AUTO_INCREMENT, 
					name VARCHAR(1000) NOT NULL CHECK(LENGTH(name)>=2));

CREATE TABLE project (id INT PRIMARY KEY AUTO_INCREMENT, 
					client_id BIGINT,
					start_date DATE,
					finish_date DATE,
					 FOREIGN KEY (client_id) REFERENCES client(id)); 

CREATE TABLE project_worker (project_id BIGINT,
							worker_id BIGINT, 
							PRIMARY KEY(project_id, worker_id),
							FOREIGN KEY(project_id) REFERENCES project(id), 
							FOREIGN KEY (worker_id) REFERENCES worker(id));

