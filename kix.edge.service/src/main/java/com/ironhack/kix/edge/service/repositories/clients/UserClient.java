package com.ironhack.kix.edge.service.repositories.clients;

import com.ironhack.kix.edge.service.models.dto.UserDTO;
import com.ironhack.kix.edge.service.models.dto.WishDTO;
import com.ironhack.kix.edge.service.models.views.UserView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("kix-user-service")
public interface UserClient {
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/users/find")
    UserView getUserByEmail(@RequestParam(name = "email", required = true) String email);

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    UserView getUserById(@PathVariable("userId") Long userId);

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    List<UserView> getAllUsers();

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    UserView createUser(@RequestBody UserDTO userDTO);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    void deleteUser(@PathVariable("userId") Long userId);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}")
    void updateUser(@RequestBody UserDTO userDTO, @PathVariable("userId") Long userId);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/wish-lists")
    List<WishDTO> getAllWishListsByUserId(@PathVariable("userId") Long userId);

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/wish-list")
    WishDTO createWishList(@RequestBody WishDTO wishList,
                           @PathVariable("userId") Long userId);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/wish-lists/{wishListId}")
    WishDTO getWishList(@PathVariable("wishListId") Long wishListId,
                        @PathVariable("userId") Long userId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/wish-lists/{wishListId}")
    void deleteWishList(@PathVariable("wishListId") Long wishListId,
                        @PathVariable("userId") Long userId);

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}/wish-lists/{wishListId}")
    void updateWishList(@RequestBody WishDTO wishList,
                        @PathVariable("wishListId") Long wishListId,
                        @PathVariable("userId") Long userId);

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/wish-lists/{wishListId}")
    WishDTO addProductsToWishList(@PathVariable("wishListId") Long wishListId,
                                  @PathVariable("userId") Long userId,
                                  @RequestBody WishDTO newProducts);
}
