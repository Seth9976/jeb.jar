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
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEvent;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataHints;
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
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.AddressSegmentMap;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.WeakValueMap;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
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
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.Predicate;

@Ser
public class abg extends AbstractCodeUnit implements INativeCodeUnit, aam {
   private static final String gO = "Unit is not processed";
   private static final ILogger nf = GlobalLog.getLogger(abg.class);
   public static final String q = "start";
   @SerId(1)
   private abc gP = new abc(this);
   @SerId(2)
   private abd za;
   @SerId(3)
   private IProcessor lm;
   @SerId(4)
   private IVirtualMemory zz;
   @SerId(5)
   private aaf JY;
   @SerId(6)
   aae RF;
   @SerId(7)
   private GenericCodeFormatter HF;
   @SerId(8)
   private long LK;
   @SerId(9)
   private long io;
   @SerId(10)
   private bby qa;
   @SerId(11)
   private ICodeObjectUnit Hk;
   @SerId(12)
   private axt Me;
   @SerId(13)
   private abu PV;
   @SerId(14)
   private be oQ = new be();
   @SerId(15)
   private MemoryRanges xW;
   @SerId(16)
   private MemoryRanges KT;
   @SerId(17)
   private aaw Gf;
   @SerId(18)
   private INativeCodeAnalyzerExtension Ef;
   @SerId(19)
   WeakValueMap xK;
   @SerId(20)
   private Long cC;
   @SerId(21)
   NativeCommentManager Dw;
   @SerId(22)
   private ICallingConvention sH;
   @SerTransient
   private CompilerType CE;
   @SerTransient
   private SubsystemType wF;
   @SerTransient
   public IQuickStateObject Uv;
   @SerTransient
   private TypeLibraryService If;
   @SerTransient
   private long Dz;
   @SerTransient
   private bbk mI;
   @SerTransient
   private IUnitContribution jq;
   @SerTransient
   private NativeSignatureDBManager ui;
   @SerTransient
   private boolean TX;
   @SerTransient
   private int Rr = 0;
   @SerTransient
   private UnmanglerService EB;
   @SerTransient
   private boolean Xo;
   @SerTransient
   private boolean Bu;
   @SerTransient
   private IUnitContribution IN;
   @SerTransient
   private String rL;
   @SerTransient
   public boolean oW;

   @SerCustomInitPostGraph
   private void JY() {
      this.xK = new WeakValueMap();
      if (this.Dw == null) {
         this.Dw = new NativeCommentManager(this);
      }

      if (this.JY != null && this.JY.RF == null) {
         this.JY.RF = this.Dw;
      }
   }

   public abg(String var1, String var2, IInput var3, IUnitProcessor var4, IUnitCreator var5, IPropertyDefinitionManager var6) {
      super(var1, var2, var3, var4, var5, var6);
      this.JY();
   }

   @Override
   public void postDeserialization(IRuntimeProject var1) {
      if (this.isProcessed()) {
         this.q(var1);
         this.RF(var1);
         ICompiler var2 = this.RF.getDetectedCompiler();
         if (var2 == null) {
            var2 = this.LK();
            this.RF.q(var2);
         }

         this.HF();
         this.RF.q(this.Gf);
         this.q(var2);
         this.RF.q(this.EB);
         int var3 = this.getPropertyManager().getInteger("DebugInformationUsagePolicy");
         int var4 = this.getPropertyManager().getInteger("DebugInformationRetrievalPolicy");
         this.RF.q(var3, var4);
         this.Xo = this.getPropertyManager().getBoolean("CreatePackagesFromRoutineNames");
      }
   }

   private void HF() {
      if (this.Gf == null) {
         this.Gf = new aaw(this);
      }
   }

   private void q(IRuntimeProject var1) {
      if (this.getPropertyManager().getBoolean("UseTypeLibraries")) {
         this.If = var1.getEnginesContext().getTypeLibraryService();
         this.qa.q(this.If);
         if (this.Hk instanceof IPECOFFUnit) {
            if (((IPECOFFUnit)this.Hk).getPEOptionalHeader() == null) {
               this.If.load(this.io(), 10);
            } else {
               byte var2 = 0;
               short var3 = ((IPECOFFUnit)this.Hk).getPEOptionalHeader().getSubsystem();
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
                  this.If.load(this.io(), var2);
               }

               this.If.load(this.io(), 9);
            }
         } else if (this.Hk instanceof IELFUnit) {
            if (!this.LK().isAndroidCompatible() && UnitUtil.findAncestor(this, IApkUnit.class, false) == null) {
               this.If.load(this.io(), 2);
            } else {
               this.If.load(this.io(), 3);
            }
         }
      }
   }

   private void RF(IRuntimeProject var1) {
      switch (this.getPropertyManager().getInteger("SignaturePackagesLoading")) {
         case 0:
            return;
         case 1:
         case 2:
            this.ui = var1.getEnginesContext().getNativeSignatureDBManager();
            this.RF.q(this.ui);
      }
   }

   private void q(ICompiler var1) {
      this.EB = new UnmanglerService(this);
      if (var1.isVisualStudio() || this.Hk instanceof com.pnfsoftware.jeb.corei.parsers.wincoff.nI) {
         this.EB.registerEngine(1);
         this.EB.registerEngine(3);
      } else if (var1.isLinuxCompatible() || this.Hk instanceof com.pnfsoftware.jeb.corei.parsers.macho.EE) {
         this.EB.registerEngine(4);
      }
   }

   private ICompiler LK() {
      int var1 = this.getPropertyManager().getInteger("CompilerIdentification");
      return var1 != 0 ? Tq.q(var1) : Tq.q(this.Hk);
   }

   @Override
   public void dispose() {
      if (this.PV != null) {
         this.PV.cancel(true);

         try {
            this.PV.join();
         } catch (InterruptedException var2) {
            nf.catching(var2);
         }
      }

      if (this.ui != null && this.RF != null) {
         this.ui.unregisterAnalyzer(this.RF);
      }

      super.dispose();
      this.gP = null;
      this.za = null;
      this.lm = null;
      this.zz = null;
      if (this.JY != null) {
         this.JY.xK();
      }

      this.RF = null;
      this.HF = null;
      if (this.qa != null) {
         this.qa.Hk();
         this.qa = null;
      }

      this.Hk = null;
      this.Me = null;
      this.PV = null;
      this.oQ = null;
      this.xW = null;
      this.KT = null;
      this.Gf = null;
      this.Ef = null;
      this.jq = null;
      this.mI = null;
      this.EB = null;
      if (this.JY != null) {
         this.JY.Dw();
         this.JY = null;
      }
   }

   public be q() {
      return this.oQ;
   }

   @Override
   public ICompiler getDetectedCompiler() {
      return this.RF != null ? this.RF.getDetectedCompiler() : ICompiler.COMP_UNKNOWN;
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
         this.lm = var1;
      }
   }

   @Override
   public IProcessor getProcessor() {
      return this.lm;
   }

   @Override
   public String getProcessorName() {
      ProcessorType var1 = this.lm.getType();
      if (var1 == null) {
         var1 = ProcessorType.UNKNOWN;
      }

      return var1.name().toLowerCase();
   }

   @Override
   public void setCallingConvention(ICallingConvention var1) {
      this.sH = var1;
   }

   private ProcessorType io() {
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
         this.zz = var1;
      }
   }

   @Override
   public IVirtualMemory getMemory() {
      return this.zz;
   }

   @Override
   public void setSubsystemType(SubsystemType var1) {
      this.wF = var1;
   }

   @Override
   public SubsystemType getSubsystemType() {
      return this.wF;
   }

   @Override
   public void setCompilerType(CompilerType var1) {
      this.CE = var1;
   }

   @Override
   public int getCodeContainerType() {
      if (this.Hk != null) {
         if (this.Hk instanceof IELFUnit) {
            return 1;
         }

         if (this.Hk instanceof IPECOFFUnit) {
            return 2;
         }
      }

      return 0;
   }

   @Override
   public void setCodeFormatter(GenericCodeFormatter var1) {
      this.HF = var1;
   }

   @Override
   public GenericCodeFormatter getCodeFormatter() {
      return this.HF;
   }

   @Override
   public INativeCodeAnalyzerExtension getAnalyzerExtension() {
      return this.Ef;
   }

   @Override
   public void setAnalyzerExtension(INativeCodeAnalyzerExtension var1) {
      this.Ef = var1;
   }

   @Override
   public INativeCodeModel getCodeModel() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.JY;
      }
   }

   @Override
   public INativeCodeAnalyzer getCodeAnalyzer() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.RF;
      }
   }

   @Override
   public INativeCodeAnalyzerExtensionsManager getCodeAnalyzerExtensionsManager() {
      return this.RF != null ? this.RF.Uv() : null;
   }

   @Deprecated
   @Override
   public List getPackages() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.qa.getPackages();
      }
   }

   @Override
   public List getTypes() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return new ArrayList(this.qa.getTypes());
      }
   }

   @Override
   public List getFields() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return Collections.unmodifiableList(new ArrayList(this.qa.LK()));
      }
   }

   public axm q(int var1) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.qa.Uv(var1);
      }
   }

   public INativeFieldItem q(long var1) {
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
         return new ArrayList(this.qa.io());
      }
   }

   public axl RF(int var1) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.qa.oW(var1);
      }
   }

   @Override
   public NativeSignatureDBManager getSignatureManager() {
      return this.ui;
   }

   @Override
   public IClassManager getClassManager() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.qa;
      }
   }

   @Override
   public IFieldManager getFieldManager() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.qa;
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

   public bby RF() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.qa;
      }
   }

   @Override
   public IPackageManager getPackageManager() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.qa;
      }
   }

   @Override
   public TypeLibraryService getTypeLibraryService() {
      return this.If;
   }

   public axt xK() {
      return this.Me;
   }

   @Override
   public List getStrings() {
      ArrayList var1 = new ArrayList();

      for (INativeContinuousItem var3 : this.getCodeModel().getView(null, null).values()) {
         if (var3 instanceof axw var4) {
            String var5 = DataStringUtil.determineValue(var4, this.zz, false);
            var4.xK(var5);
            var1.add(var4);
         }
      }

      return var1;
   }

   public axw xK(int var1) {
      INativeDataItem var2 = this.xK().q(var1);
      return !(var2 instanceof axw) ? null : (axw)var2;
   }

   public axl q(String var1) {
      for (axl var3 : this.getClasses()) {
         if (Strings.equals(var3.getAddress(), var1)) {
            return var3;
         }
      }

      for (axl var5 : this.getClasses()) {
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
      return new ArrayList(this.RF().HF());
   }

   public axp RF(String var1) {
      for (axp var3 : this.getMethods()) {
         if (Strings.equals(var3.getAddress(), var1)) {
            return var3;
         }
      }

      for (axp var5 : this.getMethods()) {
         if (Strings.equals(var5.getName(true), var1)) {
            return var5;
         }
      }

      return null;
   }

   public INativeItem xK(String var1) {
      Object var2 = this.RF(var1);
      if (var2 == null) {
         var2 = this.getField(var1);
         if (var2 == null) {
            var2 = this.q(var1);
         }
      }

      return (INativeItem)var2;
   }

   @Override
   public List getInternalMethods() {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return new ArrayList(this.JY.gP());
      }
   }

   @Override
   public List getInternalMethodsLeafFirst() {
      ArrayList var1 = new ArrayList();
      aaq var2 = new aaq(this.JY, this.JY.nf());

      while (var2.hasNext()) {
         axo var3 = (axo)var2.q();
         var1.addAll(var3.getMethodReferences());
      }

      return var1;
   }

   @Override
   public List getInternalMethodsSizeFirst() {
      ArrayList var1 = new ArrayList(this.JY.nf());
      Collections.sort(var1, new abh(this));
      ArrayList var2 = new ArrayList();

      for (axo var4 : var1) {
         var2.addAll(var4.getMethodReferences());
      }

      return var2;
   }

   public axp Dw(int var1) {
      for (axp var3 : this.getInternalMethods()) {
         if (var3.getIndex() == var1) {
            return var3;
         }
      }

      return null;
   }

   public axp Uv(int var1) {
      axp var2 = this.RF().xK(var1);
      if (var2 == null) {
         return null;
      } else {
         Assert.a(var2.getIndex() == var1);
         return var2;
      }
   }

   public axp RF(long var1) {
      return this.q(var1, true);
   }

   public axp q(long var1, boolean var3) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.JY.xK(var1, var3);
      }
   }

   @Override
   public List getInternalMethods(long var1) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         ArrayList var3 = new ArrayList();

         for (axo var5 : this.JY.nf()) {
            if (var5.RF(var1)) {
               var3.addAll(var5.getMethodReferences());
            }
         }

         return var3;
      }
   }

   public axp q(String var1, IPrototypeItem var2, INativeMethodDataItem var3) {
      return this.q(var1, var2, var3, 0);
   }

   public axp q(String var1, IPrototypeItem var2, INativeMethodDataItem var3, int var4) {
      axp var5 = this.RF().q(null, var1, (bbs)var2, (axo)var3);
      if ((var4 & 1) != 0) {
         var5.xK(true);
      }

      if (this.RF != null && (var4 & 2) == 0 && this.RF.q(var5, var1 != null ? var1 : var5.getName(true), (var4 & 4) != 0)) {
         var5.q(true);
      }

      return var5;
   }

   @Override
   public NativeCommentManager getCommentManager() {
      return this.isDisposed() ? null : this.Dw;
   }

   @Override
   public boolean setInlineComment(String var1, String var2) {
      return this.Dw.setPrimary(var1, var2, 0, true);
   }

   @Override
   public String getInlineComment(String var1) {
      return this.Dw.getPrimary(var1, 0);
   }

   @Override
   public Map getInlineComments() {
      return this.Dw.getPrimaryMap(0);
   }

   @Override
   public boolean setInlineComment(long var1, String var3) {
      String var4 = this.Dw.memoryToAddress(var1);
      return var4 == null ? false : this.setInlineComment(var4, var3);
   }

   @Override
   public String getInlineComment(long var1) {
      String var3 = this.Dw.memoryToAddress(var1);
      return var3 == null ? null : this.getInlineComment(var3);
   }

   @Override
   public String getAddressLabel(String var1) {
      long var2 = this.getCanonicalMemoryAddress(var1);
      return var2 == -1L ? null : this.q(var2, AutoLabelPolicy.OFF);
   }

   @Override
   public boolean isProcessed() {
      return this.RF != null;
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else {
         this.Dw = new NativeCommentManager(this);
         if (this.getParent() instanceof ICodeObjectUnit) {
            this.Hk = (ICodeObjectUnit)this.getParent();
            this.wF = this.Hk.getLoaderInformation().getTargetSubsystem();
            long var1 = Conversion.stringToLong(this.getPropertyManager().getString("ImageBase"), -1L);
            if (var1 == -1L) {
               long var3 = this.Hk.getLoaderInformation().getImageBase();
               if (var3 != 0L) {
                  var1 = var3;
               } else {
                  boolean var5 = (this.Hk.getLoaderInformation().getFlags() & 32) != 0;
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

         ICompiler var29 = this.LK();
         if (this.CE == null || !var29.isUnknown()) {
            this.CE = var29.getType();
         }

         IPrimitiveSizes var30 = null;
         if (this.getAnalyzerExtension() != null) {
            var30 = this.getAnalyzerExtension().getPrimitiveSizes(this.wF, this.CE);
         }

         String var31 = null;
         int var4 = this.getPropertyManager().getInteger("CallingConvention");
         if (var4 != CallingConventionName.UNKNOWN.id()) {
            CallingConventionName var32 = CallingConventionName.valueOf(var4);
            if (var32 != null) {
               var31 = var32.name();
            }
         }

         if (var31 == null && this.sH != null) {
            var31 = this.sH.getName();
         }

         this.qa = new bby(this, this.io(), this.wF, this.CE, var30, this.lm.getPCRegisterBitsize() / 8, this.lm.getGPRegisterBitsize() / 8, var31);
         if (this.getAnalyzerExtension() != null) {
            this.getAnalyzerExtension().typeManagerInitialized(this.qa);
         }

         this.Me = new axt(this.qa);
         if (this.Hk != null) {
            if (this.zz == null || this.zz.getSpaceBits() < 32 || this.zz.getPageSize() < 4096) {
               this.zz = new azm(32, 12);
            }

            this.zz.setStandardEndianness(this.Hk.getLoaderInformation().getEndianness());
         }

         int var33 = this.zz.getSpaceBits();
         if (var33 > 64) {
            nf.debug("The memory space is wider than 64-bit: %d", var33);
            var33 = 64;
         }

         this.JY = new aaf(var33, this, this, this.Dw, this.oQ);
         this.xW = new MemoryRanges(var33);
         this.KT = new MemoryRanges(var33);
         this.RF = new aae(this.JY, this.zz, this.lm, this.Hk, this.qa, this.Me, this.xW, this.KT);
         this.RF.q(var29);
         if (!var29.isUnknown()) {
            String var6 = var29.getName();
            if (var6 != null) {
               UnitUtil.logInfo(this, null, true, nf, S.L("Detected compiler: %s"), var6);
            }
         }

         this.za = new abd(this);
         this.HF();
         this.RF.q(this.Gf);
         this.q(var29);
         this.RF.q(this.EB);
         int var34 = this.getPropertyManager().getInteger("DebugInformationUsagePolicy");
         int var7 = this.getPropertyManager().getInteger("DebugInformationRetrievalPolicy");
         this.RF.q(var34, var7);
         this.Xo = this.getPropertyManager().getBoolean("CreatePackagesFromRoutineNames");
         IRuntimeProject var8 = RuntimeProjectUtil.findProject(this);
         if (var8 != null) {
            this.q(var8);
            this.RF(var8);
         }

         IInput var9 = this.getInput();
         if (this.Hk == null) {
            this.io = Longs.maxUnsigned(this.io, var9.getCurrentSize());
            Assert.a(Longs.compareUnsigned(this.io, 4294967296L) < 0, "Cannot map more than 4Gb");
            if (this.io != 0L) {
               Assert.a(VirtualMemoryUtil.allocateFillGaps(this.zz, this.LK, (int)this.io, 7) != 0, "Allocation error");

               try (InputStream var10 = var9.getStream()) {
                  byte[] var11 = com.pnfsoftware.jeb.util.io.IO.readInputStream(var10);
                  VirtualMemoryUtil.writeSafe(this.zz, this.LK, var11);
               } catch (IOException var27) {
                  return false;
               }

               this.xW.add(this.LK, this.LK + this.io);
               this.Gf.q();
               this.RF.enqueuePointerForAnalysis(new CodePointer(this.LK), 1);
            }
         } else {
            ILoaderInformation var35 = this.Hk.getLoaderInformation();
            this.lm.setEndianness(var35.getEndianness());
            this.io = var35.getImageSize();
            Assert.a(this.Hk.map(this.zz, this.LK, true, null), "Could not map file");

            for (ISegmentInformation var13 : this.Hk.getValidSegments()) {
               if ((var13.getFlags() & 1073741824) == 0) {
                  long var14 = this.LK + var13.getOffsetInMemory();
                  long var16 = var13.getSizeInMemory();
                  if (var16 != 0L) {
                     this.xW.add(var14, var14 + var16);
                  }
               }
            }

            boolean var37 = false;

            for (ISegmentInformation var41 : this.Hk.getValidSections()) {
               if (var41.getFlags() != 0) {
                  long var15 = var41.getSizeInMemory();
                  if (var15 != 0L) {
                     var37 = true;
                     if ((var41.getFlags() & 1073741824) == 0 && (var41.getFlags() & 4) != 0) {
                        long var17 = this.LK + var41.getOffsetInMemory();
                        ISegmentInformation var19 = CodeObjectUnitUtil.findSegmentByRelativeAddress(this.Hk, var17 - this.LK);
                        if (var19 == null || (var19.getFlags() & 4) != 0 || (var19.getFlags() & -2147483648) != 0) {
                           this.KT.add(var17, var17 + var15);
                        }
                     }
                  }
               }
            }

            if (!var37) {
               for (ISegmentInformation var42 : this.Hk.getValidSegments()) {
                  if ((var42.getFlags() & 1073741824) == 0 && (var42.getFlags() & 4) != 0) {
                     long var45 = this.LK + var42.getOffsetInMemory();
                     long var48 = var42.getSizeInMemory();
                     if (var48 != 0L) {
                        this.KT.add(var45, var45 + var48);
                     }
                  }
               }
            }

            boolean var40 = false;
            if (var40 && var37) {
               AddressSegmentMap var43 = new AddressSegmentMap(var33);
               var43.setRemoveSegmentsOnOverlap(true);

               for (ISegmentInformation var47 : this.Hk.getValidSections()) {
                  if (var47.getFlags() != 0 && CodeObjectUnitUtil.findSegmentByRelativeAddress(this.Hk, var47.getOffsetInMemory()) != null) {
                     long var49 = this.LK + var47.getOffsetInMemory();
                     if (var47.getAlignment() != 0L && var47.getAlignment() != 1L) {
                        long var50 = var47.getSizeInMemory();
                        long var21 = var47.getAlignment();
                        T var23 = new T(var49, var50, var21);

                        try {
                           var43.add(var23);
                        } catch (IllegalArgumentException var25) {
                        }
                     }
                  }
               }

               if (!var43.isEmpty()) {
                  this.RF.q(var43);
               }
            }

            this.Gf.q();
            boolean var44 = (var35.getFlags() & 4) != 0;
            if (!var44) {
               this.q(var35);
            }

            this.qa();
            if (var44) {
               this.q(var35);
            }
         }

         if (this.HF == null) {
            this.HF = new GenericCodeFormatter();
         }

         this.HF.setCodeUnit(this);
         this.PV = new abu(this);
         xJ var36 = new xJ(this);
         this.RF.q(var36);
         this.RF.q(this.gO());
         if (this.Hk()) {
            this.performInitialAnalysis(true);
         } else if (this.Dw() || Boolean.TRUE.equals(this.Me())) {
            this.performInitialAnalysis();
         }

         this.notifyListeners(new JebEvent(J.UnitProcessed));
         return true;
      }
   }

   private void q(ILoaderInformation var1) {
      long var2 = var1.getEntryPoint();
      if (var2 != 0L || (var1.getFlags() & 16) != 0) {
         long var4 = this.LK + var2;
         CodePointer var6 = this.getProcessor().createEntryPoint(var4);
         int var7 = 0;
         if (this.Gf != null) {
            var7 = this.Gf.HF();
         }

         this.RF.enqueuePointerForAnalysis(var6, var7);
         if (this.q(var6.getAddress(), AutoLabelPolicy.OFF) == null) {
            this.q(var6.getAddress(), "start");
         }

         this.getNotificationManager()
            .addNotification(new UnitNotification(NotificationType.AREA_OF_INTEREST, S.L("Entry point"), this.getSymbolicStringAddress(var6.getAddress())));
      }
   }

   private void qa() {
      Collection var1 = this.Hk.getSymbols();
      if (var1 != null) {
         TreeMap var2 = new TreeMap();

         for (ISymbolInformation var4 : var1) {
            if (var4.getSymbolRelativeAddress() != 0L && (var4.getFlags() & 8) == 0) {
               long var5 = this.LK + var4.getSymbolRelativeAddress();
               long var7 = var4.getSymbolSize();
               String var9 = (var4.getFlags() & 16) != 0 ? null : var4.getName();
               SymbolType var10 = var4.getType();
               int var11 = var4.getFlags();
               if (var10 == SymbolType.FUNCTION_MAYBE) {
                  boolean var12 = false;

                  try {
                     long var13 = this.zz.readPointer(var5);
                     if (this.Uv(var13)) {
                        var12 = true;
                     }
                  } catch (MemoryException var20) {
                  }

                  if (!var12 && (this.KT == null || this.KT.contains(var5))) {
                     var10 = SymbolType.FUNCTION;
                  }
               }

               String var27 = null;
               if (var10 != SymbolType.FUNCTION
                  && var10 != SymbolType.EXTERN_FUNCTION
                  && var10 != SymbolType.EXTERN_DATA
                  && var10 != SymbolType.PTRFUNCTION
                  && var9 != null) {
                  IUnmangledData var29 = this.EB.unmangleData(var9, false);
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

                  this.RF.enqueuePointerForAnalysis(var34, 1, var37);
                  if (var9 != null) {
                     this.q(var34.getAddress(), var9);
                  }
               } else if (var10 == SymbolType.PTRFUNCTION) {
                  axp var36 = null;
                  if ((var11 & 1) != 0) {
                     var36 = this.Dw(var9);
                     if (var36 == null) {
                        var36 = this.q(var9, null, null);
                     }
                  } else if (((SymbolInformation)var4).isInternal() && this.Gf.Uv()) {
                     Long var39 = null;

                     try {
                        var39 = this.zz.readPointer(var5);
                     } catch (MemoryException var19) {
                     }

                     var36 = this.q(var9, var39);
                  }

                  String var40 = "ptr_" + var9;
                  this.RF.q(var5, var40, var36, var9);
               } else if (var10 != SymbolType.EXTERN_FUNCTION && var10 != SymbolType.EXTERN_DATA) {
                  if (var10 == SymbolType.PTROBJECT) {
                     if (this.JY.getItemAt(var5) == null) {
                        bbt var33 = this.qa.gP();
                        if (var33 != null) {
                           this.setDataAt(var5, var33, var9);
                        }
                     } else if (var9 != null) {
                        this.JY.LK().setLabel(var5, var9, true, true, false);
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
                        if (this.JY.getItemAt(var5) == null) {
                           bbt var31 = this.qa.gP();
                           if (var31 != null) {
                              this.setDataAt(var5, var31, var9);
                           }
                        } else if (var9 != null) {
                           this.JY.LK().setLabel(var5, var9, true, true, false);
                        }
                     } else if (var9 != null && !var9.isEmpty()) {
                        this.JY.LK().setLabel(var5, var9, true, true, false);
                     }
                  }
               } else {
                  String var30 = var9;
                  if (var9.startsWith("__imp_")) {
                     var30 = var9.substring(6);
                  }

                  axp var14 = this.Dw(var30);
                  if (var14 == null) {
                     var14 = this.q(var30, null, null);
                  }

                  String var15 = "extern" + var9;
                  this.RF.q(var5, var15, var14, var9);
               }

               if (var27 != null) {
                  this.JY.LK().setLabel(var5, var27, true, true, false);
               }
            }
         }

         int var21 = this.getProcessor().getType().is64Bit() ? 8 : 4;

         for (Entry var23 : var2.entrySet()) {
            for (ISymbolInformation var24 : (List)var23.getValue()) {
               long var8 = this.LK + var24.getSymbolRelativeAddress();
               int var25 = (int)var24.getSymbolSize();
               String var26 = (var24.getFlags() & 16) != 0 ? null : var24.getName();
               SymbolType var28 = var24.getType();
               String var35 = null;
               IUnmangledData var38 = this.EB.unmangleData(var26, false);
               if (var38 != null) {
                  var35 = var26;
                  var26 = var38.getFull();
               }

               boolean var41 = false;
               INativeContinuousItem var16 = this.JY.getItemAt(var8);
               if (var16 == null && this.JY.isEmptyRange(var8, var25 == 0 ? var21 : var25)) {
                  bbd var17 = null;
                  String var18 = var24.getSymbolDataTypeInformation();
                  if (var18 != null) {
                     var17 = this.qa.Uv(var18);
                  }

                  if (var17 != null) {
                     var41 = this.setDataAt(var8, var17, var26);
                  }
               } else if (var25 <= 0 || var16 != null && var25 == var16.getMemorySize()) {
                  if (var26 != null) {
                     this.JY.LK().setLabel(var8, var26, true, true, false);
                  }

                  var41 = true;
               }

               if (!var41) {
                  this.RF.Hk().q(var8, var26, var25, var28 == SymbolType.VARIABLE ? this.qa.Uv("int") : null, true, var16 != null);
               }

               if (var35 != null) {
                  this.JY.LK().setLabel(var8, var35, true, true, false);
               }
            }
         }
      }
   }

   private axp q(String var1, Long var2) {
      axp var3 = this.RF().q(null, var1, null, null);
      var3.xK(true);
      var3.q(var2);
      if (this.RF != null && this.RF.q(var3, var1 != null ? var1 : var3.getName(true), false)) {
         var3.q(true);
      }

      return var3;
   }

   private boolean Hk() {
      return this.getParentProject() instanceof Pl && ((Pl)this.getParentProject()).RF;
   }

   protected boolean Dw() {
      try {
         return this.getPropertyManager().getBoolean("DelayAnalysis") ? false : !JebCoreService.getInstance().getOptions().isAllowAsynchronousProcessing();
      } catch (Exception var1) {
         return false;
      }
   }

   protected boolean Uv() {
      try {
         return !JebCoreService.getInstance().getOptions().isAllowAsynchronousProcessing();
      } catch (Exception var1) {
         return false;
      }
   }

   private Boolean Me() {
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
            var1 = this.Uv();
            Boolean var2 = this.Me();
            if (var2 != null) {
               var1 = var2;
            }
         }

         if (this.Gf != null) {
            UnitUtil.logInfo(this, null, true, nf, Strings.ff(S.L("Initial native analysis styles: code gaps: %s, data gaps: %s"), this.Gf.Ef(), this.Gf.Gf()));
         }

         abi var3 = new abi(this);
         return this.performAnalysis(!var1, null, var3);
      }
   }

   @Override
   public boolean performAnalysis(boolean var1, Boolean var2, Runnable var3) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else if (this.PV != null && this.PV.isDone()) {
         if (var2 != null) {
            this.RF.q(Boolean.valueOf(var2));
         }

         if (!var1) {
            this.PV.run();
            if (var3 != null) {
               var3.run();
            }

            return true;
         } else {
            this.PV.start(var3);
            return true;
         }
      } else {
         return false;
      }
   }

   protected void oW() {
      if (this.Xo) {
         for (axp var2 : this.getInternalMethods()) {
            this.xK(var2);
         }
      }
   }

   private void xK(axp var1) {
      String var2 = var1.getName(true);
      bbp var3 = this.qa.q((INativeItem)var1);
      boolean var4 = false;
      if (var2 != null && TypeUtil.containsCppSeparator(var2) && !var2.contains("→")) {
         List var5 = TypeUtil.splitCppName(var2);
         if (var5.size() >= 2) {
            var4 = true;
            bbo var6 = this.qa.q(var5.subList(0, var5.size() - 1));
            if (var6 != null) {
               bbp var7 = (bbp)var6.RF();
               if (var7 != null) {
                  if (!this.qa.moveToPackage(var1, var7) && !this.Bu) {
                     this.Bu = true;
                     JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("Package automatic move failed"));
                  }
               } else if (!this.Bu) {
                  this.Bu = true;
                  JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("Package automatic creation failed"));
               }
            }
         }
      }

      if (var3 != null && !var3.isRootPackage()) {
         if (!var4) {
            this.qa.moveToPackage(var1, (IPackage)this.qa.JY().RF());
         }

         if (var3.getChildren().isEmpty()) {
            this.qa.deletePackage(var3);
         }
      }
   }

   private void PV() {
      this.oQ.verifyLocked();
      SortedMap var1 = this.getCodeModel()
         .getItemsInRange(this.getVirtualImageBase(), true, this.getVirtualImageBase() + this.getImageSize(), true, var0 -> var0 instanceof INativeDataItem);
      boolean var2 = this.JY.q(false);

      try {
         for (INativeContinuousItem var4 : var1.values()) {
            this.q((INativeDataItem)var4, true);
         }
      } finally {
         this.JY.q(var2);
         this.getCodeModel().notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
      }
   }

   protected boolean q(INativeDataItem var1, boolean var2) {
      return this.q(var1, var1, var2);
   }

   protected boolean q(INativeDataItem var1, INativeContinuousItem var2, boolean var3) {
      if (var1 instanceof axr) {
         boolean var12 = false;

         for (INativeDataItem var19 : ((axr)var1).sH()) {
            var12 |= this.q(var19, var2, false);
         }

         return var12;
      } else if (var1 instanceof axq) {
         bbd var11 = ((axq)var1).wF();
         if (!(var11 instanceof bbt) && var11 != null && var11.getSize() * 8 == this.zz.getSpaceBits() && var11 == this.RF.zz().q(var11.getSize(), false)) {
            boolean var13 = true;

            for (INativeDataItem var21 : ((axq)var1).cC()) {
               var13 &= this.q(var21, var2, false);
               if (!var13) {
                  break;
               }
            }

            if (var13) {
               bbt var17 = this.qa.gP();
               bbf var22 = this.qa.RF((INativeType)var17, (int)(var1.getMemorySize() / var17.getSize()));
               this.RF.defineData(var1.getMemoryAddress(), var22);
            }
         }

         boolean var14 = false;
         if (((axq)var1).wF() instanceof bbt) {
            for (INativeDataItem var23 : ((axq)var1).cC()) {
               var14 |= this.q(var23, var2, false);
            }
         }

         return var14;
      } else if (!(var1 instanceof axv)) {
         return false;
      } else {
         bbd var4 = ((axv)var1).Uv();
         Couple var5 = aaj.q(this.RF, var1, this.getVirtualImageBase());
         if (var5 == null) {
            return false;
         } else {
            boolean var6 = aaj.q(var1);
            if (!(var4 instanceof bbt) && !var6) {
               DataHints var7 = var1.getHints(false);
               if (var7 != null && var7.getAddressCalculationHint() == 3) {
                  return false;
               }

               if (var3) {
                  INativeDataItem var8 = this.RF.defineData(var1.getMemoryAddress(), this.qa.gP());
                  if (!(var8 instanceof axv)) {
                     this.RF.defineData(var1.getMemoryAddress(), var4);
                     return false;
                  }

                  if (var1.getAttributes() != null) {
                     for (Entry var10 : var1.getAttributes().entrySet()) {
                        var8.setAttribute((String)var10.getKey(), var10.getValue());
                     }
                  }

                  if (var1.getName() != null) {
                     var8.setName(var1.getName());
                  }

                  var8.setFlags(var1.getGenericFlags());
                  var1 = var8;
               }
            }

            ((abl)this.getCodeModel().getReferenceManager())
               .recordInternalReference(var1.getMemoryAddress(), ((INativeContinuousItem)var5.getSecond()).getMemoryAddress(), ReferenceType.PTR_DATA);
            if (!var6 && var3) {
               if (((INativeContinuousItem)var5.getSecond()).getName(true) == null) {
                  return false;
               } else {
                  String var20 = var1.getName(true);
                  if (((Nx)this.getCodeModel().getLabelManager()).q(var20)) {
                     String var24 = "ptr_" + this.q((INativeContinuousItem)var5.getSecond(), (Long)var5.getFirst());
                     var1.setName(var24);
                     HashSet var25 = new HashSet();
                     var25.add(var1.getMemoryAddress());
                     this.q(var1, var24, var25);
                  }

                  return true;
               }
            } else {
               return true;
            }
         }
      }
   }

   private String q(INativeContinuousItem var1, Long var2) {
      if (var1.getMemoryAddress() != var2 && aaj.Uv(var1)) {
         String var4 = DataStringUtil.getStringAt(this.zz, var2, 0, (int)var1.getMemorySize(), null);
         return var4 != null && !var4.isEmpty() ? DataStringUtil.createItemNameFromString(var4, 16) : "aNULL";
      } else {
         String var3 = var1.getName(true);
         if (var1.getMemoryAddress() != var2) {
            var3 = var3 + "_" + (var2 - var1.getMemoryAddress());
         }

         return var3;
      }
   }

   private void q(INativeContinuousItem var1, String var2, Set var3) {
      Set var4 = this.getCodeModel().getReferenceManager().getReferencesTo(var1.getMemoryAddress());
      var2 = "ptr_" + var2;

      for (IReference var6 : var4) {
         if (var6.getFrom().isInternalAddress() && !var3.contains(var6.getFrom().getInternalAddress())) {
            INativeContinuousItem var7 = this.getCodeModel().getItemAt(var6.getFrom().getInternalAddress());
            if (var7 instanceof axv) {
               bbd var8 = ((axv)var1).Uv();
               if (var8 instanceof bbt) {
                  String var9 = var7.getName(true);
                  if (((Nx)this.getCodeModel().getLabelManager()).q(var9) && !var2.equals(var7.getName())) {
                     var7.setName(var2);
                     var3.add(var7.getMemoryAddress());
                     this.q(var7, var2, var3);
                  }
               }
            }
         }
      }
   }

   private String RF(INativeItem var1) {
      if (var1 == null) {
         return null;
      } else {
         return var1 instanceof axi ? ((axi)var1).RF(true) : var1.getName(true);
      }
   }

   private void oQ() {
      v var1 = this.RF.za().LK();
      Map var2 = var1.getPrimaryLabels();

      label65:
      for (Entry var4 : var2.entrySet()) {
         if (Character.isDigit(((String)var4.getValue()).charAt(((String)var4.getValue()).length() - 1))) {
            INativeContinuousItem var5 = this.RF.za().getItemAt((Long)var4.getKey());
            if (var5 != null) {
               String var6 = this.RF(var5);
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
                           long var8 = this.zz.readPointer(var12);
                           if (var8 == (Long)var4.getKey()) {
                              String var10 = "ptr_" + var6;
                              var1.removeLabel(var12);
                              var1.setLabel(var12, var10, true, true, true);
                              var1.setLabel((Long)var4.getKey(), var6, false, false, true);
                           } else {
                              var8 = this.zz.readPointer((Long)var4.getKey());
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
         return (this.PV == null || this.PV.isDone()) && !this.RF.isAnalyzing();
      }
   }

   protected boolean gO() {
      int var1 = this.getPropertyManager().getInteger("AdvancedAnalysis");
      switch (var1) {
         case 0:
            return false;
         case 1:
            if (this.KT != null && this.KT.aggregatedRangesSize() < 1000000L) {
               return true;
            }

            return false;
         case 2:
            return true;
         default:
            return false;
      }
   }

   protected boolean nf() {
      return this.getPropertyManager().getBoolean("PerformRttiRecovery");
   }

   protected boolean gP() {
      return this.getPropertyManager().getBoolean("PerformGlobalAnalysis");
   }

   @Override
   public ICodeObjectUnit getCodeObjectContainer() {
      return this.Hk;
   }

   private axp Dw(String var1) {
      axp var2 = this.RF(var1);
      if (var2 != null) {
         return var2;
      } else if (this.RF == null) {
         nf.debug("analyzer has to be there for library routine import");
         return null;
      } else {
         var2 = this.RF.q(var1);
         if (var2 == null) {
            nf.debug("Could not find reference to external artifact: %s", var1);
            return null;
         } else {
            axp var3 = this.qa.q(this, var2);
            if (var3 == null) {
               nf.error(S.L("Import() failed for routine: %s"), var2);
               return null;
            } else {
               if (!var3.getName(false).equals(var1)) {
                  var3.q(var1);
               }

               bbs var4 = var3.Uv();
               if (var4 != null && var4.getPrototypeAttributes().contains(PrototypeAttribute.NoReturn)) {
                  var3.Dw(true);
               }

               return var3;
            }
         }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() instanceof axp var3) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            String var4 = var3.getName(true);
            if (var4 != null) {
               axp var5 = (axp)this.xK.get(var4);
               if (var5 != null && (var5.isDisposed() || var5 == var3)) {
                  this.xK.remove(var4);
               }
            }
         }

         if (var1.getType() == NativeItemEventType.UPDATED && var1.getSubtype() == NativeItemEventSubType.RENAMED) {
            String var11 = (String)var1.getDetails();
            String var13 = var3.getName(true);
            if (var11 != null) {
               axp var6 = (axp)this.xK.get(var11);
               if (var6 != null && (var6.isDisposed() || var6 == var3)) {
                  this.xK.remove(var11);
               }
            }

            boolean var15 = true;
            if (var13 != null && this.xK.containsKey(var13)) {
               axp var7 = (axp)this.xK.get(var13);
               if (!var7.isDisposed() && var7 != var3) {
                  var15 = false;
               }
            }

            if (var13 != null && var15) {
               this.xK.put(var13, var3);
            }

            if (this.Xo && this.isInitialAnalysisDone()) {
               this.xK(var3);
            }
         }

         if (var1.getType() == NativeItemEventType.UPDATED
            && (var1.getSubtype() == NativeItemEventSubType.ATTRIBUTE_REMOVED || var1.getSubtype() == NativeItemEventSubType.ATTRIBUTE_SET)) {
            String var12 = (String)var1.getDetails();
            if (var12 != null && var12.equals("isNonReturning") && var3.oW() != null) {
               HashSet var14 = new HashSet(this.JY.getCallGraphManager().getGlobalCallGraph().getCallerRoutines(CodePointer.createFrom(var3), true));
               Object var16 = Collections.emptySet();
               int var8 = 0;

               while (var14.size() != var16.size()) {
                  var16 = new HashSet(var14);

                  for (INativeMethodItem var10 : var16) {
                     var14.addAll(this.JY.getCallGraphManager().getGlobalCallGraph().getCallerRoutines(CodePointer.createFrom(var10), true));
                  }

                  if (var8++ == 10) {
                     break;
                  }
               }

               if (!var14.isEmpty()) {
                  for (INativeMethodItem var19 : var14) {
                     this.RF.enqueuePointerForAnalysis(CodePointer.createFrom(var19), 2, 8);
                  }

                  abb var18 = this.RF.io();
                  if (var18 != null) {
                     var18.q();
                  }

                  if (this.RF.needsAnalysis()) {
                     this.RF.analyze();
                  }
               }
            }
         }
      }

      this.lm();
   }

   @Override
   public void onModelUpdate(MemoryModelEvent var1) {
      Assert.a(var1.getModel() == this.JY);
      this.lm();
   }

   @Override
   public void q(axp var1) {
   }

   @Override
   public void RF(axp var1) {
   }

   void q(boolean var1) {
      if (var1 && !this.TX) {
         this.TX = true;
         this.Rr = 0;
      } else if (!var1 && this.TX) {
         this.TX = false;
         if (this.Rr >= 1) {
            this.lm();
         }

         this.Rr = 0;
      }
   }

   boolean za() {
      return this.TX;
   }

   void lm() {
      if (this.TX) {
         this.Rr++;
      } else {
         this.notifyListeners(new JebEvent(J.UnitChange));
      }
   }

   @Override
   public void setPhysicalImageBase(long var1) {
      this.Dz = var1;
   }

   @Override
   public long getPhysicalImageDelta() {
      return this.Dz != 0L ? this.Dz - this.LK : 0L;
   }

   @Override
   public long getVirtualImageBase() {
      return this.LK;
   }

   @Override
   public void setVirtualImageBase(long var1) {
      if (this.isProcessed()) {
         throw new IllegalStateException("The unit was already processed");
      } else {
         this.LK = var1;
      }
   }

   @Override
   public long getImageSize() {
      return this.io;
   }

   public void xK(long var1) {
      this.io = var1;
   }

   public long zz() {
      return this.LK + this.io;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new abj(this, 1L, S.L("Disassembly"), true), false);
      }

      return var1;
   }

   @Override
   public INativeDisassemblyDocument getDisassemblyDocument() {
      return new bay(this);
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
      if (this.mI == null) {
         this.mI = new bbk(this);
      }

      return this.mI;
   }

   public boolean Dw(long var1) {
      int var3 = this.zz.getSpaceBits();
      if (var3 >= 64) {
         return true;
      } else {
         long var4 = MathUtil.makeInverseMask(var3);
         return (var1 & var4) == 0L;
      }
   }

   public boolean q(long var1, long var3) {
      if (Longs.compareUnsigned(var1, var3) > 0) {
         return false;
      } else {
         int var5 = this.zz.getSpaceBits();
         if (var5 >= 64) {
            return true;
         } else {
            long var6 = MathUtil.makeInverseMask(var5);
            long var8 = MathUtil.makeOverflow(var5);
            return (var1 & var6) == 0L && ((var3 & var6) == 0L || var3 == var8);
         }
      }
   }

   public boolean Uv(long var1) {
      return Longs.compareUnsigned(var1, this.LK) >= 0 && Longs.compareUnsigned(var1, this.LK + this.io) < 0;
   }

   public boolean RF(long var1, long var3) {
      return Longs.compareUnsigned(var1, var3) <= 0 && Longs.compareUnsigned(var1, this.LK) >= 0 && Longs.compareUnsigned(var3, this.LK + this.io) < 0;
   }

   public String oW(long var1) {
      return this.q(var1, AutoLabelPolicy.ON);
   }

   public String q(long var1, AutoLabelPolicy var3) {
      return this.JY.q(var1, this.getPhysicalImageDelta(), var3);
   }

   public void q(long var1, String var3) {
      this.JY.q(var1, var3);
   }

   @Override
   public boolean setRoutinePrototype(INativeMethodItem var1, String var2) {
      axp var3 = (axp)var1;
      TypeStringParser var5 = new TypeStringParser(this.RF());

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
      axp var4 = (axp)var1;
      TypeStringParser var6 = new TypeStringParser(this.RF());

      IPrototypeItem var5;
      try {
         var5 = var6.parseSignature(var2, true);
      } catch (TypeStringParseException var8) {
         nf.catchingSilent(var8);
         nf.error(S.L("Cannot parse signature: \"%s\" (to be applied on %s)"), var2, var1);
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
      return this.q(var1, var3, var4, var5, false);
   }

   public boolean q(long var1, INativeType var3, String var4, boolean var5, boolean var6) {
      bbd var7 = (bbd)var3;
      if (!var5 && !this.JY.isEmptyRange(var1, var3.getSize())) {
         return false;
      } else {
         if (var7.xK() != this.RF()) {
            var7 = this.RF().q(var7);
            if (var7 == null) {
               return false;
            }
         }

         if (var4 == null) {
            INativeContinuousItem var8 = this.getNativeItemAt(var1);
            var4 = this.RF(var8);
         }

         INativeDataItem var9 = this.RF.defineData(var1, var7);
         if (var9 == null) {
            return false;
         } else {
            if (var4 != null) {
               var9.setName(var4);
               if (var6) {
                  this.RF.q(var9);
               }
            } else {
               this.q(var9, true);
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
         return this.RF.q(var1, var3, var5, var6, var7, var8) != null;
      }
   }

   @Override
   public boolean setCodeAt(long var1, int var3, boolean var4) {
      if (!this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.RF.q(var1, var3, 3, var4) != null;
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
         axu var5 = this.RF.q(var1, var4, (axp)var3, null);
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
      return this.JY.oW(var5.getAddress()) != null;
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
         this.JY.q(var3, false);
         return true;
      }
   }

   public long gO(long var1) {
      return this.za.xK(var1);
   }

   public long q(IInstruction var1) {
      return this.za.q(var1);
   }

   public long q(long var1, int var3) {
      return this.za.RF(var1, var3);
   }

   public long nf(long var1) {
      return this.za.Dw(var1);
   }

   public long q(int var1, int var2) {
      return this.za.q(var1, var2);
   }

   public long q(IStructureType var1, int var2) {
      return this.za.q(var1, var2);
   }

   public String gP(long var1) {
      return this.za.Uv(var1);
   }

   @Override
   public String getAddressOfItem(long var1) {
      return this.za.q(var1);
   }

   public Long q(INativeItem var1) {
      return this.za.q(var1);
   }

   @Override
   public long getItemAtAddress(String var1) {
      return this.za.q(var1);
   }

   @Override
   public long getCanonicalMemoryAddress(String var1, AddressConversionPrecision var2) {
      Long var3 = this.za.q(var1, var2);
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
      return this.za.q(var1, var3);
   }

   @Override
   public INativeItem getItemObject(long var1) {
      return this.za.RF(var1);
   }

   @Override
   public ICodeCoordinates getCodeCoordinatesFromAddress(String var1) {
      return this.za.RF(var1);
   }

   @Override
   public final String getAddressFromCodeCoordinates(ICodeCoordinates var1) {
      return this.getAddressFromCodeCoordinates(var1, AddressConversionPrecision.FINE);
   }

   @Override
   public String getAddressFromCodeCoordinates(ICodeCoordinates var1, AddressConversionPrecision var2) {
      return this.za.q(var1, var2);
   }

   @Override
   public IInputLocation addressToLocation(String var1) {
      return this.za.xK(var1);
   }

   @Override
   public String locationToAddress(IInputLocation var1) {
      return this.za.q(var1);
   }

   @Override
   public List getGlobalActions() {
      return this.gP.RF();
   }

   @Override
   public List getItemActions(long var1) {
      return this.gP.q(var1);
   }

   @Override
   public List getAddressActions(String var1) {
      return this.gP.q(var1);
   }

   @Override
   public boolean canExecuteAction(ActionContext var1) {
      return this.gP.q(var1);
   }

   @Override
   public boolean prepareExecution(ActionContext var1, IActionData var2) {
      return this.gP.q(var1, var2, true, false);
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2, boolean var3) {
      return this.gP.q(var1, var2, false, var3);
   }

   @Override
   public IMetadataManager getMetadataManager() {
      IMetadataManager var1 = super.getMetadataManager();
      synchronized (this) {
         if (var1.getGroupByName("primary") == null) {
            var1.addGroup(new abf(this));
         }

         return var1;
      }
   }

   @Override
   public List getContributions() {
      List var1 = super.getContributions();
      synchronized (this) {
         if (this.jq == null) {
            this.jq = new bas(this);
            var1.add(this.jq);
         }

         if (this.IN == null && this.xW()) {
            String var3 = this.KT();

            try {
               this.IN = this.Uv(var3);
               if (this.IN != null) {
                  var1.add(this.IN);
               }
            } catch (Exception var6) {
               nf.error(
                  S.L("The doc contribution for %s unit cannot be created.\nPlease update your properties, this doc root location seems illegal: %s"),
                  this,
                  var3
               );
               nf.catching(var6);
            }
         }

         return var1;
      }
   }

   private boolean xW() {
      switch (this.io().id()) {
         case 40:
         case 183:
            return true;
         default:
            return false;
      }
   }

   private String KT() {
      switch (this.io().id()) {
         case 40:
            return this.getPropertyManager().getString("DocumentationRoot");
         case 183:
            return this.getPropertyManager().getString("DocumentationRoot");
         default:
            return null;
      }
   }

   private IUnitContribution Uv(String var1) {
      if (!Strings.isBlank(var1) && (this.rL == null || !this.rL.equals(var1))) {
         this.rL = var1;

         try {
            Net var2 = ((zJ)RuntimeProjectUtil.findProject(this).getEnginesContext()).xK();
            switch (this.io().id()) {
               case 40:
               case 183:
                  return new mC(this, var1, var2);
            }
         } catch (Exception var3) {
            nf.error(
               S.L("The documentation contribution for an %s unit cannot be created.\nPlease update your properties, this root location seems illegal: %s"),
               this.lm.getType().name(),
               var1
            );
            nf.catching(var3);
         }
      } else {
         nf.debug("This root location is illegal for %s Processor: %s", this.lm.getType().name(), var1);
      }

      return null;
   }

   @Override
   public IQuickStateObject generateQuickState() {
      return !this.isProcessed() ? null : new abe(this);
   }

   @Override
   public long getEntryPointAddress() {
      return this.Hk == null ? -1L : this.getVirtualImageBase() + this.Hk.getLoaderInformation().getEntryPoint();
   }

   @Override
   public long getHighLevelEntryPointAddress() {
      return this.cC == null ? -1L : this.cC;
   }

   public void za(long var1) {
      this.cC = var1;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      if (this.isProcessed()) {
         Strings.ff(var1, S.L("\nInformation:\n"));
         Strings.ff(var1, S.L("- Processor type: %s\n"), this.io());
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
      return this.RF(var1);
   }

   @Override
   public INativeMethodItem getRoutineOver(long var1) {
      return this.q(var1, false);
   }

   @Override
   public INativeMethodItem getRoutineByName(String var1) {
      return this.RF(var1);
   }

   @Override
   public INativeFieldItem getField(long var1) {
      return this.q(var1);
   }

   @Override
   public IBranchResolution getDynamicBranchResolution(long var1) {
      return this.JY.HF(var1);
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
      return new abg.eo(var1, var2).q();
   }

   @Override
   public INativeDecompilerUnit getDecompiler() {
      return (INativeDecompilerUnit)DecompilerHelper.getDecompiler(this, true);
   }

   private class eo {
      private List RF;
      private int xK;
      private Predicate Dw;

      eo(int var2, Predicate var3) {
         if (var2 < 0) {
            var2 = Integer.MAX_VALUE;
         }

         this.xK = var2;
         this.Dw = var3;
      }

      public List q() {
         if (this.RF == null) {
            this.RF = new ArrayList();

            for (String var3 : abg.this.getCodeModel().getLabelManager().getPrimaryLabels().values()) {
               if (!this.q(var3)) {
                  return this.RF;
               }
            }

            if (!this.q(abg.this.getInternalMethods())) {
               return this.RF;
            }
         }

         return this.RF;
      }

      private boolean q(Collection var1) {
         for (axj var3 : var1) {
            if (!this.q(var3.getAddress())) {
               return false;
            }
         }

         return true;
      }

      boolean q(String var1) {
         if (!Strings.isBlank(var1) && (this.Dw == null || this.Dw.test(var1))) {
            this.RF.add(var1);
            if (this.RF.size() >= this.xK) {
               return false;
            }
         }

         return true;
      }
   }
}
