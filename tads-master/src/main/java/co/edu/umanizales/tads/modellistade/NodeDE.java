package co.edu.umanizales.tads.modellistade;

import lombok.Data;

@Data
public class NodeDE {
    private Pet dataDE;

    private NodeDE nextDE;

    private NodeDE previous;

    public NodeDE(Pet pet) {this.dataDE = dataDE;
    }
}
