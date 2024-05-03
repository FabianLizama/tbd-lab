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
('Medical Assistance'),
('Swimming'),
('Construction'),
('Communication'),
('Navigation'),
('Leadership'),
('Problem Solving'),
('Teamwork'),
('Emergency Response'),
('Driving'),
('Cooking'),
('Electrical Repair'),
('Mechanical Repair');

-- Población de datos para Institution
INSERT INTO Institution (institution_name)
VALUES 
('Red Cross'),
('Fire Department'),
('Medical Center'),
('Police Department'),
('Civil Protection');

-- Población de datos para UserM
INSERT INTO UserM (type_user_id, name, password, email, phone)
VALUES 
((SELECT Type_User_id FROM Type_User WHERE name = 'Admin'), 'Felipito', 'admin123', 'felipito@gmail.com', '123456789'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Coordinator'), 'Camille', 'coord123', 'camille@gmail.com', '987654321'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Coordinator'), 'Pedro', 'coord456', 'pedro1@gmail.com', '987654322'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Volunteer'), 'Luis', 'volunteer123', 'luismi@gmail.com', '555555555'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Volunteer'), 'Tania', 'volunteer456', 'taniaup@gmail.com', '666666666'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Volunteer'), 'Sofia', 'Volunteer258', 'sofia@example.com', '111222333'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Volunteer'), 'Daniel', 'volunteer147', 'daniel@example.com', '444555666'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Coordinator'), 'Jose', 'coord111', 'jose1@gmail.com', '458461523'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Coordinator'), 'Fito', 'coord222', 'fito1@gmail.com', '458461453'),
((SELECT Type_User_id FROM Type_User WHERE name = 'Coordinator'), 'Ariel', 'coord333', 'Ariel@gmail.com', '442461523');



-- Población de datos para Task_state
INSERT INTO Task_state (user_id, state, description)
VALUES
((SELECT User_id FROM UserM WHERE name = 'Luis'), 'Pending', 'Task has not yet been started'),
((SELECT User_id FROM UserM WHERE name = 'Luis'),'In Progress', 'Task is currently being worked on'),
((SELECT User_id FROM UserM WHERE name = 'Tania'),'Completed', 'Task has been completed'),
((SELECT User_id FROM UserM WHERE name = 'Sofia'),'In Progress', 'Task is currently being worked on'),
((SELECT User_id FROM UserM WHERE name = 'Daniel'),'Pending', 'Task has not yet been started');

-- Población de datos para Volunteer
INSERT INTO Volunteer (user_id, disponibility)
VALUES 
((SELECT User_id FROM UserM WHERE name = 'Luis'), TRUE),
((SELECT User_id FROM UserM WHERE name = 'Tania'), TRUE),
((SELECT User_id FROM UserM WHERE name = 'Sofia'), TRUE),
((SELECT User_id FROM UserM WHERE name = 'Daniel'), TRUE);

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
((SELECT institution_id FROM Institution WHERE institution_name = 'Fire Department'), (SELECT User_id FROM UserM WHERE name = 'Pedro')),
((SELECT institution_id FROM Institution WHERE institution_name = 'Medical Center'), (SELECT User_id FROM UserM WHERE name = 'Jose')),
((SELECT institution_id FROM Institution WHERE institution_name = 'Police Department'), (SELECT User_id FROM UserM WHERE name = 'Fito')),
((SELECT institution_id FROM Institution WHERE institution_name = 'Civil Protection'), (SELECT User_id FROM UserM WHERE name = 'Ariel'));

-- Población de datos para Emergency
INSERT INTO Emergency (institution_id, coordinator_id, name, emergency_state, latitude, longitude)
VALUES 
((SELECT institution_id FROM Institution WHERE institution_name = 'Red Cross'), (SELECT coordinator_id FROM Coordinator c, Institution i WHERE i.institution_name = 'Red Cross' AND i.institution_id = c.institution_id), 'Fire in downtown', 'Active', -33.4372, -70.6506),
((SELECT institution_id FROM Institution WHERE institution_name = 'Fire Department'), (SELECT coordinator_id FROM Coordinator c, Institution i WHERE i.institution_name = 'Fire Department' AND i.institution_id = c.institution_id), 'Earthquake in suburbs', 'Active', -33.4489, -70.6679),
((SELECT institution_id FROM Institution WHERE institution_name = 'Medical Center'), (SELECT coordinator_id FROM Coordinator c, Institution i WHERE i.institution_name = 'Medical Center' AND i.institution_id = c.institution_id), 'Flood in the city', 'Active', -33.4569, -70.6483),
((SELECT institution_id FROM Institution WHERE institution_name = 'Police Department'), (SELECT coordinator_id FROM Coordinator c, Institution i WHERE i.institution_name = 'Police Department' AND i.institution_id = c.institution_id), 'Tsunami in the coast', 'Active', -33.4691, -70.6420),
((SELECT institution_id FROM Institution WHERE institution_name = 'Civil Protection'), (SELECT coordinator_id FROM Coordinator c, Institution i WHERE i.institution_name = 'Civil Protection' AND i.institution_id = c.institution_id), 'Hurricane in the city', 'Active', -33.4195, -70.6058);

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
INSERT INTO Task (emergency_id, task_state_id, task_skill_id, task_name, latitude, longitude)
VALUES 
((SELECT emergency_id FROM Emergency WHERE name = 'Fire in downtown' LIMIT 1), (SELECT task_state_id FROM Task_state WHERE state = 'Pending' LIMIT 1), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Evacuate building', -33.4372, -70.6506),
((SELECT emergency_id FROM Emergency WHERE name = 'Fire in downtown' LIMIT 1), (SELECT task_state_id FROM Task_state WHERE state = 'In Progress' LIMIT 1), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Administer first aid', -33.4372, -70.6506),
((SELECT emergency_id FROM Emergency WHERE name = 'Earthquake in suburbs' LIMIT 1), (SELECT task_state_id FROM Task_state WHERE state = 'Pending' LIMIT 1), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Rescue trapped people', -33.4489, -70.6679),
((SELECT emergency_id FROM Emergency WHERE name = 'Earthquake in suburbs' LIMIT 1), (SELECT task_state_id FROM Task_state WHERE state = 'In Progress' LIMIT 1), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Provide medical assistance', -33.4489, -70.6679),
((SELECT emergency_id FROM Emergency WHERE name = 'Flood in the city' LIMIT 1), (SELECT task_state_id FROM Task_state WHERE state = 'Pending' LIMIT 1), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Evacuate flooded areas', -33.4569, -70.6483),
((SELECT emergency_id FROM Emergency WHERE name = 'Flood in the city' LIMIT 1), (SELECT task_state_id FROM Task_state WHERE state = 'In Progress' LIMIT 1), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Provide shelter to affected people', -33.4569, -70.6483),
((SELECT emergency_id FROM Emergency WHERE name = 'Tsunami in the coast' LIMIT 1), (SELECT task_state_id FROM Task_state WHERE state = 'Pending' LIMIT 1), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Evacuate coastal areas', -33.4691, -70.6420),
((SELECT emergency_id FROM Emergency WHERE name = 'Tsunami in the coast' LIMIT 1), (SELECT task_state_id FROM Task_state WHERE state = 'In Progress' LIMIT 1), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Provide food to affected people', -33.4691, -70.6420),
((SELECT emergency_id FROM Emergency WHERE name = 'Hurricane in the city' LIMIT 1), (SELECT task_state_id FROM Task_state WHERE state = 'Pending' LIMIT 1), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Evacuate hurricane path', -33.4195, -70.6058),
((SELECT emergency_id FROM Emergency WHERE name = 'Hurricane in the city' LIMIT 1), (SELECT task_state_id FROM Task_state WHERE state = 'In Progress' LIMIT 1), (SELECT task_skill_id FROM Task_skill ORDER BY RANDOM() LIMIT 1), 'Provide medical assistance to affected people', -33.4195, -70.6058);




-- Población de datos para Ranking (Asignamos evaluaciones aleatorias a los voluntarios) del 1 al 10
INSERT INTO Ranking (volunteer_id, task_id, grade, accepted)
SELECT Volunteer.volunteer_id, Task.task_id, (RANDOM() * 10) + 1, CASE WHEN ((RANDOM() * 10) + 1) >= 2.0 THEN TRUE ELSE FALSE END
FROM Volunteer
CROSS JOIN Task
ORDER BY RANDOM()
LIMIT (SELECT COUNT(*) FROM Volunteer);

-- Población de datos para Emergency_log
INSERT INTO Emergency_log (coordinator_id, emergency_id, description, date_change, hour_change)
VALUES 
((SELECT coordinator_id FROM Emergency WHERE name = 'Fire in downtown'), (SELECT emergency_id FROM Emergency WHERE name = 'Fire in downtown'), 'Updated emergency status', CURRENT_DATE, CURRENT_TIME),
((SELECT coordinator_id FROM Emergency WHERE name = 'Earthquake in suburbs'), (SELECT emergency_id FROM Emergency WHERE name = 'Earthquake in suburbs'), 'New emergency reported', CURRENT_DATE, CURRENT_TIME),
((SELECT coordinator_id FROM Emergency WHERE name = 'Flood in the city'), (SELECT emergency_id FROM Emergency WHERE name = 'Flood in the city'), 'Emergency status updated', CURRENT_DATE, CURRENT_TIME),
((SELECT coordinator_id FROM Emergency WHERE name = 'Tsunami in the coast'), (SELECT emergency_id FROM Emergency WHERE name = 'Tsunami in the coast'), 'Emergency status updated', CURRENT_DATE, CURRENT_TIME),
((SELECT coordinator_id FROM Emergency WHERE name = 'Hurricane in the city'), (SELECT emergency_id FROM Emergency WHERE name = 'Hurricane in the city'), 'Emergency status updated', CURRENT_DATE, CURRENT_TIME);

-- Población de datos para Task_log
INSERT INTO Task_log (coordinator_id, task_id, description, date_change, hour_change)
VALUES 
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Evacuate building' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Evacuate building'), 'Task started', CURRENT_DATE, CURRENT_TIME),
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Administer first aid' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Administer first aid'), 'Task completed', CURRENT_DATE, CURRENT_TIME),
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Rescue trapped people' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Rescue trapped people'), 'Task started', CURRENT_DATE, CURRENT_TIME),
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Provide medical assistance' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Provide medical assistance'), 'Task completed', CURRENT_DATE, CURRENT_TIME),
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Evacuate flooded areas' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Evacuate flooded areas'), 'Task started', CURRENT_DATE, CURRENT_TIME),
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Provide shelter to affected people' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Provide shelter to affected people'), 'Task completed', CURRENT_DATE, CURRENT_TIME),
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Evacuate coastal areas' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Evacuate coastal areas'), 'Task started', CURRENT_DATE, CURRENT_TIME),
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Provide food to affected people' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Provide food to affected people'), 'Task completed', CURRENT_DATE, CURRENT_TIME),
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Evacuate hurricane path' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Evacuate hurricane path'), 'Task started', CURRENT_DATE, CURRENT_TIME),
((SELECT e.coordinator_id FROM Emergency e, Task ts WHERE ts.task_name = 'Provide medical assistance to affected people' AND ts.emergency_id = e.emergency_id), (SELECT task_id FROM Task WHERE task_name = 'Provide medical assistance to affected people'), 'Task completed', CURRENT_DATE, CURRENT_TIME);