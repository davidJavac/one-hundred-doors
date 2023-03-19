package adapter.rest;

import org.example.adapter.controller.rest.DoorRestController;
import org.example.config.rest.RouteModule;
import org.example.core.usecase.port.DoorsExecutor;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DoorRestControllerTest {

    private MockMvc mockMvc;
    private DoorRestController doorRestController;
    private DoorsExecutor doorsExecutor;
    private static final Integer QUANTITY = 100;

    @BeforeEach
    public void setUp() {
        doorsExecutor = mock(DoorsExecutor.class);
        doorRestController = new DoorRestController(doorsExecutor);
        mockMvc = MockMvcBuilders.standaloneSetup(doorRestController).build();
    }

    @Test
    public void test_when_post_door_controller_then_should_return_ok() {
        ResponseEntity<Object> responseEntity = doorRestController.postDoorsQuantity(QUANTITY);
        Assertions.assertEquals(HttpStatus.ACCEPTED.value(), responseEntity.getStatusCodeValue());
        verify()
    }

    @Test
    public void test_when_post_door_endpoint_with_quantity_then_should_run_ok() throws Exception {
        mockMvc.perform(post(String.format("%s/%s", RouteModule.DOORS, QUANTITY)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }
}
