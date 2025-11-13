package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class LC extends Yg {
   private static Map A = new HashMap();
   private static Map kS = new HashMap();
   @SerId(1)
   private int wS = 32;

   public static final Yg pC(int var0) {
      return var0 == 64 ? A(RegisterUtil.createPureRegisterId(0, 10), var0, var0) : A(RegisterUtil.createPureRegisterId(15, 0), 32, var0);
   }

   public static final Yg sY(int var0) {
      return var0 == 64 ? A(RegisterUtil.createPureRegisterId(31, 0), var0, var0) : A(RegisterUtil.createPureRegisterId(13, 0), 32, var0);
   }

   public static final Yg ys(int var0) {
      return var0 == 64 ? A(RegisterUtil.createPureRegisterId(30, 0), var0, var0) : A(RegisterUtil.createPureRegisterId(14, 0), 32, var0);
   }

   protected LC(int var1, long var2, int var4, int var5) {
      super(0, var1, var2, var5 & 16777215);
      this.wS = this.ld(var4);
   }

   private int ld(int var1) {
      switch (var1) {
         case 16:
            return 32;
         case 32:
         case 64:
            return var1;
         default:
            return 32;
      }
   }

   public static Yg pC(int var0, int var1) {
      return kS(0, var0, var1);
   }

   public static Yg pC(int var0, int var1, int var2) {
      return pC(0, var0, var1, var2);
   }

   public static long A(int var0, int var1, int var2) {
      int var3 = var0 & 0xFF;
      int var4 = A(var0, var2);
      if (var2 != 64 && var3 == 6 && var4 != 128) {
         int var5 = 128 / var4;
         int var6 = var5 - 1;
         int var7 = var4 * (var1 & var6);
         int var8 = var1 >>> (var5 >>> 1);
         return RegisterUtil.createRegisterId(var8, var3, var4, var7);
      } else {
         return RegisterUtil.createRegisterId(var1, var3, var4);
      }
   }

   public static Yg A(long var0, int var2) {
      return A(var0, RegisterUtil.getRegisterBitsize(var0), var2);
   }

   public static Yg pC(int var0, int var1, int var2, int var3) {
      return (Yg)(var3 == 0 ? kS(var0, var1, var2) : new LC(A(var0, var2), A(var0, var1, var2), var2, var3));
   }

   public static Yg kS(int var0, int var1, int var2) {
      int var3 = A(var0, var2);
      long var4 = A(var0, var1, var2);
      return var2 == 64 ? A(var4, var3, var2) : pC(var4, var3, var2);
   }

   private static Yg pC(long var0, int var2, int var3) {
      Object var4 = (Yg)kS.get(var0);
      if (var4 == null) {
         var4 = new LC(var2, var0, var3, 0);
         kS.put(var0, var4);
      }

      return (Yg)var4;
   }

   private static Yg A(long var0, int var2, int var3) {
      Object var4 = (Yg)A.get(var0);
      if (var4 == null) {
         var4 = new LC(var2, var0, var3, 0);
         A.put(var0, var4);
      }

      return (Yg)var4;
   }

   protected static int A(int var0, int var1) {
      int var2 = var0 >>> 16;
      if (var2 != 0) {
         return var2;
      } else if ((var0 & 0xFF) == 4) {
         return 32;
      } else {
         return var1 == 64 ? 64 : 32;
      }
   }

   @Override
   public String getRegisterName(long var1) {
      return kS(var1, this.wS);
   }

   public static String kS(long var0, int var2) {
      int var3 = RegisterUtil.getRegisterGroup(var0);
      int var4 = RegisterUtil.getRegisterIndex(var0);
      IRegisterBank var5 = RegisterUtil.getBank(var2 == 64 ? ProcessorType.ARM64 : ProcessorType.ARM);
      switch (var3) {
         case 10:
            return "PC";
         default:
            RegisterDescriptionEntry var6 = var5.getDescriptionEntryById(var0);
            if (var6 == null) {
               throw new IllegalArgumentException("Can not retrieve register from group " + var3 + " with index " + var4 + " in mode " + var2);
            } else {
               return var6.getName();
            }
      }
   }

   @Override
   public boolean A(int var1) {
      return wS(this.getOperandValue(), var1);
   }

   @Override
   public boolean kS(int var1) {
      return UT(this.getOperandValue(), var1);
   }

   @Override
   public boolean wS(int var1) {
      return E(this.getOperandValue(), var1);
   }

   @Override
   public boolean E(int var1) {
      if (var1 != 64) {
         return false;
      } else {
         int var2 = RegisterUtil.getRegisterGroup(this.value);
         int var3 = RegisterUtil.getRegisterIndex(this.value);
         return var2 == 0 && var3 == 33;
      }
   }

   public static boolean wS(long var0, int var2) {
      int var3 = RegisterUtil.getRegisterGroup(var0);
      int var4 = RegisterUtil.getRegisterIndex(var0);
      return var2 == 64 ? var3 == 10 && var4 == 0 : var3 == 0 && var4 == 15;
   }

   public static boolean UT(long var0, int var2) {
      int var3 = RegisterUtil.getRegisterGroup(var0);
      int var4 = RegisterUtil.getRegisterIndex(var0);
      return var3 == 0 && var4 == (var2 == 64 ? 30 : 14);
   }

   public static boolean E(long var0, int var2) {
      int var3 = RegisterUtil.getRegisterGroup(var0);
      int var4 = RegisterUtil.getRegisterIndex(var0);
      return var3 == 0 && var4 == (var2 == 64 ? 31 : 13);
   }

   public static boolean pC(Yg var0, int var1) {
      if (!var0.isRegister()) {
         return false;
      } else {
         long var2 = var0.getOperandValue();
         return UT(var2, var1);
      }
   }

   public static boolean A(Yg var0, int var1) {
      if (!var0.isRegister()) {
         return false;
      } else {
         long var2 = var0.getOperandValue();
         return E(var2, var1);
      }
   }

   @Override
   public boolean UT(int var1) {
      return RegisterUtil.getRegisterGroup(this.getOperandValue()) == 0 && var1 == RegisterUtil.getRegisterIndex(this.getOperandValue());
   }

   public boolean A() {
      int var1 = RegisterUtil.getRegisterGroup(this.getOperandValue());
      switch (var1) {
         case 6:
         case 7:
            return true;
         default:
            return false;
      }
   }

   public boolean kS() {
      int var1 = RegisterUtil.getRegisterGroup(this.getOperandValue());
      switch (var1) {
         case 12:
         case 13:
         case 14:
            return true;
         default:
            return false;
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + this.wS;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         LC var2 = (LC)var1;
         return this.wS == var2.wS;
      }
   }
}
