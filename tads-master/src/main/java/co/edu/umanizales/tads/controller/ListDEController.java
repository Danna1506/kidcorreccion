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
        }
