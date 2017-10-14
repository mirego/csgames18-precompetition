const users = require('./controllers/users');
const auth = require('./controllers/auth');
const conversations = require('./controllers/conversations');
const posts = require('./controllers/posts')

module.exports = function (app) {
  app.get('/api/users', users.list);
  app.get('/api/users/:user_id', users.getById);

  //app.get('/api/users/:user_id/conversations', conversations.list);
  //app.get('/api/users/:user_id/conversations/:conversation_id', conversations.getById);
  //app.post('/api/users/:user_id/conversations', conversations.create);
  //app.post('/api/users/:user_id/conversations/:conversation_id', conversations.addMessage)

  app.post('/api/posts/create', posts.create);
  app.get('/api/posts', posts.list)
};
