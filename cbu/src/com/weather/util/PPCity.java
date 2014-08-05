package com.weather.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.weather.pojo.BTree;
import com.weather.pojo.City;
import com.weather.pojo.Hits;
import com.weather.pojo.Inct;

public class PPCity {
	private static List<City> cL=new ArrayList<City>();
	public static void parse(String pth){
		File f=new File(pth);
		if(f.exists()){
			FileReader fr=null;
			BufferedReader read=null;
			cL.clear();
			try {
				fr= new FileReader(f);
				read=new BufferedReader(fr);
				parse(read);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void parse(BufferedReader read) throws IOException{
		String line = read.readLine();
		Pattern pt=Pattern.compile("([\u4E00-\u9FA5]+),(\\d+)");
		int i=1;
		while(line!=null){
			Matcher m=pt.matcher(line);
			while(m.find()){
				String n=m.group(1);
				String cod=m.group(2);	
				City c= new City();
				c.setCityName(n);
				try{
					c.setCode(Long.parseLong(cod));
				}catch(Exception ex){
					
				}
				cL.add(c);					
				handle(c,i++);
			}
			
			line=read.readLine();
		}
	}
	private static BTree<Inct> iTree;
	public static void handle(City ci,int index){
		String n=ci.getCityName();
		String[] ss =cut(n,2);
		for(String s:ss){
			int hash =s.hashCode();
			Inct ih=new Inct(hash);
			if(iTree==null){
				iTree = new BTree<Inct>();
				ih.addData(s, index);
				iTree.setData(ih);
			}else{
				Inct i = iTree.search(ih);
				if(i==null){
					ih.addData(s, index);
					iTree.put(ih);
				}else{
					i.putData(s, index);
				}
			}
		}
	}

	
	/**
	 * 切词
	 * @param s
	 * @param i
	 * @return
	 */
	private static String[] cut(String s,int i){
		char[] cs = s.toCharArray();		
		String [] rs=new String[cs.length-i+1];
		for(int d=0;d<rs.length;d++){
			rs[d]=new String(cs,d,i);
		}
		return rs;
	}
	
	
	public static void main(String[] args){
//		parse("D:/my work/cbu/src/com/weather/res/aCitys.txt");
//		ObjectOutputStream os=null;
//		
//		try {
//			File f = new File("D:/my work/cbu/src/com/weather/res/index.inx");
//			if(!f.exists())f.createNewFile();
//			os= new ObjectOutputStream(new FileOutputStream(f));
//			os.writeObject(iTree);
//			os.flush();
//			//os.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			if(os!=null){
//				try {
//					os.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		
		//initIndex(WTUtil.getCPth()+"com/weather/res/aCitys.txt");
		List<City> cl=search("浙江杭州");
		for(City c:cl){
			System.out.println(c);
		}
	}
	
	static void initIndex(String cpth){
		if(cpth==null){
			InputStream is =null ;
			InputStreamReader r=null;
			BufferedReader read=null;
			try{			
				is = PPCity.class.getClassLoader().getResourceAsStream("com/weather/res/aCitys.txt");
				r=new InputStreamReader(is);
				read =new BufferedReader(r);
				parse(read);				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(is!=null){
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(r!=null){
					try {
						r.close();
						read.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{			
			parse(cpth);//
		}
		ObjectOutputStream os=null;
		File f = new File(WTUtil.getDPth(),"index.inx");
		File df = new File(WTUtil.getDPth(),"data.inx");
		try{
			if(f.exists())
				f.delete();
			f.createNewFile();
			if(df.exists())
				df.delete();
			df.createNewFile();
			os= new ObjectOutputStream(new FileOutputStream(f));
			os.writeObject(PPCity.iTree);
			os.flush();
			os.close();
			os= new ObjectOutputStream(new FileOutputStream(df));
			os.writeObject(PPCity.cL);
			os.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void reloadIndex(){
		File f = new File(WTUtil.getDPth(),"index.inx");
		File df = new File(WTUtil.getDPth(),"data.inx");
		iTree=null;
		if(!f.exists())return;
		ObjectInputStream oi = null;
		try{
			oi=new ObjectInputStream(new FileInputStream(f));
			iTree=(BTree<Inct>)oi.readObject();
			if(df.exists()){				
				oi.close();			
				oi=new ObjectInputStream(new FileInputStream(df));
				cL=(List<City>)oi.readObject();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(oi!=null){
				try {
					oi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<City> search(String str){
		if(iTree==null)reloadIndex();
		if(iTree==null)initIndex(null);
		String ss[] = cut(str,2);
		Map<Integer,Hits<City>> mh=new HashMap<Integer,Hits<City>>();
		for(String s:ss){
			Inct it = iTree.search(new Inct(s.hashCode()));
			if(it!=null){
				int [] ix = it.get(s);
				if(ix!=null)
				for(int i:ix){	
					if(i==0)continue;
					Hits<City> ht=mh.get(i);
					if(ht==null){
						int index=i-1;
						mh.put(i,new Hits<City>(index,cL.get(index)));
					}else{
						ht.hit();
					}
				}
			}
		}
		Hits<City>[] hs=mh.values().toArray(new Hits[0]);
		Arrays.sort(hs);
		if(hs.length>0){
			Hits<City> it=hs[0];			
			City ic = it.getStr();
			if(str.equals(ic.getCityName())){
				List<City> icL=new ArrayList<City>();
				icL.add(ic);
				return icL;
			}
			List<City> icL=new ArrayList<City>();
			int s=0;
			for(Hits<City> h : hs){
				if(s++ >=5)break;				
				icL.add(h.getStr());
			}
			return icL;
		}
		return null;
	}
}
