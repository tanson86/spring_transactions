package com.example.demo.util;

import com.example.demo.entities.Employee;

public class EmployeeUtil {

    public static Employee getEmployee1(){
        Employee emp = new Employee();
        emp.setAge(30);
        emp.setDepartment("A");
        emp.setName("A");
        emp.setSalary(1000.0);
        return emp;
    }

    public static Employee getEmployee2(){
        Employee emp = new Employee();
        emp.setAge(40);
        emp.setDepartment("B");
        emp.setName("B");
        emp.setSalary(2000.0);
        return emp;
    }
}
