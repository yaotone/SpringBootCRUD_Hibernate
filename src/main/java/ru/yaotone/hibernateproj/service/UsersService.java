package ru.yaotone.hibernateproj.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yaotone.hibernateproj.HiberDAOImpl.HiberDAO;
import ru.yaotone.hibernateproj.model.User;

import java.util.List;

@Service
public class UsersService {
    private final HiberDAO hiberDAO;

    public UsersService(HiberDAO hiberDAO) {
        this.hiberDAO = hiberDAO;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return hiberDAO.showUsers();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return hiberDAO.showUser(id);
    }

    @Transactional
    public void addUser(User user) {
        hiberDAO.addUser(user);
    }

    @Transactional
    public void updateUser(User user, Long id) {
        hiberDAO.updateUser(user, id);
    }

    @Transactional
    public void deleteUser(Long id) {
        hiberDAO.deleteUser(id);
    }
}

