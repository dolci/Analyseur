package Data;

import java.util.ArrayList;



	public class Regle_Suiv {
		
		char name;
		ArrayList<String>regle;
		
		public Regle_Suiv(char x)
		{
			name=x;
			regle=new ArrayList<String>();
		}
		public void addRegle(String a)
		{	
			regle.add(a);
		}

	}