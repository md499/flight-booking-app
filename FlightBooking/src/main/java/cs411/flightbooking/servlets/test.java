/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs411.flightbooking.servlets;

import cs411.flightbooking.dao.UserDao;
import cs411.flightbooking.models.User;

/**
 *
 * @author minhl
 */
public class test {

    public static void main(String[] args) {
        User user = new User("adam", "smith", "asmith@bu.edu", "12345");

//        try {
//            UserDao userdao = new UserDao(Secrets.mysql_url, Secrets.username, Secrets.password);
//            System.out.println("Create userdao success");
//            userdao.insert(user);
//            System.out.println("Register success");
//        } catch (Exception ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
        UserDao userdao = new UserDao();
        userdao.insert(user);
    }
}
