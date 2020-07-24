package com.ironhack.kix.users.service.controllers.Api;

import com.ironhack.kix.users.service.models.dto.WishDTO;

import java.util.List;

public interface WishListApi {
    List<WishDTO> getAllWishListsByUserId(Long userId);
    WishDTO createWishList(WishDTO wishList, Long userId);
    WishDTO getWishList(Long wishListId, Long userId);
    void deleteWishList(Long wishListId, Long userId);
    void updateWishList(WishDTO wishList, Long wishListId, Long userId);
    WishDTO addProductsToWishList(Long wishListId, Long userId, WishDTO newProducts);

}
