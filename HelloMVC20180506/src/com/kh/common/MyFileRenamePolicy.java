package com.kh.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		File newFile  =null;
		//유니크한 파일명을 위해서 현재시각정보와 난수를 사용
		do {
			long currentTime = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd_HHmmssSSS");
			int rndNum = (int)(Math.random()*1000);
		

		//확장자명 가져오기

			String fname = oldFile.getName();
			String ext = "";
			int dot = fname.lastIndexOf(".");
			
			if(dot > -1 ) {
				ext = fname.substring(dot);
			}
			//새파일명 생성
			String newFname = sdf.format(new Date(currentTime)) + "_" + rndNum + ext ;
			
			//새파일 생성 
			newFile = new File(oldFile.getParent(), newFname);
			System.out.println("newFile@MyFileRenamePolicy="+newFile.getName());
			System.out.println("newFile.exist()="+newFile.exists());
		}while(!createNewFile(newFile));
		
		
		createNewFile(newFile);
		return newFile;
	}

	private boolean createNewFile(File newFile) {
		try {
			//파일이 존재하지 않으면, 파일생성후 true리턴
			//파일이 존재하면, 파일을 생성하지 않고 false리턴
		return newFile.createNewFile();
		} catch(IOException e) {
			return false;
		}
	}
	
	

}
