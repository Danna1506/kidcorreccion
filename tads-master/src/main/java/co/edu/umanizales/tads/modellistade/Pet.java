package co.edu.umanizales.tads.modellistade;


import co.edu.umanizales.tads.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class Pet {
    private String identification;
    private String namePet;
    private byte agePet;
    private char genderPet;
    private Location locationPet;

}
