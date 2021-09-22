import org.apache.commons.lang3.ArrayUtils;
import java.util.Map;

public class CD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] str1 = {"a","c","c","d","b"};
		String[] str2 = {"a","b","c","","d"};
		
		/*for(String cor:correct) {
			cor.equalsIgnoreCase(anotherString)
		}*/
//		
		//compare strings
		if(str1.length!=str2.length){
			System.out.println("Strings are not same, lengths are different!!!");
			return;
		}
		
		boolean flg=true;
		int count=0;
		for(int loop=0; loop<str1.length;loop++){
			if(str2[loop].equalsIgnoreCase("")){
				count = count + 0;
			} else if(str1[loop].equalsIgnoreCase(str2[loop])){
				count = count + 4;
			} else if(!str1[loop].equalsIgnoreCase(str2[loop])){
				count = count -1;
			} 
		}
		
		System.out.println(count);
		
		
		
	}

}
