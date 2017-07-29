import React, {} from 'react';
import { Radio, Button, Form, Icon, Input, message, Select, Tooltip } from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import { getParentUserGroupMsg, getSystemListMsg, getRoleListMsg, getAllRoleListMsg, addUserGroup } from '../../../services/api.js';

const createForm = Form.create;
const Option = Select.Option;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;

let userGroupCreat = React.createClass({
  //	页面初始化
  getInitialState(){
    this.props.form.resetFields();
    return {
      userGroupListData:[],
      systemListData:[],
      roleListData:[],
      allRoleList:[],
      defaultRoleList:[]
    }
  },

  /*初始化表单*/
  componentDidMount() {
    //获取用户组列表
    getParentUserGroupMsg().then((msg)=>{
      this.setState({ userGroupListData:msg.jsonResult.data });
    });
    //获取系统列表
    getSystemListMsg().then((msg)=>{
      this.setState({ systemListData:msg.jsonResult.data });
    })
  },

  /*新建用户组*/
  parentSystem(val){
    getRoleListMsg(val).then((msg)=>{
      this.setState({ roleListData:msg.jsonResult.data });
    })
  },

  getAllRoleListMsg(val){
    getAllRoleListMsg(val).then((msg)=>{
      this.setState({
        allRoleList:msg.jsonResult.data.result,
        defaultRoleList:msg.jsonResult.data.roleStr
      });
    })
  },
  addOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        console.log('Errors in form!!!');
        return;
      }
      let addMsg={
        'groupName':values.userGroupName,
        'stateCd':values.userGroupStatus,
        'parentGroupId':values.parentUserGroup,
        'roleId':values.selectUsers.join(","),
        'groupDesc':values.userGroupRemark
      };
      addUserGroup(addMsg).then((msg)=>{
        if (!msg.jsonResult.success){
          message.error(msg.jsonResult.resultMsg);
        }else{
          message.success(msg.jsonResult.data);
          this.props.callback(msg);
          this.props.form.resetFields();
          this.setState({
            allRoleList:[],
            defaultRoleList:[]
          });
          //获取用户组列表
          getParentUserGroupMsg().then((msg)=>{
            this.setState({ userGroupListData:msg.jsonResult.data });
          });
        }
      })
    });
  },
  addCancel() {
    this.setState({ addCust: false });
  },
  /*新建用户组 END*/

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

  render() {
    const { getFieldProps } = this.props.form;

    /*新建菜单*/
    //渲染用户组列表
    const userGroupList = this.state.userGroupListData.map((items,index)=>{
      return (
        <Option key={index} value={items.groupId}>{items.groupName}</Option>
      )
    });
    //渲染系统列表
    const systemList = this.state.systemListData.map((items,index)=>{
      return (
        <Option key={index} value={items.System_Platform_CD}>{items.System_Platform_Name}</Option>
      )
    });
    //渲染角色列表
    const roleList = this.state.roleListData.map((items,index)=>{
      return (
        <Option key={index} value={items.roleId}>{items.roleName}</Option>
      )
    });
    //渲染所属角色列表
    const allRoleList = this.state.allRoleList.map((items,index)=>{
      return (
        <Option key={index} value={items.roleId}>{items.roleName}</Option>
      )
    });
    /*新建菜单 END*/
    return (
      <div>
        <Form onSubmit={this.addOk} className="login-form">
          <FormItem label="用户组名称" hasFeedback>
            <Input type="text" placeholder="请输入用户组名称（必填）"
              {...getFieldProps('userGroupName',{rules:[{ required: true, message: '请填写正确的用户组名称！'},{validator: this.userExists}]})}
            />
          </FormItem>
          <FormItem label="上级用户组">
            <Select size="large"  {...getFieldProps('parentUserGroup',{initialValue:"null",onChange:this.getAllRoleListMsg})}>
              <Option id="null" value="null">不选择</Option>
              {userGroupList}
            </Select>
          </FormItem>
          <FormItem label="继承角色">
            <Select size="large" multiple disabled
              {...getFieldProps('selectDefaultUsers',{initialValue: this.state.defaultRoleList})}
            >
              {allRoleList}
            </Select>
          </FormItem>
          <FormItem hasFeedback
                    label={<span>所属系统 <Tooltip title="请选择系统，根据所选系统筛选角色"><Icon type="question-circle-o" /></Tooltip></span>}
          >
            <Select size="large" multiple placeholder="请选择"
              {...getFieldProps('parentSystem',{rules:[{ required: true, message: '请选择所属系统！',type: 'array'}],onChange:this.parentSystem})}
            >
              {systemList}
            </Select>
          </FormItem>
          <FormItem label="添加角色" hasFeedback>
            <Select size="large" multiple notFoundContent="请选择" placeholder="请选择"
              {...getFieldProps('selectUsers',{rules:[{ required: true, message: '请选择角色！',type: 'array'}]})}
            >
              {roleList}
            </Select>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup {...getFieldProps('userGroupStatus', { initialValue: '1' })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" max="250" {...getFieldProps('userGroupRemark', { initialValue: '' })}/>
          </FormItem>
        </Form>
        <Button key="submit" type="primary" size="large" onClick={this.addOk}>确定</Button>
      </div>
    );
  }
});
userGroupCreat = createForm()(userGroupCreat);
export default userGroupCreat;
