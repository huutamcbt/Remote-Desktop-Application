package Views;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;


import javax.swing.JPanel;

import Models.RemoteDesktop;

public class RemoteDesktopPanel extends JPanel implements java.awt.event.ActionListener, java.awt.event.KeyListener, java.awt.event.MouseListener{
    private RemoteDesktop remoteDesktop;
    public RemoteDesktopPanel(){
        remoteDesktop = null;
        remoteDesktop = new RemoteDesktop();
        remoteDesktop.rootPanel = new JPanel();
        remoteDesktop.panel = new javax.swing.JPanel();
        remoteDesktop.frequentlyQuestion = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("/Images/help.png")));
        remoteDesktop.remotePanel = new javax.swing.JPanel();
        remoteDesktop.gc = new java.awt.GridBagConstraints();
        remoteDesktop.allowControlPassword = new javax.swing.JTextField();
        remoteDesktop.allowControlID = new javax.swing.JTextField();
        remoteDesktop.controlComputerConnection = new javax.swing.JButton();
        remoteDesktop.controlComputerPassword = new javax.swing.JTextField();
        remoteDesktop.controlComputerID = new javax.swing.JTextField();
        remoteDesktop.firstCheckBox = new javax.swing.JCheckBox();
        remoteDesktop.secondCheckBox = new javax.swing.JCheckBox();
        remoteDesktop.thirdCheckBox = new javax.swing.JCheckBox();
        remoteDesktop.customPassword = new javax.swing.JTextField();

        initHomePanel();
    }
    public JPanel getRemoteDesktopPanel(){
        return remoteDesktop.rootPanel;
    }
    private void initHomePanel(){
        try {
            generateAllowControlID();
        } catch (Exception e) {
            java.lang.System.out.println(e);
        }
        generateAllowControlPassword();
        
        remoteDesktop.rootPanel.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth, MainFrame.height - MainFrame.footerHeight));
        remoteDesktop.rootPanel.setLayout(new java.awt.FlowLayout());

        remoteDesktop.gc = new java.awt.GridBagConstraints();
        javax.swing.border.Border border1 = javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1,  new java.awt.Color(189, 186, 191 ));
        remoteDesktop.remotePanel.setLayout(new java.awt.GridBagLayout()); 
        remoteDesktop.frequentlyQuestion.setPreferredSize(new java.awt.Dimension(35,35));
        remoteDesktop.frequentlyQuestion.setBackground(new java.awt.Color(242, 245, 251));
        remoteDesktop.frequentlyQuestion.addMouseListener(new java.awt.event.MouseAdapter(){
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
        remoteDesktop.allowControlID.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        remoteDesktop.allowControlPassword.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        remoteDesktop.controlComputerID.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        remoteDesktop.controlComputerPassword.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        
        
        remoteDesktop.panel.setBorder(border1);
        remoteDesktop.panel.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth, 35));
        remoteDesktop.panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,10,0));
        remoteDesktop.panel.add(remoteDesktop.frequentlyQuestion,java.awt.BorderLayout.LINE_END);

        remoteDesktop.rootPanel.add(remoteDesktop.panel);
        
        // Create allowing control panel
        remoteDesktop.label = new javax.swing.JLabel();
        remoteDesktop.label.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth/2 -90,40));
        remoteDesktop.label.setIcon(new javax.swing.ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/satellite_vista.png")).getImage().getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH)));
        remoteDesktop.label.setText("Allow Remote Control");
        remoteDesktop.label.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        remoteDesktop.label.setBorder(border1);
        remoteDesktop.label.setIconTextGap(20);
        
        remoteDesktop.gc.fill = java.awt.GridBagConstraints.HORIZONTAL;  
        remoteDesktop.gc.insets = new java.awt.Insets(15,50,15,50);
        remoteDesktop.gc.gridwidth = 2;
        remoteDesktop.gc.gridx = 0;
        remoteDesktop.gc.gridy = 0;
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        // Create controlling computer panel
        remoteDesktop.label = new javax.swing.JLabel();
        remoteDesktop.label.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth/2 -90,40));
        remoteDesktop.label.setIcon(new javax.swing.ImageIcon(new javax.swing.ImageIcon( getClass().getResource("/Images/profile.png")).getImage().getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH)));
        remoteDesktop.label.setText("Control a Remote Computer");
        remoteDesktop.label.setFont(new java.awt.Font("",java.awt.Font.BOLD, 15));
        remoteDesktop.label.setBorder(border1);
        remoteDesktop.label.setIconTextGap(20);

        remoteDesktop.gc.gridx = 3;
        remoteDesktop.gc.gridy = 0;
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        // Create the first description
        remoteDesktop.label = new javax.swing.JLabel("<html>Please tell your partner the following ID and Password if you would like to allow remote control</html>");
        remoteDesktop.label.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth/2 -90,40));

        remoteDesktop.gc.gridx = 0;
        remoteDesktop.gc.gridy = 1;
        remoteDesktop.gc.insets = new java.awt.Insets(15,50,50,50);
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        // Create the vertical separator
        remoteDesktop.sep = new javax.swing.JSeparator();
        remoteDesktop.sep.setOrientation(javax.swing.SwingConstants.VERTICAL);
        remoteDesktop.sep.setPreferredSize(new java.awt.Dimension(2,200));
        remoteDesktop.sep.setBackground(new java.awt.Color(189, 186, 191 ));

        remoteDesktop.gc.gridx = 2;
        remoteDesktop.gc.gridy = 1;
        remoteDesktop.gc.gridheight = 3;
        remoteDesktop.gc.insets = new java.awt.Insets(0,0,0,0);
        remoteDesktop.remotePanel.add(remoteDesktop.sep, remoteDesktop.gc);

        remoteDesktop.gc.gridheight = 1;
        // Create the second description
        remoteDesktop.label = new javax.swing.JLabel("<html>Please enter your partner's ID to remote control your partner's computer</html>");
        remoteDesktop.label.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth/2 -90,40));

        remoteDesktop.gc.gridx = 3;
        remoteDesktop.gc.gridy = 1;
        
       
        remoteDesktop.gc.insets = new java.awt.Insets(15,50,50,50);
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        remoteDesktop.gc.gridwidth = 1;
        
        // Create the server ID
        remoteDesktop.label = new javax.swing.JLabel("Your ID");
        
        remoteDesktop.gc.gridx = 0;
        remoteDesktop.gc.gridy = 2;
        remoteDesktop.gc.insets = new java.awt.Insets(0,50,0,0);
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        remoteDesktop.gc.gridx = 1;
        remoteDesktop.gc.gridy = 2;
        remoteDesktop.gc.ipady =10;
        remoteDesktop.gc.insets = new java.awt.Insets(0,10,0,50);
        remoteDesktop.remotePanel.add(remoteDesktop.allowControlID, remoteDesktop.gc);

        // Create the server Password
        remoteDesktop.label = new javax.swing.JLabel("Password");
        //gc.anchor = java.awt.GridBagConstraints.PAGE_START;
        remoteDesktop.gc.gridx = 0;
        remoteDesktop.gc.gridy = 3;
        remoteDesktop.gc.insets = new java.awt.Insets(0,50,0,0);
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        
      
        remoteDesktop.gc.gridx = 1;
        remoteDesktop.gc.gridy = 3;
       
        remoteDesktop.gc.insets = new java.awt.Insets(0,10,0,50);
        remoteDesktop.remotePanel.add(remoteDesktop.allowControlPassword, remoteDesktop.gc);

        // Create the client ID
        remoteDesktop.label = new javax.swing.JLabel("Partner ID");

        remoteDesktop.gc.gridx = 3;
        remoteDesktop.gc.gridy = 2;
       
        remoteDesktop.gc.insets = new java.awt.Insets(5,50,5,0);
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        remoteDesktop.controlComputerID.addKeyListener(this);
      
        remoteDesktop.gc.gridx = 4;
        remoteDesktop.gc.gridy = 2;
        
        remoteDesktop.gc.insets = new java.awt.Insets(5,10,5,50);
        remoteDesktop.remotePanel.add(remoteDesktop.controlComputerID, remoteDesktop.gc);

        // Create the client Password
        remoteDesktop.label = new javax.swing.JLabel("Password");

        remoteDesktop.gc.gridx = 3;
        remoteDesktop.gc.gridy = 3;
        remoteDesktop.gc.insets = new java.awt.Insets(5,50,5,0);
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        remoteDesktop.controlComputerPassword.addKeyListener(this);
      
        remoteDesktop.gc.gridx = 4;
        remoteDesktop.gc.gridy = 3;
        
        remoteDesktop.gc.insets = new java.awt.Insets(5,10,5,50);
        remoteDesktop.remotePanel.add(remoteDesktop.controlComputerPassword, remoteDesktop.gc);

        // Create the horizontal separator
        remoteDesktop.sep = new javax.swing.JSeparator();
        remoteDesktop.sep.setOrientation(javax.swing.SwingConstants.HORIZONTAL);
        remoteDesktop.sep.setBackground(new java.awt.Color(189, 186, 191 ));

        remoteDesktop.gc.gridx = 0;
        remoteDesktop.gc.gridy = 4;
        remoteDesktop.gc.gridwidth = 2;
        remoteDesktop.gc.ipady = 0;
        remoteDesktop.gc.insets = new java.awt.Insets(25,50,0,50);
        remoteDesktop.remotePanel.add(remoteDesktop.sep, remoteDesktop.gc);

        // Create the connection button
        
        remoteDesktop.controlComputerConnection.setText("<html>Connect to partner</html>");
        remoteDesktop.controlComputerConnection.setIcon(new javax.swing.ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/forward.png")).getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
        remoteDesktop.controlComputerConnection.setIconTextGap(20);
        remoteDesktop.controlComputerConnection.setFocusPainted(false);
        remoteDesktop.controlComputerConnection.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                if(remoteDesktop.controlComputerConnection.isEnabled() == true){
                    java.lang.System.out.println(getControlComputerID());
                    java.lang.System.out.println(getControlComputerPassword());
                    new Services.RDP.Client.Client(getControlComputerID(), getControlComputerPassword());
                }
            }
        });
        remoteDesktop.controlComputerConnection.setEnabled(false);

        remoteDesktop.gc.gridwidth = 2;
        remoteDesktop.gc.gridx = 3;
        remoteDesktop.gc.gridy = 4;
        remoteDesktop.gc.ipadx = 50;
        remoteDesktop.gc.ipady = 10;
        remoteDesktop.gc.fill = java.awt.GridBagConstraints.NONE;
        remoteDesktop.remotePanel.add(remoteDesktop.controlComputerConnection, remoteDesktop.gc);
        
        // Create unattended access panel
        remoteDesktop.label = new javax.swing.JLabel("Unattended access");
        remoteDesktop.label.setFont(new java.awt.Font("",java.awt.Font.BOLD,15));
        remoteDesktop.label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        remoteDesktop.gc.gridx = 0;
        remoteDesktop.gc.gridy = 5;
        remoteDesktop.gc.gridwidth = 2;
        remoteDesktop.gc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        remoteDesktop.gc.insets = new java.awt.Insets(30,50,30,0);
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        remoteDesktop.gc.gridx = 0;
        remoteDesktop.gc.gridy = 6;
        remoteDesktop.gc.ipadx = 0;
        remoteDesktop.gc.ipady = 0;
        remoteDesktop.gc.gridwidth = 1;
        remoteDesktop.gc.insets = new java.awt.Insets(10,50,10,0);
        remoteDesktop.remotePanel.add(remoteDesktop.firstCheckBox, remoteDesktop.gc);

        remoteDesktop.gc.gridx = 0;
        remoteDesktop.gc.gridy = 7;
        remoteDesktop.gc.gridwidth = 1;
        remoteDesktop.remotePanel.add(remoteDesktop.secondCheckBox, remoteDesktop.gc);

        remoteDesktop.gc.gridx = 0;
        remoteDesktop.gc.gridy = 8;
        remoteDesktop.gc.gridwidth = 1;
        remoteDesktop.remotePanel.add(remoteDesktop.thirdCheckBox, remoteDesktop.gc);

        remoteDesktop.label = new javax.swing.JLabel("<html>Run the application with Windows</html>");

        remoteDesktop.gc.gridx = 1;
        remoteDesktop.gc.gridy = 6;
        remoteDesktop.gc.gridwidth = 1;
        remoteDesktop.gc.insets = new java.awt.Insets(0,0,0,0);
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        remoteDesktop.label = new javax.swing.JLabel("<html>Prevent Windows from going to sleep</html>");

        remoteDesktop.gc.gridx = 1;
        remoteDesktop.gc.gridy = 7;
        remoteDesktop.gc.gridwidth = 1;
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        remoteDesktop.label = new javax.swing.JLabel("<html>Allow turn on computer remotely</html>");

        remoteDesktop.gc.gridx = 1;
        remoteDesktop.gc.gridy = 8;
        remoteDesktop.gc.gridwidth = 1;
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        // Create the custom password field
        remoteDesktop.label = new javax.swing.JLabel("<html>Custom <br> Password</html>");

        remoteDesktop.gc.gridx = 0;
        remoteDesktop.gc.gridy = 9;
        remoteDesktop.gc.insets = new java.awt.Insets(10,50,0,0);
        remoteDesktop.remotePanel.add(remoteDesktop.label, remoteDesktop.gc);

        remoteDesktop.gc.gridx = 1;
        remoteDesktop.gc.gridy = 9;
        remoteDesktop.gc.ipady = 10;
        remoteDesktop.gc.insets = new java.awt.Insets(10,10,0,50);
        
        remoteDesktop.remotePanel.add(remoteDesktop.customPassword, remoteDesktop.gc);
        
        remoteDesktop.rootPanel.add(remoteDesktop.remotePanel);
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    private void generateAllowControlID() throws java.net.UnknownHostException{
        remoteDesktop.allowControlID.setText(Services.RDP.Server.Server.getIpAddress());
       
    }

    private void generateAllowControlPassword(){
        remoteDesktop.allowControlPassword.setText(Services.RDP.Server.Server.getPassword()); 
    }


    private String getControlComputerID(){
        return remoteDesktop.controlComputerID.getText();
    }
    private String getControlComputerPassword(){
        return remoteDesktop.controlComputerPassword.getText();
    }

    public int getAllowControlPassword(){
        return Integer.parseInt(remoteDesktop.allowControlPassword.getText());
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
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

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == remoteDesktop.controlComputerID){
            if(java.util.Objects.equals(remoteDesktop.controlComputerID.getText(), "") == false){
                if(java.util.Objects.equals(remoteDesktop.controlComputerPassword.getText(), "") == false){
                    remoteDesktop.controlComputerConnection.setEnabled(true);
                }
            }
            else{
                remoteDesktop.controlComputerConnection.setEnabled(false);
            }
        }
        if(e.getSource() == remoteDesktop.controlComputerPassword){
            if(java.util.Objects.equals(remoteDesktop.controlComputerPassword.getText(), "") == false){
                if(java.util.Objects.equals(remoteDesktop.controlComputerID.getText(), "") == false){
                    remoteDesktop.controlComputerConnection.setEnabled(true);
                }
            }
            else{
                remoteDesktop.controlComputerConnection.setEnabled(false);
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
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
