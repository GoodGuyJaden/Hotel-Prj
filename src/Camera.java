import java.time.LocalDate;

public class Camera {
	private String numero;
	private double prezzo_min;
	private double prezzo_max;
	private int num_posti;
	private static final int YEAR = LocalDate.now().getYear();
	private Cliente[] clienti = new Cliente[365]; // ogni posizione si riferisce ad un giorno e segna che cliente c'è
													// quel giorno
	//private boolean isFree = true;

	public Camera(String numero, double prezzo_min, double prezzo_max, int num_posti) {
		this.numero = numero;
		this.prezzo_min = prezzo_min;
		this.prezzo_max = prezzo_max;
		this.num_posti = num_posti;
	}

//	public void occ_lib()
//	{
//		isFree = !isFree;
//	}
	
	public boolean insertCliente(LocalDate inizio, LocalDate fine, Cliente c)
	{
		int iterator = fine.getDayOfYear() - inizio.getDayOfYear();
		if(checkCamera(inizio.getDayOfYear(), iterator, true))
		{
			for(int i = 0; i < iterator; i++)
			{
				clienti[inizio.getDayOfYear()-1 + i] = c;	
			}
			System.out.println("Prenotazione effettuata con successo.");
			System.out.println("Camera " + numero + " prenotata per il cliente: " + c + ", dalla data " + inizio + " a " + fine + "\n");
			return true;
		}
		return false;
	}
	
	public boolean controlloDisponibilita(LocalDate inizio, LocalDate fine)
	{
		int iterator = fine.getDayOfYear() - inizio.getDayOfYear();
		return checkCamera(inizio.getDayOfYear(), iterator, true);			
	}
	
	private boolean checkCamera(int pos_inizio, int iterator, boolean flag)
	{
		pos_inizio--;  //serve perché il giorno 24 dell'anno equivale alla posizione 23 dell'array
		if(flag)
		{
			if(iterator < 0)
			{
				System.out.println("Insersci prima la data di inizio soggiorno e poi quella finale\n");
				return false;
			}
			
			for(int i = 0; i < iterator; i++)
			{
				if(clienti[pos_inizio + i] != null)
				{
					System.out.println("Prenotazione fallita, la camera è occupata in quel periodo.\n");
					return false;
				}
			}
			return true;
		}
		else
		{
			for(int i = 0; i < iterator; i++)
			{
				if(clienti[pos_inizio + i] != null)
				{
					return false;
				}
			}
			return true;
		}
		
	}
	
	public String isCustomerHere(Cliente c)
	{
		LocalDate inizio = null;
		LocalDate fine = null;
		for(int i = 0; i < clienti.length; i++)
		{
			if(clienti[i] != null)
			{
				if(clienti[i].getNome().equals(c.getNome()) && clienti[i].getCognome().equals(c.getCognome()) && c.getClass() == clienti[i].getClass())
				{
					if (inizio == null) 
					{
						inizio = LocalDate.ofYearDay(YEAR, i+1);
					}
						
					if(clienti[i+1] == null)
					{
						fine = LocalDate.ofYearDay(YEAR, i+2);
						return (fine.isBefore(LocalDate.now()) ? " è stato da " + inizio + " a " + fine: (inizio.isAfter(LocalDate.now()) ? "" : " è fino a " + fine));
					}
					
					
				}
			}
						
		}
		
		return "";
	}
	
	public String controlloDisponibilitaByData(LocalDate data)
	{
		try
		{
			return ((checkCamera(data.getDayOfYear(),1, false)) ? "" : "La camera è occupata da: " + clienti[data.getDayOfYear()-1].toString());
		}
		catch(NullPointerException e)
		{
			return "La stanza è vuota";
		}
				
	}
	
	
	public String getNum()
	{
		return numero;
	}
	
	@Override
	public String toString()
	{
		String text = "";
		int conta = 0;
		
		for(int i = 0;i<clienti.length;i++)
		{
			if(clienti[i] != null)
			{
				if(conta == 0)
				{
					text += "Camera " + numero + " prenotata per il cliente: " + clienti[i] + ", dal " + LocalDate.ofYearDay(YEAR, i+1);
					conta++;
				}
				else if(clienti[i+1] == null)
				{
					text += " al " + LocalDate.ofYearDay(YEAR,i+2) + "\n";
					conta = 0;
				}
				
			}
		}
		
		return text;
	}

	
	
}
