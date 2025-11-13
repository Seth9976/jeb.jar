package com.pnfsoftware.jebglobal;

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
public class cnb extends AbstractConverter {
   private static final ILogger zz = GlobalLog.getLogger(cnb.class);
   public static int q = 0;
   public static int RF = 7936;
   public static int xK = 8192;
   public static int Dw = 16128;
   public static int Uv = 16384;
   public static int oW = 16640;
   @SerId(1)
   IEVar[] gO = new IEVar[32];
   @SerId(2)
   IEVar[] nf;
   @SerId(3)
   IEVar gP;
   @SerId(4)
   IEVar za;
   @SerId(5)
   IEVar[] lm;

   public cnb(cno var1) {
      super(var1);

      for (int var2 = 0; var2 <= 31; var2++) {
         int var3 = var2 * 256 + q;
         this.gO[var2] = this.gCtx.createRegister(var3, cnp.RF[var2], this.regNormalBitsize);
      }

      this.nf = new IEVar[32];

      for (int var4 = 0; var4 <= 31; var4++) {
         int var6 = var4 * 256 + xK;
         this.nf[var4] = this.gCtx.createRegister(var6, cnp.xK[var4], this.regNormalBitsize);
      }

      this.gP = this.gCtx.createRegister(Uv, "pc", this.regNormalBitsize);
      this.za = this.gCtx.createRegister(oW, "fcsr", this.regNormalBitsize);
      this.lm = new IEVar[8];

      for (int var5 = 0; var5 <= 7; var5++) {
         this.lm[var5] = this.gCtx.createVirtualRegister("temp" + var5, this.regNormalBitsize);
      }
   }

   @Override
   public IEVar getProgramCounter() {
      return this.gP;
   }

   @Override
   public IEVar getStackPointer() {
      return this.gO[2];
   }

   @Override
   public IEVar getReturnAddressRegister() {
      return this.gO[1];
   }

   @Override
   public IEVar getGPRegister(int var1) {
      return this.gO[var1];
   }

   @Override
   public IEVar getFPRegister(int var1) {
      return this.nf[var1];
   }

   @Override
   public IEVar getTempRegister(int var1) {
      return this.lm[var1];
   }

   @Override
   public final void setCurrentContext(IERoutineContext var1) {
      super.setCurrentContext(var1);
   }

   @Override
   public final void convertBlock(BasicBlock var1, List var2) {
      super.convertBlock(var1, var2);
   }

   protected IEGeneric q(long var1, cnl var3, int var4) {
      cnm var5 = (cnm)var3.getOperand(var4);
      switch (var5.getOperandType()) {
         case 0:
            long var10 = var5.getOperandValue();
            int var8 = RegisterUtil.getRegisterIndex(var10);
            switch (RegisterUtil.getRegisterGroup(var10)) {
               case 0:
                  return this.gO[var8];
               case 2:
                  return this.nf[var8];
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
      if (!this.q(var1, var2, var3)) {
         return false;
      } else {
         for (IEStatement var5 : var1.r) {
            var5.replaceVar(this.gO[0], EUtil.zero(this.getRegisterBitsize()), true);
         }

         EUtil.setLowerLevelAddress(var1.address, var1.r);
         return true;
      }
   }

   private boolean q(ConverterInstructionEntry var1, String var2, boolean var3) {
      ACS var4 = ((cnl)var1.insn).getACS();
      if (var4 != null && var4.getOpcodeSemantic() != ACS.OPS.CUSTOM && this.autoConvert(var1, var4)) {
         return true;
      } else {
         long var5 = var1.address;
         cnl var7 = (cnl)var1.insn;
         IEGeneric var8 = var7.getCountOfOperands() <= 0 ? null : this.q(var5, var7, 0);
         IEGeneric var9 = var7.getCountOfOperands() <= 1 ? null : this.q(var5, var7, 1);
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
               IEUntranslatedInstruction var12 = this.ctx.createUntranslatedInstruction(var5, ((cnl)var1.insn).getMnemonic());
               var1.r.add(var12);
               return true;
            default:
               return false;
         }
      }
   }

   @Override
   protected boolean convertInstructionLastChance(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.ctx.createUntranslatedInstruction(var1.address, ((cnl)var1.insn).getMnemonic());
      var1.r.add(var2);
      return true;
   }
}
