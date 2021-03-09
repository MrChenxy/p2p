package com.bjpowernode.p2p.dto;

import java.io.InputStream;
import java.io.Serializable;

public class ImageHolder implements Serializable {

	private String imageName;
	private transient InputStream image;

	public ImageHolder(String imageName, InputStream image) {
		this.imageName = imageName;
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}
}
