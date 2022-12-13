package com.aluraflix.Controller;

import com.aluraflix.Controller.entities.Videos;
import com.aluraflix.Controller.repository.VideosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VideosControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Videos videos;

    private Videos videos2;


    private final UUID repeatId = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

    private final UUID notFoundId = UUID.fromString("0fa00f00-0000-0000-b0fc-0c000f00afa0");
    @Autowired
    private VideosRepository repository;

    private final URI uri = URI.create("/videos");
    private final URI uriById = URI.create("/videos/3fa85f64-5717-4562-b3fc-2c963f66afa6");

    private final URI uriNotFound = URI.create("/videos/0fa00f00-0000-0000-b0fc-0c000f00afa0");


    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public void putId() {
        videos2 = new Videos(repeatId, "a", "a", "a", true);
        repository.save(videos2);
    }

    @AfterAll
    public void deleteId() {
        repository.deleteById(repeatId);
    }

    @BeforeEach
    public void setup() {

        videos = new Videos("a", "a", "a");

    }

    @Test
    public void shouldReturn201WhenPosting() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(objectMapper.writeValueAsString(videos))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }
    @Test
    public void shouldReturn201WhenPutting() throws Exception {

        videos.setId(repeatId);
        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .content(objectMapper.writeValueAsString(videos))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void shouldReturn200WhenGettingById() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get(uriById))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void shouldReturn404WhenGettingUnExistingId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uriNotFound))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldReturn200WhenGettingAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void shouldReturn200WhendDeletingById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(uriById))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }


}
