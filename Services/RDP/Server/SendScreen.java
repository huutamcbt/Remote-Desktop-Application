package Services.RDP.Server;

import java.io.IOException;

public class SendScreen extends java.lang.Thread{
    private java.net.Socket socket;
    private java.net.Socket byteSocket;
    private java.awt.Robot robot;
    private java.io.DataOutputStream dos;
    private java.awt.Rectangle rectangle;
    private java.io.OutputStream os;
    private boolean continueLoop = true;
    
    public SendScreen(java.net.Socket sock, java.net.Socket byteSock, java.awt.Robot rb, java.awt.Rectangle rect){
        socket = sock;
        byteSocket = byteSock;
        robot = rb;
        rectangle = rect;
        try {
            dos = new java.io.DataOutputStream(byteSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setDaemon(true);
        start();
    }
    public void run(){
        
        try {
            os = socket.getOutputStream();
            
        } catch (Exception e) {

        }
        java.awt.image.BufferedImage image;
        
        while(continueLoop){
            try {
                image = robot.createScreenCapture(rectangle);
                
                
                javax.imageio.ImageIO.write(image, "jpeg", os);
            } catch (java.io.IOException e1) {

            }

            
            java.lang.System.gc();
        }
        try {
            socket.close();
            os.close();
        } catch (java.io.IOException e) {
            
        }
    }
    public void stopLoop(){
        continueLoop = false;
    }
}
