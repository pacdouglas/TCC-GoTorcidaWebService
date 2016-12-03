package br.com.gotorcidaws.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

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
			file.setReadable(true, false);
			file.setWritable(true, false);
			file.setExecutable(true, false);
			ImageIO.write(bufferedImage, "png", file);
			
			Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
	        //add owners permission
	        perms.add(PosixFilePermission.OWNER_READ);
	        perms.add(PosixFilePermission.OWNER_WRITE);
	        perms.add(PosixFilePermission.OWNER_EXECUTE);
	        //add group permissions
	        perms.add(PosixFilePermission.GROUP_READ);
	        perms.add(PosixFilePermission.GROUP_WRITE);
	        perms.add(PosixFilePermission.GROUP_EXECUTE);
	        //add others permissions
	        perms.add(PosixFilePermission.OTHERS_READ);
	        perms.add(PosixFilePermission.OTHERS_WRITE);
	        perms.add(PosixFilePermission.OTHERS_EXECUTE);
	        
	        Files.setPosixFilePermissions(Paths.get("/var/www/html/img/" + name + ".png"), perms);
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
