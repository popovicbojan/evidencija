package zavrsni.web.dto;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import zavrsni.model.Sprint;
import zavrsni.model.Stanje;

public class ZadatakDTO {


	private Long id;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max = 40)
	private String ime;
	
	private String zaduzeni;
	
	@Max(value = 20)
	@Min(value = 0)
	private Integer bodovi;
	
	private Long idSprinta;
	
	private String imeSprinta;
	
	private long idStanja;
	
	private String imeStanja;

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

	public Long getIdSprinta() {
		return idSprinta;
	}

	public void setIdSprinta(Long idSprinta) {
		this.idSprinta = idSprinta;
	}

	public String getImeSprinta() {
		return imeSprinta;
	}

	public void setImeSprinta(String nazivSprinta) {
		this.imeSprinta = nazivSprinta;
	}

	public long getIdStanja() {
		return idStanja;
	}

	public void setIdStanja(long idStanja) {
		this.idStanja = idStanja;
	}

	public String getImeStanja() {
		return imeStanja;
	}

	public void setImeStanja(String imeStanja) {
		this.imeStanja = imeStanja;
	}
	
	
	
	
}
