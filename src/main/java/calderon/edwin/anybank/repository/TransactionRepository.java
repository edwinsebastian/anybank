package calderon.edwin.anybank.repository;

import calderon.edwin.anybank.model.TransactionModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {
    List<TransactionModel> findAllByAccountModel_Id(UUID id, Sort sort);

}
