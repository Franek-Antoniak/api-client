package github.apiuser.controller;

import github.apiuser.service.RepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class RepositoryControllerIntegrationTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	RepositoryService service;

	@Test
	void testGetRepositoryDetails() throws Exception {
		// given
		String owner = "Franek-Antoniak";
		String name = "Franek-Antoniak";
		// when
		// then
		mockMvc.perform(get("/repositories/{owner}/{name}", owner, name))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.fullName").value(owner + "/" + name))
		       .andExpect(jsonPath("$.description").value(anyOf(any(String.class), nullValue())))
		       .andExpect(jsonPath("$.cloneUrl").value("https://github.com/Franek-Antoniak/Franek-Antoniak.git"))
		       .andExpect(jsonPath("$.stars").isNumber())
		       .andExpect(jsonPath("$.stars").value(greaterThanOrEqualTo(0)))
		       .andExpect(jsonPath("$.createdAt").isNotEmpty())
		       .andExpect(jsonPath("$.createdAt").value(matchesRegex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z")));
	}

	@Test
	void testGetRepositoryDetailsWithInvalidOwnerAndName() throws Exception {
		// given
		String owner = "Franek-Antoniak";
		String name = "Non-existing-repository";
		// when
		// then
		mockMvc.perform(get("/repositories/{owner}/{name}", owner, name))
		       .andExpect(status().isNotFound());
	}

	@Test
	void testGetRepositoryDetailsWithNonExistingParameters() throws Exception {
		// given
		String owner = "";
		String name = "";
		// when
		// then
		mockMvc.perform(get("/repositories/{owner}/{name}", owner, name))
		       .andExpect(status().isNotFound());
	}
}