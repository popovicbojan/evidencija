package zavrsni.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import zavrsni.model.Sprint;
import zavrsni.service.SprintService;
import zavrsni.web.dto.SprintDTO;
@Component
public class SprintDTOToSprint implements Converter<SprintDTO, Sprint>{
	
	@Autowired
	private SprintService sprintService;

	@Override
	public Sprint convert(SprintDTO source) {
		Sprint sprint = null;
		
		if(source.getId() != null) {
			sprint = sprintService.findOne(source.getId());
			
			if(sprint == null) {
				throw new IllegalStateException("Pokusali ste da promenite nepostojeci Sprint!");
			}
			
		} else {
			sprint = new Sprint();
		}
		
		sprint.setId(source.getId());
		sprint.setIme(source.getIme());
		sprint.setUkupnoBodova(source.getUkupnoBodova());
				
		return sprint;
	}

}
