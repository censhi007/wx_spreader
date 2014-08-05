package com.weather.imp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.weather.inf.LThread;

/**
 * 写入文件
 * @author BUJUN
 *
 */
public class W2FTask extends LThread{
	private String html;
	private String pth;
	public W2FTask(String html,String pth){
		this.html=html;
		this.pth=pth;
	}
	@Override
	public void main() {
		File f=new File(pth);
		Writer w=null;
		try {
			if(!f.getParentFile().exists())f.getParentFile().mkdirs();
			if(f.exists())f.delete();
			f.createNewFile();
			w=new FileWriter(f);
			w.write(html);
			w.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(w!=null){
				try {
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void destroy() {
		
	}

}
