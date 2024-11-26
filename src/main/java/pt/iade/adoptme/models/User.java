package pt.iade.adoptme.models;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "user")  // Mapeando a tabela 'user' no banco de dados
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")  // Supondo que a tabela 'user' tenha uma coluna para o ID
    private int id;

    @Column(name = "usr_name")
    private String name;

    @Column(name = "usr_email")
    private String email;

    @Column(name = "usr_password")
    private String password;

    @Column(name = "usr_date_registered")
    private LocalDate dateRegistered;

    // Construtor vazio
    public User() {}

    // Métodos getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    // Métodos setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}
