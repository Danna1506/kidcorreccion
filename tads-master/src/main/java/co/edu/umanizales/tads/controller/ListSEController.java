package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
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
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
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
                    409, "Ya existe un niño con ese código", null), HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>(new ResponseDTO(200,kidsByLocationDTOList,null), HttpStatus.OK);

    }

    @GetMapping(path="/addKidByPosition")
    public ResponseEntity<ResponseDTO> addKidByPosition(@RequestBody Kid kid){
        listSEService.getKids().passByPosition(kid);
        return new ResponseEntity<>(new ResponseDTO(
                200, "El niño se movió de posición", null),HttpStatus.OK);
    }

    @GetMapping(path = "/delete")
    public ResponseEntity<ResponseDTO> deleteByIdentification(@PathVariable String identification){
        listSEService.getKids().deleteByIdentification(identification);
        return new ResponseEntity<>(new ResponseDTO(200,"the kids has been deleted",null),HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbyage")
    public ResponseEntity<ResponseDTO> KidsByAge(@PathVariable byte age){
        List<KidGenderDTO> kidsBy
    }
    @GetMapping(path = "/kidsreport")
    public ResponseEntity<ResponseDTO> getCountKidsByCityGender(int age) {
        List<ReportKidsDTO> reportKidsDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            if (loc.getCode().length() == 8) {
                String nameCity = loc.getName();
                List<KidGenderDTO> kidGenderDTOS = new ArrayList<>();

                int count = listSEService.getKids().getCountKidsBycityGender(loc.getCode());
                if (count > 0) {
                    reportKidsDTOList.add(new ReportKidsDTO('m', listSEService.getKids().getCountKidsByCityGender(loc.getCode(),
                            '1', age)));
                    reportKidsDTOList.add(new ReportKidsDTO('f', listSEService.getKids().getCountKidsByCityGender(loc.getCode(),
                            '2', age)));
                    int total = reportKidsDTOList.get(0).getQuantity() + reportKidsDTOList.get(1).getQuantity();
                    reportKidsDTOList.add(new ReportKidsDTO(nameCity, kidGenderDTOS, total));
                }
            }
        }

    }
}



