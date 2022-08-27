package com.hongrider.repository;

import com.hongrider.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
//@Sql("classpath:sql/test-data.sql")
class UserRepositoryNotSameDataBaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAll() {
//        System.out.println(userRepository.findAll());
        User first_user = new User(1, "harry", "小名",
                "harry@gamil.com", "1111111",
                "业主", "富力半岛");
        Iterable<User> users = userRepository.findAll();
        assertThat(users).hasSize(19).first().isEqualTo(first_user);
    }

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

//        System.out.println("测试保存：" + savedUser);
    }

    @Test
    void findById() {
        //System.out.println( userRepository.findById( 9) );

        User first_user = new User(1, "harry", "小名",
                "harry@gamil.com", "1111111",
                "业主", "富力半岛");

        User user = userRepository.findById(1).get();
        assertThat(user).isEqualTo(first_user);
    }

    @Test
    @Transactional
    void update() {
        User user = userRepository.findById(5).get();
        user.setEstate("南国花园");
        user.setRole("租客");
        User updatedUser = userRepository.save(user);
        assertThat(updatedUser).hasFieldOrPropertyWithValue("estate", "南国花园");
        assertThat(updatedUser).hasFieldOrPropertyWithValue("role", "租客");
//        System.out.println(user);
    }

    @Test
    @Transactional
    void delete() {
        List<User> users = userRepository.findAll();
        Integer befordel = users.size();
        userRepository.deleteById(4);
        users = userRepository.findAll();
        Integer afterdel = befordel - users.size();

        assertThat(afterdel).isEqualTo(1);
    }

}