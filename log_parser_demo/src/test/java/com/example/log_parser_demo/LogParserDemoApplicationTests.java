package com.example.log_parser_demo;

import com.example.log_parser_demo.repository.LogRepository;
import com.example.log_parser_demo.service.LogService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(MockitoExtension.class)

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
class LogParserDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private LogRepository logRepository;

	@InjectMocks
	private LogService logService;

	@Test
	public void getLoggersPageTest() throws Exception {
		this.mockMvc
				.perform(get("/loggers"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Logs")));
	}

	@Test
	public void saveLogsFromFile() throws Exception {
		MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
				"text/plain", "2019-08-30 20:43:37.407 [main] INFO ru.javastudy.examples.User - This is some message".getBytes());

		this.mockMvc
				.perform(MockMvcRequestBuilders.multipart("/upload-file")
						.file(multipartFile)
						.param("name", "4asd")
				)
				.andExpect(status().is(302));

	}

}
