package tri.chung.sbjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tri.chung.sbjwt.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

  Role findByCode(String code);
}
