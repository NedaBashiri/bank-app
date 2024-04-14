package service;

import base.service.BaseService;
import entity.User;

import java.util.Optional;

public interface UserService extends BaseService<User, Long> {

    String createUser(User user);

    Optional<User> findUserByName(String name);
    Optional<User> findUserByEmail(String email);

}