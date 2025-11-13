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
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
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
import org.apache.commons.lang3.BooleanUtils;

@Ser
public class a implements INativeCodeAnalyzer {
   private static final ILogger kS = GlobalLog.getLogger(a.class);
   private static int wS = 32;
   private static int UT = 6;
   private static Boolean E;
   private static final Object sY = new Object();
   @SerId(1)
   private Tw ys;
   @SerId(2)
   private IVirtualMemory ld;
   @SerId(3)
   private IProcessor gp;
   @SerId(4)
   private auy oT;
   @SerId(5)
   private ayy fI;
   @SerId(6)
   private MemoryRanges WR;
   @SerId(7)
   private Ib NS = new Ib();
   @SerId(8)
   protected boolean pC = true;
   @SerId(9)
   private boolean vP = true;
   @SerId(10)
   protected boolean A = true;
   @SerId(13)
   private boolean xC = true;
   @SerId(14)
   private nq ED;
   @SerId(15)
   private boolean Sc;
   @SerId(16)
   private MemoryRanges ah;
   @SerId(17)
   private ISegmentMap eP;
   @SerId(18)
   private INativeCodeAnalyzerExtensionsManager UO;
   @SerId(19)
   private ICompiler Ab;
   @SerId(20)
   private ICodeObjectUnit rl;
   @SerId(21)
   private int z;
   @SerId(22)
   private int Ek;
   @SerId(23)
   private aW hK;
   @SerId(24)
   private boolean Er;
   @SerTransient
   private volatile int FE;
   @SerTransient
   private NativeSignatureDBManager Aj;
   @SerTransient
   private Vr EX;
   @SerTransient
   private int LM;
   @SerTransient
   private List mv;
   @SerTransient
   private List sO;
   @SerTransient
   private List os;
   @SerTransient
   private UnmanglerService Cu;
   @SerTransient
   private int hZ;
   @SerTransient
   private int UW;
   @SerTransient
   private Set PR = new HashSet();
   @SerTransient
   private a.Av cX;
   @SerTransient
   private Boolean DQ;
   @SerTransient
   private Boolean ZN;
   @SerTransient
   private Kt OB;
   @SerTransient
   private CC pF;
   @SerTransient
   private yj Bc;
   @SerTransient
   private TypeStringParser OI;
   @SerTransient
   private volatile int Bf;
   @SerTransient
   private long Pe;

   @SerCustomInitPostGraph
   private void ED() {
      if (this.UO == null) {
         this.UO = new rv(this, true);
      }

      if (this.hK == null) {
         this.hK = new aW();
      }

      this.PR = new HashSet();
   }

   public a(Tw var1, IVirtualMemory var2, IProcessor var3, ICodeObjectUnit var4, ayy var5, auy var6, MemoryRanges var7, MemoryRanges var8) {
      this.ys = var1;
      this.ld = var2;
      this.gp = var3;
      this.rl = var4;
      this.fI = var5;
      this.oT = var6;
      this.WR = var7;
      this.UO = new rv(this, true);
      this.ah = var8;
      this.hK = new aW();
   }

   @Override
   public MemoryRanges getAnalysisRanges() {
      return this.WR;
   }

   public MemoryRanges pC() {
      return this.ah;
   }

   public ISegmentMap A() {
      return this.eP;
   }

   public void pC(ISegmentMap var1) {
      this.eP = var1;
   }

   public aW kS() {
      return this.hK;
   }

   public void pC(Vr var1) {
      this.EX = var1;
      if (var1 != null) {
         if (this.UO != null) {
            ((rv)this.UO).registerExtensions(var1.UT(), true);
         }

         UT = var1.ED();
         wS = var1.Sc();
      }
   }

   public Vr wS() {
      return this.EX;
   }

   public void pC(UnmanglerService var1) {
      this.Cu = var1;
   }

   @Override
   public UnmanglerService getUnmanglerService() {
      return this.Cu;
   }

   public void pC(int var1, int var2) {
      this.hZ = var1;
      this.UW = var2;
   }

   @Override
   public DebugInformationPolicy getDebugInformationPolicy() {
      return new DebugInformationPolicy(this.hZ, this.UW);
   }

   public INativeCodeAnalyzerExtensionsManager UT() {
      return this.UO;
   }

   public void pC(nq var1) {
      this.ED = var1;
   }

   public nq E() {
      return this.ED;
   }

   public void pC(boolean var1) {
      this.Sc = var1;
   }

   public void pC(Boolean var1) {
      this.ZN = var1;
   }

   public void pC(NativeSignatureDBManager var1) {
      this.Aj = var1;
      if (this.Aj != null) {
         this.Aj.registerAnalyzer(this);
      }
   }

   public NativeSignatureDBManager sY() {
      return this.Aj;
   }

   @Override
   public ICompiler getDetectedCompiler() {
      return this.Ab;
   }

   public void pC(ICompiler var1) {
      this.Ab = var1;
   }

   public Tw ys() {
      return this.ys;
   }

   @Override
   public IProcessor getProcessor() {
      return this.gp;
   }

   @Override
   public IVirtualMemory getMemory() {
      return this.ld;
   }

   public ayy ld() {
      return this.fI;
   }

   @Override
   public ICodeObjectUnit getContainer() {
      return this.rl;
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
      return !this.WR.contains(var1.getAddress()) ? false : this.NS.pC(var1, var2, var3);
   }

   @Override
   public boolean clearAnalysisQueue() {
      if (this.NS.pC()) {
         return false;
      } else {
         this.NS.wS();
         return true;
      }
   }

   @Override
   public boolean needsAnalysis() {
      return !this.NS.pC();
   }

   @Override
   public boolean isAnalyzing() {
      return this.FE > 0;
   }

   @Override
   public void requestAnalysisInterruption() {
      if (this.ED != null) {
         this.ED.kS = true;
      }
   }

   @Override
   public int getAnalysisCount() {
      return this.z;
   }

   @Override
   public void analyze() {
      this.analyze(false, false);
   }

   @Override
   public void analyze(boolean var1, boolean var2) {
      try (ACLock var3 = this.ys.pC.a()) {
         this.FE++;
         this.cX = new a.Av();
         boolean var5 = this.ys.pC(false);
         this.ys.wS();

         boolean var4;
         try {
            var4 = this.Sc();
         } finally {
            this.ys.UT();
            this.ys.pC(var5);
            this.ys.notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
            this.cX = null;
            if (this.FE == 1) {
               this.pC(S.L("Standard analysis pass is complete"));
            }
         }

         if (!var1 && this.ED != null && this.FE == 1 && var4) {
            boolean var6 = this.Sc;
            if (this.DQ != null) {
               var6 = this.DQ;
            }

            if (this.ZN != null) {
               var6 = this.ZN;
            }

            if (var6) {
               synchronized (sY) {
                  this.ys.pC(false);

                  try {
                     if (E != null) {
                        var6 = E;
                     } else if (this.ZN == null && this.DQ == null && this.Ek > 0 && this.ys().ys() >= this.Ek) {
                        try {
                           String var8 = this.rl != null ? this.rl.getName() + " " : "";
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
                           this.DQ = (Boolean)var9.getResponse();
                           if (var9.isDoNotShowAnymoreResponse()) {
                              E = this.DQ;
                           }

                           var6 = this.DQ;
                        } catch (JebException var52) {
                        }
                     }

                     if (var6) {
                        try {
                           this.ED.perform();
                        } catch (Exception var51) {
                           kS.error(S.L("Advanced analysis pass failed"));
                           kS.catchingSilent(var51);
                           JebCoreService.notifySilentExceptionToClient(var51);
                        }
                     }
                  } finally {
                     this.ys.pC(var5);
                     this.ys.notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
                  }
               }
            }
         }

         if (!var2) {
            var5 = this.ys.pC(false);

            try {
               this.gp();
               if (this.needsAnalysis()) {
                  this.analyze();
               }
            } finally {
               if (this.FE == 1) {
                  this.UO.sigMatchingPostProcess(this.z);
               }

               this.ys.pC(var5);
               this.ys.notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
            }
         }
      } finally {
         this.z++;
         this.FE--;
         if (this.FE <= 0) {
            this.ZN = null;
         }
      }
   }

   public void gp() {
      List var1 = (List)this.ys().E().stream().filter(var0 -> var0.kS() != null && var0.kS().rl()).collect(Collectors.toList());

      try (ACLock var2 = this.ys.pC.a()) {
         this.pC(var1, false, false);
      }
   }

   public void oT() {
      try (ACLock var1 = this.ys.pC.a()) {
         this.pC(this.ys().E(), true, true);
      }
   }

   private void pC(Collection var1, boolean var2, boolean var3) {
      this.ys.pC.verifyLocked();
      if (this.Aj != null && this.Aj.isActive() && var1 != null && !var1.isEmpty()) {
         boolean var4 = this.ys.pC(false);

         try {
            if (this.EX != null && this.EX.WR() == 2) {
               this.Aj.loadDefaultPackages(this);
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
                     kS.status(S.L("Code matching on %d routines... (%d,%d)"), var5.size(), var8, var9);
                  }

                  List var12 = this.Aj.match(this, (Collection)var5, false, true, var10.isEmpty() ? var3 : false);
                  var5 = this.pC(var12, var10, var6, var2);
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

               if (this.PR != null && this.PR.size() > 0 && this.hK != null) {
                  ArrayList var28 = new ArrayList();
                  ArrayList var13 = new ArrayList();

                  for (NativeSignatureMatchResult var15 : this.PR) {
                     if (var15.getSignatures() != null) {
                        if (((auu)var15.getTarget()).E() == null) {
                           var13.add(var15);
                        } else {
                           ArrayList var16 = new ArrayList();

                           for (INativeSignature var18 : var15.getSignatures()) {
                              Set var19 = ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var18).wS();
                              if (var19 != null) {
                                 long var20 = ((auu)var15.getTarget()).E().getMemoryAddress();

                                 for (LibraryID var23 : var19) {
                                    jF var24 = this.hK.pC(var23);
                                    if (var24 != null && var24.A() && var24.pC(var20)) {
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
                     this.PR.removeAll(var13);
                     var5 = this.pC(var28, null, var6, var2);
                  }
               }
            }

            if (!var6.isEmpty() && this.OB != null) {
               this.OB.A(new ArrayList(var6));
            }
         } finally {
            this.ys.pC(var4);
            this.ys.notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
         }
      }
   }

   private Set pC(List var1, List var2, Set var3, boolean var4) {
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
               if (!ph.kS(var14.getTarget().getName())) {
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
         var5.addAll(this.pC(var7, var3, var4));
         if (var2 != null && var8 && !var5.isEmpty()) {
            var2.addAll(var6);
            return var5;
         }

         var7.clear();
      }

      return var5;
   }

   private Set pC(List var1, Set var2, boolean var3) {
      HashSet var4 = new HashSet();

      for (NativeSignatureMatchResult var6 : var1) {
         if (var6.getSignatures().size() != 0 && var6.isComplete()) {
            if (var6.getConfidenceLevel() == INativeSignature.ConfidenceLevel.LOW) {
               if (this.PR != null) {
                  this.PR.add(var6);
               }
            } else if (!(var6.getTarget() instanceof auo) || !((auo)var6.getTarget()).isDisposed()) {
               INativeSignature var7 = (INativeSignature)var6.getSignatures().get(0);
               List var8 = null;
               if (var6.getSignatures().size() > 1) {
                  var8 = var6.getSignatures();
                  var8.remove(var7);
               }

               if (var6.getTarget() instanceof auu) {
                  auu var9 = (auu)var6.getTarget();
                  if (var9.z() != null) {
                     INativeSignature var10 = var9.z().pC();
                     List var11 = var9.z().A();
                     if (Objects.equals(var10, var7) && Objects.equals(var11, var8)) {
                        continue;
                     }
                  }

                  boolean var18 = false;

                  try {
                     var18 = this.pC(var9, var7, var8);
                  } catch (DecompilerException var17) {
                  }

                  if (var3) {
                     kS.info(S.L("=> %s matched by signature"), var6.getTarget().getAddress());
                  }

                  List var19 = this.ys.getCallGraphManager().getGlobalCallGraph().getCallerRoutines(CodePointer.createFrom(var9), !this.EX.xC());

                  for (INativeMethodItem var13 : var19) {
                     ((auu)var13).pC(Boolean.valueOf(true));
                  }

                  if (var18) {
                     var2.add(var9.E());
                  } else {
                     for (INativeMethodItem var21 : var19) {
                        if (var21.getData().getTrampolineTarget() != null && var21.getData().getTrampolineTarget().equals(var9)) {
                           for (INativeMethodItem var16 : this.ys
                              .getCallGraphManager()
                              .getGlobalCallGraph()
                              .getCallerRoutines(CodePointer.createFrom(var21), !this.EX.xC())) {
                              ((auu)var16).pC(Boolean.valueOf(true));
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

   private boolean pC(auu var1, INativeSignature var2, List var3) {
      this.ys.pC.verifyLocked();
      boolean var4 = false;
      com.pnfsoftware.jeb.corei.parsers.asm.nativesig.HE var5 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.HE(var2, var3);
      var1.pC(var5);
      List var6 = var2.getAttributes();
      boolean var8 = var2.getFlags().hasMeaningfulTargetName();
      String var9 = var1.getName(true);
      String var7;
      if (var8) {
         var7 = var2.getTargetName();
      } else {
         var7 = com.pnfsoftware.jeb.corei.parsers.asm.nativesig.sy.pC(var2, this.Cu, var9);
      }

      boolean var10 = false;
      if (var7 != null && (this.ys().oT().pC(var9) || var9.equals("start") || var1.Ek())) {
         var10 = true;
      }

      if (var10) {
         var1.setName(var7);
      }

      if (var8 && this.pC(var1, var7, false)) {
         var4 = true;
      }

      if (var6 != null) {
         for (INativeAttribute var12 : var6) {
            if (!var12.importTo(var1) && var12 instanceof aww && var1.Er() == null) {
               String var13 = (String)var12.getValue();
               if (var10) {
                  if (var8) {
                     var1.pC(var7);
                  }

                  var1.kS(var13);
                  var1.setName(var13);
               }

               if (this.pC(var1, var13, true)) {
                  var4 = true;
               }
            }
         }
      }

      if (this.hK != null) {
         HashSet var16 = new HashSet();
         Set var17 = ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var2).wS();
         if (var17 != null) {
            var16.addAll(var17);
         }

         if (var3 != null) {
            for (INativeSignature var14 : var3) {
               Set var15 = ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var14).wS();
               if (var15 != null) {
                  var16.addAll(var15);
               }
            }
         }

         for (LibraryID var20 : var16) {
            this.hK.pC(var20, var1);
         }
      }

      return var4 || var1.os();
   }

   public boolean pC(INativeMethodItem var1, String var2, boolean var3) {
      if (this.ys.oT().pC(var2)) {
         return false;
      } else {
         Boolean var4 = var1.getNonReturning();
         boolean var5 = false;

         try {
            if (var2 != null && !var2.isEmpty()) {
               if (!var3 && this.Cu != null) {
                  IUnmangledRoutine var6 = this.Cu.unmangleRoutine(var2, false);
                  if (var6 != null && this.Cu.importUnmangledRoutineName(var1, var2, var6, true)) {
                     String var7 = var6.getName();
                     if (var7 != null && !var7.isEmpty()) {
                        var5 = this.pC(var1, var7);
                     }

                     if (!var5) {
                        var5 = this.Cu.importUnmangledRoutinePrototype(var1, var6);
                     }
                  }
               }

               if (!var5) {
                  this.pC(var1, var2);
               }
            }
         } catch (DecompilerException var8) {
         }

         return !Booleans.equals(var1.getNonReturning(), var4);
      }
   }

   private boolean pC(INativeMethodItem var1, String var2) {
      auu var3 = this.pC(var2);
      if (var3 == null
         && (
            this.rl != null && this.rl.getLoaderInformation().getTargetSubsystem() == SubsystemType.LINUX
               || this.getDetectedCompiler() != null && this.getDetectedCompiler().isLinuxCompatible()
         )
         && var2.length() >= 2
         && var2.startsWith("x")) {
         var3 = this.pC(var2.substring(1));
      }

      if (var3 == null && this.Cu != null) {
         try {
            IUnmangledRoutine var4 = this.Cu.unmangleRoutine(var2, true);
            if (var4 != null) {
               String var5 = var4.getReturnType();
               aye var6 = this.fI.A(var5, false, false);
               if (var6 != null) {
                  List var7 = var4.getParameterTypes();
                  ArrayList var8 = new ArrayList();

                  for (String var10 : var7) {
                     aye var11 = this.fI.A(var10, true, false);
                     var8.add(var11);
                  }

                  ays var16 = this.fI.pC(var6, var8);
                  var1.setPrototype(var16);
               } else {
                  ((auu)var1).pC(var2);
                  String var14 = var4.getName();
                  if (((auu)var1).Aj() == null && var14 != null) {
                     ((auu)var1).kS(var14);
                  }

                  String var15 = var4.getFull();
                  if (((auu)var1).FE() == null && var15 != null) {
                     ((auu)var1).A(var15);
                  }
               }
            }
         } catch (Exception var12) {
            kS.catchingSilent(var12);
         }
      }

      if (var3 != null) {
         ays var13 = var3.UT();
         if (var13 != null) {
            if (var13.getPrototypeAttributes().contains(PrototypeAttribute.NoReturn)) {
               ((auu)var1).wS(true);
            }

            var1.setPrototype((ays)this.fI.pC((aye)var13));
            var1.setParameterNames(new ArrayList(var3.getParameterNames()));
            return true;
         }
      }

      return false;
   }

   public auu pC(String var1) {
      TypeLibraryService var2 = this.fI.getTypeLibraryService();
      if (var2 == null) {
         return null;
      } else {
         String[] var3 = var1.split("!");
         if (var3.length == 0) {
            return null;
         } else {
            String var4 = var3[var3.length - 1];
            auu var5 = (auu)var2.findRoutineByName(var4, this.gp.getType());
            if (var5 == null) {
               String var6 = var4.startsWith("_") ? var4.substring(1) : "_" + var4;
               var5 = (auu)var2.findRoutineByName(var6, this.gp.getType());
               if (var5 == null && this.Cu != null) {
                  IUnmangledRoutine var7 = this.Cu.unmangleRoutine(var4, true);
                  if (var7 != null) {
                     String var8 = var7.getName();
                     if (var8 != null && !var8.isEmpty() && !var8.equals(var6)) {
                        var5 = (auu)var2.findRoutineByName(var8, this.gp.getType());
                     }
                  }
               }
            }

            return var5;
         }
      }
   }

   void pC(List var1) {
      this.mv = var1;

      for (kx var3 : var1) {
         var3.A(this.gp.getInstructionAlignment());
         var3.pC(this.gp.getInstructionAlignment());
      }
   }

   void A(List var1) {
      this.sO = var1;
   }

   void kS(List var1) {
      this.os = var1;
   }

   void pC(boolean var1, boolean var2) {
      this.pC(var1, var1, var2, var2);
   }

   void pC(boolean var1, boolean var2, boolean var3, boolean var4) {
      if (var1) {
         this.pC = true;
      }

      if (var2) {
         for (kx var6 : this.xC()) {
            var6.kS();
         }
      }

      if (var3) {
         this.vP = true;
      }

      if (var4) {
         for (kx var9 : this.sO) {
            var9.kS();
         }

         for (kx var10 : this.os) {
            var10.kS();
         }
      }
   }

   public boolean pC(Set var1, Set var2, Set var3) {
      while (!this.NS.pC()) {
         Ib.Av var4 = this.NS.kS();
         if ((var4.kS() & 32) == 0) {
            if (var1.contains(var4.pC())) {
               this.NS.A();
               continue;
            }

            if (var2.contains(var4.pC()) && (var4.kS() & 1) != 0) {
               this.NS.A();
               continue;
            }

            if (var3.contains(var4.pC())) {
               this.NS.A();
               continue;
            }
         }

         long var5 = var4.pC().getAddress();
         if (this.WR.contains(var5)) {
            break;
         }

         this.NS.A();
      }

      return !this.NS.pC();
   }

   private boolean Sc() {
      this.ys.pC.verifyLocked();

      try {
         boolean var1 = false;
         if (this.FE == 1) {
            this.UO.preprocessImage(this.z);
         }

         HashSet var2 = new HashSet();
         HashSet var3 = new HashSet();
         HashSet var4 = new HashSet();
         if (this.mv == null) {
            if (this.EX != null) {
               this.pC(this.EX.ys());
            } else {
               ArrayList var5 = new ArrayList();
               var5.add(new Dz(this));
               this.pC(var5);
            }
         }

         if (this.os == null && this.EX != null) {
            this.A(this.EX.E());
            this.kS(this.EX.sY());
         }

         boolean var49 = false;
         ArrayList var6 = new ArrayList();
         long var7 = System.currentTimeMillis();

         label1319:
         while (true) {
            Ib var10 = new Ib();
            int var11 = 0;

            label1316:
            while (true) {
               boolean var9 = false;

               while (this.pC(var2, var3, var4)) {
                  ArrayDeque var12 = new ArrayDeque();

                  while (this.pC(var2, var3, var4)) {
                     Ib.Av var13 = this.NS.A();
                     if (var13.pC() instanceof CodePointer) {
                        CodePointer var14 = (CodePointer)var13.pC();
                        if ((var13.kS() & 1) == 0) {
                           var2.add(var14);
                        } else {
                           var3.add(var14);
                        }

                        int var15 = this.gp.getMode();

                        try {
                           try {
                              this.gp.setMode(var14.getMode());
                           } catch (ProcessorException var45) {
                           }

                           if (!var14.isUnknownAddress()) {
                              if (this.pC(var14.getAddress(), var13.A(), var13.kS())) {
                                 var49 = true;
                              } else if ((var13.kS() & 32) != 0) {
                                 auu var16 = this.ys.E(var13.pC().getAddress());
                                 if (var16 != null && var16.E() != null) {
                                    var6.add(var16.E());
                                    var49 = true;
                                 }
                              }
                           }
                        } finally {
                           try {
                              this.gp.setMode(var15);
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
                     } else if (var13.pC().getType() == 1) {
                        this.enqueuePointerForAnalysis(this.gp.createEntryPoint(var13.pC().getAddress()));
                     } else {
                        if (var13.pC().getType() == 5) {
                           var4.add(var13.pC());

                           try {
                              long var66 = this.ld.readPointer(var13.pC().getAddress());
                              this.enqueuePointerForAnalysis(this.gp.createEntryPoint(var66));
                           } catch (MemoryException var44) {
                           }
                        } else if (var13.pC().getType() == 6) {
                           var4.add(var13.pC());
                        } else if (var13.pC().getType() == 2) {
                           var4.add(var13.pC());
                        }

                        var12.add(var13);
                     }
                  }

                  while (!var12.isEmpty()) {
                     Ib.Av var60 = (Ib.Av)var12.pop();
                     int var67 = var60.A();
                     long var74 = var60.pC().getAddress();
                     int var17 = var60.pC().getSize();
                     if (var17 == 0 && var60.pC().getType() == 0) {
                        CodePointer var18 = (CodePointer)((kx)this.mv.get(var11)).pC(var74, var74 + 64L, false);
                        if (var18 != null) {
                           int var94 = 0;
                           if (this.EX != null) {
                              var94 = this.EX.oT();
                           }

                           this.enqueuePointerForAnalysis(var18, var94);
                           continue;
                        }
                     }

                     if (var67 < 1 && !this.ys.isEmptyRange(var74, Math.max(1, var17))) {
                        if (var60.pC().getType() == 2) {
                           var4.remove(var60.pC());
                        } else {
                           var10.pC(var60.pC(), var60.A(), var60.pC().getSize());
                        }
                     } else {
                        if (var17 == 0 && this.A) {
                           if (this.ys.getItemOver(var74) instanceof avb) {
                              continue;
                           }

                           avb var89 = this.NS().pC(var74, -1L);
                           if (var89 != null) {
                              continue;
                           }
                        }

                        INativeDataItem var90 = null;
                        if (var17 == this.fI.getPointerSize()) {
                           for (kx var20 : this.os) {
                              var90 = (INativeDataItem)var20.pC(var74, var74 + var17, false);
                              if (var90 != null) {
                                 break;
                              }
                           }
                        }

                        if (var90 == null) {
                           Object var93;
                           if (var17 != this.fI.getPointerSize() || var60.pC().getType() != 5 && var60.pC().getType() != 6) {
                              var93 = this.fI.pC(var17 == 0 ? 1 : var17, false);
                           } else {
                              var93 = this.fI.E();
                           }

                           if ((var60.kS() & 4096) != 0) {
                              Object var98 = this.fI.UT(((aye)var93).getName() + "_transient");
                              if (var98 == null) {
                                 var98 = this.fI.pC(((aye)var93).getName() + "_transient", (INativeType)var93);
                                 ((aye)var98).addFlags(128);
                              }

                              var93 = var98;
                           }

                           INativeDataItem var99 = this.vP().pC(var74, null, var17, (INativeType)var93, false);
                           if (var99 == null) {
                              var10.pC(var60.pC(), var60.A(), var60.pC().getSize());
                           }
                        }
                     }
                  }
               }

               if (this.pC) {
                  Qb var55 = ((kx)this.mv.get(var11)).A();
                  if (var55 != null) {
                     int var61 = 0;
                     if (this.EX != null) {
                        var61 = this.EX.oT();
                     }

                     int var68 = Vr.pC(CodeGapAnalysisStyle.PROLOGUES_ONLY);
                     List var75 = (List)var55.A();
                     if (var75 != null) {
                        for (CodePointer var84 : var75) {
                           if (var84 == null) {
                              var9 = true;
                           } else {
                              long var91 = var84.getAddress();
                              if (this.UT().getPrologueLooking(var91, var91 + 4L).getResult() != null) {
                                 var61 = var68;
                              }

                              this.enqueuePointerForAnalysis(var84, var61);
                              Object[] var10000 = new Object[]{var84};
                           }
                        }
                     }
                  }
               }

               if (this.NS.pC() && var11 + 1 < this.mv.size()) {
                  var9 = true;
                  var11++;
                  Object[] var124 = new Object[0];
                  if (this.EX != null) {
                     this.EX.A(var11);
                  }

                  while (!var10.pC()) {
                     Ib.Av var56 = var10.A();
                     this.NS.pC(var56.pC(), var56.A(), var56.pC().getSize());
                  }
               }

               if (this.NS.pC() && !var9) {
                  if (this.vP) {
                     Qb var69;
                     for (kx var62 : this.sO) {
                        do {
                           var69 = var62.A();
                        } while (var69 != null);
                     }

                     for (kx var63 : this.os) {
                        while (true) {
                           var69 = var63.A();
                           if (var69 == null) {
                              break;
                           }

                           List var76 = (List)var69.A();
                           if (var76 != null) {
                              for (INativeDataItem var85 : var76) {
                                 Object[] var125 = new Object[]{var69.pC(), var85};
                              }
                           }
                        }
                     }
                  }

                  this.pC = false;
                  this.vP = false;
                  if (!var49) {
                     return false;
                  }

                  for (Ss var82 : new ArrayList(this.ys.ld())) {
                     long var86 = var82.pC;
                     ON var95 = this.ys.gp(var86);
                     if (var95 != null) {
                        Assert.a(var95.pC == var86, "Conflict for bases. Expected: %Xh but was %Xh", var95.pC, var86);
                        if (var82.pC() == null && !this.ys.kS(var86) && var95.UT.size() >= 2) {
                           boolean var100 = true;
                           if (var100 && !var82.UT && var95.UT.size() == 2) {
                              Ss var21 = (Ss)var95.UT.get(0);
                              if (var21 == var82) {
                                 var21 = (Ss)var95.UT.get(1);
                              }

                              String var22 = this.ys.oT().getLabel(var86, 0L, AutoLabelPolicy.OFF);
                              if (var22 == null) {
                                 boolean var23 = true;
                                 Set var24 = this.ys.gp().getReferencesTo(var86);
                                 if (var24.isEmpty()) {
                                    var23 = false;
                                 } else {
                                    for (xy var26 : var24) {
                                       ReferenceLocation var27 = (ReferenceLocation)var26.pC();
                                       if (!var27.isInternalAddress()
                                          || !this.ys.wS(var27.getInternalAddress(), true).contains(var21.pC)
                                          || var26.getType() == ReferenceType.ROUTINE_CALL) {
                                          var23 = false;
                                          break;
                                       }
                                    }
                                 }

                                 if (var23) {
                                    for (ON var122 : var82.A) {
                                       var122.UT.remove(var82);
                                    }

                                    Object[] var126 = new Object[]{var86, var21.pC};
                                    this.ys.sY(var86);
                                    var2.remove(new CodePointer(var86, this.gp.getMode()));
                                    var82.pC = -1L;
                                    var82.A = null;
                                    continue;
                                 }
                              }
                           }

                           ArrayList var103 = new ArrayList(var95.UT);
                           var103.remove(var82);

                           for (Ss var112 : var103) {
                              for (ON var117 : var112.A) {
                                 if (!var82.A.contains(var117) && var117.kS(var86)) {
                                    var117.pC(Long.valueOf(var86));
                                 }
                              }
                           }

                           for (Ss var113 : var103) {
                              List var116 = this.ys.pC(var113.pC, true, null, true);
                              if (var116 == null) {
                                 kS.error(S.L("Expected a routine at: %Xh"), var113.pC);
                              } else {
                                 for (ON var121 : var113.A) {
                                    if (!var116.contains(var121)) {
                                       var121.UT.remove(var113);
                                    }
                                 }

                                 var113.A = var116;
                                 var113.kS = null;
                              }
                           }
                        }
                     }
                  }

                  Object[] var127 = new Object[0];

                  label1129:
                  for (Ss var72 : this.ys.ld()) {
                     long var78 = var72.pC;
                     if (var72.pC() == null || !this.ys.kS(var78)) {
                        aut var87 = this.ys.wS(var78);
                        if (var87 != null) {
                           var87.E(true);
                           var87.xC();
                        }

                        CFG var92 = var72.pC();
                        if (var92 == null) {
                           var127 = new Object[]{var72.pC, var72.A.size(), var72.A};
                           ArrayList var96 = new ArrayList(var72.A.size());

                           for (ON var104 : var72.A) {
                              BasicBlock var109 = var104.pC();
                              if (var109 == null) {
                                 kS.catchingSilent(new JebRuntimeException("Basic Block is empty at " + Long.toHexString(var78) + "h"));
                                 kS.error(S.L("Routine: %X can not be built"), var72.pC);
                                 continue label1129;
                              }

                              var96.add(var109);
                              var104.pC(var109);
                           }

                           var92 = new CFG(var72.pC, var96);
                           var72.kS = var92;
                        }

                        String var97 = this.ys.oT().getLabel(var72.pC, 0L, AutoLabelPolicy.OFF);
                        if (var97 == null) {
                           var97 = this.ys.oT().pC(var72.pC);
                        }

                        aut var102 = new aut(var92, this.ys, this.fI, var97);
                        var6.add(var102);

                        for (AddressableInstruction var110 : var92.addressableInstructions()) {
                           long var114 = var110.getOffset();
                           INativeContinuousItem var120 = this.ys.getItemAt(var114);
                           if (var120 instanceof aus) {
                              int var123 = ((aus)var120).UT();
                              if (var123 >= 1) {
                                 var102.sY(true);
                                 break;
                              }
                           }
                        }

                        for (xy var111 : (Set)this.ys
                           .gp()
                           .getReferencesTo(var72.pC)
                           .stream()
                           .filter(var0 -> var0.getFrom().isInternalAddress() && var0.getType().isCode() && var0.getType() != ReferenceType.ROUTINE_CALL)
                           .collect(Collectors.toSet())) {
                           this.ys
                              .getCallGraphManager()
                              .getGlobalCallGraph()
                              .recordInternalCall(var111.getFrom().getInternalAddress(), this.gp.createEntryPoint(var72.pC), true);
                        }

                        if (var72.wS != null && !var72.wS.isEmpty()) {
                           var102.pC(var72.wS);
                        }

                        this.ys.pC(var102);
                     }
                  }

                  if (this.xC) {
                     this.fI();
                     boolean var65 = true;
                     if (var65 && this.wS(var6)) {
                        var6.removeIf(var0 -> var0.isDisposed());
                     }

                     for (aut var79 : var6) {
                        if (!var79.isDisposed()) {
                           CFG var83 = var79.getCFG();
                           Integer var88 = (Integer)this.UO.determineRoutineStackPointerDelta(var83).getResult();
                           if (var88 != null) {
                              var79.setSPDeltaOnReturn(var88);
                           }
                        }
                     }
                  }

                  if (this.pC(var2, var3, var4)) {
                     continue label1319;
                  }
                  break;
               }
            }

            if (var1) {
               throw new InterruptionException("The code analysis was interrupted");
            }

            for (kx var53 : this.mv) {
               Object[] var129 = new Object[]{var53.ys(), TimeFormatter.formatTimestampDelta(var53.pC())};
            }

            for (kx var54 : this.os) {
               Object[] var130 = new Object[]{var54.ys(), TimeFormatter.formatTimestampDelta(var54.pC())};
            }

            return true;
         }
      } finally {
         if (this.FE == 1) {
            this.ah();
            this.UO.postprocessImage(this.z);
         }

         this.pC(false, true, false, true);
      }
   }

   public void fI() {
      for (aut var2 : this.ys.E()) {
         if (var2.E() == null) {
            CFG var3 = var2.getCFG();
            Pointer var4 = (Pointer)this.UO.getTrampolineTarget(var3).getResult();
            if (var4 != null && var4.getType() == 5) {
               INativeContinuousItem var5 = this.ys.getItemAt(var4.getAddress());
               if (var5 instanceof auz) {
                  this.pC(var2, (auz)var5, false);
               } else {
                  auu var6 = this.ys.E(var4.getAddress());
                  if (var6 != null) {
                     this.pC(var2, var6);
                  }
               }
            }
         }
      }
   }

   private void ah() {
      if (!this.Er) {
         try {
            HashMap var1 = new HashMap();
            Collection var2 = this.ld().gp();

            for (auu var4 : var2) {
               if (var4.isPlaceholderMethod() && var4.Cu() != null) {
                  var1.put(var4.Cu(), null);
               }
            }

            for (auu var11 : var2) {
               if (!var11.isPlaceholderMethod() && var11.Cu() != null && var1.containsKey(var11.Cu())) {
                  var1.put(var11.Cu(), var11);
               }
            }

            for (auu var12 : var2) {
               if (var12.isPlaceholderMethod() && var12.Cu() != null) {
                  auu var5 = (auu)var1.get(var12.Cu());
                  if (var5 != null) {
                     var12.pC(new NativeItemEvent(NativeItemEventType.UPDATED, var12, NativeItemEventSubType.ROUTINE_SET, var5));
                     var12.xC();
                  }
               }
            }
         } finally {
            this.Er = true;
         }
      }
   }

   private boolean wS(List var1) {
      if (this.OB == null) {
         this.OB = new Kt(this);
      }

      return this.OB.pC(var1);
   }

   private void pC(aut var1, auu var2) {
      if (var2 != null) {
         if (var1.E() == null || var1.E() != var2) {
            var1.kS(var2);
            this.pC(var1, var2, false);
         }
      }
   }

   public void pC(aut var1, auz var2, boolean var3) {
      if (var2.UO() != null) {
         auu var4 = (auu)var2.UO();
         if (var1.E() == null || var1.E() != var2.UO()) {
            boolean var5 = false;
            String var6 = var3 ? "r" : "";
            if (this.EX != null && this.EX.wS()) {
               String var7 = var4.Aj();
               if (var7 != null) {
                  var1.setName(var6 + "→" + var7);
                  auu var8 = var1.kS();
                  if (var8 != null) {
                     if (var4.Er() != null) {
                        var8.pC(var4.Er());
                     }

                     if (var4.Aj() != null) {
                        var8.kS(var4.Aj());
                     }

                     if (var4.FE() != null) {
                        var8.A(var4.FE());
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

            var1.kS(var4);
            this.pC(var1, var4, true);
         }
      }
   }

   private void pC(aut var1, auu var2, boolean var3) {
      String var4 = var1.getName();
      if (var4 == null) {
         JebCoreService.notifySilentExceptionToClient(new RuntimeException("Unexpected null routine name"));
      } else {
         String var5 = var2.getName();
         if (var5 == null) {
            JebCoreService.notifySilentExceptionToClient(new RuntimeException("Unexpected null target name"));
         } else if (!var5.equals("*" + var4) && !var4.equals("→" + var5)) {
            if (ph.kS(var4)) {
               var1.setName(this.A("→" + var5));
            } else if (ph.kS(var5)) {
               var2.setName(this.A("*" + var4));
            } else if ((var4.startsWith("→") || var4.startsWith("*")) && (!var3 || var4.charAt(1) == '_' && var4.charAt(2) == 'Z')) {
               var1.setName(this.A("→" + var5));
            }
         }
      }
   }

   private String A(String var1) {
      return !var1.startsWith("→*") && !var1.startsWith("*→") ? var1 : var1.substring(2);
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private IInstruction A(long var1, int var3) {
      boolean var13 = false /* VF: Semaphore variable */;

      IInstruction var5;
      label78: {
         try {
            var13 = true;
            if (var3 != -1) {
               var3 = this.gp.setMode(var3);
            }

            IInstruction var4 = this.gp.parseAt(this.ld, var1);
            var5 = var4;
            var13 = false;
            break label78;
         } catch (Exception var17) {
            var13 = false;
         } finally {
            if (var13) {
               if (var3 != -1) {
                  try {
                     this.gp.setMode(var3);
                  } catch (ProcessorException var14) {
                  }
               }
            }
         }

         Object var6 = null;
         if (var3 != -1) {
            try {
               this.gp.setMode(var3);
            } catch (ProcessorException var15) {
            }
         }

         return (IInstruction)var6;
      }

      if (var3 != -1) {
         try {
            this.gp.setMode(var3);
         } catch (ProcessorException var16) {
         }
      }

      return var5;
   }

   private boolean pC(long var1, int var3, int var4) {
      this.ys.pC.verifyLocked();
      if (this.ys.ys(var1) != null) {
         boolean var5 = false;
         if (var3 >= 2) {
            var5 = this.ys.A(var1, true);
         }

         if (!var5) {
            return false;
         }
      }

      return this.A(var1, var3, var4);
   }

   private static String pC(int var0) {
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

   private static String A(int var0) {
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

   public void pC(PointerLocation var1, boolean var2) {
      if (var2) {
         this.enqueuePointerForAnalysis(var1.getPointer(), 0, var1.getFlags());
      }

      long var3 = var1.getPointer().getAddress();
      INativeContinuousItem var5 = this.ys.getItemOver(var3);
      if (var5 != null && var5.getMemoryAddress() != var3 && var5 instanceof INativeDataItem var6) {
         if (var5 instanceof auv && (var3 - var5.getMemoryAddress()) % ((auv)var5).z().getSize() == 0L) {
            var6.getHints(true).setReferenceHint(16);
         } else if (var1.getPointer().getSize() == 1) {
            var6.getHints(true).setReferenceHint(16);
         } else if (var5 instanceof auw var7) {
            if (var7.UT() instanceof IStructureType) {
               IStructureType var8 = (IStructureType)var7.UT();
               if (var8.getFieldAt((int)(var3 - var5.getMemoryAddress())) == null) {
                  var7.getHints(true).setReferenceHint(16);
               }
            } else {
               var7.getHints(true).setReferenceHint(16);
            }
         } else {
            var6.getHints(true).setReferenceHint(16);
         }
      }

      this.ys.gp().recordInternalReference(var1.getLocation(), var3, ReferenceType.GEN_DATA);
   }

   private boolean A(long var1, int var3, int var4) {
      this.ys.pC.verifyLocked();
      Object[] var10000 = new Object[]{++this.Bf, pC(var3), A(var4), var1};
      this.pC(S.L("Routine: %Xh"), var1);
      ON var5 = null;
      ArrayList var6 = new ArrayList();
      ArrayDeque var7 = new ArrayDeque();
      var7.add(new CodePointer(var1, this.gp.getMode()));
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
            aus var25 = this.pC(var9, var3);
            if (var25 == null) {
               return false;
            }

            var9 += var25.getMemorySize();
         }
      }

      label636:
      while (true) {
         IInstruction var44 = null;
         if (var8 == 0) {
            if (var5 != null && !var5.A.isEmpty() && !var11) {
               this.UO.determinePotentialPointersInProtoBlock(var5, var21);

               for (PointerLocation var54 : var21) {
                  this.pC(var54, !pC(var7, var54.getPointer().getAddress()));
               }

               var21.clear();
            }

            if (var7.isEmpty()) {
               for (Entry var55 : var20.entrySet()) {
                  this.pC((Long)var55.getKey(), (ON)var55.getValue(), var7, var6);
               }

               var20.clear();
               if (var7.isEmpty()) {
                  break;
               }
            }

            CodePointer var50 = (CodePointer)var7.pop();
            if (var50.getMode() != 0 && var50.getMode() != this.gp.getMode()) {
               try {
                  this.gp.setMode(var50.getMode());
               } catch (ProcessorException var42) {
                  kS.catchingSilent(var42);
               }
            }

            var9 = var50.getAddress();
         }

         INativeContinuousItem var51 = this.ys.getItemOver(var9);
         if (var51 == null) {
            if (var8 == 0) {
               var5 = new ON(var9);
               var6.add(var5);
               var8 = 1;
               var15 = 0L;
            }
         } else if (var51.getMemoryAddress() == var9) {
            if (var51 instanceof aus) {
               ON var56 = this.ys.gp(var9);
               if (var56 == null) {
                  var56 = (ON)var19.get(var9);
               }

               if (var56 != null) {
                  if (var8 < 0) {
                     var10000 = new Object[0];
                     var44 = ((aus)var51).getInstruction();
                     int var64 = this.gp.getMode() == 0 ? this.gp.getDefaultMode() : this.gp.getMode();
                     int var69 = var44.getProcessorMode() == 0 ? this.gp.getDefaultMode() : var44.getProcessorMode();
                     if (var64 != var69) {
                        var10000 = new Object[]{var64, var69};
                        var11 = true;
                        var8 = 0;
                     } else {
                        var8++;
                        var5.A.add(var44);
                        var14++;
                        if (var8 != 0) {
                           var9 += var44.getSize();
                        }
                     }
                     continue;
                  }

                  var44 = ((aus)var51).getInstruction();
                  int var63 = this.gp.getMode() == 0 ? this.gp.getDefaultMode() : this.gp.getMode();
                  int var68 = var44.getProcessorMode() == 0 ? this.gp.getDefaultMode() : var44.getProcessorMode();
                  if (var63 != var68) {
                     var10000 = new Object[]{var63, var68};
                     var11 = true;
                     var8 = 0;
                     continue;
                  }

                  if (var56.pC == var9) {
                     var56.UT.isEmpty();
                     if (var8 != 0) {
                        var5.A(var9);
                        var8 = 0;
                        IInstruction var73 = (IInstruction)var5.A.get(var5.A.size() - 1);
                        this.ys.gp().recordInternalReference(var9 - var73.getSize(), var9, ReferenceType.GEN_CODE);
                     }
                     continue;
                  }

                  boolean var28 = false;
                  if (var56.sY > 0L && var9 == var56.sY) {
                     var28 = true;
                  } else if (var56.sY > 0L && var56.getLastAddress() == var9 && var56.getInsntructions().size() > 1) {
                     int var29 = var56.getInsntructions().size() - 2;
                     IInstruction var30 = (IInstruction)var56.getInsntructions().get(var29);
                     long var31 = var56.pC(var29);
                     var28 = this.pC(var30, var31);
                  }

                  if (var28) {
                     var10000 = new Object[0];
                     this.ys.ld(var9);
                     this.ys.pC(var51, true);
                     var5 = new ON(var9);
                     var6.add(var5);
                     var8 = 1;
                     var15 = 0L;
                  } else {
                     ON var77 = this.pC(var56, var9, var22);
                     if (var77 == null) {
                        var10000 = new Object[0];
                        var11 = true;
                        var8 = 0;
                     } else {
                        var6.add(var77);
                        var56.UT.isEmpty();
                        if (var8 != 0) {
                           var5.A(var9);
                           IInstruction var81 = (IInstruction)var5.A.get(var5.A.size() - 1);
                           this.ys.gp().recordInternalReference(var9 - var81.getSize(), var9, ReferenceType.GEN_CODE);
                        }

                        var8 = 0;
                     }
                  }
                  continue;
               }

               var44 = ((aus)var51).getInstruction();
               int var26 = this.gp.getMode() == 0 ? this.gp.getDefaultMode() : this.gp.getMode();
               int var27 = var44.getProcessorMode() == 0 ? this.gp.getDefaultMode() : var44.getProcessorMode();
               if (var26 != var27) {
                  var10000 = new Object[0];
                  this.pC(var9, true);
                  var44 = null;
               }
            } else {
               if (var3 < 1 || !(var51 instanceof INativeDataItem)) {
                  var10000 = new Object[0];
                  var11 = true;
                  var8 = 0;
                  continue;
               }

               if (var51 instanceof auz var57 && var57.UO() != null && var57.UO().getData() == null) {
                  var10000 = new Object[0];
                  var11 = true;
                  var12 = false;
                  var8 = 0;
                  continue;
               }

               this.pC(var9, true);
            }
         } else {
            if (var3 < 1) {
               var10000 = new Object[]{var51};
               var11 = true;
               var8 = 0;
               continue;
            }

            var10000 = new Object[0];
            this.pC(var9, false);
         }

         if (var44 == null) {
            aus var58 = this.pC(var9, var3);
            if (var58 == null) {
               var10000 = new Object[0];
               if (this.EX != null) {
                  this.EX.pC(Vr.Av.A, var1);
               }

               if (var3 < 2 && var14 < UT) {
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
            var5 = new ON(var9);
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

         var5.A.add(var44);
         if (var22) {
            var19.put(var9, var5);
         } else {
            this.ys.pC(var9, var5);
         }

         var14++;
         if (wS > 0) {
            byte[] var59 = var44.getCode();
            if (ArrayUtil.isSled(var59, (byte)0)) {
               long var17 = var9 + var59.length;
               if (var15 == 0L) {
                  var15 = var9;
               }

               if (var17 - var15 >= wS) {
                  var10000 = new Object[0];
                  long var65 = var17;

                  while (true) {
                     if (var65 > var15) {
                        IInstruction var74 = (IInstruction)var5.A.get(var5.A.size() - 1);
                        byte[] var78 = var74.getCode();
                        if (ArrayUtil.isSled(var78, (byte)0)) {
                           var65 -= var78.length;
                           this.pC(var65, true);
                           this.ys.ld(var65);
                           var5.A.remove(var5.A.size() - 1);
                           continue;
                        }
                     }

                     if (var5.A.isEmpty()) {
                        var6.remove(var5);
                     }

                     var65 = (var17 - var15) / var59.length;
                     if (var14 - var65 < UT) {
                        var10000 = new Object[0];
                        var11 = true;
                     } else {
                        var13.add(var9);
                     }

                     var8 = 0;
                     continue label636;
                  }
               }
            } else {
               var15 = 0L;
            }
         }

         boolean var60 = false;
         boolean var67 = false;
         if (!var44.getInstructionFlags().contains(InstructionFlags.CONDITIONAL_EXEC)) {
            for (INativeMethodItem var79 : this.ys.getCallGraphManager().getGlobalCallGraph().getCalleeRoutines(var9, !this.EX.xC())) {
               Boolean var82 = ((auu)var79).getNonReturning();
               if (Boolean.TRUE.equals(var82)) {
                  var60 = true;
                  break;
               }

               if (!var67 && var82 != null) {
                  var67 = true;
               }
            }
         }

         if (!var60) {
            var60 = (Boolean)this.UO.shouldForceRoutineEnd(var9, var44).getResult();
         }

         long var71 = var9 + var44.getSize();
         if (var3 < 3 && this.ah != null && this.ah.count() != 0 && !this.ah.contains(var71)) {
            var60 = true;
         }

         byte var80 = 0;
         boolean var83 = false;
         boolean var84 = false;
         boolean var32 = false;
         boolean var33 = false;
         INativeContinuousItem var34 = this.ys.getItemAt(var9);
         if (var34 instanceof aus) {
            InstructionHints var35 = ((aus)var34).getHints(false);
            if (var35 != null) {
               var83 = var35.isFakeCall();
               var84 = var35.isActualCall();
               var32 = var35.isTailCall();
               var33 = var35.isCondTailCall();
            }
         }

         IFlowInformation var85 = this.pC(var9, var44);
         if (var85.isBroken()) {
            var80 = 1;
            if (var3 < 3 && this.EX != null && this.EX.pC(var3) && !this.pC(var9, var85)) {
               var10000 = new Object[0];
               var11 = true;
               break;
            }

            ReferenceType var36 = var85.getTargets().size() > 1 ? ReferenceType.COND_BRANCH : ReferenceType.BRANCH;
            Long var37;
            if (var85.mustComputeFallThrough()) {
               var36 = ReferenceType.COND_BRANCH;
               var37 = this.pC(var71, var85, var3);
               if (var37 == null) {
                  if (var14 < UT) {
                     var10000 = new Object[0];
                     var11 = true;
                  } else {
                     var13.add(var9);
                  }
                  break;
               }

               if (!var60) {
                  var5.A(var37);
                  var7.push(new CodePointer(var37, this.gp.getMode()));
                  this.ys.gp().recordInternalReference(var9, var37, var36);
               }
            } else {
               var37 = var71;
            }

            boolean var38 = false;
            if (var85.isBrokenKnown() && var85.getTargets().size() >= 2) {
               Me var39 = this.ys.WR(var9);
               if (var39.isResolved()) {
                  BranchTarget var40 = var39.pC();
                  if (var40.isInternal()) {
                     var38 = true;
                  }
               }
            }

            if (!var38) {
               for (ICodePointer var104 : var85.getTargets()) {
                  if (var104.isUnknownAddress()) {
                     var5.ys = true;
                  } else {
                     if ((!var33 || var104.getAddress() == var9 + var44.getSize()) && !var32 && !var84 && this.ys.E(var104.getAddress()) == null) {
                        INativeContinuousItem var108 = this.ys.getItemAt(var104.getAddress());
                        if (var108 instanceof auz && ((auz)var108).UO().getData() == null) {
                           var5.ys = true;
                           this.ys.getCallGraphManager().getGlobalCallGraph().recordExternalCall(var9, ((auz)var108).UO(), true);
                        } else {
                           var5.A(var104.getAddress());
                           var7.push(new CodePointer(var104));
                        }
                     } else {
                        CodePointer var41 = new CodePointer(var104);
                        this.enqueuePointerForAnalysis(var41, this.A(var3, var4), var4 & 1);
                        this.ys.getCallGraphManager().getGlobalCallGraph().recordInternalCall(var9, var41, true);
                     }

                     this.ys.gp().recordInternalReference(var9, var104.getAddress(), var36);
                  }
               }
            }

            if (var84) {
               auu var98 = this.ys.E(var37);
               if (var98 != null && var3 >= 2) {
                  this.ys.A(var37, true);
               }

               var5.A(var37);
               var7.push(new CodePointer(var37, this.gp.getMode()));
               var5.sY = var37;
               var8 = -var85.getDelaySlotCount();
            } else {
               var5.sY = var71;
               var8 = -var85.getDelaySlotCount();
            }
         }

         var85 = var44.getRoutineCall(var9);
         if (var85.isBroken()) {
            var80 = 2;
            if (var3 < 3 && this.EX != null && this.EX.pC(var3) && !this.pC(var9, var85)) {
               var10000 = new Object[0];
               var11 = true;
               break;
            }

            Long var87 = this.pC(var71, var85, var3);
            if (var87 == null) {
               if (var14 < UT) {
                  var10000 = new Object[0];
                  var11 = true;
               } else {
                  var13.add(var9);
               }
               break;
            }

            for (ICodePointer var92 : var85.getTargets()) {
               if (var92.getAddress() != var87 || var92.getMode() != this.gp.getMode()) {
                  if (var92.isUnknownAddress()) {
                     var5.ys = true;
                  } else {
                     if (!var60 && !var67 && !var44.getInstructionFlags().contains(InstructionFlags.CONDITIONAL_EXEC)) {
                        String var99 = this.ys.oT().getLabel(var92.getAddress(), 0L, AutoLabelPolicy.OFF);
                        if (var99 != null) {
                           auu var105 = this.pC(var99);
                           if (var105 != null) {
                              ays var109 = var105.UT();
                              if (var109.getPrototypeAttributes().contains(PrototypeAttribute.NoReturn)) {
                                 var60 = true;
                              }
                           }
                        }

                        if (this.OB != null && this.OB.pC(var92.getAddress())) {
                           var60 = true;
                        }

                        if (var83) {
                           var60 = true;
                        }
                     }

                     if (var83) {
                        var5.A(var92.getAddress());
                        var7.push(new CodePointer(var92));
                        this.ys.gp().recordInternalReference(var9, var92.getAddress(), ReferenceType.BRANCH);
                     } else {
                        int var100 = var92.getMode() == 0 ? this.gp.getMode() : var92.getMode();
                        this.enqueuePointerForAnalysis(new CodePointer(var92.getAddress(), var100), this.A(var3, var4), var4 & 1);
                        this.ys.gp().recordInternalReference(var9, var92.getAddress(), ReferenceType.ROUTINE_CALL);
                     }
                  }
               }
            }

            if (!var60) {
               var5.A(var87);
               var7.push(new CodePointer(var87, this.gp.getMode()));
            }

            var5.sY = var87;
            var8 = -var85.getDelaySlotCount();
         }

         IFlowInformation var89 = var44.collectIndirectCallReferences(var9);
         if (var89.isBroken()) {
            for (ICodePointer var101 : var89.getTargets()) {
               if (!var101.isUnknownAddress()) {
                  this.enqueuePointerForAnalysis(new Pointer(var101.getAddress(), this.gp.getMode() / 8, 2));
                  this.ys.gp().recordInternalReference(var9, var101.getAddress(), ReferenceType.READ_DATA);
               }
            }
         }

         if (var80 != 0) {
            Me var90 = this.ys.WR(var9);

            for (BranchTarget var102 : var90.getTargets()) {
               if (var102.isInternal()) {
                  CodePointer var106 = var102.getInternalAddress();
                  auu var110 = this.ys.E(var106.getAddress());
                  if (var110 != null && var80 == 1 && var3 >= 2 && this.ys.A(var106.getAddress(), true)) {
                     var110 = null;
                  }

                  if (var110 != null) {
                     this.enqueuePointerForAnalysis(var106, this.A(var3, var4));
                  } else {
                     var5.A(var106.getAddress());
                     var7.push(var106);
                  }

                  this.ys.gp().recordInternalReference(var9, var106.getAddress(), ReferenceType.DYNAMIC_BRANCH);
               }
            }

            if ((Boolean)this.UO.isCandidateSwitchDispatcher(var9, var44, var5).getResult()) {
               INativeContinuousItem var95 = this.ys.getItemAt(var9);
               if (var95 instanceof aus) {
                  ((aus)var95).pC(1);
               }

               if (!this.pC(var9, var5, var7, var6)) {
                  var20.put(var9, var5);
                  this.EX.pC(Vr.Av.UT, var9);
               }
            }
         }

         if (var60 && var8 >= 0) {
            this.ys.oT(var9);
            var8 = 0;
         }

         if (var8 != 0) {
            this.ys.fI(var9);
         }

         if (var80 == 0) {
            ArrayList var91 = new ArrayList();
            int var96 = this.pC(var9, var44, var91);

            for (Pointer var107 : var91) {
               int var111 = var96 == 0 && var107.getSize() == 0 && var107.getType() == 0 ? 65536 : 0;
               var21.add(new PointerLocation(var9, var107, var111));
            }
         }

         if (var8 != 0) {
            var9 = var71;
         }
      }

      if (var22) {
         return false;
      } else if (var11) {
         for (ON var53 : var6) {
            if (var53.UT.isEmpty()) {
               long var62 = var53.pC;

               for (IInstruction var76 : var53.A) {
                  this.ys.ld(var62);
                  var62 += var76.getSize();
               }
            }
         }

         if (this.EX != null && var12) {
            this.EX.pC(Vr.Av.A, var1);
         }

         return false;
      } else {
         List var43 = this.ys.pC(var1, true, var13, true);
         if (var43 == null) {
            return false;
         } else {
            Ss var47 = new Ss(var1);

            for (ON var61 : var43) {
               var47.A.add(var61);
               var61.UT.add(var47);
            }

            if (!var13.isEmpty()) {
               var47.wS = var13;
            }

            this.ys.pC(var47);
            if (var3 == 3 || (var4 & 64) != 0) {
               var47.UT = true;
            }

            if (this.EX != null) {
               this.EX.pC(Vr.Av.pC, var1);
            }

            return true;
         }
      }
   }

   private IFlowInformation pC(long var1, IInstruction var3) {
      ChainedOperationResult var4 = this.UO.getPreferredBreakingFlow(var1, var3);
      IFlowInformation var5 = (IFlowInformation)var4.getResult();
      return var5 != null ? var5 : var3.getBreakingFlow(var1);
   }

   private boolean pC(IInstruction var1, long var2) {
      IFlowInformation var4 = this.pC(var2, var1);
      if (var4.isBroken() && var4.getDelaySlotCount() != 0) {
         return true;
      } else {
         var4 = var1.getRoutineCall(var2);
         return var4.isBroken() && var4.getDelaySlotCount() != 0;
      }
   }

   private int A(int var1, int var2) {
      if ((var2 & 16) != 0) {
         return 2;
      } else if ((var2 & 8) != 0) {
         return 1;
      } else {
         return (var2 & 4) != 0 ? 0 : var1;
      }
   }

   private boolean pC(long var1, IFlowInformation var3) {
      this.ys.pC.verifyLocked();
      boolean var4 = true;
      if (this.ah != null && this.ah.contains(var1)) {
         for (ICodePointer var6 : var3.getTargets()) {
            if (!var6.isUnknownAddress() && !this.ah.contains(var6.getAddress())) {
               var4 = false;
               break;
            }
         }
      }

      return var4;
   }

   private boolean pC(long var1, ON var3, Deque var4, List var5) {
      this.ys.pC.verifyLocked();
      SwitchInformation var6 = (SwitchInformation)this.UO.determineSwitchInformation(var1, var3, var5).getResult();
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
               if (this.pC(var11, var7)) {
                  var21 = this.fI.E();
               } else {
                  var21 = this.fI.pC(var11.getJumpTableEntrySize(), false);
               }

               this.defineData(var11.getJumpTableEntryAddress(), (INativeType)var21);
            }
         }

         for (Entry var17 : var9.entrySet()) {
            CodePointer var19 = (CodePointer)var17.getKey();
            if (!var3.kS(var19.getAddress())) {
               var3.A(var19.getAddress());
               var4.push(new CodePointer(var19));
               this.ys.gp().recordInternalReference(var1, var19.getAddress(), ReferenceType.DYNAMIC_BRANCH);
               this.recordDynamicBranchTarget(var1, false, new aab(var19, (Collection)var17.getValue()), false);
            }
         }

         if (var8 != null && !var8.isEmpty()) {
            for (SwitchInformation.JumpTableInformation var18 : var8) {
               Object var20;
               if (this.pC(var18, var7)) {
                  var20 = this.fI.E();
               } else {
                  var20 = this.fI.pC(var18.getEntrySize(), false);
               }

               int var22 = (int)(var18.getEndAddress() - var18.getStartAddress()) / ((aye)var20).getSize();
               this.defineData(var18.getStartAddress(), this.fI.A((INativeType)var20, var22));
               this.recordAnalysisComment(var18.getStartAddress(), Strings.ff("%sjump table for switch at 0x%x", var18.isSecondary() ? "secondary " : "", var1));
            }
         }

         INativeContinuousItem var16 = this.ys().getItemAt(var1);
         if (var16 instanceof aus) {
            ((aus)var16).pC(2);
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean pC(SwitchInformation.SwitchCaseInformation var1, Set var2) {
      return this.pC(var1.getJumpTableEntryAddress(), var1.getJumpTableEntrySize(), var1.getJumpTableEntryAddress() + var1.getJumpTableEntrySize(), var2);
   }

   private boolean pC(SwitchInformation.JumpTableInformation var1, Set var2) {
      return this.pC(var1.getStartAddress(), var1.getEntrySize(), var1.getEndAddress(), var2);
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private boolean pC(long var1, int var3, long var4, Set var6) {
      if (var3 != this.fI.getPointerSize()) {
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

               long var9 = this.ld.readPointer(var7);
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

   private static boolean pC(Deque var0, long var1) {
      for (CodePointer var4 : var0) {
         if (var4.getAddress() == var1) {
            return true;
         }
      }

      return false;
   }

   private Long pC(long var1, IFlowInformation var3, int var4) {
      this.ys.pC.verifyLocked();
      long var5 = var1;
      int var7 = var3.getDelaySlotCount();

      while (var7 > 0) {
         aus var8 = this.pC(var5, -1, var4, true);
         if (var8 == null) {
            Object[] var10000 = new Object[]{var5};
            return null;
         }

         var7--;
         var5 += var8.getInstruction().getSize();
      }

      return var5;
   }

   private boolean A(long var1, IInstruction var3) {
      this.ys.pC.verifyLocked();
      IFlowInformation var4 = var3.getRoutineCall(var1);
      if (!var4.isBroken()) {
         return false;
      } else {
         for (ICodePointer var6 : var4.getTargets()) {
            if (!var6.isUnknownAddress()) {
               INativeContinuousItem var7 = this.ys.getItemAt(var6.getAddress());
               if (var7 instanceof auz && ((auz)var7).UO().getData() == null) {
                  this.ys.getCallGraphManager().getGlobalCallGraph().recordExternalCall(var1, ((auz)var7).UO(), true);
               } else {
                  this.ys.getCallGraphManager().getGlobalCallGraph().recordInternalCall(var1, (CodePointer)var6, true);
               }
            }
         }

         if (var4.getTargets().size() == 0) {
            IFlowInformation var8 = var3.collectIndirectCallReferences(var1);
            if (var8.isBroken()) {
               for (ICodePointer var10 : var8.getTargets()) {
                  this.ys.getCallGraphManager().getGlobalCallGraph().recordUnresolvedCall(var1, var10.getAddress(), true);
               }
            }
         }

         return true;
      }
   }

   private int pC(long var1, IInstruction var3, List var4) {
      if ((Boolean)this.UO.determinePotentialPointers(var1, var3, var4).getResult()) {
         return 1;
      } else {
         for (int var5 = 0; var5 < var3.getOperands().length; var5++) {
            IInstructionOperand var6 = var3.getOperands()[var5];
            if (var6 instanceof IInstructionOperandGeneric) {
               int var7 = ((IInstructionOperandGeneric)var6).getOperandType();
               switch (var7) {
                  case 1:
                     boolean var19 = false;
                     long var20 = ((IInstructionOperandGeneric)var6).getOperandValue(var1);
                     int[] var21 = new int[1];
                     INativeContinuousItem var12 = this.ys.getItemOver(var20);
                     if (!BooleanUtils.isFalse(yj.pC(this, var20, var12, false))) {
                        if (VirtualMemoryUtil.readLEIntSafe(this.ld, var20, var21) && var21[0] != 0) {
                           var19 = true;
                        } else if (this.getContainer() != null) {
                           ICodeObjectUnit var13 = this.getContainer();
                           long var14 = var20 - this.EX.NS();
                           if (CodeObjectUnitUtil.hasSymbolsAtRelativeAddress(var13, var14)) {
                              var19 = true;
                           } else {
                              ISegmentInformation var16 = CodeObjectUnitUtil.findSectionByRelativeAddress(var13, var14);
                              if (var16 != null && var16.getName() != null) {
                                 String var17 = var16.getName();
                                 if (var17.contains("bss") || var17.contains("data")) {
                                    var19 = true;
                                 }
                              }
                           }
                        }
                     }

                     if (var19) {
                        var4.add(new Pointer(var20));
                     }
                     break;
                  case 2:
                  case 3:
                  case 5:
                     long var18 = ((IInstructionOperandGeneric)var6).getOperandValue(var1);
                     int var10 = 0;
                     if (var7 == 5) {
                        var10 = ((IInstructionOperandGeneric)var6).getOperandBitsize();
                     }

                     var4.add(new Pointer(var18, var10 / 8, 2));
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

   private ON pC(ON var1, long var2, boolean var4) {
      this.ys.pC.verifyLocked();
      if (var1.sY > 0L && Longs.compareUnsigned(var2, var1.sY) >= 0) {
         return null;
      } else {
         ON var5 = var1.pC(var2, var4);
         if (var5 == null) {
            return null;
         } else if (var4) {
            return var5;
         } else {
            long var6 = var2;

            for (int var8 = 0; var8 < var5.A.size(); var8++) {
               IInstruction var9 = (IInstruction)var5.A.get(var8);
               ON var10 = this.ys.gp(var6);
               if (var10 == null || var10 == var1) {
                  this.ys.pC(var6, var5);
               }

               var6 += var9.getSize();
            }

            for (Long var14 : this.ys.getContainedRoutineAddresses(var2)) {
               aut var11 = this.ys.wS(var14);
               var11.E(true);
               var11.xC();
            }

            return var5;
         }
      }
   }

   public aus pC(long var1, int var3) {
      return this.pC(var1, -1, var3, true);
   }

   public aus pC(long var1, int var3, int var4, boolean var5) {
      return this.pC(var1, var3, var4, var5 ? 2 : 1);
   }

   public aus pC(long var1, int var3, int var4, int var5) {
      if (var5 >= 0 && var5 <= 2) {
         aus var10;
         try (ACLock var6 = this.ys.pC.a()) {
            if (this.ys.getItemAt(var1) instanceof aus var13) {
               return var13;
            }

            IInstruction var8 = this.A(var1, var3);
            if (var8 == null) {
               return null;
            }

            if (var8.getInstructionFlags().contains(InstructionFlags.UNPREDICTABLE_INSN) && var4 < 3) {
               return null;
            }

            if (var5 == 1 && !this.ys.isEmptyRange(var1, var8.getSize())) {
               return null;
            }

            aus var9 = new aus(var1, var8);
            this.UT().customizeInstructionItem(var9);
            if (var5 != 0) {
               this.pC(var9);
               this.A(var1, var8);
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

   @Override
   public INativeDataItem defineData(long var1, INativeType var3, int var4) {
      return this.pC(var1, (aye)var3, var4, false);
   }

   public INativeDataItem pC(long var1, aye var3, int var4, boolean var5) {
      if (var5 && this.EX != null && this.EX.eP() != null && this.EX.eP().contains(var1)) {
         return null;
      } else {
         INativeContinuousItem var6 = this.ys.getItemAt(var1);
         if (var6 instanceof INativeDataItem && ((INativeDataItem)var6).getType() == var3) {
            return (INativeDataItem)var6;
         } else {
            aum var13;
            try (ACLock var7 = this.ys.pC.a()) {
               aum var8 = this.oT.pC(var1, var3, var4);
               if (var8 == null) {
                  return null;
               }

               INativeType var9 = TypeUtil.getNonAlias(var3);
               boolean var10 = var9 instanceof ayg;
               if (var10 || var9 instanceof ayq && var9.getSize() > 1) {
                  this.pC(var8, !var10);
               }

               this.pC((aul)var8);
               String var11 = this.ys.oT().getLabel(var1, 0L, AutoLabelPolicy.OFF);
               String var12 = Strings.ff("%s%X", "gvar_", var1);
               if (var11 != null) {
                  this.pC((INativeDataItem)var8);
               }

               var8.pC(var12);
               var13 = var8;
            }

            return var13;
         }
      }
   }

   private void pC(aum var1, boolean var2) {
      HashMap var3 = new HashMap();

      for (int var4 = 1; var1.getMemoryAddress() + var4 < var1.getMemoryAddressEnd(); var4++) {
         Set var5 = this.ys().gp().getReferencesTo(var1.getMemoryAddress() + var4);
         if (!var5.isEmpty()) {
            var3.put(var4, var5);
         }
      }

      if (!var3.isEmpty()) {
         var1.getHints(true).setReferenceHint(16);
      }
   }

   public void pC(INativeDataItem var1) {
      long var2 = var1.getMemoryAddress();
      String var4 = var1.getName(true);
      if (this.getUnmanglerService() != null) {
         IUnmangledData var5;
         if (var1 instanceof auz) {
            var5 = this.getUnmanglerService().unmangle(var4, true);
         } else {
            var5 = this.getUnmanglerService().unmangleData(var4, true);
         }

         if (var5 != null) {
            String var6 = var5.getFull();
            boolean var7 = false;

            try {
               if (this.OI == null) {
                  this.OI = new TypeStringParser(this.fI);
               }

               TypeStringParser.Decl var8 = this.OI.parseDeclaration(var6);
               if (var8 != null && !var8.getType().equals(var1.getType())) {
                  aum var9 = this.oT.pC(var2, var8.getType(), -1);
                  if (var9 != null) {
                     var9.setName(var8.getName());
                     this.ys.pC(var1);
                     this.pC((aul)var9);
                     this.recordAnalysisComment(var2, "(mangled:'" + var4 + "')");
                     var7 = true;
                  }
               }
            } catch (TypeStringParseException var10) {
            }

            if (!var7 && var6 != null && !var6.isEmpty()) {
               if (this.ys.oT().isLegalLabel(var6)) {
                  var1.setName(var6);
                  this.recordAnalysisComment(var2, "(mangled:'" + var4 + "')");
               } else {
                  this.recordAnalysisComment(var2, "(unmangled:'" + var6 + "')");
               }
            }
         }
      }
   }

   public avb pC(long var1, long var3, StringEncoding var5, int var6, int var7, boolean var8) {
      avb var11;
      try (ACLock var9 = this.ys.pC.a()) {
         avb var10 = (avb)DataStringUtil.createFromMemory(this.oT, this.ys.oT(), this.ld, var1, var3, var5, var6, var7);
         if (var10 == null) {
            return null;
         }

         if (!var8 && !this.ys.isEmptyRange(var1, (int)var10.getMemorySize())) {
            var10.xC();
            return null;
         }

         this.pC(var10, false);
         this.pC((aul)var10);
         var11 = var10;
      }

      return var11;
   }

   private void pC(aul var1) {
      this.ys.pC.verifyLocked();
      this.pC(var1.kS(), var1.wS(), false);
      this.ys.pC(var1);
      if (!var1.hasFlag(128)) {
         var1.addListener(this.ys);
      }
   }

   private void pC(long var1, long var3, boolean var5) {
      this.ys.pC.verifyLocked();

      for (INativeContinuousItem var8 : this.ys.getItemsInRange(var1, true, var3, true).values()) {
         this.ys.pC(var8, var5);
      }
   }

   private void pC(long var1, boolean var3) {
      this.ys.pC.verifyLocked();
      INativeContinuousItem var4 = this.ys.getItemOver(var1);
      if (var4 != null) {
         this.ys.pC(var4, var3);
      }
   }

   public auz pC(long var1, String var3, auu var4, String var5) {
      auz var8;
      try (ACLock var6 = this.ys.pC.a()) {
         auz var7 = this.oT.pC(var1, var4, var5);
         this.pC((aul)var7);
         var7.setName(var3);
         var8 = var7;
      }

      return var8;
   }

   @Override
   public boolean enqueueRoutineForReanalysis(INativeMethodItem var1) {
      CodePointer var2 = CodePointer.createFrom(var1);
      CodePointer var3 = this.gp.createEntryPoint(var2.getAddress(), var2.getMode());
      return this.enqueuePointerForAnalysis(var3, 2);
   }

   @Override
   public boolean recordDynamicBranchTarget(long var1, boolean var3, IBranchTarget var4, boolean var5) {
      if (var4 == null) {
         throw new NullPointerException();
      } else {
         aul var6 = (aul)this.ys.getItemAt(var1);
         if (!(var6 instanceof INativeInstructionItem)) {
            return false;
         } else {
            IInstruction var7 = ((aus)var6).getInstruction();

            boolean var29;
            try (ACLock var8 = this.ys.pC.a()) {
               IFlowInformation var9 = this.pC(var1, var7);
               if (!var9.isBroken()) {
                  var9 = var7.getRoutineCall(var1);
                  if (var9.isBroken() && var4.isInternal()) {
                     ICodePointer var28 = var4.getInternalAddress();
                     if (FlowInformation.isAddressInTargets(var9, var28.getAddress())) {
                        return false;
                     }

                     if (var5 && !this.ys.isRoutineHeader(var28.getAddress())) {
                        CodePointer var30 = new CodePointer(var28);
                        if (var30.getMode() == 0) {
                           var30.setMode(var28.getMode());
                        }

                        this.enqueuePointerForAnalysis(var30, 1);
                     }
                  }
               } else if (var5 && var4.isInternal()) {
                  List var10 = this.ys.getContainedRoutineAddresses(var1);

                  for (long var12 : var10) {
                     auu var14 = this.ys.E(var12);
                     if (var14 != null) {
                        boolean var15 = true;
                        if (var14.E() != null && var14.E().E() != null) {
                           var15 = false;
                        } else {
                           label106:
                           for (xy var18 : this.ys.gp().getReferencesTo(var4.getInternalAddress().getAddress())) {
                              if (var18.getFrom().isInternalAddress()) {
                                 long var19 = var18.getFrom().getInternalAddress();

                                 for (long var23 : this.ys.getContainedRoutineAddresses(var19)) {
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
                           CodePointer var33 = this.gp.createEntryPoint(var32.getAddress(), var32.getMode());
                           this.enqueuePointerForAnalysis(var33, 2);
                        }
                     }
                  }
               }

               var29 = this.ys.pC(var1, var3, (BranchTarget)var4);
            }

            return var29;
         }
      }
   }

   @Override
   public boolean unrecordDynamicBranchTarget(long var1, boolean var3, IBranchTarget var4) {
      aul var5 = (aul)this.ys.getItemAt(var1);
      if (!(var5 instanceof INativeInstructionItem)) {
         return false;
      } else {
         boolean var7;
         try (ACLock var6 = this.ys.pC.a()) {
            var7 = this.ys.A(var1, var3, (BranchTarget)var4);
         }

         return var7;
      }
   }

   @Override
   public boolean recordDynamicRegisterValue(long var1, boolean var3, long var4, long var6) {
      aul var8 = (aul)this.ys.getItemAt(var1);
      if (!(var8 instanceof INativeInstructionItem)) {
         return false;
      } else {
         aul var9 = (aul)this.ys.getItemAt(var6);
         if (var9 == null && !this.getAnalysisRanges().contains(var6)) {
            return false;
         } else {
            if (var1 + var8.getMemorySize() == var6 && var9 instanceof INativeInstructionItem) {
               IInstruction var10 = ((INativeInstructionItem)var8).getInstruction();
               ICodePointer var11 = this.kS(var1, var10);
               if (var11 != null) {
                  auu var12 = this.ys.E(var11.getAddress());
                  if (var12 != null && Booleans.isTrue(var12.getNonReturning())) {
                     return false;
                  }
               }
            }

            boolean var16;
            try (ACLock var15 = this.ys.pC.a()) {
               var16 = this.ys.pC(var1, var3, var4, var6);
            }

            return var16;
         }
      }
   }

   private ICodePointer kS(long var1, IInstruction var3) {
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

   public boolean pC(String var1, Object... var2) {
      long var3 = System.currentTimeMillis();
      if (var3 - this.Pe < 200L) {
         return false;
      } else {
         this.Pe = var3;
         this.A(var1, var2);
         return true;
      }
   }

   private void A(String var1, Object... var2) {
      Double var4 = this.cX == null ? null : this.cX.pC();
      if (var4 != null) {
         String var3 = var1 + " (%.1f%%)";
         if (var2 != null) {
            Object[] var5 = new Object[var2.length + 1];
            System.arraycopy(var2, 0, var5, 0, var2.length);
            var5[var2.length] = var4;
            kS.status(var3, var5);
         } else {
            kS.status(var3, var4);
         }
      } else {
         kS.status(var1, var2);
      }
   }

   public Kt WR() {
      return this.OB;
   }

   public CC NS() {
      if (this.pF == null) {
         this.pF = new CC(this);
      }

      return this.pF;
   }

   public yj vP() {
      if (this.Bc == null) {
         this.Bc = new yj(this);
      }

      return this.Bc;
   }

   public List xC() {
      return this.mv;
   }

   @Override
   public boolean recordAnalysisComment(long var1, String var3) {
      ICodeCoordinates var4 = this.ys.A.memoryToCoord(var1);
      return var4 == null ? false : this.ys.A.addMetaComment2(var4, new MetaComment(var3), false);
   }

   private class Av {
      final int pC = 10;
      int A;
      Double kS;
      long wS;
      Map UT = new HashMap();

      public Av() {
         MemoryRanges var2 = a.this.WR;
         if (a.this.ah != null) {
            var2 = a.this.ah;
         }

         if (var2 != null) {
            this.wS = var2.aggregatedRangesSize();
         }
      }

      public Double pC() {
         if (this.wS == 0L) {
            return null;
         } else if (this.A % 10 != 0) {
            this.A++;
            return this.kS;
         } else {
            try {
               long var1 = 0L;

               for (Ss var4 : a.this.ys.ld()) {
                  Integer var5 = (Integer)this.UT.get(var4.pC);
                  if (var5 == null) {
                     var5 = var4.A();
                     this.UT.put(var4.pC, var5);
                  }

                  var1 += var5.intValue();
               }

               if (var1 > this.wS) {
                  return null;
               } else {
                  this.kS = var1 * 100.0 / this.wS;
                  this.A++;
                  return this.kS;
               }
            } catch (Exception var6) {
               a.kS.catchingSilent(var6);
               return null;
            }
         }
      }
   }

   @Ser
   public static class Sv extends AbstractAnalyzerExtension {
   }
}
