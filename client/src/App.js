import React, { Component } from "react";
import SimpleStorageContract from "./contracts/SimpleStorage.json";
import KNS from "./contracts/KNS.json";
import SuperFactory from "./contracts/SuperFactory.json";
import MultiSigWalletWithDailyLimit from "./contracts/MultiSigWalletWithDailyLimit.json";


import getWeb3 from "./getWeb3";

import "./App.css";
import Web3 from "web3";

class App extends Component {
  state = { storageValue: 0, web3: null, accounts: null, contract: null };

  componentDidMount = async () => {
    try {
      // Get network provider and web3 instance.
      const web3 = await getWeb3();

      // Use web3 to get the user's accounts.
      const accounts = await web3.eth.getAccounts();

      // Get the contract instance.
      const networkId = await web3.eth.net.getId();
      const deployedNetwork_KNS = KNS.networks[networkId];
      const deployedNetwork_WF = SuperFactory.networks[networkId];
      const instance_KNS = new web3.eth.Contract(
        KNS.abi,
        deployedNetwork_KNS && deployedNetwork_KNS.address,
      );

      const WF = new web3.eth.Contract(
        SuperFactory.abi,
        deployedNetwork_WF && deployedNetwork_WF.address,
      );



      // Set web3, accounts, and contract to the state, and then proceed with an
      // example of interacting with the contract's methods.
      this.setState({ web3, accounts, contract1: instance_KNS, contract2: WF }, this.runExample);
    } catch (error) {
      // Catch any errors for any of the above operations.
      alert(
        `Failed to load web3, accounts, or contract. Check console for details.`,
      );
      console.error(error);
    }
  };

  runExample = async () => {
    const { accounts, contract1, contract2 } = this.state;

    // Stores a given value, 5 by default.
   // await contract.methods.set(5).send({ from: accounts[0] });

    // Get the value from the contract to prove it worked.
    //const response = await contract.methods.get().call();

    //define vars
    var req = 1;
    req = parseInt(req);
    var daily_limit = 100000000;
    daily_limit = parseInt(daily_limit);
    var jid = 'test@test';
    var tel = '12345';

    let ownrs = new Array();
    ownrs.push(accounts[0]);

    //Creating a wallet with one owner
    await contract2.methods.createWallet(ownrs,req,daily_limit,jid,tel).send({ from: accounts[0] });


    //Get the registered wallet addr by registry get
    const response = await contract1.methods.GetWalletByJid(jid).call();

    // Update state with the result.
    this.setState({ storageValue: response });
  };


  // Appendix for create Wallet
  // TODO: refactor this
  createWallet = async () => {
    const { accounts, registry, w_factory, owner, req, dl, jid, tel} = this.state;

    let ownrs = new Array();
    ownrs.push(owner);

    //Creating a wallet with one owner
    await contract2.methods.createWallet(ownrs,req,daily_limit,jid,tel).send({ from: accounts[0] });


    //Get the registered wallet addr by registry get
    const response = await contract1.methods.GetWalletByJid(jid).call();

    //TODO: update state with result

  };

  // TODO: add exeptional 
  // deposit funds to wallet
  depositToWallet = async () => {

    // param -- address of wallet, amount
    const { accounts, wallet_address, val} = this.state;

    // send funds
    const response = await Web3.eth.send({ from: accounts[0], to: wallet_address, value:Web3.toWei(val,"ether")});

    //TODO: update state with result

  };


  // submit new transaction to a multisig wallet
  // general function
  submitTransaction = async () => {

    // param -- multisig wallet, deployed address, destination address, uint value, bytes payload
    const { accounts, dep_address, destination, val, payload}

    //defining instance of wallet
    const wallet = new web3.eth.Contract(
      MultiSigWalletWithDailyLimit.abi,
      deployedNetwork_WF && dep_address,
    );

    // calling wallet method
    // destination, value, data
    const response = await wallet.methods.submitTransaction(destination,Web3.toWei(val,"ether"),payload).send({ from: accounts[0] });

    // TODO: Upd state with result

  };

  

  render() {
    if (!this.state.web3) {
      return <div>Loading Web3, accounts, and contract...</div>;
    }
    return (
      <div className="App">
        <h1>Good to Go!</h1>
        <p>Your Truffle Box is installed and ready.</p>
        <h2>Smart Contract Example</h2>
        <p>
          If your contracts compiled and migrated successfully, below will show
          an address value of new created wallet (by default).
        </p>
        <p>
          Try changing the value stored on <strong>line 40</strong> of App.js.
        </p>
        <div>The stored value is: {this.state.storageValue}</div>
      </div>
    );
  }
}

export default App;
