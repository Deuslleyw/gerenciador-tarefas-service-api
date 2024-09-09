package com.deusley.gerenciadorDeTarefasAPI.controller;



import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.ListaResponse;
import com.deusley.gerenciadorDeTarefasAPI.service.ListaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
@WebMvcTest(ListaController.class)
public class ListaControllerTest {

    private static final String URI_PATH = "/listas";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListaService listaService;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    public void deveCriarListaComSucesso() {
        // Construindo a requisição (simulação)
        ListaRequest listaRequest = new ListaRequest();
        listaRequest.setNome("Minha Lista");

        // Mock do service
        ListaResponse listaResponse = new ListaResponse();
        listaResponse.setId(1L);
        listaResponse.setNome("Minha Lista");

        willReturn(listaResponse).given(listaService).criarLista(Mockito.any(ListaRequest.class));

        String json = objectMapper.writeValueAsString(listaRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(URI_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Execução da requisição e validação do resultado
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Minha Lista"));
    }
}
