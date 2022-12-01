package com.binar.challenge5.service;

import com.binar.challenge5.entities.Users;
import com.binar.challenge5.model.request.UsersRequest;
import com.binar.challenge5.service.Interface.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UsersServiceImplImplTest {
    @Autowired
    UsersService usersService;

    @Test
    void tesAddData() {
//        UsersRequest user = new UsersRequest();
//        user.setUsername("Rajab");
//        user.setAddress("Indonesia");
//        user.setEmail("rajab@gmail.com");
//        user.setPassword("rajab");
//
//        try {
//            usersService.newUser(user);
//            System.out.println("Successful Add Data");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    @Test
    void tesUpdateData() {
//
//        UsersRequest user = new UsersRequest();
//        user.setIdUser(78);
//        user.setUsername("D Rajab");
//        user.setAddress("Indonesia");
//        user.setEmail("rajab@gmail.com");
//        user.setPassword("rajabpass");
//
//        try {
//            usersService.updateUser(user);
//            System.out.println("Successful Update Data");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    @Test
    void deleteUsers() {
        try {
//            usersService.deleteUser(80L);
            System.out.println("Successful Delete Data");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void tesGetUserById() {
        try {
            Users usersList = usersService.searchUserById(2);
            System.out.println("UserName : " + usersList.getUsername());
            System.out.println("Address : " + usersList.getAddress());
            System.out.println("Email : " + usersList.getEmail());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void tesGetUserByName() {
        try {
            List<Users> usersList = usersService.searchUserByName("Rajab");
            usersList.forEach(usr -> {
                System.out.println("Id : " + usr.getId());
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testGetAllUsers() {
        try {
            List<Users> usersList0 = usersService.findAllUser();
            usersList0.forEach(usr -> {
                System.out.print(usr.getId() + " - ");
                System.out.println(usr.getUsername());
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
