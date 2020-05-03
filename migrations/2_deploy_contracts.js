var SuperFactory = artifacts.require("./SuperFactory.sol");
var KNS = artifacts.require("./KNS.sol");
var TicketFactory = artifacts.require("./721/singleton/TicketFactory721.sol")
var Deposit = artifacts.require("./Deposit.sol")
var Ticket = artifacts.require("./721/singleton/Ticket721.sol")
var accounts = web3.eth.getAccounts();
var limitGas = web3.eth.getBlock("latest").gasLimit;


module.exports = function(deployer, network, accounts) {
  console.log(accounts);
 deployer.deploy(KNS,{gasPrice:'1'}).then(function() {
  console.log(network);
  console.log(network.port);
  console.log(network.gasPrice);
  console.log(accounts);
  console.log(accounts[0]);
  return
 // return deployer.deploy(SuperFactory, KNS.address,accounts[1],accounts[2],{gasPrice:'1'});
});
console.log("gas limit");
console.log(limitGas);
deployer.deploy(Ticket, {gasPrice:'1'}).then(function() {
  return deployer.deploy(TicketFactory,Ticket.address,{gasPrice:'1'});
})
deployer.deploy(Deposit,{gasPrice:'1', value:'1'}); //FIXME: add value sent to deposit contract
};
