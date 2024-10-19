# Blockchain - Peer-to-Peer Ledger System in Java

This project implements a basic blockchain system that supports a shared ledger among networked peers. The ledger is managed by three blockchain processes that communicate over a distributed network using multicast messaging to add and verify blocks. Each process operates independently and collaboratively to ensure the integrity of the blockchain.

## Project Overview:

• Blockchain.java: This Java file contains the core logic to run three separate processes, each responsible for handling blocks of data, performing verification, and updating the blockchain. Each process reads from a different input file.

• BlockchainLedger.json: The output file containing the verified blockchain ledger after the processes complete their execution. This file represents the collective ledger maintained by all three processes.

• BlockchainLedgerSample.json: A sample file illustrating what the final blockchain ledger looks like after running the program with sample data.

• BlockInput0.txt, BlockInput1.txt, BlockInput2.txt: These files contain input data for each of the three blockchain processes. Each file represents different unverified blocks to be processed.


## Key Features:

• Multicast Communication: Processes communicate using multicast messages to share unverified blocks and update the blockchain ledger.

• Proof of Work: The program simulates a simplified "proof of work" mechanism by having the processes compete to verify blocks.

• Peer-to-Peer Ledger: Each process maintains a local copy of the shared blockchain, ensuring consistency across the network of peers.

• Concurrency: Three concurrent processes (process 0, 1, and 2) read data, perform verification, and update the blockchain simultaneously.

## Technical Details:

• Developed using Java, with each process running independently but communicating over a multicast network.

• SHA-256 Hashing is used to verify blocks and ensure the integrity of the blockchain.

• Each process is responsible for reading its input file, generating unverified blocks, and multicasting them to the other processes for validation.

• The system uses public-key cryptography to secure messages exchanged between processes.

• JSON format is used for input and output files, representing both the blocks and the final ledger.

## How to Run the Project:

• Compile the project using: javac -cp "gson-2.8.2.jar" *.java (Execute twice to ensure all classes are compiled.)

• Run the three blockchain processes in separate terminals: java Blockchain 0 , java Blockchain 1, java Blockchain 2

• The program will read from the respective input files (BlockInput0.txt, BlockInput1.txt, and BlockInput2.txt), process the blocks, and write the verified blockchain to BlockchainLedger.json.

• Check the output ledger and logs in the generated BlockchainLedger.json and BlockchainLog.txt files.

## Learning Outcomes:

• Gained an understanding of the basic structure of blockchain technology.

• Developed familiarity with public-key cryptography techniques.

• Built a simple framework for a peer-to-peer shared ledger.

• Explored the concept of Proof-of-Work and its role in ensuring blockchain integrity.


## This project helped me understand the fundamentals of blockchain technology, distributed systems, and peer-to-peer communication, while working with cryptographic techniques and blockchain verification processes.
