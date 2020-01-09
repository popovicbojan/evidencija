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
@Table(name = "tbl_sprint")
public class Sprint {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ime")
	private String ime;
	
	@Column(name = "ukupno_bodova")
	private Integer ukupnoBodova;
	
	@OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Zadatak> zadaci = new ArrayList<Zadatak>();

	public Sprint(String ime, Integer ukupnoBodova, List<Zadatak> zadaci) {
		super();
		this.ime = ime;
		this.ukupnoBodova = ukupnoBodova;
		this.zadaci = zadaci;
	}

	public Sprint() {
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

	public Integer getUkupnoBodova() {
		return ukupnoBodova;
	}

	public void setUkupnoBodova(Integer ukupnoBodova) {
		this.ukupnoBodova = ukupnoBodova;
	}

	public List<Zadatak> getZadaci() {
		return zadaci;
	}

	public void setZadaci(List<Zadatak> zadaci) {
		this.zadaci = zadaci;
	}
	
	public void addZadatak(Zadatak zadatak) {
		this.getZadaci().add(zadatak);
		if(zadatak.getSprint() != null && !this.equals(zadatak.getSprint())) {
			zadatak.setSprint(this);
		}
	}

}
