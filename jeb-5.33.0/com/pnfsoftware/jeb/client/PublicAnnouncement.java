package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class PublicAnnouncement {
   private static final ILogger logger = GlobalLog.getLogger(PublicAnnouncement.class);
   private int id;
   private String text;
   Version jebver_lt;
   Version jebver_le;
   Version jebver_gt;
   Version jebver_ge;

   private PublicAnnouncement() {
   }

   public static PublicAnnouncement parse(String var0) {
      JSONObject var1 = (JSONObject)JSONValue.parse(var0);

      try {
         PublicAnnouncement var2 = new PublicAnnouncement();
         var2.id = (int)((Long)var1.get("id")).longValue();
         var2.text = (String)var1.get("text");
         String var3 = (String)var1.get("text_" + Licensing.buildkey);
         if (var3 != null) {
            var2.text = var2.text + var3;
         }

         var3 = (String)var1.get("jebver_lt");
         if (var3 != null) {
            var2.jebver_lt = Version.parseFromString(var3);
         }

         var3 = (String)var1.get("jebver_le");
         if (var3 != null) {
            var2.jebver_le = Version.parseFromString(var3);
         }

         var3 = (String)var1.get("jebver_gt");
         if (var3 != null) {
            var2.jebver_gt = Version.parseFromString(var3);
         }

         var3 = (String)var1.get("jebver_ge");
         if (var3 != null) {
            var2.jebver_ge = Version.parseFromString(var3);
         }

         return var2;
      } catch (Exception var4) {
         return null;
      }
   }

   public int getId() {
      return this.id;
   }

   public String getText() {
      return this.text;
   }

   public boolean checkTests() {
      return Strings.isBlank(this.getText()) ? false : this.checkVersion();
   }

   boolean checkVersion() {
      if (this.jebver_lt != null && AbstractContext.app_ver.compareTo(this.jebver_lt) >= 0) {
         return false;
      } else if (this.jebver_le != null && AbstractContext.app_ver.compareTo(this.jebver_le) > 0) {
         return false;
      } else {
         return this.jebver_gt != null && AbstractContext.app_ver.compareTo(this.jebver_gt) <= 0
            ? false
            : this.jebver_ge == null || AbstractContext.app_ver.compareTo(this.jebver_ge) >= 0;
      }
   }
}
