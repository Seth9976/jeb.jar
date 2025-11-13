package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.objects.SerEnumSet;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class p extends AbstractInstruction implements fA {
   private static final ILogger RF = GlobalLog.getLogger(p.class);
   public static final CW[] q = new CW[0];
   @SerId(1)
   private int xK;
   @SerId(2)
   private int Dw;
   @SerId(3)
   private List Uv;
   @SerId(4)
   private Op oW;
   @SerId(5)
   private Set gO;
   @SerId(6)
   private long nf = 0L;

   @SerCustomInitPostGraph
   private void HF() {
      if (this.getProcessorMode() == 16 && this.Dw < 65535 && this.Dw().Dw() != null) {
         byte[] var1 = this.xK();
         boolean var2 = this.Dw().q().equals("B") && (var1.length == 2 && var1[0] == 13 || var1.length == 4 && (var1[2] & 16) == 0);
         if (!var2) {
            this.Dw |= 262144;
         }
      }

      if (this.Dw == 0) {
         try {
            if (this.getProcessorMode() == 16) {
               byte[] var5 = this.xK();
               Je var7;
               if (var5.length == 2) {
                  var7 = new co().q(var5);
               } else {
                  var7 = new YE().q(var5);
               }

               mZ var3 = var7.oW(var5);
               this.Dw = var3.q();
            } else if (this.getProcessorMode() == 64) {
               byte[] var6 = this.xK();
               Je var8 = PQ.q(var6);
               mZ var9 = var8.oW(var6);
               this.Dw = var9.q();
            }
         } catch (ProcessorException var4) {
         }
      }
   }

   public p(fA var1) {
      this(var1.getCodeBlock(), var1.Dw(), var1.RF(), var1.q(), var1.Uv(), var1.getProcessorMode(), var1.lm(), var1.za());
   }

   public p(BytesBlock var1, Op var2, CW[] var3, lH var4, mZ var5, int var6, Long var7, List var8) {
      super(var1, var2.gO(), var3, var6);
      this.oW = var2;
      this.Dw = (var5 == null ? mZ.q(65536) : var5).q();
      if (var4 == null) {
         this.xK = 0;
      } else {
         this.xK = var4.RF(var3, var6).getFlags();
      }

      if (var8 != null) {
         this.Uv = new ArrayList(var8);
      }

      this.nf = var7 == null ? 0L : var7;
   }

   @Override
   public mZ Uv() {
      return mZ.q(this.Dw);
   }

   @Override
   public Op Dw() {
      return this.oW;
   }

   @Override
   public lH q() {
      return OQ.q(this.xK);
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      CW[] var6 = this.JY();
      ArrayList var7 = new ArrayList(Arrays.asList(var6));
      CW[] var8 = RF(this);
      ArrayList var9 = new ArrayList();

      for (CW var13 : var8) {
         var9.add(var13);
      }

      for (CW var21 : var6) {
         if (!var9.contains(var21)) {
            var9.add(var21);
         }
      }

      for (CW var18 : var9) {
         int var20 = (int)RegisterUtil.getPureId(var18.getOperandValue());
         if (this.getProcessorMode() != 64 || var20 != 33) {
            if (var7.contains(var18)) {
               if (var4.contains(var20)) {
                  var5.add(var20);
               } else {
                  if (this.RF(var20)) {
                     var5.add(var20);
                  }

                  var4.add(var20);
               }
            } else {
               var5.add(var20);
            }
         }
      }

      var1.addAll(var4);
      var2.addAll(var5);
      String var16 = this.Dw().q();
      if (Strings.isContainedIn(var16, "HVC", "SMC", "SVC")) {
         var2.addAll(var1);
      }
   }

   private boolean RF(int var1) {
      fV var2 = PG.q(this);
      if (var2 != null && var2.Dw().Uv() && (int)RegisterUtil.getPureId(var2.Dw().xK().getOperandValue()) == var1) {
         return true;
      } else {
         String var3 = this.Dw().q();
         if (Strings.startsWith(var3, "LDC", "STC", "RFE") && !Strings.startsWith(var3, "LDCLR", "STCLR")) {
            return true;
         } else if (Strings.startsWith(var3, "SMLAL", "SMLSL", "UMAAL", "UMLAL")) {
            return true;
         } else if (Strings.isContainedIn(var3, "MOVT", "MOVK", "BFC", "BFI", "BFM", "BFXIL")) {
            return true;
         } else {
            int var4 = this.getCountOfOperands();
            return var4 >= 3
               ? false
               : Strings.isContainedIn(var3, "ADD", "SUB", "AND", "EOR", "LSL", "LSR", "ASR", "ADC", "SBC", "ROR", "RSB", "ORR", "MUL", "BIC", "ADDW", "SUBW");
         }
      }
   }

   @Override
   public Set getInstructionFlags() {
      if (this.gO == null) {
         HashSet var1 = new HashSet();
         if (this.Uv().gO()) {
            var1.add(InstructionFlags.CONDITIONAL_EXEC);
         }

         if (this.getMnemonic().equalsIgnoreCase("UDF")) {
            var1.add(InstructionFlags.INTERRUPT_EXEC);
         }

         if (this.nf()) {
            var1.add(InstructionFlags.UNPREDICTABLE_INSN);
         }

         if (!var1.isEmpty()) {
            this.gO = SerEnumSet.wrap(InstructionFlags.class, EnumSet.copyOf(var1));
         } else {
            this.gO = Collections.emptySet();
         }
      }

      return this.gO;
   }

   @Override
   public boolean canThrow() {
      return false;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      FlowInformation var3 = new FlowInformation();
      if (this.gP() || this.nf()) {
         var3.addTarget(CodePointer.createUnknown(this.q().q(this.processorMode, -1L)));
         return var3;
      } else if (!this.isBreakingFlow(this.q())) {
         return FlowInformation.NONE;
      } else {
         Long var4 = null;

         try {
            var4 = this.q().compute(this.xK(), var1, this.processorMode, (CW[])this.operands, null);
         } catch (ProcessorException var6) {
         }

         if (var4 != null) {
            int var5 = this.q().q(this.processorMode, var4);
            if ((var4 & 1L) == 1L) {
               var4 = var4 - 1L;
            }

            var3.addTarget(this.q(var4, var5));
         } else {
            var3.addTarget(CodePointer.createUnknown(this.q().q(this.processorMode, -1L)));
         }

         if (!this.Uv().oW()) {
            var3.addTarget(this.buildNextEntryPoint(var1));
         }

         return var3;
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      if (this.isRoutineCall(this.q()) && !this.nf()) {
         FlowInformation var3 = new FlowInformation();
         Long var4 = null;

         try {
            var4 = this.q().compute(this.xK(), var1, this.processorMode, (CW[])this.operands, null);
         } catch (ProcessorException var6) {
         }

         if (var4 != null) {
            int var5 = this.q().q(this.processorMode, var4);
            if ((var4 & 1L) == 1L) {
               var4 = var4 - 1L;
            }

            var3.addTarget(this.q(var4, var5));
         }

         return var3;
      } else {
         return FlowInformation.NONE;
      }
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public ICodePointer q(IMachineContext var1) throws ProcessorException {
      if (this.nf()) {
         throw new ProcessorException("Unable to compute next address for this instruction");
      } else {
         long var2 = var1.getRegisters().getProgramCounter();
         if (!this.isJump(this.q())) {
            if (this.Uv().q(var1, (CW[])this.operands, this.processorMode)) {
               for (CW var7 : (CW[])this.operands) {
                  if (var7 instanceof wh && ((wh)var7).gP()) {
                     throw new ProcessorException("More than one operand updates the PC: unable to retrieve correct jump address");
                  }
               }
            }

            if ((this.q().getFlags() & 3840) != 1024) {
               return this.buildNextEntryPoint(var2);
            }
         }

         boolean var8 = this.Uv().q(var1, (CW[])this.operands, this.processorMode);
         if (var8) {
            Long var9 = this.q().compute(this.xK(), var2, this.processorMode, (CW[])this.operands, var1);
            if (var9 != null) {
               int var10 = this.q().q(this.processorMode, var9);
               if ((var9 & 1L) == 1L) {
                  var9 = var9 - 1L;
               }

               if (var10 != 16 && (var9 & 2L) != 0L) {
                  ProcessorException var11 = new ProcessorException(
                     Strings.ff("Wrong computation: Expected bit<1> to be 0 for instruction %s", this.toString())
                  );
                  RF.catching(var11);
                  JebCoreService.notifySilentExceptionToClient(var11);
               }

               return this.q(var9, var10);
            } else {
               throw new ProcessorException("Unable to compute next address for this instruction");
            }
         } else {
            return this.buildNextEntryPoint(var2);
         }
      }
   }

   private CodePointer q(long var1, int var3) {
      return new CodePointer(var3 == 64 ? var1 : var1 & 4294967295L, var3);
   }

   public void q(Integer var1) {
      this.Dw = mZ.q(var1).RF();
   }

   @Override
   public List za() {
      return this.Uv;
   }

   @Override
   public boolean gP() {
      return Strings.isContainedIn(this.Dw().q(), "UDF", "BKPT", "BRK");
   }

   @Override
   public boolean nf() {
      return this.Uv != null && !this.Uv.isEmpty();
   }

   @Override
   public long lm() {
      return this.nf;
   }

   @Override
   public List q(long var1) {
      ArrayList var3 = new ArrayList();
      if (this.Uv != null) {
         for (String var5 : this.Uv) {
            var3.add(new UnitNotification(NotificationType.CORRUPTION, var5, Long.toString(var1)));
         }
      }

      return var3;
   }

   @Override
   public byte[] xK() {
      return this.getCode(ByteOrder.BIG_ENDIAN);
   }

   @Override
   public boolean oW() {
      Op var1 = this.Dw();
      return this.q(var1) || this.getProcessorMode() == 64 && this.LK();
   }

   public boolean q(Op var1) {
      String var2 = var1.gO();
      return !Strings.isBlank(var1.oW()) || var2.startsWith("F") || var2.startsWith("V");
   }

   @Override
   public boolean gO() {
      return (this.lm() & 524288L) != 0L
         || (this.lm() & 131072L) != 0L
         || (this.lm() & 524288L) != 0L
         || (this.lm() & 524288L) != 0L
         || this.getProcessorMode() == 64 && this.io();
   }

   private boolean LK() {
      return this.getOperands() == null ? false : q((CW[])this.getOperands());
   }

   private static boolean q(CW[] var0) {
      for (CW var4 : var0) {
         if (var4.getOperandType() == 0 && var4 instanceof GC && ((GC)var4).RF()) {
            return true;
         }

         if (var4.getOperandType() == 7) {
            boolean var5 = q(var4.oW());
            if (var5) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean io() {
      return this.getOperands() == null ? false : RF((CW[])this.getOperands());
   }

   private static boolean RF(CW[] var0) {
      for (CW var4 : var0) {
         if (var4.getOperandType() == 0 && var4 instanceof GC && ((GC)var4).xK()) {
            return true;
         }

         if (var4.getOperandType() == 7) {
            boolean var5 = RF(var4.oW());
            if (var5) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public CW[] JY() {
      return q(this);
   }

   public static CW[] q(fA var0) {
      CW[] var1 = xK(var0);

      for (int var2 = 0; var2 < var1.length; var2++) {
         int var3 = (int)RegisterUtil.getPureId(var1[var2].getOperandValue());
         if (var0.getProcessorMode() == 64 && var3 == 33) {
            if (var1.length == 1) {
               return q;
            }

            CW[] var4 = new CW[var1.length - 1];
            if (var2 != 0) {
               System.arraycopy(var1, 0, var4, 0, var2);
            }

            if (var2 + 1 != var1.length) {
               System.arraycopy(var1, var2 + 1, var4, var2, var4.length - var2);
            }

            var1 = var4;
            var2--;
         }
      }

      return var1;
   }

   private static CW[] xK(fA var0) {
      CW var1 = GC.q(var0.getProcessorMode());
      String var2 = var0.Dw().q();
      CW[] var3 = var0.RF();
      int var4 = var0.getProcessorMode();
      if (var3 != null && var3.length != 0) {
         CW var5 = var3[0];
         CW var6 = GC.oW(var4);
         CW var7 = GC.gO(var4);
         if (var0.oW()) {
            return Dw(var0);
         } else if (var0.gO()) {
            return q;
         } else if ((!Strings.startsWith(var2, "SMLAL", "SMLSL", "SMULL", "UMAAL", "UMLAL", "UMULL") || var4 == 64)
            && !Strings.startsWith(var2, "LDAXP", "LDNP", "LDXP", "CASP", "MRRS")) {
            if (Strings.startsWith(var2, "LDC", "STC") && !Strings.startsWith(var2, "LDCLR", "STCLR")) {
               wh var15 = (wh)var3[2];
               return var15.Uv() ? new CW[]{var15.xK()} : q;
            } else if (var2.startsWith("RFE")) {
               return var5.Uv() ? new CW[]{GC.RF(var5.getOperandValue(), var4)} : q;
            } else if (var2.startsWith("SRS")) {
               return q;
            } else if (var2.startsWith("CPY")) {
               wh var14 = (wh)var5;
               wh var19 = (wh)var3[1];
               return new CW[]{var14.xK(), var19.xK(), GC.RF(var3[2].getOperandValue(), var4)};
            } else if (var4 == 64 && Strings.startsWith(var2, "SETP", "SETE", "SETM", "SETG")) {
               wh var13 = (wh)var5;
               return new CW[]{var13.xK(), GC.RF(var3[1].getOperandValue(), var4)};
            } else {
               fV var8 = PG.q(var0);
               if (var8 != null) {
                  CW[] var18 = var8.gP();
                  int var21 = (var8.nf() ? var18.length : 0) + (var8.gO() != null ? 1 : 0) + (var8.Dw().Uv() ? 1 : 0);
                  if (var21 == 0) {
                     return q;
                  } else if (var8.nf() && var21 == var18.length) {
                     return var8.gP();
                  } else {
                     CW[] var24 = new CW[var21];
                     int var25 = 0;
                     if (var8.nf()) {
                        System.arraycopy(var18, 0, var24, 0, var18.length);
                        var25 = var18.length;
                     }

                     if (var8.gO() != null) {
                        var24[var25] = var8.gO();
                        var25++;
                     }

                     if (var8.Dw().Uv()) {
                        var24[var25] = var8.Dw().xK();
                     }

                     return var24;
                  }
               } else if (Strings.startsWith(var2, "RCWCASP", "RCWCLRP", "RCWSCASP", "RCWSCLRP", "RCWSETP", "RCWSSETP", "RCWSWPP", "RCWSSWPP")) {
                  return new CW[]{var5, var3[1]};
               } else if (Strings.startsWith(var2, "RCWCAS", "RCWSCAS")) {
                  return new CW[]{var5};
               } else if (Strings.startsWith(var2, "RCWCLR", "RCWSCLR", "RCWSET", "RCWSSET", "RCWSWP", "RCWSSWP")) {
                  return new CW[]{var3[1]};
               } else if (Strings.startsWith(var2, "STILP")) {
                  wh var17 = (wh)var3[2];
                  return var17.Uv() ? new CW[]{var17.xK()} : q;
               } else if (Strings.startsWith(var2, "LDIAPP")) {
                  wh var16 = (wh)var3[2];
                  return var16.Uv() ? new CW[]{var5, var3[1], var16.xK()} : new CW[]{var5, var3[1]};
               } else if (!Strings.isContainedIn(var2, "HVC", "SMC", "SVC")) {
                  if (var4 == 64 && Strings.startsWith(var0.getMnemonic(), "SWP")) {
                     return new CW[]{var3[1]};
                  } else {
                     switch (var2) {
                        case "B":
                        case "BL":
                        case "BLR":
                        case "BLX":
                        case "BR":
                        case "BX":
                        case "BXJ":
                        case "CBNZ":
                        case "CBZ":
                        case "TBNZ":
                        case "TBZ":
                        case "TBB":
                        case "TBH":
                        case "BRAA":
                        case "BRAAZ":
                        case "BRAB":
                        case "BRABZ":
                        case "BLRAA":
                        case "BLRAAZ":
                        case "BLRAB":
                        case "BLRABZ":
                           if (var0.q().getBranchType() == IInsnEmulator.BranchType.CALL) {
                              return new CW[]{var1, var7};
                           }

                           return new CW[]{var1};
                        case "CDP":
                        case "CDP2":
                        case "CMN":
                        case "CMP":
                        case "CMPP":
                        case "CCMN":
                        case "CCMP":
                        case "GCSSTR":
                        case "GCSSTTR":
                        case "LDC":
                        case "LDC2":
                        case "MCR":
                        case "MCR2":
                        case "MCRR":
                        case "MCRR2":
                        case "PLD":
                        case "PLDW":
                        case "PLDI":
                        case "PLI":
                        case "RMIF":
                        case "SETF8":
                        case "SETF16":
                        case "STC":
                        case "STC2":
                        case "TEQ":
                        case "TST":
                        case "WFET":
                        case "WFIT":
                        case "AT":
                        case "BRB":
                        case "DC":
                        case "GCSSS1":
                        case "GCSPOPCX":
                        case "GCSPOPX":
                        case "GCSPUSHM":
                        case "GCSPUSHX":
                        case "IC":
                        case "PRFM":
                        case "SYS":
                        case "TLBI":
                        case "TRCIT":
                           return q;
                        case "SYSP":
                           if (var3.length > 4) {
                              return new CW[]{var3[4], var3[5]};
                           }

                           return q;
                        case "TLBIP":
                           if (var3.length > 1) {
                              return new CW[]{var3[1], var3[2]};
                           }

                           return q;
                        case "MRC":
                        case "MRC2":
                           if (var3[2].RF(var4)) {
                              return q;
                           }

                           return new CW[]{var3[2]};
                        case "MRRC":
                        case "MRRC2":
                           return new CW[]{var3[2], var3[3]};
                        case "POP":
                           CW[] var23 = var3[0].oW();
                           CW[] var12 = new CW[var23.length + 1];
                           System.arraycopy(var23, 0, var12, 0, var23.length);
                           var12[var12.length - 1] = var6;
                           return var12;
                        case "PUSH":
                           return new CW[]{var6};
                        default:
                           ArrayList var22 = new ArrayList();
                           q(var22, var5);
                           return (CW[])var22.toArray(new CW[var22.size()]);
                     }
                  }
               } else {
                  int var9 = var2.equals("SVC") ? 4 : 8;
                  CW[] var10 = new CW[var9];

                  for (int var11 = 0; var11 < var9; var11++) {
                     var10[var11] = GC.q(var11, var0.getProcessorMode());
                  }

                  return var10;
               }
            }
         } else {
            return new CW[]{var5, var3[1]};
         }
      } else if (!var2.startsWith("RET") && !var2.startsWith("ERET")) {
         if (Strings.isContainedIn(var2, "AUTIA1716", "AUTIB1716", "PACIA1716", "PACIB1716")) {
            return new CW[]{GC.q(17, var0.getProcessorMode())};
         } else {
            return Strings.isContainedIn(var2, "AUTIASP", "AUTIAZ", "AUTIBSP", "AUTIBZ", "PACIASP", "PACIAZ", "PACIBSP", "PACIBZ", "XPACLRI")
               ? new CW[]{GC.gO(var0.getProcessorMode())}
               : q;
         }
      } else {
         return new CW[]{var1};
      }
   }

   private static CW[] Dw(fA var0) {
      String var1 = var0.Dw().q();
      CW[] var2 = var0.RF();
      CW var3 = var2[0];
      if (Strings.isContainedIn(var1, "VMOV", "VMRS", "FMOV", "MOV", "SMOV", "UMOV", "FJCVTZS") || Strings.startsWith(var1, "FCVT")) {
         ArrayList var8 = new ArrayList();
         q(var8, var3);
         if (!var8.isEmpty() && var2.length > 1) {
            q(var8, var2[1]);
         }

         return (CW[])var8.toArray(new CW[var8.size()]);
      } else if (Strings.startsWith(var1, "FLD", "FST", "VLDM", "VSTM")) {
         return var3.Uv() ? new CW[]{GC.RF(var3.getOperandValue(), var0.getProcessorMode())} : q;
      } else if (var1.equals("VPOP") || var1.equals("VPUSH")) {
         CW var7 = GC.oW(var0.getProcessorMode());
         return new CW[]{var7};
      } else if (Strings.startsWith(var1, "VLD", "VST", "LD", "ST") && var2[var2.length - 1] instanceof wh var4) {
         if (var4.Uv()) {
            long var5 = var4.RF().getOperandValue();
            return new CW[]{GC.RF(var5, var0.getProcessorMode())};
         } else {
            return q;
         }
      } else {
         return q;
      }
   }

   public static CW[] RF(fA var0) {
      ArrayList var1 = new ArrayList();
      q(var1, var0.RF());
      String var2 = var0.Dw().q();
      CW[] var3 = var0.RF();
      if (var3 == null || var3.length == 0) {
         int var4 = var0.getProcessorMode();
         if (Strings.isContainedIn(var2, "AUTIA1716", "AUTIB1716", "PACIA1716", "PACIB1716")) {
            var1.add(GC.q(16, var0.getProcessorMode()));
            var1.add(GC.q(15, var0.getProcessorMode()));
         } else if (Strings.isContainedIn(var2, "AUTIASP", "AUTIBSP")) {
            var1.add(GC.oW(var4));
            var1.add(GC.q(16, var0.getProcessorMode()));
         } else if (Strings.isContainedIn(var2, "PACIASP", "PACIBSP")) {
            var1.add(GC.oW(var4));
         }
      }

      return (CW[])var1.toArray(new CW[var1.size()]);
   }

   private static void q(List var0, CW[] var1) {
      for (CW var5 : var1) {
         q(var0, var5);
      }
   }

   private static void q(List var0, CW var1) {
      if (var1.getOperandType() == 0) {
         int var2 = RegisterUtil.getRegisterGroup(var1.getOperandValue());
         if (var2 == 0 || var2 == 10) {
            var0.add(var1);
         }
      } else if (var1.getOperandType() == 7) {
         for (CW var5 : var1.oW()) {
            q(var0, var5);
         }
      }
   }

   @Override
   public boolean zz() {
      return this.Dw().q().equals("IT");
   }
}
