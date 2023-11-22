package Client;

import Common.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanJoinRoom extends JPanel {
    private HGClientMain MainFrame;

    private JPanel Pan_SelRoom;
    private ArrayList<Btn_Room> Btn_Rooms;

    public PanJoinRoom(HGClientMain parent){
        MainFrame = parent;
        InitGui();
    }

    private void InitGui(){
        setLayout(null);
        setBounds(0, 0, 960, 640);

        Pan_SelRoom = new JPanel();
        Pan_SelRoom.setLayout(new FlowLayout(FlowLayout.LEFT));
        Pan_SelRoom.setBounds(125, 200, 712, 364);
        add(Pan_SelRoom);

        JLabel Label_Title = new JLabel("방 들어가기");
        Label_Title.setFont(new Font("맑은 고딕", Font.PLAIN, 56));
        Label_Title.setBounds(350, 67, 300, 58);
        add(Label_Title);
    }

    public void ShowRoomList(List<Room> roomList){
        Btn_Rooms = new ArrayList<>();
        for(Room room: roomList){
            Btn_Rooms.add(new Btn_Room(room));
        }
        for(Btn_Room btn: Btn_Rooms){
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        MainFrame.user.JoinRoom(btn.getRoom());
                        MainFrame.display("PanReadyRoom", btn.room, false);
                    }catch (IOException ioException){
                        ioException.printStackTrace();
                    }
                }
            });
            Pan_SelRoom.add(btn);
        }
    }

    private class Btn_Room extends JButton{
        private Room room;

        public Btn_Room(Room room){
            this.room = room;
            super.setText(room.getRoomName());
        }

        public Room getRoom() {
            return room;
        }
    }
}
