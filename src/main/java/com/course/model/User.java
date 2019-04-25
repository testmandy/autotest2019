package com.course.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String sex;
    private String age;

    @Override
    public String toString(){
        return ("{id" + id + "," +
                "name" + name + "," +
                "sex" + sex + "," +
                "age" + age + ",}");

    }

}
