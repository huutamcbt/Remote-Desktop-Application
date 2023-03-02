package Views;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Models.OnlineContact;

public class OnlineContactPanel implements KeyListener {
    OnlineContact onlineContact ;
    public OnlineContactPanel(){

        onlineContact = null;
        onlineContact = new OnlineContact();

        onlineContact.rootPanel = new JPanel();
        onlineContact.email = new javax.swing.JTextField();
        onlineContact.password = new javax.swing.JTextField();
        onlineContact.login = new javax.swing.JButton("Login");
        onlineContact.keepSignIn = new javax.swing.JCheckBox();
        onlineContact.forgotPassword = new javax.swing.JLabel("<html><u>Forgot Password</u></html>");
        onlineContact.signUp = new javax.swing.JLabel("<html><u>Sign Up</u></html>");

        initOnlineContact();
    }

    public JPanel getOnlineContactPanel(){
        return onlineContact.rootPanel;
    }

    private void initOnlineContact(){
        onlineContact.rootPanel.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth, MainFrame.height - MainFrame.footerHeight));
        onlineContact.sep = new javax.swing.JSeparator();
        onlineContact.rootPanel.setLayout(new java.awt.GridBagLayout());
        onlineContact.gc = new java.awt.GridBagConstraints();
        onlineContact.email.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        onlineContact.password.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        // First column
        onlineContact.label = new javax.swing.JLabel("Email");
        onlineContact.label.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));

        onlineContact.gc.gridx = 0;
        onlineContact.gc.gridy = 0;
        onlineContact.gc.gridwidth = 2;
        onlineContact.gc.anchor = java.awt.GridBagConstraints.LINE_START;
        onlineContact.gc.insets = new java.awt.Insets(10,20,10,20);
        onlineContact.rootPanel.add(onlineContact.label,onlineContact.gc);

        // Email text field
        onlineContact.email.addKeyListener(this);
        onlineContact.email.setMargin(new Insets(0,10,0,0));
        onlineContact.gc.gridx = 0;
        onlineContact.gc.gridy = 1;
        onlineContact.gc.ipadx = 200;
        onlineContact.gc.ipady = 10;
        onlineContact.rootPanel.add(onlineContact.email, onlineContact.gc);

        // Password label
        onlineContact.label = new javax.swing.JLabel("Password");
        onlineContact.label.setFont(new java.awt.Font("", java.awt.Font.BOLD, 15));

        onlineContact.gc.gridx = 0;
        onlineContact.gc.gridy = 2;
        onlineContact.rootPanel.add(onlineContact.label,onlineContact.gc);

        // Password text field
        onlineContact.password.addKeyListener(this);
        onlineContact.password.setMargin(new Insets(0,10,0,0));
        onlineContact.gc.gridx = 0;
        onlineContact.gc.gridy = 3;
        onlineContact.rootPanel.add(onlineContact.password, onlineContact.gc);

        // Login button
        onlineContact.login.setFocusPainted(false);
        onlineContact.login.setEnabled(false);
        onlineContact.login.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override 
            public void mouseClicked(java.awt.event.MouseEvent e){
                if(onlineContact.login.isEnabled() == true){
                    java.lang.System.out.println(Integer.parseInt(onlineContact.email.getText()) - Integer.parseInt(onlineContact.password.getText()));
                }
            }
        });

        onlineContact.gc.gridx = 0;
        onlineContact.gc.gridy = 4;
        onlineContact.gc.ipadx = 70;
        onlineContact.gc.ipady = 10;
        onlineContact.rootPanel.add(onlineContact.login, onlineContact.gc);

        // Keep user signed in
        onlineContact.gc.gridx = 0;
        onlineContact.gc.gridy = 5;
        onlineContact.gc.gridwidth = 1;
        onlineContact.gc.ipadx = 0;
        onlineContact.gc.ipady = 0;
        onlineContact.rootPanel.add(onlineContact.keepSignIn, onlineContact.gc);

        onlineContact.label = new javax.swing.JLabel("Keep me signed in");
        onlineContact.label.setFont(new java.awt.Font("", java.awt.Font.PLAIN, 12));

        onlineContact.gc.gridx = 1;
        onlineContact.gc.gridy = 5;
        onlineContact.rootPanel.add(onlineContact.label,onlineContact.gc);

        // Forgot password label
        onlineContact.forgotPassword.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        onlineContact.forgotPassword.setFont(new java.awt.Font("", java.awt.Font.PLAIN, 12));

        onlineContact.gc.gridx = 0;
        onlineContact.gc.gridy = 6;
        onlineContact.gc.gridwidth = 2;
        onlineContact.rootPanel.add(onlineContact.forgotPassword, onlineContact.gc);

        // Sign up label
        onlineContact.signUp.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        onlineContact.signUp.setFont(new java.awt.Font("", java.awt.Font.PLAIN, 12));

        onlineContact.gc.gridx = 0;
        onlineContact.gc.gridy = 7;
        onlineContact.rootPanel.add(onlineContact.signUp, onlineContact.gc);

        // Add vertical separator
        onlineContact.sep = new javax.swing.JSeparator();
        onlineContact.sep.setOrientation(javax.swing.JSeparator.VERTICAL);

        onlineContact.gc.gridx = 2;
        onlineContact.gc.gridy = 0;
        onlineContact.gc.ipadx = 2;
        onlineContact.gc.ipady = 500;
        onlineContact.gc.gridheight = 9;
        onlineContact.gc.gridwidth = 1;
        onlineContact.rootPanel.add(onlineContact.sep, onlineContact.gc);

        // Second column
        onlineContact.label = new javax.swing.JLabel("<html>You don't need to remember ID and <br> Password of your partners<html>");
        onlineContact.label.setFont(new java.awt.Font("", java.awt.Font.BOLD, 15));
       
        onlineContact.gc.gridx = 3;
        onlineContact.gc.gridy = 0;
        onlineContact.gc.ipadx = 0;
        onlineContact.gc.ipady = 0;
        onlineContact.gc.gridheight = 1;
        onlineContact.rootPanel.add(onlineContact.label, onlineContact.gc);

        onlineContact.label = new javax.swing.JLabel("<html>Sign in with your Application account to <br> use the online contact feature<html>");
        onlineContact.label.setFont(new java.awt.Font("", java.awt.Font.PLAIN, 12));
       
        onlineContact.gc.gridx = 3;
        onlineContact.gc.gridy = 1;
        onlineContact.rootPanel.add(onlineContact.label, onlineContact.gc);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == onlineContact.email){
            if(java.util.Objects.equals(onlineContact.email.getText(), "") == false){
                if(java.util.Objects.equals(onlineContact.password.getText(), "") == false){
                    onlineContact.login.setEnabled(true);
                }
            }
            else{
                onlineContact.login.setEnabled(false);
            }
        }
        if(e.getSource() == onlineContact.password){
            if(java.util.Objects.equals(onlineContact.password.getText(), "") == false){
                if(java.util.Objects.equals(onlineContact.email.getText(), "") == false){
                    onlineContact.login.setEnabled(true);
                }
            }
            else{
                onlineContact.login.setEnabled(false);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
