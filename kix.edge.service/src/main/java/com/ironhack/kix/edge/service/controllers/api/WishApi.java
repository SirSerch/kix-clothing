package com.ironhack.kix.edge.service.controllers.api;

import com.ironhack.kix.edge.service.models.dto.WishDTO;

import java.util.List;

public interface WishApi{
    List<WishDTO> getAllWishListsByUserId(Long userId);
    WishDTO createWishList(WishDTO wishList, Long userId);
    WishDTO getWishList(Long wishListId, Long userId);
    void deleteWishList(Long wishListId, Long userId);
    void updateWishList(WishDTO wishList, Long wishListId, Long userId);
    WishDTO addProductsToWishList(Long wishListId, Long userId, WishDTO newProducts);
}
