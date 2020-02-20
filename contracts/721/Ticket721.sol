pragma solidity ^0.5.11;

import '../zeppeline/token/ERC20/ERC20Mintable.sol';
import '../zeppeline/token/ERC721/ERC721Enumerable.sol';


/** 

    We will use this template if we consider, that EACH ticket(token) is unique


    Probably we should consider, that each ticketsale has a specific contract of ticket?

    Or should we considered, that every tickets are available throught this single contract?

**/




// Ticket is ERC721 (NFT) token with  availability to reedem tickets

/**

    ERC-721 is non-fungible token.  So, each tokenID is a unique ticket

    Usefull tips:
    // Mapping from owner to list of owned token IDs
    mapping(address => uint256[]) private _ownedTokens;

    // Mapping from token ID to index of the owner tokens list
    mapping(uint256 => uint256) private _ownedTokensIndex;

    // Array with all token ids, used for enumeration
    uint256[] private _allTokens;

    // Mapping from token id to position in the allTokens array
    mapping(uint256 => uint256) private _allTokensIndex;

    Gets the list of token IDs of the requested owner.
     function _tokensOfOwner(address owner) internal view returns (uint256[] storage) {
        return _ownedTokens[owner];
    }



 **/

contract Ticket721 is ERC721Enumerable {
 
   using SafeMath for uint256;
  // using Counters for Counters.Counter;

  // event (groupID) assosiated with this ticket
   string _event_id;


    address _factory_address;


   /*
    struct Ticket {

    

    }
    */


    // If we are assuming, that tokenID is a eventID and ticket is a counter
    // Mapping from owner to tokenID to number of ticket
    //mapping (address => mapping(uint256 => Counters.Counter)) tickets;


    // Otherwise we should consider that ticket is unique

    constructor(string memory event_id,address factory_address) public {
        _event_id = event_id;
        _factory_address = factory_address;
    }
}