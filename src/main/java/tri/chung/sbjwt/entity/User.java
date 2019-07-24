package tri.chung.sbjwt.entity;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String username;

  @Column
  private String password;

  @Column
  private String fullname;

  // @Transient
  // private String passwordConfirm;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id", nullable = false)},
      inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false)})
  @JsonManagedReference
  private Set<Role> roles;

  // public User(Long id, String username, String password, String fullname, Set<Role> roles) {
  // super();
  // this.id = id;
  // this.username = username;
  // this.password = password;
  // this.fullname = fullname;
  // this.roles = roles;
  // }

  public User() {}

  public User(String username, String password, String fullname) {
    this.username = username;
    this.password = password;
    this.fullname = fullname;
  }


  // public String getPasswordConfirm() {
  // return passwordConfirm;
  // }
  //
  // public void setPasswordConfirm(String passwordConfirm) {
  // this.passwordConfirm = passwordConfirm;
  // }


}
