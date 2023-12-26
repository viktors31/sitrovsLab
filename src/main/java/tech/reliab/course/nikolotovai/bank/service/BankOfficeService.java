package tech.reliab.course.nikolotovai.bank.service;

import java.util.List;

import tech.reliab.course.nikolotovai.bank.entity.BankAtm;
import tech.reliab.course.nikolotovai.bank.entity.BankOffice;
import tech.reliab.course.nikolotovai.bank.entity.Employee;

public interface BankOfficeService {
  BankOffice create(BankOffice bankOffice);
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
}
