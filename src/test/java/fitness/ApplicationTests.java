package fitness;

import fitness.controller.CategoryController;
import fitness.controller.EquipmentController;
import fitness.controller.OrderController;
import fitness.controller.ServiceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

    private MockMvc mockMvc;

    private final CategoryController categoryController;
    private final OrderController orderController;
    private final EquipmentController equipmentController;
    private final ServiceController serviceController;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Autowired
    ApplicationTests(final CategoryController categoryController, final OrderController orderController, final EquipmentController equipmentController, final ServiceController serviceController) {
        this.categoryController = categoryController;
        this.orderController = orderController;
        this.equipmentController = equipmentController;
        this.serviceController = serviceController;
    }

    @Test
    void controllerInjectionTest() {
        assertThat(categoryController).isNotNull();
        assertThat(orderController).isNotNull();
        assertThat(serviceController).isNotNull();
        assertThat(equipmentController).isNotNull();
    }

    @Test
    void categoryTest() throws Exception {
        mockMvc.perform(get("/api/category/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("description")));
    }

    @Test
    void orderTest() throws Exception {
        mockMvc.perform(get("/api/order/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("categoryEntity")));
    }

    @Test
    void equipmentTest() throws Exception {
        mockMvc.perform(get("/api/equipment/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("description")));
    }

    @Test
    void serviceTest() throws Exception {
        mockMvc.perform(get("/api/service/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")));
    }

    @Test
    void categoryFindTest() throws Exception {
        mockMvc.perform(get("/api/category?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")));
    }

    @Test
    void serviceFindTest() throws Exception {
        mockMvc.perform(get("/api/service?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")));
    }

    @Test
    void orderFindTest() throws Exception {
        mockMvc.perform(get("/api/order?userId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")));
    }

    @Test
    void equipmentFindTest() throws Exception {
        mockMvc.perform(get("/api/equipment?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")));
    }
}
