package com.ironhack.kix.users.service.controllers;

import com.ironhack.kix.users.service.controllers.Api.WishListApi;
import com.ironhack.kix.users.service.models.dto.WishDTO;
import com.ironhack.kix.users.service.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishListController implements WishListApi {

    @Autowired
    WishListService wishListService;

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/wish-lists")
    public List<WishDTO> getAllWishListsByUserId(@PathVariable("userId") Long userId) {
        return wishListService.getAllWishListsByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/wish-list")
    public WishDTO createWishList(@RequestBody WishDTO wishList, @PathVariable("userId") Long userId) {
        return wishListService.createWishList(wishList, userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/wish-lists/{wishListId}")
    public WishDTO getWishList(@PathVariable("wishListId") Long wishListId,
                               @PathVariable("userId") Long userId) {
        return wishListService.getWishList(wishListId, userId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value =  "/users/{userId}/wish-lists/{wishListId}")
    public void deleteWishList(@PathVariable("wishListId") Long wishListId,
                               @PathVariable("userId") Long userId) {
        wishListService.deleteWishList(wishListId, userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value =  "/users/{userId}/wish-lists/{wishListId}")
    public void updateWishList(@RequestBody WishDTO wishList,
                               @PathVariable("wishListId") Long wishListId,
                               @PathVariable("userId") Long userId) {
        wishListService.updateWishList(wishList, wishListId, userId);
    }
    @RequestMapping(method = RequestMethod.POST, value =  "/users/{userId}/wish-lists/{wishListId}")
    @Override
    public WishDTO addProductsToWishList(@PathVariable("wishListId") Long wishListId,
                                         @PathVariable("userId") Long userId,
                                         @RequestBody WishDTO newProducts) {
        return wishListService.addProductsToWishList(wishListId, userId, newProducts);
    }
}
