package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    private String name;
    private long population;
    private double area;
    private String code;
    private String region;
    private List<String> borders;
    private Map<String, String> languages;

    public CountryDTO(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
