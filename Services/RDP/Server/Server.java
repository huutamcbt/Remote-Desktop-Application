package Services.RDP.Server;

import java.io.IOException;

public class Server implements java.lang.Runnable{
    private static String ipAddress;
    private static int port;
    private static String password;
    private java.net.ServerSocket servsock;
    private java.io.DataInputStream pw;
    private java.io.DataOutputStream verify;

    public Server(){
        ipAddress = null;
        try {
            java.net.Socket sock = new java.net.Socket("www.archlinux.org", 80);
            ipAddress = sock.getLocalAddress().getHostAddress();
            sock.close();
        } catch (Exception e) {
            java.lang.System.out.println(e);
        }
        port = 5000;
        password = generatePassword();
        
        pw = null;
        verify = null;
    }
    public static boolean running(){
        if(ipAddress != null){
            return true;
        }
        else{
            return false;
        }
    }
    public static String getIpAddress(){
        return ipAddress;
    }
    public static String getPassword(){
        return password;
    }
    private String generatePassword(){
        java.util.Random random = new java.util.Random();
        int number = random.nextInt(59999) + 40000;
        return java.lang.Integer.toString(number);
    }
    public void run(){
        java.awt.Robot robot = null;
        java.awt.Rectangle rectangle = null;
        try {
            java.lang.System.out.println("Awaiting Connection from client");
            // Create a server socket to listen on specific port 
            servsock = new java.net.ServerSocket(port);
            // Get screen size
            java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            rectangle = new java.awt.Rectangle(new java.awt.Dimension(1920,1080));
            
            robot = new java.awt.Robot();
            while(true){
                java.net.Socket sock = servsock.accept();
                java.net.Socket byteSock = servsock.accept();
                pw = new java.io.DataInputStream(sock.getInputStream());
                verify = new java.io.DataOutputStream(sock.getOutputStream());
                java.lang.System.out.println("Server");
                // Receive client password
                String pass = pw.readUTF();

                if(isTruePassword(pass)){
                    verify.writeUTF(createValidResponse());
                    verify.writeDouble(screenSize.getWidth());
                    verify.writeDouble(screenSize.getHeight());
                    SendScreen screen =  new SendScreen(sock, byteSock, robot, rectangle);
                    new ReceiveEvent(sock, robot, screen);
                }
                else{
                    verify.writeUTF(createInvalidResponse());
                }
            }
        } catch (Exception e) {
            java.lang.System.out.println(e);
            try {
                servsock.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    private boolean isTruePassword(String r){
        String[] request = r.split("\n");
        if(request[0].equals("CHECK Password RDP/1.0")){
            String[] pass = request[2].split(" ");
            java.lang.System.out.println(pass[1]);
            if(pass[1].equals(password)){
                return true;
            }
        }
        return false;
    }
    private String createValidResponse(){
        String response = "RDP/1.0 200 Valid";
        return response;
    }    
    private String createInvalidResponse(){
        String response = "RDP/1.0 200 Invalid";
        return response;
    }
}
