package com.hongrider.repository;

import com.hongrider.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryH2Test {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void save() {
        User user = new User();
        user.setName("shiny");
        user.setAlias("璇妮");
        user.setRole("物业");
        user.setEstate("珠江新城");
        user.setEmail("shiny@qq.com");
        user.setPassword("111111");

        User savedUser = userRepository.save(user);

        assertThat(savedUser).hasFieldOrPropertyWithValue("name", "shiny");
        assertThat(savedUser).hasFieldOrPropertyWithValue("estate", "珠江新城");
        assertThat(savedUser).hasFieldOrPropertyWithValue("email", "shiny@qq.com");

    }

    @Test
    @Transactional
    void findAll(){
        User user1 = new User();
        user1.setName("shiny");
        user1.setAlias("璇妮");
        user1.setRole("物业");
        user1.setEstate("珠江新城");
        user1.setEmail("shiny@qq.com");
        user1.setPassword("111111");

        User user2 = new User();
        user2.setName("shiny2");
        user2.setAlias("璇妮2");
        user2.setRole("物业2");
        user2.setEstate("珠江新城2");
        user2.setEmail("shiny2@qq.com");
        user2.setPassword("111111");

        User user3 = new User();
        user3.setName("shiny3");
        user3.setAlias("璇妮3");
        user3.setRole("物业3");
        user3.setEstate("珠江新城3");
        user3.setEmail("shiny3@qq.com");
        user3.setPassword("111111");

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);

        Iterable<User> users = userRepository.findAll();

        assertThat(users).hasSize(3).contains(user1, user2, user3);
    }

    @Test
    @Transactional
    void  delete(){
        User user1 = new User();
        user1.setName("shiny");
        user1.setAlias("璇妮");
        user1.setRole("物业");
        user1.setEstate("珠江新城");
        user1.setEmail("shiny@qq.com");
        user1.setPassword("111111");

        User user2 = new User();
        user2.setName("shiny2");
        user2.setAlias("璇妮2");
        user2.setRole("物业2");
        user2.setEstate("珠江新城2");
        user2.setEmail("shiny2@qq.com");
        user2.setPassword("111111");

        User user3 = new User();
        user3.setName("shiny3");
        user3.setAlias("璇妮3");
        user3.setRole("物业3");
        user3.setEstate("珠江新城3");
        user3.setEmail("shiny3@qq.com");
        user3.setPassword("111111");

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);

        userRepository.delete( user2);

        Iterable<User> users = userRepository.findAll();

        assertThat( users).hasSize(2).contains(user3, user1);

    }

    @Test
    @Transactional
    void update() {

        User user1 = new User();
        user1.setName("yuki");
        user1.setAlias("yuki");
        user1.setRole("物业");
        user1.setEstate("汉江花园");

        user1.setEmail("yuki@gmail.com");
        user1.setPassword("111111");

        entityManager.persist(user1);

        List<User> users = userRepository.findByName("yuki");
        User user = users.get(0);
        user.setEstate("南国花园");
        user.setRole("租客");
        User updatedUser = userRepository.save(user);
        assertThat(updatedUser).hasFieldOrPropertyWithValue("estate", "南国花园");
        assertThat(updatedUser).hasFieldOrPropertyWithValue("role", "租客");

    }
}
