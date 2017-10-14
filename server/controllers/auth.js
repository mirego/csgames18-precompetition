const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

exports.login = function (req, res) {
  let filter = {
    'username': req.body.username.toLowerCase()
  };

  let projection = {};

  req.db.users.findOne(filter, projection, function (e, user) {
    if (user && bcrypt.compareSync(req.body.password, user.password)) {
      delete user.password;
      user.token = jwt.sign(user, process.env.SECRET);
      res.json(user);
    } else {
      res.status(401).send('Username or password invalid');
    }
  });
};
