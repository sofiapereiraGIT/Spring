package com.accenture.trainingcfrest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accenture.trainingcfrest.dto.ProductsTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ActiveProfiles(profiles = { "test" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductsControllerTest {

	@Autowired
	private ProductsController controller;

	// Mock testing variables
	private static MockMvc mockMvc;
	private static ObjectMapper mapper;
	private static ProductsTO product;

	private static void getProductTest() {
		ProductsTO productsTO = new ProductsTO();
		productsTO.setName("Product Test");
		productsTO.setManufacturer("MAnufacturerTest");
		productsTO.setSalesPrice(12.0);
		productsTO.setBasePrice(12.0);
		productsTO.setQuantity(5);
		Date newDate = new Date();
		productsTO.setCreatedAT(newDate);
		productsTO.setModifiedAT(newDate);
		productsTO.setCreatedBY("Sofia Test");
		productsTO.setModifiedBY("Sofia Test");
		
		product = productsTO;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		getProductTest();
	}

	@Before
	public void setUpBefore() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void aa_saveProduct() throws UnsupportedEncodingException, Exception {
		final byte[] productAsByteArray = mapper.writeValueAsBytes(product);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/Products")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(productAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final ProductsTO objResult = mapper.readValue(result, ProductsTO.class);

		assertThat(objResult.getId()).isNotEmpty();
		product.setId(objResult.getId());
	}

	@Test
	public void ab_changeProduct() throws UnsupportedEncodingException, Exception {
		String newName = "Produto Teste Alterado";
		product.setName(newName);
		String newManu = "MAnufacturer Test Alterado";
		product.setManufacturer(newManu);
		Double newPrice = 15.0;
		product.setSalesPrice(newPrice);
		product.setBasePrice(newPrice);
		int newQuantity = 8;
		product.setQuantity(newQuantity);
		Date newDate = new Date();
		product.setCreatedAT(newDate);
		product.setModifiedAT(newDate);
		String newCreator = "Sofia Teste Alterado";
		product.setCreatedBY(newCreator);
		product.setModifiedBY(newCreator);		
		
		final byte[] productAsByteArray = mapper.writeValueAsBytes(product);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/Products/"+product.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(productAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final ProductsTO objResult = mapper.readValue(result, ProductsTO.class);

		assertThat(objResult.getId()).isEqualTo(product.getId());
		assertThat(objResult.getName()).isEqualTo(newName);
		assertThat(objResult.getManufacturer()).isEqualTo(newManu);
		assertThat(objResult.getBasePrice()).isEqualTo(newPrice);
		assertThat(objResult.getSalesPrice()).isEqualTo(newPrice);
		assertThat(objResult.getQuantity()).isEqualTo(newQuantity);
		assertThat(objResult.getCreatedAT()).isEqualTo(newDate);
		assertThat(objResult.getModifiedAT()).isEqualTo(newDate);
		assertThat(objResult.getCreatedBY()).isEqualTo(newCreator);
		assertThat(objResult.getModifiedBY()).isEqualTo(newCreator);
	}

	@Test
	public void ac_getAllProducts() throws UnsupportedEncodingException, Exception {
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/Products")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final List<ProductsTO> objResult = Arrays.asList(mapper.readValue(result, ProductsTO[].class));
		assertThat(objResult.size()).isGreaterThan(0);
	}
	
	@Test
	public void ad_getOneProduct() throws UnsupportedEncodingException, Exception {		
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/Products/"+product.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final ProductsTO objResult = mapper.readValue(result, ProductsTO.class);
		assertThat(objResult.getId()).isEqualTo(product.getId());
	}
	
	@Test
	public void az_deleteProduct() throws UnsupportedEncodingException, Exception {
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/Products/"+product.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value())).andReturn().getResponse()
				.getContentAsString();
	}

}