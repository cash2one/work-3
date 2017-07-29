import React, { Component, PropTypes } from 'react';
import {Radio, Button, Form, Input, Modal, Table, message, Select } from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import SystemCreat from './systemCreat.jsx';
import {  getCustomerMsg, updateSystem, removeSystem, getBtnLists } from '../../../services/api.js';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
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
      hotCustData:[],
      menuIDStr:sessionStorage.getItem("menuID"),
      searchVal:""
    }
  },

	/*初始化表单*/
  componentDidMount() {
    this.props.form.resetFields();
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
    let obj={
      "systemPlatformName":''
    };
    getCustomerMsg(obj).then((msg)=>{
      if (!msg.jsonResult.success){
        message.error(msg.jsonResult.resultMsg);
        this.setState({ loading:true });
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
      })
    },

  /*新建系统资源*/
  addBtn(){
    this.setState({ addCust: true });
  },
  getReturnData:function(result){
    this.setState({ addCust: false });
    let obj={
      "systemPlatformName":''
    };
    getCustomerMsg(obj).then((msg)=>{
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
    removeSystem(this.state.userMsg[0].systemPlatformCd).then((data)=>{
      if (!data.jsonResult.success) {
        message.error(data.jsonResult.resultMsg);
      }else{
        message.success(data.jsonResult.data);
        this.setState({
          delete: false,
          selectedRowKeys:[]
        });
        let obj={
          "systemPlatformName":''
        };
        getCustomerMsg(obj).then((msg)=>{
          this.setState({ hotCustData:msg.jsonResult.data.list });
        });
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
    }
  },
  updateOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        console.log('Errors in form!!!');
        return;
      }

      let systemMsg={
        stateCd:values.status,
        systemPlatformCd:this.state.userMsg[0].systemPlatformCd,
        systemPlatformDesc:values.remark,
        systemPlatformName:values.systemName
      };
      updateSystem(systemMsg).then((msg)=>{
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
            "systemPlatformName":''
          };
          getCustomerMsg(obj).then((msg)=>{
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
    let obj={
      "systemPlatformName":e.target.value
    };
    getCustomerMsg(obj).then((msg)=>{
      this.setState({ hotCustData:msg.jsonResult.data.list });
    });
  },

  /*查询系统 END*/

  /*表单校验规则*/
  //新建时，校验用户组名规则
  systemExists(rule, value, callback) {
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
  /*表单校验规则 END*/

  render() {
    /*表单初始化*/
    const { getFieldProps } = this.props.form;
    const {  selectedRowKeys } = this.state;

    //用于页面内容显示的table
    const columns = [
      { title: '编码', dataIndex: 'systemPlatformCd', key: 'systemPlatformCd' },
      { title: '系统名称', dataIndex: 'systemPlatformName', key: 'systemPlatformName' },
      { title: '描述', dataIndex: 'systemPlatformDesc', key: 'systemPlatformDesc' },
      { title: '状态', dataIndex: 'stateCd', key: 'stateCd' },
      { title: '创建人', dataIndex: 'userName', key: 'userName' },
      { title: '创建时间', dataIndex: 'createDt', key: 'createDt' }
    ];

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
    const userUpdate = this.state.userMsg.map((items,index)=>{
       return (
      <Form onSubmit={this.updateOk} className="login-form" key={index}>
        <FormItem label="名称" hasFeedback>
          <Input type="text" placeholder="请输入系统名称（选填）"
            {...getFieldProps('systemName',{initialValue: items.systemPlatformName,rules:[{ required: true, message: '请填写正确的系统名称！'},{validator: this.systemExists}] })}/>
        </FormItem>
        <FormItem label="编码" hasFeedback>
          <Input type="text" placeholder="请输入系统编码（选填）" disabled {...getFieldProps('systemNum',{initialValue: items.systemPlatformCd,rules:[{ required: true, message: '请填写正确的系统编码！'},{validator: this.systemNum}] })}/>
        </FormItem>
        <FormItem label="状态" >
          <RadioGroup {...getFieldProps('status', { initialValue: items.stateCd })}>
            <Radio value="1">激活</Radio>
            <Radio value="0">失效</Radio>
          </RadioGroup>
        </FormItem>
        <FormItem label="描述" >
          <Input type="textarea" {...getFieldProps('remark', { initialValue: items.systemPlatformDesc })}/>
        </FormItem>
      </Form>
      )
    });
    /*修改系统 数据展示 END*/

    /*查看系统 数据展示*/
    const userLook = this.state.userMsg.map((items,index)=>{
      return (
        <Form onSubmit={this.lookOk} className="login-form" key={index}>
          <FormItem label="名称" >
            <Input type="text" placeholder="请输入系统名称（选填）" disabled={true} {...getFieldProps('lookSystemName',{ initialValue: items.systemPlatformName })}/>
          </FormItem>
          <FormItem label="编码">
            <Input type="text" placeholder="请输入系统编码（选填）" disabled={true} {...getFieldProps('lookSystemNum',{ initialValue: items.systemPlatformCd })}/>
          </FormItem>
          <FormItem label="状态" >
            <RadioGroup  disabled={true} {...getFieldProps('lookStatus', { initialValue: items.stateCd })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" disabled={true} {...getFieldProps('lookRemark', { initialValue: items.systemPlatformDesc })}/>
          </FormItem>
        </Form>
      )
    });
    /*查看系统 数据展示 END*/

    /*查询条件*/
    const selectBefore = (
      <Select defaultValue="system" style={{ width: 120,color:"#000" }} onChange={this.searchType}>
        <Option value="system">系统名称</Option>
      </Select>
    );
    /*查询条件 END*/

    return (
      <div>
        <div style={{width:"100%",height:"40px",lineHeight:"40px",marginBottom:"5px"}}>
          <Input placeholder="请输入系统名称，按下回车进行搜索" size="large"
                 onPressEnter={this.searchBtn} onChange={this.searchBtn}
                 addonBefore={selectBefore}
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
            <Table rowKey='systemPlatformCd' columns={columns} rowSelection={rowSelection} loading={this.state.loading} dataSource={this.state.hotCustData} pagination={hotPage}/>
          </div>
        </div>
        <Modal title="新建系统" visible={this.state.addCust}
               onCancel={this.addCancel}
               footer={[]}
        >
          <SystemCreat callback = {this.getReturnData}/>
        </Modal>

        <Modal title="修改系统" visible={this.state.updateCon}
                       onOk={this.updateOk} onCancel={this.updateCancel}
                       footer={[
                <Button key="submit" type="primary" size="large" onClick={this.updateOk}>确定</Button>,
                <Button key="back" type="ghost" size="large" onClick={this.updateCancel}>取消</Button>
              ]}
        >
          {userUpdate}
        </Modal>

        <Modal title="查看系统" visible={this.state.lookCon}
               onOk={this.lookOk} onCancel={this.lookCancel}
               footer={[
                <Button key="submit" type="primary" size="large" onClick={this.lookOk}>确定</Button>,
                <Button key="back" type="ghost" size="large" onClick={this.lookCancel}>取消</Button>
              ]}
        >
          {userLook}
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
    );
  }
});

Demo = createForm()(Demo);
export default Demo;
