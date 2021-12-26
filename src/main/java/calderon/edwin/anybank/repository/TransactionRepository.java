package calderon.edwin.anybank.repository;

import calderon.edwin.anybank.model.TransactionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionModel, UUID> {
}
