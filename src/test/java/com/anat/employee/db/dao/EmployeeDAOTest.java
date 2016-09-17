package com.anat.employee.db.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.anat.employee.db.entity.Employee;
import com.anat.employee.db.constant.Constant;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@TransactionConfiguration
@Transactional
public class EmployeeDAOTest {
    
    private final Logger logger = LoggerFactory.getLogger(EmployeeDAOTest.class);

    @Autowired
    private EmployeeDAO employeeDAO;

    @Test
    public void testLoadEmployeeDAOShouldPass() throws Exception {
        assertNotNull(employeeDAO);
    }
    
    @Test
    public void testFindAllShouldReturnListOfAllEmployees() throws Exception {
        List<Employee> employees = employeeDAO.findAll();
        
        assertThat(employees.size(), is(not(0)));
        assertThat(employees.size(), is(300024));
        
        System.out.println("employees size : " + employees.size());
    }
    
    @Test
    public void testFindByIdShouldReturnEmployeeOfThatId() throws Exception {
        Employee employee = employeeDAO.findById(10001);
        
        assertThat(employee.getFirstName(), is("Georgi"));
        assertThat(employee.getLastName(), is("Facello"));
        assertThat(employee.getGender(), is(Constant.Gender.M));
        
        System.out.println("employee : " + employee.toString());
    }
    
    @Test
    public void testSerchEmployeeShouldReturnEmployeeOfThatCriteria() throws Exception {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("firstName", "Georgi");
        
        List<Employee> employees = employeeDAO.search(parameterMap);
        boolean isValidFirstname = true;
        if (employees != null && !employees.isEmpty()) {
            System.out.println("employees size : " + employees.size());
            for (Employee employee : employees) {
                if (!employee.getFirstName().equals("Georgi")) {
                    isValidFirstname = false;
                    break;
                }
            }
            
            assertTrue(isValidFirstname);
        } else {
            fail("employees is null or size = 0");
        }
        
    }

}
