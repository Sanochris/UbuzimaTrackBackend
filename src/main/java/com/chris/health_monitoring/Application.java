
package com.chris.health_monitoring;

import com.chris.health_monitoring.model.User;
import com.chris.health_monitoring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {
   public Application() {
   }

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }

   @Bean
   CommandLineRunner initUsers(UserRepository repo, PasswordEncoder encoder) {
      return (args) -> {
         if (!repo.existsByUsername("admin")) {
            User admin = new User("admin", encoder.encode("1234"), "ADMIN", "chrisarmel4@gmail.com", false);
            repo.save(admin);
            System.out.println("✅ Admin user created successfully");
         } else {
            System.out.println("ℹ️ Admin user already exists");
         }

      };
   }
}
