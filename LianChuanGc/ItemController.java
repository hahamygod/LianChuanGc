package com.wisely.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.shop.entity.*;
import com.wisely.shop.services.*;

@Controller
@RequestMapping("/item/*")
public class ItemController{
	
	@Autowired
	PrintService print;
	
	@RequestMapping("/index")
	public String hello(){
		return "index";
	}
	
	@RequestMapping(value="/page/profile/{state}/{pageId}/{pageNum}",method=RequestMethod.GET,
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody Map<String,Number> getPage(@PathVariable int state,
			@PathVariable long pageId,@PathVariable int pageNum){
		
		//System.out.println(state);
		if(state==0){
			state=-1;
		}
		pageId=pageId+state*pageNum;
		if(pageId<0)
			pageId=0;
		else if(pageId>=20){
			pageId=pageId-pageNum;
		}
		Map<String,Number> pageInfo=new HashMap<String,Number>();
		pageInfo.put("pageid",pageId);
		pageInfo.put("pagenum", pageNum);
		return pageInfo;
	}	
	
	@RequestMapping(value="/page/profile")
	public String getOnePage(){
		return "page";
	}
	
	@RequestMapping("/show")
	public @ResponseBody String show(){
		
		return print.printInfo("It's an error.");
	}
	
	@RequestMapping(value="/getjson",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Page getjson(Page page){
		return new Page(page.pageid+=page.pagenum,page.pagenum);
	}
	
}
