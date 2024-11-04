package ru.yaotone.hibernateproj.HiberDAOImpl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ru.yaotone.hibernateproj.model.Role;

@RequiredArgsConstructor
public class RoleDAO {

    private final EntityManager entityManager;

    public Role showRole(int id) {
        return entityManager.createQuery("from User where id = :id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
