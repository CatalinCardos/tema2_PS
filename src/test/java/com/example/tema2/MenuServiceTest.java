package com.example.tema2;

import com.example.tema2.Model.Dish;
import com.example.tema2.Repo.MenuREPO;
import com.example.tema2.Service.MenuService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class MenuServiceTest {

    @TestConfiguration
    static class MenuServiceTestContextConfiguration {

        @Bean
        public MenuService menuService() {
            return new MenuService();
        }
    }

    @Autowired
    private MenuService menuService;

    @MockBean
    private MenuREPO menuRepository;

    @Before
    public void setUp() {
        Dish dish = new Dish("Pizza", 20, 10);

        Mockito.when(menuRepository.findByName(dish.getName()))
          .thenReturn(dish);
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "Pizza";
        Dish found = menuService.findDishByName(name);

        assertThat(found.getName())
          .isEqualTo(name);
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound2() {
        String name = "Pizza";
        Dish found = menuService.findDishByName(name);

        assertThat(found.getPrice())
          .isEqualTo(20);
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound3() {
        String name = "Pizza";
        Dish found = menuService.findDishByName(name);

        assertThat(found.getStock())
          .isEqualTo(10);
    }

}
