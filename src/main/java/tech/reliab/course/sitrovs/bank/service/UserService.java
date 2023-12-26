package tech.reliab.course.sitrovs.bank.service;

import tech.reliab.course.sitrovs.bank.entity.User;

public interface UserService {
  User create(User user);
  int calculateCreditRating(User user);
}
