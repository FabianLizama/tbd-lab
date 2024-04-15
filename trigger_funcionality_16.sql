CREATE OR REPLACE FUNCTION query_modification_log()
RETURNS TRIGGER AS $$
DECLARE
    currentDate DATE;
    currentTime TIME;
    user_id_m INTEGER;
    user_name_m TEXT;
    query_type_m INTEGER;
BEGIN
    -- Obtain time and date
    currentDate := CURRENT_DATE;
    currentTime := CURRENT_TIME;
    -- 
    SELECT CURRENT_USER into user_name_m;

    IF TG_OP = 'INSERT' THEN
        query_type_m := 'Inserción';
        user_id_m := NEW.user_id;
        user_name_m := NEW.name;
    ELSIF TG_OP = 'UPDATE' THEN
        query_type_m = 'Actualización';
        user_id_m := NEW.user_id;
        user_name_m := NEW.name;
    ELSE
        query_type_m = 'Eliminación';
        user_id_m := OLD.user_id;
        user_name_m := OLD.name;
    END IF;

    INSERT INTO queries_log (user_id, user_name, call_date, call_time, query_type)
    VALUES (user_id_m, user_name_m, currentDate, currentTime, query_type_m);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER query_log_trigger
AFTER INSERT OR UPDATE OR DELETE ON UserM
FOR EACH STATEMENT
EXECUTE FUNCTION query_modification_log();
