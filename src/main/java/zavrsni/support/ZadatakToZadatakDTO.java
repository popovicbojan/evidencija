package zavrsni.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import zavrsni.model.Zadatak;
import zavrsni.web.dto.ZadatakDTO;
@Component
public class ZadatakToZadatakDTO implements Converter<Zadatak, ZadatakDTO>{

	@Override
	public ZadatakDTO convert(Zadatak source) {
		ZadatakDTO dto = new ZadatakDTO();
		
		dto.setBodovi(source.getBodovi());
		dto.setId(source.getId());
		dto.setIdSprinta(source.getSprint().getId());
		dto.setIdStanja(source.getStanje().getId());
		dto.setIme(source.getIme());
		dto.setImeStanja(source.getStanje().getIme());
		dto.setImeSprinta(source.getSprint().getIme());
		dto.setZaduzeni(source.getZaduzeni());
		
		return dto;
			
		}
		
		

	
	
	public List<ZadatakDTO> convert(List<Zadatak> zadaci){
		
		List<ZadatakDTO> dtos = new ArrayList<ZadatakDTO>();
		
		for (Zadatak z : zadaci) {
			dtos.add(convert(z));
		}
		
		return dtos;
		
	}


}
