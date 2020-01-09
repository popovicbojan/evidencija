package zavrsni.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_stanje")
public class Stanje {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ime")
	private String ime;
	
	@OneToMany(mappedBy = "stanje", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Zadatak> zadaci = new ArrayList<Zadatak>();

	public Stanje(String ime) {
		super();
		this.ime = ime;
	}

	public Stanje() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public List<Zadatak> getZadaci() {
		return zadaci;
	}

	public void setZadaci(List<Zadatak> zadaci) {
		this.zadaci = zadaci;
	}
	
	public void addZadatak(Zadatak zadatak) {
		this.getZadaci().add(zadatak);
		if(zadatak.getStanje() != null && !this.equals(zadatak.getStanje())) {
			zadatak.setStanje(this);
		}
	}

}
