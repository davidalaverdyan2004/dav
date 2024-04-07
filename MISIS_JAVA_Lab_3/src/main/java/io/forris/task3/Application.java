package io.forris.task3;

import java.util.ArrayList;
import java.util.List;

// Интерфейс итератора
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// Перечисление типов предметов
enum ItemType {
    Any,
    Weapon,
    Ring,
    Potion
}

// Класс, представляющий предмет
class Item {
    private ItemType type;
    private final String name;

    public Item(ItemType type, String name) {
        this.setType(type);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public final void setType(ItemType type) {
        this.type = type;
    }
}

// Итератор для сундука с сокровищами
class TreasureChestItemIterator implements Iterator<Item> {
    private final TreasureChest chest;
    private final ItemType type;
    private int idx;

    public TreasureChestItemIterator(TreasureChest chest, ItemType type) {
        this.chest = chest;
        this.type = type;
        this.idx = -1;
    }

    @Override
    public boolean hasNext() {
        return findNextIdx() != -1;
    }

    @Override
    public Item next() {
        idx = findNextIdx();
        if (idx != -1) {
            return chest.getItems().get(idx);
        }
        return null;
    }

    private int findNextIdx() {
        List<Item> items = chest.getItems();
        int tempIdx = idx;
        while (true) {
            tempIdx++;
            if (tempIdx >= items.size()) {
                tempIdx = -1;
                break;
            }

            if (type.equals(ItemType.Any) || items.get(tempIdx).getType().equals(type)) {
                break;
            }
        }
        return tempIdx;
    }
}

// Класс, представляющий сундук с сокровищами
class TreasureChest {
    private final List<Item> items;

    public TreasureChest() {
        items = List.of(
                new Item(ItemType.Potion, "Зелье мужества"),
                new Item(ItemType.Ring, "Кольцо теней"),
                new Item(ItemType.Potion, "Зелье мудрости"),
                new Item(ItemType.Potion, "Зелье крови"),
                new Item(ItemType.Weapon, "Меч из серебра +1"),
                new Item(ItemType.Potion, "Зелье ржавчины"),
                new Item(ItemType.Potion, "Зелье исцеления"),
                new Item(ItemType.Ring, "Кольцо брони"),
                new Item(ItemType.Weapon, "Стальная алебарда"),
                new Item(ItemType.Weapon, "Кинжал яда")
        );
    }
}
    public Iterator<Item> iterator(ItemType itemType) {
        return new TreasureChestItemIterator(this, itemType);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
}

// Основной класс приложения
public class Application {
    public static void main(String[] args) {
        // В данном примере main-метод оставлен пустым, поскольку
        // основная логика демонстрируется в классах TreasureChest и TreasureChestItemIterator.
    }
}