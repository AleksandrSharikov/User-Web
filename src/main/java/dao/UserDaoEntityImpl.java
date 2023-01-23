package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class UserDaoEntityImpl implements UserDao{

    @PersistenceContext//(unitName = "entityManagerFactoryBean")
    private EntityManager entityManager;

  //  public UserDaoEntityImpl(EntityManager entityManager){this.entityManager = entityManager;}
    @Override
    public List<User> getUserList() {
      //  List<User> answer =new ArrayList<>();
      //  answer.add(new User("Ivan", "Ivanov",55));
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM Users u", User.class);
        return query.getResultList() == null ? new ArrayList<User>() : query.getResultList();
       // return answer;
    }

    @Override
    public User getById(int id) {

        return (User) entityManager.find(User.class, id);
    }


    @Override
    @Transactional
    public void deletUser(int id) {

        User user = getById(id);
        if (user != null)
            entityManager.remove(user);
    }



    @Transactional
    @Override
    public void addUser(User user) {

        entityManager.persist(user);
    }



    @Override
    @Transactional
    public void editUser(User user, int id) {

        User userDel = getById(id);
        if (userDel != null)
            entityManager.remove(userDel);
        entityManager.persist(user);

    }


}
