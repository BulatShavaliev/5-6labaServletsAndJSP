import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/InsertNewGame")
public class InsertNewGameServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();

        String game_name = request.getParameter("game_name");
        String release_date = request.getParameter("release_date");
        String autor = request.getParameter("autor");
        String game_engine = request.getParameter("game_engine");
        String price = request.getParameter("price");

        try {
            String url = "jdbc:mysql://localhost:3306/games?useSSL=false&useJDBCComplaintTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "1234";

            try (Connection connection = DriverManager.getConnection(url, username, password)){
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT MAX(idgame) FROM game");
                resultSet.next();
                int nextidgame = resultSet.getInt(1) + 1;

                statement.executeUpdate("INSERT INTO game (game_name, release_date, autor, game_engine, price) VALUES ('"+game_name+"', ' "+release_date+"', '"+autor+"', '"+game_engine+"', "+price+")");

                writer.println("<!DOCTYPE html>"+
                        "<html lang=\"ru\">"+
                        "<head>"+
                        "<meta charset=\"UTF-8\">"+
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
                        "<title>Ответ от сервера</title>"+
                        "</head>"+
                        "<body>"+
                        "<p class=\"lead\">"+
                        "Запись добавлена!"+
                        "</p>"+
                        "<a href=\"Main\">"+
                        "<button type = \"button\">Вернуться к списку</button>"+
                        "</a>"+
                        "</body>"+
                        "</html>");
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