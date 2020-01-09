package zavrsni.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_zadatak")
public class Zadatak {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ime", nullable = false, unique = true)
	private String ime;
	
	@Column(name = "zaduzeni", nullable = false)
	private String zaduzeni;
	
	@Column(name = "bodovi")
	private Integer bodovi;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Sprint sprint;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Stanje stanje;

	public Zadatak(String ime, String zaduzeni, Integer bodovi, Sprint sprint, Stanje stanje) {
		super();
		this.ime = ime;
		this.zaduzeni = zaduzeni;
		this.bodovi = bodovi;
		this.sprint = sprint;
		this.stanje = stanje;
	}

	public Zadatak() {
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

	public String getZaduzeni() {
		return zaduzeni;
	}

	public void setZaduzeni(String zaduzeni) {
		this.zaduzeni = zaduzeni;
	}

	public Integer getBodovi() {
		return bodovi;
	}

	public void setBodovi(Integer bodovi) {
		this.bodovi = bodovi;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
		if(!sprint.getZadaci().contains(this)) {
			sprint.getZadaci().add(this);
		}
	}

	public Stanje getStanje() {
		return stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
		if(!stanje.getZadaci().contains(this)) {
			stanje.getZadaci().add(this);
		}
	}
	
	
	

}
