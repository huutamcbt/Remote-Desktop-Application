package Services.RDP.Client;

public class Client extends java.lang.Thread{
    private String ipAddress;
    private String password;
    private int port;
    private java.io.DataOutputStream pw;
    private java.io.DataInputStream verification;
    
    public Client(String id, String pw){
        ipAddress = id;
        password = pw;
        port = 5000;
        start();
    }
    public void run(){
        try {
            java.net.Socket sock = new java.net.Socket(ipAddress,port);
            java.net.Socket byteSocket = new java.net.Socket(ipAddress,port);
            javax.swing.JFrame frame = new javax.swing.JFrame("Remote Control");
            javax.swing.JPanel panel = new javax.swing.JPanel();
            
            panel.setFocusable(true);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.add(panel);
            // The pw variable is used to send a check request to server
            pw = new java.io.DataOutputStream(sock.getOutputStream());
            // The verification is used to receive a verification from server
            verification = new java.io.DataInputStream(sock.getInputStream());
            // Send request to server
            pw.writeUTF(createCheckRequest());
            // Receive the response from server
            String verify = verification.readUTF();
            
            if(isValidResponse(verify)){
                double width = verification.readDouble();
                double height = verification.readDouble();
                ReceiveScreen screen = new ReceiveScreen(sock, byteSocket, width, height, frame, panel);
                new SendEvent(sock, width, height, frame, panel, screen);
            }
            
        } catch (java.io.IOException e) {

            e.printStackTrace();
        }
    }
    private String createCheckRequest(){
        String request = "CHECK Password RDP/1.0\n";
        request += "Host: 192.168.1.10\n";
        request += "Value: " + password;
        return request;
    }
    private boolean isValidResponse(String verify){
        String[] response = verify.split("\n");
        if(response[0].equals("RDP/1.0 200 Valid")){
            return true;
        }
        return false;
    }
}
