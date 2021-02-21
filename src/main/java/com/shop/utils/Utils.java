package com.shop.utils;

import com.shop.content.Config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    public void loadConfig(){
        try(FileReader fileReader = new FileReader("E:\\SonClass\\class\\shop-java-08\\" + "src\\main\\resources\\config.properties")) {
            Properties properties = new Properties();
            properties.load(fileReader);
            Config.url = properties.getProperty("url");
            Config.user = properties.getProperty("user");
            Config.password = properties.getProperty("password");
            Config.driverClassName = properties.getProperty("driverClassName");
            Config.maxPoolSize = Integer.parseInt(properties.getProperty("maxPoolSize"));
            Config.connectionTimeOut = Integer.parseInt(properties.getProperty("connectionTimeOut"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
