import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.Math;
import java.io.IOException;

class ExtractSubtractedImg{

	BufferedImage img1, img2, template;
	int height, width;
	
	ExtractSubtractedImg(BufferedImage template, BufferedImage img1, BufferedImage img2){
		this.img1 = img1;
		this.img2 = img2;
		this.template = template;
		this.height = template.getHeight();
		this.width = template.getWidth();
	}

	Object[] getSubtractedImg(){	// didnt throw Exception for h1=h2 & w1=w2 as this Exception was thrown for ExtractTemplate

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
			
				int pixel = template.getRGB(i, j);
				if(pixel != Color.black.getRGB()){

					img1.setRGB(i, j, Color.black.getRGB());
					img2.setRGB(i, j, Color.black.getRGB());
				}
			}
		}
		return new Object[]{img1, img2};
	}

}

class ExtractTemplate {

	BufferedImage img1, img2, img3;
	int w1, w2, h1, h2;

	ExtractTemplate(BufferedImage img1, BufferedImage img2){
		this.img1 = img1;
		this.img2 = img2;
		this.w1 = img1.getWidth();
		this.w2 = img2.getWidth();
		this.h1 = img1.getHeight();
		this.h2 = img2.getHeight();
		this.img3 = new BufferedImage(w1, h1, img1.getType());	// object clone for template
	}

	BufferedImage getTemplate() throws Exception{

		if ((w1!=w2)||(h1!=h2)) {
			throw new IOException("Both images should have same dimensions");
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

						img3.setRGB(i, j, Color.black.getRGB());	
						// img1.setRGB(i, j, Color.yellow.getRGB());	// doesnt work for grayscale img
					}
					else 
						img3.setRGB(i, j, pixel1);
				}
			}
			System.out.println("countsim: "+(double)countsim/(h1*w1)*100+"; countparsim: "+(double)countparsim/(h1*w1)*100);
			double avg = diff/(w1*h1*3);
			double percentage = (avg/255)*100;	// net pixel diff
			System.out.println("Difference: "+percentage);

		}

		return img3;
	}
}


public class SimilarImage {
	public static void main(String[] args) throws Exception {// no need for full direc addr

		File[] file = {new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/xray1.png"),
			new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/xray2.png"),
			new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/xraytemplate.png"),
			new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/subtractedImg1.png"),
			new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/subtractedImg2.png")
		};
		// BufferedImage img1 = ImageIO.read(new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/xray1.png"));
		BufferedImage img1 = ImageIO.read(file[0]);
		BufferedImage img2 = ImageIO.read(file[1]);

		ExtractTemplate template = new ExtractTemplate(img1, img2);
		BufferedImage templateObject = template.getTemplate();
		ImageIO.write(templateObject, "png", file[2]);

		ExtractSubtractedImg subImagesObject = new ExtractSubtractedImg(templateObject, img1, img2);
		Object[] subtractedImages = subImagesObject.getSubtractedImg();
		ImageIO.write((BufferedImage)subtractedImages[0], "png", file[3]);
		ImageIO.write((BufferedImage)subtractedImages[1], "png", file[4]);

		System.out.println("\n---------------------------------------------------------------------------\n");
		System.out.println("Total orig size = \t\t\t" + size(file[0]) + " + " + size(file[1]) + " = \t\t" + round(size(file[0])+size(file[1])) + " KB");
		System.out.println("Template + subImage1 + subImage2 = \t"+ size(file[2])+" + "+ size(file[3])+" + "+ size(file[4])+" = \t"+ round(size(file[2])+size(file[3])+size(file[4]))+ " KB");

   	}

	static double size(File ff){
		
		double s =  ff.length()/1000.0;
		return round(s);
	}

	static double round(double r){
		return Math.round(r * 100.0) / 100.0;
	}
   
}

