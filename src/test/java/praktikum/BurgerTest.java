package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    // Моки
    private Bun bunMock;
    private Ingredient ingredientMock;
    private Burger burger;

    String bunName;
    float bunPrice;
    IngredientType ingredientType;
    String ingredientName;
    float ingredientPrice;
    float totalPrice;

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"black bun", 100, IngredientType.FILLING, "Начинка", 40, 250},
                {"white bun", 200, IngredientType.SAUCE, "Соус", 15, 425},
                {"red bun", 230, IngredientType.SAUCE, "Соус", 30, 480},
        };
    }

    public BurgerTest(String bunName, float bunPrice,
                      IngredientType ingredientType, String ingredientName, float ingredientPrice, float totalPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.totalPrice = totalPrice;

        // Создаем мок моков
        bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(bunPrice);

        ingredientMock = mock(Ingredient.class);
        when(ingredientMock.getType()).thenReturn(ingredientType);
        when(ingredientMock.getName()).thenReturn(ingredientName);
        when(ingredientMock.getPrice()).thenReturn(ingredientPrice);

        // Создаем объект Burger и передаем мок объекты, если есть возможность
        // или присваиваем мок через сеттеры, если класс позволяет
        burger = new Burger();

        // Или, если класс Burger принимает в конструктор или сеттеры, то делайте так
        // burger.setBuns(bunMock);
        // burger.addIngredient(ingredientMock);
    }

    @Test
    public void setBunsTest() {
        // Используйте мок
        burger.setBuns(bunMock);
        // Проверка, что receipt содержит имя булки
        assertTrue("Ошибка! Не добавлена булка", burger.getReceipt().contains(bunName));
    }

    @Test
    public void addIngredientTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        assertTrue("Ошибка! Не добавлен ингредиент", burger.getReceipt().contains(ingredientName));
    }

    @Test
    public void removeIngredientTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertFalse("Ошибка! Ингредиент не удалён", burger.getReceipt().contains(ingredientName));
    }

    @Test
    public void moveIngredientTest() {
        burger.setBuns(bunMock);
        Ingredient ingredient1 = ingredientMock;
        // добавляем два ингредиента
        burger.addIngredient(ingredientMock);
        Ingredient secondIngredient = mock(Ingredient.class);
        when(secondIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(secondIngredient.getName()).thenReturn("chili");
        when(secondIngredient.getPrice()).thenReturn(0f);
        burger.addIngredient(secondIngredient);

        // перемещаем первый ингредиент
        burger.moveIngredient(0,1);
        // проверка, что порядок изменился
        assertTrue("Ошибка! Не изменился порядок ингредиентов", burger.ingredients.get(1).equals(ingredient1));
    }
}