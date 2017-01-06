package com.sp.utils;
import java.util.ArrayList;
import java.util.List;




/**
 * 
 * @ClassName Page
 * @Description   分页工具类  支持mysql （oracle的以后再补充）
 * @author:Gaoxueyong  http://blog.csdn.net/fenfenguai
 * @Date 2016年11月16日 下午5:32:25
 * @version 1.0.0
 * @param <T>
 * @param <T>
 * 
 * 
 * 
 * 上一页   1 ... 4 5 6 7 ... 10 下一页
 * 
 */
public class Page<T> {
	
	private int firstNo=1;//页码
	private int currentNo=1;//当前页吗     (已知)
	private int lastNo;//尾页码
	private int pageSize = 10;//默认   (已知)
	private int totalNum;//总数据条数      (已知)
	private int length = 8;//显示页面长度
	private int slider = 1;// 前后显示页面长度
	
	private int pageNumStart;
	private int pageNumEnd;
	
	private boolean beforeMore = false;//是否显示前省略号
	private boolean afterMore = false;//是否显示后省略号
	
	private String pageInfo = "";//分页信息
	
	private int startSize;
	
	
	private List<String> listPageNum = new ArrayList<String>();
	private List<T> list = new ArrayList<T>();
	
 
	/*public Page(List<T> list ,Integer pageSize,Integer totalNum,Integer currentNo) {
		this.initialize(list ,pageSize, totalNum, currentNo);
	}*/


	/**
	 * 
	 * @Description 初始化参数
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	 * Create at: 2016年11月17日 上午10:48:38
	 */
	//public void initialize(List<T> list,Integer pageSize,Integer totalNum,Integer currentNo){
	public void initialize(){
		this.pageSize = this.pageSize<1?10:this.pageSize;
		this.currentNo = this.currentNo<1?1:this.currentNo;
		this.lastNo = this.totalNum/this.pageSize;
		if(this.totalNum%this.pageSize!=0){
			this.lastNo++;
		}
		
		
		if(this.lastNo>this.length+2){
			//大于分开显示
			if(this.currentNo< (this.length-2)){
				this.pageNumStart =1;
				this.pageNumEnd = this.length;
				this.afterMore = true;
				
			}else if(this.currentNo== (this.length-2)){
				this.pageNumStart =1;
				this.pageNumEnd = this.currentNo+3;
				this.afterMore = true;
				
			}else{
				if(this.currentNo+4<this.lastNo){
					this.pageNumStart =this.currentNo-4;
					this.pageNumEnd = this.currentNo+3;
					this.afterMore = true;
					this.beforeMore = true;
				}else if(this.currentNo+4==this.lastNo){
					this.beforeMore = true;
					this.afterMore = false;
					this.pageNumStart =this.currentNo-4;
					this.pageNumEnd = this.lastNo;
					
				}else if(this.currentNo+4>=this.lastNo){
					this.beforeMore = true;
					this.afterMore = false;
					this.pageNumStart =this.lastNo-7;
					this.pageNumEnd = this.lastNo;
				} 
				
			}
			
		}else{
			//小于直接显示
			this.pageNumStart = 1;
			this.pageNumEnd = this.lastNo;
			
		}
		
		if(this.pageNumStart<=1){
			this.pageNumStart =1;
			this.beforeMore = false;
		}
		if(beforeMore){
			listPageNum.add(firstNo+"");
			listPageNum.add("...");
		}
		for(int i = this.pageNumStart;i<=this.pageNumEnd;i++){
			listPageNum.add(i+"");
		}
		if(afterMore){
			listPageNum.add("...");
			listPageNum.add(this.lastNo+"");
		}
		
		this.setPageInfo(getPageHtml());
	}
	
	
	public String getPageHtml(){
		/*
		 *<div class="pagination">
			<ul>
				<li class="disabled"><a href="javascript:">« 上一页</a></li>
				<li class="active"><a href="javascript:">1</a></li>
				<li class="disabled"><a href="javascript:">下一页 »</a></li>
				<li class="disabled controls"><a href="javascript:">当前 <input
						type="text" value="1"
						onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,30,'');"
						onclick="this.select();"> / <input type="text" value="30"
						onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(1,this.value,'');"
						onclick="this.select();"> 条，共 0 条
				</a></li>
			</ul>
			<div style="clear: both;"></div>
		</div>
		 * */
		StringBuffer sf = new StringBuffer();
		sf.append("			<ul>    ");
		
		if(this.totalNum!=0){
			if(this.currentNo==1){
				sf.append("				<li class=\"disabled\"><a href=\"javascript:\">« 上一页</a></li>    ");
			}else{
				sf.append("				<li class=\"\"><a href=\"javascript:\"  onclick=\"page("+(this.currentNo-1)+","+this.pageSize+",'');\">« 上一页</a></li>    ");
			}
		}else{
			sf.append("				<li class=\"disabled\"><a href=\"javascript:\">« 上一页</a></li>    ");
		}
		for(String pageNum:listPageNum){
			if(pageNum.equals(this.currentNo+"")){
				sf.append("			<li class=\"active\"><a href=\"javascript:\">"+pageNum+"</a></li>    ");
			}else{
				sf.append("			<li><a href=\"javascript:\" onclick=\"page("+pageNum+","+this.pageSize+",'');\">"+pageNum+"</a></li>    ");
			}
		}
		
		if(this.totalNum!=0){
			if(this.currentNo==this.lastNo){
				sf.append("				<li class=\"disabled\"><a href=\"javascript:\">下一页 »</a></li>    ");
			}else{
				sf.append("				<li class=\"\"><a href=\"javascript:\"  onclick=\"page("+(this.currentNo+1)+","+this.pageSize+",'');\" >下一页 »</a></li>    ");
			}
		}else{
			sf.append("				<li class=\"disabled\"><a href=\"javascript:\">下一页 »</a></li>    ");
		}
		sf.append("					<li class=\"disabled controls\">  ");
		sf.append("					<a href=\"javascript:\">当前 <input type=\"text\" value=\""+this.currentNo+"\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,"+this.pageSize+",'');\" onclick=\"this.select();\"> ");
		sf.append("					/  ");
		sf.append("					<input type=\"text\" value=\""+this.pageSize+"\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(1,this.value,'');\" onclick=\"this.select();\"> 条，共 "+this.totalNum+" 条   </a></li>   ");
		sf.append("			</ul>    ");
		sf.append("		<div style=\"clear: both;\"></div>    ");
		return sf.toString();
		
	}
	public String getPageHtml2(){
		/*
		  <div class="pagination">
			<div class="span6">
				<div class="dataTables_paginate paging_bootstrap pagination">
					<ul>
						<li class="prev disabled"><a href="#">← 上一页</a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li class="next"><a href="#">下一页 → </a></li>
					</ul>
				</div>
			</div>
			<div class="span6">
				<div class="dataTables_info" id="example_info">Showing 1 to 10of 57 entries</div>
			</div>
		</div>
		 */
		
		
		if(totalNum<=0 || pageSize<=0 || currentNo<=0 || firstNo<=0){
			return "";
		}
		
		StringBuffer sf = new StringBuffer();
		sf.append("   <div class=\"span6\">	");
		//System.out.println("   <div class=\"span6\">	");
		sf.append("  		 <div class=\"dataTables_paginate paging_bootstrap pagination\">	");
		//System.out.println("  		 <div class=\"dataTables_paginate paging_bootstrap pagination\">	");
		sf.append("   			<ul>	");
		//System.out.println("   			<ul>	");
		if(this.currentNo==1){
			sf.append("   			<li class=\"prev disabled\"><a href=\"#\">← 上一页</a></li>	");
			//System.out.println("   			<li class=\"prev disabled\"><a href=\"#\">← 上一页</a></li>	");
		}else{
			sf.append("   			<li class=\"prev \"><a href=\"#\">← 上一页</a></li>	");
			//System.out.println("   			<li class=\"prev \"><a href=\"#\">← 上一页</a></li>	");
		}
		for(String pageNum:listPageNum){
			if(pageNum.equals(this.currentNo+"")){
				sf.append("   		<li class=\"active\"><a href=\"#\">"+pageNum+"</a></li>	");
				//System.out.println("   		   	<li class=\"active\"><a href=\"#\">"+pageNum+"</a></li>	");
			}else{
				sf.append("   		<li><a href=\"#\">"+pageNum+"</a></li>	");
				//System.out.println("   		   	<li><a href=\"#\">"+pageNum+"</a></li>	");
			}
		}
		if(this.currentNo==this.lastNo){
			sf.append("   			<li class=\"next disabled\"><a href=\"#\">下一页 → </a></li>	");
			//System.out.println("   			<li class=\"next disabled\"><a href=\"#\">下一页 → </a></li>	");
		}else{
			sf.append("   			<li class=\"next\"><a href=\"#\">下一页 → </a></li>	");
			//System.out.println("   			<li class=\"next\"><a href=\"#\">下一页 → </a></li>	");
		}
		sf.append("   			</ul>	");
		//System.out.println("   			</ul>	");
		sf.append("   		</div>	");
		//System.out.println("   		</div>	");
		sf.append("   </div>	");
		//System.out.println("   </div>	");
		sf.append("   <div class=\"span6\">	");
		//System.out.println("   <div class=\"span6\">	");
		sf.append("   		<div class=\"dataTables_info\" id=\"example_info\"> 当前 	"+this.currentNo+" / "+this.pageSize+" 条  共"+this.totalNum+" 条 ");
		//System.out.println("   		<div class=\"dataTables_info\" id=\"example_info\">Showing 1 to 10of 57 entries</div>	");
		sf.append("   </div>	");
		//System.out.println("   </div>	");
		return sf.toString();
	}



	public int getFirstNo() {
		return firstNo;
	}



	public void setFirstNo(int firstNo) {
		this.firstNo = firstNo;
	}



	public int getCurrentNo() {
		return currentNo;
	}



	public void setCurrentNo(int currentNo) {
		this.currentNo = currentNo;
	}



	public int getLastNo() {
		return lastNo;
	}



	public void setLastNo(int lastNo) {
		this.lastNo = lastNo;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public int getTotalNum() {
		return totalNum;
	}



	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}



	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}



	public int getSlider() {
		return slider;
	}



	public void setSlider(int slider) {
		this.slider = slider;
	}



	public int getPageNumStart() {
		return pageNumStart;
	}



	public void setPageNumStart(int pageNumStart) {
		this.pageNumStart = pageNumStart;
	}



	public int getPageNumEnd() {
		return pageNumEnd;
	}



	public void setPageNumEnd(int pageNumEnd) {
		this.pageNumEnd = pageNumEnd;
	}



	public boolean isBeforeMore() {
		return beforeMore;
	}



	public void setBeforeMore(boolean beforeMore) {
		this.beforeMore = beforeMore;
	}



	public boolean isAfterMore() {
		return afterMore;
	}



	public void setAfterMore(boolean afterMore) {
		this.afterMore = afterMore;
	}



	public List<String> getListPageNum() {
		return listPageNum;
	}



	public void setListPageNum(List<String> listPageNum) {
		this.listPageNum = listPageNum;
	}



	public List<T> getList() {
		return list;
	}



	public void setList(List<T> list) {
		this.list = list;
	}



	public String getPageInfo() {
		return pageInfo;
	}



	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}


	public int getStartSize() {
		return pageSize*(currentNo-1);
	}


	public void setStartSize(int startSize) {
		this.startSize = startSize;
	}
	
	
}
