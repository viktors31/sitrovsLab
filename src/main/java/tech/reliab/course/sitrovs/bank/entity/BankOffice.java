package tech.reliab.course.sitrovs.bank.entity;

import java.util.ArrayList;
import java.util.List;

public class BankOffice {
  private static int currentId;
  private int id;
  private String name;
  private String address;
  private Bank bank;
  private boolean isWorking;
  private boolean isAtmPlaceable;
  private boolean isCreditAvailable;
  private boolean isCashWithdrawalAvailable;
  private boolean isCashDepositAvailable;
  private double totalMoney;
  private double rentPrice;
  private List<Employee> employees;
  private List<BankAtm> bankAtms;

  private void initializeId() {
    id = currentId++;
  };

  private void initializeWithDefaults() {
    name = "No name";
    address = "No address";
    bank = null;
    isWorking = false;
    isAtmPlaceable = false;
    isCreditAvailable = false;
    isCashWithdrawalAvailable = false;
    isCashDepositAvailable = false;
    totalMoney = 0;
    rentPrice = 0;
    employees = new ArrayList<>();
    bankAtms = new ArrayList<>();
  }

  public BankOffice() {
    initializeId();
    initializeWithDefaults();
  }

  public BankOffice(String name, String address) {
    initializeId();
    initializeWithDefaults();
    this.name = name;
    this.address = address;
  }

  public BankOffice(int id, String name, String address) {
    initializeWithDefaults();
    this.id = id;
    this.name = name;
    this.address = address;
  }

  public BankOffice(String name, String address, Bank bank, boolean isWorking, boolean isAtmPlaceable, boolean isCreditAvailable, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, double totalMoney, double rentPrice) {
    initializeId();
    this.name = name;
    this.address = address;
    this.bank = bank;
    this.isWorking = isWorking;
    this.isAtmPlaceable = isAtmPlaceable;
    this.isCreditAvailable = isCreditAvailable;
    this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
    this.isCashDepositAvailable = isCashDepositAvailable;
    this.totalMoney = totalMoney;
    this.rentPrice = rentPrice;
    employees = new ArrayList<>();
    bankAtms = new ArrayList<>();
  }

  public BankOffice(BankOffice bankOffice) {
    this.id = bankOffice.getId();
    this.name = bankOffice.getName();
    this.address = bankOffice.getAddress();
    this.bank = bankOffice.getBank();
    this.isWorking = bankOffice.getIsWorking();
    this.isAtmPlaceable = bankOffice.getIsAtmPlaceable();
    this.isCreditAvailable = bankOffice.getIsCreditAvailable();
    this.isCashWithdrawalAvailable = bankOffice.getIsCashWithdrawalAvailable();
    this.isCashDepositAvailable = bankOffice.getIsCashDepositAvailable();
    this.totalMoney = bankOffice.getTotalMoney();
    this.rentPrice = bankOffice.getRentPrice();
    employees = new ArrayList<>();
    bankAtms = new ArrayList<>();
  }

  @Override
  public String toString() {
    return 
      "\nBank Office: {\n" +
      "\tid: " + id + ",\n" +
      "\tname: " + name + ",\n" +
      "\taddress: " + address + ",\n" +
      "\tbank: " + (bank == null ? "null" : bank.getName()) + ",\n" +
      "\tisWorking: " + isWorking + ",\n" +
      "\tisAtmPlaceable: " + isAtmPlaceable + ",\n" +
      "\tisCreditAvailable: " + isCreditAvailable + ",\n" +
      "\tisCashWithdrawalAvailable: " + isCashWithdrawalAvailable + ",\n" +
      "\tisCashDepositAvailable: " + isCashDepositAvailable + ",\n" +
      "\ttotalMoney: " + String.format("%.2f", totalMoney) + ",\n" +
      "\trentPrice: " + String.format("%.2f", rentPrice) + ",\n" +
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

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void setBank(Bank bank) {
    this.bank = bank;
  }

  public Bank getBank() {
    return bank;
  }

  public void setIsWorking(boolean isWorking) {
    this.isWorking = isWorking;
  }

  public boolean getIsWorking() {
    return isWorking;
  }

  public void setIsAtmPlaceable(boolean isAtmPlaceable) {
    this.isAtmPlaceable = isAtmPlaceable;
  }

  public boolean getIsAtmPlaceable() {
    return isAtmPlaceable;
  }

  public void setIsCreditAvailable(boolean isCreditAvailable) {
    this.isCreditAvailable = isCreditAvailable;
  }

  public boolean getIsCreditAvailable() {
    return isCreditAvailable;
  }

  public void setIsCashWithdrawalAvailable(boolean isCashWithdrawalAvailable) {
    this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
  }

  public boolean getIsCashWithdrawalAvailable() {
    return isCashWithdrawalAvailable;
  }

  public void setIsCashDepositAvailable(boolean isCashDepositAvailable) {
    this.isCashDepositAvailable = isCashDepositAvailable;
  }

  public boolean getIsCashDepositAvailable() {
    return isCashDepositAvailable;
  }

  public void setTotalMoney(double totalMoney) {
    this.totalMoney = totalMoney;
  } 

  public double getTotalMoney() {
    return totalMoney;
  }

  public void setRentPrice(double rentPrice) {
    this.rentPrice = rentPrice;
  }

  public double getRentPrice() {
    return rentPrice;
  }

  public void addEmployee(Employee employee) {
    employees.add(employee);
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void addAtm(BankAtm bankAtm) {
    bankAtms.add(bankAtm);
  }

  public List<BankAtm> getAtms() {
    return bankAtms;
  }
}