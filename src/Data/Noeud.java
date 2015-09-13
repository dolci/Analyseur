package Data;

import java.util.ArrayList;

public class Noeud {
	String racine;
	ArrayList<Noeud> noeud;

	public Noeud(String x)
	{
		racine=x;
		noeud =new ArrayList<Noeud>();
	}
	public void addNoeud(Noeud a)
	{
		noeud.add(a);
	}
  public  String getRacine()
  {
	  return racine;
  }
  public ArrayList<Noeud> getNoeudFils()
  {
	  return noeud;
  }
}
