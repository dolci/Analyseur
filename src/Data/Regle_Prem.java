package Data;
import java.util.ArrayList;
public class Regle_Prem {

	
		
		char name;
		ArrayList <String>regle;
		
		public Regle_Prem(char x)
		{
			name=x;
			regle=new ArrayList<String>();
		}
		public char Retourneterminaux()
		{
			return name;
		}
		public ArrayList <String> RetournerRegle()
		{
			return regle;
		}
		public void addRegle(String a)
		{	
			regle.add(a);
		}
       
	}
