import fetch from 'isomorphic-fetch';
import cookie from 'js-cookie';
import {message} from 'antd';
import {API_HREF} from './config';

const errorMessages = (res) => `${res.status} ${res.statusText}`;

function check401(res) {

  if (res.status === 401) {
    window.location.href = API_HREF + location.href;
  }
  return res;
}

function check404(res) {
  if (res.status === 404) {
    return Promise.reject(errorMessages(res));
  }
  return res;
}

function check403(res) {
  if (res.status === 403) {
    message.error("无权限，请重试！");
  }
  return res;
}

function jsonParse(res) {
  return res.json().then(jsonResult => ({...res, jsonResult}));
}

function errorMessageParse(res) {
  return res;
}

function xFetch(url, options) {
  const opts = {...options};
  opts.headers = {
    ...opts.headers
  };
  console.log(url, options, 'ppp');
  opts.credentials = 'include';
  let result = fetch(url, opts)
    .then(check401)
    .then(check404)
    .then(check403)
    .then(jsonParse)
    .then(errorMessageParse);
  return result;
}

export default xFetch;
