package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.Entities.CountryE;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

        private final String API_URL = "https://restcountries.com/v3.1/all";

        @Autowired
        private final CountryRepository countryRepository;

        @Autowired
        private final RestTemplate restTemplate;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());
        }
        public List<Country> fetchAllCountries() {
            RestTemplate restTemplate = new RestTemplate();
            Country[] countries = restTemplate.getForObject(API_URL, Country[].class);

            return List.of(countries);
        }
        public List<CountryE> getAllCountriesR() {
            return countryRepository.findAll();
        }
        public List<Country> filterCountriesByName(String name) {
            List<Country> countries = this.getAllCountries();
            Iterator<Country> it = countries.iterator();
            while (it.hasNext()){
                String current = it.next().getName();
                if(!current.equals(name)){
                    it.remove();
                }
            }
            return countries;
        }

        public List<Country> filterCountriesByCode(String code) {
            List<Country> countries = this.getAllCountries();
            Iterator<Country> it = countries.iterator();
            while (it.hasNext()){
                String current = it.next().getCode();
                if(!current.equals(code)){
                    it.remove();
                }
            }
            return countries;
        }
        public List<Country> filterCountriesByLanguage(String len) {
            List<Country> countries = this.getAllCountries();
            List<Country> countriesF = new ArrayList<>();

            Iterator<Country> it = countries.iterator();
            while (it.hasNext()){
                Map<String, String> map = new HashMap<>();
                map = it.next().getLanguages();
                boolean has = false;
                for(Map.Entry<String, String> entry : map.entrySet()){
                    if(!entry.getValue().equals(len)){
                        has = true;
                        break;
                    }
                }
                if(has){
                    countriesF.add(it.next());
                }
            }
            return countriesF;
        }
        public List<Country> filterCountriesByCon(String con) {
            List<Country> countries = this.getAllCountries();
            Iterator<Country> it = countries.iterator();
            while (it.hasNext()){
                String current = it.next().getRegion();
                if(!current.equals(con)){
                    it.remove();
                }
            }
            return countries;
        }


        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .build();
        }


        private CountryDTO mapToDTO(Country country) {
                return new CountryDTO(country.getCode(), country.getName());
        }


}