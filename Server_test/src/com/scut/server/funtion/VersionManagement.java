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
//	public static boolean uploadFile(File file,String target){//����һ���ļ������Ҫ�ϴ���Ŀ��·����
//		Path sPath=Paths.get(file.toString());//Դ�ļ�·��
//		Path dPath=Paths.get(target+file.getName());//Ŀ��·��
//		if(new File(dPath.toString()).exists()){	//Ŀ��·��������ͬ���ļ�
//			
//			//����������ʾ
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
//			//ѯ���û�ѡ��
//			System.out.println("������������ѡ����ʽ��0�°汾��1��������2ȡ��");
//			Scanner scanner=new Scanner(System.in);
//			
//			
//			String hisPath="F:/history/";//�����ʷ�汾��·��
//			switch(scanner.nextInt()){
//			case 0:{//���ļ���Ϊ��ʷ�汾���ߣ����ļ�˳���ϴ�
//				String[] str=file.getName().split("\\.");//ȡ�ļ����ͺ�׺������������ļ�����ʽ����Ϊx.y
//				try {
//					char add='A';
//					for(int i=0;i<26;i++){
//						if(!new File(hisPath+str[0]+'_'+(char)(add+i)+'.'+str[1]).exists()){
//							add=(char)('A'+i);
//							//System.out.println(add);
//							break;
//						}
//					}
//					Files.move(dPath,Paths.get("F:/history/"+str[0]+'_'+add+'.'+str[1]), //���ļ���������
//							StandardCopyOption.ATOMIC_MOVE);
//					Files.move(sPath,dPath,StandardCopyOption.ATOMIC_MOVE); //���ļ���ԭ�������ϴ�
//				} catch (IOException e) {
//					e.printStackTrace();
//					return false;
//				}
//				return true;
//			}
//			case 1:{//���ļ����䣬���ļ�������
//				System.out.println("��Ϊ���ϴ����ļ�������");
//				String newname=scanner.next();
//				while(new File(target+newname).exists()){
//					System.out.println("�������Ѵ��ڣ���ָ�������ļ���");
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
//			case 2:{//�����ƶ�����
//				return false;
//			}
//			default://Ĭ�ϳ����ƶ�����
//				return false;
//			}
//			
//		}
//		else {//���������������ֱ���ϴ�
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
