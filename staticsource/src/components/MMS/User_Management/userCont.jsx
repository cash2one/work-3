import React, { Component, PropTypes } from 'react';
import { Radio, Button, Form, Input, Modal, Table, message, Select} from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import UserCreat from './userCreat.jsx';
import { getUserMsg, getUserGroupList, getRoleList,  deleteUserMsg, getUpdateUserMsg, getUserGroupListsMsg, updateUserMsg, getBtnLists} from '../../../services/api.js';

const createForm = Form.create;
const Option = Select.Option;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;

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
      userGroupListData:[],
      roleListData:[],
      updateList:[],
      updateGroupRoles:[],
      roleIdList:[],
      defaultID:"",
	    userGroups:[],
      menuIDStr : sessionStorage.getItem("menuID")
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

    //获取用户列表
    getUserMsg(obj).then((msg)=>{
      if (!msg.jsonResult.success){
        this.setState({ loading:true });
        message.error(msg.jsonResult.resultMsg);
      }else {
        for (let i = 0; i < msg.jsonResult.data.resultList.length; i++){
          let roleList = msg.jsonResult.data.resultList[i].roleNames.join("，");
          msg.jsonResult.data.resultList[i].roleNames = roleList;
        }
        this.setState({
          loading:false,
          hotCustData:msg.jsonResult.data.resultList
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

  searchBtn (e){
    let obj={
      "queryUserName":e.target.value
    };
    getUserMsg(obj).then((msg)=>{
      this.setState({ hotCustData:msg.jsonResult.data.resultList });
    });
  },

  /*新建用户*/
  addBtn(){
    this.setState({ addCust: true });
    //获取用户组列表
    getUserGroupList().then((msg)=>{
      this.setState({
        userGroupListData:msg.jsonResult.data,
        defaultID:msg.jsonResult.data[0].groupId
      });
    });
  },
  getReturnData:function(result){
    let obj={
      "systemPlatformName":""
    };
    getUserMsg(obj).then((msg)=>{
      this.setState({
        loading:false,
        addCust: false,
        hotCustData:msg.jsonResult.data.resultList,
        roleListData:[],
        roleIdList:[]
      });
    });
  },
  addCancel() {
    this.setState({ addCust: false });
  },
  /*新建用户 END*/

  /*删除用户*/
  deleteBtn(){
    if (this.state.userMsg == 0) {
      message.warning ("请选择至少一条数据！");
    }else {
      this.setState({ delete: true });
    }
  },
  deleteOk() {
    deleteUserMsg(this.state.userMsg[0].systemUserId).then((data)=>{
      if (!data.jsonResult.success) {
        message.error("信息删除失败！");
      }else{
        message.success("信息删除成功！");
        this.setState({
          delete: false,
          selectedRowKeys:[]
        });
        let obj={
          "systemPlatformName":""
        };
        getUserMsg(obj).then((msg)=>{
          this.setState({
            loading:false,
            addCust: false,
            hotCustData:msg.jsonResult.data.resultList
          })
        });
      }
    })
  },
  deleteCancel() {
    this.setState({ delete: false });
  },
  /*删除用户 END*/

  /*修改用户*/
  updateBtn(){
    if (this.state.userMsg == "") {
      message.warning('请选择至少一条数据！');
    }else {
      this.setState({ updateCon: true });
      getUpdateUserMsg(this.state.userMsg[0].systemUserId).then((msg)=>{
        this.setState({
          updateList:[msg.jsonResult.data.userInfo],
          updateGroupRoles:msg.jsonResult.data.userRoles,
          roleListData:msg.jsonResult.data.roles,
		      userGroups:msg.jsonResult.data.userGroups
        });
      });
      //获取用户组列表
      getUserGroupListsMsg().then((msg)=>{
        this.setState({ userGroupListData:msg.jsonResult.data });
      });
    }
  },
  updateUGSelect(val){

    getRoleList(val.join(",")).then((msg)=>{
      this.setState({
        roleListData:msg.jsonResult.data.result,
        updateGroupRoles:msg.jsonResult.data.roleStr
      });
    });
  },
  updateOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        console.log('Errors in form!!!');
        return;
      }
      let userName = values.updateUserName;
      if (userName == this.state.userMsg[0].userName){
        userName = null;
      }
      let updateMsg={
        "systemUserId":this.state.userMsg[0].systemUserId,
        'userName':userName,
        'password':values.updateUserPsw,
        'idCard':values.updateUserIDCard,
        'stateCd':values.updateUserStatus,
        'groupId':values.updateUGParent,
        'roleId':values.updateUserRoles.join(","),
        'userDesc':values.updateUserRemark,
        "memberType":values.updateUserIdType
      };
      updateUserMsg(updateMsg).then((msg)=>{
        if (!msg.jsonResult.success) {
          message.error("修改失败！"+ msg.jsonResult.resultMsg);
        }else{
          message.success("修改成功！");
          this.setState({
            updateCon: false,
            selectedRowKeys:[],
            userMsg:[]
          });
          let obj={
            "systemPlatformName":""
          };
          getUserMsg(obj).then((msg)=>{
            this.setState({
              loading:false,
              updateCon: false,
              hotCustData:msg.jsonResult.data.resultList
            });
          });
          this.props.form.resetFields();
        }
      })
    });

  },
  updateCancel() {
    this.setState({ updateCon: false });
  },
  /*修改用户 END*/
  /*查看*/
  lookBtn(){
    if (this.state.userMsg == "") {
      message.warning('请选择至少一条数据！');
    }else {
      this.setState({ lookCon: true });
      getUpdateUserMsg(this.state.userMsg[0].systemUserId).then((msg)=>{
        this.setState({
          updateList:[msg.jsonResult.data.userInfo],
          updateGroupRoles:msg.jsonResult.data.userRoles,
          roleListData:msg.jsonResult.data.roles,
          userGroups:msg.jsonResult.data.userGroups
        });
      });
      //获取用户组列表
      getUserGroupListsMsg().then((msg)=>{
        this.setState({ userGroupListData:msg.jsonResult.data });
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
  /*查看 END*/
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
  systemNum(rule, value, callback) {
    if (!value) {
      callback();
    } else {
      let pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
      let re = new RegExp("[^\u4e00-\u9fa5]");
      if (pattern.test(value)) {
        callback([new Error('请输入正确的系统编码！')]);
      }else if(!re.test(value)){
        callback([new Error('请输入英文或数字！')]);
      }else {
        callback();
      }
    }
  },
  userPsw(rule, value, callback){
    if (!value) {
      callback();
    } else {
      let re = new RegExp("[^\u4e00-\u9fa5]");
      if(!re.test(value)){
        callback([new Error('请输入英文或数字！')]);
      }else {
        callback();
      }
    }
  },
  userIdNum(rule, value, callback){
    if (!value) {
      callback();
    } else {
      let re = new RegExp("[^\u4e00-\u9fa5]");
      let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
      if(!re.test(value) || !reg.test(value)){
        callback([new Error('请输入正确的身份证号码！')]);
      }else {
        callback();
      }
    }
  },
  /*表单校验规则 END*/

  render() {
    //用于页面内容显示的table
    const columns = [
      { title: '用户名称', dataIndex: 'userName', key: 'userName' },
      { title: '角色', dataIndex: 'roleNames', key: 'roleNames' },
      { title: '状态', dataIndex: 'stateCd', key: 'stateCd' },
      { title: '创建人', dataIndex: 'createUserName', key: 'createUserName' },
      { title: '创建时间', dataIndex: 'dateStr', key: 'dateStr' }
    ];

    const {  selectedRowKeys } = this.state;
    const { getFieldProps } = this.props.form;

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

    /*查询条件*/
    const selectBefore = (
      <Select defaultValue="userGroup" style={{ width: 120 }} onChange={this.searchType}>
        <Option value="userGroup">用户名称</Option>
      </Select>
    );
    /*查询条件*/

    /*新建菜单*/
    //渲染用户组列表
    const userGroupList = this.state.userGroupListData.map((items,index)=>{
      return (
        <Option key={index} value={items.groupId}>{items.groupName}</Option>
      )
    });
    //渲染角色列表
    const roleList = this.state.roleListData.map((items,index)=>{
      return (
        <Option key={index} value={items.roleId}>{items.roleName}</Option>
      )
    });
    /*新建菜单 END*/

    /*修改系统 数据展示*/
    const userUpdate = this.state.updateList.map((items,index)=>{
      return (
        <Form onSubmit={this.updateOk} className="login-form" key={index}>
          <FormItem label="用户名称" hasFeedback>
            <Input type="text" placeholder="请输入用户名称（必填）"
              {...getFieldProps('updateUserName',{initialValue:items.userName,rules:[{ required: true, message: '请填写正确的用户名称！'},{validator: this.userExists}]})}
            />
          </FormItem>
          <FormItem label="用户证件类型">
            <Select size="large" notFoundContent="请选择" placeholder="请选择"
              {...getFieldProps('updateUserIdType',{initialValue:items.memberType})}
            >
              <Option value="身份证">身份证</Option>
              <Option value="其他">其他</Option>
            </Select>
          </FormItem>
          <FormItem label="用户身份证号码" hasFeedback>
            <Input type="text" placeholder="请输入用户身份证号码（必填）"
              {...getFieldProps('updateUserIDCard',{initialValue:items.idCard,rules:[{ required: false, message: '请填写正确的身份证号码！！'},{validator: this.userIdNum}]})}
            />
          </FormItem>
          <FormItem label="所属用户组" hasFeedback>
            <Select size="large" multiple
              {...getFieldProps('updateUGParent',{initialValue:this.state.userGroups,rules:[{ required: true, message: '请选择所属用户组！',type: 'array'}],onChange:this.updateUGSelect})}
            >
              {userGroupList}
            </Select>
          </FormItem>
          <FormItem label="所属角色" hasFeedback>
            <Select size="large" multiple disabled
              {...getFieldProps('updateUserRoles',{initialValue:this.state.updateGroupRoles})}
            >
              {roleList}
            </Select>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup {...getFieldProps('updateUserStatus', { initialValue: items.state })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea"  {...getFieldProps('updateUserRemark', { initialValue:items.userDesc })}/>
          </FormItem>
        </Form>
      )
    });
    /*修改系统 数据展示 END*/
    /*查看系统 数据展示*/
    const userLook = this.state.updateList.map((items,index)=>{
      return (
        <Form onSubmit={this.lookOk} className="login-form" key={index}>
          <FormItem label="用户名称" >
            <Input type="text" disabled {...getFieldProps('lookUserName',{initialValue:items.userName})}/>
          </FormItem>
          <FormItem label="用户证件类型">
            <Select size="large" disabled
              {...getFieldProps('updateUserIdType',{initialValue:items.memberType})}
            >
              <Option value="身份证">身份证</Option>
              <Option value="其他">其他</Option>
            </Select>
          </FormItem>
          <FormItem label="用户身份证号码" >
            <Input type="text" disabled {...getFieldProps('lookUserIDCard',{initialValue:items.idCard})}/>
          </FormItem>
          <FormItem label="所属用户组">
            <Select size="large" multiple disabled
                    {...getFieldProps('lookUGParent',{initialValue:this.state.userGroups})}
            >
              {userGroupList}
            </Select>
          </FormItem>

          <FormItem label="所属角色">
            <Select size="large" multiple disabled
              {...getFieldProps('lookUserRoles',{initialValue:this.state.updateGroupRoles})}
            >
              {roleList}
            </Select>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup disabled {...getFieldProps('lookUserStatus',  { initialValue: items.state })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" disabled {...getFieldProps('lookUserRemark', { initialValue:items.userDesc })}/>
          </FormItem>
        </Form>
      )
    });
    /*查看系统 数据展示 END*/

    return (
      <div>
        <div style={{width:"100%",height:"40px",lineHeight:"40px"}}>
          <Input placeholder="请输入系统名称，按下回车进行搜索" size="large"
                 onPressEnter={this.searchBtn} onChange={this.searchBtn}
                 addonBefore={selectBefore} style={{height:"33px"}}
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
            <Table rowKey='systemUserId' columns={columns} loading={this.state.loading}  rowSelection={rowSelection} dataSource={this.state.hotCustData} pagination={hotPage}/>
          </div>
          <Modal title="新建用户" visible={this.state.addCust}
                 onCancel={this.addCancel}
                 footer={[]}
          >
            <UserCreat callback = {this.getReturnData}/>
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
        </div>
        <Modal title="修改用户" visible={this.state.updateCon}
               onOk={this.updateOk} onCancel={this.updateCancel}
               footer={[
                <Button key="submit" type="primary" size="large" onClick={this.updateOk}>确定</Button>,
                <Button key="back" type="ghost" size="large" onClick={this.updateCancel}>取消</Button>
              ]}
        >
          {userUpdate}
        </Modal>
        <Modal title="查看用户" visible={this.state.lookCon}
               onOk={this.lookOk} onCancel={this.lookCancel}
               footer={[
                <Button key="submit" type="primary" size="large" onClick={this.lookOk}>确定</Button>,
                <Button key="back" type="ghost" size="large" onClick={this.lookCancel}>取消</Button>
              ]}
        >
          {userLook}
        </Modal>
      </div>
    );
  }
});

Demo = createForm()(Demo);
export default Demo;
