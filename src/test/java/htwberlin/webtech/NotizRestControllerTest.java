package htwberlin.webtech;

import HTWBERLIN.webtech.service.NotizbucheintragService;
import HTWBERLIN.webtech.web.api.Notizbucheintrag;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import HTWBERLIN.webtech.web.NotizbucheintragRestController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.doReturn;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(NotizbucheintragRestController.class)
class NotizRestControllerTest {


	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NotizbucheintragService notizenService;



	@Test
	@DisplayName("should return found notizen from notizen service")
	void should_return_found_person_from_notizen_service() throws Exception {


		// given
		var persons = List.of(
				new Notizbucheintrag(1L, "01.01.2020", "Test", "blue"),
				new Notizbucheintrag(2L, "01.01.2021", "Test", "green")
		);
		doReturn(persons).when(notizenService).findAll();

		// when
		mockMvc.perform(get("/api/v1/notizen"))
				// then
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(2))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].ldt").value("01.01.2020"))
				.andExpect(jsonPath("$[0].entry").value("Test"))
				.andExpect(jsonPath("$[0].colour").value("blue"))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].ldt").value("01.01.2021"))
				.andExpect(jsonPath("$[1].entry").value("Test"))
				.andExpect(jsonPath("$[1].colour").value("green"));
	}
		@Test
        @DisplayName("should return 404 if person is not found")
        void should_return_404_if_note_is_not_found() throws Exception {
            // given
            doReturn(null).when(notizenService).findById(anyLong());

            // when
            mockMvc.perform(get("/api/v1/notizen/123"))
                    // then
                    .andExpect(status().isNotFound());
        }

	@Test
	@DisplayName("should return 201 http status and Location header when creating a note")
	void should_return_201_http_status_and_location_header_when_creating_a_note() throws Exception {
		// given
		String notizenToCreateAsJson = "{\"ldt\": \"2022.12.01 17:00\", \"entry\":\"Test\", \"colour\":\"blue\"}";
		var notizen = new Notizbucheintrag(123L, null, null, null);
		doReturn(notizen).when(notizenService).create(any());

		// when
		mockMvc.perform(
						post("/api/v1/notizen")
								.contentType(MediaType.APPLICATION_JSON)
								.content(notizenToCreateAsJson)
				)
				// then
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"))
				.andExpect(header().string("Location", Matchers.equalTo(("/api/v1/notizen/" + notizen.getId()))));
	}



}
