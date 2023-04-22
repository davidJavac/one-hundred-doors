package acceptance;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.core.usecase.port.dto.DoorDto;
import org.example.core.usecase.port.dto.OutputDto;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DoorVisitorStepsIT extends CucumberBootstrapIT {

    private String server = "http://localhost:8081/doors/%s";
    private RestTemplate restTemplate = new RestTemplate();
    private String doors;
    private OutputDto result;
    private List<OutputDto> resultList;
    private DataTable dataTable;
    
    @Given("^(.*) door to visit$")
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

    @Given("^an amount of doors to visit as table$")
    public void an_amount_of_doors_to_visit (DataTable dataTable) {
        this.dataTable = dataTable;
    }

    @When("^the visit is performed to multiple doors$")
    public void the_visit_is_performed_as_table () {
        List<String> inputDoors = dataTable.asList(String.class);
        resultList = new ArrayList<>();
        for (String doors : inputDoors) {
            String url = String.format(server, doors);

            ResponseEntity<OutputDto> responseEntity = restTemplate.postForEntity(url, null, OutputDto.class);
            resultList.add(responseEntity.getBody());
        }
    }

    @Then("^doors should have the correct number and status$")
    public void doors_should_have_the_correct_number_and_status (DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (OutputDto result : resultList) {
            for (DoorDto doorDto : result.getDoors()) {
                // Process each row of the table
                Boolean resultIsInRule = Boolean.FALSE;
                for (Map<String, String> row : rows) {
                    int number = Integer.parseInt(row.get("number"));
                    String status = row.get("status");
                    if (doorDto.getNumber().equals(number) && doorDto.getState().equals(status)) {
                        resultIsInRule = Boolean.TRUE;
                    }
                }
                Assertions.assertTrue(resultIsInRule);
            }

        }
    }
}
