package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.RangesK;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RangeAgeKidsDTO {
    RangesK ranges;
    int quantity;
}
