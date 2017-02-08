package com.sp.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sp.entity.SysProject;
import com.sp.service.SysProjectService;

/******************************************************************
 *    http://blog.csdn.net/fenfenguai  
 *    Package: com.sp.interceptor
 *    Filename: SysInitListener.java
 *    Description:  SpringMVC启动后进行项目信息初始化
 *    @author: Gaoxueyong
 *    @version: 1.0.0
 *    Create at: 2017年2月7日 下午5:57:06
 *    Revision:
 *    2017年2月7日 下午5:57:06- first revision
 *
 *****************************************************************/
public class SysInitListener implements ApplicationListener<ContextRefreshedEvent>{

	/**
	 * @Description 执行初始化方法
	 * @param event
	 * @author: Gaoxueyong
	 * Create at: 2017年2月7日 下午5:59:16
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
	/*	Map<String, Object> paramerMap = new HashMap<String,Object>();
		SysProjectService	sysProjectService = WebApplicationContextUtils.getWebApplicationContext(event.getApplicationContext()).getBean(SysProjectService.class);
		List<SysProject> list = sysProjectService.querySysProjectList(paramerMap);
		for(SysProject project:list){
			if("1".equals(project.getType())){
				modelAndView.addObject("projectName",project.getName());
			}else if("2".equals(project.getType())){
				modelAndView.addObject("remark",project.getName());
			}
		}
		*/
	}

	 

}
