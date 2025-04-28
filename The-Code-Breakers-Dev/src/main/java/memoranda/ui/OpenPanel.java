package memoranda.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import memoranda.auth.AuthenticationService;
import memoranda.util.Local;


public class OpenPanel extends JFrame {

    JPanel openPanel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    TitledBorder titledBorder;

    JButton loginButton = new JButton();

    JButton registerButton = new JButton();

    JLabel title = new JLabel();

    String titleText = "<html> <style> h1 {text-align: center;} <style> <h1> Welcome to Teach Memoranda! \nPlease Choose Register or Login. <h1> <html>";

    static OpenPanel op;


    //Sets the login panel
    public void setLoginPanel(AuthenticationService authenticationService) {

        //Create main panel with a TitledBorder
        titledBorder = new TitledBorder(BorderFactory.createEtchedBorder(
                Color.white, new Color(156, 156, 158)), Local
                .getString("Sound"));
        setResizable(false);

        //Sets the icon/title/size for the app and exit on close
        setIconImage(new ImageIcon(AppFrame.class.getResource(
                "/ui/icons/jnotes16.png"))
                .getImage());
        setTitle("Teach Memoranda");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Sets border color
        openPanel.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white, new Color(43, 43, 43))));

        //Sets background color
        openPanel.setBackground(Color.decode("0x2b2b2b"));

        //Sets spacing for GBC
        gbc.insets = new Insets(20, 20, 20, 20);

        //Initial setup for GBC
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;

        //GBC for title
        title.setText(titleText);
        title.setMaximumSize(new Dimension(100, 50));
        title.setPreferredSize(new Dimension(100, 50));
        title.setForeground(Color.decode("0xffffff"));
        openPanel.add(title, gbc);

        //GBC setup for register button
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

        //Back button
        registerButton.setMaximumSize(new Dimension(200, 25));
        registerButton.setPreferredSize(new Dimension(200, 25));
        registerButton.setText("Register");
        openPanel.add(registerButton, gbc);

        //Action listener for register button
        registerButton.addActionListener(e -> {
            op.setVisible(false);
            RegisterPanel rp = new RegisterPanel();
            rp.startRegister(authenticationService);
        });


        //GBC setup for login button
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;

        //Login button
        loginButton.setMaximumSize(new Dimension(200, 25));
        loginButton.setPreferredSize(new Dimension(200, 25));
        loginButton.setText("Login");
        openPanel.add(loginButton, gbc);

        //Action listener for login button
        loginButton.addActionListener(e -> {
            op.setVisible(false);
            LoginPanel lp = new LoginPanel();
            lp.startLogin(authenticationService);
        });

        add(openPanel);

    }

    //Code for login page opening
    public static void openPanel(AuthenticationService authenticationService) {
        op = new OpenPanel();
        try {
            //Starts panel and makes it visible
            op.setLoginPanel(authenticationService);
            op.setLocationRelativeTo(null);
            op.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}