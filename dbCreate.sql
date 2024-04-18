-- Tablas que no tienen relaciones
CREATE TABLE Type_User (
    Type_User_id INT NOT NULL,
    name CHAR(50) NOT NULL,
    CRUD_emergencies BOOLEAN NOT NULL,
    CRUD_tasks BOOLEAN NOT NULL,
    CRUD_tasks_states BOOLEAN NOT NULL,
    assign_volunteers BOOLEAN NOT NULL,
    PRIMARY KEY (Type_User_id)
);

CREATE TABLE Skill (
    skill_id SERIAL,
    skill_name CHAR(100) NOT NULL,
    PRIMARY KEY (skill_id)
);

CREATE TABLE Institution (
    institution_id SERIAL,
    institution_name CHAR(50) NOT NULL,
    PRIMARY KEY (institution_id)
);

-- Tablas con relaciones directas
CREATE TABLE UserM (
    User_id SERIAL,
    type_user_id INT NOT NULL,
    name CHAR(50) NOT NULL,
    password CHAR(50) NOT NULL,
    email CHAR(50) NOT NULL,
    phone CHAR(50) NOT NULL,
    PRIMARY KEY (User_id),
    FOREIGN KEY (type_user_id) REFERENCES Type_User (Type_User_id)
);

CREATE TABLE Task_state (
    task_state_id SERIAL,
    user_id INT NOT NULL,
    state CHAR(15) NOT NULL,
    description CHAR(200),
    PRIMARY KEY (task_state_id),
    FOREIGN KEY (user_id) REFERENCES UserM (User_id)
);

CREATE TABLE Volunteer (
    volunteer_id SERIAL,
    user_id INT NOT NULL,
    disponibility BOOLEAN NOT NULL,
    PRIMARY KEY (volunteer_id),
    FOREIGN KEY (user_id) REFERENCES UserM (User_id)
);

-- Tablas con relaciones más complejas


CREATE TABLE Vol_skill (
    vol_skill_id SERIAL,
    volunteer_id INT NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (vol_skill_id),
    FOREIGN KEY (volunteer_id) REFERENCES Volunteer (volunteer_id),
    FOREIGN KEY (skill_id) REFERENCES Skill (skill_id)
);

CREATE TABLE Coordinator (
    coordinator_id SERIAL,
    institution_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (coordinator_id),
    FOREIGN KEY (institution_id) REFERENCES Institution (institution_id),
    FOREIGN KEY (user_id) REFERENCES UserM (User_id)
);

CREATE TABLE Emergency (
    emergency_id SERIAL,
    institution_id INT NOT NULL,
	coordinator_id INT NOT NULL,
    name CHAR(100) NOT NULL,
	emergency_state char(20),
    PRIMARY KEY (emergency_id),
    FOREIGN KEY (institution_id) REFERENCES Institution (institution_id),
	FOREIGN KEY (coordinator_id) REFERENCES Coordinator (coordinator_id)
	
);

CREATE TABLE Eme_skill (
    eme_skill_id SERIAL,
    emergency_id INT NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (eme_skill_id),
    FOREIGN KEY (emergency_id) REFERENCES Emergency (emergency_id),
    FOREIGN KEY (skill_id) REFERENCES Skill (skill_id)
);

CREATE TABLE Task_skill (
    task_skill_id SERIAL,
    eme_skill_id INT NOT NULL,
    PRIMARY KEY (task_skill_id),
    FOREIGN KEY (eme_skill_id) REFERENCES Eme_skill (eme_skill_id)
);

CREATE TABLE Task (
    task_id SERIAL,
    emergency_id INT NOT NULL,
    task_state_id INT NOT NULL,
    task_skill_id INT NOT NULL,
    task_name CHAR(50) NOT NULL,
    PRIMARY KEY (task_id),
    FOREIGN KEY (emergency_id) REFERENCES Emergency (emergency_id),
    FOREIGN KEY (task_state_id) REFERENCES Task_state (task_state_id),
    FOREIGN KEY (task_skill_id) REFERENCES Task_skill (task_skill_id)
);

CREATE TABLE Ranking (
    ranking_id SERIAL,
    volunteer_id INT NOT NULL,
    task_id INT NOT NULL,
    grade DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (ranking_id),
    FOREIGN KEY (volunteer_id) REFERENCES Volunteer (volunteer_id),
    FOREIGN KEY (task_id) REFERENCES Task (task_id)
);

CREATE TABLE Emergency_log (
    emergency_log_id SERIAL,
    coordinator_id INT NOT NULL,
    emergency_id INT NOT NULL,
    description CHAR(200) NOT NULL,
    date_change DATE NOT NULL,
    hour_change TIME NOT NULL,
    PRIMARY KEY (emergency_log_id),
    FOREIGN KEY (coordinator_id) REFERENCES Coordinator (coordinator_id),
    FOREIGN KEY (emergency_id) REFERENCES Emergency (emergency_id)
);

CREATE TABLE Task_log (
    task_log_id SERIAL,
    coordinator_id INT NOT NULL,
    task_id INT NOT NULL,
    description CHAR(200) NOT NULL,
    date_change DATE NOT NULL,
    hour_change TIME NOT NULL,
    PRIMARY KEY (task_log_id),
    FOREIGN KEY (coordinator_id) REFERENCES Coordinator (coordinator_id),
    FOREIGN KEY (task_id) REFERENCES Task (task_id)
);

CREATE TABLE Queries_log (
    query_log_id SERIAL,
    user_id INT NOT NULL,
    user_name CHAR(50) NOT NULL,
    call_date DATE NOT NULL,
    call_time TIME NOT NULL,
    query_type CHAR(50) NOT NULL,
	query_statement CHAR(200) NOT NULL,
    PRIMARY KEY (query_log_id),
    FOREIGN KEY (user_id) REFERENCES Userm (user_id)
);
