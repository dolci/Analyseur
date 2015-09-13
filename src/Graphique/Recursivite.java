package Graphique;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import Data.LectureFichier;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Recursivite extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private LectureFichier lf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recursivite frame = new Recursivite();
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
	public Recursivite() {
		setTitle("Analyseur");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Nouveau dossier\\Analyse_Program\\Fonts-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, null, null, null));
		panel.setBounds(10, 30, 368, 83);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Grammaire");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(18, 30, 80, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(92, 28, 167, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Parcourir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        int returnVal = chooser.showOpenDialog(getParent());
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        
		           textField.setText(chooser.getSelectedFile().getName());
		           lf=new LectureFichier(textField.getText());
		         }
		       
			}
		});
		btnNewButton.setBounds(269, 8, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Recursivit\u00E9");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lf.Regle_Production();
				lf.Elimination_RecursivviteGauche();
				try 
				{
					
					BufferedWriter entree= new BufferedWriter(new FileWriter("Grammaire_Rectifi.txt"));
					for(int i=0;i<lf.getListNterminaux().getListeN().size();i++)
					{
						entree.write(lf.getListNterminaux().getListeN().get(i).getlibelle()+" ->"+lf.getListNterminaux().getListeN().get(i).getRegle()+"   \n");
						entree.newLine();
					   System.out.println(lf.getListNterminaux().getListeN().get(i).getlibelle()+" ->"+lf.getListNterminaux().getListeN().get(i).getRegle()+"\n");
					}
					 entree.close();
				} 
				catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"D:\\Nouveau dossier\\Analyse_Program\\Grammaire_Rectifi.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(269, 44, 89, 23);
		panel.add(btnNewButton_1);
	}
}
