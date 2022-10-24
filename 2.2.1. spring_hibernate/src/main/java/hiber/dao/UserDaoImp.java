package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getListUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public User findUserByCar(String model, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User u " +
                "left outer join fetch u.car " +
                "where u.car.model= :model and u.car.series= :series")
                .setParameter("series", series)
                .setParameter("model", model);
        return (User) query.getSingleResult();
    }
}
