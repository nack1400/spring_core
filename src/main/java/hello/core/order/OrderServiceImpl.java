package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //생성자를 자동으로 만들어줌
public class OrderServiceImpl implements OrderService {

  //생성자 주입
  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy; //인터페이스에만 의존


  @Autowired //생성자가 하나면 생략가능
  public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

//  //수정자 주입
//  private MemberRepository memberRepository;
//  private DiscountPolicy discountPolicy;
//
//  @Autowired
//  public void setMemberRepository(MemberRepository memberRepository) {
//    this.memberRepository = memberRepository;
//  }
//  @Autowired
//  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//    this.discountPolicy = discountPolicy;
//  }

//  //필드 주입 *권장하지 않음*
//  @Autowired private MemberRepository memberRepository;
//  @Autowired private DiscountPolicy discountPolicy;

  //테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
