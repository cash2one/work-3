import React, { Component, PropTypes } from 'react';
import { Upload, Icon, Form, message } from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import { getOSSUploadedFileUrl, getOSSUploadCert } from '../../../services/api.js';

const createForm = Form.create;
const Dragger = Upload.Dragger;

var OSS = window.OSS;

let Demo = React.createClass({
  //	页面初始化
  getInitialState(){
    return {
      uploadCertObj:{},//用户上传凭证
      fileName: '',
      objSend:{}//调用OCR时候的参数
    }
  },
  /*
   * 选择上传文件
   * */
  chooseFile(files){
    let fileName = files.name;
    this.getUploadCert(fileName,files);
  },
  /*
   * 获取用户上传凭证
   * */
  getUploadCert(fileName,files){
    var objSend={
      resFromAppName:'eprivilege',       //固定
      resAccessAppName:'eprivilege',     //固定
      resType:'privilegeConfig',     //固定
      resName:fileName             //resName当前图片的唯一标识，建议使用文件的名字
      //是自己定义的名字，取图片地址的时候需要和这个相同
    };
    getOSSUploadCert(objSend).then(({jsonResult})=>{
      if(jsonResult.success){
        this.setState({ uploadCertObj:jsonResult.data });
        this.uploadFiles(objSend,files);
      }else{
        message.error("实名认证-上传凭证失败！");
      }
    })
  },

  /*
   * 回调函数：文件上传
   * */
  uploadFiles(objSend,files){
    let client = new OSS.Wrapper({
      region: 'oss-cn-hzfinance',
      accessKeyId: this.state.uploadCertObj.accessKeyID,
      accessKeySecret: this.state.uploadCertObj.accessKeySecret,
      stsToken: this.state.uploadCertObj.securityToken,
      bucket: this.state.uploadCertObj.bucketName
    });
    client.multipartUpload(this.state.uploadCertObj.key, files).then((result)=> {
      let obj = {
        resFromAppName:'eprivilege',
        resAccessAppName:'eprivilege',
        resType:'privilegeConfig',
        resName:objSend.resName
      };
      getOSSUploadedFileUrl(obj).then(({jsonResult})=>{
        if (jsonResult.success){
          message.success(jsonResult.data, 15);
          this.props.form.resetFields();
        }else{
          message.error(jsonResult.resultMsg,15);
          this.props.form.resetFields();
        }
      });
    }).catch(function (err) {
      console.log(err);
    });
  },

  render() {
    const props = {
      action: '',
      accept:"application/vnd.ms-excel",
      multiple: false,
      listType: 'picture',
      beforeUpload:(files)=>{this.chooseFile(files)}
    };

    return (
      <div style={{height:"400px"}}>
        <Dragger {...props}>
          <p className="ant-upload-drag-icon">
            <Icon type="inbox" />
          </p>
          <p className="ant-upload-text">点击或将文件拖拽到此区域上传</p>
          <p className="ant-upload-hint">支持单个或批量上传，严禁上传公司内部资料及其他违禁文件</p>
        </Dragger>
      </div>
    );
  }
});
Demo = createForm()(Demo);
export default Demo;
