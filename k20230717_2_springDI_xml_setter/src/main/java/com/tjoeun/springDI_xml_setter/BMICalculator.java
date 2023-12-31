package com.tjoeun.springDI_xml_setter;

public class BMICalculator {
	
// 키(height)와 몸무게(weight)를 인수로 넘겨받아 BMI 지수를 계산하고 등급을 출력하는
// 메소드
// BMI 지수는 몸무게(㎏) / (키(㎝) * 키(㎝))
// 산출된 값이 18.5 미만이면 저체중, 18.5 이상 23 미만이면 정상, 23 이상 25 미만이면
// 과체중, 25 이상 30미만이면 비만, 30 이상이면 고도비만
	 public void bmiCalculator(double height, double weight) {
		double bmi =  weight / Math.pow(height/100, 2);
		System.out.print("키("+height +"cm, 몸무게" + weight + "㎏)의 bmi는 "
						+ bmi+"이고 ");
		if (bmi < 18.5) {
			System.out.println("저체중입니다.");
		}else if (bmi < 23) {
			System.out.println("정상입니다.");
		}else if (bmi < 25) {
			System.out.println("과체중입니다.");
		}else if (bmi < 30) {
			System.out.println("비만입니다.");
		}else {
			System.out.println("고도비만입니다.");
		}
	}
}
