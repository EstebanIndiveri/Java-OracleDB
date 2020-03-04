--stored procedure que actualiza el salario de un id_empleado
--segun el factor de incremento proporcionado
CREATE OR REPLACE PROCEDURE "HR"."SET_EMPLOYEE_SALARY"(
p_emp_id IN employees.employee_id%TYPE,
p_factor IN NUMBER
)AS
v_monthly_salary employees.salary%TYPE;
BEGIN
--Obtiene el salario actual del ic_empleado proporcionado
SELECT NVL(salary,-999)
INTO v_monthly_salary
FROM employees
Where employee_id=p_emp_id;
--si existe el registro, se actualiza su salacio con el factor proporcionado
IF(v_monthly_salary!=-999)THEN
UPDATE employees SET salary=salary * p_factor WHERE employee_id=p_emp_id;
COMMIT;
DBMS_OUTPUT.PUT_LINE('Termino el programa, salary:' || v_monthly_salary);
END IF;
END;