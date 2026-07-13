package org.example.ui.login;

import net.miginfocom.swing.MigLayout;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;
import org.example.ui.component.DangerButton;
import org.example.ui.component.PrimaryButton;
import org.example.ui.component.RoundedPasswordField;
import org.example.ui.component.RoundedTextField;
import org.example.ui.dashboard.DashboardFrame;
import org.example.ui.theme.AppColors;
import org.example.ui.theme.AppFonts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JPanel backgroundPanel;
    private JPanel loginCard;

    private JLabel titleLabel;
    private JLabel subtitleLabel;

    private RoundedTextField txtUsername;
    private RoundedPasswordField txtPassword;

    private JCheckBox chkShowPassword;

    private PrimaryButton btnLogin;
    private DangerButton btnExit;

    private final UserService userService;

    public LoginFrame() {

        userService = new UserServiceImpl();

        initializeFrame();

        initializeComponents();

        initializeLayout();

        registerEvents();

        setVisible(true);

    }

    private void initializeFrame() {

        setTitle("Student Management System");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(1200,700);

        setLocationRelativeTo(null);

        setResizable(false);

    }

    private void initializeComponents() {

        backgroundPanel = new JPanel(new MigLayout(
                "fill",
                "[grow,center]",
                "[grow,center]"
        ));
        backgroundPanel.setBackground(AppColors.BACKGROUND);

        loginCard = new JPanel(new MigLayout(
                "wrap 1,fillx,insets 40",
                "[grow,fill]"
        ));

        loginCard.setBackground(AppColors.CARD);

        loginCard.setBorder(new EmptyBorder(40,40,40,40));

        titleLabel = new JLabel("Student Management System");

        titleLabel.setFont(AppFonts.TITLE);

        titleLabel.setForeground(AppColors.PRIMARY);

        subtitleLabel = new JLabel("Login to continue");

        subtitleLabel.setFont(AppFonts.NORMAL);

        txtUsername = new RoundedTextField(20);

        txtUsername.putClientProperty(
                "JTextField.placeholderText",
                "Username"
        );

        txtPassword = new RoundedPasswordField(20);

        txtPassword.putClientProperty(
                "JTextField.placeholderText",
                "Password"
        );

        chkShowPassword = new JCheckBox("Show Password");

        chkShowPassword.setBackground(AppColors.CARD);

        chkShowPassword.setFont(AppFonts.NORMAL);

        btnLogin = new PrimaryButton("Login");

        btnExit = new DangerButton("Exit");

    }

    private void initializeLayout() {

        loginCard.add(titleLabel);

        loginCard.add(subtitleLabel,"gapbottom 20");

        loginCard.add(txtUsername,"h 42");

        loginCard.add(txtPassword,"h 42");

        loginCard.add(chkShowPassword);

        loginCard.add(btnLogin,"h 45");

        loginCard.add(btnExit,"h 45");

        backgroundPanel.add(
                loginCard,
                "center,w 500!,h 470!"
        );

        setContentPane(backgroundPanel);

    }

    private void registerEvents() {

        btnExit.addActionListener(e -> System.exit(0));

        chkShowPassword.addActionListener(e -> {

            if(chkShowPassword.isSelected()){

                txtPassword.setEchoChar((char)0);

            }else{

                txtPassword.setEchoChar('•');

            }

        });

        btnLogin.addActionListener(e -> login());

        getRootPane().setDefaultButton(btnLogin);

    }

    private void login() {

        String username = txtUsername.getText().trim();

        String password = String.valueOf(txtPassword.getPassword());

        if(username.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username."
            );

            txtUsername.requestFocus();

            return;

        }

        if(password.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter password."
            );

            txtPassword.requestFocus();

            return;

        }

        User user = userService.login(username,password);

        if(user==null){

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password"
            );

            txtPassword.setText("");

            txtPassword.requestFocus();

            return;

        }

        JOptionPane.showMessageDialog(
                this,
                "Welcome " + user.getUsername()
        );

        dispose();

        new DashboardFrame();

    }

}