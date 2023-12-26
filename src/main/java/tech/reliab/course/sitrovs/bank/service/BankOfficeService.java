package tech.reliab.course.sitrovs.bank.service;

import java.util.List;

import tech.reliab.course.sitrovs.bank.entity.BankAtm;
import tech.reliab.course.sitrovs.bank.entity.BankOffice;
import tech.reliab.course.sitrovs.bank.entity.Employee;
import tech.reliab.course.sitrovs.bank.exception.NotFoundException;
import tech.reliab.course.sitrovs.bank.exception.UniquenessException;

public interface BankOfficeService {
  public void setAtmService(AtmService atmService);
  public void setEmployeeService(EmployeeService employeeService);
  BankOffice create(BankOffice bankOffice) throws UniquenessException;
  public void printBankOfficeData(int id);
  public BankOffice getBankOfficeById(int id);
  public List<BankOffice> getAllOffices();
  public List<Employee> getAllEmployeesByOfficeId(int officeId);
  public boolean installAtm(int bankOfficeId, BankAtm bankAtm);
  public boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm);
  public boolean depositMoney(BankOffice bankOffice, double amount);
  public boolean withdrawMoney(BankOffice bankOffice, double amount);
  public boolean addEmployee(int bankOfficeId, Employee employee);
  public boolean removeEmployee(BankOffice bankOffice, Employee employee);
  public List<BankAtm> getAllOfficeAtms(int id);
  public boolean isSuitableBankOffice(BankOffice bankOffice, double sum) throws NotFoundException;
  public List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, double sum);
  public List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice) throws NotFoundException;
}
