package prueba;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import tds.video.VideoWeb;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Video {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Video window = new Video();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Video() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		VideoWeb videoWeb = new VideoWeb();
		JButton btnVideo = new JButton("Video");
		btnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				videoWeb.playVideo("https://www.youtube.com/watch?v=WuVSx9F8rro");
			}
		});
		frame.getContentPane().add(btnVideo, BorderLayout.NORTH);
		
		
		frame.getContentPane().add(videoWeb, BorderLayout.CENTER);
	}
	// tenemos q ver como  hacer un nuevo  componete 
}
