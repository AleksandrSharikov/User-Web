package dao;

import model.User;

import java.util.List;

public interface UserDao {
    List<User> getUserList();
    User getById(int id);
    void deletUser(int id);
    void addUser(User user);
    void editUser(User user, int id);

}
