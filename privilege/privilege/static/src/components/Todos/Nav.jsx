import React from 'react';
import {Menu, Icon} from 'antd';
import {Link} from 'react-router';
import styles from './Nav.less';

const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;

const Nav = ({pathname}) => {
  console.log(pathname);
  return (
    <div className='todos-nav'>
      <Menu
        selectedKeys={[pathname]}
        mode="inline"
      >
        <Menu.Item key="/todos/completed">
          <span>
            <Icon type="mail" />
            <Link to={`/todos/completed`}>completed</Link>
          </span>
        </Menu.Item>
        <Menu.Item key="/todos/actived">
          <span>
            <Icon type="mail" />
            <Link to={`/todos/actived`}>actived</Link>
          </span>
        </Menu.Item>
      </Menu>
    </div>
  );
};

export default Nav;
