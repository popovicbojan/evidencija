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
import zavrsni.model.Stanje;
import zavrsni.service.ZadatakService;
import zavrsni.service.SprintService;
import zavrsni.service.StanjeService;
import zavrsni.support.ZadatakToZadatakDTO;
import zavrsni.support.SprintToSprintDTO;
import zavrsni.support.StanjeToStanjeDTO;
import zavrsni.web.dto.SprintDTO;
import zavrsni.web.dto.StanjeDTO;

@RestController
@RequestMapping(value = "api/stanja")
public class ApiStanjaController {
	
	@Autowired
	private StanjeService stanjeService;

	@Autowired
	private StanjeToStanjeDTO toDTO;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StanjeDTO>> get(){
		List<Stanje> stanja = stanjeService.findAll();
		
		return new ResponseEntity<>(toDTO.convert(stanja), HttpStatus.OK);
	}

}
