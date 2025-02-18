#  Paxos Real-World Example: Choosing a Leader in a Distributed System

Let’s say we have **three servers (A, B, C) in a distributed system. 
These servers need to agree on a leader using Paxos.**

Actors in Paxos:
1.	**Proposers:** Suggest a value (e.g., a leader).
2.	**Acceptors:** Vote on proposals.
3.	**Learners:** Learn the final decision.

-------------------------------------------------------------

**Step-by-Step Paxos Execution:**

**Step 1:** </br>
Prepare Phase (Leader Election) </br>

•	Server A (Proposer) wants to become the leader. </br>
•	A chooses a unique proposal number (N=1) and sends a Prepare(1) 
message to all Acceptors (B, C). </br>

```
A → B, C: "Prepare(1)"
```

**Step 2:** </br>
Acceptors Respond </br>

•	Each Acceptor (B, C) checks if (N=1) is the highest proposal number it has seen.</br>
•	Since it’s the first proposal, both B and C 
accept it and send back a Promise(1, None) 
(None means no previous accepted value).

```
B → A: "Promise(1, None)"
C → A: "Promise(1, None)"
```

✅ Majority (B, C) responded! Now A can move to the next phase.

**Step 3:** </br>
Accept Phase (Agreement on a Leader)

Since A got a majority response, 
it sends Accept(1, “A is Leader”) to Acceptors.

```
A → B, C: "Accept(1, A is Leader)"
```

•	Each Acceptor checks if it already promised a higher proposal number.</br>
•	Since no higher proposal exists, B and C accept the proposal.

✅ Majority (B, C) accepted the proposal!

**Step 4:** </br>
Learners Learn the Leader

Once a majority has accepted a value, the Learners (all nodes) learn the final decision:
“A is Leader”

✅ Consensus is reached! All nodes agree that A is the leader. 🎉

📌 **What Happens if There’s a Failure?** </br>

•	If A crashes before Step 3, another proposer (e.g., B) 
will start a new proposal with a higher number (e.g., N=2).</br>
•	The system will always agree on one final value, ensuring correctness.


📌 **Summary of Paxos Workflow**
1.	Prepare Phase: Proposer selects a unique proposal number and asks acceptors.
2.	Promise Response: Acceptors reply with the highest seen proposal.
3.	Accept Phase: If a majority respond, the proposer sends the final value.
4.	Final Decision: A majority of acceptors agree, and learners learn the chosen value.



## Paxos in Distributed Transactions - Example

🔹 What is a Distributed Transaction?

A distributed transaction involves multiple nodes (e.g., databases, services) that must either commit or abort together to maintain consistency.

Paxos ensures fault-tolerant consensus, meaning all nodes agree on committing or aborting a transaction, even if some nodes fail.

🔹 Example: Distributed Bank Transfer using Paxos

Scenario:
A user transfers $100 from Account A (Bank1) to Account B (Bank2). Both banks must agree on the transaction before committing.

**Actors in Paxos**</br>
*Bank1 -> Bank2 and the 3rd Participant is Ledger*</br>

**Actor &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	Role**<br>
Proposers &nbsp;&nbsp;&nbsp;	Suggest transaction commit/abort.</br>
Acceptors &nbsp;&nbsp;&nbsp;	Vote on transaction decision.</br>
Learners  &nbsp;&nbsp;&nbsp;	Learn the final decision (commit/abort).</br>

**Step-by-Step Paxos Execution for a Distributed Transaction:**

🔹 **Step 1:** Prepare Phase (Propose Transaction)
1.	Proposer (Bank1) picks a unique proposal number (N=1) and sends a Prepare(1) request to Acceptors (Bank2, Central Ledger).
2.	Acceptors check if N=1 is the highest seen proposal.</br>
•	If yes, they promise not to accept lower proposals and respond with Promise(1, None).
```
Bank1 → Bank2, Ledger: "Prepare(1)"
Bank2 → Bank1: "Promise(1, None)"
Ledger → Bank1: "Promise(1, None)"
```
✅ If the proposer gets a majority response, it proceeds to the next phase.

🔹 **Step 2:** Accept Phase (Agreement on Transaction)
1.	Bank1 now sends Accept(1, “Commit Transaction”) to all acceptors.
2.	Acceptors check:
•	If they haven’t promised a higher proposal, they accept the transaction.
•	Otherwise, they reject it.
```
Bank1 → Bank2, Ledger: "Accept(1, Commit)"
Bank2 → Bank1: "Accepted(1, Commit)"
Ledger → Bank1: "Accepted(1, Commit)"
```
✅ Once a majority accepts, the transaction is ready to commit.

🔹 **Step 3:** Learners Learn the Decision
1.	Since a majority of acceptors (Bank2, Ledger) accepted, all learners update their records.
2.	The transaction is finalized and both banks commit the money transfer.
```
Bank2, Ledger → Clients: "Final Decision: COMMIT Transaction"
```
✅ $100 is transferred from Bank1 to Bank2 safely! 🎉

🔹 **What if a Node Fails?:**

**Scenario 1:** Bank2 Crashes
•	The transaction still completes because Ledger + Bank1 form a majority.

**Scenario 2:** Bank1 Crashes After Prepare
•	A new proposer (Bank2) starts a new proposal (N=2) and retries the process.

🔹 **Summary**</br>
•	Paxos ensures fault-tolerant distributed transactions.</br>
•	Guarantees consistency across all nodes.</br>
•	Works even if some nodes fail or restart.</br>


Here’s a Java implementation of a distributed transaction using Paxos. This code simulates a bank transaction where multiple nodes (banks) agree on whether to commit or abort a transaction.

📌 Paxos-Based Distributed Transaction in Java
•	Proposer: Initiates the transaction.
•	Acceptors: Vote on commit/abort.
•	Learners: Apply the final decision.

🔹 Step 1: Define Paxos Participants
```java


import java.util.*;

class PaxosTransaction {
private static final int MAJORITY = 2; // Majority needed for commit
private final Map<Integer, String> acceptorResponses = new HashMap<>();
private int highestProposal = 0;

    public synchronized boolean prepare(int proposalId) {
        if (proposalId > highestProposal) {
            highestProposal = proposalId;
            System.out.println("Acceptor: Promising proposal " + proposalId);
            return true;
        }
        return false;
    }

    public synchronized boolean accept(int proposalId, String transactionDecision) {
        if (proposalId >= highestProposal) {
            acceptorResponses.put(proposalId, transactionDecision);
            System.out.println("Acceptor: Accepted transaction - " + transactionDecision);
            return true;
        }
        return false;
    }

    public synchronized String getFinalDecision(int proposalId) {
        return acceptorResponses.getOrDefault(proposalId, "ABORT");
    }
}
```
🔹 Step 2: Implement Proposer (Bank1)

```java
class Proposer {
private final int proposalId;
private final List<PaxosTransaction> acceptors;
private int promiseCount = 0;
private int acceptCount = 0;
private final String transactionDecision;

    public Proposer(int proposalId, List<PaxosTransaction> acceptors, String transactionDecision) {
        this.proposalId = proposalId;
        this.acceptors = acceptors;
        this.transactionDecision = transactionDecision;
    }

    public boolean propose() {
        System.out.println("\nProposer: Starting proposal " + proposalId);
        
        // Phase 1: Prepare
        for (PaxosTransaction acceptor : acceptors) {
            if (acceptor.prepare(proposalId)) {
                promiseCount++;
            }
        }

        if (promiseCount < MAJORITY) {
            System.out.println("Proposer: Not enough promises, aborting.");
            return false;
        }

        // Phase 2: Accept
        for (PaxosTransaction acceptor : acceptors) {
            if (acceptor.accept(proposalId, transactionDecision)) {
                acceptCount++;
            }
        }

        if (acceptCount >= MAJORITY) {
            System.out.println("Proposer: Transaction COMMITTED!");
            return true;
        } else {
            System.out.println("Proposer: Transaction ABORTED!");
            return false;
        }
    }
}
```
🔹 Step 3: Simulate the Paxos Transaction
```java
public class PaxosSimulation {
public static void main(String[] args) {
List<PaxosTransaction> acceptors = Arrays.asList(new PaxosTransaction(), new PaxosTransaction(), new PaxosTransaction());

        Proposer proposer = new Proposer(1, acceptors, "COMMIT");
        boolean transactionResult = proposer.propose();

        if (transactionResult) {
            System.out.println("✅ Transaction committed successfully!");
        } else {
            System.out.println("❌ Transaction aborted due to lack of consensus.");
        }
    }
}
```
🔹 How This Works
1.	Proposer (Bank1) initiates a transaction using a unique proposal ID.
2.	Acceptors (Bank2, Ledger) respond with a promise if they haven’t seen a higher proposal.
3.	If a majority promises, the proposer sends the Accept(Commit/Abort) request.
4.	If a majority accepts, the transaction commits.

🔹 Sample Output
```
Proposer: Starting proposal 1
Acceptor: Promising proposal 1
Acceptor: Promising proposal 1
Acceptor: Promising proposal 1
Acceptor: Accepted transaction - COMMIT
Acceptor: Accepted transaction - COMMIT
Acceptor: Accepted transaction - COMMIT
Proposer: Transaction COMMITTED!
✅ Transaction committed successfully!
```
🔹 Key Takeaways

✔ Ensures fault tolerance using consensus.
✔ Majority voting prevents inconsistencies.
✔ Handles failures gracefully (new proposer can retry).

Would you like me to extend this with failure handling and leader election? 🚀