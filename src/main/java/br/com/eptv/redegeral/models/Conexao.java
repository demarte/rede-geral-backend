package br.com.eptv.redegeral.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity(name="conexao")
public class Conexao extends AbstractEntity{
	
	@NotEmpty
	@Column(length=20)
	private String porta;
	@Column(length=30)
	private String tipo;
	@Column(length=30)
	private String interfaceDeRede; 
	//@NotEmpty
	//@OneToOne(orphanRemoval=true)
	@OneToOne
	private Equipamento equipamento;
	//@NotEmpty
	@OneToOne
	private Switch sw;
	@ManyToMany
	private List<Vlan> untags;
	@ManyToMany
	private List<Vlan> tags;
	

	public Conexao() {
		
	}
	
	
	public Conexao(@NotEmpty String porta, String tipo, String interfaceDeRede, Equipamento equipamento, Switch sw,
			List<Vlan> untags, List<Vlan> tags) {
		super();
		this.porta = porta;
		this.tipo = tipo;
		this.interfaceDeRede = interfaceDeRede;
		this.equipamento = equipamento;
		this.sw = sw;
		this.untags = untags;
		this.tags = tags;
	}


	public String getPorta() {
		return porta;
	}
	public void setPorta(String porta) {
		this.porta = porta;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getInterfaceDeRede() {
		return interfaceDeRede;
	}

	public void setInterfaceDeRede(String interfaceDeRede) {
		this.interfaceDeRede = interfaceDeRede;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	public Switch getSw() {
		return sw;
	}
	public void setSw(Switch sw) {
		this.sw = sw;
	}
	public List<Vlan> getUntags() {
		return untags;
	}
	public void setUntags(List<Vlan> untags) {
		this.untags = untags;
	}
	public List<Vlan> getTags() {
		return tags;
	}

	public void setTags(List<Vlan> tags) {
		this.tags = tags;
	}
	

}
