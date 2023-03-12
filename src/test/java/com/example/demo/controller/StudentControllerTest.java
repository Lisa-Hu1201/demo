package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getById() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/students/{studentId}", 3)
				.header("headerName", "headerValue") // 添加自定義 header
				.queryParam("graduate", "true"); 	 // .param("graduate", "true") 也可以
		
		
		// 可以一直點下去的寫法稱為 Builder 設計模式(建造者模式)
		MvcResult mvcResult = mockMvc.perform(requestBuilder)
				.andDo(print()) // 輸出 API 執行結果，進而可以去看返回的 response body 的值，方便對照去寫下面的 andExpect
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.id", equalTo(3)))
				.andExpect(jsonPath("$.name", notNullValue()))
				.andReturn(); // 只能加在驗證結果的最後一行，作用是去取得完整的 API 執行結果出來
		
		String body = mvcResult.getResponse().getContentAsString();
		System.out.println(body);
	}
	
	@Test
	public void create() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/students")
				.contentType(MediaType.APPLICATION_JSON) // 要在這個 http request 加上一個 contentType 的 header，然後這個值是 application/json，使用POST一定要加這一行，才能在 request body 中回傳json格式參數
				.content("{\r\n" + 
						"  \"name\" : \"Coco\",\r\n" + 
						"  \"score\": 100.0,\r\n" + 
						"  \"graduate\":false\r\n" + 
						"}");
		mockMvc.perform(requestBuilder)
		.andExpect(status().is(201));
	}
}
