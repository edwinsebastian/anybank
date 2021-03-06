package calderon.edwin.anybank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class AccountModel extends Model{
    private BigDecimal balance;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountModel")
    private List<TransactionModel> transactions = Collections.emptyList();

    public AccountModel(){
        this(BigDecimal.ZERO);
    }
    public AccountModel(BigDecimal balance){
        this.setId(UUID.randomUUID());
        this.balance = balance;
    }
}
