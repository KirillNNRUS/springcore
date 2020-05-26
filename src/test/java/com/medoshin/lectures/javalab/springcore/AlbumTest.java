package com.medoshin.lectures.javalab.springcore;

import com.medoshin.lectures.javalab.springcore.dao.AlbumDAOImpl;
import com.medoshin.lectures.javalab.springcore.dao.IAlbumDAO;

import com.medoshin.lectures.javalab.springcore.entity.Album;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        AlbumTest.Config.class,
        AlbumTest.class
})
@WebAppConfiguration
@EnableWebMvc
@TestPropertySource("classpath:test.properties")
public class AlbumTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IAlbumDAO IAlbumDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initIt() throws SQLException {
    }

    @BeforeClass
    public static void beforeClass() {

    }

//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        ScriptUtils.executeSqlScript(
//                dataSource.getConnection(),
//                new EncodedResource(new ClassPathResource("test-add-user.sql"), StandardCharsets.UTF_8)
//        );
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        ScriptUtils.executeSqlScript(
//                dataSource.getConnection(),
//                new EncodedResource(new ClassPathResource("test-clean-db.sql"), StandardCharsets.UTF_8)
//        );
//    }

    /*
    Стыдно признаваться, но у меня просто не получается...
    А где ошибка, найти не могу...
     */
    @Test
    public void if_correctRequestForGetUserById_when_createRightResponse() throws Exception {
        Album album = new Album();
        int id = 1;
        album.setName("TestAlbum");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/rest/albums/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

//    @Test
//    public void if_correctRequestForAddUser_when_processRequestRightly() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(new User("John", 100));
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

//    @Test
//    public void test_TestController() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/for-mvc")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

    @Configuration
    static class Config {
        @Autowired
        private Environment env;

        @Bean("albumDAO")
        public IAlbumDAO albumDAO(EntityManagerFactory entityManagerFactory) {
            return new AlbumDAOImpl(entityManagerFactory);
        }

        @Bean("entityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
            entityManagerFactory.setPackagesToScan("com.medoshin.lectures.javalab.springcore.entity");

            entityManagerFactory.setJpaProperties(hibernateJpaProperties());
            return entityManagerFactory;
        }

        @Bean
        public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory emf) {
            JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
            jpaTransactionManager.setEntityManagerFactory(emf);
            return jpaTransactionManager;
        }

        @Bean
        public DataSource dataSource() {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName(env.getProperty("spring.datasource.driverClassName"));
            dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
            dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
            dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
            return dataSourceBuilder.build();
        }

        @Bean
        public JdbcTemplate jdbcTemplate(DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }

        private Properties hibernateJpaProperties() {
            Properties props = new Properties();
            props.put(org.hibernate.cfg.Environment.DRIVER, env.getProperty("spring.datasource.driverClassName"));
            props.put(org.hibernate.cfg.Environment.URL, env.getProperty("spring.datasource.url"));
            props.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
            props.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
            props.put(org.hibernate.cfg.Environment.USER, env.getProperty("spring.datasource.username"));
            props.put(org.hibernate.cfg.Environment.PASS, env.getProperty("spring.datasource.password"));
            return props;
        }
    }
}

