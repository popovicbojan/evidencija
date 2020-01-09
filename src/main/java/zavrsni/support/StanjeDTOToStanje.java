package zavrsni.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import zavrsni.model.Stanje;
import zavrsni.service.StanjeService;
import zavrsni.web.dto.StanjeDTO;
@Component
public class StanjeDTOToStanje implements Converter<StanjeDTO, Stanje>{
	
	@Autowired
	private StanjeService StanjeService;

	@Override
	public Stanje convert(StanjeDTO source) {
		Stanje stanje = null;
		
		if(source.getId() != null) {
			stanje = StanjeService.findOne(source.getId());
			
			if(stanje == null) {
				throw new IllegalStateException("Pokusali ste da promenite nepostojece Stanje!");
			}
			
		} else {
			stanje = new Stanje();
		}
		
		stanje.setId(source.getId());
		stanje.setIme(source.getIme());
				
		return stanje;
	}

}
