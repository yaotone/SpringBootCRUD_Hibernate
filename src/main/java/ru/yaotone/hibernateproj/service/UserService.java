package ru.yaotone.hibernateproj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yaotone.hibernateproj.DTO.UserDTO;
import ru.yaotone.hibernateproj.HiberDAOImpl.HiberDAO;
import ru.yaotone.hibernateproj.mapper.UserMapper;
import ru.yaotone.hibernateproj.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final HiberDAO hiberDAO;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return hiberDAO.showUsers();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return hiberDAO.showUser(id);
    }

    @Transactional
    public void addUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);

        hiberDAO.addUser(user);
    }

    @Transactional
    public void updateUser(UserDTO userDTO, Long id) {
        User user = userMapper.userDTOToUser(userDTO);
        hiberDAO.updateUser(user, id);
    }

    @Transactional
    public void deleteUser(Long id) {
        hiberDAO.deleteUser(id);
    }
}

