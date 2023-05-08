package co.edu.umanizales.tads.service;


import co.edu.umanizales.tads.modellistade.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {
    private ListDE Pet;

    public ListDEService() {
        Pet = new ListDE();

    }

}
