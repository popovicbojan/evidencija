package zavrsni.service.imp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zavrsni.model.Sprint;
import zavrsni.repository.SprintRepository;
import zavrsni.service.SprintService;

@Service
public class JpaSprintService implements SprintService{

	@Autowired
	private SprintRepository sprintRepository;
	
	
	@Override
	public Sprint findOne(Long id) {
		// TODO Auto-generated method stub
		return sprintRepository.findOne(id);
	}

	@Override
	public Sprint save(Sprint sprint) {
		// TODO Auto-generated method stub
		return sprintRepository.save(sprint);
	}

	@Override
	public Sprint delete(Long id) {
		// TODO Auto-generated method stub
		Sprint toDelete = sprintRepository.findOne(id);
		if(toDelete == null) {
			throw new IllegalArgumentException("Pokusaj brisanja nepostojeceg Sprinta!");
		}
		
		sprintRepository.delete(toDelete);
		
		return toDelete;
	}

	@Override
	public List<Sprint> findAll() {
		// TODO Auto-generated method stub
		return sprintRepository.findAll();
	}

}
