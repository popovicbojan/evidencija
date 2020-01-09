package zavrsni.service;

import java.util.List;

import zavrsni.model.Sprint;

public interface SprintService {
	
	Sprint findOne(Long id);
	Sprint save(Sprint sprint);
	Sprint delete(Long id);
	List<Sprint> findAll();

}
