package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import entities.Student;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class SimplePresentationScreen extends JFrame {

	private JPanel contentPane;
	private JPanel tabInformation;
	private JTabbedPane tabbedPane;
	private Student studentData;
	private GroupLayout gl;
	private JLabel lblFechaHora;

	public SimplePresentationScreen(Student studentData) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SimplePresentationScreen.class.getResource("/images/tdp.png")));
		this.studentData = studentData;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setTitle("TdP-DCIC-UNS 2021 :: Pantalla de presentación");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(615, 250));
		setResizable(false);
		setContentPane(contentPane);
		
		init();
	}
	
	private void init() {
		// Tabbed Pane to student personal data
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabInformation = new JPanel();
		tabInformation.setPreferredSize(new Dimension(425, 200));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate now = LocalDate.now();
		LocalDateTime nowh = LocalDateTime.now();
		
		JLabel lblLU = new JLabel("LU");
		
		JLabel lblApellido = new JLabel("Apellido");
		
		JTextPane textPaneLU = new JTextPane();
		textPaneLU.setEditable(false);
		textPaneLU.setText(Integer.toString(studentData.getId()));
		
		JTextPane textPaneApellido = new JTextPane();
		textPaneApellido.setEditable(false);
		textPaneApellido.setText(studentData.getLastName());
		
		JTextPane textPaneNombre = new JTextPane();
		textPaneNombre.setEditable(false);
		textPaneNombre.setText(studentData.getFirstName());
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JTextPane textPaneEmail = new JTextPane();
		textPaneEmail.setEditable(false);
		textPaneEmail.setText(studentData.getMail());
		
		JLabel lblEmail = new JLabel("E-mail");
		
		JLabel lblGithub = new JLabel("Github URL");
		
		JTextPane textPaneGithub = new JTextPane();
		textPaneGithub.setEditable(false);
		textPaneGithub.setText(studentData.getGithubURL());
		
		gl = new GroupLayout(tabInformation);
		gl.setHorizontalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addComponent(lblApellido)
						.addComponent(lblLU)
						.addComponent(lblNombre)
						.addComponent(lblEmail)
						.addComponent(lblGithub))
					.addGap(27)
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addComponent(textPaneApellido, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
						.addComponent(textPaneLU, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
						.addComponent(textPaneNombre, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
						.addComponent(textPaneEmail, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
						.addComponent(textPaneGithub, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl.setVerticalGroup(
			gl.createParallelGroup(Alignment.BASELINE)
				.addGroup(gl.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblLU, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textPaneLU))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblApellido, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textPaneApellido))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textPaneNombre))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textPaneEmail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblGithub, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textPaneGithub))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		tabInformation.setLayout(gl);
		tabbedPane.addTab("Información del alumno", null, tabInformation, "Muestra la información declarada por el alumno");
		contentPane.add(tabbedPane, BorderLayout.WEST);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(lblFoto, BorderLayout.EAST);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResourceAsStream(studentData.getPathPhoto()));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		lblFoto.setSize(new Dimension(150, 175));
		Image dimg = img.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(dimg);
		lblFoto.setIcon(icon);
		
		lblFechaHora = new JLabel("");
		lblFechaHora.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaHora.setText("Esta ventana fue generada el "+df.format(now)+" a las: "+dtf.format(nowh));
		
		contentPane.add(lblFechaHora, BorderLayout.SOUTH);
	}
}
