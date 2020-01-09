package zavrsni.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zavrsni.model.Sprint;
import zavrsni.service.ZadatakService;
import zavrsni.service.SprintService;
import zavrsni.support.ZadatakToZadatakDTO;
import zavrsni.support.SprintToSprintDTO;
import zavrsni.web.dto.SprintDTO;

@RestController
@RequestMapping(value = "api/sprintevi")
public class ApiSprinteviController {
	
	@Autowired
	private SprintService sprintService;

	
	@Autowired
	private SprintToSprintDTO toDTO;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SprintDTO>> getSprint(){
		List<Sprint> sprintevi = sprintService.findAll();
		
		return new ResponseEntity<>(toDTO.convert(sprintevi), HttpStatus.OK);
	}

}
