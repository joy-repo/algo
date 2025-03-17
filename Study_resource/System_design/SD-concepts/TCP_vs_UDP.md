
|Protocol|Header Size|Description|
|---|---|---|
|UDP (User Datagram Protocol)|8 bytes|Lightweight, minimal overhead, used for fast data transmission (e.g., VoIP, streaming).
|TCP (Transmission Control Protocol)|20-60 bytes|More complex, includes sequencing, error checking, and reliability mechanisms.


## UDP Header (8 Bytes)

UDP has a small, fixed-size header with only 4 fields:

|Field |Size (Bytes)|Description|
|---|--|---|
|Source Port|2|Identifies the sender's port.|
|Destination Port|2|Identifies the receiver's port.|
|Length|2|Total size of the UDP packet (header + data).|
|Checksum|2|Used for error detection (optional in IPv4).|

🟢 **Advantages of UDP:**
* ✔️ Low overhead (only 8 bytes).
* ✔️ Faster, better for real-time applications.

🔴 **Disadvantages:**
* ❌ No reliability or congestion control.


## TCP Header (20-60 Bytes)


|Field |Size (Bytes)| Description
|---|---|---|
|Source Port|2|Identifies sender's port.|
|Destination Port|2|Identifies receiver's port.|
|Sequence Number|4|Keeps track of data order.|
|Acknowledgment Number|4|Confirms received data.|
|Header Length|1 (4 bits)|Indicates the TCP header size.
|Flags|1 (6 bits)|Controls flow (SYN, ACK, FIN, etc.).|
|Window Size|2|Controls data flow (congestion control).|
|Checksum|2|Error checking.|
|Urgent Pointer|2|Indicates priority data.|
|Options (Optional)|0-40|Used for additional features like timestamps.|

🟢 **Advantages of TCP:**
* ✔️ Reliable (handles retransmission & ordering).
* ✔️ Connection-oriented (ensures data delivery).

🔴 **Disadvantages:**
* ❌ Higher overhead (minimum 20 bytes, max 60 bytes).
* ❌ Slower than UDP due to handshakes & acknowledgments.



