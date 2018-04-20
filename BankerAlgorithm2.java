package cn.just.thread.experiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class BankerAlgorithm2 {
	static int allo;
	static int num;			//��Դ������
	public static List<Resource> resourcesList=new ArrayList<Resource>();
	public static List<Work> worksList=new ArrayList<Work>();
	public static List<Work> success=new ArrayList<Work>();
	
	public static class Work{
		public String name;
		public List<Map<String,Integer>> need=new ArrayList<Map<String,Integer>>();
		public List<Map<String,Integer>> allocation=new ArrayList<Map<String,Integer>>();
		public boolean finish;
		public Work() {
		}
	}
	/**
	 * ��Դ��
	 * @author shinelon
	 *
	 */
	public static class Resource{
		public int sum;		//��Դ������
		public int available;
		public String name;
		public Resource(int sum, String name,int available) {
			super();
			this.sum = sum;				//��Դ����
			this.name = name;			//��Դ����
			this.available=available;	//��Դ������
		}
	}
	
	private static void run() {
		int remain;			//��Դʣ����
		Iterator<Work> it_work=worksList.iterator();
		Iterator<Resource> it_resource=resourcesList.iterator();
		if(it_work.hasNext()){
			int i=0;
			Work work=it_work.next();
			while(it_resource.hasNext()){		//������Դ����,����п�����Դ����ʱ���Է���һ��
					if(work.need.size()==0){
						work.finish=true;
					}
				Resource resource=it_resource.next();
				if(!work.finish){		//��ǰ�������û����ɣ�����з���
						if(resource.available>0){		//���������Դ���п�����Դ
							int temp_avali=resource.available;
							if(temp_avali--<=0){
								System.out.println("��ǰ״̬Ϊ����ȫ״̬�����������");
								break;
							}
							if(work.need.get(i).get("��Դ"+i)!=null){
							int allo_temp=work.allocation.get(i).get("��Դ"+i);
							work.allocation.get(i).put("��Դ"+i, allo_temp++);
							int need_temp=work.need.get(i).get("��Դ"+i);
							work.need.get(i).put("��Դ"+i, need_temp--);
							if(need_temp==0){
//								work.need.remove(i);		//��ǰ��Դ�Ѿ�����Ҫ�˾ͽ����Ƴ�
								work.need.get(i).put("��Դ"+i, 0);
							}
							}else{
								work.allocation.get(i).put("��Դ"+i, 1);
							}
							resource.available--;
						}else{
							System.out.println("����������");
							System.exit(0);
						}
						int k=0;
						for(Map m:work.need){
							if(m.get("��Դ"+i)!=null)
								break;
							k++;
							if(k==m.size()){
								work.finish=true;
							}
						}
				}else{
//					work.finish=true;		//��ʶΪ���״̬
					success.add(work);		//����װ״̬��˳�����һ��������
					it_work.remove(); 		//��ִ����ϵĽ����Ƴ���������
					break;
				}
				i++;
			}
		}
		
	}
	public static void main(String[] args) {
		System.out.println("��������Դ�������Ŀ��");
		
		int resourceNum = 0;
		int threadNum;
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		do{
		num=scanner.nextInt(5);			//1~5����Դ
		if(num==0){
			System.out.println("���������ĿΪ0������������");
		}
		}while(num==0);
		for(int i=0;i<num;i++){
			System.out.println("�������"+i+"�����Դ������");
			resourceNum=scanner.nextInt(20);
			resourcesList.add(new Resource(resourceNum, "��Դ"+i,resourceNum));
		}
		System.out.println("�������������");
		threadNum=scanner.nextInt();	
			
		for(int i=0;i<threadNum;i++){
			Work work=new Work();
			for(int j=0;j<num;j++){
			System.out.println("���������"+i+"�Ѿ������"+j+"��Դ����Ŀ�����ܳ�����Դ������");
			int allo=scanner.nextInt();
			System.out.println("���������"+i+"����Ҫ��"+j+"��Դ����Ŀ�����ܳ�����Դ������");
			int ne=scanner.nextInt();
			Map<String,Integer> need_map=new HashMap<String,Integer>();
			need_map.put("��Դ"+j, ne);
			work.need.add(need_map);
			Map<String,Integer> allo_map=new HashMap<String,Integer>();
			allo_map.put("��Դ"+j, allo);
			work.allocation.add(allo_map);
			}
			work.finish=false;			//��ʼ��Ϊδ���״̬
			work.name="����"+i;
			worksList.add(work);
		}
		//������̶����л��н���û�з������Դ
		while(!worksList.isEmpty()){
			run();
		}
		System.out.println();
		for(int i=0;i<success.size();i++){
			System.out.println(success.get(i).name);
		}
	}
}
