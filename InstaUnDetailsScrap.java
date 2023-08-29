package selenium.test.threadsnet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.classes.base.SeleniumPackages;
/**
*
* Copyright © 2023 Kumaresan L
* @author kumaresan
* 
*/
public class InstagramDetailsScrapping {
	/**
        *
	* @author kumaresan
        * instagramUsername(String) method - to get and reterive information from instagram account 
	*
	*/
	public static void instagramUsername(String un) throws InterruptedException, IOException {
		SeleniumPackages.chromeDriverLaunch();
		SeleniumPackages.launchUrl("https://www.instagram.com/" + un);
		try {
			WebElement wrong_user = SeleniumPackages.locator(By.xpath("//*[starts-with(text(),'Sorry')]"));
			String wu = wrong_user.getText();
			boolean wu_check = wu.startsWith("Sorry");
			if (wu_check) {
				SeleniumPackages.closeEntireDriver();
				System.out.println("Username not available !!");
			}
		} catch (Exception e) {

			System.out.println("Username : " + un);
			SeleniumPackages.pageWaitBasedOnNetwork(5000);
			/**
			* @author kumaresan
                        * get and reterive the total posts count in the instagram account using getText() method and locator xpath.
			*
	                */
			WebElement total_posts = SeleniumPackages.locator(By.xpath("(//li[@class])[1]"));
			String tp = total_posts.getText();
			System.out.println("Total Posts of " + un + " is " + tp);
			/**
			* @author kumaresan
                        * get and reterive the total followers count in the instagram account using getText() method and locator xpath.
			*
			*/
			WebElement total_followers = SeleniumPackages.locator(By.xpath("(//li[@class])[2]"));
			String tf = total_followers.getText();
			System.out.println("Total Followers of " + un + " is " + tf);
			/**
			* @author kumaresan
                        * get and reterive the total following count in the instagram account using getText() method and locator xpath.
			*
			*/
			WebElement total_following = SeleniumPackages.locator(By.xpath("(//li[@class])[3]"));
			String tfg = total_following.getText();
			System.out.println(un + " who following accounts : " + tfg);
			/**
			* @author kumaresan
                        * get and reterive the name in the instagram account using getText() method and locator xpath.
			*
			*/
			WebElement name = SeleniumPackages.locator(By.xpath("(//span[@dir])[1]"));
			String un_name = name.getText();
			System.out.println("Name of " + un + " is " + un_name);
			/**
			* @author kumaresan
                        * get and reterive the bio information in the instagram account using getText() method and locator xpath.
			*
			*/
			WebElement bio = SeleniumPackages.locator(By.xpath("//h1[@dir]"));
			String un_bio = bio.getText();
			System.out.println(un + "'s bio : ");
			System.out.println(un_bio);
			/**
			* @author kumaresan
                        * get and reterive the posts links and screenshots in the instagram account and save it to the mentioned folder path.
			*
			*/
			SeleniumPackages.pageWaitBasedOnNetwork(20000);
			List<WebElement> posts_photos = SeleniumPackages.locatorList(By.xpath("//div[@class='_aagv']"));
			int image[] = new int[posts_photos.size()];
			int file_name = 1;
			for (int i = 0; i < image.length; i++) {
				image[i] = file_name;
				file_name++;
			}
			System.out.println("\nFetching "+image.length+" images links and take shots ...");
			File file = new File("/home/kumaresan/Documents/TestScreenshots/"+un+"");
			for (int i = 1; i <= posts_photos.size(); i++) {

				List<WebElement> photos = SeleniumPackages.locatorList(posts_photos,
						By.xpath("(//div[@class='_aagv'])[" + i + "]"));
				List<WebElement> photos_links = SeleniumPackages.locatorList(photos,
						By.xpath("(//div[@class='_aagv'])[" + i + "]/img[@src]"));

				SeleniumPackages.minimizeWindow();
//						  Scanner input2 = new Scanner(System.in);
//						  image[i] = input2.nextInt();
				for (WebElement pp : photos_links) {

					System.out.println("Post Image " + i + " : " + pp.getAttribute("src"));
					String urls = pp.getAttribute("src");
					SeleniumPackages.launchUrl(urls);
					
					SeleniumPackages.takeScreenshot(SeleniumPackages.driver,
							"/home/kumaresan/Documents/TestScreenshots/"+un+"/" + image[i - 1] + ".png");
					SeleniumPackages.pageWaitTill(3000);
					SeleniumPackages.backPage();

				}
			}
		}

		System.out.println("\n"+un+" Details Scrapped Successfully *");
		SeleniumPackages.pageWaitTill(2000);
		SeleniumPackages.closeEntireDriver();

	}
	/**
	* @author kumaresan
        * getting no.of.usernames count and usernames, based on that the instagramUsername(String) method get invoked.
	*
	*/
	public static void main(String[] args) throws InterruptedException, IOException {

		SeleniumPackages.setSystemEnvironment("chrome", "drivers/chromedriver");
		System.out.println("Enter number of Instagram User Id to scrap : ");
		Scanner in1 = new Scanner(System.in);
		int no_of_ids = in1.nextInt();
		System.out.println("Enter "+no_of_ids+" IDs :- ");
		Scanner un = new Scanner(System.in);
		String username[] = new String[no_of_ids];
		for(int i=0;i<no_of_ids;i++)
		{
			username[i] = un.next();
			
		}
		for(int i=0;i<no_of_ids;i++)
		{
			instagramUsername(username[i]);
			SeleniumPackages.closeEntireDriver();
			SeleniumPackages.pageWaitTill(2000);
		}
		
	}

}
