import json
import time
import stomp


class MyListener(stomp.ConnectionListener):

    def on_error(self, headers, message):
        print('received an error "%s"' % message)

    def on_message(self, headers, message):
        print('received a message "%s"' % message)


if __name__ == '__main__':

    hosts = [('130.239.82.47', 61616)]
    conn = stomp.Connection(host_and_ports=hosts)
    # conn.set_listener('', MyListener())
    conn.start()
    conn.connect('myuser', 'password', wait=True,headers={'client-id': 'jms.queue'})
    # conn.subscribe(destination='exempel', id=1, ack='auto', headers={'subscription-type': 'MULTICAST',
    #                                                                  'durable-subscription-name': 'exempel'})

    counter = 0
    while True:
        counter = counter + 1
        message = [{"hej": "san"}, {"hej": "da: " + str(counter)}]
        json_string = json.dumps(message)
        conn.send(body=json_string, destination='exempel', headers={'transformation': 'jms-map-json',
                                                                    'content-length': '100000'})
        print("Sent message: " + json_string)

        time.sleep(1)
