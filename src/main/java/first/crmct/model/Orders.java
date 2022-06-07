package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*
Сущность Заказы.

Order - ссылается на конкретные заказы от этого id
Manager - ссылается на менеджеров, которые идут к заказам этой компании

 */

@Entity
@Table(name = "Orders", schema = "schema_first")
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Orders_id_seq")
    @SequenceGenerator(name = "Orders_id_seq", schema = "schema_first", allocationSize = 1)
    private Long id;

    @Column(name = "Order")
    private Long order;

    @Column(name = "Manager")
    private Long manager;

    @OneToOne(mappedBy = "Orders")
    private Company company;

    @OneToMany(mappedBy = "Orders")
    private List<Order> orderList;

}
