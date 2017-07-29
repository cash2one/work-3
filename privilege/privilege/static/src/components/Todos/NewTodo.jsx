import React, { Component, PropTypes } from 'react';
import {Input, Button, Row, Col} from 'antd';
const InputGroup = Input.Group;

const NewTodo = React.createClass({

  getInitialState(){
    return {};
  },

  nameChangeHandler(e){
    this.setState({
      name: e.target.value
    });
  },

  pwdChangeHandler(e){
    this.setState({
      pwd: e.target.value
    });
  },

  submitHandler(){
    this.props.onAdd({
      name: this.state.name,
      pwd: this.state.pwd,
    });
    this.setState({
      name:'',
      pwd:'',
    });
  },

  render(){
    return (
      <div>
        <InputGroup size="large">
          <Col span="4">
            <Input
              type="name"
              value={this.state.name || ''}
              onChange={this.nameChangeHandler}
            />
          </Col>
          <Col span="8">
            <Input
              type="pwd"
              value={this.state.pwd || ''}
              onChange={this.pwdChangeHandler}
            />
          </Col>
        </InputGroup>
        <Button onClick={this.submitHandler}>ok</Button>
      </div>
    );
  }
});

export default NewTodo;

