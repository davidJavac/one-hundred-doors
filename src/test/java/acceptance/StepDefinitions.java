package acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.core.usecase.port.dto.OutputDto;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.RestTemplate;

public class StepDefinitions {

    private String server = "http://localhost:8081/doors/%s";
    private RestTemplate restTemplate = new RestTemplate();
    private String doors;
    private OutputDto result;

    @Given("^(.*) door to visit$")
    public void i_have_doors_to_visit (String doors) {
        this.doors = doors;
    }

    @When("^the visit is performed$")
    public void the_visit_is_performed () {
        String url = String.format("%s", server, doors);

        result = restTemplate.getForObject(url, OutputDto.class);
    }

    @Then("^I receive a door with number (.*) and status (.*)$")
    public void i_receive_doors_with_number_and_state_as_a_result (String expectedDoors, String expectedState) {
        Assertions.assertEquals(expectedDoors, result.getDoors()[0].getNumber().toString());
        Assertions.assertEquals(expectedState, result.getDoors()[0].getState());
    }
}
