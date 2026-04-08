package baseDades;

import java.sql.SQLException;
import java.util.Scanner;

import org.h2.tools.Server;

import baseDades.dao.DataBase.DatabaseInit;

public class Main {
    public static void main(String[] args) throws SQLException {
        Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        System.out.println("El link es = " + webServer.getURL());
        

        DatabaseInit.init();

   
        System.out.println("Presiona ENTER para apagar el servidor y salir...");
        Scanner teclat = new Scanner(System.in);
        teclat.nextLine();
        teclat.close();
    }
}