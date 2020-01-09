package zavrsni.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zavrsni.model.Sprint;


@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{

}
