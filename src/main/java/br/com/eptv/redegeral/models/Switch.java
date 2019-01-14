package br.com.eptv.redegeral.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity(name = "switches")
@Table(uniqueConstraints = {
@UniqueConstraint(columnNames="ip", name = "switches_uk")})
public class Switch extends AbstractEntity{
    
	@NotEmpty(message="O nome do switch está vazio")
	private String nome;
	@NotEmpty(message="O IP está vazio")
	@Column(length=20)
	private String ip;
	private String tipo;
	private boolean irf;
	@Column(nullable=false)
	private int numeroPortas;
	@ManyToOne()
	//@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="localizacao", nullable=false)
	private Localizacao localizacao;
	@OneToMany
	private List<Vlan> vlans;
//	@OneToOne(mappedBy="sw")
//	private Conexao conexao;
	
	public Switch() {
		
	}
	
	public Switch(@NotEmpty(message = "O nome do switch está vazio") String nome,
			@NotEmpty(message = "O IP está vazio") String ip, String tipo, boolean irf, int numeroPortas,
			Localizacao localizacao, List<Vlan> vlans) {
		super();
		this.nome = nome;
		this.ip = ip;
		this.tipo = tipo;
		this.irf = irf;
		this.numeroPortas = numeroPortas;
		this.localizacao = localizacao;
		this.vlans = vlans;
		
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isIrf() {
		return irf;
	}
	public void setIrf(boolean irf) {
		this.irf = irf;
	}
	public int getNumeroPortas() {
		return numeroPortas;
	}
	public void setNumeroPortas(int numeroPortas) {
		this.numeroPortas = numeroPortas;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public List<Vlan> getVlans() {
		return vlans;
	}

	public void setVlans(List<Vlan> vlans) {
		this.vlans = vlans;
	}


	
}
