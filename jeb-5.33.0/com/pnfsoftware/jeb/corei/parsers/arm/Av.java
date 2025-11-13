package com.pnfsoftware.jeb.corei.parsers.arm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.events.ClientNotification;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.PointerLocation;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPatternVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceLocation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.SwitchInformation;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.InstructionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.corei.parsers.x86.wn;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.AH;
import com.pnfsoftware.jebglobal.Cx;
import com.pnfsoftware.jebglobal.Jl;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.MI;
import com.pnfsoftware.jebglobal.Nd;
import com.pnfsoftware.jebglobal.ON;
import com.pnfsoftware.jebglobal.OU;
import com.pnfsoftware.jebglobal.PU;
import com.pnfsoftware.jebglobal.QW;
import com.pnfsoftware.jebglobal.RZ;
import com.pnfsoftware.jebglobal.Td;
import com.pnfsoftware.jebglobal.Tw;
import com.pnfsoftware.jebglobal.Yg;
import com.pnfsoftware.jebglobal.ZM;
import com.pnfsoftware.jebglobal.ZV;
import com.pnfsoftware.jebglobal.a;
import com.pnfsoftware.jebglobal.aK;
import com.pnfsoftware.jebglobal.aus;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.by;
import com.pnfsoftware.jebglobal.ey;
import com.pnfsoftware.jebglobal.kw;
import com.pnfsoftware.jebglobal.uj;
import com.pnfsoftware.jebglobal.vq;
import com.pnfsoftware.jebglobal.zj;
import com.pnfsoftware.jebglobal.zx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

@Ser
public class Av extends AbstractAnalyzerExtension {
   private static final ILogger wS = GlobalLog.getLogger(Av.class);
   private static final String[] UT = new String[]{"AND", "BIC", "IT", "CSET"};
   @SerId(1)
   private TreeSet E;
   @SerId(2)
   private Map sY;
   @SerId(3)
   private Set ys;
   @SerId(4)
   private MI ld;
   @SerId(5)
   private Set gp;
   @SerId(6)
   private Map oT;
   @SerId(7)
   private Set fI;
   @SerId(8)
   private TreeSet WR;
   @SerId(9)
   private TreeMap NS;
   private static boolean vP = false;
   @SerTransient
   private Map xC;
   @SerTransient
   private Map ED;
   @SerTransient
   private Map Sc;
   @SerTransient
   private Map ah;
   @SerTransient
   private by eP;
   @SerTransient
   private Nd UO;
   @SerTransient
   Cx pC;
   @SerTransient
   AH A;
   @SerTransient
   public wn.Av kS;

   public Av() {
      this.A();
   }

   @SerCustomInitPostGraph
   private void A() {
      if (this.E == null) {
         this.E = new TreeSet();
      }

      if (this.WR == null) {
         this.WR = new TreeSet();
      }

      if (this.sY == null) {
         this.sY = new HashMap();
      }

      if (this.ys == null) {
         this.ys = new HashSet();
      }

      if (this.oT == null) {
         this.oT = new HashMap();
         if (!this.ys.isEmpty()) {
            for (long var2 : this.ys) {
               this.oT.put(var2, -1);
            }
         }
      }

      if (this.ld == null && this.gca != null) {
         this.ld = new MI(this.gca.getProcessor());
      }

      if (this.gp == null) {
         this.gp = new HashSet();
      }

      if (this.fI == null) {
         this.fI = new HashSet();
      }

      if (this.NS == null) {
         this.NS = new TreeMap();
      }

      if (this.xC == null) {
         this.xC = new HashMap();
      }

      if (this.ED == null) {
         this.ED = new HashMap();
      }

      if (this.Sc == null) {
         this.Sc = new HashMap();
      }

      if (this.ah == null) {
         this.ah = new HashMap();
      }

      if (this.kS == null && this.gca != null) {
         this.kS = ((a)this.gca).wS().gp();
      }
   }

   @Override
   public void initialize(INativeCodeAnalyzer var1) {
      super.initialize(var1);
      this.ld = new MI(this.gca.getProcessor());
   }

   @Override
   protected void initializePaddingPatterns(BinaryPatternVerifier var1) {
      this.pC = new Cx(this.gca, var1);
   }

   @Override
   public ChainedOperationResult getPossiblePaddingSize(long var1, long var3) {
      long var5 = this.pC.pC(var1);
      return var5 == 0L ? new ChainedOperationResult(0L, ChainedOperationResult.ContinuationStatus.CONTINUE) : new ChainedOperationResult(var5);
   }

   @Override
   protected void initializeProloguePatterns(BinaryPatternVerifier var1) {
      this.UO = new Nd(this.gca, this, var1);
   }

   public Nd pC() {
      return this.UO;
   }

   @Override
   public ChainedOperationResult getPrologueLooking(long var1, long var3) {
      return this.pC(var1, var3, true, true);
   }

   public ChainedOperationResult pC(long var1, long var3, boolean var5) {
      return this.pC(var1, var3, var5, false);
   }

   public ChainedOperationResult pC(long var1, long var3, boolean var5, boolean var6) {
      ChainedOperationResult var7 = ChainedOperationResult.stop();
      if (var1 % 2L == 0L && (var1 % 4L == 0L || this.gca.getProcessor().getMode() != 64)) {
         if (this.kS(var1, null)) {
            return var7;
         } else {
            CodePointer var8 = this.UO.pC(var1, 0, Nd.Av.kS);
            if (var8 != null) {
               if (var8.getMode() == 32) {
                  if (!aK.pC(this.gca.getMemory(), var1)) {
                     var8 = null;
                  }
               } else if (var8.getMode() == 16) {
                  long var9 = var1 & -4L;
                  if ((var1 & 2L) != 0L && this.A(var9)) {
                     CodePointer var11 = this.UO.pC(var1 + 2L, 0, Nd.Av.kS);
                     if (var11 != null) {
                        return var7;
                     }
                  }
               }
            }

            if (var8 != null) {
               ZM var12 = new ZM(this.gca, this, true, true);
               if (var12.pC(var8.getMode(), var8.getAddress())) {
                  this.WR.add(var8.getAddress());
                  if (var6) {
                     this.gca.enqueuePointerForAnalysis(var8, 1, 64);
                  }

                  return new ChainedOperationResult(var8);
               }
            }

            return var5 ? this.ys(var1) : var7;
         }
      } else {
         return var7;
      }
   }

   private ChainedOperationResult ys(long var1) {
      ChainedOperationResult var3 = ChainedOperationResult.stop();
      if (var1 % 4L == 0L) {
         CodePointer var4 = this.UO.pC(var1);
         if (var4 != null) {
            ZM var5 = new ZM(this.gca, this, true, true);
            if (var5.pC(var4.getMode(), var4.getAddress())) {
               return new ChainedOperationResult(var4);
            }
         }
      }

      return var3;
   }

   @Override
   public ChainedOperationResult verifyGapRoutineCandidate(long var1) {
      if (this.kS(var1, null)) {
         return ChainedOperationResult.FALSE_STOP;
      } else if (this.pC(var1, var1 + 4L, true).getResult() != null) {
         return ChainedOperationResult.TRUE_CONTINUE;
      } else {
         if ((var1 & 2L) != 0L) {
            CodePointer var3 = (CodePointer)this.pC(var1 + 2L, var1 + 6L, true).getResult();
            if (var3 != null && var3.getMode() == 32) {
               return ChainedOperationResult.FALSE_STOP;
            }
         }

         long var11 = var1 & -4L;
         if (this.A(var11)) {
            return ChainedOperationResult.FALSE_STOP;
         } else {
            StringEncoding[] var5 = new StringEncoding[1];
            String var6 = DataStringUtil.getStringAt(this.gca.getMemory(), var1, 8, -1, var5);
            if (var6 != null && (var5[0] == StringEncoding.ASCII_ZERO || var5[0] == StringEncoding.UTF8_ZERO || var5[0] == StringEncoding.UTF8_NONZERO)) {
               ZM var7 = new ZM(this.gca, this, true, true);
               if (!var7.pC(this.gca.getProcessor().getDefaultMode() == 64 ? 64 : 0, var1)) {
                  int var13 = var6.getBytes(var5[0].getCharset()).length;

                  for (long var14 = 0L; var14 < var13; var14 += 4L) {
                     this.pC(var1 + var14);
                  }

                  this.pC(var1 + var13);
                  return ChainedOperationResult.FALSE_STOP;
               }
            }

            ZM var12 = new ZM(this.gca, this, true, false);
            Pointer var8 = var12.pC(var1, this.gca.getProcessor().getDefaultMode() == 64 ? 64 : 0, 2048, true);
            if (var8 == null) {
               return ChainedOperationResult.FALSE_STOP;
            } else {
               int var9 = var8 instanceof CodePointer ? ((CodePointer)var8).getMode() : var8.getSize();
               ZM.Av var10 = var12.A(var1, var9);
               if (var10 == ZM.Av.pC || !(var8 instanceof CodePointer)) {
                  this.ED.put(var1, var9);
               }

               if (var10 == ZM.Av.A) {
                  this.Sc.put(var1, var9);
               }

               if (var9 == 16) {
                  this.xC.put(var1, Arrays.asList(var9));
               }

               return ChainedOperationResult.TRUE_CONTINUE;
            }
         }
      }
   }

   public ICodePointer pC(long var1, int var3) {
      ZM var4 = new ZM(this.gca, this, true, true);
      rQ var5 = var4.pC(var1, var3);
      if (var5 == null) {
         return null;
      } else {
         IFlowInformation var6 = var5.getBreakingFlow(var1);
         if (var6 == null || !var6.isBroken() || var6.getTargets().size() != 1) {
            var6 = var5.getRoutineCall(var1);
            if (var6 == null || !var6.isBroken() || var6.getTargets().size() != 1) {
               return null;
            }
         }

         return (ICodePointer)var6.getTargets().get(0);
      }
   }

   @Override
   public ChainedOperationResult getProbableEntryPoints(long var1, long var3) {
      ArrayList var5 = new ArrayList();
      IProcessor var6 = this.gca.getProcessor();
      CodePointer var7 = var6.createEntryPoint(var1);
      if (this.Sc.containsKey(var1)) {
         Object[] var29 = new Object[]{var1};
         var7 = var6.createEntryPoint(var1, (Integer)this.Sc.get(var1));
         var5.add(var7);
         return new ChainedOperationResult(var5);
      } else if (this.ED.containsKey(var1)) {
         Object[] var28 = new Object[]{var1};
         int var17 = (Integer)this.ED.get(var1);
         ICodePointer var19 = this.pC(var1, var17);
         if (var19 != null) {
            long var24 = var19.getAddress();
            INativeContinuousItem var26 = this.gca.getModel().getItemOver(var24);
            if (var26 != null) {
               if (!(var26 instanceof INativeInstructionItem) || var26.getMemoryAddress() != var24) {
                  var5.add(null);
                  return new ChainedOperationResult(var5);
               }
            } else if (this.pC(var24, var24 + 4L, true).getResult() != null) {
               CodePointer var27 = new CodePointer(var24, var19.getMode());
               this.gca.enqueuePointerForAnalysis(var27);
               var5.add(var27);
            }
         }

         this.gca.enqueuePointerForAnalysis(new CodePointer(var1, var17), 0, 1);
         if (var5.isEmpty()) {
            var5.add(null);
         }

         return new ChainedOperationResult(var5);
      } else if (this.gca.getProcessor().getDefaultMode() == 64) {
         var5.add(var7);
         return new ChainedOperationResult(var5);
      } else {
         INativeInstructionItem var8 = this.pC(0, var1, 10);
         if (var8 != null) {
            INativeInstructionItem var9 = this.pC(1, var1, 10);
            if (var9 != null) {
               long var10 = var8.getMemoryAddress() - var9.getMemoryAddressEnd();
               if (var10 <= 4L) {
                  CodePointer var12;
                  if (var10 == 2L) {
                     var12 = this.gca.getProcessor().createEntryPoint(var1, 16);
                  } else {
                     List var13 = (List)this.xC.get(var1);
                     if (var13 != null) {
                        var12 = this.gca.getProcessor().createEntryPoint(var1, (Integer)var13.get(0));
                     } else {
                        var12 = this.gca.getProcessor().createEntryPoint(var1, 0);
                     }
                  }

                  Object[] var10000 = new Object[]{var1};
                  this.gca.enqueuePointerForAnalysis(var12, 0, 1);
                  var5.add(null);
                  return new ChainedOperationResult(var5);
               }
            }
         }

         List var18 = (List)this.xC.get(var1);
         if (var18 != null) {
            for (Integer var25 : var18) {
               var5.add(var6.createEntryPoint(var1, var25));
            }

            return new ChainedOperationResult(var5);
         } else if (var1 % 4L == 0L) {
            try {
               if (var3 < var1 + 4L) {
                  if (var3 >= var1 + 2L) {
                     var7.setMode(16);
                     var5.add(var7);
                  }

                  return new ChainedOperationResult(var5);
               }

               if (var8 != null) {
                  INativeInstructionItem var21 = this.pC(1, var1, 10);
                  if (var21 != null && var8.getInstruction().getProcessorMode() == var21.getInstruction().getProcessorMode()) {
                     var5.add(new CodePointer(var1, var8.getInstruction().getProcessorMode()));
                  }
               }

               if (var5.size() == 0) {
                  int var22 = this.gca.getMemory().readInt(var1, ((cq)this.gca.getProcessor()).pC());
                  if ((var22 & -268435456) == -536870912) {
                     var5.add(var7);
                  } else if ((var22 & -268435456) == -268435456 && var3 >= var1 + 8L) {
                     int var11 = this.gca.getMemory().readInt(var1 + 4L, ((cq)this.gca.getProcessor()).pC());
                     if ((var11 & -268435456) == -268435456) {
                        var5.add(var7);
                     }
                  }

                  var5.add(new CodePointer(var1, 16));
               }

               if (var5.size() == 1) {
                  if (((CodePointer)var5.get(0)).getMode() == 16) {
                     var5.add(new CodePointer(var1, 32));
                  } else if (((CodePointer)var5.get(0)).getMode() == 32) {
                     var5.add(new CodePointer(var1, 16));
                  }
               }
            } catch (MemoryException var14) {
               var5.clear();
            }

            return new ChainedOperationResult(var5);
         } else {
            try {
               short var20 = this.gca.getMemory().readShort(var1);
               if (var20 == -1 || var20 == 0) {
                  var7 = var6.createEntryPoint(var1 + 2L);
               }
            } catch (MemoryException var15) {
               wS.catchingSilent(var15);
            }

            var5.add(var7);
            var7.setMode(16);
            return new ChainedOperationResult(Arrays.asList(var7));
         }
      }
   }

   private INativeInstructionItem pC(int var1, long var2, int var4) {
      INativeContinuousItem var5 = var1 == 0 ? this.gca.getModel().getNextItem(var2) : this.gca.getModel().getPreviousItem(var2);

      for (int var6 = 0; var6 < var4 && var5 != null && !(var5 instanceof INativeInstructionItem); var6++) {
         var5 = var1 == 0 ? this.gca.getModel().getNextItem(var5.getMemoryAddress()) : this.gca.getModel().getPreviousItem(var5.getMemoryAddress());
      }

      return var5 instanceof INativeInstructionItem ? (INativeInstructionItem)var5 : null;
   }

   public ChainedOperationResult pC(long var1, rQ var3, List var4) {
      boolean var5 = false;
      Yg[] var6 = var3.A();

      for (Yg var10 : var6) {
         if (var10 != null) {
            if (!InstructionUtil.isAddressOperand(var10)) {
               var10 = (Yg)var10.merge(var1);
               if (var10 == null || !InstructionUtil.isAddressOperand(var10)) {
                  continue;
               }
            }

            boolean var11 = true;
            int var12 = 4;
            long var13 = var10.getOperandValue(var1);
            byte var15 = 0;
            String var16 = var3.wS().pC();
            switch (var16) {
               case "ADRP":
               case "ADR":
                  return ChainedOperationResult.TRUE_CONTINUE;
               case "PLI":
                  var12 = var10.getOperandBitsize() / 8;
                  var15 = 1;
                  break;
               case "LDRD":
               case "STRD":
                  var12 = 8;
                  var15 = 2;
                  break;
               case "LDR":
               case "STR":
                  RegisterUtil.getRegisterGroup(var6[0].getOperandValue());
                  RegisterUtil.getRegisterBitsize(var6[0].getOperandValue());
                  var12 = var6[0].getOperandBitsize() / 8;
                  var15 = 2;
                  break;
               case "VSTR":
               case "VLDR":
                  var12 = var6[0].getOperandBitsize() / 8;
                  var15 = 2;
                  break;
               case "LDRH":
               case "LDRSH":
               case "STRH":
                  var12 = 2;
                  var15 = 2;
                  break;
               case "LDRB":
               case "LDRSB":
               case "STRB":
                  var12 = 1;
                  var15 = 2;
                  break;
               case "PLD":
               case "PLDW":
                  var12 = 0;
                  var15 = 2;
            }

            if (var15 == 2) {
               this.pC(var13);
            }

            if (var11) {
               AH.pC(var13, var4, var12, var15);
               var5 = true;
            }
         }
      }

      return var5 ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.TRUE_CONTINUE;
   }

   public void pC(long var1) {
      this.E.add(var1);
   }

   public boolean A(long var1) {
      return this.E.contains(var1) || this.fI.contains(var1);
   }

   public void pC(Long var1, Long var2) {
      Object var3 = (Set)this.ah.get(var1);
      if (var3 == null) {
         var3 = new HashSet();
         this.ah.put(var1, var3);
      }

      var3.add(var2);
   }

   public boolean kS(long var1) {
      return this.fI.contains(var1);
   }

   public boolean wS(long var1) {
      return this.WR.contains(var1);
   }

   public void UT(long var1) {
      this.WR.remove(var1);
   }

   @Override
   public ChainedOperationResult determinePotentialPointersInProtoBlock(IBasicBlockSkeleton var1, List var2) {
      if (this.A == null) {
         this.A = new AH(this.gca, this);
      }

      this.A.pC(var1, var2);

      for (PointerLocation var4 : var2) {
         if (var4.getPointer().getType() == 2) {
            this.pC(var4.getPointer().getAddress());
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult isNonReturningRoutine(INativeMethodItem var1) {
      if (var1.getData() != null) {
         if (var1.getMemoryAddress() != null && this.oT(var1.getMemoryAddress())) {
            return ChainedOperationResult.TRUE_STOP;
         }

         for (BasicBlock var4 : var1.getData().getCFG().getExitBlocks()) {
            AddressableInstruction var5 = var4.getBranchingInstruction2();
            if (var5 != null && PU.sY((rQ)var5.getInstruction())) {
               return ChainedOperationResult.FALSE_STOP;
            }
         }

         if (this.fI(var1.getMemoryAddress())) {
            return ChainedOperationResult.TRUE_STOP;
         }
      }

      return ChainedOperationResult.ignore();
   }

   public ChainedOperationResult pC(long var1, rQ var3) {
      if (var3.pC().isPCUpdated() && var3.A().length == 1 && var3.A()[0].A(this.gca.getProcessor().getMode())) {
         return ChainedOperationResult.FALSE_STOP;
      } else {
         ChainedOperationResult var4 = this.A(var1, var3);
         return var4 == ChainedOperationResult.FALSE_STOP ? this.sY(var1, var3) : var4;
      }
   }

   public ChainedOperationResult A(long var1, rQ var3) {
      if ((var3.pC().isPCUpdated() || (var3.pC().getFlags() & 2816) != 0) && var3.getRoutineCall(var1).isBroken() && !var3.UT().sY()) {
         if (this.kS(var1, var3)) {
            INativeContinuousItem var15 = this.gca.getModel().getItemAt(var1);
            if (var15 instanceof aus) {
               InstructionHints var5 = ((aus)var15).getHints(true);
               var5.setFakeCall(true);
            }

            return ChainedOperationResult.TRUE_STOP;
         }

         if (!this.oT.isEmpty()) {
            Long var4 = this.wS(var1, var3);
            if (var4 != null && this.oT(var4)) {
               return ChainedOperationResult.TRUE_STOP;
            }
         }

         long var14 = var1 + var3.getSize();
         if (this.A(var14)) {
            this.UT(var1, var3);
            return ChainedOperationResult.TRUE_STOP;
         }

         Long var6 = (Long)this.E.higher(var14);
         if (var6 != null && var6 <= var14 + 12L) {
            int var16 = (int)(var6 - var14);
            if (var16 >= 2 && (var3.getProcessorMode() == 16 || var16 >= 4)) {
               int var17 = 0;

               while (var17 < var16 && var16 >= 2 && (var3.getProcessorMode() == 16 || var16 >= 4)) {
                  rQ var18 = PU.pC(this.gca, var14 + var17, var3.getProcessorMode());
                  if (var18 == null) {
                     this.UT(var1, var3);
                     return this.A(var14, var16);
                  }

                  if (var18.pC().isPCUpdated()) {
                     IFlowInformation var19 = var18.getBreakingFlow(var14 + var17);
                     if (!var19.isBroken()) {
                        var19 = var18.getRoutineCall(var14 + var17);
                     }

                     boolean var11 = false;
                     if (var19.isBroken()) {
                        for (ICodePointer var13 : var19.getTargets()) {
                           if (!var13.isUnknownAddress() && !this.gca.getAnalysisRanges().contains(var13.getAddress())) {
                              var11 = true;
                              break;
                           }
                        }
                     }

                     if (!var11 && !var18.UT().sY()) {
                        return ChainedOperationResult.FALSE_STOP;
                     }
                  }

                  var17 += var18.getSize();
               }

               this.UT(var1, var3);
               return this.A(var14, var16);
            }

            return ChainedOperationResult.TRUE_STOP;
         }

         ZM var7 = new ZM(this.gca, this, false, false);
         if (!var7.pC(var3.getProcessorMode(), var14)) {
            this.UT(var1, var3);
            return ChainedOperationResult.TRUE_STOP;
         }

         auu var8 = ((Tw)this.gca.getModel()).E(var14);
         if (var8 != null && this.UO.pC(var8)) {
            this.UT(var1, var3);
            return ChainedOperationResult.TRUE_STOP;
         }

         Boolean var9 = this.ld(var14);
         if (Boolean.TRUE.equals(var9)) {
            this.UT(var1, var3);
            return ChainedOperationResult.TRUE_STOP;
         }

         if (var9 == null) {
            Long var10 = this.wS(var1, var3);
            if (var10 != null && !this.gp(var10)) {
               this.pC(var1, var3, -2);
               return ChainedOperationResult.TRUE_STOP;
            }
         }
      }

      this.E(var1, var3);
      return ChainedOperationResult.FALSE_STOP;
   }

   public void A(Long var1, Long var2) {
      this.NS.put(var1, var2);
   }

   private boolean kS(long var1, rQ var3) {
      Entry var4 = this.NS.lowerEntry(var1);
      if (var4 == null) {
         return false;
      } else if (var1 > (Long)var4.getValue()) {
         return false;
      } else if (var3 == null) {
         return true;
      } else {
         IFlowInformation var5 = var3.getRoutineCall(var1);
         if (!var5.isBrokenKnown()) {
            return false;
         } else {
            long var6 = ((ICodePointer)var5.getTargets().get(0)).getAddress();
            return (Long)var4.getKey() < var6 && var6 < (Long)var4.getValue();
         }
      }
   }

   public boolean E(long var1) {
      return this.UO.pC(var1, 0, Nd.Av.kS) != null;
   }

   public boolean sY(long var1) {
      return this.UO.pC(var1, 0) != null;
   }

   private Boolean ld(long var1) {
      int var3 = this.gca.getProcessor().getMode();
      int var4 = this.UO.pC(var1, var1 + 8L, var3);
      if (var4 == 0) {
         return this.sY(var1) ? null : false;
      } else {
         if (var3 == 64) {
            try {
               if (this.gca.getMemory().readInt(var1) == 0) {
                  return true;
               }

               if (var4 > 4 && this.gca.getMemory().readInt(var1 + 4L) == 0) {
                  return true;
               }
            } catch (MemoryException var11) {
               return false;
            }
         }

         long var5 = var1 + var4;
         if (this.sY(var5)) {
            return true;
         } else {
            AH var7 = new AH(this.gca, this);
            ArrayList var8 = new ArrayList();

            try {
               if (var7.pC(var5, var5 + 4L, var8, 4, 0, true) && !var8.isEmpty()) {
                  return true;
               }
            } catch (MemoryException var10) {
               wS.catchingSilent(var10);
            }

            return false;
         }
      }
   }

   private Long wS(long var1, rQ var3) {
      if (PU.UT(var3)) {
         if (var3.A() != null && var3.A().length != 0) {
            Yg var4 = var3.A()[0];
            switch (var4.getOperandType()) {
               case 1:
               case 2:
               case 3:
                  return var4.getOperandValue(var1);
               default:
                  return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private void UT(long var1, rQ var3) {
      this.pC(var1, var3, -1);
   }

   private void pC(long var1, rQ var3, int var4) {
      Long var5 = this.wS(var1, var3);
      if (var5 != null) {
         this.oT.put(var5, var4);
      }
   }

   private void E(long var1, rQ var3) {
      Long var4 = this.wS(var1, var3);
      if (var4 != null) {
         Integer var5 = (Integer)this.oT.get(var4);
         if (var5 == null) {
            var5 = 0;
         } else if (var5 == -1 || var5 == -2) {
            return;
         }

         this.oT.put(var4, var5 + 1);
      }
   }

   private boolean gp(long var1) {
      if (this.gca.getModel() instanceof Tw) {
         auu var3 = ((Tw)this.gca.getModel()).E(var1);
         if (var3 != null) {
            Boolean var4 = var3.getNonReturning();
            if (var4 != null) {
               this.oT.remove(var1);
               return var4;
            }
         }
      }

      Integer var5 = (Integer)this.oT.get(var1);
      return var5 != null && var5 > 1;
   }

   private boolean oT(long var1) {
      if (this.gca.getModel() instanceof Tw) {
         auu var3 = ((Tw)this.gca.getModel()).E(var1);
         if (var3 != null) {
            Boolean var4 = var3.getNonReturning();
            if (var4 != null) {
               this.oT.remove(var1);
               return var4;
            }
         }
      }

      Integer var5 = (Integer)this.oT.get(var1);
      return var5 != null && var5 == -1;
   }

   private boolean fI(long var1) {
      Integer var3 = (Integer)this.oT.get(var1);
      return var3 != null && var3 == -2;
   }

   private ChainedOperationResult A(long var1, int var3) {
      this.pC(var1);
      int var4 = 0;

      while (var4 < var3) {
         int var5 = Math.min(4, var3 - var4);
         this.gca.enqueuePointerForAnalysis(new Pointer(var1 + var4, var5, 2));
         var4 += var5;
      }

      return ChainedOperationResult.TRUE_STOP;
   }

   private ChainedOperationResult sY(long var1, rQ var3) {
      if (!var3.pC().isPCUpdated()) {
         return ChainedOperationResult.FALSE_STOP;
      } else if (var3.A() != null && var3.A().length != 0) {
         QW var4 = (QW)this.sY.get(var1);
         return var4 != null && var4.A() ? ChainedOperationResult.TRUE_STOP : this.ys(var1, var3);
      } else {
         return ChainedOperationResult.FALSE_STOP;
      }
   }

   private ChainedOperationResult ys(long var1, rQ var3) {
      if (InstructionUtil.isAddressOperand(var3.A()[0])) {
         IFlowInformation var4 = var3.getRoutineCall(var1);
         if (var4.isBroken() && var4.getTargets().size() == 1) {
            long var5 = ((ICodePointer)var4.getTargets().get(0)).getAddress();
            long var7 = var1 + var3.getSize();
            QW var9 = (QW)this.sY.get(var5);
            this.ld.wS();
            if (var9 == null) {
               var9 = vq.pC(this.gca, this.ld, var5, ((ICodePointer)var4.getTargets().get(0)).getMode());
               if (var9 == null) {
                  var9 = RZ.kS();
               }

               this.sY.put(var5, var9);
            }

            if (var9.pC()) {
               this.pC(var7);
               this.sY.put(var1, var9.pC(this.ld, var1, var3, false));
            }

            this.ld.UT();
            return var9.A() ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.FALSE_STOP;
         }
      }

      return ChainedOperationResult.FALSE_STOP;
   }

   public ChainedOperationResult pC(long var1, rQ var3, IBasicBlockSkeleton var4) {
      if (this.kS == wn.Av.pC) {
         return ChainedOperationResult.FALSE_STOP;
      } else if (var3.A() != null && var3.A().length != 0) {
         QW var5 = (QW)this.sY.get(var1);
         if (var5 != null && var5.A()) {
            return ChainedOperationResult.TRUE_STOP;
         } else {
            this.A(var1, var3, var4);
            Yg var6 = var3.A()[0];
            String var7 = var3.wS().pC();
            if (this.pC(var3, var7, var6)) {
               return ChainedOperationResult.FALSE_STOP;
            } else {
               long var8 = var4.getFirstAddress();
               boolean var10 = false;
               IFlowInformation var11 = var3.getBreakingFlow(var1);
               if (var11 != null && var11.isBroken()) {
                  for (ICodePointer var13 : var11.getTargets()) {
                     if (var13.isUnknownAddress()) {
                        var10 = true;
                        break;
                     }
                  }

                  if (this.pC(var8, var3, var7, var6)) {
                     var10 = true;
                  }

                  if (!var10) {
                     return ChainedOperationResult.FALSE_STOP;
                  } else if (this.pC(var8, var3, var7, var6)) {
                     return this.pC(var1, var3, var6);
                  } else if (this.gp.contains(var8)) {
                     return ChainedOperationResult.TRUE_STOP;
                  } else {
                     CodePointer var15 = this.UO.pC().pC(((ON)var4).pC(), false, false);
                     if (var15 != null) {
                        return ChainedOperationResult.FALSE_STOP;
                     } else if (var7.equals("LDR")) {
                        if (!var6.A(this.gca.getProcessor().getMode())) {
                           return ChainedOperationResult.FALSE_STOP;
                        } else {
                           Yg var17 = var3.A()[1];
                           if (var17.getOperandType() != 7) {
                              return ChainedOperationResult.FALSE_STOP;
                           } else {
                              Yg[] var14 = var17.E();
                              return var14.length == 2 && var14[1] instanceof ZV ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.FALSE_STOP;
                           }
                        }
                     } else if (var7.equals("ADD")) {
                        boolean var16 = var6.A(this.gca.getProcessor().getMode()) && (var3.A().length == 2 || var3.A()[1].A(this.gca.getProcessor().getMode()));
                        return ChainedOperationResult.stop(var16);
                     } else if (var7.equals("MOV")) {
                        return var6.A(this.gca.getProcessor().getMode()) && !this.pC(var4, var3.A()[1])
                           ? ChainedOperationResult.TRUE_STOP
                           : ChainedOperationResult.FALSE_STOP;
                     } else if (!var7.equals("TBB") && !var7.equals("TBH")) {
                        if ((var7.equals("BR") || var7.equals("BLR")) && !this.pC(var4, var6)) {
                           return ChainedOperationResult.TRUE_STOP;
                        } else {
                           return vP ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.FALSE_STOP;
                        }
                     } else {
                        return ChainedOperationResult.TRUE_STOP;
                     }
                  }
               } else {
                  return this.ys(var1, var3);
               }
            }
         }
      } else {
         return ChainedOperationResult.FALSE_STOP;
      }
   }

   private ChainedOperationResult pC(long var1, rQ var3, Yg var4) {
      boolean var5 = pC(var3);
      long var6 = var5 ? var1 + var3.getSize() : var4.getOperandValue(var1);
      rQ var8 = PU.pC(this.gca, var6);

      for (ArrayList var9 = new ArrayList(); var8 != null && !var8.pC().isPCUpdated(); var8 = PU.pC(this.gca, var6)) {
         var9.add(var8);
         var6 += var8.getSize();
      }

      if (var8 != null && var8.A() != null && var8.A().length != 0) {
         Yg var10 = var8.A()[0];
         if (var8.wS().pC().equalsIgnoreCase("bl") && (var10.getOperandType() == 2 || var10.getOperandType() == 3)) {
            long var11 = var10.getOperandValue(var6);
            QW var13 = (QW)this.sY.get(var11);
            this.ld.wS();
            if (var13 == null) {
               var13 = uj.pC(this.gca, this.ld, var11);
               if (var13 == null) {
                  var13 = RZ.kS();
               }

               this.sY.put(var11, var13);
            }

            if (var13.pC()) {
               this.pC(var6 + var8.getSize());
               this.sY.put(var6, var13.pC(this.ld, var6, var8, var5));
            }

            this.ld.UT();
         }

         return ChainedOperationResult.FALSE_STOP;
      } else {
         return ChainedOperationResult.FALSE_STOP;
      }
   }

   private void A(long var1, rQ var3, IBasicBlockSkeleton var4) {
      Integer var5 = Td.pC(var3);
      Long var6 = null;
      if (var5 != null) {
         if (PU.pC(var5)) {
            var6 = var1 + var3.getSize();
         } else if (PU.A(var5) && var3.A().length == 1 && InstructionUtil.isAddressOperand(var3.A()[0])) {
            var6 = var3.A()[0].getOperandValue(var1);
         }

         if (var6 != null) {
            for (int var7 = var4.getInsntructions().size() - 2; var7 >= 0; var7--) {
               rQ var8 = (rQ)var4.getInsntructions().get(var7);
               if (var8.wS().pC().equals("CMP")) {
                  if (var8.A()[1].getOperandType() != 1 || var8.A()[1].getOperandValue() <= 1L) {
                     break;
                  }

                  this.gp.add(var6);
               }

               if (var8.wS().kS()) {
                  break;
               }
            }
         }
      }
   }

   private boolean pC(long var1, rQ var3, String var4, Yg var5) {
      return var4.equals("B") && InstructionUtil.isAddressOperand(var5) && (Td.A(var3) || this.gp.contains(var1));
   }

   private boolean pC(rQ var1, String var2, Yg var3) {
      switch (var2) {
         case "ADC":
         case "AND":
         case "ASR":
         case "BIC":
         case "EOR":
         case "LSL":
         case "LSR":
         case "MVN":
         case "ORN":
         case "ORR":
         case "ROR":
         case "RRX":
         case "RSB":
         case "RSC":
         case "SBC":
         case "POP":
         case "LDM":
            return true;
         case "BX":
            if (LC.pC(var3, this.gca.getProcessor().getMode()) || var3.A(this.gca.getProcessor().getMode())) {
               return true;
            }
            break;
         case "MOV":
            if (LC.pC(var1.A()[1], this.gca.getProcessor().getMode())) {
               return true;
            }
      }

      return false;
   }

   private static boolean pC(rQ var0) {
      zj var1 = var0.UT();
      if (!var1.sY()) {
         return false;
      } else {
         Integer var2 = var1.A();
         return var2 != 9 && var2 != 13 && var2 != 11 && var2 != 3 && var2 != 4;
      }
   }

   private boolean pC(IBasicBlockSkeleton var1, Yg var2) {
      Set var3 = this.gca.getModel().getReferenceManager().getReferencesTo(var1.getFirstAddress());
      boolean var4 = false;
      if (var3 == null || var3.isEmpty()) {
         for (rQ var6 : var1) {
            if (Strings.isContainedIn(var6.wS().pC(), UT)) {
               var4 = true;
               break;
            }
         }

         if (!var4) {
            return true;
         }
      }

      ArrayList var15 = new ArrayList();
      var15.add((int)RegisterUtil.getPureId(var2.getOperandValue()));
      List var16 = var1.getInsntructions();
      boolean var7 = false;
      int var8 = this.gca.getProcessor().getMode();
      Integer var9 = (int)RegisterUtil.getPureId(LC.pC(var8).getOperandValue());

      for (int var10 = var16.size() - 2; var10 >= 0; var10--) {
         rQ var11 = (rQ)var16.get(var10);
         ArrayList var12 = new ArrayList();
         ArrayList var13 = new ArrayList();
         var11.getDefUse(var12, var13);
         boolean var14 = var15.removeAll(var12);
         if (var14) {
            if (!var11.UT().E()) {
               return false;
            }

            if (Strings.isContainedIn(var11.wS().pC(), UT)) {
               return false;
            }

            var7 = true;
            var13.remove(var9);
            var15.addAll(var13);
         }

         if (var15.isEmpty()) {
            return true;
         }
      }

      if (!this.wS(var1.getFirstAddress()) && !this.E(var1.getFirstAddress())) {
         var7 |= var4;
         return !var7 ? true : !var7;
      } else {
         return true;
      }
   }

   @Override
   public ChainedOperationResult determineSwitchInformation(long var1, IBasicBlockSkeleton var3, List var4) {
      HashMap var5 = new HashMap();
      HashMap var6 = new HashMap();

      for (IBasicBlockSkeleton var8 : var4) {
         var5.put(var8.getFirstAddress(), new ArrayList(var8.getInsntructions()));
         var6.put(var8.getFirstAddress(), new ArrayList(var8.getDstOffsets()));
      }

      return this.pC(var1, var3.getFirstAddress(), var5, var6);
   }

   public ChainedOperationResult pC(long var1, long var3, Map var5, Map var6) {
      SwitchInformation var7 = new SwitchInformation();
      QW var8 = (QW)this.sY.get(var1);
      zx var9;
      if (var8 != null) {
         var9 = var8.pC(this.gca, this, this.ld);
      } else {
         var9 = RZ.kS().pC(this.gca, this, this.ld);
      }

      this.ld.wS();

      ChainedOperationResult var10;
      try {
         if (var9.pC(var1, var3, var5, var6)) {
            long var29 = var9.A();
            if (var29 >= 0L) {
               this.pC(var29);
               this.gca.enqueuePointerForAnalysis(new Pointer(var29, var9.kS(), 2));
               this.gca.recordAnalysisComment(var29, "switch size");
               ((ey)this.gca.getModel().getReferenceManager()).recordInternalReference(var1, var29, ReferenceType.READ_DATA);
            }

            short var12 = 1000;
            TreeMap var13 = new TreeMap();

            for (int var14 = 0; var14 < var9.pC() && var14 < var12; var14++) {
               try {
                  Long var15 = var9.pC(var14);
                  if (var15 != null) {
                     long var16 = var9.kS(var14);
                     if (this.gca.getAnalysisRanges().contains(var16)) {
                        SwitchInformation.SwitchCaseInformation var18 = new SwitchInformation.SwitchCaseInformation();
                        var18.setCaseHandler(this.gca.getProcessor().createEntryPoint(var16));
                        if (var9.wS()) {
                           var18.setJumpTableEntryAddress(var15);
                           var18.setJumpTableEntrySize(var9.A(var14));

                           for (int var19 = 0; var19 < var9.UT(); var19++) {
                              Long var20 = var9.pC(var19, var14);
                              if (var20 != null) {
                                 TreeSet var21 = (TreeSet)var13.get(var19);
                                 if (var21 == null) {
                                    var21 = new TreeSet();
                                    var13.put(var19, var21);
                                 }

                                 var21.add(var20);
                                 this.pC(var20);
                              }
                           }
                        }

                        var7.addCase(var18);
                     }
                  }
               } catch (MemoryException var26) {
                  wS.catchingSilent(var26);
               }
            }

            for (Entry var31 : var13.entrySet()) {
               long var32 = (Long)((TreeSet)var31.getValue()).first();
               int var33 = var9.A((Integer)var31.getKey(), 0);
               SwitchInformation.JumpTableInformation var34 = new SwitchInformation.JumpTableInformation(var32, var33);
               long var35 = var32;

               while (((TreeSet)var31.getValue()).contains(var35 + var33)) {
                  var35 += var33;
               }

               if ((Long)((TreeSet)var31.getValue()).last() == var35) {
                  var34.setEndAddress(var35 + var33);
                  var7.addJumpTable(var34);
                  this.fI.add(var34.getStartAddress());
                  this.pC(var1, var34.getStartAddress());
               }
            }

            return new ChainedOperationResult(var7);
         }

         var10 = new ChainedOperationResult(var7);
      } catch (Exception var27) {
         wS.catchingSilent(var27);
         var7 = new SwitchInformation();
         return new ChainedOperationResult(var7);
      } finally {
         this.ld.UT();
      }

      return var10;
   }

   private void pC(long var1, long var3) {
      Set var5 = this.gca.getModel().getReferenceManager().getReferencesTo(var3);
      if (var5 != null && !var5.isEmpty()) {
         for (IReference var7 : var5) {
            ReferenceLocation var8 = var7.getFrom();
            if (var8.isInternalAddress()) {
               INativeContinuousItem var9 = this.gca.getModel().getItemAt(var8.getInternalAddress());
               if (var9 instanceof INativeInstructionItem) {
                  rQ var10 = (rQ)((INativeInstructionItem)var9).getInstruction();
                  if (!PU.pC(var10) || var1 == var8.getInternalAddress()) {
                     for (IReference var13 : this.gca.getModel().getReferenceManager().getReferencesFrom(var8)) {
                        if (var13.getTo().isInternalAddress() && var13.getTo().getInternalAddress() >= var3) {
                           this.gca.getModel().getReferenceManager().unrecordReference(var8, var13.getTo());
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public ChainedOperationResult postprocessImage(int var1) {
      if (this.A != null) {
         this.A.pC();
      }

      ChainedOperationResult var2 = ChainedOperationResult.FALSE_IGNORE;

      try {
         if (this.eP == null) {
            this.eP = new by(this.gca, this.UO, ((a)this.gca).wS().ld());
         }

         if (this.eP != null && this.eP.pC()) {
            this.eP.A();
            var2 = ChainedOperationResult.TRUE_CONTINUE;
         }
      } catch (Exception var12) {
         wS.error(S.L("Tail call analyzer failed"));
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("tail call analyzer failed", var12));
      }

      if (var1 == 0) {
         boolean var3 = ((a)this.gca).wS().A().getPropertyManager().getBoolean("PerformFakeRoutineCallAnalysis");
         if (var3 && this.gca.getProcessor().getDefaultMode() != 64) {
            Jl var4 = new Jl(this, (a)this.gca, this.UO);
            GlobalLog.status(S.L("ARM fake routine calls analysis started..."));
            boolean var5 = var4.pC();
            if (!var5) {
               try {
                  JebCoreService.getInstance()
                     .notifyListeners(
                        new JebEvent(
                           J.Notification,
                           new ClientNotification(
                              S.L(
                                 "Experimental analysis for ARM Branch and Link instructions used as simple branches failed. We suggest deactivating it and re-analyzing (see back-end properties \"Perform fake routine call analysis\" in parsers>native>disas>arm and armT32)."
                              )
                           )
                        )
                     );
               } catch (JebException var11) {
               }

               JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("fake calls analysis failed"));
            }

            GlobalLog.status(S.L("ARM fake routine calls analysis completed"));
            var2 = ChainedOperationResult.TRUE_CONTINUE;
         }

         OU var14 = new OU(this, (a)this.gca, this.UO);
         var14.A();
         HashSet var16 = new HashSet();
         int var6 = 0;
         kw var7 = new kw((a)this.gca, this.UO);

         while (true) {
            for (CodePointer var10 : var7.pC()) {
               Object[] var10000 = new Object[]{var10.getAddress()};
               this.gca.enqueuePointerForAnalysis(var10, 1, 64);
               var16.add(var10.getAddress());
            }

            if (var6 == var16.size()) {
               var14.pC();
               var14.A();
               break;
            }

            var6 = var16.size();
            if (this.gca.needsAnalysis()) {
               this.gca.analyze(true, true);
            }
         }
      }

      label80:
      for (Entry var15 : this.ah.entrySet()) {
         INativeContinuousItem var17 = this.gca.getModel().getItemAt((Long)var15.getKey());
         if (var17 instanceof INativeDataItem var18) {
            for (IReference var22 : this.gca.getModel().getReferenceManager().getReferencesTo((Long)var15.getKey())) {
               if (var22.getFrom().isInternalAddress() && !((Set)var15.getValue()).contains(var22.getFrom().getInternalAddress())) {
                  continue label80;
               }
            }

            if (var18.getType() instanceof IReferenceType) {
               INativeType var21 = this.gca.getTypeManager().getExactInteger((int)var17.getMemorySize(), false);
               var18 = this.gca.defineData(var17.getMemoryAddress(), var21);
            }

            var18.getHints(true).setAddressCalculationHint(3);
         }
      }

      this.ah.clear();
      return var2;
   }
}
