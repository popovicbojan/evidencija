package zavrsni.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import zavrsni.model.Zadatak;

public interface ZadatakService {
	
	Zadatak findOne(Long id);
	Zadatak save(Zadatak zadatak);
	Zadatak delete(Long id);
	List<Zadatak> findAll();
	Page<Zadatak> findAll(int pageNum);
	Page<Zadatak> search(
			@Param("idSprinta") Long idSprinta,
			@Param("imeZadatka") String imeZadatka,
			int pageNum);

}
