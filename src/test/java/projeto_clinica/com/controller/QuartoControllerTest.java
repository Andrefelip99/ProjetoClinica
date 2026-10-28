package projeto_clinica.com.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import projeto_clinica.com.dto.Response.QuartoResponseDTO;
import projeto_clinica.com.model.enums.TipoQuarto;
import projeto_clinica.com.service.QuartoService;
import projeto_clinica.com.service.exceptions.ConflictException;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;
import projeto_clinica.com.controller.hendlers.ControllerExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class QuartoControllerTest {
    private QuartoService service;
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        service = mock(QuartoService.class);
        mvc = MockMvcBuilders.standaloneSetup(new QuartoController(service))
                .setControllerAdvice(new ControllerExceptionHandler()).build();
    }

    @Test
    void getReturnsQuarto() throws Exception {
        when(service.findById(3L)).thenReturn(new QuartoResponseDTO(3L, "12", 1, TipoQuarto.ENFERMARIA));
        mvc.perform(get("/quartos/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.numero").value("12"));
    }

    @Test
    void missingQuartoMapsTo404() throws Exception {
        when(service.findById(99L)).thenThrow(new ResourceNotFoundException("Quarto não encontrado"));
        mvc.perform(get("/quartos/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Quarto não encontrado"));
    }

    @Test
    void conflictMapsTo409() throws Exception {
        when(service.findById(4L)).thenThrow(new ConflictException("Quarto duplicado"));
        mvc.perform(get("/quartos/4"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409));
    }

    @Test
    void createReturns201() throws Exception {
        when(service.create(any())).thenReturn(new QuartoResponseDTO(7L, "7", 0, TipoQuarto.ENFERMARIA));
        mvc.perform(post("/quartos").contentType(MediaType.APPLICATION_JSON)
                .content("{\"numero\":\"7\",\"andar\":0,\"tipo\":\"ENFERMARIA\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(7));
    }

    @Test
    void invalidPayloadReturns422() throws Exception {
        mvc.perform(post("/quartos").contentType(MediaType.APPLICATION_JSON)
                .content("{\"numero\":\" \",\"andar\":0,\"tipo\":\"ENFERMARIA\"}"))
                .andExpect(status().isUnprocessableContent())
                .andExpect(jsonPath("$.errors").isArray());
    }
}
