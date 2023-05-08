package co.edu.umanizales.tads.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReportKidsDTO {
    private List<LocationGenderQuantityDTO> locationGenderQuantityDTOS;

    public ReportKidsDTO(List<Location> cities){
        locationGenderQuantityDTOS = new ArrayList<>();
        for(Location location: cities){
            locationGenderQuantityDTOS.add(new
                    LocationGenderQuantityDTO(location.getName()));
        }
    }

    //método actualizar
    public void updateQuantity(String city, char gender){
        for(LocationGenderQuantityDTO loc: locationGenderQuantityDTOS){
            if (loc.getCity().equals(city)) {
                for(KidGenderDTO genderDTO: loc.getGenders()){
                    if(genderDTO.getGender()==gender){
                        genderDTO.setQuantity(genderDTO.getQuantity()+1);
                        loc.setTotal(loc.getTotal()+1);
                        return;
                    }
                }
            }
        }
    }

}
