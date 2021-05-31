package pt.upacademy.stockMaven.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Entity_ implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDateTime date_inserted;
	private LocalDateTime date_updated;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public LocalDateTime getDate_inserted() {
		return date_inserted;
	}

	public void setDate_inserted(LocalDateTime date_inserted) {
		this.date_inserted = date_inserted;
	}

	public LocalDateTime getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(LocalDateTime date_updated) {
		this.date_updated = date_updated;
	}

}
