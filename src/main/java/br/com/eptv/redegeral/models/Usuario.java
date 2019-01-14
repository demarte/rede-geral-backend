package br.com.eptv.redegeral.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name="usuarios")
@Table(uniqueConstraints = 
@UniqueConstraint(columnNames = "login", name = "usuarios_uk"))
public class Usuario extends AbstractEntity implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Column(length=50)
	private String login;
    @NotEmpty
	private String senha;
    
	public String getLogin() {
		return login;
	}

	public void setLogin(String nome) {
		this.login = nome;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
