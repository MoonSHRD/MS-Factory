pragma solidity ^0.5.11;

//import '../zeppeline/token/ERC20/ERC20Mintable.sol';
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

    // If we are assuming, that tokenID is a eventID and ticket is a counter
    // Mapping from owner to tokenID to number of ticket
    //mapping (address => mapping(uint256 => Counters.Counter)) tickets;


    // Otherwise we should consider that ticket is unique

    constructor(address factory_address) public {
       // _event_id = event_id;
        _factory_address = factory_address;
        addMinter(msg.sender);
    }


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
        for (uint256 i = 0; i < ticketAmount; i++ ){
            _ticket_id_count.increment();
            uint256 ticket_id = _ticket_id_count.current();
            _mint(buyer,ticket_id);
            ticketIds[event_id].push(ticket_id);
        }
    }

    

}