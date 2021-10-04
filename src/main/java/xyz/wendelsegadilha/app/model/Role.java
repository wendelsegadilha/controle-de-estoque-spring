package xyz.wendelsegadilha.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
@Table(name = "ROLES")
public class Role extends AbstractEntity<Long> implements GrantedAuthority {
	
	@Column(name = "nome_role", nullable = false)
	private String nomeRole;
	
	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	@Override
	public String getAuthority() {
		return this.nomeRole;
	}

}
