import paho.mqtt.publish as publish

publish.single("topic1","{'test':'true'}",hostname="localhost")
publish.single("topic1abcdef","{'test':'true'}",hostname="localhost")
publish.single("topic2","{'test':'false'}",hostname="localhost")

publish.single("topic1/a/b/c/de/f/1/2/3","{'test':'true'}",hostname="localhost")
publish.single("topic2/012/status/latest/reported/blaat/blaat/gaap","{'test':'false'}",hostname="localhost")
publish.single("topic2/012456789ABCDEF/status/latest/reported/blaat/blaat/gaap","{'test':'false'}",hostname="localhost")
