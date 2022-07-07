package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }
    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());

        assertThat(savedItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);

        //when
        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> result = itemRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);
    }


    @Test
    void update() {
        //given
        Item item = new Item("itemA", 10000, 10);

        Item saveItem = itemRepository.save(item);
        Long itemId = saveItem.getId();

        //when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        Item id = itemRepository.findById(itemId);

        assertThat(id.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(id.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(id.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}