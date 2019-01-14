package br.com.eptv.redegeral.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity(name="vlans")
@Table(uniqueConstraints = 
@UniqueConstraint(columnNames = "gateway", name = "vlans_uk"))
public class Vlan extends AbstractEntity {

	@NotEmpty
	@Column(length=50)
	private String nome;
	@NotEmpty
	@Column(length=20)
	private String mask;
	@NotEmpty
	@Column(length=20)
	private String gateway;
	private String descricao;
	
	public Vlan() {
		
	}
	
	public Vlan(String nome, String mask, String gateway, String descricao) {
		this.nome = nome;
		this.mask = mask;
		this.gateway = gateway;
		this.descricao = descricao;
	}
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
