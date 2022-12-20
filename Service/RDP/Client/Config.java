package Service.RDP.Client;

public enum Config {
    LARGE_SCREEN_SIZE(1920, 1080),
    SMALL_SCREEN_SIZE(1536, 864),
    LARGE_PANEL_SIZE(1760, 990),
    SMALL_PANEL_SIZE(1229, 691);

    private int width;
    private int height;
    Config(int w, int h){
        width = w;
        height = h;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
