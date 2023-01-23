package dao;

import model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class UserDaoListImpl implements UserDao{
    private static List<User> userList;
    private static int id = 0;
    public UserDaoListImpl(){
        userList = new ArrayList<>();
        userList.add(new User(++id,"Model1","Manufacture1",1));
        userList.add(new User(++id,"Model2","Manufacture1",2));
        userList.add(new User(++id,"Model3","Manufacture1",3));
        userList.add(new User(++id,"Model4","Manufacture1",4));
        userList.add(new User(++id,"Model5","Manufacture1",5));
        userList.add(new User(++id,"Model1","Manufacture1",6));
        userList.add(new User(++id,"Model2","Manufacture1",7));
        userList.add(new User(++id,"Model3","Manufacture1",8));
        userList.add(new User(++id,"Model4","Manufacture1",9));
        userList.add(new User(++id,"Model5","Manufacture1",10));
    }
    @Override
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public User getById(int id) {
        return userList.stream().filter(user ->user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void deletUser(int id) {
        if(id <= userList.size())
            userList.remove(--id);
            for (int i = id; i<userList.size();){
                userList.get(i).setName("refactor");
                userList.get(i).setId(++i);
                }
            UserDaoListImpl.id--;
    }

    @Override
    public void addUser(User user) {
        user.setId(++id);
        userList.add(user);
    }

    @Override
    public void editUser(User user, int id) {
        userList.get(--id).setName(user.getName());
        userList.get(id).setSurname(user.getSurname());
        userList.get(id).setAge(user.getAge());
    }
}
