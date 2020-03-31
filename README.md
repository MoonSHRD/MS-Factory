# MS-Factory


## Commands:

  Compile:              truffle compile
  
  Migrate:              truffle migrate
  
  Test contracts:       truffle test
  
  Test dapp:            cd client && npm test
  
  Run dev server:       cd client && npm run start
  
  Build for production: cd client && npm run build

  ## Build JAVA artifacts to android

  ```

$ cd /path/to/your/web3j/java/project
$ web3j truffle generate /path/to/<truffle-smart-contract-output>.json -o /path/to/src/main/java -p com.your.organisation.name
```

  ### Generate our main contracts

  ```
  web3j truffle generate ./client/src/contracts/KNS.json -o ./java/ -p com.example.web3wallet
  web3j truffle generate ./client/src/contracts/SuperFactory.json -o ./java/ -p com.example.web3wallet
  web3j truffle generate ./client/src/contracts/TicketFactory721.json -o ./java/ -p com.example.web3wallet
  web3j truffle generate ./client/src/contracts/TicketSale721.json -o ./java/ -p com.example.web3wallet
  web3j truffle generate ./client/src/contracts/Ticket721.json -o ./java/ -p com.example.web3wallet
  web3j truffle generate ./client/src/contracts/Deposit.json -o ./java/ -p com.example.web3wallet
  web3j truffle generate ./client/src/contracts/PluggableSale.json -o ./java/ -p com.example.web3wallet
  web3j truffle generate ./client/src/contracts/TicketSalePluggable.json -o ./java/ -p com.example.web3wallet
  ```
