package Client;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanRoom extends JPanel implements ActionListener{
    private HGClientMain MainFrame;

    private JLabel Label_Title;
    private JButton Btn_CreateRoom, Btn_EnterRoom, Btn_Back, Btn_Exit;

    public PanRoom(HGClientMain parent) {
        MainFrame = parent;
        InitGui();
    }

    private void InitGui(){
        setLayout(null);
        setBounds(0, 0, 960, 640);

        Label_Title = new JLabel("할리갈리 게임");
        Label_Title.setFont(new Font("맑은 고딕", Font.PLAIN, 56));
        Label_Title.setBounds(289, 86, 356, 58);
        add(Label_Title);

        Btn_CreateRoom = new JButton("방 만들기");
        Btn_CreateRoom.setFont(new Font("맑은 고딕", Font.PLAIN, 40));
        Btn_CreateRoom.setBounds(98, 268, 295, 116);
        Btn_CreateRoom.addActionListener(this);
        add(Btn_CreateRoom);

        Btn_EnterRoom = new JButton("방 들어가기");
        Btn_EnterRoom.setFont(new Font("맑은 고딕", Font.PLAIN, 40));
        Btn_EnterRoom.setBounds(554, 268, 295, 116);
        Btn_EnterRoom.addActionListener(this);
        add(Btn_EnterRoom);

        Btn_Back = new JButton("이전");
        Btn_Back.setFont(new Font("굴림", Font.PLAIN, 32));
        Btn_Back.setBounds(285, 453, 177, 71);
        Btn_Back.addActionListener(this);
        add(Btn_Back);

        Btn_Exit = new JButton("종료");
        Btn_Exit.setFont(new Font("굴림", Font.PLAIN, 32));
        Btn_Exit.setBounds(468, 453, 177, 71);
        Btn_Exit.addActionListener(this);
        add(Btn_Exit);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Btn_CreateRoom){
            MainFrame.display("PanCreateRoom");
        }
        if(e.getSource() == Btn_EnterRoom){
            MainFrame.display("PanJoinRoom");
        }
        if(e.getSource() == Btn_Back){
            MainFrame.display("PanRoom");
        }
        if(e.getSource() == Btn_Exit){
            System.exit(0);
        }
    }
}
