package first.crmct.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static first.crmct.model.DbConstants.DB_SCHEMA;

@Entity
@Table(name = "roles", schema = DB_SCHEMA)
@Getter @Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    @SequenceGenerator(name = "roles_id_seq", schema = DB_SCHEMA, allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;


}
