import xFetch from './xFetch';

let API_PREFIX;

if(location.port === '8989'){
  API_PREFIX = `${location.protocol}//` + location.host;
}else{
  API_PREFIX = `${location.protocol}//` + location.hostname + ':8341';
}
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

export async function getUserList() {
  return xFetch(API_PREFIX + '/webapi/users?page=1&pageSize=5');
}

export async function addUser(user) {
  return xFetch(API_PREFIX + '/webapi/users', {
    method: "post",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(user)
  });
}
