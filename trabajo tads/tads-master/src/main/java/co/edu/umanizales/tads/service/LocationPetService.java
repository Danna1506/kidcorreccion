package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.modellistade.LocationPets;

import java.util.ArrayList;
import java.util.List;

public class LocationPetService {
    private List<LocationPets> locationsPets;

    public void LocationPetsService() {
        //Conectaría a una base de datos
        locationsPets = new ArrayList<>();
        locationsPets.add(new LocationPets("169","Colombia"));
        locationsPets.add(new LocationPets("16905","Antioquia"));
        locationsPets.add(new LocationPets("16917","Caldas"));
        locationsPets.add(new LocationPets("16963","Risaralda"));
        locationsPets.add(new LocationPets("16905001","Medellín"));
        locationsPets.add(new LocationPets("16963001","Pereira"));
        locationsPets.add(new LocationPets("16917001","Manizales"));
        locationsPets.add(new LocationPets("16917003","Chinchiná"));
    }

    public List<LocationPets> getLocationsPetsByCodeSize(int size){
        List<LocationPets> listLocations = new ArrayList<>();

        for(LocationPets loc: locationsPets) {
            if (loc.getCodePet().length() == size) {
                listLocations.add(loc);
            }
        }
        return listLocations;
    }

    public LocationPets getLocationsPetsByCode(String code){

        for(LocationPets loc: locationsPets) {
            if (loc.getCodePet().equals(code)) {
                return loc;
            }
        }
        return null;
    }

}
