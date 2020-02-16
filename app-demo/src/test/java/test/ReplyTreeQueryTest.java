package test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cn.edu.dgut.school_helper.SpringBootSecurityApplication;
import cn.edu.dgut.school_helper.controller.ReplyController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootSecurityApplication.class })
public class ReplyTreeQueryTest {

	private static final Logger log = LoggerFactory.getLogger(ReplyTreeQueryTest.class);

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void mytest() throws UnsupportedEncodingException, Exception {
		System.out.println("ss");
		String result = mvc.perform(MockMvcRequestBuilders.get("/api/reply/selectAll/1")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn().getResponse().getContentAsString();
	}

}
