package tech.reliab.course.sitrovs.bank.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
  public static final byte MAX_BANK_RATING = 100;
  public static final double MAX_BANK_TOTAL_MONEY = 1000000;
  public static final double MAX_BANK_INTEREST_RATE = 20;
  public static final double MAX_USER_MONTHLY_INCOME = 10000;
  public static final List<String> HUMAN_NAMES = new ArrayList<>();
  public static final List<String> BANK_ROLES = new ArrayList<>();
  public static final List<String> COMPANY_NAMES = new ArrayList<>();

  public Constants() {
    HUMAN_NAMES.add("Aleksandr");
    HUMAN_NAMES.add("Sergei");
    HUMAN_NAMES.add("Andrey");
    HUMAN_NAMES.add("Denis");
    HUMAN_NAMES.add("Anton");
    HUMAN_NAMES.add("Lev");
    HUMAN_NAMES.add("Roman");
    HUMAN_NAMES.add("Danila");
    HUMAN_NAMES.add("Nikolaj");
    HUMAN_NAMES.add("Elon");
    HUMAN_NAMES.add("Johhny");
    HUMAN_NAMES.add("John");
    HUMAN_NAMES.add("Una");
    HUMAN_NAMES.add("Alex");
    HUMAN_NAMES.add("Olga");
    HUMAN_NAMES.add("Rooney");
    HUMAN_NAMES.add("Barbara");
    HUMAN_NAMES.add("Anna");
    HUMAN_NAMES.add("Anya");
    HUMAN_NAMES.add("Alice");
    HUMAN_NAMES.add("Siri");

    BANK_ROLES.add("CEO");
    BANK_ROLES.add("Cashier");
    BANK_ROLES.add("Manager");
    BANK_ROLES.add("Vault keeper");
    BANK_ROLES.add("Programmer");
    BANK_ROLES.add("Lawyer");

    COMPANY_NAMES.add("Google");
    COMPANY_NAMES.add("Amazon");
    COMPANY_NAMES.add("SpaceX");
    COMPANY_NAMES.add("Microsoft");
    COMPANY_NAMES.add("Yandex");
    COMPANY_NAMES.add("TSMC");
    COMPANY_NAMES.add("Netflix");
  }
}