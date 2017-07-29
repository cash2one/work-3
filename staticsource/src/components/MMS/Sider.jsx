import React, {Component, PropTypes} from 'react';
import {Router, Route, IndexRoute, Link} from 'react-router';
import {Menu, Icon, Button} from 'antd';
import styles from './Sider.less';
import {getMenuLists} from '../../../src/services/api.js'
import {API_HREF,API_FIX} from '../../services/config'
const Sider = React.createClass({
  getInitialState() {
    return {
      current: '1',
      menuList: [],
      defaultMenu: [],
      userName: [],
      userID: []
    }
  },

  handleClick(e) {
    sessionStorage.setItem("menuID", this.state.menuList[e.key].menuId);
  },

  componentDidMount() {
    getMenuLists().then((msg) => {
      if (!msg.jsonResult.success) {
        message.error("获取菜单栏失败！");
      } else {
        this.setState({
          UserName: sessionStorage.getItem("userName"),
          menuList: msg.jsonResult.data
        });
      }
    });
  },
  removeCookie(name, path, domain) {
    let date=new Date();
    date.setTime(date.getTime()-10000);
    document.cookie= name + "=" + null + "; expire="+date.toUTCString()+"; path=" + path + ";domain=" + domain;
  },
  getOut(){
    let keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if (keys) {
      for (let i = keys.length; i--;)
        document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
    }
    this.removeCookie("CASTGC", '/', ".trustlife.com");
    this.removeCookie("auth_token", '/', API_FIX)
    window.location.href = API_HREF + location.href;
  },

  render() {
    const menuList = this.state.menuList.map((items, index) => {
      return (
        <Menu.Item key={index}>
          <Link to={items.url}>
            <span className="nav-text">{items.menuName}</span>
          </Link>
        </Menu.Item>
      )
    });

    return (
      <div>
        <div className="IMG_user">
          <img src="./IMG/privilege-logo.png"/>
          <div>
            <h2>{this.state.UserName}
              <Button type="primary" onClick={this.getOut}>退出</Button>
            </h2>
          </div>
        </div>
        <Menu mode="inline" theme="dark" defaultSelectedKeys={["index"]} onClick={this.handleClick}>
          <Menu.Item ket="index">
            <Link to="/content/index">
              <span className="nav-text"><Icon type="windows"/>首页</span>
            </Link>
          </Menu.Item>
          {menuList}
        </Menu>
      </div>
    );
  }

});

export default Sider;

