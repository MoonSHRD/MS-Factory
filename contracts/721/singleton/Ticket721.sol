pragma solidity ^0.5.11;

import '../../zeppeline/token/ERC721/ERC721Enumerable.sol';
import '../../zeppeline/token/ERC721/ERC721Mintable.sol';
import "../../zeppeline/drafts/Counters.sol";







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

contract Ticket721 is ERC721Enumerable, ERC721Mintable {
   using SafeMath for uint256;
   using Counters for Counters.Counter;

    address _factory_address;


    // Global counters for ticket_id and event_id
    Counters.Counter _ticket_id_count;
    Counters.Counter _event_id_count;

    // map from ticketsales to event_id
    mapping(uint256 => address) eventsales;
    // map from event id to ticket ids
    mapping (uint256 => uint256[]) ticketIds;
    // map fron token ID to its index in ticketIds
    mapping (uint256 => uint256) ticketIndex;


    //FIXME: invoke constructor from 721(?)
    constructor(address factory_address) public {
        _factory_address = factory_address;
        addMinter(msg.sender);
    }

    // FIXME: approve for ticketsale, not factory
    function setApprovalForAllFactory(address _owner) public{
        bool approved;
        _operatorApprovals[_owner][_factory_address] = approved;
        emit ApprovalForAll(_owner, _factory_address, approved);
    }

    function _transferFromTicket(address from, address to, uint256 tokenId) public {
        super._transferFrom(from, to, tokenId);
    }


    function reserveEventId() public returns(uint256 event_id){
        _event_id_count.increment();
        event_id = _ticket_id_count.current();
        eventsales[event_id] = msg.sender;
        return event_id;
    }

    function buyTicket(address buyer, uint256 ticketAmount, uint256 event_id) public{
        require(eventsales[event_id] == msg.sender, "caller doesn't match with event_id");
        for (uint256 i = 0; i < ticketAmount; i++ ){
            _ticket_id_count.increment();
            uint256 ticket_id = _ticket_id_count.current();
            _mint(buyer,ticket_id);
            ticketIndex[ticket_id] = ticketIds[event_id].length;
            ticketIds[event_id].push(ticket_id);
        }
    }

    function redeemTicket(address owner,uint256 tokenId, uint256 event_id) public{
        require(eventsales[event_id] == msg.sender, "caller doesn't match with event_id");
        super._burn(owner,tokenId);

       // To prevent a gap in the tokens array, we store the last token in the index of the token to delete, and
       // then delete the last slot (swap and pop).


        uint256 ticket_index = ticketIndex[tokenId];
        uint256 lastTicketIndex = ticketIds[event_id].length.sub(1);

      //  uint256[] storage ticketIdArray = ticketIds[event_id];
      //  uint256 lastTicketId = ticketIdArray[lastTicketIndex];

        uint256 lastTicketId = ticketIds[event_id][lastTicketIndex];


        ticketIds[event_id][ticket_index] = lastTicketId; // Move the last token to the slot of the to-delete token
        ticketIndex[lastTicketId] = ticket_index;         // Update the moved token's index

        ticketIds[event_id].length--;  // remove last element in array
        ticketIndex[tokenId] = 0;


    }

}