package tech.reliab.course.sitrovs.bank.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
  public static final byte MAX_BANK_RATING = 100;
  public static final double MAX_BANK_TOTAL_MONEY = 1000000;
  public static final double MAX_BANK_INTEREST_RATE = 20;
  public static final double MAX_USER_MONTHLY_INCOME = 10000;
  public static final List<String> HUMAN_NAMES = new ArrayList<>() {
    {
      add("Aleksandr");
      add("Sergei");
      add("Andrey");
      add("Denis");
      add("Anton");
      add("Lev");
      add("Roman");
      add("Danila");
      add("Nikolaj");
      add("Elon");
      add("Johhny");
      add("John");
      add("Una");
      add("Alex");
      add("Olga");
      add("Rooney");
      add("Barbara");
      add("Anna");
      add("Anya");
      add("Alice");
      add("Siri");
    }
  };
  public static final List<String> BANK_ROLES = new ArrayList<>() {
    {
      add("CEO");
      add("Cashier");
      add("Manager");
      add("Vault keeper");
      add("Programmer");
      add("Lawyer");
    }
  };
  public static final List<String> COMPANY_NAMES = new ArrayList<>() {
    {
      add("Google");
      add("Amazon");
      add("SpaceX");
      add("Microsoft");
      add("Yandex");
      add("TSMC");
      add("Netflix");
    }
  };

  public static final String COLOR_RED = "\u001B[31m";
  public static final String COLOR_GREEN = "\u001B[32m";
  public static final String COLOR_BLUE = "\u001B[34m";
  public static final String COLOR_PURPLE = "\u001B[35m";
  public static final String COLOR_RESET = "\u001B[0m";
}