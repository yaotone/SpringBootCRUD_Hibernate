package ru.yaotone.hibernateproj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yaotone.hibernateproj.DTO.UpdateUserDTO;
import ru.yaotone.hibernateproj.DTO.UserDTO;
import ru.yaotone.hibernateproj.HiberDAOImpl.HiberDAO;
import ru.yaotone.hibernateproj.mapper.UserMapper;
import ru.yaotone.hibernateproj.model.Role;
import ru.yaotone.hibernateproj.model.User;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final HiberDAO hiberDAO;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        hiberDAO.addUser(user);
    }

    @Transactional
    public void updateUser(UpdateUserDTO userDTO, Long id) {
        User user = userMapper.updateUserDTOToUser(userDTO);
        hiberDAO.updateUser(user, id);
    }

    @Transactional
    public User getUserByEmail(String email) {
        return hiberDAO.showUserByEmail(email);
    }

    @Transactional
    public void deleteUser(Long id) {
        hiberDAO.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        User user = hiberDAO.showUserByEmail(email);
        if (user == null) {
            return null;
        }

        return user;
    }
}

