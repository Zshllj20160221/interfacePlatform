package interfacePlatform;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.IsCollectionContaining;
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

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-*xml", "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
////如果spring的配置文件放在WEB-INF目录下，需要使用这种方法
@ActiveProfiles("dev")
//@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)
@Transactional
@WebAppConfiguration
public class TestWithMockmvc {
    private Logger logger = LoggerFactory.getLogger(TestWithMockmvc.class);
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

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

//    @Test
//    public void getPerson() throws Exception {
//        //请求ur和param
//        final ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders
//                .get("/user/getPerson")
//                .param("name","zsh")
//                .param("age","18")
//        );
//
//        ra.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("zsh"))
//                .andDo(MockMvcResultHandlers.print());
//
//        //返回值
//        final MvcResult result = ra.andReturn();
//        //assertThat(result.getResponse().getStatus(), is(200));//判断响应状态是否正常
//        String returnStr = result.getResponse().getContentAsString();
//        logger.debug("returnStr>>>>>>>>>>>>>"+returnStr);
//        //https://www.oschina.net/code/snippet_273576_34427
//        //http://jinnianshilongnian.iteye.com/blog/2004660
//        //http://blog.csdn.net/open_curry/article/details/60143780   ****
//    }

    @Test
    public void testPathJson() throws Exception {
        //https://www.oschina.net/code/snippet_273576_34427
        //http://blog.csdn.net/nicolezhangcheers/article/details/51322171
        //http://www.programcreek.com/java-api-examples/index.php?api=org.hamcrest.core.IsCollectionContaining
        String json = "\n" +
                "{ \"store\": {\n" +
                "    \"book\": [ \n" +
                "      { \"category\": \"reference\",\n" +
                "        \"author\": \"Nigel Rees\",\n" +
                "        \"title\": \"Sayings of the Century\",\n" +
                "        \"price\": 8.95\n" +
                "      },\n" +
                "      { \"category\": \"fiction\",\n" +
                "        \"author\": \"Evelyn Waugh\",\n" +
                "        \"title\": \"Sword of Honour\",\n" +
                "        \"price\": 12.99,\n" +
                "        \"isbn\": \"0-553-21311-3\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"bicycle\": {\n" +
                "      \"color\": \"red\",\n" +
                "      \"price\": 19.95\n" +
                "    }\n" +
                "  }\n" +
                "}\n" +
                " ";

        logger.debug("json:"+json);

//        All authors:

        List<String> authors = JsonPath.read(json, "$.store.book[*].author");

//        Author of first book in store:

        String author = JsonPath.read(json, "$.store.book[1].author");

//        All books with category = "reference"

        List<Object> books = JsonPath.read(json, "$.store.book[?(@.category == 'reference')]");

        List<Object> books2 = JsonPath.read(json, "$.store.book[?]", Filter.filter(Criteria.where("category").is("reference")));

//        All books that cost more than 10 USD

        List<Object> books3 = JsonPath.read(json, "$.store.book[?(@.price > 10)]");

        List<Object> books4 = JsonPath.read(json, "$.store.book[?]", Filter.filter(Criteria.where("price").gt(10)));

//        All books that have isbn

        List<Object> books5 = JsonPath.read(json, "$.store.book[?(@.isbn)]");

        List<Object> books6 = JsonPath.read(json, "$.store.book[?]", Filter.filter(Criteria.where("isbn").exists(true)));

//        Chained filters

        Filter filter = Filter.filter(Criteria.where("isbn").exists(true).and("category").in("fiction", "reference"));

        List<Object> books7 = JsonPath.read(json, "$.store.book[?]", filter);

//        Custom filters

        Filter myFilter = new Filter.FilterAdapter<Map<String, Object>>(){
            @Override
            public boolean accept(Map<String, Object> map) {
                return map.containsKey("isbn");
            }
        };

        List<Object> book8 = JsonPath.read(json, "$.store.book[?]", myFilter);

//        All prices in the document

        List<Double> prices = JsonPath.read(json, "$..price");

//        Compiled path

//        You can pre compile a path and use it multiple times

        JsonPath path = JsonPath.compile("$.store.book[*]");

        List<Object> books9 = path.read(json);

//        Assert

//        Asserts are made with Hamcrest matchers

        JsonAssert.with(json).assertThat("$.store.bicycle.color", Matchers.equalTo("red"))
                .assertThat("$.store.bicycle.price", Matchers.equalTo(19.95D));

//        Add some static imports and you get this

        JsonAssert.with(json).assertThat("$.store.bicycle.color", Matchers.equalTo("red"))
                .assertThat("$.store.bicycle.price", Matchers.equalTo(19.95D));

//        The Hamcrest library contains a lot of different matchers and they can often be nested.

        JsonAssert.with(json).assertThat("$..author", IsCollectionContaining.hasItems("Nigel Rees", "Evelyn Waugh"))
                .assertThat("$..author", Matchers.is(JsonAssert.collectionWithSize(Matchers.equalTo(2))));

        JsonAssert.with(json).assertThat("$.store.book[?(@.category == 'x')]", JsonAssert.emptyCollection());

    }
}
