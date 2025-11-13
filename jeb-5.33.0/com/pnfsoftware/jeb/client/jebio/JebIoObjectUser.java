package com.pnfsoftware.jeb.client.jebio;

import java.util.Map;

public class JebIoObjectUser {
   int code;
   int userid;
   String username;
   String created;
   boolean confirmed;
   String apikey;
   int score;
   int sharecount;
   String lastsharets;
   int receivecount;
   String lastreceivets;

   public int getUserid() {
      return this.userid;
   }

   public String getUsername() {
      return this.username;
   }

   public String getCreated() {
      return this.created;
   }

   public boolean isConfirmed() {
      return this.confirmed;
   }

   public long getCode() {
      return this.code;
   }

   public String getApikey() {
      return this.apikey;
   }

   public int getScore() {
      return this.score;
   }

   public int getSharecount() {
      return this.sharecount;
   }

   public String getLastsharets() {
      return this.lastsharets;
   }

   public int getReceivecount() {
      return this.receivecount;
   }

   public String getLastreceivets() {
      return this.lastreceivets;
   }

   public static JebIoObjectUser fromJson(Map var0) throws Exception {
      long var1 = (Long)var0.get("code");
      if (var1 != 0L) {
         return null;
      } else {
         JebIoObjectUser var3 = new JebIoObjectUser();
         var3.userid = ((Long)var0.get("userid")).intValue();
         var3.username = (String)var0.get("username");
         var3.created = (String)var0.get("created");
         var3.confirmed = (Boolean)var0.get("confirmed");
         var3.apikey = (String)var0.get("apikey");
         var3.score = ((Long)var0.get("score")).intValue();
         var3.sharecount = ((Long)var0.get("sharecount")).intValue();
         var3.lastsharets = (String)var0.get("lastsharets");
         var3.receivecount = ((Long)var0.get("receivecount")).intValue();
         var3.lastreceivets = (String)var0.get("lastreceivets");
         return var3;
      }
   }
}
