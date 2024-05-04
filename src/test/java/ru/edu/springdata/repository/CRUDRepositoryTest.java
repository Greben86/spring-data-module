package ru.edu.springdata.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.edu.springdata.model.Book;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
public class CRUDRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetById() throws Exception {
        byte[] bookByte = mockMvc.perform(MockMvcRequestBuilders.post("/api/book/add")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"Капитал\",\"language\":\"Русский\",\"category\":\"Философия\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        Long id0 = getIdFromAnswer(bookByte);
        bookByte = mockMvc.perform(MockMvcRequestBuilders.post("/api/book/add")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"Молот ведьм\",\"language\":\"Английсткий\",\"category\":\"Религия\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        Long id1 = getIdFromAnswer(bookByte);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/"+id0+"/get"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(String.valueOf(id0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Капитал"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/"+id1+"/get"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(String.valueOf(id1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Молот ведьм"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/"+id0+"/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/"+id1+"/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAll() throws Exception {
        byte[] bookByte = mockMvc.perform(MockMvcRequestBuilders.post("/api/book/add")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"Капитал\",\"language\":\"Русский\",\"category\":\"Философия\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        Long id0 = getIdFromAnswer(bookByte);
        bookByte = mockMvc.perform(MockMvcRequestBuilders.post("/api/book/add")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"Молот ведьм\",\"language\":\"Английсткий\",\"category\":\"Религия\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        Long id1 = getIdFromAnswer(bookByte);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/get/all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(String.valueOf(id0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Капитал"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(String.valueOf(id1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Молот ведьм"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/"+id0+"/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/"+id1+"/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllByCategory() throws Exception {
        byte[] bookByte = mockMvc.perform(MockMvcRequestBuilders.post("/api/book/add")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"Физика\",\"language\":\"Русский\",\"category\":\"Учебники\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        Long id0 = getIdFromAnswer(bookByte);
        bookByte = mockMvc.perform(MockMvcRequestBuilders.post("/api/book/add")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"Математика\",\"language\":\"Русский\",\"category\":\"Учебники\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        Long id1 = getIdFromAnswer(bookByte);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/get/category").param("category", "Учебники"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(String.valueOf(id0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Физика"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].language").value("Русский"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(String.valueOf(id1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Математика"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].language").value("Русский"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/get/category").param("category", "Самоучители"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/"+id0+"/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/"+id1+"/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/get/category").param("category", "Учебники"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testAddBook() throws Exception {
        byte[] bookByte = mockMvc.perform(MockMvcRequestBuilders.post("/api/book/add")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"Список рецептов\",\"language\":\"Русский\",\"category\":\"Кулинария\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        Long id = getIdFromAnswer(bookByte);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/"+id+"/get"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(String.valueOf(id)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Список рецептов"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.language").value("Русский"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Кулинария"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/1/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/"+id+"/get"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    private Long getIdFromAnswer(byte[] answer) throws IOException {
        String str = new String(answer, StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = objectMapper.createParser(str).readValueAs(Book.class);
        return book.getId();
    }

}