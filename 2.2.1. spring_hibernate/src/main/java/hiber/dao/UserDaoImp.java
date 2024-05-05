package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User findUser(Integer series, String model) {
      TypedQuery<User> query =sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u JOIN Car c ON u.car.id = c.id WHERE c.series = :series AND c.model = :model", User.class);
      query.setParameter("series", series);
      query.setParameter("model", model);
      return query.getSingleResult();
   }
   public void deleteAll() {
      Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM User");
      query.executeUpdate();
      query = sessionFactory.getCurrentSession().createQuery("DELETE FROM Car");
      query.executeUpdate();
   }
}
