package br.com.gotorcidaws.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class FileUtilsGoTorcida {

	public static String convertBase64ToImage(String base64, String name) {
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64);
		try {
			name = name.toLowerCase();
			name = name.replaceAll(" ", "_");
			name = "a_" + name + ".png";
			
	        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
			bufferedImage = resizeImage(bufferedImage, bufferedImage.getType());
			ImageIO.write(bufferedImage, "png", new File(name));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return name.replaceAll(".png", "");
	}

	public static void sendFileToFTP(String fileName) {
		try {
			JSch jsch = new JSch();

			String user = "root";
			String host = "gotorcida.com.br";
			int port = 22;
			String privateKey = "C:/privatekey";

			jsch.addIdentity(privateKey);
			System.out.println("identity added ");

			Session session = jsch.getSession(user, host, port);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			System.out.println("session created.");

			session.connect();
			System.out.println("session connected.....");

			Channel channel = session.openChannel("sftp");
			channel.setInputStream(System.in);
			channel.setOutputStream(System.out);
			channel.connect();
			System.out.println("shell channel connected....");

			ChannelSftp c = (ChannelSftp) channel;

			c.put("C:/eclipse/" + fileName, "/var/www/html/img/");
			c.exit();
			System.out.println("done");

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(100, 100, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 150, 150, null);
		g.dispose();
		return resizedImage;
	}
}
