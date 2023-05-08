package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exceptions.ListDEExceptions;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.modellistade.Pet;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ListDEController {
    @RequestMapping(path = "/listde")
    public class listDEController {
        @Autowired
        private ListDEService listDEService;
      @Autowired
        private LocationPetService locationPetService;
    }
    @GetMapping(path = "/addpet")
    public ResponseEntity<ResponseDTO> add(@RequestBody Pet pet) {
        try {
            if (pet == null) {
                throw new ListDEExceptions("La mascota no tiene datos");
            }
            listDEService.getPets().addPet(pet);
            return new ResponseEntity<>(new ResponseDTO(200,
                    "La mascota ha sido añadida exitosamente", null), HttpStatus.OK);
        } catch (ListDEExceptions e) {
            return new ResponseEntity<>(new ResponseDTO(500,
                    "Error al añadir la mascota", null), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ListDEExceptions e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/invertpets")
    public ResponseEntity<ResponseDTO> invertPets() {
        try {
            listDEService.invertPets();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha invertido la lista correctamente",
                    null), HttpStatus.OK);
        } catch (ListDEExceptions e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al invertir la lista",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/petssbylocations")
    public ResponseEntity<ResponseDTO> getPetByLocation() {
        List<PeyByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, kidsByLocationDTOList,
                null), HttpStatus.OK);
    }
    @GetMapping("/orderPetsByGender")
    public ResponseEntity<ResponseDTO> orderPetsByGender() {
        try {
            listDEService.getPets().orderPetsToStart();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se han añadido las mascotas masculinas al inicio, las femeninas al final.",
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ocurrió un error al ordenar el género de las mascotas.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path="/intercalatePetsGender")
    public ResponseEntity<ResponseDTO> intercalatePetsGender()  {
        try {
            listDEService.getPets().intercalatePetsGender();
            return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas se han intercalado.",
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ocurrió un error al intercalar el género de las mascotas",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = " eliminar")
    public ResponseEntity<ResponseDTO>eliminar(@PathVariable String code)  {
        try {
            listDEService.deletePetByIdentification(code);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Las mascotas con el código" + code + "han sido eliminados.",
                    null), HttpStatus.OK);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al eliminar las mascotas.",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/calculateAverageAge")
    public ResponseEntity<ResponseDTO> calculateAverageAge() {
        try {
            listDEService.getPets().calculateAverageAge();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha calculado el promedio de edad de las mascotas",
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al calcular la edad promedio.",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/generateCityReport")
    public ResponseEntity<ResponseDTO> getPetsByLocation() {
        try {
            List<PetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
            for (LocationPets loc : locationPetsService.getLocationsPets()) {
                int count = listDEService.getPets().getCountPetsByLocationCode(loc.getCode());
                if (count > 0) {
                    petsByLocationDTOList.add(new PetsByLocationDTO(loc, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(
                    200, petsByLocationDTOList,
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ocurrió un error al obtener las mascotas por ubicación.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    //Obtener un informe de mascotas por rango de edades
    @GetMapping(path="/rangeagepets")
    public ResponseEntity<ResponseDTO> getRangeAgePets() {
        List<RangeAgesPetsDTO> petsRangeDTOList = new ArrayList<>();
        try {
            for(RangeAgePets i: rangeAgePetsService.getRangesPets()){
                int quantity = listDEService.getPets().rangePetsByAge(i.getFromPet(), i.getToPet());
                petsRangeDTOList.add(new RangeAgesPetsDTO(i, quantity));
            }
            return new ResponseEntity<>(new ResponseDTO(200, petsRangeDTOList, null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Error al obtener el rango de edades", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Implementar un método que me permita enviar al final de la lista a las mascotas que su nombre inicie con una letra dada
    @GetMapping(path="/petsByLetter")
    public ResponseEntity<ResponseDTO> petsByLetter(@PathVariable char initial) {
        try {
            listDEService.getPets().petsByLetter(Character.toUpperCase(initial));
            return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas con esa letra se han enviado al final de la lista.", null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Ocurrió un error al enviar al final la lista de mascotas por la letra dada.",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/cambiarExtremos")
    public ResponseEntity<ResponseDTO> cambiarExtremos() {
        try {
            listDEService.getPets().cambiarExtremos();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha intercambiado los extremos ", null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ha ocurrido un error al intercambiar los extremos", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        }
