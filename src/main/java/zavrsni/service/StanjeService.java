package zavrsni.service;

import java.util.List;

import zavrsni.model.Stanje;

public interface StanjeService {
	
	Stanje findOne(Long id);
	Stanje save(Stanje stanje);
	Stanje delete(Long id);
	List<Stanje> findAll();
	Stanje findByIme(String ime);

}
