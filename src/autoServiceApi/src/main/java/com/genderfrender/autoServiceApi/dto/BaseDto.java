package com.genderfrender.autoServiceApi.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseDto<T>
{
	
	private T object;
	
	private int status;
	

	public String getErrorText()
	{
		return errorText;
	}

	public void setErrorText(String errorText)
	{
		this.errorText = errorText;
	}

	private String errorText;
	
	public BaseDto(T object, HttpStatus status)
	{
		this.object = object;
		this.status = status.value();
	}
	
	public BaseDto(HttpStatus status, String errorText)
	{
		this.status = status.value();
		this.errorText = errorText;
	}
	public T getObject()
	{
		return object;
	}

	public void setObject(T object)
	{
		this.object = object;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(HttpStatus status)
	{
		this.status = status.value();
	}
	
	public static <T>  BaseDto<T> generateNormal(T object1){
		return new BaseDto<T>(object1,HttpStatus.OK);
	}

	public static <T>  BaseDto<T> generateWithStatus(T object1, HttpStatus status){
		return new BaseDto<T>(object1,status);
	}

	public static <T>  BaseDto<T> generateWithStatus(HttpStatus status,String errorText){
		return new BaseDto<T>(status,errorText);
	}
}
