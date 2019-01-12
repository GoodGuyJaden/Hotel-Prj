
public class Cliente 
{
	private String nome;
	private String cognome;
	private String num_telefono;
	
	public Cliente(String nome, String cognome) 
	{
		this.nome = nome;
		this.cognome = cognome;
		this.num_telefono = "3203718397";
	}
	
	public String getNome() 
	{
		return nome;
	}
	
	public String getCognome() 
	{
		return cognome;
	}
	
	public String getNum_telefono() 
	{
		return num_telefono;
	}
	
	@Override
	public String toString() {
		return nome + " " + cognome;
	}


}
