package com.pnfsoftware.jeb.client.jebio;

import com.pnfsoftware.jeb.client.AbstractClientContext;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.util.format.Strings;

public class JebIoUtil {
   public static UserCredentials retrieveCredentials(AbstractClientContext var0) {
      IPropertyManager var1 = var0.getPropertyManager();
      String var2 = var1.getString(".jebio.credentials.Email");
      String var3 = var1.getString(".jebio.credentials.Password");
      String var4 = var1.getString(".jebio.credentials.ApiKey");
      return new UserCredentials(var2, var3, var4);
   }

   public static void saveCredentials(AbstractClientContext var0, UserCredentials var1) {
      IPropertyManager var2 = var0.getPropertyManager();
      var2.setString(".jebio.credentials.Email", Strings.safe(var1.getEmail()));
      var2.setString(".jebio.credentials.Password", Strings.safe(var1.getPassword()));
      var2.setString(".jebio.credentials.ApiKey", Strings.safe(var1.getApikey()));
   }
}
