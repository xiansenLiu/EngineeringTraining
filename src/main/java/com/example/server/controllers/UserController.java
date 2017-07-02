package com.example.server.controllers;

import com.example.server.domain.SignUpResult;
import com.example.server.domain.User;
import com.example.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         10:01 PM
 */
@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/signup")
    public SignUpResult signUp(@RequestParam("account") String account, @RequestParam("username") String username, @RequestParam("password") String password) {
        int status = SignUpResult.ERROR;
        int msg = SignUpResult.MSG_ALREADY_EXIST;
        User user = userRepository.findByAccount(account);
        if (user == null) {
            user = new User(account, username, password);
            userRepository.save(user);
            status = SignUpResult.SUCCESS;
            msg = SignUpResult.MSG_SIGN_UP_OK;
        }

        return new SignUpResult(status, msg);
    }

    @PostMapping("/signin")
    public void signIn() {

    }
}
