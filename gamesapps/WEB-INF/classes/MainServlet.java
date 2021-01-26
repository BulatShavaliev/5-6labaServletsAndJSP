import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import JavaClasses.GameBean;

@WebServlet("/Main")
public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        ArrayList<GameBean> games;

        try {
            String url = "jdbc:mysql://localhost:3306/games?useSSL=false&useJDBCComplaintTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "1234";

            try (Connection connection = DriverManager.getConnection(url, username, password)){
                // Для Резалт Сетов этого Statement'а задал свойство для скролла указателя вниз-вверх. По умолчанию курсор двигается только вниз.
                // Каждый стейтмент может держать открытым только один ResultSet
                Statement statementForgamesSet = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet gamesResultSet = statementForgamesSet.executeQuery("SELECT * FROM game");

                // создаётся ArrayList размером с количество записей. Чтобы получить число записей используются манипуляции с курсором резалт сета
                gamesResultSet.last();
                int gamesNumber = gamesResultSet.getRow();
                games = new ArrayList<GameBean>(gamesNumber);
                gamesResultSet.beforeFirst(); // курсор обратно перед первой строкой

                // Заполнение ArrayList'а Bean'ами GameBean

                while (gamesResultSet.next()) {

                    // добавление заполненной записи в ArrayList
                    games.add(
                            new GameBean(gamesResultSet.getString("idgame"), gamesResultSet.getString("game_name"),
                                    gamesResultSet.getString("release_date"), gamesResultSet.getString("autor"),
                                    gamesResultSet.getString("game_engine"), gamesResultSet.getString("price")));
                }
            }

            request.setAttribute("games", games);
            ServletContext servletContext = getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }
        }
        catch(Exception e){
            writer.println("<p>Database connection failed...</p>");
            writer.println("<p>"+e.getMessage()+"</p>");
            writer.println("<a href=\"Main\"><button>Вернуться к списку</button></a>");
            e.printStackTrace();
        }
        finally {
            writer.close();
        }
    }
}