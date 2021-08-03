package com.socialmediaplatform.web.rest.base;

import com.socialmediaplatform.domain.User;
import com.socialmediaplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Base Controller to be extended by all controllers
 */
@RestController
@RequestMapping("/api")
public class BaseController {

    @Autowired
    private UserService userService;

    public BaseController() {}

    public User getActiveUser() {
        return this.userService.getUserWithAuthorities().orElse(null);
    }

    public Long getActiveUserId() {
        return this.userService.getUserWithAuthorities().get().getId();
    }
}
