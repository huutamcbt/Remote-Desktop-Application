package Controller.FTPServer.Server;

import java.io.IOException;

public class Start extends javax.swing.SwingWorker<Void, Void>{
    private static String ipAddress;
    private static int port;
    private static String password;
    private static java.net.ServerSocket servsock;
    public static boolean continueLoop;
    private java.io.File[] files;
    private int indexOfFile;
    private javax.swing.JList<String> clientList;
    private javax.swing.JList<String> serverList;
    private javax.swing.DefaultListModel<String> serverDLM;
    byte[] buffer = new byte[1024];


    public class TransferFile extends java.lang.Thread{
        private  java.net.Socket socket;
        private int speed;
        private  java.io.InputStream is;
        private  java.io.OutputStream os;
        private  java.io.DataInputStream dis;
        private  java.io.DataOutputStream dos;
        public TransferFile(java.net.Socket sock){
            socket = sock;
            try {
                is = sock.getInputStream();
                os = sock.getOutputStream();
                dis = new java.io.DataInputStream(sock.getInputStream());
                dos = new java.io.DataOutputStream(sock.getOutputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            continueLoop = true;
            start();
        }
        @Override
        public void run(){
            String request = "";
            do {
                try {
                    request = dis.readUTF();
                    
                } catch (IOException e) {
                    continueLoop = false;
                }
                switch (checkMethod(request)) {
                    case 1:
                        sendFiles();
                        break;
                    case 2:
                        addServerFile();
                        java.lang.System.out.println("Add file");
                        break;
                    case 3:
                        removeServerFile();
                        java.lang.System.out.println("Remove file");
                        break;
                }
                java.lang.System.out.println("Close socket");
            } while (continueLoop);
            try {
                socket.close();
                os.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        private void sendFiles(){
            try {
                // Receive index of required file
                indexOfFile = dis.readInt();
                // Receive a verification of file name from client
                String fileVerification = dis.readUTF();
                if(isExistingFile(fileVerification)){
                    os.write(createValidResponse().getBytes(), 0, createValidResponse().length());
                    os.flush();
                    long len = files[indexOfFile].length();
                    dos.writeLong(len);
                    dos.flush();
                    // Receive speed from client
                    speed = dis.readInt();
    
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = 0;
            }
            if(speed == Controller.FTPServer.Speed.SLOW.getSpeed()){
                buffer = new byte[1024];
            }
            if(speed == Controller.FTPServer.Speed.MEDIUM.getSpeed()){
                buffer = new byte[1024*1024];
            }
            if(speed == Controller.FTPServer.Speed.FAST.getSpeed()){
                buffer = new byte[50*1024*1024];
            }
            
            try {
                java.io.FileInputStream fis = new java.io.FileInputStream(files[indexOfFile]);
                int count = 0;
                do {
                    count = fis.read(buffer, 0, buffer.length);
                    try {
                        os.write(buffer, 0, count);
                        os.flush();
                    } catch (Exception e) {
                    
                    }
                } while (count > 0);
                // socket.close();
                // fis.close();
                // os.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for (int i = 0; i< buffer.length; i++) {
                buffer[i] = 0;
            }
        }
        private void addServerFile(){
            try {
                String fileName = dis.readUTF();
                serverDLM.addElement(fileName);
                serverList.setModel(serverDLM);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        private void removeServerFile(){
            try {
                int index = dis.readInt();
                View.MainFrame.Panel.removeFile(index);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
        private int checkMethod(String r){
            String[] request = r.split("\n");
            try {
                if(request[0].equals("DOWNLOAD FileName FTP/1.0")){
                    dos.writeUTF(createValidResponse());
                    return Controller.FTPServer.Method.DOWNLOAD.getAbbrev();    
                }
                if(request[0].equals("ADD FileName FTP/1.0")){
                    dos.writeUTF(createValidResponse());
                    return Controller.FTPServer.Method.ADD.getAbbrev();
                }
                if(request[0].equals("REMOVE FileName FTP/1.0")){
                    dos.writeUTF(createValidResponse());
                    return Controller.FTPServer.Method.REMOVE.getAbbrev();
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
    
            return -1;
        }
        // public static void stopThread(){
        //     try {
        //         is.close();
        //         os.close();
        //         dis.close();
        //         dos.close();
        //         socket.close();
        //     } catch (IOException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }
    }


    public Start(java.io.File[] f, javax.swing.JList<String> cl, javax.swing.JList<String> sl, 
        javax.swing.DefaultListModel<String> servDLM){
        try {
            java.net.Socket sock = new java.net.Socket("www.archlinux.org", 80);
            ipAddress = sock.getLocalAddress().getHostAddress();
            sock.close();
        } catch (Exception e) {
            java.lang.System.out.println(e);
        }
        port = 9000;
        password = generatePassword();
        continueLoop = true;
        files = f;
        indexOfFile = -1;
        clientList =cl;
        serverList = sl;
        serverDLM = servDLM;
        execute();
    }
    @Override
    protected Void doInBackground() throws Exception {
        servsock = new java.net.ServerSocket(port);
        while(continueLoop){
            java.net.Socket sock = servsock.accept();
            //sock.setSoTimeout(120000);
            int choice = javax.swing.JOptionPane.showConfirmDialog(null, "Do you allow your partner to connect?",
             "Warning", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
            if(choice == javax.swing.JOptionPane.OK_OPTION){
                
                if(verifyConnection(clientList, serverList, serverDLM, sock) == true){
                    new TransferFile(sock);
                    
                }
            }
        }
        
        return null;
    }
    private boolean verifyConnection(javax.swing.JList<String> clientList, javax.swing.JList<String> serverList, 
        javax.swing.DefaultListModel<String> serverDLM, java.net.Socket sock) throws IOException{

        java.io.DataInputStream dis = new java.io.DataInputStream(sock.getInputStream());
        java.io.DataOutputStream dos = new java.io.DataOutputStream(sock.getOutputStream());
        
        // Receive a password from partner
        String request = dis.readUTF();
        System.out.println("The request: " +request);
        if(isTruePassword(request)){
            
            dos.writeUTF(createValidResponse());
            dos.flush();
            receiveClientFileList(dis, serverList, serverDLM);
            sendServerFileList(dos, clientList);
            return true;
        }
        else{
            dos.writeUTF(createInvalidResponse());
            dos.flush();
            return false;
        } 
    }
    private boolean isExistingFile(String fn){
        String[] request = fn.split("\n");
        if(request[0].equals("CHECK FileName FTP/1.0")){
            String fileName = request[2].split(" ")[1];
            for (int i = 0; i < files.length; i++) {
                if(files[i] != null){
                    if(fileName.equals(files[i].getName())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean isTruePassword(String r){
        String[] request = r.split("\n");
        if(request[0].equals("CHECK Password FTP/1.0")){
            String[] pass = request[2].split(" ");
            java.lang.System.out.println(pass[1]);
            java.lang.System.out.println(password);
            if(pass[1].equals(password)){
                return true;
            }
        }
        return false;
    }
    public static String getIpAddress(){
        return ipAddress;
    }
    public static String getPassword(){
        return password;
    }
    private String generatePassword(){
        java.util.Random random = new java.util.Random();
        int number = random.nextInt(90) + 10;
        return java.lang.Integer.toString(number);
    }
    private String createValidResponse(){
        String response = "FTP/1.0 200 Valid\n";
        return response;
    }    
    private String createInvalidResponse(){
        String response = "FTP/1.0 200 Invalid\n";
        return response;
    }
    public static void stopServer(){
        try {
            servsock.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //TransferFile.stopThread();
        continueLoop = false;
    }
    private void receiveClientFileList(java.io.DataInputStream dis, javax.swing.JList<String> serverList, javax.swing.DefaultListModel<String> serverDLM){
        try {
            // Receive length of file list from ftp server
            int len = dis.readInt();
            String fileName = "";
            for (int i = 0; i < len; i++) {
                fileName = dis.readUTF();
                serverDLM.addElement(fileName);
            }
            serverList.setModel(serverDLM);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    private void sendServerFileList(java.io.DataOutputStream dos, javax.swing.JList<String> clientList){
        try {
            // Send length of file list to ftp server
            dos.writeInt(clientList.getModel().getSize());
            for (int i = 0; i < clientList.getModel().getSize(); i++) {
                dos.writeUTF(clientList.getModel().getElementAt(i));
                dos.flush();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public void sendFile(String f){
        
    }
   
}
