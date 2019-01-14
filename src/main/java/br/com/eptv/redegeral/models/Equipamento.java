package br.com.eptv.redegeral.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity(name = "equipamentos")
@Table(uniqueConstraints = 
@UniqueConstraint(columnNames = "ip", name = "equipamentos_uk"))
public class Equipamento extends AbstractEntity{

	@NotEmpty(message="O nome do equipamento está vazio")
	private String nome;
	@NotEmpty(message="O nome do ip está vazio")
	@Column(length=20)
	private String ip;
	private String login;
	private String senha;
	private boolean monitoravel;
//	@OneToOne(mappedBy="equipamento")
//	private Conexao conexao;

	public Equipamento(@NotEmpty(message = "O nome do equipamento está vazio") String nome,
			@NotEmpty(message = "O nome do ip está vazio") String ip, String login, String senha, boolean monitoravel
			 ) {
		super();
		this.nome = nome;
		this.ip = ip;
		this.login = login;
		this.senha = senha;
		this.monitoravel = monitoravel;
	
	}
	
	public Equipamento() {
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isMonitoravel() {
		return monitoravel;
	}

	public void setMonitoravel(boolean monitoravel) {
		this.monitoravel = monitoravel;
	}



	
	

}
