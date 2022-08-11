package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.UpdateItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.fineOne(itemId);
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDTO dto){
        Book findItem = (Book) itemRepository.fineOne(itemId);
        findItem.setPrice(dto.getPrice());
        findItem.setName(dto.getName());
        findItem.setStockQuantity(dto.getStockQuantity());
        findItem.setAuthor(dto.getAuthor());
        findItem.setIsbn(dto.getIsbn());
    }
}
