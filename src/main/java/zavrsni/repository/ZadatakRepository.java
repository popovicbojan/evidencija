package zavrsni.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import zavrsni.model.Zadatak;

@Repository
public interface ZadatakRepository extends JpaRepository<Zadatak, Long>{
	
	@Query("SELECT z FROM Zadatak z WHERE"
			+ "(:idSprinta is NULL or z.sprint.id = :idSprinta) AND"
			+ "(:imeZadatka is NULL or z.ime LIKE :imeZadatka)")
	Page<Zadatak> search(
			@Param("idSprinta") Long idSprinta,
			@Param("imeZadatka") String imeZadatka,
			Pageable pageRequest);


}
