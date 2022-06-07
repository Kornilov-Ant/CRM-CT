package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*
Сущность компания.

Manager - менеджер с котором контакт в компании (может быть несколько у компании)
BuyList - список заказов компании

 */

@Entity
@Table(name = "Company", schema = "schema_first")
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Company_id_seq")
    @SequenceGenerator(name = "Company_id_seq", schema = "schema_first", allocationSize = 1)
    private Long id;

    @Column(name = "Manager_id")
    private Long manager;

    @Column(name = "Orders_id")
    private Long buyList;

    @OneToMany(mappedBy = "Company")
    private List<CompanyManager> companyManagerlist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Orders_id", referencedColumnName = "Id")
    private Orders orders;

}
