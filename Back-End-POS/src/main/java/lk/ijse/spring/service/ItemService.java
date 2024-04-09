package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomDTO;
import lk.ijse.spring.dto.ItemDTO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

public interface ItemService {
    void saveItem(@ModelAttribute ItemDTO dto);

    void updateItem(@RequestBody ItemDTO dto);

    void deleteItem(@RequestBody ItemDTO dto);

    ItemDTO searchItemCode(String code);

    ArrayList<ItemDTO> loadAllItem();

    @ResponseBody
    CustomDTO itemIdGenerate();

    @ResponseBody
    CustomDTO getSumItem();
}
