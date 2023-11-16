public class pizza{
    private double price;
    private String name;
    private String description;
    private char size;

    public pizza(String name, char size){
        price = updatePrice(size, name);
        this.name = name;
        description = updateDescription(size, name);
        this.size = size;
    }

    public String getName(){
        return name;
    }

    public char getSize(){
        return size;
    }

    public String getDescription(){
        return description;
    }

    public double getPrice(){
        return price;
    }
    
    public void updateSize(char s){
        size = s;
        price = updatePrice(s, name);
        description = updateDescription(s, name);
    }

    public double updatePrice(char size, String name){
        double sizePrice = 0;
        double toppingsPrice = 0;

        switch (size) {
            case 's':
                sizePrice = 12;
                break;
            case 'm':
                sizePrice = 15;
                break;
            case 'l':
                sizePrice = 18;
                break;
        }

        if(name.equals("Pepperoni")){
            toppingsPrice += 1;
        }

        if(name.equals("Mushroom")){
            toppingsPrice += 1.5;
        }

        if(name.equals("Cheese")){
            toppingsPrice += 1;
        }

        return sizePrice + toppingsPrice;
    }

    public String updateDescription(char size, String name){
        String s = "";

        switch (size) {
            case 's':
                s = "Small";
                break;
            case 'm':
                s = "Medium";
                break;
            case 'l':
                s = "Large";
                break;
        }

        return s + " " + name + " Pizza";
    }
}
