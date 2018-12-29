package my.examples.miniwebserver.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.sun.javafx.fxml.expression.Expression.add;

public class ClientGui extends JFrame implements ActionListener {
    // 지정된 갯수의 행관 열을 가지는 새로운 TextArea, TextField 생성)
    private JTextArea jta = new JTextArea(40,25);
    private JTextField jtf = new JTextField(25);
    private ClientBackground client = new ClientBackground();

    public ClientGui(){
        add(jta, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        jtf.addActionListener(this);

        // 윈도우의 X를 누르면 종료
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //프레임 보이기
        setVisible(true);
        //위치와 크기 지정
        setBounds(200,100,400,600);
        setTitle("클라이언트 부분");

        client.setGui(this);
        client.connet();

    }

    public void actionPerformed(ActionEvent e) {
        String msg = jtf.getText() + "\n";
        jta.append(msg);
        System.out.println(msg);
        client.sendmessage(msg);
        jtf.setText("");
    }

    public static void main(String[] args) {
        new ClientGui();
    }

    public void appendMsg(String msg) {
        jta.append(msg);
    }
}
