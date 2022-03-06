package com.example.atomikos.controller;


import com.example.atomikos.service.primary.service.UsersService;
import com.example.atomikos.service.primary.vo.Users;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hank
 * @since 2022-03-06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "usersService")
    private UsersService usersService;

    @PostMapping("/create")
    public String create(@RequestBody Users users){
        users.setCreateDate(LocalDate.now());
        users.setUpdateDate(LocalDate.now());

        usersService.insertTwo(users);
        return "";
    }
}

