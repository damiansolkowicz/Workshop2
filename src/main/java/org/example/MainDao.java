package org.example;

public class MainDao {

    public static void main(String[] args) {
        UserDao userDao= new UserDao();
        User user1=new User();
        user1.setUserName("Jan");
        user1.setEmail("JanKowalski@onet.pl");
        user1.setPassword("Jankowlaksi");
        userDao.create(user1);
    }
}
