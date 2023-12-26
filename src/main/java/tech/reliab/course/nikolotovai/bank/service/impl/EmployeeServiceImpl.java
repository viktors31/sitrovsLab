package tech.reliab.course.nikolotovai.bank.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.nikolotovai.bank.entity.BankOffice;
import tech.reliab.course.nikolotovai.bank.entity.Employee;
import tech.reliab.course.nikolotovai.bank.service.BankOfficeService;
import tech.reliab.course.nikolotovai.bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
  private final Map<Integer, Employee> employeesTable = new HashMap<>();
  private final BankOfficeService bankOfficeService;

  public EmployeeServiceImpl(BankOfficeService bankOfficeService) {
    this.bankOfficeService = bankOfficeService;
  }

  public Employee create(Employee employee) {
    if (employee == null) {
      return null;
    }

    if (employee.getId() < 0) {
      System.out.println("Error: id must be non-negative");
      return null;
    }

    if (employee.getSalary() < 0) {
      System.out.println("Error: salary must be non-negative");
      return null;
    }

    if (employee.getBankOffice() == null) {
      System.out.println("Error: cannot create employee without office");
      return null;
    }

    Employee createdEmployee = new Employee(employee);

    employeesTable.put(createdEmployee.getId(), createdEmployee);		
    bankOfficeService.addEmployee(employee.getBankOffice().getId(), createdEmployee);

    return createdEmployee;
  }

  public Employee getEmployeeById(int id) {
    Employee employee = employeesTable.get(id);

		if (employee == null) {
			System.out.println("Employee with id: " + id + " was not found.");
		}

		return employee;
  }

  public List<Employee> getAllEmployees() {
    return new ArrayList<Employee>(employeesTable.values());
  }

  public boolean transferEmployee(Employee employee, BankOffice bankOffice) {
    // написать перевод сотрудника в новый офис когда будут массивы офисов и тд

    return true;
  }
}
