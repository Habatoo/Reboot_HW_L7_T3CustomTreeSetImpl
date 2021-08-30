package Laptenkov;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования public методов класса {@link CustomTreeSetImpl<Object>}.
 *
 * @author habatoo
 */
class CustomTreeSetImplTest {

    private CustomTreeSetImpl<Integer> customEmptyTreeSet;
    private CustomTreeSetImpl<Integer> customNotEmptyTreeSet;

    /**
     * Инициализация экземпляров тестируемого класса {@link CustomTreeSetImpl}.
     */
    @BeforeEach
    void setUp() {
        customEmptyTreeSet = new CustomTreeSetImpl<>(Integer::compareTo);

        customNotEmptyTreeSet = new CustomTreeSetImpl<>(Integer::compareTo);
        customNotEmptyTreeSet.add(1);
        customNotEmptyTreeSet.add(20);
        customNotEmptyTreeSet.add(16);
        customNotEmptyTreeSet.add(5);
        customNotEmptyTreeSet.add(32);
        customNotEmptyTreeSet.add(50);
    }

    /**
     * Очистка экземпляров тестируемого класса {@link CustomTreeSetImpl}.
     */
    @AfterEach
    void tearDown() {
        customEmptyTreeSet = null;
        customNotEmptyTreeSet = null;
    }

    /**
     * Проверяет создание пустого экземпляра {@link CustomTreeSetImpl}.
     * Сценарий, при котором конструктор отрабатывает пустую коллекцию,
     * проверяет размер коллекции
     * равный 0 и отображение коллекции.
     */
    @Test
    public void customTreeSetImpl_Test() {
        customEmptyTreeSet = new CustomTreeSetImpl<>(Integer::compareTo);
        Assertions.assertEquals(0, customEmptyTreeSet.size());
        Assertions.assertEquals(
                "[ ]", customEmptyTreeSet.toString());
    }

    /**
     * Метод {@link CustomTreeSetImplTest#size_Test()}
     * проверяет метод {@link CustomTreeSetImpl#size()}.
     * Проверяет размер не пустого экземпляра {@link CustomTreeSetImpl}.
     * Сценарий, при котором пустой экземпляр возвращает величину
     * объекта равную 0, не пустой экземпляр возвращает 6.
     */
    @Test
    void size_Test() {
        Assertions.assertEquals(0, customEmptyTreeSet.size());
        Assertions.assertEquals(6, customNotEmptyTreeSet.size());
    }

    /**
     * Метод  {@link CustomTreeSetImplTest#isEmpty_Test()}
     * проверяет метод {@link CustomTreeSetImpl#isEmpty()}.
     * Проверяет на пустоту экземпляр объекта {@link CustomTreeSetImpl}.
     * Сценарий, при котором пустой экземпляр возвращает <code>true</code>,
     * не пустой экземпляр возвращает <code>false</code>.
     */
    @Test
    void isEmpty_Test() {
        Assertions.assertEquals(true, customEmptyTreeSet.isEmpty());
        Assertions.assertEquals(false, customNotEmptyTreeSet.isEmpty());
    }

    /**
     * Метод {@link CustomTreeSetImplTest#addSuccess_Test()}
     * Проверяет проверяет метод {@link CustomTreeSetImpl#add(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т и возвращает <code>true</code>.
     */
    @Test
    void addSuccess_Test() {
        Assertions.assertEquals(0, customEmptyTreeSet.size());
        Assertions.assertEquals(true, customEmptyTreeSet.add(1));
        Assertions.assertEquals(1, customEmptyTreeSet.size());
        Assertions.assertEquals(true, customEmptyTreeSet.add(16));
        Assertions.assertEquals(2, customEmptyTreeSet.size());
        Assertions.assertEquals(6, customNotEmptyTreeSet.size());
        Assertions.assertEquals(true, customNotEmptyTreeSet.add(10));
        Assertions.assertEquals(7, customNotEmptyTreeSet.size());
    }

    /**
     * Метод {@link CustomTreeSetImplTest#addFail_Test()}
     * Проверяет проверяет метод {@link CustomTreeSetImpl#add(Object)}.
     * Сценарий, при котором уже существующий объект не успешно
     * добавляется и возвращает <code>true</code>.
     */
    @Test
    void addFail_Test() {
        Assertions.assertEquals(true, customEmptyTreeSet.add(100));
        Assertions.assertEquals(false, customEmptyTreeSet.add(100));
    }

    /**
     * Метод {@link CustomTreeSetImplTest#removeFail_Test()}
     * Проверяет проверяет метод {@link CustomTreeSetImpl#remove(Object)}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого существующего в дереве объекта.
     */
    @Test
    void removeSuccess_Test() {

        Assert.assertEquals(
                Arrays.asList(1, 5, 16, 20, 32, 50),
                Arrays.asList(customNotEmptyTreeSet.toArray()));
        Assertions.assertEquals(true, customNotEmptyTreeSet.remove(1));

        Assertions.assertEquals(true, customNotEmptyTreeSet.remove(32));
        Assert.assertEquals(
                Arrays.asList(5, 16, 20, 50),
                Arrays.asList(customNotEmptyTreeSet.toArray()));
    }

    /**
     * Метод {@link CustomTreeSetImplTest#removeFail_Test()}
     * Проверяет проверяет метод {@link CustomTreeSetImpl#remove(Object)}.
     * Сценарий, при котором объект не успешно отрабатывает удаление
     * не пустого не существующего в дереве объекта.
     */
    @Test
    void removeFail_Test() {
        Assertions.assertEquals(false, customEmptyTreeSet.remove(99));
        Assertions.assertEquals(false, customNotEmptyTreeSet.remove(99));
    }


    /**
     * Метод {@link CustomTreeSetImplTest#containsSuccess_Test()}
     * Проверяет проверяет метод {@link CustomTreeSetImpl#contains(Object)}.
     * Сценарий, при котором проверяется наличие существующего объекта и
     * возвращает <code>true</code>.
     */
    @Test
    void containsSuccess_Test() {
        Assertions.assertEquals(true, customNotEmptyTreeSet.contains(1));
        Assertions.assertEquals(true, customNotEmptyTreeSet.contains(16));
        Assertions.assertEquals(true, customNotEmptyTreeSet.contains(50));
    }

    /**
     * Метод {@link CustomTreeSetImplTest#containsFail_Test()}
     * Проверяет проверяет метод {@link CustomTreeSetImpl#contains(Object)}.
     * Сценарий, при котором проверяется наличие не существующего объекта и
     * возвращает <code></code>.
     */
    @Test
    void containsFail_Test() {
        Assertions.assertFalse(customEmptyTreeSet.contains(99));
        Assertions.assertFalse(customNotEmptyTreeSet.contains(99));

    }

    /**
     * Метод {@link CustomTreeSetImplTest#testToStringEmpty_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomTreeSetImpl}
     * методом {@link CustomTreeSetImpl#toString()}.
     * Сценарий, при котором пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    void testToStringEmpty_Test() {
        Assertions.assertEquals("[ ]", customEmptyTreeSet.toString());
    }

    /**
     * Метод {@link CustomTreeSetImplTest#testToStringNotEmpty_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomTreeSetImpl}
     * методом {@link CustomTreeSetImpl#toString()}.
     * Сценарий, при котором не пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    void testToStringNotEmpty_Test() {
        Assertions.assertEquals(
                "[ 1 5 16 20 32 50 ]", customNotEmptyTreeSet.toString());
    }

    /**
     * Метод {@link CustomTreeSetImplTest#toArray_Test} проверяет
     * метод {@link CustomTreeSetImpl#toArray} на
     * создание копии очереди {@link CustomTreeSetImpl}.
     * Сценарий проверяет идентичность созданной копии и исходной очереди по элементно.
     */
    @Test
    void toArray_Test() {
        Assert.assertEquals(
                Arrays.asList(1, 5, 16, 20, 32, 50),
                Arrays.asList(customNotEmptyTreeSet.toArray()));
        Assert.assertEquals(Arrays.asList(),
                Arrays.asList(customEmptyTreeSet.toArray()));
    }

    private CustomTreeSet<Integer> set = new CustomTreeSetImpl<>(Integer::compareTo);

    @Test
    public void sizeTest() {

        Assert.assertEquals(0, set.size());
        Assert.assertTrue(set.isEmpty());

        for (int i = 1; i <= 10; ++i) {
            Assert.assertTrue(set.add(i));
            Assert.assertEquals(i, set.size());
        }

        for (int i = 10; i >= 1; --i) {
            Assert.assertFalse(set.add(i));
        }
        Assert.assertEquals(10, set.size());

        for (int i = 10; i >= 1; --i) {
            Assert.assertTrue(set.remove(i));
            Assert.assertEquals(i - 1, set.size());
        }

        Assert.assertTrue(set.isEmpty());
    }

    @Test
    public void addTest() {

        Assert.assertEquals(0, set.toArray().length);

        Assert.assertTrue(set.add(10));
        Assert.assertEquals(Arrays.asList(10), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(5));
        Assert.assertEquals(Arrays.asList(5, 10), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(15));
        Assert.assertEquals(Arrays.asList(5, 10, 15), Arrays.asList(set.toArray()));

        Assert.assertFalse(set.add(15));
        Assert.assertEquals(Arrays.asList(5, 10, 15), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(7));
        Assert.assertEquals(Arrays.asList(5, 7, 10, 15), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(1));
        Assert.assertEquals(Arrays.asList(1, 5, 7, 10, 15), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(9));
        Assert.assertEquals(Arrays.asList(1, 5, 7, 9, 10, 15), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(20));
        Assert.assertEquals(Arrays.asList(1, 5, 7, 9, 10, 15, 20), Arrays.asList(set.toArray()));

        Assert.assertTrue(set.add(100));
        Assert.assertEquals(Arrays.asList(1, 5, 7, 9, 10, 15, 20, 100), Arrays.asList(set.toArray()));

        Assert.assertEquals(8, set.size());
    }

    @Test
    public void removeTest() {

        Assert.assertTrue(set.add(10));
        Assert.assertEquals(1, set.size());
        Assert.assertTrue(set.contains(10));

        Assert.assertTrue(set.remove(10));
        Assert.assertFalse(set.contains(10));
        Assert.assertEquals(0, set.size());

        set.add(30);

        set.add(50);
        set.add(10);

        set.add(5);
        set.add(20);
        set.add(70);
        set.add(100);
        set.add(60);
        set.add(65);
        set.add(55);
        set.add(56);
        set.add(19);

        Assert.assertEquals(Arrays.asList(5, 10, 19, 20, 30, 50, 55, 56, 60, 65, 70, 100), Arrays.asList(set.toArray()));

        set.remove(20);
        set.remove(100);

        Assert.assertEquals(Arrays.asList(5, 10, 19, 30, 50, 55, 56, 60, 65, 70), Arrays.asList(set.toArray()));

        set.remove(65);

        Assert.assertEquals(Arrays.asList(5, 10, 19, 30, 50, 55, 56, 60, 70), Arrays.asList(set.toArray()));

        set.remove(50);
        Assert.assertEquals(Arrays.asList(5, 10, 19, 30, 55, 56, 60, 70), Arrays.asList(set.toArray()));

        set.remove(30);
        Assert.assertEquals(Arrays.asList(5, 10, 19, 55, 56, 60, 70), Arrays.asList(set.toArray()));

        set.remove(5);
        set.remove(10);
        set.remove(70);
        set.remove(19);
        set.remove(55);
        set.remove(56);
        set.remove(60);

        Assert.assertEquals(0, set.size());
        Assert.assertEquals(Collections.emptyList(), Arrays.asList(set.toArray()));
    }

    @Test
    public void containsTest() {
        set.add(30);

        set.add(50);
        set.add(10);

        set.add(5);
        set.add(20);
        set.add(70);
        set.add(100);
        set.add(60);
        set.add(65);
        set.add(55);
        set.add(56);
        set.add(19);

        Assert.assertFalse(set.contains(200));
        Assert.assertEquals(Arrays.asList(5, 10, 19, 20, 30, 50, 55, 56, 60, 65, 70, 100), Arrays.asList(set.toArray()));

        for (Object value : set.toArray()) {
            Assert.assertTrue(set.contains((Integer) value));
        }
    }

    @Test
    public void toStringTest() {

        set.add(30);

        set.add(50);
        set.add(10);

        Assert.assertEquals("[ 10 30 50 ]", set.toString());

        set.add(5);
        set.add(20);
        set.add(70);
        set.add(100);

        Assert.assertEquals("[ 5 10 20 30 50 70 100 ]", set.toString());

        set.add(60);
        set.add(65);
        set.add(55);
        set.add(56);
        set.add(19);

        Assert.assertFalse(set.contains(200));
        Assert.assertEquals("[ 5 10 19 20 30 50 55 56 60 65 70 100 ]", set.toString());
    }

    @Test
    public void toArrayTest() {

        set.add(30);

        set.add(50);
        set.add(10);

        Assert.assertEquals(Arrays.asList(10, 30, 50), Arrays.asList(set.toArray()));

        set.add(5);
        set.add(20);
        set.add(70);
        set.add(100);

        Assert.assertEquals(Arrays.asList(5, 10, 20, 30, 50, 70, 100), Arrays.asList(set.toArray()));

        set.add(60);
        set.add(65);
        set.add(55);
        set.add(56);
        set.add(19);

        Assert.assertFalse(set.contains(200));
        Assert.assertEquals(Arrays.asList(5, 10, 19, 20, 30, 50, 55, 56, 60, 65, 70, 100), Arrays.asList(set.toArray()));
    }
}