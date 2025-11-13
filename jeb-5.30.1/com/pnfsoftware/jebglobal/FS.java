package com.pnfsoftware.jebglobal;

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
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
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
public class FS extends AbstractAnalyzerExtension {
   private static final ILogger Dw = GlobalLog.getLogger(FS.class);
   private static final int Uv = -1;
   private static final int oW = -2;
   private static final int gO = 0;
   private static final String[] nf = new String[]{"AND", "BIC", "IT", "CSET"};
   @SerId(1)
   private TreeSet gP;
   @SerId(2)
   private Map za;
   @SerId(3)
   private Set lm;
   @SerId(4)
   private CB zz;
   @SerId(5)
   private Set JY;
   @SerId(6)
   private Map HF;
   @SerId(7)
   private Set LK;
   @SerId(8)
   private TreeSet io;
   @SerId(9)
   private TreeMap qa;
   private static boolean Hk = false;
   @SerTransient
   private Map Me;
   @SerTransient
   private Map PV;
   @SerTransient
   private Map oQ;
   @SerTransient
   private Map xW;
   @SerTransient
   private o KT;
   @SerTransient
   private Jc Gf;
   @SerTransient
   rl q;
   @SerTransient
   uU RF;
   @SerTransient
   public ctk.eo xK;

   public FS() {
      this.RF();
   }

   @SerCustomInitPostGraph
   private void RF() {
      if (this.gP == null) {
         this.gP = new TreeSet();
      }

      if (this.io == null) {
         this.io = new TreeSet();
      }

      if (this.za == null) {
         this.za = new HashMap();
      }

      if (this.lm == null) {
         this.lm = new HashSet();
      }

      if (this.HF == null) {
         this.HF = new HashMap();
         if (!this.lm.isEmpty()) {
            for (long var2 : this.lm) {
               this.HF.put(var2, -1);
            }
         }
      }

      if (this.zz == null && this.gca != null) {
         this.zz = new CB(this.gca.getProcessor());
      }

      if (this.JY == null) {
         this.JY = new HashSet();
      }

      if (this.LK == null) {
         this.LK = new HashSet();
      }

      if (this.qa == null) {
         this.qa = new TreeMap();
      }

      if (this.Me == null) {
         this.Me = new HashMap();
      }

      if (this.PV == null) {
         this.PV = new HashMap();
      }

      if (this.oQ == null) {
         this.oQ = new HashMap();
      }

      if (this.xW == null) {
         this.xW = new HashMap();
      }

      if (this.xK == null && this.gca != null) {
         this.xK = ((aae)this.gca).Dw().zz();
      }
   }

   @Override
   public void initialize(INativeCodeAnalyzer var1) {
      super.initialize(var1);
      this.zz = new CB(this.gca.getProcessor());
   }

   @Override
   protected void initializePaddingPatterns(BinaryPatternVerifier var1) {
      this.q = new rl(this.gca, var1);
   }

   @Override
   public ChainedOperationResult getPossiblePaddingSize(long var1, long var3) {
      long var5 = this.q.q(var1);
      return var5 == 0L ? new ChainedOperationResult(0L, ChainedOperationResult.ContinuationStatus.CONTINUE) : new ChainedOperationResult(var5);
   }

   @Override
   protected void initializeProloguePatterns(BinaryPatternVerifier var1) {
      this.Gf = new Jc(this.gca, this, var1);
   }

   public Jc q() {
      return this.Gf;
   }

   @Override
   public ChainedOperationResult getPrologueLooking(long var1, long var3) {
      return this.q(var1, var3, true, true);
   }

   public ChainedOperationResult q(long var1, long var3, boolean var5) {
      return this.q(var1, var3, var5, false);
   }

   public ChainedOperationResult q(long var1, long var3, boolean var5, boolean var6) {
      ChainedOperationResult var7 = ChainedOperationResult.stop();
      if (var1 % 2L == 0L && (var1 % 4L == 0L || this.gca.getProcessor().getMode() != 64)) {
         if (this.xK(var1, null)) {
            return var7;
         } else {
            CodePointer var8 = this.Gf.q(var1, 0, Jc.eo.xK);
            if (var8 != null) {
               if (var8.getMode() == 32) {
                  if (!BN.q(this.gca.getMemory(), var1)) {
                     var8 = null;
                  }
               } else if (var8.getMode() == 16) {
                  long var9 = var1 & -4L;
                  if ((var1 & 2L) != 0L && this.xK(var9)) {
                     CodePointer var11 = this.Gf.q(var1 + 2L, 0, Jc.eo.xK);
                     if (var11 != null) {
                        return var7;
                     }
                  }
               }
            }

            if (var8 != null) {
               SZ var12 = new SZ(this.gca, this, true, true);
               if (var12.q(var8.getMode(), var8.getAddress())) {
                  this.io.add(var8.getAddress());
                  if (var6) {
                     this.gca.enqueuePointerForAnalysis(var8, 1, 64);
                  }

                  return new ChainedOperationResult(var8);
               }
            }

            return var5 ? this.gP(var1) : var7;
         }
      } else {
         return var7;
      }
   }

   private ChainedOperationResult gP(long var1) {
      ChainedOperationResult var3 = ChainedOperationResult.stop();
      if (var1 % 4L == 0L) {
         CodePointer var4 = this.Gf.q(var1);
         if (var4 != null) {
            SZ var5 = new SZ(this.gca, this, true, true);
            if (var5.q(var4.getMode(), var4.getAddress())) {
               return new ChainedOperationResult(var4);
            }
         }
      }

      return var3;
   }

   @Override
   public ChainedOperationResult verifyGapRoutineCandidate(long var1) {
      if (this.xK(var1, null)) {
         return ChainedOperationResult.FALSE_STOP;
      } else if (this.q(var1, var1 + 4L, true).getResult() != null) {
         return ChainedOperationResult.TRUE_CONTINUE;
      } else {
         if ((var1 & 2L) != 0L) {
            CodePointer var3 = (CodePointer)this.q(var1 + 2L, var1 + 6L, true).getResult();
            if (var3 != null && var3.getMode() == 32) {
               return ChainedOperationResult.FALSE_STOP;
            }
         }

         long var11 = var1 & -4L;
         if (this.xK(var11)) {
            return ChainedOperationResult.FALSE_STOP;
         } else {
            StringEncoding[] var5 = new StringEncoding[1];
            String var6 = DataStringUtil.getStringAt(this.gca.getMemory(), var1, 8, -1, var5);
            if (var6 != null && (var5[0] == StringEncoding.ASCII_ZERO || var5[0] == StringEncoding.UTF8_ZERO || var5[0] == StringEncoding.UTF8_NONZERO)) {
               SZ var7 = new SZ(this.gca, this, true, true);
               if (!var7.q(this.gca.getProcessor().getDefaultMode() == 64 ? 64 : 0, var1)) {
                  int var13 = var6.getBytes(var5[0].getCharset()).length;

                  for (long var14 = 0L; var14 < var13; var14 += 4L) {
                     this.q(var1 + var14);
                  }

                  this.q(var1 + var13);
                  return ChainedOperationResult.FALSE_STOP;
               }
            }

            SZ var12 = new SZ(this.gca, this, true, false);
            Pointer var8 = var12.q(var1, this.gca.getProcessor().getDefaultMode() == 64 ? 64 : 0, 2048, true);
            if (var8 == null) {
               return ChainedOperationResult.FALSE_STOP;
            } else {
               int var9 = var8 instanceof CodePointer ? ((CodePointer)var8).getMode() : var8.getSize();
               SZ.eo var10 = var12.RF(var1, var9);
               if (var10 == SZ.eo.q || !(var8 instanceof CodePointer)) {
                  this.PV.put(var1, var9);
               }

               if (var10 == SZ.eo.RF) {
                  this.oQ.put(var1, var9);
               }

               if (var9 == 16) {
                  this.Me.put(var1, Arrays.asList(var9));
               }

               return ChainedOperationResult.TRUE_CONTINUE;
            }
         }
      }
   }

   public ICodePointer q(long var1, int var3) {
      SZ var4 = new SZ(this.gca, this, true, true);
      fA var5 = var4.q(var1, var3);
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
      if (this.oQ.containsKey(var1)) {
         Object[] var29 = new Object[]{var1};
         var7 = var6.createEntryPoint(var1, (Integer)this.oQ.get(var1));
         var5.add(var7);
         return new ChainedOperationResult(var5);
      } else if (this.PV.containsKey(var1)) {
         Object[] var28 = new Object[]{var1};
         int var17 = (Integer)this.PV.get(var1);
         ICodePointer var19 = this.q(var1, var17);
         if (var19 != null) {
            long var24 = var19.getAddress();
            INativeContinuousItem var26 = this.gca.getModel().getItemOver(var24);
            if (var26 != null) {
               if (!(var26 instanceof INativeInstructionItem) || var26.getMemoryAddress() != var24) {
                  var5.add(null);
                  return new ChainedOperationResult(var5);
               }
            } else if (this.q(var24, var24 + 4L, true).getResult() != null) {
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
         INativeInstructionItem var8 = this.q(0, var1, 10);
         if (var8 != null) {
            INativeInstructionItem var9 = this.q(1, var1, 10);
            if (var9 != null) {
               long var10 = var8.getMemoryAddress() - var9.getMemoryAddressEnd();
               if (var10 <= 4L) {
                  CodePointer var12;
                  if (var10 == 2L) {
                     var12 = this.gca.getProcessor().createEntryPoint(var1, 16);
                  } else {
                     List var13 = (List)this.Me.get(var1);
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

         List var18 = (List)this.Me.get(var1);
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
                  INativeInstructionItem var21 = this.q(1, var1, 10);
                  if (var21 != null && var8.getInstruction().getProcessorMode() == var21.getInstruction().getProcessorMode()) {
                     var5.add(new CodePointer(var1, var8.getInstruction().getProcessorMode()));
                  }
               }

               if (var5.size() == 0) {
                  int var22 = this.gca.getMemory().readInt(var1, ((vh)this.gca.getProcessor()).q());
                  if ((var22 & -268435456) == -536870912) {
                     var5.add(var7);
                  } else if ((var22 & -268435456) == -268435456 && var3 >= var1 + 8L) {
                     int var11 = this.gca.getMemory().readInt(var1 + 4L, ((vh)this.gca.getProcessor()).q());
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
               Dw.catchingSilent(var15);
            }

            var5.add(var7);
            var7.setMode(16);
            return new ChainedOperationResult(Arrays.asList(var7));
         }
      }
   }

   private INativeInstructionItem q(int var1, long var2, int var4) {
      INativeContinuousItem var5 = var1 == 0 ? this.gca.getModel().getNextItem(var2) : this.gca.getModel().getPreviousItem(var2);

      for (int var6 = 0; var6 < var4 && var5 != null && !(var5 instanceof INativeInstructionItem); var6++) {
         var5 = var1 == 0 ? this.gca.getModel().getNextItem(var5.getMemoryAddress()) : this.gca.getModel().getPreviousItem(var5.getMemoryAddress());
      }

      return var5 instanceof INativeInstructionItem ? (INativeInstructionItem)var5 : null;
   }

   public ChainedOperationResult q(long var1, fA var3, List var4) {
      boolean var5 = false;
      CW[] var6 = var3.RF();

      for (CW var10 : var6) {
         if (var10 != null) {
            if (!InstructionUtil.isAddressOperand(var10)) {
               var10 = (CW)var10.merge(var1);
               if (var10 == null || !InstructionUtil.isAddressOperand(var10)) {
                  continue;
               }
            }

            boolean var11 = true;
            int var12 = 4;
            long var13 = var10.getOperandValue(var1);
            byte var15 = 0;
            String var16 = var3.Dw().q();
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
               this.q(var13);
            }

            if (var11) {
               uU.q(var13, var4, var12, var15);
               var5 = true;
            }
         }
      }

      return var5 ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.TRUE_CONTINUE;
   }

   public void q(long var1) {
      this.gP.add(var1);
   }

   public Long RF(long var1) {
      return (Long)this.gP.ceiling(var1);
   }

   public boolean xK(long var1) {
      return this.gP.contains(var1) || this.LK.contains(var1);
   }

   public void q(Long var1, Long var2) {
      Object var3 = (Set)this.xW.get(var1);
      if (var3 == null) {
         var3 = new HashSet();
         this.xW.put(var1, var3);
      }

      var3.add(var2);
   }

   public boolean Dw(long var1) {
      return this.LK.contains(var1);
   }

   public boolean Uv(long var1) {
      return this.io.contains(var1);
   }

   public void oW(long var1) {
      this.io.remove(var1);
   }

   @Override
   public ChainedOperationResult determinePotentialPointersInProtoBlock(IBasicBlockSkeleton var1, List var2) {
      if (this.RF == null) {
         this.RF = new uU(this.gca, this);
      }

      this.RF.q(var1, var2);

      for (PointerLocation var4 : var2) {
         if (var4.getPointer().getType() == 2) {
            this.q(var4.getPointer().getAddress());
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult isNonReturningRoutine(INativeMethodItem var1) {
      if (var1.getData() != null) {
         if (var1.getMemoryAddress() != null && this.zz(var1.getMemoryAddress())) {
            return ChainedOperationResult.TRUE_STOP;
         }

         for (BasicBlock var4 : var1.getData().getCFG().getExitBlocks()) {
            AddressableInstruction var5 = var4.getBranchingInstruction2();
            if (var5 != null && OC.gO((fA)var5.getInstruction())) {
               return ChainedOperationResult.FALSE_STOP;
            }
         }

         if (this.JY(var1.getMemoryAddress())) {
            return ChainedOperationResult.TRUE_STOP;
         }
      }

      return ChainedOperationResult.ignore();
   }

   public ChainedOperationResult q(long var1, fA var3) {
      if (var3.q().isPCUpdated() && var3.RF().length == 1 && var3.RF()[0].RF(this.gca.getProcessor().getMode())) {
         return ChainedOperationResult.FALSE_STOP;
      } else {
         ChainedOperationResult var4 = this.RF(var1, var3);
         return var4 == ChainedOperationResult.FALSE_STOP ? this.gO(var1, var3) : var4;
      }
   }

   public ChainedOperationResult RF(long var1, fA var3) {
      if ((var3.q().isPCUpdated() || (var3.q().getFlags() & 2816) != 0) && var3.getRoutineCall(var1).isBroken() && !var3.Uv().gO()) {
         if (this.xK(var1, var3)) {
            INativeContinuousItem var15 = this.gca.getModel().getItemAt(var1);
            if (var15 instanceof axn) {
               InstructionHints var5 = ((axn)var15).getHints(true);
               var5.setFakeCall(true);
            }

            return ChainedOperationResult.TRUE_STOP;
         }

         if (!this.HF.isEmpty()) {
            Long var4 = this.Dw(var1, var3);
            if (var4 != null && this.zz(var4)) {
               return ChainedOperationResult.TRUE_STOP;
            }
         }

         long var14 = var1 + var3.getSize();
         if (this.xK(var14)) {
            this.Uv(var1, var3);
            return ChainedOperationResult.TRUE_STOP;
         }

         Long var6 = (Long)this.gP.higher(var14);
         if (var6 != null && var6 <= var14 + 12L) {
            int var16 = (int)(var6 - var14);
            if (var16 >= 2 && (var3.getProcessorMode() == 16 || var16 >= 4)) {
               int var17 = 0;

               while (var17 < var16 && var16 >= 2 && (var3.getProcessorMode() == 16 || var16 >= 4)) {
                  fA var18 = OC.q(this.gca, var14 + var17, var3.getProcessorMode());
                  if (var18 == null) {
                     this.Uv(var1, var3);
                     return this.RF(var14, var16);
                  }

                  if (var18.q().isPCUpdated()) {
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

                     if (!var11 && !var18.Uv().gO()) {
                        return ChainedOperationResult.FALSE_STOP;
                     }
                  }

                  var17 += var18.getSize();
               }

               this.Uv(var1, var3);
               return this.RF(var14, var16);
            }

            return ChainedOperationResult.TRUE_STOP;
         }

         SZ var7 = new SZ(this.gca, this, false, false);
         if (!var7.q(var3.getProcessorMode(), var14)) {
            this.Uv(var1, var3);
            return ChainedOperationResult.TRUE_STOP;
         }

         axp var8 = ((aaf)this.gca.getModel()).oW(var14);
         if (var8 != null && this.Gf.q(var8)) {
            this.Uv(var1, var3);
            return ChainedOperationResult.TRUE_STOP;
         }

         Boolean var9 = this.za(var14);
         if (Boolean.TRUE.equals(var9)) {
            this.Uv(var1, var3);
            return ChainedOperationResult.TRUE_STOP;
         }

         if (var9 == null) {
            Long var10 = this.Dw(var1, var3);
            if (var10 != null && !this.lm(var10)) {
               this.q(var1, var3, -2);
               return ChainedOperationResult.TRUE_STOP;
            }
         }
      }

      this.oW(var1, var3);
      return ChainedOperationResult.FALSE_STOP;
   }

   public void RF(Long var1, Long var2) {
      this.qa.put(var1, var2);
   }

   private boolean xK(long var1, fA var3) {
      Entry var4 = this.qa.lowerEntry(var1);
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

   public boolean gO(long var1) {
      return this.Gf.q(var1, 0, Jc.eo.xK) != null;
   }

   public boolean nf(long var1) {
      return this.Gf.q(var1, 0) != null;
   }

   private Boolean za(long var1) {
      int var3 = this.gca.getProcessor().getMode();
      int var4 = this.Gf.q(var1, var1 + 8L, var3);
      if (var4 == 0) {
         return this.nf(var1) ? null : false;
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
         if (this.nf(var5)) {
            return true;
         } else {
            uU var7 = new uU(this.gca, this);
            ArrayList var8 = new ArrayList();

            try {
               if (var7.q(var5, var5 + 4L, var8, 4, 0, true) && !var8.isEmpty()) {
                  return true;
               }
            } catch (MemoryException var10) {
               Dw.catchingSilent(var10);
            }

            return false;
         }
      }
   }

   private Long Dw(long var1, fA var3) {
      if (OC.Uv(var3)) {
         if (var3.RF() != null && var3.RF().length != 0) {
            CW var4 = var3.RF()[0];
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

   private void Uv(long var1, fA var3) {
      this.q(var1, var3, -1);
   }

   private void q(long var1, fA var3, int var4) {
      Long var5 = this.Dw(var1, var3);
      if (var5 != null) {
         this.HF.put(var5, var4);
      }
   }

   private void oW(long var1, fA var3) {
      Long var4 = this.Dw(var1, var3);
      if (var4 != null) {
         Integer var5 = (Integer)this.HF.get(var4);
         if (var5 == null) {
            var5 = 0;
         } else if (var5 == -1 || var5 == -2) {
            return;
         }

         this.HF.put(var4, var5 + 1);
      }
   }

   private boolean lm(long var1) {
      if (this.gca.getModel() instanceof aaf) {
         axp var3 = ((aaf)this.gca.getModel()).oW(var1);
         if (var3 != null) {
            Boolean var4 = var3.getNonReturning();
            if (var4 != null) {
               this.HF.remove(var1);
               return var4;
            }
         }
      }

      Integer var5 = (Integer)this.HF.get(var1);
      return var5 != null && var5 > 1;
   }

   private boolean zz(long var1) {
      if (this.gca.getModel() instanceof aaf) {
         axp var3 = ((aaf)this.gca.getModel()).oW(var1);
         if (var3 != null) {
            Boolean var4 = var3.getNonReturning();
            if (var4 != null) {
               this.HF.remove(var1);
               return var4;
            }
         }
      }

      Integer var5 = (Integer)this.HF.get(var1);
      return var5 != null && var5 == -1;
   }

   private boolean JY(long var1) {
      Integer var3 = (Integer)this.HF.get(var1);
      return var3 != null && var3 == -2;
   }

   private ChainedOperationResult RF(long var1, int var3) {
      this.q(var1);
      int var4 = 0;

      while (var4 < var3) {
         int var5 = Math.min(4, var3 - var4);
         this.gca.enqueuePointerForAnalysis(new Pointer(var1 + var4, var5, 2));
         var4 += var5;
      }

      return ChainedOperationResult.TRUE_STOP;
   }

   private ChainedOperationResult gO(long var1, fA var3) {
      if (!var3.q().isPCUpdated()) {
         return ChainedOperationResult.FALSE_STOP;
      } else if (var3.RF() != null && var3.RF().length != 0) {
         iD var4 = (iD)this.za.get(var1);
         return var4 != null && var4.RF() ? ChainedOperationResult.TRUE_STOP : this.nf(var1, var3);
      } else {
         return ChainedOperationResult.FALSE_STOP;
      }
   }

   private ChainedOperationResult nf(long var1, fA var3) {
      if (InstructionUtil.isAddressOperand(var3.RF()[0])) {
         IFlowInformation var4 = var3.getRoutineCall(var1);
         if (var4.isBroken() && var4.getTargets().size() == 1) {
            long var5 = ((ICodePointer)var4.getTargets().get(0)).getAddress();
            long var7 = var1 + var3.getSize();
            iD var9 = (iD)this.za.get(var5);
            this.zz.Dw();
            if (var9 == null) {
               var9 = Js.q(this.gca, this.zz, var5, ((ICodePointer)var4.getTargets().get(0)).getMode());
               if (var9 == null) {
                  var9 = gR.xK();
               }

               this.za.put(var5, var9);
            }

            if (var9.q()) {
               this.q(var7);
               this.za.put(var1, var9.q(this.zz, var1, var3, false));
            }

            this.zz.Uv();
            return var9.RF() ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.FALSE_STOP;
         }
      }

      return ChainedOperationResult.FALSE_STOP;
   }

   public ChainedOperationResult q(long var1, fA var3, IBasicBlockSkeleton var4) {
      if (this.xK == ctk.eo.q) {
         return ChainedOperationResult.FALSE_STOP;
      } else if (var3.RF() != null && var3.RF().length != 0) {
         iD var5 = (iD)this.za.get(var1);
         if (var5 != null && var5.RF()) {
            return ChainedOperationResult.TRUE_STOP;
         } else {
            this.RF(var1, var3, var4);
            CW var6 = var3.RF()[0];
            String var7 = var3.Dw().q();
            if (this.q(var3, var7, var6)) {
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

                  if (this.q(var8, var3, var7, var6)) {
                     var10 = true;
                  }

                  if (!var10) {
                     return ChainedOperationResult.FALSE_STOP;
                  } else if (this.q(var8, var3, var7, var6)) {
                     return this.q(var1, var3, var6);
                  } else if (this.JY.contains(var8)) {
                     return ChainedOperationResult.TRUE_STOP;
                  } else {
                     CodePointer var15 = this.Gf.RF().q(((kR)var4).RF(), false, false);
                     if (var15 != null) {
                        return ChainedOperationResult.FALSE_STOP;
                     } else if (var7.equals("LDR")) {
                        if (!var6.RF(this.gca.getProcessor().getMode())) {
                           return ChainedOperationResult.FALSE_STOP;
                        } else {
                           CW var17 = var3.RF()[1];
                           if (var17.getOperandType() != 7) {
                              return ChainedOperationResult.FALSE_STOP;
                           } else {
                              CW[] var14 = var17.oW();
                              return var14.length == 2 && var14[1] instanceof ZD ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.FALSE_STOP;
                           }
                        }
                     } else if (var7.equals("ADD")) {
                        boolean var16 = var6.RF(this.gca.getProcessor().getMode())
                           && (var3.RF().length == 2 || var3.RF()[1].RF(this.gca.getProcessor().getMode()));
                        return ChainedOperationResult.stop(var16);
                     } else if (var7.equals("MOV")) {
                        return var6.RF(this.gca.getProcessor().getMode()) && !this.q(var4, var3.RF()[1])
                           ? ChainedOperationResult.TRUE_STOP
                           : ChainedOperationResult.FALSE_STOP;
                     } else if (!var7.equals("TBB") && !var7.equals("TBH")) {
                        if ((var7.equals("BR") || var7.equals("BLR")) && !this.q(var4, var6)) {
                           return ChainedOperationResult.TRUE_STOP;
                        } else {
                           return Hk ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.FALSE_STOP;
                        }
                     } else {
                        return ChainedOperationResult.TRUE_STOP;
                     }
                  }
               } else {
                  return this.nf(var1, var3);
               }
            }
         }
      } else {
         return ChainedOperationResult.FALSE_STOP;
      }
   }

   private ChainedOperationResult q(long var1, fA var3, CW var4) {
      boolean var5 = q(var3);
      long var6 = var5 ? var1 + var3.getSize() : var4.getOperandValue(var1);
      fA var8 = OC.q(this.gca, var6);

      for (ArrayList var9 = new ArrayList(); var8 != null && !var8.q().isPCUpdated(); var8 = OC.q(this.gca, var6)) {
         var9.add(var8);
         var6 += var8.getSize();
      }

      if (var8 != null && var8.RF() != null && var8.RF().length != 0) {
         CW var10 = var8.RF()[0];
         if (var8.Dw().q().equalsIgnoreCase("bl") && (var10.getOperandType() == 2 || var10.getOperandType() == 3)) {
            long var11 = var10.getOperandValue(var6);
            iD var13 = (iD)this.za.get(var11);
            this.zz.Dw();
            if (var13 == null) {
               var13 = AL.q(this.gca, this.zz, var11);
               if (var13 == null) {
                  var13 = gR.xK();
               }

               this.za.put(var11, var13);
            }

            if (var13.q()) {
               this.q(var6 + var8.getSize());
               this.za.put(var6, var13.q(this.zz, var6, var8, var5));
            }

            this.zz.Uv();
         }

         return ChainedOperationResult.FALSE_STOP;
      } else {
         return ChainedOperationResult.FALSE_STOP;
      }
   }

   private void RF(long var1, fA var3, IBasicBlockSkeleton var4) {
      Integer var5 = tB.q(var3);
      Long var6 = null;
      if (var5 != null) {
         if (OC.q(var5)) {
            var6 = var1 + var3.getSize();
         } else if (OC.RF(var5) && var3.RF().length == 1 && InstructionUtil.isAddressOperand(var3.RF()[0])) {
            var6 = var3.RF()[0].getOperandValue(var1);
         }

         if (var6 != null) {
            for (int var7 = var4.getInsntructions().size() - 2; var7 >= 0; var7--) {
               fA var8 = (fA)var4.getInsntructions().get(var7);
               if (var8.Dw().q().equals("CMP")) {
                  if (var8.RF()[1].getOperandType() != 1 || var8.RF()[1].getOperandValue() <= 1L) {
                     break;
                  }

                  this.JY.add(var6);
               }

               if (var8.Dw().xK()) {
                  break;
               }
            }
         }
      }
   }

   private boolean q(long var1, fA var3, String var4, CW var5) {
      return var4.equals("B") && InstructionUtil.isAddressOperand(var5) && (tB.RF(var3) || this.JY.contains(var1));
   }

   private boolean q(fA var1, String var2, CW var3) {
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
            if (GC.q(var3, this.gca.getProcessor().getMode()) || var3.RF(this.gca.getProcessor().getMode())) {
               return true;
            }
            break;
         case "MOV":
            if (GC.q(var1.RF()[1], this.gca.getProcessor().getMode())) {
               return true;
            }
      }

      return false;
   }

   private static boolean q(fA var0) {
      mZ var1 = var0.Uv();
      if (!var1.gO()) {
         return false;
      } else {
         Integer var2 = var1.RF();
         return var2 != 9 && var2 != 13 && var2 != 11 && var2 != 3 && var2 != 4;
      }
   }

   private boolean q(IBasicBlockSkeleton var1, CW var2) {
      Set var3 = this.gca.getModel().getReferenceManager().getReferencesTo(var1.getFirstAddress());
      boolean var4 = false;
      if (var3 == null || var3.isEmpty()) {
         for (fA var6 : var1) {
            if (Strings.isContainedIn(var6.Dw().q(), nf)) {
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
      Integer var9 = (int)RegisterUtil.getPureId(GC.q(var8).getOperandValue());

      for (int var10 = var16.size() - 2; var10 >= 0; var10--) {
         fA var11 = (fA)var16.get(var10);
         ArrayList var12 = new ArrayList();
         ArrayList var13 = new ArrayList();
         var11.getDefUse(var12, var13);
         boolean var14 = var15.removeAll(var12);
         if (var14) {
            if (!var11.Uv().oW()) {
               return false;
            }

            if (Strings.isContainedIn(var11.Dw().q(), nf)) {
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

      if (!this.Uv(var1.getFirstAddress()) && !this.gO(var1.getFirstAddress())) {
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

      return this.q(var1, var3.getFirstAddress(), var5, var6);
   }

   public ChainedOperationResult q(long var1, long var3, Map var5, Map var6) {
      SwitchInformation var7 = new SwitchInformation();
      iD var8 = (iD)this.za.get(var1);
      BH var9;
      if (var8 != null) {
         var9 = var8.q(this.gca, this, this.zz);
      } else {
         var9 = gR.xK().q(this.gca, this, this.zz);
      }

      this.zz.Dw();

      ChainedOperationResult var10;
      try {
         if (var9.q(var1, var3, var5, var6)) {
            long var29 = var9.RF();
            if (var29 >= 0L) {
               this.q(var29);
               this.gca.enqueuePointerForAnalysis(new Pointer(var29, var9.xK(), 2));
               this.gca.recordAnalysisComment(var29, "switch size");
               ((abl)this.gca.getModel().getReferenceManager()).recordInternalReference(var1, var29, ReferenceType.READ_DATA);
            }

            short var12 = 1000;
            TreeMap var13 = new TreeMap();

            for (int var14 = 0; var14 < var9.q() && var14 < var12; var14++) {
               try {
                  Long var15 = var9.q(var14);
                  if (var15 != null) {
                     long var16 = var9.xK(var14);
                     if (this.gca.getAnalysisRanges().contains(var16)) {
                        SwitchInformation.SwitchCaseInformation var18 = new SwitchInformation.SwitchCaseInformation();
                        var18.setCaseHandler(this.gca.getProcessor().createEntryPoint(var16));
                        if (var9.Dw()) {
                           var18.setJumpTableEntryAddress(var15);
                           var18.setJumpTableEntrySize(var9.RF(var14));

                           for (int var19 = 0; var19 < var9.Uv(); var19++) {
                              Long var20 = var9.q(var19, var14);
                              if (var20 != null) {
                                 TreeSet var21 = (TreeSet)var13.get(var19);
                                 if (var21 == null) {
                                    var21 = new TreeSet();
                                    var13.put(var19, var21);
                                 }

                                 var21.add(var20);
                                 this.q(var20);
                              }
                           }
                        }

                        var7.addCase(var18);
                     }
                  }
               } catch (MemoryException var26) {
                  Dw.catchingSilent(var26);
               }
            }

            for (Entry var31 : var13.entrySet()) {
               long var32 = (Long)((TreeSet)var31.getValue()).first();
               int var33 = var9.RF((Integer)var31.getKey(), 0);
               SwitchInformation.JumpTableInformation var34 = new SwitchInformation.JumpTableInformation(var32, var33);
               long var35 = var32;

               while (((TreeSet)var31.getValue()).contains(var35 + var33)) {
                  var35 += var33;
               }

               if ((Long)((TreeSet)var31.getValue()).last() == var35) {
                  var34.setEndAddress(var35 + var33);
                  var7.addJumpTable(var34);
                  this.LK.add(var34.getStartAddress());
                  this.q(var1, var34.getStartAddress());
               }
            }

            return new ChainedOperationResult(var7);
         }

         var10 = new ChainedOperationResult(var7);
      } catch (Exception var27) {
         Dw.catchingSilent(var27);
         var7 = new SwitchInformation();
         return new ChainedOperationResult(var7);
      } finally {
         this.zz.Uv();
      }

      return var10;
   }

   private void q(long var1, long var3) {
      Set var5 = this.gca.getModel().getReferenceManager().getReferencesTo(var3);
      if (var5 != null && !var5.isEmpty()) {
         for (IReference var7 : var5) {
            ReferenceLocation var8 = var7.getFrom();
            if (var8.isInternalAddress()) {
               INativeContinuousItem var9 = this.gca.getModel().getItemAt(var8.getInternalAddress());
               if (var9 instanceof INativeInstructionItem) {
                  fA var10 = (fA)((INativeInstructionItem)var9).getInstruction();
                  if (!OC.q(var10) || var1 == var8.getInternalAddress()) {
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
      if (this.RF != null) {
         this.RF.q();
      }

      ChainedOperationResult var2 = ChainedOperationResult.FALSE_IGNORE;

      try {
         if (this.KT == null) {
            this.KT = new o(this.gca, this.Gf, ((aae)this.gca).Dw().lm());
         }

         if (this.KT != null && this.KT.RF()) {
            this.KT.xK();
            var2 = ChainedOperationResult.TRUE_CONTINUE;
         }
      } catch (Exception var12) {
         Dw.error(S.L("Tail call analyzer failed"));
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("tail call analyzer failed", var12));
      }

      if (var1 == 0) {
         boolean var3 = ((aae)this.gca).Dw().Dw().getPropertyManager().getBoolean("PerformFakeRoutineCallAnalysis");
         if (var3 && this.gca.getProcessor().getDefaultMode() != 64) {
            Uk var4 = new Uk(this, (aae)this.gca, this.Gf);
            GlobalLog.status(S.L("ARM fake routine calls analysis started..."));
            boolean var5 = var4.q();
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

         Za var14 = new Za(this, (aae)this.gca, this.Gf);
         var14.RF();
         HashSet var16 = new HashSet();
         int var6 = 0;
         uf var7 = new uf((aae)this.gca, this.Gf);

         while (true) {
            for (CodePointer var10 : var7.q()) {
               Object[] var10000 = new Object[]{var10.getAddress()};
               this.gca.enqueuePointerForAnalysis(var10, 1, 64);
               var16.add(var10.getAddress());
            }

            if (var6 == var16.size()) {
               var14.q();
               var14.RF();
               break;
            }

            var6 = var16.size();
            if (this.gca.needsAnalysis()) {
               this.gca.analyze(true, true);
            }
         }
      }

      label80:
      for (Entry var15 : this.xW.entrySet()) {
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

      this.xW.clear();
      return var2;
   }
}
