package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;


public class LectureFichier {

	/**
	 * @param args
	 */
	 private String nomFich;
     private BufferedReader sortie;
     Liste_Non_Terminaux list_ter;
     String [] Terminaux;
     Stack <String> pile;
    //static ArrayList <ArrayList> Table_LL = new ArrayList<ArrayList>();
     /*                              */
    /*                              */
    
    public LectureFichier(String nomFich)
    {
        try {
	           sortie=new BufferedReader(new FileReader(nomFich));
             }
        catch (FileNotFoundException e) 
             { e.printStackTrace();  }
     }
	public Liste_Non_Terminaux getListNterminaux()
	{
		return list_ter;
	}
	public String[] getListterminaux()
	{
		return Terminaux;
	}
    public void Regle_Production()
    
    {
   	    String ligne;
   	    int i=1;
   	     list_ter=new Liste_Non_Terminaux ();
   	    try {
   	    	   
   	    	   ligne=sortie.readLine(); 
   	    	   Terminaux =new String[ligne.split(" ").length];
   	    	   Terminaux=ligne.split(":");
   	    	   System.out.println(ligne);
   	    	   ligne=sortie.readLine();
   	    	System.out.println(ligne);
   	    	   while(ligne!=null)
   	    	   {
   	    		
			     StringTokenizer st=new StringTokenizer(ligne,"[->]",false);
			     Non_Terminaux t=new Non_Terminaux(st.nextToken(),st.nextToken());				
			     list_ter.addTerminaux(t); 
				 ligne=sortie.readLine();
				 
   	    	    }
		   } 
   	 catch (IOException e) {
			
			e.printStackTrace();
		}
   	 
    }
 
    public String[][] Tableau_AnalyseLL()
    {
    	String tab[][];
    	tab=new String[list_ter.liste.size()][Terminaux.length];
    	for(int i=0;i<list_ter.liste.size();i++)
    		for(int j=0;j<Terminaux.length;j++)
    		    tab[i][j]="";
    	
    	for(int i=0;i<list_ter.liste.size();i++)
    	{	
    		if(list_ter.liste.get(i).Verifie_Existe('£')>=0)
		     {
			
			      for(int l=0;l<list_ter.liste.get(i).suivant.size();l++)
			       {  int m=0;
			         // System.out.print("suivant "+list_ter.liste.get(i).suivant.get(l)+" ");
			          while(m<Terminaux.length)
			           {
		                   if((list_ter.liste.get(i).suivant.get(l)).equals(Terminaux[m])==true)
		                   { tab[i][m]+="£";break;}
		                       m++;
			           }
			         }
		       }
    		
    		for(int j=0;j<Terminaux.length;j++)
    		{ int k=0; 
    		  
    			   while(k<list_ter.liste.get(i).premier.size()) 
    			   { 
    				    if ((list_ter.liste.get(i).premier.get(k).Retourneterminaux()+"").equals(Terminaux[j])==true && k!=list_ter.liste.get(i).Verifie_Existe('$'))
    				       
    				          {
    					        String s="";
        				        for(int l=0;l<list_ter.liste.get(i).premier.get(k).RetournerRegle().size();l++)
        					     s+=list_ter.liste.get(i).premier.get(k).RetournerRegle().get(l)+"";	   
        				         tab[i][j]+=s ;
        				         break;
    				         }
    				         k++;
    			    }
    		}	  
    		
    	}
    	return tab;
    }
    public int[] Pos_Suiv(int indice)
    { int tab[]=new int[list_ter.liste.get(indice).suivant.size()];
      
        for(int i=0;i<list_ter.liste.get(indice).suivant.size();i++)
        	 for(int j=0;j<Terminaux.length;j++)
        		 if(list_ter.liste.get(indice).suivant.get(i).equals(Terminaux[j])==true)
        		 { tab[i]=j; break;}
    	return tab;
    }
    public void affiche()
	
    {
    	for(int i=0;i<list_ter.liste.size();i++)
    	{
    		System.out.print(	list_ter.liste.get(i).libelle);
    		System.out.println("-->"+	list_ter.liste.get(i).regles);
    	}
    }
 
	public void Initialiser_Stack()
	 {
		 
		 pile =new Stack();
		 pile.push("$");
		 pile.push(list_ter.liste.get(0).libelle);
	 }
	public boolean Mot_Accept()
	{
		return pile.peek().equals("$");
	}
	public int Indice_Terminaux(String m)
	{
		for(int i=0;i<Terminaux.length;i++)
		
			if(Terminaux[i].equals(m)==true)
				return i;
	  return -1;
	}
    public ArrayList<Noeud> Arbre_Derivation(String mot)
	{
    	String X,regle;int i=0;
    	String [][] tab_ll;
    	Noeud arbre_de;
    	tab_ll=Tableau_AnalyseLL();
    	Initialiser_Stack();
    	ArrayList<Noeud> tab;
    	tab=new ArrayList<Noeud> ();
    	
    	int k=0;
    	
    	boolean erreur=false;
    	boolean accepte=false;
		while(!accepte )
		{
			X=pile.peek();
			
			System.out.println(" en tete pile "+X);
			if(list_ter.Recherche_Non_Terminaux(X)>-1)
			{
				arbre_de=new Noeud(X);
				regle=tab_ll[list_ter.Recherche_Non_Terminaux(X)][Indice_Terminaux(mot.substring(i,i+1))];
				System.out.println(" regle "+regle+" a= "+mot.substring(i,i+1));  
				if(regle.equals("")==false)
				    {
				        
				         if(regle.length()==1)
				        	 {
				        	   if(regle.equals("£"))
				        	   { String l=pile.pop(); arbre_de.addNoeud(new Noeud("£"));}
				        	   else
				        	   { pile.pop();pile.push(regle); arbre_de.addNoeud(new Noeud(regle));}
				        	 }
				         else
				         {
				        	 pile.pop();
				        	 for(int j=0;j<regle.length();j++)
					         { 
					        	System.out.println(regle.substring(j,j+1));
					           
					         }
				         for(int j=regle.length()-1;j>=0;j--)
				         { 
				        	System.out.println(regle.substring(j,j+1));
				        	 pile.push(regle.substring(j,j+1));
				        	 arbre_de.addNoeud(new Noeud(regle.substring(j,j+1)));
				         }
				         }
				  tab.add(arbre_de);
			        }
				    else // ase vide dans la tableau
				    {
					    erreur=true;tab=null;break;
				    }
			}
			else
			{
				 if(Mot_Accept())
				 {
					 if(mot.substring(i,i+1).equals("$"))
						{accepte=true; }
					 else
					 { erreur=true;tab=null;break;}
				 }
				 else
				 {
					 if(X.equals(mot.substring(i, i+1)))
					 {
						//tmp=tmp.noeud.get(k+1);
						 i++;
						 pile.pop();
						 System.out.println(accepte +" "+i);

					 }
					 else
					 { erreur=true;tab=null;break;}
					 
				 }
				
			}
			for(int l=0;l<pile.size();l++)
				System.out.println("  "+pile.elementAt(l));
			

		}
		int index=0;
		for(int m=0;m<tab.size();m++)
			{
			System.out.println("parent "+tab.get(m).racine);
			for(int n=0;n<tab.get(m).noeud.size();n++)
				System.out.print(tab.get(m).noeud.get(n).racine);
			}
		System.out.println(" "+erreur);
		return tab;
	}
    public boolean Grammaire_LL1()
    {
    	boolean ll1=true;
    	
    	
    	for(int i=0;i<list_ter.liste.size();i++)
    		for(int j=0;j<list_ter.liste.get(i).premier.size();j++)
    			if(list_ter.liste.get(i).premier.get(j).regle.size()>1)
    				{ll1=false;break; }
    	
    	return ll1;
    }
    public Noeud RechecherNT_Arbre(Noeud arbre_de,String symbole)
    { Noeud tmp=null;
    	
    
    	return tmp;
    }
    public boolean Eliminer_RecursiviteImmediat(int indice)
    {
    	String regle="",r1="";
    	ArrayList<String> s=new ArrayList<String>() ;
    	ArrayList<String> s1=new ArrayList<String>() ;boolean recursive=false;
   
    	StringTokenizer st=new StringTokenizer(list_ter.liste.get(indice).regles,"[|]",false);
    	//System.out.println("regle");
    	//System.out.println(list_ter.liste.get(indice).regles);
		 int nbre_regle=st.countTokens();
		 for(int i=0;i<nbre_regle;i++)
		 {
			 r1=st.nextToken();
			 if(list_ter.liste.get(indice).libelle.equals(r1.substring(0,1)))
			  { 
				 s.add(r1.substring(1)); 
			    recursive=true;
			  
			  }
			 else
				 s1.add(r1);	
			 
		 }
		 if(recursive)
		 {
			 list_ter.liste.get(indice).regles="";
			 for(int i=0;i<s1.size();i++)	 
			  {
				 list_ter.liste.get(indice).regles+=s1.get(i)+list_ter.liste.get(indice).libelle+"1";
					 list_ter.liste.get(indice).regles+="|";
			  }
			 list_ter.liste.get(indice).regles=list_ter.liste.get(indice).regles.substring(0, list_ter.liste.get(indice).regles.length()-1);
			// System.out.println( "after "+list_ter.liste.get(indice).regles);
			 //System.out.println("size"+s.size());
			 for(int i=0;i<s.size();i++)	 
			   regle+=s.get(i)+list_ter.liste.get(indice).libelle+"1|";
			   regle+="£";
			   System.out.println("****************** "+regle);
			   Non_Terminaux t=new Non_Terminaux(list_ter.liste.get(indice).libelle+"1",regle);
			   list_ter.liste.add(t);
		 }
		 s=s1=null;
		 return recursive;
    }
    public void Elimination_RecursivviteGauche()
    {
    	String s,s1="";int nbre_regle1=0;
    	// Rendre_propre 
    	// recursivite à gauche +factorisation
    	for(int i=0;i<list_ter.liste.size()-1;i++)
    	{
    		 for(int j=0;j<=i-1;j++)
    		  {
    			 StringTokenizer st=new StringTokenizer(list_ter.liste.get(i).regles,"[|]",false);
    			 s1="";
    			 int nbre_regle=st.countTokens();
    			  for(int k=0;k<nbre_regle;k++)
   		          {s1="";
   		              s=st.nextToken();
   		              if(s.substring(0, 1).equals((list_ter.liste.get(j).libelle))==true)
   		              {	
   		            	  StringTokenizer st1=new StringTokenizer(list_ter.liste.get(j).regles,"[|]",false);
   		            	  //System.out.println("+ "+list_ter.liste.get(j).regles);
   		    			  nbre_regle1=st1.countTokens();
   		    			  for(int l=0;l<nbre_regle1;l++)
   	   		              {
   		    				s1+=st1.nextToken()+s.substring(1)+"|";
   		    				//System.out.println("substrign ! "+s.substring(1));
   	   		              }
   		    			  s1=s1.substring(0, s1.length()-1);
   		    			list_ter.liste.get(i).regles=list_ter.liste.get(i).regles.replace(s, s1);
   		    			//System.out.println("  "+ list_ter.liste.get(i).regles.replace(s, s1)+" "+i);
   		              }
   		              
   		              k+=nbre_regle1;
   		              
    				}	
    		}
    		//eliminer recursivité immediat	
    		 Eliminer_RecursiviteImmediat(i);
    			
    	}
    }
	public void Factorisation_Gauche()
	{
		String s1 [];
		for(int i=0;i<list_ter.liste.size();i++)
		{
			s1=list_ter.liste.get(i).regles.split("|");
			 int nbre_regle=s1.length;
			  for(int j=0;j<nbre_regle;j++)
			  {
				  
			  }
		 	
		}
	}
    public String [] GetTerminaux()
    {
    	return Terminaux;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
      LectureFichier l=new LectureFichier("Exemple_Prem.txt");
      l.Regle_Production();
      l.list_ter.Determiner_Premier_Etap1();  
      l.list_ter.Determiner_Premier_Etap2(); 
      l.list_ter.Suppression_Redondance();
      l.list_ter.Determiner_Premier3();
      l.list_ter.affiche();
      l.list_ter.Determiner_Regle_Suiv();
      l.list_ter.Suivant();
      l.list_ter.affiche_Suiv();
      for(int i=0;i<l.Terminaux.length;i++)
  	  {System.out.print("  "+l.Terminaux[i]+"      ");	}
      
     l.Tableau_AnalyseLL();
      System.out.println();
      for(int i=0;i<l.Tableau_AnalyseLL().length;i++)
  	  {	//System.out.print(l.list_ter.liste.get(i).libelle+"  ");
    	  for(int j=0;j<l.Tableau_AnalyseLL()[i].length;j++)
  		
  		{
  		   System.out.print("  "+l.Tableau_AnalyseLL()[i][j]+"    ");
  		} 
    	  System.out.println();
  	}
 l.Arbre_Derivation("n+n*n$");
       System.out.println(l.Grammaire_LL1());
      
     
  // l.Elimination_RecursivviteGauche();
  /* for(int i=0;i<l.list_ter.liste.size();i++)
 	  System.out.println(l.list_ter.liste.get(i).libelle+" -> "+l.list_ter.liste.get(i).regles);
   */ 
   	}

}