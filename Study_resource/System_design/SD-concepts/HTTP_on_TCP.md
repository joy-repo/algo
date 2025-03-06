
# What is TCP ?

TCP is a transport layer protocol that ensures **reliable, ordered,** and error-checked delivery 
of data between devices over a network. 
It establishes a **connection-oriented communication** , 
meaning that a connection is established before 
data is transmitted, and it ensures that all data is 
received correctly.

**Key Features of TCP:** </br>
* Reliable data transfer (ensures all packets are delivered in order)
* Error checking and correction
* Flow control (prevents overwhelming the receiver)
* Congestion control (manages network traffic)
* Three-way handshake for connection establishment

## HTTP (HyperText Transfer Protocol)

HTTP is an application layer protocol used for transferring hypertext documents (like web pages) over the internet. It is the foundation of data communication on the web, enabling browsers to request and receive content from web servers.

**Key Features of HTTP:** </br>
* Stateless (each request is independent)
* Uses request-response model (client sends a request, server responds)
* Supports different methods like GET, POST, PUT, DELETE, etc.
* Can work with different transport protocols (usually TCP)

## How HTTP Works Over TCP – Real-Life Example

**Let’s say you open your browser and type https://www.example.com. Here’s what happens step by step:** </br>
```
+--------------------------+
|  User enters URL in browser |
+--------------------------+
            |
            v
+--------------------------+
|  DNS Lookup (Find IP)   |
+--------------------------+
            |
            v
+--------------------------+
|  TCP 3-Way Handshake   |
|  (SYN, SYN-ACK, ACK)    |
+--------------------------+
            |
            v
+--------------------------+
|  HTTP Request Sent      |
|  (GET /index.html)      |
+--------------------------+
            |
            v
+--------------------------+
|  Server Processes Request|
+--------------------------+
            |
            v
+--------------------------+
|  HTTP Response Sent      |
|  (200 OK + HTML Content)|
+--------------------------+
            |
            v
+--------------------------+
|  TCP Ensures Delivery   |
|  (Packets Ordered & Checked) |
+--------------------------+
            |
            v
+--------------------------+
|  Browser Renders Page   |
+--------------------------+
            |
            v
+--------------------------+
|  Additional HTTP Requests |
|  (For images, CSS, JS)   |
+--------------------------+
            |
            v
+--------------------------+
|  TCP Connection Closes  |
+--------------------------+

```


### Step 1: DNS Lookup

Before HTTP and TCP even come into play, your browser needs to find the IP address of www.example.com. It does this using DNS (Domain Name System).

Example:
* Your browser asks a DNS server: “What is the IP address of www.example.com?”
* The DNS server replies: “93.184.216.34”.

### Step 2: Establishing a TCP Connection

Now that your browser knows the IP address of the website, it needs to connect to the server.
Since HTTP runs on top of TCP, your browser initiates a TCP connection to the server at 93.184.216.34 (Port 80 for HTTP, Port 443 for HTTPS).

This happens using the Three-Way Handshake:
1.	**SYN:** Your browser sends a SYN (synchronize) message to the server.
2.	**SYN-ACK:** The server responds with a SYN-ACK (synchronize-acknowledge).
3.	**ACK:** Your browser sends an ACK (acknowledge) back, and the TCP connection is established.

Now, we have a reliable TCP connection.

### Step 3: Sending an HTTP Request

Your browser now sends an HTTP request over the TCP connection.
Example request:

```
GET /index.html HTTP/1.1  
Host: www.example.com  
User-Agent: Mozilla/5.0  
Accept: text/html

```
This tells the server:
* “I want the index.html page.”
* “I’m using Mozilla/Chrome/etc.”
* “I can accept text/html content.”


### Step 4: Server Processes the Request

* The web server receives the HTTP request.
* It finds index.html (or generates it dynamically).
* It prepares an HTTP response.

### Step 5: Server Sends an HTTP Response Over TCP

```http request
HTTP/1.1 200 OK  
Content-Type: text/html  
Content-Length: 5120  

<html>  
  <head><title>Example</title></head>  
  <body>Welcome to Example!</body>  
</html>
```

* HTTP/1.1 200 OK → Success, the page is available.
* tent-Type: text/html → It’s an HTML page.
* tent-Length: 5120 → Size of the content. 
* actual HTML content follows.

### Step 6: TCP Ensures Reliable Delivery

* The HTTP response is broken into packets.
*  ensures all packets arrive, in the correct order.
	•`If a packet is lost, TCP retransmits it.
	•	Once all packets are received, TCP reassembles them.
