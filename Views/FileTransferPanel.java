package Views;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Models.FileTransfer;

public class FileTransferPanel implements KeyListener, MouseListener {
    private static FileTransfer fileTransfer;
    public FileTransferPanel(){
        fileTransfer = null;
         fileTransfer = new FileTransfer();
        fileTransfer.rootPanel = new JPanel();
        fileTransfer.partnerID = new javax.swing.JTextField();
        fileTransfer.partnerPassword = new javax.swing.JTextField();
        fileTransfer.connection = new javax.swing.JButton("<html>Connect to partner<html>");
        fileTransfer.clientFiles = new java.io.File[20];
        fileTransfer.serverFiles = new java.io.File[20];
        fileTransfer.clientDLM = new javax.swing.DefaultListModel<String>();
        fileTransfer.serverDLM = new javax.swing.DefaultListModel<String>();
        fileTransfer.clientList = new javax.swing.JList<String>();
        fileTransfer.serverList = new javax.swing.JList<String>();
        fileTransfer.clientSP = new javax.swing.JScrollPane(fileTransfer.clientList);
        fileTransfer.serverSP = new javax.swing.JScrollPane(fileTransfer.serverList);
        fileTransfer.choiceButton = new javax.swing.JButton("Open a file");
        fileTransfer.removeButton = new javax.swing.JButton("Remove these files");
        fileTransfer.slowDownload = new javax.swing.JButton("Slow download");
        fileTransfer.mediumDownload = new javax.swing.JButton("Medium download");
        fileTransfer.fastDownload = new javax.swing.JButton("Fast download");
        fileTransfer.fc = new javax.swing.JFileChooser();

        initFTPServerPanel();
    }

    public JPanel GetFileTransferPanel(){
        return fileTransfer.rootPanel;
    }

    private void initFTPServerPanel(){
        //fileTransfer.rootPanel.setPreferredSize(new java.awt.Dimension(MainFrame.rightPnWidth, MainFrame.height - MainFrame.footerHeight));
        new Services.FTPServer.Server.Start(fileTransfer.clientFiles, fileTransfer.clientList, fileTransfer.serverList, fileTransfer.serverDLM);
       
        fileTransfer.rootPanel.setLayout(new java.awt.GridBagLayout());
        fileTransfer.rootPanel.setPreferredSize(new java.awt.Dimension(800, 700));
        fileTransfer.gc = new java.awt.GridBagConstraints();
        
        // Client ID Label
        fileTransfer.label = new javax.swing.JLabel("Your ID:   " + Services.FTPServer.Server.Start.getIpAddress());
        fileTransfer.label.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));
        fileTransfer.label.setPreferredSize(new java.awt.Dimension(270,20));

        fileTransfer.gc.gridx = 0;
        fileTransfer.gc.gridy = 0;
        fileTransfer.gc.gridwidth = 2;
        fileTransfer.gc.ipady = 5;
        fileTransfer.gc.insets = new java.awt.Insets(10, 10, 10, 10);
        fileTransfer.gc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        fileTransfer.rootPanel.add(fileTransfer.label, fileTransfer.gc);

        // Client Password label
        fileTransfer.label = new javax.swing.JLabel("Your Password:   " + Services.FTPServer.Server.Start.getPassword());
        fileTransfer.label.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));
        fileTransfer.label.setPreferredSize(new java.awt.Dimension(270,20));

        fileTransfer.gc.gridx = 2;
        fileTransfer.gc.gridy = 0;
        fileTransfer.gc.gridwidth = 2;
        fileTransfer.gc.ipady = 5;
        fileTransfer.gc.insets = new java.awt.Insets(10, 10, 10, 10);
        fileTransfer.gc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        fileTransfer.rootPanel.add(fileTransfer.label, fileTransfer.gc);

        // Partner ID label
        fileTransfer.label = new javax.swing.JLabel("Partner ID");
        fileTransfer.label.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));

        fileTransfer.gc.gridx = 0;
        fileTransfer.gc.gridy = 1;
        fileTransfer.gc.gridwidth = 1;
        fileTransfer.gc.ipady = 5;
        fileTransfer.gc.insets = new java.awt.Insets(10, 10, 10, 10);
        fileTransfer.gc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        fileTransfer.rootPanel.add(fileTransfer.label, fileTransfer.gc);

        // Partner ID textfield
        fileTransfer.partnerID.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));
        
        fileTransfer.gc.gridx = 1;
        fileTransfer.gc.gridy = 1;
        fileTransfer.gc.gridwidth = 1;
        fileTransfer.rootPanel.add(fileTransfer.partnerID, fileTransfer.gc);

        // Partner Password label
        fileTransfer.label = new javax.swing.JLabel("Partner Password");
        fileTransfer.label.setFont(new java.awt.Font(null, java.awt.Font.BOLD, 15));

        fileTransfer.gc.gridx = 2;
        fileTransfer.gc.gridy = 1;
        fileTransfer.gc.gridwidth = 1;
        fileTransfer.rootPanel.add(fileTransfer.label, fileTransfer.gc);

        // Partner Password textfield
        fileTransfer.partnerPassword.setFont(new java.awt.Font("", java.awt.Font.BOLD, 15));
        
        fileTransfer.gc.gridx = 3;
        fileTransfer.gc.gridy = 1;
        fileTransfer.gc.gridwidth = 1;
        fileTransfer.rootPanel.add(fileTransfer.partnerPassword, fileTransfer.gc);

        fileTransfer.gc.ipadx = 0;
        fileTransfer.gc.ipady = 0;

        // connection button
        fileTransfer.connection.setIcon(new javax.swing.ImageIcon(
            new javax.swing.ImageIcon(getClass().getResource("/Images/forward.png")).getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
        fileTransfer.connection.setFocusPainted(false);
        fileTransfer.connection.setEnabled(false);
        fileTransfer.connection.setIconTextGap(20);

        fileTransfer.gc.gridx = 4;
        fileTransfer.gc.gridy = 1;
        fileTransfer.gc.ipady = 5;
        fileTransfer.gc.gridwidth = 1;
        fileTransfer.rootPanel.add(fileTransfer.connection, fileTransfer.gc);

        // client label
        fileTransfer.label = new javax.swing.JLabel("Your files:");
        fileTransfer.label.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 15));

        fileTransfer.gc.gridx = 0;
        fileTransfer.gc.gridy = 2;
        fileTransfer.gc.gridwidth = 2;
        fileTransfer.gc.ipady = 0;
        fileTransfer.gc.insets = new java.awt.Insets(5, 10, 5, 10);
        fileTransfer.rootPanel.add(fileTransfer.label, fileTransfer.gc);

        // server label
        fileTransfer.label = new javax.swing.JLabel("Partner files:");
        fileTransfer.label.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 15));

        fileTransfer.gc.gridx = 2;
        fileTransfer.gc.gridy = 2;
        fileTransfer.gc.gridwidth = 2;
        fileTransfer.rootPanel.add(fileTransfer.label, fileTransfer.gc);
        
        // client file Jlist
        fileTransfer.clientList.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 15));
        fileTransfer.clientList.setVisibleRowCount(15);
        fileTransfer.clientList.setPreferredSize(new java.awt.Dimension(250, 300));

        fileTransfer.clientSP.setPreferredSize(new java.awt.Dimension(270, 350));
        fileTransfer.gc.gridx = 0;
        fileTransfer.gc.gridy = 3;
        fileTransfer.gc.gridwidth = 2;
        fileTransfer.gc.fill = java.awt.GridBagConstraints.VERTICAL;
        
        fileTransfer.rootPanel.add(fileTransfer.clientSP, fileTransfer.gc);

        // server file JList
        fileTransfer.serverList.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 15));
        fileTransfer.serverList.setVisibleRowCount(15);
        fileTransfer.serverList.setPreferredSize(new java.awt.Dimension(250, 300));

        fileTransfer.serverSP.setPreferredSize(new java.awt.Dimension(270, 350));
        fileTransfer.gc.gridx = 2;
        fileTransfer.gc.gridy = 3;
        fileTransfer.gc.gridwidth =2;
        
        fileTransfer.rootPanel.add(fileTransfer.serverSP, fileTransfer.gc);

        // reset attribute to default value
        fileTransfer.gc.ipadx = 0;
        fileTransfer.gc.ipady = 0;
        fileTransfer.gc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        // choice button
        fileTransfer.choiceButton.setFocusPainted(false);

        fileTransfer.gc.gridx = 0;
        fileTransfer.gc.gridy = 4;
        fileTransfer.gc.ipady = 5;
        fileTransfer.gc.gridwidth = 2;

        fileTransfer.rootPanel.add(fileTransfer.choiceButton, fileTransfer.gc);

        // removeButton
        fileTransfer.removeButton.setFocusPainted(false);

        fileTransfer.gc.gridx = 0;
        fileTransfer.gc.gridy = 5;
        fileTransfer.gc.ipady = 5;
        fileTransfer.gc.gridwidth = 2;

        fileTransfer.rootPanel.add(fileTransfer.removeButton, fileTransfer.gc);


        // slow download button
        fileTransfer.slowDownload.setFocusPainted(false);

        fileTransfer.gc.gridx = 2;
        fileTransfer.gc.gridy = 4;
        fileTransfer.gc.gridwidth = 2;
        fileTransfer.rootPanel.add(fileTransfer.slowDownload, fileTransfer.gc);

        // medium download button
        fileTransfer.mediumDownload.setFocusPainted(false);

        fileTransfer.gc.gridx = 2;
        fileTransfer.gc.gridy = 5;
        fileTransfer.gc.gridwidth = 2;
        fileTransfer.rootPanel.add(fileTransfer.mediumDownload, fileTransfer.gc);

        // fast download button
        fileTransfer.fastDownload.setFocusPainted(false);

        fileTransfer.gc.gridx = 2;
        fileTransfer.gc.gridy = 6;
        fileTransfer.gc.gridwidth = 2;
        fileTransfer.rootPanel.add(fileTransfer.fastDownload, fileTransfer.gc);

        fileTransfer.gc.ipadx = 0;
        fileTransfer.gc.ipady = 0;
        fileTransfer.gc.gridwidth = 1;

        fileTransfer.partnerID.addKeyListener(this);
        fileTransfer.partnerPassword.addKeyListener(this);
        fileTransfer.choiceButton.addMouseListener(this);
        fileTransfer.removeButton.addMouseListener(this);
        fileTransfer.connection.addMouseListener(this);
        fileTransfer.slowDownload.addMouseListener(this);
        fileTransfer.mediumDownload.addMouseListener(this);
        fileTransfer.fastDownload.addMouseListener(this);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static void addFile(String file){
        if(fileTransfer.serverList.getModel().getSize() <= 20){
            fileTransfer.serverFiles[fileTransfer.serverList.getModel().getSize()] = new java.io.File(file);
            fileTransfer.serverDLM.addElement(fileTransfer.serverFiles[fileTransfer.serverList.getModel().getSize()].getName());
            fileTransfer.serverList.setModel(fileTransfer.serverDLM);
        }
    }

    public static void removeFile(int index){
        if(fileTransfer.serverList.getModel().getSize() > 0){
            fileTransfer.serverDLM.removeElementAt(index);
            fileTransfer.serverList.setModel(fileTransfer.serverDLM);

        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void mouseClicked(MouseEvent e) {
         // Choice button clicked event in FTP Server
         if(e.getSource() == fileTransfer.choiceButton){
            fileTransfer.fc.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
            int returnValue = fileTransfer.fc.showOpenDialog(fileTransfer.rootPanel);
            if(returnValue == javax.swing.JFileChooser.APPROVE_OPTION){
                if(fileTransfer.clientList.getModel().getSize() <= 20){
                    //clientCount += 1;
                    fileTransfer.clientFiles[fileTransfer.clientList.getModel().getSize()] = fileTransfer.fc.getSelectedFile();
                    if(fileTransfer.client != null){
                        fileTransfer.client.addClientFile(fileTransfer.clientFiles[fileTransfer.clientList.getModel().getSize()].getName());
                    }
                    
                    fileTransfer.clientDLM.addElement(fileTransfer.clientFiles[fileTransfer.clientList.getModel().getSize()].getName());
                    fileTransfer.clientList.setModel(fileTransfer.clientDLM);
                } 
            }
            else{
                java.lang.System.out.println("Open command cancelled by user");
            }
        }
        // Remove button clicked event in FTP Server
        if(e.getSource() == fileTransfer.removeButton){
            if(fileTransfer.clientList.getSelectedValue() != null){
                if(fileTransfer.clientList.getModel().getSize() > 0){
                    java.util.List<String> items;
                    
                    if(fileTransfer.clientList.getSelectedValuesList().size() > 1){
                        items = fileTransfer.clientList.getSelectedValuesList();
                        for (String item : fileTransfer.clientList.getSelectedValuesList()) {
                            fileTransfer.clientDLM.removeElement(item);
                            System.out.println(item);
                        }

                        fileTransfer.clientList.setModel(fileTransfer.clientDLM);
                    }
                    else{
                       fileTransfer.client.removeClientFile(fileTransfer.clientList.getSelectedIndex());
                    }
                }
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(fileTransfer.rootPanel, "You have no file to remove", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
        // Connection button clicked event in FTP Server
        if(e.getSource() == fileTransfer.connection){
            fileTransfer.client = new Services.FTPServer.Client.Start(fileTransfer.partnerID.getText(), fileTransfer.partnerPassword.getText());
            
            if(fileTransfer.client.verifyConnection(fileTransfer.clientList, fileTransfer.serverList, fileTransfer.serverDLM) == false){
                fileTransfer.client = null;
            }
        }
        if(e.getSource() == fileTransfer.slowDownload){
            if(fileTransfer.serverList.getSelectedValue() != null){
                boolean continueloop = true;
                // Create default  name for file
                do {
                    fileTransfer.fc.setSelectedFile(new java.io.File(fileTransfer.serverList.getSelectedValue()));
                    int returnValue = fileTransfer.fc.showSaveDialog(fileTransfer.rootPanel);
                    if(returnValue == javax.swing.JFileChooser.APPROVE_OPTION){
                        java.lang.System.out.println("Slow download");
                        java.io.File file = fileTransfer.fc.getSelectedFile();
                        if(file.exists()){
                            int choice = javax.swing.JOptionPane.showConfirmDialog(fileTransfer.rootPanel, "This file already exist. Do you want to replace it?", "Warning", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                            if(choice == javax.swing.JOptionPane.OK_OPTION){
                                if(fileTransfer.client != null){
                                    fileTransfer.client.downloadFile(fileTransfer.serverList.getSelectedValue(), file, Services.FTPServer.Speed.SLOW.getSpeed(), fileTransfer.serverList.getSelectedIndex());
                                }
                                continueloop = false;
                                String fileName = fileTransfer.serverList.getSelectedValue();
                                int index = fileTransfer.serverList.getSelectedIndex();
                                fileTransfer.serverDLM.removeElementAt(index);
                                fileTransfer.serverDLM.add(index, fileName + "  Complete");
                            }
                        }
                        else{
                            if(fileTransfer.client != null){
                                fileTransfer.client.downloadFile(fileTransfer.serverList.getSelectedValue(), file, Services.FTPServer.Speed.SLOW.getSpeed(), fileTransfer.serverList.getSelectedIndex());
                            }
                            continueloop = false;
                            String fileName = fileTransfer.serverList.getSelectedValue();
                            int index = fileTransfer.serverList.getSelectedIndex();
                            fileTransfer.serverDLM.removeElementAt(index);
                            fileTransfer.serverDLM.add(index, fileName + "  Complete");
                        }
                    }
                    else{
                        java.lang.System.out.println("Open command cancelled by user");
                        break;
                    }
                    
                } while (continueloop);
                
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(fileTransfer.rootPanel, "Please select some files to download", "", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == fileTransfer.mediumDownload){
            if(fileTransfer.serverList.getSelectedValue() != null){
                boolean continueloop = true;
                // Create default  name for file
                do {
                    fileTransfer.fc.setSelectedFile(new java.io.File(fileTransfer.serverList.getSelectedValue()));
                    int returnValue = fileTransfer.fc.showSaveDialog(fileTransfer.rootPanel);
                    if(returnValue == javax.swing.JFileChooser.APPROVE_OPTION){
                        java.lang.System.out.println("Slow download");
                        java.io.File file = fileTransfer.fc.getSelectedFile();
                        if(file.exists()){
                            int choice = javax.swing.JOptionPane.showConfirmDialog(fileTransfer.rootPanel, "This file already exist. Do you want to replace it?", "Warning", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                            if(choice == javax.swing.JOptionPane.OK_OPTION){
                                if(fileTransfer.client != null){
                                    fileTransfer.client.downloadFile(fileTransfer.serverList.getSelectedValue(), file, Services.FTPServer.Speed.MEDIUM.getSpeed(), fileTransfer.serverList.getSelectedIndex());
                                }
                                continueloop = false;
                                String fileName = fileTransfer.serverList.getSelectedValue();
                                int index = fileTransfer.serverList.getSelectedIndex();
                                fileTransfer.serverDLM.removeElementAt(index);
                                fileTransfer.serverDLM.add(index, fileName + "  Complete");
                            }
                        }
                        else{
                            if(fileTransfer.client != null){
                                fileTransfer.client.downloadFile(fileTransfer.serverList.getSelectedValue(), file, Services.FTPServer.Speed.MEDIUM.getSpeed(), fileTransfer.serverList.getSelectedIndex());
                            }
                            continueloop = false;
                            String fileName = fileTransfer.serverList.getSelectedValue();
                            int index = fileTransfer.serverList.getSelectedIndex();
                            fileTransfer.serverDLM.removeElementAt(index);
                            fileTransfer.serverDLM.add(index, fileName + "  Complete");
                        }
                    }
                    else{
                        java.lang.System.out.println("Open command cancelled by user");
                        break;
                    }
                    
                } while (continueloop);
                
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(fileTransfer.rootPanel, "Please select some files to download", "", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == fileTransfer.fastDownload){
            if(fileTransfer.serverList.getSelectedValue() != null){
                boolean continueloop = true;
                // Create default  name for file
                do {
                    fileTransfer.fc.setSelectedFile(new java.io.File(fileTransfer.serverList.getSelectedValue()));
                    int returnValue = fileTransfer.fc.showSaveDialog(fileTransfer.rootPanel);
                    if(returnValue == javax.swing.JFileChooser.APPROVE_OPTION){
                        java.lang.System.out.println("Slow download");
                        java.io.File file = fileTransfer.fc.getSelectedFile();
                       
                        if(file.exists()){
                            int choice = javax.swing.JOptionPane.showConfirmDialog(fileTransfer.rootPanel, "This file already exist. Do you want to replace it?", "Warning", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                            if(choice == javax.swing.JOptionPane.OK_OPTION){
                                if(fileTransfer.client != null){
                                    fileTransfer.client.downloadFile(fileTransfer.serverList.getSelectedValue(), file, Services.FTPServer.Speed.FAST.getSpeed(), fileTransfer.serverList.getSelectedIndex());
                                }
                                
                                continueloop = false;
                                String fileName = fileTransfer.serverList.getSelectedValue();
                                int index = fileTransfer.serverList.getSelectedIndex();
                                fileTransfer.serverDLM.removeElementAt(index);
                                fileTransfer.serverDLM.add(index, fileName + "  Complete");
                            }
                        }
                        else{
                            
                            if(fileTransfer.client != null){
                                fileTransfer.client.downloadFile(fileTransfer.serverList.getSelectedValue(), file, Services.FTPServer.Speed.FAST.getSpeed(), fileTransfer.serverList.getSelectedIndex());
                            }
                            
                            continueloop = false;
                            String fileName = fileTransfer.serverList.getSelectedValue();
                            int index = fileTransfer.serverList.getSelectedIndex();
                            fileTransfer.serverDLM.removeElementAt(index);
                            fileTransfer.serverDLM.add(index, fileName + "  Complete");
                        }
                    }
                    else{
                        java.lang.System.out.println("Open command cancelled by user");
                        break;
                    }
                    
                } while (continueloop);
                
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(fileTransfer.rootPanel, "Please select some files to download", "", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == fileTransfer.partnerID){
            if(java.util.Objects.equals(fileTransfer.partnerID.getText(), "") == false){
                if(java.util.Objects.equals(fileTransfer.partnerPassword.getText(), "") == false){
                    fileTransfer.connection.setEnabled(true);
                }
            }
            else{
                fileTransfer.connection.setEnabled(false);
            }
        }
        if(e.getSource() == fileTransfer.partnerPassword){
            if(java.util.Objects.equals(fileTransfer.partnerPassword.getText(), "") == false){
                if(java.util.Objects.equals(fileTransfer.partnerID.getText(), "") == false){
                    fileTransfer.connection.setEnabled(true);
                }
            }
            else{
                fileTransfer.connection.setEnabled(false);
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
