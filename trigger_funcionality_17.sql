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


