package com.pnfsoftware.jeb.util.net;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import okhttp3.Authenticator;

public class NetProxyInfo {
   private static final ILogger logger = GlobalLog.getLogger(NetProxyInfo.class);
   public static final int INDEX_DIRECT = 0;
   public static final int INDEX_HTTP = 1;
   public static final int INDEX_SOCKS = 2;
   public static final String TYPE_DIRECT = "direct";
   public static final String TYPE_HTTP = "http";
   public static final String TYPE_SOCKS = "socks";
   private String typestr;
   private String hostname;
   private int port;
   private String user;
   private String password;
   private String whitelist;
   private Proxy proxy;
   private Authenticator authenticator;
   private List wlpatterns = new ArrayList();

   public static List getProxyTypes() {
      return Arrays.asList("direct", "http", "socks");
   }

   public static List getProxyTypesWithDescriptions() {
      return Arrays.asList(S.L("Direct connection (no proxy)"), "HTTP", "SOCKS");
   }

   public static String getProxyTypeFromIndex(int var0) {
      if (var0 == 0) {
         return "direct";
      } else if (var0 == 1) {
         return "http";
      } else if (var0 == 2) {
         return "socks";
      } else {
         logger.warn(S.L("Illegal proxy type index: %d"), var0);
         return "direct";
      }
   }

   public static NetProxyInfo parse(String var0) {
      try {
         if (Strings.isBlank(var0)) {
            return null;
         } else {
            String[] var1 = Strings.splitall(var0, "\\|");
            String var2 = var1[0].trim();
            String var3 = null;
            int var4 = 0;
            if (var1.length >= 3) {
               var3 = var1[1].trim();
               var4 = Integer.parseInt(var1[2].trim());
            }

            String var5 = null;
            String var6 = null;
            if (var1.length >= 4) {
               var5 = var1[3].trim();
            }

            if (var1.length >= 5) {
               var6 = var1[4].trim();
            }

            String var7 = null;
            if (var1.length >= 6) {
               var7 = var1[5].trim();
            }

            return new NetProxyInfo(var2, var3, var4, var5, var6, var7);
         }
      } catch (Exception var8) {
         logger.catching(var8);
         return null;
      }
   }

   public NetProxyInfo(String var1, String var2, int var3) {
      this(var1, var2, var3, null, null, null);
   }

   public NetProxyInfo(String var1, String var2, int var3, String var4, String var5) {
      this(var1, var2, var3, var4, var5, null);
   }

   public NetProxyInfo(String var1, String var2, int var3, String var4, String var5, String var6) {
      Type var7;
      if (var1.equalsIgnoreCase("direct")) {
         var7 = Type.DIRECT;
      } else if (var1.equalsIgnoreCase("http")) {
         var7 = Type.HTTP;
      } else {
         if (!var1.equalsIgnoreCase("socks")) {
            throw new IllegalArgumentException("Unsupported proxy type: " + var1);
         }

         var7 = Type.SOCKS;
      }

      if (var7 == Type.DIRECT) {
         this.proxy = Proxy.NO_PROXY;
      } else {
         this.proxy = new Proxy(var7, new InetSocketAddress(var2, var3));
      }

      this.typestr = var1;
      this.hostname = var2;
      this.port = var3;
      if (var4 != null && var4.length() > 0 || var5 != null && var5.length() > 0) {
         this.user = Strings.safe(var4);
         this.password = Strings.safe(var5);
      }

      if (var6 != null) {
         this.whitelist = "";

         for (String var11 : var6.trim().split(",")) {
            var11 = var11.trim();
            if (!var11.isEmpty()) {
               if (var11.contains("|")) {
                  logger.warn(S.L("Illegal filter for hostname or IP in proxy whitelist: %s"), var11);
               } else {
                  try {
                     String var12 = var11.replace(".", "\\.").replace("*", ".*");
                     Pattern var13 = Pattern.compile(var12, 2);
                     this.wlpatterns.add(var13);
                     if (this.whitelist.length() > 0) {
                        this.whitelist = this.whitelist + ",";
                     }

                     this.whitelist = this.whitelist + var11;
                  } catch (PatternSyntaxException var14) {
                     logger.warn(S.L("Illegal filter for hostname or IP in proxy whitelist: %s"), var11);
                  }
               }
            }
         }
      }
   }

   public boolean isProxy() {
      return this.proxy.type() != Type.DIRECT;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public String getType() {
      switch (this.proxy.type()) {
         case DIRECT:
            return "direct";
         case HTTP:
            return "http";
         case SOCKS:
            return "socks";
         default:
            logger.warn(S.L("Unsupported proxy type: %s"), this.proxy.type());
            return "direct";
      }
   }

   public int getTypeIndex() {
      String var1 = this.getType();
      switch (var1) {
         case "direct":
            return 0;
         case "http":
            return 1;
         case "socks":
            return 2;
         default:
            throw new RuntimeException();
      }
   }

   public String getHostname() {
      return this.proxy.type() == Type.DIRECT ? null : ((InetSocketAddress)this.proxy.address()).getHostString();
   }

   public int getPort() {
      return this.proxy.type() == Type.DIRECT ? 0 : ((InetSocketAddress)this.proxy.address()).getPort();
   }

   public String getUser() {
      return this.proxy.type() == Type.DIRECT ? null : this.user;
   }

   public String getPassword() {
      return this.proxy.type() == Type.DIRECT ? null : this.password;
   }

   public boolean hasCredentials() {
      return this.getUser() != null;
   }

   public Authenticator getAuthenticator() {
      if (this.authenticator == null && this.hasCredentials()) {
         String var1 = this.getUser();
         String var2 = this.getPassword();
         this.authenticator = new NetProxyInfo$1(this, var1, var2);
      }

      return this.authenticator;
   }

   public String getWhitelist() {
      return this.proxy.type() == Type.DIRECT ? null : this.whitelist;
   }

   public boolean isWhitelisted(String var1) {
      if (this.wlpatterns.isEmpty()) {
         return false;
      } else {
         String var2;
         try {
            URL var3 = new URL(var1);
            var2 = var3.getHost();
         } catch (MalformedURLException var5) {
            return false;
         }

         for (Pattern var4 : this.wlpatterns) {
            if (var4.matcher(var2).matches()) {
               return true;
            }
         }

         return false;
      }
   }

   public String getRecordedHostname() {
      return this.hostname;
   }

   public int getRecordedPort() {
      return this.port;
   }

   public String getRecordedUser() {
      return this.user;
   }

   public String getRecordedPassword() {
      return this.password;
   }

   public String getRecordedWhitelist() {
      return this.whitelist;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.proxy == null ? 0 : this.proxy.hashCode());
      var1 = 31 * var1 + (this.user == null ? 0 : this.user.hashCode());
      var1 = 31 * var1 + (this.password == null ? 0 : this.password.hashCode());
      return 31 * var1 + (this.whitelist == null ? 0 : this.whitelist.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         NetProxyInfo var2 = (NetProxyInfo)var1;
         if (this.proxy == null) {
            if (var2.proxy != null) {
               return false;
            }
         } else if (!this.proxy.equals(var2.proxy)) {
            return false;
         }

         if (this.password == null) {
            if (var2.password != null) {
               return false;
            }
         } else if (!this.password.equals(var2.password)) {
            return false;
         }

         if (this.user == null) {
            if (var2.user != null) {
               return false;
            }
         } else if (!this.user.equals(var2.user)) {
            return false;
         }

         if (this.whitelist == null) {
            if (var2.whitelist != null) {
               return false;
            }
         } else if (!this.whitelist.equals(var2.whitelist)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      try {
         String var1 = this.typestr;
         if (this.hostname != null) {
            var1 = var1 + Strings.ff("|%s|%d", this.hostname, this.port);
         }

         if (this.user != null) {
            if (this.hostname == null) {
               var1 = var1 + "||";
            }

            var1 = var1 + Strings.ff("|%s|%s", this.user, Strings.safe(this.password));
         }

         if (this.whitelist != null) {
            if (this.hostname == null) {
               var1 = var1 + "||";
            }

            if (this.user == null) {
               var1 = var1 + "||";
            }

            var1 = var1 + Strings.ff("|%s", this.whitelist);
         }

         return var1;
      } catch (Exception var2) {
         logger.catching(var2);
         return null;
      }
   }
}
