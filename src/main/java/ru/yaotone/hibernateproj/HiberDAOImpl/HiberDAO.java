package ru.yaotone.hibernateproj.HiberDAOImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yaotone.hibernateproj.model.User;
import ru.yaotone.hibernateproj.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HiberDAO {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> showUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User showUser(Long id) {
        return userRepository.getOne(id);
    }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User updatedUser, Long id) {
        User user = userRepository.findById(id).orElse(null);

        assert user != null;
        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setAge(updatedUser.getAge());

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
