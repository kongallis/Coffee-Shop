
package coffeeshop.models;


public class CatalogItem {
    
    private int id;
    private String code;
    private String description;
    private double cost;

    public CatalogItem(int id, String code, String description, double cost) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "CatalogItem{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
