package Server;

import Common.Player;

import java.util.ArrayList;
import java.util.List;

public class GameHandler implements Runnable{
    private ClientHandler clientHandler;
    private GameRoom gameRoom;
    private List<Player> gamePlayer;

    public GameHandler(ClientHandler clientHandler, GameRoom gameRoom){
        this.clientHandler = clientHandler;
        this.gameRoom = gameRoom;

        gamePlayer = new ArrayList<>();
        for(Player player : gameRoom.getPlayers()){
            gamePlayer.add(player);
        }
    }

    @Override
    public void run() {

    }
}
