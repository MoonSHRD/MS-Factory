# MS-Factory
**DEPRECATED AS OBSOLETE** , see new version here -- https://github.com/MoonSHRD/UniversalNFTMarketplace 

Portable payment system core, based on ethereum

## Main features
TBA

## Description
This repository is truffle project, focused on ability to build any kind of payment system for any service.
It contains modified smart-contracts libraries, such as open-zeppeline and gnosis multisig contracts. 

Openzeppeline has been upgraded to solidity version 0.5.x and modified to been able to **create and manage crowdsale of ERC-721 tokens**, which allow us to make marketplaces with non-fungible items

Gnosis has been modified to allow multisig contracts creating contracts, added possability to make invoice to multisig from 3-rd party user. 

More info about contracts and it's usage you can find in below section of this readme



## Contracts

**KNS** -- simple **N**ame **S**ervice for storing contract actual addresses, interfaces, mapping user wallets to their profile at jabber / email / telephone.

**SuperFactory** - contract for creation of gnosis multisigs, including 2FA multisigs, and attachment multisigs to Social Media accounts

**Deposit** - **gateway contract** for exchanging fiat money (USD,EUR,RUB, etc) to moonshard stable-coin. More info about custom gateways TBA.

./contracts/721/singleton -- libs for creation different non-fungible marketplaces, based on erc-721(non-fungible token)

**Ticket721** -- erc721 **mintable**, **singleton** contract. This contract serve as singleton central point of all sold tokens. Any information about tickets status, ownership, e.t.c. can be obtained throught this single contract. It also have special function to reedeem tickets (fork and override this function if you presume, that sold item cannot be refunded/returned/scanned)
**Note** -- all tokens from this contract can be bought only through TicketSale contract, direct buying is forbidden

**TicketSale721** - interface to ticket sale, allowing to buy and reedeem tokens with custom price and other values

**TokenSale721** - basic contract for crowdsale of ERC-721 tokens, without tethering functional for tickets. 

**TicketFactory721** - **main contract for creating ticketsale. Every created tokensale should be created throught this contract.**

**PluggableSale** and **TicketSalePluggable** - if you want to sell a few different types of items at one tokensale - you should use this contracts for attaching new items to main sale instance

/contracts/gnosis/ -- modified gnosis multisig libs
/contracts/openzeppeline/ -- modified openzeppeline libs


## Testing enviroment, truffle and ganache, deployment process
Firts of all you need to get [truffle](https://www.trufflesuite.com/)

For testing purposes is higly reccommended to use [Ganache](https://www.trufflesuite.com/ganache) as testing enviroment
Once ganache is running - you can deploy project by ```truffle migrate --reset```. This command will compile contracts, deploy them to a running node(ganache is for test) , create JSON artifacts of deployed contracts. Those artifacts can be converted into different languages later.

**Note** - you can also can attach truffle.js into ganache workspace to get full decoded info about transactions, contracts, events and all other data.

**Deployment scripts** placed at ``` ./migration/2_deploy_contracts.js ``` -- modify this file for deployment in your own private network if neccessary

Deployment and truffle configuration placed at ```./truffle.js```

### Local testing step-by-step manual
1. install truffle as global npm package from https://www.trufflesuite.com/
2. install ganache 'one-click-blockchain' enviroment
3. clone this repo
4. launch ganache, set gaslimit to 7.3m or higher, set gasprice = 1, link ganache to this project (by adding truffle.js config -- follow GUI instructions)
5. go to this repo, create separate branch, execute ``` truffle migrate --reset``` in terminal from root folder of repo.
after you do that -- you get all contracts deployed and contracts artifacts file generated. Now you all set to interact with blockchain
6. (optional) generate client-side artifacts, that required by your client application. Generating artifacts for java/android can be build as https://github.com/MoonSHRD/MS-Factory#build-java-artifacts-to-android

**Debugging** of smart-contracts is available from ganache GUI and from internal tool **truffle debug**

## Front-end and administration
React front-end can be build by ``` cd client && npn run build ``` or ``` npm run start```, see commands section.

Sources for front-end can be found at ```client``` (for now there are no works there)


### Commands:

  Compile:              truffle compile
  
  Migrate:              truffle migrate
  
  Test contracts:       truffle test
  
  Test dapp:            cd client && npm test
  
  Run dev server:       cd client && npm run start
  
  Build for production: cd client && npm run build

## Generating controllers to 'client'-side projects

**Web3** allow us to generate controllers for client side projects just from truffle json artifacts. Those controllers can be generated for golang, c++, python, js, java natively

Those controllers can be used as universal end-points for interaction with smart-contracts. As example you can browse [web3wallet](https://github.com/MoonSHRD/Web3wallet)

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

  ## Generating ABI

```
  solc --abi --bin ./contracts/KNS.sol -o build
  solc --abi --bin ./contracts/Deposit.sol -o build
  solc --abi --bin ./contracts/721/singleton/Ticket721.sol -o build --allow-paths *,
  solc --abi --bin ./contracts/721/singleton/TicketSale721.sol -o build --allow-paths *,
  solc --abi --bin ./contracts/721/singleton/TicketFactory721.sol -o build --allow-paths *,
  solc --abi --bin ./contracts/721/singleton/TicketSalePluggable.sol -o build --allow-paths *,
  solc --abi --bin ./contracts/721/singleton/PluggableSale.sol -o build --allow-paths *,
  ```

  ## Generating go files from ABI (for backend instances)

  ```
  abigen --abi="build/KNS.abi" --pkg=KNS --out="./go/KNS.go"
  abigen --abi="build/Deposit.abi" --pkg=Deposit --out="./go/Deposit.go"
  abigen --abi="build/Ticket721.abi" --pkg=Ticket721 --out="./go/Ticket721.go"
  ```

