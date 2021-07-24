package com.evoting;

import com.evoting.user.User;
import com.evoting.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static final String DELAVEGA = "DELAVEGA";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel")
                .forEach(name -> {
                    User user = new User(name, DELAVEGA, name.toLowerCase() + "@fulldemo.com");
                    userRepository.save(user);
                });
    }
}