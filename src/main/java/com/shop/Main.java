//package com.shop;
//
//import com.shop.connection.MysqlConnection;
//import com.shop.content.Config;
//
//import java.io.FileReader;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class Main {
//    public static void main(String[] args) {
//        Connection connection = MysqlConnection.connection();
//        try{
//            FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties");
//            Properties properties = new Properties();
//            properties.load(fileReader);
//            Config.url = properties.getProperty("url");
//            Config.user = properties.getProperty("user");
//            Config.password = properties.getProperty("password");
//            Config.driverClassName = properties.getProperty("driverClassName");
//            Config.maxPoolSize = Integer.parseInt(properties.getProperty("maxPoolSize"));
//            Config.connectionTimeOut = Integer.parseInt(properties.getProperty("connectionTimeOut"));
//
//            connection.setAutoCommit(false);
//            String sql = "SELECT * FROM NhanVien";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                System.out.println(rs.getString("MaNV"));
//                System.out.println(rs.getString("Ten"));
//                System.out.println(rs.getString("Luong"));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            if(connection != null){
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//}
