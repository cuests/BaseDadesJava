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
        Scanner teclat = new Scanner(System.in);

        
        UserDAO.createTable();
        UserDAO.insertSampleUsers();
        boolean sortir = false;

        while (!sortir){
        System.out.println("-- USUARIS MANUALS --");
        System.out.println("0 = Veure usuaris --");
        System.out.println("1 = Afegir usuari --");
        System.out.println("2 = Modificar usuari --");
        System.out.println("3 = Eliminar usuari --");
        System.out.println("4 = ACABAR --");
        int funcio = teclat.nextInt();
        teclat.nextLine();
        switch (funcio){
            case 0:
                List<User> llistaUsuaris = UserDAO.getAllUsers();
                if (llistaUsuaris.isEmpty()) {
                    System.out.println("No s'ha trobat cap usuari.");
                } 
                else {
                    for (User u : llistaUsuaris) {
                        System.out.println(u); 
                    }
                }
                break;
            case 1: 
                System.out.println("Nom del usuari: ");
                String usuari = teclat.nextLine();
                System.out.println("Email del usuari: ");
                String email = teclat.nextLine();
                UserDAO.insertUser(usuari, email);
                break;
            case 2:
                System.out.println("Nom del usuari: ");
                String usuariModify = teclat.nextLine();
                System.out.println("Nova direcció de correu: ");
                String emailModify = teclat.nextLine();
                User usernou = new  User (usuariModify, emailModify);
                UserDAO.updateUser(usernou);
                break;
            case 3:
                System.out.println("Nom del usuari: ");
                String usuariDelete = teclat.nextLine();
                User userdelete = new  User (usuariDelete, "");
                UserDAO.deleteUser(userdelete);
                break;
            case 4:
                System.out.println("Aturant el servidor...");
                webServer.stop();
                sortir = true;
                break;
            }
        }
    }
}
