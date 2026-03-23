/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package baseDades;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.h2.tools.Server;

import baseDades.dao.UserDAO;
import baseDades.model.User;
/**
 *
 * @author @cuests
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Server webServer = Server.createWebServer ("-web", "-webAllowOthers", "-webPort", "8082").start();
        System.out.println("El link es = "+ webServer.getURL());

        
        UserDAO.createTable();
        UserDAO.insertSampleUsers();
        UserDAO.createTable();
        System.out.println("Obteniendo usuarios de la base de datos...");

        // LLISTA D'USUARIS IMPLEMENTACIÓ
        List<User> llistaUsuaris = UserDAO.getAllUsers();
        if (llistaUsuaris.isEmpty()) {
            System.out.println("No se ha encontrado ningún usuario.");
        } else {
            System.out.println("--- Lista de Usuarios ---");
            for (User u : llistaUsuaris) {
                System.out.println(u); 
            }
        }


        Scanner teclat = new Scanner(System.in);
        System.out.println("Introdueix una tecla per acabar: ");
        String sortir = teclat.nextLine();

        System.out.println("Aturant el servidor...");
        webServer.stop();
    }
}
