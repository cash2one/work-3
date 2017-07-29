import React, { Component, PropTypes } from 'react';
import { Spin } from 'antd';
import Todo from './Todo';
import styles from './Todos.less';

const Todos = ({ todos, onToggleComplete }) => {
  const renderList = () => {
    const { list, loading } = todos;
    if (loading) {
      return <Spin />;
    }

    return (
      <div className={styles.list}>
        {list.map((item,i) => <Todo
          key={i}
          data={item}
          onToggleComplete={onToggleComplete.bind(this, item.id)}
        />
          )}
      </div>
    );
  };

  return (
    <div className={styles.normal}>
      {renderList()}
    </div>
  );
};

Todos.propTypes = {};

export default Todos;
