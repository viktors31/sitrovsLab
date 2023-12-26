package tech.reliab.course.sitrovs.bank.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee extends Human {
  private String jobTitle;
  private Bank bank;
  private boolean isWorkingFromHome;
  private BankOffice bankOffice;
  private boolean isCreditAvailable;
  private double salary;

  private void initializeWithDefaults() {
    jobTitle = "No job title";
    bank = null;
    isWorkingFromHome = false;
    bankOffice = null;
    isCreditAvailable = false;
    salary = 0;
  }

  public Employee() {
    super();
    initializeWithDefaults();
  }

  public Employee(String jobTitle, Bank bank, boolean isWorkingFromHome, BankOffice bankOffice, boolean isCreditAvailable, double salary) {
    super();
    this.jobTitle = jobTitle;
    this.bank = bank;
    this.isWorkingFromHome = isWorkingFromHome;
    this.bankOffice = bankOffice;
    this.isCreditAvailable = isCreditAvailable;
    this.salary = salary;
  }

  public Employee(String name, LocalDate birthDate, String jobTitle, Bank bank, boolean isWorkingFromHome, BankOffice bankOffice, boolean isCreditAvailable, double salary) {
    super(name, birthDate);
    this.jobTitle = jobTitle;
    this.bank = bank;
    this.isWorkingFromHome = isWorkingFromHome;
    this.bankOffice = bankOffice;
    this.isCreditAvailable = isCreditAvailable;
    this.salary = salary;
  }

  public Employee(Employee employee) {
    super(employee.getId(), employee.getName(), employee.getBirthDate());
    this.jobTitle = employee.getJobTitle();
    this.bank = employee.getBank();
    this.isWorkingFromHome = employee.getIsWorkingFromHome();
    this.bankOffice = employee.getBankOffice();
    this.isCreditAvailable = employee.getIsCreditAvailable();
    this.salary = employee.getSalary();
  }

  @Override
  public String toString() {
    return 
      "\nBank Employee: {\n" +
      "\tid: " + id + ",\n" +
      "\tname: " + name + ",\n" +
      "\tbirthDate: " + (birthDate == null ? "null" : birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) + ",\n" +
      "\tjobTitle: " + jobTitle + ",\n" +
      "\tbank: " + (bank == null ? "null" : bank.getName()) + ",\n" +
      "\tisWorkingFromHome: " + isWorkingFromHome + ",\n" +
      "\tbankOffice: " + (bankOffice == null ? "null" : bankOffice.getName()) + ",\n" +
      "\tisCreditAvailable: " + isCreditAvailable + ",\n" +
      "\tsalary: " + String.format("%.2f", salary) + ",\n" +
      "}\n";
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }
  
  public String getJobTitle() {
    return jobTitle;
  }

  public void setBank(Bank bank) {
    this.bank = bank;
  }

  public Bank getBank() {
    return bank;
  }

  public void setIsWorkingFromHome(boolean isWorkingFromHome) {
    this.isWorkingFromHome = isWorkingFromHome;
  }

  public boolean getIsWorkingFromHome() {
    return isWorkingFromHome;
  }

  public void setBankOffice(BankOffice bankOffice) {
    this.bankOffice = bankOffice;
  }

  public BankOffice getBankOffice() {
    return bankOffice;
  }

  public void setIsCreditAvailable(boolean isCreditAvailable) {
    this.isCreditAvailable = isCreditAvailable;
  }

  public boolean getIsCreditAvailable() {
    return isCreditAvailable;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public double getSalary() {
    return salary;
  }
}