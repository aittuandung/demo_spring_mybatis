package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();

    User getUserById(long id);

    int createUser(User user);

    int removeUserById(long id);

    int updateUserById(User user);
}
