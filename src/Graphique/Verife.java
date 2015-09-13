package Graphique;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import Data.Noeud;;

public class Verife extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JTree tree;
	static ArrayList<Noeud> tab;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Verife frame = new Verife();
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
	public Verife() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Nouveau dossier\\Analyse_Program\\Fonts-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cha\u00EEne \u00E0 v\u00E9rifier:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 35, 115, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(124, 33, 140, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tab=AnlyseLL1.lf.Arbre_Derivation(textField.getText()+"$");
				if(tab==null)
					JOptionPane.showMessageDialog(null, "  l'expression est non vérifiée ");
				else
				{
				Trees t=new Trees();t.setVisible(true);}
				
			}
		});
		btnNewButton.setBounds(288, 32, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		
		
	}
}
