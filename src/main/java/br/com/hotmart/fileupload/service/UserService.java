package br.com.hotmart.fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotmart.fileupload.dao.UserDAO;
import br.com.hotmart.fileupload.entity.User;

/**
 * Classe de servico de usuario
 * @author brunoeverton
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	/**
	 * Retorna o usuario existente a partir do email, caso nao exista salva novo usuario
	 * @param email
	 * @return
	 */
	public User getByEmail(String email) {
		User user = findByEmail(email);
		if(user==null) {
			user = new User(email);
			user = save(user);
		}
		return user;
	}
	
	
	/**
	 * Busca o usuário pelo email
	 * @param email
	 * @return
	 */
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}
	
	/**
	 * Salva/Atualiza dados do usuário
	 * @param user
	 * @return
	 */
	public User save(User user) {
		return userDAO.save(user);
	}
}
