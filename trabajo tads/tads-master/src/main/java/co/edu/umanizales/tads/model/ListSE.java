 package co.edu.umanizales.tads.model;

import lombok.Data;



 @Data
public class ListSE {
    private Node head;
    private int size;

    public void add(Kid kid) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
        size++;
    }


    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
        size++;
    }

    public void invert() {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }


    public void changeExtremes() {
        if (this.head != null && this.head.getNext() != null) {
            Node temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            //temp está en el último
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }

    }

    public int getCountKidsByLocationCode(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountKidsByDeptoCode(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public void passByPosition(Kid kid) {
        Node temp = head;

        for (int i = 0; i < 10; i++) {
            if (temp.getNext() != null) {
                temp = temp.getNext();
            }
            head.setNext(temp);

        }

    }

    public void deleteByIdentification(String identification) {
        Node temp = head;
        Node prev = null;

        while (temp.getNext() != null && temp.getData().getIdentification() != identification) {
            prev = temp;
            temp = temp.getNext();

            if (temp != null) {
                if (prev == null) {
                    head = temp.getNext();
                } else {
                    prev.setNext(temp.getNext());
                }
            }
        }

    }
    public float averAge(){

        if (head != null){
            Node temp = head;
            int contador = 0;
            int ages = 0;
            while(temp.getNext() != null) {
                contador++;
                ages = ages + temp.getData().getAge();
                temp = temp.getNext();
            }
            return (float) ages/contador;
        }
        else{
            return (float) 0;
        }
    }
    public int getKidsByGenderCity( int age, String code, String codegender){
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code) && temp.getData().getGenderKid().getCode().equals(codegender)){
                    if(temp.getData().getAge() > age){
                        count++;
                    }
                }
            }
        }
        return count;
    }
    public void boyStartGirlsLast(){
        ListSE listCopy = new ListSE();
        Node temp = this.head;
        while (temp != null){
            if (temp.getData().getGender() == 'M'){
                listCopy.add(temp.getData());
            }
            temp = temp.getNext();
        }
        temp = this.head;

        while (temp != null){
            if (temp.getData().getGender() == 'F'){
                listCopy.add((temp.getData()));
            }
            temp = temp.getNext();
        }
        this.head = listCopy.getHead();
    }




     public void deleteByAge(byte age) {
         if (this.head != null) {
             ListSE listCP = new ListSE();
             Node temp = this.head;
             while (temp != null) {
                 if (temp.getData().getAge() <= age) {
                     listCP.addToStart(temp.getData());
                 }
                 temp = temp.getNext();
             }
             this.head = listCP.getHead();
         }
     }

            public void sendfinletter ( char initial){

                ListSE sendfinletter = new ListSE();
                Node temp = this.head;

                while (temp != null) {
                    if (temp.getData().getName().charAt(0) != Character.toUpperCase(initial)) {
                        sendfinletter.add(temp.getData());
                    }
                    temp = temp.getNext();
                }

                temp = this.head;

                while (temp != null) {
                    if (temp.getData().getName().charAt(0) == Character.toUpperCase(initial)) {
                        sendfinletter.add(temp.getData());
                    }
                    temp = temp.getNext();
                }

                this.head = sendfinletter.getHead();
            }
            public int rangeByAge ( int min, int max){
                Node temp = head;
                int counter = 0;
                while (temp != null) {
                    if (temp.getData().getAge() > min && temp.getData().getAge() < max) {
                        counter++;
                    }
                    temp = temp.getNext();
                }
                return counter;
            }
    public void forwardPositions(String identification, int positions) {
        if (head == null) {
            // La lista está vacía, no hay nada que hacer
            return;
        }

        // Busca el nodo con la identificación especificada
        Node temp = head;
        while (temp != null && !temp.getData().getIdentification().equals(identification)) {
            temp = temp.getNext();
        }

        if (temp == null) {
            // No se encontró ningún nodo con la identificación especificada, no hay nada que hacer
            return;
        }

        // Determina la nueva posición del nodo después de moverlo hacia adelante
        int newPosition = positions.get(temp) + positions;
        if (newPosition >= size) {
            // El nodo terminaría después del final de la lista, así que se mueve al principio
            addToStart(temp.getData());
        } else {
            // Mueve el nodo a su nueva posición
           deleteByIdentification(String.valueOf(temp));
            addByPosition(temp.getData(), newPosition);
        }
    }




     public void InterlayerBoyAndGirl() {
         if (head == null || head.getNext() == null) {
             return; // Lista vacía o con solo un elemento, no hay nada que hacer
         }
         Node nodetemp = head;
         Node next = nodetemp.getNext();
         boolean nodetempIsBoy = nodetemp.getData().equals("niño");
         while (next != null) {
             boolean nextIsBoy = next.getData().equals("niño");
             if ((nodetempIsBoy && !nextIsBoy) || (!nodetempIsBoy && nextIsBoy)) {
                 String temp = String.valueOf(nodetemp.getData());
                 nodetemp.setData(next.getData());
                 next.setData(nodetemp.getData());
             }
             nodetemp = next;
             next = nodetemp.getNext();
             nodetempIsBoy = nodetemp.getData().equals("niño");
         }
     }
 }










