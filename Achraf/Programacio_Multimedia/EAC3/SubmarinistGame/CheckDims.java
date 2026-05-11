import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CheckDims {
    public static void main(String[] args) {
        try {
            File f = new File("assets/player-idle.png");
            BufferedImage img = ImageIO.read(f);
            System.out.println("player-idle.png: " + img.getWidth() + "x" + img.getHeight());
            
            f = new File("assets/player-swiming.png");
            img = ImageIO.read(f);
            System.out.println("player-swiming.png: " + img.getWidth() + "x" + img.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
