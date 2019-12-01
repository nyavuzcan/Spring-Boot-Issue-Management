package com.temelt.issuemanagement.service.impl;

import com.temelt.issuemanagement.entity.User;
import com.temelt.issuemanagement.repository.UserRepository;
import com.temelt.issuemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }




    @Override
    public User save(User user) {
        if(user.getEmail()==null)
            throw new IllegalArgumentException("Username cannot be null");
        user=userRepository.save(user);
        return user;
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public Page<User> getAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(User user) {
        userRepository.delete(user);
        return Boolean.TRUE;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
