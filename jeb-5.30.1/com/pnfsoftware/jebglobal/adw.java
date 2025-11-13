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
public class adw extends AbstractInteractiveUnit implements INativeDecompilerUnit {
   private static List Uv = new ArrayList();
   private static final StructuredLogger oW = aeg.q(adw.class);
   @SerId(1)
   aea q;
   @SerId(2)
   aek RF;
   @SerId(3)
   private abg gO;
   @SerId(4)
   private IdentityHashMap nf = new IdentityHashMap();
   @SerId(5)
   private afa gP;
   @SerId(6)
   private IEConverter za;
   @SerId(7)
   private bby lm;
   @SerId(8)
   private amu zz;
   @SerId(9)
   private DecompilerOptions JY = new DecompilerOptions();
   @SerTransient
   private INativeDecompilerExtension HF;
   @SerTransient
   private IGlobalAnalyzer LK;
   @SerTransient
   private ISourceCustomizer io;
   @SerTransient
   private INativeDecompilerExtensionsManager qa;
   @SerTransient
   private boolean Hk;
   @SerTransient
   private boolean Me;
   @SerTransient
   private boolean PV;
   @SerTransient
   private com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer.CU oQ;
   @SerTransient
   private UnitItemIdGenerator xW;
   @SerTransient
   private aec KT;
   @SerTransient
   private Rd Gf;
   @SerTransient
   volatile boolean xK;
   @SerTransient
   volatile boolean Dw;

   public static void q(INativeDecompilerExtension var0) {
      if (var0 instanceof AbstractNativeDecompilerExtension) {
         Uv.add((AbstractNativeDecompilerExtension)var0);
      }
   }

   public aec q() {
      if (this.KT == null) {
         this.KT = new aec(this.gO, this);
      }

      return this.KT;
   }

   public UnitItemIdGenerator RF() {
      if (this.xW == null) {
         this.xW = new UnitItemIdGenerator();
      }

      return this.xW;
   }

   @SerCustomInitPostGraph
   private void HF() {
      if (this.qa == null) {
         this.qa = new aei(this);
         this.qa.registerExtension(new adv());
         if (this.gO != null && this.gO.getSubsystemType() != null && this.gO.getSubsystemType().isWindowsLike()) {
            this.qa.registerExtension(new aer());
         }

         this.qa.registerExtensions(Uv, false);
      }
   }

   @Override
   public void postDeserialization(IRuntimeProject var1) {
      String var2 = this.getFormatType();
      IUnitIdentifier var3 = this.getUnitProcessor().getUnitIdentifier(var2);
      if (!(var3 instanceof NativeDecompilerPseudoIdentifier)) {
         throw new IllegalStateException(Strings.ff("Unexpected identifier found for type=%s: class=%s", var2, var3 == null ? null : var3.getClass().getName()));
      } else {
         INativeDecompilerPlugin var4 = ((NativeDecompilerPseudoIdentifier)var3).getPlugin();
         this.q(var4);
      }
   }

   void q(INativeDecompilerPlugin var1) {
      this.RF(var1.getPrimaryExtension(this));
      this.q(var1.getSourceCustomizer(this));
      this.q(var1.getGlobalAnalyzer(this));
   }

   public adw(String var1, String var2, IUnitProcessor var3, abg var4, IPropertyDefinitionManager var5) {
      super(var1, var2, var3, var4, var5);
      this.gO = var4;
      this.HF();
   }

   @Override
   public DecompilerOptions getOptions() {
      return this.JY;
   }

   @Override
   public void onPropertyChange(String var1) {
      if (var1 != null && var1.startsWith(".parsers.native.decomp")) {
         if (this.xK) {
            this.xK = false;
         } else {
            this.LK();
            if ("NextDecompKeepIR".equals(PropertyUtil.extractName(var1))) {
               this.Dw = this.getPropertyManager().getBoolean("NextDecompKeepIR");
               this.xK = true;
               this.getPropertyManager().setBoolean("NextDecompKeepIR", false);
            }
         }
      }
   }

   private void LK() {
      IPropertyManager var1 = this.getPropertyManager();
      this.JY.methodDecompTimeout = 1000L * var1.getInteger("MethodDecompilationTimeout");
      this.JY.enableUnsafeOptimizers = var1.getBoolean("EnableUnsafeOptimizers");
      this.JY.enableDeobfuscators = var1.getBoolean("EnableDeobfuscators");
      this.JY.decryptorSupport = var1.getInteger("DecryptorSupport");
      this.JY.memoryResolutionPolicy = var1.getInteger("MemoryResolutionPolicy");
      this.JY.reconversionMaxCount = var1.getInteger("ReconversionMaxCount");
      this.JY.irOptMaxRunCount = var1.getInteger("IROptimizerMaxRunCount");
      this.JY.structurerUseVersion = var1.getInteger("StructurerUseVersion");
      this.JY.astOptMaxRunCount = var1.getInteger("ASTOptimizerMaxRunCount");
      this.JY.astFriendlyNames = var1.getBoolean("UseFriendlyVariableNames");
      this.JY.astReplaceConstantsByWellKnownLiterals = var1.getBoolean("UseWellKnownLiterals");
      this.q(this.JY.astFriendlyNames ? new aez() : null);
      this.JY.enableExternalPlugins = var1.getBoolean("EnableExternalPlugins");
      this.JY.listOfDisabledExternalPlugins = var1.getString("ListOfDisabledExternalPlugins");
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
      return this.gO.q();
   }

   @Override
   public boolean processInternal() {
      if (this.za == null) {
         return false;
      } else if (!this.gO.isProcessed()) {
         oW.error(S.L("%s is not processed. Process first before processing %s."), this.gO, this);
         return false;
      } else {
         this.lm = this.gO.RF();
         this.zz = new amu(this.lm, this.za.getWildcardTypeManagerDefaultResolutionGroup());
         this.q = new aea(this);
         this.RF = new aek(this);
         ((AbstractConverter)this.za).setNativeContext(this.gO);
         ((alr)this.za.getGlobalContext()).q(this.RF);
         this.gP = new afa(this.zz);
         this.LK();
         ((alr)this.za.getGlobalContext()).q(this.zz);
         ((alr)this.za.getGlobalContext()).q(this.RF);
         this.za.initialize();
         return true;
      }
   }

   public void q(IEConverter var1) {
      this.za = var1;
      if (var1 instanceof AbstractConverter) {
         ((AbstractConverter)var1).setDecompiler(this);
      }
   }

   @Override
   public IEConverter getConverter() {
      return this.za;
   }

   @Override
   public INativeDecompilerExtensionsManager getExtensionsManager() {
      return this.qa;
   }

   public void RF(INativeDecompilerExtension var1) {
      if (this.HF != null) {
         throw new IllegalStateException("A primary extension was already registered");
      } else {
         this.HF = var1;
         if (var1 != null) {
            this.qa.registerExtension(var1, INativeDecompilerExtensionsManager.ExtensionPriority.HIGH_PRIORITY);
         }
      }
   }

   public INativeDecompilerExtension xK() {
      return this.HF;
   }

   public void q(IGlobalAnalyzer var1) {
      this.LK = var1;
   }

   public IGlobalAnalyzer Dw() {
      return this.LK;
   }

   public void q(ISourceCustomizer var1) {
      this.io = var1;
   }

   public ISourceCustomizer Uv() {
      return this.io;
   }

   @Override
   public DecompilerOutputType getOutputType() {
      return DecompilerOutputType.C;
   }

   private INativeItem RF(String var1) {
      if (var1 == null) {
         return null;
      } else {
         long var2 = this.gO.getItemAtAddress(var1);
         if (var2 == 0L) {
            return null;
         } else {
            Object var4 = this.gO.getItemObject(var2);
            if (var4 instanceof axz) {
               var4 = this.gO.q(((axz)var4).getMemoryAddress(), false);
            }

            return (INativeItem)(!(var4 instanceof axp) && !(var4 instanceof axm) && !(var4 instanceof axl) ? null : var4);
         }
      }
   }

   @Override
   public boolean canDecompile(String var1) {
      return this.RF(var1) != null;
   }

   @Override
   public INativeSourceUnit decompileToUnit(String var1) {
      return this.decompileToUnit(var1, null);
   }

   @Override
   public INativeSourceUnit decompileToUnit(String var1, DecompilationContext var2) {
      INativeSourceUnit[] var3 = new INativeSourceUnit[1];
      return !this.q(var1, var2, null, false, true, var3) ? null : var3[0];
   }

   @Override
   public INativeSourceUnit getDecompiledUnit(String var1) {
      INativeSourceUnit[] var2 = new INativeSourceUnit[1];
      return !this.q(var1, null, null, false, false, var2) ? null : var2[0];
   }

   @Override
   public INativeSourceUnit decompileToUnit(INativeItem var1) {
      return this.decompileToUnit(var1, null);
   }

   @Override
   public INativeSourceUnit decompileToUnit(INativeItem var1, DecompilationContext var2) {
      INativeSourceUnit[] var3 = new INativeSourceUnit[1];
      return !this.q(var1, var2, false, true, var3) ? null : var3[0];
   }

   @Override
   public INativeSourceUnit getDecompiledUnit(INativeItem var1) {
      INativeSourceUnit[] var2 = new INativeSourceUnit[1];
      return !this.q(var1, null, false, false, var2) ? null : var2[0];
   }

   private boolean q(String var1, DecompilationContext var2, Class var3, boolean var4, boolean var5, INativeSourceUnit[] var6) {
      boolean var9;
      try (ACLock var7 = this.getLock().a()) {
         INativeItem var8 = this.RF(var1);
         if (var8 != null && (var3 == null || var3 == var8.getClass())) {
            return this.q(var8, var2, var4, var5, var6);
         }

         var9 = false;
      }

      return var9;
   }

   private boolean q(INativeItem var1, DecompilationContext var2, boolean var3, boolean var4, INativeSourceUnit[] var5) {
      boolean var8;
      try (ACLock var6 = this.getLock().a()) {
         if (var5 == null) {
            if (var1 instanceof axp) {
               return this.q((axp)var1, var2, var4, null) != null;
            }

            if (var1 instanceof axm) {
               return this.q((axm)var1, var2, var4) != null;
            }

            if (!(var1 instanceof axl)) {
               boolean var14;
               return var14;
            }

            return this.q((axl)var1, var2, var4) != null;
         }

         INativeSourceUnit var7 = null;
         if (var1 instanceof axp) {
            var7 = this.RF((axp)var1, var2, var4, null);
         } else if (var1 instanceof axm) {
            var7 = this.RF((axm)var1, var2, var4);
         } else if (var1 instanceof axl) {
            var7 = this.RF((axl)var1, var2, var4);
         }

         if (var7 != null && !var3) {
            var1 = var7.getNativeItem();
            axl var18 = null;
            if (var1 instanceof axp) {
               var18 = ((axp)var1).gP();
            } else if (var1 instanceof axm) {
               var18 = ((axm)var1).Dw();
            }

            if (var18 != null) {
               INativeSourceUnit var9 = this.RF(var18, var2, false);
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
         var5 = this.q(var1, var2, avy.q(var3));
      }

      return var5;
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private IDecompiledMethod q(axp var1, DecompilationContext var2, boolean var3, NativeDecompilationStage var4) {
      this.getLock().verifyLocked();

      try {
         adz var5 = (adz)this.q.q((INativeMethodItem)var1);
         if (var5 != null && var5.getStatus() == DecompilationStatus.COMPLETED) {
            return var5;
         }
      } catch (RuntimeException var13) {
         throw new DecompilerException(Strings.ff("Failed to decompile method %s", var1.getAddress()), var13);
      }

      if (!var3) {
         return null;
      } else {
         adz var14;
         try {
            var14 = (adz)this.q(var1, var2, avy.q(var4));
            if (var14 == null) {
               return null;
            }
         } catch (RuntimeException var12) {
            throw new DecompilerException(Strings.ff("Failed to decompile method %s", var1.getAddress()), var12);
         }

         try {
            var1 = (axp)var14.getMethodItem();
            DecompilationStatus var6 = var14.getStatus();
            if (var6 != DecompilationStatus.COMPLETED) {
               return null;
            } else {
               ICMethod var7 = var14.getMethodAST();
               if (var7 == null || var14.HF() != null) {
                  agp var15 = this.gP.q.q(var14.getIRContext(), false);
                  var14.q(var15);
                  byte var8 = 2;
                  String var9 = null;
                  aef var10 = var14.HF();
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
   private INativeSourceUnit RF(axp var1, DecompilationContext var2, boolean var3, NativeDecompilationStage var4) {
      this.getLock().verifyLocked();

      aem var6;
      label67: {
         try {
            adz var5 = (adz)this.q.q((INativeMethodItem)var1);
            var6 = (aem)this.nf.get(var1);
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

      adz var15;
      try {
         var15 = (adz)this.q(var1, var2, avy.q(var4));
         if (var15 == null) {
            return null;
         }
      } catch (RuntimeException var13) {
         throw new DecompilerException(Strings.ff("Failed to decompile method %s", var1.getAddress()), var13);
      }

      try {
         var1 = (axp)var15.getMethodItem();
         DecompilationStatus var7 = var15.getStatus();
         if (var7 == DecompilationStatus.COMPLETED) {
            ICMethod var8 = var15.getMethodAST();
            if (var8 == null) {
               agp var16 = this.gP.q.q(var15.getIRContext(), false);
               var15.q(var16);
               byte var9 = 2;
               String var10 = null;
               aef var11 = var15.HF();
               if (var11 != null) {
                  if (Strings.equals(var11.getRootClassname(), UnsupportedConversionException.class.getSimpleName())) {
                     var9 = 3;
                  }

                  var10 = var11.getStacktrace();
               }

               var16.setStatus(var9, var10);
            }

            if (var6 == null) {
               var6 = new aem(
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

            var6.q(var15);
         } else if (var6 != null) {
            var6.notifyListeners(new JebEvent(J.UnitChange));
         }

         this.nf.put(var1, var6);
         return var6;
      } catch (RuntimeException var12) {
         throw new DecompilerException(Strings.ff("Failed to decompile method %s", var1.getAddress()), var12);
      }
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private IDecompiledField q(axm var1, DecompilationContext var2, boolean var3) {
      this.getLock().verifyLocked();

      try {
         IDecompiledField var4 = this.q.q((INativeFieldItem)var1);
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
            return this.q.q((INativeFieldItem)var1, var2);
         } catch (RuntimeException var5) {
            throw new DecompilerException(Strings.ff("Failed to decompile field %s", var1.getAddress()), var5);
         }
      }
   }

   private INativeSourceUnit RF(axm var1, DecompilationContext var2, boolean var3) {
      this.getLock().verifyLocked();

      try {
         aem var4 = (aem)this.nf.get(var1);
         if (var4 == null && var3) {
            IDecompiledField var5 = this.q.q((INativeFieldItem)var1, var2);
            var4 = new aem(
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
            this.nf.put(var1, var4);
            return var4;
         } else {
            return var4;
         }
      } catch (RuntimeException var6) {
         throw new DecompilerException(Strings.ff("Failed to decompile field %s", var1.getAddress()), var6);
      }
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private IDecompiledClass q(axl var1, DecompilationContext var2, boolean var3) {
      this.getLock().verifyLocked();

      try {
         IDecompiledClass var4 = this.q.q((INativeClassItem)var1);
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
            return this.q.q((INativeClassItem)var1, var2);
         } catch (RuntimeException var5) {
            throw new DecompilerException(Strings.ff("Failed to decompile class %s", var1.getAddress()), var5);
         }
      }
   }

   private INativeSourceUnit RF(axl var1, DecompilationContext var2, boolean var3) {
      this.getLock().verifyLocked();

      try {
         aem var4 = (aem)this.nf.get(var1);
         if (var4 == null && var3) {
            IDecompiledClass var5 = this.q.q((INativeClassItem)var1, var2);
            var4 = new aem(
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
            this.nf.put(var1, var4);
            return var4;
         } else {
            return var4;
         }
      } catch (RuntimeException var6) {
         throw new DecompilerException(Strings.ff("Failed to decompile class %s", var1.getAddress()), var6);
      }
   }

   public IDecompiledMethod q(String var1) {
      long var2 = this.gO.getCanonicalMemoryAddress(var1);
      if (var2 == -1L) {
         return null;
      } else {
         axp var4 = this.gO.q(var2, false);
         return var4 == null ? null : this.q(var4);
      }
   }

   public IDecompiledMethod q(axp var1) {
      return this.q.q((INativeMethodItem)var1);
   }

   @Override
   public boolean decompileClass(String var1) {
      return this.decompileClass(var1, null);
   }

   @Override
   public boolean decompileClass(String var1, DecompilationContext var2) {
      return this.q(var1, var2, axl.class, true, true, null);
   }

   @Override
   public boolean decompileField(String var1) {
      return this.decompileField(var1, null);
   }

   @Override
   public boolean decompileField(String var1, DecompilationContext var2) {
      return this.q(var1, var2, axm.class, true, true, null);
   }

   @Override
   public boolean decompileMethod(String var1) {
      return this.decompileMethod(var1, null);
   }

   @Override
   public boolean decompileMethod(String var1, DecompilationContext var2) {
      return this.q(var1, var2, axp.class, true, true, null);
   }

   @Override
   public Collection getDecompiledItems() {
      return this.zz().RF();
   }

   @Override
   public IDecompiledItem getDecompiledItem(String var1) {
      INativeItem var2 = this.RF(var1);
      return var2 == null ? null : this.zz().q(var2);
   }

   @Override
   public IDecompiledItem getDecompiledItem(INativeItem var1) {
      return this.zz().q(var1);
   }

   @Override
   public IDecompiledItem decompile(INativeItem var1, DecompilationContext var2) {
      return this.zz().q(var1, var2);
   }

   @Override
   public void resetDecompilation(INativeItem var1) {
      this.q.RF(var1);
   }

   @Override
   public void resetDecompilation(String var1) {
      INativeItem var2 = this.RF(var1);
      if (var2 != null) {
         this.resetDecompilation(var2);
      }
   }

   @Override
   public void removeDecompilation(INativeItem var1) {
      this.q.xK(var1);
   }

   @Override
   public void removeDecompilation(String var1) {
      INativeItem var2 = this.RF(var1);
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
      INativeItem var2 = this.RF(var1);
      if (var2 == null) {
         return null;
      } else {
         IDecompiledItem var3 = this.getDecompiledItem(var2);
         return var3 != null && var3.getASTItem() != null ? this.q(var3.getASTItem()) : null;
      }
   }

   public String q(ICElement var1) {
      COutputSink var2 = new COutputSink(0L);
      aec var3 = new aec(this.gO, this);
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
         var2.setTotal(this.gO.getInternalMethods().size());
      }

      int var3 = 0;

      for (axp var5 : this.gO.getInternalMethodsLeafFirst()) {
         if (!var5.isDisposed() && var5.isInternal() && !this.q(var5, var1, false, true, null)) {
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

      for (axl var4 : this.gO.getClasses()) {
         if (!var4.isDisposed() && var4.isInternal() && !this.q(var4, var1, false, true, null)) {
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
         INativeItem var6 = this.RF(var5);
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
               aeb.q(new RuntimeException("unexpected null item"));
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

      ArrayList var9 = new ArrayList(this.q.RF());
      var9.removeAll(var1);

      for (IDecompiledItem var11 : var9) {
         this.removeDecompilation(var11.getNativeItem());
      }
   }

   private IDecompiledMethod q(INativeMethodItem var1, DecompilationContext var2, avy var3) {
      IDecompiledMethod var5;
      try {
         var2 = DecompilationContext.safe(var2);
         if (this.Dw) {
            var2.addFlags(64);
         }

         IDecompiledMethod var4 = this.q.q(var1, var2, var3, 0, false, false);
         var5 = var4;
      } finally {
         this.Dw = false;
      }

      return var5;
   }

   public abg oW() {
      return this.gO;
   }

   public bby gO() {
      return this.lm;
   }

   public amu nf() {
      return this.zz;
   }

   public EMasterOptimizer q(IERoutineContext var1) {
      EMasterOptimizer var2 = new EMasterOptimizer(var1, this.JY.irOptMaxRunCount);
      var2.setEnableUnsafeOptimizers(this.JY.enableUnsafeOptimizers);
      var2.setEnableDeobfuscators(this.JY.enableDeobfuscators);
      var2.setDecryptorSupport(this.JY.decryptorSupport);
      this.q.q(var2);
      this.getExtensionsManager().customizeIntermediateOptimizer(this, var2);
      boolean var3 = true;
      if (var3 && this.JY.enableExternalPlugins) {
         try {
            Collection var4 = DecompilerHelper.parsePluginNamesListProperty(this.JY.listOfDisabledExternalPlugins);

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
            oW.error(S.L("An error occurred while processing the list of external gendec IR optimizer plugins: %s"), var9.getMessage());
         }
      }

      return var2;
   }

   @Override
   public ICMasterOptimizer createASTOptimizer(ICMethod var1) {
      CMasterOptimizer var2 = new CMasterOptimizer(var1, this.JY.astOptMaxRunCount);
      var2.setEnableUnsafeOptimizers(this.JY.enableUnsafeOptimizers);
      var2.setEnableDeobfuscators(this.JY.enableDeobfuscators);
      boolean var3 = true;
      if (var3 && this.JY.enableExternalPlugins) {
         try {
            Collection var4 = DecompilerHelper.parsePluginNamesListProperty(this.JY.listOfDisabledExternalPlugins);

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
            oW.error(S.L("An error occurred while processing the list of external gendec AST optimizer plugins: %s"), var9.getMessage());
         }
      }

      return var2;
   }

   public void q(boolean var1) {
   }

   public boolean gP() {
      return this.Me;
   }

   public void RF(boolean var1) {
   }

   public boolean za() {
      return this.PV;
   }

   public void q(com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer.CU var1) {
   }

   public void q(ICNamingEngine var1) {
      if (this.gP != null) {
         try (ACLock var2 = this.getLock().a()) {
            this.gP.q(var1);
         }
      }
   }

   public ICNamingEngine lm() {
      return this.gP == null ? null : this.gP.getNamingEngine();
   }

   @Override
   protected void removeChild(IUnit var1, boolean var2) {
      try (ACLock var3 = this.getLock().a()) {
         super.removeChild(var1, var2);
         if (var1 instanceof aem var4) {
            IDecompiledItem var5 = var4.getDecompiledItem();
            if (var5 != null) {
               this.nf.remove(var5.getNativeItem());
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
      var1.append(S.L("Converter statistics:\n")).append(this.za.formatStatistics()).append("\n");
      var1.append(S.L("Number of converted items currently held by the decompiler unit:")).append(" ");
      var1.append(this.zz().RF().size());
      var1.append("\n");
      return var1.toString();
   }

   @Override
   public INativeContext getNativeContext() {
      return this.gO;
   }

   @Override
   public IEGlobalContext getIntermediateContext() {
      return this.za.getGlobalContext();
   }

   @Override
   public ICGlobalContext getHighLevelContext() {
      return this.gP;
   }

   @Override
   public INativeObjectTracker getObjectTracker() {
      return this.RF;
   }

   public aea zz() {
      return this.q;
   }

   @Override
   public void onEngineNotification(Object var1) {
      Couple var2 = (Couple)var1;
      IDecompiledItem var3 = (IDecompiledItem)var2.getFirst();
      int var4 = (Integer)var2.getSecond();
      if (var4 >= 0 && var4 <= 2) {
         INativeSourceUnit var5 = (INativeSourceUnit)this.nf.get(var3.getNativeItem());
         if (var5 != null) {
            ((aem)var5).xK();
            if (var4 == 2) {
               this.removeChild(var5, true);
            } else if (var4 == 1) {
               this.q(var3.getNativeItem(), null, false, true, null);
            } else if (var4 == 0) {
               this.notifyListeners(new JebEvent(J.DecompSrcUnitResetEvent, var5));
            }
         }
      }
   }

   public NativeDecompilerExporter JY() {
      return new NativeDecompilerExporter(this);
   }

   @Override
   public void dispose() {
      super.dispose();
      if (this.q != null) {
         this.q.gO();
         this.q = null;
      }

      if (this.RF != null) {
         this.RF.xK();
         this.RF = null;
      }

      this.gO = null;
      this.nf = null;
      this.gP = null;
      this.za = null;
      this.lm = null;
      this.zz = null;
      this.JY = null;
   }
}
