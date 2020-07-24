package com.ironhack.kix.users.service.repositories;

import com.ironhack.kix.users.service.models.User;
import com.ironhack.kix.users.service.models.WishList;
import com.ironhack.kix.users.service.models.dto.WishDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishDTO> findByUser(User user);
}
