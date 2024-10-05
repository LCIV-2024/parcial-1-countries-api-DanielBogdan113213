package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.model.Continent;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.model.Lenguage;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;


    @GetMapping("")
    public List<Country> getAll() {
        return countryService.getAllCountries();
    }

    @GetMapping("/getByName")
    public List<Country> getCountriesName(@RequestParam String name){
        return countryService.filterCountriesByName(name);
    }
    @GetMapping("/getByCode")
    public List<Country> getCountriesCode(@RequestParam String code){
        return countryService.filterCountriesByCode(code);
    }
    @GetMapping("/getByLenguage")
    public List<Country> getCountriesLenguage(@RequestParam Lenguage len){
        return countryService.filterCountriesByLanguage(len.toString());
    }
    @GetMapping("/getByContinent")
    public List<Country> getCountriesCon(@RequestParam Continent con){
        return countryService.filterCountriesByCon(con.toString());
    }





}