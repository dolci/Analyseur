package Graphique;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import Data.LectureFichier;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Canvas;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import java.awt.Toolkit;

public class MainProgram extends JFrame {

	private JPanel contentPane;
	static JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainProgram frame = new MainProgram();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainProgram() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Nouveau dossier\\Analyse_Program\\Fonts-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 127);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(95, 43, 202, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Parcourir");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
		        int returnVal = chooser.showOpenDialog(getParent());
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		         //  System.out.println(chooser.getSelectedFile().getName());
		           textField.setText(chooser.getSelectedFile().getName());
		           
		           AnlyseLL1 al=new AnlyseLL1();
		           al.show();
		           
		         //  LectureFichier lf=new LectureFichier(chooser.getSelectedFile().getPath());
		          
		        /* if(! verifierFichier()) 
		         { JOptionPane.showMessageDialog(null,"choisir fichier .txt ");System.out.println(".txt ou bien ex");}
		        */
		        	
		         }
			}
		});
		btnNewButton.setBounds(307, 43, 87, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNomFichier = new JLabel("Nom Fichier:");
		lblNomFichier.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNomFichier.setBounds(20, 47, 81, 14);
		contentPane.add(lblNomFichier);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 21, 388, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 77, 388, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(11, 21, 2, 58);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(398, 21, 2, 57);
		contentPane.add(separator_3);
	}
}
