package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class PanLogin extends JPanel {
    private HGClientMain MainFrame;

    private JLabel Label_Title, Label_InputName, Label_InputServerIp, Label_InputPort;
    private JTextField Field_InputName, Field_InputServerIp, Field_InoutPort;

    public PanLogin(HGClientMain parent){
        MainFrame = parent;
        InitGui();
    }

    private void InitGui(){
        setLayout(null);
        setBounds(0, 0, 960, 640);


        JLabel Label_Title = new JLabel("할리갈리 게임");
        Label_Title.setFont(new Font("맑은 고딕", Font.PLAIN, 56));
        Label_Title.setBounds(289, 86, 356, 58);
        add(Label_Title);

        Label_InputName = new JLabel("닉네임");
        Label_InputName.setFont(new Font("한컴 고딕", Font.PLAIN, 28));
        Label_InputName.setBounds(138, 231, 89, 58);
        add(Label_InputName);

        Field_InputName = new JTextField();
        Field_InputName.setBounds(239, 231, 468, 58);
        Field_InputName.setFont(new Font("한컴 고딕", Font.PLAIN, 20));
        add(Field_InputName);
        Field_InputName.setColumns(10);

        Label_InputServerIp = new JLabel("iP");
        Label_InputServerIp.setFont(new Font("한컴 고딕", Font.PLAIN, 28));
        Label_InputServerIp.setBounds(138, 340, 89, 58);
        add(Label_InputServerIp);

        Field_InputServerIp = new JTextField();
        Field_InputServerIp.setColumns(10);
        Field_InputServerIp.setFont(new Font("한컴 고딕", Font.PLAIN, 20));
        Field_InputServerIp.setBounds(239, 340, 468, 58);
        add(Field_InputServerIp);

        Label_InputPort = new JLabel("port");
        Label_InputPort.setFont(new Font("한컴 고딕", Font.PLAIN, 28));
        Label_InputPort.setBounds(138, 438, 89, 58);
        add(Label_InputPort);

        Field_InoutPort = new JTextField();
        Field_InoutPort.setColumns(10);
        Field_InoutPort.setFont(new Font("한컴 고딕", Font.PLAIN, 20));
        Field_InoutPort.setBounds(239, 438, 468, 58);
        add(Field_InoutPort);

        JButton Btn_Login = new JButton("접속");
        Btn_Login.setFont(new Font("굴림", Font.PLAIN, 32));
        Btn_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckInput()){
                    MainFrame.user = new User(Field_InputName.getText(), Field_InputServerIp.getText(), Integer.parseInt(Field_InoutPort.getText()), MainFrame);
                    try{
                        MainFrame.user.Connect();
                        MainFrame.display("PanRoom");
                    }catch (IOException ioException){
                        JOptionPane.showMessageDialog(MainFrame, "연결 실패", "경고", JOptionPane.WARNING_MESSAGE);
                        ioException.printStackTrace();
                    }
                }
            }
        });
        Btn_Login.setBounds(239, 540, 177, 71);
        add(Btn_Login);

        JButton Btn_Exit = new JButton("종료");
        Btn_Exit.setFont(new Font("굴림", Font.PLAIN, 32));
        Btn_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Btn_Exit.setBounds(530, 540, 177, 71);
        add(Btn_Exit);

    }

    private boolean CheckInput(){
        if (Field_InputName.getText().length() <= 0) {
            JOptionPane.showMessageDialog(MainFrame, "닉네임을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String ipRegex = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";
        if(Field_InputServerIp.getText().matches(ipRegex) != true){
            JOptionPane.showMessageDialog(MainFrame, "올바른 ip형식을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String portRegex = "\\b(?:[0-9]{1,5})\\b";
        if(Field_InoutPort.getText().matches(portRegex) != true){
            JOptionPane.showMessageDialog(MainFrame, "올바른 port형식을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

}
