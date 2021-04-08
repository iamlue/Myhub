package com.lue.springboottest.controller.bean;

/**
 * @author lue
 * @date 2021/4/6 --22:45
 */
public class Dog {
    private String name;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
