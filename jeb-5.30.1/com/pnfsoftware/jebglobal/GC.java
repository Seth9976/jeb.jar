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
public class GC extends CW {
   public static final int q = 0;
   public static final int gP = 2097152;
   public static final int za = 8388614;
   public static final int lm = 4194310;
   public static final int zz = 2097158;
   public static final int JY = 1048582;
   public static final int HF = 524294;
   public static final int LK = 8388615;
   public static final int io = 8388620;
   public static final int qa = 1048589;
   public static final int Hk = 1048591;
   public static final int Me = 4194318;
   public static final int PV = 1;
   public static final int oQ = 13;
   public static final int xW = 14;
   public static final int KT = 15;
   public static final int Gf = 31;
   public static final int Ef = 30;
   private static Map cC = new HashMap();
   private static Map sH = new HashMap();
   @SerId(1)
   private int CE = 32;

   public static final CW q(int var0) {
      return var0 == 64 ? RF(RegisterUtil.createPureRegisterId(0, 10), var0, var0) : RF(RegisterUtil.createPureRegisterId(15, 0), 32, var0);
   }

   public static final CW oW(int var0) {
      return var0 == 64 ? RF(RegisterUtil.createPureRegisterId(31, 0), var0, var0) : RF(RegisterUtil.createPureRegisterId(13, 0), 32, var0);
   }

   public static final CW gO(int var0) {
      return var0 == 64 ? RF(RegisterUtil.createPureRegisterId(30, 0), var0, var0) : RF(RegisterUtil.createPureRegisterId(14, 0), 32, var0);
   }

   protected GC(long var1, int var3) {
      this(32, var1, var3, 0);
   }

   protected GC(int var1, long var2, int var4, int var5) {
      super(0, var1, var2, var5 & 16777215);
      this.CE = this.nf(var4);
   }

   private int nf(int var1) {
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

   public static CW q(int var0, int var1) {
      return xK(0, var0, var1);
   }

   public static CW q(int var0, int var1, int var2) {
      return q(0, var0, var1, var2);
   }

   public static long RF(int var0, int var1, int var2) {
      int var3 = var0 & 0xFF;
      int var4 = RF(var0, var2);
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

   public static CW RF(long var0, int var2) {
      return RF(var0, RegisterUtil.getRegisterBitsize(var0), var2);
   }

   public static CW q(int var0, int var1, int var2, int var3) {
      return (CW)(var3 == 0 ? xK(var0, var1, var2) : new GC(RF(var0, var2), RF(var0, var1, var2), var2, var3));
   }

   public static CW xK(int var0, int var1, int var2) {
      int var3 = RF(var0, var2);
      long var4 = RF(var0, var1, var2);
      return var2 == 64 ? RF(var4, var3, var2) : q(var4, var3, var2);
   }

   private static CW q(long var0, int var2, int var3) {
      Object var4 = (CW)sH.get(var0);
      if (var4 == null) {
         var4 = new GC(var2, var0, var3, 0);
         sH.put(var0, var4);
      }

      return (CW)var4;
   }

   private static CW RF(long var0, int var2, int var3) {
      Object var4 = (CW)cC.get(var0);
      if (var4 == null) {
         var4 = new GC(var2, var0, var3, 0);
         cC.put(var0, var4);
      }

      return (CW)var4;
   }

   protected static int RF(int var0, int var1) {
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
      return xK(var1, this.CE);
   }

   public static String xK(long var0, int var2) {
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
   public boolean RF(int var1) {
      return Dw(this.getOperandValue(), var1);
   }

   @Override
   public boolean xK(int var1) {
      return Uv(this.getOperandValue(), var1);
   }

   @Override
   public boolean Dw(int var1) {
      return oW(this.getOperandValue(), var1);
   }

   public static boolean Dw(long var0, int var2) {
      int var3 = RegisterUtil.getRegisterGroup(var0);
      int var4 = RegisterUtil.getRegisterIndex(var0);
      return var2 == 64 ? var3 == 10 && var4 == 0 : var3 == 0 && var4 == 15;
   }

   public static boolean Uv(long var0, int var2) {
      int var3 = RegisterUtil.getRegisterGroup(var0);
      int var4 = RegisterUtil.getRegisterIndex(var0);
      return var3 == 0 && var4 == (var2 == 64 ? 30 : 14);
   }

   public static boolean oW(long var0, int var2) {
      int var3 = RegisterUtil.getRegisterGroup(var0);
      int var4 = RegisterUtil.getRegisterIndex(var0);
      return var3 == 0 && var4 == (var2 == 64 ? 31 : 13);
   }

   public static boolean q(CW var0, int var1) {
      if (!var0.isRegister()) {
         return false;
      } else {
         long var2 = var0.getOperandValue();
         return Uv(var2, var1);
      }
   }

   public static boolean RF(CW var0, int var1) {
      if (!var0.isRegister()) {
         return false;
      } else {
         long var2 = var0.getOperandValue();
         return oW(var2, var1);
      }
   }

   @Override
   public boolean Uv(int var1) {
      return RegisterUtil.getRegisterGroup(this.getOperandValue()) == 0 && var1 == RegisterUtil.getRegisterIndex(this.getOperandValue());
   }

   public boolean RF() {
      int var1 = RegisterUtil.getRegisterGroup(this.getOperandValue());
      switch (var1) {
         case 6:
         case 7:
            return true;
         default:
            return false;
      }
   }

   public boolean xK() {
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
      return 31 * var1 + this.CE;
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
         GC var2 = (GC)var1;
         return this.CE == var2.CE;
      }
   }
}
