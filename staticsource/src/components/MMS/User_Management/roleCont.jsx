import React, { Component, PropTypes } from 'react';
import { Radio, Button, Form, Input, Modal, message, Table, Select, Col } from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import RoleCreat from './roleCreat.jsx';
import { getRoleMsg, getParentMenuName, deleteRoleMsg, getUserMsgs, getPrivilegeMsgs, updateRoleMsg, getBtnLists} from '../../../services/api.js';

const createForm = Form.create;
const Option = Select.Option;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;

let uuid = 0;

let Demo = React.createClass({
  //	页面初始化
  getInitialState(){
    return {
      loading:true,
      delDisabled:true,
      addDisabled:true,
      updDisabled:true,
      userMsg: [],
      selectedRowKeys: [],
      hotCustData:[],
      menuSystem:[],
      parentMenu:[],
      pageData:[],
      updateList:[],
      lookData:[],
      selectUserGroup:[],
      menuIDStr:sessionStorage.getItem("menuID"),
      parentSystemVal:"",
      parentMenuVal:"",
      pageResourcesVal:"",
      searchVal:"",
      loopKeys:[]
    }
  },

	/*初始化表单*/
  componentDidMount() {
    this.props.form.resetFields();
    let obj={
      "systemPlatformName":""
    };
    //获取按钮列表
    getBtnLists(this.state.menuIDStr).then((msg)=>{
      if (msg.jsonResult.data.delete == undefined){
        this.setState({delDisabled:true})
      }else {
        this.setState({delDisabled:false})
      }
      if (msg.jsonResult.data.add == undefined){
        this.setState({addDisabled:true})
      }else {
        this.setState({addDisabled:false})
      }
      if (msg.jsonResult.data.update == undefined){
        this.setState({updDisabled:true})
      }else {
        this.setState({updDisabled:false})
      }
    });

    getRoleMsg(obj).then((msg)=>{
      if (!msg.jsonResult.success){
        this.setState({ loading:true });
        message.error(msg.jsonResult.resultMsg);
      }else {
        this.setState({
          loading:false,
          hotCustData:msg.jsonResult.data.list
        });
      }
    });
  },

 //点击数据前面小框取得数据信息
  onSelectChange(selectedRowKeys,record) {
    this.setState({ userMsg:[] });
    this.setState({
      selectedRowKeys,
      userMsg:record
    });
  },

  /*新建系统资源*/
  addBtn(){
    this.setState({ addCust: true });
  },
  getReturnData:function(result){
    this.setState({ addCust: false });
    let obj={
      "systemPlatformName":""
    };
    getRoleMsg(obj).then((msg)=>{
      this.setState({ hotCustData:msg.jsonResult.data.list });
    });
  },
  addCancel() {
    this.setState({ addCust: false });
  },
  /*新建系统资源 END*/

  /*删除系统资源 END*/
  deleteBtn(){
    if (this.state.userMsg == 0) {
      message.warning ("请选择至少一条数据！");
    }else {
      this.setState({ delete: true });
    }
  },
  deleteOk() {
    deleteRoleMsg(this.state.userMsg[0].roleId).then((data)=>{
      if (data.jsonResult.success) {
        message.success(data.jsonResult.data);
        this.setState({
          delete: false,
          selectedRowKeys:[]
        });
        let obj={
          "systemPlatformName":""
        };
        getRoleMsg(obj).then((msg)=>{
          this.setState({ hotCustData:msg.jsonResult.data.list });
        });
      }else{
        message.error(data.jsonResult.resultMsg);
      }
    })
  },
  deleteCancel() {
    this.setState({ delete: false });
  },
  /*删除系统资源 END*/

  /*修改系统资源*/
  updateBtn(){
    if (this.state.userMsg == "") {
      message.warning('请选择至少一条数据！');
    }else {
      this.setState({ updateCon: true });
      /*根据UserId，获取个人基本信息*/
      getUserMsgs(this.state.userMsg[0].roleId).then((msg)=>{
        if (msg.jsonResult.data.resIdsList.length == 0){
          this.setState({ updateList:[msg.jsonResult.data], loopKeys:[0]});
        }else{
          this.setState({ updateList:[msg.jsonResult.data], loopKeys:msg.jsonResult.data.resIdsList});
        }
        if (msg.jsonResult.data.memuIdsList.length != 0){
          //根据返回值内的memuIdsList，获取资源
          let dat = {
            "stateCd":"1",
            "menuIds":msg.jsonResult.data.memuIdsList.join(",")
          };
          getPrivilegeMsgs(dat).then((msg)=>{
            this.setState({ pageData:msg.jsonResult.data });
          });
        }else{
          this.setState({ pageData:[] });
        }
      });

      let menus={
        "systemPlatformCd":this.state.userMsg[0].systemPlatformCd,
        "stateCd":"1"
      };
      getParentMenuName(menus).then((msg)=>{
        this.setState({ parentMenu:msg.jsonResult.data.list })
      });
    }
  },
  //根据菜单Id，获取资源
  updateRoleMenu(val){
    if(val.length == 0){
      return false;
    }else{
      let dat = {
        "stateCd":"1",
        "menuIds":val.join(",")
      };
      getPrivilegeMsgs(dat).then((msg)=>{
        this.setState({ pageData:msg.jsonResult.data });
      });
    }
  },
  updateOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        return;
      }
      let roleDesc = values.updateRoleRemark;
      let resMap = [];
      let updateRoleName = values.updateRoleName;

      for (let y = 0 ; y < this.state.loopKeys.length; y++){
        resMap[y] = values[`updateRolePrivileges`+y]+values[`updateRolePrivilegesType`+y];
      }

      if (roleDesc == this.state.userMsg[0].roleDesc){
        roleDesc = null;
      }
      if (updateRoleName == this.state.userMsg[0].roleName){
        updateRoleName = null;
      }
      let updateMenuMsg={
        "roleId":this.state.userMsg[0].roleId,
        "roleName":updateRoleName,
        "stateCd":values.updateRoleStatus,
        "menuIds":values.updateRoleMenus.join(","),
        "resMap":resMap,
        "roleDesc":roleDesc
      };
      updateRoleMsg(updateMenuMsg).then((msg)=>{
        if (!msg.jsonResult.success){
          message.error(msg.jsonResult.resultMsg);
        }else{
          message.success(msg.jsonResult.data);
          this.setState({
            updateCon: false,
            selectedRowKeys:[],
            userMsg:[]
          });
          this.props.form.resetFields();
          let obj={
            "systemPlatformName":""
          };
          getRoleMsg(obj).then((msg)=>{
            this.setState({ hotCustData:msg.jsonResult.data.list });
          });
        }
      });
    });
  },
  updateCancel() {
    this.setState({ updateCon: false });
  },
  /*修改系统资源 END*/
  /*查看系统资源*/
  lookBtn(){
    if (this.state.userMsg == "") {
      message.warning('请选择至少一条数据！');
    }else {
      this.setState({ lookCon: true });
      /*根据UserId，获取个人基本信息*/
      getUserMsgs(this.state.userMsg[0].roleId).then((msg)=>{
        if (msg.jsonResult.data.resIdsList.length == 0){
          this.setState({ lookData:[msg.jsonResult.data], loopKeys:[0]});
        }else{
          this.setState({ lookData:[msg.jsonResult.data], loopKeys:msg.jsonResult.data.resIdsList});
        }
        if (msg.jsonResult.data.memuIdsList.length != 0){
          //根据返回值内的memuIdsList，获取资源
          let dat = {
            "stateCd":"1",
            "menuIds":msg.jsonResult.data.memuIdsList.join(",")
          };
          getPrivilegeMsgs(dat).then((msg)=>{
            this.setState({ pageData:msg.jsonResult.data });
          });
        }else{
          this.setState({ pageData:[] });
        }
      });

      let menus={
        "systemPlatformCd":this.state.userMsg[0].systemPlatformCd,
        "stateCd":"1"
      };
      getParentMenuName(menus).then((msg)=>{
        this.setState({ parentMenu:msg.jsonResult.data.list })
      });
    }
  },
  lookOk(e) {
    e.preventDefault();
    this.setState({ lookCon: false });
  },
  lookCancel() {
    this.setState({ lookCon: false });
  },
  /*查看系统资源 END*/

  /*查询系统*/
  searchType(val){
    this.setState({ searchVal: val });
  },
  searchBtn (e){
    let obj;
    if (this.state.searchVal == "system"){
      obj={
        "resName":"",
        "systemPlatformName":e.target.value
      };
    }else{
      obj={
        "roleName":e.target.value,
        "systemPlatformName":""
      };
    }
    getRoleMsg(obj).then((msg)=>{
      this.setState({ hotCustData:msg.jsonResult.data.list });
    });
  },
  /*查询系统 END*/

  /*表单校验规则*/
  //新建时，校验用户组名规则
  userExists(rule, value, callback) {
    if (!value) {
      callback();
    } else {
      let pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
      if (pattern.test(value)) {
        callback([new Error('请输入中文、英文或数字！')]);
      }else {
        callback();
      }
    }
  },
  /*表单校验规则 END*/

  remove(k) {
    let keys = this.state.loopKeys;
    keys.splice(k, 1);
    this.setState({ loopKeys:keys })
  },
  add() {
    uuid++;
    let keys = this.state.loopKeys;
    keys = keys.concat(uuid);
    this.setState({ loopKeys:keys })
  },

  render() {
    //用于页面内容显示的table
    const columns = [
      { title: '角色名称', dataIndex: 'roleName', key: 'roleName' },
      { title: '角色描述', dataIndex: 'roleDesc', key: 'roleDesc' },
      { title: '状态', dataIndex: 'stateCd', key: 'stateCd' },
      { title: '所属系统', dataIndex: 'systemPlatformName', key: 'systemPlatformName' },
      { title: '创建时间', dataIndex: 'createDt', key: 'createDt' }
    ];

    const {  selectedRowKeys } = this.state;
    const { getFieldProps } = this.props.form;

    /*新建菜单*/
    //渲染菜单列表
    const ParentMenuList = this.state.parentMenu.map((items,index)=>{
      return (
        <Option key={index} value={items.menuId}>{items.menuName}</Option>
      )
    });
    //渲染资源列表
    const pageResourcesList = this.state.pageData.map((items,index)=>{
      return (
        <Option key={index} value={items.privilegeResId}>{items.resName}</Option>
      )
    });
    /*新建菜单 END*/

    /*资源操作类型*/
    const formItems = this.state.loopKeys.map((items,k) => {
      return (
        <Form.Item key={k} style={{marginBottom:"20px"}}>
          <Col span="9">
            <FormItem hasFeedback>
              <Select size="large"
                {...getFieldProps(`updateRolePrivileges${k}`,
                  {onChange:this.selectPage,
                    rules:[{ required: false, message: '请选择可访问资源！'}],
                    initialValue:this.state.loopKeys[k].privilegeResId
                    })}
                >
                {pageResourcesList}
              </Select>
            </FormItem>
          </Col>
          <Col span="1"></Col>
          <Col span="9">
            <FormItem hasFeedback>
              <Select size="large"
                {...getFieldProps(`updateRolePrivilegesType${k}`,{rules:[{ required: false, message: '请选择操作类型！'}],initialValue:this.state.loopKeys[k].stateType})}
                >
                <Option value="1">可见</Option>
                <Option value="2">不可见</Option>
                <Option value="3">可编辑</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="1"></Col>
          <Col span="4">
            <Button size="large" onClick={() => this.remove(k)}>删除</Button>
          </Col>
        </Form.Item>
      );
    });
    /*资源操作类型*/

    /*查看资源操作类型*/
    const lookFormItems = this.state.loopKeys.map((items,k) => {
      return (
        <Form.Item key={k} style={{marginBottom:"20px"}}>
          <Col span="9">
            <FormItem>
              <Select size="large" disabled
                {...getFieldProps(`lookRolePrivileges${k}`,
                  {onChange:this.selectPage,
                    initialValue:this.state.loopKeys[k].privilegeResId
                  })}
              >
                {pageResourcesList}
              </Select>
            </FormItem>
          </Col>
          <Col span="1">&nbsp;</Col>
          <Col span="9">
            <FormItem>
              <Select size="large" disabled
                {...getFieldProps(`lookRolePrivilegesType${k}`,{initialValue:this.state.loopKeys[k].stateType})}
              >
                <Option value="1">可见</Option>
                <Option value="2">不可见</Option>
                <Option value="3">可编辑</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="1">&nbsp;</Col>
          <Col span="4">
            <Button size="large" disabled onClick={() => this.remove(k)}>删除</Button>
          </Col>
        </Form.Item>
      );
    });
    /*查看资源操作类型*/

    const rowSelection = {
      type:"radio",
      selectedRowKeys,
      onChange: this.onSelectChange
    };

    /*分页控制*/
  	const hotPage = {
      total: this.state.hotCustData.length,
      showSizeChanger: true
    };

    /*修改系统 数据展示*/
    const userUpdate = this.state.updateList.map((items,index)=>{
      return (
        <Form onSubmit={this.updateOk} className="login-form" key={index}>
          <FormItem label="角色名称" hasFeedback>
            <Input type="text" placeholder="请输入角色名称（必填）"
              {...getFieldProps('updateRoleName',
                {initialValue:items.roleName,rules:[{ required: true, message: '请填写正确的角色名称！'},{validator: this.userExists}]})}
            />
          </FormItem>
          <FormItem label="可访问菜单">
            <Select size="large" multiple
              {...getFieldProps('updateRoleMenus',
                {initialValue:items.memuIdsList, onChange:this.updateRoleMenu,rules:[{ required: false, message: '请选择可访问菜单！',type: 'array'}]})
              }
            >
              {ParentMenuList}
            </Select>
          </FormItem>
          <FormItem label="可访问资源" hasFeedback>
            {formItems}
            <Form.Item wrapperCol={{ span: 10, offset: 0 }}>
              <Button onClick={this.add}>新增</Button>
            </Form.Item>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup {...getFieldProps('updateRoleStatus', { initialValue: items.stateCd })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" {...getFieldProps('updateRoleRemark', { initialValue:items.roleDesc })}/>
          </FormItem>
        </Form>
      )
    });
    /*修改系统 数据展示 END*/
    /*查看系统 数据展示*/
    const userLook = this.state.lookData.map((items,index)=>{
      return (
        <Form onSubmit={this.updateOk} className="login-form" key={index}>
          <FormItem label="角色名称" hasFeedback>
            <Input type="text"  placeholder="请输入角色名称（必填）" disabled
              {...getFieldProps('lookRoleName',
                {initialValue:items.roleName,rules:[{ required: true, message: '请填写正确的角色名称！'},{validator: this.userExists}]})}
            />
          </FormItem>
          <FormItem label="可访问菜单">
            <Select size="large" multiple disabled
              {...getFieldProps('lookRoleMenus',
                {initialValue:items.memuIdsList, onChange:this.updateRoleMenu})
              }
            >
              {ParentMenuList}
            </Select>
          </FormItem>
          <FormItem label="可访问资源" hasFeedback >
            {lookFormItems}
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup disabled {...getFieldProps('lookRoleStatus', { initialValue: items.stateCd })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" disabled  {...getFieldProps('lookRoleRemark', { initialValue:items.roleDesc })}/>
          </FormItem>
        </Form>
      )
    });
    /*查看系统 数据展示 END*/
    /*查询条件*/
    const selectBefore = (
      <Select defaultValue="role" style={{ width: 120 }} onChange={this.searchType}>
        <Option value="role">角色名称</Option>
        <Option value="system">所属系统</Option>
      </Select>
    );
    /*查询条件*/

    return (
      <div>
        <div style={{width:"100%",height:"40px",lineHeight:"40px"}}>
          <Input placeholder="请输入系统名称，按下回车进行搜索" size="large"
                 onPressEnter={this.searchBtn} onChange={this.searchBtn}
                 addonBefore={selectBefore}
                 style={{height:"33px"}}
          />
        </div>
        <div>
          <div className="btnBox">
            <div style={{float:"right"}}>
              <Button style={{marginLeft:"5px"}} onClick={this.lookBtn} type="primary" size="large">查看</Button>
              <Button style={{marginLeft:"5px"}} onClick={this.addBtn} disabled={this.state.addDisabled} type="primary" size="large">新建</Button>
              <Button style={{marginLeft:"5px"}} onClick={this.updateBtn} disabled={this.state.updDisabled} type="primary" size="large">修改</Button>
              <Button style={{marginLeft:"5px"}} onClick={this.deleteBtn} disabled={this.state.delDisabled} type="primary" size="large">删除</Button>
            </div>
          </div>
          <div style={{width:"100%"}}>
            <Table rowKey='roleId' columns={columns} loading={this.state.loading}  rowSelection={rowSelection} dataSource={this.state.hotCustData} pagination={hotPage}/>
          </div>
          <Modal title="新建角色" visible={this.state.addCust}
                 onCancel={this.addCancel}
                 footer={[ ]}
          >
            <RoleCreat callback = {this.getReturnData}/>
          </Modal>
          <Modal title="删除" visible={this.state.delete}
                 onOk={this.deleteOk} onCancel={this.deleteCancel}
                 footer={[
                <Button key="submit" type="primary" size="large" onClick={this.deleteOk}>确定</Button>,
                <Button key="back" type="ghost" size="large" onClick={this.deleteCancel}>取消</Button>
              ]}
          >
            <p style={{textAlign:"center"}}>是否确认删除此条数据？</p>
            <p style={{textAlign:"center"}}>删除后，将无法恢复。</p>
          </Modal>
          <Modal title="修改角色" visible={this.state.updateCon}
                 onOk={this.updateOk} onCancel={this.updateCancel}
                 footer={[
                <Button key="submit" type="primary" size="large" onClick={this.updateOk}>确定</Button>,
                <Button key="back" type="ghost" size="large" onClick={this.updateCancel}>取消</Button>
              ]}
          >
            {userUpdate}
          </Modal>
          <Modal title="查看角色" visible={this.state.lookCon}
                 onOk={this.lookOk} onCancel={this.lookCancel}
                 footer={[
                <Button key="submit" type="primary" size="large" onClick={this.lookOk}>确定</Button>,
                <Button key="back" type="ghost" size="large" onClick={this.lookCancel}>取消</Button>
              ]}
          >
            {userLook}
          </Modal>
        </div>
      </div>
    );
  }
});
Demo = createForm()(Demo);
export default Demo;
