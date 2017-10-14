const users = require('./controllers/users');
const auth = require('./controllers/auth');
const posts = require('./controllers/posts')

module.exports = function (app) {
  app.get('/api/users', users.list);
  app.get('/api/users/:user_id', users.getById);

  app.post('/api/posts/create', posts.create);
  app.get('/api/posts', posts.list)
};
