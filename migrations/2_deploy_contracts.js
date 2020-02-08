//var SimpleStorage = artifacts.require("./SimpleStorage.sol");
var SuperFactory = artifacts.require("./SuperFactory.sol");
var KNS = artifacts.require("./KNS.sol");

module.exports = function(deployer) {
 // deployer.deploy(SimpleStorage);
 deployer.deploy(KNS).then(function() {
  return deployer.deploy(SuperFactory, A.address);
});
};
