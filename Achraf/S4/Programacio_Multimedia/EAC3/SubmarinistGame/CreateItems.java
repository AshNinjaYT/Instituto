import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CreateItems {
    public static void main(String[] args) {
        try {
            // 1. Crear treasure.png (Cofre amarillo) 32x32
            BufferedImage treasure = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g1 = treasure.createGraphics();
            g1.setColor(Color.YELLOW);
            g1.fillRect(4, 8, 24, 16);
            g1.setColor(Color.ORANGE);
            g1.drawRect(4, 8, 24, 16);
            g1.fillRect(14, 12, 4, 4); // Cerradura
            g1.dispose();
            
            File f1 = new File("assets/treasure.png");
            ImageIO.write(treasure, "png", f1);
            System.out.println("Creado: " + f1.getAbsolutePath());

            // 2. Crear oxygen.png (Burbuja azul claro) 32x32
            BufferedImage oxygen = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = oxygen.createGraphics();
            g2.setColor(new Color(173, 216, 230, 200)); // Light blue
            g2.fillOval(6, 6, 20, 20);
            g2.setColor(Color.WHITE);
            g2.fillOval(10, 10, 6, 6); // Reflejo
            g2.dispose();
            
            File f2 = new File("assets/oxygen.png");
            ImageIO.write(oxygen, "png", f2);
            System.out.println("Creado: " + f2.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
