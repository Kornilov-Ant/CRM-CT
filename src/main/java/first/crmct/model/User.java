package first.crmct.model;

/*



 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static first.crmct.model.DbConstants.DB_SCHEMA;

@Entity
@Table(name = "users", schema = DB_SCHEMA)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", schema = DB_SCHEMA, allocationSize = 1)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles", schema = DB_SCHEMA,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}
