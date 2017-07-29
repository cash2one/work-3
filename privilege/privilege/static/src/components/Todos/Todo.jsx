import React, { Component, PropTypes } from 'react';
import classnames from 'classnames';
import styles from './Todo.less';

const Todo = ({ data, onToggleComplete }) => {
  const { name, pwd } = data;
  const todoCls = classnames({
    [styles.normal]: true,
    [styles.isComplete]: false,
  });

  return (
    <div className={todoCls}>
      <div className={styles.checkbox}>
        <input
          type="checkbox"
          value=""
          checked={false}
          onChange={onToggleComplete.bind(this)}
        />
      </div>
      <div className={styles.text}>
        {name} {pwd}
      </div>
    </div>
  );
};

Todo.propTypes = {
  data: PropTypes.object.isRequired,
  onToggleComplete: PropTypes.func.isRequired,
};

export default Todo;

