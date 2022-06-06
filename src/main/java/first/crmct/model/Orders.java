package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Orders", schema = "schema_first")
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Orders_id_seq")
    @SequenceGenerator(name = "Orders_id_seq", schema = "schema_first", allocationSize = 1)
    private Long id;

    @Column(name = "Company")
    private Long company;

    @Column(name = "Order")
    private Long order;

    @Column(name = "Manager")
    private Long manager;

    @OneToOne(mappedBy = "Orders")
    private Company companys;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Company", referencedColumnName = "Company_id")
    private Company comp;
}
