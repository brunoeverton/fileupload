package br.com.hotmart.fileupload.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Informaçoes do usuário
 * @author brunoeverton
 *
 */
@Entity
@Table(name="user")
public class User extends BaseEntity{

	private static final long serialVersionUID = -2994388551843890316L;
	
	@Column(name = "email")
	private String email;
		
	/**
	 * Cria nova instancia do usuario com email
	 * @param email
	 */
	public User(String email) {
		this();
		this.email = email;
	}
	/**
	 * Cria instancia do usuario
	 */
	public User() {
		super();
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


}
