package tech.reliab.course.sitrovs.bank.entity;

public class Bank {
  private static int currentId;
  private int id;
  private String name;
  private short officeCount;
  private int atmCount;
  private int employeeCount;
  private int userCount;
  private byte rating;
  private double totalMoney;
  private double interestRate;

  private void initializeId() {
    id = currentId++;
  };

  private void initializeWithDefaults() {
    name = "No name";
    officeCount = 0;
    atmCount = 0;
    employeeCount = 0;
    userCount = 0;
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
      "\tofficeCount: " + officeCount + ",\n" +
      "\tatmCount: " + atmCount + ",\n" +
      "\temployeeCount: " + employeeCount + ",\n" +
      "\tuserCount: " + userCount + ",\n" +
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

  public void setOfficeCount(short officeCount) {
    this.officeCount = officeCount;
  }

  public short getOfficeCount() {
    return officeCount;
  }

  public void setAtmCount(int atmCount) {
    this.atmCount = atmCount;
  }

  public int getAtmCount() {
    return atmCount;
  }

  public void setEmployeeCount(int employeeCount) {
    this.employeeCount = employeeCount;
  }

  public int getEmployeeCount() {
    return employeeCount;
  }

  public void setUserCount(int userCount) {
    this.userCount = userCount;
  }

  public int getUserCount() {
    return userCount;
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
}