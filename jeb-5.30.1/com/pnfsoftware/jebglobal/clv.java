package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionOperandList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class clv extends AbstractInstructionOperandList {
   private static Map RF = new HashMap();
   public static final int q = 65536;
   @SerId(1)
   private boolean xK;

   public clv(int var1, int var2, long var3) {
      super(var1, var2, var3, 0);
   }

   public clv(int var1, int var2, int var3, long var4, boolean var6) {
      super(var1, var3, var4, var2);
      this.xK = var6;
   }

   public clv(long var1, int var3, clv... var4) {
      super(var4.length, var1, var3, var4);
   }

   public static long q(int var0, int var1, int var2) {
      int var3 = q(var0, var2);
      return RegisterUtil.createRegisterId(var1, var0, var3);
   }

   public static clv RF(int var0, int var1, int var2) {
      int var3 = q(var0, var2);
      long var4 = q(var0, var1, var2);
      switch (var0) {
         case 0:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 10:
            return q(var4, var3, var2);
         case 1:
         case 7:
         case 8:
         case 9:
         default:
            return null;
      }
   }

   private static clv q(long var0, int var2, int var3) {
      clv var4 = (clv)RF.get(var0);
      if (var4 == null) {
         var4 = new clv(0, var2, var0);
         RF.put(var0, var4);
      }

      return var4;
   }

   private static int q(int var0, int var1) {
      switch (var0) {
         case 2:
            return 1;
         default:
            return var1 == 64 ? 64 : 32;
      }
   }

   @Override
   public String getRegisterName(long var1) {
      return q(var1, this.getOperandBitsize() == 64 ? 64 : 32);
   }

   public static String q(long var0, int var2) {
      int var3 = RegisterUtil.getRegisterGroup(var0);
      IRegisterBank var4 = RegisterUtil.getBank(var2 == 64 ? ProcessorType.MIPS64 : ProcessorType.MIPS);
      switch (var3) {
         case 0:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 10:
         case 12:
            RegisterDescriptionEntry var5 = var4.getDescriptionEntryById(var0);
            if (var5 == null) {
               throw new RuntimeException("Invalid register value " + var0 + " for group " + var3 + " at index " + RegisterUtil.getRegisterIndex(var0));
            }

            return var5.getName();
         case 1:
         case 7:
         case 8:
         case 9:
         case 11:
         default:
            return null;
      }
   }

   @Override
   public long getOperandValue(long var1) {
      int var3 = (this.getFlags() & 65536) != 0 ? 4 : 0;
      if (this.type == 2) {
         return (var1 + var3 & -268435456L) + this.value + var3;
      } else {
         return this.type == 3 ? var1 + this.value + var3 : super.getOperandValue(var1);
      }
   }

   public static clv q(int var0, long var1, boolean var3) {
      return new clv(1, 0, var0, var1, var3);
   }

   public Long q(long var1, int var3, IMachineContext var4) {
      switch (this.getOperandType()) {
         case 0:
            return cmz.q(var4, this.getOperandValue(), var3);
         case 1:
         case 2:
         case 3:
            return this.getOperandValue(var1);
         default:
            return null;
      }
   }

   public boolean q() {
      return this.xK;
   }

   public void q(List var1) {
      switch (this.getOperandType()) {
         case 0:
            var1.add((int)RegisterUtil.getPureId(this.getOperandValue()));
         case 1:
         case 2:
         case 3:
         case 6:
            break;
         case 4:
         case 5:
         default:
            throw new RuntimeException("TBI");
         case 7:
            for (IInstructionOperand var5 : this.getOperands()) {
               ((clv)var5).q(var1);
            }
      }
   }
}
