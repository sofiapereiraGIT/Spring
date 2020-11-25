package com.accenture.trainingcfrest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accenture.trainingcfrest.dto.SalesOrderItemTO;
import com.accenture.trainingcfrest.dto.SalesOrderTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ActiveProfiles(profiles = { "test" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesOrderControllerTest {

	@Autowired
	private SalesOrderController controller;

	// Mock testing variables
	private static MockMvc mockMvc;
	private static ObjectMapper mapper;
	private static SalesOrderTO salesOrder;

	private static void getSalesOrderTest() {
		SalesOrderTO salesOrderTO = new SalesOrderTO();
		salesOrderTO.setStatus("C");
		
		SalesOrderItemTO salesOrderItemTO = new SalesOrderItemTO();
		salesOrderItemTO.setQuantity(10);
		salesOrderItemTO.setStatus("C");
		
		List<SalesOrderItemTO> items = new ArrayList<SalesOrderItemTO>();
		items.add(salesOrderItemTO);
		salesOrderTO.setItems(items);
		salesOrder = salesOrderTO;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		getSalesOrderTest();
	}

	@Before
	public void setUpBefore() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void aa_saveSalesOrder() throws UnsupportedEncodingException, Exception {
		final byte[] productAsByteArray = mapper.writeValueAsBytes(salesOrder);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/SalesOrder")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(productAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final SalesOrderTO objResult = mapper.readValue(result, SalesOrderTO.class);

		assertThat(objResult.getId()).isNotEmpty();
		salesOrder.setId(objResult.getId());
	}

	@Test
	public void ab_changeSalesOrder() throws UnsupportedEncodingException, Exception {
		String newStatus = "D";
		salesOrder.setStatus(newStatus);
		
		final byte[] productAsByteArray = mapper.writeValueAsBytes(salesOrder);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/SalesOrder/"+salesOrder.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(productAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final SalesOrderTO objResult = mapper.readValue(result, SalesOrderTO.class);

		assertThat(objResult.getId()).isEqualTo(salesOrder.getId());
		assertThat(objResult.getStatus()).isEqualTo(newStatus);
	}

	@Test
	public void ac_getAllSalesOrder() throws UnsupportedEncodingException, Exception {
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/SalesOrder")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final List<SalesOrderTO> objResult = Arrays.asList(mapper.readValue(result, SalesOrderTO[].class));
		assertThat(objResult.size()).isGreaterThan(0);
	}
	
	@Test
	public void ad_getOneSalesOrder() throws UnsupportedEncodingException, Exception {		
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/SalesOrder/"+salesOrder.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final SalesOrderTO objResult = mapper.readValue(result, SalesOrderTO.class);
		assertThat(objResult.getId()).isEqualTo(salesOrder.getId());
	}
	
	@Test
	public void az_deleteSalesOrder() throws UnsupportedEncodingException, Exception {
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/SalesOrder/"+salesOrder.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value())).andReturn().getResponse()
				.getContentAsString();
	}

}