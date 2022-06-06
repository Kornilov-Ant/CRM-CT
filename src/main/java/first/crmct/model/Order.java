package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Order", schema = "schema_first")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Order_id_seq")
    @SequenceGenerator(name = "Order_id_seq", schema = "schema_first", allocationSize = 1)
    private Long id;

    @Column(name = "Sum")
    private Long sum;

    @Column(name = "Text")
    private String text;

    @Column(name = "Status")
    private Long status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Sum", referencedColumnName = "Sum_id")
    private Sum sums;
}
