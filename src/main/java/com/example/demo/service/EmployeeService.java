package com.example.demo.service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entities.Employee;
import com.example.demo.util.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService2 employeeService2;

    public void saveEmployeeWithoutTransaction(boolean error) {

        employeeRepository.save(EmployeeUtil.getEmployee1());
        if(error)
            throw new RuntimeException("Exception from service 1");
        employeeRepository.save(EmployeeUtil.getEmployee2());
    }

    @Transactional
    public void saveEmployeeWithTransaction(boolean error) {

        employeeRepository.save(EmployeeUtil.getEmployee1());
        if(error)
            throw new RuntimeException("Exception from service 1");
        employeeRepository.save(EmployeeUtil.getEmployee2());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveEmployeeWithTransactionRequired(boolean error) {
        employeeRepository.save(EmployeeUtil.getEmployee1());
        employeeService2.saveWithTransactionRequired(true);
        if(error)
            throw new RuntimeException("Exception from Required");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveEmployeeWithTransactionRequiredNew(boolean error) {

        employeeRepository.save(EmployeeUtil.getEmployee1());
        employeeService2.saveWithTransactionRequiredNew(true);
        if(error)
            throw new RuntimeException("Exception from Requires_New");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveEmployeeWithTransactionRequiredNewThrowException(boolean error) {

        employeeRepository.save(EmployeeUtil.getEmployee1());
        try{
            employeeService2.saveWithTransactionRequiredNewThrowException(true);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveEmployeeWithTransactionNested(boolean error) {

        employeeRepository.save(EmployeeUtil.getEmployee1());
        employeeService2.saveWithTransactionNested(true);
        if(error)
            throw new RuntimeException("Exception from Nested");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveEmployeeWithTransactionLocalCall(boolean error) {

        employeeRepository.save(EmployeeUtil.getEmployee1());
        saveEmployeeCalledLocally(error);
    }

    public void saveEmployeeCalledLocally(boolean error){
        employeeRepository.save(EmployeeUtil.getEmployee2());
        if(error)
            throw new RuntimeException("Exception from local call");
    }

    @Transactional
    public void testIsolation(){
        employeeRepository.save(EmployeeUtil.getEmployee1());
        try {
            System.out.println("Thread going to sleep");
            Thread.sleep(60000);
            System.out.println("Thread woke up from sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void testIsolationReadUncommited(){
        System.out.println("Read uncommited "+employeeRepository.findById(1));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void testIsolationReadCommited(){
        System.out.println("Read commited "+employeeRepository.findById(1));
    }



}
