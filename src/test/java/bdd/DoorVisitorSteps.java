package bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.core.usecase.port.dto.OutputDto;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DoorVisitorSteps extends CucumberBootstrap {

    private String server = "http://localhost:8081/doors/%s";
    private RestTemplate restTemplate = new RestTemplate();
    private String doors;
    private OutputDto result;
    @Given("^(.*) doors to visit$")
    public void i_have_doors_to_visit (String doors) {
        this.doors = doors;
    }

    @When("^the visit is performed$")
    public void the_visit_is_performed () {
        String url = String.format(server, doors);

        ResponseEntity<OutputDto> responseEntity = restTemplate.postForEntity(url, null, OutputDto.class);
        result = responseEntity.getBody();

    }

    @Then("^I receive a door with number (.*) and status (.*)$")
    public void i_receive_one_door_with_number_and_status_as_a_result (String expectedDoors, String expectedState) {
        System.out.println("then running");
        Assertions.assertEquals(expectedDoors, result.getDoors()[0].getNumber().toString());
        Assertions.assertEquals(expectedState, result.getDoors()[0].getState());
    }

    @Then("^I receive (.*) doors: one with number (.*) and status (.*), and the other with number (.*) and status (.*)$")
    public void i_receive_two_doors_with_number_and_status_as_a_result (String expectedAmountDoors, String expectedFirstNumber, String expectedFirstStatus, String expectedSecondNumber, String expectedSecondStatus) {
        Assertions.assertEquals(expectedAmountDoors, String.valueOf(result.getDoors().length));
        Assertions.assertEquals(expectedFirstNumber, result.getDoors()[0].getNumber().toString());
        Assertions.assertEquals(expectedFirstStatus, result.getDoors()[0].getState());
        Assertions.assertEquals(expectedSecondNumber, result.getDoors()[1].getNumber().toString());
        Assertions.assertEquals(expectedSecondStatus, result.getDoors()[1].getState());
    }
}
