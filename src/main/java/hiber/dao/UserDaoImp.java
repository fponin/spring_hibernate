package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override

    public User getUserByCar(String model, int series) {
        return sessionFactory.getCurrentSession()
                .createQuery("select user from User user where user.car.series = ?1 and user.car.model = ?2", User.class)
                .setParameter(1, series)
                .setParameter(2, model)
                .stream()
                .findAny()
                .orElse(null);
    }


}
