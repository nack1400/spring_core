package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //스캔할 패키지를 설정해 줌
        //default로 설정할 시 이걸 붙인 클래스의 패키지를 스캔함, 여기는 package hello.core;
        //관례는 굳이 지정하지 않는 것을 권장
        //basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) //실무에선 지우지 않으나 기존의 예제 코드때메 하는 것
public class AutoAppConfig {

  /*@Bean(name = "memoryMemberRepository")
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
*/
}
