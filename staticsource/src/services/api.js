import xFetch from './xFetch';
import {API_FIX} from './config';

/*export const API_PREFIX = `${location.protocol}//` + location.hostname + ":8341" ;*/

let localhost = API_FIX;
// let localhost = 'portal.trustlife.com';
let API_PREFIX = 'http://'+localhost+':8341';
//let API_PREFIX = 'http://localhost:8341';

/*
新添加接口请按这个格式新增
export async function apiMethod() {
  return xFetch(API_PREFIX + '/webapi/users/xiao', [options]);
}
options:<Object> 可选参数
  {
    method: 'post' //默认为get
    data: {}
 }
*/

/**
 * 系统初始化，获取菜单
 **/
export async function getMenuLists() {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/selectSysMenu/1',{
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });
}
/**
 * 系统初始化，获取菜单
 **/

/**
 * 系统资源接口
 **/
// user获取系统信息 || user查询系统
export async function getCustomerMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/systemPlatform/selectSystemPlatform',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//新建系统
export async function addSystem(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/systemPlatform/addSystem',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

// user修改系统信息
export async function updateSystem(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/systemPlatform/UpadateSystemPlatform',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//user删除系统信息
export async function removeSystem(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/systemPlatform/deleteSystem/'+userMsg,{});
}
/**
 * 系统资源接口 END
 **/

/**
 * 菜单资源接口
 **/
//user获取菜单信息 || user查询菜单
export async function getMenuMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/getAllMenus',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}
// user新建菜单信息
export async function addMenu(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/addMenu',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//新建菜单 点击上级菜单 获取展示顺序
export async function getShowOlder(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/getMenuByPrimaryKey/'+userMsg,{});
}

// user删除菜单信息
export async function removeMenu(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/deleteMenu/'+userMsg,{});
}

//user修改菜单信息
export async function updateMenu(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/updateMenu',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}
/**
 * 菜单资源接口 END
 **/

/**
 * 页面资源接口
 **/

//获取页面资源
export async function getPageMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/privilege/privilegeList',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//新建页面资源
export async function addPage(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/privilege/addPrivilege',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//新建页面资源时，获取资源类型
export async function getPageType() {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/privilege/getAllResType',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });
}

// user删除页面资源
export async function removePage(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/privilege/deletePrivilege/'+userMsg,{});
}

// 修改页面资源
export async function updatePage(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/privilege/updatePrivilege',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}
//点击修改时，获取资源类型
export async function getPageTypeList() {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/privilege/getAllResType',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });
}
/**
 * 页面资源接口 END
 **/

/**
 * 角色管理接口
 **/
//获取角色列表
export async function getRoleMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/getAllRoles',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//新建角色
export async function addRoleMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/insertRole',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//删除角色
export async function deleteRoleMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/deleteRoleByRoleId/'+userMsg,{});
}

//修改角色
export async function updateRoleMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/updateRole',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

/**
 * 角色管理接口 END
 **/

/**
 * 日志管理接口
 **/
export async function getLogMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/log/getLogMsg',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}
export async function getAllSystem() {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/systemPlatform/getAllSystem',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });
}

/**
 * 日志管理接口 END
 **/

/**
 * 用户组管理接口
 **/
//获取用户组列表
export async function getUserGroupMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/selectGroup',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//新建时，获取上级用户组列表
export async function getParentUserGroupMsg() {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/selectOptGroup',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });
}
//新建时，获取系统列表
export async function getSystemListMsg() {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/selectSys');
}
//新建时，获取用户列表 || 修改要用户组时，根据系统ID获取角色
export async function getRoleListMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/selectRoleBySys/'+userMsg);
}
//新建时，根据用户组ID 获取此用户组包含角色列表
export async function getAllRoleListMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/selectRoleFromGroup/'+userMsg);
}

//新建接口
export async function addUserGroup(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/add',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//删除用户组
export async function deleteUserGroupMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/delete/'+userMsg);
}

//修改用户组
export async function updateUserGroup(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/update',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}
//修改时，获取单条userGroup的信息
export async function getUserGroupListMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/updateDis/'+userMsg);
}
//查看，单条用户组信息
export async function lookUserGroupMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/detail/'+userMsg);
}

/**
 * 用户组管理接口 END
 *
 **/

/**
 * 用户管理接口
 **/
//获取用户组列表
export async function getUserMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/user/select',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//新建用户
export async function addUserMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/user/add',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}
//新建查询用户组
export async function getUserGroupList() {
	return xFetch(API_PREFIX + '/etheta/management/privilege/group/selectOptGroup',{
		method: "post",
		headers: {
		  'Accept': 'application/json',
		  'Content-Type': 'application/json'
		}
	})
}
//新建根据所选用户组，查询角色
export async function getRoleList(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/selectRoleFromGroup/'+userMsg);
}
//删除用户
export async function deleteUserMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/user/delete/'+userMsg);
}

//修改时，根据所选用户ID，查询用户基本信息
export async function getUpdateUserMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/user/selectUpdDis/'+userMsg);
}
//修改时查询所有用户组
export async function getUserGroupListsMsg() {
  return xFetch(API_PREFIX + '/etheta/management/privilege/group/selectOptGroup',{
	method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  })
}
//修改完成，提交表单
export async function updateUserMsg(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/privilege/user/update',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

/**
 * 用户管理接口 END
 **/

/**
 * 公共接口
 **/

//新建时获取系统列表--
export async function getMenuSystem(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/systemPlatform/selectSystemPlatform',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//新建时，根据所选择系统获取菜单
export async function getParentMenuName(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/getAllMenus',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}

//新建时，根据所选择系统获取菜单
export async function AddGetParentMenuName(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/getAllParentMenus',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}
//新建角色时，根据所选择菜单获取页面资源
export async function getPageResources(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/privilege/getPrivilegeByMenuIds',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}
//新建时，获取可见角色列表
export async function getShowUsers(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/getAllRoles',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}
//查看菜单资源时，获取可见角色列表
export async function getLookUsers(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/getRolesByMenuId/'+userMsg,{});
}

//修改时，获取个人基本信息
export async function getUserMsgs(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/getAllResourceByRoleId/'+userMsg,{});
}

//修改时，根据已有菜单获取资源
export async function getPrivilegeMsgs(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/privilege/getPrivilegeByMenuIds',{
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userMsg)
  });
}


//点击菜单切换页面时，获取权限
export async function getBtnLists(userMsg) {
  return xFetch(API_PREFIX + '/etheta/management/xinmei/button/privilege/selectSysPrivilege/'+userMsg,{});
}

//上传接口
export async function getOSSUploadCert(fileMsg) {
  return xFetch(API_PREFIX + '/etheta/management/upload/getSignature', {
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(fileMsg)
  });
}
export async function getOSSUploadedFileUrl(fileMsg) {
  return xFetch(API_PREFIX + '/etheta/management/upload/uploadInitData', {
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(fileMsg)
  });
}
/**
 * 公共接口 END
 **/
