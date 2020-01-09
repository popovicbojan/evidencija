package zavrsni.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import zavrsni.model.Sprint;
import zavrsni.web.dto.SprintDTO;
@Component
public class SprintToSprintDTO implements Converter<Sprint, SprintDTO>{

	@Override
	public SprintDTO convert(Sprint source) {
		SprintDTO dto = new SprintDTO();
		
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setUkupnoBodova(source.getUkupnoBodova());
		
		return dto;
	}
	
	public List<SprintDTO> convert(List<Sprint> sprintevi){
		List<SprintDTO> dtos = new ArrayList<SprintDTO>();
		
		for(Sprint s : sprintevi) {
			dtos.add(convert(s));
		}
		
		return dtos;
	}

}
