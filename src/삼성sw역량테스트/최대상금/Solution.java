package 삼성sw역량테스트.최대상금;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		int[][] data = new int[T][2];
		for(int i=0;i<T;i++) {
			data[i][0] = scan.nextInt();
			data[i][1] = scan.nextInt();
		}
		
		/*for(int i=0;i<T;i++) {
			System.out.print(String.format("%d %d", data[i][0], data[i][1]));
			System.out.println("");
		}*/
		
		//dfs using stack
		Stack<Data> stack = new Stack<>();
		
		
		for(int i=0;i<T;i++) {
			stack.push(new Data(0,0,String.valueOf(data[i][0])));
			//Data buf = stack.pop();
			//System.out.println(String.format("%d %d %s", buf.getCount(),buf.getIdx(),buf.getNum()));
			int res =0;
			while(!stack.isEmpty()) {
				Data bufData = stack.pop();
				int idx = bufData.getIdx();
				int count = bufData.getCount();
				String num = bufData.getNum();
				
				if(count == data[i][1]) {
					if(Integer.valueOf(num) > res) res = Integer.valueOf(num);
					continue;
				}
				
				//������ ��ȯ
				char[] ch = new char[num.length()];
				for(int j=0;j<num.length();j++) {
					ch[j] = num.charAt(j);
				}
		
				for(int j=0;j<num.length();j++) {
					for(int k=j+1;k<num.length();k++) {
						char tmp = ch[j];
						ch[j] = ch[k];
						ch[k] = tmp;
						num = "";
						for(int l=0;l<ch.length;l++) num += ch[l];
						stack.push(new Data(count+1,0,num));
						tmp = ch[j];
						ch[j] = ch[k];
						ch[k] = tmp;
					}
				}
				
			}
			System.out.println(String.format("#%d %d", i+1,res));
		}
		
	}

}
class Data{
	int idx;
	int count;
	String num;
	public Data(int count,int idx,String num) {
		this.count = count;
		this.idx = idx;
		this.num = num;
	}
	
	public int getCount() {
		return count;
	}
	public int getIdx() {
		return idx;
	}
	public String getNum() {
		return num;
	}
}