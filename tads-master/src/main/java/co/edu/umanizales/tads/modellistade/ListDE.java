package co.edu.umanizales.tads.modellistade;

import co.edu.umanizales.tads.exceptions.ListDEExceptions;
import co.edu.umanizales.tads.model.Node;
import lombok.Data;

import java.util.HashMap;

@Data
public class ListDE {

    private Node head;

    private int size;

    public void addPet(Pet pet) throws ListDEExceptions {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNextDE() != null) {
                if (temp.getDataDE().getCodepet().equals(pet.getCodePet())) {
                    throw new ListDEExceptions("Ya existe una mascota con ese código.");
                }
                temp = temp.getNextDE();
            }
            if (temp.getDataDE().getCodePet().equals(pet.getCodePet())) {
                throw new ListDEExceptions("Ya existe una mascota con ese código.");
            }
            NodeDE newNode = new NodeDE(pet);
            newNode.setPrevious(temp);
            temp.setNextDE(newNode);
        } else {
            this.head = new Node(pet);
        }
        this.size++;
    }


    public NodeDE invertirPets(NodeDE head) {
        NodeDE actual = head;
        while (actual != null) {

            NodeDE temp = actual.nextDE;
            actual.setNextDE(actual.previous);
            actual.previous = temp;

            actual = actual.previous;
        }

        head.nextDE = head.previous;
        head.previous = null;
        return head;
    }
    public void orderPetsByGender() throws  ListDEExceptions {
        if (this.head == null) {
            throw new ListDEExceptions("La lista está vacía");
        }

        ListDE malePets = new ListDE();
        ListDE femalePets = new ListDE();

        Node current = this.head;
        while (current != null) {
            if (current.getDataDE().getGenderPet() == 'M') {
                malePets.addPetsToStart(current.getDataDE());
            } else {
                femalePets.addPetsToEnd(current.getDataDE());
            }
            current = current.getNext();
        }

        malePets.connectList();
        this.head = malePets.getHead();
    }



        public boolean eliminar(String identification) {
            if (head == null) { // verifica si la lista está vacía
                return false;
            }

            Node temp = head;

            while (temp != null) {
                if (temp.getPet().getCodigo().equals(identification)) {
                    if (temp == head && temp == cola) { // verifica si el nodo a eliminar es el único nodo en la lista
                        head = null;
                        cola = null;
                    } else if (temp == head) { // verifica si el nodo a eliminar es el primer nodo en la lista
                        head = temp.getNext();
                        head.setPrevious(null);
                    } else if (temp == cola) { // verifica si el nodo a eliminar es el último nodo en la lista
                        cola = temp.getPrevious();
                        cola.setNext(null);
                    } else { // el nodo a eliminar está en el medio de la lista
                        temp.getPrevious().setNext(temp.getNext());
                        temp.getNext().setPrevious(temp.getPrevious());
                    }

                    // Liberar memoria del nodo eliminado
                    temp = null;

                    return true;
                } else {
                    temp = temp.getNext();
                }
            }

            // El nodo a eliminar no fue encontrado en la lista
            return false;
        }
    public void intercalatePetsGender() throws ListDEExceptions{
        ListDE listPetMale = new ListDE();
        ListDE listPetFemale = new ListDE();
        NodeDE temp = this.head;

        if (temp == null) {
            throw new ListDEExceptions("La lista está vacía");
        }

        while (temp != null) {
            if (temp.getDataDE().getGenderPet() == 'M') {
                listPetMale.addPet(temp.getDataDE());
            }
            if (temp.getDataDE().getGenderPet() == 'F') {
                listPetFemale.addPet(temp.getDataDE());
            }
            temp = temp.getNextDE();
        }
        ListDE newListPetsFemale = new ListDE();
        NodeDE petMaleNode = listPetMale.getHead();
        NodeDE petFemaleNode = listPetFemale.getHead();
        while (petMaleNode != null || petFemaleNode != null) {
            if (petMaleNode != null) {
                newListPetsFemale.addPet(petMaleNode.getDataDE());
                petMaleNode = petMaleNode.getNextDE();
            }
            if (petFemaleNode != null) {
                newListPetsFemale.addPet(petFemaleNode.getDataDE());
                petFemaleNode = petFemaleNode.getNextDE();
            }
        }
        this.head = newListPetsFemale.getHead();
    }

    public double calculateAverageAge() throws ListDEExceptions {
        if (this.head == null) {
            throw new ListDEExceptions("La lista está vacía");
        }

        NodeDE current = this.head;
        double sum = 0.0;
        int count = 0;

        // Recorrer la lista y sumar las edades de las mascotas
        while (current != null) {
            sum += current.getDataDE().getAgePet();
            count++;
            current = current.getNextDE();
        }

        // Calcular el promedio
        double average = sum / count;

        return average;
    }

    public void generateCityReport() throws ListDEExceptions {
        if (this.head == null) {
            throw new ListDEExceptions("La lista está vacía");
        }

        HashMap<String, Integer> cityCounts = new HashMap<>();
        NodeDE current = this.head;

        // Recorrer la lista y contar las mascotas por ciudad
        while (current != null) {
            String city = current.getDataDE().getCityPet();
            if (cityCounts.containsKey(city)) {
                int count = cityCounts.get(city);
                cityCounts.put(city, count + 1);
            } else {
                cityCounts.put(city, 1);
            }
            current = current.getNextDE();
        }

        // Imprimir el reporte
        System.out.println("Reporte de mascotas por ciudad:");
        for (String city : cityCounts.keySet()) {
            int count = cityCounts.get(city);
            System.out.printf("%s: %d mascotas\n", city, count);
        }
    }


}


