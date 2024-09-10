package com.deusley.gerenciadorDeTarefasAPI.controller;


import com.deusley.gerenciadorDeTarefasAPI.controller.request.ItemRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.TarefaResponse;
import com.deusley.gerenciadorDeTarefasAPI.mapper.TarefaMapper;
import com.deusley.gerenciadorDeTarefasAPI.repositories.TarefaRepository;
import com.deusley.gerenciadorDeTarefasAPI.service.impl.TarefaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@WebMvcTest(TarefaController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class TarefaControllerTest {

    private static final String URI_PATH = "/api/v1/tarefas";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TarefaServiceImpl tarefaService;
    @MockBean
    private TarefaMapper tarefaMapper;
    @MockBean
    private TarefaRepository tarefaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    public void deveCriarTarefaComSucesso(){

        ListaRequest listaRequest = new ListaRequest();
        listaRequest.setNome("Minha Lista");


        TarefaResponse tarefaResponse = new TarefaResponse();
        tarefaResponse.setId(1L);
        tarefaResponse.setNome("Minha Lista");

        willReturn(tarefaResponse).given(tarefaService).criar(Mockito.any(ListaRequest.class));

        String json = objectMapper.writeValueAsString(listaRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .post(URI_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
              .content(json);


        mockMvc.perform(request)
              .andExpect(status().isCreated())
              .andExpect(jsonPath("$.id").value(1L))
              .andExpect(jsonPath("$.nome").value("Minha Lista"));
    }

    @SneakyThrows
    @Test
    public void deveRetornarUnsupportedMediaTypeQuandoTipoDeConteudoInvalido() {
        String invalidContent = "Texto não é um JSON válido";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .post(URI_PATH)
              .contentType(MediaType.TEXT_PLAIN)
              .accept(MediaType.APPLICATION_JSON)
              .content(invalidContent);

        mockMvc.perform(request)
              .andExpect(status().isUnsupportedMediaType());
    }


    @SneakyThrows
    @Test
    public void deveAdicionarItemComSucesso(){
        Long tarefaId = 1L;
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setTitulo("Novo Item");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .post(URI_PATH + "/" + tarefaId + "/itens")
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(itemRequest));

        mockMvc.perform(request)
              .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void deveAtualizarItemComSucesso(){
        Long tarefaId = 1L;
        Long itemId = 1L;
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setTitulo("Item Atualizado");

        TarefaResponse tarefaResponse = new TarefaResponse();
        tarefaResponse.setId(tarefaId);
        tarefaResponse.setNome("Tarefa Atualizada");

        willReturn(tarefaResponse).given(tarefaService).atualizarItem(Mockito.eq(tarefaId), Mockito.eq(itemId), Mockito.any(ItemRequest.class));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .put(URI_PATH + "/" + tarefaId + "/itens/" + itemId)
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(itemRequest));

        mockMvc.perform(request)
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").value(tarefaId))
              .andExpect(jsonPath("$.nome").value("Tarefa Atualizada"));
    }

    @SneakyThrows
    @Test
    public void deveRemoverItemComSucesso(){
        Long tarefaId = 1L;
        Long itemId = 1L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .delete(URI_PATH + "/" + tarefaId + "/itens/" + itemId)
              .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
              .andExpect(status().isNoContent());
    }

    @SneakyThrows
    @Test
    public void deveObterTodasTarefasComSucesso(){
        TarefaResponse tarefaResponse = new TarefaResponse();
        tarefaResponse.setId(1L);
        tarefaResponse.setNome("Tarefa 1");

        willReturn(List.of(tarefaResponse)).given(tarefaService).obterTodas();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .get(URI_PATH)
              .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
              .andExpect(status().isOk())
              .andExpect(jsonPath("$[0].id").value(1L))
              .andExpect(jsonPath("$[0].nome").value("Tarefa 1"));
    }

    @SneakyThrows
    @Test
    public void deveObterTarefaPorIdComSucesso(){
        Long tarefaId = 1L;

        TarefaResponse tarefaResponse = new TarefaResponse();
        tarefaResponse.setId(tarefaId);
        tarefaResponse.setNome("Tarefa 1");

        willReturn(tarefaResponse).given(tarefaService).obterPorId(tarefaId);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .get(URI_PATH + "/" + tarefaId)
              .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").value(tarefaId))
              .andExpect(jsonPath("$.nome").value("Tarefa 1"));
    }

    @SneakyThrows
    @Test
    public void deveDeletarTarefaComSucesso(){
        Long tarefaId = 1L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .delete(URI_PATH + "/" + tarefaId)
              .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
              .andExpect(status().isNoContent());
    }

    @SneakyThrows
    @Test
    public void deveAlterarEstadoComSucesso() {
        Long tarefaId = 1L;
        Long itemId = 1L;

        TarefaResponse tarefaResponse = new TarefaResponse();
        tarefaResponse.setId(tarefaId);
        tarefaResponse.setNome("Tarefa 1");

        willReturn(tarefaResponse).given(tarefaService)
              .alterarEstado(Mockito.eq(tarefaId), Mockito.eq(itemId));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .patch(URI_PATH + "/" + tarefaId + "/itens/" + itemId + "/estado")
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
              .content("\"CONCLUIDO\"");

        mockMvc.perform(request)
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").value(tarefaId))
              .andExpect(jsonPath("$.nome").value("Tarefa 1"));
    }


}
