package my.project.report.repository.impl;

import my.project.report.model.Costs;
import my.project.report.model.User;
import my.project.report.repository.IUserDAO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public class UserDAO implements IUserDAO {

    @PersistenceContext
    private EntityManager manager;

    private Session getSession() {
        return manager.unwrap(Session.class);
    }
    @Override
    public void createUser(User user) {
        getSession().saveOrUpdate(user);
    }

    @Override
    public boolean ifUserExists(String login) {
        TypedQuery<Long> query = manager.createQuery("SELECT count(u) from User u where u.login = :login", Long.class);
        query.setParameter("login", login);
        Long count = query.getSingleResult();
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User findById(Long id) {
        User user = manager.find(User.class, id);
        initLazyCosts(Collections.singletonList(user));
        return user;
    }

    @Override
    public Optional<User> getUserByLogin(String login, boolean initLazyObjects) {
        return Optional.empty();
    }

    private void initLazyCosts(List<User> users) {
        for (User user : users) {
            for (Costs costs : user.getCosts()) {
                Hibernate.initialize(costs);
            }
        }
    }
}
