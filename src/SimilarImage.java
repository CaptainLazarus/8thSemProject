import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics2D;

public class SimilarImage {
   public static void main(String[] args) throws Exception {	// no need for full direc addr
//       BufferedImage img1 = ImageIO.read(new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/1.jpg"));
//       BufferedImage img2 = ImageIO.read(new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/2.jpg"));
      BufferedImage img1 = ImageIO.read(new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/xray1.png"));
      BufferedImage img2 = ImageIO.read(new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/xray2.png"));

      int w1 = img1.getWidth();
      int w2 = img2.getWidth();
      int h1 = img1.getHeight();
      int h2 = img2.getHeight();
      if ((w1!=w2)||(h1!=h2)) {
         System.out.println("Both images should have same dimensions");
      } else {
         long diff = 0, countsim=0, countparsim=0;
         for (int j = 0; j < h1; j++) {
            for (int i = 0; i < w1; i++) {
               
               int pixel1 = img1.getRGB(i, j);
               Color color1 = new Color(pixel1, true);
               int r1 = color1.getRed();
               int g1 = color1.getGreen();
               int b1 = color1.getBlue();
               int pixel2 = img2.getRGB(i, j);
               Color color2 = new Color(pixel2, true);
               int r2 = color2.getRed();
               int g2 = color2.getGreen();
               int b2= color2.getBlue();
               //sum of differences of rgb vals of the two images
               long data = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
               diff = diff+data;
	       if(r1 == r2 || g1 == g2 || b1 == b2) countparsim++;	// no point
	       if(r1 == r2 && g1 == g2 && b1 == b2) countsim++;	// rgb vals of pixels match
	       
	       if(r1 != r2 && g1 != g2 && b1 != b2){
		img1.setRGB(i, j, Color.black.getRGB());	// for normal (non-grayscale) img
		// img1.setRGB(i, j, Color.yellow.getRGB());	// for img in grayscale
		// img1.setRGB(i, j, new Color(0.8f, 0.9019608f, 0.6392157f).getRGB()); // something I tried for graycale. Didnt work
	       }
            }
         }
	 File outputfile = new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/xraytemplate.png");
	 ImageIO.write(img1, "png", outputfile);
	//  File outputfile = new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/normalimgtemplate.jpg");
	//  ImageIO.write(img1, "jpg", outputfile);
	
	 System.out.println("countsim: "+(double)countsim/(h1*w1)*100+"; countparsim: "+(double)countparsim/(h1*w1)*100);
         double avg = diff/(w1*h1*3);
         double percentage = (avg/255)*100;	// net pixel diff
         System.out.println("Difference: "+percentage);
      }
   }
}
