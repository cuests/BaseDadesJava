/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package baseDades;

import java.sql.SQLException;
import java.util.Scanner;

import org.h2.tools.Server;

import baseDades.dao.UserDAO;
/**
 *
 * @author @cuests
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Server webServer = Server.createWebServer ("-web", "-webAllowOthers", "-webPort", "8082").start();

        
        UserDAO.createTable();
        UserDAO.insterSampleUsers();

        Scanner teclat = new Scanner(System.in);
        System.out.println("Introdueix una tecla per acabar: ");
        String sortir = teclat.nextLine();
    }
}
