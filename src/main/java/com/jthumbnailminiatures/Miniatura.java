package com.jthumbnailminiatures;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

@SuppressWarnings("serial")

public class Miniatura extends JLabel {

	private int ancho;

	private int alto;

	private JLabel lblNewLabel;

	private String image;

	private int grosor;

	private Color color;

	public String getImage() {

		return image;

	}

	public void setImage(String image) {

		if (!this.image.equals(image)) {

			this.image = image;

			repaint();

		}

	}

	public void setColor(Color color) {

		if (color == null) {

			color = Color.BLACK;

		}

		this.color = color;

	}

	public void setBorder(Color color, int thickness) {

		if (thickness < 0) {

			thickness = 0;

		}

		if (color == null) {

			color = Color.BLACK;

		}

		this.color = color;

		grosor = thickness;

		repaint();

	}

	public Icon getIcon() {

		return lblNewLabel.getIcon();

	}

	public void setGrosor(int grosor) {

		if (grosor < 0) {

			grosor = 0;

		}

		this.grosor = grosor;

		repaint();

	}

	public void generateThumbnailLoop(String videoPath) {

		FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);

		try {

			grabber.start();

			Java2DFrameConverter converter = new Java2DFrameConverter();

			Frame frame = grabber.grab();

			if (frame == null) {

				grabber.setFrameNumber(0);

			}

			setIcon(new ImageIcon(converter.convert(frame)));

			grabber.stop();

			converter.close();

			grabber.close();

		}

		catch (Exception e) {

		}

	}

	public Miniatura(String image) {

		grosor = 0;

		this.image = image;

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				if ((ancho == 0 && alto == 0)
						|| ((ancho > 0 && alto > 0) && ancho != getWidth() && alto != getHeight())) {

					ancho = getWidth();

					alto = getHeight();

					setSize(ancho, alto);

					repaint();

				}

			}

		});

		setLayout(null);

	}

	@Override
	public void paint(Graphics g) {

		try {

			Graphics2D g2 = (Graphics2D) g;

			int mitad = grosor / 2;

			if (mitad == 0) {

				mitad = 1;

			}

			g2.setColor(color);

			if (grosor > 0) {

				g2.setStroke(new BasicStroke(grosor));

				g2.drawRect(0, 0, ancho - grosor, alto - grosor);

			}

			g2.drawImage(
					ImageIO.read(new File(image)).getScaledInstance(ancho - grosor, alto - grosor, Image.SCALE_SMOOTH),
					mitad, mitad, null);

		}

		catch (Exception e) {

		}

	}

}
