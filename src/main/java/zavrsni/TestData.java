package zavrsni;

import java.util.Stack;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import zavrsni.model.Sprint;
import zavrsni.model.Stanje;
import zavrsni.model.Zadatak;
import zavrsni.service.ZadatakService;
import zavrsni.service.SprintService;
import zavrsni.service.StanjeService;

@Component
public class TestData {
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired
	private StanjeService stanjeService;
	
	@PostConstruct
	public void init() {
		Sprint s1 = new Sprint();
		s1.setIme("Sprint1");
		s1.setUkupnoBodova(8);
		sprintService.save(s1);
		
		Sprint s2 = new Sprint();
		s2.setIme("Sprint2");
		s2.setUkupnoBodova(5);
		sprintService.save(s2);
		
		Stanje st1 = new Stanje();
		st1.setIme("Zavrsen");
		stanjeService.save(st1);
		
		Stanje st2 = new Stanje();
		st2.setIme("U toku");
		stanjeService.save(st2);
		
		Stanje st3 = new Stanje();
		st3.setIme("Nov");
		stanjeService.save(st3);
		
		Zadatak z1 = new Zadatak();
		z1.setBodovi(8);
		z1.setIme("Kreirati rest servis");
		z1.setSprint(s1);
		z1.setStanje(st1);
		z1.setZaduzeni("Nikola");
		zadatakService.save(z1);
		
		Zadatak z2 = new Zadatak();
		z2.setBodovi(8);
		z2.setIme("Kreirati pocetnu stranicu");
		z2.setSprint(s1);
		z2.setStanje(st2);
		z2.setZaduzeni("Bane");
		zadatakService.save(z2);
		
		Zadatak z3 = new Zadatak();
		z3.setBodovi(8);
		z3.setIme("Kreirati logo");
		z3.setSprint(s2);
		z3.setStanje(st3);
		z3.setZaduzeni("Bane");
		zadatakService.save(z3);
		
		s1.addZadatak(z1);
		s1.addZadatak(z2);
		s2.addZadatak(z3);
		
		st1.addZadatak(z1);
		st2.addZadatak(z2);
		st3.addZadatak(z3);
		
		
	}

}
