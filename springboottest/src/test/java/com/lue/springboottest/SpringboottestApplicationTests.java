package com.lue.springboottest;

import com.lue.springboottest.controller.bean.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringboottestApplicationTests {
    @Autowired
    Person person;
    @Test
    void test() {
        System.out.println(person);
    }

}
