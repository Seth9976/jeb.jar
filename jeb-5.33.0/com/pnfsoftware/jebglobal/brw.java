package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexFieldData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class brw {
   private static final ILogger pC = GlobalLog.getLogger(brw.class);
   private static final brw.Av A = new brw.Av();
   private IDexDecompilerUnit kS;
   private Map wS = new ConcurrentHashMap();
   private static final Set UT = new HashSet();

   public brw(IDexDecompilerUnit var1) {
      Assert.a(var1 != null);
      this.kS = var1;
   }

   public brw.Av pC(String var1) {
      brw.Av var2 = (brw.Av)this.wS.get(var1);
      if (var2 == A) {
         var2 = null;
      }

      return var2;
   }

   public boolean pC(String var1, IDState var2) {
      brw.Av var3 = this.kS(var1);
      if (var3 != null && var3 != A && var3.E >= 0) {
         Object var4;
         try {
            IDImm var5 = var2.getStaticField(var1);
            if (var3.A.startsWith("[")) {
               Object var6 = var2.getArrayObject(var5);
               String var7 = var3.A.substring(1);
               switch (var7) {
                  case "Z":
                     var4 = (boolean[])var6;
                     break;
                  case "B":
                     var4 = (byte[])var6;
                     break;
                  case "C":
                     var4 = (char[])var6;
                     break;
                  case "S":
                     var4 = (short[])var6;
                     break;
                  case "I":
                     var4 = (int[])var6;
                     break;
                  case "J":
                     var4 = (long[])var6;
                     break;
                  case "F":
                     var4 = (float[])var6;
                     break;
                  case "D":
                     var4 = (double[])var6;
                     break;
                  case "Ljava/lang/String;":
                     var4 = (String[])var6;
                     break;
                  default:
                     return false;
               }
            } else {
               String var11 = var3.A;
               switch (var11) {
                  case "Z":
                     var4 = (var5.getRawValue() & 1L) == 1L;
                     break;
                  case "B":
                     var4 = (byte)var5.getRawValue();
                     break;
                  case "C":
                     var4 = (char)var5.getRawValue();
                     break;
                  case "S":
                     var4 = (short)var5.getRawValue();
                     break;
                  case "I":
                     var4 = (int)var5.getRawValue();
                     break;
                  case "J":
                     var4 = var5.getRawValue();
                     break;
                  case "F":
                     var4 = Float.intBitsToFloat((int)var5.getRawValue());
                     break;
                  case "D":
                     var4 = Double.longBitsToDouble(var5.getRawValue());
                     break;
                  case "Ljava/lang/String;":
                     var4 = var2.getStringObject(var5);
                     break;
                  default:
                     return false;
               }
            }
         } catch (Exception var10) {
            return false;
         }

         return this.pC(var3, var4);
      } else {
         return false;
      }
   }

   private boolean pC(brw.Av var1, Object var2) {
      Assert.a(var1 != null);
      if (var2 == null) {
         return false;
      } else {
         synchronized (var1) {
            var1.UT++;
            if (var1.E < 0) {
               return false;
            } else {
               if (var1.wS == null) {
                  var1.wS = var2;
               } else if (!this.pC(var2, var1)) {
                  var1.wS = null;
                  var1.E = -1;
                  return true;
               }

               var1.E++;
               return true;
            }
         }
      }
   }

   private boolean pC(Object var1, brw.Av var2) {
      try {
         if (var2.A.startsWith("[")) {
            String var6 = var2.A.substring(1);
            switch (var6) {
               case "Z":
                  return Arrays.equals((boolean[])var1, (boolean[])var2.wS);
               case "B":
                  return Arrays.equals((byte[])var1, (byte[])var2.wS);
               case "C":
                  return Arrays.equals((char[])var1, (char[])var2.wS);
               case "S":
                  return Arrays.equals((short[])var1, (short[])var2.wS);
               case "I":
                  return Arrays.equals((int[])var1, (int[])var2.wS);
               case "J":
                  return Arrays.equals((long[])var1, (long[])var2.wS);
               case "F":
                  return Arrays.equals((float[])var1, (float[])var2.wS);
               case "D":
                  return Arrays.equals((double[])var1, (double[])var2.wS);
               case "Ljava/lang/String;":
                  return Arrays.equals((Object[])((String[])var1), (Object[])((String[])var2.wS));
               default:
                  return false;
            }
         } else {
            String var3 = var2.A;
            switch (var3) {
               case "Z":
                  return (Boolean)var1 == (Boolean)var2.wS;
               case "B":
                  return (Byte)var1 == (Byte)var2.wS;
               case "C":
                  return (Character)var1 == (Character)var2.wS;
               case "S":
                  return (Short)var1 == (Short)var2.wS;
               case "I":
                  return (Integer)var1 == (Integer)var2.wS;
               case "J":
                  return (Long)var1 == (Long)var2.wS;
               case "F":
                  return (Float)var1 == (Float)var2.wS;
               case "D":
                  return (Double)var1 == (Double)var2.wS;
               case "Ljava/lang/String;":
                  return var2.wS.equals(var1);
               default:
                  return false;
            }
         }
      } catch (Exception var5) {
         return false;
      }
   }

   private brw.Av kS(String var1) {
      brw.Av var2 = (brw.Av)this.wS.get(var1);
      if (var2 == null) {
         synchronized (var1.intern()) {
            if ((var2 = (brw.Av)this.wS.get(var1)) == null) {
               IDexUnit var4 = this.kS.getCodeUnit();
               JvmFieldSig var5 = JvmFieldSig.parse(var1);
               IDexClass var6 = var4.getClass(var5.csig);
               if (var6 == null) {
                  return null;
               }

               if (!A(var5.ftype)) {
                  return null;
               }

               IDexField var7 = var4.getField(var1);
               if (var5 == null || !var7.isInternal()) {
                  return null;
               }

               IDexFieldData var8 = var7.getData();
               if (!var8.isStatic()) {
                  return null;
               }

               var2 = new brw.Av(var1, var8.getAccessFlags());
               IDexValue var9 = DexUtil.getStaticFieldInitializer(var6, var8);
               if (var9 != null && var9.getType() != 30) {
                  Object var10 = this.pC(var9);
                  if (!this.pC(var2, var10)) {
                     return null;
                  }
               }

               this.wS.put(var1, var2);
            }
         }
      }

      return var2;
   }

   public static boolean A(String var0) {
      if (var0.startsWith("[")) {
         var0 = var0.substring(1);
      }

      return UT.contains(var0);
   }

   private Object pC(IDexValue var1) {
      switch (var1.getType()) {
         case 0:
            return var1.getByte();
         case 1:
         case 5:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 24:
         case 25:
         case 26:
         case 27:
         case 29:
         default:
            return null;
         case 2:
            return var1.getShort();
         case 3:
            return var1.getChar();
         case 4:
            return var1.getInt();
         case 6:
            return var1.getLong();
         case 16:
            return var1.getFloat();
         case 17:
            return var1.getDouble();
         case 23:
            return this.kS.getCodeUnit().getString(var1.getStringIndex());
         case 28:
            ArrayList var2 = new ArrayList();

            for (IDexValue var4 : var1.getArray()) {
               Object var5 = this.pC(var4);
               if (var5 == null) {
                  return null;
               }

               var2.add(var5);
            }

            return var2;
         case 30:
            return null;
         case 31:
            return var1.getBoolean();
      }
   }

   static {
      UT.add("Z");
      UT.add("B");
      UT.add("C");
      UT.add("S");
      UT.add("I");
      UT.add("J");
      UT.add("F");
      UT.add("D");
      UT.add("Ljava/lang/String;");
   }

   public static class Av {
      final String pC;
      final String A;
      final int kS;
      Object wS;
      int UT;
      int E;

      private Av() {
         this.pC = null;
         this.A = null;
         this.kS = 0;
      }

      private Av(String var1, int var2) {
         this.pC = var1;
         this.A = var1.substring(var1.lastIndexOf(58) + 1);
         this.kS = var2;
      }

      public String pC() {
         return this.A;
      }

      public Object A() {
         return this.wS;
      }

      public brw.Sv kS() {
         return brw.Sv.pC(this.E);
      }

      @Override
      public String toString() {
         return Strings.ff("score:%d", this.E);
      }
   }

   public static enum Sv {
      pC(0),
      A(1),
      kS(3),
      wS(10),
      UT(Integer.MAX_VALUE);

      final int E;

      private Sv(int var3) {
         this.E = var3;
      }

      static brw.Sv pC(int var0) {
         if (var0 < A.E) {
            return pC;
         } else if (var0 < kS.E) {
            return A;
         } else if (var0 < wS.E) {
            return kS;
         } else {
            return var0 < UT.E ? wS : UT;
         }
      }

      public boolean pC(brw.Sv var1) {
         return this.E >= var1.E;
      }
   }
}
