package baseDades.model;

public class Event {
    private int id;
    private String title;
    private String description;
    private Category category;
    private String city;
    private String creatorId;

    public Event () {
    }

    public Event (int id, String title, String description, Category category, String city, String creatorId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.city = city;
        this.creatorId = creatorId;
    }
    
}
