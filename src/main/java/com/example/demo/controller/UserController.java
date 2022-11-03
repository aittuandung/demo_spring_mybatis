package com.example.demo.controller;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserMapper userMapper;
    private ProductMapper productMapper;

    public UserController(UserMapper userMapper, ProductMapper productMapper) {
        this.userMapper = userMapper;
        this.productMapper = productMapper;
    }

    @GetMapping("")
    public String user(Model model) {
        List<User> users = userMapper.getAllUsers();
        users.forEach(u -> {
            logger.info("User id: {}", u);
        });

        model.addAttribute("users", users);

        return "user";
    }

    @GetMapping("/product")
    public String product(Model model) {
        List<Product> products = productMapper.getAllProducts();
        products.forEach(p -> {
            logger.info("Product: {}", p);
        });

        model.addAttribute("products", products);

        return "product";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable long id, Model model){
        User user = userMapper.getUserById(id);
        logger.info("User: ", user.getId());
        model.addAttribute("user", user);
        return "view";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/save")
    public String save(User user) {
        user.setId((long) (Math.random() * 10000));
        userMapper.createUser(user);
        return "redirect:/user";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable long id, Model model){
        User user = userMapper.getUserById(id);
        model.addAttribute("user", user);
        return "remove";
    }

    @PostMapping("/remove")
    public String remove(User user){
        userMapper.removeUserById(user.getId());
        return "redirect:/user";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable long id, Model model){
        User user = userMapper.getUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/update")
    public String update(User user){
        logger.info("Update user: {}", user);
        userMapper.updateUserById(user);
        return "redirect:/user";
    }
}
