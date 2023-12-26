package tech.reliab.course.sitrovs.bank.exception;

public class AccountTransferException extends RuntimeException {
  public AccountTransferException(String problem) {
		super("Error: account transfer is not possible: " + problem);
	}
}