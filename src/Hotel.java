import java.time.LocalDate;

public class Hotel 
{
	private Camera [] camere = new Camera[20];
	private String [] num_camere = new String[20];
	
	public Hotel()
	{
		String num = "";
		for (int i = 0; i < camere.length;) 
		{
			num = String.valueOf((int)(Math.random()*900+100));
			
			if(checkNum(num))
			{
				camere[i] = new Camera(num, (double)(Math.random()*350+100), (double)(Math.random()*600+200), 4);
				i++;
			}
		}
	}
	
	private boolean checkNum(String num)
	{
		int conta  = 0;
		for(int i = 0; i < num_camere.length; i++)
		{
			if(num_camere[i] == null)
			{
				conta++;
			}
			else
			{
				if(num_camere[i].equals(num))
				{
					return false;
				}
			}
			
		}
		num_camere[num_camere.length-conta] = num;
		return true;
	}

	public String camereLibere(LocalDate inizio, LocalDate fine)
	{
		String text = "";
		for(Camera c:camere)
		{
			if(c.controlloDisponibilita(inizio, fine))
			{
				text += c.getNum() + " libera\n";
			}
		}
		
		return text;
	}

	public String getCliente(String num, LocalDate data)
	{
		String text = getCameraByNum(num).controlloDisponibilitaByData(data);
		return (text.equals("") ? "La camera non è occupata" : text);
	}
	
	public boolean findCliente(Cliente c)
	{
		String numStanza = "";
		String text = "";
		for(Camera camera : camere)
		{
			text = camera.isCustomerHere(c);
			if(!text.equals(""))
			{
				numStanza = camera.getNum();
				text = "Il cliente " + c + text + " nella stanza " + numStanza;
				System.out.println(text);
				return true;
			}
		}
		return false;
	}
	
	public boolean insCliente(Cliente c, LocalDate inizio, LocalDate fine)
	{		
		for(Camera ca:camere)
		{
			if(ca.insertCliente(inizio, fine, c))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		String text = "";
		for(int i = 0; i < camere.length; i++)
		{
			text += camere[i].toString() + "\n";
		}
		
		return text;
	}

	public Camera getCameraByNum(String num)
	{
		for(int i = 0; i < camere.length; i++)
		{
			if(camere[i].getNum().equals(num))
			{
				return camere[i];
			}
		}
		System.out.println("Non esiste una camera con questo numero");
		return null;
	}
}
