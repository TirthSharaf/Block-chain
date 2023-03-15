Grading procedures
File names: Blockchain.java, BlockchainLedger.json, BlockchainLedgerSample.json, BlockInput0.txt, BlockInput1.txt, BlockInput2.txt, BlockchainLog.txt. In grading your Blockchain programs we will (at least)...
check for plagiarism using TII and an array of other plagiarism checkers.
run scripts to automate the following:
download and unzip your program and the given data files into a directory
execute: javac -cp "gson-2.8.2.jar" *.java twice.
read your checklist-block.html file
erase BlockchainLedger.json [if you happen to have submitted it]
execute some version of the master script to run three processes from your Blockchain.class file using your data input files for each process with the given names. [start java Blockchain 0 / start java Blockchain 1 / start java Blockchain 2] NOTE: We will start your processes in the order, 0, 1,2 so you can use process 2 to trigger events based on the assumption that all three processes are now running.
follow your console output in each of the three processes.
examine your newly created BlockchainLedger.json file
read your BlockchainLedgerSample.json file if there is a problem with the BlockchainLedger.json file. Be sure to submit it!
repeat with our own data files.
read your BlockchainLog.txt file that has collected informative console output from your three running processes. (Copy your console output into a file manually.)
try the console commands you have provided for listing, verification, reading data files and etc.
attend to any special features or bragging rights.
read your code in some detail and read your comments in the code that show you understand how this all works.

Learning outcomes
At the end of this assignment you will:
Understand the basic structure of blockchain technology
Become familiar with some basic public key cryptographic techniques
Have a simple running framework for a peer-to-peer shared ledger system based on a blockchain
Understand the basic idea of "work" which is used to guarantee the validity of cryptocurrencies like Bitcoin
Have a collection of code utilities for working with blockchain technology, and cryptography that can be applied to many applications
Have a rudimentary working konwledge of JSON
In this assignment we will learn about the design of a simple blockchain system to support a ledger shared among networked peers.
Some of the basic crytographic and other techniques you will need are in the running utilities program (linked above). You are free to use the code in your assignment, but you MUST REMOVE MY COMMENTS and ADD YOUR OWN COMMENTS showing that you understand how the code works. Leave in citations of the original web source locations. Note that this pedagogical code is for presenting the general ideas of the blockchain design but makes no attempt to be up to date with the latest details regarding security. Therefor this code MUST be updated with the latest practices for any actual secure computing application.

You will probably have to listen to the lectures on blockchain technology to complete this assignment. You may find the YouTube video on Bitcoin (linked above) helpful as well, and the links provided to contemporary blockchain development.

Glossary:
Blockchain—a series of data blocks, where the validity of each succeeding block is dependent upon the previous block (and thus on all previous blocks).
Multicast—messages are sent to all participating multicast group nodes on a network, but not intended for other nodes in the network. For this assignment, multicast=send. That is we use the term multicast to imply these messages would only be going to processes in the blockchain consortium, but for our purposes, with only three processes total, it is the sames as broadcast.
Work—A computationally expensive puzzle to solve making it hard to verify a block. This makes it difficult for one node to create a counterfeit series of blocks since it is competing against the cooperation of all other nodes working together to build a valid blockchain. We will use a combination of simple real work and fake work here (the latter simulated with sleep() statements).
Proof-of-Work—A 256-bit hash value that meets the requirements to solve the work puzzle. Proof-of-Work results from hashing the data (which can include many things such as the Proof-of-Work value from the previous block) concatenated with a random value (our guess).
SHA-256 hash—A 256-bit string representing a typically much-larger string of data. It is all-but impossible to work backwards to reproduce any sutiable original data from the hash. One string of data deterministically produces the same SHA-256 hash each time. If even one bit of the data is changed, it will (with extremely high probability) produce a dramatically different hash.
Public key cryptograhy—A cryptographic technique using pairs of keys. When key one is used to encrypt a string of data bits, the other key must be used to decrypt it. The keys are publically bound to a stakeholder. One key is kept secret by the stakeholder and the other is distributed as public. To send a secret message, the recipient's public key is used; he then decrypts the message with his private key. To sign a document, the signer uses her secret key on the document to produce a signature; authorship is verified when the signer's public key successfully decrypts the signature.
BlockchainLedger.json the output file from your running system (created by process 0). If you aren't able to create one large compliant JSON file, then at least concatenate together chunks of compliant JSON.
The assignment


Submisson:
checklist-block.html
Blockchain.java
BlockchainLog.txt
BlockchainLedgerSample.json
[We will generate BlockchainLedger.json from running your program]
BlockInput0.txt, BlockInput1.txt, BlockInput2.txt
Block-postings.txt
Turn in (1) a single java file, Blockchain.java that will support three processes 0, 1 and 2 depending on the first arugment passed. (2) Turn in a single concatenated console logs file, BlockchainLog.txt showing record input, verification steps, receipt of public keys, listings, network communications from peers, and verification (and bragging rights functionality if you have some): Run your programs and display steps on each console to show us everything you've got. Clearly label the three sections PROCESS ZERO, PROCESS ONE and PROCESS TWO. (3) Submit your external text file containing one compliant json ledger containing your individual json blocks representating the final ledger in the file (BlockchainLedgerSample.json) populated with blockchain records. That is, run your program on the data input files and then rename your BlockchainLedger.json file to BlockchainLedgerSample.json (3) Submit all of your files except your data-input files, and your checklist concatenated together as .docx or .html or .txt file to the plagiarism checker as usual.

Use copy and paste from the consoles of your three processes (or whatever is easiest for you) to create your BlockchainLog.txt file. But note, all of the content must be generated by your running program as it is writing on each of the process consoles. You can clean it up for readability, and possibly add annotations, but you are never allowed to add "fake" output from your program.

Simplest overview, in each process:

Read in the data from the input files, put into unverified blocks (UBs).
Multicast (send) the UBs to all the processes (in JSON format).
Do the work of verifying a UB. Add it to the blockchain (BC), multicast the new BC to all the processes (in JSON format).
Repeat until done. Write the BC to disk (in JSON format).


General
Block-postings.txt: You are required to make at least two scholarly postings in the D2L discussion forums Blockchain thread. A scholarly post can be any of, e.g., a thread you start on design aspects of the Blockchain assignment, or blockchain technology in general, bragging rights for some interesting extra feature you plan on implementing, a well-formed question about how to implement or design the Blockchain, bigger-picture ideas about scale-up design considerations, or data-protection considerations. You can include annotated URLs as part of your discussion. Favorite topics might have to do with discussions about maintaining conversation state. Or, you can reply to any of the above that others have posted. Answers to coding or design questions are always a big hit. Postings that initiate or take part in discussions are always more highly prized. Submit your two (or more) postings with your Java code, to the TII link and also insert into the middle section of your Study Log. (Yes, you get double credit, once for this assignment, and once again as part of your study log.) Include ONLY your own text, not the context-setting text, or response text of others.
After working with the mini-projects as you wish, complete the basic assignment given here.
Read—and make sure you understand—the more advanced parts of the assignment (for which an implementation is not required).
Consider implementing some of the advanced parts of the assignment for fun and fame. Make sure you make your work known through the checklist.
Start with a dummy first block zero, with a known "Previous Proof of Work" string. Solve the puzzle in the usual way to produce a Proof-of-Work hash for this first dummy block.
Read input from AT LEAST the three provided data files, one file per process. We will run your program on the provided data files, and then also on our own data files that have the same format, but not necessarily the same number of records.
Each process maintains a copy of the shared ledger implemented as a blockchain. Each time the blockchain is updated it is multicast to all other peers. (Or, you can multicast only the update (new verified block) and add the verified block to each local existing blockchain. Each solution will have its own challenges to address.) Process zero is responsible for writing the updated BlockchainLedger.json file to disk. We will use only three processes, but your system design should—in theory—work for any number of peers with minor modifications.
Each process reads in a data file to create new records. A new record is placed in an unverified block. The block is marshaled as JSON and multicast to all the processes in the blockchain group which begin competing with one another to solve the "work" puzzle. One process solves a puzzle to verify the block, prepends the verified block to the blockchain, and multicasts the new blockchain to all other processes in the group. All other processes abandon the attempt to verify that block.
Every record in the shared ledger is considered canonical. It is ~impossible to insert a counterfeit block (record) into the blockchain.
We will use simple medical records in files, but the data is not really of concern to us. We could use the same system for a complete audit trail of business transactions, to support a digital currency, or to keep track of banking records.
Ordinarily many records would comprise a single block; for simplicity we will use a single record for each block.
Your system must run from some version of the provided run scripts. Adapt as needed for other operating systems. Use the trick that when process 2 starts, it will send a message to start the action on the other procsses as well.
If you have to, you can use sleep() statements arbitrarily inserted to help with process coordination as needed, but don't sleep too long so that we can get your program graded! Be sure to add comments for these coordination statements so we know you understand what is going on.


Ports and servers
Because we will have multiple participating processes running on the same machine we will need flexibility to avoid port conflicts. For each process:
Port 4710+process number receives public keys (4710, 4711, 4712)
Port 4820+process number receives unverified blocks (4820, 4821, 4822)
Port 4930+process number receives updated blockchains (4930, 4931, 4932)
Other ports at your discretion, but please use the same scheme: base+process number.
Feel free to use ten or twenty ports / servers if for some reason you need them, or not. This is entirely up to you.

Initialization
Using the start script, start your servers in the order P0, P1, P2
When P2 starts, it also triggers the multicast of public keys, and starts the whole system running.
All processes start with the same initial one-block (dummy entry) form of the blockchain.
After all public keys have been established read the data file for this process.
Create unverified blocks from the data and using JSON as the external data format, multicast each unverified block in turn to all other processes.
