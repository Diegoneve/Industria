import java.time.LocalDate;

/**
 * @author Diego Andrade 
 * 1 - Classe Pessoa com os atributos: nome (String) e data nascimento (LocalDate)
 */
public class Pessoa {
	
	protected String nome;
	protected LocalDate dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}	

}
