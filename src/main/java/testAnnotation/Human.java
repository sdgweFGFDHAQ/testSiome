package testAnnotation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class Human implements Serializable {
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
