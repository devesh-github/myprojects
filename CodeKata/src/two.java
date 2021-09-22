
public class two {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int sum = 84848;
		for(int i = 0,j=sum;i<Integer.MAX_VALUE;i++,j--) {
			//System.out.println(String.valueOf(i));
			//System.out.println(String.valueOf(i).equalsIgnoreCase("4"));
			if(!String.valueOf(i).contains("4") &&
					!String.valueOf(j).contains("4")){
				if(i+j == sum) {
					System.out.println(i);
					System.out.println(j);
					break;
				}
			}
		}
		
		
	}

}
