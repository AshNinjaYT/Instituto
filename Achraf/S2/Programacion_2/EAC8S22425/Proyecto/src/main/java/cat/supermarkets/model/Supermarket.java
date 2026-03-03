package cat.supermarkets.model;

import jakarta.persistence.*;

@Entity
@Table(name = "supermarkets")
public class Supermarket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(precision = 10) // Sin scale
    private float longitude;

    @Column(precision = 10) // Sin scale
    private float latitude;

    // Constructor por defecto
    public Supermarket() {
    }

    // Constructor parametrizado
    public Supermarket(String name, String city, float longitude, float latitude) {
        setName(name);
        setCity(city);
        setLongitude(longitude);
        setLatitude(latitude);
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("La ciudad no puede ser nula o vacía.");
        }
        this.city = city;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    // Método funcional para mostrar como String
    public String supermarketToString() {
        return String.format("%s,%s,%.7f,%.7f", name, city, longitude, latitude);
    }
}