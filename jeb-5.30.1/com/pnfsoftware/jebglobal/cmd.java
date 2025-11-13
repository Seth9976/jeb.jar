package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.nio.ByteOrder;
import java.util.List;

@Ser
public class cmd extends AbstractInstruction implements cki {
   @SerId(2)
   private int q;
   @SerId(3)
   private cmb RF;
   @SerTransient
   private clt xK;

   public cmd(BytesBlock var1, String var2, clv[] var3, clt var4, cmb var5, int var6) {
      super(var1, var2, var3, var6);
      this.xK = var4;
      if (this.xK == null) {
         this.q = 0;
      } else {
         this.q = var4.getFlags();
      }

      this.RF = var5 != null ? var5 : cmb.q;
   }

   public FlowInformation Uv() {
      String var1 = this.getMnemonic();
      if (!var1.equals("BREAK") && !var1.equals("SDBBP")) {
         return FlowInformation.NONE;
      } else {
         FlowInformation var2 = new FlowInformation();
         var2.addTarget(CodePointer.createUnknown(this.processorMode));
         return var2;
      }
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      if (!this.isBreakingFlow(this.RF())) {
         return FlowInformation.NONE;
      } else {
         FlowInformation var3 = new FlowInformation(this.RF != cmb.q, this.RF().q() ? 1 : 0);

         try {
            Long var4 = this.RF().q(this.getCode(), var1, 0, (clv[])this.getOperands(), null);
            if (var4 != null) {
               int var5 = this.processorMode;
               if (this.RF().RF()) {
                  if (var5 == 16) {
                     var5 = 32;
                  } else {
                     var5 = 16;
                  }
               }

               var3.addTarget(this.q(var4, var5));
            } else {
               var3.addTarget(CodePointer.createUnknown());
            }
         } catch (ProcessorException var6) {
            var3.addTarget(CodePointer.createUnknown());
         }

         return var3;
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      FlowInformation var3 = this.Uv();
      if (var3.isBroken()) {
         return var3;
      } else if (!this.isRoutineCall(this.RF())) {
         return FlowInformation.NONE;
      } else {
         var3 = new FlowInformation(false, this.RF().q() ? 1 : 0);

         try {
            Long var4 = this.RF().q(this.getCode(), var1, 0, (clv[])this.getOperands(), null);
            if (var4 != null) {
               int var5 = this.processorMode;
               if (this.RF().RF()) {
                  if (var5 == 16) {
                     var5 = 32;
                  } else {
                     var5 = 16;
                  }
               }

               var3.addTarget(this.q(var4, var5));
            }
         } catch (ProcessorException var6) {
         }

         return var3;
      }
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
      String var4 = this.getMnemonic();
      switch (var4) {
         case "LWL":
         case "LWR":
         case "LDL":
         case "LDR":
            if (((clv[])this.operands)[0].getOperandType() == 0) {
               var1.add((int)RegisterUtil.getPureId(((clv[])this.operands)[0].getOperandValue()));
            }

            ((clv[])this.operands)[1].q(var2);
            break;
         case "SWL":
         case "SWR":
         case "SDL":
         case "SDR":
            ((clv[])this.operands)[0].q(var2);
            ((clv[])this.operands)[1].q(var2);
         case "NOP":
            break;
         default:
            throw new RuntimeException("TBI " + this.getMnemonic());
      }
   }

   public void q(List var1) {
      for (int var2 = 0; var2 < ((clv[])this.operands).length; var2++) {
         ((clv[])this.operands)[var2].q(var1);
      }
   }

   public static boolean q(String var0) {
      return var0.contains(".") || var0.contains("0") || var0.contains("1") || var0.contains("2") && !var0.contains("32");
   }

   public static boolean RF(String var0) {
      switch (var0) {
         case "DALIGN":
         case "DAUI":
         case "DAHI":
         case "DATI":
         case "DBITSWAP":
         case "DADD":
         case "DADDI":
         case "DADDIU":
         case "DADDU":
         case "DCLZ":
         case "DCLO":
         case "DSUB":
         case "DSUBU":
         case "DSLLV":
         case "DSRLV":
         case "DSRAV":
         case "DLSA":
         case "DSLL":
         case "DSLL32":
         case "DSRA":
         case "DSRA32":
         case "DSRL":
         case "DSRL32":
         case "DROTR":
         case "DROTR32":
         case "DROTRV":
         case "DMULT":
         case "DMULTU":
         case "DDIV":
         case "DDIVU":
         case "DMOD":
         case "DMODU":
         case "DMUH":
         case "DMUL":
         case "DMUHU":
         case "DMULU":
         case "DMFC0":
         case "DMFC1":
         case "DMFC2":
         case "DMTC0":
         case "DMTC1":
         case "DMTC2":
         case "LD":
         case "LDL":
         case "LDR":
         case "LDPC":
         case "LLD":
         case "LLDX":
         case "LWU":
         case "LWUPC":
         case "SCD":
         case "SCDX":
         case "SD":
         case "SDL":
         case "SDR":
         case "DEXT":
         case "DEXTM":
         case "DEXTU":
         case "DINS":
         case "DINSM":
         case "DINSU":
         case "DSBH":
         case "DSHD":
            return true;
         default:
            return false;
      }
   }

   @Override
   public boolean canThrow() {
      String var1 = this.getMnemonic();
      if (RF(var1)) {
         return true;
      } else if (!q(var1)) {
         switch (var1) {
            case "BREAK":
            case "SDBBP":
            case "TEQ":
            case "TEQI":
            case "TGE":
            case "TGEI":
            case "TGEIU":
            case "TGEU":
            case "TLT":
            case "TLTI":
            case "TLTIU":
            case "TLTU":
            case "TNE":
            case "TNEI":
            case "SYSCALL":
            case "ADD":
            case "ADDI":
            case "SUB":
            case "CACHE":
            case "CACHEE":
            case "LBE":
            case "LBU":
            case "LBUE":
            case "LH":
            case "LHE":
            case "LHU":
            case "LHUE":
            case "LL":
            case "LLE":
            case "LLX":
            case "LLXE":
            case "LW":
            case "LWE":
            case "LWL":
            case "LWLE":
            case "LWPC":
            case "LWR":
            case "LWRE":
            case "SB":
            case "SBE":
            case "SC":
            case "SCE":
            case "SCX":
            case "SCXE":
            case "SH":
            case "SHE":
            case "SW":
            case "SWE":
            case "SWL":
            case "SWLE":
            case "SWR":
            case "SWRE":
            case "SYNCI":
            case "PREF":
            case "PREFE":
            case "PREFX":
            case "DERET":
            case "DI":
            case "DVP":
            case "EI":
            case "ERET":
            case "ERETNC":
            case "EVP":
            case "MOVF":
            case "MOVT":
            case "RDPGPR":
            case "TLBINV":
            case "TLBINVF":
            case "TLBP":
            case "TLBR":
            case "TLBWI":
            case "TLBWR":
            case "WAIT":
            case "WRPGPR":
            case "DDIV":
            case "DDIVU":
            case "EXT":
            case "INS":
            case "RDHWR":
            case "ROTR":
            case "ROTRV":
            case "SEB":
            case "SEH":
            case "SIGRIE":
            case "WSBH":
               return true;
            default:
               return false;
         }
      } else {
         return !var1.startsWith("J");
      }
   }

   @Override
   public ICodePointer q(IMachineContext var1) throws ProcessorException {
      long var2 = var1.getRegisters().getProgramCounter();
      String var4 = this.getMnemonic();
      if (var4.equals("BREAK") || var4.equals("SDBBP")) {
         return this.q(2147484032L, this.processorMode);
      } else if (!this.isJump(this.RF())) {
         return this.buildNextEntryPoint(var2);
      } else {
         boolean var5;
         try {
            var5 = this.RF.q(var1, (clv[])this.operands, this.processorMode);
         } catch (RuntimeException var8) {
            throw new ProcessorException("Unable to determine result of condition", var8);
         }

         if (var5) {
            Long var9 = this.RF().q(this.getCode(), var2, this.processorMode, (clv[])this.operands, var1);
            if (var9 != null) {
               if ((var9 & 1L) == 1L) {
                  throw new ProcessorException("MIPS16 is not managed by the current plugin version");
               } else {
                  return this.q(var9, this.processorMode);
               }
            } else {
               throw new ProcessorException("Unable to compute next address for this instruction");
            }
         } else if (this.RF().q()) {
            long var6 = var2 + this.getSize();
            return this.q(var6 + clu.q(var1, var6), this.processorMode);
         } else {
            return this.buildNextEntryPoint(var2);
         }
      }
   }

   private CodePointer q(long var1, int var3) {
      return new CodePointer(var1, var3);
   }

   @Override
   public clt RF() {
      if (this.xK == null) {
         this.xK = clt.q(this.q);
      }

      return this.xK;
   }

   public cmb oW() {
      return this.RF;
   }

   @Override
   public byte[] xK() {
      return this.getCode(ByteOrder.BIG_ENDIAN);
   }

   public boolean gO() {
      String var1 = this.getMnemonic();
      return !var1.startsWith("S") ? false : this.operands != null && ((clv[])this.operands).length == 2 && ((clv[])this.operands)[1].getOperandType() == 7;
   }

   @Override
   public boolean Dw() {
      if (this.RF().isPCUpdated()) {
         return true;
      } else {
         String var1 = this.getMnemonic();
         return var1.equals("PAUSE") || var1.equals("WAIT");
      }
   }
}
