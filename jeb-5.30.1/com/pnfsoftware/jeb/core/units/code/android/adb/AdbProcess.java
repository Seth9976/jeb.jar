package com.pnfsoftware.jeb.core.units.code.android.adb;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdbProcess {
   private String user;
   private int pid;
   private String name;

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   void setField(AdbProcess.Field var1, String var2) {
      if (var1 != null) {
         switch (AdbProcess$1.$SwitchMap$com$pnfsoftware$jeb$core$units$code$android$adb$AdbProcess$Field[var1.ordinal()]) {
            case 1:
               this.user = var2;
               break;
            case 2:
               this.pid = Conversion.stringToInt(var2);
               break;
            case 3:
               this.name = var2;
         }
      }
   }

   public String getUser() {
      return this.user;
   }

   public int getPid() {
      return this.pid;
   }

   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return Strings.ff("%d:%s", this.pid, this.name);
   }

   public static List parsePsData(byte[] var0) {
      ArrayList var1 = new ArrayList();
      String[] var2 = Strings.decodeLocal(var0).split("\\r?\\n");
      if (var2.length == 0) {
         return var1;
      } else {
         HashMap var3 = new HashMap();
         int var4 = 0;
         String[] var5 = var2[0].trim().split("\\s+");

         for (String var9 : var5) {
            if (var9.equalsIgnoreCase("user")) {
               var3.put(var4, AdbProcess.Field.USER);
            } else if (var9.equalsIgnoreCase("pid")) {
               var3.put(var4, AdbProcess.Field.PID);
            }

            var4++;
         }

         for (int var15 = 1; var15 < var2.length; var15++) {
            String var16 = var2[var15].trim();
            if (!var16.isEmpty()) {
               AdbProcess var17 = new AdbProcess();
               var4 = 0;
               var5 = var16.split("\\s+");

               for (String var12 : var5) {
                  var17.setField((AdbProcess.Field)var3.get(var4), var12);
                  var4++;
               }

               var17.setField(AdbProcess.Field.NAME, var5[var5.length - 1]);
               var1.add(var17);
            }
         }

         return var1;
      }
   }

   static enum Field {
      USER,
      PID,
      NAME;
   }
}
