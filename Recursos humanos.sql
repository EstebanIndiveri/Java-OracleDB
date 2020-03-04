CREATE OR REPLACE FUNCTION "HR"."GET_EMPLOYEE_SALARY"(p_emp_id IN employees.employee_id%TYPE)
RETURN employees.salary%TYPE
AS
v_monthly_salary employees.salary%TYPE;
BEGIN
--ejecuta un select para obtener el salario actual para
--el id_empleado proporcionado
SELECT NVL(salary,-999)
INTO v_monthly_salary
FROM employees
WHERE
employee_id=p_emp_id;
RETURN v_monthly_salary;
END;
/