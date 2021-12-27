package calderon.edwin.anybank.repository;

import calderon.edwin.anybank.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountModel, UUID> {
}
