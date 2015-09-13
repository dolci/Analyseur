package Data;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class Liste_Non_Terminaux {

	/**
	 * @param args
	 */
    ArrayList <Non_Terminaux> liste;
    
    public Liste_Non_Terminaux()
    {
    	liste=new  ArrayList <Non_Terminaux>();
    }
	public void addTerminaux(Non_Terminaux t)
	{
		
		liste.add(t);
	}
	public ArrayList <Non_Terminaux> getListeN()
	{
		return liste;
	}
    public void Determiner_Premier_Etap1()
	{
		for(int i=0; i<liste.size();i++)
		{
    	    liste.get(i).Determiner_Premier();   
		}
	
	}
	
    public void Determiner_Premier_Etap2()
    {
    	String s,s1; int j,nbre_£,nbre,car=0,indice=0;
    	boolean test=true;
       // ArrayList 
    	for(int i=0; i<liste.size();i++)
    	{
    		nbre=liste.get(i).fileprec.size();
    		//System.out.println(" val = "+liste.get(i).libelle);
    		if(liste.get(i).fileprec.size()>0 )
    		{
    			 j=0;nbre_£=0;
    		    while(j<liste.get(i).fileprec.size())
    			 {   
    		    	 s=liste.get(i).fileprec.get(j).get_prem();
    		    	 s1=liste.get(i).fileprec.get(j).get_regle();
    		    	// System.out.println(" chaine = "+s+" regle "+s1);
    		         indice=Recherche_Non_Terminaux(s);         
    				 if(liste.get(indice).fileprec.size()>0)
				       {//System.out.println(" sverif"+liste.get(indice).fileprec.get); 
    					   if(liste.get(indice).premier.size()>0)
    				        {
    						    if(liste.get(indice).Verifie_Existe('£')>=0)
    				    	    	{
    				    	    	  nbre_£++; 
    				    	    	  for(int l=0;l<liste.get(indice).premier.size();l++)
    				    	    		  if(l!=liste.get(indice).Verifie_Existe('£'))
    				    	    			  {
    				    	    			     Regle_Prem rp=new  Regle_Prem(liste.get(indice).premier.get(l).Retourneterminaux());
    				    	    			     rp.addRegle(s1);
    				    	    			    liste.get(i).premier.add(rp);
    				    	    	          }
    				    	        }
    				    	    else
    				    	    	{
    				    	    	    for(int l=0;l<liste.get(indice).premier.size();l++)
    				    	    	     {
  				    	    			     Regle_Prem rp=new  Regle_Prem(liste.get(indice).premier.get(l).Retourneterminaux());
  				    	    			     rp.addRegle(s1);
  				    	    			    liste.get(i).premier.add(rp);//liste.get(i).premier.addAll(liste.get(indice).premier);
  				    	    			  }
    				    	    	}
    				    	    	
    				         }
    					   if( liste.get(indice).Verifie_Existe('£')>=0 & s1.indexOf(s)>=0)
    						 {
    						   
    	    	    	        car= s1.indexOf(s)+1;
    					       if(car<s1.length())
    					       {
    						    if(s1.charAt(car)>=90 || s1.charAt(car)<=65 )
    				    	     {    						    
    				    	       Regle_Prem tmp=new Regle_Prem(s1.charAt(car));
    				    	        tmp.addRegle(s1);
    				    	        if( liste.get(Recherche_Non_Terminaux(liste.get(i).fileprec.get(j).get_prem())).Verifie_Existe(s1.charAt(car))==-1)
    				    	               liste.get(i).addPremier(tmp);
    				    	      }
    				              else
    				              {
    				            	  Struct_Prem sp=new Struct_Prem(""+s1.charAt(car),s);
    				            	  liste.get(i).fileprec.add(sp);
    					          }
    						    
    				          }
    					   }
    					  
    					 liste.get(i).fileprec.remove(j);
    					// System.out.println("apres supp "+liste.get(i).fileprec.size());
					     for(int k=0;k< liste.get(indice).fileprec.size();k++)
					    	   liste.get(i).fileprec.add(new Struct_Prem(liste.get(indice).fileprec.get(k).get_prem(),s1));	      
					    }
    				// System.out.println("apres ajout "+liste.get(i).fileprec.size());
    				 // si £ appertient au premier 
    				j++;
    				 if(liste.get(indice).fileprec.size()>0)
    					    { j=0;}
    				 else
    				 { j++;
    					 }
    					  	
    		    }
    			if(nbre_£==nbre )
    			{ System.out.println(nbre_£);
    			Regle_Prem tmp=new Regle_Prem('£');
    	        tmp.addRegle("£");
    			liste.get(i).premier.add(tmp);}
    		}
    	}
   
    	
    }
    
    public int Recherche_Non_Terminaux(String s)
    {
    	int j=-1;
    	
    	for(int i=0;i<liste.size();i++)
    		if(s.equals(liste.get(i).libelle))
    		{j=i;return j;}
    	return j;
    }
    
    public void affiche()
    {
    	for(int i=0; i<liste.size();i++)
		{
    		System.out.println( "NT--> "+liste.get(i).libelle);
			if(liste.get(i).premier.size()>0)
			{
				for(int j=0; j<liste.get(i).premier.size();j++)
					for(int l=0; l<liste.get(i).premier.get(j).regle.size();l++)
    	          System.out.println(" j "+j +" l "+l+liste.get(i).premier.get(j).Retourneterminaux()+ " regme "+liste.get(i).premier.get(j).RetournerRegle().get(l)); 
			      System.out.println();
			}
			if (liste.get(i).fileprec!=null)
			{	
				for(int k=0; k<liste.get(i).fileprec.size();k++)
		    	       System.out.println("k "+k+" "+liste.get(i).fileprec.get(k).get_prem() +" regle "+liste.get(i).fileprec.get(k).get_regle()); 
		    }  
		}
    }
    
    
    /**
     * 
     */
    public void Suppression_Redondance()
    {
    	 ArrayList<Integer> tab;
         
     	for(int i=0;i<liste.size();i++)
     	{tab= new ArrayList<Integer>();
     		if(liste.get(i).premier.size()>0)
     		{
    			
    			for(int j=0;j<liste.get(i).premier.size()-1;j++)
    			{
    				for(int l=j+1;l<liste.get(i).premier.size();l++)
        			{
    				   String b=null,a=null;	
    				  if(liste.get(i).premier.get(j).Retourneterminaux()==liste.get(i).premier.get(l).Retourneterminaux())
    				  {
                           a=liste.get(i).premier.get(j).RetournerRegle().get(0);
                           b=liste.get(i).premier.get(l).RetournerRegle().get(0);
                          
    					  if(a.equals(b)==false)
    					  {
    					     liste.get(i).premier.get(j).addRegle(liste.get(i).premier.get(l).RetournerRegle().get(0));
    					     liste.get(i).premier.remove(l);
    					   }
    					  
    				  }
        			}
    			}
    		}
    		
    		
    	}
    }
     
    public void Determiner_Premier3()
    { 
   	
    	for(int i=0;i<liste.size();i++)
    	{
        	if(liste.get(i).fileprec.size()>0)
    		{
    		    for(int j=0;j<liste.get(i).fileprec.size();j++)
			   	{ int indice=Recherche_Non_Terminaux(liste.get(i).fileprec.get(j).get_prem());
			    for(int l=0;l<liste.get(indice).premier.size();l++)
			     { 
			    	if(liste.get(i).Verifie_Existe(liste.get(indice).premier.get(l).Retourneterminaux())>-1) 
    		    	   liste.get(i).premier.get(liste.get(i).Verifie_Existe(liste.get(indice).premier.get(l).Retourneterminaux())).addRegle(liste.get(i).fileprec.get(j).get_regle()); 					
    			     
    			     else
    			     { Regle_Prem rp=new Regle_Prem(liste.get(indice).premier.get(l).Retourneterminaux());
    			      rp.addRegle(liste.get(i).fileprec.get(j).get_regle());
    				   liste.get(i).premier.add(rp)  ;
    			     }
			    	
    		       }
			    }
    		    liste.get(i).fileprec=null;
    	}
    	}
    }
    
	public void Determiner_Regle_Suiv()
	{
		liste.get(0).suivant.add("$");
		for(int i=0;i<liste.size();i++)
		{
			for(int j=0;j<liste.size();j++)
			{					
				if(liste.get(j).regles.contains(liste.get(i).libelle))
				{
					 StringTokenizer st=new StringTokenizer(liste.get(j).regles,"[|]",false);
					 int nbre_regle=st.countTokens();
					 for(int l=0;l<nbre_regle;l++)
					 { 
						 String s;
						 s=st.nextToken();
						 if(s.contains(liste.get(i).libelle))
						 {	 if( s.indexOf(liste.get(i).libelle)== s.length()-1 && liste.get(j).libelle.equals(s.substring(s.indexOf(liste.get(i).libelle)))==false)
						      {//System.out.println("  "+liste.get(i).libelle+" "+s+s.indexOf(liste.get(i).libelle)+liste.get(j).libelle);
							     liste.get(i).filesuiv.add(liste.get(j).libelle);
							  }
						      else
						      {//non terminaux TB
						    	  if(Recherche_Non_Terminaux(s.substring(s.indexOf(liste.get(i).libelle)+1))>-1)
						    	  { int indice=Recherche_Non_Terminaux(s.substring(s.indexOf(liste.get(i).libelle)+1));
						    		  if(liste.get(indice).Verifie_Existe('£')>-1)//£ appertient premier 
						    		  {
						    			  for(int k=0;k<liste.get(indice).premier.size();k++)
						    				  if(k!=liste.get(indice).Verifie_Existe('£'))
						    				  liste.get(i).suivant.add(liste.get(indice).premier.get(k).Retourneterminaux()+"");
						    			     if(s.indexOf(liste.get(i).libelle)+1==s.length()-1)  
						    		    	  liste.get(i).filesuiv.add(liste.get(j).libelle);
						    			  //longueur de chaine
						    		  }
						    		  else
						    			  for(int k=0;k<liste.get(indice).premier.size();k++)
						    				  liste.get(i).suivant.add(liste.get(indice).premier.get(k).Retourneterminaux()+"");
						    	  }
						    	  else
						    		  liste.get(i).suivant.add(s.substring(s.indexOf(liste.get(i).libelle)+1));
						      }
						 }
						 
					 }
				}
			}
			Suppression_Red_Suiv(i);
		}
	}
	public void Suppression_Red_Suiv(int indice)
	{
		for(int i=0;i<liste.get(indice).suivant.size()-1;i++)
		{
			for(int j=i+1;j<liste.get(indice).suivant.size();j++)
			{
				if(liste.get(indice).suivant.get(i).equals(liste.get(indice).suivant.get(j))==true)
					{liste.get(indice).suivant.remove(j);j--;}
			}
			
		}
		if(liste.get(indice).filesuiv.size() >0)
		{
			for(int i=0;i<liste.get(indice).filesuiv.size()-1;i++)
			{
				for(int j=i+1;j<liste.get(indice).filesuiv.size();j++)
				{
					if(liste.get(indice).filesuiv.get(i).equals(liste.get(indice).filesuiv.get(j))==true)
						{liste.get(indice).filesuiv.remove(j);j--;}
				}
				
			}
			
		}
	}
	public void Suivant()
	{
		for(int i=0;i<liste.size();i++)
		{	
		    if(liste.get(i).filesuiv !=null)
		     { int j=0;
			    while(j<liste.get(i).filesuiv.size())
			       { 
			    	  int indice=Recherche_Non_Terminaux(liste.get(i).filesuiv.get(j));
			         /*  if(liste.get(indice).suivant!=null)			           
			        	   liste.get(i).suivant.addAll(liste.get(indice).suivant);*/
			           if(liste.get(indice).filesuiv !=null )
			           { 
			        	   if(liste.get(indice).suivant!=null)			           
				        	   liste.get(i).suivant.addAll(liste.get(indice).suivant);
			        	   liste.get(i).filesuiv.remove(j);
			        	   liste.get(i).filesuiv.addAll(liste.get(indice).filesuiv);
			           }
			           else
			           {
			        	   liste.get(i).suivant.addAll(liste.get(indice).suivant);
			        	   j++;
			           }
			           
		            }
			    Suppression_Red_Suiv(i);
			    }
		   
		 }
	}
   
	public void affiche_Suiv()
	{
		for(int i=0;i<liste.size();i++)
		{
			System.out.print(liste.get(i).libelle+" -->");
			for(int j=0;j<liste.get(i).suivant.size();j++)
				System.out.print(liste.get(i).suivant.get(j)+"   ");System.out.println();
			if(liste.get(i).filesuiv.size()>0)
				for(int j=0;j<liste.get(i).filesuiv.size();j++)
					System.out.print(liste.get(i).filesuiv.get(j)+"   ");
			System.out.println();
			}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
