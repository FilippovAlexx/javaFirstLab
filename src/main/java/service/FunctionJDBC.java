package service;

import entity.Person;

import java.sql.*;

import static controller.ConnectionJDBC.getConnection;
import static Constants.ConstantsJDBC.*;

public class FunctionJDBC {
    public static ResultSet getUsers() throws Exception {
        // Устанавливаем соединение с базой данных
        Connection connection = null;
        try {
            connection = getConnection();
            // Создаем SQL-запрос для выборки информации о пользователях
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_DATA);
            return statement.executeQuery();
        } finally {
            // Закрываем соединение
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static boolean userExistsByLogin(String login) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PERSONS);

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean userExistsByID(int id) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUser(String login, String password) {
        Connection connection = null;
        try {connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_PERSON);
            statement.setInt(1, getLastId() + 1);//нужно переделать рандом!!!
            statement.setString(2, login);
            statement.setString(3, password);

            /*statement.setString(1, login);
            statement.setString(2, password);*/
            statement.executeUpdate();

    } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteUser(int id){
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_PERSON);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e + "Ошибка в deleteUser");
            //System.out.println("");
        }
    }

    public static void changeUser(int id, String login, String password){
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(CHANGE_PERSON);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setInt(3,id);
            statement.executeUpdate();

            /*if (rowsAffected > 0) {
                // Изменения внесены успешно
                response.getWriter().println("Changes applied successfully.");
            } else {
                // Ничего не было изменено
                response.getWriter().println("No changes applied.");
            }*/
        }catch (Exception e){
            throw new RuntimeException(e + "Ошибка в changeUSer");
        }
    }

    public static Person getUserData(int userId) throws Exception {
        String[] userData = new String[2]; // Массив для хранения данных пользователя

        Person person = new Person();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            // SQL-запрос для получения данных пользователя по его ID
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            // Получаем данные о пользователе из результата запроса
            if (resultSet.next()) {

                person.setId(userId);
                person.setLogin(resultSet.getString("login"));
                person.setPassword(resultSet.getString("password"));
                System.out.println(resultSet.getString("login") + resultSet.getString("password"));
            }
        } finally {
            // Закрываем ресурсы
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return person;
    }

    private static int getLastId(){
        Connection connection = null;
        int maxIndex = -1;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_MAX_ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                maxIndex = resultSet.getInt("max_id");
            }
        }catch (Exception e){
            throw new RuntimeException(e + "getLastID");
        }
        return maxIndex;
    }
}
