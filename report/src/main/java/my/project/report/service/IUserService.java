package my.project.report.service;

import my.project.report.model.User;
import my.project.report.lib.dto.RegisterFormDTO;
import my.project.report.lib.exception.UserAlreadyExistsException;
import my.project.report.lib.exception.UserNotFoundException;

public interface IUserService {

    User createUser(RegisterFormDTO registerForm)throws UserAlreadyExistsException;

    User findUserByLogin(String login, boolean initLazyObjects) throws UserNotFoundException;
}
