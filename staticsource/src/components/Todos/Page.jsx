import React, { Component, PropTypes } from 'react';
import { getAll } from '../../services/api';
import {Row, Col} from 'antd';
import Todos from './Todos';
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
    getAll().then(({ jsonResult }) => {
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
  };

  componentDidMount() {
    this.loadTodos();
  }

  render() {
    const { location } = this.props;
    const { list, loading } = this.state;
    const todos = filter({ list, loading }, location.pathname);
    return (
      <Row className={styles['todos-page']}>
        <Col span="3">

        </Col>
        <Col span="21">
          <Todos todos={todos} onToggleComplete={this.handleToggleComplete} />
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
