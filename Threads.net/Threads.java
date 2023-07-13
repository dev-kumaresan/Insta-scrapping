package selenium.test.threadsnet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Threads implements ThreadsModules {
	WebDriver driver = new ChromeDriver();
	Dimension d = new Dimension(736, 927);
	@Override
	public void visitPage() throws InterruptedException {
		driver.manage().window().setSize(d);
		driver.get("https://www.threads.net/");
		WebElement button1 = driver.findElement(By.xpath("//*[text()='Get the app']"));
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		button1.click();
		this.nextTab(1);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		WebElement link1 = driver.findElement(By.xpath("//*[text()='iOS']"));
		link1.click();
		Thread.sleep(3000);
		this.nextTab(2);
		Thread.sleep(3000);
		this.nextTab(1);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		WebElement link2 = driver.findElement(By.xpath("//*[text()='Android']"));
		link2.click();
		Thread.sleep(3000);
		this.nextTab(3);
		Thread.sleep(3000);
		this.nextTab(0);
	}
	private void nextTab(int i) {
		Set<String> next = driver.getWindowHandles();
		List<String> next_tab = new ArrayList<>(next);
//		System.out.println(next_tab.size());
		if (i == 0 || i == 1 || i == 2 || i == 3) {
			driver.switchTo().window(next_tab.get(i));
		}
	}
	private void validUsername(String username) // Test Case - 1
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		WebElement Followers_count = driver.findElement(By.xpath("//span[@title]"));
		String Followers = Followers_count.getText();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		WebElement profile_pic = driver.findElement(By.xpath("(//img[@src])[3]"));
		String profilepic_link = profile_pic.getAttribute("src");
		System.out.println("Test Case Passed...");
		System.out.println("\nthreads.net profile details :- ");
		System.out.println("\nUsername : "+username);
		System.out.println("Total Followers of "+username+" is : "+Followers);
		System.out.println("Profile pic of "+username+" is : "+profilepic_link);
		System.out.println("\nValid Username...");
		
	}
	private void invalidUsername(String username) // Test Case - 2
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		WebElement wrong_username = driver.findElement(By.xpath("//*[starts-with(text(),'Sorry')]"));
		String invalid_username = wrong_username.getText();
		boolean i = invalid_username.startsWith("S");
		if(i)
		{
			
			System.out.println("Test Case Failed, Invalid Username !");
			
		}
	}
	@Override
	public void checkUsers(String username) {
		driver.get("https://www.threads.net/"+username);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.manage().window().minimize();
		System.out.println("Test Case Check : 1.Test with Valid Username or 2.Test with Invalid Username");
		Scanner tc = new Scanner(System.in);
		int choice2 = tc.nextInt();
		switch(choice2) // Test case check part
		{
		case 1 : this.validUsername(username); 
		         break;
		case 2 : this.invalidUsername(username);
				break;
		default : System.out.println("Invalid Option !");
		break;
		}
		
		
		
		
	}
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"/home/kumaresan/eclipse-workspace/SeleniumTesting/drivers/chromedriver");
		int choice;
		Scanner s = new Scanner(System.in);
		System.out.println("1. Visit threads.net site : ");
		System.out.println("2. Visit threads.net with username : ");
		System.out.println("\nEnter module to test : ");
		choice = s.nextInt();
		switch (choice) {
		case 1:
			Threads v = new Threads();
			v.visitPage();
			break;

		case 2:
			System.out.println("Enter Username : ");
			Scanner u = new Scanner(System.in);
			String un = u.next();
			Threads unn = new Threads();
			unn.checkUsers(un);
			break;
		default : System.out.println("Invalid Option !");
		break;

		}

	}

}
