package com.mitologia.chin.abstraction;

import com.mitologia.chin.bukkit.MCAwtImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BukkitMCAwtImage implements MCAwtImage {

	private Image image;

	public BukkitMCAwtImage(String path, boolean is_url) {
		if(is_url){
			try{
				URL url = new URL(path);
				image = ImageIO.read(url);
			} catch (IOException ignored) { /*ignored*/ }
		} else {
			image = new ImageIcon(path).getImage();
		}
	}

	@Override
	public Object getHandle() {
		return image;
	}
}
