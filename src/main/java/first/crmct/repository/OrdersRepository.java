package first.crmct.repository;

/*

Репозиторий с SQL запросом в базу, который строится на основе полученной строки из главноименной ....ServiceIml
Таблица заказа (Order) соединяется с таблицей заказов (Orders) по id (Order.status=Orders.id),
потом с таблицей статусов (StatusOrder) по id (Order.status=StatusOrder.id),
потом с таблицей менеджеров (Manager) по id (Orders.manager.id=Manager.id),
потом с таблицей компаний (Company) по id (Manager.company.id=Company.id),
и производится поиск в полученных данных по:
названию компании(Company.name),
имени менеджера(Manager.firstName),
фамилии менеджера(Manager.lastName)

 */

import first.crmct.model.Order;
import first.crmct.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Long> {

    @Query("SELECT c FROM Order c " +
            "WHERE c.orders.manager.company.name LIKE %:q% " +
            "OR c.statusOrder.name LIKE %:q% " +
            "OR c.orders.manager.firstName LIKE %:q% " +
            "OR c.orders.manager.lastName LIKE %:q%")
    List<Order> findByQuery(@Param("q") String query);

}
