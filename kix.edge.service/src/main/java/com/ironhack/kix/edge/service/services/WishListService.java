package com.ironhack.kix.edge.service.services;

import com.ironhack.kix.edge.service.controllers.api.WishApi;
import com.ironhack.kix.edge.service.models.dto.WishDTO;
import com.ironhack.kix.edge.service.repositories.clients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService implements WishApi {

    @Autowired
    UserClient userClient;

    @Override
    public List<WishDTO> getAllWishListsByUserId(Long userId) {
        return userClient.getAllWishListsByUserId(userId);
    }

    @Override
    public WishDTO createWishList(WishDTO wishList, Long userId) {
        return userClient.createWishList(wishList, userId);
    }

    @Override
    public WishDTO getWishList(Long wishListId, Long userId) {
        return userClient.getWishList(wishListId,userId);
    }

    @Override
    public void deleteWishList(Long wishListId, Long userId) {
        userClient.deleteWishList(wishListId, userId);
    }

    @Override
    public void updateWishList(WishDTO newWishList, Long wishListId, Long userId) {
        userClient.updateWishList(newWishList, wishListId, userId);
    }

    @Override
    public WishDTO addProductsToWishList(Long wishListId, Long userId, WishDTO newProducts) {
        return userClient.addProductsToWishList(wishListId, userId, newProducts);
    }
}
