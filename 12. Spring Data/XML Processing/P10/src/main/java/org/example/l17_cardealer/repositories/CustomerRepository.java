package org.example.l17_cardealer.repositories;

import org.example.l17_cardealer.entities.Customer;
import org.example.l17_cardealer.entities.CustomerExportDTO2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByOrderByBirthDateAscIsYoungDriverDesc();
    @Query("select  new org.example.l17_cardealer.entities.CustomerExportDTO2(c.name, count(distinct s.id), sum(distinct p.price)) " +
            "from Customer c join c.sales s join s.car car join car.parts p " +
            "group by c.name " +
            "having count(s) > 0 " +
            "order by sum(distinct p.price) desc, count(distinct s.id) desc")
    List<CustomerExportDTO2> findAllCustomersWithTotalSales();
}
