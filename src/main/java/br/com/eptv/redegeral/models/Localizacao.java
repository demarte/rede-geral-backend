package br.com.eptv.redegeral.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity(name="localizacoes")
public class Localizacao extends AbstractEntity {

	@NotEmpty(message="a localizacao está vazia")
	private String localizacao;
	@OneToMany(mappedBy="localizacao")
	private List<Switch> sw;

	public Localizacao() {
	}

	public Localizacao(String localizacao) {
		super();
		this.localizacao = localizacao;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	

}
