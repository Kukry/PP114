package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl userPrim = new UserDaoJDBCImpl();
    public void createUsersTable() {
        userPrim.createUsersTable();
    }

    public void dropUsersTable() {
        userPrim.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userPrim.saveUser(name, lastName, age);
        System.out.println("User с именем - ".concat(name).concat(" добавлен в базу данных"));
    }

    public void removeUserById(long id) {
        userPrim.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userPrim.getAllUsers();
    }

    public void cleanUsersTable() {
        userPrim.cleanUsersTable();
    }
}
