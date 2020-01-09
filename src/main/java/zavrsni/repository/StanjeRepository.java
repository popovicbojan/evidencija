package zavrsni.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zavrsni.model.Stanje;


@Repository
public interface StanjeRepository extends JpaRepository<Stanje, Long>{

	Stanje findByIme(String ime);

}
