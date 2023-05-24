package doanlehyvong.demo.repository;

import doanlehyvong.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework. stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT U FROM User U WHERE U.username = ?1")
    User findByUsername(String username);
}
