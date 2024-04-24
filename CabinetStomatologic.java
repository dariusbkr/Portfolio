import java.util.*;
import java.io.*;
public class CabinetStomatologic {
	
	
	public static boolean validareUser(String[] info)
	{
		if(info[0].length()<3)
		{
			return false;
		}
		int litereMari=0;
		char[] v= info[1].toCharArray();
		for(int i=0;i<v.length;i++)
		{
		if(!Character.isLetterOrDigit(v[i]))
		{
			return false;
		}
		}
		for(int i=0;i<v.length;i++)
		{
			if(Character.isUpperCase(v[i]))
			{
				litereMari++;
			}
		}
		if(litereMari<1)
		{
			return false;
		}
		
		if( !(info[2].equalsIgnoreCase("pacient") || info[2].equalsIgnoreCase("medic") || 
			info[2].equalsIgnoreCase("administrator")) )
		{
			return false;
		}
		
		return true;

	}
	//functie de afisare - cerinta 3
	public static void afisare(ArrayList<Medic> listaUseri)
	{
		System.out.println(listaUseri.size());
		//primul element
		System.out.println(listaUseri.get(0));
		//ultimul element
		System.out.println(listaUseri.get(listaUseri.size()-1));
	}

	//cerinta d - testare unicitate
	public static boolean testareUnicitate(ArrayList<User> listaValizi)
	{
		for(int i=0;i<listaValizi.size()-1;i++)
		{
			for(int j=i+1;j<listaValizi.size();j++)
			{
				if(listaValizi.get(i).equals(listaValizi.get(j)))
				{
					//pentru a afisa duplicatele
					//System.out.println(listaValizi.get(i).getNumeUtilizator()+listaValizi.get(i).getParola());
					return false;
				}
			}
		}
		return true;
	}
	
	public static void testareMedici(ArrayList<Pacient> listaPacienti)
	{
		int contor=0;
		String k= listaPacienti.get(0).getNumeMedic();
		for(Pacient p:listaPacienti)
		{
			if(p.getNumeMedic().equalsIgnoreCase(k))
			{
				contor++;
			}
		}
		
		if(listaPacienti.size()==contor)
		{
			System.out.println("Au acelasi medic");
		}
		else
		{
			System.out.println("Nu au acelasi medic");
		}
		
	}
	
	public static int numarPacienti(ArrayList<Pacient> listaPacienti)
	{
		int contor=0;
		for(Pacient p:listaPacienti)
		{
			if(p.getDeclaratieCovid().equalsIgnoreCase("DA"))
			{
	        	 String[] g= p.getDataDiagnostic().split("\\.");
			if(g[1].equalsIgnoreCase("02") && g[2].equalsIgnoreCase("2020"))
				{
				contor++;
				}
			}
		}
		return contor;
	}
	
	public static void testareDescriere(ArrayList<Medic> listaMedici, int k)
	{
		int contor=0;
		for(Medic m:listaMedici)
		{
			if(m.getCaracterizare().equalsIgnoreCase("cardiolog"))
			{
				contor++;
			}
		}
		if(contor>k)
		{
			System.out.println("Corect");
		}
		else
		{
			System.out.println("Incorect");
		}
		
	}
	
	
	public static void procentajCovid(ArrayList<Pacient> listaPacienti)
	{
		float contor=0;
		for(Pacient p:listaPacienti)
		{
			if(p.getDeclaratieCovid().equalsIgnoreCase("DA"))
			{
				contor++;
			}
		}
		
		System.out.println(contor/listaPacienti.size()*100 + "%");
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//asa se citeste din fisier toate datele - se adauga intr-un arraylist
		Scanner fin=new Scanner(new File("C:\\Users\\darius\\eclipse-workspace\\CabinetStomatologicv2\\cabinet.txt"));
		ArrayList<String> info_pacienti = new ArrayList<>();
		while(fin.hasNextLine())
		{
			info_pacienti.add(fin.nextLine());
		}
		fin.close();
		
//		Scanner k=new Scanner(System.in);
//		int parametru=k.nextInt();
		
		
//		for(String a:info_pacienti)
//		{
//			System.out.println(a);
//		}
		ArrayList<Medic> listaMedici=new ArrayList<>();
		ArrayList<Administrator> listaAdministratori=new ArrayList<>();
		ArrayList<Pacient> listaPacienti = new ArrayList<>();
		for(String a:info_pacienti)
		{
			String[] v=a.split("\\*+");
			for(int i=0;i<v.length;i++)
			{
				v[i]=v[i].trim();
			}
			if(validareUser(v)==true)
			{
				if(v[2].equalsIgnoreCase("administrator"))
				{
					listaAdministratori.add(new Administrator(v[0],v[1],v[2]));
				}
				if(v[2].equalsIgnoreCase("medic"))
				{
					listaMedici.add(new Medic(v[0],v[1],v[2],v[3]));
				}
				if(v[2].equalsIgnoreCase("pacient"))
				{
					String[] p=v[3].split("_");
					listaPacienti.add(new Pacient(v[0],v[1],v[2],p[0],p[1],p[2]));
				}
			}
		}
		//afisare(listaMedici);
//		for(Medic m:listaMedici)
//		{
//			System.out.println(m);
//
//		}
//		
//		for(Pacient p:listaPacienti)
//		{
//			System.out.println(p);
//		}
//		
//		for(Administrator a:listaAdministratori)
//		{
//			System.out.println(a);
//		}
		
		ArrayList<User> listaValizi= new ArrayList<>();
		for(Medic m:listaMedici)
			{
				listaValizi.add(m);
			}
		for(Pacient p:listaPacienti)
			{
				listaValizi.add(p);
			}
		for(Administrator a:listaAdministratori)
			{
				listaValizi.add(a);
			}
		
		for(User a:listaValizi)
		{
			System.out.println(a);
		}
		
		System.out.println(testareUnicitate(listaValizi));
		testareMedici(listaPacienti);
		System.out.println(numarPacienti(listaPacienti));
		Collections.sort(listaAdministratori, new ComparatorUtilizator());
		procentajCovid(listaPacienti);
		
	
	

	}

}
