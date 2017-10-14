const jwt = require('jsonwebtoken');

exports.authenticate = function(req, res, next) {
  var token = req.body.token || req.query.token || req.headers['x-access-token'];

  if (token) {
    jwt.verify(token, process.env.SECRET, function(err, decoded) {
      if (err) {
        return res.status(403).json({message: 'Failed to authenticate token'});
      } else {
        req.user = decoded;
        next();
      }
    });

  } else {
    return res.status(401).send({
        message: 'Unauthorized user'
    });
  }
}
