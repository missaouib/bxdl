package com.hzcf.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberFormat {
	/**
	 * 保留BigDecimal后两位小数(后两位直接舍掉)
	 * 
	 * @param number
	 * @return
	 */
	public static BigDecimal bigDecimalDownTwoDecimal(BigDecimal number) {
		DecimalFormat df = new DecimalFormat("#.0000");
		String format = df.format(number);
		
		number = new BigDecimal(format).setScale(2, RoundingMode.DOWN);
		return number;
	}

	/**
	 * 保留BigDecimal后两位小数(四舍五入)
	 * @param number
	 * @return
	 */
	public static BigDecimal bigDecimalDownTwoDecimalHalfUp(BigDecimal number) {
		DecimalFormat df = new DecimalFormat("#.0000");
		String format = df.format(number);

		number = new BigDecimal(format).setScale(2, RoundingMode.HALF_UP);
		return number;
	}

}
