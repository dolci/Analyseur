package Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Non_Terminaux {

	/**
	 * @param args
	 */
	    String libelle;
	    String regles;
	    ArrayList<Struct_Prem> fileprec;
	    ArrayList<String> filesuiv;
	   // ArrayList<String> reglePr;
	    ArrayList< Regle_Prem> premier ;
	    ArrayList <String> suivant;
	
	public Non_Terminaux(String l,String r)
	{
		libelle=l;
		regles=r;	
		premier= new ArrayList< Regle_Prem>();
		suivant  = new ArrayList<String>();
	}
	public  ArrayList <String> getSuiv()
	{
		return suivant;
	}
	public  ArrayList< Regle_Prem> getPrem()
	{
		return premier;
	}
	public String getlibelle()
	{
		return libelle;
	}
	public String getRegle()
	{
		return regles;
	}
	public void addPremier(Regle_Prem p)
	{
		premier.add(p);
	}
	/*public void addSuivant(String s)
	{
		suivant.add(s);
	}*/
	
	public void Determiner_Premier()
	{
		fileprec=new ArrayList<Struct_Prem>();
		filesuiv=new ArrayList<String>();

		   // System.out.println("regle = "+regles);
    	    StringTokenizer st=new StringTokenizer(regles,"[|]",false);
		    int nbre_regle=st.countTokens();
		  
		     String s=null;char c;
		     for(int j=0;j<nbre_regle;j++)
		      {
		             s=st.nextToken();
		           //  System.out.println("regle  "+j+"  :"+s);
		             c=s.charAt(0);
		             if(c>90 || c<65 )
		    	     {
		    	       Regle_Prem tmp=new Regle_Prem(c);
		    	       tmp.addRegle(s);
		    	      
		    	         if( Verifie_Existe(c)==-1)
		    	               addPremier(tmp);
		    	         else
		    	        	 premier.get(Verifie_Existe(c)).addRegle(s);
		    	    // System.out.println(precedent.get(0).regle.toString());
		    	      }
		              else
		              {
		            	  Struct_Prem sp=new Struct_Prem(""+c,s);
		            	  fileprec.add(sp);
		            	 /*
		            	  * if(s.charAt(1)=='1')
		            	  {
		            		  Struct_Prem sp=new Struct_Prem(""+c+s.charAt(1),s);
		            	  fileprec.add(sp);
		            	  }
		            	  else
		            	  {
		            		  Struct_Prem sp=new Struct_Prem(""+c,s);
			            	  fileprec.add(sp); 
		            	  }*/
		            	  //System.out.println("valeur "+Recherche_Terminaux(""+s.charAt(0)));
		               }
		         //   System.out.println(s);
		      }
		}
	
    public int Verifie_Existe(char c)
    { int j=-1;
    	
    	
    		for(int i=0;i<premier.size();i++ )
    		
    			if(premier.get(i).Retourneterminaux()==c)
    				{ j=i;break;}
    				
    		return j;
    	
    	
    }
}
