package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.entity.Order;
import m2m_phase2.clothing.clothing.data.entity.UserE;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrders();

    List<Object[]> findOrdersWithUsernameByEmail(String email);

}