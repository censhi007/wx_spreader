package com.weather.pojo;

import java.util.Arrays;

public class Inct implements java.io.Serializable,Comparable<Inct>{

	
	private static final long serialVersionUID = 5952570238699656617L;
	public Inct(){}
	public Inct(int hash){this.hash=hash;}
	private int hash=0;
	private String[] ts = new String[1];
	private int i=0;
	private int[][] index = new int[1][2];
	public boolean eq(int hash){
		return this.hash == hash;
	}
	public int hash(){
		return hash;
	}
	
	public void addData(String s){
		if(i < ts.length){
			ts[i++]=s;
		}else{
			int l = i == 1 ? 2 : (i+i/2);
			ts = Arrays.copyOf(ts, l);
			index = Arrays.copyOf(index,l);
			
			ts[i++]=s;
		}
	}
	public void addData(String s,int idx){
		addData(s);
		putIndex(idx,i-1);
	}
	
	public void putData(String s,int idx){
		for(int g=0;g<i;g++){
			if(ts[g].equals(s)){
				putIndex(idx,g);
				return;
			}
		}
		addData(s,idx);
	}
	
	public void putIndex(int idx,int i){
		int [] ix = index[i];
		if(ix==null){
			ix=index[i] = new int[2];
			
			int j=0;
			for(String s:ts){
				System.out.print(s+"["+s.hashCode()+"]:");
				int []ixx=index[j++];
				for(int ii:ixx){
					System.out.print(ii+",");
				}
				System.out.println();
			}
		}
		int p = ix[0];
		if(p < ix.length-1){
			p++;
			ix[p]=idx;
			ix[0]=p;
		}else{
			int l = p == 1 ? 2 : (p+p/2);
			l++;
			ix=Arrays.copyOf(ix,l);
			p++;
			ix[p]=idx;
			ix[0]=p;
			index[i]=ix;
		}
	}
	/**
	 * if o &gt; this then 1 <br/>
	 * else if o &lt; this then -1<br/>
	 * else 0
	 */
	@Override
	public int compareTo(Inct o) {		
		return o.hash == hash ? 0 : o.hash > hash ? 1 : -1;
	}
	public String toString(){
		return hash+"";
	}

	public int[] get(String s){
		int i=0;
		for(String sx:ts){
			if(s.equals(sx)){
				return Arrays.copyOfRange(index[i], 1, index[i].length);
				//return index[i];
			}
			i++;
		}
		return null;
	}
}
