package com.pnfsoftware.jeb.client.jebio;

import com.pnfsoftware.jeb.util.format.Strings;

public class UserCredentials {
   private String email;
   private String password;
   private String apikey;

   public UserCredentials(String var1, String var2, String var3) {
      this.email = var1;
      this.password = var2;
      this.apikey = var3;
   }

   public UserCredentials(String var1) {
      this.apikey = var1;
   }

   public String getEmail() {
      return this.email;
   }

   public String getPassword() {
      return this.password;
   }

   public String getApikey() {
      return this.apikey;
   }

   public boolean lookValid() {
      return this.lookValid(true);
   }

   public boolean lookValid(boolean var1) {
      return !Strings.isBlank(this.email) && !Strings.isBlank(this.password) && !Strings.isBlank(this.apikey);
   }
}
