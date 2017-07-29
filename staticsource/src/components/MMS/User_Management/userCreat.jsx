import React, { Component, PropTypes } from 'react';
import { Radio, Button, Form, Input, message, Select} from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import { getUserGroupList, getRoleList, addUserMsg } from '../../../services/api.js';


const createForm = Form.create;
const Option = Select.Option;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;

let userCreat = React.createClass({
  //	页面初始化
  getInitialState(){
    this.props.form.resetFields();
    return {
      userGroupListData:[],
      roleListData:[],
      roleIdList:[],
      defaultID:""
    }
  },

  /*初始化表单*/
  componentDidMount() {
    getUserGroupList().then((msg)=>{
      this.setState({
        userGroupListData:msg.jsonResult.data,
        defaultID:msg.jsonResult.data[0].groupId
      });
    });
  },


  /*新建用户组*/
  parentUGSelect(val){
    getRoleList(val).then((msg)=>{
      this.setState({
        roleListData:msg.jsonResult.data.result,
        roleIdList:msg.jsonResult.data.roleStr
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
        'userName':values.userName,
        'password':values.userPsw,
        'idCard':values.userIdCard,
        'stateCd':values.userStatus,
        'groupId':values.userParentGroup,
        'roleId':values.userRoleList.join(","),
        'userDesc':values.userRemark,
        "memberType":values.userIdType
      };
      addUserMsg(addMsg).then((msg)=>{
        if (!msg.jsonResult.success){
          message.error(msg.jsonResult.resultMsg);
        }else{
          message.success(msg.jsonResult.data);
          this.setState({ roleIdList:[] });
          this.props.callback(msg);
          this.props.form.resetFields();
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
  //0706修改用户名验证规则  同邮箱验证
  userExists(rule,value,callback) {
    if (!value) {
      callback();
    } else {
      let reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
      if(!reg.test(value)){
        callback([new Error('请填写邮箱格式！')]);
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
    const { getFieldProps } = this.props.form;

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

    return (
      <div>
        <Form onSubmit={this.addOk} className="login-form">
          <FormItem label="用户名称" hasFeedback>
            <Input type="text" placeholder="请输入用户名称（必填）"
              {...getFieldProps('userName',{rules:[{ required: true, message: '请填写正确的用户名称！'},{validator: this.userExists}]})}
            />
          </FormItem>
          <FormItem label="用户证件类型">
            <Select size="large" notFoundContent="请选择" placeholder="请选择"
              {...getFieldProps('userIdType',{initialValue:"身份证"})}
            >
              <Option value="身份证">身份证</Option>
              <Option value="其他">其他</Option>
            </Select>
          </FormItem>
          <FormItem label="用户身份证号码" hasFeedback>
            <Input type="text" placeholder="请输入身份证号码（必填）"
              {...getFieldProps('userIdCard',{rules:[{ required: true, message: '请填写正确的身份证号码！！'},{validator: this.userIdNum}]})}
            />
          </FormItem>
          <FormItem label="所属用户组">
            <Select size="large" notFoundContent="请选择" placeholder="请选择"
              {...getFieldProps('userParentGroup',{rules:[{ required: true, message: '请选择所属用户组！'}],onChange:this.parentUGSelect})}>
              {userGroupList}
            </Select>
          </FormItem>
          <FormItem label="所属角色">
            <Select size="large" multiple disabled notFoundContent="请选择" placeholder="请选择"
              {...getFieldProps('userRoleList',{initialValue: this.state.roleIdList})}
            >
              {roleList}
            </Select>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup {...getFieldProps('userStatus', { initialValue: '1' })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" {...getFieldProps('userRemark', { initialValue: '' })}/>
          </FormItem>
        </Form>
        <Button key="submit" type="primary" size="large" onClick={this.addOk}>确定</Button>
      </div>
    );
  }
});

userCreat = createForm()(userCreat);
export default userCreat;
