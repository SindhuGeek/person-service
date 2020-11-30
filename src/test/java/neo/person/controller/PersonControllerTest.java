package neo.person.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import neo.person.dto.request.PersonDTO;
import neo.person.dto.response.PersonResponseDTO;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonController personController;

	String examplePersonJson = "{\"firstName\":\"Sindhu\",\"lastName\":\"Gummalla\",\"Address\":[{\"street\":\"Saggart\",\"city\":\"Saggart\",\"state\":\"Dublin\",\"postalCode\":\"D24HD89\"}]}";

	@Test
	public void testAddPerson() throws Exception {

		Mockito.when(personController.createPerson(Mockito.any(PersonDTO.class)))
				.thenReturn(PersonResponseDTO.builder().message("").build());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/person").accept(MediaType.APPLICATION_JSON)
				.content(examplePersonJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void testUpdatePerson() throws Exception {
		String updatePersonJson = "{\"firstName\":\"Harish\",\"lastName\":\"Gummalla\"}";
		PersonResponseDTO response = PersonResponseDTO.builder().message("Updated person ID").build();	

		Mockito.when(personController.updatePerson(Mockito.any(PersonDTO.class), Mockito.any()))
				.thenReturn(response);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/person/1")

				.contentType(MediaType.APPLICATION_JSON_VALUE).content(updatePersonJson);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	
          assertEquals(200, result.getResponse().getStatus());
	}
	
	
	@Test
	public void testListAll() throws Exception {
		String personJson = "[{\"id\":1,\"firstName\":\"Harish\",\"lastName\":\"Gummalla\",\"addressList\":null}]";

		PersonDTO personDTO= new PersonDTO((long) 1, "Harish", "Gummalla",null);
		List<PersonDTO> personList= new ArrayList<>();
		personList.add(personDTO);

		Mockito.when(personController.listAll())
				.thenReturn(personList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/person");			

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	
          assertEquals(200, result.getResponse().getStatus());
          
          assertEquals(personJson, result.getResponse().getContentAsString());
	}
	
	@Test
	public void testDelete() throws Exception {
		
		Mockito.doNothing().when(personController).deleteById( Mockito.any());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/person/1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	
          assertEquals(204, result.getResponse().getStatus());
	}

}