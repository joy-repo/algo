

![capacity-BW_Storage.png](capacity-BW_Storage.png)

## Tech Details

* Will use UDP since it provides faster delivery
* We can lose few packets

For peer to peer Video Transfer we can use **WebRTC (Web Real-Time Communication).**

### WebRTC consists of three primary APIs:
1.	**getUserMedia (Media Capture API)**
   * Accesses media devices (camera, microphone, screen) to capture audio and video streams.
   * Returns a MediaStream object that can be attached to HTML elements tags like video or audio.

2. **RTCPeerConnection (P2P Connection API)**
   * Establishes a direct connection between peers.
   * Handles network traversal (NAT and firewall bypassing).
   * Manages audio/video encoding, encryption, and transmission.
   * Uses protocols like SRTP (Secure Real-Time Protocol) for media encryption.
   
3. **RTCDataChannel (P2P Data API)**
* Enables direct exchange of arbitrary data between peers (e.g., file transfer, chat messages).
* Supports TCP-like reliable transmission and UDP-like unreliable transmission.

