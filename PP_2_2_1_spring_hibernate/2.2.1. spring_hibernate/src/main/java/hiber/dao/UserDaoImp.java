package hiber.dao;

import hiber.model.Car;
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
    public void add(User user, Car car) {
        sessionFactory.getCurrentSession().save(car);
        user.setUserCar(car);
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUser(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where userCar.model = :model and userCar.series = :series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getSingleResult();

    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
