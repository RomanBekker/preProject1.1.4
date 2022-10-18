package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        //Создание таблицы Userов
        userServiceImpl.createUsersTable();

        //Добавление 4 Userов в таблицу
        userServiceImpl.saveUser("Роман", "Беккер", (byte) 25);
        System.out.println("User с именем – Роман добавлен в базу данных");
        userServiceImpl.saveUser("Борис", "Григорян", (byte) 24);
        System.out.println("User с именем – Борис добавлен в базу данных");
        userServiceImpl.saveUser("Алексей", "Кленов", (byte) 23);
        System.out.println("User с именем – Алексей добавлен в базу данных");
        userServiceImpl.saveUser("Александр", "Вертянкин", (byte) 26);
        System.out.println("User с именем – Александр добавлен в базу данных");

        //Получение всех User из базы и вывод в консоль
        System.out.println(userServiceImpl.getAllUsers());

        //Очистка таблицы Userов
        userServiceImpl.cleanUsersTable();

        //Удаление таблицы
        userServiceImpl.dropUsersTable();
    }
}
