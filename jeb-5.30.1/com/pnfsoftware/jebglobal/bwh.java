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

public class bwh {
   private static final ILogger q = GlobalLog.getLogger(bwh.class);
   private static final bwh.eo RF = new bwh.eo();
   private IDexDecompilerUnit xK;
   private Map Dw = new ConcurrentHashMap();
   private static final Set Uv = new HashSet();

   public bwh(IDexDecompilerUnit var1) {
      Assert.a(var1 != null);
      this.xK = var1;
   }

   public void q() {
      this.Dw.clear();
   }

   public boolean RF() {
      return this.Dw.isEmpty();
   }

   public bwh.eo q(String var1) {
      bwh.eo var2 = (bwh.eo)this.Dw.get(var1);
      if (var2 == RF) {
         var2 = null;
      }

      return var2;
   }

   public boolean q(String var1, IDState var2) {
      bwh.eo var3 = this.xK(var1);
      if (var3 != null && var3 != RF && var3.oW >= 0) {
         Object var4;
         try {
            IDImm var5 = var2.getStaticField(var1);
            if (var3.RF.startsWith("[")) {
               Object var6 = var2.getArrayObject(var5);
               String var7 = var3.RF.substring(1);
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
               String var11 = var3.RF;
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

         return this.q(var3, var4);
      } else {
         return false;
      }
   }

   public boolean q(String var1, Object var2) {
      bwh.eo var3 = this.xK(var1);
      return var3 != null && var3 != RF && var3.oW >= 0 ? this.q(var3, var2) : false;
   }

   private boolean q(bwh.eo var1, Object var2) {
      Assert.a(var1 != null);
      if (var2 == null) {
         return false;
      } else {
         synchronized (var1) {
            var1.Uv++;
            if (var1.oW < 0) {
               return false;
            } else {
               if (var1.Dw == null) {
                  var1.Dw = var2;
               } else if (!this.q(var2, var1)) {
                  var1.Dw = null;
                  var1.oW = -1;
                  return true;
               }

               var1.oW++;
               return true;
            }
         }
      }
   }

   private boolean q(Object var1, bwh.eo var2) {
      try {
         if (var2.RF.startsWith("[")) {
            String var6 = var2.RF.substring(1);
            switch (var6) {
               case "Z":
                  return Arrays.equals((boolean[])var1, (boolean[])var2.Dw);
               case "B":
                  return Arrays.equals((byte[])var1, (byte[])var2.Dw);
               case "C":
                  return Arrays.equals((char[])var1, (char[])var2.Dw);
               case "S":
                  return Arrays.equals((short[])var1, (short[])var2.Dw);
               case "I":
                  return Arrays.equals((int[])var1, (int[])var2.Dw);
               case "J":
                  return Arrays.equals((long[])var1, (long[])var2.Dw);
               case "F":
                  return Arrays.equals((float[])var1, (float[])var2.Dw);
               case "D":
                  return Arrays.equals((double[])var1, (double[])var2.Dw);
               case "Ljava/lang/String;":
                  return Arrays.equals((Object[])((String[])var1), (Object[])((String[])var2.Dw));
               default:
                  return false;
            }
         } else {
            String var3 = var2.RF;
            switch (var3) {
               case "Z":
                  return (Boolean)var1 == (Boolean)var2.Dw;
               case "B":
                  return (Byte)var1 == (Byte)var2.Dw;
               case "C":
                  return (Character)var1 == (Character)var2.Dw;
               case "S":
                  return (Short)var1 == (Short)var2.Dw;
               case "I":
                  return (Integer)var1 == (Integer)var2.Dw;
               case "J":
                  return (Long)var1 == (Long)var2.Dw;
               case "F":
                  return (Float)var1 == (Float)var2.Dw;
               case "D":
                  return (Double)var1 == (Double)var2.Dw;
               case "Ljava/lang/String;":
                  return var2.Dw.equals(var1);
               default:
                  return false;
            }
         }
      } catch (Exception var5) {
         return false;
      }
   }

   private bwh.eo xK(String var1) {
      bwh.eo var2 = (bwh.eo)this.Dw.get(var1);
      if (var2 == null) {
         synchronized (var1.intern()) {
            if ((var2 = (bwh.eo)this.Dw.get(var1)) == null) {
               IDexUnit var4 = this.xK.getCodeUnit();
               JvmFieldSig var5 = JvmFieldSig.parse(var1);
               IDexClass var6 = var4.getClass(var5.csig);
               if (var6 == null) {
                  return null;
               }

               if (!RF(var5.ftype)) {
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

               var2 = new bwh.eo(var1, var8.getAccessFlags());
               IDexValue var9 = DexUtil.getStaticFieldInitializer(var6, var8);
               if (var9 != null && var9.getType() != 30) {
                  Object var10 = this.q(var9);
                  if (!this.q(var2, var10)) {
                     return null;
                  }
               }

               this.Dw.put(var1, var2);
            }
         }
      }

      return var2;
   }

   public static boolean RF(String var0) {
      if (var0.startsWith("[")) {
         var0 = var0.substring(1);
      }

      return Uv.contains(var0);
   }

   private Object q(IDexValue var1) {
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
            return this.xK.getCodeUnit().getString(var1.getStringIndex());
         case 28:
            ArrayList var2 = new ArrayList();

            for (IDexValue var4 : var1.getArray()) {
               Object var5 = this.q(var4);
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
      Uv.add("Z");
      Uv.add("B");
      Uv.add("C");
      Uv.add("S");
      Uv.add("I");
      Uv.add("J");
      Uv.add("F");
      Uv.add("D");
      Uv.add("Ljava/lang/String;");
   }

   public static enum CU {
      q(0),
      RF(1),
      xK(3),
      Dw(10),
      Uv(Integer.MAX_VALUE);

      final int oW;

      private CU(int var3) {
         this.oW = var3;
      }

      public int q() {
         return this.oW;
      }

      static bwh.CU q(int var0) {
         if (var0 < RF.oW) {
            return q;
         } else if (var0 < xK.oW) {
            return RF;
         } else if (var0 < Dw.oW) {
            return xK;
         } else {
            return var0 < Uv.oW ? Dw : Uv;
         }
      }

      public boolean q(bwh.CU var1) {
         return this.oW >= var1.oW;
      }
   }

   public static class eo {
      final String q;
      final String RF;
      final int xK;
      Object Dw;
      int Uv;
      int oW;

      private eo() {
         this.q = null;
         this.RF = null;
         this.xK = 0;
      }

      private eo(String var1, int var2) {
         this.q = var1;
         this.RF = var1.substring(var1.lastIndexOf(58) + 1);
         this.xK = var2;
      }

      public String q() {
         return this.q;
      }

      public String RF() {
         return this.RF;
      }

      public int xK() {
         return this.xK;
      }

      public Object Dw() {
         return this.Dw;
      }

      public bwh.CU Uv() {
         return bwh.CU.q(this.oW);
      }

      @Override
      public String toString() {
         return Strings.ff("score:%d", this.oW);
      }
   }
}
