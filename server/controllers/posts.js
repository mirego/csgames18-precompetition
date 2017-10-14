exports.create = function(req, res) {
  if(req.body.author && req.body.message) {
    let post = {
      'date': new Date().toJSON(),
      'author': req.body.author,
      'message': req.body.message,
      'attachment': req.body.attachment || null
    };

    req.db.posts.insert(post, function (e, post) {
        res.json(post);
    });
  } else {
    res.status(400).json({message: 'Missing required fields'});
  }
};

exports.list = function(req, res) {
  let filter = {};
  let projection = {
  };
  req.db.posts.find(filter, projection).limit(50).exec(function (e, posts) {
    res.json({
      'feed': 'fdsa',
      'lastUpdate': new Date().toJSON(),
      'posts': posts
    });
  });
}
