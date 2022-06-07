package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "Company")
    private List<CompanyManager> companyManagerlist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BuyList", referencedColumnName = "Orders_id")
    private Orders orders;

}
