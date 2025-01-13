package pt.iade.adoptme.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")  // tabela 'user' no banco de dados
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private int id;

    @Column(name = "usr_name", nullable = false)
    private String name;

    @Column(name = "usr_email", nullable = false, unique = true)
    private String email;

    @Column(name = "usr_password", nullable = false)
    private String password;

    @Column(name = "usr_phone", nullable = false)
    private String phone;

    @Column(name = "urs_address", nullable = false)
    private String address;

    @Column(name = "urs_birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "usr_date_registered", nullable = false)
    private LocalDate dateRegistered;
}
