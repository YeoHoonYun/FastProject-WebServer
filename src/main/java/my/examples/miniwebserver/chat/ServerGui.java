package my.examples.miniwebserver.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGui extends JFrame implements ActionListener {
    // 지정된 갯수의 행관 열을 가지는 새로운 TextArea, TextField 생성)
    private JTextArea jta = new JTextArea(40,25);
    private JTextField jtf = new JTextField(25);

    private ServerBackground server = new ServerBackground();

    public ServerGui(){
        add(jta, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        jtf.addActionListener(this);

        // 윈도우의 X를 누르면 종료
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //프레임 보이기
        setVisible(true);
        //위치와 크기 지정
        setBounds(200,100,400,600);
        setTitle("서버부분");

        server.setGui(this);
        server.setting();
    }

    public void actionPerformed(ActionEvent e) {
        String msg = jtf.getText() + "\n";
        jta.append(msg);
        System.out.println(msg);
        server.sendMessage(msg);
        jtf.setText("");
    }

    public void appendMsg(String msg) {
        jta.append(msg);
        System.out.println("날라온 메시지" + msg);
    }

    public static void main(String[] args) {
        new ServerGui();
    }
}
