'use strict';

module.exports = {
  '/webapi/users/xiao': function(req, res) {
    setTimeout(function() {
      res.json({
        success: true,
        data: [
          {
            id: 1,
            text: 'Learn antd',
            isComplete: true
          },
          {
            id: 2,
            text: 'Learn ant-tool'
          },
          {
            id: 3,
            text: 'Learn dora'
          }
        ]
      });
    }, 500);
  }
};
