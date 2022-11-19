package org.example;

public class MainDao {

    public static void main(String[] args) {
        UserDao userDao= new UserDao();
        System.out.println(userDao.read(7));
    }
}
