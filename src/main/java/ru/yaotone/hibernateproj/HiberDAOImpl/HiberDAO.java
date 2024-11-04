package ru.yaotone.hibernateproj.HiberDAOImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yaotone.hibernateproj.model.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HiberDAO {

    private final EntityManager entityManager;

    public List<User> showUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    public User showUser(Long id) {
        return entityManager.createQuery("FROM User WHERE id=:id", User.class)
                .setParameter("id", id).getSingleResult();
    }

    public User showUserByEmail(String email) {
        User ans = null;
        try {
            ans = entityManager.createQuery("FROM User u where u.email=:email", User.class)
                    .setParameter("email", email).getSingleResult();
        }catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        return ans;
    }

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public void updateUser(User updatedUser, Long id) {
        entityManager.createQuery("UPDATE User u SET u.name=:name, u.surname=:surname, u.email=:email WHERE u.id=:id")
                .setParameter("name", updatedUser.getName())
                .setParameter("surname", updatedUser.getSurname())
                .setParameter("email", updatedUser.getEmail())
                .setParameter("id", id)
                .executeUpdate();
    }

    public void deleteUser(Long id) {
        entityManager.createQuery("DELETE FROM User WHERE id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
