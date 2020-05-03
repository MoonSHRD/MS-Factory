var Migrations = artifacts.require("./Migrations.sol");

module.exports = function(deployer) {
  var accounts = web3.eth.getAccounts();
  console.log(accounts);
  deployer.deploy(Migrations);
};
