import React, { Component, PropTypes } from 'react';
import { getUserList, addUser } from '../../services/api';
import Nav from './Nav';
import {Row, Col} from 'antd';
import Todos from './Todos';
import NewTodo from './NewTodo';
import styles from './Page.less';

class TodosPage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      list: [],
      loading: false,
    };
  }

  loadTodos() {
    this.setState({ loading: true });
    getUserList().then(({ jsonResult }) => {
      this.setState({
        list: jsonResult.data,
        loading: false,
      });
    })
  }

  handleToggleComplete = (id) => {
    const newList = this.state.list.map(todo => {
      if (id === todo.id) {
        return { ...todo, isComplete: !todo.isComplete };
      } else {
        return todo;
      }
    });
    this.setState({
      list: newList,
    });
  }

  componentDidMount() {
    this.loadTodos();
  }

  addHandler(user){
    let self =this;
    addUser(user).then(({jsonResult})=>{
      if(jsonResult.success){
        console.log(self);
        let list = self.state.list;
        console.log(list);
        list.push(user);
        self.setState({list});
      }
    })
  }
  render() {
    const { location } = this.props;
    const { list, loading } = this.state;
    const todos = filter({ list, loading }, location.pathname);
    return (
      <Row className={styles['todos-page']}>
        <Col span="3">
          <Nav {...location}/>
        </Col>
        <Col span="21">
          <Todos todos={todos} onToggleComplete={this.handleToggleComplete} />
          <NewTodo onAdd={this.addHandler.bind(this)}/>
        </Col>
      </Row>
    );
  }
}


function filter(todos, pathname) {
  const newList = todos.list.filter(todo => {
    if (pathname === '/todos/actived') {
      return !todo.isComplete;
    }
    if (pathname === '/todos/completed') {
      return todo.isComplete;
    }
    return true;
  });
  return { ...todos, list: newList };
}

export default TodosPage;
