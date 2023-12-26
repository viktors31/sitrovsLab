package tech.reliab.course.sitrovs.bank.entity;

import java.util.ArrayList;
import java.util.List;

public class Bank {
  private static int currentId;
  private int id;
  private String name;
  private byte rating;
  private double totalMoney;
  private double interestRate;
  private List<Employee> employees;
  private List<User> users;
  private List<BankOffice> bankOffices;
  private List<BankAtm> bankAtms;
  private List<Account> accounts;

  private void initializeId() {
    id = currentId++;
  };

  private void initializeWithDefaults() {
    name = "No name";
    employees = new ArrayList<>();
    users = new ArrayList<>();
    bankOffices = new ArrayList<>();
    bankAtms = new ArrayList<>();
    accounts = new ArrayList<>();
  }

  public Bank() {
    initializeId();
    initializeWithDefaults();
  }

  public Bank(String name) {
    initializeId();
    initializeWithDefaults();
    this.name = name;
  }

  public Bank(int id, String name) {
    this.id = id;
    initializeWithDefaults();
    this.name = name;
  }

  @Override
  public String toString() {
    return 
      "\nBank: {\n" +
      "\tid: " + id + ",\n" +
      "\tname: " + name + ",\n" +
      "\tofficeCount: " + bankOffices.size() + ",\n" +
      "\tatmCount: " + bankAtms.size() + ",\n" +
      "\temployeeCount: " + employees.size() + ",\n" +
      "\tuserCount: " + users.size() + ",\n" +
      "\trating: " + rating + ",\n" +
      "\ttotalMoney: " + String.format("%.2f", totalMoney) + ",\n" +
      "\tinterestRate: " + String.format("%.2f", interestRate) + "\n" +
      "}\n";
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setRating(byte rating) {
    this.rating = rating;
  }

  public byte getRating() {
    return rating;
  }

  public void setTotalMoney(double totalMoney) {
    this.totalMoney = totalMoney;
  }

  public double getTotalMoney() {
    return totalMoney;
  }

  public void setInterestRate(double interestRate) {
    this.interestRate = interestRate;
  }

  public double getInterestRate() {
    return interestRate;
  }

  public void addEmployee(Employee employee) {
    employees.add(employee);
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void addUser(User user) {
    users.add(user);
  }

  public List<User> getUsers() {
    return users;
  }

  public void addOffice(BankOffice bankOffice) {
    for (BankAtm bankAtm : bankOffice.getAtms()) {
      addAtm(bankAtm);
    }
    for (Employee employee : bankOffice.getEmployees()) {
      addEmployee(employee);
    }
    bankOffices.add(bankOffice);
  }

  public List<BankOffice> getBankOffices() {
    return bankOffices;
  }

  public void addAtm(BankAtm bankAtm) {
    bankAtms.add(bankAtm);
  }

  public List<BankAtm> getAtms() {
    return bankAtms;
  }

  public void addAccount(Account account) {
    accounts.add(account);
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void removeUser(User user) {
    users.remove(users.indexOf(user));
  }
}