package com.jthumbnailminiatures;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

@SuppressWarnings("serial")

public class VideoThumbnailPanel extends JLabel {

	private int ancho;

	private int alto;

	private String videoPath;

	private FFmpegFrameGrabber grabber;

	private Java2DFrameConverter converter;

	private Color color;

	private Frame videoFrame;

	private int grosor;

	public void setColor(Color color) {

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

	public void setGrosor(int grosor) {

		if (grosor < 0) {

			grosor = 0;

		}

		this.grosor = grosor;

		repaint();

	}

	public VideoThumbnailPanel(String path) {

		videoPath = path;

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				ancho = getWidth();

				alto = getHeight();

				repaint();

			}

		});

	}

	@Override
	public void paint(Graphics g) {

		try {

			int mitad = grosor / 2;

			if (mitad == 0) {

				mitad = 1;

			}

			Graphics2D g2 = (Graphics2D) g;

			g2.setStroke(new BasicStroke(grosor));

			g2.setColor(color);

			g2.drawRect(0, 0, ancho, alto);

			grabber = new FFmpegFrameGrabber(videoPath);

			grabber.start();

			converter = new Java2DFrameConverter();

			videoFrame = grabber.grab();

			grabber.setFrameNumber(0);

			g2.drawImage((converter.getBufferedImage(videoFrame)).getScaledInstance(ancho - grosor, alto - grosor,
					Image.SCALE_SMOOTH), mitad, mitad, null);

			grabber.stop();

		}

		catch (Exception e) {

		}

	}

}
