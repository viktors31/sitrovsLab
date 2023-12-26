package tech.reliab.course.sitrovs.bank.service.impl;

import static tech.reliab.course.sitrovs.bank.utils.Constants.COLOR_PURPLE;
import static tech.reliab.course.sitrovs.bank.utils.Constants.COLOR_RESET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.sitrovs.bank.entity.BankAtm;
import tech.reliab.course.sitrovs.bank.entity.BankOffice;
import tech.reliab.course.sitrovs.bank.entity.Employee;
import tech.reliab.course.sitrovs.bank.exception.NotFoundException;
import tech.reliab.course.sitrovs.bank.exception.UniquenessException;
import tech.reliab.course.sitrovs.bank.service.AtmService;
import tech.reliab.course.sitrovs.bank.service.BankOfficeService;
import tech.reliab.course.sitrovs.bank.service.BankService;
import tech.reliab.course.sitrovs.bank.service.EmployeeService;

public class BankOfficeServiceImpl implements BankOfficeService {
  private final Map<Integer, BankOffice> bankOfficesTable = new HashMap<>();
  private final Map<Integer, List<Employee>> employeesByOfficeIdTable = new HashMap<>();
  private final Map<Integer, List<BankAtm>> atmsByOfficeIdTable = new HashMap<>();
  private final BankService bankService;
  private EmployeeService employeeService;
  private AtmService atmService;

  public BankOfficeServiceImpl(BankService bankService) {
    this.bankService = bankService;
  }

  public void setAtmService(AtmService atmService) {
    this.atmService = atmService;
  }

  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public BankOffice create(BankOffice bankOffice) throws UniquenessException {
    if (bankOffice == null) {
      return null;
    }

    if (bankOffice.getId() < 0) {
      System.out.println("Error: id must be non-negative");
      return null;
    }

    if (bankOffice.getTotalMoney() < 0) {
      System.out.println("Error: bankOffice total money must be non-negative");
      return null;
    }

    if (bankOffice.getBank() == null) {
      System.out.println("Error: bankOffice must have bank");
      return null;
    }

    if (bankOffice.getRentPrice() < 0) {
      System.out.println("Error: bankOffice rentPrice must be non-negative");
      return null;
    }

    if (bankOfficesTable.containsKey(bankOffice.getId())) {
      throw new UniquenessException("BankOffice", bankOffice.getId());
    }

    bankOfficesTable.put(bankOffice.getId(), bankOffice);
    employeesByOfficeIdTable.put(bankOffice.getId(), new ArrayList<>());
    atmsByOfficeIdTable.put(bankOffice.getId(), new ArrayList<>());
    bankService.addOffice(bankOffice.getBank().getId(), bankOffice);

    return new BankOffice(bankOffice);
  }

  public void printBankOfficeData(int id) {
    BankOffice bankOffice = bankOfficesTable.get(id);

    if (bankOffice == null) {
      System.out.println("Bank office with id: " + id + " was not found.");
      return;
    }

    System.out.println(bankOffice);
    List<Employee> employees = employeesByOfficeIdTable.get(id);
    if (employees != null) {
      System.out.println(COLOR_PURPLE + "Employees:" + COLOR_RESET);
      employees.forEach(System.out::println);
    }

    List<BankAtm> atms = atmsByOfficeIdTable.get(id);
    if (atms != null) {
      System.out.println(COLOR_PURPLE + "ATMs:" + COLOR_RESET);
      atms.forEach(System.out::println);
    }
  }

  public BankOffice getBankOfficeById(int id) {
    BankOffice office = bankOfficesTable.get(id);

		if (office == null) {
			System.out.println("Bank office with id: " + id + " was not found.");
		}

		return office;
  }

  public List<BankOffice> getAllOffices() {
    return new ArrayList<BankOffice>(bankOfficesTable.values());
  }

  public List<Employee> getAllEmployeesByOfficeId(int officeId) {
    return employeesByOfficeIdTable.get(officeId);
  }

  public boolean installAtm(int bankOfficeId, BankAtm bankAtm) {
    BankOffice bankOffice = getBankOfficeById(bankOfficeId);

    if (bankOffice != null && bankAtm != null) {
      if (!bankOffice.getIsAtmPlaceable()) {
        System.out.println("Error: cannot install atm at office " + bankOffice.getId());
        return false;
      }

      bankAtm.setBankOffice(bankOffice);
      bankAtm.setAddress(bankOffice.getAddress());
      bankAtm.setBank(bankOffice.getBank());
      List<BankAtm> officeAtms = atmsByOfficeIdTable.get(bankOffice.getId());
      officeAtms.add(bankAtm);
      bankOffice.addAtm(bankAtm);
      bankOffice.getBank().addAtm(bankAtm);
      return true;
    }
    return false;
  }

  public boolean depositMoney(BankOffice bankOffice, double amount) {
    if (bankOffice == null) {
      System.out.println("Error: cannot deposit money to not existing office");
      return false;
    }

    if (amount <= 0) {
      System.out.println("Error: cannot deposit money - deposit amount must be positive");
      return false;
    }

    if (!bankOffice.getIsCashDepositAvailable()) {
      System.out.println("Error: cannot deposit money - office aint accepting deposits");
      return false;
    }

    bankOffice.setTotalMoney(bankOffice.getTotalMoney() + amount);

    return true;
  }

  public boolean withdrawMoney(BankOffice bankOffice, double amount) {
    if (bankOffice == null) {
      System.out.println("Error: cannot withdraw money from not existing office");
      return false;
    }

    if (amount <= 0) {
      System.out.println("Error: cannot withdraw money - withdraw amount must be positive");
      return false;
    }

    if (!bankOffice.getIsCashWithdrawalAvailable()) {
      System.out.println("Error: cannot withdraw money - office aint giving withdrawals");
      return false;
    }

    if (bankOffice.getTotalMoney() < amount) {
      System.out.println("Error: cannot withdraw money - office does not have enough money. Please come back later.");
      return false;
    }

    bankOffice.setTotalMoney(bankOffice.getTotalMoney() - amount);

    return true;
  }

  public boolean addEmployee(int bankOfficeId, Employee employee) {
    BankOffice bankOffice = getBankOfficeById(bankOfficeId);

    if (bankOffice != null && employee != null) {
      employee.setBankOffice(bankOffice);
      employee.setBank(bankOffice.getBank());
      List<Employee> officeEmployees = employeesByOfficeIdTable.get(bankOffice.getId());
      officeEmployees.add(employee);
      bankOffice.addEmployee(employee);
      bankOffice.getBank().addEmployee(employee);
      return true;
    }
    return false;
  }

  public List<BankAtm> getAllOfficeAtms(int id) {
    return atmsByOfficeIdTable.get(id);
  }

  public List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, double sum) {
    List<BankAtm> bankAtmByOffice = getAllOfficeAtms(bankOffice.getId());
    List<BankAtm> suitableBankAtm = new ArrayList<>();

    for (BankAtm bankAtm : bankAtmByOffice) {
      if (atmService.isAtmSuitable(bankAtm, sum)) {
        suitableBankAtm.add(bankAtm);
      }
    }

    return suitableBankAtm;
  }

  public List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice) throws NotFoundException {
    List<Employee> employees = getAllEmployeesByOfficeId(bankOffice.getId());
    List<Employee> suitableEmployee = new ArrayList<>();

    for (Employee employee : employees) {
      if (employeeService.isEmployeeSuitable(employee)) {
        suitableEmployee.add(employee);
      }
    }

    return suitableEmployee;
  }

  public boolean isSuitableBankOffice(BankOffice bankOffice, double sum) throws NotFoundException {
    if (bankOffice.getIsWorking() && bankOffice.getIsCashWithdrawalAvailable() && bankOffice.getTotalMoney() >= sum) {
      List<BankAtm> bankAtmSuitable = getSuitableBankAtmInOffice(bankOffice, sum);
      // System.out.println("Here bankAtmSuitable " + bankAtmSuitable.size());
      if (bankAtmSuitable.isEmpty()) {
        return false;
      }

      List<Employee> employeesSuitable = getSuitableEmployeeInOffice(bankOffice);
      // System.out.println("Here employeesSuitable " + employeesSuitable.size());
      if (employeesSuitable.isEmpty()) {
        return false;
      }

      return true;
    }

    return false;
  }
}