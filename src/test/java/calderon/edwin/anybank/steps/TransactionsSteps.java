package calderon.edwin.anybank.steps;

import calderon.edwin.anybank.AnybankApplication;
import calderon.edwin.anybank.dto.TransactionStatusReqDto;
import calderon.edwin.anybank.dto.TransactionStatusResDto;
import calderon.edwin.anybank.enums.ChannelEnum;
import calderon.edwin.anybank.model.AccountModel;
import calderon.edwin.anybank.model.TransactionModel;
import calderon.edwin.anybank.repository.AccountRepository;
import calderon.edwin.anybank.repository.TransactionRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ContextConfiguration(classes = AnybankApplication.class)
@SpringBootTest(classes = AnybankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public class TransactionsSteps {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private static ResponseEntity<TransactionStatusResDto> lastResponseEntity;

    private AccountModel accountModel;
    private UUID transactionReference;
    private BigDecimal amount;
    private BigDecimal fee;
    private Date date;

    @Given("^A transaction that is not stored in our system$")
    public void a_transaction_that_is_not_stored_in_our_system() {
        transactionReference = UUID.randomUUID();
    }

    @Given("^A transaction that is stored in our system with transaction date (.+) than today$")
    public void a_transaction_that_is_stored_in_our_system(String transactionDate) throws ParseException {
        date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        switch (transactionDate)
        {
            case "before":
                c.add(Calendar.DATE, -1);
                date = c.getTime();
                break;
            case "greater":
                c.add(Calendar.DATE, 1);
                date = c.getTime();
                break;
        }

        accountModel = accountRepository.saveAndFlush(new AccountModel(BigDecimal.valueOf(500.00)));
        transactionReference = UUID.randomUUID();
        amount = BigDecimal.valueOf(89.11);
        fee = BigDecimal.valueOf(6.03);
        TransactionModel t = transactionRepository.saveAndFlush(
                new TransactionModel(
                        transactionReference,
                        accountModel,
                        date,
                        amount,
                        fee,
                        ""
                )
        );
    }

    @When("^I check the status from (.+) channel$")
    public void i_check_the_status_from_x_channel(String channel) throws Throwable {
        String path = "/v1/transaction/status";
        lastResponseEntity = restTemplate.postForEntity(
                "http://localhost:8080" + path,
                new TransactionStatusReqDto(
                        transactionReference,
                        ChannelEnum.valueOf(channel)
                ),
                TransactionStatusResDto.class
        );
    }

    @Then("^The system returns the status (.+)$")
    public void the_system_returns_the_status_X(String status) throws JSONException {
        assertThat(lastResponseEntity.getBody().getStatus().toString(), is(status.toUpperCase()));
    }

    @And("^the amount subtracting the fee$")
    public void the_amount_subtracting_the_fee() {
        assertThat(lastResponseEntity.getBody().getAmount(), is(amount.subtract(fee.abs())));
    }

    @And("^the amount$")
    public void the_amount() {
        assertThat(lastResponseEntity.getBody().getAmount(), is(amount));
    }

    @And("^the fee$")
    public void the_fee() {
        assertThat(lastResponseEntity.getBody().getFee(), is(fee));
    }
}
