package tech.reliab.course.sitrovs.bank.service;

import java.util.List;

import tech.reliab.course.sitrovs.bank.entity.CreditAccount;
import tech.reliab.course.sitrovs.bank.entity.PaymentAccount;
import tech.reliab.course.sitrovs.bank.entity.User;
import tech.reliab.course.sitrovs.bank.exception.NoPaymentAccountException;
import tech.reliab.course.sitrovs.bank.exception.NotFoundException;
import tech.reliab.course.sitrovs.bank.exception.UniquenessException;

public interface UserService {
  User create(User user) throws UniquenessException;
  public void printUserData(int id, boolean withAccounts);
  public User getUserById(int id);
  public List<User> getAllUsers();
  public boolean addPaymentAccount(int userId, PaymentAccount paymentAccount);
  public boolean addCreditAccount(int userId, CreditAccount creditAccount);
  public List<PaymentAccount> getAllPaymentAccountsByUserId(int userId);
  public List<CreditAccount> getAllCreditAccountsByUserId(int userId);
  public int calculateCreditRating(User user);
  public PaymentAccount getBestPaymentAccount(int id) throws NotFoundException, NoPaymentAccountException;
  public boolean transferUserToAnotherBank(User user, int newBankId);
  public boolean exportUserAccountsToTxtFile(int userId, int bankId) throws NoPaymentAccountException;
}
