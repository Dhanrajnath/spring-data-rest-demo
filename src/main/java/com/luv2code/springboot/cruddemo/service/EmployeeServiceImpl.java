package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.dao.EmployeeJpaRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional   ---> removing it because JpaRepository provides this functionality
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeJpaRepository employeeJpaRepository) {
        this.employeeJpaRepository = employeeJpaRepository;
    }

    //    private EmployeeDAO employeeDAO;
//
//    // class name --> EmployeeDAOJpaImpl
//    // bean id ----> employeeDAOJpaImpl
//
//    @Autowired
//    public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO theEmployeeDAO) {
//        employeeDAO = theEmployeeDAO;
//    }

    @Override
    public List<Employee> findAll() {
        return employeeJpaRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeJpaRepository.findById(theId);

        Employee theEmployee =null;

        if (result.isPresent()){
            theEmployee = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - "+ theId);
        }
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        employeeJpaRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theEmployeeId) {
        employeeJpaRepository.deleteById(theEmployeeId);
    }
}
