CREATE OR REPLACE FUNCTION query_modification_log() 
RETURNS TRIGGER AS $$ 
DECLARE     
	currentDate DATE;     
	currentTime TIME;     
	query_type_m TEXT; 
BEGIN     
	currentDate := CURRENT_DATE;
	currentTime := CURRENT_TIME;

	IF TG_OP = 'INSERT' THEN
		query_type_m := 'Inserción';
	ELSIF TG_OP = 'UPDATE' THEN
		query_type_m = 'Actualización';
	ELSIF TG_OP = 'DELETE' THEN
		query_type_m = 'Eliminación';
	END IF;

	INSERT INTO queries_log (user_name, call_date, call_time, query_type, query_statement)
	VALUES (CURRENT_USER, currentDate, currentTime, query_type_m, CURRENT_QUERY());

	RETURN NULL;
END;
$$ LANGUAGE plpgsql; 

-- coordinator 
CREATE TRIGGER query_log_trigger_coordinator
AFTER INSERT OR UPDATE OR DELETE ON coordinator 
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log(); 

-- emergency 
CREATE TRIGGER query_log_trigger_emergency
AFTER INSERT OR UPDATE OR DELETE ON emergency
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log(); 

-- institution 
CREATE TRIGGER query_log_trigger_institution
AFTER INSERT OR UPDATE OR DELETE ON institution
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log(); 

-- ranking 
CREATE TRIGGER query_log_trigger_ranking
AFTER INSERT OR UPDATE OR DELETE ON ranking
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log(); 

-- skill 
CREATE TRIGGER query_log_trigger_skill
AFTER INSERT OR UPDATE OR DELETE ON skill 
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log(); 

-- task 
CREATE TRIGGER query_log_trigger_task
AFTER INSERT OR UPDATE OR DELETE ON task
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log(); 

-- task_state 
CREATE TRIGGER query_log_trigger_task_state
AFTER INSERT OR UPDATE OR DELETE ON task_state 
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log(); 

-- type_user 
CREATE TRIGGER query_log_trigger_type_user
AFTER INSERT OR UPDATE OR DELETE ON type_user
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log(); 

-- userm 
CREATE TRIGGER query_log_trigger_userm
AFTER INSERT OR UPDATE OR DELETE ON userm
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log(); 

-- volunteer 
CREATE TRIGGER query_log_trigger_volunteer
AFTER INSERT OR UPDATE OR DELETE ON volunteer
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log();
