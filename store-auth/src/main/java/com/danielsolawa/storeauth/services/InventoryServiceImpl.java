package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.InventoryDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.mappers.InventoryMapper;
import com.danielsolawa.storeauth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    private final UserRepository userRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryServiceImpl(UserRepository userRepository, InventoryMapper inventoryMapper) {
        this.userRepository = userRepository;
        this.inventoryMapper = inventoryMapper;
    }


    @Transactional
    @Override
    public InventoryDto updateInventory(InventoryDto inventoryDto) {
        User user = getUserById(inventoryDto.getUserId());

        user.setInventory(inventoryMapper.inventoryDtoToInventory(inventoryDto));

        User returnedUser = userRepository.save(user);

        return inventoryMapper.inventoryToInventoryDto(returnedUser.getInventory());
    }

    @Override
    public InventoryDto getInventoryByUserId(Long userId) {
        User user = getUserById(userId);

        return inventoryMapper.inventoryToInventoryDto(user.getInventory());
    }

    @Override
    public void deleteInventoryByUserId(Long userId) {

        User user = getUserById(userId);
        user.setInventory(null);

        userRepository.save(user);
    }


    private User getUserById(Long id){
        User user = userRepository.findOne(id);

        if(user == null){
            throw new ResourceNotFoundException();
        }

        return user;
    }
}
