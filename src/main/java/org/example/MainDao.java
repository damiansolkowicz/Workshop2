package org.example;

public class MainDao {

    public static void main(String[] args) {
        UserDao userDao= new UserDao();
        System.out.println(userDao.read(7));

        User user=new User();
        user.setId(7);
        user.setEmail("jakismail@mail");
        user.setPassword("nowe");
        user.setUserName(("po update"));
        userDao.update(user);

        System.out.println(userDao.read(7));
    }
}
