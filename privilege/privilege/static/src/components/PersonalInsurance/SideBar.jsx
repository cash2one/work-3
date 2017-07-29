import React, { Component, PropTypes } from 'react';
import { Router, Route, IndexRoute, Link } from 'react-router';
import { Menu, Icon } from 'antd';

const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;

const SiderBar = React.createClass({
  getInitialState() {
    return {
      current: '1',
    };
  },
  handleClick(e) {
    // console.log('click ', e);
    this.setState({
      current: e.key,
    },function () {
      this.props.onClick(this.state.current)
    })
    
  },
  render() {
    return (
      <Menu onClick={this.handleClick}
        defaultOpenKeys={['sub1']}
        selectedKeys={[this.state.current]}
        style={{backgroundColor:'#464646'}}

        mode="inline"
      >
        <Menu.Item key="1">
          <Link to="/content/item1" >医疗资源管理</Link><br />
        </Menu.Item>
        <Menu.Item key="2">
           <Link to="/content/item2" style={{color:'white'}}>体检资源管理</Link>
        </Menu.Item>
      
     
        <Menu.Item key="3">
          <Link to="/item3" style={{color:'white'}}>齿科资源管理</Link>
        </Menu.Item>
        <Menu.Item key="4"> 
          <Link to="/item4" style={{color:'white'}}>医疗任务处理</Link><br />
        </Menu.Item>

        <Menu.Item key="5">
          <Link to="/item5" style={{color:'white'}}>录入任务列表</Link><br />
        </Menu.Item>
        <Menu.Item key="6" style={{color:'white'}}>挂号任务处理</Menu.Item>
       
        <Menu.Item key="7" style={{color:'white'}}>体检任务处理</Menu.Item>
        <Menu.Item key="8" style={{color:'white'}}>齿科任务处理</Menu.Item>
     
        <Menu.Item key="9" style={{color:'white'}}>重疾审核处理</Menu.Item>
        <Menu.Item key="10" style={{color:'white'}}>会员管理</Menu.Item>
        <Menu.Item key="11" style={{color:'white'}}>积分管理</Menu.Item>
        <Menu.Item key="12" style={{color:'white'}}>积分订单</Menu.Item>
        <Menu.Item key="13" style={{color:'white'}}>积分配置</Menu.Item>
        <Menu.Item key="14" style={{color:'white'}}>用户管理</Menu.Item>
        <Menu.Item key="15" style={{color:'white'}}>投诉处理</Menu.Item>
        
      </Menu>
    );
  },
});
export default SiderBar