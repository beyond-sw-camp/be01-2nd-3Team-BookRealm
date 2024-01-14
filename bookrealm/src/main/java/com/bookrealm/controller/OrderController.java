package com.bookrealm.controller;

import com.bookrealm.model.Address;
import com.bookrealm.model.Member;
import com.bookrealm.model.Order;
import com.bookrealm.model.Payment;
import com.bookrealm.model.dto.OrderDto;
import com.bookrealm.service.BookService;
import com.bookrealm.service.CartService;
import com.bookrealm.service.MemberService;
import com.bookrealm.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final MemberService memberService;
    private final OrderService orderService;
    private final BookService bookService;

    public OrderController(CartService cartService, MemberService memberService, OrderService orderService, BookService bookService) {
        this.cartService = cartService;
        this.memberService = memberService;
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping("/cart")
    public String showCartOrderPage(Model model, Principal principal) {
        Member member = memberService.getUser(principal.getName());
        model.addAttribute("cartItems", cartService.getCartByMember(member));
        model.addAttribute("currentUser", member);
        model.addAttribute("totalAmount", cartService.totalPrice(member));
        return "order";
    }

    @GetMapping("/one")
    public String showOneOrderPage(@RequestParam("id") Long id,@RequestParam("num") int num, Model model, Principal principal) {
        Member member = memberService.getUser(principal.getName());
        model.addAttribute("cartItems", bookService.findBookById(id));
        model.addAttribute("currentUser", member);
        model.addAttribute("totalAmount", num);
        return "order";
    }

    @RequestMapping(value = "/one/{id}/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    public String oneOrder(@PathVariable("id") Long id, @PathVariable("num") int num, @RequestBody OrderDto orderDto,
                           Model model, Principal principal) {
        return null;
    }


    @PostMapping("/cart")
    public String cartOrder(
            @RequestParam("postcode") String postcode,
            @RequestParam("address") String address,
            @RequestParam("detailAddress") String detailAddress,
            @RequestParam(name = "extraAddress", required = false) String extraAddress,
            @RequestParam("paymentMethod") String paymentMethod,
            Model model, Principal principal) {
        // 여기에서 주문 처리 로직을 추가
        // 받아온 주소 및 결제 수단 정보를 사용하여 주문을 처리하는 비즈니스 로직을 구현합니다.
        Address address1 = new Address(postcode, address, detailAddress,extraAddress);
        Member member = memberService.getUser(principal.getName());
        Order order = new Order();
        order.setDestination(address1);
        order.setMember(member);

        if(paymentMethod.equals("card")) order.setPayment(Payment.CREDIT_CARD);
        else if(paymentMethod.equals("bank")) order.setPayment(Payment.BANK_TRANSFER);

        orderService.cartOrder(order);


        // 주문 완료 페이지로 리다이렉트
        return "redirect:/order/success?id=" + order.getId();
    }

    @GetMapping("/success")
    public String showOrderCompletePage(Model model, @RequestParam("id") Long id) {
        // 여기에 주문 정보를 모델에 추가하세요
        Order order = orderService.findById(id);
        int total = orderService.totalPrice(order);
        model.addAttribute("orderNumber", id);
        model.addAttribute("orderTotalAmount", total);

        return "order_complete";
    }
}
