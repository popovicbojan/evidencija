package zavrsni.service.imp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zavrsni.model.Stanje;
import zavrsni.repository.StanjeRepository;
import zavrsni.service.StanjeService;

@Service
public class JpaStanjeService implements StanjeService{

	@Autowired
	private StanjeRepository stanjeRepository;
	
	
	@Override
	public Stanje findOne(Long id) {
		// TODO Auto-generated method stub
		return stanjeRepository.findOne(id);
	}

	@Override
	public Stanje save(Stanje stanje) {
		// TODO Auto-generated method stub
		return stanjeRepository.save(stanje);
	}

	@Override
	public Stanje delete(Long id) {
		// TODO Auto-generated method stub
		Stanje toDelete = stanjeRepository.findOne(id);
		if(toDelete == null) {
			throw new IllegalArgumentException("Pokusaj brisanja nepostojeceg Stanja!");
		}
		
		stanjeRepository.delete(toDelete);
		
		return toDelete;
	}

	@Override
	public List<Stanje> findAll() {
		// TODO Auto-generated method stub
		return stanjeRepository.findAll();
	}

	@Override
	public Stanje findByIme(String ime) {
		// TODO Auto-generated method stub
		return stanjeRepository.findByIme(ime);
	}

}
