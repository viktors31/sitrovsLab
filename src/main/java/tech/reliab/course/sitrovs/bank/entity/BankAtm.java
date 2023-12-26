package tech.reliab.course.sitrovs.bank.entity;

import tech.reliab.course.sitrovs.bank.utils.BankAtmStatus;

public class BankAtm {
  private static int currentId;
  private int id;
  private String name;
  private String address;
  private BankAtmStatus status;
  private Bank bank;
  private BankOffice bankOffice;
  private boolean isCashWithdrawalAvailable;
  private boolean isCashDepositAvailable;
  private double totalMoney;
  private double maintenanceCost;

  private void initializeId() {
    id = currentId++;
  };
  
  private void initializeWithDefaults() {
    name = "No name";
    address = "No address";
    status = BankAtmStatus.NOT_WORKING;
    bank = null;
    bankOffice = null;
    isCashWithdrawalAvailable = false;
    isCashDepositAvailable = false;
    totalMoney = 0;
    maintenanceCost = 0;
  }

  public BankAtm() {
    initializeId();
    initializeWithDefaults();
  }

  public BankAtm(String name, String address, BankAtmStatus status, Bank bank, BankOffice bankOffice, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, double totalMoney, double maintenanceCost) {
    initializeId();
    this.name = name;
    this.address = address;
    this.status = status;
    this.bank = bank;
    this.bankOffice = bankOffice;
    this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
    this.isCashDepositAvailable = isCashDepositAvailable;
    this.totalMoney = totalMoney;
    this.maintenanceCost = maintenanceCost;
  }

  public BankAtm(BankAtm bankAtm) {
    this.id = bankAtm.getId();
    this.name = bankAtm.getName();
    this.address = bankAtm.getAddress();
    this.status = bankAtm.getStatus();
    this.bank = bankAtm.getBank();
    this.bankOffice = bankAtm.getBankOffice();
    this.isCashWithdrawalAvailable = bankAtm.getIsCashWithdrawalAvailable();
    this.isCashDepositAvailable = bankAtm.getIsCashDepositAvailable();
    this.totalMoney = bankAtm.getTotalMoney();
    this.maintenanceCost = bankAtm.getMaintenanceCost();
  }

  @Override
  public String toString() {
    return 
      "\nBank ATM: {\n" +
      "\tid: " + id + ",\n" +
      "\tname: " + name + ",\n" +
      "\taddress: " + address + ",\n" +
      "\tstatus: " + status.name() + ",\n" +
      "\tbank: " + (bank == null ? "null" : bank.getName()) + ",\n" +
      "\tbankOffice: " + (bankOffice == null ? "null" : bankOffice.getName()) + ",\n" +
      "\tisCashWithdrawalAvailable: " + isCashWithdrawalAvailable + ",\n" +
      "\tisCashDepositAvailable: " + isCashDepositAvailable + ",\n" +
      "\ttotalMoney: " + String.format("%.2f", totalMoney) + ",\n" +
      "\tmaintenanceCost: " + String.format("%.2f", maintenanceCost) + ",\n" +
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

  public void setStatus(BankAtmStatus status) {
    this.status = status;
  }

  public BankAtmStatus getStatus() {
    return status;
  }

  public void setBank(Bank bank) {
    this.bank = bank;
  }

  public Bank getBank() {
    return bank;
  }

  public void setBankOffice(BankOffice bankOffice) {
    this.bankOffice = bankOffice;
  }

  public BankOffice getBankOffice() {
    return bankOffice;
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

  public void setMaintenanceCost(double maintenanceCost) {
    this.maintenanceCost = maintenanceCost;
  }

  public double getMaintenanceCost() {
    return maintenanceCost;
  }
}
