package calderon.edwin.anybank.steps;

//import calderon.edwin.anybank.model.TransactionModel;
//import calderon.edwin.anybank.repository.TransactionRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TransactionsSteps extends SpringIntegrationTest {

//    @Autowired
//    private TransactionRepository transactionRepository;
    private double amount;
    private double fee;
    private UUID transactionID;

    @Given("^A transaction that is not stored in our system$")
    public void a_transaction_that_is_not_stored_in_our_system() {
        transactionID = UUID.randomUUID();
    }

    @Given("^A transaction that is stored in our system with transaction date (.+) than today$")
    public void a_transaction_that_is_stored_in_our_system(String transactionDate) throws ParseException {
        Date date;
        transactionID = UUID.randomUUID();
        switch (transactionDate)
        {
            case "before":
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")
                        .parse(LocalDate.now(ZoneId.systemDefault()).minusDays(1).toString());
                break;
            case "greater":
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")
                        .parse(LocalDate.now(ZoneId.systemDefault()).plusDays(1).toString());
                break;
            default:
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")
                        .parse(LocalDate.now(ZoneId.systemDefault()).toString());
                break;
        }

        amount = 89.1;
        fee = 6.03;
//        transactionRepository.save(new TransactionModel(transactionID,
//                //UUID.fromString("98134c74bee24626aa52043ed92dde8c"), //TODO: add this parameter
//                amount,
//                fee,
//                date,
//                "Mousse - Chocolate"));
    }

    @When("^I check the status from (.+) channel$")
    public void i_check_the_status_from_x_channel(String channel) throws Throwable {
        String path = "";
        executeGet("http://localhost:8080" + path); //TODO call the real endpoint using channel and transactionID

        HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " +
                latestResponse.getBody(), currentStatusCode.value(), is(200));
    }

    @Then("^The system returns the status (.+)$")
    public void the_system_returns_the_status_X(String status) throws JSONException {
        JSONObject response = new JSONObject(latestResponse.getTheResponse().toString());

        assertThat("status transaction is incorrect : " +
                response.getString("status").toUpperCase(), is(status.toUpperCase()));
    }

    @And("^the amount subtracting the fee$")
    public void the_amount_subtracting_the_fee() throws JSONException {
        JSONObject response = new JSONObject(latestResponse.getTheResponse().toString());

        assertThat("amount incorrect : " + response.getString("amount"),
                response.getString("amount"), is(amount - fee));
    }

    @And("^the amount$")
    public void the_amount() throws JSONException {
        JSONObject response = new JSONObject(latestResponse.getTheResponse().toString());
        assertThat("amount is incorrect : " + response.getString("amount"),
                response.getString("amount"), is(amount));
    }

    @And("^the fee$")
    public void the_fee() throws JSONException {
        JSONObject response = new JSONObject(latestResponse.getTheResponse().toString());
        assertThat("Fee is incorrect : " + response.getString("fee"),
                response.getString("fee"), is(fee));
    }
}
