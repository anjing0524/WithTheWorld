package testjna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.ptr.PointerByReference;

import testjna.testdll.TestDll1.CompanyStruct2;
import testjna.testdll.TestDll1.Userstruct;


/**
 * @author liushuo524
 *
 */

public class testdll {
	public interface TestDll1 extends Library {//接口
		TestDll1 INSTANCE = (TestDll1)Native.loadLibrary("jnatest", TestDll1.class);//加载dll
	
		
		//结构体
		public static class Userstruct extends Structure{
			public NativeLong id;
			public String name;
			public int age;
			
			public static class Byreference extends Userstruct implements Structure.ByReference{
			//结构体指针	
			}
			public static class Byvalue extends Userstruct implements Structure.ByValue{
			//结构体本身
			}
		}
		//指针
		public void sayUser(Userstruct.ByReference struct);
		public void sayUser2(PointerByReference ppUserStruct);
		public void sayUser3(Pointer pppUserStruct);
		public void sayUser(Userstruct.Byvalue struct);
		//包含结构体对象的指针数组
		public static class CompanyStruct2 extends Structure{
			public NativeLong id;
			public String name;
			public Userstruct.Byreference users[]=new Userstruct.Byreference[100];
			public int count;
			
			public static class ByReference extends CompanyStruct2 implements Structure.ByReference{
			
			}
			public static class ByValue extends CompanyStruct2 implements Structure.ByValue{
			
			}
		}
		public void sayCompanyStruct2(CompanyStruct2.ByReference struct);
		//public void sayCompanyStruct2(CompanyStruct2.ByValue struct);
		public void say(WString value);
		public void say1(int i);
		}
	
	public static void main(String[] args) {
		int i= 5;
		TestDll1.INSTANCE.say1(i);
		System.out.println("Java 输出。");
		
		TestDll1.INSTANCE.say(new WString("Hello World!"));
		System.out.println("Java 输出。");
		//用结构体指针测试
		Userstruct.Byreference user = new Userstruct.Byreference();
		user.id=new NativeLong(100);
		user.age=30;
		user.name=new String("刘硕");
		TestDll1.INSTANCE.sayUser(user);
		System.out.println("------我是分割线--------");
		//用结构体本身测试
		Userstruct.Byvalue user1 = new Userstruct.Byvalue();
		user1.id=new NativeLong(10);
		user1.age=30;
		user1.name=new String("刘硕");
		TestDll1.INSTANCE.sayUser(user1);
		
		
		//------指针数组
		CompanyStruct2.ByReference companyStruct2=new CompanyStruct2.ByReference();
		companyStruct2.id=new NativeLong(2);
		companyStruct2.name=new String("Yahoo");
		companyStruct2.count=10;
			Userstruct.Byreference pUserStruct=new Userstruct.Byreference();
			pUserStruct.id=new NativeLong(90);
			pUserStruct.age=99;
			pUserStruct.name=new String("刘硕");
			pUserStruct.write();//固定到内存中
		for(int j=0;j<companyStruct2.count;j++){
			companyStruct2.users[j]=pUserStruct;
			Userstruct.Byreference p=new Userstruct.Byreference();
			p=companyStruct2.users[j];
			System.out.println(p.age);
			}
		TestDll1.INSTANCE.sayCompanyStruct2(companyStruct2);
		
		//指针getPoint() PointByReference
		Userstruct pUserStruct2=new Userstruct();
		pUserStruct2.id=new NativeLong(90);
		pUserStruct2.age=99;
		pUserStruct2.name=new String("刘硕");
		pUserStruct2.write();
		System.out.println("-------分割线----------");
		System.out.println("使用pUserStruct!!!!");
		TestDll1.INSTANCE.sayUser(pUserStruct);
		Pointer pPointer=pUserStruct2.getPointer();
		PointerByReference ppUserStruct=new PointerByReference(pPointer);
		System.out.println("使用ppUserStruct!!!!");
		TestDll1.INSTANCE.sayUser2(ppUserStruct);
		System.out.println("使用pppUserStruct!!!!");
		Pointer ppUsesStruct = ppUserStruct.getPointer();
		PointerByReference pppUserStruct=new PointerByReference(ppUsesStruct);
		TestDll1.INSTANCE.sayUser3(pppUserStruct.getPointer());
		}
		

}
