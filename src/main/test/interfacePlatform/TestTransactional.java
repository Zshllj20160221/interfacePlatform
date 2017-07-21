package interfacePlatform;

import com.jayway.jsonassert.JsonAssert;
import org.hamcrest.Matchers;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by zsh on 2017/7/21.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-*xml", "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
////如果spring的配置文件放在WEB-INF目录下，需要使用这种方法
@ActiveProfiles("dev")
// TransactionConfiguration不用    事务回滚也起作用
//@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)
//@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = false)
@Transactional
@WebAppConfiguration
public class TestTransactional {

    private Logger logger = LoggerFactory.getLogger(TestTransactional.class);
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // test Transactional
    @Test
    public void test() throws Exception{

        final ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/user/addUser").param("userName","test").param("age","21")
        );

        ra.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("user/view"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.key").value("/user/addUser"))
                .andDo(MockMvcResultHandlers.print());

        final MvcResult result = ra.andReturn();

        String responseString = result.getResponse().getContentAsString();
        logger.debug("responseString:"+responseString);
        JsonAssert.with(responseString).assertThat("$.status", Matchers.equalTo(10000));
    }
}
