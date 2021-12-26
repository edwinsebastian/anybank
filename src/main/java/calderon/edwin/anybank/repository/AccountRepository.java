package calderon.edwin.anybank.repository;

import calderon.edwin.anybank.model.AccountModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<AccountModel, UUID> {
}
