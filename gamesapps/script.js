function deleteRow() {
    var endgameToDelete = prompt("Введите Id игры, которую нужно удалить.");
    // строка с параметрами для отправки
    var parameters = "endgame=" + endgameToDelete;

    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8081/gamesapps/DeleteGame");
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    request.send(parameters);
    window.location.reload(false);
}