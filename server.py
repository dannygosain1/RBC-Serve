from flask import Flask, render_template, request
from datetime import date, datetime
from message import text
from flask import Flask
from flask_pymongo import PyMongo

# Create Flask App
app = Flask(__name__)

# Set Up Database Connections
app.config['MONGO_DBNAME'] = 'rbc-serve-database'
app.config['MONGO_URI'] = 'mongodb://syde:syde@ds053828.mlab.com:53828/rbc-serve-database'

# Get Collections
mongo = PyMongo(app)

@app.route('/api/create_user', methods = ['POST'])
def create_user():
    mongo.db.users.insert(request.json)

@app.route('/api/get_users')
def get_users():
    return mongo.db.users.find({})

@app.route('/api/create_post', methods = ['POST'])
def create_post():
    mongo.db.posts.insert(request.json)

@app.route('/api/get_posts')
def get_posts():
    return mongo.db.posts.find({})

@app.route('/')
def hello_world():
    return render_template("index.html")

@app.route('/api/history/<userid>')
def api_history(userid):
    return simplejson.dumps(output, default=date_handler)

@app.route('/api/go', methods = ['POST'])
def api_go():
    return simplejson.dumps(output_response, default=date_handler)

if __name__ == '__main__':
    app.run(debug=True)