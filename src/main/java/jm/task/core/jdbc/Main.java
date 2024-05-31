package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userMake = new UserServiceImpl();
        userMake.createUsersTable();
        userMake.createUsersTable();
        userMake.saveUser("Alexey", "Blinov", (byte) 39);
        userMake.saveUser("Egor", "Evtushenko", (byte) 37);
        userMake.saveUser("Vitalij", "Lepehin", (byte) 34);
        userMake.saveUser("Alina", "Maximova", (byte) 33);
        System.out.println(userMake.getAllUsers());
        userMake.cleanUsersTable();
        userMake.dropUsersTable();
    }
}
