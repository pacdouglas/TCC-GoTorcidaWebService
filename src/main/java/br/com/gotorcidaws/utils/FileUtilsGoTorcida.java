package br.com.gotorcidaws.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUtilsGoTorcida {

	public static String convertBase64ToImage(String base64, String name) {
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64);
		try {
			name = name.toLowerCase();
			name = name.replaceAll(" ", "_");
			name = "a_" + name;
			
	        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
			bufferedImage = resizeImage(bufferedImage, bufferedImage.getType());
			
			File file = new File("/var/www/html/img/" + name + ".png");
			file.setReadable(true);
			file.setWritable(true);
			file.setExecutable(true);
			
	        ImageIO.write(bufferedImage, "png", file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return name.replaceAll(".png", "");
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(100, 100, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 100, 100, null);
		g.dispose();
		return resizedImage;
	}
}
