package calderon.edwin.anybank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class TransactionModel extends Model {
    private double amount;
    private double fee;
    private Date date;
    private String description;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(columnDefinition = "account_model_id")
    private AccountModel accountModel = new AccountModel();

//{
//    "reference":"12345A"
//    "account_iban":"ES9820385778983000760236",
//    "date":"2019-07-16T16:55:42.000Z",
//    "amount":193.38,
//    "fee":3.18,
//    "description":"Restaurant payment"
//}

    public TransactionModel(UUID reference, AccountModel accountModel, double amount, double fee, Date date, String description){
        this.setId(reference);
        this.amount = amount;
        this.fee = fee;
        this.date = date;
        this.description = description;
        this.accountModel = accountModel;
    }

    public TransactionModel(){ }
}
