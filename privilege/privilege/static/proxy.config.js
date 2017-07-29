'use strict';
// Learn more on how to config.
// - https://github.com/dora-js/dora-plugin-proxy#规则定义

const mock = {};

require('fs').readdirSync(require('path').join(__dirname + '/src/mock'))
  .forEach(function (file) {
    Object.assign(mock, require('./src/mock/' + file));
  });

module.exports = mock;