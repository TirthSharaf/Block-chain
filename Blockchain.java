/*
1. Name;- Tirth Sharaf

2. Date: 2023-02-28

3. Java version: 19.0.1.1

4. Precise examples / instructions to run this program:
In separate terminal windows:
Execution Steps:
> java cp ".:gson-2.8.2.jar" Blockchain 0
> java cp ".:gson-2.8.2.jar" Blockchain 1
> java cp ".:gson-2.8.2.jar" Blockchain 2

5. List of files needed for running the program.
 a. checklist-block.html
 b. Blockchain.java
 c. BlockchainLog.txt
 d. BlockchainLedgerSample.json
 e. BlockInput0.txt, BlockInput1.txt, BlockInput2.txt

6.. Web Sources Credits - THANKS!
https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa
https://www.quickprogrammingtips.com/java/how-to-generate-sha256-hash-in-java.html
https://mkyong.com/java/how-to-parse-json-with-gson/
http://www.java2s.com/Code/Java/Security/SignatureSignAndVerify.htm
https://www.mkyong.com/java/java-digital-signatures-example/ (not so clear)
https://javadigest.wordpress.com/2012/08/26/rsa-encryption-example/
https://www.programcreek.com/java-api-examples/index.php?api=java.security.SecureRandom
https://www.mkyong.com/java/java-sha-hashing-example/
https://stackoverflow.com/questions/19818550/java-retrieve-the-actual-value-of-the-public-key-from-the-keypair-object
https://www.java67.com/2014/10/how-to-pad-numbers-with-leading-zeroes-in-Java-example.html
- Thank you Dr. Elliott for the utility code.
 */

// Importing libraries for handling JSON data
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

// Importing libraries for working with input and output streams
import java.io.*;

import java.lang.reflect.Type;

// Importing libraries for working with networking features
import java.net.ServerSocket;
import java.net.Socket;

// Importing libraries for working with character encodings
import java.nio.charset.StandardCharsets;

// Importing libraries for working with cryptographic operations
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

// Importing libraries for working with concurrency features
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

// import date time related packages
import java.util.Date;

/**
 * Class for Blockchain
 *
 * The main method of this class initializes a BlockChainTaskCenter with a given
 * process ID.
 * The pID is obtained either from the command line arguments or set to 0 by
 * default if no argument is provided.
 */
public class Blockchain {

    /**
     * main method for blockchain class
     *
     * @param args String[] arguments to run the main blockchain
     * @throws NumberFormatException throw NumberFormatException if the first
     *                               argument does not match 0, or 1, or 2
     */
    public static void main(String[] args) {

        int queueLength = 6; // queueLength is set to 6 as a default value.

        int pID; // pID represent process id

        if (args.length < 1) { // Check if the command line argument is empty
            pID = 0; // default to 0 if no arguments are passed
        }

        switch (args[0]) { // Check the value of the first command line argument
            case "0":
                pID = 0; // Set the process ID to 0 if the first command line argument is "0"
                break;

            case "1":
                pID = 1; // Set the process ID to 1 if the first command line argument is "1"
                break;

            case "2":
                pID = 2; // Set the process ID to 2 if the first command line argument is "2"
                break;

            default:
                throw new NumberFormatException("wrong argument passed");// Throw an exception if the first command line
                                                                         // argument is not "0", "1", or "2"
        }

        // create a new instance of the BlockChainTaskCenter class with the chosen
        // process ID
        BlockChainTaskCenter taskDefinitionCenter = new BlockChainTaskCenter(pID);
    }
}

/**
 * The BlockchainRecord class represents a record in a blockchain.
 *
 * A Blockchain record has information about a patient's medical history.
 *
 * A blockchain record also records these metadata of the record
 * 1. creation of the blockchain record
 * 2. verification of the blockchain record
 * 3. hash of the previous block in the chain
 * 4. Unique identifier (UUID) of the record
 * 5. Timestamp when the record was written to the blockchain
 */
class BlockchainRecord implements Serializable {

    /**
     * the identifier of the current block in the blockchain
     */
    private String blockIdentifier;

    /**
     * signed block identifier
     */
    private String signedBlockIdentifier;

    /**
     * timestamp of the record
     */
    private String timestamp;

    /**
     * block number of the record
     */
    private String blockNumber;

    /**
     * first name of the patient
     */
    private String patientFirstName;

    /**
     * last name of the patient
     */
    private String patientLastName;

    /**
     * patient's date of birth
     */
    private String dob;

    /**
     * patent's ssn
     */
    private String ssn;

    /**
     * patient's medical diagnosis
     */
    private String medicalDiagnosis;

    /**
     * medical treatment given to a patient
     */
    private String medicalTreatment;

    /**
     * prescription given to a patient
     */
    private String prescription;

    /**
     * hash creator
     */
    private String hashCreator;

    /**
     * signed hash creator
     */
    private String signedHashCreator;

    /**
     * value of previous hash that won
     */
    private String pastHash;

    /**
     * winning current hash
     */
    private String winningHash;

    /**
     * signed winning current hash
     */
    private String winningHashSigned;

    /**
     * random seed value
     */
    private String randomSeed;

    /**
     * processID which verified the block
     */
    private String pidVerification;

    /**
     * creation of process
     */
    private String creationOfProcess;

    /**
     * Unique identifier of the blockchain record
     */
    private UUID uuid;

    // Getters and setters for private fields.
    public String getBlockIdentifier() {
        return blockIdentifier;
    }

    public void setBlockIdentifier(String blockIdentifier) {
        this.blockIdentifier = blockIdentifier;
    }

    public String getSignedBlockIdentifier() {
        return signedBlockIdentifier;
    }

    public void setSignedBlockIdentifier(String signedBlockIdentifier) {
        this.signedBlockIdentifier = signedBlockIdentifier;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getMedicalDiagnosis() {
        return medicalDiagnosis;
    }

    public void setMedicalDiagnosis(String medicalDiagnosis) {
        this.medicalDiagnosis = medicalDiagnosis;
    }

    public String getMedicalTreatment() {
        return medicalTreatment;
    }

    public void setMedicalTreatment(String medicalTreatment) {
        this.medicalTreatment = medicalTreatment;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getHashCreator() {
        return hashCreator;
    }

    public void setHashCreator(String hashCreator) {
        this.hashCreator = hashCreator;
    }

    public String getSignedHashCreator() {
        return signedHashCreator;
    }

    public void setSignedHashCreator(String signedHashCreator) {
        this.signedHashCreator = signedHashCreator;
    }

    public String getPastHash() {
        return pastHash;
    }

    public void setPastHash(String pastHash) {
        this.pastHash = pastHash;
    }

    public String getWinningHash() {
        return winningHash;
    }

    public void setWinningHash(String winningHash) {
        this.winningHash = winningHash;
    }

    public String getWinningHashSigned() {
        return winningHashSigned;
    }

    public void setWinningHashSigned(String winningHashSigned) {
        this.winningHashSigned = winningHashSigned;
    }

    public String getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(String randomSeed) {
        this.randomSeed = randomSeed;
    }

    public String getPidVerification() {
        return pidVerification;
    }

    public void setPidVerification(String pidVerification) {
        this.pidVerification = pidVerification;
    }

    public String getCreationOfProcess() {
        return creationOfProcess;
    }

    public void setCreationOfProcess(String creationOfProcess) {
        this.creationOfProcess = creationOfProcess;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns a string representation of this blockchain record.
     * The string representation consists of the names and values of
     * all the fields in this object, separated by commas and enclosed in
     * curly braces.
     *
     * @return a string representation of this blockchain record
     */
    @Override
    public String toString() {
        return "BlockchainRecord{" +
                "Block_ID='" + blockIdentifier + '\'' +
                ", SignedBlock_ID='" + signedBlockIdentifier + '\'' +
                ", TimeStamp='" + timestamp + '\'' +
                ", BlockNumber='" + blockNumber + '\'' +
                ", FirstName='" + patientFirstName + '\'' +
                ", LastName='" + patientLastName + '\'' +
                ", DateOfBirth='" + dob + '\'' +
                ", SSN_Number='" + ssn + '\'' +
                ", MedicalDiagnosis='" + medicalDiagnosis + '\'' +
                ", MedicalTreatment='" + medicalTreatment + '\'' +
                ", MedRX(Prescription)='" + prescription + '\'' +
                ", HashCreator='" + hashCreator + '\'' +
                ", SignedHashCreator='" + signedHashCreator + '\'' +
                ", PastHash='" + pastHash + '\'' +
                ", WinningHash='" + winningHash + '\'' +
                ", WinningHashSigned='" + winningHashSigned + '\'' +
                ", RandomSeed='" + randomSeed + '\'' +
                ", process_ID_Verification='" + pidVerification + '\'' +
                ", CreationOfProcess='" + creationOfProcess + '\'' +
                ", UUID=" + uuid +
                '}';
    }
}

/**
 * Blockchain Task Center
 */
class BlockChainTaskCenter {

    /**
     * Number of processes (i.e. peers)
     */
    public static final int numberOfProcesses = 3;

    /**
     * process Id
     */
    public static int processId;

    /**
     * server name
     */
    public static String serverName = "localhost";

    /**
     * a boolean that indicates whether all processes are ready if the value is true
     */
    public static boolean startProcessFlagExec = false;

    /**
     * A boolean flag for public key
     */
    public static boolean publicKeyFlag = false;

    /**
     * Counter for public keys of nodes
     */
    public static int publicKeyCounter = 0;

    /**
     * A public private key pair
     */
    public static KeyPair keyPair;

    /**
     * Array of public keys
     */
    public static PublicKey[] arrayOfPublicKeys = new PublicKey[numberOfProcesses];

    /**
     * Queue of unverified blocks
     */
    public static final PriorityBlockingQueue<BlockchainRecord> blockRecordsQueue = new PriorityBlockingQueue<>(50,
            new BlockchainComparator());

    /**
     * Blockchain ledger in the form of linked list
     */
    public static LinkedList<BlockchainRecord> ledger = new LinkedList<>();

    /**
     * blockchain records as linked list
     */
    public static LinkedList<BlockchainRecord> blockRecords = new LinkedList<>();

    // Indices for the input file
    private static final int firstNameIndex = 0;
    private static final int lastNameIndex = 1;
    private static final int dobIndex = 2;
    private static final int ssnIndex = 3;
    private static final int medicalDiagnosisIndex = 4;
    private static final int medicalTreatmentIndex = 5;
    private static final int medicalPrescriptionIndex = 6;

    /**
     * Constructor for BlockChainTaskCenter class
     *
     * @param processID
     */
    public BlockChainTaskCenter(int processID) {
        BlockChainTaskCenter.processId = processID;

        // initialize the ports for every processId
        new ProcessPorts().setPorts(processID);

        // start the thread
        run();
    }

    public void run() { // Method to run the blockchain

        // Print banner
        System.out.println("");
        System.out.println(" _____ _         __      __      __       _______      _______ _   __  __  ");
        System.out.println(" | __  | |___ ___| |_ ___| |_ ___|_|___   | __  |_ _   |_   _|_|___| |_| |_");
        System.out.println(" | __ -| | . |  _| '_|  _|   | .'| |   |  | __ -| | |    | | | |  _|  _|   |");
        System.out.println(" |_____|_|___|___|_,_|___|_|_|__,|_|_|_|  |_____|_  |    |_| |_|_| |_| |_|_|");
        System.out.println("                                                |___|                       ");
        System.out.println("");

        logger.info(
                "After initial block verification completes, additional commands (C, V, L) are available in the console");
        logger.info("Nodes have initial delay of about 15 to 20 seconds to sync up with other nodes\n");

        // print input file name and start server threads
        logger.info("Input file: " + String.format("BlockInput%d.txt", processId) + "\n");

        // start the main server in a new thread
        new Thread(new StartMainServer()).start();

        // start the public key server in a new thread
        new Thread(new PublicKeyServer()).start();

        // start the unverified block server in a new thread
        new Thread(new UnverifiedBlockServer(blockRecordsQueue)).start();

        // start unverified block server (that receives updated blocks) in a new thread
        new Thread(new UpdatedBlockchainServer()).start();

        try {
            // sleep the main (current) thread for 2 seconds.
            Thread.sleep(2000);
        } catch (Exception e) {
            logger.error("exception in thread sleep: " + e.getMessage());
        }

        if (processId == 2) {
            // Call the startAllProcesses(), if process ID is 2
            startAllProcesses();
        }

        try {
            // generate key pair with the random seed
            keyPair = generateKeyPair(999);
        } catch (Exception e) {
            logger.error("exception in generate keypair: " + e.getMessage());
        }

        // Wait until start process flag is executed
        while (!startProcessFlagExec) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                logger.error("startProcessFlagExec=true, exception in thread sleep: " + e.getMessage());
            }
        }

        logger.info("Starting program");

        // Call the multicast method to share public keys among all the processes in the
        // network.
        multicastPublicKeys();

        // go to sleep for a second if publicKeyFlag is false
        while (!publicKeyFlag) { // Wait until the public key has been obtained from the network
            try {
                // sleep for a second
                Thread.sleep(1000);
            } catch (Exception e) {
                logger.error("publicKeyFlag, exception in thread sleep: " + e.getMessage());
            }
        }

        // If the processID is 0, it is the first process to run, so it needs to create
        // a
        // dummy entry (block) to start the blockchain.
        if (processId == 0) {
            createFirstBlock();
        }

        // Call the ParseInputFile() method to read input files for each process
        // separately
        parseInputFile();
        multicast();

        try { // Wait for a short time to allow the multicast messages to propagate
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            // Handle the exception if it occurs
            exception.printStackTrace();
        }

        // A new thread is created to solve the work puzzle for processes P0, P1, and
        // P2.
        // Start a new thread to process the transaction records and add them to the
        // blockchain
        new Thread(new BlockchainWork(blockRecordsQueue)).start();

        try { // Wait for a longer time to allow the blockchain to be built
            Thread.sleep(21000);
        } catch (Exception e) {
            logger.error("exception in thread sleep while waiting for blockchain to be build: " + e.getMessage());
        }

        // Print a message indicating that the blockchain has been created and provide
        // options for further actions
        System.out.println("");
        System.out.println("=================================================");
        System.out.println("Blockchain ledger created successfully");
        System.out.println("=================================================");
        System.out.println("For further actions, enter one of these commands:");
        System.out.println("1. 'C' or 'c' to list credits of each process");
        System.out.println("2. 'V' or 'v' to repeat blockchain verification");
        System.out.println("3. 'L' or 'l' to list blocks of a blockchain");
        System.out.println("=================================================");

        // Loop indefinitely to wait for user input and execute the corresponding option
        while (true) {
            System.out.println("\nplease enter the command (v, c or l) : ");

            // Read the user input from the console
            Scanner consoleInputScanner = new Scanner(System.in);
            String command = consoleInputScanner.nextLine();

            // Execute the corresponding option based on the user input
            switch (command) {
                case "C":
                case "c":
                    Option_C();
                    break;
                case "V":
                case "v":
                    Option_V();
                    break;
                case "L":
                case "l":
                    Option_L();
                    break;
            }
        }
    }

    /**
     * This method is responsible for displaying all the verified records present in
     * the blockchain ledger file.
     * It reads the contents of the blockchain ledger file, deserialize the JSON
     * contents to LinkedList<BlockchainRecord>,
     * and iterate over each record to display the details in a formatted way on the
     * console.
     */
    private void Option_L() {
        Gson gson = new Gson(); // Create a new instance of Gson object
        LinkedList<BlockchainRecord> listRecords;
        try {
            // Read the contents of the blockchain ledger file using FileReader and create a
            // new instance of LinkedList<BlockchainRecord>
            Reader inputFileReader = new FileReader("BlockchainLedger.json");
            Type Format_Type = new TypeToken<LinkedList<BlockchainRecord>>() {
            }.getType();

            listRecords = gson.fromJson(inputFileReader, Format_Type);
            // Print a message on console to inform the user that the verified records will
            // be displayed next
            System.out.println(
                    "The following verified records are present in our BlockChain Ledger (most recent first):");

            Iterator<BlockchainRecord> it = listRecords.iterator();
            int count = listRecords.size();

            // Loop through each record and display the record details on the console in a
            // formatted way
            while (it.hasNext()) {
                BlockchainRecord iteratorRec = it.next();
                System.out.printf("%d. " + "%s " + "%s " + "%s " + "%s " + "%s " + "%s " + "%s " + "%s \n", count,
                        iteratorRec.getTimestamp(), iteratorRec.getPatientFirstName(), iteratorRec.getPatientLastName(),
                        iteratorRec.getDob(), iteratorRec.getSsn(), iteratorRec.getMedicalDiagnosis(),
                        iteratorRec.getMedicalTreatment(), iteratorRec.getPrescription());
                count--;
            }
        } catch (IOException e) {
            // If an error occurs while reading the file, print the stack trace to the
            // console
            logger.error("IOException in option_L: " + e.getMessage());
        }
    }

    /**
     * Option_V method verifies the integrity and authenticity of the blockchain
     * records
     * By checking the hash values, work puzzles, and digital signatures.
     * It reads the records from the BlockchainLedger.json file, and performs the
     * following checks:
     * Verifies the hash value of each record using SHA-256 algorithm
     * Verifies the work puzzle has been solved for each record
     * Verifies the digital signature of each record's winning hash and block
     * identifier
     * Prints out the verification result to the console.
     * 
     * @return void
     */
    private void Option_V() {
        boolean flag = false; // flag to keep track of any errors found during verification
        Gson gson = new Gson();// create a Gson object to deserialize JSON data
        LinkedList<BlockchainRecord> listRecords;
        try {
            // read the JSON file containing blockchain records
            Reader inputFileReader = new FileReader("BlockchainLedger.json");

            // specify the type of data to be deserialized
            Type FormatType = new TypeToken<LinkedList<BlockchainRecord>>() {
            }.getType();

            // deserialize the JSON data into a LinkedList of BlockchainRecord objects
            listRecords = gson.fromJson(inputFileReader, FormatType);

            // itereate through the list of blockchain records
            for (BlockchainRecord rec : listRecords) {

                String recordNumber = rec.getBlockNumber(); // get the block number of the current record

                // verify the record only if it is not the first block
                if (!recordNumber.equals("1")) {
                    String Data_String;

                    // Concatenate the block record components
                    String blockRecord = rec.getBlockIdentifier() +
                            rec.getPatientFirstName() + rec.getPatientLastName() + rec.getSsn() +
                            rec.getDob() + rec.getMedicalDiagnosis() +
                            rec.getMedicalTreatment() + rec.getPrescription() + rec.getCreationOfProcess();

                    try {// Append the previous hash to the block record
                        String UBlock = blockRecord;
                        UBlock = UBlock + rec.getPastHash();

                        // Append the random seed to the concatenated data
                        String concatData = UBlock + rec.getRandomSeed();

                        // Hash the concatenated data using SHA-256 algorithm
                        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                        byte[] hashValueBytes = messageDigest.digest(concatData.getBytes(StandardCharsets.UTF_8));
                        Data_String = BlockchainWork.byteArrayToString(hashValueBytes);

                        // Verify the hash value of the record
                        if (!Data_String.equals(rec.getWinningHash())) {
                            System.out.println("Hash value verification (using SHA-256) has failed.\n");
                            flag = true;
                        }

                        // Verify the work puzzle has been solved for the record
                        int workNum = Integer.parseInt(Data_String.substring(0, 4), 16);
                        if (!(workNum < 20000)) {
                            logger.warn("The required work puzzle has not been solved\n");
                            flag = true;
                        }

                        try {

                            // Verify the digital signature of the winning hash
                            boolean isSignatureVerified = verifysignature(rec.getWinningHash().getBytes(),
                                    arrayOfPublicKeys[Integer.parseInt(rec.getPidVerification())],
                                    Base64.getDecoder().decode(rec.getWinningHashSigned()));

                            // Check if the signature verification of the winning hash was successful or not
                            if (isSignatureVerified) {
                                logger.info(
                                        "#BlockId: " + recordNumber + " Hash (SHA-256) signature verification success");
                            } else {
                                logger.info(
                                        "#BlockId: " + recordNumber + " Hash (SHA-256) signature verification failure");
                                flag = true;
                            }

                            // Verify the digital signature of the block identifier
                            boolean isBlockIdVerified = verifysignature(rec.getBlockIdentifier().getBytes(),
                                    arrayOfPublicKeys[Integer.parseInt(rec.getCreationOfProcess())],
                                    Base64.getDecoder().decode(rec.getSignedBlockIdentifier()));

                            // Check if the signature verification of the block identifier was successful or
                            // not
                            if (isBlockIdVerified) {
                                logger.info(
                                        "#BlockId: " + recordNumber + " Signature verification was successful");
                            } else {
                                logger.info("#BlockId: " + recordNumber + " Signature verification was failed!");
                                flag = true;
                            }
                        } catch (Exception e) {
                            logger.error("exception: " + e.getMessage());
                        }
                    } catch (NoSuchAlgorithmException e) {
                        logger.error("NoSuchAlgorithmException exception:" + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            // Catch any exceptions thrown during verification and print the error message
            logger.error("exception:" + e.getMessage());
        }

        // Generate final message based on whether there were any errors during
        // verification
        String message = (!flag) ? "Blockchain is verified successfully"
                : "There are errors in the blockchain ledger";
        logger.info(message); // Print the final message
    }

    /**
     * This method reads the blockchain ledger file and prints out the number of
     * credits received by each process
     * for verification.
     */
    private void Option_C() {
        Gson gson = new Gson();
        LinkedList<BlockchainRecord> listRecords;

        // array to track credit score of each process
        int[] processCreditScore = new int[numberOfProcesses];

        try {
            // Read the blockchain ledger file
            Reader inputFileReader = new FileReader("BlockchainLedger.json");

            // Define the type for deserializing the JSON into a LinkedList of
            // BlockchainRecords
            Type ledgerRecordType = new TypeToken<LinkedList<BlockchainRecord>>() {
            }.getType();

            // Deserialize the JSON into a LinkedList of BlockchainRecords
            listRecords = gson.fromJson(inputFileReader, ledgerRecordType);

            // Iterate through the list of BlockchainRecords and count the number of credits
            // each process has received
            for (BlockchainRecord rec : listRecords) {
                if (rec.getPidVerification() != null) {

                    int processNumber = Integer.parseInt(rec.getPidVerification());
                    processCreditScore[processNumber]++;
                }
            }

            // print the credit score of processes
            System.out.println("Credits received from verification processes:");
            System.out.printf("Credit for P0: %d" + "\n" +
                    "Credit for P1: %d" + "\n" +
                    "Credit for P2: %d", processCreditScore[0],
                    processCreditScore[1], processCreditScore[2]);

        } catch (IOException e) {
            logger.error("IOException while processing Option_C: " + e.getMessage());
        }
    }

    /**
     * This method multicasts the unverified blocks to all the processes in the
     * network.
     * It iterates through the blockRecords list, and for each block, it sends a
     * message
     * to all the processes in the network.
     *
     * @throws IOException If an I/O error occurs while sending the unverified
     *                     blocks.
     */
    public void multicast() {
        Socket multicastSocket;
        PrintStream sentToServer;
        BlockchainRecord temporaryBlockchainRecord;

        // Iterator for iterating through the blockRecords list
        Iterator<BlockchainRecord> iteratorVar = blockRecords.iterator();

        try {
            // Iterate through the blockRecords list
            while (iteratorVar.hasNext()) {
                temporaryBlockchainRecord = iteratorVar.next();

                // Convert the BlockchainRecord object to JSON string
                String blockRecordStr = jsonBuilder(temporaryBlockchainRecord);

                // Send the message to all the processes in the network
                for (int i = 0; i < numberOfProcesses; i++) {

                    multicastSocket = new Socket(serverName, ProcessPorts.unverifiedBlockServerPortConst + i);
                    sentToServer = new PrintStream(multicastSocket.getOutputStream());
                    sentToServer.println(blockRecordStr);
                    sentToServer.flush();
                    multicastSocket.close();
                }
            }
        } catch (Exception e) {
            logger.error("exception in multicast: " + e.getMessage());
        }
    }

    /**
     * Check if a blockchain record already exists in the ledger based on the block
     * identifier.
     * 
     * @param record the blockchain record to be checked for duplicates
     * @return true if a duplicate record is found, false otherwise
     */
    public static boolean checkDuplicateRecords(BlockchainRecord record) {

        // iterate through the ledger
        for (BlockchainRecord block : ledger) {
            // compare blockId of our record against every stored blocks in ledger
            if (record.getBlockIdentifier().equals(block.getBlockIdentifier()))
                return true; // duplicate was found
        }
        return false;
    }

    /**
     * Generates a key pair for RSA encryption using a specified random seed.
     * 
     * @param randomSeed the seed to be used for the random number generator
     * @return the generated key pair
     * @throws Exception if there is an error generating the key pair
     */
    public static KeyPair generateKeyPair(long randomSeed) throws Exception {

        // Create a new key pair generator for RSA
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");

        // Create a new instance of SecureRandom and initialize it with the specified
        // random seed
        SecureRandom randomObject = SecureRandom.getInstance("SHA1PRNG", "SUN");

        // seed randomObject with random seed
        randomObject.setSeed(randomSeed);

        // Initialize the key generator with the specified random number generator and
        // key size
        keyGenerator.initialize(1024, randomObject);

        // return the newly create key pair
        return keyGenerator.generateKeyPair();
    }

    /**
     * This method is used to multicast the public key to all the other processes in
     * the network.
     * It gets the public key from the keyPair object and encodes it using Base64
     * encoding.
     * It then creates a string by concatenating the processId with the encoded
     * public key.
     * It then creates a socket connection with each process in the network and
     * sends the string through the connection.
     * It catches any exceptions that may occur during the process.
     */
    public void multicastPublicKeys() {

        // Declare variables for the socket and print stream
        Socket socket;
        PrintStream send2Server;

        // Get the public key for this process and convert it to a string
        byte[] publicKey = keyPair.getPublic().getEncoded();
        String pkStr = Base64.getEncoder().encodeToString(publicKey);

        // For debugging, print the public key only
        logger.debug("New Public Key for Multicasting: " + pkStr);

        try { // Loop through all processes and send the public key to each one

            // For each process in the network, create a socket connection and send the
            // processId and public key string
            for (int i = 0; i < numberOfProcesses; i++) {

                // Create a socket to connect to the key server for the current process
                socket = new Socket(serverName, ProcessPorts.keyServerPortConst + i);

                // Create a print stream to send the public key to the server
                send2Server = new PrintStream(socket.getOutputStream());

                // Create a string that includes the process ID and public key
                String processIdPublicKey = processId + " " + pkStr;

                // Send the string to the server and flush the print stream
                send2Server.println(processIdPublicKey);
                send2Server.flush();

                socket.close();
            }
        } catch (Exception e) {
            logger.error("exception in multicastPublicKeys: " + e.getMessage());
        }
    }

    /**
     * This method starts all the processes in the blockchain network by sending a
     * "Start" signal to each process.
     * The method creates a socket connection to each process and sends the signal
     * through the socket.
     * After sending the signal, the socket is closed.
     * 
     * @return boolean value true indicating that all processes have been
     *         successfully started
     */
    public boolean startAllProcesses() {

        Socket startSocket;
        PrintStream send2Server;

        try { // iterate over all processes in the network

            for (int i = 0; i < numberOfProcesses; i++) {

                // create socket connection to the process
                startSocket = new Socket(serverName, ProcessPorts.startServerPortConst + i);

                // send "Start" signal to the process through the socket
                send2Server = new PrintStream(startSocket.getOutputStream());
                logger.info("Signalling Start");
                send2Server.println("Start");
                send2Server.flush();

                // close the socket
                startSocket.close();
            }
        } catch (Exception e) {
            logger.error("exception in startAllProcesses: " + e.getMessage());
        }
        return true; // return true to indicate that all processes have been successfully started
    }

    /**
     * This method is used to create the first block in the blockchain. It generates
     * a unique
     * block identifier using UUID and sets default values in the blockchain record
     * object.
     * It also sets the current timestamp by appending the process ID to the current
     * time in milliseconds.
     * It then generates the SHA256 hash of the blockchain record object and adds
     * the record to the ledger.
     * Finally, if the process ID is 0, it sends the block to the ledger and saves
     * the ledger to disk.
     * 
     * @return true if the operation was successful, false otherwise
     */
    public static void createFirstBlock() {
        // data in the form of SHA256 hash
        String data;

        // create a blockchain record object
        BlockchainRecord record = new BlockchainRecord();

        Date currentDate = new Date();

        // current time in milliseconds
        long currentTime = currentDate.getTime();

        // current time as string
        String strTime = String.valueOf(currentTime);

        // append pid to the timestemp to have the unique timestamp value
        String stampedTime = strTime + "." + processId;

        // generate a new random UUID
        String uuid = UUID.randomUUID().toString();

        // set default values in blockchain record object
        record.setBlockIdentifier(uuid);
        record.setTimestamp(stampedTime);
        record.setPatientFirstName("George");
        record.setPatientLastName("Bushel");
        record.setSsn("111-00-1111");
        record.setDob("1890.10.10");
        record.setMedicalDiagnosis("Cancer");
        record.setMedicalTreatment("Chemotheraphy");
        record.setPrescription("HealthyFood");
        record.setPastHash("1111111111");
        record.setBlockNumber("1");

        // blockchain record as string
        String blockRecord = record.getBlockIdentifier() +
                record.getPatientFirstName() +
                record.getPatientLastName() +
                record.getSsn() +
                record.getDob() +
                record.getMedicalDiagnosis() +
                record.getMedicalTreatment() +
                record.getPrescription();

        // generate SHA256 hash of the blockchain record object
        data = Md2StringBuilder(blockRecord);

        // set the winning hash value in the blockchain record object
        record.setWinningHash(data);

        // add the record to the ledger at index 0
        ledger.add(0, record);

        logger.info("Size of the BlockChain Ledger is: " + ledger.size());

        // if the process ID is 0, send the block to the ledger and save the ledger to
        // disk
        if (processId == 0) {

            logger.info("Creating the first dummy block in the blockchain");

            sendBlockToLedger(record, "BlockchainLedgerUpdate");

            saveToDisk();
        }
    }

    /**
     * Sends the specified BlockchainRecord to all nodes in the network for updating
     * the ledger.
     * 
     * @param blockchainRecord the BlockchainRecord to be sent to the network
     * @param operation        the operation to be performed on the BlockchainRecord
     *                         ("bcLedgerUpdate" or "re-VerifyBlock")
     */
    public static void sendBlockToLedger(BlockchainRecord blockchainRecord, String operation) {
        Socket socket;
        PrintStream sendToServer;

        switch (operation) {
            case "bcLedgerUpdate":
                try {

                    for (int i = 0; i < numberOfProcesses; i++) {

                        // create a socket to connect to the server
                        socket = new Socket(serverName, ProcessPorts.updatedBlockServerPortConst + i);

                        // create a PrintStream object to send the data to the server
                        sendToServer = new PrintStream(socket.getOutputStream());

                        // send the blockchain record to the server in JSON format
                        sendToServer.println(jsonBuilder(blockchainRecord));

                        // print message to console for verification purposes
                        logger.info("Broadcasting the verified block with blockId: "
                                + blockchainRecord.getBlockIdentifier());
                        sendToServer.flush();
                        socket.close();
                    }
                } catch (IOException e) {
                    logger.error("IOException exception:" + e.getMessage());
                }
                break;

            case "re-VerifyBlock":
                try {
                    // create a socket to connect to the server
                    for (int v = 0; v < numberOfProcesses; v++) {

                        socket = new Socket(serverName, ProcessPorts.unverifiedBlockServerPortConst + v);

                        // create a PrintStream object to send the data to the server
                        sendToServer = new PrintStream(socket.getOutputStream());

                        // send the blockchain record to the server in JSON format
                        sendToServer.println(jsonBuilder(blockchainRecord));

                        // print message to console for verification purposes
                        System.out.println("Block is being broadcasted: " + blockchainRecord.getBlockIdentifier());

                        sendToServer.flush();
                        socket.close();
                    }
                } catch (IOException e) {
                    logger.error("IOException in re-VerifyBlock:" + e.getMessage());
                }
                break;
        }
    }

    /**
     * Takes a BlockchainRecord object and converts it into a JSON string using the
     * Gson library.
     * 
     * @param blockRec The BlockchainRecord object to convert to JSON.
     * @return The JSON string representing the BlockchainRecord object.
     */
    public static String jsonBuilder(BlockchainRecord blockRec) {

        // Create a new Gson object with pretty printing enabled
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convert the BlockchainRecord object to a JSON string
        String json = gson.toJson(blockRec);
        return json;
    }

    /**
     * Reads the input data file for the current process and creates a blockchain
     * record for each line of input data.
     * Each blockchain record is then signed and hashed before being added to the
     * blockRecords ArrayList.
     * 
     * @throws FileNotFoundException if the input file for the current process is
     *                               not found.
     * @throws IOException           if there is an I/O error while reading the
     *                               input data.
     * @throws InterruptedException  if the thread is interrupted while sleeping.
     */
    public static void parseInputFile() {

        // Get the input file name based on the process id
        String inputFile = String.format("BlockInput%d.txt", processId);

        try {
            // Open the input file for reading
            BufferedReader inputData = new BufferedReader(new FileReader(inputFile));

            // Declare variables to hold the input data
            String[] tokensArray;
            String inputStringData;
            String uuid;

            try {
                // Check if the inputData is not null before proceeding
                while ((inputStringData = inputData.readLine()) != null) {

                    // Create a new blockchain record
                    BlockchainRecord blockchainRecord = new BlockchainRecord();

                    // current date in ISO
                    Date currentDate = new Date();

                    // current time in milliseconds
                    long time = currentDate.getTime();

                    // current time as string
                    String timeStampStr = String.valueOf(time);

                    // Add the process id to the timestamp to make it unique across all processes
                    String timeStampWithProcessId = timeStampStr + "." + processId;

                    // Generate a random UUID for the block identifier
                    uuid = UUID.randomUUID().toString();

                    // Split the input data into individual tokens and store them as an array of
                    // strings.
                    tokensArray = inputStringData.split(" +");

                    String signedBlock = "";

                    try {
                        // sign the block data using private key
                        byte[] signedData = signData(uuid.getBytes(), keyPair.getPrivate());

                        // encode signed data with Base 64 encoding
                        signedBlock = Base64.getEncoder().encodeToString(signedData);

                    } catch (Exception e) {
                        logger.error("exception in sign data:" + e.getMessage());
                    }

                    // Set the remaining fields in the blockchain record from the input data
                    blockchainRecord.setBlockIdentifier(uuid);
                    blockchainRecord.setTimestamp(timeStampWithProcessId);
                    blockchainRecord.setSignedBlockIdentifier(signedBlock);
                    blockchainRecord.setCreationOfProcess(String.valueOf(processId));
                    blockchainRecord.setPatientFirstName(tokensArray[firstNameIndex]);
                    blockchainRecord.setPatientLastName(tokensArray[lastNameIndex]);
                    blockchainRecord.setSsn(tokensArray[ssnIndex]);
                    blockchainRecord.setDob(tokensArray[dobIndex]);
                    blockchainRecord.setMedicalDiagnosis(tokensArray[medicalDiagnosisIndex]);
                    blockchainRecord.setMedicalTreatment(tokensArray[medicalTreatmentIndex]);
                    blockchainRecord.setPrescription(tokensArray[medicalPrescriptionIndex]);

                    // Add the blockchain record to the list of block records
                    blockRecords.add(blockchainRecord);

                    // Concatenate the fields in the blockchain record to create a string for
                    // hashing
                    String blcokRecordString = blockchainRecord.getBlockIdentifier()
                            + blockchainRecord.getPatientFirstName()
                            + blockchainRecord.getPatientLastName() +
                            blockchainRecord.getSsn() + blockchainRecord.getDob()
                            + blockchainRecord.getMedicalDiagnosis() +
                            blockchainRecord.getMedicalTreatment() + blockchainRecord.getPrescription()
                            + blockchainRecord.getCreationOfProcess();

                    // Hash the string using the MD2 algorithm
                    String hash256DigestStr = Md2StringBuilder(blcokRecordString);

                    // Sign the hash using the process's private key
                    String signedHash = "";

                    try {
                        byte[] signedData2 = signData(hash256DigestStr.getBytes(), keyPair.getPrivate());
                        signedHash = Base64.getEncoder().encodeToString(signedData2);
                    } catch (Exception e) {
                        logger.error("exception in sign data second time:" + e.getMessage());
                    }

                    // Set the hash creator and signed hash creator in the blockchain record
                    blockchainRecord.setHashCreator(hash256DigestStr);
                    blockchainRecord.setSignedHashCreator(signedHash);

                    // sleep for 1 second
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                logger.error("IOException: " + e.getMessage());
            } catch (InterruptedException e) {
                logger.error("InterruptedException: " + e.getMessage());
            }

        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException: " + e.getMessage());
        }
    }

    /**
     * Computes the SHA-256 hash of the given input string and returns it as a
     * hexadecimal string.
     * 
     * @param input the input string to be hashed
     * @return the SHA-256 hash of the input string as a hexadecimal string
     */
    private static String Md2StringBuilder(String input) {
        StringBuffer buffer;
        String hash = "";
        try {

            // Create a MessageDigest object for the SHA-256 algorithm
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Update the digest with the input string bytes
            messageDigest.update(input.getBytes());

            // Compute the hash value as a byte array
            byte[] byteValue = messageDigest.digest();

            // Convert the byte array to a hexadecimal string
            buffer = new StringBuffer();
            for (byte bd : byteValue) {
                // convert byte to data in hexadecimal
                buffer.append(Integer.toString((bd & 0xff) + 0x100, 16).substring(1));
            }

            // Set the hash value as a string
            hash = buffer.toString();

        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException exception:" + e.getMessage());
        }

        return hash;
    }

    /**
     * Signs the given data using the private key and the "SHA1withRSA" algorithm.
     * 
     * @param bytesData   the data to be signed
     * @param aPrivateKey the private key used for signing
     * @return the signed data
     * @throws SignatureException       if the signature algorithm is inappropriate
     *                                  or if this signature
     * @throws InvalidKeyException      if the given key is inappropriate for
     *                                  initializing this signature
     * @throws NoSuchAlgorithmException if the "SHA1withRSA" algorithm is not
     *                                  available in the environment
     */
    public static byte[] signData(byte[] bytesData, PrivateKey aPrivateKey)
            throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {

        // Create a Signature object with the "SHA1withRSA" algorithm
        Signature signer = Signature.getInstance("SHA1withRSA");

        // Initialize the Signature object with the private key
        signer.initSign(aPrivateKey);

        // Update the Signature object with the data to be signed
        signer.update(bytesData);

        // Return the signed data
        return (signer.sign());
    }

    /**
     * Verifies the signature of the given data using the given public key and the
     * provided signature.
     * 
     * @param bytesData The data to verify the signature for.
     * @param publicKey The public key to use for signature verification.
     * @param decode    The signature to verify.
     * @return A boolean value indicating whether the signature verification was
     *         successful or not.
     * @throws NoSuchAlgorithmException If the specified algorithm for signature
     *                                  verification is not available.
     * @throws InvalidKeyException      If the specified key is invalid.
     * @throws SignatureException       If the signature is not valid or is
     *                                  corrupted.
     */

    public static boolean verifysignature(byte[] bytesData, PublicKey publicKey, byte[] decode)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA1withRSA");
        signer.initVerify(publicKey);
        signer.update(bytesData);
        return (signer.verify(decode));
    }

    /**
     * This method saves the current blockchain ledger to the disk.
     * It converts the ledger to JSON format using Gson library and writes it to the
     * "BlockchainLedger.json" file.
     * The ledger is stored in pretty printed format for easy readability.
     * If any IO exception occurs, it prints an error message with the exception
     * message.
     */
    public static void saveToDisk() {
        logger.info("Persisting blockchain to disk");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileWriter writer = null;
        try {
            writer = new FileWriter("BlockchainLedger.json");

            // gson.toJson(BlockChainTaskCenter.ledger, writer);

            String content = gson.toJson(BlockChainTaskCenter.ledger);
            writer.write(content);
        } catch (IOException e) {
            logger.error("saveToDisk exception:" + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                logger.error("IOException, can not close the file writer: " + e.getMessage());
            }
        }
    }
}

/**
 * This class defines the port numbers for various servers.
 * It also includes variables for the actual port numbers.
 */
class ProcessPorts {

    /**
     * Port number constant for StartServer
     */
    public static final int startServerPortConst = 4600;

    /**
     * Port number constant for Public Key Server
     */
    public static final int keyServerPortConst = 4710;

    /**
     * Port number constant for Unverified Block Server
     */
    public static final int unverifiedBlockServerPortConst = 4820;

    /**
     * Port number constant for Updated Block Server
     */
    public static final int updatedBlockServerPortConst = 4930;

    /**
     * startServer port variable
     */
    public static int startServerPort;

    /**
     * keyServer port variable
     */
    public static int keyServerPort;

    /**
     * unverified block port variable
     */
    public static int unverifiedBlockServerPort;

    /**
     * blockchain port variable
     */
    public static int updatedBlockServerPort;

    /**
     * 
     * Sets the ports for each process based on its ID.
     * 
     * @param processId the ID of the process.
     */
    public void setPorts(int processId) {

        // Set the port number for the start server by adding the process ID to the
        // constant port number
        startServerPort = startServerPortConst + processId;

        // Set the port number for the public key server by adding the process ID to the
        // constant port number
        keyServerPort = keyServerPortConst + processId;

        // Set the port number for the unverified block server by adding the process ID
        // to the constant port number
        unverifiedBlockServerPort = unverifiedBlockServerPortConst + processId;

        // Set the port number for the updated block server by adding the process ID to
        // the constant port number
        updatedBlockServerPort = updatedBlockServerPortConst + processId;

    }
}

/**
 * A comparator for comparing two BlockchainRecord objects based on their
 * timestamps.
 * If the timestamps are equal, returns 0. If date1 is null, returns -1. If
 * date2 is null, returns 1.
 */
class BlockchainComparator implements Comparator<BlockchainRecord> {

    @Override
    // Compare method to compare two BlockchainRecord objects based on their
    // timestamps
    public int compare(BlockchainRecord record1, BlockchainRecord record2) {

        // Get the timestamps of the two records being compared
        String date1 = record1.getTimestamp();
        String date2 = record2.getTimestamp();

        // If the timestamps are equal, return 0
        if (date1.equals(date2)) {
            return 0;
        }
        // If the first timestamp is null, return -1 (i.e., it comes before the second
        // record)
        if (date1 == null) {
            return -1;
        }
        // If the second timestamp is null, return 1 (i.e., it comes before the first
        // record)
        if (date2 == null) {
            return 1;
        }

        // Otherwise, compare the timestamps and return the result
        return date1.compareTo(date2);
    }
}

/**
 * This class represents a runnable that starts the main server,
 * which listens for incoming connections on the specified port and creates new
 * worker
 * threads to handle the requests.
 */
class StartMainServer implements Runnable {
    public void run() { // Starts the main server and creates worker threads to handle incoming
                        // requests.

        int queueLength = 6;

        Socket soc;

        logger.info("Starting Main server on port: " + ProcessPorts.startServerPort);

        try {

            // Create a new server socket to listen for incoming connections on the
            // specified port
            ServerSocket socket = new ServerSocket(ProcessPorts.startServerPort, queueLength);
            while (true) {

                // Accept incoming connections and create a new worker thread to handle the
                // request
                soc = socket.accept();

                new StartMainServerWorker(soc).start();
            }
        } catch (IOException e) {
            logger.error("IOException in StartMainServer: " + e.getMessage());
        }
    }
}

/**
 * This class represents a worker thread for the StartMainServer class.
 * It receives and processes incoming requests from the main server.
 */
class StartMainServerWorker extends Thread {
    Socket socket;

    /**
     * Constructs a StartMainServerWorker with a given socket.
     *
     * @param socket the socket associated with this worker
     */
    public StartMainServerWorker(Socket socket) {
        this.socket = socket;
    }

    // The run method processes the incoming request from the socket input stream
    // and sets the
    // startProcessFlagExec of the BlockChainTaskCenter class to true.
    public void run() {
        try {

            // create a BufferedReader to read data from the socket input stream
            BufferedReader inputData = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // read the incoming data from the input stream
            String data = inputData.readLine();

            // set the startProcessFlagExec of the BlockChainTaskCenter class to true
            BlockChainTaskCenter.startProcessFlagExec = true;

            socket.close(); // close the socket connection
        } catch (IOException e) {
            logger.error("IOException: " + e.getMessage());
        }
    }
}

/**
 * Implements a server that listens for incoming connections on the key server
 * port and creates a new worker thread
 * for each incoming connection.
 */
class PublicKeyServer implements Runnable {
    public void run() { // runs the key server

        // Set the maximum number of queued incoming connection requests
        int queueLength = 6;

        // Declare a socket to hold incoming connections
        Socket soc;

        // Print the port on which the server is listening
        logger.info("Starting Public Keys Server on port: " + ProcessPorts.keyServerPort);

        try {

            // Create a server socket on the specified port and start listening for incoming
            // connections
            ServerSocket socket = new ServerSocket(ProcessPorts.keyServerPort, queueLength);
            while (true) {

                // Accept an incoming connection
                soc = socket.accept();

                // Create a new worker thread to handle the incoming connection
                new PublicKeyServerWorker(soc).start();
            }
        } catch (IOException e) {
            logger.error("IOException in PublicKeyServer: " + e.getMessage());
        }
    }
}

/**
 * This class represents a worker thread for PublicKeyServer class. It handles
 * the
 * incoming public key data and decodes it to store the public keys of each
 * process in
 * the blockchain network.
 */
class PublicKeyServerWorker extends Thread {
    Socket socket;

    /**
     * 
     * Constructor that accepts a socket object.
     * 
     * @param socket The socket object to use for communication.
     */
    public PublicKeyServerWorker(Socket socket) {
        this.socket = socket;
    }

    // The main logic of this thread which receives public key data and decodes it
    // to
    // store the public keys of each process in the blockchain network.
    public void run() {
        try {

            BufferedReader inputData = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // read the data and split it to get the processId and public key
            String[] data = inputData.readLine().split(" ");

            // get the processId
            int pid = Integer.parseInt(data[0]);

            // decode the public key from base64 encoding
            byte[] publicKeyInBytes = Base64.getDecoder().decode(data[1]);

            // convert the public key to a PublicKey object
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyInBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            // add public keys to the publicKeyList
            BlockChainTaskCenter.arrayOfPublicKeys[pid] = publicKey;

            // increment the public key counter
            BlockChainTaskCenter.publicKeyCounter++;

            if (BlockChainTaskCenter.publicKeyCounter == 3) {
                // set the publicKeyFlag to true if we receive public keys of all of the
                // processes
                BlockChainTaskCenter.publicKeyFlag = true;
            }

            logger.info("Public key received for processId: " + pid);

            socket.close();

        } catch (Exception e) {
            logger.error("exception in PublicKeyServerWorker: " + e.getMessage());
        }
    }
}

/**
 * A class that represents the unverified block server, which listens for
 * incoming
 * connections from processes and adds their unverified blocks to the shared
 * queue.
 */
class UnverifiedBlockServer implements Runnable {

    // The shared queue to which the unverified blocks received from processes will
    // be added.
    BlockingQueue<BlockchainRecord> queue;

    /**
     * Constructs a new UnverifiedBlockServer object with the specified blocking
     * queue.
     *
     * @param queue the blocking queue to which unverified blocks received from
     *              processes
     *              will be added.
     */
    public UnverifiedBlockServer(BlockingQueue<BlockchainRecord> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        int queueLength = 6; // The maximum number of queued incoming connections

        Socket Unv_Sock;

        logger.info("Starting Unverified Block Server on port: "
                + ProcessPorts.unverifiedBlockServerPort);
        try {

            // Creates a new server socket at the specified port and listens for incoming
            // connections
            ServerSocket Unv_BlockServer = new ServerSocket(ProcessPorts.unverifiedBlockServerPort, queueLength);
            while (true) {

                // Accepts a new incoming connection
                Unv_Sock = Unv_BlockServer.accept();

                // Starts a new UnverifiedBlockServerWorker thread to handle the incoming
                // connection
                new UnverifiedBlockServerWorker(Unv_Sock).start();
            }
        } catch (IOException e) {
            logger.error("exception in UnverifiedBlockServer:" + e.getMessage());
        }
    }
}

/**
 * The UnverifiedBlockServerWorker class is responsible for handling incoming
 * connections to the unverified block server.
 * Upon receiving a new unverified block, the worker places the block in the
 * priority blocking queue.
 */
class UnverifiedBlockServerWorker extends Thread {
    Socket socket;

    /**
     * Constructor for creating a new UnverifiedBlockServerWorker object.
     * 
     * @param socket the socket to listen for incoming connections
     */
    public UnverifiedBlockServerWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    // Handles the incoming unverified block from the socket by deserializing it and
    // placing it in the priority blocking queue.
    public void run() {
        try {
            // Create a reader to receive the input stream from the socket
            BufferedReader inputData = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputString;

            Gson gson = new Gson();
            StringBuffer buffer = new StringBuffer();

            // Read the incoming data and store it in the buff
            while ((inputString = inputData.readLine()) != null) {
                buffer.append(inputString);

            }

            // Deserialize the JSON string to a BlockchainRecord object
            BlockchainRecord blockchainRecord = gson.fromJson(buffer.toString(), BlockchainRecord.class);

            logger.info("Adding the block to the queue, blockId = " + blockchainRecord.getBlockIdentifier());

            // Place the block in the priority blocking queue
            BlockChainTaskCenter.blockRecordsQueue.put(blockchainRecord);

            // Close the socket
            socket.close();
        } catch (Exception e) {
            logger.error("exception in UnverifiedBlockServerWorker: " + e.getMessage());
        }
    }
}

/**
 * The UpdatedBlockchainServer class represents a server that listens for
 * incoming connections on a specified port
 * and creates worker threads to handle these connections.
 * It implements the Runnable interface, which allows it to be executed in a
 * separate thread.
 */
class UpdatedBlockchainServer implements Runnable {
    @Override
    public void run() {

        // Set the maximum length of the queue of incoming connections
        int queueLength = 6;
        // declaring the Socket variable
        Socket Upd_sock;

        // Print a message to indicate that the server is starting
        logger.info("Starting Updated BlockChain Server on port: " + ProcessPorts.updatedBlockServerPort);

        try {

            // Create a new server socket object and bind it to the specified port
            ServerSocket serv_sockS = new ServerSocket(ProcessPorts.updatedBlockServerPort, queueLength);
            while (true) {

                // Wait for an incoming connection
                Upd_sock = serv_sockS.accept();

                // Create a new worker thread to handle the incoming connection
                new UpdatedBlockchainWorker(Upd_sock).start();
            }
        } catch (IOException e) {
            logger.error("IOException in UpdatedBlockchainServer: " + e.getMessage());
        }
    }
}

/**
 * The UpdatedBlockchainWorker class represents a worker thread that handles
 * incoming connections on the server
 * and processes the data received.
 * It extends the Thread class, which allows it to be executed in a separate
 * thread.
 */
class UpdatedBlockchainWorker extends Thread {
    private Socket socket;

    /**
     * Constructor for the UpdatedBlockchainWorker class. It takes a Socket object
     * as an argument, which represents the connection to the client.
     * 
     * @param socket The Socket object representing the client connection.
     */
    public UpdatedBlockchainWorker(Socket socket) {
        this.socket = socket;
    }

    /**
     * The run() method is called when the worker thread is started. It reads data
     * from the client socket,
     * parses it as a JSON-formatted BlockchainRecord object,
     * and adds it to the blockchain ledger if it is not a duplicate.
     * If the processId is 0, it also writes the blockchain ledger to disk.
     */
    @Override
    public void run() {
        try {
            // read socket
            BufferedReader inputData = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Gson gson = new Gson();

            String data;
            StringBuffer buffer = new StringBuffer();

            // store data to buffer
            while ((data = inputData.readLine()) != null) {
                buffer.append(data);
            }

            // parse buffer as json data of block chain record
            BlockchainRecord record = gson.fromJson(buffer.toString(), BlockchainRecord.class);

            // add the blockchain record to the ledger if it does not exist
            if (!BlockChainTaskCenter.checkDuplicateRecords(record)) {
                logger.info(
                        "successfully verified new block, adding new block to the ledger. current count of blocks in ledger = "
                                + BlockChainTaskCenter.ledger.size());

                BlockChainTaskCenter.ledger.add(0, record);
                logger.info("successfully added blockchain record to the ledger. new count of blocks in the ledger = "
                        + BlockChainTaskCenter.ledger.size());

            }

            // If processId is 0, then write the blockchain ledger to disk
            if (BlockChainTaskCenter.processId == 0) {
                BlockChainTaskCenter.saveToDisk();
            }
            socket.close();
        } catch (IOException e) {
            logger.error("IOException in UpdatedBlockchainWorker: " + e.getMessage());
        }
    }
}

/**
 * This class implements the Runnable interface and performs the mining work for
 * a blockchain.
 * It generates random alphanumeric strings and hashes them, appending the
 * result to a
 * BlockchainRecord to solve a puzzle. The solved block is then sent to a ledger
 * for recording.
 */
class BlockchainWork implements Runnable {

    BlockingQueue<BlockchainRecord> queue;

    /**
     * Static variable that will be used to generate the random alpha numeric string
     */
    private static final String AlphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // Constructor to initialize the queue for BlockchainWork
    public BlockchainWork(PriorityBlockingQueue<BlockchainRecord> blockQueue) {
        this.queue = blockQueue;
    }

    @Override // Override the run method from the Runnable interface
    public void run() {
        try {
            // Loop indefinitely

            while (true) {

                // Take a BlockchainRecord from the BlockChainTaskCenter's blockRecordsQueue
                BlockchainRecord blockchainRecord = BlockChainTaskCenter.blockRecordsQueue.take();

                // Concatenate all of the BlockchainRecord's fields into one string
                String blockchainRecordAsString = blockchainRecord.getBlockIdentifier()
                        + blockchainRecord.getPatientFirstName() +
                        blockchainRecord.getPatientLastName() + blockchainRecord.getSsn() +
                        blockchainRecord.getDob() + blockchainRecord.getMedicalDiagnosis() +
                        blockchainRecord.getMedicalTreatment() + blockchainRecord.getPrescription() +
                        blockchainRecord.getCreationOfProcess();

                String randomString;
                String concatString;
                String hashString;

                boolean isHashVerified;
                boolean isBlockIdVerified;

                // Check if the BlockchainRecord is a duplicate, and if so, skip it
                if (BlockChainTaskCenter.checkDuplicateRecords(blockchainRecord) && blockchainRecord != null) {
                    // System.out.println("Duplicated Block Record in BlockChain");
                    continue;
                }

                // Verify the signature of the BlockIdentifier field using the public key of the
                // process that created it
                isBlockIdVerified = BlockChainTaskCenter.verifysignature(
                        blockchainRecord.getBlockIdentifier().getBytes(),
                        BlockChainTaskCenter.arrayOfPublicKeys[Integer
                                .parseInt(blockchainRecord.getCreationOfProcess())],
                        Base64.getDecoder().decode(blockchainRecord.getSignedBlockIdentifier()));

                // Print out whether or not the BlockIdentifier is verified
                String message = isBlockIdVerified ? "Block ID is Signed" : "Block ID is not Signed";
                logger.debug(message);

                // Verify the signature of the HashCreator field using the public key of the
                // process that created it
                isHashVerified = BlockChainTaskCenter.verifysignature(blockchainRecord.getHashCreator().getBytes(),
                        BlockChainTaskCenter.arrayOfPublicKeys[Integer
                                .parseInt(blockchainRecord.getCreationOfProcess())],
                        Base64.getDecoder().decode(blockchainRecord.getSignedHashCreator()));

                // Print out whether or not the HashCreator is verified
                String hashMessage = isHashVerified ? "Hash is Signed" : "Hash is not Signed";
                logger.debug(hashMessage);

                // Retrieve the BlockIdentifier of the previous block in the ledger
                String previousBlkID = BlockChainTaskCenter.ledger.get(0).getBlockIdentifier();

                // A value between 0 and 65535, to verify if the puzzle is solved
                int workNumber;
                String updatedBlock = blockchainRecordAsString;

                // Append the WinningHash of the previous block to the end of the
                // BlockchainRecord string
                updatedBlock = updatedBlock + BlockChainTaskCenter.ledger.get(0).getWinningHash();

                // If the BlockchainRecord is not a duplicate, attempt to solve the puzzle
                if (!BlockChainTaskCenter.checkDuplicateRecords(blockchainRecord)) {
                    try {
                        // Attempt to solve the puzzle 20 times
                        for (int i = 1; i < 20; i++) {

                            // Generate a random alphanumeric string of length 8
                            // randomString = generateRandomAlphaNumericString(8);
                            randomString = newRandomAlphaNumeric(8);

                            // Concatenate the updated block data and the random string
                            concatString = updatedBlock + randomString;

                            // Generate SHA-256 hash value of the concatenated string
                            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
                            byte[] bytesHash = msgDigest.digest(concatString.getBytes(StandardCharsets.UTF_8));
                            hashString = byteArrayToString(bytesHash);
                            logger.debug("Hash value of final BlockData: " + hashString);

                            // Get the first 16 bits of the hash in hexadecimal and decimal form
                            workNumber = Integer.parseInt(hashString.substring(0, 4), 16);
                            logger.debug("Initial 16 bits in Hex and Decimal: " + hashString.substring(0, 4)
                                    + " and " + workNumber);

                            // If the work number is not less than 20,000, rework
                            if (!(workNumber < 20000)) {
                                logger.warn(workNumber + " is not less than 20,000. Solving puzzle again");
                            }

                            // If the work number is less than 20,000, solve the puzzle
                            if (workNumber < 20000) {

                                // Check if the previous block's identifier matches the current
                                // blockchain's first block's identifier
                                if (!previousBlkID
                                        .equals(BlockChainTaskCenter.ledger.get(0).getBlockIdentifier())) {
                                    logger.debug("Reading BlockData from the Work Loop");
                                    BlockChainTaskCenter.sendBlockToLedger(blockchainRecord, "reVerifyBlock");
                                }

                                else { // Set the winning hash and random seed values

                                    blockchainRecord.setWinningHash(hashString);
                                    blockchainRecord.setRandomSeed(randomString);
                                    logger.info(workNumber + " is less than 20,000. Solved!");
                                    logger.info("Winning Random Seed String: " + randomString);

                                    // Set the past hash value to the winning hash value of the previous block
                                    blockchainRecord.setPastHash(BlockChainTaskCenter.ledger.get(0).getWinningHash());

                                    // read previous block
                                    int blockNumber = Integer
                                            .parseInt(BlockChainTaskCenter.ledger.get(0).getBlockNumber());

                                    // increment the block number
                                    blockNumber = blockNumber + 1;

                                    // set new block number
                                    blockchainRecord.setBlockNumber(String.valueOf(blockNumber));

                                    // Set the process ID of the current node that verified this block
                                    blockchainRecord.setPidVerification(String.valueOf(BlockChainTaskCenter.processId));

                                    // Generate and set the digital signature of the winning hash value
                                    String signHashVerifier;
                                    byte[] digitalSign = BlockChainTaskCenter.signData(hashString.getBytes(),
                                            BlockChainTaskCenter.keyPair.getPrivate());
                                    signHashVerifier = Base64.getEncoder().encodeToString(digitalSign);

                                    blockchainRecord.setWinningHashSigned(signHashVerifier);

                                    // Add the verified block to the blockchain ledger
                                    BlockChainTaskCenter.ledger.add(0, blockchainRecord);
                                    logger.info("Block is added to blockchain ledger. " + "Total Verified Blocks= "
                                            + BlockChainTaskCenter.ledger.size());

                                    // Update the blockchain ledger
                                    BlockChainTaskCenter.sendBlockToLedger(blockchainRecord, "bcLedgerUpdate");
                                    continue;
                                }
                                break;
                            }
                            // If the record is verified drop the attempt tp verify block
                            if (BlockChainTaskCenter.checkDuplicateRecords(blockchainRecord)) {

                                break;
                            }

                            try {

                                Thread.sleep(1000);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    } catch (Exception excpt) {

                        excpt.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("exception in BlockchainWork: " + e.getMessage());
        }
    }

    /**
     * Generate random alpha-numeric string of a specified length using Java's
     * SecureRandom
     * 
     * @param length The desired length of the string.
     * @return A randomly generated alpha-numeric string.
     */
    public static String newRandomAlphaNumeric(int length) {
        final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHA_NUMERIC.length());
            char randomChar = ALPHA_NUMERIC.charAt(randomIndex);
            buffer.append(randomChar);
        }
        return buffer.toString();
    }

    /**
     * Converts a byte array to a hexadecimal string.
     * 
     * @param bytes The byte array to be converted.
     * @return A string representation of the byte array in hexadecimal form.
     */
    public static String byteArrayToString(byte[] bytes) {

        // Create a StringBuilder to build the hexadecimal string.
        StringBuilder buffer = new StringBuilder(bytes.length * 2);

        // Loop through each byte in the byte array
        for (byte byteA2S : bytes) {

            // Convert byte to a hexadecimal string and append it to the buffer
            buffer.append(String.format("%02X", byteA2S));
        }

        // Return the buffer as a string
        return buffer.toString();
    }
}

class logger {
    private static final String INFO = "INFO";
    private static final String DEBUG = "DEBUG";
    private static final String WARN = "WARN";
    private static final String ERROR = "ERROR";
    private static final String SEPARATOR = " :: ";

    static void info(String message) {
        java.util.Date date = new java.util.Date();
        System.out.println(date + SEPARATOR + INFO + SEPARATOR + message);
    }

    static void debug(String message) {
        java.util.Date date = new java.util.Date();
        System.out.println(date + SEPARATOR + DEBUG + SEPARATOR + message);
    }

    static void warn(String message) {
        java.util.Date date = new java.util.Date();
        System.out.println(date + SEPARATOR + WARN + SEPARATOR + message);
    }

    static void error(String message) {
        java.util.Date date = new java.util.Date();
        System.out.println(date + SEPARATOR + ERROR + SEPARATOR + message);
    }
}
