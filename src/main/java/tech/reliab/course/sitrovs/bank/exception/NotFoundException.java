package tech.reliab.course.sitrovs.bank.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String entity, int id) {
		super("Error: " + entity + " with id: " + id + " was not found.");
	}
}