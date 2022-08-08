package com.example.demo;

import java.sql.*;
import java.util.Random;

public class ClientRepository {

//    public static void main(String[] args) {
//        getConnection();
//
//        Client employee = new Client();
//
//        employee.setName("igor");
//        employee.setCoffe("Americano");
//        employee.setPrice(100);
//
//        save(employee);
//    }

    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/prodvigator";
        String user = "postgres";
        String password = "0191";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    public static int getPriceForCoffe(String coffe) {
        int price = 0;
        if (coffe.equals("Americano")) {
            price += 150;
        } else if (coffe.equals("Latte")) {
            price += 200;
        } else if (coffe.equals("Espresso")) {
            price += 100;
        } else {
            price = 0;
        }
        return price;
    }

    public static int generateINQID() {
        Random random = new Random();
        int inqid = random.nextInt(12313);
        return inqid;
    }


    public static int save(Client client) {
        int status = 0;
        try {
            Connection connection = ClientRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into client(name,coffe,price,iniqid) values (?,?,?,?)");
            ps.setString(1, client.getName());
            ps.setString(2, client.getCoffe());
            ps.setInt(3, client.getPrice());
            ps.setInt(4, client.getIniqid());


            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }


    public static int delete(int iniqid) {

        int status = 0;

        try {
            Connection connection = ClientRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from client where iniqid=?");
            ps.setInt(1, iniqid);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Client getOrder(int iniqid) {
        Client client = new Client();

        try {
            Connection connection = ClientRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from client where iniqid =?");
            ps.setInt(1, iniqid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                client.setId(rs.getInt(1));
                client.setName(rs.getString(2));
                client.setCoffe(rs.getString(3));
                client.setPrice(rs.getInt(4));
                client.setIniqid(rs.getInt(5));
            }

            connection.close();


        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return client;
    }


    public static int update(Client client) {
        int status = 0;

        try {
            Connection connection = ClientRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update client set coffe=?,price=? where iniqid=?");
            ps.setString(1, client.getCoffe());
            ps.setInt(2,client.getPrice());
            ps.setInt(3, client.getIniqid());

            status = ps.executeUpdate();
            connection.close();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return status;
    }
}
