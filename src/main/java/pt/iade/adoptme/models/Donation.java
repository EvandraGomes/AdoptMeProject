package pt.iade.adoptme.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "don_id")
    private int id;

    @Column(name = "don_typ_id")
    private String type;

    @Column(name = "don_amount")
    private LocalDate amount;

    @Column(name = "don_payment_method")
    private String method;

    @Column(name = "don_date")
    private LocalDate date;

    @Column(name = "don_usr_id")
    private int user;

    public Donation(){
    }

    public int getId(){
        return id;
    }

    public String getdon_typ_id(){
        return type;
    }

    public LocalDate getdon_amount(){
        return amount;
    }

    public String getdon_payment_method(){
        return method;
    }

    public LocalDate getdon_date(){
        return date;
    }
    public int getdon_usr_id(){
        return user;
    }

    public void setdon_typ_idChar(String don_typ_id){this.type=don_typ_id;}
    public void setLocalDatebrtD(LocalDate don_amount){
        this.amount=don_amount;
    }
    public void setnametring(String don_payment_method){
        this.method=don_payment_method;
    }
    public void setintdon_usr_id(int don_usr_id){
        this.user=don_usr_id;
    }
    public void setLocalDatedon_usr_id(LocalDate don_date){
        this.date=don_date;
    }


}
