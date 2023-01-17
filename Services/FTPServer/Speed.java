package Services.FTPServer;

public enum Speed {
    SLOW(1),
    MEDIUM(2),
    FAST(3);
    int speed;
    Speed(int sp){
        speed = sp;
    }
    public int getSpeed(){
        return speed;
    }
}
