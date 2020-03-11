pragma solidity ^0.5.11;

//import './zeppeline/token/ERC20/ERC20Mintable.sol';

import './Ticket721.sol';
import './TicketSale721.sol';
import './TicketSalePluggable.sol';

contract TicketFactory721 {

// constant
address ticket_template;


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

function createTicketSale(address payable organizer, uint price) public returns (address payable ticket_sale_adr, uint256 event_id) {

    address ticket_adr = ticket_template;
    Ticket721 ticket = Ticket721(ticket_adr);
    ticket_sale_adr = createTicketSale721(organizer, price, ticket);
    TicketSale721 ticket_sale = TicketSale721(ticket_sale_adr);

    event_id = ticket_sale.event_id();
    return(ticket_sale_adr, event_id);


}

function calculateRate (uint256 price) internal pure returns (uint256 rate_p) {
    // rate = price * 1 eth
    rate_p = price * (1 ether);
    return rate_p;
}

}