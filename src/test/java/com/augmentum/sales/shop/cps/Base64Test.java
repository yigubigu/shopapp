package com.augmentum.sales.shop.cps;

import java.util.Base64;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Base64Test {

	@Test
	public void verifyBase64() {
		String str = "1000001";
		byte[]   bytesEncoded = Base64.getEncoder().encode(str .getBytes());
		String s2 = Base64.getEncoder().encodeToString(str.getBytes());
		System.out.println("new String " + new String(bytesEncoded));
		String s3 = new String( Base64.getDecoder().decode(s2));
		Assert.assertEquals(str, s3);

		
	}
}
