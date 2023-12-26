package tech.reliab.course.sitrovs.bank.exception;

public class CreditException extends RuntimeException {
	public CreditException(String reason) {
		super("Error: credit can not be approven - " + reason);
	}
}