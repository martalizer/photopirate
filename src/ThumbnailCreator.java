
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ThumbnailCreator {
    final int scale = 300;

	
    public void createThumbnail(String inputPath, String outputPath) {
        BufferedImage read = null;
		try {
			read = ImageIO.read(new File(inputPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int thumb_height = scale; 
        float ratio = (float)read.getWidth() / (float)read.getHeight();
        int thumb_width = (int)(ratio * scale); 
           
        BufferedImage bufferedImage = new BufferedImage(thumb_width, thumb_height, read.getType());
        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(read, 0, 0, thumb_width, thumb_height, null);
        graphics.dispose();
        try {
			ImageIO.write(bufferedImage, "jpg", new File(outputPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}