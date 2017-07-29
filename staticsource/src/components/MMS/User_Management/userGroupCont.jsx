import React, { } from 'react';
import { Radio, Button, Form, Input, Modal, Table, message, Select} from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import UserGroupCreat from './userCroupCreat.jsx';
import { getUserGroupMsg, getAllRoleListMsg, getRoleListMsg, deleteUserGroupMsg, getUserGroupListMsg, lookUserGroupMsg, updateUserGroup, getBtnLists} from '../../../services/api.js';

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
      systemListData:[],
      roleListData:[],
      updateList:[],
      updateGroupRoles:[],
      updateSystems:[],
      allRoleList:[],
      defaultRoleList:[],
      menuIDStr:sessionStorage.getItem("menuID")
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

    getUserGroupMsg(obj).then((msg)=>{
      if (!msg.jsonResult.success){
        this.setState({ loading:true });
        message.error(msg.jsonResult.resultMsg);
      }else {
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

  /*查询系统*/
  searchBtn (e){
    let obj={"groupName":e.target.value};
    getUserGroupMsg(obj).then((msg)=>{
      this.setState({ hotCustData:msg.jsonResult.data.resultList });
    });
  },
  /*查询系统 END*/

  /*新建用户组*/
  addBtn(){
    this.setState({ addCust: true });
  },
  getReturnData:function(result){
    let obj={"systemPlatformName":""};
    getUserGroupMsg(obj).then((msg)=>{
      this.setState({
        loading:false,
        addCust: false,
        hotCustData:msg.jsonResult.data.resultList
      });
    });
  },
  addCancel() {
    this.setState({ addCust: false });
  },
  /*新建用户组 END*/

  /*删除用户组*/
  deleteBtn(){
    if (this.state.userMsg == 0) {
      message.warning ("请选择至少一条数据！");
    }else {
      this.setState({ delete: true });
    }
  },
  deleteOk() {
    deleteUserGroupMsg(this.state.userMsg[0].groupId).then((data)=>{
      if (!data.jsonResult.success) {
        message.error(data.jsonResult.resultMsg);
      }else{
        message.success(data.jsonResult.data);
        this.setState({
          delete: false,
          selectedRowKeys:[]
        });
        let obj={
          "systemPlatformName":""
        };
        getUserGroupMsg(obj).then((msg)=>{
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
  /*删除用户组 END*/

  /*修改用户组*/
  updateBtn(){
    if (this.state.userMsg == "") {
      message.warning('请选择至少一条数据！');
    }else {
      this.setState({ updateCon: true });
      getUserGroupListMsg(this.state.userMsg[0].groupId).then((msg)=>{
        this.setState({
          updateGroupRoles:msg.jsonResult.data.groupRoles,
          updateList:[msg.jsonResult.data.detail],
          roleListData:msg.jsonResult.data.roles,
          updateSystems:msg.jsonResult.data.groupSys,
          systemListData:msg.jsonResult.data.systems,
          userGroupListData:msg.jsonResult.data.groups,
          allRoleList:msg.jsonResult.data.pGroupRole.result,
          defaultRoleList:msg.jsonResult.data.pGroupRole.roleStr
        });
      });
    }
  },
  updateParentSystem(val){
    //获取角色列表
    getRoleListMsg(val.join(",")).then((msg)=>{
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
  updateOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        console.log('Errors in form!!!');
        return;
      }
      let groupName = values.updateUserGroupName;
      if (groupName == this.state.userMsg[0].groupName){
        groupName = null
      }
      let updateMsg={
        'groupId':this.state.userMsg[0].groupId,
        'groupName':groupName,
        'parentGroupId':values.updateUserGroupParent,
        'stateCd':values.updateUserGroupStatus,
        'groupDesc':values.updateUserGroupRemark,
        'roleId':values.updateUserGroupRoles.join(","),
        'systemId':values.updateParentSystem.join(",")
      };
      updateUserGroup(updateMsg).then((msg)=>{
        if (!msg.jsonResult.success) {
          message.error(msg.jsonResult.resultMsg);
        }else{
          message.success(msg.jsonResult.data);
          this.setState({
            updateCon: false,
            selectedRowKeys:[],
            allRoleList:[],
            userMsg:[]
          });
          this.props.form.resetFields();
          let obj={
            "systemPlatformName":""
          };
          getUserGroupMsg(obj).then((msg)=>{
            this.setState({
              loading:false,
              updateCon: false,
              hotCustData:msg.jsonResult.data.resultList
            })
          });
        }
      })
    });
  },
  updateCancel() {
    this.setState({ updateCon: false });
  },
  /*修改用户组 END*/


  /*查看*/
  lookBtn(){
    if (this.state.userMsg == "") {
      message.warning('请选择至少一条数据！');
    }else {
      this.setState({ lookCon: true });
      lookUserGroupMsg(this.state.userMsg[0].groupId).then((msg)=>{
        this.setState({
          updateGroupRoles:msg.jsonResult.data.groupRoles,
          updateList:[msg.jsonResult.data.detail],
          roleListData:msg.jsonResult.data.users
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
  /*表单校验规则 END*/

  render() {
    //用于页面内容显示的table
    const columns = [
      { title: '用户组名称', dataIndex: 'groupName', key: 'groupName' },
      { title: '状态', dataIndex: 'State_CD', key: 'State_CD' },
      { title: '上级用户组', dataIndex: 'pname', key: 'pname' },
      { title: '创建人', dataIndex: 'User_Name', key: 'User_Name' },
      { title: '创建时间', dataIndex: 'Create_Dt', key: 'Create_Dt' }
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
        <Option value="userGroup">用户组名称</Option>
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

    /*修改系统 数据展示*/
    const userUpdate = this.state.updateList.map((items,index)=>{
      return (
        <Form onSubmit={this.updateOk} className="login-form" key={index}>
          <FormItem label="用户组名称" hasFeedback>
            <Input type="text" placeholder="请输入角色名称（必填）"
              {...getFieldProps('updateUserGroupName',{initialValue:items.groupName,rules:[{ required: true, message: '请填写正确的用户组名称！'},{validator: this.userExists}]})}
            />
          </FormItem>
          <FormItem label="上级用户组">
            <Select size="large"
              {...getFieldProps('updateUserGroupParent',{initialValue:items.pid,onChange:this.getAllRoleListMsg})}
            >
              <Option value="null">不选择</Option>
              {userGroupList}
            </Select>
          </FormItem>
          <FormItem label="继承角色">
            <Select size="large" multiple disabled {...getFieldProps('selectDefaultUsers',{initialValue: this.state.defaultRoleList})}
            >
              {allRoleList}
            </Select>
          </FormItem>
          <FormItem label="所属系统">
            <Select size="large" multiple placeholder="请选择"
              {...getFieldProps('updateParentSystem',{initialValue:this.state.updateSystems,rules:[{ required: true, message: '请选择所属系统！',type: 'array'}],onChange:this.updateParentSystem})}
            >
              {systemList}
            </Select>
          </FormItem>
          <FormItem label="所属角色">
            <Select size="large" multiple
              {...getFieldProps('updateUserGroupRoles',{initialValue:this.state.updateGroupRoles,rules:[{ required: true, message: '请选择角色！',type: 'array'}]})}
            >
              {roleList}
            </Select>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup {...getFieldProps('updateUserGroupStatus', { initialValue: items.state })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea"  {...getFieldProps('updateUserGroupRemark', { initialValue:items.des })}/>
          </FormItem>
        </Form>
      )
    });
    /*修改系统 数据展示 END*/
    /*查看系统 数据展示*/
    const userLook = this.state.updateList.map((items,index)=>{
      return (
        <Form onSubmit={this.lookOk} className="login-form" key={index}>
          <FormItem label="用户组名称" >
            <Input type="text" placeholder="请输入角色名称（必填）" disabled {...getFieldProps('lookUserGroupName',{initialValue:items.groupName})}/>
          </FormItem>
          <FormItem label="上级用户组">
            <Input type="text" placeholder="请输入角色名称（必填）" disabled {...getFieldProps('lookUserGroupName',{initialValue:items.pname})}/>
          </FormItem>
          <FormItem label="所属角色">
            <Select size="large" multiple disabled
              {...getFieldProps('lookUserGroupRoles',{initialValue:this.state.updateGroupRoles})}
            >
              {roleList}
            </Select>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup disabled {...getFieldProps('lookUserGroupStatus', { initialValue: items.state })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" disabled {...getFieldProps('lookUserGroupRemark', { initialValue:items.des })}/>
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
            <Table rowKey='groupId' columns={columns} loading={this.state.loading}  rowSelection={rowSelection} dataSource={this.state.hotCustData} pagination={hotPage}/>
          </div>
          <Modal title="新建用户组" visible={this.state.addCust} onCancel={this.addCancel} footer={[]}>
            <UserGroupCreat callback = {this.getReturnData}/>
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
        <Modal title="修改用户组" visible={this.state.updateCon}
               onOk={this.updateOk} onCancel={this.updateCancel}
               footer={[
                <Button key="submit" type="primary" size="large" onClick={this.updateOk}>确定</Button>,
                <Button key="back" type="ghost" size="large" onClick={this.updateCancel}>取消</Button>
              ]}
        >
          {userUpdate}
        </Modal>
        <Modal title="查看用户组" visible={this.state.lookCon}
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
