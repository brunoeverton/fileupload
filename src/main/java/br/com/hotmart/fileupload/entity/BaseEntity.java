package br.com.hotmart.fileupload.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Classe base para as entidades
 * @author brunoeverton
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 6525214743655583132L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		BaseEntity modelo = (BaseEntity) obj;
		if (modelo.getId() == null) {
			return false;
		}
		return modelo.getId().equals(this.getId());
	}

	@Override
	public int hashCode() {

		if (getId() != null) {
			return getId().hashCode();
		} else {
			return 0;
		}
	}

}
