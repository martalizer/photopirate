
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ThumbnailCreator {
	
    public void createThumbnail(String inputPath, String outputPath, int longside) {

    	BufferedImage read = null;
		try {
			read = ImageIO.read(new File(inputPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		int thumb_height, thumb_width;
		
		if(read.getWidth() > read.getHeight()) {
			thumb_width = longside;
			float ratio = (float)read.getHeight() / (float)read.getWidth();
			thumb_height = (int)(ratio * longside);		
		}
		else {
			thumb_height = longside; 
	        float ratio = (float)read.getWidth() / (float)read.getHeight();
	        thumb_width = (int)(ratio * longside); 
		}      
           
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