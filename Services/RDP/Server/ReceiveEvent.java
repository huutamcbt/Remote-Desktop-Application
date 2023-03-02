package Services.RDP.Server;

public class ReceiveEvent extends java.lang.Thread{
    private java.net.Socket socket;
    private java.awt.Robot robot;
    private boolean continueLoop;
    private boolean lock;
    private SendScreen screen;
    public ReceiveEvent(java.net.Socket sock, java.awt.Robot rb, SendScreen screen) throws java.io.IOException{
        socket = sock;
        robot = rb;
        continueLoop = true;
        lock = false;
        this.screen = screen;
        this.setDaemon(true);
        this.start();
    }
    @Override
    public void run(){
        super.run();
        try {
            java.util.Scanner scanner = new java.util.Scanner(socket.getInputStream());
            int command;
            while (continueLoop) {
                command = scanner.nextInt();
                try {
                    switch (command) {
                        case 0:
                            continueLoop = false;
                            screen.stopLoop();
                            break;
                        case 1:
                            robot.mousePress(scanner.nextInt());
                            break;
                        case 2:
                            robot.mouseRelease(scanner.nextInt());
                            lock = false;
                            break;
                        case 3:
                            robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                            break;
                        case 4:
                            robot.mouseWheel(scanner.nextInt());
                            break;
                        case 5:
                            robot.keyPress(scanner.nextInt());
                            break;
                        case 6:
                            robot.keyRelease(scanner.nextInt());
                            break;
                        case 7:
                            if (lock == false) {
                                robot.mousePress(scanner.nextInt());
                            }
                            robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                            lock = true;
                            break;
                        case 8:
                            robot.mousePress(scanner.nextInt());
                            robot.mouseRelease(scanner.nextInt());
                            break;
                    }
                } catch (Exception e) {

                }
            }
            java.lang.System.out.println("Stop receive event");
            try {
                socket.close();
                scanner.close();
            } catch (Exception e) {

            }
        } catch (java.io.IOException e) {
        
        }
    }
}
