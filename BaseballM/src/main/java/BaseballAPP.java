import db.DBConnection;

import model.service.*;
import java.sql.Connection;
import java.util.Scanner;

public class BaseballAPP {
    public static void main(String[] args) {

        Connection connection = DBConnection.getInstance();
        Scanner scanner = new Scanner(System.in);
        PrintOut printOut = new PrintOut();
        Service service = new Service(connection);
        printOut.mainPrint();
        String input = scanner.nextLine();
        service.run(input);


    }
}