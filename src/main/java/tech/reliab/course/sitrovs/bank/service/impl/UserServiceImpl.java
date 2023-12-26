package tech.reliab.course.sitrovs.bank.service.impl;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static tech.reliab.course.sitrovs.bank.utils.Constants.*;

import tech.reliab.course.sitrovs.bank.entity.Account;
import tech.reliab.course.sitrovs.bank.entity.CreditAccount;
import tech.reliab.course.sitrovs.bank.entity.PaymentAccount;
import tech.reliab.course.sitrovs.bank.entity.User;
import tech.reliab.course.sitrovs.bank.exception.NoPaymentAccountException;
import tech.reliab.course.sitrovs.bank.exception.NotFoundException;
import tech.reliab.course.sitrovs.bank.exception.UniquenessException;
import tech.reliab.course.sitrovs.bank.service.BankService;
import tech.reliab.course.sitrovs.bank.service.UserService;

public class UserServiceImpl implements UserService {
  private final Map<Integer, User> usersTable = new HashMap<>();
  private final Map<Integer, List<PaymentAccount>> paymentAccountsByUserIdTable = new HashMap<>();
  private final Map<Integer, List<CreditAccount>> creditAccountsByUserIdTable = new HashMap<>();
  private final BankService bankService;

  public UserServiceImpl(BankService bankService) {
    this.bankService = bankService;
  }

  public int calculateCreditRating(User user) {
    final int rating = (int)Math.ceil(user.getMonthlyIncome() / 1000) * 100;
    user.setCreditRating(rating);
    return rating;
  }

  public User create(User user) throws UniquenessException {
    if (user == null) {
      return null;
    }

    if (user.getId() < 0) {
      System.out.println("Error: user id must be non-negative");
      return null;
    }

    if (user.getBank() == null) {
      System.out.println("Error: can not create user without bank");
      return null;
    }

    User createdUser = new User(user);

    if (usersTable.containsKey(createdUser.getId())) {
      throw new UniquenessException("User", createdUser.getId());
    }

    final Random random = new Random();

    final double monthlyIncome = random.nextDouble() * MAX_USER_MONTHLY_INCOME;
    createdUser.setMonthlyIncome(monthlyIncome);
    calculateCreditRating(createdUser);

    usersTable.put(createdUser.getId(), createdUser);
    paymentAccountsByUserIdTable.put(createdUser.getId(), new ArrayList<>());
    creditAccountsByUserIdTable.put(createdUser.getId(), new ArrayList<>());
    bankService.addClient(user.getBank().getId(), createdUser);

    return createdUser;
  }

  public void printUserData(int id, boolean withAccounts) {
    User user = usersTable.get(id);

    if (user == null) {
      System.out.println("User with id: " + id + " was not found.");
      return;
    }

    System.out.println(user);

    if (withAccounts) {
      List<PaymentAccount> paymentAccounts = paymentAccountsByUserIdTable.get(id);
      if (paymentAccounts != null) {
        System.out.println(COLOR_PURPLE + "Payment accounts:" + COLOR_RESET);
        paymentAccounts.forEach(System.out::println);
      }

      List<CreditAccount> creditAccounts = creditAccountsByUserIdTable.get(id);
      if (creditAccounts != null) {
        System.out.println(COLOR_PURPLE + "Credit accounts:" + COLOR_RESET);
        creditAccounts.forEach(System.out::println);
      }
    }
  }

  public User getUserById(int id) {
    User user = usersTable.get(id);

		if (user == null) {
			System.out.println("User with id: " + id + " was not found.");
		}

		return user;
  }

  public List<User> getAllUsers() {
    return new ArrayList<User>(usersTable.values());
  }

  public boolean addPaymentAccount(int userId, PaymentAccount paymentAccount) {
    User user = usersTable.get(userId);
    if (user != null) {
      List<PaymentAccount> userPaymentAccounts = paymentAccountsByUserIdTable.get(userId);
      userPaymentAccounts.add(paymentAccount);
      user.addAccount(paymentAccount);
      user.getBank().addAccount(paymentAccount);
      return true;
    }

    return false;
  }

  public boolean addCreditAccount(int userId, CreditAccount creditAccount) {
    User user = usersTable.get(userId);
    if (user != null) {
      List<CreditAccount> userCreditAccounts = creditAccountsByUserIdTable.get(userId);
      userCreditAccounts.add(creditAccount);
      user.addAccount(creditAccount);
      user.getBank().addAccount(creditAccount);
      return true;
    }

    return false;
  }

  public List<PaymentAccount> getAllPaymentAccountsByUserId(int userId) {
    List<PaymentAccount> userPaymentAccounts = paymentAccountsByUserIdTable.get(userId);
    return userPaymentAccounts;
  }

  public List<CreditAccount> getAllCreditAccountsByUserId(int userId) {
    List<CreditAccount> userCreditAccounts = creditAccountsByUserIdTable.get(userId);
    return userCreditAccounts;
  }

  public PaymentAccount getBestPaymentAccount(int id) throws NotFoundException, NoPaymentAccountException {
    List<PaymentAccount> paymentAccounts = getAllPaymentAccountsByUserId(id);
    PaymentAccount paymentAccount = paymentAccounts
      .stream()
      .min(Comparator.comparing(PaymentAccount::getBalance))
      .orElseThrow(NoPaymentAccountException::new);
      
    return paymentAccount;
  }

  public boolean transferUserToAnotherBank(User user, int newBankId) {
    return bankService.transferClient(user, newBankId);
  }

  public boolean exportUserAccountsToTxtFile(int userId, int bankId) throws NoPaymentAccountException {
    // List<PaymentAccount> userPaymentAccounts = getAllPaymentAccountsByUserId(userId);
    List<CreditAccount> userCreditAccounts = getAllCreditAccountsByUserId(userId);

    // List<Account> userAccountsByBankId = new ArrayList<Account>();

    // for (PaymentAccount account : userPaymentAccounts) {
    //   if (account.getBank().getId() == bankId)
    //     userAccountsByBankId.add(account);
    // }
    // for (CreditAccount account : userCreditAccounts) {
    //   if (account.getBank().getId() == bankId)
    //     userAccountsByBankId.add(account);
    // }

    if (userCreditAccounts.size() == 0)
      throw new NoPaymentAccountException();

    try {
      // PrintWriter out = new PrintWriter("user_" + userId + "_accounts_of_bank_" + bankId + ".txt");
      PrintWriter out = new PrintWriter("a.txt");
      Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
      out.println(gson.toJson(userCreditAccounts));
      out.close();
      return true;
    } catch (FileNotFoundException e) {
      System.err.println("File writer error");
      e.printStackTrace();
      return false;
    }
  }
}