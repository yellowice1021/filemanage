package com.scut.server.funtion;

//import java.awt.EventQueue;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.Scanner;
//
//import javax.swing.UIManager;
//
//public class VersionManagement {
//	public static boolean uploadFile(File file,String target){//传入一个文件对象和要上传的目标路径名
//		Path sPath=Paths.get(file.toString());//源文件路径
//		Path dPath=Paths.get(target+file.getName());//目标路径
//		if(new File(dPath.toString()).exists()){	//目标路径下已有同名文件
//			
//			//弹出界面提示
//			try {
//	            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//	        } catch (Throwable e) {
//	            e.printStackTrace();
//	        }
//	        EventQueue.invokeLater(new Runnable() {
//	            @Override
//	            public void run() {
//	                try {
//	                    view frame = new view();
//	                    frame.setVisible(true);
//	                } catch (Exception e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	        });
//			
//			
//			//询问用户选择
//			System.out.println("存在重名，请选择处理方式：0新版本，1重命名，2取消");
//			Scanner scanner=new Scanner(System.in);
//			
//			
//			String hisPath="F:/history/";//存放历史版本的路径
//			switch(scanner.nextInt()){
//			case 0:{//旧文件作为历史版本移走，新文件顺利上传
//				String[] str=file.getName().split("\\.");//取文件名和后缀，这里假设了文件名格式必须为x.y
//				try {
//					char add='A';
//					for(int i=0;i<26;i++){
//						if(!new File(hisPath+str[0]+'_'+(char)(add+i)+'.'+str[1]).exists()){
//							add=(char)('A'+i);
//							//System.out.println(add);
//							break;
//						}
//					}
//					Files.move(dPath,Paths.get("F:/history/"+str[0]+'_'+add+'.'+str[1]), //旧文件改名移走
//							StandardCopyOption.ATOMIC_MOVE);
//					Files.move(sPath,dPath,StandardCopyOption.ATOMIC_MOVE); //新文件按原名正常上传
//				} catch (IOException e) {
//					e.printStackTrace();
//					return false;
//				}
//				return true;
//			}
//			case 1:{//旧文件不变，新文件重命名
//				System.out.println("请为新上传的文件重命名");
//				String newname=scanner.next();
//				while(new File(target+newname).exists()){
//					System.out.println("该名称已存在，请指定其他文件名");
//					newname=scanner.next();
//				}
//				
//				dPath=Paths.get(target+newname);
//				try {
//					Files.move(sPath,dPath,StandardCopyOption.ATOMIC_MOVE);
//				} catch (IOException e) {
//					e.printStackTrace();
//					return false;
//				}
//				return true;
//			}
//			case 2:{//撤销移动操作
//				return false;
//			}
//			default://默认撤销移动操作
//				return false;
//			}
//			
//		}
//		else {//不存在重名情况，直接上传
//			try {
//				Files.move(sPath, dPath, StandardCopyOption.ATOMIC_MOVE);
//			} 
//			catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//		}
//		
//		return true;
//		
//		
//	}
//	
//	
//	
//	
//	public static void main(String args[]){
//		File file=new File("F:/file.txt");
//		try{
//			file.createNewFile();
//		}
//		catch(IOException e){
//			e.printStackTrace();
//		}
//		
//		
//		
//		VersionManagement.uploadFile(file,"F:/test/");
//	}
//}
