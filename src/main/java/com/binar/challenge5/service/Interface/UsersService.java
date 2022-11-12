package com.binar.challenge5.service.Interface;

import com.binar.challenge5.entities.Users;
import com.binar.challenge5.model.response.UsersResponse;
import com.binar.challenge5.model.request.UsersRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {

    UsersResponse newUser(UsersRequest user);

    UsersResponse updateUser(UsersRequest user) throws Exception;

    void deleteUser(Integer id_user);

    List<Users> findAllUser();

    Users searchUserById(Integer id_user) throws Exception;

    List<Users> searchUserByName(String username) throws Exception;
}
