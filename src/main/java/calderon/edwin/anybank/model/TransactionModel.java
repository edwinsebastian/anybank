package calderon.edwin.anybank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class TransactionModel extends Model {
    private BigDecimal amount;
    private BigDecimal fee;
    private Date date;
    private String description;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(columnDefinition = "account_model_id")
    private AccountModel accountModel = new AccountModel();

    public TransactionModel(UUID reference, AccountModel accountModel, Date date, BigDecimal amount, BigDecimal fee, String description){
        this.setId(reference);
        this.accountModel = accountModel;
        this.date = date;
        this.amount = amount;
        this.fee = fee;
        this.description = description;
    }

    public TransactionModel(){ }
}
