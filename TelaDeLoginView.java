package view;

import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class TelaDeLoginView extends JFrame {
    public static JLabel lblLogin;
    public static JTextField txtLogin;
    public static JLabel lblSenha;
    public static JPasswordField txtSenha;
    public static JButton btnLogar;
    public static JLabel lblNotificacoes;

    public TelaDeLoginView() {
        super("Tela de Login");
        // setLayout(new GridLayout(6,1,5,5));
        setLayout(new FlowLayout());

        lblLogin = new JLabel("Login:");
        add(lblLogin);

        txtLogin = new JTextField(10);
        add(txtLogin);

        lblSenha = new JLabel("Senha:");
        add(lblSenha);

        txtSenha = new JPasswordField(10);
        add(txtSenha);

        btnLogar = new JButton("Logar");
        add(btnLogar);

        lblNotificacoes = new JLabel("Notificações");
        // lblNotificacoes.setSize(getContentPane().getWidth(), 40);
        add(lblNotificacoes);

        ButtonHandler buttonHandler = new ButtonHandler();
        btnLogar.addActionListener(buttonHandler);

        txtSenha.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (String.valueOf(txtSenha.getPassword()).trim().length() > 0) {
                        if (e.getKeyCode() == 10) {
                            TelaDeLoginController.logarController();
                        }
                    }
                }
            }
        );
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            TelaDeLoginController.logarController();
        }
    }

    public static String setHtmlFormat(String txt) {
        return "<html><body>" + txt + "</body></html>";
    }

    public static void notificarUsuario(String strTexto) {
        lblNotificacoes.setText(setHtmlFormat(strTexto));
    }

    public static TelaDeLoginView appTelaDeLoginView;
    public static void main(String[] args) {
        appTelaDeLoginView = new TelaDeLoginView();
        appTelaDeLoginView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        appTelaDeLoginView.setSize(200,200);
        appTelaDeLoginView.setVisible(true);
    }
}