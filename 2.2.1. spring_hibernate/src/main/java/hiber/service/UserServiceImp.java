package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

   private final UserDao userDao;

   public UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
   }


   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getListUsers() {
      return userDao.getListUsers();
   }

   @Override
   public User findUserByCar(String model, int series) {
      return userDao.findUserByCar(model, series);
   }
}
