package br.com.hotmart.fileupload.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hotmart.fileupload.entity.User;

/**
 * Interface de persistencia de usuário
 * @author brunoeverton
 *
 */
public interface UserDAO extends JpaRepository<User, Long>{
	
	/**
	 * Busca o usuário pelo email
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

}
