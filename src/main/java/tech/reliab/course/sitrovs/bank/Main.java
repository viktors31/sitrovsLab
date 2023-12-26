package tech.reliab.course.sitrovs.bank;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import static tech.reliab.course.sitrovs.bank.utils.Constants.*;

import tech.reliab.course.sitrovs.bank.entity.Bank;
import tech.reliab.course.sitrovs.bank.entity.BankAtm;
import tech.reliab.course.sitrovs.bank.entity.BankOffice;
import tech.reliab.course.sitrovs.bank.entity.CreditAccount;
import tech.reliab.course.sitrovs.bank.entity.Employee;
import tech.reliab.course.sitrovs.bank.entity.PaymentAccount;
import tech.reliab.course.sitrovs.bank.entity.User;
import tech.reliab.course.sitrovs.bank.exception.CreditException;
import tech.reliab.course.sitrovs.bank.exception.NoPaymentAccountException;
import tech.reliab.course.sitrovs.bank.exception.NotFoundException;
import tech.reliab.course.sitrovs.bank.exception.UniquenessException;
import tech.reliab.course.sitrovs.bank.service.AtmService;
import tech.reliab.course.sitrovs.bank.service.BankOfficeService;
import tech.reliab.course.sitrovs.bank.service.BankService;
import tech.reliab.course.sitrovs.bank.service.CreditAccountService;
import tech.reliab.course.sitrovs.bank.service.EmployeeService;
import tech.reliab.course.sitrovs.bank.service.PaymentAccountService;
import tech.reliab.course.sitrovs.bank.service.UserService;
import tech.reliab.course.sitrovs.bank.service.impl.AtmServiceImpl;
import tech.reliab.course.sitrovs.bank.service.impl.BankOfficeServiceImpl;
import tech.reliab.course.sitrovs.bank.service.impl.BankServiceImpl;
import tech.reliab.course.sitrovs.bank.service.impl.CreditAccountServiceImpl;
import tech.reliab.course.sitrovs.bank.service.impl.EmployeeServiceImpl;
import tech.reliab.course.sitrovs.bank.service.impl.PaymentAccountServiceImpl;
import tech.reliab.course.sitrovs.bank.service.impl.UserServiceImpl;
import tech.reliab.course.sitrovs.bank.utils.BankAtmStatus;
import tech.reliab.course.sitrovs.bank.utils.Constants;


public class Main {
  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Random random = new Random();
    new Constants();
    Scanner scanner = new Scanner(System.in);

    // Создание сервисов
    BankService bankService = new BankServiceImpl();
    BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
    bankService.setBankOfficeService(bankOfficeService);
    EmployeeService employeeService = new EmployeeServiceImpl(bankOfficeService);
    bankOfficeService.setEmployeeService(employeeService);
    AtmService atmService = new AtmServiceImpl(bankOfficeService);
    bankOfficeService.setAtmService(atmService);
    UserService userService = new UserServiceImpl(bankService);
    bankService.setUserService(userService);
    PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(userService);
    CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService);
    paymentAccountService.setCreditAccountService(creditAccountService);
    paymentAccountService.setBankService(bankService);

    try {
      // Создание банков
      bankService.create(new Bank("Nikolotov Bank"));
      bankService.create(new Bank("Alpha Bank"));
      bankService.create(new Bank("Tinkoff Bank"));
      bankService.create(new Bank("Bank of America"));
      bankService.create(new Bank("Commerzbank"));
      // System.out.println(bank);

      // Создание офисов в каждом банке
      List<Bank> banks = bankService.getAllBanks();
      for (Bank bank : banks) {
        for (int i = 1; i <= 3; i++) {
          bankOfficeService.create(new BankOffice(
            "Office #" + String.valueOf(i) + " of " + bank.getName(), 
            "Belgorod, Pushkina st., " + String.valueOf(i), 
            bank, 
            true, 
            true,
            true,
            true,
            true,
            10000,
            100 * i
          ));
        }
      }

      // Добавление сотрудников в каждый офис
      List<BankOffice> offices = bankOfficeService.getAllOffices();
      for (BankOffice office : offices) {
        for (int i = 1; i <= 5; i++) {
          employeeService.create(new Employee(
            HUMAN_NAMES.get(random.nextInt(HUMAN_NAMES.size())), 
            LocalDate.of(random.nextInt(1990, 2003), random.nextInt(1, 13), random.nextInt(1, 29)), 
            BANK_ROLES.get(random.nextInt(BANK_ROLES.size())), 
            office.getBank(), 
            true,
            office,
            true,
            500
          ));
        }
      }

      // Добавление банкоматов в каждый офис
      for (BankOffice office : offices) {
        for (int i = 1; i <= 3; i++) {
          atmService.create(new BankAtm(
            "Atm " + String.valueOf(i), 
            office.getAddress(), 
            BankAtmStatus.WORKING, 
            office.getBank(), 
            office,
            true, 
            true, 
            random.nextDouble() * 15000, 
            random.nextDouble() * 25
          ));
        }
      }

      // Добавление клиентов в каждый банк
      for (Bank bank : banks) {
        for (int i = 1; i <= 5; i++) {
          userService.create(
            new User(
              HUMAN_NAMES.get(random.nextInt(HUMAN_NAMES.size())), 
              LocalDate.of(random.nextInt(1940, 2003), random.nextInt(1, 13), random.nextInt(1, 29)), 
              COMPANY_NAMES.get(random.nextInt(COMPANY_NAMES.size())), 
              random.nextDouble() * 10000, 
              bank, 
              random.nextInt(10000)
            ));
        }
      }

      // Добавление платежных счетов каждому клиенту
      List<User> users = userService.getAllUsers();
      for (User user : users) {
        for (int i = 1; i <= 2; i++) {
          paymentAccountService.create(new PaymentAccount(
            user, 
            user.getBank(), 
            random.nextDouble() * 10000
          ));
        }
      }

      // Добавление кредитных счетов каждому клиенту
      for (User user : users) {
        for (int i = 1; i <= 2; i++) {
          List<BankOffice> bankOffices = bankService.getAllOfficesByBankId(user.getBank().getId());
          BankOffice randomOffice = bankOffices.get(random.nextInt(bankOffices.size()));
          List<Employee> officeEmployees = bankOfficeService.getAllEmployeesByOfficeId(randomOffice.getId());
          Employee randomEmployee = officeEmployees.get(random.nextInt(officeEmployees.size()));

          creditAccountService.create(new CreditAccount(
            user, 
            user.getBank(), 
            LocalDate.of(2023, 10, 1), 
            LocalDate.of(2026, 10, 1), 
            36, 
            3600, 
            3600, 
            100, 
            user.getBank().getInterestRate(), 
            randomEmployee, 
            userService.getAllPaymentAccountsByUserId(user.getId()).get(random.nextInt(userService.getAllPaymentAccountsByUserId(user.getId()).size()))
          ));
        }
      }

      System.out.println("\nWelcome to lab4.");
      System.out.println("Number of banks in system: " + bankService.getAllBanks().size());
      for (Bank bank : bankService.getAllBanks()) {
        System.out.println("id: " + bank.getId() + " - " + bank.getName());
      }

      while (true) {
        try {
          System.out.println("\nChoose an action: ");
          System.out.println("b - check bank data by bank id");
          System.out.println("u - check user data by user id");
          System.out.println("a - apply for a credit");
          System.out.println("e - export user accounts by bank id to .txt file");
          System.out.println("i - import accounts from .txt file and move them to another bank");
          System.out.println("q - quit program");

          String action = scanner.nextLine();

          if (action.equals("b")) {
            System.out.println("Enter bank id:");
            int bankIdToPrint = scanner.nextInt();
            scanner.nextLine();
            bankService.printBankData(bankIdToPrint);
          } else if (action.equals("u")) {
            System.out.println("Enter user id:");
            int userIdToPrint = scanner.nextInt();
            scanner.nextLine();
            userService.printUserData(userIdToPrint, true);
          } else if (action.equals("a")) {
            System.out.println("Which user wants to apply for a credit?");
            for (User user : userService.getAllUsers()) {
              System.out.println("id: " + user.getId() + " - " + user.getName());
            }

            System.out.println("Enter user id:");
            int userId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter credit amount:");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter duration in months:");
            int months = scanner.nextInt();
            scanner.nextLine();

            List<Bank> suitableBanks = bankService.getBanksSuitable(amount, months);
            System.out.println("List of suitable banks:");
            for (Bank bank : suitableBanks) {
              System.out.println("id: " + bank.getId() + " - " + bank.getName());
            }

            System.out.println("Enter chosen bank id:");
            int bankId = scanner.nextInt();
            scanner.nextLine();
            Bank bank = bankService.getBankById(bankId);
            BankOffice bankOffice = bankService.getBankOfficeSuitableInBank(bank, amount).get(0);
            Employee employee = bankOfficeService.getSuitableEmployeeInOffice(bankOffice).get(0);

            PaymentAccount paymentAccount;
            // Если у клиента нет платежного счета - следует его создать
            try {
              paymentAccount = userService.getBestPaymentAccount(userId);
            } catch (NoPaymentAccountException e) {
              paymentAccount = paymentAccountService.create(new PaymentAccount(
                userService.getUserById(userId),
                userService.getUserById(userId).getBank(),
                0
              ));
            }

            CreditAccount creditAccount = creditAccountService.create(new CreditAccount(
              userService.getUserById(userId), 
              bank, 
              LocalDate.now(),
              LocalDate.now().plusMonths(months),
              months,
              amount,
              amount,
              0,
              bank.getInterestRate(),
              employee,
              paymentAccount
            ));
            if (bankService.approveCredit(bank, creditAccount, employee)) {
              System.out.println("Apply for credit was approved!");
              System.out.println("credit account id: " + creditAccount.getId());
            } else {
              System.out.println("Credit was not approved");
            }
          } else if (action.equals("e")) {
            System.out.println("Available users:");
            for (User user : userService.getAllUsers()) {
              System.out.println("id: " + user.getId() + " - " + user.getName());
            }

            System.out.println("Enter user id:");
            int userId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter bank id:");
            int bankId = scanner.nextInt();
            scanner.nextLine();

            boolean success = userService.exportUserAccountsToTxtFile(userId, bankId);

            if (success) {
              System.out.println(COLOR_GREEN + "Export done successfully" + COLOR_RESET);
            } else {
              System.out.println(COLOR_RED + "Export operation wasn't done" + COLOR_RESET);
            }
          } else if (action.equals("i")) {
            System.out.println("Enter file name:");
            String fileName = scanner.nextLine();

            System.out.println("Enter new bank id:");
            int newBankId = scanner.nextInt();
            scanner.nextLine();

            boolean success = paymentAccountService.importAccountsFromTxtAndTransferToAnotherBank(fileName, newBankId);

            if (success) {
              System.out.println(COLOR_GREEN + "Import done successfully" + COLOR_RESET);
            } else {
              System.out.println(COLOR_RED + "Import operation wasn't done" + COLOR_RESET);
            }
          } else if (action.equals("q")) {
            break;
          } else {
            System.out.println("Error: unknown action. Please, try again");
          }
        } catch (CreditException | NotFoundException | NoPaymentAccountException e) {
          System.err.println(e.getMessage());
        }
      }
    } catch (UniquenessException e) {
      System.err.println(e.getMessage());
    }
  }
}