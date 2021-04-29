import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.Math;
import java.io.IOException;

class ExtractSubtractedImg{

	BufferedImage img1, img2, template, img3;
	int height, width;
	
	ExtractSubtractedImg(BufferedImage template, BufferedImage img1, BufferedImage img2 , BufferedImage img3){
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.template = template;
		this.height = template.getHeight();
		this.width = template.getWidth();
	}

	Object[] getSubtractedImg(){	// didnt throw Exception for h1=h2 & w1=w2 as this Exception was thrown for ExtractTemplate
		int count = 0;
		int flag = 0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
			
				int p = template.getRGB(i, j);
				// if(flag == 0){
				// System.out.println("Color P is :" + Integer.toHexString(p));
				// 	flag+=1;
				// }
				int p1 = img1.getRGB(i, j);
				int p2 = img2.getRGB(i, j);
				int p3 = img3.getRGB(i, j);

				// Simple
				if(p == 0xff000000){
					img1.setRGB(i, j, p1);
					img2.setRGB(i, j, p2);
					img3.setRGB(i, j, p3);
				}
				else{
					img1.setRGB(i, j, 0);
					img2.setRGB(i, j, 0);
					img2.setRGB(i, j, 0);
				}
				// flag = 1;

				// img1.setRGB(i, j, p1^p);
				// img2.setRGB(i, j, p2^p);

				if(count < 5){
					System.out.println("\n----------------------\n" + 
					"\nHex p = " + Integer.toHexString(p) + 
					"\nHex p1 = " + Integer.toHexString(p1) + 
					"\nHex p2 = " + Integer.toHexString(p2) + 
					"\nHex p1^p = " + Integer.toHexString(p1^p) +
					"\nHex p2^p = " + Integer.toHexString(p2^p) +
					"\n----------------------\n");
					count+=1;
				}
			}
		}
		return new Object[]{img1, img2, img3};
	}

}

class ExtractTemplate {

	BufferedImage img1, img2, template, img3;
	int w1, w2, h1, h2;

	ExtractTemplate(BufferedImage img1, BufferedImage img2 , BufferedImage img3){
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.w1 = img1.getWidth();
		this.w2 = img2.getWidth();
		this.h1 = img1.getHeight();
		this.h2 = img2.getHeight();
		this.template = new BufferedImage(w1, h1, img1.getType());	// object clone for template
	}

	BufferedImage getTemplate() throws Exception{

		if ((w1!=w2)||(h1!=h2)) {
			throw new IOException("Both images should have same dimensions");
		} else {
			long diff = 0, countsim=0, countparsim=0;
			int flag = 0;
			for (int j = 0; j < h1; j++) {
				for (int i = 0; i < w1; i++) {
				
					int pixel1 = img1.getRGB(i, j);
					int pixel2 = img2.getRGB(i, j);
					int pixel3 = img3.getRGB(i, j);
					
					// Color color1 = new Color(pixel1);
					// Color color2 = new Color(pixel2);
					// Color color3 = new Color(pixel3);
					
					// int r1 = color1.getRed();
					// int g1 = color1.getGreen();
					// int b1 = color1.getBlue();

					// int r2 = color2.getRed();
					// int g2 = color2.getGreen();
					// int b2= color2.getBlue();

					// int r3 = color3.getRed();
					// int g3 = color3.getGreen();
					// int b3= color3.getBlue();

					// String sss = Integer.toHexString(pixel1);
					// String sss1 = Integer.toHexString(pixel2);	
					// if(flag == 0){
					// 	System.out.println(sss);
					// 	System.out.println(sss1);
					// }

					// Simple
					if(pixel1 == pixel2 && pixel3 == pixel1)
						template.setRGB(i, j, pixel1);
					else
						template.setRGB(i, j, 0x000000);

					// int tempColor = 0x000000;

					// // tempColor = 0xff000000 ^ ~(pixel1^pixel2) ;
					// // tempColor = ((((~r1^~r2)) << 16) ^ (((~g1^~g2)) << 8) ^ ((~b1^~b2)));

					// if(r1 == r2){	
					// 	String s = Integer.toHexString(r1) + "0000";
					// 	int xxx = Integer.parseInt(s , 16);
					// 	tempColor = tempColor ^ xxx;
					// }
					// else{
					// 	int temp = (r1^r2);
					// 	if(temp < 16){
					// 		temp = r1 & 0xfffff0;
					// 		String s = Integer.toHexString(temp) + "0000";
					// 		int xxx = Integer.parseInt(s , 16);
					// 		tempColor = tempColor ^ xxx;
					// 	}
					// 	else if(temp%16 == 0){
					// 		temp = r1 & 0xffff0f;
					// 		String s = Integer.toHexString(temp) + "0000";
					// 		int xxx = Integer.parseInt(s , 16);
					// 		tempColor = tempColor ^ xxx;
					// 	}
					// }
					// if(g1 == g2){
					// 	String s = Integer.toHexString(g1) + "00";
					// 	int xxx = Integer.parseInt(s , 16);
					// 	tempColor = tempColor ^ xxx;
					// }
					// else{
					// 	int temp = (g1^g2);
					// 	if(temp < 16){
					// 		temp = g1 & 0xfffff0;
					// 		String s = Integer.toHexString(temp) + "00";
					// 		int xxx = Integer.parseInt(s , 16);
					// 		tempColor = tempColor ^ xxx;
					// 	}
					// 	else if(temp%16 == 0){
					// 		temp = g1 & 0xffff0f;
					// 		String s = Integer.toHexString(temp) + "00";
					// 		int xxx = Integer.parseInt(s , 16);
					// 		tempColor = tempColor ^ xxx;
					// 	}
					// }
					// if(b1 == b2){
					// 	String s = Integer.toHexString(b1);
					// 	int xxx = Integer.parseInt(s , 16);
					// 	tempColor = tempColor ^ xxx;
					// }
					// else{
					// 	int temp = (b1^b2);
					// 	if(temp < 16){
					// 		temp = b1 & 0xfffff0;
					// 		String s = Integer.toHexString(temp);
					// 		int xxx = Integer.parseInt(s , 16);
					// 		tempColor = tempColor ^ xxx;
					// 	}
					// 	else if(temp%16 == 0){
					// 		temp = b1 & 0xffff0f;
					// 		String s = Integer.toHexString(temp);
					// 		int xxx = Integer.parseInt(s , 16);
					// 		tempColor = tempColor ^ xxx;
					// 	}
					// }
					// if(flag == 0){
					// 	System.out.println(Integer.toHexString(tempColor));
					// }
					
					// template.setRGB(i , j , tempColor);
					flag = 1;
				}
			}
			System.out.println("countsim: "+(double)countsim/(h1*w1)*100+"; countparsim: "+(double)countparsim/(h1*w1)*100);
			double avg = diff/(w1*h1*3);
			double percentage = (avg/255)*100;	// net pixel diff
			System.out.println("Difference: "+percentage);

		}

		return template;
	}
}


public class SimilarImage {
	public static void main(String[] args) throws Exception {// no need for full direc addr

		File[] file = {
			   new File("../images/1.png"),
			new File("../images/2.png"),
			new File("../output/temp.png"),
			new File("../output/sub1.png"),
			new File("../output/sub2.png"),
			new File("../images/3.png"),
			new File("../output/sub3.png")
		};
		// BufferedImage img1 = ImageIO.read(new File("/home/hrithik/Downloads/gitrepo/8thSemProject/images/xray1.png"));
		BufferedImage img1 = ImageIO.read(file[0]);
		BufferedImage img2 = ImageIO.read(file[1]);
		BufferedImage img3 = ImageIO.read(file[5]);
		System.out.println("\nType 1 ->" + img1.getType() + "\nType 2 ->" + img2.getType() + "\nType 3 ->" + img3.getType());
		// ImageIO.write(img1, "png", file[3]);
		// ImageIO.write(img2, "png", file[4]);
		// System.out.println("\n---------------------------------------------------------------------------\n");
		// System.out.println((size(file[0]) + size(file[1])));
		// System.out.println((size(file[3]) + size(file[4])));
		// BufferedImage img3 = ImageIO.read(file[3]);
		// BufferedImage img4 = ImageIO.read(file[4]);

		ExtractTemplate template = new ExtractTemplate(img1, img2, img3);
		BufferedImage templateObject = template.getTemplate();
		ImageIO.write(templateObject, "png", file[2]);

		ExtractSubtractedImg subImagesObject = new ExtractSubtractedImg(templateObject, img1, img2 , img3);
		Object[] subtractedImages = subImagesObject.getSubtractedImg();
		ImageIO.write((BufferedImage)subtractedImages[0], "png", file[3]);
		ImageIO.write((BufferedImage)subtractedImages[1], "png", file[4]);
		ImageIO.write((BufferedImage)subtractedImages[1], "png", file[6]);

		System.out.println("\n---------------------------------------------------------------------------\n");
		System.out.println("Total orig size = \t\t\t" + size(file[0]) + " + " + size(file[1]) + " + " + size(file[5]) + " = \t\t" + round(size(file[0])+size(file[1])+size(file[5])) + " KB");
		System.out.println("Template + subImage1 + subImage2 + subimage3= \t"+ size(file[2])+" + "+ size(file[3])+" + "+ size(file[4])+" + "+ size(file[6])+" = \t"+ round(size(file[2])+size(file[3])+size(file[4])+size(file[6]))+ " KB");

	}

	static double size(File ff){
		
		double s =  ff.length()/1000.0;
		return round(s);
	}

	static double round(double r){
		return Math.round(r * 100.0) / 100.0;
	}
   
}

