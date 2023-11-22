package Client;

import Common.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanCreateRoom extends JPanel {
    private HGClientMain MainFrame;

    private JLabel Label_Title, Label_InputRoomName, Label_InputPassword, Label_InputPeople;
    private JTextField Field_InputRoomName, Field_InputPassword, Field_InputPeople;
    private JButton Btn_CreateRoom, Btn_Back;

    public PanCreateRoom(HGClientMain parent){
        MainFrame = parent;
        InitGui();
    }

    private void InitGui() {
        setLayout(null);
        setBounds(0, 0, 960, 640);

        Label_Title = new JLabel("방 만들기");
        Label_Title.setFont(new Font("맑은 고딕", Font.PLAIN, 56));
        Label_Title.setBounds(345, 89, 244, 58);
        add(Label_Title);

        Label_InputRoomName = new JLabel("방 이름");
        Label_InputRoomName.setFont(new Font("한컴 고딕", Font.PLAIN, 28));
        Label_InputRoomName.setBounds(154, 224, 89, 58);
        add(Label_InputRoomName);

        Field_InputRoomName = new JTextField();
        Field_InputRoomName.setFont(new Font("한컴 고딕", Font.PLAIN, 20));
        Field_InputRoomName.setColumns(10);
        Field_InputRoomName.setBounds(255, 224, 468, 58);
        add(Field_InputRoomName);

        JLabel Label_InputPassword = new JLabel("비번");
        Label_InputPassword.setToolTipText("");
        Label_InputPassword.setFont(new Font("한컴 고딕", Font.PLAIN, 28));
        Label_InputPassword.setBounds(154, 333, 89, 58);
        add(Label_InputPassword);

        Field_InputPassword = new JTextField();
        Field_InputPassword.setColumns(10);
        Field_InputPassword.setBounds(255, 333, 468, 58);
        add(Field_InputPassword);

        Label_InputPeople = new JLabel("인원");
        Label_InputPeople.setFont(new Font("한컴 고딕", Font.PLAIN, 28));
        Label_InputPeople.setBounds(154, 431, 89, 58);
        add(Label_InputPeople);

        Field_InputPeople = new JTextField();
        Field_InputPeople.setColumns(10);
        Field_InputPeople.setBounds(255, 431, 468, 58);
        add(Field_InputPeople);

        Btn_CreateRoom = new JButton("생성");
        Btn_CreateRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Room room = new Room(Field_InputRoomName.getText(), Field_InputPassword.getText(), Integer.parseInt(Field_InputPeople.getText()));
                MainFrame.user.CreateRoom(room);
                MainFrame.display("PanReadyRoom", room, true);
            }
        });
        Btn_CreateRoom.setFont(new Font("굴림", Font.PLAIN, 32));
        Btn_CreateRoom.setBounds(255, 534, 177, 71);
        add(Btn_CreateRoom);

        Btn_Back = new JButton("뒤로");
        Btn_Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        Btn_Back.setFont(new Font("굴림", Font.PLAIN, 32));
        Btn_Back.setBounds(546, 534, 177, 71);
        add(Btn_Back);
    }
}