
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class ThumbnailCreator {
    public static void main(String[] args) throws Exception {
        BufferedImage read = ImageIO.read(new File(args[0]));
        
        final int scale = 750;

        int thumb_height = scale; 
        float ratio = (float)read.getWidth() / (float)read.getHeight();
        int thumb_width = (int)(ratio * scale); 
           
        BufferedImage bufferedImage = new BufferedImage(thumb_width, thumb_height, read.getType());
        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(read, 0, 0, thumb_width, thumb_height, null);
        graphics.dispose();
        ImageIO.write(bufferedImage, "jpg", new File(args[1]));
    }
}