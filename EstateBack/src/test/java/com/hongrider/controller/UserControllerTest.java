package com.hongrider.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongrider.entity.User;
import com.hongrider.repository.UserRepository;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeAll
    public static void init(){
        System.out.println("数据初始化");
    }

    @AfterAll
    public static void cleanup(){
        System.out.println("数据清理");
    }

    @BeforeEach
    void tearUp(){
        this.user = new User();
        user.setName("giant");
        user.setEstate("马踏飞燕");
        user.setPassword("111111");
        user.setEmail("giant@sohu.com");
        user.setRole("租户");
        user.setAlias("巨人");
        user.setId(999);
        System.out.println("I am tear up...");
    }

    @AfterEach
    void tearDown(){
        System.out.println("I am tear down...");
    }

    @Test
    void findAll() throws Exception {

        List<User> users = new ArrayList<>();
        users.add(user);
        Page<User> page = new PageImpl<>(users);
        when(userRepository.findAll(isA(Pageable.class))).thenReturn( page);
        String result_1 = mockMvc.perform( get("/user/all/1/5"))
                .andDo( print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse()
                .getContentAsString( Charset.defaultCharset() );

        System.out.println( "<<<<<<<<<<<<<<<<<<<<<1\n" + result_1 + "\n1>>>>>>>>>>>>>>>>>>>>>>>");

        String result_2 = mockMvc.perform(get("/user/all/{page}/{size}", 1,5))
                .andDo( print())
                .andExpect( status().isOk())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect( jsonPath("$.content[0].id").value(999))
                .andExpect(jsonPath("$.content[0].name").value("giant"))
                .andExpect(jsonPath("$.content[0].email").value("giant@sohu.com"))
                .andExpect( jsonPath("$.content[0].alias").value("巨人"))
                .andReturn()
                .getResponse()
                .getContentAsString(Charset.defaultCharset());

        System.out.println( "<<<<<<<<<<<<<<<<<<<<<2\n" + result_2 + "\n2>>>>>>>>>>>>>>>>>>>>>>>" );

    }

    @Test
    void save() throws  Exception{

        String jsonRequest =  objectMapper.writeValueAsString( user );

        when(userRepository.save( isA(User.class) )).thenReturn( user );
        String result = mockMvc.perform( post("/user/save")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString(Charset.defaultCharset());
        System.out.println( "<<<<<<<<<<<<<<<<<<<<<\n" + result + "\n>>>>>>>>>>>>>>>>>>>>>>>" );

        mockMvc.perform( post("/user/save")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("succeed"));
    }

    @Test
    void save_null () throws Exception{
        User user = new User();

        mockMvc.perform( post("/user/save")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( objectMapper.writeValueAsString( user )))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("failed"));

    }

    @Test
    void findById() throws Exception {
        Optional<User> op_user = Optional.of(user);
        when(userRepository.findById(isA( Integer.class))).thenReturn(op_user);
        String result = mockMvc.perform( get("/user/select/{id}", this.user.getId()))
                .andDo( print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString( Charset.defaultCharset() );

        System.out.println( "<<<<<<<<<<<<<<<<<<<<<1\n" + result + "\n1>>>>>>>>>>>>>>>>>>>>>>>");


        String result_2 = mockMvc.perform(get("/user/select/{id}", this.user.getId()))
                .andDo( print())
                .andExpect( status().isOk())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect( jsonPath("$.id").value(999))
                .andExpect(jsonPath("$.name").value("giant"))
                .andExpect(jsonPath("$.email").value("giant@sohu.com"))
                .andExpect( jsonPath("$.alias").value("巨人"))
                .andReturn()
                .getResponse()
                .getContentAsString(Charset.defaultCharset());
    }

    @Test
    void update() throws Exception {
        String jsonRequest =  objectMapper.writeValueAsString( user );

        when(userRepository.save( isA(User.class) )).thenReturn( user );
        String result = mockMvc.perform( put("/user/update")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString(Charset.defaultCharset());
        System.out.println( "<<<<<<<<<<<<<<<<<<<<<\n" + result + "\n>>>>>>>>>>>>>>>>>>>>>>>" );

        mockMvc.perform( put("/user/update")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("succeed"));
    }

    @Test
    void update_null () throws Exception{
        User user = new User();

        mockMvc.perform( put("/user/update")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( objectMapper.writeValueAsString( user )))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("failed"));

    }

}