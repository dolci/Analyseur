package Graphique;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import Data.LectureFichier;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;

public class AnlyseLL1 extends JFrame {

	private JPanel contentPane;
	JPanel panel, panelT;
	static LectureFichier lf;
	JLabel lblNewLabel_1,lblNewLabel_2,jlt,jlnt;
	private JLabel lblNewLabel_3;
	private JPanel panel_1,panel_2,panel_3;
	private JScrollPane scrollPane,scrollPane1;
	private JTable table,table1;
	private JPanel panel_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					AnlyseLL1 frame = new AnlyseLL1();
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
	public AnlyseLL1() {
		setTitle("Analyse");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				String s="";
				
				lblNewLabel_3.setText("Grammaire");
			    lf=new LectureFichier(MainProgram.textField.getText());	
				lf.Regle_Production();
				jlt.setText(" "+lf.GetTerminaux()[0]);
				for(int i=1;i<lf.getListNterminaux().getListeN().size();i++)
				{
					jlt.setText(jlt.getText()+"   "+lf.GetTerminaux()[i]);
				}
				for(int i=0;i<lf.getListNterminaux().getListeN().size();i++)
				{
					int w = 30 +(30*i);
					JLabel jl=createJLabel();
					s+=lf.getListNterminaux().getListeN().get(i).getlibelle()+"   ";
					jl.setText(lf.getListNterminaux().getListeN().get(i).getlibelle());
					jl.setFont(new Font("Arial Black", Font.BOLD, 20));
					jl.setForeground(Color.BLUE);
					
					JLabel jl1= createJLabel();
					jl1.setIcon(new ImageIcon("fleche-bleu.png"));
					
		    		
		    		JLabel jl2= createJLabel();
		    		jl2.setFont(new Font("Arial Black", Font.BOLD, 12));
		    		jl2.setText(lf.getListNterminaux().getListeN().get(i).getRegle());
		    		jl.setBounds(10,w,50, 20);
		    		jl1.setBounds(60, w,50, 20);
		    		jl2.setBounds(110,w,100,20);
		    		panelT.add(jl);
		    		panelT.add(jl1);
		    		panelT.add(jl2);
		    		
				}
				jlnt.setText(s);
			      lf.getListNterminaux().Determiner_Premier_Etap1();  
			      lf.getListNterminaux().Determiner_Premier_Etap2(); 
			      lf.getListNterminaux().Suppression_Redondance();
			      lf.getListNterminaux().Determiner_Premier3();
			      lf.getListNterminaux().Determiner_Regle_Suiv();
			      lf.getListNterminaux().Suivant();
			     
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Nouveau dossier\\Analyse_Program\\Fonts-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//lf.Regle_Production();
		
		JLabel lblNewLabel = new JLabel("Analyseur Syntaxique");
		lblNewLabel.setBounds(134, 16, 203, 37);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		 panel = new JPanel();
		 panel.setBounds(16, 64, 440, 73);
		 panel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(64, 64, 64), null, null, null));
		panel.setLayout(null);
		
	    lblNewLabel_2 = new JLabel("Les Terminaux :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblNewLabel_2.setBounds(10, 5, 112, 28);
		panel.add(lblNewLabel_2);
		
	    lblNewLabel_1 = new JLabel("Les non-Terminaux :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(10, 30, 126, 32);
		panel.add(lblNewLabel_1);
		
	    jlt = new JLabel("New label");
		jlt.setForeground(Color.MAGENTA);
		jlt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		jlt.setBounds(128, 13, 160, 14);
		panel.add(jlt);
		
		jlnt = new JLabel("New label");
		jlnt.setForeground(Color.MAGENTA);
		jlnt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		jlnt.setBounds(138, 40, 160, 14);
		panel.add(jlnt);
		
		panelT = new JPanel();
		panelT.setBounds(16, 148, 440, 172);
		panelT.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, null, null, null));
		panelT.setLayout(null);
		
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblNewLabel_3.setBounds(195, 0, 185, 24);
		panelT.add(lblNewLabel_3);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, null, null, null));
		panel_1.setBounds(467, 64, 107, 256);
		
		JButton btnNewButton_1 = new JButton("AnalyseLL");
		btnNewButton_1.setBounds(9, 11, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  
				 
				if(lf.Grammaire_LL1()==true )
				{
				  String col[]={"Non Terminaux","Premiers","Suivant"};
				  String [][]data;
				  data=new String[lf.getListNterminaux().getListeN().size()][3];
				  for(int i=0;i<lf.getListNterminaux().getListeN().size();i++)
					  data[i][1]=data[i][2]="";
				  for(int i=0;i<lf.getListNterminaux().getListeN().size();i++)
					  data[i][0]=lf.getListNterminaux().getListeN().get(i).getlibelle();
				  for(int i=0;i<lf.getListNterminaux().getListeN().size();i++)
				  {  for( int j=0;j<lf.getListNterminaux().getListeN().get(i).getPrem().size();j++)
					  data[i][1]+=lf.getListNterminaux().getListeN().get(i).getPrem().get(j).Retourneterminaux()+" ";
					  for( int j=0;j<lf.getListNterminaux().getListeN().get(i).getSuiv().size();j++)
					  data[i][2]+=lf.getListNterminaux().getListeN().get(i).getSuiv().get(j)+"  "; 
					  System.out.println(data[i][0]);
				  }
				  
				  DefaultTableModel model = new DefaultTableModel(data, col);
				
				  table = new JTable(model){
		 	    	    public Component prepareRenderer(TableCellRenderer renderer,int Index_row, int Index_col) {
		 	    	        Component comp = super.prepareRenderer(renderer,Index_row, Index_col);
		 	    	       JComponent jcomp = (JComponent)comp;
		 	    	            
		 	    	            if (Index_col ==0 ) {
		 	    	                comp.setBackground(Color.getHSBColor(0.5f,0.5f,0.8f));
		 	    	                ((JLabel) comp).setHorizontalAlignment(SwingConstants.CENTER);
		 	    	            } else {
		 	    	                comp.setBackground(Color.getColor("gainsboro"));
		 	    	            }
		 	    	            return comp;
		 	    	        }
		 	    	   @Override
		 	    	    public boolean isCellEditable(int row, int column) {
		 	    	       //all cells false
		 	    	       return false;
		 	    	    }
		 	    	    };
				  JTableHeader anHeader = table.getTableHeader();  
		           anHeader.setForeground(new Color(0).yellow);
		           anHeader.setBackground(new Color(0).black);
		           panel_3.add(table.getTableHeader(),BorderLayout.NORTH);
		           scrollPane = new JScrollPane(table);
		   		   scrollPane.setBounds(50, 17, 420, 110);
		   		  panel_3.add(scrollPane);
		   		  String col1[];
		   		  col1=new String[lf.getListterminaux().length+1];
		   		  col1[0]="   ";
		   		  for(int i=0;i<lf.getListterminaux().length;i++)
		   		  {col1[i+1]=lf.getListterminaux()[i];}
		   		  String [][]data1=new String [lf.getListNterminaux().getListeN().size()][col1.length];
		   		for(int i=0;i<lf.getListNterminaux().getListeN().size();i++)
		   		{
		   			data1[i][0]=lf.getListNterminaux().getListeN().get(i).getlibelle();
		   			for(int j=1;j<col1.length;j++)
		   				data1[i][j]=lf.Tableau_AnalyseLL()[i][j-1];
		   			}
		   		 DefaultTableModel model1 = new DefaultTableModel(data1, col1);
		   		 
		   		table1 = new JTable(model1){
	 	    	    public Component prepareRenderer(TableCellRenderer renderer,int Index_row, int Index_col) {
	 	    	        Component comp = super.prepareRenderer(renderer,Index_row, Index_col);
	 	    	       JComponent jcomp = (JComponent)comp;
	 	    	            
	 	    	            if (Index_col ==0 ) {
	 	    	                comp.setBackground(Color.getHSBColor(0.5f,0.5f,0.8f));
	 	    	                ((JLabel) comp).setHorizontalAlignment(SwingConstants.CENTER);
	 	    	            } else {
	 	    	                comp.setBackground(Color.getColor("gainsboro"));
	 	    	            }
	 	    	            return comp;
	 	    	        }
	 	    	   @Override
	 	    	    public boolean isCellEditable(int row, int column) {
	 	    	       //all cells false
	 	    	       return false;
	 	    	    }
	 	    	    };
			  JTableHeader anHeader1 = table1.getTableHeader();  
	           anHeader1.setForeground(new Color(0).yellow);
	           anHeader1.setBackground(new Color(0).black);
	           panel_4.add(table1.getTableHeader(),BorderLayout.NORTH);
	           scrollPane1 = new JScrollPane(table1);
	   		   scrollPane1.setBounds(50, 17, 420, 110);
	   		  panel_4.add(scrollPane1);	
		   	        
		           
					JOptionPane.showMessageDialog(null, " LL1 ");
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "n'est pas LL1 ");
				}
				
			}
			
		});
		panel_1.setLayout(null);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Recurcivit\u00E9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setBounds(9, 80, 89, 23);
		panel_1.add(btnNewButton);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(panel);
		contentPane.add(panelT);
		contentPane.add(panel_1);
		
		JButton btnNewButton_2 = new JButton("V\u00E9rifier");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Verife ver=new Verife();
				ver.show();
			}
		});
		btnNewButton_2.setBounds(9, 45, 89, 23);
		panel_1.add(btnNewButton_2);
		
		panel_2 = new JPanel();
		panel_2.setBounds(16, 331, 551, 404);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("\t \t Premiers & Suivants");
		lblNewLabel_4.setBounds(178, 11, 212, 17);
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Segoe Script", Font.BOLD, 16));
		panel_2.add(lblNewLabel_4);
		
		panel_3 = new JPanel();
		panel_3.setBounds(10, 26, 511, 150);
		panel_2.add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setBounds(10, 198, 511, 150);
		panel_2.add(panel_4);
		
		JLabel lblTableauLl = new JLabel("\t Tableau LL1");
		lblTableauLl.setForeground(Color.RED);
		lblTableauLl.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblTableauLl.setBounds(195, 177, 253, 27);
		panel_2.add(lblTableauLl);
		
		
		
		
		//scrollPane.setViewportView(table);
		
	}
	private static JLabel createJLabel() {
		JLabel j =new JLabel();
		j.setVisible(true);
		return j ;
	}
}
