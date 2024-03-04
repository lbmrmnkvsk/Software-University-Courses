package org.example.l17_productsshop.repositories;

import org.example.l17_productsshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select distinct u from User u join u.soldProducts p where p.buyer is not null order by u.lastName, u.firstName")
    List<User> findUsersWithSoldProducts();
}
