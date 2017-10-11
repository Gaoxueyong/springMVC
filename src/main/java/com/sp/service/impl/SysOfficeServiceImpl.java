package com.sp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sp.dao.SysAreaDao;
import com.sp.dao.SysOfficeDao;
import com.sp.entity.SysOffice;
import com.sp.service.SysOfficeService;
import com.sp.utils.Page;

/**
 * 
 * @ClassName SysOfficeServiceImpl
 * @Description 组织机构接口实现
 * @author:Gaoxueyong  http://blog.csdn.net/fenfenguai
 * @Date 2016年11月21日 上午10:47:39
 * @version 1.0.0
 */
@Service
public class SysOfficeServiceImpl implements SysOfficeService{

	@Resource
	SysAreaDao sysAreaDao;
	@Resource
	SysOfficeDao sysOfficeDao;
	
	public SysOfficeDao getSysOfficeDao() {
		return sysOfficeDao;
	}

	public void setSysOfficeDao(SysOfficeDao sysOfficeDao) {
		this.sysOfficeDao = sysOfficeDao;
	}

	@Override
	public SysOffice selectSysOfficeByPrimaryKey(String id) {
		return sysOfficeDao.selectSysOfficeByPrimaryKey(id);
	}

	@Override
	public int insertSysOffice(SysOffice sysOffice){
		 return sysOfficeDao.insertSysOffice(sysOffice);
	}

	@Override
	public int updateSysOffice(SysOffice sysOffice) {
		return sysOfficeDao.updateSysOffice(sysOffice);
	}

	@Override
	public int deleteSysOfficeByPrimaryKey(String id) {
		return sysOfficeDao.deleteSysOfficeByPrimaryKey(id);
	}

	@Override
	public Page<SysOffice> getSysOfficListPage(Page<SysOffice> page,Map<String, Object> paramerMap) {
		if(paramerMap.get("currentNo")!=null){
			page.setCurrentNo(Integer.parseInt(paramerMap.get("currentNo").toString()));
		}
		if(paramerMap.get("pageSize")!=null){
			page.setPageSize(Integer.parseInt(paramerMap.get("pageSize").toString()));
		}
		 
		paramerMap.put("startSize",page.getStartSize());
		paramerMap.put("pageSize",page.getPageSize());
		 
		page.setTotalNum(sysOfficeDao.getSysOfficListTotalNum(paramerMap));
		page.setList(sysOfficeDao.getSysOfficListPage(paramerMap));
		page.initialize();
		
		return page;
	}

	@Override
	public int getSysOfficListTotalNum(Map<String, Object> paramerMap) {
		return sysOfficeDao.getSysOfficListTotalNum(paramerMap);
	}

	@Override
	public List<Map<String, Object>> getSysOfficeTreeData(Map<String, Object> paramerMap) {
		return sysOfficeDao.getSysOfficeTreeData(paramerMap);
	}

	@Override
	public List<SysOffice> getSysOfficeList(Map<String, Object> paramerMap) {
		return sysOfficeDao.getSysOfficeList(paramerMap);
	}
	
    /**
     * 
     * @Description 删除目标机构及其下属机构
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月30日 下午3:49:18
     */
	@Override
	public int deleteSysOfficeAndChildrenById(Map<String, Object> paramerMap) {
		return sysOfficeDao.deleteSysOfficeAndChildrenById(paramerMap);
	}

	 /**
     * 
     * @Description 获取树形结构列表
     * @param menuRootList
     * @param officeList
     * @param resultList
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年12月13日 上午10:00:41
     */
	@Override
	public List<SysOffice> getTreeList(List<SysOffice> menuRootList, List<SysOffice> officeList,
			List<SysOffice> resultList) {
		return getOfficeList(menuRootList, officeList, null);
	}
	
	/**
	 * 
	 * @Description 获取根及其子节点
	 * @param rootList
	 * @param menuList
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	 * Create at: 2016年12月12日 上午11:47:51
	 */
	public List<SysOffice> getOfficeList(List<SysOffice> menuRootList,List<SysOffice> officeList,Map<String, Object> containMap){
		List<SysOffice> returnList = new ArrayList<SysOffice>();
		if(officeList==null || officeList.size()==0){
			return returnList;
		}
		if(containMap==null){containMap=new HashMap<String,Object>();}
		for(SysOffice root:menuRootList){
			//if(!containMap.containsKey(root.getId())){
				returnList.add(root);
				containMap.put(root.getId(), root.getId());
				for(SysOffice child:officeList){
					if(!containMap.containsKey(child.getId())){
						if(root.getId().equals(child.getParentId())){
							returnList.add(child);
							containMap.put(child.getId(), child.getId());
						}
					}
				}
			//}
		}
		
		if(returnList!=null && returnList.size()==officeList.size()){
			return returnList;
		} 
		return getOfficeList(returnList, officeList,containMap);
	}

}
