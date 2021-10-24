package com.smoothiemx.ecoomerceapp.shopmebackend.app.user;

import com.smoothiemx.ecommerceapp.shopmecommon.entity.Role;
import com.smoothiemx.ecommerceapp.shopmecommon.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll() {
        return (List<User>) this.userRepository.findAll();
    }

    public List<Role> listRoles() {
        return (List<Role>) this.roleRepository.findAll();
    }

    public void save(User user) {
        encodePassword(user);
        userRepository.save(user);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(String email) {
        User userByEmail = this.userRepository.getUserByEmail(email);
        return userByEmail == null;
    }
}