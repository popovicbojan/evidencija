package zavrsni.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import zavrsni.model.Zadatak;
import zavrsni.repository.ZadatakRepository;
import zavrsni.service.ZadatakService;

@Service
public class JpaZadatakService implements ZadatakService{
	
	@Autowired
	private ZadatakRepository zadatakRepository;

	@Override
	public Zadatak findOne(Long id) {
		// TODO Auto-generated method stub
		return zadatakRepository.findOne(id);
	}

	@Override
	public Zadatak save(Zadatak zadatak) {
		// TODO Auto-generated method stub
		return zadatakRepository.save(zadatak);
	}

	@Override
	public Zadatak delete(Long id) {
		// TODO Auto-generated method stub
		
		Zadatak toDelete = zadatakRepository.findOne(id);
		
		if(toDelete == null) {
			throw new IllegalArgumentException("Pokusali ste da obrisete zadatak koji ne postoji!");
		}
		
		zadatakRepository.delete(toDelete);
		
		return toDelete;
	}

	@Override
	public List<Zadatak> findAll() {
		// TODO Auto-generated method stub
		return zadatakRepository.findAll();
	}

	@Override
	public Page<Zadatak> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return zadatakRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Zadatak> search(Long idSprinta, String imeZadatka, int pageNum) {
		// TODO Auto-generated method stub
		
		if (imeZadatka != null) {
			imeZadatka = "%" + imeZadatka + "%";
		}
		
		
		return zadatakRepository.search(idSprinta, imeZadatka, new PageRequest(pageNum, 5));
	}



}
