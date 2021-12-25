package calderon.edwin.anybank.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TransactionsSteps extends SpringIntegrationTest{

    @Given("^A transaction that is stored in our system$")
    public void a_transaction_that_is_stored_in_our_system() {
    }

    @Given("^A transaction that is not stored in our system$")
    public void a_transaction_that_is_not_stored_in_our_system() {
    }

    @When("^I check the status from any channel$")
    public void i_check_the_status_from_any_channel() {
    }

    @When("^I check the status from CLIENT or ATM channel$")
    public void i_check_the_status_from_client_or_atm_channel() {
    }

    @When("^I check the status from CLIENT channel$")
    public void i_check_the_status_from_client_channel() {
    }

    @When("^I check the status from ATM channel$")
    public void i_check_the_status_from_atm_channel() {
    }

    @When("^I check the status from INTERNAL channel$")
    public void i_check_the_status_from_internal_channel() {
    }

    @And("^the transaction date is before today$")
    public void the_transaction_date_is_before_today(){}

    @And("^the transaction date is equals to today$")
    public void the_transaction_date_is_equals_to_today(){}

    @And("^the transaction date is greater than today$")
    public void the_transaction_date_is_greater_than_today(){}

    @Then("^The system returns the status INVALID$")
    public void the_system_returns_the_status_INVALID() {
    }

    @Then("^The system returns the status SETTLED$")
    public void the_system_returns_the_status_SETTLED() {
    }

    @Then("^The system returns the status PENDING$")
    public void the_system_returns_the_status_PENDING() {
    }

    @Then("^The system returns the status FUTURE$")
    public void the_system_returns_the_status_FUTURE() {
    }

    @And("^the amount subtracting the fee$")
    public void the_amount_subtracting_the_fee(){}

    @And("^the amount$")
    public void the_amount(){}

    @And("^the fee$")
    public void the_fee(){}
}
