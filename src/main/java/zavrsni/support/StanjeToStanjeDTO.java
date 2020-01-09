package zavrsni.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import zavrsni.model.Stanje;
import zavrsni.web.dto.StanjeDTO;

@Component
public class StanjeToStanjeDTO implements Converter<Stanje, StanjeDTO>{

	@Override
	public StanjeDTO convert(Stanje source) {
		StanjeDTO dto = new StanjeDTO();
		
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		
		return dto;
	}
	
	public List<StanjeDTO> convert(List<Stanje> stanja){
		List<StanjeDTO> dtos = new ArrayList<StanjeDTO>();
		
		for(Stanje s : stanja) {
			dtos.add(convert(s));
		}
		
		return dtos;
	}

}
