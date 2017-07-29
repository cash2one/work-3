'use strict';

module.exports = {
  'GET /webapi/users': function(req, res) {
    setTimeout(function() {
      res.json({
        success: true,
        data: [
          {
            id: 1,
            name: 'jim',
            pwd: "asdfasdf",
          },
          {
            id: 2,
            name: 'lilei',
            pwd: "asdf",
          },
          {
            id: 3,
            name: 'weihua',
            pwd: "asdf",
          },
        ],
      });
    }, 500);
  },

  'POST /webapi/users': function(req, res) {
    setTimeout(function() {
      res.json({
        success: true,
        data: {name:11,pwd:11},
      });
    }, 500);
  },
};
