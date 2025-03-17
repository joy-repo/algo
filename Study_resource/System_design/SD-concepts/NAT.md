
## Types of NAT (Network Address Translation)

NAT is used to modify IP addresses in packets as they traverse a network, often enabling multiple private devices to share a single public IP. There are different types of NAT, categorized based on how they handle IP and port mappings.

⸻

### 1️⃣ Based on Connection Type

1. **Full-Cone NAT (Static NAT)**
* Any external host can send packets to the internal device as long as the port is known.
* Once an internal device maps a port, that port remains open for any external connection.
* Best for Peer-to-Peer (P2P) applications like VoIP, gaming, and WebRTC.

✅ **Pros:**
* ✔️ Allows direct inbound connections (good for VoIP, P2P apps).
❌ **Cons:**
* ❌ Less secure (open access to internal devices).

⸻

2. **Restricted-Cone NAT**
* Works similarly to full-cone NAT but only allows inbound connections from IPs that the internal device has previously communicated with.
* The port is still mapped statically, but only for known external hosts.

✅ **Pros:**
* ✔️ More secure than full-cone NAT.
❌ **Cons:**
* ❌ Can still be problematic for some P2P connections.

⸻

3. **Port-Restricted Cone NAT**
* Even stricter than restricted-cone NAT: Only allows inbound connections from an IP and port that the internal device has contacted first.
* Prevents unsolicited traffic.

✅ **Pros:**
* ✔️ Provides better security.
❌ **Cons:**
* ❌ Breaks many peer-to-peer applications (harder for STUN to work).

⸻

4. **Symmetric NAT (Most Restrictive)**
* Each outbound connection gets a unique port mapping (i.e., different external ports for each destination).
* Only the exact external IP and port that the internal device contacted can send data back.
* Worst for P2P applications like WebRTC and VoIP.

✅ **Pros:**
* ✔️ High security (hard to track internal devices).
❌ **Cons:**
* ❌ Makes direct connections almost impossible.
* ❌ Requires TURN servers for WebRTC and VoIP.

⸻

### 2️⃣ Based on Address Mapping

1. **Static NAT (One-to-One)**
* A fixed one-to-one mapping between a private IP and a public IP.
* Used for servers that need a consistent external presence (e.g., web servers).

✅ **Pros:**
* ✔️ Easy to configure for permanent IP mappings.
❌ **Cons:**
* ❌ Doesn’t conserve IP addresses (requires many public IPs).

⸻

2. **Dynamic NAT (Many-to-Many)**
* Maps multiple private IPs to multiple public IPs dynamically.
* The router maintains a pool of public IPs and assigns them as needed.

✅ **Pros:**
* ✔️ Efficient for organizations with multiple devices.
❌ **Cons:**
* ❌ Needs a pool of public IPs (not as efficient as PAT).

⸻

3. **PAT (Port Address Translation) / NAT Overload**
* Most common NAT type used in home and enterprise networks.
* Maps multiple private IPs to a single public IP, differentiating them using port numbers.
* Used in home routers and ISPs.

✅ **Pros:**
* ✔️ Conserves public IP addresses (efficient use of IPv4).
* ✔️ Works well for browsing and basic communication.
❌ **Cons:**
* ❌ Can cause issues with P2P and some applications.

⸻

🔹 Quick Comparison of NAT Types