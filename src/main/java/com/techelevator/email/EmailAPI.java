//package com.techelevator.email;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class EmailAPI {
//	
//	public static void sendInvitation(String invitationKey, String email)
//	{
//		Mailin http = new Mailin("https://api.sendinblue.com/v2.0","CFOqmR7st1f2J84I",5000);   //Optional parameter: Timeout in MS
//
//        Map <String,String> data = new HashMap <String,String> ();
//            data.put("type", "classic");
//            data.put("status", "draft");
//            data.put("page", 1);
//            data.put("page_limit", 10);
//            
//            //begin writing email
//            
//            data.put("to", email);
//            data.put("from", "inroletechelevator@gmail.com");
//            data.put("subject", "You've been invited to InRole");
//            data.put("html", "<h3>Your sign-up link is: <a href=" +)
//            
//
//        String str = http.get_campaigns_v2(data);
//        System.out.println(str);
//	}
//	
//	
//
//}
