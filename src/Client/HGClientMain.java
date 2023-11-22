package Client;

import Common.Room;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HGClientMain extends JFrame {
    private PanLogin panLogin;
    private PanRoom panRoom;
    private PanCreateRoom panCreateRoom;
    private PanJoinRoom panJoinRoom;
    private PanReadyRoom panReadyRoom;

    public User user;

    public HGClientMain() {
        initGui();
        setVisible(true);
    }

    private void initGui() {
        setLayout(null);
        setTitle("HGGAME");
        setBounds(100, 100, 960, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        panLogin = new PanLogin(this);
        add(panLogin);

        panRoom = new PanRoom(this);
        add(panRoom);

        panCreateRoom = new PanCreateRoom(this);
        add(panCreateRoom);

        panJoinRoom = new PanJoinRoom(this);
        add(panJoinRoom);

        panReadyRoom = new PanReadyRoom(this);
        add(panReadyRoom);

        ResetPannel();
        panLogin.setVisible(true);
    }

    public void display (String viewName){
        if(viewName == "PanLogin"){
            ResetPannel();
            panLogin.setVisible(true);
        }
        if(viewName == "PanRoom"){
            ResetPannel();
            panRoom.setVisible(true);
        }
        if(viewName == "PanCreateRoom"){
            ResetPannel();
            panCreateRoom.setVisible(true);
        }
        if(viewName == "PanJoinRoom"){
            ResetPannel();
            panJoinRoom.ShowRoomList(user.getRooms());
            panJoinRoom.setVisible(true);
        }
    }

    public void display(String viewName, Room room, boolean isHeadPlayer){
        if(viewName == "PanReadyRoom"){
            ResetPannel();
            panReadyRoom.JoinRoom(room, isHeadPlayer);
            panReadyRoom.setVisible(true);
        }
    }

    private void ResetPannel(){
        panLogin.setVisible(false);
        panRoom.setVisible(false);
        panCreateRoom.setVisible(false);
        panJoinRoom.setVisible(false);
        panReadyRoom.setVisible(false);
    }

    public static void main(String[] args) {
        new HGClientMain();
    }
}
