const path = require("path");

module.exports = {
  // See <http://truffleframework.com/docs/advanced/configuration>
  // to customize your Truffle configuration!
  contracts_build_directory: path.join(__dirname, "client/src/contracts"),
  networks: {
    develop: {
      host: "192.168.1.39",
      port: 7545,
      gasPrice: '1',
      network_id: "*"
    },
    ganache: {            // truffle migrate --reset --network ganache
      host: "127.0.0.1",
      port: 7545,
      gasLimit: '7000000',
      gasPrice: '1',
      network_id: '5777'
    },
    moonshard: {
      host: "51.15.244.238",
      port: "8501",
      gasLimit: '9000000',
     // gas:'8000000',
      gasPrice: '1',
      network_id: '8995',
      from: "0x5fCBc110868ce8Ec2cc840D5dC1f5C234783cdaE"
    }
  },
  compilers: {
    solc: {
      version: "^0.5.11", // A version or constraint - Ex. "^0.5.0"
                         // Can also be set to "native" to use a native solc
      parser: "solcjs",
      settings: {
      optimizer: {
        enabled: true,
        runs: 200
      },
     // evmVersion: 'petersburg'
      //  evmVersion: 'constantinople'
      }
    }
  }
};
