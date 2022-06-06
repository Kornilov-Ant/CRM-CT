package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Company", schema = "schema_first")
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Company_id_seq")
    @SequenceGenerator(name = "Company_id_seq", schema = "schema_first", allocationSize = 1)
    private Long id;

    @Column(name = "Manager")
    private Long manager;

    @Column(name = "BuyList")
    private Long buyList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Manager", referencedColumnName = "Manager_id")
    private CompanyManager companyManager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BuyList", referencedColumnName = "Orders_id")
    private Orders orders;

    @OneToOne(mappedBy = "Company")
    private Orders ord;
}
