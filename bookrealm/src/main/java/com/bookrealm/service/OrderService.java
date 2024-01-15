package com.bookrealm.service;

import com.bookrealm.model.*;
import com.bookrealm.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final OrderListRepository orderListRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, BookRepository bookRepository, OrderListRepository orderListRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
        this.orderListRepository = orderListRepository;
    }

    public List<Order> getAllOrdersWithBooks() {
        return orderRepository.findAll();
    }

    public Order getOrderWithBooksById(Long orderId) {
        return orderRepository.findOrderWithBooksById(orderId);
    }

    public List<Order> findByMemberId(Long id) {
        List<Order> order = orderRepository.findByMemberId(id);
        return null;
    }

    public Order findById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return order;
    }

    public int totalPrice(Order order){  // 총가격 메서드
        int total = 0;
        List<OrderList> orderLists= orderListRepository.findByOrderId(order.getId());

        for (int i = 0; i < orderLists.size(); i++) {
            OrderList orderList = orderLists.get(i);
            int bookPrice = orderList.getBook().getPrice();
            int purchase = orderList.getPurchase();
            total += purchase*bookPrice;
        }

        return total;

    }

    public void selectPayment(Order order){

        switch (order.getPayment()) {
            case CREDIT_CARD:
                processCreditCardPayment(order.getTotalAmount());
                break;

            case BANK_TRANSFER:
                processBankTransferPayment(order.getTotalAmount());
                break;
            default:
                throw new IllegalArgumentException("지원하지 않는 결제수단입니다.");
        }

    }

    // 실제 결제 처리 메서드들 (이 부분은 각 결제 방식에 따라 구현되어야 합니다.)
    void processCreditCardPayment(int totalPrice) {
        System.out.println("신용카드 결제 완료. 총액: " + totalPrice);
    }

     void processBankTransferPayment(int totalPrice) {
        System.out.println("계좌이체 결제 완료. 총액: " + totalPrice);
    }

    @Transactional
    public Order cartOrder(Order order) {
        order = orderRepository.save(order);
        List<Cart> carts = cartRepository.findByMemberId(order.getMember().getId());
        int price = 0;

        for(Cart cart : carts) {
            OrderList orderList = new OrderList();
            Book book = cart.getBook();
            orderList.setBook(book);
            orderList.setOrder(order);
            orderList.setPurchase(cart.getPurchase());
            orderList.setStatus(Status.ORDER_COMPLETE);

            book.setStock(book.getStock() - cart.getPurchase());
            book.setSales(book.getSales() + cart.getPurchase());

            price = price + (book.getPrice() * orderList.getPurchase());
            bookRepository.save(book);
            orderListRepository.save(orderList);
        }

        order.setTotalAmount(price);
        order = orderRepository.save(order);

        selectPayment(order);
        return order;
    }

    public Order oneOrder(Order order, Book book, int num) {
        order = orderRepository.save(order);

        OrderList orderList = new OrderList();
        orderList.setBook(book);
        orderList.setOrder(order);
        orderList.setPurchase(num);
        orderList.setStatus(Status.ORDER_COMPLETE);

        book.setStock(book.getStock() - num);
        book.setSales(book.getSales() + num);

        order.setTotalAmount(book.getPrice() * num);

        bookRepository.save(book);
        orderListRepository.save(orderList);

        selectPayment(order);

        return order;
    }
}



