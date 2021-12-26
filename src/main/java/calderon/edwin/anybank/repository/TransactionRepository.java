package calderon.edwin.anybank.repository;

import calderon.edwin.anybank.model.TransactionModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepository extends CrudRepository<TransactionModel, UUID> {
}
