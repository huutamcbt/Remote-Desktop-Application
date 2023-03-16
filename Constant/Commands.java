package Constant;

public enum Commands {
    PRESS_MOUSE(1),
    RELEASE_MOUSE(2),
    MOVE_MOUSE(3),
    WHEEL_MOUSE(4),
    PRESS_KEY(5),
    RELEASE_KEY(6),
    DRAG_MOUSE(7),
    CLICK_MOUSE(8);
    
    private int abbrev;
    Commands(int a){
        abbrev = a;
    }

    public int getAbbrev(){
        return abbrev;
    }
}
