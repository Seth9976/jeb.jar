package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginFileEntry;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.output.UnitItemIdGenerator;
import com.pnfsoftware.jeb.core.output.text.TextPartUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyUtil;
import com.pnfsoftware.jeb.core.units.AbstractInteractiveUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.DecompilerOutputType;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.INativeDecompilerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilerOptions;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledItem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IGlobalAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeObjectTracker;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeSourceUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ISourceCustomizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilerExporter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICNamingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.CMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.DecompilerException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.EMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.corei.parsers.asm.NativeDecompilerPseudoIdentifier;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;

@Ser
public class ace extends AbstractInteractiveUnit implements INativeDecompilerUnit {
   private static List UT = new ArrayList();
   private static final StructuredLogger E = aco.pC(ace.class);
   @SerId(1)
   aci pC;
   @SerId(2)
   acs A;
   @SerId(3)
   private C sY;
   @SerId(4)
   private IdentityHashMap ys = new IdentityHashMap();
   @SerId(5)
   private adh ld;
   @SerId(6)
   private IEConverter gp;
   @SerId(7)
   private ayy oT;
   @SerId(8)
   private akr fI;
   @SerId(9)
   private DecompilerOptions WR = new DecompilerOptions();
   @SerTransient
   private INativeDecompilerExtension NS;
   @SerTransient
   private IGlobalAnalyzer vP;
   @SerTransient
   private ISourceCustomizer xC;
   @SerTransient
   private INativeDecompilerExtensionsManager ED;
   @SerTransient
   private boolean Sc;
   @SerTransient
   private boolean ah;
   @SerTransient
   private boolean eP;
   @SerTransient
   private com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer.Av UO;
   @SerTransient
   private UnitItemIdGenerator Ab;
   @SerTransient
   private ack rl;
   @SerTransient
   private Ee z;
   @SerTransient
   volatile boolean kS;
   @SerTransient
   volatile boolean wS;

   public static void pC(INativeDecompilerExtension var0) {
      if (var0 instanceof AbstractNativeDecompilerExtension) {
         UT.add((AbstractNativeDecompilerExtension)var0);
      }
   }

   public ack pC() {
      if (this.rl == null) {
         this.rl = new ack(this.sY, this);
      }

      return this.rl;
   }

   public UnitItemIdGenerator A() {
      if (this.Ab == null) {
         this.Ab = new UnitItemIdGenerator();
      }

      return this.Ab;
   }

   @SerCustomInitPostGraph
   private void gp() {
      if (this.ED == null) {
         this.ED = new acq(this);
         this.ED.registerExtension(new acd());
         if (this.sY != null && this.sY.getSubsystemType() != null && this.sY.getSubsystemType().isWindowsLike()) {
            this.ED.registerExtension(new acz());
         }

         this.ED.registerExtensions(UT, false);
      }
   }

   @Override
   public void postDeserialization(IRuntimeProject var1) {
      String var2 = this.getFormatType();
      IUnitIdentifier var3 = this.getUnitProcessor().getUnitIdentifier(var2);
      if (!(var3 instanceof NativeDecompilerPseudoIdentifier)) {
         throw new IllegalStateException(Strings.ff("Unexpected identifier found for type=%s: class=%s", var2, var3 == null ? null : var3.getClass().getName()));
      } else {
         INativeDecompilerPlugin var4 = ((NativeDecompilerPseudoIdentifier)var3).pC();
         this.pC(var4);
      }
   }

   void pC(INativeDecompilerPlugin var1) {
      this.A(var1.getPrimaryExtension(this));
      this.pC(var1.getSourceCustomizer(this));
      this.pC(var1.getGlobalAnalyzer(this));
   }

   public ace(String var1, String var2, IUnitProcessor var3, C var4, IPropertyDefinitionManager var5) {
      super(var1, var2, var3, var4, var5);
      this.sY = var4;
      this.gp();
   }

   @Override
   public DecompilerOptions getOptions() {
      return this.WR;
   }

   @Override
   public void onPropertyChange(String var1) {
      if (var1 != null && var1.startsWith(".parsers.native.decomp")) {
         if (this.kS) {
            this.kS = false;
         } else {
            this.oT();
            if ("NextDecompKeepIR".equals(PropertyUtil.extractName(var1))) {
               this.wS = this.getPropertyManager().getBoolean("NextDecompKeepIR");
               this.kS = true;
               this.getPropertyManager().setBoolean("NextDecompKeepIR", false);
            }
         }
      }
   }

   private void oT() {
      IPropertyManager var1 = this.getPropertyManager();
      this.WR.methodDecompTimeout = 1000L * var1.getInteger("MethodDecompilationTimeout");
      this.WR.enableUnsafeOptimizers = var1.getBoolean("EnableUnsafeOptimizers");
      this.WR.enableDeobfuscators = var1.getBoolean("EnableDeobfuscators");
      this.WR.decryptorSupport = var1.getInteger("DecryptorSupport");
      this.WR.memoryResolutionPolicy = var1.getInteger("MemoryResolutionPolicy");
      this.WR.reconversionMaxCount = var1.getInteger("ReconversionMaxCount");
      this.WR.irOptMaxRunCount = var1.getInteger("IROptimizerMaxRunCount");
      this.WR.structurerUseVersion = var1.getInteger("StructurerUseVersion");
      this.WR.astOptMaxRunCount = var1.getInteger("ASTOptimizerMaxRunCount");
      this.WR.astFriendlyNames = var1.getBoolean("UseFriendlyVariableNames");
      this.WR.astReplaceConstantsByWellKnownLiterals = var1.getBoolean("UseWellKnownLiterals");
      this.pC(this.WR.astFriendlyNames ? new adg() : null);
      this.WR.enableExternalPlugins = var1.getBoolean("EnableExternalPlugins");
      this.WR.listOfDisabledExternalPlugins = var1.getString("ListOfDisabledExternalPlugins");
   }

   @Override
   public boolean canPerformConcurrentDecompilations() {
      return false;
   }

   @Override
   public int getThreadPoolSize() {
      return 1;
   }

   @Override
   public void setThreadPoolSize(int var1) {
   }

   @Override
   public IUnitLock getLock() {
      return this.sY.pC();
   }

   @Override
   public boolean processInternal() {
      if (this.gp == null) {
         return false;
      } else if (!this.sY.isProcessed()) {
         E.error(S.L("%s is not processed. Process first before processing %s."), this.sY, this);
         return false;
      } else {
         this.oT = this.sY.A();
         this.fI = new akr(this.oT, this.gp.getWildcardTypeManagerDefaultResolutionGroup());
         this.pC = new aci(this);
         this.A = new acs(this);
         ((AbstractConverter)this.gp).setNativeContext(this.sY);
         ((ajo)this.gp.getGlobalContext()).pC(this.A);
         this.ld = new adh(this.fI);
         this.oT();
         ((ajo)this.gp.getGlobalContext()).pC(this.fI);
         ((ajo)this.gp.getGlobalContext()).pC(this.A);
         this.gp.initialize();
         return true;
      }
   }

   public void pC(IEConverter var1) {
      this.gp = var1;
      if (var1 instanceof AbstractConverter) {
         ((AbstractConverter)var1).setDecompiler(this);
      }
   }

   @Override
   public IEConverter getConverter() {
      return this.gp;
   }

   @Override
   public INativeDecompilerExtensionsManager getExtensionsManager() {
      return this.ED;
   }

   public void A(INativeDecompilerExtension var1) {
      if (this.NS != null) {
         throw new IllegalStateException("A primary extension was already registered");
      } else {
         this.NS = var1;
         if (var1 != null) {
            this.ED.registerExtension(var1, INativeDecompilerExtensionsManager.ExtensionPriority.HIGH_PRIORITY);
         }
      }
   }

   public void pC(IGlobalAnalyzer var1) {
      this.vP = var1;
   }

   public IGlobalAnalyzer kS() {
      return this.vP;
   }

   public void pC(ISourceCustomizer var1) {
      this.xC = var1;
   }

   public ISourceCustomizer wS() {
      return this.xC;
   }

   @Override
   public DecompilerOutputType getOutputType() {
      return DecompilerOutputType.C;
   }

   private INativeItem pC(String var1) {
      if (var1 == null) {
         return null;
      } else {
         long var2 = this.sY.getItemAtAddress(var1);
         if (var2 == 0L) {
            return null;
         } else {
            Object var4 = this.sY.getItemObject(var2);
            if (var4 instanceof avd) {
               var4 = this.sY.pC(((avd)var4).getMemoryAddress(), false);
            }

            return (INativeItem)(!(var4 instanceof auu) && !(var4 instanceof aur) && !(var4 instanceof auq) ? null : var4);
         }
      }
   }

   @Override
   public boolean canDecompile(String var1) {
      return this.pC(var1) != null;
   }

   @Override
   public INativeSourceUnit decompileToUnit(String var1) {
      return this.decompileToUnit(var1, null);
   }

   @Override
   public INativeSourceUnit decompileToUnit(String var1, DecompilationContext var2) {
      INativeSourceUnit[] var3 = new INativeSourceUnit[1];
      return !this.pC(var1, var2, null, false, true, var3) ? null : var3[0];
   }

   @Override
   public INativeSourceUnit getDecompiledUnit(String var1) {
      INativeSourceUnit[] var2 = new INativeSourceUnit[1];
      return !this.pC(var1, null, null, false, false, var2) ? null : var2[0];
   }

   @Override
   public INativeSourceUnit decompileToUnit(INativeItem var1) {
      return this.decompileToUnit(var1, null);
   }

   @Override
   public INativeSourceUnit decompileToUnit(INativeItem var1, DecompilationContext var2) {
      INativeSourceUnit[] var3 = new INativeSourceUnit[1];
      return !this.pC(var1, var2, false, true, var3) ? null : var3[0];
   }

   @Override
   public INativeSourceUnit getDecompiledUnit(INativeItem var1) {
      INativeSourceUnit[] var2 = new INativeSourceUnit[1];
      return !this.pC(var1, null, false, false, var2) ? null : var2[0];
   }

   private boolean pC(String var1, DecompilationContext var2, Class var3, boolean var4, boolean var5, INativeSourceUnit[] var6) {
      boolean var9;
      try (ACLock var7 = this.getLock().a()) {
         INativeItem var8 = this.pC(var1);
         if (var8 != null && (var3 == null || var3 == var8.getClass())) {
            return this.pC(var8, var2, var4, var5, var6);
         }

         var9 = false;
      }

      return var9;
   }

   private boolean pC(INativeItem var1, DecompilationContext var2, boolean var3, boolean var4, INativeSourceUnit[] var5) {
      boolean var8;
      try (ACLock var6 = this.getLock().a()) {
         if (var5 == null) {
            if (var1 instanceof auu) {
               return this.pC((auu)var1, var2, var4, null) != null;
            }

            if (var1 instanceof aur) {
               return this.pC((aur)var1, var2, var4) != null;
            }

            if (!(var1 instanceof auq)) {
               boolean var14;
               return var14;
            }

            return this.pC((auq)var1, var2, var4) != null;
         }

         INativeSourceUnit var7 = null;
         if (var1 instanceof auu) {
            var7 = this.A((auu)var1, var2, var4, null);
         } else if (var1 instanceof aur) {
            var7 = this.A((aur)var1, var2, var4);
         } else if (var1 instanceof auq) {
            var7 = this.A((auq)var1, var2, var4);
         }

         if (var7 != null && !var3) {
            var1 = var7.getNativeItem();
            auq var18 = null;
            if (var1 instanceof auu) {
               var18 = ((auu)var1).ld();
            } else if (var1 instanceof aur) {
               var18 = ((aur)var1).wS();
            }

            if (var18 != null) {
               INativeSourceUnit var9 = this.A(var18, var2, false);
               if (var9 != null) {
                  var7 = var9;
               }
            }

            var5[0] = var7;
            return true;
         }

         if (var7 == null) {
            return false;
         }

         if (var5 != null) {
            var5[0] = var7;
         }

         var8 = true;
      }

      return var8;
   }

   @Override
   public IDecompiledMethod decompileMethodEx(INativeMethodItem var1, DecompilationContext var2, NativeDecompilationStage var3) {
      IDecompiledMethod var5;
      try (ACLock var4 = this.getLock().a()) {
         var5 = this.pC(var1, var2, atg.pC(var3));
      }

      return var5;
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private IDecompiledMethod pC(auu var1, DecompilationContext var2, boolean var3, NativeDecompilationStage var4) {
      this.getLock().verifyLocked();

      try {
         ach var5 = (ach)this.pC.pC((INativeMethodItem)var1);
         if (var5 != null && var5.getStatus() == DecompilationStatus.COMPLETED) {
            return var5;
         }
      } catch (RuntimeException var13) {
         throw new DecompilerException(Strings.ff("Failed to decompile method %s", var1.getAddress()), var13);
      }

      if (!var3) {
         return null;
      } else {
         ach var14;
         try {
            var14 = (ach)this.pC(var1, var2, atg.pC(var4));
            if (var14 == null) {
               return null;
            }
         } catch (RuntimeException var12) {
            throw new DecompilerException(Strings.ff("Failed to decompile method %s", var1.getAddress()), var12);
         }

         try {
            var1 = (auu)var14.getMethodItem();
            DecompilationStatus var6 = var14.getStatus();
            if (var6 != DecompilationStatus.COMPLETED) {
               return null;
            } else {
               ICMethod var7 = var14.getMethodAST();
               if (var7 == null || var14.fI() != null) {
                  aew var15 = this.ld.pC.pC(var14.getIRContext(), false);
                  var14.pC(var15);
                  byte var8 = 2;
                  String var9 = null;
                  acn var10 = var14.fI();
                  if (var10 != null) {
                     if (Strings.equals(var10.getRootClassname(), UnsupportedConversionException.class.getSimpleName())) {
                        var8 = 3;
                     }

                     var9 = var10.getStacktrace();
                  }

                  var15.setStatus(var8, var9);
               }

               return var14;
            }
         } catch (RuntimeException var11) {
            throw new DecompilerException(Strings.ff("Failed to decompile method %s", var1.getAddress()), var11);
         }
      }
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private INativeSourceUnit A(auu var1, DecompilationContext var2, boolean var3, NativeDecompilationStage var4) {
      this.getLock().verifyLocked();

      acu var6;
      label67: {
         try {
            ach var5 = (ach)this.pC.pC((INativeMethodItem)var1);
            var6 = (acu)this.ys.get(var1);
            if (var5 != null && var5.getStatus() == DecompilationStatus.COMPLETED) {
               if (var6 == null && var3) {
                  break label67;
               }

               return var6;
            }
         } catch (RuntimeException var14) {
            throw new DecompilerException(Strings.ff("Failed to decompile method %s", var1.getAddress()), var14);
         }

         if (!var3) {
            return null;
         }
      }

      ach var15;
      try {
         var15 = (ach)this.pC(var1, var2, atg.pC(var4));
         if (var15 == null) {
            return null;
         }
      } catch (RuntimeException var13) {
         throw new DecompilerException(Strings.ff("Failed to decompile method %s", var1.getAddress()), var13);
      }

      try {
         var1 = (auu)var15.getMethodItem();
         DecompilationStatus var7 = var15.getStatus();
         if (var7 == DecompilationStatus.COMPLETED) {
            ICMethod var8 = var15.getMethodAST();
            if (var8 == null) {
               aew var16 = this.ld.pC.pC(var15.getIRContext(), false);
               var15.pC(var16);
               byte var9 = 2;
               String var10 = null;
               acn var11 = var15.fI();
               if (var11 != null) {
                  if (Strings.equals(var11.getRootClassname(), UnsupportedConversionException.class.getSimpleName())) {
                     var9 = 3;
                  }

                  var10 = var11.getStacktrace();
               }

               var16.setStatus(var9, var10);
            }

            if (var6 == null) {
               var6 = new acu(
                  var15,
                  "c",
                  var1.getAddress(false),
                  var1.getSignature(false),
                  var1.getName(true),
                  this.getUnitProcessor(),
                  this,
                  this.getPropertyDefinitionManager()
               );
               var6.setProcessed(true);
               this.addChild(var6);
            } else {
               var6.setProcessed(true);
               var6.setStatus(null);
            }

            var6.pC(var15);
         } else if (var6 != null) {
            var6.notifyListeners(new JebEvent(J.UnitChange));
         }

         this.ys.put(var1, var6);
         return var6;
      } catch (RuntimeException var12) {
         throw new DecompilerException(Strings.ff("Failed to decompile method %s", var1.getAddress()), var12);
      }
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private IDecompiledField pC(aur var1, DecompilationContext var2, boolean var3) {
      this.getLock().verifyLocked();

      try {
         IDecompiledField var4 = this.pC.pC((INativeFieldItem)var1);
         if (var4 != null && var4.getStatus() == DecompilationStatus.COMPLETED) {
            return var4;
         }
      } catch (RuntimeException var6) {
         throw new DecompilerException(Strings.ff("Failed to decompile field %s", var1.getAddress()), var6);
      }

      if (!var3) {
         return null;
      } else {
         try {
            return this.pC.pC((INativeFieldItem)var1, var2);
         } catch (RuntimeException var5) {
            throw new DecompilerException(Strings.ff("Failed to decompile field %s", var1.getAddress()), var5);
         }
      }
   }

   private INativeSourceUnit A(aur var1, DecompilationContext var2, boolean var3) {
      this.getLock().verifyLocked();

      try {
         acu var4 = (acu)this.ys.get(var1);
         if (var4 == null && var3) {
            IDecompiledField var5 = this.pC.pC((INativeFieldItem)var1, var2);
            var4 = new acu(
               var5,
               "c",
               var1.getAddress(false),
               var1.getSignature(false),
               var1.getName(true),
               this.getUnitProcessor(),
               this,
               this.getPropertyDefinitionManager()
            );
            var4.setProcessed(true);
            this.addChild(var4);
            this.ys.put(var1, var4);
            return var4;
         } else {
            return var4;
         }
      } catch (RuntimeException var6) {
         throw new DecompilerException(Strings.ff("Failed to decompile field %s", var1.getAddress()), var6);
      }
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private IDecompiledClass pC(auq var1, DecompilationContext var2, boolean var3) {
      this.getLock().verifyLocked();

      try {
         IDecompiledClass var4 = this.pC.pC((INativeClassItem)var1);
         if (var4 != null && var4.getStatus() == DecompilationStatus.COMPLETED) {
            return var4;
         }
      } catch (RuntimeException var6) {
         throw new DecompilerException(Strings.ff("Failed to decompile class %s", var1.getAddress()), var6);
      }

      if (!var3) {
         return null;
      } else {
         try {
            return this.pC.pC((INativeClassItem)var1, var2);
         } catch (RuntimeException var5) {
            throw new DecompilerException(Strings.ff("Failed to decompile class %s", var1.getAddress()), var5);
         }
      }
   }

   private INativeSourceUnit A(auq var1, DecompilationContext var2, boolean var3) {
      this.getLock().verifyLocked();

      try {
         acu var4 = (acu)this.ys.get(var1);
         if (var4 == null && var3) {
            IDecompiledClass var5 = this.pC.pC((INativeClassItem)var1, var2);
            var4 = new acu(
               var5,
               "c",
               var1.getAddress(false),
               var1.getSignature(false),
               var1.getName(true),
               this.getUnitProcessor(),
               this,
               this.getPropertyDefinitionManager()
            );
            var4.setProcessed(true);
            this.addChild(var4);
            this.ys.put(var1, var4);
            return var4;
         } else {
            return var4;
         }
      } catch (RuntimeException var6) {
         throw new DecompilerException(Strings.ff("Failed to decompile class %s", var1.getAddress()), var6);
      }
   }

   @Override
   public boolean decompileClass(String var1) {
      return this.decompileClass(var1, null);
   }

   @Override
   public boolean decompileClass(String var1, DecompilationContext var2) {
      return this.pC(var1, var2, auq.class, true, true, null);
   }

   @Override
   public boolean decompileField(String var1) {
      return this.decompileField(var1, null);
   }

   @Override
   public boolean decompileField(String var1, DecompilationContext var2) {
      return this.pC(var1, var2, aur.class, true, true, null);
   }

   @Override
   public boolean decompileMethod(String var1) {
      return this.decompileMethod(var1, null);
   }

   @Override
   public boolean decompileMethod(String var1, DecompilationContext var2) {
      return this.pC(var1, var2, auu.class, true, true, null);
   }

   @Override
   public Collection getDecompiledItems() {
      return this.ys().A();
   }

   @Override
   public IDecompiledItem getDecompiledItem(String var1) {
      INativeItem var2 = this.pC(var1);
      return var2 == null ? null : this.ys().pC(var2);
   }

   @Override
   public IDecompiledItem getDecompiledItem(INativeItem var1) {
      return this.ys().pC(var1);
   }

   @Override
   public IDecompiledItem decompile(INativeItem var1, DecompilationContext var2) {
      return this.ys().pC(var1, var2);
   }

   @Override
   public void resetDecompilation(INativeItem var1) {
      this.pC.A(var1);
   }

   @Override
   public void resetDecompilation(String var1) {
      INativeItem var2 = this.pC(var1);
      if (var2 != null) {
         this.resetDecompilation(var2);
      }
   }

   @Override
   public void removeDecompilation(INativeItem var1) {
      this.pC.kS(var1);
   }

   @Override
   public void removeDecompilation(String var1) {
      INativeItem var2 = this.pC(var1);
      if (var2 != null) {
         this.removeDecompilation(var2);
      }
   }

   @Override
   public void resetAllDecompilations() {
      for (IDecompiledItem var2 : new ArrayList(this.getDecompiledItems())) {
         this.resetDecompilation(var2.getNativeItem());
      }
   }

   @Override
   public void removeAllDecompilations() {
      for (IDecompiledItem var2 : new ArrayList(this.getDecompiledItems())) {
         this.removeDecompilation(var2.getNativeItem());
      }
   }

   @Override
   public String getDecompiledMethodText(String var1) {
      return this.getDecompiledText(var1);
   }

   @Override
   public String getDecompiledFieldText(String var1) {
      return this.getDecompiledText(var1);
   }

   @Override
   public String getDecompiledClassText(String var1) {
      return this.getDecompiledText(var1);
   }

   @Override
   public String getDecompiledText(String var1) {
      INativeItem var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         IDecompiledItem var3 = this.getDecompiledItem(var2);
         return var3 != null && var3.getASTItem() != null ? this.pC(var3.getASTItem()) : null;
      }
   }

   public String pC(ICElement var1) {
      COutputSink var2 = new COutputSink(0L);
      ack var3 = new ack(this.sY, this);
      var2.setDynamicContentManager(var3);
      var1.generate(var2);
      return TextPartUtil.buildRawTextFromPart(var2);
   }

   @Override
   public boolean decompileMethods(Collection var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      IProgressCallback var3 = var2.getCallback();
      if (var3 != null && !var3.isInitialized()) {
         var3.setTotal(var1.size());
      }

      int var4 = 0;

      for (String var6 : var1) {
         if (!this.decompileMethod(var6, var2)) {
            var4++;
         }
      }

      return var4 == 0;
   }

   @Override
   public boolean decompileAllMethods(DecompilationContext var1) {
      var1 = DecompilationContext.safe(var1);
      IProgressCallback var2 = var1.getCallback();
      if (var2 != null && !var2.isInitialized()) {
         var2.setTotal(this.sY.getInternalMethods().size());
      }

      int var3 = 0;

      for (auu var5 : this.sY.getInternalMethodsLeafFirst()) {
         if (!var5.isDisposed() && var5.isInternal() && !this.pC(var5, var1, false, true, null)) {
            var3++;
         }
      }

      return var3 == 0;
   }

   @Override
   public boolean decompileClasses(Collection var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      int var3 = 0;

      for (String var5 : var1) {
         if (!this.decompileClass(var5, var2)) {
            var3++;
         }
      }

      return var3 == 0;
   }

   @Override
   public boolean decompileAllClasses(DecompilationContext var1) {
      var1 = DecompilationContext.safe(var1);
      int var2 = 0;

      for (auq var4 : this.sY.getClasses()) {
         if (!var4.isDisposed() && var4.isInternal() && !this.pC(var4, var1, false, true, null)) {
            var2++;
         }
      }

      return var2 == 0;
   }

   @Override
   public boolean decompileAll(DecompilationContext var1) {
      return this.decompileAllMethods(var1) & this.decompileAllClasses(var1);
   }

   @Override
   public boolean decompile(Collection var1, DecompilationContext var2) {
      boolean var3 = true;

      for (String var5 : var1) {
         INativeItem var6 = this.pC(var5);
         if (var6 == null || this.decompile(var6, var2) == null) {
            var3 = false;
         }
      }

      return var3;
   }

   @Override
   public void removeFreeElements() {
      ArrayList var1 = new ArrayList();

      for (IUnit var3 : this.getChildren()) {
         if (var3 instanceof INativeSourceUnit var4) {
            IDecompiledItem var5 = var4.getDecompiledItem();
            if (var5 == null) {
               acj.pC(new RuntimeException("unexpected null item"));
            } else if (!(var5 instanceof IDecompiledClass)) {
               var1.add(var5);
            } else {
               INativeClassItem var6 = ((IDecompiledClass)var5).getClassItem();

               for (INativeFieldItem var8 : var6.getFields()) {
                  if (this.getDecompiledItem(var8) != null) {
                     var1.add(this.getDecompiledItem(var8));
                  }
               }

               for (INativeMethodItem var13 : var6.getMethods()) {
                  if (this.getDecompiledItem(var13) != null) {
                     var1.add(this.getDecompiledItem(var13));
                  }
               }
            }
         }
      }

      ArrayList var9 = new ArrayList(this.pC.A());
      var9.removeAll(var1);

      for (IDecompiledItem var11 : var9) {
         this.removeDecompilation(var11.getNativeItem());
      }
   }

   private IDecompiledMethod pC(INativeMethodItem var1, DecompilationContext var2, atg var3) {
      IDecompiledMethod var5;
      try {
         var2 = DecompilationContext.safe(var2);
         if (this.wS) {
            var2.addFlags(64);
         }

         IDecompiledMethod var4 = this.pC.pC(var1, var2, var3, 0, false, false);
         var5 = var4;
      } finally {
         this.wS = false;
      }

      return var5;
   }

   public C UT() {
      return this.sY;
   }

   public ayy E() {
      return this.oT;
   }

   public akr sY() {
      return this.fI;
   }

   public EMasterOptimizer pC(IERoutineContext var1) {
      EMasterOptimizer var2 = new EMasterOptimizer(var1, this.WR.irOptMaxRunCount);
      var2.setEnableUnsafeOptimizers(this.WR.enableUnsafeOptimizers);
      var2.setEnableDeobfuscators(this.WR.enableDeobfuscators);
      var2.setDecryptorSupport(this.WR.decryptorSupport);
      this.pC.pC(var2);
      this.getExtensionsManager().customizeIntermediateOptimizer(this, var2);
      boolean var3 = true;
      if (var3 && this.WR.enableExternalPlugins) {
         try {
            Collection var4 = DecompilerHelper.parsePluginNamesListProperty(this.WR.listOfDisabledExternalPlugins);

            for (IPluginFileEntry var6 : this.getParentProject().getEnginesContext().getPluginManager().getPluginEntries(IEOptimizer.class)) {
               if (var6.isValidPlugin()) {
                  IEOptimizer var7 = (IEOptimizer)var6.getPluginObject();
                  if (var7.getPluginInformation() != null) {
                     String var8 = var7.getPluginInformation().getName();
                     if (var8 != null && var4.contains(var8)) {
                        continue;
                     }
                  }

                  if (var6.isPythonPlugin()) {
                     IEOptimizer var10 = (IEOptimizer)var6.getPluginObject();
                     var2.registerOptimizer(var10);
                     var10.setMasterOptimizer(null);
                  } else {
                     IEOptimizer var11 = (IEOptimizer)var6.getPluginObject(true);
                     var2.registerOptimizer(var11);
                  }
               }
            }
         } catch (Exception var9) {
            E.error(S.L("An error occurred while processing the list of external gendec IR optimizer plugins: %s"), var9.getMessage());
         }
      }

      return var2;
   }

   @Override
   public ICMasterOptimizer createASTOptimizer(ICMethod var1) {
      CMasterOptimizer var2 = new CMasterOptimizer(var1, this.WR.astOptMaxRunCount);
      var2.setEnableUnsafeOptimizers(this.WR.enableUnsafeOptimizers);
      var2.setEnableDeobfuscators(this.WR.enableDeobfuscators);
      boolean var3 = true;
      if (var3 && this.WR.enableExternalPlugins) {
         try {
            Collection var4 = DecompilerHelper.parsePluginNamesListProperty(this.WR.listOfDisabledExternalPlugins);

            for (IPluginFileEntry var6 : this.getParentProject().getEnginesContext().getPluginManager().getPluginEntries(ICOptimizer.class)) {
               if (var6.isValidPlugin()) {
                  ICOptimizer var7 = (ICOptimizer)var6.getPluginObject();
                  if (var7.getPluginInformation() != null) {
                     String var8 = var7.getPluginInformation().getName();
                     if (var8 != null && var4.contains(var8)) {
                        continue;
                     }
                  }

                  ICOptimizer var10;
                  if (var6.isPythonPlugin()) {
                     var10 = (ICOptimizer)var6.getPluginObject();
                  } else {
                     var10 = (ICOptimizer)var6.getPluginObject(true);
                  }

                  var2.registerOptimizer(var10);
               }
            }
         } catch (Exception var9) {
            E.error(S.L("An error occurred while processing the list of external gendec AST optimizer plugins: %s"), var9.getMessage());
         }
      }

      return var2;
   }

   public void pC(ICNamingEngine var1) {
      if (this.ld != null) {
         try (ACLock var2 = this.getLock().a()) {
            this.ld.pC(var1);
         }
      }
   }

   @Override
   protected void removeChild(IUnit var1, boolean var2) {
      try (ACLock var3 = this.getLock().a()) {
         super.removeChild(var1, var2);
         if (var1 instanceof acu var4) {
            IDecompiledItem var5 = var4.getDecompiledItem();
            if (var5 != null) {
               this.ys.remove(var5.getNativeItem());
               this.removeDecompilation(var5.getNativeItem());
            }
         }
      }
   }

   @Override
   public List getContributions() {
      return ((IUnit)this.getParent()).getContributions();
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append(S.L("Converter statistics:\n")).append(this.gp.formatStatistics()).append("\n");
      var1.append(S.L("Number of converted items currently held by the decompiler unit:")).append(" ");
      var1.append(this.ys().A().size());
      var1.append("\n");
      return var1.toString();
   }

   @Override
   public INativeContext getNativeContext() {
      return this.sY;
   }

   @Override
   public IEGlobalContext getIntermediateContext() {
      return this.gp.getGlobalContext();
   }

   @Override
   public ICGlobalContext getHighLevelContext() {
      return this.ld;
   }

   @Override
   public INativeObjectTracker getObjectTracker() {
      return this.A;
   }

   public aci ys() {
      return this.pC;
   }

   @Override
   public void onEngineNotification(Object var1) {
      Couple var2 = (Couple)var1;
      IDecompiledItem var3 = (IDecompiledItem)var2.getFirst();
      int var4 = (Integer)var2.getSecond();
      if (var4 >= 0 && var4 <= 2) {
         INativeSourceUnit var5 = (INativeSourceUnit)this.ys.get(var3.getNativeItem());
         if (var5 != null) {
            ((acu)var5).A();
            if (var4 == 2) {
               this.removeChild(var5, true);
            } else if (var4 == 1) {
               this.pC(var3.getNativeItem(), null, false, true, null);
            } else if (var4 == 0) {
               this.notifyListeners(new JebEvent(J.DecompSrcUnitResetEvent, var5));
            }
         }
      }
   }

   public NativeDecompilerExporter ld() {
      return new NativeDecompilerExporter(this);
   }

   @Override
   public void dispose() {
      super.dispose();
      if (this.pC != null) {
         this.pC.UT();
         this.pC = null;
      }

      if (this.A != null) {
         this.A.A();
         this.A = null;
      }

      this.sY = null;
      this.ys = null;
      this.ld = null;
      this.gp = null;
      this.oT = null;
      this.fI = null;
      this.WR = null;
   }
}
