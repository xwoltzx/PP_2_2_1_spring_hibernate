package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      //Для многоразового использования
      userService.deleteAll();
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car( 1,"Tesla Model X")));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car( 2,"Renault Logan")));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car( 3, "Audi R8")));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car( 4, "BMW X5")));
      List<User> users = userService.listUsers();
      for (User user1 : users) {
         System.out.println("Id = "+user1.getId());
         System.out.println("First Name = "+user1.getFirstName());
         System.out.println("Last Name = "+user1.getLastName());
         System.out.println("Email = "+user1.getEmail());
         System.out.println();
      }
      System.out.println(userService.findUser(3, "Audi R8"));
      System.out.println(userService.findUser(4, "BMW X5"));
      System.out.println(userService.findUser(2, "Renault Logan"));
      System.out.println(userService.findUser(1, "Tesla Model X"));
      context.close();
   }
}
