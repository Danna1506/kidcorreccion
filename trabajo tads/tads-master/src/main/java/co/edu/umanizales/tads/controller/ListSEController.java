package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.RangesK;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getKids().getHead(), null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(
                200, "SE ha invertido la lista",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200, "SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO){
        Location location = locationService.getLocationsByCode(kidDTO.getCodeLocation());
        if(location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no existe ", null), HttpStatus.OK);
        }
        else if( location != null) {
            listSEService.getKids().add(new Kid(kidDTO.getIdentification(),
                    kidDTO.getName(), kidDTO.getAge(),
                    kidDTO.getGender(), location));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha añadido", null), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ResponseDTO(
                    409, "Ya existe un niño con ese código", null
            ), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
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

    @GetMapping(path = "/kidsbydepto")
    public ResponseEntity<ResponseDTO> getKidsByDeptoCode() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listSEService.getKids().getCountKidsByDeptoCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsByLocationDTOList, null), HttpStatus.OK);

    }

    @GetMapping(path = "/addKidByPosition")
    public ResponseEntity<ResponseDTO> addKidByPosition(@RequestBody Kid kid) {
        listSEService.getKids().passByPosition(kid);
        return new ResponseEntity<>(new ResponseDTO(
                200, "El niño se movió de posición", null), HttpStatus.OK);
    }

    @GetMapping(path = "/delete")
    public ResponseEntity<ResponseDTO> deleteByIdentification(@PathVariable String identification) {
        listSEService.getKids().deleteByIdentification(identification);
        return new ResponseEntity<>(new ResponseDTO(200, "the kids has been deleted", null), HttpStatus.OK);
    }


    @GetMapping(path="/boyStartGirlsLast")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast(){
        listSEService.getKids().boyStartGirlsLast();
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños han sido alternados según su género",
                null), HttpStatus.OK);
    }
    }@GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteByAge(@PathVariable int age){
        listSEService.getKids().deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños han sido eliminados",
                null), HttpStatus.OK);
    }
    @GetMapping(path="/averAge")
    public ResponseEntity<ResponseDTO> averAge(){
        return new ResponseEntity<>(new ResponseDTO(200,
                listSEService.getKids().averAge(), null), HttpStatus.OK);
    }
    @GetMapping(path="sendfinletter")
    public ResponseEntity<ResponseDTO> sendBottomByLetter(@PathVariable char letter){
        listSEService.getKids().sendfinletter(Character.toUpperCase(letter));
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños con esa letra se han enviado al final",
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/Rangeage")
    public ResponseEntity<ResponseDTO> getRangeByKids(){
        List<RangeAgeKidsDTO> kidsRangeDTOList = new ArrayList<>();



        return new ResponseEntity<>(new ResponseDTO(200,kidsRangeDTOList,null),HttpStatus.OK);
    }

    @GetMapping(path="/forwardPositions")
    public ResponseEntity<ResponseDTO> afterwardsPositions(@PathVariable String identification, int positions){
        listSEService.getKids().afterwardsPositions(identification, positions);
        return new ResponseEntity<>(new ResponseDTO(200, "se ha adelantado el niño a la posicion deseada ", null), HttpStatus.OK);
    }
    @GetMapping(path="/InterlayerBoyAndGirl")
    public ResponseEntity<ResponseDTO> InterlayerBoyAndGirl(){
        return new ResponseEntity<>(new ResponseDTO(200,
                "se han intercambiado los niños correctamente", null), HttpStatus.OK);
    }
    }
}




