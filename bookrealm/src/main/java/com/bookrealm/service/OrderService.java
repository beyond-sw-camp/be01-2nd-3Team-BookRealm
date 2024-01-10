package com.bookrealm.service;

import com.bookrealm.model.Order;
import com.bookrealm.model.OrderList;
import com.bookrealm.model.Payment;
import com.bookrealm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public int totalPrice(Order order){  // 총가격 메서드
        int total = 0;
        for (int i = 0; i < order.getOrderLists().size(); i++) {
            OrderList orderList = order.getOrderLists().get(i);
            int bookPrice = orderList.getBook().getPrice();
            int purchase = orderList.getPurchase();
            total += purchase*bookPrice;
        }

        return total;

    }

    public void selectPayment(Order order, Payment payment){

        int totalPrice = totalPrice(order);
        switch (payment) {
            case CREDIT_CARD:
                processCreditCardPayment(totalPrice);
                break;

            case BANK_TRANSFER:
                processBankTransferPayment(totalPrice);
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

}



