package com.tjoeun.springProfile_java;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("실행할 작업 환경을 입력하세요(1 => dev, 2 => run): ");
		int info = scanner.nextInt();
		String config = "";
		switch (info) {
			case 1:
				config = "dev";
				break;
			case 2:
				config = "run";
				break;
		}
		
		// xml
		// profile이 설정된 xml 파일의 bean을 읽어오기 위해서는 DI 컨테이너(ctx)를 
		// 먼저 만든 후 읽어올 bean의 profile을 지정한 다음 해당 profile이 지정된 
		// bean을 load 시켜야 한다.
		// DI 컨테이너 생성. bean 설정 정보는 들어있지 않다.
//		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		// java
		// profile이 설정된 java 파일의 bean을 읽어오기 위해서는 DI 컨테이너(ctx)를 
		// 먼저 만든 후 읽어올 bean의 profile을 지정한 다음 해당 profile이 지정된 
		// bean을 load 시켜야 한다.
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		
		// 읽어올 bean의 profile을 넣어준다.
		// getEnvironment() 메소드는 DI 컨테이너의 환경 설정 정보를 얻어온 후 
		// setActiveProfiles() 메소드로 읽어올 profile을 넣어준다.
		ctx.getEnvironment().setActiveProfiles(config);
		
		// xml
		// ctx.load("classpath:applicationCTX_dev.xml", "classpath:applicationCTX_run.xml");
		
		// GenericXmlApplicationContext 클래스로 xml 파일에서 설정한 bean 설정 
		// 정보를 DI 컨테이너에 넣어주기 위해서 load() 메소드를 사용했지만 
		// AnnotationConfigApplicationContext 클래스로 java 파일에서 @profile
		// 어노테이션을 붙여서 설정한 bean 설정 정보를 넣어주려면 register() 
		// 메소드를 사용해야 한다.
		
		// java
		ctx.register(ApplicationConfigDev.class, ApplicationConfigRun.class);
		
		// bean 설정 정보에 따른 bean을 만든다.
		ctx.refresh();
		
		ServerInfo serverInfo = ctx.getBean("serverInfo", ServerInfo.class);
		System.out.println(serverInfo);
		
		ctx.close();
	}
} 
