package Controller.RDP.Client;

public class ReceiveScreen extends java.lang.Thread{
    private java.net.Socket socket = null;
    private java.net.Socket byteSocket = null;
    private double width;
    private double height;
    private boolean continueLoop;
    private java.io.InputStream is;
    private java.awt.image.BufferedImage image;
    private javax.swing.JFrame frame;
    private javax.swing.JPanel panel;
    public ReceiveScreen(java.net.Socket sock,java.net.Socket bytesock, double w, double h, javax.swing.JFrame f, javax.swing.JPanel p){
        socket = sock;
        byteSocket = bytesock;
        width = w;
        height = h;
        continueLoop = true;
        frame = f;
        panel = p;
        this.setDaemon(true);
        start();
    }
    @Override
    public void run() {
        super.run();
        try {
            is = socket.getInputStream();
          
            byte[] bytes = new byte[50*1024*1024];
            int sum;
            int count;
            if(width == Config.SMALL_SCREEN_SIZE.getWidth() && height == Config.SMALL_SCREEN_SIZE.getHeight()){
                
                panel.setPreferredSize(new java.awt.Dimension(Config.SMALL_PANEL_SIZE.getWidth(), Config.SMALL_PANEL_SIZE.getHeight()));
                
                frame.pack();
                java.awt.Graphics g = panel.getGraphics();
                while(continueLoop){
                    sum = 0;
                    count = 0;
            
                    do{
                        sum+=is.read(bytes,sum,bytes.length-sum);
                        java.lang.System.out.println(sum);
                        count++;
                    }while(!(bytes[sum-2]==(byte)-1 && bytes[sum-1]==(byte)-39)&& count<150);
                    java.lang.System.out.println(sum);
                    try {
                        image = javax.imageio.ImageIO.read(new java.io.ByteArrayInputStream(bytes));
                        if( count < 150){
                            g.drawImage(image ,0,0, Config.SMALL_SCREEN_SIZE.getWidth(), Config.SMALL_SCREEN_SIZE.getHeight(), panel);
                        }
                    } catch (Exception e) {
                       
                    }
                       
                    java.lang.System.gc();
                }
            }
            if(width == Config.LARGE_SCREEN_SIZE.getWidth() && height == Config.LARGE_SCREEN_SIZE.getHeight()){
               
                panel.setPreferredSize(new java.awt.Dimension(Config.LARGE_PANEL_SIZE.getWidth(), Config.LARGE_PANEL_SIZE.getHeight()));

                frame.pack();
                java.awt.Graphics g = panel.getGraphics();
                while(continueLoop){
                    sum = 0;
                    count = 0;
                    do{
                        sum+=is.read(bytes,sum,bytes.length-sum);
                        java.lang.System.out.println(sum);
                        java.lang.System.out.println(bytes[sum-2]);
                        java.lang.System.out.println(bytes[sum-1]);
                        count++;
                    }while(!(sum>100000 && bytes[sum-2]==(byte)-1 && bytes[sum-1]==(byte)-39)&&count<120);

                    image = javax.imageio.ImageIO.read( new java.io.ByteArrayInputStream(bytes));
                    if(image != null || count <120){
                        g.drawImage(image.getScaledInstance(panel.getWidth(), panel.getHeight(), java.awt.Image.SCALE_SMOOTH) ,0,0, panel.getWidth(), panel.getHeight(), panel);
                    }
                    java.lang.System.gc();
                }
            }
            socket.close();
            is.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public void stopLoop(){
        continueLoop = false;
    }
}
