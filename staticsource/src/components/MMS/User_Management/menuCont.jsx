import React, { Component, PropTypes } from 'react';
import { Button,  Form,  Input, Modal, Table, message, Radio, Select, Col} from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import MenuCreat from './menuCreat.jsx';
import { getBtnLists,  getMenuSystem, AddGetParentMenuName, getParentMenuName, getShowOlder, getMenuMsg, updateMenu, removeMenu,  getLookUsers } from '../../../services/api.js'

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
      hotCustData:[],
      menuSystem:[],
      parentMenu:[],
      lookUserdata:[],
      menuIDStr:sessionStorage.getItem("menuID"),
      searchVal:"",
      showOlder:[],
      parentShowOder:[]
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

    //获取列表
    let val = {"systemPlatformCd":""};
    getMenuMsg(val).then((msg)=>{
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
  getReturnData:function(val){
    this.setState({ addCust: false });
    let obj = {"systemPlatformName":""};
    message.success(val.jsonResult.data);
    getMenuMsg(obj).then((msg)=>{
      this.setState({
        hotCustData:msg.jsonResult.data.list
      });
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
      this.setState({delete: true});
    }
  },
  deleteOk() {
    removeMenu(this.state.userMsg[0].menuId).then((data)=>{
      if (data.jsonResult.success) {
        message.success(data.jsonResult.data);
        this.setState({
          delete: false,
          selectedRowKeys:[]
        });
        let obj={"systemPlatformName":""};
        getMenuMsg(obj).then((msg)=>{
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
      /*获取系统列表*/
      let msg = {"stateCd":"1"};
      getMenuSystem(msg).then((msg)=>{
        this.setState({
          menuSystem:msg.jsonResult.data.list
        });
      });
      /*根据系统，获取菜单列表*/
      let val = {
        "menuId":this.state.userMsg[0].menuId,
        "systemPlatformCd":this.state.userMsg[0].systemPlatformCd,
        "stateCd":"1"
      };
      AddGetParentMenuName(val).then((msg)=>{
        this.setState({ parentMenu:msg.jsonResult.data.list });
      });

      let str = this.state.userMsg[0].showOrder;
      if (str == undefined || !str){
        this.setState({
          showOlder:[],
          parentShowOder:1
        });
      }else{
        this.setState({
          showOlder:str.substr(str.length-2),
          parentShowOder:str.substring(0,str.length-2)
        });
      }
    }
  },
  menuSystemSelect(e){
    /*根据系统，获取菜单列表*/
    let val = {
      "menuId":this.state.userMsg[0].menuId,
      "systemPlatformCd":e,
      "stateCd":"1"
    };
    AddGetParentMenuName(val).then((msg)=>{
      this.setState({ parentMenu:msg.jsonResult.data.list });
    });
  },
  parentMenuSelect(e){
    if (e == null || e == 1){
      this.setState({parentShowOder:[]});
    }else{
      getShowOlder(e).then((msg)=>{
        this.setState({
          parentShowOder:msg.jsonResult.data.showOrder
        });
      })
    }
  },
  updateOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        return;
      }
      let menuName = values.updateMenuName;
      let menuUrl = values.updateMenuUrl;
      let showOrder = values.ParentShowOrder+values.updateShowOrder;

      if (menuName == this.state.userMsg[0].menuName){
        menuName = null;
      }
      if (menuUrl == this.state.userMsg[0].url){
        menuUrl = null;
      }
      if (showOrder == this.state.userMsg[0].showOrder){
        showOrder = null;
      }

      let updateMenuMsg={
        "menuId":this.state.userMsg[0].menuId,
        "menuName":menuName,
        "url":menuUrl,
        "parentId":values.updateParentMenu,
        "showOrder":showOrder,
        "roleIds":values.updateLookRole,
        "stateCd":values.updateMenuStatus,
        "menuDesc":values.updateMenuRemark,
        "systemPlatformCd":values.updateMenuSystem
      };
      updateMenu(updateMenuMsg).then((msg)=>{
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
          getMenuMsg(obj).then((msg)=>{
            this.setState({ hotCustData:msg.jsonResult.data.list });
          });
        }
      });
    });
  },
  updateCancel() {
    this.props.form.resetFields();
    this.setState({ updateCon: false });
  },
  /*修改系统资源 END*/

  /*查看系统资源*/
  lookBtn(){
    if (this.state.userMsg == "") {
      message.warning('请选择至少一条数据！');
    }else {
      getLookUsers(this.state.userMsg[0].menuId).then((msg)=>{
        this.setState({
          lookCon: true,
          lookUserdata:msg.jsonResult.data
        });
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
    this.setState({
      searchVal:val
    });
  },
  searchBtn (e){
    let obj;
    if (this.state.searchVal == "system"){
      obj={
        "menuName":"",
        "systemPlatformName":e.target.value
      };
    }else{
      obj={
        "menuName":e.target.value,
        "systemPlatformName":""
      };
    }
    getMenuMsg(obj).then((msg)=>{
      this.setState({ hotCustData:msg.jsonResult.data.list });
    });
  },
  /*查询系统 END*/

  //点击数据前面小框取得数据信息
  onSelectChange(selectedRowKeys,record) {
    this.setState({ userMsg:[] });
    this.setState({
      selectedRowKeys,
      userMsg:record
    })
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
      let regular = new RegExp("[^/S*$]");
      if (!pattern.test(value)) {
        callback([new Error('请输入正确的特殊字符！')]);
      }else if (!re.test(value)){
        callback([new Error('请输入正确的url地址！')]);
      }else if (!regular.test(value)){
        callback([new Error('URL请以‘/’开头！')]);
      }else {
        callback();
      }
    }
  },
  showOderExists(rule, value, callback){
    if (!value) {
      callback();
    } else {
      if (!isFinite(value)) {
        callback([new Error('请输入数字！')]);
      }else if (value.length > 2){
        callback([new Error('请输入0-99之间的数字！')]);
      }else if (value.length <2){
        callback([new Error('展示顺序必须为两位数！')]);
      }else {
        callback();
      }
    }
  },
  menuOderExists(rule, value, callback){
    if (!value) {
      callback();
    } else {
      let patrn = /^[1-9]$/ig;
      if (!patrn.test(value)) {
        callback([new Error('请输入1-10之间的数字！')]);
      }else if (!isFinite(value)){
        callback([new Error('请输入数字！')]);
      }else {
        callback();
      }
    }

  },
  /*表单校验规则 END*/

  render() {
    //用于页面内容显示的table
    const columns = [
      { title: '菜单名称', dataIndex: 'menuName', key: 'menuName' },
      { title: 'URL', dataIndex: 'url', key: 'url' },
      { title: '状态', dataIndex: 'stateCd', key: 'stateCd'},
      { title: '上级菜单', dataIndex: 'parentMenuName', key: 'parentMenuName' },
      { title: '所属系统', dataIndex: 'systemPlatformName', key: 'systemPlatformName' },
      { title: '展示顺序', dataIndex: 'showOrder', key: 'showOrder' },
      { title: '菜单描述', dataIndex: 'menuDesc', key: 'menuDesc' },
      { title: '创建人', dataIndex: 'userName', key: 'userName' },
      { title: '创建时间', dataIndex: 'createDt', key: 'createDt' }
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

    /*根据系统 获取菜单*/
    const ParentMenuList = this.state.parentMenu.map((items,index)=>{
      return (
        <Option key={index} value={items.menuId}>{items.menuName}</Option>
      )
    });
    /*根据系统 获取菜单 END*/
    const menuSystemList = this.state.menuSystem.map((items,index)=>{
      return (
        <Option key={index} value={items.systemPlatformCd}>{items.systemPlatformName}</Option>
      )
    });
    /*查询条件*/
    const selectBefore = (
      <Select defaultValue="菜单名称" style={{ width: 120 }} onChange={this.searchType}>
        <Option value="menu">菜单名称</Option>
        <Option value="system">所属系统</Option>
      </Select>
    );
    /*查询条件 END*/

    /*修改系统 数据展示*/
    const userUpdate = this.state.userMsg.map((items,index)=>{
      return (
        <Form onSubmit={this.updateOk} className="login-form" key={index}>
          <FormItem label="菜单名称" hasFeedback>
            <Input type="text" placeholder="请输入菜单名称（必填）"
              {...getFieldProps('updateMenuName',
                {initialValue:items.menuName,rules:[{ required: true, message: '请填写菜单名称！'},{validator: this.userExists}]}
              )}
            />
          </FormItem>
          <FormItem label="入口URL" hasFeedback>
            <Input type="text" placeholder="请输入入口URL（以‘/’开头）"
              {...getFieldProps('updateMenuUrl',{initialValue:items.url,rules:[{ required: true, message: '请填写URL！'},{validator: this.urlExists}]})}
            />
          </FormItem>
          <FormItem label="所属系统">
            <Select size="large"
              {...getFieldProps('updateMenuSystem',{initialValue:items.systemPlatformCd,onChange:this.menuSystemSelect,rules:[{ required: true, message: '请选择所属系统！'}]})}
            >
              {menuSystemList}
            </Select>
          </FormItem>
          <FormItem label="上级菜单">
            <Select id="select" size="large"
              {...getFieldProps('updateParentMenu',{onChange:this.parentMenuSelect,initialValue:items.parentId})}
            >
              <Option value={null}>不选择</Option>
              {ParentMenuList}
            </Select>
          </FormItem>
          <div style={{width:"auto",height:"auto",overflow:"hidden"}}>
            <Col span="11">
              <FormItem label="上级菜单顺序">
                <Input type="text" disabled
                  {...getFieldProps('ParentShowOrder',{initialValue: this.state.parentShowOder})}
                />
              </FormItem>
            </Col>
            <Col span="2"></Col>
            <Col span="11">
              <FormItem label="本级菜单顺序">
                <Input type="text" placeholder="请输入显示顺序（选填）"
                  {...getFieldProps('updateShowOrder',{initialValue:this.state.showOlder,rules:[{ required: true, message: '请输入显示顺序！'},{validator: this.showOderExists}]})}
                />
              </FormItem>
            </Col>
          </div>
          <FormItem label="是否激活" >
            <RadioGroup {...getFieldProps('updateMenuStatus', { initialValue: items.stateCd })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea"  {...getFieldProps('updateMenuRemark', { initialValue: items.menuDesc })}/>
          </FormItem>
        </Form>
      )
    });
    /*修改系统 数据展示 END*/
    /*查看系统 数据展示*/
    const userLook = this.state.userMsg.map((items,index)=>{
      return (
        <Form onSubmit={this.lookOk} className="login-form" key={index}>
          <FormItem label="菜单名称" >
            <Input type="text" disabled {...getFieldProps('LookMenuName',{initialValue:items.menuName})}/>
          </FormItem>
          <FormItem label="入口URL">
            <Input type="text" disabled {...getFieldProps('LookMenuUrl',{initialValue:items.url})}/>
          </FormItem>
          <FormItem label="所属系统">
            <Select id="select" size="large" defaultValue={items.systemPlatformName} disabled >
              <Option value={items.systemPlatformName}>{items.systemPlatformName}</Option>
            </Select>
          </FormItem>
          <FormItem label="上级菜单" >
            <Select id="select" size="large" defaultValue="jack" disabled>
              <Option value="jack">{items.parentMenuName}</Option>
            </Select>
          </FormItem>
          <FormItem label="显示顺序" >
            <Input type="text" disabled {...getFieldProps('LookShowOrder',{initialValue:items.showOrder})}/>
          </FormItem>
          <FormItem label="所属角色" >
            <Input type="text" disabled {...getFieldProps('LookLookRole',{initialValue:this.state.lookUserdata})}/>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup disabled {...getFieldProps('LookMenuStatus', { initialValue: items.stateCd })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" disabled {...getFieldProps('LookMenuRemark', { initialValue: items.menuDesc })}/>
          </FormItem>
        </Form>
      )
    });
    /*查看系统 数据展示 END*/
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
              <Button style={{marginLeft:"5px"}} onClick={this.lookBtn}  type="primary" size="large">查看</Button>
              <Button style={{marginLeft:"5px"}} onClick={this.addBtn}  disabled={this.state.addDisabled}type="primary" size="large">新建</Button>
              <Button style={{marginLeft:"5px"}} onClick={this.updateBtn} disabled={this.state.updDisabled}type="primary" size="large">修改</Button>
              <Button style={{marginLeft:"5px"}} onClick={this.deleteBtn} disabled={this.state.delDisabled} type="primary" size="large">删除</Button>
            </div>
          </div>
          <div style={{width:"100%"}}>
            <Table rowKey='menuId' columns={columns} loading={this.state.loading} rowSelection={rowSelection} dataSource={this.state.hotCustData} pagination={hotPage}/>
          </div>
        </div>

        <Modal title="新建菜单" visible={this.state.addCust}
               onCancel={this.addCancel}
               footer={[]}
        >
          <MenuCreat callback = {this.getReturnData}/>
        </Modal>

        <Modal title="修改菜单" visible={this.state.updateCon}
               onOk={this.updateOk} onCancel={this.updateCancel}
               footer={[
                <Button key="submit" type="primary" size="large" onClick={this.updateOk}>确定</Button>,
                <Button key="back" type="ghost" size="large" onClick={this.updateCancel}>取消</Button>
              ]}
        >
          {userUpdate}
        </Modal>

        <Modal title="查看菜单" visible={this.state.lookCon}
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
