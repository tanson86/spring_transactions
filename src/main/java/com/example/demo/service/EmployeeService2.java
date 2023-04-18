package com.example.demo.service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.util.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService2 {

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveWithTransactionRequired(boolean error){
        System.out.println(employeeRepository.findById(1));
        employeeRepository.save(EmployeeUtil.getEmployee2());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveWithTransactionRequiredNew(boolean error){
        System.out.println(employeeRepository.findById(1));
        employeeRepository.save(EmployeeUtil.getEmployee2());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveWithTransactionRequiredNewThrowException(boolean error){
        System.out.println(employeeRepository.findById(1));
        employeeRepository.save(EmployeeUtil.getEmployee2());
        if(error)
            throw new RuntimeException("Error thrown by RequiredNewThrowException");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void saveWithTransactionNested(boolean error){
        System.out.println(employeeRepository.findById(1));
        employeeRepository.save(EmployeeUtil.getEmployee2());
    }
}
