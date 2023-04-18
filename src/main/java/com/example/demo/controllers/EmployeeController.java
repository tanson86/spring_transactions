package com.example.demo.controllers;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/saveWithoutTransaction")
    public String saveEmployeeWithoutTransaction(){
        employeeService.saveEmployeeWithoutTransaction(true);
        return "Saved";
    }

    @GetMapping("/saveWithTransaction")
    public String saveEmployeeWithTransaction(){
        employeeService.saveEmployeeWithTransaction(true);
        return "Saved";
    }


    @GetMapping("/saveEmployeeWithTransactionRequired")
    public String saveEmployeeWithTransactionRequired(){
        employeeService.saveEmployeeWithTransactionRequired(true);
        return "Saved";
    }

    @GetMapping("/saveEmployeeWithTransactionRequiredNew")
    public String saveEmployeeWithTransactionRequiredNew(){
        employeeService.saveEmployeeWithTransactionRequiredNew(true);
        return "Saved";
    }

    @GetMapping("/saveEmployeeWithTransactionRequiredNewThrowException")
    public String saveEmployeeWithTransactionRequiredNewThrowException(){
        employeeService.saveEmployeeWithTransactionRequiredNewThrowException(true);
        return "Saved";
    }

    @GetMapping("/saveEmployeeWithTransactionNested")
    public String saveEmployeeWithTransactionNested(){
        employeeService.saveEmployeeWithTransactionNested(true);
        return "Saved";
    }

    @GetMapping("/saveEmployeeWithTransactionLocalCall")
    public String saveEmployeeWithTransactionLocalCall(){
        employeeService.saveEmployeeWithTransactionLocalCall(true);
        return "Saved";
    }

    @GetMapping("/readEmployeeWithIsolation")
    public String readEmployeeWithIsolation(){
        employeeService.testIsolation();
        return "read test isolation";
    }

    @GetMapping("/readEmployeeWithIsolationUncommited")
    public String readEmployeeWithIsolationUncommited(){
        employeeService.testIsolationReadUncommited();
        return "read test isolation uncommited";
    }

    @GetMapping("/readEmployeeWithIsolationCommited")
    public String readEmployeeWithIsolationCommited(){
        employeeService.testIsolationReadCommited();
        return "read test isolation commited";
    }
}
