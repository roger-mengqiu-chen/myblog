package com.myblog.myblog.controller;

import com.myblog.myblog.constant.Status;
import com.myblog.myblog.constant.Website;
import com.myblog.myblog.entity.User;
import com.myblog.myblog.request.CreateUserRequest;
import com.myblog.myblog.response.JsonResponse;
import com.myblog.myblog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    private final static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @PostMapping()
    public JsonResponse createUser (@RequestBody CreateUserRequest createUserRequest) {

        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(encoder.encode(createUserRequest.getPassword()));
        user.setAvatarUrl(Website.LINK + "/img/avatar/visitor.png");
        if (!createUserRequest.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
            return new JsonResponse(Status.INVALID_FORMAT, "Email format is invalid");
        }
        user.setEmail(createUserRequest.getEmail());
        user.setRoleId(1);
        return userService.createUser(user);
    }
}
