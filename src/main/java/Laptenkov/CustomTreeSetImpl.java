package Laptenkov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * Класс {@link CustomTreeSetImpl<T>} реализует множество на основе бинарного дерева поиска.
 * Класс {@link CustomTreeSetImpl<T>} реализует интерфейс {@link CustomTreeSet<T>}.
 * Класс {@link CustomTreeSetImpl<T>} может хранить объекты любого типа.
 * @author habatoo
 */
public class CustomTreeSetImpl<T> implements CustomTreeSet<T>{

    /**
     * Контейнер для хранения объекта {@link CustomTreeSetImpl<T>}
     * @param <T>
     */
    private static class Node<T> {

        T item;
        Node<T> left, right;
        Node<T> parent;

        public Node(T item) {
            this.item = item;
        }

        void setLeftChild(Node<T> node) {
            this.left = node;
            if (this.left != null) {
                this.left.parent = this;
            }
        }

        void setRightChild(Node<T> node) {
            this.right = node;
            if (this.right != null) {
                this.right.parent = this;
            }
        }

    }

    private Node<T> root;
    private final Comparator<T> comparator;
    private int size;

    /**
     * Конструктор объекта {@link CustomTreeSetImpl<T>}
     * @param comparator для логики упорядочивания элементов
     * объекта {@link CustomTreeSetImpl<T>}
     */
    CustomTreeSetImpl(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Метод {@link CustomTreeSetImpl#size()} возвращает размер связанного списка
     * объекта {@link CustomTreeSetImpl}.
     *
     * @return целое число типа {@link int}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод {@link CustomTreeSetImpl#isEmpty()} возвращает булево значение
     * при проверке объекта {@link CustomTreeSetImpl} на пустоту.
     *
     * @return <code>true</code> если размер не нулевой,
     * <code>false</code> если размер нулевой.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод {@link CustomTreeSetImpl#add(Object)} возвращает булево значение
     * при попытке добавления объекта в {@link CustomTreeSetImpl}.
     *
     * @param item объект типа Т для добавления.
     * @return возвращает <code>true</code> если добавление успешно,
     * возвращает <code>false</code> если добавление не удалось.
     */
    @Override
    public boolean add(T item) {
        if (root == null) {
            root = new Node<>(item);
            size = 1;
            return true;
        }

        return insertAfter(root, item);
    }

    /**
     * Метод {@link CustomTreeSetImpl#remove(Object)} возвращает булево значение
     * при попытке удаления объекта в {@link CustomTreeSetImpl}.
     *
     * @param item объект типа Т для удаления.
     * @return возвращает <code>true</code> если удаление успешно,
     * возвращает <code>false</code> если удаление не удалось.
     */
    @Override
    public boolean remove(T item) {
        if (root == null) {
            return false;
        }

        if (root.item == item) {
            siftAfter(root);
            return true;
        }
        return removeBefore(root, item);
    }


    /**
     * Метод удаляет объект из {@link CustomTreeSetImpl<T>} с упорядочиванием их
     * по порядку - используя comparator.
     * @param node узел в котором производится попытка удаления.
     * @param newItem объект типа Т для удаления.
     * @return возвращает <code>true</code> если удаление успешно,
     * возвращает <code>false</code> если удаление не удалось.
     */
    private boolean removeBefore(Node<T> node, T newItem) {

        int result = comparator.compare(newItem, node.item);
        if (result == 0) {
            siftAfter(node);
            return true;
        }

        if (result < 0) {
            if (node.left == null) {
                return false;
            } else {
                return removeBefore(node.left, newItem);
            }
        } else {
            if (node.right == null) {
                return false;
            } else {
                return removeBefore(node.right, newItem);
            }
        }
    }

    /**
     * Вспомогательный метод для метода {@link CustomTreeSetImpl<T>#remove(Object)}
     * прохохжления по дереву и удаления
     * @param node узел для дальнейшего рекурсивного обхода.
     */
    private void siftAfter(Node<T> node) {

        if (node.right == null && node.left == null) {
            if (node.parent == null) {
                root = null;
                size = 0;
                return;
            }

            if (node.parent.left == node) {
                node.parent.left = null;
                size--;
                return;
            }
            if (node.parent.right == node) {
                node.parent.right = null;
                size--;
                return;
            }
        }

        // X не имеет правого потомка
        if (node.right == null) {
            exchange(node, node.left);
            return;
        }

        // X имеет правого потомка Y
        if (node.right != null) {
            if (node.right.left == null) { // Y не имеет левого потомка
                node.right.setLeftChild(node.left);
                exchange(node, node.right);
                return;
            }

            if (node.right.left != null) { // Y имеет левого потомка
                Node<T> tmpLeft = node.right;
                Node<T> tmpPrev = null;
                while (tmpLeft.left != null) {
                    tmpPrev = tmpLeft;
                    tmpLeft = tmpLeft.left;
                }

                if (tmpLeft.right != null) {
                    tmpPrev.left = tmpLeft.right;
                } else {
                    tmpPrev.left = null;
                }
                tmpLeft.setLeftChild(node.left);
                tmpLeft.setRightChild(node.right);
                exchange(node, tmpLeft);
                return;
            }
        }

    }

    /**
     * Вспомогательный метод для метода {@link CustomTreeSetImpl<T>#remove(Object)}
     * замены двух нод.
     * @param node узел который меняют.
     * @param changeNode узел для замены.
     */
    private void exchange(Node<T> node, Node<T> changeNode) {
        if (node.parent == null) {
            changeNode.parent = null;
            root = changeNode;
        } else if (node.parent.left == node) {
            node.parent.left = changeNode;
        } else if (node.parent.right == node) {
            node.parent.right = changeNode;
        }
        node = changeNode;
        size--;
        return;
    }


    /**
     * Метод {@link CustomTreeSetImpl#contains(Object)} возвращает булево значение
     * при проверке наличия объекта в {@link CustomTreeSetImpl}.
     *
     * @param item объект типа Т для проверки.
     * @return возвращает <code>true</code> если объект присутствует,
     * возвращает <code>false</code> если объект отсутствует.
     */
    @Override
    public boolean contains(T item) {
        if (root == null) {
            return false;
        }

        return searchAfter(root, item);
    }

    /**
     * Вспомогательный метод для рекурсивного поиска объекта по его значению
     * в дереве.
     * @param node текущий объект от которого осуществляется рекурсивный поиск.
     * @param newItem объект для поиска
     * @return возвращает <code>true</code> если объект присутствует,
     * возвращает <code>false</code> если объект отсутствует.
     */
    private boolean searchAfter(Node<T> node, T newItem) {

        int result = comparator.compare(newItem, node.item);

        if (result == 0) {
            return true;
        }

        if (result < 0) {
            if (node.left == null) {
                return false;
            } else {
                return searchAfter(node.left, newItem);
            }
        } else {
            if (node.right == null) {
                return false;
            } else {
                return searchAfter(node.right, newItem);
            }
        }
    }

    /**
     * Метод {@link CustomTreeSetImpl#toString()}
     * возвращает строковое представление дерева {@link CustomTreeSetImpl}
     *
     * @return объект типа String в формате '[ element1 element2 ... elementN ]
     * или [ ] если дерево пустое.
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[ ]";
        }

        Collection<T> array = new ArrayList<>();
        fillRecursive(root, array);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (T c : array) {
            sb.append(" " + c);
        }
        sb.append(" ]");

        return sb.toString();
    }

    /**
     * Вспомогательный метод для прохохжления по дереву и заполнению данных
     * хранящихся в узлах дерева в о вспомогательный массив.
     * @param node узел для дальнейшего рекурсивного обхода.
     * @param array вспомогательный массив для заполнения данными.
     */
    private void fillRecursive(Node<T> node, Collection<T> array) {

        if (node.left != null) {
            fillRecursive(node.left, array);
        }
        array.add(node.item);
        if (node.right != null) {
            fillRecursive(node.right, array);
        }
    }

    /**
     * Метод {@link CustomTreeSetImpl<T>#toArray()}
     * возвращает копию текущего объекта
     * {@link CustomTreeSetImpl<T>}.
     *
     * @return объект типа {@link CustomTreeSetImpl<T>}.
     */
    @Override
    public Object[] toArray() {
        if (size == 0) {
            return new Object[0];
        }

        ArrayList<T> array = new ArrayList<>();
        fillRecursive(root, array);
        Object[] newData = new Object[size];
        for (int i = 0; i < size; i++) {
            newData[i] = array.get(i);
        }

        return newData;
    }

    /**
     * Метод добавлляет объект в {@link CustomTreeSetImpl<T>} с упорядочиванием их
     * по порядку - используя comparator.
     * @param node узел в который производится попытка добавления.
     * @param newItem объект типа Т для добавления.
     * @return возвращает <code>true</code> если добавление успешно,
     * возвращает <code>false</code> если добавление не удалось.
     */
    private boolean insertAfter(Node<T> node, T newItem) {

        int result = comparator.compare(newItem, node.item);
        if (result == 0) {
            return false;
        }

        if (result < 0) {
            if (node.left == null) {
                node.left = new Node<>(newItem);
                node.left.parent = node;
                size++;
                return true;
            } else {
                return insertAfter(node.left, newItem);
            }
        } else {
            if (node.right == null) {
                node.right = new Node<>(newItem);
                node.right.parent = node;
                size++;
                return true;
            } else {
                return insertAfter(node.right, newItem);
            }
        }
    }
}
