package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainDao {

    public static void main(String[] args) {

    /*  User user1=new User();
      UserDao userDao=new UserDao();
      user1.setPassword("khf");
      user1.setUserName("kjf");
      user1.setEmail("jnkj@kjd");
      userDao.create(user1);
*/

        User user = new User();
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Co chcesz zrobić?? a - dodaj rekord  r- kasuj rekord s- wyświetla pojedynczy rekord, p- drukuj wszystkie rekordy u-aktualizuj wszystkie rekordy");
            String command = scanner.nextLine();
            if ("p".equals(command)) {
                User[] users = userDao.findAll();
                {
                    for (User u : users) {
                        System.out.println(u);
                    }
                }
            }
            if ("a".equals(command)) {
                System.out.println("Podaj nazwe użytkownika");
                user.setUserName(scanner.nextLine());
                System.out.println("podaj Mail");
                user.setEmail(scanner.nextLine());
                System.out.println("podaj hasło");
                user.setPassword(scanner.nextLine());
                userDao.create(user);
            }
            if ("r".equals(command)) {
                System.out.println("Podaj index");
                int id = scanner.nextInt();
                userDao.delete(id);
            }
            if ("u".equals(command)) {
                System.out.println("Podaj id użytkownika ");
                user.setId(Integer.parseInt(scanner.nextLine()));
                System.out.println("Podaj nową nazwe użytkownika");
                user.setUserName(scanner.nextLine());
                System.out.println("podaj nowy Mail");
                user.setEmail(scanner.nextLine());
                System.out.println("podaj nowe  hasło");
                user.setPassword(scanner.nextLine());
                userDao.update(user);

            }
            if ("s".equals(command)) {
                System.out.println("Który rekord wyświetlić");
                int id= scanner.nextInt();
                System.out.println(userDao.read(id));
            }
        }
    }
}













