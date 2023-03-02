package Services.RDP.Client;

import java.io.IOException;

public class SendEvent implements java.awt.event.KeyListener, java.awt.event.MouseListener, java.awt.event.MouseMotionListener, java.awt.event.MouseWheelListener{
    private java.net.Socket socket;
    private double width;
    private double height;
    private javax.swing.JPanel panel;
    private javax.swing.JFrame frame;
    private java.io.PrintWriter writer;
    private boolean lock;
    private ReceiveScreen screen;
    public SendEvent(java.net.Socket sock, double w, double h, javax.swing.JFrame f, javax.swing.JPanel p, ReceiveScreen screen){
        socket = sock;
        width = w;
        height = h;
        panel = p;
        frame = f;
        lock = false;
        this.screen = screen;
        panel.addKeyListener(this);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseWheelListener(this);

        frame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent e){
               String[] options = {"Yes", "No"};
               int choice = javax.swing.JOptionPane.showOptionDialog(frame, "Do you want to close the connection?", "Warning", 
               javax.swing.JOptionPane.DEFAULT_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                if (choice == 0) {
                    writer.println(0);
                    writer.flush();
                    try {
                        socket.close();
                        writer.close();
                    } catch (IOException e1) {
                        
                        e1.printStackTrace();
                    }
                    SendEvent.this.screen.stopLoop();
                }
               else{
                frame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
               }
            }
        });
        
        try {
            writer = new java.io.PrintWriter(socket.getOutputStream());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public void exit(){
        writer.println(0);
        java.lang.System.out.println("Close socket");
    }
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        
    }
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        writer.println(Services.RDP.Server.Commands.PRESS_KEY.getAbbrev());
        writer.println(e.getKeyCode());
        writer.flush();
        java.lang.System.out.println("Press key");
    }
    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        writer.println(Services.RDP.Server.Commands.RELEASE_KEY.getAbbrev());
        writer.println(e.getKeyCode());
        writer.flush();
        java.lang.System.out.println("Release key");
    }
    @Override
    public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {
        writer.println(Services.RDP.Server.Commands.WHEEL_MOUSE.getAbbrev());
        int value = e.getWheelRotation();
        if(value < 0){
            writer.println(value);
            java.lang.System.out.println("Mouse wheel moved up");
        }
        else{
            writer.println(value);
            java.lang.System.out.println("Mouse wheel moved down");
        }
        writer.flush();
        java.lang.System.out.println("Mouse wheel");
    }
    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) { 
        writer.println(Services.RDP.Server.Commands.DRAG_MOUSE.getAbbrev());
        // Request server press the left button 
        if(lock == false){
            writer.println(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
        }
        
        if(width == Config.SMALL_SCREEN_SIZE.getWidth()){
            double xScale = (double)width/Config.SMALL_PANEL_SIZE.getWidth();
            double yScale = (double)height/Config.SMALL_PANEL_SIZE.getHeight();
            writer.println((int)(e.getX()*xScale));
            writer.println((int)(e.getY()*yScale));
            writer.flush();
        }
        if(width == Config.LARGE_SCREEN_SIZE.getWidth()){
            double xScale = (double)width/Config.LARGE_PANEL_SIZE.getWidth();
            double yScale = (double)height/Config.LARGE_PANEL_SIZE.getHeight();
            writer.println((int)(e.getX()*xScale));
            writer.println((int)(e.getY()*yScale));
            writer.flush();
        }
        lock = true;
        java.lang.System.out.println("Mouse drag");
    }
    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        if(width == Config.SMALL_SCREEN_SIZE.getWidth() && height == Config.SMALL_SCREEN_SIZE.getHeight()){
            double xScale = (double)width/Config.SMALL_PANEL_SIZE.getWidth();
            double yScale = (double)height/Config.SMALL_PANEL_SIZE.getHeight();
            writer.println(Services.RDP.Server.Commands.MOVE_MOUSE.getAbbrev());
            writer.println((int)(e.getX()*xScale));
            writer.println((int)(e.getY()*yScale));
            writer.flush();
        }
        if(width == Config.LARGE_SCREEN_SIZE.getWidth() && height == Config.LARGE_SCREEN_SIZE.getHeight()){
            double xScale = (double)width/Config.LARGE_PANEL_SIZE.getWidth();
            double yScale = (double)height/Config.LARGE_PANEL_SIZE.getHeight();
            writer.println(Services.RDP.Server.Commands.MOVE_MOUSE.getAbbrev());
            writer.println((int)(e.getX()*xScale));
            writer.println((int)(e.getY()*yScale));
            writer.flush();
        }
    }
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        // writer.println(Service.Server.Commands.CLICK_MOUSE.getAbbrev());
        // int button = e.getButton();
        // if(button == java.awt.event.MouseEvent.BUTTON1){
        //     writer.println(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
        //     writer.println(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
        // }
        // if(button == java.awt.event.MouseEvent.BUTTON2){
        //     writer.println(java.awt.event.InputEvent.BUTTON2_DOWN_MASK);
        //     writer.println(java.awt.event.InputEvent.BUTTON2_DOWN_MASK);
        // }
        // if(button == java.awt.event.MouseEvent.BUTTON3){
        //     writer.println(java.awt.event.InputEvent.BUTTON3_DOWN_MASK);
        //     writer.println(java.awt.event.InputEvent.BUTTON3_DOWN_MASK);
        // }
        // writer.flush();
        // java.lang.System.out.println("Mouse clicked");
    }
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        writer.println(Services.RDP.Server.Commands.PRESS_MOUSE.getAbbrev());
        int button = e.getButton();
        if(button == java.awt.event.MouseEvent.BUTTON1){
            writer.println(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
        }
        if(button == java.awt.event.MouseEvent.BUTTON2){
            writer.println(java.awt.event.InputEvent.BUTTON2_DOWN_MASK);
        }
        if(button == java.awt.event.MouseEvent.BUTTON3){
            writer.println(java.awt.event.InputEvent.BUTTON3_DOWN_MASK);
        }
        writer.flush();
    }
    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        writer.println(Services.RDP.Server.Commands.RELEASE_MOUSE.getAbbrev());
        int button = e.getButton();
        if(button == java.awt.event.MouseEvent.BUTTON1){
            writer.println(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
        }
        if(button == java.awt.event.MouseEvent.BUTTON2){
            writer.println(java.awt.event.InputEvent.BUTTON2_DOWN_MASK);
        }
        if(button == java.awt.event.MouseEvent.BUTTON3){
            writer.println(java.awt.event.InputEvent.BUTTON3_DOWN_MASK);
        }
        lock = false;
        writer.flush();
        java.lang.System.out.println("Mouse release");
    }
    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        
    }
    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        
    }
}
