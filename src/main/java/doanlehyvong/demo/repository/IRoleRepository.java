package doanlehyvong.demo.repository;

import doanlehyvong.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype. Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT r.id FROM Role r WHERE г.name = ?1", nativeQuery = true)
    Long getRoleIdByName (String roleName);
}
