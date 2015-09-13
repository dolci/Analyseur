package Graphique;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Trees extends JFrame {

	private JPanel contentPane;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Trees frame = new Trees();
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
	public Trees() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 625);
		contentPane = new JPanel();
		/*if(tab==null)
		{
		JOptionPane.showMessageDialog(null, " Expression non vérifiée ");
	}
	else
	{ int indice=0;*/
	
	System.out.println(Verife.tab.get(0).getRacine());
	DefaultMutableTreeNode myRoot=new DefaultMutableTreeNode(Verife.tab.get(0).getRacine());
		for(int i =0;i< Verife.tab.size();i++)
		{int indice=0;
		DefaultMutableTreeNode myRoot1=new DefaultMutableTreeNode(Verife.tab.get(0).getRacine());	           
	          if(Verife.tab.get(i).getNoeudFils().size()>0)  
		       {
	        	  for(int j1 =0;j1< Verife.tab.get(i).getNoeudFils().size();j1++)
	        	  {
	              DefaultMutableTreeNode chap = new DefaultMutableTreeNode(Verife.tab.get(i).getNoeudFils().get(j1).getRacine());
	              myRoot1.add(chap);}
	               
	            }
		myRoot.add( myRoot1);
//	           if(Verife.tab.get(i).getNoeudFils().size()>0 && i<Verife.tab.get(i).getNoeudFils().size()-1)
//	           {
//	              for(int k=0;k<Verife.tab.get(i).getNoeudFils().size();k++)
//	               {
//	            	   if(Verife.tab.get(i+1).getRacine().equals(Verife.tab.get(i).getNoeudFils().get(k).getRacine())==true)
//	                    myRoot=dt.get(k);
//	               }
//	           }
	               }
	
	// Construction du modèle de l'arbre.
	
		
		
	
	DefaultTreeModel myModel = new DefaultTreeModel(myRoot);
	  final JTree tree = new JTree(myModel);
	  tree.setEditable(true);
	tree.setBounds(25, 10, 400, 600);
	contentPane.add(tree);
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
