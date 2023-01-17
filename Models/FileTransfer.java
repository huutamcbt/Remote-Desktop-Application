package Models;

import Services.FTPServer.Client.Start;

public class FileTransfer {
    public javax.swing.JLabel label;
    public javax.swing.JSeparator sep;
    public java.awt.GridBagConstraints gc;
    
    
    
    ///////////////////////////////////////////////////////////
    // Create attributes for FTPServerPanel
    public javax.swing.JPanel rootPanel;
    public javax.swing.JTextField partnerID;
    public javax.swing.JTextField partnerPassword;
    public javax.swing.JButton connection;
    public static javax.swing.JScrollPane clientSP;
    public static javax.swing.JScrollPane serverSP;
    public static javax.swing.JList<String> clientList;
    public static javax.swing.JList<String> serverList;
    public static java.io.File[] clientFiles;
    public static java.io.File[] serverFiles;
    public static javax.swing.DefaultListModel<String> clientDLM;
    public static javax.swing.DefaultListModel<String> serverDLM;
    public javax.swing.JFileChooser fc;
    public javax.swing.JButton choiceButton;
    public javax.swing.JButton removeButton;
    public javax.swing.JButton slowDownload; 
    public javax.swing.JButton mediumDownload; 
    public javax.swing.JButton fastDownload;
    public Start client;
}
