# JThumbnailMiniatures

~~~java

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("all")

public class Main extends javax.swing.JFrame {

	public Main() throws IOException {

		setAlwaysOnTop(true);

		setTitle("Title");

		setType(Type.NORMAL);

		initComponents();

		setVisible(true);

	}

	public static void main(String[] args) {

		try {

			new Main().setVisible(true);

		}

		catch (Exception e) {
			
		}

	}

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		String videoPath = "path_of_file";

		JThumbnail btnNewButton = new JThumbnail(videoPath);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(38)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(40, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(27)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(91, Short.MAX_VALUE)));

		getContentPane().setLayout(layout);

		setSize(500, 500);

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}
}

~~~