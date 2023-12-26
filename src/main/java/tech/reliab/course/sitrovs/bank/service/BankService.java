package tech.reliab.course.sitrovs.bank.service;

import java.util.List;

import tech.reliab.course.sitrovs.bank.entity.Bank;
import tech.reliab.course.sitrovs.bank.entity.BankOffice;
import tech.reliab.course.sitrovs.bank.entity.CreditAccount;
import tech.reliab.course.sitrovs.bank.entity.Employee;
import tech.reliab.course.sitrovs.bank.entity.User;
import tech.reliab.course.sitrovs.bank.exception.CreditException;
import tech.reliab.course.sitrovs.bank.exception.NotFoundException;
import tech.reliab.course.sitrovs.bank.exception.UniquenessException;

public interface BankService {
  public void setBankOfficeService(BankOfficeService bankOfficeService);
  public void setUserService(UserService userService);
  // Создание банка
  public Bank create(Bank bank) throws UniquenessException;
  // Вывод всей информации о банке
  public void printBankData(int id);
  // Получение банка по id
  public Bank getBankById(int id);
  // Получение всех банков
  public List<Bank> getAllBanks();
  // Добавление офиса
  public boolean addOffice(int bankId, BankOffice bankOffice);
  // Удаление офиса
  public boolean removeOffice(int bankId, BankOffice bankOffice);
  // Получение всех офисов банка
  public List<BankOffice> getAllOfficesByBankId(int bankId);
  // Добавление сотрудника
  public boolean addEmployee(Bank bank, Employee employee);
  // Удаление сотрудника
  public boolean removeEmployee(Bank bank, Employee employee);
  // Добавление клиента
  public boolean addClient(int bankId, User user);
  // Удаление клиента
  public boolean removeClient(Bank bank, User user);
  // Вычисление процентной ставки банка (чем выше рейтинг, тем ниже ставка).
  public double calculateInterestRate(Bank bank);
  /*
  Внести сумму денег sum в банк bank.
  */
  public boolean depositMoney(int bankId, double amount);
  /*
  Вывести деньги sum из банка bank.
  */
  public boolean withdrawMoney(Bank bank, double amount);
  /*
  Оформление заявки на кредит.
  В операции может быть отказано, если в банке недостаточно денег / сотрудник employee не выдает кредиты /
  доход клиента меньше, чем ежемесячная выплата по кредиту
  */
  public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) throws CreditException;

  // Возвращает банки, подходящие для выдачи кредита с указанной суммой и длительностью
  public List<Bank> getBanksSuitable(double sum, int countMonth) throws NotFoundException, CreditException;

  // Подходит ли банк для выдачи суммы
  public boolean isBankSuitable(Bank bank, double sum) throws NotFoundException;

  // Возвращает список офисов, подходящих для выдачи указанной суммы в банке
  public List<BankOffice> getBankOfficeSuitableInBank(Bank bank, double sum) throws NotFoundException;
}