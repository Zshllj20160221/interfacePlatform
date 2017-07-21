import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-*xml", "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
////如果spring的配置文件放在WEB-INF目录下，需要使用这种方法
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
//    @Test
//    public void getAllData() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/getPerson"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }

//    @Test
//    public void getPerson2() throws Exception {
//        //请求ur和param
//        final ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
//                .get("/user/getPerson")
//                .param("name","zsh")
//                .param("age","18")
//        );
//        ra.andExpect(MockMvcResultMatchers.status().isOk())
//          .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("zsh"))
//          .andDo(MockMvcResultHandlers.print());
//        //返回值
//        final MvcResult result = ra.andReturn();
//        String returnStr = result.getResponse().getContentAsString();
//    }

    @Test
    public void getPerson() throws Exception {
        //请求ur和param
        final ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/user/getPerson")
                .param("name","zsh")
                .param("age","18")
        );
        ra.andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("zsh"))
          .andDo(MockMvcResultHandlers.print());

        //返回值
        final MvcResult result = ra.andReturn();
        //assertThat(result.getResponse().getStatus(), is(200));//判断响应状态是否正常
        String returnStr = result.getResponse().getContentAsString();
        logger.debug("returnStr>>>>>>>>>>>>>"+returnStr);
        System.out.println("returnStr>>>>"+returnStr);
        //https://www.oschina.net/code/snippet_273576_34427
        //http://jinnianshilongnian.iteye.com/blog/2004660
        //http://blog.csdn.net/open_curry/article/details/60143780   ****
    }

}
