package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.RangesK;
import org.w3c.dom.ranges.Range;

import java.util.ArrayList;
import java.util.List;

public class RangeService {
    private List<RangesK> ranges ;
    public RangeService(){
        ranges = new ArrayList<>();
        ranges.add(new RangesK(1,3));
        ranges.add(new RangesK(4,6));
        ranges.add(new RangesK(7,9));
        ranges.add(new RangesK(10,12));
        ranges.add(new RangesK(14,15));
    }
}
