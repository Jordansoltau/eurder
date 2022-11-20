package com.example.eurder.repositories;

import com.example.eurder.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {



     Optional<Item> findById(String itemid);

}
