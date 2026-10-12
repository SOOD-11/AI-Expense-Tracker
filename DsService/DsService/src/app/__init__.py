import json
import os
from flask import Flask
from flask import request,jsonify
from kafka import KafkaProducer
from app.service.messageService import MessageService

app=Flask(__name__)
app.config.from_pyfile('config.py')
kafka_host=os.getenv('KAFKA_HOST','localhost');
kafka_port=os.getenv('KAFKA_PORT','9092');
kafka_bootstrap_servers=f"{kafka_host}:{kafka_port}"
producer=KafkaProducer(bootstrap_servers=kafka_bootstrap_servers,value_serializer=lambda v:json.dumps(v).encode('utf-8'))

@app.route('/v1/ds/message/',methods=['POST'])
def handle_message():
    if not request.json or 'message' not in request.json:
        return jsonify({"error": "message is required"}), 400
    message=request.json.get('message')
  
    messageService=MessageService()
  
    result=messageService.process_message(message)
   
    #send the serialized result to the kafka topic 
    producer.send('expense_service',result.__dict__)
    print(result)
    return jsonify(result.model_dump())


@app.route('/',methods=['GET'])
def handle_get():  
 message="Oye punjabi aagye oye "
 return message



if __name__ == " __main__":
    print("Starting server.......")
    app.run(host="0.0.0.0", port=8010,debug=True)