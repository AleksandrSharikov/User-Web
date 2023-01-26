package dao;



import model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext//(unitName = "entityManagerFactoryBean")
    private EntityManager entityManager;

    @Override
    public List<User> getUserList() {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();

    }

    @Override
    public User getById(int id) {

        return entityManager.find(User.class, id);
    }


    @Override
    public void deletUser(int id) {

        User user = getById(id);
        if (user != null)
            entityManager.remove(user);
    }



    @Override
    public void addUser(User user) {

        entityManager.persist(user);
    }



    @Override
    public void editUser(User user, int id) {

        entityManager.merge(user);

    }


}
