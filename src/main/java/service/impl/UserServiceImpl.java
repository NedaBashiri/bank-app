package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.User;
import org.hibernate.exception.DataException;
import repository.UserRepository;
import service.UserService;

import java.util.Optional;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public String createUser(User user) {
        String message = "";
        try {
            baseRepository.persist(user);
            message = "Registration Success! Please login";
        } catch (DataException e) {
            message = "Wrong entered data format for " + e.getMessage() + "!";
        }
        return message;
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return baseRepository.findUserByName(name);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return baseRepository.findUserByEmail(email);
    }
}

