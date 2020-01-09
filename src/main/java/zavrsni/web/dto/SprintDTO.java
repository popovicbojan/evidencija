package zavrsni.web.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import zavrsni.model.Zadatak;

public class SprintDTO {
	
	private Long id;
	
	private String ime;
	
	private Integer ukupnoBodova;

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

	

}
