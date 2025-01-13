package pt.iade.adoptme.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity@Data
@NoArgsConstructor
@Table(name = "loc")
public class Loc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loc_id")
    @JsonProperty("id") // Alinha com o frontend
    private Long locId;

    @Column(name = "loc_name")
    @JsonProperty("name") // Alinha com o frontend
    private String locName;

    @Column(name = "loc_address")
    @JsonProperty("address") // Opcional, caso necessário no frontend
    private String locAddress;

    @Column(name = "loc_latitude")
    @JsonProperty("latitude") // Alinha com o frontend
    private double locLatitude;

    @Column(name = "loc_longitude")
    @JsonProperty("longitude") // Alinha com o frontend
    private double locLongitude;

    @Column(name = "loc_description")
    @JsonProperty("description") // Alinha com o frontend
    private String locDescription;

    @Column(name = "loc_type")
    @JsonProperty("type") // Opcional, caso necessário no frontend
    private String locType;
}

