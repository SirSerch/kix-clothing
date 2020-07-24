package com.ironhack.kix.users.service.services;

import com.ironhack.kix.users.service.controllers.Api.WishListApi;
import com.ironhack.kix.users.service.exceptions.WishListNotFoundException;
import com.ironhack.kix.users.service.exceptions.WishListNotOwnerException;
import com.ironhack.kix.users.service.models.User;
import com.ironhack.kix.users.service.models.WishList;
import com.ironhack.kix.users.service.models.dto.WishDTO;
import com.ironhack.kix.users.service.models.enums.Role;
import com.ironhack.kix.users.service.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WishListService implements WishListApi {
    @Autowired UserService userService;
    @Autowired WishListRepository wishListRepository;

    @Override
    public List<WishDTO> getAllWishListsByUserId(Long userId) {
        return wishListRepository.findByUser(userService.getUserById(userId));
    }

    @Override
    public WishDTO createWishList(WishDTO wishList, Long userId) {
        User user = userService.getUserById(userId);
        return new WishDTO(wishListRepository.save(new WishList(wishList, user)));
    }

    @Override
    public WishDTO getWishList(Long wishListId, Long userId) {
        User user = userService.getUserById(userId);
        WishList wishList = this.getWishListById(wishListId);
        if(! wishList.getUser().getId().equals(userId) && user.getRole().equals(Role.CLIENT)) throw new WishListNotOwnerException();
        return new WishDTO(wishList);
    }

    @Override
    public void deleteWishList(Long wishListId, Long userId) {
        WishList wishList = this.getWishListById(wishListId);
        if(! wishList.getUser().getId().equals(userId)) throw new WishListNotOwnerException();
        wishListRepository.delete(wishList);
    }

    @Override
    public void updateWishList(WishDTO newWishList, Long wishListId, Long userId) {
        WishList oldWishList = this.getWishListById(wishListId);
        if(! oldWishList.getUser().getId().equals(userId)) throw new WishListNotOwnerException();
        oldWishList.setName(newWishList.getName());
        oldWishList.setProducts(newWishList.getProducts());
        wishListRepository.save(oldWishList);
    }

    @Override
    public WishDTO addProductsToWishList(Long wishListId, Long userId, WishDTO newProducts) {
        WishList wishList = this.getWishListById(wishListId);
        if(! wishList.getUser().getId().equals(userId)) throw new WishListNotOwnerException();
        Set<String> products = new LinkedHashSet<>();
        products.addAll(wishList.getProducts());
        products.addAll(newProducts.getProducts());
        wishList.setProducts(new ArrayList<>(products));
        return new WishDTO(wishListRepository.save(wishList));
    }

    private WishList getWishListById(Long wishListId){
        return wishListRepository.findById(wishListId).orElseThrow(WishListNotFoundException::new);
    }
}
