
import myScanner.MS;

public class MSTestExapleUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Random value between 120 and 180 in full tens = " +
								MS.getRandomFDouble(120, 180, -1));
		System.out.println(MS.getString("Press Enter for random name", MS.STRINGS.NAME));
		

	}

}
