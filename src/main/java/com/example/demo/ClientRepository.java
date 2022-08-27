package com.example.demo;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.xml.Log4jEntityResolver;
import org.slf4j.*;
import org.slf4j.spi.LoggingEventBuilder;

import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Random;

public class ClientRepository {

//    public static void main(String[] args) {
//        getConnection();
//        System.out.println(checkOrder(11227));
//    }
    private static final Logger log = LoggerFactory.getLogger(ClientRepository.class);
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

    @SneakyThrows
    public static int save(Client client) {
        int status = 0;
        Connection connection = ClientRepository.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into client(name,coffe,price,iniqid) values (?,?,?,?)");
        ps.setString(1, client.getName());
        ps.setString(2, client.getCoffe());
        ps.setInt(3, client.getPrice());
        ps.setInt(4, client.getIniqid());

        status = ps.executeUpdate();
        connection.close();

        log.info("Works method save and status: "+ status );

        return status;
    }

    @SneakyThrows
    public static int delete(int iniqid) {
        int status = 0;
        Connection connection = ClientRepository.getConnection();
        log.info("connection " + connection);
    PreparedStatement ps = connection.prepareStatement("UPDATE client SET isDeleted = ? WHERE iniqid = ?");
        ps.setBoolean(1, true);
        ps.setInt(2, iniqid);
        status = ps.executeUpdate();

        connection.close();
        log.info("Works method delete order and status: "+ status);
        return status;
    }

    @SneakyThrows
    public static Client getOrder(int iniqid) {
        Client client = new Client();
        Connection connection = ClientRepository.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from client where iniqid =? and isdeleted = false ");
        ps.setInt(1, iniqid);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            client.setId(rs.getInt(1));
            client.setName(rs.getString(2));
            client.setCoffe(rs.getString(3));
            client.setPrice(rs.getInt(4));
            client.setIniqid(rs.getInt(5));
        }else{
            System.out.println("Sorry your account is deleted");
        }

        log.info("Works method get order" + client);
        connection.close();

        return client;
    }

    @SneakyThrows
    public static int checkOrder(int iniqid){
        int status;
        Client client = new Client();
        Connection connection = ClientRepository.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from client where iniqid =? and isdeleted = false");
        ps.setInt(1,iniqid);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            client.setId(rs.getInt(1));
            client.setName(rs.getString(2));
            client.setCoffe(rs.getString(3));
            client.setPrice(rs.getInt(4));
            client.setIniqid(rs.getInt(5));
            status = 0;
        }else{
            status = 1;
        }
        return status;
    }


    @SneakyThrows
    public static int update(Client client) {
        int status = 0;


        Connection connection = ClientRepository.getConnection();
        PreparedStatement ps = connection.prepareStatement("update client set coffe=?,price=? where iniqid=?");
        ps.setString(1, client.getCoffe());
        ps.setInt(2, client.getPrice());
        ps.setInt(3, client.getIniqid());

        status = ps.executeUpdate();
        connection.close();
        log.info("Works method update and status: " + status);
        return status;
    }
}
