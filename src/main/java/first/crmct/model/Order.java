package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static first.crmct.model.DbConstants.DB_SCHEMA;

/*
Сущность заказ.

Sum - сумма заказа
Text - описание заказа
Status - статус заказа

 */

@Entity
@Table(name = "Order", schema = DB_SCHEMA)
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Order_id_seq")
    @SequenceGenerator(name = "Order_id_seq", schema = DB_SCHEMA, allocationSize = 1)
    private Long id;

    @Column(name = "Text")
    private String text;

    @Column(name = "Status")
    private Long status;

    @Column(name = "Sum")
    private Long sum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Orders_Id")
    private Orders orders;

}
