package com.epam.spring.theater.domain.aspects;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.epam.spring.theater.domain.User;

/**
 * DiscountAspect
 * 
 * count how many times each discount was given total and for specific user
 *
 */

@Aspect
@Service("discountAspect")
public class DiscountAspect {

	private final String interfaceDServ = "DiscountService";
	private final String interfaceDStrat = "DiscountStrategy";

	private Map<String, Integer> discountUserStat;
	private Map<String, Integer> discountStrategyStat;
	private String discount_Name;
	private double discount_Value;
	private String discount_User;

	public DiscountAspect() {
		discountUserStat = new HashMap<String, Integer>();
		discountStrategyStat = new HashMap<String, Integer>();
	}

	@Pointcut("execution(public double com.epam.spring.theater..getDiscount(com.epam.spring.theater.domain.User,..)) "
			+ "&& args(user,..)")
	private void getDiscountForEachStrategy(User user) {
	}

	@Before("getDiscountForEachStrategy(user)")
	private void getUser(JoinPoint jp, User user) {

		if (getInterfaceName(jp).equals(interfaceDServ)) {

			resetTempDiscountVariables();

			if (user == null)
				discount_User = "anonymous";
			else
				discount_User = user.getName() + "(" + user.getEmail() + ")";
		}
	}

	@AfterReturning(pointcut = "getDiscountForEachStrategy(user)", returning = "discount")
	private void countDiscountStatistics(JoinPoint jp, User user, double discount) throws Exception {

		if (discount > 0.0) {

			switch (getInterfaceName(jp)) {
			case interfaceDStrat:
				if (discount > discount_Value) {
					discount_Value = discount;
					discount_Name = getClassName(jp);
				}
				break;

			case interfaceDServ:
				addDiscount(discountUserStat, discount_User + " " + discount_Name);
				addDiscount(discountStrategyStat, discount_Name);
				break;

			default:
				throw new Exception(
						"Unexpected interface '" + getInterfaceName(jp) + "' in class '" + getClassName(jp) + "'");
			}
		}
	}

	private String getInterfaceName(JoinPoint jp) {
		return jp.getTarget().getClass().getInterfaces()[0].getSimpleName();
	}

	private String getClassName(JoinPoint jp) {
		return jp.getTarget().getClass().getSimpleName();
	}

	private void resetTempDiscountVariables() {
		discount_Name = "";
		discount_Value = 0.0;
		discount_User = "";
	}

	private void addDiscount(Map<String, Integer> map, String key) {
		if (map.containsKey(key))
			map.put(key, map.get(key) + 1);
		else
			map.put(key, 1);
	}

	public Map<String, Integer> getDiscountUserStatistics() {
		return discountUserStat;
	}
	
	public Map<String, Integer> getDiscountStrategyStatistics() {
		return discountStrategyStat;
	}
	
	public int getDiscountTotalStatistics() {
		int total = 0;
		for (Integer num : discountStrategyStat.values())
			total = total + num; 
		return total;
	}
}