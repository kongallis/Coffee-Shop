
package coffeeshop.models;


import java.util.Date;
public class Order {

    private Long id;
    private Date createdAt;
    private boolean espresso_classic;
    private boolean espresso_arabica;
    private boolean espresso_decaf;
    private boolean milk;
    private boolean soy;
    private boolean caramel_syrop;
    private boolean almond_milk;

    public Order(Long id, Date createdAt, boolean espresso_classic, boolean espresso_arabica, boolean espresso_decaf, boolean milk, boolean soy, boolean caramel_syrop, boolean almond_milk) {
        this.id = id;
        this.createdAt = createdAt;
        this.espresso_classic = espresso_classic;
        this.espresso_arabica = espresso_arabica;
        this.espresso_decaf = espresso_decaf;
        this.milk = milk;
        this.soy = soy;
        this.caramel_syrop = caramel_syrop;
        this.almond_milk = almond_milk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isEspresso_classic() {
        return espresso_classic;
    }

    public void setEspresso_classic(boolean espresso_classic) {
        this.espresso_classic = espresso_classic;
    }

    public boolean isEspresso_arabica() {
        return espresso_arabica;
    }

    public void setEspresso_arabica(boolean espresso_arabica) {
        this.espresso_arabica = espresso_arabica;
    }

    public boolean isEspresso_decaf() {
        return espresso_decaf;
    }

    public void setEspresso_decaf(boolean espresso_decaf) {
        this.espresso_decaf = espresso_decaf;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public boolean isCaramel_syrop() {
        return caramel_syrop;
    }

    public void setCaramel_syrop(boolean caramel_syrop) {
        this.caramel_syrop = caramel_syrop;
    }

    public boolean isAlmond_milk() {
        return almond_milk;
    }

    public void setAlmond_milk(boolean almond_milk) {
        this.almond_milk = almond_milk;
    }
}

