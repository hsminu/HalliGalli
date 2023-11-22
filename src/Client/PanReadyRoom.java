package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Common.*;

public class PanReadyRoom extends JPanel {
    private HGClientMain MainFrame;

    private JPanel Pan_ShowPlayer;
    private JLabel Label_Title;
    private JButton Btn_Refresh, Btn_GameStart;

    private List<JLabel> Lab_Players;

    public PanReadyRoom(HGClientMain parent) {
        MainFrame = parent;
        InitGui();
    }

    private void InitGui() {
        setLayout(null);
        setBounds(0, 0, 960, 640);

        Lab_Players = new ArrayList<>();

        Pan_ShowPlayer = new JPanel();
        Pan_ShowPlayer.setBounds(106, 172, 751, 360);
        add(Pan_ShowPlayer);
        Pan_ShowPlayer.setLayout(new GridLayout(0, 4, 10, 10));

        Label_Title = new JLabel();
        Label_Title.setHorizontalAlignment(SwingConstants.CENTER);
        Label_Title.setFont(new Font("맑은 고딕", Font.PLAIN, 56));
        Label_Title.setBounds(126, 72, 710, 58);
        add(Label_Title);

        Btn_Refresh = new JButton("새로고침");
        Btn_Refresh.setBounds(852, 39, 68, 58);
        add(Btn_Refresh);

        Btn_GameStart = new JButton("게임 시작");
        Btn_GameStart.setFont(new Font("맑은 고딕", Font.PLAIN, 34));
        Btn_GameStart.setBounds(341, 542, 289, 88);
        Btn_GameStart.setVisible(false);
        add(Btn_GameStart);
    }

    public void JoinRoom(Room room, boolean isHeadPlayer){
        Label_Title.setText("방: "+room.getRoomName());

        if(isHeadPlayer)
            Btn_GameStart.setVisible(true);

        Btn_Refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowPlayer(MainFrame.user.getRoomPlayers(room));
            }
        });
        ShowPlayer(MainFrame.user.getRoomPlayers(room));
    }

    private void ShowPlayer(List<Player> players){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (JLabel label : Lab_Players) {
                    Pan_ShowPlayer.remove(label);
                }
                Lab_Players.clear();

                for (Player player : players) {
                    System.out.println("showplayer:" + player.getName());
                    JLabel label = new JLabel(player.getName());
                    Lab_Players.add(label);

                    Pan_ShowPlayer.add(label);
                }

                Pan_ShowPlayer.revalidate();
                Pan_ShowPlayer.repaint();
            }
        });
    }

}
