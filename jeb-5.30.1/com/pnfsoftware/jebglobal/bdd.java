package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionOperandGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class bdd extends AbstractInstructionOperandGeneric {
   public static final int q = 4097;
   public static final int RF = 4098;
   public static final int xK = 4099;
   public static final int Dw = 4100;
   public static final int Uv = 1;
   public static final int oW = 2;
   public static final int gO = 3;
   public static final int nf = 4;
   public static final int gP = 5;
   public static final int za = 6;
   public static final int lm = 7;
   public static final int zz = 8;
   public static final int JY = 9;
   public static final int HF = 10;
   public static final int LK = 11;
   public static final int io = 0;
   public static final int qa = 1;
   public static final int Hk = 2;

   public bdd(int var1, int var2, long var3) {
      super(var1, var2, var3);
   }

   public static bdd q(int var0, int var1) {
      if (var0 != 10 && var0 != 11) {
         if (var0 < 1 || var0 > 9) {
            throw new IllegalArgumentException();
         }

         Assert.a(var1 == 0);
      } else {
         Assert.a(var1 >= 0 && var1 <= 63);
      }

      long var2 = var0 | var1 << 8;
      return new bdd(4097, 16, var2);
   }

   @Override
   public String getRegisterName(long var1) {
      return q(var1);
   }

   public static String q(long var0) {
      int var2 = RegisterUtil.getRegisterGroup(var0);
      int var3 = RegisterUtil.getRegisterBitsize(var0);
      int var4 = RegisterUtil.getRegisterIndex(var0);
      return q(var2, var3, var4);
   }

   public static String q(int var0, int var1, int var2) {
      if (var0 == 0) {
         if (var1 == 8 && var2 >= 0 && var2 < 32) {
            return "r" + var2;
         } else {
            throw new RuntimeException();
         }
      } else if (var0 == 1) {
         if (var1 == 16 && var2 >= 0 && var2 < 16) {
            return Strings.ff("r%d:r%d", var2 * 2 + 1, var2 * 2);
         } else {
            throw new RuntimeException();
         }
      } else if (var0 == 2) {
         if (var1 == 8 && var2 >= 0 && var2 < 64) {
            return "ior" + var2;
         } else {
            throw new RuntimeException();
         }
      } else {
         throw new RuntimeException();
      }
   }

   @Override
   protected CharSequence formatOperand(IInstruction var1, long var2) {
      long var4 = this.getOperandValue(var2);
      switch (this.type) {
         case 4097:
            int var6 = (int)var4 & 0xFF;
            int var7 = (int)var4 >> 8 & 0xFF;
            switch (var6) {
               case 1:
                  return "X";
               case 2:
                  return "X+";
               case 3:
                  return "-X";
               case 4:
                  return "Y";
               case 5:
                  return "Y+";
               case 6:
                  return "-Y";
               case 7:
                  return "Z";
               case 8:
                  return "Z+";
               case 9:
                  return "-Z";
               case 10:
                  return "Y+" + var7;
               case 11:
                  return "Z+" + var7;
            }
         default:
            return super.formatOperand(var1, var2);
         case 4098:
            return "ior" + (int)var4;
      }
   }

   public static long RF(int var0, int var1, int var2) {
      return RegisterUtil.createRegisterId(var2, var0, var1);
   }
}
