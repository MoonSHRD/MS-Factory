pragma solidity ^0.5.11;

//import './zeppeline/token/ERC20/ERC20Mintable.sol';

// TODO: replace contracts to interfaces cause of gas limit
// FIXME: this contract required for 7m gas limit -- which is more than standart 6m in ethereum networks. We should consider that when deployment

import './Ticket721.sol';
import './TicketSale721.sol';
import './TicketSalePluggable.sol';

contract TicketFactory721 {

// constant
address ticket_template;

// event
event SaleCreated(address indexed organizer, uint price, uint256 indexed event_id, string indexed event_JID);
event SaleCreatedHuman(address organizer, uint price, uint256 event_id, string event_JID);
event PluggedSale(address indexed organizer, address indexed orginal_sale, uint256 indexed event_id);
event PluggedSaleHuman(address organizer, address original_sale, uint256 event_id);

// mapping from JID to event_id
mapping (string => uint256) events_jids;


constructor() public {
    ticket_template = createTicket721();
}


function createTicket721() internal returns (address ticket_address) {
   address factory_address = address(this);
   ticket_address = address(new Ticket721(factory_address));
   return ticket_address;
}


function createTicketSale721(address payable organizer, uint price, Ticket721 token) internal returns(address payable ticket_sale) {
    // calculate price
    uint256 cena = calculateRate(price);

    ticket_sale = address(new TicketSale721(cena, organizer, token));
    return ticket_sale;
}

function createTicketSale(address payable organizer, uint price, string memory event_JID) public returns (address payable ticket_sale_adr, uint256 event_id) {

    address ticket_adr = ticket_template;
    Ticket721 ticket = Ticket721(ticket_adr);
    ticket_sale_adr = createTicketSale721(organizer, price, ticket);
    TicketSale721 ticket_sale = TicketSale721(ticket_sale_adr);

    event_id = ticket_sale.event_id();
    events_jids[event_JID] = event_id;
    emit SaleCreated(organizer, price, event_id, event_JID);
    emit SaleCreatedHuman(organizer,price,event_id, event_JID);
    return(ticket_sale_adr, event_id);


}

function PlugInTicketSale(address payable origin_sale, uint price) public returns(address payable plugin_sale) {
    uint cena = calculateRate(price);
    plugin_sale = address(new TicketSalePluggable(cena,origin_sale));
    TicketSale721 ticket_sale = TicketSale721(origin_sale);
    uint256 event_id = ticket_sale.event_id();
    emit PluggedSale(msg.sender,origin_sale,event_id);
    emit PluggedSaleHuman(msg.sender, origin_sale, event_id);
    return plugin_sale;
}

function calculateRate (uint256 price) internal pure returns (uint256 rate_p) {
    // rate = price * 1 eth
    rate_p = price * (1 ether); // override for price determinition
    return rate_p;
}


function getEventIdByJid(string memory JID) public view returns(uint256) {
    return events_jids[JID];
}

function getTicketTemplateAddress() public view returns(address) {
    return ticket_template;
}

}