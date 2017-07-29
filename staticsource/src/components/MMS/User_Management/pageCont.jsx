import React, { Component, PropTypes } from 'react';
import { Button, Form, Input, Modal, Table, message, Radio, Select } from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import PageCreat from './pageCreat.jsx';
import { getMenuSystem, getParentMenuName, getPageTypeList, getPageMsg, updatePage, removePage,  getBtnLists, getPageType } from '../../../services/api.js'

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
      typeMark:false,
      userMsg: [],
      hotCustData:[],
      menuSystem:[],
      parentMenu:[],
      pageTypeList:[],
      menuIDStr:sessionStorage.getItem("menuID"),
      searchVal:"",
      pageAllType:[],
      initialParentMenu:[]
    }
  },


  /*初始化表单*/
  componentDidMount() {
    this.props.form.resetFields();
    let obj={
      "resName":"",
      "menuName":"",
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

    getPageMsg(obj).then((msg)=>{
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

  /*新建系统资源*/
  addBtn(){
    this.setState({ addCust: true });
  },
  getReturnData:function(result){
    this.setState({ addCust: false });
    let obj={
      "resName":"",
      "menuName":"",
      "systemPlatformName":""
    };
    getPageMsg(obj).then((msg)=>{
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
    removePage(this.state.userMsg[0].privilegeResId).then((data)=>{
      if (data.jsonResult.success) {
        message.success(data.jsonResult.data);
        this.setState({
          delete: false,
          selectedRowKeys:[]
        });
        let obj={
          "resName":"",
          "menuName":"",
          "systemPlatformName":""
        };
        getPageMsg(obj).then((msg)=>{
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
      /*根据系统，获取菜单列表*/
      let val = {
        "systemPlatformCd":this.state.userMsg[0].systemPlatformCd,
        "stateCd":"1"
      };
      getParentMenuName(val).then((msg)=>{
        this.setState({ parentMenu:msg.jsonResult.data.list });
      });
      getPageTypeList().then((msg)=>{
        this.setState({ pageAllType:msg.jsonResult.data });
      });
      let vals = {"stateCd":"1"};
      getMenuSystem(vals).then((msg)=>{
        this.setState({ menuSystem:msg.jsonResult.data.list });
      });
      this.setState({ initialParentMenu:this.state.userMsg[0].menuId});

    }
  },
  updSystemSelect(e){
    /*根据系统，获取菜单列表*/
    let val = {
      "systemPlatformCd":e,
      "stateCd":"1"
    };
    getParentMenuName(val).then((msg)=>{
      this.setState({ parentMenu:msg.jsonResult.data.list });
      if (msg.jsonResult.data.list.length == 0){
        this.setState({ initialParentMenu:[] });
      }else{
        this.setState({ initialParentMenu:msg.jsonResult.data.list[0].menuId });
      }
    });
  },
  updateOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        return;
      }
      let resName = values.updatePageName;
      let resUrl = values.updatePageUrl;
      let pageResTypeName = values.updatePageResTypeName;
      let mark = values.pageResTypeMark;

      if (resName == this.state.userMsg[0].resName){
        resName = null;
      }
      if (resUrl == this.state.userMsg[0].url){
        resUrl = null;
      }
      if (pageResTypeName != "da8fe8e2fae444848142f137bf7ff675"){
        mark = null
      }

      let updateMenuMsg={
        "privilegeResId":this.state.userMsg[0].privilegeResId,
        "resName":resName,
        "url":resUrl,
        "stateCd":values.updatePageStatus,
        "menuId":values.updatePageParentMenu,
        "resTypeCd":pageResTypeName,
        "resDesc":values.updatePageRemark,
        "systemPlatformCd":values.updatePageParentSystem,
        "mark":mark
      };
      updatePage(updateMenuMsg).then((msg)=>{
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
            "resName":"",
            "menuName":"",
            "systemPlatformName":""
          };
          getPageMsg(obj).then((msg)=>{
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
      getPageTypeList().then((msg)=>{
        this.setState({ pageAllType:msg.jsonResult.data });
      });
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
    this.setState({ searchVal : val });
  },
  searchBtn (e){
    let obj;
    if (this.state.searchVal == "menu"){
      obj={
        "resName":"",
        "menuName":e.target.value,
        "systemPlatformName":""
      };
    }else if (this.state.searchVal == "system"){
      obj={
        "resName":"",
        "menuName":"",
        "systemPlatformName":e.target.value
      };
    }else{
      obj={
        "resName":e.target.value,
        "menuName":"",
        "systemPlatformName":""
      };
    }
    getPageMsg(obj).then((msg)=>{
      this.setState({ hotCustData:msg.jsonResult.data.list });
    });
  },
  /*查询系统 END*/

  //点击数据前面小框取得数据信息
  onSelectChange(selectedRowKeys,record) {
    if(record[0].resTypeName == "按钮"){
      record[0].resTypeName = "0"
    }else{
      record[0].resTypeName = "1"
    }
    this.setState({ userMsg:[] });
    this.setState({
      selectedRowKeys,
      userMsg:record
    })
  },

  pageType(value){
    if (value == "da8fe8e2fae444848142f137bf7ff675"){
      this.setState({typeMark:false})
    }else {
      this.setState({typeMark:true})
    }
  },

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

  urlExists(rule, value, callback) {
    if (!value) {
      callback();
    } else {
      let pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
      let re = new RegExp("[^\u4e00-\u9fa5]");
      if (!pattern.test(value)) {
        callback([new Error('请输入正确的特殊字符！')]);
      }else if (!re.test(value)){
        callback([new Error('请输入正确的url地址！')]);
      }else {
        callback();
      }
    }
  },
  /*表单校验规则 END*/

  render() {
    //用于页面内容显示的table
    const columns = [
      { title: '资源名称', dataIndex: 'resName', key: 'resName' },
      { title: 'URL', dataIndex: 'url', key: 'url' },
      { title: '状态', dataIndex: 'stateCd', key: 'stateCd'},
      { title: '所属菜单', dataIndex: 'menuName', key: 'menuName' },
      { title: '所属系统', dataIndex: 'systemPlatformName', key: 'systemPlatformName' },
      { title: '资源类型', dataIndex: 'resTypeName', key: 'resTypeName' },
      { title: '创建人', dataIndex: 'userName', key: 'userName' },
      { title: '创建时间', dataIndex: 'createDate', key: 'createDate' }
    ];

    /*表格选中控制*/
    const {  selectedRowKeys } = this.state;
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

    /*表单初始化*/
    const { getFieldProps } = this.props.form;

    /*获取系统列表*/
    const menuSystemList = this.state.menuSystem.map((items,index)=>{
      return (
        <Option key={index} value={items.systemPlatformCd}>{items.systemPlatformName}</Option>
      )
    });
    /*获取系统列表 END*/
    /*根据系统 获取菜单*/
    const ParentMenuList = this.state.parentMenu.map((items,index)=>{
      return (
        <Option key={index} value={items.menuId}>{items.menuName}</Option>
      )
    });
    /*根据系统 获取菜单 END*/

    /*点击修改 获取资源类型*/
    const PageAllType = this.state.pageAllType.map((items,index)=>{
      return (
        <Option key={index} value={items.resTypeCd}>{items.resTypeName}</Option>
      )
    });
    /*点击修改 获取资源类型 END*/

    /*修改系统 数据展示*/
    const userUpdate = this.state.userMsg.map((items,index)=>{
      return (
        <Form onSubmit={this.updateOk} className="login-form" key={index}>
          <FormItem label="页面资源名称" hasFeedback>
            <Input type="text" placeholder="请输入页面资源名称（必填）"
              {...getFieldProps('updatePageName',{initialValue:items.resName,rules:[{ required: true, message: '请填写正确的菜单名称！'},{validator: this.userExists}]})}
            />
          </FormItem>
          <FormItem label="入口URL">
            <Input type="text" placeholder="请输入入口URL（选填）"
              {...getFieldProps('updatePageUrl',{initialValue:items.url,rules:[{ required: true, message: '请填写正确的URL！'},{validator: this.urlExists}]})}
            />
          </FormItem>
          <FormItem label="所属系统">
            <Select id="select" size="large"
              {...getFieldProps('updatePageParentSystem',{initialValue:items.systemPlatformCd,onChange:this.updSystemSelect,rules:[{ required: true, message: '请填写所属系统！'}]})}>
              {menuSystemList}
            </Select>
          </FormItem>
          <FormItem label="所属菜单">
            <Select id="select" size="large"
              {...getFieldProps('updatePageParentMenu',{initialValue:this.state.initialParentMenu,rules:[{ required: true, message: '请填写所属菜单！'}]})}>
              {ParentMenuList}
            </Select>
          </FormItem>
          <FormItem label="资源类型">
            <Select size="large" defaultValue="请选择" {...getFieldProps('updatePageResTypeName',{onChange:this.pageType,initialValue:items.resTypeCd,rules:[{ required: true, message: '请填写资源类型！'}]})}>
              {PageAllType}
            </Select>
          </FormItem>
          <FormItem label="资源标示">
            <Select size="large" disabled={this.state.typeMark} {...getFieldProps('pageResTypeMark',{ initialValue:items.mark})}>
              <Option value="add">增加</Option>
              <Option value="delete">删除</Option>
              <Option value="update">修改</Option>
            </Select>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup {...getFieldProps('updatePageStatus', { initialValue: items.stateCd })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea"  {...getFieldProps('updatePageRemark', { initialValue: items.resDesc })}/>
          </FormItem>
        </Form>
      )
    });
    /*修改系统 数据展示 END*/

    /*查看系统 数据展示*/
    const userLook = this.state.userMsg.map((items,index)=>{
      return (
        <Form onSubmit={this.lookOk} className="login-form" key={index}>
          <FormItem label="页面资源名称" >
            <Input type="text" placeholder="请输入页面资源名称（必填）" disabled {...getFieldProps('lookPageName',{initialValue:items.resName})}/>
          </FormItem>
          <FormItem label="入口URL">
            <Input type="text" placeholder="请输入入口URL（选填）" disabled {...getFieldProps('lookPageUrl',{initialValue:items.url})}/>
          </FormItem>
          <FormItem label="所属菜单">
            <Select id="select" size="large" defaultValue={items.menuName} disabled  onChange={this.selectParentMenu}>
              {ParentMenuList}
            </Select>
          </FormItem>
          <FormItem label="资源类型">
            <Select size="large" defaultValue="请选择" disabled {...getFieldProps('lookPageResTypeName',{ initialValue: items.resTypeCd })}>
              {PageAllType}
            </Select>
          </FormItem>
          <FormItem label="资源标示">
            <Select size="large" disabled={true} {...getFieldProps('lookPageResTypeMark',{ initialValue:items.mark})}>
              <Option value="add">增加</Option>
              <Option value="delete">删除</Option>
              <Option value="update">修改</Option>
            </Select>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup disabled {...getFieldProps('lookPageStatus',  { initialValue: items.stateCd })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" disabled  {...getFieldProps('lookPageRemark', { initialValue: items.resDesc })}/>
          </FormItem>
        </Form>
      )
    });
    /*查看系统 数据展示 END*/

    /*查询条件*/
    const selectBefore = (
      <Select defaultValue="资源名称" style={{ width: 120 }} onChange={this.searchType}>
        <Option value="page">资源名称</Option>
        <Option value="menu">所属菜单</Option>
        <Option value="system">所属系统</Option>
      </Select>
    );
    /*查询条件 END*/

    return (
      <div>
        <div style={{width:"100%",height:"40px",lineHeight:"40px",marginBottom:"5px"}}>
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
            <Table rowKey='privilegeResId' columns={columns} rowSelection={rowSelection} loading={this.state.loading} dataSource={this.state.hotCustData} pagination={hotPage}/>
          </div>
        </div>
        <Modal title="新建页面资源" visible={this.state.addCust}
               onCancel={this.addCancel}
               footer={[]}
        >
          <PageCreat callback = {this.getReturnData}/>
        </Modal>

        <Modal title="修改页面资源" visible={this.state.updateCon}
               onOk={this.updateOk} onCancel={this.updateCancel}
               footer={[
                <Button key="submit" type="primary" size="large" onClick={this.updateOk}>确定</Button>,
                <Button key="back" type="ghost" size="large" onClick={this.updateCancel}>取消</Button>
              ]}
        >
          {userUpdate}
        </Modal>

        <Modal title="查看页面资源" visible={this.state.lookCon}
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
