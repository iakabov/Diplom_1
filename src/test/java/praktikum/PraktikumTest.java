package praktikum;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class PraktikumTest {

    @Test
    public void testMainMethodCoverage() {
        // Мокаем базу данных
        Database mockDatabase = mock(Database.class);

        // Определяем список булочек
        List<Bun> buns = List.of(
                new Bun("black bun", 100),
                new Bun("white bun", 200),
                new Bun("red bun", 300)
        );

        // Определяем список ингредиентов
        List<Ingredient> ingredients = List.of(
                new Ingredient(IngredientType.SAUCE, "hot sauce", 100),
                new Ingredient(IngredientType.SAUCE, "sour cream", 200),
                new Ingredient(IngredientType.SAUCE, "chili sauce", 300),
                new Ingredient(IngredientType.FILLING, "cutlet", 100),
                new Ingredient(IngredientType.FILLING, "dinosaur", 200),
                new Ingredient(IngredientType.FILLING, "sausage", 300)
        );

        // Настраиваем мок-объект
        when(mockDatabase.availableBuns()).thenReturn(buns);
        when(mockDatabase.availableIngredients()).thenReturn(ingredients);

        // Вызываем main, передавая мок-базу
        // Для этого нужно немного изменить ваш класс Praktikum:
        // Добавить перегруженную версию main
        Praktikum.main(new String[0], mockDatabase);
        // Или, если изменить нельзя, то оставить так и вызвать
        // Praktikum.main(new String[0]);
        // Но тогда мок-объект не будет использован внутри main
        // Вариант с вызовом метода run:
        // Praktikum.run(mockDatabase);

    }
    @Test
    public void testMainWithMockDatabase() {
        Database mockDatabase = mock(Database.class);

        List<Bun> buns = List.of(
                new Bun("black bun", 100),
                new Bun("white bun", 200),
                new Bun("red bun", 300)
        );

        List<Ingredient> ingredients = List.of(
                new Ingredient(IngredientType.SAUCE, "hot sauce", 100),
                new Ingredient(IngredientType.SAUCE, "sour cream", 200),
                new Ingredient(IngredientType.SAUCE, "chili sauce", 300),
                new Ingredient(IngredientType.FILLING, "cutlet", 100),
                new Ingredient(IngredientType.FILLING, "dinosaur", 200),
                new Ingredient(IngredientType.FILLING, "sausage", 300)
        );

        when(mockDatabase.availableBuns()).thenReturn(buns);
        when(mockDatabase.availableIngredients()).thenReturn(ingredients);

        // Вызов тестируемого метода
        Praktikum.main(new String[0], mockDatabase);
    }
}