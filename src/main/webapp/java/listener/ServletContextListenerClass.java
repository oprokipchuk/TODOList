package listener;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;

public class ServletContextListenerClass implements ServletContextListener {

    private static String login;
    private static String password;
    private static String url = "jdbc:mysql://localhost:3306/dietsite?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public void contextInitialized(ServletContextEvent event) {

        ServletContext context = event.getServletContext();

        login = context.getInitParameter("dbLogin");
        password = context.getInitParameter("dbPassword");

        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(url, login, password);
            if (!connection.isClosed()) {
                System.out.println("Connected");
            }

            statement = connection.createStatement();
            //System.out.println(statement.executeUpdate("INSERT INTO users (login, email, password) VALUES ('oleg2', 'oleg2@mail.ru', '12345')"));

        } catch (SQLException exc) {
            exc.printStackTrace();
        }

        context.setAttribute("dataBaseStatement", statement);
        context.setAttribute("dataBaseConnection", connection);

    }

    public void contextDestroyed(ServletContextEvent event) {
        try {
            statement.close();
            connection.close();
            if (connection.isClosed()) {
                System.out.println("Closed");
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
}