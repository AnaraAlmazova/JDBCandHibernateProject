package com.peaksoft;


import com.peaksoft.model.User;
import com.peaksoft.service.UserService;
import com.peaksoft.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
    List<User> users = new ArrayList<>();
        users.add(new User("Anara", "Almazova", (byte) 28));
        users.add(new User("Bek", "Almazov", (byte) 29));
        users.add(new User("Aidai", "Mamatova", (byte) 23));
        users.add(new User("Dilnaz", "Tolubai", (byte) 18));

        for (User user : users) {
    userService.saveUser(user.getName(), user.getLastName(),  user.getAge());
    System.out.println(user.getName() + " базага кошулду");
}

    List<User> u = userService.getAllUsers();
        for (User us : u) {
    System.out.println(us);
}

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
