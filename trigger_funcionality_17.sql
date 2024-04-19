CREATE OR REPLACE FUNCTION query_log_report()
RETURNS TABLE(user_name TEXT, query_type TEXT, query_statement TEXT, query_count INTEGER) AS $$
BEGIN
    RETURN QUERY
    SELECT user_name, query_type, query_statement, COUNT(*) as query_count
    FROM queries_log
    GROUP BY user_name, query_type, query_statement
    ORDER BY query_count DESC;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION query_modification_log()
RETURNS TRIGGER AS $$
DECLARE
    currentDate DATE;
    currentTime TIME;
    user_id_m INTEGER;
    user_name_m TEXT;
    query_type_m TEXT;
BEGIN
    currentDate := CURRENT_DATE;
    currentTime := CURRENT_TIME;
    SELECT CURRENT_USER into user_name_m;

    IF TG_OP = 'INSERT' THEN
        query_type_m := 'Inserción';
        user_id_m := NEW.user_id;
        user_name_m := NEW.name;
    ELSIF TG_OP = 'UPDATE' THEN
        query_type_m = 'Actualización';
        user_id_m := NEW.user_id;
        user_name_m := NEW.name;
    ELSIF TG_OP = 'DELETE' THEN
        query_type_m = 'Eliminación';
        user_id_m := OLD.user_id;
        user_name_m := OLD.name;
    END IF;

    INSERT INTO queries_log (user_id, user_name, call_date, call_time, query_type, query_statement)
    VALUES (user_id_m, user_name_m, currentDate, currentTime, query_type_m, TG_SQL_STATEMENT::TEXT);

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