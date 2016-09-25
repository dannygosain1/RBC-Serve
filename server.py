from flask import Flask, render_template, request
from datetime import date, datetime
from message import text
from flask import Flask
from flask_pymongo import PyMongo
import simplejson
import json
from bson import ObjectId
from bson import json_util

# Create Flask App
app = Flask(__name__)

# Set Up Database Connections
app.config['MONGO_DBNAME'] = 'rbc-serve-database'
app.config['MONGO_URI'] = 'mongodb://syde:syde@ds053828.mlab.com:53828/rbc-serve-database'

# Get Collections
mongo = PyMongo(app)

class JSONEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, ObjectId):
            return str(o)
        return json.JSONEncoder.default(self, o)

@app.route('/api/create_user', methods = ['POST'])
def create_user():
    return JSONEncoder().encode(mongo.db.users.insert(request.json))

@app.route('/api/get_users')
def get_users():
    user_list = list(mongo.db.users.find({}))
    return JSONEncoder().encode(user_list)

@app.route('/api/create_post', methods = ['POST'])
def create_post():
    return JSONEncoder().encode(mongo.db.posts.insert(request.json))

@app.route('/api/get_posts')
def get_posts():
    post_list = list(mongo.db.posts.find({}))
    return JSONEncoder().encode(post_list)

@app.route('/')
def hello_world():
    return render_template("test.html")

@app.route('/api/get_jobs/<userid>')
def api_history(userid):
    return simplejson.dumps(output, default=date_handler)

@app.route('/api/go', methods = ['POST'])
def api_go():
    return simplejson.dumps(output_response, default=date_handler)

@app.route('/api/get_jobs')
def get_jobs():
    jobs_list = list(mongo.db.jobs.find({}))
    return JSONEncoder().encode(jobs_list)

@app.route('/Analytics')
def Analytics():
    jobs_list = list(mongo.db.jobs.find({'employer':'dan@hotmail.com'}))
    revenue = [x['revenue'] for x in jobs_list]
    dates = [x['date'] for x in jobs_list]
    print revenue
    print dates
    chart = {"type": 'line',"zoomType": 'xy'}
    series = [{"name": 'Revenue', "data": revenue}]
    title = {"text": 'Historic Revenue'}
    xAxis = {"categories": dates}
    yAxis = {"title": {"text": 'Revenue (CAD)'}}
    obj = {'chart':chart,'series':series,'title':title,'xAxis':xAxis,'yAxis':yAxis}
    return json.dumps(obj)

if __name__ == '__main__':
    app.run(debug=True)