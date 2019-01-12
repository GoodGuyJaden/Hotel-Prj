
public class Azienda extends Cliente
{
	private String nome_Azienda;
	private String partita_IVA;
	private String indirizzo_fatt;
	
	public Azienda(String nome, String cognome, String nome_Azienda) 
	{
		super(nome, cognome);
		this.nome_Azienda = nome_Azienda;
		this.partita_IVA = "ciao";
		this.indirizzo_fatt = "via ciao 1";
	}
	
	
	@Override
	public String toString()
	{
		return super.toString() + " facente parte di: " + nome_Azienda;
	}


}
