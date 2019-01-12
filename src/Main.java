import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main 
{
	private static Scanner sc = new Scanner(System.in);
	private static LocalDate inizio = null;
	private static LocalDate fine = null;
	
	public static void main(String[] args) 
	{
		menu();
	}
	
	public static void menu()
	{
		Hotel plaza = new Hotel();
		
		String scelta = "";
		
		while(!scelta.equalsIgnoreCase("x"))
		{
			System.out.println("Inserisci 1 per inserire un cliente in una stanza libera per un periodo definito\n"
							  +"Inserisci 2 per controllare quali stanze sono libero dato un periodo\n"
							  +"Inserisci 3 per controllare dato un cliente dove si trova o si è trovato e per quale periodo\n"
							  +"Inserisci 4 per controllare data una stanza e una data se è occupata e da chi");
			scelta = sc.nextLine();
			switch(scelta)
			{
			case "1": 	
						creaPeriodo(true);
						plaza.insCliente(creaCliente(), inizio, fine);
						break;
				
			case "2":	
						creaPeriodo(true);
						System.out.println(plaza.camereLibere(inizio, fine));
						break;
				
			case "3":
						plaza.findCliente(creaCliente());
						break;
			
			case "4":
						System.out.println("Inserisci il numero della stanza");
						String num_stanza = sc.nextLine();
						creaPeriodo(false);
						System.out.println(plaza.getCliente(num_stanza, inizio));
						break;
			
			case "x":	System.out.println("Arrivederci..");
						break;
				
			default:	System.out.println("Inserisci solo le opzioni consentite, grazie");
						break;
			}
		}
	}
	
	public static Cliente creaCliente()
	{
		String scelta = "";
		
		while(!(scelta.equals("1") || scelta.equals("2")))
		{
			System.out.println("1)Cliente privato\n2)Azienda");
			scelta = sc.nextLine();
		}
		
		return (scelta.equals("1") ? creaClientePriv() : creaAzienda());
		
	}
	
	public static Cliente_privato creaClientePriv()
	{
		System.out.println("Inserisci il tuo nome");
		String nome = sc.nextLine();
		System.out.println("Inserisci il tuo cognome");
		String cognome = sc.nextLine();
		Cliente_privato cp = new Cliente_privato(nome, cognome);
		return cp;
	}
	
	public static Azienda creaAzienda()
	{
		System.out.println("Inserisci il tuo nome");
		String nome = sc.nextLine();
		System.out.println("Inserisci il tuo cognome");
		String cognome = sc.nextLine();
		System.out.println("Inserisci il nome dell'azienda");
		String nome_Azienda = sc.nextLine();
		Azienda az = new Azienda(nome, cognome, nome_Azienda);
		return az;
	}

	public static LocalDate getData()  //da esportare, creare package per data
	{
		int mese = 0;
		int gg = 0;
		do
		{
			try
			{
				System.out.println("Inserisci il mese in numeri");
				mese = sc.nextInt();
				switch(mese)
				{
				case 2:	if(LocalDate.now().isLeapYear())
						{
							while(!(gg>0 && gg<30))
							{
								System.out.println("Inserisci il giorno in numeri max 29");
								gg = sc.nextInt();
							}
							
						}
						else
						{
							while(!(gg>0 && gg<29))
							{
								System.out.println("Inserisci il giorno in numeri max 28");
								gg = sc.nextInt();
							}
						}
						break;
				case 4:case 6:case 9: case 11:
						
						while(!(gg>0 && gg<31))
						{
							System.out.println("Inserisci il giorno in numeri max 30");
							gg = sc.nextInt();
						}
						break;
						
				case 1:case 3:case 5:case 7:case 8:case 10:case 12:
						
						while(!(gg>0 && gg<32))
						{
							System.out.println("Inserisci il giorno in numeri max 31");
							gg = sc.nextInt();
						}
						break;
				}
			
			}
			catch(InputMismatchException e)
			{
				gg = 0;
				mese = 0;
				System.out.println("Inserisci solo numeri grazie, ripete l'operazione");
				sc.nextLine();
			}
			
		}
		while(!(gg > 0 && gg < 32) && !(mese > 0 && mese < 13));
		
		LocalDate data = LocalDate.of(2019, mese, gg);   //da modificare con anno inserito dall'utente se esportato
		sc.nextLine();
		return data;
	}

	public static void creaPeriodo(boolean flag)
	{
		if(flag)
		{
			System.out.println("Ora inserirai la data di inizio passo per passo\n");
			inizio = getData();
			System.out.println("Ora inserirai la data di fine passo per passo\n");
			fine = getData();
		}
		else
		{
			System.out.println("Ora inserirai la data di inizio passo per passo\n");
			inizio = getData();
		}
	}

}
