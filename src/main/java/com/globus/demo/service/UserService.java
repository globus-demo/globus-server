package com.globus.demo.service;

import com.globus.demo.model.entites.User;
import com.globus.demo.response.token.Token;
import com.globus.demo.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private static Logger log = LoggerFactory.getLogger(UserService.class.getName());

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Token create(User user) {
        String email = user.getEmail();
        log.info("Create user from email: " + email);

        if (userRepository.findUserByEmail(email) != null) {
            log.info("User exist!");
            return null;
        }

        String code = user.getEmail() + user.getName() + user.getSurname();
        user.setToken(code);
        userRepository.save(user);

        Integer id = userRepository.findUserByEmail(email).getId();
        Token token = new Token(id, code);

        return token;
    }

    @Override
    public Token read(User user) {
        User userFromCollection = userRepository.findUserByEmail(user.getEmail());
        if(userFromCollection != null) {
            if (userFromCollection.getPassword().equals(user.getPassword())) {
                return new Token(userFromCollection.getId(), userFromCollection.getToken());
            }
            else {
                log.info("Password fake!");
                return null;
            }
        }

        log.info("User not found!");
        return null;
    }

    @Override
    public User getUserInformation(Token token1) {
        User user =  userRepository.findUserById(token1.getId());
        if(user.getToken().equals(token1.getToken())){
            return user;
        }
        return null;
    }

    @Override
    public User readByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
