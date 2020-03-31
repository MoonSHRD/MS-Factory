var SuperFactory = artifacts.require("./SuperFactory.sol");
var KNS = artifacts.require("./KNS.sol");
var TicketFactory = artifacts.require("./721/singleton/TicketFactory721.sol")
var Deposit = artifacts.require("./Deposit.sol")
//var accounts = web3.eth.getAccounts();

module.exports = function(deployer, network, accounts) {
 // deployer.deploy(SimpleStorage);
 deployer.deploy(KNS,{gasPrice:'1'}).then(function() {
  console.log(network);
  console.log(network.port);
  console.log(network.gasPrice);
  console.log(accounts);
  console.log(accounts[0]);
  return deployer.deploy(SuperFactory, KNS.address,accounts[1],accounts[2],{gasPrice:'1'});
});
deployer.deploy(TicketFactory, {gas:'7500000',gasPrice:'1'});


deployer.deploy(Deposit,{gasPrice:'1', value:'1'}); //FIXME: add value sent to deposit contract
// test for estimating gas usage

};
