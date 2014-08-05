package com.util;

public class Strings {
	/**
	 * 计算s1和s2直接的编辑距离<br/>
	 * 所谓编辑距离：即从s1变换到s2所需要的操作步骤<br/>
	 * 使用动态规划算法
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int editDs_dg(String s1,String s2){
		int l1=s1.length();
		int l2=s2.length();
		char[] cs1=s1.toCharArray();
		char[] cs2=s2.toCharArray();
		int[][] ed=new int[l1+1][l2+1];
		//用于计算s2是0长度时，s1为0~l1情况下的编辑距离。
		for(int i=0;i<=l1;i++){
			ed[i][0]=i;
		}
		//用于计算s1为0长度时，s2为0~l2情况下的编辑距离
		for(int i=0;i<=l2;i++){
			ed[0][i]=i;
		}
		//从1~l1,遍历。
		for(int i=1;i<=l1;i++){
			//用于计算s1为i长度时，s2为1~l2情况下的编辑距离
			for(int j=1;j<=l2;j++){
				//①减少s1的长度，然后从s1减去一位 的情况下变到s2。
				int t1=ed[i-1][j]+1;
				//①减少s2的长度，然后从s1变化到s2减去一位后的字符串
				int t2=ed[i][j-1]+1;
				//直接变换s1和s2最后一位字符。如果最后一位字符一致，结果为s1减1变到s2减1
				//变换变换最后一位所需要的操作步骤是 1或者0
				int t3=ed[i-1][j-1]+(cs1[i-1]==cs2[j-1] ? 0 :1 );
				//从上面三个结果中选择最小的结果作为本次变换需要的操作步骤
				int tmp=t1<t2 ? t1 : t2;				
					tmp=tmp < t3 ? tmp : t3;
				ed[i][j]=tmp;
				
			}
		}		
		for(int i=0;i<=l1;i++){
			for(int j=0;j<=l2;j++){
				System.out.print(ed[i][j]+" " );
			}
			System.out.println();
		}
		return ed[l1][l2];
	}
	
	public static void main(String[] args){
		long s=System.currentTimeMillis();
		System.out.println("从 printn 变换到pringtl 一共需要"+editDs_dg("printn","pringtl")+"步");
		System.out.println("本次计算一共耗时："+(System.currentTimeMillis()-s));
	}
}
