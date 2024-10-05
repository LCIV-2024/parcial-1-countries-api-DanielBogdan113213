package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.Entities.CountryE;
import ar.edu.utn.frc.tup.lciii.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;


import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryE, Long> {
}
