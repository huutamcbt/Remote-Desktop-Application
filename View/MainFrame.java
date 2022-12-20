package View;

public class MainFrame  extends javax.swing.JFrame{
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuSettings;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel rightPanel;
    public static javax.swing.JPanel mainPanel;
    private javax.swing.JPanel footer;
    private javax.swing.JLabel home;
    private javax.swing.JLabel onlineContact; 
    private javax.swing.JLabel ftpServer; 
    private boolean homeChoice = true;
    private boolean onlineContactChoice = false;
    private boolean ftpServerChoice = false;
    private boolean lock = true;
    public static int leftPnWidth = 90;
    public static int rightPnWidth = 810;
    private int height = 700;
    private int footerHeight = 30;
    private javax.swing.JLabel status;
    private javax.swing.JLabel copyright;
    public static MainPanel Panel;

    public MainFrame(){
        Controller.RDP.Server.Server server = new Controller.RDP.Server.Server(); 
        java.lang.Thread thread = new java.lang.Thread(server);
        thread.setDaemon(true);
        thread.start();

        initComponent();
        
        InitEvent();
        updateStatus();
    }

    private void InitEvent(){
        this.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent e){
                Object message = "Do you want to quit the program?";
                int choice = javax.swing.JOptionPane.showConfirmDialog(MainFrame.this, message, "Warning", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                if(choice==javax.swing.JOptionPane.OK_OPTION){
                    MainFrame.this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                    try {
                        Controller.FTPServer.Server.Start.stopServer();
                    } catch (Exception ex) {
                      
                    } 
                }
                else{
                    MainFrame.this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    private void updateStatus(){
        try {
            if(Controller.RDP.Server.Server.running()){
                status.setIcon(new javax.swing.ImageIcon("Images/16_circle_green.png"));
                status.setText("Ready to connect");
                status.setIconTextGap(20);
                footer.add(status,java.awt.BorderLayout.LINE_START);
                footer.add(copyright,java.awt.BorderLayout.LINE_END);
            }else{
                status.setIcon(new javax.swing.ImageIcon("Images/16_circle_red.png"));
                status.setText("Can't find the IP address");
                status.setIconTextGap(20);
                footer.add(status,java.awt.BorderLayout.LINE_START);
                footer.add(copyright,java.awt.BorderLayout.LINE_END);
            }
        } catch (Exception e) {
            java.lang.System.out.println(e);
        }
    }

    private void initComponent(){
        leftPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        footer = new javax.swing.JPanel();
        status = new javax.swing.JLabel();
        copyright = new javax.swing.JLabel("Copyright huutamcbt Company  ");
        Panel = new MainPanel();

        initMenuBar();
        
        home = new javax.swing.JLabel((new javax.swing.ImageIcon("Images/house_3.png"))); 
        onlineContact = new javax.swing.JLabel((new javax.swing.ImageIcon("Images/gnome_x_office_address_book.png"))); 
        ftpServer = new javax.swing.JLabel(new javax.swing.ImageIcon("Images/ftp-server.png"));
        javax.swing.border.Border leftPanelBorder = javax.swing.BorderFactory.createMatteBorder(0,0,0,1,new java.awt.Color(189, 186, 191 )); 
        java.awt.FlowLayout leftPanelLayout = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER);
        Panel.initHomePanel();
        
        leftPanel.setBackground(new java.awt.Color(235, 227, 251 ));
        //rightPanel.setBackground(new java.awt.Color(242, 245, 251));
        footer.setBackground(new java.awt.Color(239, 239, 239));

        // Create components for the left panel
        leftPanel.setPreferredSize(new java.awt.Dimension(leftPnWidth, height));
        
        // home panel set up some properties
        home.setPreferredSize(new java.awt.Dimension(leftPnWidth-1, leftPnWidth-1));
        home.setOpaque(true);   // Set up this property to color a background
        //home.setBackground(new java.awt.Color(125, 161, 239));
        home.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                home.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
                if(homeChoice == false){
                    home.setBackground(new java.awt.Color(251, 248, 248));
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                if(homeChoice == false){
                    home.setBackground(new java.awt.Color(235, 227, 251 ));
                }
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                if(e.getButton() == java.awt.event.MouseEvent.BUTTON1){
                    if(homeChoice == false){
                        int choice = javax.swing.JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to switch to Home Panel?", "Warning",
                             javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                        if(choice == javax.swing.JOptionPane.OK_OPTION){
                            if(ftpServerChoice == true){
                                Controller.FTPServer.Server.Start.stopServer();
                            }
                            homeChoice = true;
                            onlineContactChoice = false;
                            ftpServerChoice = false;
                            onlineContact.setBackground(new java.awt.Color(235, 227, 251 ));
                            ftpServer.setBackground(new java.awt.Color(235, 227, 251 ));
                            home.setBackground(new java.awt.Color(125, 161, 239));
                        
                            mainPanel.removeAll();
                            java.lang.System.gc();
                            Panel = new MainPanel();
                            Panel.initHomePanel(); 
                            mainPanel.setPreferredSize(new java.awt.Dimension(rightPnWidth, height - footerHeight));
                            mainPanel.revalidate();
                            mainPanel.repaint();
                           
                        }
                    }
                }
            }
        });
        
        // online Contact panel set up some properties
        onlineContact.setPreferredSize(new java.awt.Dimension(leftPnWidth - 1, leftPnWidth -1));
        onlineContact.setOpaque(true);
        onlineContact.setBackground(new java.awt.Color(235, 227, 251 ));
        onlineContact.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                onlineContact.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
                if(onlineContactChoice == false){
                    onlineContact.setBackground(new java.awt.Color(251, 248, 248));
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                if(onlineContactChoice == false){
                    onlineContact.setBackground(new java.awt.Color(235, 227, 251 ));
                }
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                if(e.getButton() == java.awt.event.MouseEvent.BUTTON1){
                    if(onlineContactChoice == false){
                        int choice = javax.swing.JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to switch to Online Contact Panel?", "Warning",
                             javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                        if(choice == javax.swing.JOptionPane.OK_OPTION){
                            if(ftpServerChoice == true){
                                Controller.FTPServer.Server.Start.stopServer();
                            }
                            onlineContactChoice = true;
                            homeChoice = false;
                            ftpServerChoice = false;
                            home.setBackground(new java.awt.Color(235, 227, 251 ));
                            ftpServer.setBackground(new java.awt.Color(235, 227, 251 ));
                            onlineContact.setBackground(new java.awt.Color(125, 161, 239));
                            mainPanel.removeAll();
                            java.lang.System.gc();
                            Panel = new MainPanel();
                            Panel.initOnlineContact();  
                            mainPanel.setPreferredSize(new java.awt.Dimension(rightPnWidth, height - footerHeight));
                            mainPanel.revalidate();
                            mainPanel.repaint();
                        }
                    }
                }
            }
        });

        // Set up some propertys for ftp server panel
        ftpServer.setPreferredSize(new java.awt.Dimension(leftPnWidth - 1, leftPnWidth -1));
        ftpServer.setOpaque(true);
        ftpServer.setBackground(new java.awt.Color(235, 227, 251 ));
        ftpServer.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                ftpServer.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
                if(ftpServerChoice == false){
                    ftpServer.setBackground(new java.awt.Color(251, 248, 248));
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                if(ftpServerChoice == false){
                    ftpServer.setBackground(new java.awt.Color(235, 227, 251 ));
                }
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                if(e.getButton() == java.awt.event.MouseEvent.BUTTON1){
                    if(ftpServerChoice == false){
                        int choice = javax.swing.JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to switch to FTP Server Panel?", "Warning",
                             javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                        if(choice == javax.swing.JOptionPane.OK_OPTION){
                            ftpServerChoice = true;
                            onlineContactChoice = false;
                            homeChoice = false;
                            home.setBackground(new java.awt.Color(235, 227, 251 ));
                            onlineContact.setBackground(new java.awt.Color(235, 227, 251 ));
                            ftpServer.setBackground(new java.awt.Color(125, 161, 239));
                            mainPanel.removeAll();
                            java.lang.System.gc();
                            Panel = new MainPanel();
                            Panel.initFTPServerPanel(); 
                            mainPanel.setPreferredSize(new java.awt.Dimension(rightPnWidth, height - footerHeight));
                            mainPanel.revalidate();
                            mainPanel.repaint();
                        }
                    }
                }
            }
        });
        
        // Set up left panel Layout and border of left panel
        leftPanelLayout.setHgap(0);
        leftPanelLayout.setVgap(0);
        leftPanel.setBorder(leftPanelBorder);
        leftPanel.setLayout(leftPanelLayout);
        
        // Add components tp left panel
        leftPanel.add(home);
        leftPanel.add(onlineContact);
        leftPanel.add(ftpServer);
        
        // Create components for the right panel
        javax.swing.border.Border footerBorder = javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(189, 186, 191 ));
        rightPanel.setPreferredSize(new java.awt.Dimension(rightPnWidth, height));
        mainPanel.setPreferredSize(new java.awt.Dimension(rightPnWidth, height - footerHeight));
        footer.setPreferredSize(new java.awt.Dimension(rightPanel.getWidth(), footerHeight));
        footer.setBorder(footerBorder);
        footer.setLayout(new java.awt.BorderLayout());
    
        rightPanel.setLayout(new java.awt.BorderLayout());
        rightPanel.add(mainPanel, java.awt.BorderLayout.NORTH);
        rightPanel.add(footer, java.awt.BorderLayout.SOUTH);
        
        // Set up some properties of MainFrame window
        this.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowOpened(java.awt.event.WindowEvent e){
                java.lang.System.out.println("Opened");
                lock = false;
                home.setBackground(new java.awt.Color(125, 161, 239));
            }
            @Override
            public void windowActivated(java.awt.event.WindowEvent e) {
                System.out.println("Window activate");
                if(lock == false){
                    if(homeChoice == true ){
                        home.setBackground(new java.awt.Color(125, 161, 239));
                    }
                    if(onlineContactChoice == true){
                        onlineContact.setBackground(new java.awt.Color(125, 161, 239));
                    }
                    if(ftpServerChoice == true){
                        ftpServer.setBackground(new java.awt.Color(125, 161, 239));
                    }
                }
            }
            @Override
            public void windowDeactivated(java.awt.event.WindowEvent e) {
                System.out.println("Window deactivate");
                // home.setBackground(new java.awt.Color(235, 227, 251 ));
                // onlineContact.setBackground(new java.awt.Color(235, 227, 251 ));  
                // ftpServer.setBackground(new java.awt.Color(235, 227, 251 ));             
            }

            public void windowStateChanged(java.awt.event.WindowEvent e) {
               
            }

            public void windowGainedFocus(java.awt.event.WindowEvent e) {
            
            }

            public void windowLostFocus(java.awt.event.WindowEvent e) {
               
            }
        });

        this.setLayout(new java.awt.BorderLayout());
        this.setJMenuBar(menuBar);
        this.add(leftPanel, java.awt.BorderLayout.WEST);
        this.add(rightPanel, java.awt.BorderLayout.CENTER); 
    
        this.setTitle("New Frame");
        //this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void initMenuBar(){
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu("File");
        menuSettings = new javax.swing.JMenu("Settings");
        menuHelp = new javax.swing.JMenu("Help");

        menuFile.setMnemonic(java.awt.event.KeyEvent.VK_F);
   
        // Create menu items for menu bar
        menuBar.add(menuFile);
        menuBar.add(menuSettings);
        menuBar.add(menuHelp);
        
        // Create Menu Items of File Menu
        javax.swing.JMenuItem miViewLogs = new javax.swing.JMenuItem("View logs");
        javax.swing.JMenuItem miCloseRemoteDesktop = new javax.swing.JMenuItem("Close RemoteDesktop");
        javax.swing.JMenuItem miRestartUltraViewer = new javax.swing.JMenuItem("Restart UltraViewer");
        javax.swing.JMenuItem miExit = new javax.swing.JMenuItem("Exit");

        javax.swing.JSeparator sep1 = new javax.swing.JSeparator();

        miViewLogs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        miViewLogs.getAccessibleContext().setAccessibleDescription("Alt + V");

        menuFile.add(miViewLogs);
        menuFile.add(sep1);
        menuFile.add(miCloseRemoteDesktop);
        menuFile.add(miRestartUltraViewer);
        menuFile.add(miExit);

        // Create Menu Items of Settings Menu
        javax.swing.JMenuItem miOption = new javax.swing.JMenuItem("Option");
        javax.swing.JMenu miLanguage = new javax.swing.JMenu("Language");
        javax.swing.JMenuItem miVietnamese = new javax.swing.JMenuItem("Vietnamese");
        javax.swing.JMenuItem miEnglish = new javax.swing.JMenuItem("English");

        miLanguage.add(miVietnamese);
        miLanguage.add(miEnglish);

        menuSettings.add(miOption);
        menuSettings.add(miLanguage);

        // Create Menu Items for Help Menu
        javax.swing.JMenuItem miActiveLicense = new javax.swing.JMenuItem("ActiveLicense");
        javax.swing.JMenuItem miShowBannerPreview = new javax.swing.JMenuItem("Show Banner Preview");
        javax.swing.JMenuItem miUltraViewerWebsite = new javax.swing.JMenuItem("UltraViewer website");
        javax.swing.JMenuItem miCheckForNewVersion = new javax.swing.JMenuItem("Check for new version");
        javax.swing.JMenuItem miAbout = new javax.swing.JMenuItem("About");

        menuHelp.add(miActiveLicense);
        menuHelp.addSeparator();
        menuHelp.add(miShowBannerPreview);
        menuHelp.add(miUltraViewerWebsite);
        menuHelp.add(miCheckForNewVersion);
        menuHelp.add(miAbout);
    }
}
