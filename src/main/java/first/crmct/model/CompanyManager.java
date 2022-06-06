package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CompanyManager", schema = "schema_first")
@Getter @Setter
public class CompanyManager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CompanyManager_id_seq")
    @SequenceGenerator(name = "CompanyManager_id_seq", schema = "schema_first", allocationSize = 1)
    private Long id;

    @Column(name = "First_name")
    private String firstName;

    @Column(name = "Last_name")
    private String lastName;

    @Column(name = "Contact_number")
    private Long contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "BuyList")
    private Long BuyList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Manager_id", referencedColumnName = "Manager")
    private Company company;
}
