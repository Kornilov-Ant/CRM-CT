package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static first.crmct.model.DbConstants.DB_SCHEMA;

/*
Сущность Заказы.

Order - ссылается на конкретные заказы от этого id
Manager - ссылается на менеджеров, которые идут к заказам этой компании

 */

@Entity
@Table(name = "Orders", schema = DB_SCHEMA)
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Orders_id_seq")
    @SequenceGenerator(name = "Orders_id_seq", schema = DB_SCHEMA, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Manager_id")
    private CompanyManager manager;

    @OneToOne(mappedBy = "orders")
    private Company company;

    @OneToMany(mappedBy = "orders")
    private List<Order> orderList;

}
