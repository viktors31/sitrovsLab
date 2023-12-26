package tech.reliab.course.sitrovs.bank.service;

import java.util.List;

import tech.reliab.course.sitrovs.bank.entity.PaymentAccount;
import tech.reliab.course.sitrovs.bank.exception.AccountTransferException;
import tech.reliab.course.sitrovs.bank.exception.UniquenessException;

public interface PaymentAccountService {
  PaymentAccount create(PaymentAccount paymentAccount) throws UniquenessException;
  public void printPaymentData(int id);
  public PaymentAccount getPaymentAccountById(int id);
  public List<PaymentAccount> getAllPaymentAccounts();
  boolean depositMoney(PaymentAccount account, double amount);
  boolean withdrawMoney(PaymentAccount account, double amount);
  public boolean importAccountsFromTxtAndTransferToAnotherBank(String fileName, int newBankId) throws AccountTransferException;
  public void setCreditAccountService(CreditAccountService creditAccountService);
  public void setBankService(BankService bankService);
}
