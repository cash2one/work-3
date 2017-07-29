import React, { Component, PropTypes } from 'react';
import {Button, Form,  Input,  Table, message,  Row, Col, DatePicker,Select } from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import { getLogMsg, getAllSystem } from '../../../services/api.js';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const RangePicker = DatePicker.RangePicker;

let Demo = React.createClass({

  //	页面初始化
  getInitialState(){
    return {
      loading:true,
      selectedRowKeys: [],
      hotCustData:[],
      userID:sessionStorage.getItem("userId"),
      startDate:"",
      endDate:"",
      systemList:[]
    }
  },

	/*初始化表单*/
  componentDidMount() {
    this.props.form.resetFields();
    let obj={
      "systemPlatformName":""
    };
    getLogMsg(obj).then((msg)=>{
      let msgText = msg.jsonResult.data.list;
      for(let i = 0; i < msgText.length; i++){
        if (msgText[i].method == "insert"){
          msgText[i].method = "增加";
        }else if(msgText[i].method == "update"){
          msgText[i].method = "修改";
        }else if(msgText[i].method == "delete"){
          msgText[i].method = "删除";
        }else if(msgText[i].method == "login"){
          msgText[i].method = "登陆";
        }else{
          msgText[i].method = " ";
        }
      }
      if (!msg.jsonResult.success){
        message.error(msg.jsonResult.resultMsg);
        this.setState({ loading:true });
      }else {
        this.setState({
          loading:false,
          hotCustData:msgText
        });
      }
    });

    //获取系统列表
    getAllSystem().then((msg)=>{
      this.setState({systemList:msg.jsonResult.data})
    });
  },
  onChange(dateString) {
    this.setState({startDate: dateString[0],endDate:dateString[1]});
  },
  /*查询系统*/
  searchBtn (e){
    e.preventDefault();
    let data = this.props.form.getFieldsValue();
    this.setState({ loading:true });
    if (data.systemPlatformName == "请选择" ||data.systemPlatformName == "" ){
      data.systemPlatformName = null;
    }
    if (data.method == "请选择" ||data.method == "" ){
      data.method = null;
    }
    if (data.userName == undefined ||data.userName == "" ){
      data.userName = null;
    }
    if (data.message == undefined ||data.message == "" ){
      data.message = null;
    }
    if (data.resourceName == undefined ||data.resourceName == "" ){
      data.resourceName = null;
    }
    let searchMsg={
      "typeId":data.typeId,
      "systemPlatformName":data.systemPlatformName,
      "userName":data.userName,
      "message": data.message,
      "startDate":this.state.startDate,
      "endDate":this.state.endDate,
      "resourceName":data.resourceName,
      "method":data.method
    };
    getLogMsg(searchMsg).then((msg)=>{
      let msgText = msg.jsonResult.data.list;
      for(let i = 0; i < msgText.length; i++){
        if (msgText[i].method == "insert"){
          msgText[i].method = "增加";
        }else if(msgText[i].method == "update"){
          msgText[i].method = "修改";
        }else if(msgText[i].method == "delete"){
          msgText[i].method = "删除";
        }else if(msgText[i].method == "login"){
          msgText[i].method = "登陆";
        }else{
          msgText[i].method = " ";
        }
      }
      if (!msg.jsonResult.success){
        message.error(msg.jsonResult.resultMsg);
        this.setState({ loading:true });
      }else {
        this.setState({
          loading:false,
          hotCustData:msgText
        });
      }
    });
  },
  /*查询系统 END*/

  render() {
    const { getFieldProps } = this.props.form;
    //用于页面内容显示的table
    const columns = [
      { title: '操作人', dataIndex: 'userName', key: 'userName' },
      { title: '操作类型', dataIndex: 'method', key: 'method' },
      { title: '操作描述', dataIndex: 'message', key: 'message' },
      { title: '资源名称', dataIndex: 'resourceName', key: 'resourceName' },
      { title: '所属系统', dataIndex: 'systemPlatformName', key: 'systemPlatformName' },
      { title: '操作时间', dataIndex: 'createTime', key: 'createTime' }
    ];

    /*分页控制*/
  	const hotPage = {
      total: this.state.hotCustData.length,
      showSizeChanger: true
    };

    const formItemLayout = {
      labelCol: { span: 5 },
      wrapperCol: { span: 19 }
    };

    const systemList = this.state.systemList.map((items,index)=>{
      return (
        <Option key={index} value={items.systemPlatformName}>{items.systemPlatformName}</Option>
      )
    });

    return (
      <div>
        <Form horizontal className="ant-advanced-search-form" onSubmit={this.searchBtn}>
          <Row gutter={16}>
            <Col sm={8}>
              <FormItem {...formItemLayout} label="资源类型">
                <Select size="large" {...getFieldProps('typeId',{initialValue:""})} >
                  <Option value="">请选择</Option>
                  <Option value="页面资源">页面类型</Option>
                  <Option value="菜单">菜单类型</Option>
                  <Option value="角色">角色类型</Option>
                  <Option value="系统">系统类型</Option>
                  <Option value="用户">用户类型</Option>
                  <Option value="用户组">用户组类型</Option>
                </Select>
              </FormItem>
              <FormItem {...formItemLayout} label="资源名称">
                <Input {...getFieldProps('resourceName')} onPressEnter={this.searchBtn} placeholder="请输入搜索名称" size="large" />
              </FormItem>
              <FormItem {...formItemLayout} label="时间">
                <RangePicker format="yyyy/MM/dd"  {...getFieldProps('data',{onChange:this.onChange})}/>
              </FormItem>
            </Col>
            <Col sm={8}>
              <FormItem {...formItemLayout} label="操作人">
                <Input {...getFieldProps('userName')} onPressEnter={this.searchBtn} placeholder="请输入搜索名称" size="large" />
              </FormItem>
              <FormItem {...formItemLayout} label="所属系统">
                <Select size="large" {...getFieldProps('systemPlatformName',{initialValue:""})} >
                  <Option value="">请选择</Option>
                  {systemList}
                </Select>
              </FormItem>
            </Col>
            <Col sm={8}>
              <FormItem {...formItemLayout} label="日志信息" >
                <Input {...getFieldProps('message')} onPressEnter={this.searchBtn} placeholder="请输入搜索名称" size="large" />
              </FormItem>
              <FormItem {...formItemLayout} label="操作类型" >
                <Select size="large" {...getFieldProps('method',{initialValue:""})} >
                  <Option value="">请选择</Option>
                  <Option value="update">修改</Option>
                  <Option value="delete">删除</Option>
                  <Option value="insert">增加</Option>
                </Select>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span={12} offset={12} style={{ textAlign: 'right' }}>
              <Button key="submit" type="primary" size="large" onClick={this.searchBtn}>搜索</Button>
            </Col>
          </Row>
        </Form>

        <div style={{width:"100%"}} className="logCss">
          <Table columns={columns} loading={this.state.loading} dataSource={this.state.hotCustData} pagination={hotPage} style={{textAlign:"left"}}/>
        </div>
      </div>
    );
  }
});
Demo = createForm()(Demo);
export default Demo;
