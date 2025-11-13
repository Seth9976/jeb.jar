package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.HK;
import com.pnfsoftware.jebglobal.ta;
import java.util.Deque;
import java.util.Map;
import java.util.TreeSet;

public class kY implements PY {
   private PY xK;
   private static final float Dw = 0.00390625F;
   private static final float[] Uv = new float[]{0.00390625F, 3.0517578E-5F, 1.1920929E-7F, 4.656613E-10F};
   public static final String[] q = new String[]{"px", "dp", "sp", "pt", "in", "mm"};
   public static final String[] RF = new String[]{"%", "%p"};

   public kY() {
   }

   public kY(PY var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.xK = var1;
      }
   }

   public String q(ta var1) {
      return var1.q() ? this.q(var1.lm >> 24, var1.gO, null, null) : "{complex_resource}";
   }

   public String q(HK var1) {
      return this.q(0, var1, null, null);
   }

   public boolean q(ta var1, int[] var2) {
      if (var1.q() && var1.gO.RF == 3) {
         if (var2 != null) {
            var2[0] = var1.lm >> 24;
            var2[1] = var1.gO.xK;
         }

         return true;
      } else {
         return false;
      }
   }

   private static String q(long var0, eo.eo var2, int var3) {
      Map var4 = var2.Dw();
      if (var4.isEmpty()) {
         return null;
      } else if (var2.q() == 1) {
         return (String)var4.get(var0);
      } else if (var2.q() != 0) {
         return null;
      } else if (var0 == 0L) {
         return (String)var4.get(var0);
      } else {
         TreeSet var5 = new TreeSet();
         if (var3 > 0) {
            long var6 = (1L << var3) - 1L;
            long var8 = var0 & var6;
            String var10 = (String)var4.get(var8);
            if (var10 == null) {
               return null;
            }

            var5.add(var10);
            var0 &= ~var6;
         }

         if (var0 != 0L) {
            for (Long var7 : var4.keySet()) {
               if (var7 != 0L && (var0 & var7) == var7) {
                  String var12 = (String)var4.get(var7);
                  if (var12 != null) {
                     var5.add(var12);
                  }

                  var0 &= ~var7;
               }
            }

            if (var0 != 0L) {
               return null;
            }
         }

         return Strings.join("|", var5);
      }
   }

   public static String q(String var0, String var1, long var2) {
      byte var5 = 0;
      eo.eo var4;
      if (var0.equals("manifest")) {
         var4 = eo.q().q(var1);
         if (var4 != null && var4.Uv()) {
            var4 = eo.RF().q(var1);
         }

         if (var1.equals("protectionLevel")) {
            var5 = 4;
         }
      } else {
         var4 = eo.RF().q(var1);
      }

      return var4 == null ? null : q(var2, var4, var5);
   }

   public String q(int var1, HK var2, String var3, Deque var4) {
      if ((var2.RF == 16 || var2.RF == 17) && var3 != null && var4 != null && var4.size() >= 1) {
         String var5 = (String)var4.getLast();
         String var6 = q(var5, var3, var2.xK);
         if (var6 != null) {
            return var6;
         }
      }

      switch (var2.RF) {
         case 0:
            if (var2.xK == 0) {
               return "undefined";
            } else {
               if (var2.xK == 1) {
                  return "empty";
               }

               return Strings.ff("illegal(%d)", var2.xK);
            }
         case 1:
            if (this.xK != null) {
               String var11 = this.xK.xK(var2.xK);
               if (var11 != null) {
                  return "@" + var11;
               }
            }

            return Strings.ff("@ResourceId_0x%08x", var2.xK);
         case 2:
            if (this.xK != null) {
               String var10 = this.xK.xK(var2.xK);
               if (var10 != null) {
                  return "?" + var10;
               }
            }

            return Strings.ff("?ResourceId_0x%08x", var2.xK);
         case 3:
            if (this.xK != null) {
               String var9 = this.xK.q(var1, var2.xK);
               if (var9 != null) {
                  return var9;
               }
            }

            return Strings.ff("ResourceId_0x%08x", var2.xK);
         case 4:
            return Float.toString(Float.intBitsToFloat(var2.xK));
         case 5:
            return Float.toString(q(var2.xK)) + q[var2.xK >> 0 & 15];
         case 6:
            return Float.toString(q(var2.xK) * 100.0F) + RF[var2.xK >> 0 & 15];
         case 7:
            return Strings.ff("DynRef_0x%08x", var2.xK);
         case 8:
            return Strings.ff("DynAttr_0x%08x", var2.xK);
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         default:
            if (Licensing.isDebugBuild()) {
               throw new RuntimeException("Don't know how to format a value of type: " + var2.RF);
            }

            return Strings.ff("Type_%d_0x%08x", var2.RF, var2.xK);
         case 16:
            return Integer.toString(var2.xK);
         case 17:
            return Strings.ff("0x%x", var2.xK);
         case 18:
            if (var2.xK != 0) {
               ;
            }

            return var2.xK == 0 ? "false" : "true";
         case 28:
            return Strings.ff("#%08x", var2.xK);
         case 29:
            return Strings.ff("#%06x", var2.xK & 16777215);
         case 30:
            char[] var8 = Strings.ff("%08x", var2.xK).toCharArray();
            return "" + var8[0] + var8[2] + var8[4] + var8[6];
         case 31:
            char[] var7 = Strings.ff("%08x", var2.xK).toCharArray();
            return "" + var7[2] + var7[4] + var7[6];
      }
   }

   public static float q(int var0) {
      return (var0 & -256) * Uv[var0 >> 4 & 3];
   }

   @Override
   public String q(int var1, int var2) {
      return this.xK == null ? null : this.xK.q(var1, var2);
   }

   @Override
   public boolean q(int var1, int var2, String var3) {
      return this.xK == null ? false : this.xK.q(var1, var2, var3);
   }

   @Override
   public tl RF(int var1) {
      return this.xK == null ? null : this.xK.RF(var1);
   }

   @Override
   public String xK(int var1) {
      return this.xK == null ? null : this.xK.xK(var1);
   }

   @Override
   public String q(int var1, String var2) {
      return this.xK == null ? null : this.xK.q(var1, var2);
   }
}
