package model;

public class Contato implements modeloListagem, Comparable<Contato> {
	private String nomeContato;
	private String telefone;
	private String email;
	private String endereco;
	
	public Contato(){}
	
	@Override
	public String dadosFormatados() {
		String dadosFormatados;
		dadosFormatados = this.getNomeContato() + "; ";
		dadosFormatados = dadosFormatados + this.getTelefone() + "; ";
		dadosFormatados = dadosFormatados + this.getEmail() + "; ";
		dadosFormatados = dadosFormatados + this.getEndereco();
		return dadosFormatados;		
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int compareTo(Contato contato ) {
		return nomeContato.compareTo(contato.getNomeContato());
	}
	
    @Override
    public boolean equals(Object o){
    	if (o instanceof Contato && ((Contato) o).getNomeContato().equals(this.nomeContato)) {
    		return true;
        }
    	return false;
    }
}
