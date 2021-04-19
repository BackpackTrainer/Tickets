package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import events.Event;
import events.IVenue;


public class PricingTests {

	int ticketsOnSaleDays;
	LocalDate ticketSalesBeginDate;
	int ticketsSold;
	IVenue venue;
	Event uut;
	
	LocalDate today = LocalDate.now();
	double ticketPrice = 100.0;
	int venueCapacity = 1000;
	
	@Before
	public void setUp()  {
		venue = new VenueTestStub();	
		uut = new Event(venue);
	}
	
	@After
	public void tearDown()  {
		venue = null;
		uut = null;
	}


	@Test
	public void testConstruction()  {

		
		assertNotNull(uut);
	}
	
	@Test
	public void testTicketsOnSaleLessThan30Days()  {
		ticketsOnSaleDays = 29;
		ticketSalesBeginDate = today.minusDays(ticketsOnSaleDays);
			
		uut.setTicketPrice(ticketPrice);
		uut.setTicketSalesBeginDate(ticketSalesBeginDate);
		double actualTicketPrice = uut.getTicketPrice(today);
		
		assertEquals(ticketPrice, actualTicketPrice, .01);
		
	}
	
	@Test
	public void testTicketsOnSaleFor30DaysVenueAt20PercentCapacity()  {
		int ticketsOnSaleDays = 30;
		LocalDate ticketSalesBeginDate = today.minusDays(ticketsOnSaleDays);
		int venueCapacity = 1000;
		int ticketsSold = 200;
		
		venue.setCapacity(venueCapacity);
		uut.setTicketPrice(ticketPrice);
		uut.setTicketSalesBeginDate(ticketSalesBeginDate);
		generateTicketSales(ticketsSold);
		double actualTicketPrice = uut.getTicketPrice(today);
		
		assertEquals(ticketPrice, actualTicketPrice, .01);
	}
	
	@Test
	public void testTicketsOnSaleFor30DaysVenueAt19PercentCapacity()  {
		int ticketsOnSaleDays = 30;
		LocalDate ticketSalesBeginDate = today.minusDays(ticketsOnSaleDays);
		int venueCapacity = 1000;
		int ticketsSold = 190;
		double expectedDiscountRate = .75;
		double expectedTicketPrice = ticketPrice*expectedDiscountRate;
		
		venue.setCapacity(venueCapacity);
		uut.setTicketPrice(ticketPrice);
		uut.setTicketSalesBeginDate(ticketSalesBeginDate);
		generateTicketSales(ticketsSold);
		double actualTicketPrice = uut.getTicketPrice(today);
		
		assertEquals(expectedTicketPrice, actualTicketPrice, .01);
	}
	
	@Test
	public void testTicketsOnSaleFor30DaysVenueAt31PercentCapacity()  {
		int ticketsOnSaleDays = 30;
		LocalDate ticketSalesBeginDate = today.minusDays(ticketsOnSaleDays);
		int venueCapacity = 1000;
		int ticketsSold = 310;
		double expectedPremiumRate = 1.50;
		double expectedTicketPrice = ticketPrice*expectedPremiumRate;
		
		venue.setCapacity(venueCapacity);
		uut.setTicketPrice(ticketPrice);
		uut.setTicketSalesBeginDate(ticketSalesBeginDate);
		generateTicketSales(ticketsSold);
		double actualTicketPrice = uut.getTicketPrice(today);
		
		assertEquals(expectedTicketPrice, actualTicketPrice, .01);
	}
	
	private void generateTicketSales(int ticketsSold)  {
		for (int i = 0; i<ticketsSold; i++)  {
		uut.sellTicket();
		}
	}
		
	@Test
	public void test() {
//		This test isn't really testing anything.  It is here to show 
//		the syntax of creating dates, adding days to dates, and calculating
//		the difference (in days) between 2 dates
			
		LocalDate startDate = LocalDate.of(2021,  04,  16);
		LocalDate endDate = startDate.plusDays(100);
		long expectedResult = 100;
		
		long numberOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		
		assertTrue(expectedResult == numberOfDaysBetween);
	}	
}
