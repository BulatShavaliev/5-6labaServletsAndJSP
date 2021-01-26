package JavaClasses;

import java.io.Serializable;

public class GameBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idgame;
    private String game_name;
    private String release_date;
    private String autor;
    private String game_engine;
    private String price;

    public GameBean(){ }

    public GameBean(String idgame, String game_name, String release_date, String autor, String game_engine, String price) {

        this.idgame = idgame;
        this.game_name = game_name;
        this.release_date = release_date;
        this.autor = autor;
        this.game_engine = game_engine;
        this.price = price;
    }

    public String getidgame() {
        return idgame;
    }

    public String getgame_name() {
        return game_name;
    }

    public String getrelease_date() {
        return release_date;
    }

    public String getautor() {
        return autor;
    }

    public String getgame_engine() {
        return game_engine;
    }

    public String getprice() {
        return price;
    }
}