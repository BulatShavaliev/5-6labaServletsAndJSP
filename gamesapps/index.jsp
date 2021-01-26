<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="JavaClasses.*" %>
<%@ page import ="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>GamesTableHtml</title>
    <script src="script.js"></script>
</head>

<body>
    <section>
        <div>
            <div>
                <div>
                    <form action="InsertNewGame" method="POST">
                        <fieldset>
                            <p> Название</p>

                            <input type="text" class="form-control" name="game_name" id="game_name">

                            <p> Дата выхода </p>

                            <input type="text" class="form-control raz" name="release_date" id="release_date">

                            <p> Издатель </p>

                            <input type="text" class="form-control raz" name="autor" id="autor">

                            <p> Игровой движок </p>

                            <input type="text" class="form-control raz" name="game_engine" id="game_engine">

                            <p> Цена </p>

                            <input type="number" class="form-control raz" name="price" id="price">
    
                            <br>
                            <button type="submit" id="create">Add</button>
                        </fieldset>
                    </form>
                    <button type="button" id="delete" onclick="deleteRow()"> Delete</button>
                </div>
            </div>
        </div>
    </section>
    <br>
    <div></div>
    <br>
    <section>
        <div>
            <div>
                <div>
                    <br>
                    <table id="games">
                        <thead>
                            <tr>
                                <th>Id игры</th>
                                <th>Название</th>
                                <th>Дата выхода</th>
                                <th>Издатель</th>
                                <th>Игровой движок</th>
                                <th>Цена</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <% if (request.getAttribute("games") != null) {

                                ArrayList<GameBean> list = (ArrayList<GameBean>) request.getAttribute("games");
                                for (GameBean element : list) {
                                    out.println("<tr>");
                                    out.println("<td>" + element.getidgame() + "</td>");
                                    out.println("<td>" + element.getgame_name() + "</td>");
                                    out.println("<td>" + element.getrelease_date() + "</td>");
                                    out.println("<td>" + element.getautor() + "</td>");
                                    out.println("<td>" + element.getgame_engine() + "</td>");
                                    out.println("<td>" + element.getprice() + "</td>");
                                    out.println("</tr>");
                                }
                              }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</body>
</html>