package my.project.report.service.impl;

import my.project.report.lib.dto.RegisterFormDTO;
import my.project.report.lib.exception.UserAlreadyExistsException;
import my.project.report.lib.exception.UserNotFoundException;
import my.project.report.model.User;
import my.project.report.repository.IUserDAO;
import my.project.report.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    private final IUserDAO userDAO;

    private final BCryptPasswordEncoder encoder;

@Autowired
    public UserService(IUserDAO userDAO, BCryptPasswordEncoder encoder) {
        this.userDAO = userDAO;
        this.encoder = encoder;
    }


    @Override
    public User createUser(RegisterFormDTO registerForm) throws UserAlreadyExistsException {
        User user = registerForm.convert(encoder);
        if (userDAO.ifUserExists(user.getLogin())) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        userDAO.createUser(user);
        return user;
    }

    @Override
    public User findUserByLogin(String login, boolean initLazyObjects) throws UserNotFoundException {
        Optional<User> opt = userDAO.getUserByLogin(login, initLazyObjects);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new UserNotFoundException("Пользователь с логином " + login + " не найден");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.getUserByLogin(s, false)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь " + s + " не найден"));
    }
}
