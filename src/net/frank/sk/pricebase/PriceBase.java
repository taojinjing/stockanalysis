package net.frank.sk.pricebase;

import java.util.List;

/**
 * 计算收盘价到上次相同收盘区间 若高于最低点10%，为买入信号 若低于最高点10%，为卖出信号
 * 
 * @author ftao
 * 
 */
public class PriceBase {
	private float RANGE_RATIO = 0.10f;

	protected static int NO_MATCH = -99999;

	protected static int BOTTON_OR_TOP = 99999;

	public PriceBase() {

	}

	public PriceBase(float range_ratio) {
		this.RANGE_RATIO = range_ratio;
	}

	public int findRange(List<Double> list, int index) {
		int returnindex = NO_MATCH;
		double price = list.get(index);
		boolean bottom = false;
		boolean top = false;
		for (int i = index - 1; i > -1; i--) {
			double d = list.get(i);
			if (d * (1 + RANGE_RATIO * 8) < price
					|| d * (1 - RANGE_RATIO * 3) > price) {
				// negative for sale
				if (d > price) {
					return -1;
				} else {
					return 1;
				}
			}

			if (d * 1.01 < price) {
				if (top) {
					return BOTTON_OR_TOP;
				}
			}

			if (d * 1.03 < price) {
				if (top) {
					// return BOTTON_OR_TOP;
				} else {
					bottom = true;
				}
			}

			if (d * 0.99 > price) {
				if (bottom) {
					return BOTTON_OR_TOP;
				}
			}
			if (d * 0.97 > price) {
				if (bottom) {
					// return BOTTON_OR_TOP;
				} else {
					top = true;
				}
			}
		}
		return returnindex;
	}
}
