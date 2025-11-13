package com.pnfsoftware.jeb.corei.parsers.riscv;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ACS;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class Sv extends AbstractConverter {
   private static final ILogger fI = GlobalLog.getLogger(Sv.class);
   public static int pC = 0;
   public static int A = 7936;
   public static int kS = 8192;
   public static int wS = 16128;
   public static int UT = 16384;
   public static int E = 16640;
   @SerId(1)
   IEVar[] sY = new IEVar[32];
   @SerId(2)
   IEVar[] ys;
   @SerId(3)
   IEVar ld;
   @SerId(4)
   IEVar gp;
   @SerId(5)
   IEVar[] oT;

   public Sv(sy var1) {
      super(var1);

      for (int var2 = 0; var2 <= 31; var2++) {
         int var3 = var2 * 256 + pC;
         this.sY[var2] = this.gCtx.createRegister(var3, HE.pC[var2], this.regNormalBitsize);
      }

      this.ys = new IEVar[32];

      for (int var4 = 0; var4 <= 31; var4++) {
         int var6 = var4 * 256 + kS;
         this.ys[var4] = this.gCtx.createRegister(var6, HE.A[var4], this.regNormalBitsize);
      }

      this.ld = this.gCtx.createRegister(UT, "pc", this.regNormalBitsize);
      this.gp = this.gCtx.createRegister(E, "fcsr", this.regNormalBitsize);
      this.oT = new IEVar[8];

      for (int var5 = 0; var5 <= 7; var5++) {
         this.oT[var5] = this.gCtx.createVirtualRegister("temp" + var5, this.regNormalBitsize);
      }
   }

   @Override
   public IEVar getProgramCounter() {
      return this.ld;
   }

   @Override
   public IEVar getStackPointer() {
      return this.sY[2];
   }

   @Override
   public IEVar getReturnAddressRegister() {
      return this.sY[1];
   }

   @Override
   public IEVar getGPRegister(int var1) {
      return this.sY[var1];
   }

   @Override
   public IEVar getFPRegister(int var1) {
      return this.ys[var1];
   }

   @Override
   public IEVar getTempRegister(int var1) {
      return this.oT[var1];
   }

   @Override
   public final void setCurrentContext(IERoutineContext var1) {
      super.setCurrentContext(var1);
   }

   @Override
   public final void convertBlock(BasicBlock var1, List var2) {
      super.convertBlock(var1, var2);
   }

   protected IEGeneric pC(long var1, yt var3, int var4) {
      RC var5 = (RC)var3.getOperand(var4);
      switch (var5.getOperandType()) {
         case 0:
            long var10 = var5.getOperandValue();
            int var8 = RegisterUtil.getRegisterIndex(var10);
            switch (RegisterUtil.getRegisterGroup(var10)) {
               case 0:
                  return this.sY[var8];
               case 2:
                  return this.ys[var8];
               default:
                  throw new RuntimeException();
            }
         case 1:
         case 9:
            long var9 = var5.getOperandValue();
            return this.gCtx.createImm(var9, var5.getOperandBitsize());
         case 2:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         default:
            throw new RuntimeException();
         case 3:
            long var6 = var5.getOperandValue(var1);
            return this.gCtx.createImm(var6, this.getAddressBitsize());
      }
   }

   @Override
   protected boolean convertInstructionFirstChance(ConverterInstructionEntry var1, String var2, boolean var3) {
      if (!this.pC(var1, var2, var3)) {
         return false;
      } else {
         for (IEStatement var5 : var1.r) {
            var5.replaceVar(this.sY[0], EUtil.zero(this.getRegisterBitsize()), true);
         }

         EUtil.setLowerLevelAddress(var1.address, var1.r);
         return true;
      }
   }

   private boolean pC(ConverterInstructionEntry var1, String var2, boolean var3) {
      ACS var4 = ((yt)var1.insn).getACS();
      if (var4 != null && var4.getOpcodeSemantic() != ACS.OPS.CUSTOM && this.autoConvert(var1, var4)) {
         return true;
      } else {
         long var5 = var1.address;
         yt var7 = (yt)var1.insn;
         IEGeneric var8 = var7.getCountOfOperands() <= 0 ? null : this.pC(var5, var7, 0);
         IEGeneric var9 = var7.getCountOfOperands() <= 1 ? null : this.pC(var5, var7, 1);
         String var10 = var2.toLowerCase();
         switch (var10) {
            case "lui":
               var1.r.add(this.ctx.createAssign(var8, EUtil.shl(var9, EUtil.imm(12L, var9.getBitsize())).signExtend(var8.getBitsize())));
               return true;
            case "auipc":
               int var13 = this.getProgramCounter().getBitsize();
               Assert.a(var13 == var8.getBitsize());
               var1.r.add(this.ctx.createAssign(var8, EUtil.add(EUtil.imm(var5, var13), EUtil.shl(var9, EUtil.imm(12L, var9.getBitsize())).signExtend(var13))));
               return true;
            case "ecall":
            case "ebreak":
               IEUntranslatedInstruction var12 = this.ctx.createUntranslatedInstruction(var5, ((yt)var1.insn).getMnemonic());
               var1.r.add(var12);
               return true;
            default:
               return false;
         }
      }
   }

   @Override
   protected boolean convertInstructionLastChance(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.ctx.createUntranslatedInstruction(var1.address, ((yt)var1.insn).getMnemonic());
      var1.r.add(var2);
      return true;
   }
}
