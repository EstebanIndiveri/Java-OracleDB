CREATE OR REPLACE PACKAGE "REF_CURSOR_PACKAGE"
AS
TYPE t_ref_cursor IS REF CURSOR;
FUNCTION get_dept_ref_cursor(p_dept_id INTEGER)RETURN t_ref_cursor;
END;
/
CREATE OR REPLACE PACKAGE BODY ref_cursor_package
AS
FUNCTION get_dept_ref_cursor(p_dept_id INTEGER)
RETURN t_ref_cursor IS

dept_ref_cursor t_ref_cursor;

BEGIN

OPEN dept_ref_cursor FOR
SELECT department_id,department_name,location_id
FROM departments
WHERE department_id>p_dept_id
ORDER BY department_id;

RETURN dept_ref_cursor;
END get_dept_ref_cursor;
END ref_cursor_package;
/
