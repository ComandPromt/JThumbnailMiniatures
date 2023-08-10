import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

@SuppressWarnings("serial")

public class VideoThumbnailPanel extends JLabel {

	private int ancho;

	private int alto;

	String videoPath;

	FFmpegFrameGrabber grabber;

	int frameRate;

	int totalFrames;

	Java2DFrameConverter converter;

	long startTime;

	BufferedImage bufferedImage;

	long currentTime;

	long elapsedTime;

	Icon thumbnailIcon;

	Frame videoFrame;

	public void generateThumbnailLoop(String videoPath, int width, int height) {

		grabber = new FFmpegFrameGrabber(videoPath);

		try {

			grabber.start();

			frameRate = (int) grabber.getFrameRate();

			totalFrames = frameRate;

			converter = new Java2DFrameConverter();

			startTime = System.currentTimeMillis();

			currentTime = System.currentTimeMillis();

			elapsedTime = currentTime - startTime;

			videoFrame = grabber.grab();

			grabber.setFrameNumber(0);

			startTime = currentTime;

			bufferedImage = converter.getBufferedImage(videoFrame);

			thumbnailIcon = new ImageIcon(bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH));

			setIcon(thumbnailIcon);

			grabber.stop();

		}

		catch (Exception e) {

		}

	}

	public VideoThumbnailPanel(String path) {

		videoPath = path;

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				ancho = getWidth();

				alto = getHeight();

				generateThumbnailLoop(videoPath, ancho, alto);

			}

		});

	}

}
