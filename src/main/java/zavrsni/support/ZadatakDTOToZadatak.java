package zavrsni.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import zavrsni.model.Sprint;
import zavrsni.model.Stanje;
import zavrsni.model.Zadatak;
import zavrsni.service.ZadatakService;
import zavrsni.service.SprintService;
import zavrsni.service.StanjeService;
import zavrsni.web.dto.ZadatakDTO;

@Component
public class ZadatakDTOToZadatak implements Converter<ZadatakDTO, Zadatak> {

	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private StanjeService stanjeService;
	

	@Override
	public Zadatak convert(ZadatakDTO source) {
		
		Zadatak zadatak = null;
		
		Sprint sprint = sprintService.findOne(source.getIdSprinta());
		Stanje stanje = stanjeService.findOne(source.getIdStanja());
		
		
		
		if (sprint != null && stanje != null) {
			if (source.getId() != null) {
				zadatak = zadatakService.findOne(source.getId());
				if (zadatak == null) {
					throw new IllegalStateException("Pokusali ste da izmenite zadatak koji ne postoji!");
				}
			} else {
				zadatak = new Zadatak();
			}
			zadatak.setBodovi(source.getBodovi());
			zadatak.setId(source.getId());
			zadatak.setIme(source.getIme());
			zadatak.setSprint(sprint);
			zadatak.setStanje(stanje);
			zadatak.setZaduzeni(source.getZaduzeni());
			
			return zadatak;
		}
		else {
			throw new IllegalStateException("Pokusaliu ste da dodate zadatak za nepostojece stanj ili sprint!");
		}
	}

}
