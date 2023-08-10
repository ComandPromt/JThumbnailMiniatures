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

	public JLabel getThumbnail() {

		return lblNewLabel;

	}

	public JThumbnail(String file) {

		setBackground(Color.GRAY);

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				setLayout(null);

				lblNewLabel = null;

				archivo = new File(file);

				try {

					if (archivo.isFile()) {

						if (esImagen(file)) {

							lblNewLabel = new Miniatura(file);

						}

						else {

							lblNewLabel = new VideoThumbnailPanel(file);

						}

					}

					lblNewLabel.setBounds(0, 0, getWidth(), getHeight());

					add(lblNewLabel);

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
