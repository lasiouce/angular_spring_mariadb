package evoting.app.demo.persistence;

import evoting.app.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository <User, Long> {
}
