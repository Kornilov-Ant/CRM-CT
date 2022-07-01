package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static first.crmct.model.DbConstants.DB_SCHEMA;

/*
Сущность менеджер компании.

BuyList - список заказов к конкретному менеджер

 */

@Entity
@Table(name = "CompanyManager", schema = DB_SCHEMA)
@Getter @Setter
public class CompanyManager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CompanyManager_id_seq")
    @SequenceGenerator(name = "CompanyManager_id_seq", schema = DB_SCHEMA, allocationSize = 1)
    private Long id;

    @Column(name = "First_name")
    private String firstName;

    @Column(name = "Last_name")
    private String lastName;

    @Column(name = "Contact_number")
    private String contactNumber;

    @Column(name = "Email")
    private String email;

    @Column(name = "Orders_id")
    private Long BuyList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Company_id")
    private Company company;

}
