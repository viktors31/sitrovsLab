package tech.reliab.course.nikolotovai.bank.service;

import java.util.List;

import tech.reliab.course.nikolotovai.bank.entity.CreditAccount;
import tech.reliab.course.nikolotovai.bank.entity.PaymentAccount;
import tech.reliab.course.nikolotovai.bank.entity.User;

public interface UserService {
  User create(User user);
  public void printUserData(int id, boolean withAccounts);
  public User getUserById(int id);
  public List<User> getAllUsers();
  public boolean addPaymentAccount(int userId, PaymentAccount paymentAccount);
  public boolean addCreditAccount(int userId, CreditAccount creditAccount);
  public List<PaymentAccount> getAllPaymentAccountsByUserId(int userId);
  public int calculateCreditRating(User user);
}
