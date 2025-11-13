package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.input.IInputLocation;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IMetadataManager;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IQuickStateObject;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.AbstractCodeUnit;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.ICodeHierarchy;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.NativeCommentManager;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchResolution;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEvent;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.IFieldManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.IMethodManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventSubType;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.UnmanglerService;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.INativeDisassemblyDocument;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureDBManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionName;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPackage;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPackageManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveSizes;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrototypeAttribute;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeStringParseException;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeStringParser;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ILoaderInformation;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.AddressSegmentMap;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.WeakValueMap;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.Net;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.Predicate;

@Ser
public class C extends AbstractCodeUnit implements INativeCodeUnit, ko {
   private static final ILogger E = GlobalLog.getLogger(C.class);
   @SerId(1)
   private YK sY = new YK(this);
   @SerId(2)
   private nE ys;
   @SerId(3)
   private IProcessor ld;
   @SerId(4)
   private IVirtualMemory gp;
   @SerId(5)
   private Tw oT;
   @SerId(6)
   a pC;
   @SerId(7)
   private GenericCodeFormatter fI;
   @SerId(8)
   private long WR;
   @SerId(9)
   private long NS;
   @SerId(10)
   private ayy vP;
   @SerId(11)
   private ICodeObjectUnit xC;
   @SerId(12)
   private auy ED;
   @SerId(13)
   private aac Sc;
   @SerId(14)
   private rn ah = new rn();
   @SerId(15)
   private MemoryRanges eP;
   @SerId(16)
   private MemoryRanges UO;
   @SerId(17)
   private Vr Ab;
   @SerId(18)
   private INativeCodeAnalyzerExtension rl;
   @SerId(19)
   WeakValueMap A;
   @SerId(20)
   private Long z;
   @SerId(21)
   NativeCommentManager kS;
   @SerId(22)
   private ICallingConvention Ek;
   @SerTransient
   private CompilerType hK;
   @SerTransient
   private SubsystemType Er;
   @SerTransient
   public IQuickStateObject wS;
   @SerTransient
   private TypeLibraryService FE;
   @SerTransient
   private long Aj;
   @SerTransient
   private ayk EX;
   @SerTransient
   private IUnitContribution LM;
   @SerTransient
   private NativeSignatureDBManager mv;
   @SerTransient
   private boolean sO;
   @SerTransient
   private int os = 0;
   @SerTransient
   private UnmanglerService Cu;
   @SerTransient
   private boolean hZ;
   @SerTransient
   private boolean UW;
   @SerTransient
   private IUnitContribution PR;
   @SerTransient
   private String cX;
   @SerTransient
   public boolean UT;

   @SerCustomInitPostGraph
   private void fI() {
      this.A = new WeakValueMap();
      if (this.kS == null) {
         this.kS = new NativeCommentManager(this);
      }

      if (this.oT != null && this.oT.A == null) {
         this.oT.A = this.kS;
      }
   }

   public C(String var1, String var2, IInput var3, IUnitProcessor var4, IUnitCreator var5, IPropertyDefinitionManager var6) {
      super(var1, var2, var3, var4, var5, var6);
      this.fI();
   }

   @Override
   public void postDeserialization(IRuntimeProject var1) {
      if (this.isProcessed()) {
         this.pC(var1);
         this.A(var1);
         ICompiler var2 = this.pC.getDetectedCompiler();
         if (var2 == null) {
            var2 = this.NS();
            this.pC.pC(var2);
         }

         this.WR();
         this.pC.pC(this.Ab);
         this.pC(var2);
         this.pC.pC(this.Cu);
         int var3 = this.getPropertyManager().getInteger("DebugInformationUsagePolicy");
         int var4 = this.getPropertyManager().getInteger("DebugInformationRetrievalPolicy");
         this.pC.pC(var3, var4);
         this.hZ = this.getPropertyManager().getBoolean("CreatePackagesFromRoutineNames");
      }
   }

   private void WR() {
      if (this.Ab == null) {
         this.Ab = new Vr(this);
      }
   }

   private void pC(IRuntimeProject var1) {
      if (this.getPropertyManager().getBoolean("UseTypeLibraries")) {
         this.FE = var1.getEnginesContext().getTypeLibraryService();
         this.vP.pC(this.FE);
         if (this.xC instanceof IPECOFFUnit) {
            if (((IPECOFFUnit)this.xC).getPEOptionalHeader() == null) {
               this.FE.load(this.vP(), 10);
            } else {
               byte var2 = 0;
               short var3 = ((IPECOFFUnit)this.xC).getPEOptionalHeader().getSubsystem();
               switch (var3) {
                  case 1:
                     var2 = 20;
                     break;
                  case 2:
                  case 3:
                  case 9:
                     var2 = 10;
                     break;
                  case 4:
                  case 5:
                  case 6:
                  case 7:
                  case 8:
                  default:
                     JebCoreService.notifySilentExceptionToClient(
                        new JebRuntimeException("Does not know which typelib should be loaded for PE subsystem: " + var3)
                     );
                     break;
                  case 10:
                  case 11:
                  case 12:
                  case 13:
                     var2 = 30;
               }

               if (var2 != 0) {
                  this.FE.load(this.vP(), var2);
               }

               this.FE.load(this.vP(), 9);
            }
         } else if (this.xC instanceof IELFUnit) {
            if (!this.NS().isAndroidCompatible() && UnitUtil.findAncestor(this, IApkUnit.class, false) == null) {
               this.FE.load(this.vP(), 2);
            } else {
               this.FE.load(this.vP(), 3);
            }
         }
      }
   }

   private void A(IRuntimeProject var1) {
      switch (this.getPropertyManager().getInteger("SignaturePackagesLoading")) {
         case 0:
            return;
         case 1:
         case 2:
            this.mv = var1.getEnginesContext().getNativeSignatureDBManager();
            this.pC.pC(this.mv);
      }
   }

   private void pC(ICompiler var1) {
      this.Cu = new UnmanglerService(this);
      if (var1.isVisualStudio() || this.xC instanceof com.pnfsoftware.jeb.corei.parsers.wincoff.K) {
         this.Cu.registerEngine(1);
         this.Cu.registerEngine(3);
      } else if (var1.isLinuxCompatible() || this.xC instanceof com.pnfsoftware.jeb.corei.parsers.macho.zp) {
         this.Cu.registerEngine(4);
      }
   }

   private ICompiler NS() {
      int var1 = this.getPropertyManager().getInteger("CompilerIdentification");
      return var1 != 0 ? KV.pC(var1) : KV.pC(this.xC);
   }

   @Override
   public void dispose() {
      if (this.Sc != null) {
         this.Sc.cancel(true);

         try {
            this.Sc.join();
         } catch (InterruptedException var2) {
            E.catching(var2);
         }
      }

      if (this.mv != null && this.pC != null) {
         this.mv.unregisterAnalyzer(this.pC);
      }

      super.dispose();
      this.sY = null;
      this.ys = null;
      this.ld = null;
      this.gp = null;
      if (this.oT != null) {
         this.oT.A();
      }

      this.pC = null;
      this.fI = null;
      if (this.vP != null) {
         this.vP.WR();
         this.vP = null;
      }

      this.xC = null;
      this.ED = null;
      this.Sc = null;
      this.ah = null;
      this.eP = null;
      this.UO = null;
      this.Ab = null;
      this.rl = null;
      this.LM = null;
      this.EX = null;
      this.Cu = null;
      if (this.oT != null) {
         this.oT.kS();
         this.oT = null;
      }
   }

   public rn pC() {
      return this.ah;
   }

   @Override
   public ICompiler getDetectedCompiler() {
      return this.pC != null ? this.pC.getDetectedCompiler() : ICompiler.COMP_UNKNOWN;
   }

   @Override
   public void trackNativeItem(INativeType var1, IEventListener var2) {
      throw new RuntimeException();
   }

   @Override
   public void setProcessor(IProcessor var1) {
      if (this.isProcessed()) {
         throw new IllegalStateException("The processor object cannot be changed after processing");
      } else {
         this.ld = var1;
      }
   }

   @Override
   public IProcessor getProcessor() {
      return this.ld;
   }

   @Override
   public String getProcessorName() {
      ProcessorType var1 = this.ld.getType();
      if (var1 == null) {
         var1 = ProcessorType.UNKNOWN;
      }

      return var1.name().toLowerCase();
   }

   @Override
   public void setCallingConvention(ICallingConvention var1) {
      this.Ek = var1;
   }

   private ProcessorType vP() {
      return this.getProcessor().getType();
   }

   @Override
   public Endianness getEndianness() {
      if (this.getProcessor() == null) {
         throw new IllegalStateException("The processor object is not defined");
      } else {
         return this.getProcessor().getEndianness();
      }
   }

   @Override
   public void setMemory(IVirtualMemory var1) {
      if (this.isProcessed()) {
         throw new IllegalStateException("The VM object cannot be changed after processing");
      } else {
         this.gp = var1;
      }
   }

   @Override
   public IVirtualMemory getMemory() {
      return this.gp;
   }

   @Override
   public void setSubsystemType(SubsystemType var1) {
      this.Er = var1;
   }

   @Override
   public SubsystemType getSubsystemType() {
      return this.Er;
   }

   @Override
   public void setCompilerType(CompilerType var1) {
      this.hK = var1;
   }

   @Override
   public int getCodeContainerType() {
      if (this.xC != null) {
         if (this.xC instanceof IELFUnit) {
            return 1;
         }

         if (this.xC instanceof IPECOFFUnit) {
            return 2;
         }
      }

      return 0;
   }

   @Override
   public void setCodeFormatter(GenericCodeFormatter var1) {
      this.fI = var1;
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return this.fI;
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return this.rl;
   }

   @Override
   public void setAnalyzerExtension(INativeCodeAnalyzerExtension var1) {
      this.rl = var1;
   }

   @Override
   public INativeCodeModel getCodeModel() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.oT;
      }
   }

   @Override
   public INativeCodeAnalyzer getCodeAnalyzer() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.pC;
      }
   }

   @Override
   public INativeCodeAnalyzerExtensionsManager getCodeAnalyzerExtensionsManager() {
      return this.pC != null ? this.pC.UT() : null;
   }

   @Deprecated
   @Override
   public List getPackages() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.vP.getPackages();
      }
   }

   @Override
   public List getTypes() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return new ArrayList(this.vP.getTypes());
      }
   }

   @Override
   public List getFields() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return Collections.unmodifiableList(new ArrayList(this.vP.oT()));
      }
   }

   public aur pC(int var1) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.vP.UT(var1);
      }
   }

   public INativeFieldItem pC(long var1) {
      for (INativeFieldItem var4 : this.getFields()) {
         if (var4.getData() != null && var4.getData().getMemoryAddress() == var1) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public List getClasses() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return new ArrayList(this.vP.fI());
      }
   }

   public auq A(int var1) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.vP.E(var1);
      }
   }

   @Override
   public NativeSignatureDBManager getSignatureManager() {
      return this.mv;
   }

   @Override
   public IClassManager getClassManager() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.vP;
      }
   }

   @Override
   public IFieldManager getFieldManager() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.vP;
      }
   }

   @Override
   public IMethodManager getMethodManager() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this;
      }
   }

   public ayy A() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.vP;
      }
   }

   @Override
   public IPackageManager getPackageManager() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.vP;
      }
   }

   @Override
   public TypeLibraryService getTypeLibraryService() {
      return this.FE;
   }

   public auy kS() {
      return this.ED;
   }

   @Override
   public List getStrings() {
      ArrayList var1 = new ArrayList();

      for (INativeContinuousItem var3 : this.getCodeModel().getView(null, null).values()) {
         if (var3 instanceof avb var4) {
            String var5 = DataStringUtil.determineValue(var4, this.gp, false);
            var4.kS(var5);
            var1.add(var4);
         }
      }

      return var1;
   }

   public avb kS(int var1) {
      INativeDataItem var2 = this.kS().pC(var1);
      return !(var2 instanceof avb) ? null : (avb)var2;
   }

   public auq pC(String var1) {
      for (auq var3 : this.getClasses()) {
         if (Strings.equals(var3.getAddress(), var1)) {
            return var3;
         }
      }

      for (auq var5 : this.getClasses()) {
         if (Strings.equals(var5.getName(true), var1)) {
            return var5;
         }
      }

      return null;
   }

   @Override
   public INativeFieldItem getField(String var1) {
      for (INativeFieldItem var3 : this.getFields()) {
         if (Strings.equals(var3.getAddress(), var1)) {
            return var3;
         }
      }

      for (INativeFieldItem var5 : this.getFields()) {
         if (Strings.equals(var5.getName(true), var1)) {
            return var5;
         }
      }

      return null;
   }

   @Override
   public List getMethods() {
      return new ArrayList(this.A().gp());
   }

   public auu A(String var1) {
      for (auu var3 : this.getMethods()) {
         if (Strings.equals(var3.getAddress(), var1)) {
            return var3;
         }
      }

      for (auu var5 : this.getMethods()) {
         if (Strings.equals(var5.getName(true), var1)) {
            return var5;
         }
      }

      return null;
   }

   public INativeItem kS(String var1) {
      Object var2 = this.A(var1);
      if (var2 == null) {
         var2 = this.getField(var1);
         if (var2 == null) {
            var2 = this.pC(var1);
         }
      }

      return (INativeItem)var2;
   }

   @Override
   public List getInternalMethods() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return new ArrayList(this.oT.sY());
      }
   }

   @Override
   public List getInternalMethodsLeafFirst() {
      ArrayList var1 = new ArrayList();
      fc var2 = new fc(this.oT, this.oT.E());

      while (var2.hasNext()) {
         aut var3 = (aut)var2.pC();
         var1.addAll(var3.getMethodReferences());
      }

      return var1;
   }

   @Override
   public List getInternalMethodsSizeFirst() {
      ArrayList var1 = new ArrayList(this.oT.E());
      Collections.sort(var1, new SX(this));
      ArrayList var2 = new ArrayList();

      for (aut var4 : var1) {
         var2.addAll(var4.getMethodReferences());
      }

      return var2;
   }

   public auu wS(int var1) {
      for (auu var3 : this.getInternalMethods()) {
         if (var3.getIndex() == var1) {
            return var3;
         }
      }

      return null;
   }

   public auu UT(int var1) {
      auu var2 = this.A().kS(var1);
      if (var2 == null) {
         return null;
      } else {
         Assert.a(var2.getIndex() == var1);
         return var2;
      }
   }

   public auu A(long var1) {
      return this.pC(var1, true);
   }

   public auu pC(long var1, boolean var3) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.oT.kS(var1, var3);
      }
   }

   @Override
   public List getInternalMethods(long var1) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         ArrayList var3 = new ArrayList();

         for (aut var5 : this.oT.E()) {
            if (var5.A(var1)) {
               var3.addAll(var5.getMethodReferences());
            }
         }

         return var3;
      }
   }

   public auu pC(String var1, IPrototypeItem var2, INativeMethodDataItem var3) {
      return this.pC(var1, var2, var3, 0);
   }

   public auu pC(String var1, IPrototypeItem var2, INativeMethodDataItem var3, int var4) {
      auu var5 = this.A().pC(null, var1, (ays)var2, (aut)var3);
      if ((var4 & 1) != 0) {
         var5.kS(true);
      }

      if (this.pC != null && (var4 & 2) == 0 && this.pC.pC(var5, var1 != null ? var1 : var5.getName(true), (var4 & 4) != 0)) {
         var5.pC(true);
      }

      return var5;
   }

   @Override
   public NativeCommentManager getCommentManager() {
      return this.isDisposed() ? null : this.kS;
   }

   @Override
   public boolean setInlineComment(String var1, String var2) {
      return this.kS.setPrimary(var1, var2, 0, true);
   }

   @Override
   public String getInlineComment(String var1) {
      return this.kS.getPrimary(var1, 0);
   }

   @Override
   public Map getInlineComments() {
      return this.kS.getPrimaryMap(0);
   }

   @Override
   public boolean setInlineComment(long var1, String var3) {
      String var4 = this.kS.memoryToAddress(var1);
      return var4 == null ? false : this.setInlineComment(var4, var3);
   }

   @Override
   public String getInlineComment(long var1) {
      String var3 = this.kS.memoryToAddress(var1);
      return var3 == null ? null : this.getInlineComment(var3);
   }

   @Override
   public String getAddressLabel(String var1) {
      long var2 = this.getCanonicalMemoryAddress(var1);
      return var2 == -1L ? null : this.pC(var2, AutoLabelPolicy.OFF);
   }

   @Override
   public boolean isProcessed() {
      return this.pC != null;
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else {
         this.kS = new NativeCommentManager(this);
         if (this.getParent() instanceof ICodeObjectUnit) {
            this.xC = (ICodeObjectUnit)this.getParent();
            this.Er = this.xC.getLoaderInformation().getTargetSubsystem();
            long var1 = Conversion.stringToLong(this.getPropertyManager().getString("ImageBase"), -1L);
            if (var1 == -1L) {
               long var3 = this.xC.getLoaderInformation().getImageBase();
               if (var3 != 0L) {
                  var1 = var3;
               } else {
                  boolean var5 = (this.xC.getLoaderInformation().getFlags() & 32) != 0;
                  if (var5) {
                     var1 = Conversion.stringToLong(this.getPropertyManager().getString("RelocationBaseForZeroBasedRelocatableObjects"), -1L);
                     if (var1 == -1L) {
                        var1 = var3;
                     }
                  } else {
                     var1 = var3;
                  }
               }
            }

            this.setVirtualImageBase(var1);
         } else {
            int var28 = this.getPropertyManager().getInteger("Endianness");
            if (var28 == 1) {
               this.getProcessor().setEndianness(Endianness.LITTLE_ENDIAN);
            } else if (var28 == 2) {
               this.getProcessor().setEndianness(Endianness.BIG_ENDIAN);
            }

            long var2 = Conversion.stringToLong(this.getPropertyManager().getString("ImageBase"), -1L);
            if (var2 != -1L) {
               this.setVirtualImageBase(var2);
            }
         }

         ICompiler var29 = this.NS();
         if (this.hK == null || !var29.isUnknown()) {
            this.hK = var29.getType();
         }

         IPrimitiveSizes var30 = null;
         if (this.getAnalyzerExtension() != null) {
            var30 = this.getAnalyzerExtension().getPrimitiveSizes(this.Er, this.hK);
         }

         String var31 = null;
         int var4 = this.getPropertyManager().getInteger("CallingConvention");
         if (var4 != CallingConventionName.UNKNOWN.id()) {
            CallingConventionName var32 = CallingConventionName.valueOf(var4);
            if (var32 != null) {
               var31 = var32.name();
            }
         }

         if (var31 == null && this.Ek != null) {
            var31 = this.Ek.getName();
         }

         this.vP = new ayy(this, this.vP(), this.Er, this.hK, var30, this.ld.getPCRegisterBitsize() / 8, this.ld.getGPRegisterBitsize() / 8, var31);
         if (this.getAnalyzerExtension() != null) {
            this.getAnalyzerExtension().typeManagerInitialized(this.vP);
         }

         this.ED = new auy(this.vP);
         if (this.xC != null) {
            if (this.gp == null || this.gp.getSpaceBits() < 32 || this.gp.getPageSize() < 4096) {
               this.gp = new awp(32, 12);
            }

            this.gp.setStandardEndianness(this.xC.getLoaderInformation().getEndianness());
         }

         int var33 = this.gp.getSpaceBits();
         if (var33 > 64) {
            E.debug("The memory space is wider than 64-bit: %d", var33);
            var33 = 64;
         }

         this.oT = new Tw(var33, this, this, this.kS, this.ah);
         this.eP = new MemoryRanges(var33);
         this.UO = new MemoryRanges(var33);
         this.pC = new a(this.oT, this.gp, this.ld, this.xC, this.vP, this.ED, this.eP, this.UO);
         this.pC.pC(var29);
         if (!var29.isUnknown()) {
            String var6 = var29.getName();
            if (var6 != null) {
               UnitUtil.logInfo(this, null, true, E, S.L("Detected compiler: %s"), var6);
            }
         }

         this.ys = new nE(this);
         this.WR();
         this.pC.pC(this.Ab);
         this.pC(var29);
         this.pC.pC(this.Cu);
         int var34 = this.getPropertyManager().getInteger("DebugInformationUsagePolicy");
         int var7 = this.getPropertyManager().getInteger("DebugInformationRetrievalPolicy");
         this.pC.pC(var34, var7);
         this.hZ = this.getPropertyManager().getBoolean("CreatePackagesFromRoutineNames");
         IRuntimeProject var8 = RuntimeProjectUtil.findProject(this);
         if (var8 != null) {
            this.pC(var8);
            this.A(var8);
         }

         IInput var9 = this.getInput();
         if (this.xC == null) {
            this.NS = Longs.maxUnsigned(this.NS, var9.getCurrentSize());
            Assert.a(Longs.compareUnsigned(this.NS, 4294967296L) < 0, "Cannot map more than 4Gb");
            if (this.NS != 0L) {
               Assert.a(VirtualMemoryUtil.allocateFillGaps(this.gp, this.WR, (int)this.NS, 7) != 0, "Allocation error");

               try (InputStream var10 = var9.getStream()) {
                  byte[] var11 = IO.readInputStream(var10);
                  VirtualMemoryUtil.writeSafe(this.gp, this.WR, var11);
               } catch (IOException var27) {
                  return false;
               }

               this.eP.add(this.WR, this.WR + this.NS);
               this.Ab.pC();
               this.pC.enqueuePointerForAnalysis(new CodePointer(this.WR), 1);
            }
         } else {
            ILoaderInformation var35 = this.xC.getLoaderInformation();
            this.ld.setEndianness(var35.getEndianness());
            this.NS = var35.getImageSize();
            Assert.a(this.xC.map(this.gp, this.WR, true, null), "Could not map file");

            for (ISegmentInformation var13 : this.xC.getValidSegments()) {
               if ((var13.getFlags() & 1073741824) == 0) {
                  long var14 = this.WR + var13.getOffsetInMemory();
                  long var16 = var13.getSizeInMemory();
                  if (var16 != 0L) {
                     this.eP.add(var14, var14 + var16);
                  }
               }
            }

            boolean var37 = false;

            for (ISegmentInformation var41 : this.xC.getValidSections()) {
               if (var41.getFlags() != 0) {
                  long var15 = var41.getSizeInMemory();
                  if (var15 != 0L) {
                     var37 = true;
                     if ((var41.getFlags() & 1073741824) == 0 && (var41.getFlags() & 4) != 0) {
                        long var17 = this.WR + var41.getOffsetInMemory();
                        ISegmentInformation var19 = CodeObjectUnitUtil.findSegmentByRelativeAddress(this.xC, var17 - this.WR);
                        if (var19 == null || (var19.getFlags() & 4) != 0 || (var19.getFlags() & -2147483648) != 0) {
                           this.UO.add(var17, var17 + var15);
                        }
                     }
                  }
               }
            }

            for (ISegmentInformation var42 : this.xC.getValidSegments()) {
               if ((var42.getFlags() & 1073741824) == 0 && var42.getOffsetInFile() != 0L && (var42.getFlags() & 4) != 0) {
                  long var45 = this.WR + var42.getOffsetInMemory();
                  long var48 = var42.getSizeInMemory();
                  if (var48 != 0L) {
                     this.UO.add(var45, var45 + var48);
                  }
               }
            }

            boolean var40 = false;
            if (var40 && var37) {
               AddressSegmentMap var43 = new AddressSegmentMap(var33);
               var43.setRemoveSegmentsOnOverlap(true);

               for (ISegmentInformation var47 : this.xC.getValidSections()) {
                  if (var47.getFlags() != 0 && CodeObjectUnitUtil.findSegmentByRelativeAddress(this.xC, var47.getOffsetInMemory()) != null) {
                     long var49 = this.WR + var47.getOffsetInMemory();
                     if (var47.getAlignment() != 0L && var47.getAlignment() != 1L) {
                        long var50 = var47.getSizeInMemory();
                        long var21 = var47.getAlignment();
                        dt var23 = new dt(var49, var50, var21);

                        try {
                           var43.add(var23);
                        } catch (IllegalArgumentException var25) {
                        }
                     }
                  }
               }

               if (!var43.isEmpty()) {
                  this.pC.pC(var43);
               }
            }

            this.Ab.pC();
            boolean var44 = (var35.getFlags() & 4) != 0;
            if (!var44) {
               this.pC(var35);
            }

            if (this.getAnalyzerExtension() == null || !this.getAnalyzerExtension().skipSymbolProcessing()) {
               this.xC();
            }

            if (var44) {
               this.pC(var35);
            }
         }

         if (this.fI == null) {
            this.fI = new GenericCodeFormatter();
         }

         this.fI.setCodeUnit(this);
         this.Sc = new aac(this);
         nq var36 = new nq(this);
         this.pC.pC(var36);
         this.pC.pC(this.sY());
         if (this.ED()) {
            this.performInitialAnalysis(true);
         } else if (this.wS() || Boolean.TRUE.equals(this.Sc())) {
            this.performInitialAnalysis();
         }

         this.notifyListeners(new JebEvent(J.UnitProcessed));
         return true;
      }
   }

   private void pC(ILoaderInformation var1) {
      long var2 = var1.getEntryPoint();
      if (var2 != 0L || (var1.getFlags() & 16) != 0) {
         long var4 = this.WR + var2;
         CodePointer var6 = this.getProcessor().createEntryPoint(var4);
         int var7 = 0;
         if (this.Ab != null) {
            var7 = this.Ab.fI();
         }

         this.pC.enqueuePointerForAnalysis(var6, var7);
         if (this.pC(var6.getAddress(), AutoLabelPolicy.OFF) == null) {
            this.pC(var6.getAddress(), "start");
         }

         this.getNotificationManager()
            .addNotification(new UnitNotification(NotificationType.AREA_OF_INTEREST, S.L("Entry point"), this.getSymbolicStringAddress(var6.getAddress())));
      }
   }

   private void xC() {
      Collection var1 = this.xC.getSymbols();
      if (var1 != null) {
         TreeMap var2 = new TreeMap();

         for (ISymbolInformation var4 : var1) {
            if (var4.getSymbolRelativeAddress() != 0L && (var4.getFlags() & 8) == 0) {
               long var5 = this.WR + var4.getSymbolRelativeAddress();
               long var7 = var4.getSymbolSize();
               String var9 = (var4.getFlags() & 16) != 0 ? null : var4.getName();
               SymbolType var10 = var4.getType();
               int var11 = var4.getFlags();
               if (var10 == SymbolType.FUNCTION_MAYBE) {
                  boolean var12 = false;

                  try {
                     long var13 = this.gp.readPointer(var5);
                     if (this.kS(var13)) {
                        var12 = true;
                     }
                  } catch (MemoryException var20) {
                  }

                  if (!var12 && (this.UO == null || this.UO.contains(var5))) {
                     var10 = SymbolType.FUNCTION;
                  }
               }

               String var27 = null;
               if (var10 != SymbolType.FUNCTION
                  && var10 != SymbolType.EXTERN_FUNCTION
                  && var10 != SymbolType.EXTERN_DATA
                  && var10 != SymbolType.PTRFUNCTION
                  && var9 != null) {
                  IUnmangledData var29 = this.Cu.unmangleData(var9, false);
                  if (var29 != null) {
                     var27 = var9;
                     var9 = var29.getFull();
                  }
               }

               if (var10 == SymbolType.FUNCTION) {
                  CodePointer var34 = this.getProcessor().createEntryPoint(var5);
                  if (var4.getProcessorMode() != 0) {
                     var34.setMode(var4.getProcessorMode());
                  }

                  int var37 = 0;
                  if (var7 > 0L && (var11 & 4) != 0) {
                     var37 = 2 | (int)var7 << 8;
                  }

                  this.pC.enqueuePointerForAnalysis(var34, 1, var37);
                  if (var9 != null) {
                     this.pC(var34.getAddress(), var9);
                  }
               } else if (var10 == SymbolType.PTRFUNCTION) {
                  auu var36 = null;
                  if ((var11 & 1) != 0) {
                     var36 = this.wS(var9);
                     if (var36 == null) {
                        var36 = this.pC(var9, null, null);
                     }
                  } else if (((SymbolInformation)var4).isInternal() && this.Ab.kS()) {
                     Long var39 = null;

                     try {
                        var39 = this.gp.readPointer(var5);
                     } catch (MemoryException var19) {
                     }

                     var36 = this.pC(var9, var39);
                  }

                  String var40 = "ptr_" + var9;
                  this.pC.pC(var5, var40, var36, var9);
               } else if (var10 != SymbolType.EXTERN_FUNCTION && var10 != SymbolType.EXTERN_DATA) {
                  if (var10 == SymbolType.PTROBJECT) {
                     if (this.oT.getItemAt(var5) == null) {
                        ayt var33 = this.vP.E();
                        if (var33 != null) {
                           this.setDataAt(var5, var33, var9);
                        }
                     } else if (var9 != null) {
                        this.oT.oT().setLabel(var5, var9, true, true, false);
                     }
                  } else {
                     if (var10 == SymbolType.OBJECT || var10 == SymbolType.VARIABLE) {
                        Object var32 = (List)var2.get(var4.getSymbolSize());
                        if (var32 == null) {
                           var32 = new ArrayList();
                           var2.put(var4.getSymbolSize(), var32);
                        }

                        var32.add(var4);
                        continue;
                     }

                     if (var10 == SymbolType.PTRVARIABLE) {
                        if (this.oT.getItemAt(var5) == null) {
                           ayt var31 = this.vP.E();
                           if (var31 != null) {
                              this.setDataAt(var5, var31, var9);
                           }
                        } else if (var9 != null) {
                           this.oT.oT().setLabel(var5, var9, true, true, false);
                        }
                     } else if (var9 != null && !var9.isEmpty()) {
                        this.oT.oT().setLabel(var5, var9, true, true, false);
                     }
                  }
               } else {
                  String var30 = var9;
                  if (var9.startsWith("__imp_")) {
                     var30 = var9.substring(6);
                  }

                  auu var14 = this.wS(var30);
                  if (var14 == null) {
                     var14 = this.pC(var30, null, null);
                  }

                  String var15 = "extern" + var9;
                  this.pC.pC(var5, var15, var14, var9);
               }

               if (var27 != null) {
                  this.oT.oT().setLabel(var5, var27, true, true, false);
               }
            }
         }

         int var21 = this.getProcessor().getType().is64Bit() ? 8 : 4;

         for (Entry var23 : var2.entrySet()) {
            for (ISymbolInformation var24 : (List)var23.getValue()) {
               long var8 = this.WR + var24.getSymbolRelativeAddress();
               int var25 = (int)var24.getSymbolSize();
               String var26 = (var24.getFlags() & 16) != 0 ? null : var24.getName();
               SymbolType var28 = var24.getType();
               String var35 = null;
               IUnmangledData var38 = this.Cu.unmangleData(var26, false);
               if (var38 != null) {
                  var35 = var26;
                  var26 = var38.getFull();
               }

               boolean var41 = false;
               INativeContinuousItem var16 = this.oT.getItemAt(var8);
               if (var16 == null && this.oT.isEmptyRange(var8, var25 == 0 ? var21 : var25)) {
                  aye var17 = null;
                  String var18 = var24.getSymbolDataTypeInformation();
                  if (var18 != null) {
                     var17 = this.vP.UT(var18);
                  }

                  if (var17 != null) {
                     var41 = this.setDataAt(var8, var17, var26);
                  }
               } else if (var25 <= 0 || var16 != null && var25 == var16.getMemorySize()) {
                  if (var26 != null) {
                     this.oT.oT().setLabel(var8, var26, true, true, false);
                  }

                  var41 = true;
               }

               if (!var41) {
                  this.pC.vP().pC(var8, var26, var25, var28 == SymbolType.VARIABLE ? this.vP.UT("int") : null, true, var16 != null);
               }

               if (var35 != null) {
                  this.oT.oT().setLabel(var8, var35, true, true, false);
               }
            }
         }
      }
   }

   private auu pC(String var1, Long var2) {
      auu var3 = this.A().pC(null, var1, null, null);
      var3.kS(true);
      var3.pC(var2);
      if (this.pC != null && this.pC.pC(var3, var1 != null ? var1 : var3.getName(true), false)) {
         var3.pC(true);
      }

      return var3;
   }

   private boolean ED() {
      return this.getParentProject() instanceof Or && ((Or)this.getParentProject()).A;
   }

   protected boolean wS() {
      try {
         return this.getPropertyManager().getBoolean("DelayAnalysis") ? false : !JebCoreService.getInstance().getOptions().isAllowAsynchronousProcessing();
      } catch (Exception var1) {
         return false;
      }
   }

   protected boolean UT() {
      try {
         return !JebCoreService.getInstance().getOptions().isAllowAsynchronousProcessing();
      } catch (Exception var1) {
         return false;
      }
   }

   private Boolean Sc() {
      try {
         int var1 = this.getPropertyManager().getInteger("ForceSynchronousAnalysis");
         return var1 == -1 ? null : var1 != 0;
      } catch (Exception var2) {
         return null;
      }
   }

   @Override
   public boolean isInitialAnalysisDone() {
      return this.getCodeAnalyzer().getAnalysisCount() > 0;
   }

   @Override
   public boolean performInitialAnalysis() {
      return this.performInitialAnalysis(null);
   }

   @Override
   public boolean performInitialAnalysis(Boolean var1) {
      if (this.isInitialAnalysisDone()) {
         return false;
      } else {
         if (var1 == null) {
            var1 = this.UT();
            Boolean var2 = this.Sc();
            if (var2 != null) {
               var1 = var2;
            }
         }

         if (this.Ab != null) {
            UnitUtil.logInfo(this, null, true, E, Strings.ff(S.L("Initial native analysis styles: code gaps: %s, data gaps: %s"), this.Ab.Ab(), this.Ab.UO()));
         }

         uZ var3 = new uZ(this);
         return this.performAnalysis(!var1, null, var3);
      }
   }

   @Override
   public boolean performAnalysis(boolean var1, Boolean var2, Runnable var3) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else if (this.Sc != null && this.Sc.isDone()) {
         if (var2 != null) {
            this.pC.pC(Boolean.valueOf(var2));
         }

         if (!var1) {
            this.Sc.run();
            if (var3 != null) {
               var3.run();
            }

            return true;
         } else {
            this.Sc.start(var3);
            return true;
         }
      } else {
         return false;
      }
   }

   protected void E() {
      if (this.hZ) {
         for (auu var2 : this.getInternalMethods()) {
            this.pC(var2);
         }
      }
   }

   private void pC(auu var1) {
      String var2 = var1.getName(true);
      ayp var3 = this.vP.pC((INativeItem)var1);
      boolean var4 = false;
      if (var2 != null && TypeUtil.containsCppSeparator(var2) && !var2.contains("→")) {
         List var5 = TypeUtil.splitCppName(var2);
         if (var5.size() >= 2) {
            var4 = true;
            ayo var6 = this.vP.pC(var5.subList(0, var5.size() - 1));
            if (var6 != null) {
               ayp var7 = (ayp)var6.A();
               if (var7 != null) {
                  if (!this.vP.moveToPackage(var1, var7) && !this.UW) {
                     this.UW = true;
                     JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("Package automatic move failed"));
                  }
               } else if (!this.UW) {
                  this.UW = true;
                  JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("Package automatic creation failed"));
               }
            }
         }
      }

      if (var3 != null && !var3.isRootPackage()) {
         if (!var4) {
            this.vP.moveToPackage(var1, (IPackage)this.vP.ld().A());
         }

         if (var3.getChildren().isEmpty()) {
            this.vP.deletePackage(var3);
         }
      }
   }

   private String A(INativeItem var1) {
      if (var1 == null) {
         return null;
      } else {
         return var1 instanceof aun ? ((aun)var1).A(true) : var1.getName(true);
      }
   }

   private void ah() {
      ph var1 = this.pC.ys().oT();
      Map var2 = var1.getPrimaryLabels();

      label65:
      for (Entry var4 : var2.entrySet()) {
         if (Character.isDigit(((String)var4.getValue()).charAt(((String)var4.getValue()).length() - 1))) {
            INativeContinuousItem var5 = this.pC.ys().getItemAt((Long)var4.getKey());
            if (var5 != null) {
               String var6 = this.A(var5);
               if (!((String)var4.getValue()).equals(var6) && ((String)var4.getValue()).startsWith(var6)) {
                  for (int var7 = var6.length(); var7 < ((String)var4.getValue()).length(); var7++) {
                     if (!Character.isDigit(((String)var4.getValue()).charAt(var7))) {
                        continue label65;
                     }
                  }

                  if (!var1.getPrimaryLabels().containsValue(var6)) {
                     var1.setLabel((Long)var4.getKey(), var6, false, false, true);
                  } else {
                     Long var12 = var1.resolveLabel(var6);
                     if (var12 != null) {
                        try {
                           long var8 = this.gp.readPointer(var12);
                           if (var8 == (Long)var4.getKey()) {
                              String var10 = "ptr_" + var6;
                              var1.removeLabel(var12);
                              var1.setLabel(var12, var10, true, true, true);
                              var1.setLabel((Long)var4.getKey(), var6, false, false, true);
                           } else {
                              var8 = this.gp.readPointer((Long)var4.getKey());
                              if (var8 == var12) {
                                 String var14 = "ptr_" + var6;
                                 var1.removeLabel((Long)var4.getKey());
                                 var1.setLabel((Long)var4.getKey(), var14, true, true, true);
                              }
                           }
                        } catch (MemoryException var11) {
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean isAnalysisCompleted() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return (this.Sc == null || this.Sc.isDone()) && !this.pC.isAnalyzing();
      }
   }

   protected boolean sY() {
      int var1 = this.getPropertyManager().getInteger("AdvancedAnalysis");
      switch (var1) {
         case 0:
            return false;
         case 1:
            if (this.UO != null && this.UO.aggregatedRangesSize() < 1000000L) {
               return true;
            }

            return false;
         case 2:
            return true;
         default:
            return false;
      }
   }

   protected boolean ys() {
      return this.getPropertyManager().getBoolean("PerformRttiRecovery");
   }

   protected boolean ld() {
      return this.getPropertyManager().getBoolean("PerformGlobalAnalysis");
   }

   @Override
   public ICodeObjectUnit getCodeObjectContainer() {
      return this.xC;
   }

   private auu wS(String var1) {
      auu var2 = this.A(var1);
      if (var2 != null) {
         return var2;
      } else if (this.pC == null) {
         E.debug("analyzer has to be there for library routine import");
         return null;
      } else {
         var2 = this.pC.pC(var1);
         if (var2 == null) {
            E.debug("Could not find reference to external artifact: %s", var1);
            return null;
         } else {
            auu var3 = this.vP.pC(this, var2);
            if (var3 == null) {
               E.error(S.L("Import() failed for routine: %s"), var2);
               return null;
            } else {
               if (!var3.getName(false).equals(var1)) {
                  var3.pC(var1);
               }

               ays var4 = var3.UT();
               if (var4 != null && var4.getPrototypeAttributes().contains(PrototypeAttribute.NoReturn)) {
                  var3.wS(true);
               }

               return var3;
            }
         }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() instanceof auu var3) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            String var4 = var3.getName(true);
            if (var4 != null) {
               auu var5 = (auu)this.A.get(var4);
               if (var5 != null && (var5.isDisposed() || var5 == var3)) {
                  this.A.remove(var4);
               }
            }
         }

         if (var1.getType() == NativeItemEventType.UPDATED && var1.getSubtype() == NativeItemEventSubType.RENAMED) {
            String var11 = (String)var1.getDetails();
            String var13 = var3.getName(true);
            if (var11 != null) {
               auu var6 = (auu)this.A.get(var11);
               if (var6 != null && (var6.isDisposed() || var6 == var3)) {
                  this.A.remove(var11);
               }
            }

            boolean var15 = true;
            if (var13 != null && this.A.containsKey(var13)) {
               auu var7 = (auu)this.A.get(var13);
               if (!var7.isDisposed() && var7 != var3) {
                  var15 = false;
               }
            }

            if (var13 != null && var15) {
               this.A.put(var13, var3);
            }

            if (this.hZ && this.isInitialAnalysisDone()) {
               this.pC(var3);
            }
         }

         if (var1.getType() == NativeItemEventType.UPDATED
            && (var1.getSubtype() == NativeItemEventSubType.ATTRIBUTE_REMOVED || var1.getSubtype() == NativeItemEventSubType.ATTRIBUTE_SET)) {
            String var12 = (String)var1.getDetails();
            if (var12 != null && var12.equals("isNonReturning") && var3.E() != null) {
               HashSet var14 = new HashSet(this.oT.getCallGraphManager().getGlobalCallGraph().getCallerRoutines(CodePointer.createFrom(var3), true));
               Object var16 = Collections.emptySet();
               int var8 = 0;

               while (var14.size() != var16.size()) {
                  var16 = new HashSet(var14);

                  for (INativeMethodItem var10 : var16) {
                     var14.addAll(this.oT.getCallGraphManager().getGlobalCallGraph().getCallerRoutines(CodePointer.createFrom(var10), true));
                  }

                  if (var8++ == 10) {
                     break;
                  }
               }

               if (!var14.isEmpty()) {
                  for (INativeMethodItem var19 : var14) {
                     this.pC.enqueuePointerForAnalysis(CodePointer.createFrom(var19), 2, 8);
                  }

                  Kt var18 = this.pC.WR();
                  if (var18 != null) {
                     var18.pC();
                  }

                  if (this.pC.needsAnalysis()) {
                     this.pC.analyze();
                  }
               }
            }
         }
      }

      this.gp();
   }

   @Override
   public void onModelUpdate(MemoryModelEvent var1) {
      Assert.a(var1.getModel() == this.oT);
      this.gp();
   }

   void pC(boolean var1) {
      if (var1 && !this.sO) {
         this.sO = true;
         this.os = 0;
      } else if (!var1 && this.sO) {
         this.sO = false;
         if (this.os >= 1) {
            this.gp();
         }

         this.os = 0;
      }
   }

   void gp() {
      if (this.sO) {
         this.os++;
      } else {
         this.notifyListeners(new JebEvent(J.UnitChange));
      }
   }

   @Override
   public void setPhysicalImageBase(long var1) {
      this.Aj = var1;
   }

   @Override
   public long getPhysicalImageDelta() {
      return this.Aj != 0L ? this.Aj - this.WR : 0L;
   }

   @Override
   public long getVirtualImageBase() {
      return this.WR;
   }

   @Override
   public void setVirtualImageBase(long var1) {
      if (this.isProcessed()) {
         throw new IllegalStateException("The unit was already processed");
      } else {
         this.WR = var1;
      }
   }

   @Override
   public long getImageSize() {
      return this.NS;
   }

   public long oT() {
      return this.WR + this.NS;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new W(this, 1L, S.L("Disassembly"), true), false);
      }

      return var1;
   }

   @Override
   public INativeDisassemblyDocument getDisassemblyDocument() {
      return new axz(this);
   }

   @Override
   public String getDisassembly() {
      INativeDisassemblyDocument var1 = this.getDisassemblyDocument();

      String var2;
      try {
         var2 = var1.format();
      } finally {
         var1.dispose();
      }

      return var2;
   }

   @Override
   public ICodeHierarchy getHierarchy() {
      if (this.EX == null) {
         this.EX = new ayk(this);
      }

      return this.EX;
   }

   public boolean kS(long var1) {
      return Longs.compareUnsigned(var1, this.WR) >= 0 && Longs.compareUnsigned(var1, this.WR + this.NS) < 0;
   }

   public boolean pC(long var1, long var3) {
      return Longs.compareUnsigned(var1, var3) <= 0 && Longs.compareUnsigned(var1, this.WR) >= 0 && Longs.compareUnsigned(var3, this.WR + this.NS) < 0;
   }

   public String wS(long var1) {
      return this.pC(var1, AutoLabelPolicy.ON);
   }

   public String pC(long var1, AutoLabelPolicy var3) {
      return this.oT.pC(var1, this.getPhysicalImageDelta(), var3);
   }

   public void pC(long var1, String var3) {
      this.oT.pC(var1, var3);
   }

   @Override
   public boolean setRoutinePrototype(INativeMethodItem var1, String var2) {
      auu var3 = (auu)var1;
      TypeStringParser var5 = new TypeStringParser(this.A());

      IPrototypeItem var4;
      try {
         var4 = var5.parsePrototype(var2);
      } catch (TypeStringParseException var6) {
         return false;
      }

      var3.setPrototype(var4);
      return true;
   }

   @Override
   public boolean setRoutineSignature(INativeMethodItem var1, String var2, boolean var3) {
      auu var4 = (auu)var1;
      TypeStringParser var6 = new TypeStringParser(this.A());

      IPrototypeItem var5;
      try {
         var5 = var6.parseSignature(var2, true);
      } catch (TypeStringParseException var8) {
         E.catchingSilent(var8);
         E.error(S.L("Cannot parse signature: \"%s\" (to be applied on %s)"), var2, var1);
         return false;
      }

      var4.setPrototype(var5);
      if (!var3) {
         if (!CollectionUtil.compare(var4.getParameterNames(), var5.getParameterNames(), true)) {
            var4.setParameterNames(var5.getParameterNames());
         }

         if (!Strings.equals(var4.getName(true), var5.getRoutineName())) {
            var4.setName(var5.getRoutineName());
         }
      }

      return true;
   }

   @Override
   public INativeType getDataTypeAt(long var1) {
      INativeContinuousItem var3 = this.getCodeModel().getItemAt(var1);
      return !(var3 instanceof INativeDataItem) ? null : ((INativeDataItem)var3).getType();
   }

   @Override
   public boolean setDataTypeAt(long var1, INativeType var3) {
      return this.setDataAt(var1, var3, null);
   }

   @Override
   public boolean setDataAt(long var1, INativeType var3, String var4) {
      return this.setDataAt(var1, var3, var4, true);
   }

   @Override
   public boolean setDataAt(long var1, INativeType var3, String var4, boolean var5) {
      return this.pC(var1, var3, var4, var5, false);
   }

   public boolean pC(long var1, INativeType var3, String var4, boolean var5, boolean var6) {
      aye var7 = (aye)var3;
      if (!var5 && !this.oT.isEmptyRange(var1, var3.getSize())) {
         return false;
      } else {
         if (var7.kS() != this.A()) {
            var7 = this.A().pC(var7);
            if (var7 == null) {
               return false;
            }
         }

         if (var4 == null) {
            INativeContinuousItem var8 = this.getNativeItemAt(var1);
            var4 = this.A(var8);
         }

         INativeDataItem var9 = this.pC.defineData(var1, var7);
         if (var9 == null) {
            return false;
         } else {
            if (var4 != null) {
               var9.setName(var4);
               if (var6) {
                  this.pC.pC(var9);
               }
            } else {
               this.pC.vP().pC(this.getVirtualImageBase(), var9, true);
            }

            return true;
         }
      }
   }

   @Override
   public boolean setStringAt(long var1, long var3, StringEncoding var5, int var6, int var7) {
      return this.setStringAt(var1, var3, var5, var6, var7, true);
   }

   @Override
   public boolean setStringAt(long var1, long var3, StringEncoding var5, int var6, int var7, boolean var8) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.pC.pC(var1, var3, var5, var6, var7, var8) != null;
      }
   }

   @Override
   public boolean setCodeAt(long var1, int var3, boolean var4) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.pC.pC(var1, var3, 3, var4) != null;
      }
   }

   @Override
   public boolean setRoutineReferenceAt(long var1, INativeMethodItem var3) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else if (var3 == null) {
         return false;
      } else {
         String var4 = "_" + var3.getName(true);
         auz var5 = this.pC.pC(var1, var4, (auu)var3, null);
         return var5 != null;
      }
   }

   @Override
   public boolean setRoutineAt(long var1) {
      return this.setRoutineAt(var1, 0);
   }

   @Override
   public boolean setRoutineAt(long var1, int var3) {
      return this.setRoutineAt(var1, var3, 3);
   }

   @Override
   public boolean setRoutineAt(long var1, int var3, int var4) {
      CodePointer var5 = this.getProcessor().createEntryPoint(var1);
      if (var3 != 0) {
         var5.setMode(var3);
      }

      this.getCodeAnalyzer().enqueuePointerForAnalysis(var5, var4);
      this.getCodeAnalyzer().analyze();
      return this.oT.E(var5.getAddress()) != null;
   }

   @Override
   public INativeContinuousItem getNativeItemAt(long var1) {
      return this.getCodeModel().getItemAt(var1);
   }

   @Override
   public INativeContinuousItem getNativeItemOver(long var1) {
      return this.getCodeModel().getItemOver(var1);
   }

   @Override
   public SortedMap getNativeItemsOver(long var1, int var3) {
      return this.getCodeModel().getItemsInRange(var1, true, var1 + var3, true);
   }

   @Override
   public boolean undefineItem(long var1) {
      INativeContinuousItem var3 = this.getCodeModel().getItemAt(var1);
      if (var3 == null) {
         return false;
      } else {
         this.oT.pC(var3, false);
         return true;
      }
   }

   public long UT(long var1) {
      return this.ys.kS(var1);
   }

   public long pC(IInstruction var1) {
      return this.ys.pC(var1);
   }

   public long pC(long var1, int var3) {
      return this.ys.A(var1, var3);
   }

   public long E(long var1) {
      return this.ys.wS(var1);
   }

   public long pC(int var1, int var2) {
      return this.ys.pC(var1, var2);
   }

   public long pC(IStructureType var1, int var2) {
      return this.ys.pC(var1, var2);
   }

   public String sY(long var1) {
      return this.ys.UT(var1);
   }

   @Override
   public String getAddressOfItem(long var1) {
      return this.ys.pC(var1);
   }

   public Long pC(INativeItem var1) {
      return this.ys.pC(var1);
   }

   @Override
   public long getItemAtAddress(String var1) {
      return this.ys.pC(var1);
   }

   @Override
   public long getCanonicalMemoryAddress(String var1, AddressConversionPrecision var2) {
      Long var3 = this.ys.pC(var1, var2);
      return var3 == null ? -1L : var3;
   }

   @Override
   public long getCanonicalMemoryAddress(String var1) {
      return this.getCanonicalMemoryAddress(var1, AddressConversionPrecision.DEFAULT);
   }

   @Override
   public String getSymbolicStringAddress(long var1) {
      return this.getSymbolicStringAddress(var1, 0);
   }

   @Override
   public String getSymbolicStringAddress(long var1, int var3) {
      return this.ys.pC(var1, var3);
   }

   @Override
   public INativeItem getItemObject(long var1) {
      return this.ys.A(var1);
   }

   @Override
   public ICodeCoordinates getCodeCoordinatesFromAddress(String var1) {
      return this.ys.A(var1);
   }

   @Override
   public final String getAddressFromCodeCoordinates(ICodeCoordinates var1) {
      return this.getAddressFromCodeCoordinates(var1, AddressConversionPrecision.FINE);
   }

   @Override
   public String getAddressFromCodeCoordinates(ICodeCoordinates var1, AddressConversionPrecision var2) {
      return this.ys.pC(var1, var2);
   }

   @Override
   public IInputLocation addressToLocation(String var1) {
      return this.ys.kS(var1);
   }

   @Override
   public String locationToAddress(IInputLocation var1) {
      return this.ys.pC(var1);
   }

   @Override
   public List getGlobalActions() {
      return this.sY.pC();
   }

   @Override
   public List getItemActions(long var1) {
      return this.sY.pC(var1);
   }

   @Override
   public List getAddressActions(String var1) {
      return this.sY.pC(var1);
   }

   @Override
   public boolean canExecuteAction(ActionContext var1) {
      return this.sY.pC(var1);
   }

   @Override
   public boolean prepareExecution(ActionContext var1, IActionData var2) {
      return this.sY.pC(var1, var2, true, false);
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2, boolean var3) {
      return this.sY.pC(var1, var2, false, var3);
   }

   @Override
   public IMetadataManager getMetadataManager() {
      IMetadataManager var1 = super.getMetadataManager();
      synchronized (this) {
         if (var1.getGroupByName("primary") == null) {
            var1.addGroup(new ys(this));
         }

         return var1;
      }
   }

   @Override
   public List getContributions() {
      List var1 = super.getContributions();
      synchronized (this) {
         if (this.LM == null) {
            this.LM = new axu(this);
            var1.add(this.LM);
         }

         if (this.PR == null && this.eP()) {
            String var3 = this.UO();

            try {
               this.PR = this.UT(var3);
               if (this.PR != null) {
                  var1.add(this.PR);
               }
            } catch (Exception var6) {
               E.error(
                  S.L("The doc contribution for %s unit cannot be created.\nPlease update your properties, this doc root location seems illegal: %s"),
                  this,
                  var3
               );
               E.catching(var6);
            }
         }

         return var1;
      }
   }

   private boolean eP() {
      switch (this.vP().id()) {
         case 40:
         case 183:
            return true;
         default:
            return false;
      }
   }

   private String UO() {
      switch (this.vP().id()) {
         case 40:
            return this.getPropertyManager().getString("DocumentationRoot");
         case 183:
            return this.getPropertyManager().getString("DocumentationRoot");
         default:
            return null;
      }
   }

   private IUnitContribution UT(String var1) {
      if (!Strings.isBlank(var1) && (this.cX == null || !this.cX.equals(var1))) {
         this.cX = var1;

         try {
            Net var2 = ((Nj)RuntimeProjectUtil.findProject(this).getEnginesContext()).kS();
            switch (this.vP().id()) {
               case 40:
               case 183:
                  return new com.pnfsoftware.jeb.corei.parsers.arm.Ws(this, var1, var2);
            }
         } catch (Exception var3) {
            E.error(
               S.L("The documentation contribution for an %s unit cannot be created.\nPlease update your properties, this root location seems illegal: %s"),
               this.ld.getType().name(),
               var1
            );
            E.catching(var3);
         }
      } else {
         E.debug("This root location is illegal for %s Processor: %s", this.ld.getType().name(), var1);
      }

      return null;
   }

   @Override
   public IQuickStateObject generateQuickState() {
      return !this.isProcessed() ? null : new Fu(this);
   }

   @Override
   public long getEntryPointAddress() {
      return this.xC == null ? -1L : this.getVirtualImageBase() + this.xC.getLoaderInformation().getEntryPoint();
   }

   @Override
   public long getHighLevelEntryPointAddress() {
      return this.z == null ? -1L : this.z;
   }

   public void ys(long var1) {
      this.z = var1;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      if (this.isProcessed()) {
         Strings.ff(var1, S.L("\nInformation:\n"));
         Strings.ff(var1, S.L("- Processor type: %s\n"), this.vP());
         ICompiler var2 = this.getDetectedCompiler();
         if (var2 != null) {
            Strings.ff(var1, S.L("- Compiler (detected): %s\n"), var2.getName());
         }

         Strings.ff(var1, S.L("- Methods: %d (internal: %d)\n"), this.getMethods().size(), this.getInternalMethods().size());
      }

      return var1.toString();
   }

   @Override
   public List getRoutines() {
      return Collections.unmodifiableList(this.getMethods());
   }

   @Override
   public INativeMethodItem getRoutine(long var1) {
      return this.A(var1);
   }

   @Override
   public INativeMethodItem getRoutineOver(long var1) {
      return this.pC(var1, false);
   }

   @Override
   public INativeMethodItem getRoutineByName(String var1) {
      return this.A(var1);
   }

   @Override
   public INativeFieldItem getField(long var1) {
      return this.pC(var1);
   }

   @Override
   public IBranchResolution getDynamicBranchResolution(long var1) {
      return this.oT.WR(var1);
   }

   @Override
   public boolean recordDynamicBranchTarget(long var1, boolean var3, IBranchTarget var4) {
      return this.getCodeAnalyzer().recordDynamicBranchTarget(var1, var3, var4, true);
   }

   @Override
   public void requestRoutineReanalysis(INativeMethodItem var1) {
      this.getCodeAnalyzer().enqueueRoutineForReanalysis(var1);
   }

   @Override
   public Collection getWellKnownAddresses(int var1, Predicate var2) {
      return new C.Av(var1, var2).pC();
   }

   @Override
   public INativeDecompilerUnit getDecompiler() {
      return (INativeDecompilerUnit)DecompilerHelper.getDecompiler(this, true);
   }

   private class Av {
      private List A;
      private int kS;
      private Predicate wS;

      Av(int var2, Predicate var3) {
         if (var2 < 0) {
            var2 = Integer.MAX_VALUE;
         }

         this.kS = var2;
         this.wS = var3;
      }

      public List pC() {
         if (this.A == null) {
            this.A = new ArrayList();

            for (String var3 : C.this.getCodeModel().getLabelManager().getPrimaryLabels().values()) {
               if (!this.pC(var3)) {
                  return this.A;
               }
            }

            if (!this.pC(C.this.getInternalMethods())) {
               return this.A;
            }
         }

         return this.A;
      }

      private boolean pC(Collection var1) {
         for (auo var3 : var1) {
            if (!this.pC(var3.getAddress())) {
               return false;
            }
         }

         return true;
      }

      boolean pC(String var1) {
         if (!Strings.isBlank(var1) && (this.wS == null || this.wS.test(var1))) {
            this.A.add(var1);
            if (this.A.size() >= this.kS) {
               return false;
            }
         }

         return true;
      }
   }
}
