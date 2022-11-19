package org.example;

public class MainDao {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User[] users = userDao.findAll();
        {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }
}





