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
    public void petsByLetter(char initial) throws ListDEException{

        if (this.head == null) {
            throw new ListDEException("La lista está vacía");
        }

        ListDE listCP = new ListDE();
        NodeDE temp = this.head;

        while (temp != null){
            if (temp.getDataDE().getNamePet().charAt(0) != Character.toUpperCase(initial)){
                listCP.addPet(temp.getDataDE());
            }
            temp = temp.getNextDE();
        }

        temp = this.head;

        while (temp != null){
            if (temp.getDataDE().getNamePet().charAt(0) == Character.toUpperCase(initial)){
                listCP.addPet(temp.getDataDE());
            }
            temp = temp.getNextDE();
        }

        this.head = listCP.getHead();
    }

    public void addPetByPosition(Pet pet, int position) throws ListDEException{
        if (position < 0 || position > size) {
            throw new ListDEException("Posición inválida");
        }
        NodeDE newNode = new NodeDE(pet);
        if (position == 0){
            newNode.setNextDE(head);
            if (head != null){
                head.setPrevious(newNode);
            }
            head = newNode;
        } else {
            NodeDE temp = head;
            for (int i = 0; i < position - 1; i++){
                temp = temp.getNextDE();
            }
            newNode.setNextDE(temp.getNextDE());
            if (temp.getNextDE()!=null){
                temp.getNextDE().setPrevious(newNode);
            }
            temp.setNextDE(newNode);
            newNode.setPrevious(temp);
        }
        size++;
    }
    public void cambiarExtremos() throws ListDEException {
        if (this.head == null || this.head.getNextDE() == null) {
            throw new ListDEException("No es posible cambiar extremos en una lista con menos de dos elementos");
        }

        NodeDE temp = this.head;
        while (temp.getNextDE() != null) {
            temp = temp.getNextDE();
        }

        Pet tempData = temp.getDataDE();
        temp.setDataDE(this.head.getDataDE());
        this.head.setDataDE(tempData);

        NodeDE tempPrev = temp.getPrevious();
        NodeDE headNext = this.head.getNextDE();
        this.head.setNextDE(temp.getNextDE());
        this.head.getNextDE().setPrevious(this.head);
        this.head.setPrevious(tempPrev);
        tempPrev.setNextDE(this.head);
        temp.setNextDE(headNext);
        headNext.setPrevious(temp);
    }

     /* debo mirar si el primer nodo a eliminar es cabeza
     * si es asi le doy el valor de cabeza al siguiente nodo
        si el nodo a eliminar esta en medio de la lista actualizo los enlaces de el nodo previo y siguiente
        * para que queden conectados entre ellos, y elimino en donde se encuentra actualmente parado el temporal
        * si el que deseo elimina es el final, le doy el valor de final al nodo previo    */

    public void deleteNodeDE(int identification) {
        Node temp = head;

        while (temp != null) {
            if (temp.getId() == identification) {
                // Si el nodo a eliminar es el primer nodo en la lista
                if (temp == head) {
                    head = temp.getNext();
                    if (head != null) {
                        head.setPrevious(null);
                    }
                    else {
                        cola = null;
                    }
                }
                // Si el nodo a eliminar es el último nodo en la lista
                else if (temp == cola) {
                    cola = temp.getPrevious();
                    cola.setNext(null);
                }
                // El nodo a eliminar está en el medio de la lista
                else {
                    temp.getPrevious().setNext(temp.getNext());
                    temp.getNext().setPrevious(temp.getPrevious());
                }

                // Desreferencia el nodo eliminado
                temp.setPrevious(null);
                temp.setNext(null);

                return;
            }
            else {
                temp = temp.getNext();
            }
        }
    }

}





