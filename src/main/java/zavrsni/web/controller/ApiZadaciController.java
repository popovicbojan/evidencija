package zavrsni.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import zavrsni.model.Sprint;
import zavrsni.model.Stanje;
import zavrsni.model.Zadatak;
import zavrsni.service.SprintService;
import zavrsni.service.StanjeService;
import zavrsni.service.ZadatakService;
import zavrsni.support.ZadatakDTOToZadatak;
import zavrsni.support.ZadatakToZadatakDTO;
import zavrsni.web.dto.ZadatakDTO;

@RestController
@RequestMapping(value = "api/zadaci")
public class ApiZadaciController {

	@Autowired
	private ZadatakService zadatakService;

	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private StanjeService stanjeService;

	@Autowired
	private ZadatakToZadatakDTO toDTO;

	@Autowired
	private ZadatakDTOToZadatak toZadatak;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ZadatakDTO>> getAll(@RequestParam(required = false) Long idSprinta,
			@RequestParam(required = false) String imeZadatka,
			@RequestParam(value = "page", defaultValue = "0") int page) {

		Page<Zadatak> zadaci;

		if (idSprinta != null || imeZadatka != null) {
			zadaci = zadatakService.search(idSprinta, imeZadatka, page);
		} else {
			zadaci = zadatakService.findAll(page);
		}

		HttpHeaders header = new HttpHeaders();
		header.add("ukupnoStrana", Integer.toString(zadaci.getTotalPages()));

		return new ResponseEntity<List<ZadatakDTO>>(toDTO.convert(zadaci.getContent()), header, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ZadatakDTO> getOne(@PathVariable Long id) {
		Zadatak zadatak = zadatakService.findOne(id);

		if (zadatak == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ZadatakDTO>(toDTO.convert(zadatak), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ZadatakDTO> addOne(@RequestBody @Validated ZadatakDTO zadatakDTO) {
		Zadatak zadatak = toZadatak.convert(zadatakDTO);

		// dobavi sprint
		Sprint sprint = sprintService.findOne(zadatak.getSprint().getId());

		// sacuvaj stare bodove
		Integer stariBodovi = sprint.getUkupnoBodova();

		// afuriraj bodove
		sprint.setUkupnoBodova(stariBodovi + zadatak.getBodovi());

		// kada cuvas zadatak azurira se i sprint :)
		zadatakService.save(zadatak);

		return new ResponseEntity<ZadatakDTO>(toDTO.convert(zadatak), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ZadatakDTO> edit(@PathVariable Long id, @RequestBody ZadatakDTO zadatakDTO) {

		if (!id.equals(zadatakDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		//dobavi zadatak prije izmene
		Zadatak zadatak = zadatakService.findOne(id);
		
		//bodovi koje oduzimas
		Integer oduzmi = zadatak.getBodovi();
		
		// dobavi sprint za koji je izmenjen zadatak jer iz njega brises, mozda je samo promenjen sprint pa moram od starog da oduzmem bodove
		Sprint sprint = sprintService.findOne(zadatak.getSprint().getId());
		
		// sacuvaj stare bodove
		Integer stariBodovi = sprint.getUkupnoBodova();

		//oduzmi bodove za stari sprint
		sprint.setUkupnoBodova(stariBodovi - oduzmi);
		
		if (sprint.getUkupnoBodova() < 0) {
			sprint.setUkupnoBodova(0);
		}
		
		//auzuriraj
//		sprintService.save(sprint);
		
		//uzmi novi sprint
		Sprint noviSprint = sprintService.findOne(zadatakDTO.getIdSprinta());
		
		//bodovi novog sprinta
		Integer bodoviNovogSprinta = noviSprint.getUkupnoBodova();
		
		//dodaj bodove novom sprintu
		noviSprint.setUkupnoBodova(bodoviNovogSprinta + zadatakDTO.getBodovi());
		
		//konvertuj zadatak
		Zadatak toSave = toZadatak.convert(zadatakDTO);

		//sacuvaj ga
		zadatakService.save(toSave);

		return new ResponseEntity<ZadatakDTO>(toDTO.convert(toSave), HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}/stanje", method = RequestMethod.PUT)
	public ResponseEntity<ZadatakDTO> editStanje(@PathVariable Long id) {

		Zadatak zadatak = zadatakService.findOne(id);
		
		if (zadatak == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if("Nov".equals(zadatak.getStanje().getIme())) {
			zadatak.setStanje(stanjeService.findByIme("U toku"));
		}else if("U toku".equals(zadatak.getStanje().getIme())) {
			zadatak.setStanje(stanjeService.findByIme("Zavrsen"));
		}
		
		
		//sacuvaj ga
		zadatakService.save(zadatak);

		return new ResponseEntity<ZadatakDTO>(toDTO.convert(zadatak), HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ZadatakDTO> delete(@PathVariable Long id) {
		Zadatak deleted = zadatakService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		// bodovi koje oduzimas
		Integer oduzmi = deleted.getBodovi();

		// dobavi sprint za koji je izmenjen zadatak jer iz njega brises, mozda je samo
		// promenjen sprint pa moram od starog da oduzmem bodove
		Sprint sprint = sprintService.findOne(deleted.getSprint().getId());

		// sacuvaj stare bodove
		Integer stariBodovi = sprint.getUkupnoBodova();

		// oduzmi bodove za stari sprint
		sprint.setUkupnoBodova(stariBodovi - oduzmi);
		
		if (sprint.getUkupnoBodova() < 0) {
			sprint.setUkupnoBodova(0);
		}

		return new ResponseEntity<ZadatakDTO>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
