package com.genderfrender.autoServiceApi.utils;

import lombok.experimental.ExtensionMethod;

@ExtensionMethod({String.class, StringExtension.class})
public class StringExtension
{
	public static boolean isNotNullOrEmpty(String in) 
	{
		return in!=null && !in.isEmpty()&&!in.isBlank();
	}
}
