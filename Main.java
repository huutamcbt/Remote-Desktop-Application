import Views.MainFrame;
import java.awt.*;

import javax.tools.Tool;
public class Main {
    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        if(frame.isDisplayable()){

        }
        frame.setVisible(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
   
       
    }
}
