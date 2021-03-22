package my.project.report.repository;

import my.project.report.model.User;

import java.util.Optional;

public interface IUserDAO {

    void createUser(User user);

    boolean ifUserExists(String login);

    Optional<User> getUserByLogin(String login, boolean initLazyObjects);
}
