package tech.reliab.course.sitrovs.bank.service;

import tech.reliab.course.sitrovs.bank.entity.BankOffice;
import tech.reliab.course.sitrovs.bank.entity.Employee;

public interface EmployeeService {
  Employee create(Employee employee);
  boolean transferEmployee(Employee employee, BankOffice bankOffice);
}