import React from 'react';
import {Menu, Icon,Select,Row, Col } from 'antd';
import {Link} from 'react-router';
import './Nav.less';

const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;
const Option = Select.Option;
function handleChange(value) {
  console.log(`selected ${value}`);
}


const Nav = ({pathname, spa}) => {
  let current = pathname && pathname.substr(1).split('/')[0];
  return (
    <div className="nav">
       <Row>
          <Col span={18}>
             <Menu
                selectedKeys={[spa]}
                mode="horizontal"
                theme="dark"
              >
                <Menu.Item key="index">
                  <Icon type="mail" /><a href='/index.html'>保险服务</a>
                </Menu.Item>
                <Menu.Item key="concur">
                  <Icon type="mail" /><a href='/concur.html'>互助基金</a>
                </Menu.Item>
                <Menu.Item key="medical">
                  <Icon type="mail" /><a href='/medical.html'>医疗服务</a>
                </Menu.Item>
                <Menu.Item key="todos">
                  <Icon type="mail" /><a href='/todos.html'>todos</a>
                </Menu.Item>
              </Menu>
          </Col>
          <Col span={6} >
            <Select showSearch
              style={{ width: 200 }}
              placeholder="请选择人员"
              optionFilterProp="children"
              notFoundContent="无法找到"
              onChange={handleChange}
            >
              <Option value="jack">杰克</Option>
              <Option value="lucy">露西</Option>
              <Option value="tom">汤姆</Option>
            </Select>
          </Col>
        </Row>
    </div>
  );
};

export default Nav;
