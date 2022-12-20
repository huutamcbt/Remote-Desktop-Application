package Service.FTPServer.Client;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.UnknownHostException;

public class Start implements java.beans.PropertyChangeListener{
    private String ipAddress;
    private int port;
    private String password;
    private String fileName;
    private int indexOfFile;
    private java.io.File file;
    private java.net.Socket sock;
    private javax.swing.JProgressBar progressBar;
    private FileTransfer fileTransfer;
    private int speed;
    private javax.swing.JFrame frame;
    private java.io.InputStream is;
    private java.io.OutputStream os;
    private java.io.DataInputStream dis;
    private java.io.DataOutputStream dos;
    byte[] buffer = new byte[1024];

    class FileTransfer extends javax.swing.SwingWorker<Void, Void>{

        @Override
        protected Void doInBackground() throws Exception {
            // Send index of file
            dos.writeInt(indexOfFile);
            // Send file name to ftp server
            dos.writeUTF(createFileNameVerification());
            dos.flush();
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = 0;
            }
            // Receive a verification of file name from ftp server
            is.read(buffer, 0, buffer.length);

            String verification = new String(buffer);
            
            
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = 0;
            }
            java.lang.System.out.println("Verification: " + verification);
            if(isValidResponse(verification) == true){
                
                // Receive length of file from ftp server
                long fileLength = dis.readLong();
                java.lang.System.out.println(fileLength);
                dos.writeInt(speed);
                dos.flush();
                if(speed == Service.FTPServer.Speed.SLOW.getSpeed()){
                    buffer = new byte[1024];
                }
                if(speed == Service.FTPServer.Speed.MEDIUM.getSpeed()){
                    buffer = new byte[1024*1024];
                }
                if(speed == Service.FTPServer.Speed.FAST.getSpeed()){
                    buffer = new byte[50*1024*1024];
                }
                receiveFile(fileLength);
            }
            // fos.close();
            // is.close();
            // os.close();
            java.lang.System.out.println("Finish");
            return null;
        }
        
        @Override
        public void done(){
            java.awt.Toolkit.getDefaultToolkit().beep();
            
        }
        private void receiveFile(long fileLength){
            int count = 0;
            int progress = 0;
            try {
                java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
                double sum = 0;
                do {
                    
                    count= is.read(buffer, 0, buffer.length);

                    fos.write(buffer, 0, count);
                    fos.flush();

                    sum += count;
                    progress = (int)(sum/fileLength*100);
                   
                    setProgress(progress);
                } while (progress < 100);
            } catch (Exception e) {
                //TODO: handle exception
            }
            
        }
    }
    public Start(String ipAddr, String pw){
        ipAddress = ipAddr;
        port = 9000;
        password = pw;
        frame = new javax.swing.JFrame("Progress Bar");
        progressBar = new javax.swing.JProgressBar();
        indexOfFile = -1;
        
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName() == "progress"){
            int value = (int)evt.getNewValue();
            progressBar.setValue(value);
            if(value == 100){
                frame.setVisible(false);
            }
        }
    }

    public boolean verifyConnection(javax.swing.JList<String> clientList, javax.swing.JList<String> serverList, 
                                                        javax.swing.DefaultListModel<String> serverDLM){
        try {
            
            sock = new java.net.Socket(ipAddress, port);
            //sock.setSoTimeout(120000);
            is = sock.getInputStream();
            os = sock.getOutputStream();
            dis = new java.io.DataInputStream(sock.getInputStream());
            dos = new java.io.DataOutputStream(sock.getOutputStream());
            // Send a valid request to ftp server
            dos.writeUTF(createCheckPasswordRequest());
            // Receive a response from ftp server
            String verification = dis.readUTF(); 
   
            if(isValidResponse(verification) == true){
                javax.swing.JOptionPane.showMessageDialog(null, "Your connection is accepted", fileName, javax.swing.JOptionPane.PLAIN_MESSAGE);
                sendClientFileList(dos, clientList);
                receiveServerFileList(dis, serverList, serverDLM);
                return true;
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(null, "Your connection is denied", fileName, javax.swing.JOptionPane.ERROR_MESSAGE);
                sock.close();
                return false;
            }
            
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public void downloadFile(String fn, java.io.File f, int sp, int index){
        try {
            dos.writeUTF(createDownloadMethodRequest());
            dos.flush();
            String response = dis.readUTF();
            java.lang.System.out.println("Download" + response);
            if(isValidResponse(response)){
                
                file = f;
                fileName = fn;
                speed = sp;
                indexOfFile = index;
                fileTransfer = new FileTransfer();
                fileTransfer.addPropertyChangeListener(this);
                fileTransfer.execute();
                createProgressBar();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addClientFile(String file){
        try {
            dos.writeUTF(createAddMethodRequest());
            dos.flush();
            String response = dis.readUTF();
            if(isValidResponse(response)){
                dos.writeUTF(file);
                dos.flush();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void removeClientFile(int index){
        try {
            dos.writeUTF(createRemoveMethodRequest());
            dos.flush();
            String response = dis.readUTF();
            if(isValidResponse(response)){
                dos.writeInt(index);
                dos.flush();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void createProgressBar(){
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        progressBar.setPreferredSize(new java.awt.Dimension(350,90));

        frame.add(progressBar);
        frame.pack();
        frame.setVisible(true);
    }

    private boolean isValidResponse(String str){
        String[] response = str.split("\n");
        if(response[0].equals("FTP/1.0 200 Valid")){
            return true;
        }
        return false;
    }
    private String createCheckPasswordRequest(){
        String request = "CHECK Password FTP/1.0\n";
        request += "Host: \n";
        request += "Value: " + password;
        return request;
    }
    private String createFileNameVerification(){
        String request = "CHECK FileName FTP/1.0\n";
        request += "Host: \n";
        request += "Value: " + fileName + "\n";
        return request;
    }
    private String createDownloadMethodRequest(){
        String request = "DOWNLOAD FileName FTP/1.0\n";
        request += "Host: \n";
        return request;
    }
    private String createAddMethodRequest(){
        String request = "ADD FileName FTP/1.0\n";
        request += "Host: \n";
        return request;
    }
    private String createRemoveMethodRequest(){
        String request = "REMOVE FileName FTP/1.0\n";
        request += "Host: \n";
        return request;
    }
    private void sendClientFileList(java.io.DataOutputStream dos, javax.swing.JList<String> clientList){
        // Send length of list to ftp server
        try {
            dos.writeInt(clientList.getModel().getSize());
            for (int i = 0; i < clientList.getModel().getSize(); i++) {
                dos.writeUTF(clientList.getModel().getElementAt(i));
                dos.flush();
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    private void receiveServerFileList(java.io.DataInputStream dis, javax.swing.JList<String> serverList, 
                                                                javax.swing.DefaultListModel<String> serverDLM){
        // Receive length of file list from ftp server
        try {
            int count = dis.readInt();
            String fileName = "";
            for (int i = 0; i < count; i++) {
                fileName = dis.readUTF();
                serverDLM.addElement(fileName);
            }
            serverList.setModel(serverDLM);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
    
}
