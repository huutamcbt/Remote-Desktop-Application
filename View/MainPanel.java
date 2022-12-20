package View;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import Controller.FTPServer.Client.Start;

public class MainPanel extends javax.swing.JFrame implements java.awt.event.ActionListener, java.awt.event.KeyListener, java.awt.event.MouseListener{
    private javax.swing.JLabel label;
    private javax.swing.JSeparator sep;
    private java.awt.GridBagConstraints gc;
    
    // Create attributes for Home Panel
    private javax.swing.JPanel panel;
    private javax.swing.JButton frequentlyQuestion;
    private javax.swing.JPanel remotePanel;
    private javax.swing.JTextField allowControlPassword;
    private javax.swing.JButton allowingControlPasswordChange;
    private javax.swing.JTextField allowControlID;
    private javax.swing.JButton controlComputerConnection;
    private javax.swing.JTextField controlComputerPassword;
    private javax.swing.JTextField controlComputerID;
    private javax.swing.JCheckBox firstCheckBox;
    private javax.swing.JCheckBox secondCheckBox;
    private javax.swing.JCheckBox thirdCheckBox;
    private javax.swing.JTextField customPassword;
    private javax.swing.JButton JButtonCustomPassword;
    ///////////////////////////////////////////////////////////
    // Create attributes for Online Contact
    private javax.swing.JTextField email;
    private javax.swing.JTextField password;
    private javax.swing.JButton login;
    private javax.swing.JCheckBox keepSignIn;
    private javax.swing.JLabel forgotPassword;
    private javax.swing.JLabel signUp;
    ///////////////////////////////////////////////////////////
    // Create attributes for FTPServerPanel
    private javax.swing.JTextField partnerID;
    private javax.swing.JTextField partnerPassword;
    private javax.swing.JButton connection;
    private static javax.swing.JScrollPane clientSP;
    private static javax.swing.JScrollPane serverSP;
    private static javax.swing.JList<String> clientList;
    private static javax.swing.JList<String> serverList;
    private static java.io.File[] clientFiles;
    private static java.io.File[] serverFiles;
    private static javax.swing.DefaultListModel<String> clientDLM;
    private static javax.swing.DefaultListModel<String> serverDLM;
    private javax.swing.JFileChooser fc;
    private javax.swing.JButton choiceButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton slowDownload; 
    private javax.swing.JButton mediumDownload; 
    private javax.swing.JButton fastDownload;
    private Start client;
    

    public MainPanel(){
        
        panel = new javax.swing.JPanel();
        frequentlyQuestion = new javax.swing.JButton(new javax.swing.ImageIcon("Images/help.png"));
        remotePanel = new javax.swing.JPanel();
        gc = new java.awt.GridBagConstraints();
        allowControlPassword = new javax.swing.JTextField();
        allowControlID = new javax.swing.JTextField();
        controlComputerConnection = new javax.swing.JButton();
        controlComputerPassword = new javax.swing.JTextField();
        controlComputerID = new javax.swing.JTextField();
        firstCheckBox = new javax.swing.JCheckBox();
        secondCheckBox = new javax.swing.JCheckBox();
        thirdCheckBox = new javax.swing.JCheckBox();
        customPassword = new javax.swing.JTextField();

        email = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        login = new javax.swing.JButton("Login");
        keepSignIn = new javax.swing.JCheckBox();
        forgotPassword = new javax.swing.JLabel("<html><u>Forgot Password</u></html>");
        signUp = new javax.swing.JLabel("<html><u>Sign Up</u></html>");

        partnerID = new javax.swing.JTextField();
        partnerPassword = new javax.swing.JTextField();
        connection = new javax.swing.JButton("<html>Connect to partner<html>");
        clientFiles = new java.io.File[20];
        serverFiles = new java.io.File[20];
        clientDLM = new javax.swing.DefaultListModel<String>();
        serverDLM = new javax.swing.DefaultListModel<String>();
        clientList = new javax.swing.JList<String>();
        serverList = new javax.swing.JList<String>();
        clientSP = new javax.swing.JScrollPane(clientList);
        serverSP = new javax.swing.JScrollPane(serverList);
        choiceButton = new javax.swing.JButton("Open a file");
        removeButton = new javax.swing.JButton("Remove these files");
        slowDownload = new javax.swing.JButton("Slow download");
        mediumDownload = new javax.swing.JButton("Medium download");
        fastDownload = new javax.swing.JButton("Fast download");
        fc = new javax.swing.JFileChooser();
       
    }

    public void initHomePanel(){
        try {
            generateAllowControlID();
        } catch (Exception e) {
            java.lang.System.out.println(e);
        }
        generateAllowControlPassword();
        gc = new java.awt.GridBagConstraints();
        javax.swing.border.Border border1 = javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1,  new java.awt.Color(189, 186, 191 ));
        MainFrame.mainPanel.setLayout(new java.awt.FlowLayout());
        remotePanel.setLayout(new java.awt.GridBagLayout());
        frequentlyQuestion.setPreferredSize(new java.awt.Dimension(35,35));
        frequentlyQuestion.setBackground(new java.awt.Color(242, 245, 251));
        frequentlyQuestion.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                try {
                    java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.archlinux.org"));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        allowControlID.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        allowControlPassword.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        controlComputerID.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        controlComputerPassword.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        
        
        panel.setBorder(border1);
        panel.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth, 35));
        panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,10,0));
        panel.add(frequentlyQuestion,java.awt.BorderLayout.LINE_END);

        MainFrame.mainPanel.add(panel);
        
        // Create allowing control panel
        label = new javax.swing.JLabel();
        label.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth/2 -90,40));
        label.setIcon(new javax.swing.ImageIcon(new javax.swing.ImageIcon("Images/satellite_vista.png").getImage().getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH)));
        label.setText("Allow Remote Control");
        label.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        label.setBorder(border1);
        label.setIconTextGap(20);

        gc.fill = java.awt.GridBagConstraints.HORIZONTAL;  
        gc.insets = new java.awt.Insets(15,50,15,50);
        gc.gridwidth = 2;
        gc.gridx = 0;
        gc.gridy = 0;
        remotePanel.add(label, gc);

        // Create controlling computer panel
        label = new javax.swing.JLabel();
        label.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth/2 -90,40));
        label.setIcon(new javax.swing.ImageIcon(new javax.swing.ImageIcon("Images/profile.png").getImage().getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH)));
        label.setText("Control a Remote Computer");
        label.setFont(new java.awt.Font("",java.awt.Font.BOLD, 15));
        label.setBorder(border1);
        label.setIconTextGap(20);

        gc.gridx = 3;
        gc.gridy = 0;
        remotePanel.add(label, gc);

        // Create the first description
        label = new javax.swing.JLabel("<html>Please tell your partner the following ID and Password if you would like to allow remote control</html>");
        label.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth/2 -90,40));

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new java.awt.Insets(15,50,50,50);
        remotePanel.add(label, gc);

        // Create the vertical separator
        sep = new javax.swing.JSeparator();
        sep.setOrientation(javax.swing.SwingConstants.VERTICAL);
        sep.setPreferredSize(new java.awt.Dimension(2,200));
        sep.setBackground(new java.awt.Color(189, 186, 191 ));

        gc.gridx = 2;
        gc.gridy = 1;
        gc.gridheight = 3;
        gc.insets = new java.awt.Insets(0,0,0,0);
        remotePanel.add(sep, gc);

        gc.gridheight = 1;
        // Create the second description
        label = new javax.swing.JLabel("<html>Please enter your partner's ID to remote control your partner's computer</html>");
        label.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth/2 -90,40));

        gc.gridx = 3;
        gc.gridy = 1;
        
       
        gc.insets = new java.awt.Insets(15,50,50,50);
        remotePanel.add(label, gc);

        gc.gridwidth = 1;
        
        // Create the server ID
        label = new javax.swing.JLabel("Your ID");
        
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new java.awt.Insets(0,50,0,0);
        remotePanel.add(label, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.ipady =10;
        gc.insets = new java.awt.Insets(0,10,0,50);
        remotePanel.add(allowControlID, gc);

        // Create the server Password
        label = new javax.swing.JLabel("Password");
        //gc.anchor = java.awt.GridBagConstraints.PAGE_START;
        gc.gridx = 0;
        gc.gridy = 3;
        gc.insets = new java.awt.Insets(0,50,0,0);
        remotePanel.add(label, gc);

        
      
        gc.gridx = 1;
        gc.gridy = 3;
       
        gc.insets = new java.awt.Insets(0,10,0,50);
        remotePanel.add(allowControlPassword, gc);

        // Create the client ID
        label = new javax.swing.JLabel("Partner ID");

        gc.gridx = 3;
        gc.gridy = 2;
       
        gc.insets = new java.awt.Insets(5,50,5,0);
        remotePanel.add(label, gc);

        controlComputerID.addKeyListener(this);
      
        gc.gridx = 4;
        gc.gridy = 2;
        
        gc.insets = new java.awt.Insets(5,10,5,50);
        remotePanel.add(controlComputerID, gc);

        // Create the client Password
        label = new javax.swing.JLabel("Password");

        gc.gridx = 3;
        gc.gridy = 3;
        gc.insets = new java.awt.Insets(5,50,5,0);
        remotePanel.add(label, gc);

        controlComputerPassword.addKeyListener(this);
      
        gc.gridx = 4;
        gc.gridy = 3;
        
        gc.insets = new java.awt.Insets(5,10,5,50);
        remotePanel.add(controlComputerPassword, gc);

        // Create the horizontal separator
        sep = new javax.swing.JSeparator();
        sep.setOrientation(javax.swing.SwingConstants.HORIZONTAL);
        sep.setBackground(new java.awt.Color(189, 186, 191 ));

        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        gc.ipady = 0;
        gc.insets = new java.awt.Insets(25,50,0,50);
        remotePanel.add(sep, gc);

        // Create the connection button
        
        controlComputerConnection.setText("<html>Connect to partner</html>");
        controlComputerConnection.setIcon(new javax.swing.ImageIcon(new javax.swing.ImageIcon("Images/forward.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
        controlComputerConnection.setIconTextGap(20);
        controlComputerConnection.setFocusPainted(false);
        controlComputerConnection.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                if(controlComputerConnection.isEnabled() == true){
                    java.lang.System.out.println(getControlComputerID());
                    java.lang.System.out.println(getControlComputerPassword());
                    new Controller.RDP.Client.Client(getControlComputerID(), getControlComputerPassword());
                }
            }
        });
        controlComputerConnection.setEnabled(false);

        gc.gridwidth = 2;
        gc.gridx = 3;
        gc.gridy = 4;
        gc.ipadx = 50;
        gc.ipady = 10;
        gc.fill = java.awt.GridBagConstraints.NONE;
        remotePanel.add(controlComputerConnection, gc);
        
        // Create unattended access panel
        label = new javax.swing.JLabel("Unattended access");
        label.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 2;
        gc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gc.insets = new java.awt.Insets(30,50,30,0);
        remotePanel.add(label, gc);

        gc.gridx = 0;
        gc.gridy = 6;
        gc.ipadx = 0;
        gc.ipady = 0;
        gc.gridwidth = 1;
        gc.insets = new java.awt.Insets(10,50,10,0);
        remotePanel.add(firstCheckBox, gc);

        gc.gridx = 0;
        gc.gridy = 7;
        gc.gridwidth = 1;
        remotePanel.add(secondCheckBox, gc);

        gc.gridx = 0;
        gc.gridy = 8;
        gc.gridwidth = 1;
        remotePanel.add(thirdCheckBox, gc);

        label = new javax.swing.JLabel("<html>Run the application with Windows</html>");

        gc.gridx = 1;
        gc.gridy = 6;
        gc.gridwidth = 1;
        gc.insets = new java.awt.Insets(0,0,0,0);
        remotePanel.add(label, gc);

        label = new javax.swing.JLabel("<html>Prevent Windows from going to sleep</html>");

        gc.gridx = 1;
        gc.gridy = 7;
        gc.gridwidth = 1;
        remotePanel.add(label, gc);

        label = new javax.swing.JLabel("<html>Allow turn on computer remotely</html>");

        gc.gridx = 1;
        gc.gridy = 8;
        gc.gridwidth = 1;
        remotePanel.add(label, gc);

        // Create the custom password field
        label = new javax.swing.JLabel("<html>Custom <br> Password</html>");

        gc.gridx = 0;
        gc.gridy = 9;
        gc.insets = new java.awt.Insets(10,50,0,0);
        remotePanel.add(label, gc);

        gc.gridx = 1;
        gc.gridy = 9;
        gc.ipady = 10;
        gc.insets = new java.awt.Insets(10,10,0,50);
        
        remotePanel.add(customPassword, gc);
        
        MainFrame.mainPanel.add(remotePanel);
    }

    public void initOnlineContact(){
        sep = new javax.swing.JSeparator();
        MainFrame.mainPanel.setLayout(new java.awt.GridBagLayout());
        gc = new java.awt.GridBagConstraints();
        email.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        password.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        // First column
        label = new javax.swing.JLabel("Email");
        label.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));

        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.anchor = java.awt.GridBagConstraints.LINE_START;
        gc.insets = new java.awt.Insets(10,20,10,20);
        MainFrame.mainPanel.add(label,gc);

        // Email text field
        email.addKeyListener(this);
        email.setMargin(new Insets(0,10,0,0));
        gc.gridx = 0;
        gc.gridy = 1;
        gc.ipadx = 200;
        gc.ipady = 10;
        MainFrame.mainPanel.add(email, gc);

        // Password label
        label = new javax.swing.JLabel("Password");
        label.setFont(new java.awt.Font("", java.awt.Font.BOLD, 15));

        gc.gridx = 0;
        gc.gridy = 2;
        MainFrame.mainPanel.add(label,gc);

        // Password text field
        password.addKeyListener(this);
        password.setMargin(new Insets(0,10,0,0));
        gc.gridx = 0;
        gc.gridy = 3;
        MainFrame.mainPanel.add(password, gc);

        // Login button
        login.setFocusPainted(false);
        login.setEnabled(false);
        login.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override 
            public void mouseClicked(java.awt.event.MouseEvent e){
                if(login.isEnabled() == true){
                    java.lang.System.out.println(Integer.parseInt(email.getText()) - Integer.parseInt(password.getText()));
                }
            }
        });

        gc.gridx = 0;
        gc.gridy = 4;
        gc.ipadx = 70;
        gc.ipady = 10;
        MainFrame.mainPanel.add(login, gc);

        // Keep user signed in
        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 1;
        gc.ipadx = 0;
        gc.ipady = 0;
        MainFrame.mainPanel.add(keepSignIn, gc);

        label = new javax.swing.JLabel("Keep me signed in");
        label.setFont(new java.awt.Font("", java.awt.Font.PLAIN, 12));

        gc.gridx = 1;
        gc.gridy = 5;
        MainFrame.mainPanel.add(label,gc);

        // Forgot password label
        forgotPassword.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        forgotPassword.setFont(new java.awt.Font("", java.awt.Font.PLAIN, 12));

        gc.gridx = 0;
        gc.gridy = 6;
        gc.gridwidth = 2;
        MainFrame.mainPanel.add(forgotPassword,gc);

        // Sign up label
        signUp.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        signUp.setFont(new java.awt.Font("", java.awt.Font.PLAIN, 12));

        gc.gridx = 0;
        gc.gridy = 7;
        MainFrame.mainPanel.add(signUp,gc);

        // Add vertical separator
        sep = new javax.swing.JSeparator();
        sep.setOrientation(javax.swing.JSeparator.VERTICAL);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.ipadx = 2;
        gc.ipady = 500;
        gc.gridheight = 9;
        gc.gridwidth = 1;
        MainFrame.mainPanel.add(sep, gc);

        // Second column
        label = new javax.swing.JLabel("<html>You don't need to remember ID and <br> Password of your partners<html>");
        label.setFont(new java.awt.Font("", java.awt.Font.BOLD, 15));
       
        gc.gridx = 3;
        gc.gridy = 0;
        gc.ipadx = 0;
        gc.ipady = 0;
        gc.gridheight = 1;
        MainFrame.mainPanel.add(label,gc);

        label = new javax.swing.JLabel("<html>Sign in with your Application account to <br> use the online contact feature<html>");
        label.setFont(new java.awt.Font("", java.awt.Font.PLAIN, 12));
       
        gc.gridx = 3;
        gc.gridy = 1;
        MainFrame.mainPanel.add(label,gc);
    }
    
    public void initFTPServerPanel(){
        new Controller.FTPServer.Server.Start(clientFiles, clientList, serverList, serverDLM);
        java.lang.System.out.println(Controller.FTPServer.Server.Start.getIpAddress());
        java.lang.System.out.println(Controller.FTPServer.Server.Start.getPassword());
        MainFrame.mainPanel.setLayout(new java.awt.GridBagLayout());
        MainFrame.mainPanel.setPreferredSize(new java.awt.Dimension(800, 700));
        gc = new java.awt.GridBagConstraints();
        
        // Client ID Label
        label = new javax.swing.JLabel("Your ID:   " + Controller.FTPServer.Server.Start.getIpAddress());
        label.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));
        label.setPreferredSize(new java.awt.Dimension(270,20));

        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.ipady = 5;
        gc.insets = new java.awt.Insets(10, 10, 10, 10);
        gc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        MainFrame.mainPanel.add(label, gc);

        // Client Password label
        label = new javax.swing.JLabel("Your Password:   " + Controller.FTPServer.Server.Start.getPassword());
        label.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));
        label.setPreferredSize(new java.awt.Dimension(270,20));

        gc.gridx = 2;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.ipady = 5;
        gc.insets = new java.awt.Insets(10, 10, 10, 10);
        gc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        MainFrame.mainPanel.add(label, gc);

        // Partner ID label
        label = new javax.swing.JLabel("Partner ID");
        label.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));

        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.ipady = 5;
        gc.insets = new java.awt.Insets(10, 10, 10, 10);
        gc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        MainFrame.mainPanel.add(label, gc);

        // Partner ID textfield
        partnerID.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));
        
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        MainFrame.mainPanel.add(partnerID, gc);

        // Partner Password label
        label = new javax.swing.JLabel("Partner Password");
        label.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));

        gc.gridx = 2;
        gc.gridy = 1;
        gc.gridwidth = 1;
        MainFrame.mainPanel.add(label, gc);

        // Partner Password textfield
        partnerPassword.setFont(new java.awt.Font("", java.awt.Font.BOLD, 15));
        
        gc.gridx = 3;
        gc.gridy = 1;
        gc.gridwidth = 1;
        MainFrame.mainPanel.add(partnerPassword, gc);

        gc.ipadx = 0;
        gc.ipady = 0;

        // connection button
        connection.setIcon(new javax.swing.ImageIcon(
            new javax.swing.ImageIcon("Images/forward.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
        connection.setFocusPainted(false);
        connection.setEnabled(false);
        connection.setIconTextGap(20);
        //connection.setPreferredSize(new java.awt.Dimension(70,25));

        gc.gridx = 4;
        gc.gridy = 1;
        gc.ipady = 5;
        gc.gridwidth = 1;
        MainFrame.mainPanel.add(connection, gc);

        // client label
        label = new javax.swing.JLabel("Your files:");
        label.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 15));

        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        gc.ipady = 0;
        gc.insets = new java.awt.Insets(5, 10, 5, 10);
        MainFrame.mainPanel.add(label, gc);

        // server label
        label = new javax.swing.JLabel("Partner files:");
        label.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 15));

        gc.gridx = 2;
        gc.gridy = 2;
        gc.gridwidth = 2;
        MainFrame.mainPanel.add(label, gc);
        
        // client file Jlist
        clientList.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 15));
        clientList.setVisibleRowCount(15);
        clientList.setPreferredSize(new java.awt.Dimension(250, 300));

        clientSP.setPreferredSize(new java.awt.Dimension(270, 350));
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        gc.fill = java.awt.GridBagConstraints.VERTICAL;
        
        MainFrame.mainPanel.add(clientSP, gc);

        // server file JList
        serverList.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 15));
        serverList.setVisibleRowCount(15);
        serverList.setPreferredSize(new java.awt.Dimension(250, 300));

        serverSP.setPreferredSize(new java.awt.Dimension(270, 350));
        gc.gridx = 2;
        gc.gridy = 3;
        gc.gridwidth =2;
        
        MainFrame.mainPanel.add(serverSP, gc);

        // reset attribute to default value
        gc.ipadx = 0;
        gc.ipady = 0;
        gc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        // choice button
        choiceButton.setFocusPainted(false);

        gc.gridx = 0;
        gc.gridy = 4;
        gc.ipady = 5;
        gc.gridwidth = 2;

        MainFrame.mainPanel.add(choiceButton, gc);

        // removeButton
        removeButton.setFocusPainted(false);

        gc.gridx = 0;
        gc.gridy = 5;
        gc.ipady = 5;
        gc.gridwidth = 2;

        MainFrame.mainPanel.add(removeButton, gc);


        // slow download button
        slowDownload.setFocusPainted(false);

        gc.gridx = 2;
        gc.gridy = 4;
        gc.gridwidth = 2;
        MainFrame.mainPanel.add(slowDownload, gc);

        // medium download button
        mediumDownload.setFocusPainted(false);

        gc.gridx = 2;
        gc.gridy = 5;
        gc.gridwidth = 2;
        MainFrame.mainPanel.add(mediumDownload, gc);

        // fast download button
        fastDownload.setFocusPainted(false);

        gc.gridx = 2;
        gc.gridy = 6;
        gc.gridwidth = 2;
        MainFrame.mainPanel.add(fastDownload, gc);

        gc.ipadx = 0;
        gc.ipady = 0;
        gc.gridwidth = 1;

        partnerID.addKeyListener(this);
        partnerPassword.addKeyListener(this);
        choiceButton.addMouseListener(this);
        removeButton.addMouseListener(this);
        connection.addMouseListener(this);
        slowDownload.addMouseListener(this);
        mediumDownload.addMouseListener(this);
        fastDownload.addMouseListener(this);
    }

    public void clearScreen(){
        sep = null;
        label = null;
        gc = null;
        panel.removeAll();
        remotePanel.removeAll();
        
    }
    private void generateAllowControlPassword(){
        allowControlPassword.setText(Controller.RDP.Server.Server.getPassword()); 
    }
    private void generateAllowControlID() throws java.net.UnknownHostException{
        allowControlID.setText(Controller.RDP.Server.Server.getIpAddress());
       
    }
    public int getAllowControlPassword(){
        return Integer.parseInt(allowControlPassword.getText());
    }
    public String getControlComputerID(){
        return controlComputerID.getText();
    }
    public String getControlComputerPassword(){
        return controlComputerPassword.getText();
    }
    public static void addFile(String file){
        if(serverList.getModel().getSize() <= 20){
            serverFiles[serverList.getModel().getSize()] = new java.io.File(file);
            serverDLM.addElement(serverFiles[serverList.getModel().getSize()].getName());
            serverList.setModel(serverDLM);
        }
    }
    public static void removeFile(int index){
        if(serverList.getModel().getSize() > 0){
            serverDLM.removeElementAt(index);
            serverList.setModel(serverDLM);

        }
    }
    private String getFTPUserID(){
        return "";
    }

    private String getFTPUserPassword(){
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == partnerID){
            if(java.util.Objects.equals(partnerID.getText(), "") == false){
                if(java.util.Objects.equals(partnerPassword.getText(), "") == false){
                    connection.setEnabled(true);
                }
            }
            else{
                connection.setEnabled(false);
            }
        }
        if(e.getSource() == partnerPassword){
            if(java.util.Objects.equals(partnerPassword.getText(), "") == false){
                if(java.util.Objects.equals(partnerID.getText(), "") == false){
                    connection.setEnabled(true);
                }
            }
            else{
                connection.setEnabled(false);
            }
        }
        if(e.getSource() == controlComputerID){
            if(java.util.Objects.equals(controlComputerID.getText(), "") == false){
                if(java.util.Objects.equals(controlComputerPassword.getText(), "") == false){
                    controlComputerConnection.setEnabled(true);
                }
            }
            else{
                controlComputerConnection.setEnabled(false);
            }
        }
        if(e.getSource() == controlComputerPassword){
            if(java.util.Objects.equals(controlComputerPassword.getText(), "") == false){
                if(java.util.Objects.equals(controlComputerID.getText(), "") == false){
                    controlComputerConnection.setEnabled(true);
                }
            }
            else{
                controlComputerConnection.setEnabled(false);
            }
        }
        if(e.getSource() == email){
            if(java.util.Objects.equals(email.getText(), "") == false){
                if(java.util.Objects.equals(password.getText(), "") == false){
                    login.setEnabled(true);
                }
            }
            else{
                login.setEnabled(false);
            }
        }
        if(e.getSource() == password){
            if(java.util.Objects.equals(password.getText(), "") == false){
                if(java.util.Objects.equals(email.getText(), "") == false){
                    login.setEnabled(true);
                }
            }
            else{
                login.setEnabled(false);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == choiceButton){
            fc.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
            int returnValue = fc.showOpenDialog(this);
            if(returnValue == javax.swing.JFileChooser.APPROVE_OPTION){
                if(clientList.getModel().getSize() <= 20){
                    //clientCount += 1;
                    clientFiles[clientList.getModel().getSize()] = fc.getSelectedFile();
                    if(client != null){
                        client.addClientFile(clientFiles[clientList.getModel().getSize()].getName());
                    }
                    
                    clientDLM.addElement(clientFiles[clientList.getModel().getSize()].getName());
                    clientList.setModel(clientDLM);
                } 
            }
            else{
                java.lang.System.out.println("Open command cancelled by user");
            }
        }
        if(e.getSource() == removeButton){
            if(clientList.getSelectedValue() != null){
                if(clientList.getModel().getSize() > 0){
                    java.util.List<String> items;
                    
                    if(clientList.getSelectedValuesList().size() > 1){
                        items = clientList.getSelectedValuesList();
                        for (String item : clientList.getSelectedValuesList()) {
                            clientDLM.removeElement(item);
                            System.out.println(item);
                        }

                        clientList.setModel(clientDLM);
                    }
                    else{
                       client.removeClientFile(clientList.getSelectedIndex());
                    }
                }
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, "You have no file to remove", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == connection){
            client = new Controller.FTPServer.Client.Start(partnerID.getText(), partnerPassword.getText());
            
            if(client.verifyConnection(clientList, serverList, serverDLM) == false){
                client = null;
            }
        }
        if(e.getSource() == slowDownload){
            if(serverList.getSelectedValue() != null){
                boolean continueloop = true;
                // Create default  name for file
                do {
                    fc.setSelectedFile(new java.io.File(serverList.getSelectedValue()));
                    int returnValue = fc.showSaveDialog(this);
                    if(returnValue == javax.swing.JFileChooser.APPROVE_OPTION){
                        java.lang.System.out.println("Slow download");
                        java.io.File file = fc.getSelectedFile();
                        if(file.exists()){
                            int choice = javax.swing.JOptionPane.showConfirmDialog(this, "This file already exist. Do you want to replace it?", "Warning", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                            if(choice == javax.swing.JOptionPane.OK_OPTION){
                                if(client != null){
                                    client.downloadFile(serverList.getSelectedValue(), file, Controller.FTPServer.Speed.SLOW.getSpeed(), serverList.getSelectedIndex());
                                }
                                continueloop = false;
                                String fileName = serverList.getSelectedValue();
                                int index = serverList.getSelectedIndex();
                                serverDLM.removeElementAt(index);
                                serverDLM.add(index, fileName + "  Complete");
                            }
                        }
                        else{
                            if(client != null){
                                client.downloadFile(serverList.getSelectedValue(), file, Controller.FTPServer.Speed.SLOW.getSpeed(), serverList.getSelectedIndex());
                            }
                            continueloop = false;
                            String fileName = serverList.getSelectedValue();
                            int index = serverList.getSelectedIndex();
                            serverDLM.removeElementAt(index);
                            serverDLM.add(index, fileName + "  Complete");
                        }
                    }
                    else{
                        java.lang.System.out.println("Open command cancelled by user");
                        break;
                    }
                    
                } while (continueloop);
                
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, "Please select some files to download", "", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == mediumDownload){
            if(serverList.getSelectedValue() != null){
                boolean continueloop = true;
                // Create default  name for file
                do {
                    fc.setSelectedFile(new java.io.File(serverList.getSelectedValue()));
                    int returnValue = fc.showSaveDialog(this);
                    if(returnValue == javax.swing.JFileChooser.APPROVE_OPTION){
                        java.lang.System.out.println("Slow download");
                        java.io.File file = fc.getSelectedFile();
                        if(file.exists()){
                            int choice = javax.swing.JOptionPane.showConfirmDialog(this, "This file already exist. Do you want to replace it?", "Warning", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                            if(choice == javax.swing.JOptionPane.OK_OPTION){
                                if(client != null){
                                    client.downloadFile(serverList.getSelectedValue(), file, Controller.FTPServer.Speed.MEDIUM.getSpeed(), serverList.getSelectedIndex());
                                }
                                continueloop = false;
                                String fileName = serverList.getSelectedValue();
                                int index = serverList.getSelectedIndex();
                                serverDLM.removeElementAt(index);
                                serverDLM.add(index, fileName + "  Complete");
                            }
                        }
                        else{
                            if(client != null){
                                client.downloadFile(serverList.getSelectedValue(), file, Controller.FTPServer.Speed.MEDIUM.getSpeed(), serverList.getSelectedIndex());
                            }
                            continueloop = false;
                            String fileName = serverList.getSelectedValue();
                            int index = serverList.getSelectedIndex();
                            serverDLM.removeElementAt(index);
                            serverDLM.add(index, fileName + "  Complete");
                        }
                    }
                    else{
                        java.lang.System.out.println("Open command cancelled by user");
                        break;
                    }
                    
                } while (continueloop);
                
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, "Please select some files to download", "", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == fastDownload){
            if(serverList.getSelectedValue() != null){
                boolean continueloop = true;
                // Create default  name for file
                do {
                    fc.setSelectedFile(new java.io.File(serverList.getSelectedValue()));
                    int returnValue = fc.showSaveDialog(this);
                    if(returnValue == javax.swing.JFileChooser.APPROVE_OPTION){
                        java.lang.System.out.println("Slow download");
                        java.io.File file = fc.getSelectedFile();
                       
                        if(file.exists()){
                            int choice = javax.swing.JOptionPane.showConfirmDialog(this, "This file already exist. Do you want to replace it?", "Warning", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                            if(choice == javax.swing.JOptionPane.OK_OPTION){
                                if(client != null){
                                    client.downloadFile(serverList.getSelectedValue(), file, Controller.FTPServer.Speed.FAST.getSpeed(), serverList.getSelectedIndex());
                                }
                                
                                continueloop = false;
                                String fileName = serverList.getSelectedValue();
                                int index = serverList.getSelectedIndex();
                                serverDLM.removeElementAt(index);
                                serverDLM.add(index, fileName + "  Complete");
                            }
                        }
                        else{
                            
                            if(client != null){
                                client.downloadFile(serverList.getSelectedValue(), file, Controller.FTPServer.Speed.FAST.getSpeed(), serverList.getSelectedIndex());
                            }
                            
                            continueloop = false;
                            String fileName = serverList.getSelectedValue();
                            int index = serverList.getSelectedIndex();
                            serverDLM.removeElementAt(index);
                            serverDLM.add(index, fileName + "  Complete");
                        }
                    }
                    else{
                        java.lang.System.out.println("Open command cancelled by user");
                        break;
                    }
                    
                } while (continueloop);
                
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, "Please select some files to download", "", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}
