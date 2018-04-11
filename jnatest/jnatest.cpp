// jnatest.cpp : 定义 DLL 应用程序的导出函数。
//

#include "stdafx.h"
#include<iostream>
struct UserStruct
{
	long id;
	char* name;
	int age;
};
struct CompanyStruct2 {
	long id;
	char* name;
	UserStruct* users[100];
	int count;
};
#define MYLIBAPI extern "C" __declspec( dllexport )
MYLIBAPI void sayCompanyStruct2(CompanyStruct2* CompanyStruct2);
MYLIBAPI void sayUser(UserStruct* pUserStruct);
MYLIBAPI void sayUser2(UserStruct** ppUserStruct);
MYLIBAPI void sayUser3(UserStruct*** pppUserStruct);
MYLIBAPI void say(wchar_t* pValue);
MYLIBAPI void say1(int i);



void sayUser(UserStruct* pUserStruct) {
	std::cout << (*pUserStruct).age<<"\n" << (*pUserStruct).id <<"\n"<<pUserStruct->name << std::endl;
	std::wcout.imbue(std::locale("chs"));
	std::wcout << L"ID:" << pUserStruct->id << std::endl;
	std::cout << "姓名：" << pUserStruct->name << std::endl;
	std::wcout << L"年龄：" << pUserStruct->age << std::endl;
}
void sayUser2(UserStruct** ppUserStruct) {
	UserStruct* pUserStruct = *ppUserStruct;
	sayUser(pUserStruct);
}
void sayUser3(UserStruct*** pppUserStruct) {
	UserStruct** ppUserStruct = *pppUserStruct;
	sayUser2(ppUserStruct);
}
void sayCompanyStruct2(CompanyStruct2* CompanyStruct2){
	std::cout << "liushuo" << std::endl;
	//std::cout << (*CompanyStruct2).count << (*CompanyStruct2).id << (*CompanyStruct2).name  << std::endl;
}
void say(wchar_t* pValue) {
	std::wcout.imbue(std::locale("chs"));
	std::wcout << L"原生函数说：" << pValue << std::endl;
}
void say1(int i) {
	std::cout << i << std::endl;
}


