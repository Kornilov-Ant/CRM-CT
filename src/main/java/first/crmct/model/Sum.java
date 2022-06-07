package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Sum", schema = "schema_first")
@Getter @Setter
public class Sum {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sum_id_seq")
    @SequenceGenerator(name = "Sum_id_seq", schema = "schema_first", allocationSize = 1)
    private Long id;

    @Column(name = "Sum")
    private Long sum;

    @Column(name = "Consumption")
    private Long consumption;

    @Column(name = "income")
    private Long income;

    @OneToOne(mappedBy = "Sum")
    private Order order;
}
