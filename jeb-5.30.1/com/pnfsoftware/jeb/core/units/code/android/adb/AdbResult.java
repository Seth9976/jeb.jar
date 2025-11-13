package com.pnfsoftware.jeb.core.units.code.android.adb;

import com.pnfsoftware.jeb.util.format.Strings;

public class AdbResult {
   public static final String ADB_ERROR = "adb_error_sent_to_jeb";
   private static final String[] ERROR_MESSAGES = new String[]{"no such file or directory", "permission denied", "not found"};
   private byte[] output;

   public AdbResult(byte[] var1) {
      this.output = var1;
   }

   public byte[] getOutput() {
      return this.output;
   }

   public String getOutputString() {
      return Strings.decodeLocal(this.getOutput());
   }

   public boolean isSuccess() {
      return this.isSuccess(null, true);
   }

   public boolean isSuccess(Boolean var1) {
      return this.isSuccess(var1, true);
   }

   public boolean isSuccess(Boolean var1, boolean var2, String... var3) {
      if (this.output == null) {
         return false;
      } else if (var1 == null || (!var1 || this.output.length != 0) && (var1 || this.output.length == 0)) {
         if (var2) {
            String var4 = Strings.decodeLocal(this.getOutput()).toLowerCase();

            for (String var8 : ERROR_MESSAGES) {
               if (var4.contains(var8)) {
                  return false;
               }
            }
         }

         if (var3 != null && var3.length != 0) {
            String var10 = Strings.decodeLocal(this.getOutput()).toLowerCase();

            for (String var15 : var3) {
               if (var10.contains(var15.toLowerCase())) {
                  return false;
               }
            }
         }

         if (this.output.length >= "adb_error_sent_to_jeb".length()) {
            try {
               String var11 = Strings.decodeLocal(this.getOutput()).toLowerCase();
               if (var11.contains("adb_error_sent_to_jeb")) {
                  return false;
               }
            } catch (Exception var9) {
            }
         }

         return true;
      } else {
         return false;
      }
   }
}
