package step03_pojo01_default;

import java.io.Serializable;

/**
 * 【注意】pojo要序列化
 */
public class User implements Serializable {
    private String userName;
    private int age;
    private Pet pet;

    public User() {
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", petName=" + this.pet.getPetName() +
                '}';
    }
}
