
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class ThumbnailCreator {
    public static void main(String[] args) throws Exception {
        BufferedImage read = ImageIO.read(new File(args[0]));
        
        final int scale = 500;
       
        int thumb_width = scale; // read.getWidth()/10;
        float ratio = (float)read.getHeight() / (float)read.getWidth();
        int thumb_height = (int)(ratio * scale); // read.getHeight()/10;
        BufferedImage bufferedImage = new BufferedImage(thumb_width, thumb_height, read.getType());
        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(read, 0, 0, thumb_width, thumb_height, null);
        graphics.dispose();
        ImageIO.write(bufferedImage, "jpg", new File(args[1]));
    }
}