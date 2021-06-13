package entities;
import java.io.Serializable;

/**
 * This class represnts the Range (0 - 54), (55 - 64), (65 - 69), (70 - 74), (75 - 79), (80 - 84), (85 - 89), (90 - 94), (95 - 100)
 * @author Saher
 *
 */
public class Range  implements Serializable{
	private int Ramge1, Range2,Range3,Range4,Range5,Range6,Range7,Range8,Range9;

	/**
	 * Construction
	 * @param ramge1 (0 - 54)
	 * @param range2 (55 - 64)
	 * @param range3 (65 - 69)
	 * @param range4 (70 - 74)
	 * @param range5 (75 - 79)
	 * @param range6 (80 - 84)
	 * @param range7 (85 - 89)
	 * @param range8 (90 - 94)
	 * @param range9 (95 - 100)
	 */
	public Range(int ramge1, int range2, int range3, int range4, int range5, int range6, int range7, int range8,
			int range9) {
		super();
		Ramge1 = ramge1;
		Range2 = range2;
		Range3 = range3;
		Range4 = range4;
		Range5 = range5;
		Range6 = range6;
		Range7 = range7;
		Range8 = range8;
		Range9 = range9;
	}
	
	/**
	 * 
	 * @return Ramge1 (0 - 54)
	 */
	public int getRamge1() {
		return Ramge1;
	}

	/**
	 * 
	 * @param ramge1
	 */
	public void setRamge1(int ramge1) {
		Ramge1 = ramge1;
	}

	/**
	 * 
	 * @return Range2 (55 - 64)
	 */
	public int getRange2() {
		return Range2;
	}

	/**
	 * 
	 * @param range2
	 */
	public void setRange2(int range2) {
		Range2 = range2;
	}

	/**
	 * 
	 * @return Range3 (65 - 69)
	 */
	public int getRange3() {
		return Range3;
	}

	/**
	 * 
	 * @param range3
	 */
	public void setRange3(int range3) {
		Range3 = range3;
	}

	/**
	 * 
	 * @return Range4 (70 - 74)
	 */
	public int getRange4() {
		return Range4;
	}

	/**
	 * 
	 * @param range4
	 */
	public void setRange4(int range4) {
		Range4 = range4;
	}

	/**
	 * 
	 * @return Range5 (75 - 79)
	 */
	public int getRange5() {
		return Range5;
	}

	/**
	 * 
	 * @param range5
	 */
	public void setRange5(int range5) {
		Range5 = range5;
	}

	/**
	 * 
	 * @return Range6 (80 - 84)
	 */
	public int getRange6() {
		return Range6;
	}

	/**
	 * 
	 * @param range6
	 */
	public void setRange6(int range6) {
		Range6 = range6;
	}

	/**
	 * 
	 * @return Range7 (85 - 89)
	 */
	public int getRange7() {
		return Range7;
	}

	/**
	 * 
	 * @param range7
	 */
	public void setRange7(int range7) {
		Range7 = range7;
	}

	/**
	 * 
	 * @return Range8 (90 - 94)
	 */
	public int getRange8() {
		return Range8;
	}

	/**
	 * 
	 * @param range8
	 */
	public void setRange8(int range8) {
		Range8 = range8;
	}
    /**
     * 
     * @return Range9 ( 95 - 100)
    */
	public int getRange9() {
		return Range9;
	}

	/**
	 * 
	 * @param range9
	 */
	public void setRange9(int range9) {
		Range9 = range9;
	}
}
