package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
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
public class jR extends AbstractInstruction implements com.pnfsoftware.jeb.corei.parsers.arm.rQ {
   private static final ILogger A = GlobalLog.getLogger(jR.class);
   public static final Yg[] pC = new Yg[0];
   @SerId(1)
   private int kS;
   @SerId(2)
   private int wS;
   @SerId(3)
   private List UT;
   @SerId(4)
   private NC E;
   @SerId(5)
   private Set sY;
   @SerId(6)
   private long ys = 0L;

   @SerCustomInitPostGraph
   private void NS() {
      if (this.getProcessorMode() == 16 && this.wS < 65535 && this.wS().wS() != null) {
         byte[] var1 = this.kS();
         boolean var2 = this.wS().pC().equals("B") && (var1.length == 2 && var1[0] == 13 || var1.length == 4 && (var1[2] & 16) == 0);
         if (!var2) {
            this.wS |= 262144;
         }
      }

      if (this.wS == 0) {
         try {
            if (this.getProcessorMode() == 16) {
               byte[] var5 = this.kS();
               tz var7;
               if (var5.length == 2) {
                  var7 = new Xc().pC(var5);
               } else {
                  var7 = new Du().pC(var5);
               }

               zj var3 = var7.E(var5);
               this.wS = var3.pC();
            } else if (this.getProcessorMode() == 64) {
               byte[] var6 = this.kS();
               tz var8 = lj.pC(var6);
               zj var9 = var8.E(var6);
               this.wS = var9.pC();
            }
         } catch (ProcessorException var4) {
         }
      }
   }

   public jR(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1) {
      this(var1.getCodeBlock(), var1.wS(), var1.A(), var1.pC(), var1.UT(), var1.getProcessorMode(), var1.oT(), var1.gp());
   }

   public jR(BytesBlock var1, NC var2, Yg[] var3, dW var4, zj var5, int var6, Long var7, List var8) {
      super(var1, var2.E(), var3, var6);
      this.E = var2;
      this.wS = (var5 == null ? zj.pC(65536) : var5).pC();
      if (var4 == null) {
         this.kS = 0;
      } else {
         this.kS = var4.A(var3, var6).getFlags();
      }

      if (var8 != null) {
         this.UT = new ArrayList(var8);
      }

      this.ys = var7 == null ? 0L : var7;
   }

   @Override
   public zj UT() {
      return zj.pC(this.wS);
   }

   @Override
   public NC wS() {
      return this.E;
   }

   @Override
   public dW pC() {
      return hg.pC(this.kS);
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      Yg[] var6 = this.WR();
      ArrayList var7 = new ArrayList(Arrays.asList(var6));
      Yg[] var8 = A(this);
      ArrayList var9 = new ArrayList();

      for (Yg var13 : var8) {
         var9.add(var13);
      }

      for (Yg var21 : var6) {
         if (!var9.contains(var21)) {
            var9.add(var21);
         }
      }

      for (Yg var18 : var9) {
         int var20 = (int)RegisterUtil.getPureId(var18.getOperandValue());
         if (this.getProcessorMode() != 64 || var20 != 33) {
            if (var7.contains(var18)) {
               if (var4.contains(var20)) {
                  var5.add(var20);
               } else {
                  if (this.A(var20)) {
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
      String var16 = this.wS().pC();
      if (Strings.isContainedIn(var16, "HVC", "SMC", "SVC")) {
         var2.addAll(var1);
      }
   }

   private boolean A(int var1) {
      mN var2 = MX.pC(this);
      if (var2 != null && var2.wS().UT() && (int)RegisterUtil.getPureId(var2.wS().kS().getOperandValue()) == var1) {
         return true;
      } else {
         String var3 = this.wS().pC();
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
      if (this.sY == null) {
         HashSet var1 = new HashSet();
         if (this.UT().sY()) {
            var1.add(InstructionFlags.CONDITIONAL_EXEC);
         }

         if (this.getMnemonic().equalsIgnoreCase("UDF")) {
            var1.add(InstructionFlags.INTERRUPT_EXEC);
         }

         if (this.ys()) {
            var1.add(InstructionFlags.UNPREDICTABLE_INSN);
         }

         if (!var1.isEmpty()) {
            this.sY = SerEnumSet.wrap(InstructionFlags.class, EnumSet.copyOf(var1));
         } else {
            this.sY = Collections.emptySet();
         }
      }

      return this.sY;
   }

   @Override
   public boolean canThrow() {
      return false;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      FlowInformation var3 = new FlowInformation();
      if (this.ld() || this.ys()) {
         var3.addTarget(CodePointer.createUnknown(this.pC().pC(this.processorMode, -1L)));
         return var3;
      } else if (!this.isBreakingFlow(this.pC())) {
         return FlowInformation.NONE;
      } else {
         Long var4 = null;

         try {
            var4 = this.pC().compute(this.kS(), var1, this.processorMode, (Yg[])this.operands, null);
         } catch (ProcessorException var6) {
         }

         if (var4 != null) {
            int var5 = this.pC().pC(this.processorMode, var4);
            if ((var4 & 1L) == 1L) {
               var4 = var4 - 1L;
            }

            var3.addTarget(this.pC(var4, var5));
         } else {
            var3.addTarget(CodePointer.createUnknown(this.pC().pC(this.processorMode, -1L)));
         }

         if (!this.UT().E()) {
            var3.addTarget(this.buildNextEntryPoint(var1));
         }

         return var3;
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      if (this.isRoutineCall(this.pC()) && !this.ys()) {
         FlowInformation var3 = new FlowInformation();
         Long var4 = null;

         try {
            var4 = this.pC().compute(this.kS(), var1, this.processorMode, (Yg[])this.operands, null);
         } catch (ProcessorException var6) {
         }

         if (var4 != null) {
            int var5 = this.pC().pC(this.processorMode, var4);
            if ((var4 & 1L) == 1L) {
               var4 = var4 - 1L;
            }

            var3.addTarget(this.pC(var4, var5));
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
   public ICodePointer pC(IMachineContext var1) throws ProcessorException {
      if (this.ys()) {
         throw new ProcessorException("Unable to compute next address for this instruction");
      } else {
         long var2 = var1.getRegisters().getProgramCounter();
         if (!this.isJump(this.pC())) {
            if (this.UT().pC(var1, (Yg[])this.operands, this.processorMode)) {
               for (Yg var7 : (Yg[])this.operands) {
                  if (var7 instanceof KH && ((KH)var7).ld()) {
                     throw new ProcessorException("More than one operand updates the PC: unable to retrieve correct jump address");
                  }
               }
            }

            if ((this.pC().getFlags() & 3840) != 1024) {
               return this.buildNextEntryPoint(var2);
            }
         }

         boolean var8 = this.UT().pC(var1, (Yg[])this.operands, this.processorMode);
         if (var8) {
            Long var9 = this.pC().compute(this.kS(), var2, this.processorMode, (Yg[])this.operands, var1);
            if (var9 != null) {
               int var10 = this.pC().pC(this.processorMode, var9);
               if ((var9 & 1L) == 1L) {
                  var9 = var9 - 1L;
               }

               if (var10 != 16 && (var9 & 2L) != 0L) {
                  ProcessorException var11 = new ProcessorException(
                     Strings.ff("Wrong computation: Expected bit<1> to be 0 for instruction %s", this.toString())
                  );
                  A.catching(var11);
                  JebCoreService.notifySilentExceptionToClient(var11);
               }

               return this.pC(var9, var10);
            } else {
               throw new ProcessorException("Unable to compute next address for this instruction");
            }
         } else {
            return this.buildNextEntryPoint(var2);
         }
      }
   }

   private CodePointer pC(long var1, int var3) {
      return new CodePointer(var3 == 64 ? var1 : var1 & 4294967295L, var3);
   }

   @Override
   public List gp() {
      return this.UT;
   }

   @Override
   public boolean ld() {
      return Strings.isContainedIn(this.wS().pC(), "UDF", "BKPT", "BRK");
   }

   @Override
   public boolean ys() {
      return this.UT != null && !this.UT.isEmpty();
   }

   @Override
   public long oT() {
      return this.ys;
   }

   @Override
   public byte[] kS() {
      return this.getCode(ByteOrder.BIG_ENDIAN);
   }

   @Override
   public boolean E() {
      NC var1 = this.wS();
      return this.pC(var1) || this.getProcessorMode() == 64 && this.vP();
   }

   public boolean pC(NC var1) {
      String var2 = var1.E();
      return !Strings.isBlank(var1.UT()) || var2.startsWith("F") || var2.startsWith("V");
   }

   @Override
   public boolean sY() {
      return (this.oT() & 524288L) != 0L
         || (this.oT() & 131072L) != 0L
         || (this.oT() & 524288L) != 0L
         || (this.oT() & 524288L) != 0L
         || this.getProcessorMode() == 64 && this.xC();
   }

   private boolean vP() {
      return this.getOperands() == null ? false : pC((Yg[])this.getOperands());
   }

   private static boolean pC(Yg[] var0) {
      for (Yg var4 : var0) {
         if (var4.getOperandType() == 0 && var4 instanceof LC && ((LC)var4).A()) {
            return true;
         }

         if (var4.getOperandType() == 7) {
            boolean var5 = pC(var4.E());
            if (var5) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean xC() {
      return this.getOperands() == null ? false : A((Yg[])this.getOperands());
   }

   private static boolean A(Yg[] var0) {
      for (Yg var4 : var0) {
         if (var4.getOperandType() == 0 && var4 instanceof LC && ((LC)var4).kS()) {
            return true;
         }

         if (var4.getOperandType() == 7) {
            boolean var5 = A(var4.E());
            if (var5) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public Yg[] WR() {
      return pC(this);
   }

   public static Yg[] pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      Yg[] var1 = kS(var0);

      for (int var2 = 0; var2 < var1.length; var2++) {
         int var3 = (int)RegisterUtil.getPureId(var1[var2].getOperandValue());
         if (var0.getProcessorMode() == 64 && var3 == 33) {
            if (var1.length == 1) {
               return pC;
            }

            Yg[] var4 = new Yg[var1.length - 1];
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

   private static Yg[] kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      Yg var1 = LC.pC(var0.getProcessorMode());
      String var2 = var0.wS().pC();
      Yg[] var3 = var0.A();
      int var4 = var0.getProcessorMode();
      if (var3 != null && var3.length != 0) {
         Yg var5 = var3[0];
         Yg var6 = LC.sY(var4);
         Yg var7 = LC.ys(var4);
         if (var0.E()) {
            return wS(var0);
         } else if (var0.sY()) {
            return pC;
         } else if ((!Strings.startsWith(var2, "SMLAL", "SMLSL", "SMULL", "UMAAL", "UMLAL", "UMULL") || var4 == 64)
            && !Strings.startsWith(var2, "LDAXP", "LDNP", "LDXP", "CASP", "MRRS")) {
            if (Strings.startsWith(var2, "LDC", "STC") && !Strings.startsWith(var2, "LDCLR", "STCLR")) {
               KH var15 = (KH)var3[2];
               return var15.UT() ? new Yg[]{var15.kS()} : pC;
            } else if (var2.startsWith("RFE")) {
               return var5.UT() ? new Yg[]{LC.A(var5.getOperandValue(), var4)} : pC;
            } else if (var2.startsWith("SRS")) {
               return pC;
            } else if (var2.startsWith("CPY")) {
               KH var14 = (KH)var5;
               KH var19 = (KH)var3[1];
               return new Yg[]{var14.kS(), var19.kS(), LC.A(var3[2].getOperandValue(), var4)};
            } else if (var4 == 64 && Strings.startsWith(var2, "SETP", "SETE", "SETM", "SETG")) {
               KH var13 = (KH)var5;
               return new Yg[]{var13.kS(), LC.A(var3[1].getOperandValue(), var4)};
            } else {
               mN var8 = MX.pC(var0);
               if (var8 != null) {
                  Yg[] var18 = var8.ld();
                  int var21 = (var8.ys() ? var18.length : 0) + (var8.sY() != null ? 1 : 0) + (var8.wS().UT() ? 1 : 0);
                  if (var21 == 0) {
                     return pC;
                  } else if (var8.ys() && var21 == var18.length) {
                     return var8.ld();
                  } else {
                     Yg[] var24 = new Yg[var21];
                     int var25 = 0;
                     if (var8.ys()) {
                        System.arraycopy(var18, 0, var24, 0, var18.length);
                        var25 = var18.length;
                     }

                     if (var8.sY() != null) {
                        var24[var25] = var8.sY();
                        var25++;
                     }

                     if (var8.wS().UT()) {
                        var24[var25] = var8.wS().kS();
                     }

                     return var24;
                  }
               } else if (Strings.startsWith(var2, "RCWCASP", "RCWCLRP", "RCWSCASP", "RCWSCLRP", "RCWSETP", "RCWSSETP", "RCWSWPP", "RCWSSWPP")) {
                  return new Yg[]{var5, var3[1]};
               } else if (Strings.startsWith(var2, "RCWCAS", "RCWSCAS")) {
                  return new Yg[]{var5};
               } else if (Strings.startsWith(var2, "RCWCLR", "RCWSCLR", "RCWSET", "RCWSSET", "RCWSWP", "RCWSSWP")) {
                  return new Yg[]{var3[1]};
               } else if (Strings.startsWith(var2, "STILP")) {
                  KH var17 = (KH)var3[2];
                  return var17.UT() ? new Yg[]{var17.kS()} : pC;
               } else if (Strings.startsWith(var2, "LDIAPP")) {
                  KH var16 = (KH)var3[2];
                  return var16.UT() ? new Yg[]{var5, var3[1], var16.kS()} : new Yg[]{var5, var3[1]};
               } else if (!Strings.isContainedIn(var2, "HVC", "SMC", "SVC")) {
                  if (var4 == 64 && Strings.startsWith(var0.getMnemonic(), "SWP")) {
                     return new Yg[]{var3[1]};
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
                           if (var0.pC().getBranchType() == IInsnEmulator.BranchType.CALL) {
                              return new Yg[]{var1, var7};
                           }

                           return new Yg[]{var1};
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
                           return pC;
                        case "SYSP":
                           if (var3.length > 4) {
                              return new Yg[]{var3[4], var3[5]};
                           }

                           return pC;
                        case "TLBIP":
                           if (var3.length > 1) {
                              return new Yg[]{var3[1], var3[2]};
                           }

                           return pC;
                        case "MRC":
                        case "MRC2":
                           if (var3[2].A(var4)) {
                              return pC;
                           }

                           return new Yg[]{var3[2]};
                        case "MRRC":
                        case "MRRC2":
                           return new Yg[]{var3[2], var3[3]};
                        case "POP":
                           Yg[] var23 = var3[0].E();
                           Yg[] var12 = new Yg[var23.length + 1];
                           System.arraycopy(var23, 0, var12, 0, var23.length);
                           var12[var12.length - 1] = var6;
                           return var12;
                        case "PUSH":
                           return new Yg[]{var6};
                        default:
                           ArrayList var22 = new ArrayList();
                           pC(var22, var5);
                           return (Yg[])var22.toArray(new Yg[var22.size()]);
                     }
                  }
               } else {
                  int var9 = var2.equals("SVC") ? 4 : 8;
                  Yg[] var10 = new Yg[var9];

                  for (int var11 = 0; var11 < var9; var11++) {
                     var10[var11] = LC.pC(var11, var0.getProcessorMode());
                  }

                  return var10;
               }
            }
         } else {
            return new Yg[]{var5, var3[1]};
         }
      } else if (!var2.startsWith("RET") && !var2.startsWith("ERET")) {
         if (Strings.isContainedIn(var2, "AUTIA1716", "AUTIB1716", "PACIA1716", "PACIB1716")) {
            return new Yg[]{LC.pC(17, var0.getProcessorMode())};
         } else {
            return Strings.isContainedIn(var2, "AUTIASP", "AUTIAZ", "AUTIBSP", "AUTIBZ", "PACIASP", "PACIAZ", "PACIBSP", "PACIBZ", "XPACLRI")
               ? new Yg[]{LC.ys(var0.getProcessorMode())}
               : pC;
         }
      } else {
         return new Yg[]{var1};
      }
   }

   private static Yg[] wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      String var1 = var0.wS().pC();
      Yg[] var2 = var0.A();
      Yg var3 = var2[0];
      if (Strings.isContainedIn(var1, "VMOV", "VMRS", "FMOV", "MOV", "SMOV", "UMOV", "FJCVTZS") || Strings.startsWith(var1, "FCVT")) {
         ArrayList var8 = new ArrayList();
         pC(var8, var3);
         if (!var8.isEmpty() && var2.length > 1) {
            pC(var8, var2[1]);
         }

         return (Yg[])var8.toArray(new Yg[var8.size()]);
      } else if (Strings.startsWith(var1, "FLD", "FST", "VLDM", "VSTM")) {
         return var3.UT() ? new Yg[]{LC.A(var3.getOperandValue(), var0.getProcessorMode())} : pC;
      } else if (var1.equals("VPOP") || var1.equals("VPUSH")) {
         Yg var7 = LC.sY(var0.getProcessorMode());
         return new Yg[]{var7};
      } else if (Strings.startsWith(var1, "VLD", "VST", "LD", "ST") && var2[var2.length - 1] instanceof KH var4) {
         if (var4.UT()) {
            long var5 = var4.A().getOperandValue();
            return new Yg[]{LC.A(var5, var0.getProcessorMode())};
         } else {
            return pC;
         }
      } else {
         return pC;
      }
   }

   public static Yg[] A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      ArrayList var1 = new ArrayList();
      pC(var1, var0.A());
      String var2 = var0.wS().pC();
      Yg[] var3 = var0.A();
      if (var3 == null || var3.length == 0) {
         int var4 = var0.getProcessorMode();
         if (Strings.isContainedIn(var2, "AUTIA1716", "AUTIB1716", "PACIA1716", "PACIB1716")) {
            var1.add(LC.pC(16, var0.getProcessorMode()));
            var1.add(LC.pC(15, var0.getProcessorMode()));
         } else if (Strings.isContainedIn(var2, "AUTIASP", "AUTIBSP")) {
            var1.add(LC.sY(var4));
            var1.add(LC.pC(16, var0.getProcessorMode()));
         } else if (Strings.isContainedIn(var2, "PACIASP", "PACIBSP")) {
            var1.add(LC.sY(var4));
         }
      }

      return (Yg[])var1.toArray(new Yg[var1.size()]);
   }

   private static void pC(List var0, Yg[] var1) {
      for (Yg var5 : var1) {
         pC(var0, var5);
      }
   }

   private static void pC(List var0, Yg var1) {
      if (var1.getOperandType() == 0) {
         int var2 = RegisterUtil.getRegisterGroup(var1.getOperandValue());
         if (var2 == 0 || var2 == 10) {
            var0.add(var1);
         }
      } else if (var1.getOperandType() == 7) {
         for (Yg var5 : var1.E()) {
            pC(var0, var5);
         }
      }
   }

   @Override
   public boolean fI() {
      return this.wS().pC().equals("IT");
   }
}
