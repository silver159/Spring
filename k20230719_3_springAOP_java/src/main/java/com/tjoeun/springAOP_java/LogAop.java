package com.tjoeun.springAOP_java;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

// 공통 기능 메소드를 모아놓은 클래스
// java 파일을 이용해서 AOP 설정을 하려면 AOP를 설정할 클래스에 @Aspect 어노테이션을
// 붙여준다.
@Aspect
public class LogAop {
	
// pointcut을 지정하는 1번째 방법 => 빈 메소드를 이용해서 pointcut 메소드를 만든다.
// 적당한 이름으로 빈 메소드를 만든 후 @Pointcut 어노테이션을 붙여서 인수에 
// pointcut을 지정한다.
// @Pointcut("within() 또는 execution()을 이용한 pointcut")
// public void 적당한 이름(){
//	 	메소드가 실행할 내용이 없는 빈 메소드
// }
// pointcut 1개를 만들어서 여러 메소드에 적용시킬 수 있다.	
	
	@Pointcut("within(com.tjoeun.springAOP_java.Student)")
	public void pointcutMethod() {	}
	
// pointcut을 AOP 메소드에 적용하려면 AOP 어노테이션(@Before, @AfterReturning,
// @AfterThrowing, @After, @Aruond)의 인수로 pointcut을 설정한 빈 메소드 이름을
// 넣어주면 된다.
	
	@Before("pointcutMethod()")
	public void before() {
		System.out.println("LogAop 클래스의 before() 메소드가 실행됨");
	}	
	
	@AfterReturning("pointcutMethod()")
	public void afterReturning() {
		System.out.println("logAop 클래스의 afterReturning() 메소드가 실행됨");
	}	
	
	@AfterThrowing("pointcutMethod()")
	public void afterThrowing() {
		System.out.println("logAop 클래스의 afterThrowing() 메소드가 실행됨");
	}
	
	@After("pointcutMethod()")
	public void after() {
		System.out.println("logAop 클래스의 after() 메소드가 실행됨");
	}	
	
// around
// 1. around AOP 메소드는 핵심 기능이 실행되고 난 후 리턴되는 데이터 타입을 예측할
// 수 없으므로 모든 데이터 타입을 포함할 수 있는 자바의 최상위 클래스인 Object로 
// 지정한다.
	
// 2. around AOP 메소드의 인수인 ProceedingJoinPoint 인터페이스 타입의 객체로 
// 스프링이 자동으로 실행할 핵심 기능(메소드)을 넘겨준다.
// => 반드시 인수로 ProceedingJoinPoint 인터페이스 타입의 객체를 사용한다.
// => pom.xml 파일에 aspectjweaver dependency에 <scope>runtime</scope>이 있으면
// ProceedingJoinPoint 인터페이스를 사용할 수 없으므로 삭제하거나 주석으로 처리한다.
	
// 3. around AOP 메소드는 try ~ finally 형태로 실행하며 catch는 throw Throwable로 
// 처리한다. 
	
	
// pointcut을 지정하는 2번째 방법 => AOP 어노테이션의 인수로 바로 pointcut을 지정할 
// 수 있다.	
	@Around("execution(* com.tjoeun.springAOP_java.W*.*())")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		// 핵심 기능이 실행하기 전 실행할 내용을 코딩한다.
		System.out.println("logAop 클래스의 around() 메소드가 실행됨 - 핵심 기능 실행 전");
		long start = System.currentTimeMillis(); // 시작 시간
		try {
			// 핵심 기능을 실행한다.
			System.out.println("logAop 클래스의 around() 메소드가 실행됨 - 핵심 기능 실행 중");
			// ProceedingJoinPoint 인터페이스 객체로 넘어온 핵심 기능을 실행하고 
			// 실행 결과를 Object 클래스 객체에 저장해서 리턴시킨다.
			Thread.sleep(2000);
			Object object = joinPoint.proceed(); // 핵심 기능을 실행한다.
			
			// 핵심 기능을 실행한 결과를 리턴시킨다.
			return object;
		} finally {
			// 핵심 기능이 실행되고 난 후 실행할 내용을 코딩한다.
			System.out.println("logAop 클래스의 around() 메소드가 실행됨 - 핵심 기능 실행 후");
			long end = System.currentTimeMillis(); // 종료 시간
			System.out.println("핵심 기능이 실행되는데 경과된 시간: "+ (end - start)/1000. + "초");
		}
	}
	
}
