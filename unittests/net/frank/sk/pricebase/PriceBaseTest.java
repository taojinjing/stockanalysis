package net.frank.sk.pricebase;

import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PriceBaseTest {
	@Test
	public void testNoMatch() {
		Double[] test = { 9.07, 9.13 };
		PriceBase pb = new PriceBase();
		int index = pb.findRange(Arrays.asList(test), 1);
		assertEquals(PriceBase.NO_MATCH, index);
	}

	@Test
	public void testHitBuy() {
		System.out.println("Test hit buy...");
		Double[] test = { 8.01, 8.15, 8.23, 8.33, 8.49, 8.54, 8.68, 8.88, 9.05,
				9.13, 10.20, 11.99 };
		assertEquals(9.13, test[9].doubleValue(), 0.01);
		int index = this.runTest(test, 9);
		assertEquals(2, index);
	}

	@Test
	public void testHitSale() {
		System.out.println("Test hit sale...");
		Double[] test = { 10.33, 10.25, 10.13, 10.06, 9.88, 9.74, 9.68, 9.54,
				9.25, 9.13, 9.77, 8.10 };
		assertEquals(9.13, test[9].doubleValue(), 0.01);
		int index = this.runTest(test, 9);
		assertEquals(-1, index);
	}

	@Test
	public void testStillAtBottom() {
		System.out.println("Test still at bottom...");
		Double[] test = {10.50, 9.42, 9.13, 8.94, 8.85, 8.31, 9.01, 9.13, 9.45};
		assertEquals(9.13, test[7].doubleValue(), 0.01);
		int index = this.runTest(test, 7);
		assertEquals(PriceBase.BOTTON_OR_TOP, index);
	}
	
	@Test
	public void testStillAtTop() {
		System.out.println("Test still at bottom...");
		Double[] test = {8.20, 8.40, 8.85, 8.97, 9.13, 9.20, 9.42, 9.13, 8.20};
		assertEquals(9.13, test[7].doubleValue(), 0.01);
		int index = this.runTest(test, 7);
		assertEquals(PriceBase.BOTTON_OR_TOP, index);
	}

	private int runTest(Double[] test, int i) {
		PriceBase pb = new PriceBase();
		return pb.findRange(Arrays.asList(test), i);
	}
}
