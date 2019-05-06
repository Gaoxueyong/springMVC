package springMvc;

import org.apache.log4j.Logger;

import java.io.*;

public class Test {
	private static Logger logger = Logger.getLogger(Test.class);


	public static void main(String[] args) {


		String path = "D:\\bigFile\\WU_FILE_0";

		File file = new File(path);
		File[] files = file.listFiles();

		int chunks = 70;
		String realFileName = "hadoop-2.9.2.tar.gz";
		try {
			FileOutputStream  fileOutputStream = new FileOutputStream(path+File.separator+realFileName);
			byte[] buf = new byte[1024];
			for(int i=0;i<70;i++){
				File tempFile = new File(path+File.separator+i+"_"+realFileName);
				InputStream inputStream = new FileInputStream(tempFile);
				int len = 0;
				while((len=inputStream.read(buf))!=-1){
					fileOutputStream.write(buf,0,len);
				}
				inputStream.close();
				//删除临时文件
//				tempFile.delete();
			}




		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}


}
