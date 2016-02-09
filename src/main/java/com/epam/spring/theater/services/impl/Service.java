package com.epam.spring.theater.services.impl;

public class Service {

	protected boolean isNull(Object obj, String objName) {

		if (obj == null) {
			System.out.println(objName + " is null");
			return true;
		}

		return false;
	}
}
