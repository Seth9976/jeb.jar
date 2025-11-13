package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.QuestionNotificationYesNo;
import com.pnfsoftware.jeb.core.exceptions.InterruptionException;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.PointerLocation;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.DebugInformationPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.LibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceLocation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.SwitchInformation;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.DecompilerException;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventSubType;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledRoutine;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.UnmanglerService;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandCMA;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeAttribute;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureDBManager;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureMatchResult;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrototypeAttribute;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeStringParseException;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeStringParser;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.collect.ISegmentMap;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Ser
public class aae implements INativeCodeAnalyzer {
   private static final ILogger xK = GlobalLog.getLogger(aae.class);
   private static int Dw = 32;
   private static int Uv = 6;
   private static Boolean oW;
   private static final Object gO = new Object();
   @SerId(1)
   private aaf nf;
   @SerId(2)
   private IVirtualMemory gP;
   @SerId(3)
   private IProcessor za;
   @SerId(4)
   private axt lm;
   @SerId(5)
   private bby zz;
   @SerId(6)
   private MemoryRanges JY;
   @SerId(7)
   private ou HF = new ou();
   @SerId(8)
   protected boolean q = true;
   @SerId(9)
   private boolean LK = true;
   @SerId(10)
   protected boolean RF = true;
   @SerId(13)
   private boolean io = true;
   @SerId(14)
   private xJ qa;
   @SerId(15)
   private boolean Hk;
   @SerId(16)
   private MemoryRanges Me;
   @SerId(17)
   private ISegmentMap PV;
   @SerId(18)
   private INativeCodeAnalyzerExtensionsManager oQ;
   @SerId(19)
   private ICompiler xW;
   @SerId(20)
   private ICodeObjectUnit KT;
   @SerId(21)
   private int Gf;
   @SerId(22)
   private int Ef;
   @SerId(23)
   private aat cC;
   @SerId(24)
   private boolean sH;
   @SerTransient
   private volatile int CE;
   @SerTransient
   private NativeSignatureDBManager wF;
   @SerTransient
   private aaw If;
   @SerTransient
   private int Dz;
   @SerTransient
   private List mI;
   @SerTransient
   private List jq;
   @SerTransient
   private List ui;
   @SerTransient
   private UnmanglerService TX;
   @SerTransient
   private int Rr;
   @SerTransient
   private int EB;
   @SerTransient
   private Set Xo = new HashSet();
   @SerTransient
   private aae.eo Bu;
   @SerTransient
   private Boolean IN;
   @SerTransient
   private Boolean rL;
   @SerTransient
   private abb eJ;
   @SerTransient
   private aal YN;
   @SerTransient
   private aaj Rv;
   @SerTransient
   private TypeStringParser zx;
   @SerTransient
   private volatile int ZT;
   private static final boolean Ri = true;
   @SerTransient
   private long GY;

   @SerCustomInitPostGraph
   private void PV() {
      if (this.oQ == null) {
         this.oQ = new aav(this, true);
      }

      if (this.cC == null) {
         this.cC = new aat();
      }

      this.Xo = new HashSet();
   }

   public aae(aaf var1, IVirtualMemory var2, IProcessor var3, ICodeObjectUnit var4, bby var5, axt var6, MemoryRanges var7) {
      this(var1, var2, var3, var4, var5, var6, var7, null);
   }

   public aae(aaf var1, IVirtualMemory var2, IProcessor var3, ICodeObjectUnit var4, bby var5, axt var6, MemoryRanges var7, MemoryRanges var8) {
      this.nf = var1;
      this.gP = var2;
      this.za = var3;
      this.KT = var4;
      this.zz = var5;
      this.lm = var6;
      this.JY = var7;
      this.oQ = new aav(this, true);
      this.Me = var8;
      this.cC = new aat();
   }

   public void q(MemoryRanges var1) {
      this.JY = var1;
   }

   @Override
   public MemoryRanges getAnalysisRanges() {
      return this.JY;
   }

   public void RF(MemoryRanges var1) {
      this.Me = var1;
   }

   public MemoryRanges q() {
      return this.Me;
   }

   public ISegmentMap RF() {
      return this.PV;
   }

   public void q(ISegmentMap var1) {
      this.PV = var1;
   }

   public aat xK() {
      return this.cC;
   }

   public void q(aaw var1) {
      this.If = var1;
      if (var1 != null) {
         if (this.oQ != null) {
            ((aav)this.oQ).registerExtensions(var1.gO(), true);
         }

         Uv = var1.Me();
         Dw = var1.PV();
      }
   }

   public aaw Dw() {
      return this.If;
   }

   public void q(UnmanglerService var1) {
      this.TX = var1;
   }

   @Override
   public UnmanglerService getUnmanglerService() {
      return this.TX;
   }

   public void q(int var1, int var2) {
      this.Rr = var1;
      this.EB = var2;
   }

   @Override
   public DebugInformationPolicy getDebugInformationPolicy() {
      return new DebugInformationPolicy(this.Rr, this.EB);
   }

   public INativeCodeAnalyzerExtensionsManager Uv() {
      return this.oQ;
   }

   public void q(xJ var1) {
      this.qa = var1;
   }

   public xJ oW() {
      return this.qa;
   }

   public void q(boolean var1) {
      this.Hk = var1;
   }

   public boolean gO() {
      return this.Hk;
   }

   public void q(Boolean var1) {
      this.rL = var1;
   }

   public void q(int var1) {
      this.Ef = var1;
   }

   public int nf() {
      return this.Ef;
   }

   public void q(NativeSignatureDBManager var1) {
      this.wF = var1;
      if (this.wF != null) {
         this.wF.registerAnalyzer(this);
      }
   }

   public NativeSignatureDBManager gP() {
      return this.wF;
   }

   @Override
   public ICompiler getDetectedCompiler() {
      return this.xW;
   }

   public void q(ICompiler var1) {
      this.xW = var1;
   }

   public aaf za() {
      return this.nf;
   }

   @Override
   public IProcessor getProcessor() {
      return this.za;
   }

   public axt lm() {
      return this.lm;
   }

   @Override
   public IVirtualMemory getMemory() {
      return this.gP;
   }

   public bby zz() {
      return this.zz;
   }

   @Override
   public ICodeObjectUnit getContainer() {
      return this.KT;
   }

   @Override
   public boolean enqueuePointerForAnalysis(Pointer var1) {
      return this.enqueuePointerForAnalysis(var1, 0, 0);
   }

   @Override
   public boolean enqueuePointerForAnalysis(Pointer var1, int var2) {
      return this.enqueuePointerForAnalysis(var1, var2, 0);
   }

   @Override
   public boolean enqueuePointerForAnalysis(Pointer var1, int var2, int var3) {
      return !this.JY.contains(var1.getAddress()) ? false : this.HF.q(var1, var2, var3);
   }

   @Override
   public boolean clearAnalysisQueue() {
      if (this.HF.RF()) {
         return false;
      } else {
         this.HF.Uv();
         return true;
      }
   }

   @Override
   public boolean needsAnalysis() {
      return !this.HF.RF();
   }

   @Override
   public boolean isAnalyzing() {
      return this.CE > 0;
   }

   @Override
   public void requestAnalysisInterruption() {
      if (this.qa != null) {
         this.qa.xK = true;
      }
   }

   @Override
   public int getAnalysisCount() {
      return this.Gf;
   }

   @Override
   public void analyze() {
      this.analyze(false, false);
   }

   @Override
   public void analyze(boolean var1, boolean var2) {
      try (ACLock var3 = this.nf.q.a()) {
         this.CE++;
         this.Bu = new aae.eo();
         boolean var5 = this.nf.q(false);
         this.nf.oW();

         boolean var4;
         try {
            var4 = this.oQ();
         } finally {
            this.nf.gO();
            this.nf.q(var5);
            this.nf.notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
            this.Bu = null;
            if (this.CE == 1) {
               this.q(S.L("Standard analysis pass is complete"));
            }
         }

         if (!var1 && this.qa != null && this.CE == 1 && var4) {
            boolean var6 = this.Hk;
            if (this.IN != null) {
               var6 = this.IN;
            }

            if (this.rL != null) {
               var6 = this.rL;
            }

            if (var6) {
               synchronized (gO) {
                  this.nf.q(false);

                  try {
                     if (oW != null) {
                        var6 = oW;
                     } else if (this.rL == null && this.IN == null && this.Ef > 0 && this.za().za() >= this.Ef) {
                        try {
                           String var8 = this.KT != null ? this.KT.getName() + " " : "";
                           QuestionNotificationYesNo var9 = new QuestionNotificationYesNo(
                              Strings.ff(
                                 S.L(
                                    "An advanced analysis pass is about to start.\nThis binary file (%s)is large, therefore the operation may take a while.\n\nProceed? (The advanced analysis can be paused)"
                                 ),
                                 var8
                              ),
                              true,
                              true
                           );
                           JebCoreService.getInstance().notifyListeners(new JebEvent(J.Notification, var9));
                           this.IN = (Boolean)var9.getResponse();
                           if (var9.isDoNotShowAnymoreResponse()) {
                              oW = this.IN;
                           }

                           var6 = this.IN;
                        } catch (JebException var52) {
                        }
                     }

                     if (var6) {
                        try {
                           this.qa.perform();
                        } catch (Exception var51) {
                           xK.error(S.L("Advanced analysis pass failed"));
                           xK.catchingSilent(var51);
                           JebCoreService.notifySilentExceptionToClient(var51);
                        }
                     }
                  } finally {
                     this.nf.q(var5);
                     this.nf.notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
                  }
               }
            }
         }

         if (!var2) {
            var5 = this.nf.q(false);

            try {
               this.JY();
               if (this.needsAnalysis()) {
                  this.analyze();
               }
            } finally {
               if (this.CE == 1) {
                  this.oQ.sigMatchingPostProcess(this.Gf);
               }

               this.nf.q(var5);
               this.nf.notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
            }
         }
      } finally {
         this.Gf++;
         this.CE--;
         if (this.CE <= 0) {
            this.rL = null;
         }
      }
   }

   public void JY() {
      List var1 = (List)this.za().nf().stream().filter(var0 -> var0.xK() != null && var0.xK().CE()).collect(Collectors.toList());

      try (ACLock var2 = this.nf.q.a()) {
         this.q(var1, false, false);
      }
   }

   public void HF() {
      try (ACLock var1 = this.nf.q.a()) {
         this.q(this.za().nf(), true, true);
      }
   }

   private void q(Collection var1, boolean var2, boolean var3) {
      this.nf.q.verifyLocked();
      if (this.wF != null && this.wF.isActive() && var1 != null && !var1.isEmpty()) {
         boolean var4 = this.nf.q(false);

         try {
            if (this.If != null && this.If.LK() == 2) {
               this.wF.loadDefaultPackages(this);
            }

            Object var5 = var1;
            HashSet var6 = new HashSet();
            int var7 = 0;

            for (int var8 = 0; !var5.isEmpty(); var8++) {
               int var9 = 0;
               ArrayList var10 = new ArrayList();
               HashSet var11 = new HashSet();
               var11.addAll((Collection)var5);

               while (!var5.isEmpty() || !var11.isEmpty()) {
                  if (var5.isEmpty()) {
                     var5.addAll(var11);
                     var11.clear();
                  }

                  if (var5.size() > 20) {
                     xK.status(S.L("Code matching on %d routines... (%d,%d)"), var5.size(), var8, var9);
                  }

                  List var12 = this.wF.match(this, (Collection)var5, false, true, var10.isEmpty() ? var3 : false);
                  var5 = this.q(var12, var10, var6, var2);
                  if (!var11.isEmpty()) {
                     var11.addAll((Collection)var5);
                  }

                  if (var7 > 100) {
                     JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("siglib matching: long running loop"));
                     break;
                  }

                  var7++;
                  var9++;
               }

               if (this.Xo != null && this.Xo.size() > 0 && this.cC != null) {
                  ArrayList var28 = new ArrayList();
                  ArrayList var13 = new ArrayList();

                  for (NativeSignatureMatchResult var15 : this.Xo) {
                     if (var15.getSignatures() != null) {
                        if (((axp)var15.getTarget()).oW() == null) {
                           var13.add(var15);
                        } else {
                           ArrayList var16 = new ArrayList();

                           for (INativeSignature var18 : var15.getSignatures()) {
                              Set var19 = ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var18).Dw();
                              if (var19 != null) {
                                 long var20 = ((axp)var15.getTarget()).oW().getMemoryAddress();

                                 for (LibraryID var23 : var19) {
                                    aas var24 = this.cC.q(var23);
                                    if (var24 != null && var24.xK() && var24.q(var20)) {
                                       var16.add(var18);
                                       break;
                                    }
                                 }
                              }
                           }

                           if (!var16.isEmpty()) {
                              NativeSignatureMatchResult var29 = new NativeSignatureMatchResult(
                                 var15.getTarget(), var16, var15.isComplete(), INativeSignature.ConfidenceLevel.MEDIUM
                              );
                              var28.add(var29);
                              var13.add(var15);
                           }
                        }
                     }
                  }

                  if (!var28.isEmpty()) {
                     this.Xo.removeAll(var13);
                     var5 = this.q(var28, null, var6, var2);
                  }
               }
            }

            if (!var6.isEmpty() && this.eJ != null) {
               this.eJ.RF(new ArrayList(var6));
            }
         } finally {
            this.nf.q(var4);
            this.nf.notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
         }
      }
   }

   private Set q(List var1, List var2, Set var3, boolean var4) {
      HashSet var5 = new HashSet();
      ArrayList var6 = new ArrayList(var1);
      ArrayList var7 = new ArrayList();

      while (!var6.isEmpty() || var2 != null && !var2.isEmpty()) {
         boolean var8 = false;

         for (int var9 = 0; var9 < var6.size(); var9++) {
            NativeSignatureMatchResult var10 = (NativeSignatureMatchResult)var6.get(var9);
            if (var10.getSignatures().size() != 0
               && var10.isComplete()
               && ((INativeSignature)var10.getSignatures().get(0)).getFlags().hasMeaningfulTargetName()) {
               var7.add(var10);
               var6.remove(var9);
               var9--;
            }
         }

         if (var2 != null && var7.isEmpty() && !var2.isEmpty()) {
            for (NativeSignatureMatchResult var15 : var2) {
               var5.add(var15.getTarget().getData());
            }

            var2.clear();

            for (NativeSignatureMatchResult var16 : var6) {
               var5.add(var16.getTarget().getData());
            }

            return var5;
         }

         if (var7.isEmpty()) {
            for (int var11 = 0; var11 < var6.size(); var11++) {
               NativeSignatureMatchResult var14 = (NativeSignatureMatchResult)var6.get(var11);
               if (!v.xK(var14.getTarget().getName())) {
                  var7.add(var14);
                  var6.remove(var11);
                  var11--;
               }
            }
         } else {
            var8 = true;
         }

         if (var7.isEmpty()) {
            var7.addAll(var6);
            var6.clear();
         }

         Collections.sort(var7, (var0, var1x) -> var0.getTarget().getMemoryAddress().compareTo(var1x.getTarget().getMemoryAddress()));
         var5.addAll(this.q(var7, var3, var4));
         if (var2 != null && var8 && !var5.isEmpty()) {
            var2.addAll(var6);
            return var5;
         }

         var7.clear();
      }

      return var5;
   }

   private Set q(List var1, Set var2, boolean var3) {
      HashSet var4 = new HashSet();

      for (NativeSignatureMatchResult var6 : var1) {
         if (var6.getSignatures().size() != 0 && var6.isComplete()) {
            if (var6.getConfidenceLevel() == INativeSignature.ConfidenceLevel.LOW) {
               if (this.Xo != null) {
                  this.Xo.add(var6);
               }
            } else if (!(var6.getTarget() instanceof axj) || !((axj)var6.getTarget()).isDisposed()) {
               INativeSignature var7 = (INativeSignature)var6.getSignatures().get(0);
               List var8 = null;
               if (var6.getSignatures().size() > 1) {
                  var8 = var6.getSignatures();
                  var8.remove(var7);
               }

               if (var6.getTarget() instanceof axp) {
                  axp var9 = (axp)var6.getTarget();
                  if (var9.wF() != null) {
                     INativeSignature var10 = var9.wF().q();
                     List var11 = var9.wF().RF();
                     if (Objects.equals(var10, var7) && Objects.equals(var11, var8)) {
                        continue;
                     }
                  }

                  boolean var18 = false;

                  try {
                     var18 = this.q(var9, var7, var8);
                  } catch (DecompilerException var17) {
                  }

                  if (var3) {
                     xK.info(S.L("=> %s matched by signature"), var6.getTarget().getAddress());
                  }

                  List var19 = this.nf.getCallGraphManager().getGlobalCallGraph().getCallerRoutines(CodePointer.createFrom(var9), !this.If.Hk());

                  for (INativeMethodItem var13 : var19) {
                     ((axp)var13).q(Boolean.valueOf(true));
                  }

                  if (var18) {
                     var2.add(var9.oW());
                  } else {
                     for (INativeMethodItem var21 : var19) {
                        if (var21.getData().getTrampolineTarget() != null && var21.getData().getTrampolineTarget().equals(var9)) {
                           for (INativeMethodItem var16 : this.nf
                              .getCallGraphManager()
                              .getGlobalCallGraph()
                              .getCallerRoutines(CodePointer.createFrom(var21), !this.If.Hk())) {
                              ((axp)var16).q(Boolean.valueOf(true));
                              var4.add(var16.getData());
                           }
                        } else {
                           var4.add(var21.getData());
                        }
                     }
                  }
               }
            }
         }
      }

      return var4;
   }

   private boolean q(axp var1, INativeSignature var2, List var3) {
      this.nf.q.verifyLocked();
      boolean var4 = false;
      com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oL var5 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oL(var2, var3);
      var1.q(var5);
      List var6 = var2.getAttributes();
      boolean var8 = var2.getFlags().hasMeaningfulTargetName();
      String var9 = var1.getName(true);
      String var7;
      if (var8) {
         var7 = var2.getTargetName();
      } else {
         var7 = com.pnfsoftware.jeb.corei.parsers.asm.nativesig.vb.q(var2, this.TX, var9);
      }

      boolean var10 = false;
      if (var7 != null && (this.za().LK().q(var9) || var9.equals("start") || var1.If())) {
         var10 = true;
      }

      if (var10) {
         var1.setName(var7);
      }

      if (var8 && this.q(var1, var7, false)) {
         var4 = true;
      }

      if (var6 != null) {
         for (INativeAttribute var12 : var6) {
            if (!var12.importTo(var1) && var12 instanceof azt && var1.mI() == null) {
               String var13 = (String)var12.getValue();
               if (var10) {
                  if (var8) {
                     var1.q(var7);
                  }

                  var1.xK(var13);
                  var1.setName(var13);
               }

               if (this.q(var1, var13, true)) {
                  var4 = true;
               }
            }
         }
      }

      if (this.cC != null) {
         HashSet var16 = new HashSet();
         Set var17 = ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var2).Dw();
         if (var17 != null) {
            var16.addAll(var17);
         }

         if (var3 != null) {
            for (INativeSignature var14 : var3) {
               Set var15 = ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var14).Dw();
               if (var15 != null) {
                  var16.addAll(var15);
               }
            }
         }

         for (LibraryID var20 : var16) {
            this.cC.q(var20, var1);
         }
      }

      return var4 || var1.Bu();
   }

   public boolean q(INativeMethodItem var1, String var2, boolean var3) {
      if (this.nf.LK().q(var2)) {
         return false;
      } else {
         Boolean var4 = var1.getNonReturning();
         boolean var5 = false;

         try {
            if (var2 != null && !var2.isEmpty()) {
               if (!var3 && this.TX != null) {
                  IUnmangledRoutine var6 = this.TX.unmangleRoutine(var2, false);
                  if (var6 != null && this.TX.importUnmangledRoutineName(var1, var2, var6, true)) {
                     String var7 = var6.getName();
                     if (var7 != null && !var7.isEmpty()) {
                        var5 = this.q(var1, var7);
                     }

                     if (!var5) {
                        var5 = this.TX.importUnmangledRoutinePrototype(var1, var6);
                     }
                  }
               }

               if (!var5) {
                  this.q(var1, var2);
               }
            }
         } catch (DecompilerException var8) {
         }

         return !Booleans.equals(var1.getNonReturning(), var4);
      }
   }

   private boolean q(INativeMethodItem var1, String var2) {
      axp var3 = this.q(var2);
      if (var3 == null
         && (
            this.KT != null && this.KT.getLoaderInformation().getTargetSubsystem() == SubsystemType.LINUX
               || this.getDetectedCompiler() != null && this.getDetectedCompiler().isLinuxCompatible()
         )
         && var2.length() >= 2
         && var2.startsWith("x")) {
         var3 = this.q(var2.substring(1));
      }

      if (var3 == null && this.TX != null) {
         try {
            IUnmangledRoutine var4 = this.TX.unmangleRoutine(var2, true);
            if (var4 != null) {
               String var5 = var4.getReturnType();
               bbd var6 = this.zz.RF(var5, false, false);
               if (var6 != null) {
                  List var7 = var4.getParameterTypes();
                  ArrayList var8 = new ArrayList();

                  for (String var10 : var7) {
                     bbd var11 = this.zz.RF(var10, true, false);
                     var8.add(var11);
                  }

                  bbs var16 = this.zz.q(var6, var8);
                  var1.setPrototype(var16);
               } else {
                  ((axp)var1).q(var2);
                  String var14 = var4.getName();
                  if (((axp)var1).ui() == null && var14 != null) {
                     ((axp)var1).xK(var14);
                  }

                  String var15 = var4.getFull();
                  if (((axp)var1).jq() == null && var15 != null) {
                     ((axp)var1).RF(var15);
                  }
               }
            }
         } catch (Exception var12) {
            xK.catchingSilent(var12);
         }
      }

      if (var3 != null) {
         bbs var13 = var3.Uv();
         if (var13 != null) {
            if (var13.getPrototypeAttributes().contains(PrototypeAttribute.NoReturn)) {
               ((axp)var1).Dw(true);
            }

            var1.setPrototype((bbs)this.zz.q((bbd)var13));
            var1.setParameterNames(new ArrayList(var3.getParameterNames()));
            return true;
         }
      }

      return false;
   }

   public axp q(String var1) {
      TypeLibraryService var2 = this.zz.getTypeLibraryService();
      if (var2 == null) {
         return null;
      } else {
         String[] var3 = var1.split("!");
         if (var3.length == 0) {
            return null;
         } else {
            String var4 = var3[var3.length - 1];
            axp var5 = (axp)var2.findRoutineByName(var4, this.za.getType());
            if (var5 == null) {
               String var6 = var4.startsWith("_") ? var4.substring(1) : "_" + var4;
               var5 = (axp)var2.findRoutineByName(var6, this.za.getType());
               if (var5 == null && this.TX != null) {
                  IUnmangledRoutine var7 = this.TX.unmangleRoutine(var4, true);
                  if (var7 != null) {
                     String var8 = var7.getName();
                     if (var8 != null && !var8.isEmpty() && !var8.equals(var6)) {
                        var5 = (axp)var2.findRoutineByName(var8, this.za.getType());
                     }
                  }
               }
            }

            return var5;
         }
      }
   }

   void q(List var1) {
      this.mI = var1;

      for (PA var3 : var1) {
         var3.RF(this.za.getInstructionAlignment());
         var3.q(this.za.getInstructionAlignment());
      }
   }

   void RF(List var1) {
      this.jq = var1;
   }

   void xK(List var1) {
      this.ui = var1;
   }

   void q(boolean var1, boolean var2) {
      this.q(var1, var1, var2, var2);
   }

   void q(boolean var1, boolean var2, boolean var3, boolean var4) {
      if (var1) {
         this.q = true;
      }

      if (var2) {
         for (PA var6 : this.Me()) {
            var6.xK();
         }
      }

      if (var3) {
         this.LK = true;
      }

      if (var4) {
         for (PA var9 : this.jq) {
            var9.xK();
         }

         for (PA var10 : this.ui) {
            var10.xK();
         }
      }
   }

   public boolean q(Set var1, Set var2, Set var3) {
      while (!this.HF.RF()) {
         ou.eo var4 = this.HF.Dw();
         if ((var4.xK() & 32) == 0) {
            if (var1.contains(var4.q())) {
               this.HF.xK();
               continue;
            }

            if (var2.contains(var4.q()) && (var4.xK() & 1) != 0) {
               this.HF.xK();
               continue;
            }

            if (var3.contains(var4.q())) {
               this.HF.xK();
               continue;
            }
         }

         long var5 = var4.q().getAddress();
         if (this.JY.contains(var5)) {
            break;
         }

         this.HF.xK();
      }

      return !this.HF.RF();
   }

   private boolean oQ() {
      this.nf.q.verifyLocked();

      try {
         boolean var1 = false;
         if (this.CE == 1) {
            this.oQ.preprocessImage(this.Gf);
         }

         HashSet var2 = new HashSet();
         HashSet var3 = new HashSet();
         HashSet var4 = new HashSet();
         if (this.mI == null) {
            if (this.If != null) {
               this.q(this.If.za());
            } else {
               ArrayList var5 = new ArrayList();
               var5.add(new Rw(this));
               this.q(var5);
            }
         }

         if (this.ui == null && this.If != null) {
            this.RF(this.If.nf());
            this.xK(this.If.gP());
         }

         boolean var49 = false;
         ArrayList var6 = new ArrayList();
         long var7 = System.currentTimeMillis();

         label1319:
         while (true) {
            ou var10 = new ou();
            int var11 = 0;

            label1316:
            while (true) {
               boolean var9 = false;

               while (this.q(var2, var3, var4)) {
                  ArrayDeque var12 = new ArrayDeque();

                  while (this.q(var2, var3, var4)) {
                     ou.eo var13 = this.HF.xK();
                     if (var13.q() instanceof CodePointer) {
                        CodePointer var14 = (CodePointer)var13.q();
                        if ((var13.xK() & 1) == 0) {
                           var2.add(var14);
                        } else {
                           var3.add(var14);
                        }

                        int var15 = this.za.getMode();

                        try {
                           try {
                              this.za.setMode(var14.getMode());
                           } catch (ProcessorException var45) {
                           }

                           if (!var14.isUnknownAddress()) {
                              if (this.q(var14.getAddress(), var13.RF(), var13.xK())) {
                                 var49 = true;
                              } else if ((var13.xK() & 32) != 0) {
                                 axp var16 = this.nf.oW(var13.q().getAddress());
                                 if (var16 != null && var16.oW() != null) {
                                    var6.add(var16.oW());
                                    var49 = true;
                                 }
                              }
                           }
                        } finally {
                           try {
                              this.za.setMode(var15);
                           } catch (ProcessorException var43) {
                           }
                        }

                        if (System.currentTimeMillis() - var7 > 500L) {
                           try {
                              Thread.sleep(1L);
                           } catch (InterruptedException var47) {
                              var1 = true;
                              break label1316;
                           }

                           var7 = System.currentTimeMillis();
                        }
                     } else if (var13.q().getType() == 1) {
                        this.enqueuePointerForAnalysis(this.za.createEntryPoint(var13.q().getAddress()));
                     } else {
                        if (var13.q().getType() == 5) {
                           var4.add(var13.q());

                           try {
                              long var66 = this.gP.readPointer(var13.q().getAddress());
                              this.enqueuePointerForAnalysis(this.za.createEntryPoint(var66));
                           } catch (MemoryException var44) {
                           }
                        } else if (var13.q().getType() == 6) {
                           var4.add(var13.q());
                        } else if (var13.q().getType() == 2) {
                           var4.add(var13.q());
                        }

                        var12.add(var13);
                     }
                  }

                  while (!var12.isEmpty()) {
                     ou.eo var60 = (ou.eo)var12.pop();
                     int var67 = var60.RF();
                     long var74 = var60.q().getAddress();
                     int var17 = var60.q().getSize();
                     if (var17 == 0 && var60.q().getType() == 0) {
                        CodePointer var18 = (CodePointer)((PA)this.mI.get(var11)).q(var74, var74 + 64L, false);
                        if (var18 != null) {
                           int var94 = 0;
                           if (this.If != null) {
                              var94 = this.If.JY();
                           }

                           this.enqueuePointerForAnalysis(var18, var94);
                           continue;
                        }
                     }

                     if (var67 < 1 && !this.nf.isEmptyRange(var74, Math.max(1, var17))) {
                        if (var60.q().getType() == 2) {
                           var4.remove(var60.q());
                        } else {
                           var10.q(var60.q(), var60.RF(), var60.q().getSize());
                        }
                     } else {
                        if (var17 == 0 && this.RF) {
                           if (this.nf.getItemOver(var74) instanceof axw) {
                              continue;
                           }

                           axw var89 = this.qa().q(var74, -1L);
                           if (var89 != null) {
                              continue;
                           }
                        }

                        INativeDataItem var90 = null;
                        if (var17 == this.zz.getPointerSize()) {
                           for (PA var20 : this.ui) {
                              var90 = (INativeDataItem)var20.q(var74, var74 + var17, false);
                              if (var90 != null) {
                                 break;
                              }
                           }
                        }

                        if (var90 == null) {
                           Object var93;
                           if (var17 != this.zz.getPointerSize() || var60.q().getType() != 5 && var60.q().getType() != 6) {
                              var93 = this.zz.q(var17 == 0 ? 1 : var17, false);
                           } else {
                              var93 = this.zz.gP();
                           }

                           if ((var60.xK() & 4096) != 0) {
                              Object var98 = this.zz.Uv(((bbd)var93).getName() + "_transient");
                              if (var98 == null) {
                                 var98 = this.zz.q(((bbd)var93).getName() + "_transient", (INativeType)var93);
                                 ((bbd)var98).addFlags(128);
                              }

                              var93 = var98;
                           }

                           INativeDataItem var99 = this.Hk().q(var74, null, var17, (INativeType)var93, false);
                           if (var99 == null) {
                              var10.q(var60.q(), var60.RF(), var60.q().getSize());
                           }
                        }
                     }
                  }
               }

               if (this.q) {
                  aad var55 = ((PA)this.mI.get(var11)).RF();
                  if (var55 != null) {
                     int var61 = 0;
                     if (this.If != null) {
                        var61 = this.If.JY();
                     }

                     int var68 = aaw.q(CodeGapAnalysisStyle.PROLOGUES_ONLY);
                     List var75 = (List)var55.RF();
                     if (var75 != null) {
                        for (CodePointer var84 : var75) {
                           if (var84 == null) {
                              var9 = true;
                           } else {
                              long var91 = var84.getAddress();
                              if (this.Uv().getPrologueLooking(var91, var91 + 4L).getResult() != null) {
                                 var61 = var68;
                              }

                              this.enqueuePointerForAnalysis(var84, var61);
                              Object[] var10000 = new Object[]{var84};
                           }
                        }
                     }
                  }
               }

               if (this.HF.RF() && var11 + 1 < this.mI.size()) {
                  var9 = true;
                  var11++;
                  Object[] var124 = new Object[0];
                  if (this.If != null) {
                     this.If.RF(var11);
                  }

                  while (!var10.RF()) {
                     ou.eo var56 = var10.xK();
                     this.HF.q(var56.q(), var56.RF(), var56.q().getSize());
                  }
               }

               if (this.HF.RF() && !var9) {
                  if (this.LK) {
                     aad var69;
                     for (PA var62 : this.jq) {
                        do {
                           var69 = var62.RF();
                        } while (var69 != null);
                     }

                     for (PA var63 : this.ui) {
                        while (true) {
                           var69 = var63.RF();
                           if (var69 == null) {
                              break;
                           }

                           List var76 = (List)var69.RF();
                           if (var76 != null) {
                              for (INativeDataItem var85 : var76) {
                                 Object[] var125 = new Object[]{var69.q(), var85};
                              }
                           }
                        }
                     }
                  }

                  this.q = false;
                  this.LK = false;
                  if (!var49) {
                     return false;
                  }

                  for (Xg var82 : new ArrayList(this.nf.lm())) {
                     long var86 = var82.q;
                     kR var95 = this.nf.lm(var86);
                     if (var95 != null) {
                        Assert.a(var95.q == var86, "Conflict for bases. Expected: %Xh but was %Xh", var95.q, var86);
                        if (var82.q() == null && !this.nf.xK(var86) && var95.Uv.size() >= 2) {
                           boolean var100 = true;
                           if (var100 && !var82.Uv && var95.Uv.size() == 2) {
                              Xg var21 = (Xg)var95.Uv.get(0);
                              if (var21 == var82) {
                                 var21 = (Xg)var95.Uv.get(1);
                              }

                              String var22 = this.nf.LK().getLabel(var86, 0L, AutoLabelPolicy.OFF);
                              if (var22 == null) {
                                 boolean var23 = true;
                                 Set var24 = this.nf.HF().getReferencesTo(var86);
                                 if (var24.isEmpty()) {
                                    var23 = false;
                                 } else {
                                    for (abk var26 : var24) {
                                       ReferenceLocation var27 = (ReferenceLocation)var26.RF();
                                       if (!var27.isInternalAddress()
                                          || !this.nf.Dw(var27.getInternalAddress(), true).contains(var21.q)
                                          || var26.getType() == ReferenceType.ROUTINE_CALL) {
                                          var23 = false;
                                          break;
                                       }
                                    }
                                 }

                                 if (var23) {
                                    for (kR var122 : var82.RF) {
                                       var122.Uv.remove(var82);
                                    }

                                    Object[] var126 = new Object[]{var86, var21.q};
                                    this.nf.gO(var86);
                                    var2.remove(new CodePointer(var86, this.za.getMode()));
                                    var82.q = -1L;
                                    var82.RF = null;
                                    continue;
                                 }
                              }
                           }

                           ArrayList var103 = new ArrayList(var95.Uv);
                           var103.remove(var82);

                           for (Xg var112 : var103) {
                              for (kR var117 : var112.RF) {
                                 if (!var82.RF.contains(var117) && var117.xK(var86)) {
                                    var117.q(Long.valueOf(var86));
                                 }
                              }
                           }

                           for (Xg var113 : var103) {
                              List var116 = this.nf.q(var113.q, true, null, true);
                              if (var116 == null) {
                                 xK.error(S.L("Expected a routine at: %Xh"), var113.q);
                              } else {
                                 for (kR var121 : var113.RF) {
                                    if (!var116.contains(var121)) {
                                       var121.Uv.remove(var113);
                                    }
                                 }

                                 var113.RF = var116;
                                 var113.xK = null;
                              }
                           }
                        }
                     }
                  }

                  Object[] var127 = new Object[0];

                  label1129:
                  for (Xg var72 : this.nf.lm()) {
                     long var78 = var72.q;
                     if (var72.q() == null || !this.nf.xK(var78)) {
                        axo var87 = this.nf.Dw(var78);
                        if (var87 != null) {
                           var87.oW(true);
                           var87.PV();
                        }

                        CFG var92 = var72.q();
                        if (var92 == null) {
                           var127 = new Object[]{var72.q, var72.RF.size(), var72.RF};
                           ArrayList var96 = new ArrayList(var72.RF.size());

                           for (kR var104 : var72.RF) {
                              BasicBlock var109 = var104.RF();
                              if (var109 == null) {
                                 xK.catchingSilent(new JebRuntimeException("Basic Block is empty at " + Long.toHexString(var78) + "h"));
                                 xK.error(S.L("Routine: %X can not be built"), var72.q);
                                 continue label1129;
                              }

                              var96.add(var109);
                              var104.q(var109);
                           }

                           var92 = new CFG(var72.q, var96);
                           var72.xK = var92;
                        }

                        String var97 = this.nf.LK().getLabel(var72.q, 0L, AutoLabelPolicy.OFF);
                        if (var97 == null) {
                           var97 = this.nf.LK().q(var72.q);
                        }

                        axo var102 = new axo(var92, this.nf, this.zz, var97);
                        var6.add(var102);

                        for (AddressableInstruction var110 : var92.addressableInstructions()) {
                           long var114 = var110.getOffset();
                           INativeContinuousItem var120 = this.nf.getItemAt(var114);
                           if (var120 instanceof axn) {
                              int var123 = ((axn)var120).Uv();
                              if (var123 >= 1) {
                                 var102.gO(true);
                                 break;
                              }
                           }
                        }

                        for (abk var111 : (Set)this.nf
                           .HF()
                           .getReferencesTo(var72.q)
                           .stream()
                           .filter(var0 -> var0.getFrom().isInternalAddress() && var0.getType().isCode() && var0.getType() != ReferenceType.ROUTINE_CALL)
                           .collect(Collectors.toSet())) {
                           this.nf
                              .getCallGraphManager()
                              .getGlobalCallGraph()
                              .recordInternalCall(var111.getFrom().getInternalAddress(), this.za.createEntryPoint(var72.q), true);
                        }

                        if (var72.Dw != null && !var72.Dw.isEmpty()) {
                           var102.q(var72.Dw);
                        }

                        this.nf.q(var102);
                     }
                  }

                  if (this.io) {
                     this.LK();
                     boolean var65 = true;
                     if (var65 && this.Dw(var6)) {
                        var6.removeIf(var0 -> var0.isDisposed());
                     }

                     for (axo var79 : var6) {
                        if (!var79.isDisposed()) {
                           CFG var83 = var79.getCFG();
                           Integer var88 = (Integer)this.oQ.determineRoutineStackPointerDelta(var83).getResult();
                           if (var88 != null) {
                              var79.setSPDeltaOnReturn(var88);
                           }
                        }
                     }
                  }

                  if (this.q(var2, var3, var4)) {
                     continue label1319;
                  }
                  break;
               }
            }

            if (var1) {
               throw new InterruptionException("The code analysis was interrupted");
            }

            for (PA var53 : this.mI) {
               Object[] var129 = new Object[]{var53.nf(), TimeFormatter.formatTimestampDelta(var53.q())};
            }

            for (PA var54 : this.ui) {
               Object[] var130 = new Object[]{var54.nf(), TimeFormatter.formatTimestampDelta(var54.q())};
            }

            return true;
         }
      } finally {
         if (this.CE == 1) {
            this.xW();
            this.oQ.postprocessImage(this.Gf);
         }

         this.q(false, true, false, true);
      }
   }

   public void LK() {
      for (axo var2 : this.nf.nf()) {
         if (var2.oW() == null) {
            CFG var3 = var2.getCFG();
            Pointer var4 = (Pointer)this.oQ.getTrampolineTarget(var3).getResult();
            if (var4 != null && var4.getType() == 5) {
               INativeContinuousItem var5 = this.nf.getItemAt(var4.getAddress());
               if (var5 instanceof axu) {
                  this.q(var2, (axu)var5, false);
               } else {
                  axp var6 = this.nf.oW(var4.getAddress());
                  if (var6 != null) {
                     this.q(var2, var6);
                  }
               }
            }
         }
      }
   }

   private void xW() {
      if (!this.sH) {
         try {
            HashMap var1 = new HashMap();
            Collection var2 = this.zz().HF();

            for (axp var4 : var2) {
               if (var4.isPlaceholderMethod() && var4.IN() != null) {
                  var1.put(var4.IN(), null);
               }
            }

            for (axp var11 : var2) {
               if (!var11.isPlaceholderMethod() && var11.IN() != null && var1.containsKey(var11.IN())) {
                  var1.put(var11.IN(), var11);
               }
            }

            for (axp var12 : var2) {
               if (var12.isPlaceholderMethod() && var12.IN() != null) {
                  axp var5 = (axp)var1.get(var12.IN());
                  if (var5 != null) {
                     var12.q(new NativeItemEvent(NativeItemEventType.UPDATED, var12, NativeItemEventSubType.ROUTINE_SET, var5));
                     var12.PV();
                  }
               }
            }
         } finally {
            this.sH = true;
         }
      }
   }

   private boolean Dw(List var1) {
      if (this.eJ == null) {
         this.eJ = new abb(this);
      }

      return this.eJ.q(var1);
   }

   private void q(axo var1, axp var2) {
      if (var2 != null) {
         if (var1.oW() == null || var1.oW() != var2) {
            var1.xK(var2);
            this.q(var1, var2, false);
         }
      }
   }

   public void q(axo var1, axu var2, boolean var3) {
      if (var2.cC() != null) {
         axp var4 = (axp)var2.cC();
         if (var1.oW() == null || var1.oW() != var2.cC()) {
            boolean var5 = false;
            String var6 = var3 ? "r" : "";
            if (this.If != null && this.If.oW()) {
               String var7 = var4.ui();
               if (var7 != null) {
                  var1.setName(var6 + "→" + var7);
                  axp var8 = var1.xK();
                  if (var8 != null) {
                     if (var4.mI() != null) {
                        var8.q(var4.mI());
                     }

                     if (var4.ui() != null) {
                        var8.xK(var4.ui());
                     }

                     if (var4.jq() != null) {
                        var8.RF(var4.jq());
                     }
                  }

                  var5 = true;
               }
            }

            if (!var5) {
               String var9 = var2.getLabel();
               if (var9 != null) {
                  if (var9.startsWith("ptr_")) {
                     var9 = var9.substring(4);
                  }

                  var1.setName(var6 + "→" + var9);
               }
            }

            var1.xK(var4);
            this.q(var1, var4, true);
         }
      }
   }

   private void q(axo var1, axp var2, boolean var3) {
      String var4 = var1.getName();
      if (var4 == null) {
         JebCoreService.notifySilentExceptionToClient(new RuntimeException("Unexpected null routine name"));
      } else {
         String var5 = var2.getName();
         if (var5 == null) {
            JebCoreService.notifySilentExceptionToClient(new RuntimeException("Unexpected null target name"));
         } else if (!var5.equals("*" + var4) && !var4.equals("→" + var5)) {
            if (v.xK(var4)) {
               var1.setName(this.RF("→" + var5));
            } else if (v.xK(var5)) {
               var2.setName(this.RF("*" + var4));
            } else if ((var4.startsWith("→") || var4.startsWith("*")) && (!var3 || var4.charAt(1) == '_' && var4.charAt(2) == 'Z')) {
               var1.setName(this.RF("→" + var5));
            }
         }
      }
   }

   private String RF(String var1) {
      return !var1.startsWith("→*") && !var1.startsWith("*→") ? var1 : var1.substring(2);
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private IInstruction RF(long var1, int var3) {
      boolean var13 = false /* VF: Semaphore variable */;

      IInstruction var5;
      label78: {
         try {
            var13 = true;
            if (var3 != -1) {
               var3 = this.za.setMode(var3);
            }

            IInstruction var4 = this.za.parseAt(this.gP, var1);
            var5 = var4;
            var13 = false;
            break label78;
         } catch (Exception var17) {
            var13 = false;
         } finally {
            if (var13) {
               if (var3 != -1) {
                  try {
                     this.za.setMode(var3);
                  } catch (ProcessorException var14) {
                  }
               }
            }
         }

         Object var6 = null;
         if (var3 != -1) {
            try {
               this.za.setMode(var3);
            } catch (ProcessorException var15) {
            }
         }

         return (IInstruction)var6;
      }

      if (var3 != -1) {
         try {
            this.za.setMode(var3);
         } catch (ProcessorException var16) {
         }
      }

      return var5;
   }

   private boolean q(long var1, int var3, int var4) {
      this.nf.q.verifyLocked();
      if (this.nf.nf(var1) != null) {
         boolean var5 = false;
         if (var3 >= 2) {
            var5 = this.nf.RF(var1, true);
         }

         if (!var5) {
            return false;
         }
      }

      return this.RF(var1, var3, var4);
   }

   private static void q(long var0, String var2, Object... var3) {
   }

   private static void RF(long var0, String var2, Object... var3) {
   }

   private static String RF(int var0) {
      if (var0 == 0) {
         return "GENTLE";
      } else if (var0 == 1) {
         return "FORCEFUL";
      } else if (var0 == 2) {
         return "DIRTY";
      } else {
         return var0 == 3 ? "GOD" : "UNK_" + var0;
      }
   }

   private static String xK(int var0) {
      StringBuilder var1 = new StringBuilder();

      while (var0 != 0) {
         if ((var0 & 1) != 0) {
            var1.append("NO_ROUTINE ");
            var0 &= -2;
         } else {
            Strings.ff(var1, "UNK_0x%X", var0);
            var0 = 0;
         }
      }

      return var1.toString();
   }

   private boolean RF(long var1, int var3, int var4) {
      this.nf.q.verifyLocked();
      Object[] var10000 = new Object[]{++this.ZT, RF(var3), xK(var4), var1};
      this.q(S.L("Routine: %Xh"), var1);
      kR var5 = null;
      ArrayList var6 = new ArrayList();
      ArrayDeque var7 = new ArrayDeque();
      var7.add(new CodePointer(var1, this.za.getMode()));
      int var8 = 0;
      long var9 = 0L;
      boolean var11 = false;
      boolean var12 = true;
      HashSet var13 = new HashSet();
      int var14 = 0;
      long var15 = 0L;
      HashMap var19 = new HashMap();
      LinkedHashMap var20 = new LinkedHashMap();
      ArrayList var21 = new ArrayList();
      boolean var22 = (var4 & 1) != 0;
      if ((var4 & 2) != 0) {
         var9 = var1;
         long var23 = var1 + (var4 >>> 8 & 16777215L);

         while (var9 < var23) {
            axn var25 = this.q(var9, var3);
            if (var25 == null) {
               return false;
            }

            var9 += var25.getMemorySize();
         }
      }

      label633:
      while (true) {
         IInstruction var44 = null;
         if (var8 == 0) {
            if (var5 != null && !var5.RF.isEmpty() && !var11) {
               this.oQ.determinePotentialPointersInProtoBlock(var5, var21);

               for (PointerLocation var54 : var21) {
                  if (!q(var7, var54.getPointer().getAddress())) {
                     this.enqueuePointerForAnalysis(var54.getPointer(), 0);
                  }

                  long var26 = var54.getPointer().getAddress();
                  INativeContinuousItem var28 = this.nf.getItemOver(var26);
                  if (var28 != null
                     && var28.getMemoryAddress() != var26
                     && var28 instanceof axq
                     && (var26 - var28.getMemoryAddress()) % ((axq)var28).wF().getSize() == 0L) {
                     var26 = var28.getMemoryAddress();
                     var10000 = new Object[]{var54.getLocation(), var26, var28.getMemoryAddress()};
                  }

                  this.nf.HF().recordInternalReference(var54.getLocation(), var26, ReferenceType.GEN_DATA);
               }

               var21.clear();
            }

            if (var7.isEmpty()) {
               for (Entry var55 : var20.entrySet()) {
                  this.q((Long)var55.getKey(), (kR)var55.getValue(), var7, var6);
               }

               var20.clear();
               if (var7.isEmpty()) {
                  break;
               }
            }

            CodePointer var50 = (CodePointer)var7.pop();
            if (var50.getMode() != 0 && var50.getMode() != this.za.getMode()) {
               try {
                  this.za.setMode(var50.getMode());
               } catch (ProcessorException var42) {
                  xK.catchingSilent(var42);
               }
            }

            var9 = var50.getAddress();
         }

         INativeContinuousItem var51 = this.nf.getItemOver(var9);
         if (var51 == null) {
            if (var8 == 0) {
               var5 = new kR(var9);
               var6.add(var5);
               var8 = 1;
               var15 = 0L;
            }
         } else if (var51.getMemoryAddress() == var9) {
            if (var51 instanceof axn) {
               kR var56 = this.nf.lm(var9);
               if (var56 == null) {
                  var56 = (kR)var19.get(var9);
               }

               if (var56 != null) {
                  if (var8 < 0) {
                     var10000 = new Object[0];
                     var44 = ((axn)var51).getInstruction();
                     int var65 = this.za.getMode() == 0 ? this.za.getDefaultMode() : this.za.getMode();
                     int var70 = var44.getProcessorMode() == 0 ? this.za.getDefaultMode() : var44.getProcessorMode();
                     if (var65 != var70) {
                        var10000 = new Object[]{var65, var70};
                        var11 = true;
                        var8 = 0;
                     } else {
                        var8++;
                        var5.RF.add(var44);
                        var14++;
                        if (var8 != 0) {
                           var9 += var44.getSize();
                        }
                     }
                     continue;
                  }

                  var44 = ((axn)var51).getInstruction();
                  int var64 = this.za.getMode() == 0 ? this.za.getDefaultMode() : this.za.getMode();
                  int var69 = var44.getProcessorMode() == 0 ? this.za.getDefaultMode() : var44.getProcessorMode();
                  if (var64 != var69) {
                     var10000 = new Object[]{var64, var69};
                     var11 = true;
                     var8 = 0;
                     continue;
                  }

                  if (var56.q == var9) {
                     var56.Uv.isEmpty();
                     if (var8 != 0) {
                        var5.RF(var9);
                        var8 = 0;
                        IInstruction var75 = (IInstruction)var5.RF.get(var5.RF.size() - 1);
                        this.nf.HF().recordInternalReference(var9 - var75.getSize(), var9, ReferenceType.GEN_CODE);
                     }
                     continue;
                  }

                  boolean var74 = false;
                  if (var56.gO > 0L && var9 == var56.gO) {
                     var74 = true;
                  } else if (var56.gO > 0L && var56.getLastAddress() == var9 && var56.getInsntructions().size() > 1) {
                     int var29 = var56.getInsntructions().size() - 2;
                     IInstruction var30 = (IInstruction)var56.getInsntructions().get(var29);
                     long var31 = var56.q(var29);
                     var74 = this.q(var30, var31);
                  }

                  if (var74) {
                     var10000 = new Object[0];
                     this.nf.za(var9);
                     this.nf.q(var51, true);
                     var5 = new kR(var9);
                     var6.add(var5);
                     var8 = 1;
                     var15 = 0L;
                  } else {
                     kR var79 = this.q(var56, var9, var22);
                     if (var79 == null) {
                        var10000 = new Object[0];
                        var11 = true;
                        var8 = 0;
                     } else {
                        var6.add(var79);
                        var56.Uv.isEmpty();
                        if (var8 != 0) {
                           var5.RF(var9);
                           IInstruction var83 = (IInstruction)var5.RF.get(var5.RF.size() - 1);
                           this.nf.HF().recordInternalReference(var9 - var83.getSize(), var9, ReferenceType.GEN_CODE);
                        }

                        var8 = 0;
                     }
                  }
                  continue;
               }

               var44 = ((axn)var51).getInstruction();
               int var63 = this.za.getMode() == 0 ? this.za.getDefaultMode() : this.za.getMode();
               int var27 = var44.getProcessorMode() == 0 ? this.za.getDefaultMode() : var44.getProcessorMode();
               if (var63 != var27) {
                  var10000 = new Object[0];
                  this.q(var9, true);
                  var44 = null;
               }
            } else {
               if (var3 < 1 || !(var51 instanceof INativeDataItem)) {
                  var10000 = new Object[0];
                  var11 = true;
                  var8 = 0;
                  continue;
               }

               if (var51 instanceof axu var57 && var57.cC() != null && var57.cC().getData() == null) {
                  var10000 = new Object[0];
                  var11 = true;
                  var12 = false;
                  var8 = 0;
                  continue;
               }

               this.q(var9, true);
            }
         } else {
            if (var3 < 1) {
               var10000 = new Object[]{var51};
               var11 = true;
               var8 = 0;
               continue;
            }

            var10000 = new Object[0];
            this.q(var9, false);
         }

         if (var44 == null) {
            axn var58 = this.q(var9, var3);
            if (var58 == null) {
               var10000 = new Object[0];
               if (this.If != null) {
                  this.If.q(aaw.eo.RF, var1);
               }

               if (var3 < 2 && var14 < Uv) {
                  var11 = true;
               } else {
                  var13.add(var9);
               }

               var8 = 0;
               continue;
            }

            var44 = var58.getInstruction();
         }

         if (var8 == 0) {
            var5 = new kR(var9);
            var6.add(var5);
            var8 = 1;
            var15 = 0L;
         } else {
            if (var5 == null) {
               var10000 = new Object[0];
               var11 = true;
               break;
            }

            var8++;
         }

         var5.RF.add(var44);
         if (var22) {
            var19.put(var9, var5);
         } else {
            this.nf.q(var9, var5);
         }

         var14++;
         if (Dw > 0) {
            byte[] var59 = var44.getCode();
            if (ArrayUtil.isSled(var59, (byte)0)) {
               long var17 = var9 + var59.length;
               if (var15 == 0L) {
                  var15 = var9;
               }

               if (var17 - var15 >= Dw) {
                  var10000 = new Object[0];
                  long var66 = var17;

                  while (true) {
                     if (var66 > var15) {
                        IInstruction var76 = (IInstruction)var5.RF.get(var5.RF.size() - 1);
                        byte[] var80 = var76.getCode();
                        if (ArrayUtil.isSled(var80, (byte)0)) {
                           var66 -= var80.length;
                           this.q(var66, true);
                           this.nf.za(var66);
                           var5.RF.remove(var5.RF.size() - 1);
                           continue;
                        }
                     }

                     if (var5.RF.isEmpty()) {
                        var6.remove(var5);
                     }

                     var66 = (var17 - var15) / var59.length;
                     if (var14 - var66 < Uv) {
                        var10000 = new Object[0];
                        var11 = true;
                     } else {
                        var13.add(var9);
                     }

                     var8 = 0;
                     continue label633;
                  }
               }
            } else {
               var15 = 0L;
            }
         }

         boolean var60 = false;
         boolean var68 = false;
         if (!var44.getInstructionFlags().contains(InstructionFlags.CONDITIONAL_EXEC)) {
            for (INativeMethodItem var81 : this.nf.getCallGraphManager().getGlobalCallGraph().getCalleeRoutines(var9, !this.If.Hk())) {
               Boolean var84 = ((axp)var81).getNonReturning();
               if (Boolean.TRUE.equals(var84)) {
                  var60 = true;
                  break;
               }

               if (!var68 && var84 != null) {
                  var68 = true;
               }
            }
         }

         if (!var60) {
            var60 = (Boolean)this.oQ.shouldForceRoutineEnd(var9, var44).getResult();
         }

         long var72 = var9 + var44.getSize();
         if (var3 < 3 && this.Me != null && this.Me.count() != 0 && !this.Me.contains(var72)) {
            var60 = true;
         }

         byte var82 = 0;
         boolean var85 = false;
         boolean var86 = false;
         boolean var32 = false;
         boolean var33 = false;
         INativeContinuousItem var34 = this.nf.getItemAt(var9);
         if (var34 instanceof axn) {
            InstructionHints var35 = ((axn)var34).getHints(false);
            if (var35 != null) {
               var85 = var35.isFakeCall();
               var86 = var35.isActualCall();
               var32 = var35.isTailCall();
               var33 = var35.isCondTailCall();
            }
         }

         IFlowInformation var87 = this.q(var9, var44);
         if (var87.isBroken()) {
            var82 = 1;
            if (var3 < 3 && this.If != null && this.If.q(var3) && !this.q(var9, var87)) {
               var10000 = new Object[0];
               var11 = true;
               break;
            }

            ReferenceType var36 = var87.getTargets().size() > 1 ? ReferenceType.COND_BRANCH : ReferenceType.BRANCH;
            Long var37;
            if (var87.mustComputeFallThrough()) {
               var36 = ReferenceType.COND_BRANCH;
               var37 = this.q(var72, var87, var3);
               if (var37 == null) {
                  if (var14 < Uv) {
                     var10000 = new Object[0];
                     var11 = true;
                  } else {
                     var13.add(var9);
                  }
                  break;
               }

               if (!var60) {
                  var5.RF(var37);
                  var7.push(new CodePointer(var37, this.za.getMode()));
                  this.nf.HF().recordInternalReference(var9, var37, var36);
               }
            } else {
               var37 = var72;
            }

            boolean var38 = false;
            if (var87.isBrokenKnown() && var87.getTargets().size() >= 2) {
               qz var39 = this.nf.HF(var9);
               if (var39.isResolved()) {
                  BranchTarget var40 = var39.q();
                  if (var40.isInternal()) {
                     var38 = true;
                  }
               }
            }

            if (!var38) {
               for (ICodePointer var106 : var87.getTargets()) {
                  if (var106.isUnknownAddress()) {
                     var5.nf = true;
                  } else {
                     if ((!var33 || var106.getAddress() == var9 + var44.getSize()) && !var32 && !var86 && this.nf.oW(var106.getAddress()) == null) {
                        INativeContinuousItem var109 = this.nf.getItemAt(var106.getAddress());
                        if (var109 instanceof axu && ((axu)var109).cC().getData() == null) {
                           var5.nf = true;
                           this.nf.getCallGraphManager().getGlobalCallGraph().recordExternalCall(var9, ((axu)var109).cC(), true);
                        } else {
                           var5.RF(var106.getAddress());
                           var7.push(new CodePointer(var106));
                        }
                     } else {
                        CodePointer var41 = new CodePointer(var106);
                        this.enqueuePointerForAnalysis(var41, this.RF(var3, var4), var4 & 1);
                        this.nf.getCallGraphManager().getGlobalCallGraph().recordInternalCall(var9, var41, true);
                     }

                     this.nf.HF().recordInternalReference(var9, var106.getAddress(), var36);
                  }
               }
            }

            if (var86) {
               axp var100 = this.nf.oW(var37);
               if (var100 != null && var3 >= 2) {
                  this.nf.RF(var37, true);
               }

               var5.RF(var37);
               var7.push(new CodePointer(var37, this.za.getMode()));
               var5.gO = var37;
               var8 = -var87.getDelaySlotCount();
            } else {
               var5.gO = var72;
               var8 = -var87.getDelaySlotCount();
            }
         }

         var87 = var44.getRoutineCall(var9);
         if (var87.isBroken()) {
            var82 = 2;
            if (var3 < 3 && this.If != null && this.If.q(var3) && !this.q(var9, var87)) {
               var10000 = new Object[0];
               var11 = true;
               break;
            }

            Long var89 = this.q(var72, var87, var3);
            if (var89 == null) {
               if (var14 < Uv) {
                  var10000 = new Object[0];
                  var11 = true;
               } else {
                  var13.add(var9);
               }
               break;
            }

            for (ICodePointer var94 : var87.getTargets()) {
               if (var94.getAddress() != var89 || var94.getMode() != this.za.getMode()) {
                  if (var94.isUnknownAddress()) {
                     var5.nf = true;
                  } else {
                     if (!var60 && !var68 && !var44.getInstructionFlags().contains(InstructionFlags.CONDITIONAL_EXEC)) {
                        String var101 = this.nf.LK().getLabel(var94.getAddress(), 0L, AutoLabelPolicy.OFF);
                        if (var101 != null) {
                           axp var107 = this.q(var101);
                           if (var107 != null) {
                              bbs var110 = var107.Uv();
                              if (var110.getPrototypeAttributes().contains(PrototypeAttribute.NoReturn)) {
                                 var60 = true;
                              }
                           }
                        }

                        if (this.eJ != null && this.eJ.q(var94.getAddress())) {
                           var60 = true;
                        }

                        if (var85) {
                           var60 = true;
                        }
                     }

                     if (var85) {
                        var5.RF(var94.getAddress());
                        var7.push(new CodePointer(var94));
                        this.nf.HF().recordInternalReference(var9, var94.getAddress(), ReferenceType.BRANCH);
                     } else {
                        int var102 = var94.getMode() == 0 ? this.za.getMode() : var94.getMode();
                        this.enqueuePointerForAnalysis(new CodePointer(var94.getAddress(), var102), this.RF(var3, var4), var4 & 1);
                        this.nf.HF().recordInternalReference(var9, var94.getAddress(), ReferenceType.ROUTINE_CALL);
                     }
                  }
               }
            }

            if (!var60) {
               var5.RF(var89);
               var7.push(new CodePointer(var89, this.za.getMode()));
            }

            var5.gO = var89;
            var8 = -var87.getDelaySlotCount();
         }

         IFlowInformation var91 = var44.collectIndirectCallReferences(var9);
         if (var91.isBroken()) {
            for (ICodePointer var103 : var91.getTargets()) {
               if (!var103.isUnknownAddress()) {
                  this.enqueuePointerForAnalysis(new Pointer(var103.getAddress(), this.za.getMode() / 8, 2));
                  this.nf.HF().recordInternalReference(var9, var103.getAddress(), ReferenceType.READ_DATA);
               }
            }
         }

         if (var82 != 0) {
            qz var92 = this.nf.HF(var9);

            for (BranchTarget var104 : var92.getTargets()) {
               if (var104.isInternal()) {
                  CodePointer var108 = var104.getInternalAddress();
                  axp var111 = this.nf.oW(var108.getAddress());
                  if (var111 != null && var82 == 1 && var3 >= 2 && this.nf.RF(var108.getAddress(), true)) {
                     var111 = null;
                  }

                  if (var111 != null) {
                     this.enqueuePointerForAnalysis(var108, this.RF(var3, var4));
                  } else {
                     var5.RF(var108.getAddress());
                     var7.push(var108);
                  }

                  this.nf.HF().recordInternalReference(var9, var108.getAddress(), ReferenceType.DYNAMIC_BRANCH);
               }
            }

            if ((Boolean)this.oQ.isCandidateSwitchDispatcher(var9, var44, var5).getResult()) {
               INativeContinuousItem var97 = this.nf.getItemAt(var9);
               if (var97 instanceof axn) {
                  ((axn)var97).q(1);
               }

               if (!this.q(var9, var5, var7, var6)) {
                  var20.put(var9, var5);
                  this.If.q(aaw.eo.Uv, var9);
               }
            }
         }

         if (var60 && var8 >= 0) {
            this.nf.zz(var9);
            var8 = 0;
         }

         if (var8 != 0) {
            this.nf.JY(var9);
         }

         if (var82 == 0) {
            ArrayList var93 = new ArrayList();
            this.q(var9, var44, var93);

            for (Pointer var105 : var93) {
               var21.add(new PointerLocation(var9, var105));
            }
         }

         if (var8 != 0) {
            var9 = var72;
         }
      }

      if (var22) {
         return false;
      } else if (var11) {
         for (kR var53 : var6) {
            if (var53.Uv.isEmpty()) {
               long var62 = var53.q;

               for (IInstruction var78 : var53.RF) {
                  this.nf.za(var62);
                  var62 += var78.getSize();
               }
            }
         }

         if (this.If != null && var12) {
            this.If.q(aaw.eo.RF, var1);
         }

         return false;
      } else {
         List var43 = this.nf.q(var1, true, var13, true);
         if (var43 == null) {
            return false;
         } else {
            Xg var47 = new Xg(var1);

            for (kR var61 : var43) {
               var47.RF.add(var61);
               var61.Uv.add(var47);
            }

            if (!var13.isEmpty()) {
               var47.Dw = var13;
            }

            this.nf.q(var47);
            if (var3 == 3 || (var4 & 64) != 0) {
               var47.Uv = true;
            }

            if (this.If != null) {
               this.If.q(aaw.eo.q, var1);
            }

            return true;
         }
      }
   }

   private IFlowInformation q(long var1, IInstruction var3) {
      ChainedOperationResult var4 = this.oQ.getPreferredBreakingFlow(var1, var3);
      IFlowInformation var5 = (IFlowInformation)var4.getResult();
      return var5 != null ? var5 : var3.getBreakingFlow(var1);
   }

   private boolean q(IInstruction var1, long var2) {
      IFlowInformation var4 = this.q(var2, var1);
      if (var4.isBroken() && var4.getDelaySlotCount() != 0) {
         return true;
      } else {
         var4 = var1.getRoutineCall(var2);
         return var4.isBroken() && var4.getDelaySlotCount() != 0;
      }
   }

   private int RF(int var1, int var2) {
      if ((var2 & 16) != 0) {
         return 2;
      } else if ((var2 & 8) != 0) {
         return 1;
      } else {
         return (var2 & 4) != 0 ? 0 : var1;
      }
   }

   private boolean q(long var1, IFlowInformation var3) {
      this.nf.q.verifyLocked();
      boolean var4 = true;
      if (this.Me != null && this.Me.contains(var1)) {
         for (ICodePointer var6 : var3.getTargets()) {
            if (!var6.isUnknownAddress() && !this.Me.contains(var6.getAddress())) {
               var4 = false;
               break;
            }
         }
      }

      return var4;
   }

   private boolean q(long var1, kR var3, Deque var4, List var5) {
      this.nf.q.verifyLocked();
      SwitchInformation var6 = (SwitchInformation)this.oQ.determineSwitchInformation(var1, var3, var5).getResult();
      if (var6 != null && !var6.isEmpty()) {
         Set var7 = (Set)var6.getCases()
            .stream()
            .filter(var0 -> var0.getCaseHandler() != null)
            .map(var0 -> var0.getCaseHandler().getAddress())
            .collect(Collectors.toSet());
         List var8 = var6.getJumpTables();
         LinkedHashMap var9 = new LinkedHashMap();

         for (int var10 = 0; var10 < var6.getCases().size(); var10++) {
            SwitchInformation.SwitchCaseInformation var11 = (SwitchInformation.SwitchCaseInformation)var6.getCases().get(var10);
            CodePointer var12 = var11.getCaseHandler();
            if (var12 != null) {
               Object var13 = (List)var9.get(var12);
               if (var13 == null) {
                  var13 = new ArrayList();
                  var9.put(var12, var13);
               }

               var13.add(var10);
            }

            if ((var8 == null || var8.isEmpty()) && var11.isJumpTableBased()) {
               Object var21;
               if (this.q(var11, var7)) {
                  var21 = this.zz.gP();
               } else {
                  var21 = this.zz.q(var11.getJumpTableEntrySize(), false);
               }

               this.defineData(var11.getJumpTableEntryAddress(), (INativeType)var21);
            }
         }

         for (Entry var17 : var9.entrySet()) {
            CodePointer var19 = (CodePointer)var17.getKey();
            if (!var3.xK(var19.getAddress())) {
               var3.RF(var19.getAddress());
               var4.push(new CodePointer(var19));
               this.nf.HF().recordInternalReference(var1, var19.getAddress(), ReferenceType.DYNAMIC_BRANCH);
               this.recordDynamicBranchTarget(var1, false, new abt(var19, (Collection)var17.getValue()), false);
            }
         }

         if (var8 != null && !var8.isEmpty()) {
            for (SwitchInformation.JumpTableInformation var18 : var8) {
               Object var20;
               if (this.q(var18, var7)) {
                  var20 = this.zz.gP();
               } else {
                  var20 = this.zz.q(var18.getEntrySize(), false);
               }

               int var22 = (int)(var18.getEndAddress() - var18.getStartAddress()) / ((bbd)var20).getSize();
               this.defineData(var18.getStartAddress(), this.zz.RF((INativeType)var20, var22));
               this.recordAnalysisComment(var18.getStartAddress(), Strings.ff("%sjump table for switch at 0x%x", var18.isSecondary() ? "secondary " : "", var1));
            }
         }

         INativeContinuousItem var16 = this.za().getItemAt(var1);
         if (var16 instanceof axn) {
            ((axn)var16).q(2);
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean q(SwitchInformation.SwitchCaseInformation var1, Set var2) {
      return this.q(var1.getJumpTableEntryAddress(), var1.getJumpTableEntrySize(), var1.getJumpTableEntryAddress() + var1.getJumpTableEntrySize(), var2);
   }

   private boolean q(SwitchInformation.JumpTableInformation var1, Set var2) {
      return this.q(var1.getStartAddress(), var1.getEntrySize(), var1.getEndAddress(), var2);
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private boolean q(long var1, int var3, long var4, Set var6) {
      if (var3 != this.zz.getPointerSize()) {
         return false;
      } else {
         long var7;
         try {
            var7 = var1;
         } catch (MemoryException var11) {
            return false;
         }

         while (true) {
            try {
               if (var7 >= var4) {
                  return true;
               }

               long var9 = this.gP.readPointer(var7);
               if (!var6.contains(var9)) {
                  return false;
               }
            } catch (MemoryException var12) {
               return false;
            }

            var7 += var3;
         }
      }
   }

   private static boolean q(Deque var0, long var1) {
      for (CodePointer var4 : var0) {
         if (var4.getAddress() == var1) {
            return true;
         }
      }

      return false;
   }

   private Long q(long var1, IFlowInformation var3, int var4) {
      this.nf.q.verifyLocked();
      long var5 = var1;
      int var7 = var3.getDelaySlotCount();

      while (var7 > 0) {
         axn var8 = this.q(var5, -1, var4, true);
         if (var8 == null) {
            Object[] var10000 = new Object[]{var5};
            return null;
         }

         var7--;
         var5 += var8.getInstruction().getSize();
      }

      return var5;
   }

   private boolean RF(long var1, IInstruction var3) {
      this.nf.q.verifyLocked();
      IFlowInformation var4 = var3.getRoutineCall(var1);
      if (!var4.isBroken()) {
         return false;
      } else {
         for (ICodePointer var6 : var4.getTargets()) {
            if (!var6.isUnknownAddress()) {
               INativeContinuousItem var7 = this.nf.getItemAt(var6.getAddress());
               if (var7 instanceof axu && ((axu)var7).cC().getData() == null) {
                  this.nf.getCallGraphManager().getGlobalCallGraph().recordExternalCall(var1, ((axu)var7).cC(), true);
               } else {
                  this.nf.getCallGraphManager().getGlobalCallGraph().recordInternalCall(var1, (CodePointer)var6, true);
               }
            }
         }

         if (var4.getTargets().size() == 0) {
            IFlowInformation var8 = var3.collectIndirectCallReferences(var1);
            if (var8.isBroken()) {
               for (ICodePointer var10 : var8.getTargets()) {
                  this.nf.getCallGraphManager().getGlobalCallGraph().recordUnresolvedCall(var1, var10.getAddress(), true);
               }
            }
         }

         return true;
      }
   }

   private int q(long var1, IInstruction var3, List var4) {
      if ((Boolean)this.oQ.determinePotentialPointers(var1, var3, var4).getResult()) {
         return 1;
      } else {
         for (int var5 = 0; var5 < var3.getOperands().length; var5++) {
            IInstructionOperand var6 = var3.getOperands()[var5];
            if (var6 instanceof IInstructionOperandGeneric) {
               int var7 = ((IInstructionOperandGeneric)var6).getOperandType();
               switch (var7) {
                  case 1:
                     boolean var18 = false;
                     long var19 = ((IInstructionOperandGeneric)var6).getOperandValue(var1);
                     int[] var20 = new int[1];
                     if (VirtualMemoryUtil.readLEIntSafe(this.gP, var19, var20) && var20[0] != 0) {
                        var18 = true;
                     } else if (this.getContainer() != null) {
                        ICodeObjectUnit var12 = this.getContainer();
                        long var13 = var19 - this.If.io();
                        if (CodeObjectUnitUtil.hasSymbolsAtRelativeAddress(var12, var13)) {
                           var18 = true;
                        } else {
                           ISegmentInformation var15 = CodeObjectUnitUtil.findSectionByRelativeAddress(var12, var13);
                           if (var15 != null && var15.getName() != null) {
                              String var16 = var15.getName();
                              if (var16.contains("bss") || var16.contains("data")) {
                                 var18 = true;
                              }
                           }
                        }
                     }

                     if (var18) {
                        var4.add(new Pointer(var19));
                     }
                     break;
                  case 2:
                  case 3:
                  case 5:
                     long var17 = ((IInstructionOperandGeneric)var6).getOperandValue(var1);
                     int var10 = 0;
                     if (var7 == 5) {
                        var10 = ((IInstructionOperandGeneric)var6).getOperandBitsize();
                     }

                     var4.add(new Pointer(var17, var10 / 8, 2));
                  case 4:
                  case 6:
                  case 7:
                  default:
                     break;
                  case 8:
                     IInstructionOperandCMA var8 = (IInstructionOperandCMA)var6;
                     if (var8.getMemoryBaseRegister() == -1L && var8.getMemoryDisplacement() != 0L) {
                        long var9 = var8.getMemoryDisplacement();
                        int var11 = var8.getOperandBitsize();
                        var4.add(new Pointer(var9, var11 / 8, 2));
                     }
               }
            }
         }

         return 0;
      }
   }

   private kR q(kR var1, long var2, boolean var4) {
      this.nf.q.verifyLocked();
      if (var1.gO > 0L && Longs.compareUnsigned(var2, var1.gO) >= 0) {
         return null;
      } else {
         kR var5 = var1.q(var2, var4);
         if (var5 == null) {
            return null;
         } else if (var4) {
            return var5;
         } else {
            long var6 = var2;

            for (int var8 = 0; var8 < var5.RF.size(); var8++) {
               IInstruction var9 = (IInstruction)var5.RF.get(var8);
               kR var10 = this.nf.lm(var6);
               if (var10 == null || var10 == var1) {
                  this.nf.q(var6, var5);
               }

               var6 += var9.getSize();
            }

            for (Long var14 : this.nf.getContainedRoutineAddresses(var2)) {
               axo var11 = this.nf.Dw(var14);
               var11.oW(true);
               var11.PV();
            }

            return var5;
         }
      }
   }

   public axn q(long var1, int var3) {
      return this.q(var1, -1, var3, true);
   }

   public axn q(long var1, int var3, int var4, boolean var5) {
      return this.q(var1, var3, var4, var5 ? 2 : 1);
   }

   public axn q(long var1, int var3, int var4, int var5) {
      if (var5 >= 0 && var5 <= 2) {
         axn var10;
         try (ACLock var6 = this.nf.q.a()) {
            if (this.nf.getItemAt(var1) instanceof axn var13) {
               return var13;
            }

            IInstruction var8 = this.RF(var1, var3);
            if (var8 == null) {
               return null;
            }

            if (var8.getInstructionFlags().contains(InstructionFlags.UNPREDICTABLE_INSN) && var4 < 3) {
               return null;
            }

            if (var5 == 1 && !this.nf.isEmptyRange(var1, var8.getSize())) {
               return null;
            }

            axn var9 = new axn(var1, var8);
            this.Uv().customizeInstructionItem(var9);
            if (var5 != 0) {
               this.q(var9);
               this.RF(var1, var8);
            }

            var10 = var9;
         }

         return var10;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public INativeDataItem defineData(long var1, INativeType var3) {
      return this.defineData(var1, var3, -1);
   }

   public INativeDataItem q(long var1, INativeType var3) {
      INativeDataItem var4 = this.defineData(var1, var3, -1);
      if (var4 instanceof axr) {
         for (INativeDataItem var6 : ((axr)var4).sH()) {
            if (var6.getType() instanceof bbt) {
               bbd var7 = ((bbt)var6.getType()).oW();
               if (var7.getSize() != 0) {
                  Long var8;
                  try {
                     var8 = this.gP.readPointer(var6.getMemoryAddress());
                  } catch (MemoryException var9) {
                     break;
                  }

                  if (var8 != 0L && this.getAnalysisRanges().contains(var8) && this.q(var8, var7) == null) {
                     return null;
                  }
               }
            }
         }
      }

      return var4;
   }

   @Override
   public INativeDataItem defineData(long var1, INativeType var3, int var4) {
      return this.q(var1, (bbd)var3, var4, false);
   }

   public INativeDataItem q(long var1, bbd var3, int var4, boolean var5) {
      if (var5 && this.If != null && this.If.KT() != null && this.If.KT().contains(var1)) {
         return null;
      } else {
         INativeContinuousItem var6 = this.nf.getItemAt(var1);
         if (var6 instanceof INativeDataItem && ((INativeDataItem)var6).getType() == var3) {
            return (INativeDataItem)var6;
         } else {
            axh var18;
            try (ACLock var7 = this.nf.q.a()) {
               axh var8 = this.lm.q(var1, var3, var4);
               if (var8 == null) {
                  return null;
               }

               if (TypeUtil.getNonAlias(var3) instanceof bbf) {
                  for (long var9 = var8.getMemoryAddress() + 1L; var9 < var8.getMemoryAddressEnd(); var9++) {
                     Set var11 = this.za().HF().getReferencesTo(var9);
                     if (!var11.isEmpty()) {
                        for (abk var13 : var11) {
                           Object[] var10000 = new Object[]{var13.getFrom(), var9, var8.getMemoryAddress()};
                           this.za().HF().recordReference(var13.getFrom(), ReferenceLocation.create(var8.getMemoryAddress()), var13.getType());
                        }

                        this.za().HF().unrecordAllReferencesTo(var9);
                     }
                  }
               }

               this.q((axg)var8);
               String var16 = this.nf.LK().getLabel(var1, 0L, AutoLabelPolicy.OFF);
               String var10 = Strings.ff("%s%X", "gvar_", var1);
               if (var16 != null) {
                  this.q((INativeDataItem)var8);
               }

               var8.q(var10);
               var18 = var8;
            }

            return var18;
         }
      }
   }

   public void q(INativeDataItem var1) {
      long var2 = var1.getMemoryAddress();
      String var4 = var1.getName(true);
      if (this.getUnmanglerService() != null) {
         IUnmangledData var5;
         if (var1 instanceof axu) {
            var5 = this.getUnmanglerService().unmangle(var4, true);
         } else {
            var5 = this.getUnmanglerService().unmangleData(var4, true);
         }

         if (var5 != null) {
            String var6 = var5.getFull();
            boolean var7 = false;

            try {
               if (this.zx == null) {
                  this.zx = new TypeStringParser(this.zz);
               }

               TypeStringParser.Decl var8 = this.zx.parseDeclaration(var6);
               if (var8 != null && !var8.getType().equals(var1.getType())) {
                  axh var9 = this.lm.q(var2, var8.getType(), -1);
                  if (var9 != null) {
                     var9.setName(var8.getName());
                     this.nf.q(var1);
                     this.q((axg)var9);
                     this.recordAnalysisComment(var2, "(mangled:'" + var4 + "')");
                     var7 = true;
                  }
               }
            } catch (TypeStringParseException var10) {
            }

            if (!var7 && var6 != null && !var6.isEmpty()) {
               if (this.nf.LK().isLegalLabel(var6)) {
                  var1.setName(var6);
                  this.recordAnalysisComment(var2, "(mangled:'" + var4 + "')");
               } else {
                  this.recordAnalysisComment(var2, "(unmangled:'" + var6 + "')");
               }
            }
         }
      }
   }

   public axw q(long var1, long var3, StringEncoding var5, int var6, int var7, boolean var8) {
      axw var11;
      try (ACLock var9 = this.nf.q.a()) {
         axw var10 = (axw)DataStringUtil.createFromMemory(this.lm, this.nf.LK(), this.gP, var1, var3, var5, var6, var7);
         if (var10 == null) {
            return null;
         }

         if (!var8 && !this.nf.isEmptyRange(var1, (int)var10.getMemorySize())) {
            var10.PV();
            return null;
         }

         this.q((axg)var10);
         var11 = var10;
      }

      return var11;
   }

   private void q(axg var1) {
      this.nf.q.verifyLocked();
      this.q(var1.xK(), var1.Dw(), false);
      this.nf.q(var1);
      if (!var1.hasFlag(128)) {
         var1.addListener(this.nf);
      }
   }

   private void q(long var1, long var3, boolean var5) {
      this.nf.q.verifyLocked();

      for (INativeContinuousItem var8 : this.nf.getItemsInRange(var1, true, var3, true).values()) {
         this.nf.q(var8, var5);
      }
   }

   private void q(long var1, boolean var3) {
      this.nf.q.verifyLocked();
      INativeContinuousItem var4 = this.nf.getItemOver(var1);
      if (var4 != null) {
         this.nf.q(var4, var3);
      }
   }

   public axu q(long var1, String var3, axp var4, String var5) {
      axu var8;
      try (ACLock var6 = this.nf.q.a()) {
         axu var7 = this.lm.q(var1, var4, var5);
         this.q((axg)var7);
         var7.setName(var3);
         var8 = var7;
      }

      return var8;
   }

   @Override
   public boolean enqueueRoutineForReanalysis(INativeMethodItem var1) {
      CodePointer var2 = CodePointer.createFrom(var1);
      CodePointer var3 = this.za.createEntryPoint(var2.getAddress(), var2.getMode());
      return this.enqueuePointerForAnalysis(var3, 2);
   }

   @Override
   public boolean recordDynamicBranchTarget(long var1, boolean var3, IBranchTarget var4, boolean var5) {
      if (var4 == null) {
         throw new NullPointerException();
      } else {
         axg var6 = (axg)this.nf.getItemAt(var1);
         if (!(var6 instanceof INativeInstructionItem)) {
            return false;
         } else {
            IInstruction var7 = ((axn)var6).getInstruction();

            boolean var29;
            try (ACLock var8 = this.nf.q.a()) {
               IFlowInformation var9 = this.q(var1, var7);
               if (!var9.isBroken()) {
                  var9 = var7.getRoutineCall(var1);
                  if (var9.isBroken() && var4.isInternal()) {
                     ICodePointer var28 = var4.getInternalAddress();
                     if (FlowInformation.isAddressInTargets(var9, var28.getAddress())) {
                        return false;
                     }

                     if (var5 && !this.nf.isRoutineHeader(var28.getAddress())) {
                        CodePointer var30 = new CodePointer(var28);
                        if (var30.getMode() == 0) {
                           var30.setMode(var28.getMode());
                        }

                        this.enqueuePointerForAnalysis(var30, 1);
                     }
                  }
               } else if (var5 && var4.isInternal()) {
                  List var10 = this.nf.getContainedRoutineAddresses(var1);

                  for (long var12 : var10) {
                     axp var14 = this.nf.oW(var12);
                     if (var14 != null) {
                        boolean var15 = true;
                        if (var14.oW() != null && var14.oW().oW() != null) {
                           var15 = false;
                        } else {
                           label106:
                           for (abk var18 : this.nf.HF().getReferencesTo(var4.getInternalAddress().getAddress())) {
                              if (var18.getFrom().isInternalAddress()) {
                                 long var19 = var18.getFrom().getInternalAddress();

                                 for (long var23 : this.nf.getContainedRoutineAddresses(var19)) {
                                    if (!var10.contains(var23)) {
                                       var15 = false;
                                       break label106;
                                    }
                                 }
                              }
                           }
                        }

                        if (var15) {
                           CodePointer var32 = CodePointer.createFrom(var14);
                           CodePointer var33 = this.za.createEntryPoint(var32.getAddress(), var32.getMode());
                           this.enqueuePointerForAnalysis(var33, 2);
                        }
                     }
                  }
               }

               var29 = this.nf.q(var1, var3, (BranchTarget)var4);
            }

            return var29;
         }
      }
   }

   @Override
   public boolean unrecordDynamicBranchTarget(long var1, boolean var3, IBranchTarget var4) {
      axg var5 = (axg)this.nf.getItemAt(var1);
      if (!(var5 instanceof INativeInstructionItem)) {
         return false;
      } else {
         boolean var7;
         try (ACLock var6 = this.nf.q.a()) {
            var7 = this.nf.RF(var1, var3, (BranchTarget)var4);
         }

         return var7;
      }
   }

   public void q(long var1) {
      this.unrecordDynamicBranchTarget(var1, false, null);
      this.unrecordDynamicBranchTarget(var1, true, null);
   }

   @Override
   public boolean recordDynamicRegisterValue(long var1, boolean var3, long var4, long var6) {
      axg var8 = (axg)this.nf.getItemAt(var1);
      if (!(var8 instanceof INativeInstructionItem)) {
         return false;
      } else {
         axg var9 = (axg)this.nf.getItemAt(var6);
         if (var9 == null && !this.getAnalysisRanges().contains(var6)) {
            return false;
         } else {
            if (var1 + var8.getMemorySize() == var6 && var9 instanceof INativeInstructionItem) {
               IInstruction var10 = ((INativeInstructionItem)var8).getInstruction();
               ICodePointer var11 = this.xK(var1, var10);
               if (var11 != null) {
                  axp var12 = this.nf.oW(var11.getAddress());
                  if (var12 != null && Booleans.isTrue(var12.getNonReturning())) {
                     return false;
                  }
               }
            }

            boolean var16;
            try (ACLock var15 = this.nf.q.a()) {
               var16 = this.nf.q(var1, var3, var4, var6);
            }

            return var16;
         }
      }
   }

   public boolean q(long var1, boolean var3, int var4) {
      throw new UnsupportedOperationException();
   }

   private ICodePointer xK(long var1, IInstruction var3) {
      IFlowInformation var4 = var3.getBreakingFlow(var1);
      if (var4 == null || !var4.isBroken()) {
         var4 = var3.getRoutineCall(var1);
         if (var4 != null && var4.isBroken() && var4.isBrokenKnown() && var4.getTargets().size() == 1) {
            return (ICodePointer)var4.getTargets().get(0);
         }
      } else if (var4.isBrokenKnown() && var4.getTargets().size() == 1) {
         return (ICodePointer)var4.getTargets().get(0);
      }

      return null;
   }

   public boolean q(String var1, Object... var2) {
      long var3 = System.currentTimeMillis();
      if (var3 - this.GY < 200L) {
         return false;
      } else {
         this.GY = var3;
         this.RF(var1, var2);
         return true;
      }
   }

   private void RF(String var1, Object... var2) {
      Double var4 = this.Bu == null ? null : this.Bu.q();
      if (var4 != null) {
         String var3 = var1 + " (%.1f%%)";
         if (var2 != null) {
            Object[] var5 = new Object[var2.length + 1];
            System.arraycopy(var2, 0, var5, 0, var2.length);
            var5[var2.length] = var4;
            xK.status(var3, var5);
         } else {
            xK.status(var3, var4);
         }
      } else {
         xK.status(var1, var2);
      }
   }

   public abb io() {
      return this.eJ;
   }

   public aal qa() {
      if (this.YN == null) {
         this.YN = new aal(this);
      }

      return this.YN;
   }

   public aaj Hk() {
      if (this.Rv == null) {
         this.Rv = new aaj(this);
      }

      return this.Rv;
   }

   public List Me() {
      return this.mI;
   }

   @Override
   public boolean recordAnalysisComment(long var1, String var3) {
      ICodeCoordinates var4 = this.nf.RF.memoryToCoord(var1);
      return var4 == null ? false : this.nf.RF.addMetaComment2(var4, new MetaComment(var3), false);
   }

   @Ser
   public static class CU extends AbstractAnalyzerExtension {
      public CU(aae var1) {
      }
   }

   private class eo {
      final int q = 10;
      int RF;
      Double xK;
      long Dw;
      Map Uv = new HashMap();

      public eo() {
         MemoryRanges var2 = aae.this.JY;
         if (aae.this.Me != null) {
            var2 = aae.this.Me;
         }

         if (var2 != null) {
            this.Dw = var2.aggregatedRangesSize();
         }
      }

      public Double q() {
         if (this.Dw == 0L) {
            return null;
         } else if (this.RF % 10 != 0) {
            this.RF++;
            return this.xK;
         } else {
            try {
               long var1 = 0L;

               for (Xg var4 : aae.this.nf.lm()) {
                  Integer var5 = (Integer)this.Uv.get(var4.q);
                  if (var5 == null) {
                     var5 = var4.xK();
                     this.Uv.put(var4.q, var5);
                  }

                  var1 += var5.intValue();
               }

               if (var1 > this.Dw) {
                  return null;
               } else {
                  this.xK = var1 * 100.0 / this.Dw;
                  this.RF++;
                  return this.xK;
               }
            } catch (Exception var6) {
               aae.xK.catchingSilent(var6);
               return null;
            }
         }
      }
   }
}
