from flask import Flask, render_template, request
from datetime import date, datetime
from message import text
from flask import Flask
from flask_pymongo import PyMongo
import simplejson
import json
from bson import ObjectId
from bson import json_util
from bson.json_util import dumps
import ast

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

def stringify(records):
    post_list2 = []
    for pstdict in records:
        pstdict.pop('_id', None)
        post_list2.append(pstdict)
    return json.dumps(post_list2)[1:-1]

@app.route('/api/create_user', methods = ['POST'])
def create_user():
    return JSONEncoder().encode(mongo.db.users.insert(request.form.to_dict()))

@app.route('/api/get_users')
def get_users():
    user_list = list(mongo.db.users.find({}))
    return stringify(user_list)

@app.route('/api/check_user')
def check_job(user_email):
    email = request.args.get('email')[1:-1]
    password = request.args.get('password')[1:-1]

    record = list(mongo.db.jobs.find_one({'email':user_email}))
    if len(result) > 0:
        if password == result['password']:
            return render_template("main.html")

@app.route('/api/create_post', methods = ['POST'])
def create_post():
    return JSONEncoder().encode(mongo.db.posts.insert(request.form.to_dict()))

@app.route('/api/get_posts')
def get_posts():
    post_list = list(mongo.db.posts.find({}))
    return stringify(post_list)

@app.route('/api/get_posts_android')
def get_posts_android():
    post_list = list(mongo.db.posts.find({}))
    return JSONEncoder().encode(post_list)

@app.route('/api/search_posts', methods = ['POST'])
def search_posts():
    # location = request.args.get('location')[1:-1]
    # service = request.args.get('service')[1:-1]

    location = request.form['location']
    service = request.form['service']
    records = list(mongo.db.posts.find({'service':service, 'city':location}))
    return stringify(records)

@app.route('/api/create_job', methods = ['POST'])
def create_job():
    return JSONEncoder().encode(mongo.db.jobs.insert(request.form.to_dict()))

@app.route('/api/get_job/<employer_email>')
def get_job(employer_email):
    records = list(mongo.db.jobs.find({'employer':employer_email}))
    return stringify(records)

@app.route('/api/get_jobs')
def get_jobs():
    job_list = list(mongo.db.jobs.find({}))
    return stringify(job_list)

@app.route('/')
def hello_world():
    return render_template("login.html")

@app.route('/home')
def home_page():
    return render_template("main.html")

@app.route('/jobs')
def jobs_page():
    return render_template("jobs.html")

@app.route('/api/go', methods = ['POST'])
def api_go():
    return simplejson.dumps(output_response, default=date_handler)

@app.route('/analytics')
def analytics():
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