package tech.reliab.course.sitrovs.bank.service;

import tech.reliab.course.sitrovs.bank.entity.Bank;
import tech.reliab.course.sitrovs.bank.entity.BankOffice;
import tech.reliab.course.sitrovs.bank.entity.CreditAccount;
import tech.reliab.course.sitrovs.bank.entity.Employee;
import tech.reliab.course.sitrovs.bank.entity.User;

public interface BankService {
  // Создание банка
  Bank create(Bank bank);
  // Добавление офиса
  boolean addOffice(Bank bank, BankOffice bankOffice);
  // Удаление офиса
  boolean removeOffice(Bank bank, BankOffice bankOffice);
  // Добавление сотрудника
  boolean addEmployee(Bank bank, Employee employee);
  // Удаление сотрудника
  boolean removeEmployee(Bank bank, Employee employee);
  // Добавление клиента
  boolean addClient(Bank bank, User user);
  // Удаление клиента
  boolean removeClient(Bank bank, User user);
  // Вычисление процентной ставки банка (чем выше рейтинг, тем ниже ставка).
  double calculateInterestRate(Bank bank);
  /*
  Внести сумму денег sum в банк bank.
  */
  boolean depositMoney(Bank bank, double amount);
  /*
  Вывести деньги sum из банка bank.
  */
  boolean withdrawMoney(Bank bank, double amount);
  /*
  Оформление заявки на кредит.
  В операции может быть отказано, если в банке недостаточно денег / сотрудник employee не выдает кредиты /
  доход клиента меньше, чем ежемесячная выплата по кредиту
  */
  boolean approveCredit(Bank bank, CreditAccount account, Employee employee);
}