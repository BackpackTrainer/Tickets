package events;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import events.IVenue;

public class Event {
	LocalDate ticketSaleDate;
	double ticketPrice;
	IVenue venue;
	int ticketsSold = 0;
	double discountRate = .75;
	double premiumRate = 1.50;
	
	public Event(IVenue venue, LocalDate ticketSaleDate) {
		this.venue = venue;
		this.ticketSaleDate = ticketSaleDate;
	}

	public void setDateTicketsWentOnSale(LocalDate dateTicketsWentOnSale) {
		ticketSaleDate = dateTicketsWentOnSale;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;		
	}

	public double getTicketPrice(LocalDate today) {
		double actualTicketPrice = ticketPrice;
		
		long numberOfDaysTicketsOnSale = ChronoUnit.DAYS.between(ticketSaleDate, today);
		
		if(numberOfDaysTicketsOnSale >= 30) {
			
			double percentTicketsSold = (double)ticketsSold/(venue.getCapacity());		
			
			if(percentTicketsSold < .20) {
			actualTicketPrice = ticketPrice*discountRate;
			}
			
			else if(percentTicketsSold > .80) {
			actualTicketPrice = ticketPrice*premiumRate;
			}
		}	
		return actualTicketPrice;
	}

	public void sellTicket() {
		ticketsSold++;
		
	}
}
