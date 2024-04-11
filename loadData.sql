-- Población de datos para Type_User
INSERT INTO Type_User (Type_User_id, name, CRUD_emergencies, CRUD_tasks, CRUD_tasks_states, assign_volunteers)
VALUES 
(2, 'Admin', TRUE, TRUE, TRUE, TRUE),
(0, 'Coordinator', TRUE, TRUE, TRUE, TRUE),
(1, 'Volunteer', FALSE, TRUE, FALSE, FALSE);

-- Población de datos para Skill
INSERT INTO Skill (skill_name)
VALUES 
('First Aid'),
('CPR'),
('Firefighting'),
('Search and Rescue'),
('Medical Assistance');

-- Población de datos para Task_state
INSERT INTO Task_state (state, description)
VALUES 
('Pending', 'Task has not yet been started'),
('InProgress', 'Task is currently being worked on'),
('Completed', 'Task has been completed');

-- Población de datos para Institution
INSERT INTO Institution (institution_name)
VALUES 
('Red Cross'),
('Fire Department'),
('Medical Center');

-- Población de datos para UserM
INSERT INTO UserM (type_user_id, name, password, email, phone)
VALUES 
((SELECT Type_User_id FROM Type_User WHERE name = 'Admin'), 'Felipito', 'admin123', 'felipito@gmail.com', '123456789'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Coordinator'), 'Camille', 'coord123', 'camille@gmail.com', '987654321'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Coordinator'), 'Pedro', 'coord456', 'pedro1@gmail.com', '987654322'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Volunteer'), 'Luis', 'volunteer123', 'luismi@gmail.com', '555555555'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Volunteer'), 'Tania', 'volunteer456', 'taniaup@gmail.com', '666666666');

-- Población de datos para Volunteer
INSERT INTO Volunteer (user_id, disponibility)
VALUES 
((SELECT User_id FROM UserM WHERE name = 'Luis'), TRUE),
((SELECT User_id FROM UserM WHERE name = 'Tania'), TRUE);

-- Población de datos para Vol_skill (Asignamos habilidades aleatorias a los voluntarios)
INSERT INTO Vol_skill (volunteer_id, skill_id)
SELECT Volunteer.volunteer_id, Skill.skill_id
FROM Volunteer
CROSS JOIN Skill
ORDER BY RANDOM()
LIMIT (SELECT COUNT(*) FROM Volunteer);

-- Población de datos para Coordinator
INSERT INTO Coordinator (institution_id, user_id)
VALUES 
((SELECT institution_id FROM Institution WHERE institution_name = 'Red Cross'), (SELECT User_id FROM UserM WHERE name = 'Camille')),
((SELECT institution_id FROM Institution WHERE institution_name = 'Fire Department'), (SELECT User_id FROM UserM WHERE name = 'Pedro'));

-- Población de datos para Emergency
INSERT INTO Emergency (institution_id, coordinator_id, name, emergency_state)
VALUES 
((SELECT institution_id FROM Institution WHERE institution_name = 'Red Cross'), (SELECT coordinator_id FROM Coordinator c, Institution i WHERE i.institution_name = 'Red Cross' AND i.institution_id = c.institution_id), 'Fire in downtown', 'Active'),
((SELECT institution_id FROM Institution WHERE institution_name = 'Fire Department'), (SELECT coordinator_id FROM Coordinator c, Institution i WHERE i.institution_name = 'Fire Department' AND i.institution_id = c.institution_id), 'Earthquake in suburbs', 'Active');

-- Población de datos para Eme_skill (Asignamos habilidades aleatorias a las emergencias)
INSERT INTO Eme_skill (emergency_id, skill_id)
SELECT Emergency.emergency_id, Skill.skill_id
FROM Emergency
CROSS JOIN Skill
ORDER BY RANDOM()
LIMIT (SELECT COUNT(*) FROM Emergency);

-- Población de datos para Task_skill (Asignamos habilidades aleatorias a las tareas)
INSERT INTO Task_skill (eme_skill_id)
SELECT eme_skill_id
FROM Eme_skill;

-- Población de datos para Task
INSERT INTO Task (emergency_id, task_state_id, task_skill_id, task_name)
VALUES 
((SELECT emergency_id FROM Emergency WHERE name = 'Fire in downtown'), (SELECT task_state_id FROM Task_state WHERE state = 'Pending'), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Evacuate building'),
((SELECT emergency_id FROM Emergency WHERE name = 'Fire in downtown'), (SELECT task_state_id FROM Task_state WHERE state = 'InProgress'), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Administer first aid'),
((SELECT emergency_id FROM Emergency WHERE name = 'Earthquake in suburbs'), (SELECT task_state_id FROM Task_state WHERE state = 'Pending'), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Rescue trapped people'),
((SELECT emergency_id FROM Emergency WHERE name = 'Earthquake in suburbs'), (SELECT task_state_id FROM Task_state WHERE state = 'InProgress'), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Provide medical assistance');

-- Población de datos para Ranking (Asignamos evaluaciones aleatorias a los voluntarios)
INSERT INTO Ranking (volunteer_id, task_id, grade)
SELECT Volunteer.volunteer_id, Task.task_id, RANDOM() * 10
FROM Volunteer
CROSS JOIN Task;

-- Población de datos para Emergency_log
INSERT INTO Emergency_log (coordinator_id, emergency_id, description, date_change, hour_change)
VALUES 
((SELECT coordinator_id FROM Emergency WHERE name = 'Fire in downtown'), (SELECT emergency_id FROM Emergency WHERE name = 'Fire in downtown'), 'Updated emergency status', CURRENT_DATE, CURRENT_TIME),
((SELECT coordinator_id FROM Emergency WHERE name = 'Earthquake in suburbs'), (SELECT emergency_id FROM Emergency WHERE name = 'Earthquake in suburbs'), 'New emergency reported', CURRENT_DATE, CURRENT_TIME);

-- Población de datos para Task_log
INSERT INTO Task_log (coordinator_id, task_id, description, date_change, hour_change)
VALUES 
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Evacuate building' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Evacuate building'), 'Task started', CURRENT_DATE, CURRENT_TIME),
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Administer first aid' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Administer first aid'), 'Task completed', CURRENT_DATE, CURRENT_TIME);
