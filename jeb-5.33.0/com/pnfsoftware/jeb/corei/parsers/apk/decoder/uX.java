package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.cS;
import com.pnfsoftware.jebglobal.fp;
import java.util.Deque;
import java.util.Map;
import java.util.TreeSet;

public class uX implements RC {
   private RC kS;
   private static final float[] wS = new float[]{0.00390625F, 3.0517578E-5F, 1.1920929E-7F, 4.656613E-10F};
   public static final String[] pC = new String[]{"px", "dp", "sp", "pt", "in", "mm"};
   public static final String[] A = new String[]{"%", "%p"};

   public uX() {
   }

   public uX(RC var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.kS = var1;
      }
   }

   public String pC(cS var1) {
      return var1.pC() ? this.pC(var1.E >> 24, var1.A, null, null) : "{complex_resource}";
   }

   public String pC(fp var1) {
      return this.pC(0, var1, null, null);
   }

   public boolean pC(cS var1, int[] var2) {
      if (var1.pC() && var1.A.A == 3) {
         if (var2 != null) {
            var2[0] = var1.E >> 24;
            var2[1] = var1.A.kS;
         }

         return true;
      } else {
         return false;
      }
   }

   private static String pC(long var0, Av.Av var2, int var3) {
      Map var4 = var2.kS();
      if (var4.isEmpty()) {
         return null;
      } else if (var2.pC() == 1) {
         return (String)var4.get(var0);
      } else if (var2.pC() != 0) {
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

   public static String pC(String var0, String var1, long var2) {
      byte var5 = 0;
      Av.Av var4;
      if (var0.equals("manifest")) {
         var4 = Av.pC().pC(var1);
         if (var4 != null && var4.wS()) {
            var4 = Av.A().pC(var1);
         }

         if (var1.equals("protectionLevel")) {
            var5 = 4;
         }
      } else {
         var4 = Av.A().pC(var1);
      }

      return var4 == null ? null : pC(var2, var4, var5);
   }

   public String pC(int var1, fp var2, String var3, Deque var4) {
      if ((var2.A == 16 || var2.A == 17) && var3 != null && var4 != null && var4.size() >= 1) {
         String var5 = (String)var4.getLast();
         String var6 = pC(var5, var3, var2.kS);
         if (var6 != null) {
            return var6;
         }
      }

      switch (var2.A) {
         case 0:
            if (var2.kS == 0) {
               return "undefined";
            } else {
               if (var2.kS == 1) {
                  return "empty";
               }

               return Strings.ff("illegal(%d)", var2.kS);
            }
         case 1:
            if (this.kS != null) {
               String var11 = this.kS.kS(var2.kS);
               if (var11 != null) {
                  return "@" + var11;
               }
            }

            return Strings.ff("@ResourceId_0x%08x", var2.kS);
         case 2:
            if (this.kS != null) {
               String var10 = this.kS.kS(var2.kS);
               if (var10 != null) {
                  return "?" + var10;
               }
            }

            return Strings.ff("?ResourceId_0x%08x", var2.kS);
         case 3:
            if (this.kS != null) {
               String var9 = this.kS.pC(var1, var2.kS);
               if (var9 != null) {
                  return var9;
               }
            }

            return Strings.ff("ResourceId_0x%08x", var2.kS);
         case 4:
            return Float.toString(Float.intBitsToFloat(var2.kS));
         case 5:
            return Float.toString(pC(var2.kS)) + pC[var2.kS >> 0 & 15];
         case 6:
            return Float.toString(pC(var2.kS) * 100.0F) + A[var2.kS >> 0 & 15];
         case 7:
            return Strings.ff("DynRef_0x%08x", var2.kS);
         case 8:
            return Strings.ff("DynAttr_0x%08x", var2.kS);
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
               throw new RuntimeException("Don't know how to format a value of type: " + var2.A);
            }

            return Strings.ff("Type_%d_0x%08x", var2.A, var2.kS);
         case 16:
            return Integer.toString(var2.kS);
         case 17:
            return Strings.ff("0x%x", var2.kS);
         case 18:
            if (var2.kS != 0) {
               ;
            }

            return var2.kS == 0 ? "false" : "true";
         case 28:
            return Strings.ff("#%08x", var2.kS);
         case 29:
            return Strings.ff("#%06x", var2.kS & 16777215);
         case 30:
            char[] var8 = Strings.ff("%08x", var2.kS).toCharArray();
            return "" + var8[0] + var8[2] + var8[4] + var8[6];
         case 31:
            char[] var7 = Strings.ff("%08x", var2.kS).toCharArray();
            return "" + var7[2] + var7[4] + var7[6];
      }
   }

   public static float pC(int var0) {
      return (var0 & -256) * wS[var0 >> 4 & 3];
   }

   @Override
   public String pC(int var1, int var2) {
      return this.kS == null ? null : this.kS.pC(var1, var2);
   }

   @Override
   public boolean pC(int var1, int var2, String var3) {
      return this.kS == null ? false : this.kS.pC(var1, var2, var3);
   }

   @Override
   public p A(int var1) {
      return this.kS == null ? null : this.kS.A(var1);
   }

   @Override
   public String kS(int var1) {
      return this.kS == null ? null : this.kS.kS(var1);
   }
}
