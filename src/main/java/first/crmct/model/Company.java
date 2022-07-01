package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static first.crmct.model.DbConstants.DB_SCHEMA;

/*
Сущность компания.

Manager - менеджер с котором контакт в компании (может быть несколько у компании)


 */

@Entity
@Table(name = "Company", schema = DB_SCHEMA)
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Company_id_seq")
    @SequenceGenerator(name = "Company_id_seq", schema = DB_SCHEMA, allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "Manager_id")
    private Long manager;

    @OneToMany(mappedBy = "company")
    private List<CompanyManager> companyManagerlist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Orders_id")
    private Orders orders;

}
