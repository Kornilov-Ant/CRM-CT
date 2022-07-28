package first.crmct.model;

/*



 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static first.crmct.model.DbConstants.DB_SCHEMA;

@Entity
@Table(name = "status_order", schema = DB_SCHEMA)
@Getter @Setter
public class StatusOrder {

    @Id
    private Long id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "statusOrder")
    private List<Order> orderList;
}
