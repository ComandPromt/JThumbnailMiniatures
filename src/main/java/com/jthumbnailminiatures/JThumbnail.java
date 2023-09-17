package com.jthumbnailminiatures;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class JThumbnail extends JPanel {

	private JLabel lblNewLabel;

	private File archivo;

	public void setImage(String image) {

		archivo = new File(image);

		try {

			if (archivo.isFile()) {

				if (esImagen(image)) {

					lblNewLabel = new Miniatura(image);

				}

				else {

					lblNewLabel = new VideoThumbnailPanel(image);

				}

				lblNewLabel.setBounds(0, 0, getWidth(), getHeight());

				removeAll();

				add(lblNewLabel);

			}

		}

		catch (Exception e1) {

		}

	}

	public JThumbnail(final String file, final Color color, final int thickness) {

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				setLayout(null);

				archivo = new File(file);

				try {

					if (archivo.isFile()) {

						if (esImagen(file)) {

							lblNewLabel = new Miniatura(file);

							((Miniatura) lblNewLabel).setBorder(color, thickness);

						}

						else {

							lblNewLabel = new VideoThumbnailPanel(file);

							((VideoThumbnailPanel) lblNewLabel).setBorder(color, thickness);
						}

						lblNewLabel.setBounds(0, 0, getWidth(), getHeight());

						if (getComponentCount() == 0) {

							add(lblNewLabel);

						}

						else {

							lblNewLabel.repaint();

						}

					}

				}

				catch (Exception e1) {

				}

			}

		});

	}

	private boolean esImagen(String file) throws IOException {

		boolean resultado = true;

		if (ImageIO.read(new File(file)) == null) {

			resultado = false;

		}

		return resultado;

	}

}