package com.anat.employee.db.dao.impl;

import com.anat.employee.db.dao.EmployeeDAO;
import com.anat.employee.db.dao.base.AbstractDAOImpl;
import com.anat.employee.db.entity.Employee;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

@Repository(value = "employeeDAO")
public class EmployeeDAOImpl extends AbstractDAOImpl<Employee, Serializable> implements EmployeeDAO {

}
