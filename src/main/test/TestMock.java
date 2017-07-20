import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring-*xml","file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})////如果spring的配置文件放在WEB-INF目录下，需要使用这种方法
@ActiveProfiles("dev")
//@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)
@Transactional
@WebAppConfiguration
public class TestMock {
    protected Logger logger = LoggerFactory.getLogger(TestMock.class);
    protected MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void getAllData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getPerson"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
//                .andExpect(jsonPath("$.code").value(20000))
//                .andExpect(jsonPath("$.demoList").exists())
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
