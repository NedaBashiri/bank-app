package repository;

import base.repository.BaseRepository;
import entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findUserByName(String name);
    Optional<User> findUserByEmail(String email);
}