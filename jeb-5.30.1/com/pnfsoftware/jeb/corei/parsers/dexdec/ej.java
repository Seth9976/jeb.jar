package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginFileEntry;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.dao.IFileStore;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.DemoLimitationException;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.code.coordinates.IdentifierCoordinates;
import com.pnfsoftware.jeb.core.output.text.TextPartUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.AbstractUnit;
import com.pnfsoftware.jeb.core.units.IMetadataManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.DecompilationOptions;
import com.pnfsoftware.jeb.core.units.code.DecompilationResult;
import com.pnfsoftware.jeb.core.units.code.DecompilerOutputType;
import com.pnfsoftware.jeb.core.units.code.ISourceUnit;
import com.pnfsoftware.jeb.core.units.code.android.DexDecompilerEvent;
import com.pnfsoftware.jeb.core.units.code.android.DexDecompilerEventQueue;
import com.pnfsoftware.jeb.core.units.code.android.DexDecompilerExporter;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.IGenericUnpacker;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForParameter;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationsDirectory;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecConversionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.DeferredRequestsCollector;
import com.pnfsoftware.jeb.core.units.code.java.IJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAnnotation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDecompilableElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.corei.parsers.dex.Vj;
import com.pnfsoftware.jeb.corei.parsers.dex.bK;
import com.pnfsoftware.jeb.corei.parsers.dex.oL;
import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.concurrent.OperationTimedOutException;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.bec;
import com.pnfsoftware.jebglobal.bjk;
import com.pnfsoftware.jebglobal.bjn;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.bjp;
import com.pnfsoftware.jebglobal.bju;
import com.pnfsoftware.jebglobal.bjy;
import com.pnfsoftware.jebglobal.bkg;
import com.pnfsoftware.jebglobal.bld;
import com.pnfsoftware.jebglobal.ble;
import com.pnfsoftware.jebglobal.bli;
import com.pnfsoftware.jebglobal.bln;
import com.pnfsoftware.jebglobal.blp;
import com.pnfsoftware.jebglobal.bls;
import com.pnfsoftware.jebglobal.blz;
import com.pnfsoftware.jebglobal.bmj;
import com.pnfsoftware.jebglobal.bmu;
import com.pnfsoftware.jebglobal.bmx;
import com.pnfsoftware.jebglobal.bni;
import com.pnfsoftware.jebglobal.bnn;
import com.pnfsoftware.jebglobal.bno;
import com.pnfsoftware.jebglobal.btx;
import com.pnfsoftware.jebglobal.bud;
import com.pnfsoftware.jebglobal.buv;
import com.pnfsoftware.jebglobal.bvt;
import com.pnfsoftware.jebglobal.bwi;
import com.pnfsoftware.jebglobal.bws;
import com.pnfsoftware.jebglobal.chp;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

@Ser
public class ej extends AbstractUnit implements IDexDecompilerUnit {
   private static final ILogger zz = GlobalLog.getLogger(ej.class);
   private static final long JY = 1000L;
   private static final long HF = 1000L;
   @SerId(1)
   private bK LK;
   @SerId(2)
   private blz io;
   @SerId(10)
   private boolean qa;
   @SerId(11)
   private boolean Hk;
   @SerId(12)
   private int Me;
   @SerId(20)
   private boolean PV;
   @SerId(21)
   private boolean oQ;
   @SerId(22)
   private boolean xW;
   @SerId(23)
   private boolean KT;
   @SerId(24)
   private boolean Gf;
   @SerId(25)
   private boolean Ef;
   @SerId(26)
   private boolean cC;
   @SerId(27)
   private boolean sH;
   @SerId(28)
   private boolean CE;
   @SerId(29)
   private boolean wF;
   @SerId(30)
   private boolean If;
   @SerId(31)
   private boolean Dz;
   @SerId(32)
   private int mI;
   @SerId(33)
   private int jq;
   @SerId(38)
   private boolean ui;
   @SerId(39)
   private boolean TX;
   @SerId(40)
   private boolean Rr;
   @SerId(41)
   private int EB;
   @SerId(42)
   private Map Xo;
   @SerId(43)
   private String Bu;
   @SerId(44)
   private volatile int IN;
   @SerId(45)
   private DexDecompilerEventQueue rL;
   @SerId(46)
   private boolean eJ;
   @SerId(47)
   private Boolean YN;
   @SerId(48)
   private Boolean Rv;
   @SerId(49)
   private String zx;
   @SerId(50)
   private int ZT;
   @SerId(51)
   private int Ri;
   @SerId(52)
   private boolean GY;
   @SerId(53)
   private boolean Wx;
   @SerId(54)
   private boolean AB;
   @SerId(55)
   private int CY;
   @SerId(56)
   private boolean WI;
   @SerId(57)
   private boolean Tq;
   @SerId(58)
   private boolean Yp;
   @SerId(59)
   private boolean Gu;
   @SerId(60)
   private volatile Map nY;
   @SerId(61)
   private boolean lF;
   @SerTransient
   private IEventListener nq;
   @SerTransient
   private btx NX;
   @SerTransient
   private volatile bln br;
   @SerTransient
   Map q;
   @SerTransient
   Map RF;
   @SerTransient
   volatile Semaphore xK;
   @SerTransient
   private com.pnfsoftware.jeb.corei.parsers.dexdec.CU tW;
   @SerTransient
   private Set ZA;
   @SerTransient
   private IEventListener Ov;
   @SerTransient
   private volatile Boolean Lj;
   @SerTransient
   volatile EE Dw;
   @SerTransient
   Map Uv;
   @SerTransient
   Map oW;
   @SerTransient
   AtomicInteger gO;
   @SerTransient
   AtomicInteger nf;
   @SerTransient
   AtomicInteger gP;
   @SerTransient
   int za;
   @SerTransient
   boolean lm = false;
   @SerTransient
   private vb nv;
   @SerTransient
   private vb LL;
   @SerTransient
   private AtomicLong PQ;
   @SerTransient
   private AtomicLong fQ;
   @SerTransient
   private bkg fi = null;
   private static final int bl = 16777216;
   @SerTransient
   private List jb;
   private static final String pQ = "//-TEMP-UNCOLL-//";

   @SerCustomInitPostGraph
   private void eJ() {
      this.nv = this.sH(false);
      this.LL = this.sH(true);
      this.q = new HashMap();
      this.RF = new ConcurrentHashMap();
      this.xK = this.Rv();
      this.ZA = new ConcurrentHashSet();
      this.Dw = new PY(5000, 60);
      this.Uv = new ConcurrentHashMap();
      this.oW = new ConcurrentHashMap();
      this.gO = new AtomicInteger();
      this.nf = new AtomicInteger();
      this.gP = new AtomicInteger();
      this.PQ = new AtomicLong();
      this.fQ = new AtomicLong();
      if (this.YN == null) {
         this.YN = true;
      }

      if (this.Rv == null) {
         this.Rv = true;
      }

      if (this.Xo == null) {
         this.Xo = new ConcurrentHashMap();
      }

      if (this.rL == null) {
         this.rL = new DexDecompilerEventQueue(100000);
      }

      this.ZA.add(this.rL);
      if (this.LK != null && this.LK.xW != null && this.nY == null) {
         this.nY = new ConcurrentHashMap(this.LK.xW);
      }

      if (this.LK != null && this.Ov == null) {
         this.LK.addListener(this.Ov = new oM(this));
      }
   }

   private vb sH(boolean var1) {
      int var2 = var1 ? 16 : 8;
      String var3 = "jeb-dexdec-decomp-" + (var1 ? "m" : "c") + "-";
      return new Nt(this, 5, var3, var2);
   }

   public ej(String var1, IInput var2, IUnitProcessor var3, bK var4, IPropertyDefinitionManager var5) {
      super("dcmp_dex", var1, var3, var4, var5);
      this.LK = var4;
      this.eJ();
      this.YN();
   }

   @Override
   public String formatOngoingDecompilations() {
      return this.q(true);
   }

   public String q(boolean var1) {
      if (this.RF.isEmpty()) {
         return S.L("No on-going decompilation.");
      } else {
         ArrayList var2 = new ArrayList(this.RF.entrySet());
         var2.sort((var0, var1x) -> Long.compare((Long)var0.getValue(), (Long)var1x.getValue()));
         int var3 = 0;
         int var4 = var2.size();
         StringBuilder var5 = new StringBuilder();
         Strings.ff(var5, S.L("On-going decompilation tasks: %d\n\n"), var4);

         for (Entry var7 : var2) {
            String var8 = (String)var7.getKey();
            long var9 = (Long)var7.getValue();
            long var11 = System.currentTimeMillis() - var9;
            Strings.ff(var5, "(%d/%d) %s: %d ms\n", var3 + 1, var4, var8, var11);
            if (var1 && this.Uv != null) {
               bud var13 = (bud)this.Uv.get(var8);
               if (var13 != null) {
                  this.q(var13, false, 0, var5);
               }

               var5.append('\n');
            }

            var3++;
         }

         return var5.toString();
      }
   }

   private void q(IDMethodContext var1, boolean var2, int var3, StringBuilder var4) {
      String var5 = Strings.spaces(var3 * 4);
      bud var6 = (bud)var1;
      long var7 = System.currentTimeMillis() - var6.q;
      Strings.ff(var4, "%s%s: %d ms\n", var5, var1.getMethodSignature(), var7);
      if (!var6.RF.isEmpty()) {
         StringBuilder var9 = new StringBuilder();

         for (IDOptimizer var11 : var6.RF) {
            String var12 = var11.getName();
            if (var12 != null && var12.startsWith("Unknown_")) {
               String var13 = var11.getPluginInformation().getDescription();
               if (var13 != null && !var13.isEmpty()) {
                  var12 = var13;
               }
            }

            if (var12 != null && !var12.isEmpty()) {
               if (!var9.isEmpty()) {
                  var9.append("; ");
               }

               var9.append(var12);
            }
         }

         Strings.ff(var4, "%s%s: %s\n", var5, S.L("Optimizing"), var9);
      }

      for (IDMethodContext var16 : var1.getChildrenContexts()) {
         this.q(var16, false, var3 + 1, var4);
      }

      for (IDMethodContext var17 : var1.getCopiesContexts()) {
         this.q(var17, true, var3 + 1, var4);
      }
   }

   @Override
   public void onPropertyChange(String var1) {
      this.YN();
   }

   @Override
   public void dispose() {
      if (this.NX != null) {
         this.NX.oW();
      }

      if (this.LK != null && this.Ov != null) {
         this.LK.removeListener(this.Ov);
         this.Ov = null;
      }

      this.nv.Uv().forEach(var0 -> var0.shutdownNow());
      this.LL.Uv().forEach(var0 -> var0.shutdownNow());
      super.dispose();
   }

   private void YN() {
      IPropertyManager var1 = this.getPropertyManager();
      this.q(1000 * var1.getInteger("MethodDecompilationTimeout"));
      this.YN = var1.getBoolean("DecompileTopLevelContainerClass");
      this.xK(var1.getBoolean("ParseExceptionBlocks"));
      this.Dw(this.getPropertyManager().getBoolean("ParseDebugInformation"));
      this.setThreadPoolSize(this.getPropertyManager().getInteger("DecompilerThreadCount"));
      this.RF(this.getPropertyManager().getInteger("DeferredRequestsCap"));
      this.Uv(var1.getBoolean("EnableUnsafeOptimizers"));
      this.oW(var1.getBoolean("EnableDeobfuscatorOptimizers"));
      this.gO(var1.getBoolean("EnableInlinerOptimizers"));
      this.nf(var1.getBoolean("EnableCollectionOptimizers"));
      this.xK(var1.getInteger("DecryptorSupport"));
      this.Dw(var1.getInteger("AggressiveCodeCleanup"));
      this.gP(var1.getBoolean("EnablePredicateBreaker"));
      this.za(var1.getBoolean("EnableCFUnflattener"));
      this.lm(var1.getBoolean("EnableUnvirtualizer"));
      this.zz(var1.getBoolean("EnableARMRebuilder"));
      this.JY(var1.getBoolean("EnableFinallyRebuilder"));
      this.q(var1.getString("EmulatorConfigPath"));
      this.HF(var1.getBoolean("EnableCacheForStringDecryption"));
      this.Rv = var1.getBoolean("EnableExternalPlugins");
      this.zx = var1.getString("ListOfDisabledExternalPlugins");
      this.Uv(var1.getInteger("GenerateSpecialMetaComments"));
      this.oW(var1.getInteger("StructurerUseVersion"));
      this.gO(var1.getInteger("IdentifierNamingStrategy"));
   }

   public bK q() {
      return this.LK;
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else {
         this.io = new blz(this);
         this.Rr();
         this.qa = true;
         this.Hk = true;
         this.setProcessed(true);
         return true;
      }
   }

   @Override
   public DecompilerOutputType getOutputType() {
      return DecompilerOutputType.JAVA;
   }

   @Override
   public Object getItemObject(long var1) {
      int var3 = (int)(var1 >> 56) & 127;
      int var4 = (int)var1;
      if (var3 == 16) {
         bmu var5 = this.RF().Uv().q(var4);
         if (var5 != null) {
            return var5;
         }
      }

      return this.LK.getItemObject(var1);
   }

   @Override
   public List getGlobalActions() {
      return Collections.emptyList();
   }

   @Override
   public List getItemActions(long var1) {
      return Collections.emptyList();
   }

   @Override
   public boolean isValidAddress(String var1) {
      return false;
   }

   @Override
   public List getAddressActions(String var1) {
      return Collections.emptyList();
   }

   @Override
   public String getAddressOfItem(long var1) {
      return null;
   }

   @Override
   public List getRelatedItems(long var1) {
      return Collections.emptyList();
   }

   @Override
   public long getItemAtAddress(String var1) {
      return 0L;
   }

   @Override
   public boolean canExecuteAction(ActionContext var1) {
      return true;
   }

   @Override
   public boolean prepareExecution(ActionContext var1, IActionData var2) {
      return true;
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2) {
      return this.executeAction(var1, var2, true);
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2, boolean var3) {
      return false;
   }

   public void q(boolean var1, UnitChangeEventData var2) {
      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange, var2));
      }
   }

   public void q(UnitChangeEventData var1) {
      this.q(true, var1);
   }

   public void RF(boolean var1) {
      this.q(var1, null);
   }

   @Override
   public boolean canDecompile(String var1) {
      if (this.LK.za(var1) != null) {
         return true;
      } else {
         bjo var2;
         if ((var2 = this.LK.xK(var1)) != null) {
            return var2.isInternal();
         } else {
            bjp var3;
            return (var3 = this.LK.Dw(var1)) != null ? var3.isInternal() : false;
         }
      }
   }

   @Override
   public ISourceUnit getDecompiledUnit(String var1) {
      boolean var2 = true;
      if (this.YN != null && !this.YN) {
         var2 = false;
      }

      String var4 = var1;
      Object var3;
      if (!var2) {
         if (var1.contains("(")) {
            int var5 = var1.indexOf("+");
            if (var5 >= 0) {
               var4 = var1.substring(0, var5);
            }

            var4 = var4.trim();
            var3 = this.Uv(var4);
         } else {
            int var9 = var1.indexOf("->");
            if (var9 >= 0) {
               var4 = var1.substring(0, var9);
            }

            var4 = var4.trim();
            var3 = this.xK(var4);
         }
      } else {
         bjn var10 = this.oW(var1);
         if (var10 == null) {
            return null;
         }

         var4 = var10.getSignature(false);
         var3 = this.xK(var4);
      }

      return var3 == null ? null : this.q((IJavaElement)var3);
   }

   @Override
   public ISourceUnit decompileToUnit(String var1) {
      return this.decompileToUnit(var1, null);
   }

   @Override
   public ISourceUnit decompileToUnit(String var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return null;
      } else {
         boolean var3 = true;
         if (this.YN != null && !this.YN) {
            var3 = false;
            var2.addFlags(1);
         }

         String var5 = var1;
         Object var4;
         if (!var3) {
            if (var1.contains("(")) {
               int var6 = var1.indexOf("+");
               if (var6 >= 0) {
                  var5 = var1.substring(0, var6);
               }

               var5 = var5.trim();
               bjp var7 = this.LK.PV().q(var5);
               if (var7 == null) {
                  return null;
               }

               var5 = var7.getSignature(false);
               if (!this.decompileMethod(var5, var2)) {
                  return null;
               }

               var4 = this.Uv(var5);
            } else {
               int var12 = var1.indexOf("->");
               if (var12 >= 0) {
                  var5 = var1.substring(0, var12);
               }

               var5 = var5.trim();
               bjn var15 = this.LK.oQ().q(var5);
               if (var15 == null) {
                  return null;
               }

               var5 = var15.getSignature(false);
               if (!this.decompileClass(var5, var2)) {
                  return null;
               }

               var4 = this.xK(var5);
            }
         } else {
            bjn var13 = this.oW(var1);
            if (var13 == null) {
               return null;
            }

            var5 = var13.getSignature(false);
            if (!this.decompileClass(var5, var2)) {
               return null;
            }

            var4 = this.xK(var5);
         }

         if (var4 == null) {
            return null;
         } else {
            Object var14 = this.q((IJavaElement)var4);
            if (var14 == null) {
               var14 = new bls(var5, this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
               ((ISourceUnit)var14).process();
               this.addChild((IUnit)var14);
               this.xK((bmj)var4);
            }

            return (ISourceUnit)var14;
         }
      }
   }

   private void xK(IJavaElement var1) {
      if (var1 instanceof bni var2) {
         var2.addFlags(256);
         var2.getMethods().forEach(var1x -> this.xK(var1x));
         var2.getFields().forEach(var1x -> this.xK(var1x));
         var2.getInnerClasses().forEach(var1x -> this.xK((IJavaElement)var1x));
         var2.getAnonymousClasses().forEach(var1x -> this.xK((IJavaElement)var1x));
      } else if (var1 instanceof bno var3) {
         var3.addFlags(256);
         var3.getInnerClasses().forEach(var1x -> this.xK((IJavaElement)var1x));
         var3.getAnonymousClasses().forEach(var1x -> this.xK((IJavaElement)var1x));
      } else if (var1 instanceof bnn var4) {
         var4.addFlags(256);
      }
   }

   private int q(IDexClass var1, int var2, Collection var3) {
      if ((var2 & 1) != 0) {
         List var9 = var1.getMethods();
         if (var3 != null) {
            var3.addAll(var9);
         }

         return var9.size();
      } else {
         int var4 = 0;

         for (IDexMethod var6 : var1.getMethods()) {
            if (var3 != null) {
               var3.add(var6);
            }

            var4++;

            for (IDexClass var8 : DexUtil.getMemberClasses(this.LK, var6)) {
               var4 += this.q(var8, var2, var3);
            }
         }

         for (IDexClass var11 : DexUtil.getMemberClasses(this.LK, var1)) {
            var4 += this.q(var11, var2, var3);
         }

         return var4;
      }
   }

   private int q(IDexMethod var1, int var2, Collection var3) {
      if ((var2 & 1) != 0) {
         if (var3 != null) {
            var3.add(var1);
         }

         return 1;
      } else {
         int var4 = 1;

         for (IDexClass var6 : DexUtil.getMemberClasses(this.LK, var1)) {
            var4 += this.q(var6, var2, var3);
         }

         return var4;
      }
   }

   public ISourceUnit q(IJavaElement var1) {
      for (IUnit var3 : this.getChildren()) {
         bls var4 = (bls)var3;
         if (var4.getASTElement() == var1) {
            return (ISourceUnit)var3;
         }
      }

      return null;
   }

   private bjn oW(String var1) {
      String var2 = this.gO(var1);
      if (var2 == null) {
         return null;
      } else {
         bjn var3 = this.LK.oQ().q(var2);
         if (var3 == null) {
            return null;
         } else {
            oL var4 = this.LK.io().q(var3);

            for (oL var5 = var4.RF(); var5 != null && !(var5.xK() instanceof Vj); var5 = var5.RF()) {
               var4 = var5;
            }

            return !(var4.xK() instanceof bjn) ? null : (bjn)var4.xK();
         }
      }
   }

   private String gO(String var1) {
      if (!var1.startsWith("L")) {
         return null;
      } else {
         int var2 = var1.indexOf(59);
         return var2 < 0 ? null : var1.substring(0, var2 + 1);
      }
   }

   public blz RF() {
      return this.io;
   }

   public void q(int var1) {
      if (var1 <= 0) {
         var1 = 0;
      }

      this.Me = var1;
   }

   public void xK(boolean var1) {
      this.PV = var1;
   }

   public boolean xK() {
      return this.PV;
   }

   public void Dw(boolean var1) {
      this.oQ = var1;
   }

   public boolean Dw() {
      return this.oQ;
   }

   public void RF(int var1) {
      this.Ri = var1;
   }

   public int Uv() {
      return this.Ri;
   }

   public void Uv(boolean var1) {
      this.Rr = var1;
   }

   public boolean oW() {
      return this.Rr;
   }

   public void oW(boolean var1) {
      this.AB = var1;
   }

   public boolean gO() {
      return this.AB;
   }

   public void gO(boolean var1) {
      this.Gu = var1;
   }

   public boolean nf() {
      return this.Gu;
   }

   public void nf(boolean var1) {
      this.lF = var1;
   }

   public boolean gP() {
      return this.lF;
   }

   public void xK(int var1) {
      this.EB = var1;
   }

   public int za() {
      return this.EB;
   }

   public void Dw(int var1) {
      this.ZT = var1;
   }

   public int lm() {
      return this.ZT;
   }

   public void gP(boolean var1) {
      this.Yp = var1;
   }

   public boolean zz() {
      return this.Yp;
   }

   public void za(boolean var1) {
      this.GY = var1;
   }

   public boolean JY() {
      return this.GY;
   }

   public void lm(boolean var1) {
      this.Wx = var1;
   }

   public boolean HF() {
      return this.Wx;
   }

   public void zz(boolean var1) {
      this.WI = var1;
   }

   public boolean LK() {
      return this.WI;
   }

   public void JY(boolean var1) {
      this.Tq = var1;
   }

   public boolean io() {
      return this.Tq;
   }

   public void q(String var1) {
      this.Bu = var1;
   }

   public String qa() {
      return this.Bu;
   }

   public File Hk() {
      if (this.Bu == null) {
         return null;
      } else {
         File var1 = new File(this.Bu);
         if (var1.isAbsolute()) {
            return var1;
         } else {
            IFileStore var2 = JebCoreService.getDefaultEnginesContext().getDataProvider().getPluginStore();
            if (var2 != null) {
               File var3 = new File(var2.getStoreLocation());
               return new File(var3, this.Bu);
            } else {
               zz.warning("The dexdec emulator config file will be picked from the current folder: %s", IO.getCwd());
               return var1;
            }
         }
      }
   }

   public void HF(boolean var1) {
      this.eJ = var1;
   }

   public boolean Me() {
      return this.eJ;
   }

   public void Uv(int var1) {
      this.CY = var1;
   }

   public int PV() {
      return this.CY;
   }

   public void oW(int var1) {
      this.mI = var1;
   }

   public int oQ() {
      return this.mI;
   }

   public void gO(int var1) {
      this.jq = var1;
   }

   public int xW() {
      return this.jq;
   }

   public void LK(boolean var1) {
      this.KT = var1;
   }

   public boolean KT() {
      return this.KT;
   }

   public void io(boolean var1) {
      this.Gf = var1;
   }

   public boolean Gf() {
      return this.Gf;
   }

   public void qa(boolean var1) {
      this.Ef = var1;
   }

   public boolean Ef() {
      return this.Ef;
   }

   public void Hk(boolean var1) {
      this.cC = var1;
   }

   public boolean cC() {
      return this.cC;
   }

   public void Me(boolean var1) {
      this.sH = var1;
   }

   public boolean sH() {
      return this.sH;
   }

   public void PV(boolean var1) {
      this.CE = var1;
   }

   public boolean CE() {
      return this.CE;
   }

   public void oQ(boolean var1) {
      this.wF = var1;
   }

   public boolean wF() {
      return this.wF;
   }

   public void xW(boolean var1) {
      this.If = var1;
   }

   public boolean If() {
      return this.If;
   }

   public void KT(boolean var1) {
      this.Dz = var1;
   }

   public boolean Dz() {
      return this.Dz;
   }

   public void Gf(boolean var1) {
      this.ui = var1;
   }

   public boolean mI() {
      return this.ui;
   }

   public void Ef(boolean var1) {
      this.TX = var1;
   }

   public boolean jq() {
      return this.TX;
   }

   public void cC(boolean var1) {
      this.YN = var1;
   }

   public boolean ui() {
      return this.YN;
   }

   @Override
   public boolean canPerformConcurrentDecompilations() {
      return true;
   }

   @Override
   public int getThreadPoolSize() {
      if (this.IN > 0) {
         return this.IN;
      } else if (this.IN == 0) {
         return Math.max(2, Runtime.getRuntime().availableProcessors() / 2);
      } else {
         return this.IN == -1 ? Math.max(2, Runtime.getRuntime().availableProcessors()) : 1;
      }
   }

   @Override
   public synchronized void setThreadPoolSize(int var1) {
      this.IN = var1;
      this.xK = this.Rv();
   }

   private Semaphore Rv() {
      return new Semaphore(this.getThreadPoolSize(), true);
   }

   public bK TX() {
      return this.LK;
   }

   public synchronized btx Rr() {
      if (this.NX == null) {
         this.NX = new btx(this, this.io.xK(), this.io.Dw(), this.Bu());

         try {
            new bws().q(this.NX);
         } catch (Exception var2) {
            tw.q(var2);
         }
      }

      return this.NX;
   }

   public bln EB() {
      if (this.br == null) {
         synchronized (this) {
            if (this.br == null) {
               this.br = new bln(this);
            }
         }
      }

      return this.br;
   }

   public bud q(IDexMethod var1) {
      bud var2 = (bud)this.Rr().q(var1);
      var2.q(this.xK());
      var2.RF(this.Dw());
      return var2;
   }

   @Override
   public void addSpecialComment(String var1, String var2) {
      this.LK.q(var1, var2, 256, true);
   }

   @Override
   public void clearSpecialComments(String var1) {
      for (String var3 : this.LK.q(var1, 256, 0)) {
         this.LK.q(var1, var3, true);
      }
   }

   public bvt q(IDMethodContext var1) {
      return this.q(var1, null, true, true, true);
   }

   public bvt q(IDMethodContext var1, List var2, boolean var3, boolean var4, boolean var5) {
      if (!(var1 == null ^ var2 == null)) {
         throw new IllegalArgumentException("Provide a context or a collection of contexts (not both)");
      } else {
         if (this.Lj == null) {
            synchronized (this) {
               if (this.Lj == null) {
                  boolean var7 = Licensing.isCommunityEdition() || Licensing.license_id == 624458483834837192L;
                  this.Lj = var7;
               }
            }
         }

         if (this.Lj) {
            throw new RuntimeException();
         } else {
            bvt var6;
            if (var1 != null) {
               var6 = new bvt(var1, var3);
            } else {
               var6 = new bvt(var2, var3);
            }

            var6.setSafeMode(true);
            var6.setPolicyForOptimizerTag("reorderer", false);
            if (var4) {
               var6.setSafeMode(!this.Rr);
               var6.setPolicyForOptimizerTag("deobfuscator", this.AB);
               var6.setPolicyForOptimizerTag("inliner", this.Gu);
               var6.RF(this.EB);
               var6.xK(this.ZT);
               var6.Dw(this.Yp ? 1 : 0);
               var6.Uv(this.GY ? 1 : 0);
               var6.oW(this.Wx ? 1 : 0);
               var6.gO(this.WI ? 1 : 0);
               var6.nf(this.Tq ? 1 : 0);
            }

            if (var5 && this.Rv) {
               try {
                  if (this.getPropertyManager().getBoolean("EnableExternalPlugins")) {
                     Collection var17 = DecompilerHelper.parsePluginNamesListProperty(this.zx);
                     long var8 = System.currentTimeMillis();
                     boolean var10 = this.PQ.updateAndGet(var2x -> var8 - var2x >= 1000L ? var8 : var2x) == var8;

                     for (IPluginFileEntry var12 : this.getParentProject().getEnginesContext().getPluginManager().getPluginEntries(IDOptimizer.class, var10)) {
                        if (var12.isValidPlugin()) {
                           IDOptimizer var13 = (IDOptimizer)var12.getPluginObject();
                           if (var13.getPluginInformation() != null) {
                              String var14 = var13.getPluginInformation().getName();
                              if (var14 != null && var17.contains(var14)) {
                                 continue;
                              }
                           }

                           IDOptimizer var18;
                           if (var12.isPythonPlugin()) {
                              var18 = (IDOptimizer)var12.getPluginObject();
                           } else {
                              var18 = (IDOptimizer)var12.getPluginObject(true);
                           }

                           var6.add(var18);
                        }
                     }
                  }
               } catch (Exception var15) {
                  zz.error(S.L("An error occurred while processing the list of external dexdec IR optimizer plugins: %s"), var15.getMessage());
               }
            }

            return var6;
         }
      }
   }

   public blp q(IJavaDecompilableElement var1, DeferredRequestsCollector var2, Watchdog var3) {
      return this.q(var1, var2, var3, true, true, true);
   }

   public blp q(IJavaDecompilableElement var1, DeferredRequestsCollector var2, Watchdog var3, boolean var4, boolean var5, boolean var6) {
      if (var1 == null) {
         throw new IllegalArgumentException("Provide an AST method or class");
      } else {
         blp var7 = new blp(var1, var2, var3, var4);
         if (var5) {
            var7.setSafeMode(!this.Rr);
         }

         if (var6 && this.Rv) {
            try {
               if (this.getPropertyManager().getBoolean("EnableExternalPlugins")) {
                  Collection var8 = DecompilerHelper.parsePluginNamesListProperty(this.zx);
                  long var9 = System.currentTimeMillis();
                  boolean var11 = this.fQ.updateAndGet(var2x -> var9 - var2x >= 1000L ? var9 : var2x) == var9;

                  for (IPluginFileEntry var13 : this.getParentProject().getEnginesContext().getPluginManager().getPluginEntries(IJOptimizer.class, var11)) {
                     if (var13.isValidPlugin()) {
                        IJOptimizer var14 = (IJOptimizer)var13.getPluginObject();
                        if (var14.getPluginInformation() != null) {
                           String var15 = var14.getPluginInformation().getName();
                           if (var15 != null && var8.contains(var15)) {
                              continue;
                           }
                        }

                        IJOptimizer var17;
                        if (var13.isPythonPlugin()) {
                           var17 = (IJOptimizer)var13.getPluginObject();
                        } else {
                           var17 = (IJOptimizer)var13.getPluginObject(true);
                        }

                        var7.add(var17);
                     }
                  }
               }
            } catch (Exception var16) {
               zz.error(S.L("An error occurred while processing the list of external dexdec AST optimizer plugins: %s"), var16.getMessage());
            }
         }

         return var7;
      }
   }

   public synchronized bkg Xo() {
      if (this.fi == null) {
         this.fi = this.Bu();
      }

      return this.fi;
   }

   public synchronized bkg Bu() {
      bec var1 = this.LK.ui();
      return new bkg(this.LK, var1);
   }

   public Boolean RF(String var1) {
      return (Boolean)this.Xo.get(var1);
   }

   public void q(String var1, Boolean var2) {
      if (var2 == null) {
         this.Xo.remove(var1);
      } else {
         this.Xo.put(var1, var2);
      }
   }

   public bni xK(String var1) {
      return this.q(var1, true);
   }

   public bni q(String var1, boolean var2) {
      bjn var3 = this.LK.za(var1);
      if (var3 == null) {
         return null;
      } else {
         var1 = var3.getSignature(false);
         bni var4 = this.RF().oW().q(var1);
         if (var4 == null && var2) {
            var4 = this.RF().oW().RF(var1);
         }

         return var4;
      }
   }

   public bnn Dw(String var1) {
      return this.RF(var1, true);
   }

   public bnn RF(String var1, boolean var2) {
      bjo var3 = this.LK.xK(var1);
      if (var3 == null) {
         return null;
      } else {
         var1 = var3.getSignature(false);
         bnn var4 = this.RF().nf().q(var1);
         if (var4 == null && var2) {
            var4 = this.RF().nf().RF(var1);
         }

         return var4;
      }
   }

   public bno Uv(String var1) {
      return this.xK(var1, true);
   }

   public bno xK(String var1, boolean var2) {
      bjp var3 = this.LK.Dw(var1);
      if (var3 == null) {
         return null;
      } else {
         var1 = var3.getSignature(false);
         bno var4 = this.RF().gO().q(var1);
         if (var4 == null && var2) {
            var4 = this.RF().gO().RF(var1);
         }

         return var4;
      }
   }

   @Override
   public String getDecompiledText(String var1) {
      if (var1 == null) {
         return null;
      } else {
         Object var2;
         if (var1.contains("->")) {
            if (var1.contains(":")) {
               var2 = this.Dw(var1);
            } else {
               var2 = this.Uv(var1);
            }
         } else {
            var2 = this.xK(var1);
         }

         return var2 == null ? null : this.RF((IJavaElement)var2);
      }
   }

   @Override
   public String getDecompiledClassText(String var1) {
      bni var2 = this.xK(var1);
      return var2 == null ? null : this.RF((IJavaElement)var2);
   }

   @Override
   public String getDecompiledMethodText(String var1) {
      bno var2 = this.Uv(var1);
      return var2 == null ? null : this.RF((IJavaElement)var2);
   }

   @Override
   public String getDecompiledFieldText(String var1) {
      bnn var2 = this.Dw(var1);
      return var2 == null ? null : this.RF((IJavaElement)var2);
   }

   public String RF(IJavaElement var1) {
      JavaOutputSink var2 = new JavaOutputSink(0);
      bln var3 = new bln(this);
      var2.setDynamicContentManager(var3);
      var1.generate(var2);
      return TextPartUtil.buildRawTextFromPart(var2);
   }

   private boolean RF(String var1, DecompilationContext var2) {
      synchronized (this.q) {
         long var4 = System.currentTimeMillis();
         if (this.q.putIfAbsent(var1, var4) == null) {
            return true;
         } else {
            System.currentTimeMillis();

            do {
               try {
                  this.q.wait();
               } catch (InterruptedException var7) {
                  var2.requestInterruption();
                  return false;
               }

               var4 = System.currentTimeMillis();
            } while (this.q.putIfAbsent(var1, var4) != null);

            return true;
         }
      }
   }

   private void nf(String var1) {
      synchronized (this.q) {
         if (this.q.remove(var1) == null) {
            throw new RuntimeException("Unexpected! Cannot release unlocked item: " + var1);
         } else {
            this.q.notifyAll();
         }
      }
   }

   private Set q(Set var1, DecompilationContext var2) {
      synchronized (this.q) {
         HashSet var4 = new HashSet(var1.size());
         long var5 = System.currentTimeMillis();

         for (String var8 : var1) {
            if (this.q.putIfAbsent(var8, var5) == null) {
               var4.add(var8);
            }
         }

         return var4;
      }
   }

   private void q(Set var1) {
      synchronized (this.q) {
         for (String var4 : var1) {
            if (this.q.remove(var4) == null) {
               throw new RuntimeException("Unexpected! Cannot release unlocked item: " + var4);
            }
         }

         this.q.notifyAll();
      }
   }

   private boolean xK(String var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         IProgressCallback var3 = var2.getCallback();
         bjp var4 = this.LK.Dw(var1);
         if (var4 != null && var4.isInternal()) {
            var1 = var4.getSignature(false);
            boolean var5 = (var2.getOptions().getFlags() & 8) != 0;
            boolean var6 = (var2.getOptions().getFlags() & 16) != 0;
            if (var6) {
               var5 = true;
            }

            bno var7 = null;
            if (!var5) {
               var7 = this.Uv(var1);
               if (var7 == null || var7.isExternal()) {
                  return false;
               }

               if (var7.isBuilt()) {
                  return true;
               }
            }

            if (Thread.interrupted()) {
               var2.requestInterruption();
               return false;
            } else if (!this.RF(var1, var2)) {
               return false;
            } else if (!var5 && var7.isBuilt()) {
               this.nf(var1);
               return true;
            } else {
               bud var8 = null;
               if (var6) {
                  this.Dw.RF(var1);
               } else {
                  var8 = this.Dw.q(var1);
               }

               boolean var9 = var8 == null;
               if (var7 != null) {
                  var7.q(1, S.L("Waiting for decompilation..."));
               }

               byte var10 = 0;
               this.xK.acquireUninterruptibly();
               long var11 = System.currentTimeMillis();
               this.RF.put(var1, System.currentTimeMillis());
               DecompilationResult var13 = new DecompilationResult();
               DeferredRequestsCollector var14 = new DeferredRequestsCollector();

               try {
                  zz.debug(S.L("Decompiling method: %s (%s)"), var1, var7 == null ? S.L("temporary") : S.L("stored"));
                  bjy var16 = var4.RF();
                  if (var16.q() == null) {
                     if (!var5) {
                        var7 = this.io.gO().RF(var1);
                     }
                  } else {
                     var13.nsize = var16.q().getInstructions().size();
                     var13.bsize = var16.q().getInstructionsSize();
                     Long var55 = var2.getMaxTimePerMethod();
                     if (var55 == null) {
                        var55 = (long)this.Me;
                     }

                     Watchdog var60 = new Watchdog(var55);
                     if (var9) {
                        System.currentTimeMillis();
                        this.clearSpecialComments(var1);
                        var8 = this.q((IDexMethod)var4);
                        this.Uv.put(var1, var8);
                        Integer var64 = (Integer)this.oW.merge(var1, 1, (var0, var1x) -> var1x + 1);
                        if (var64 >= 2) {
                           Object[] var10000 = new Object[]{var1};
                        }

                        this.gO.incrementAndGet();

                        try {
                           var8.q(var60);
                           var8.q(var2.getOptions().getFlags());
                           var8.Dw();
                           bvt var66 = this.q(var8);
                           this.q(var66, var2);
                           var66.perform();
                           bwi var23 = new bwi(var8);
                           var23.q();
                           DUtil.normalizeGraph(var8);
                           chp var24 = new chp(var8);
                           var24.q();
                        } finally {
                           this.Uv.remove(var1);
                        }
                     }

                     if (var8 != null) {
                        this.Dw.q(var1, var8);
                     }

                     if (var7 != null) {
                        bli var62 = new bli(this.io, var8);
                        var62.q(this.getPropertyManager().getInteger("StructurerUseVersion"));
                        var7 = var62.Dw();
                        var7.q(0);
                        blp var63 = this.q(var7, var14, var60);
                        var63.perform();
                        var7.setData("IR_CFG_FINAL", var8.getCfg().format());
                        int var65 = var8.getDeobfuscationScore();
                        if (var65 > 0) {
                           int var67 = var8.getCfg().getInstructionCount();
                           int var68 = var65 * 10 / var67;
                           var7.setData("KEY_IR_DEOB_NSCORE", var68);
                           String var69 = null;
                           if (var68 >= 20) {
                              if (var68 < 50) {
                                 var69 = S.L("LOW");
                              } else if (var68 < 200) {
                                 var69 = S.L("MEDIUM");
                              } else if (var68 < 500) {
                                 var69 = S.L("HIGH");
                              } else {
                                 var69 = S.L("EXTRA");
                              }
                           }

                           var7.setData("KEY_IR_DEOB_RATING", var69);
                        }
                     }
                  }

                  if (var7 != null) {
                     List var56 = this.gP(var16.getMethodIndex());
                     var7.Dw(var56);
                     List var61 = this.za(var16.getMethodIndex());
                     var7.Uv(var61);
                     if (var7 != null) {
                        var7.q(var10);
                     }
                  }

                  long var57 = System.currentTimeMillis() - var11;
                  var13.time1 = var57;
                  var13.speedi1 = (double)var13.nsize / var57;
               } catch (Exception var50) {
                  boolean var17 = true;
                  this.nf.incrementAndGet();
                  Throwable var18 = Throwables.getRootCause(var50);
                  String var19 = Throwables.formatStacktrace(var18);
                  String var20 = Strings.safe(var18.getMessage(), S.L("Unspecified error"));
                  byte var21 = 1;
                  if (var18 instanceof DexDecConversionException) {
                     IDalvikInstruction var22 = ((DexDecConversionException)var18).getInstruction();
                     if (var22 != null && var22.isOptimized()) {
                        var20 = Strings.ff(
                           S.L(
                              "Cannot decompile method containing odex instructions: %s\nRestore the original dex first, then load it into JEB.\nReference: %s"
                           ),
                           var1,
                           "https://www.pnfsoftware.com/jeb/manual/android/#optimized-dex-odex"
                        );
                        var17 = false;
                     } else if (var22 == null || buv.q(var22.getOpcode())) {
                        var21 = 2;
                        var17 = false;
                     }
                  } else if (var18 instanceof DemoLimitationException) {
                     var21 = 2;
                     var17 = false;
                  } else if (var18 instanceof OperationTimedOutException) {
                     this.gP.incrementAndGet();
                  }

                  if (var4 != null && var4.RF() != null && var4.RF().q() != null && var4.RF().q().hasParsingErrors()) {
                     var20 = S.L("Bytecode errors may have prevented the decompilation of this method");
                     var17 = false;
                  }

                  if (var7 != null) {
                     var7.q(var21, Licensing.isReleaseBuild() ? var20 : var19);
                  }

                  var2.recordError(var1, var19);
                  var13.error = var19;
                  if (var17) {
                     if (var18 instanceof OperationTimedOutException) {
                        zz.error(S.L("%s: decompilation timed-out: %s"), var1, var18.getMessage());
                     } else {
                        zz.error(S.L("%s: decompilation error: %s"), var1, var18);
                     }

                     tw.q(var50, var1);
                  }

                  throw var50;
               } finally {
                  if (var7 != null) {
                     var7.markBuilt();
                  }

                  if (var9) {
                     var2.recordResult(var1, var13);
                     if (var3 != null) {
                        int var27 = var2.incrementCounter1();
                        long var28 = System.currentTimeMillis() - var11;
                        String var30 = Strings.ff("#%d: %s (%dms)", var27, var1, var28);
                        var3.message(1, var30);
                     }
                  }

                  this.xK.release();
                  this.RF.remove(var1);
                  this.nf(var1);
               }

               int var54 = 0;
               if (!this.lm && var7 != null && !var2.hasFlags(2) && !var14.isEmpty()) {
                  if ((var54 = this.q(var14, var2)) > 0 && this.RF(var1, var2)) {
                     try {
                        var7.markNotBuilt();
                        blp var58 = this.q(var7, null, null);
                        var58.perform();
                        var7.markBuilt();
                     } catch (Exception var47) {
                        throw var47;
                     } finally {
                        this.nf(var1);
                     }
                  }
               } else {
                  var14.clear();
               }

               long var59 = System.currentTimeMillis() - var11;
               var13.time2 = var59;
               var13.speedi2 = (double)var13.nsize / var59;
               Object[] var70 = new Object[]{var1, tw.q(var59), var54};
               this.q(var2, var1);
               return true;
            }
         } else {
            return false;
         }
      }
   }

   private int q(DeferredRequestsCollector var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return -1;
      } else {
         DecompilationOptions var3 = var2.addFlags(16777217);

         try {
            int var4 = 0;

            while (true) {
               List var5 = var1.pullRequestedFieldDecompilations();
               if (var5.isEmpty()) {
                  while (true) {
                     var5 = var1.pullRequestedMethodDecompilations();
                     if (var5.isEmpty() || !var2.recordAndCheckDeferredRequests(var5.size(), this.Ri)) {
                        while (true) {
                           var5 = var1.pullRequestedClassDecompilations();
                           if (var5.isEmpty()) {
                              return var4;
                           }

                           for (String var16 : var5) {
                              if (!var2.recordAndCheckDeferredRequests(1, this.Ri)) {
                                 break;
                              }

                              this.q(var16, var2, 1);
                              var4++;
                           }
                        }
                     }

                     this.decompileMethods(var5, var2);
                     var4 += var5.size();
                  }
               }

               for (String var7 : var5) {
                  if (!var2.recordAndCheckDeferredRequests(1, this.Ri)) {
                     break;
                  }

                  this.decompileField(var7, var2);
                  var4++;
               }
            }
         } finally {
            var2.setOptions(var3);
         }
      }
   }

   @Override
   public boolean decompileMethod(String var1) {
      return this.decompileMethod(var1, null);
   }

   @Override
   public boolean decompileMethod(String var1, DecompilationContext var2) {
      try {
         return this.q(var1, var2);
      } catch (Exception var4) {
         zz.catchingSilent(var4);
         return false;
      }
   }

   public boolean q(String var1, DecompilationContext var2) throws Exception {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         int var3 = var2.getFlags();
         IProgressCallback var4 = var2.getCallback();
         bjp var5 = this.LK.PV().q(var1);
         if (var5 == null) {
            return false;
         } else {
            var1 = var5.getSignature(false);
            bno var6 = this.Uv(var1);
            Boolean var7 = this.q(var6, var3);
            if (var7 != null) {
               return var7;
            } else {
               if (var4 != null && !var4.isInitialized()) {
                  this.q(var2, this.q(var5, var3, null));
               }

               ej.CU var8 = new ej.CU(var1, var2);
               if (!var8.q()) {
                  return false;
               } else {
                  if ((var3 & 1) == 0) {
                     for (IDexClass var11 : DexUtil.getMemberClasses(this.LK, var5)) {
                        this.decompileClass(var11.getSignature(false), var2);
                     }
                  }

                  return true;
               }
            }
         }
      }
   }

   private Boolean q(bno var1, int var2) {
      if (var1 != null && !var1.isExternal()) {
         if (var1.isBuilt()) {
            if ((var2 & 1) != 0) {
               return true;
            }

            if (var1.xK()) {
               return true;
            }
         }

         return null;
      } else {
         return false;
      }
   }

   @Override
   public boolean decompileField(String var1) {
      return this.decompileField(var1, null);
   }

   @Override
   public boolean decompileField(String var1, DecompilationContext var2) {
      return this.Dw(var1, var2);
   }

   private boolean Dw(String var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         var2.getCallback();
         bjo var3 = this.LK.Me().q(var1);
         if (var3 == null) {
            return false;
         } else {
            var1 = var3.getSignature(false);
            bnn var4 = this.Dw(var1);
            if (var4 == null || var4.isExternal()) {
               return false;
            } else if (var4.isBuilt()) {
               return true;
            } else if (!this.RF(var1, var2)) {
               return false;
            } else if (var4.isBuilt()) {
               this.nf(var1);
               return true;
            } else {
               try {
                  IDexValue var5 = this.LK.getStaticFieldInitializer(var3.getIndex());
                  if (var5 != null) {
                     IJavaExpression var6 = this.io.nf.q(var5);
                     var4.q(var6);
                  }

                  List var12 = this.nf(var3.getIndex());
                  var4.Dw(var12);
                  var4.markBuilt();
               } finally {
                  this.nf(var1);
               }

               return true;
            }
         }
      }
   }

   @Override
   public boolean decompileClass(String var1) {
      return this.decompileClass(var1, null);
   }

   @Override
   public boolean decompileClass(String var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         bjn var3 = this.LK.oQ().q(var1);
         return var3 == null ? false : this.q(var3.getSignature(false), var2, 2);
      }
   }

   private boolean q(String var1, DecompilationContext var2, int var3) {
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         bjn var4 = this.LK.oQ().q(var1);
         if (var4 == null) {
            return false;
         } else {
            var1 = var4.getSignature(false);
            int var5 = var2.getFlags();
            IProgressCallback var6 = var2.getCallback();
            if (var3 != 0) {
               bni var7 = this.xK(var1);
               Boolean var8 = this.q(var7, var5);
               if (var8 != null) {
                  return var8;
               }

               ArrayList var9 = new ArrayList();
               this.q(var4, var5, var9);
               ArrayList var10 = new ArrayList(var9.size());
               var9.forEach(var1x -> var10.add(var1x.getSignature(false)));
               if (var3 == 1 || !this.lF) {
                  this.decompileMethods(var10, var2);
               } else if (var3 == 2) {
                  DecompilationOptions var11 = var2.addFlags(72);

                  try {
                     this.decompileMethods(var10, var2);
                  } finally {
                     var2.setOptions(var11);
                  }

                  Set var12 = this.q(new HashSet(var10), var2);
                  if (var12 != null && !var12.isEmpty()) {
                     try {
                        ArrayList var13 = new ArrayList(var12.size());

                        for (String var15 : var12) {
                           bud var16 = this.Dw.q(var15);
                           if (var16 != null) {
                              var13.add(var16);
                           }
                        }

                        bvt var75 = this.q(null, var13, true, true, true);
                        int var77 = var75.perform();
                        if (var77 > 0) {
                           Map var79 = var75.q();

                           for (IDMethodContext var18 : var79.keySet()) {
                              bvt var19 = this.q(var18);
                              var19.perform();
                           }
                        }
                     } finally {
                        this.q(var12);
                     }
                  }

                  this.decompileMethods(var10, var2);
               }
            }

            bni var54 = this.xK(var1);
            bjk var56 = var4.gP();
            if (var56 != null) {
               for (bju var61 : var56.q()) {
                  String var68 = this.LK.oW(var61.getFieldIndex()).getSignature(false);

                  try {
                     this.Dw(var68, var2);
                  } catch (Exception var49) {
                     zz.catchingSilent(var49);
                  }
               }

               for (bjy var62 : var56.xK()) {
                  String var69 = this.LK.gO(var62.getMethodIndex()).getSignature(false);

                  try {
                     this.Uv(var69, var2);
                  } catch (Exception var48) {
                     zz.catchingSilent(var48);
                  }
               }
            }

            if (!var2.hasFlags(1)) {
               for (IDexClass var70 : DexUtil.getMemberClasses(this.LK, var4)) {
                  this.q(var70.getSignature(false), var2, 0);
               }
            }

            DeferredRequestsCollector var60 = new DeferredRequestsCollector();
            if (!this.RF(var1, var2)) {
               return false;
            } else {
               try {
                  if (var54.isBuilt()) {
                     return true;
                  }

                  var54 = this.io.Uv.RF(var1);
                  var54.markNotBuilt();
                  if (var4.isAnnotation()) {
                     Map var64 = var4.getAnnotationDefaults();
                     if (var64 != null && !var64.isEmpty()) {
                        for (IJavaMethod var73 : var54.getMethods()) {
                           String var74 = var73.getName();
                           IDexValue var76 = (IDexValue)var64.get(var74);
                           if (var76 != null) {
                              IJavaExpression var78 = this.io.nf.q(var76);
                              var73.setDefaultValue(var78);
                           }
                        }
                     }
                  }

                  List var65 = this.lm(var4.getClassTypeIndex());
                  var54.Dw(var65);
                  blp var72 = this.q(var54, var60, null);
                  var72.perform();
                  var54.markBuilt();
               } finally {
                  this.nf(var1);
               }

               if (!this.lm && var54 != null && !var2.hasFlags(2) && !var60.isEmpty() && this.q(var60, var2) > 0) {
                  if (!this.RF(var1, var2)) {
                     return false;
                  }

                  try {
                     var54.markNotBuilt();
                     blp var66 = this.q(var54, null, null);
                     var66.perform();
                     var54.markBuilt();
                  } finally {
                     this.nf(var1);
                  }
               }

               if (var6 != null) {
                  var6.message(1, "Built class: " + var1);
               }

               return true;
            }
         }
      }
   }

   private boolean Uv(String var1, DecompilationContext var2) throws Exception {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         int var3 = var2.getFlags();
         IProgressCallback var4 = var2.getCallback();
         bjp var5 = this.LK.PV().q(var1);
         if (var5 == null) {
            return false;
         } else {
            var1 = var5.getSignature(false);
            bno var6 = this.Uv(var1);
            Boolean var7 = this.q(var6, var3);
            if (var7 != null) {
               return var7;
            } else {
               if (var4 != null && !var4.isInitialized()) {
                  this.q(var2, this.q(var5, var3, null));
               }

               ej.CU var8 = new ej.CU(var1, var2);
               if (!var8.q()) {
                  return false;
               } else {
                  if ((var3 & 1) == 0) {
                     for (IDexClass var11 : DexUtil.getMemberClasses(this.LK, var5)) {
                        this.q(var11.getSignature(false), var2, 0);
                     }
                  }

                  return true;
               }
            }
         }
      }
   }

   private Boolean q(bni var1, int var2) {
      if (var1 != null && !var1.isExternal()) {
         if (var1.isBuilt()) {
            if ((var2 & 1) != 0) {
               return true;
            }

            if (var1.xK()) {
               return true;
            }
         }

         return null;
      } else {
         return false;
      }
   }

   @Override
   public boolean decompileMethods(Collection var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         IProgressCallback var3 = var2.getCallback();
         int var4 = var2.getFlags();
         Long var5 = var2.getMaxTimeTotal();
         DecompilationContext var6 = var2;
         if (var3 != null && !var3.isInitialized()) {
            this.q(var2, var1.size());
         }

         int var7 = 0;
         if ((var4 & 4) == 0 && this.getThreadPoolSize() != 1) {
            ArrayList var23 = new ArrayList(var1.size());
            var1.forEach(var3x -> var23.add(new ej.CU(var3x, var2.fork())));
            ExecutorService var24 = (ExecutorService)this.LL.Dw();

            int var11;
            try {
               List var25;
               if (var5 == null) {
                  var25 = var24.invokeAll(var23);
               } else {
                  var25 = var24.invokeAll(var23, var5, TimeUnit.MILLISECONDS);
               }

               var11 = 0;

               for (Future var13 : var25) {
                  ej.CU var14 = (ej.CU)var23.get(var11);
                  if (!var13.isDone() || var13.isCancelled() || !var14.xK || var14.Dw != null) {
                     if (!var13.isDone() || var13.isCancelled()) {
                        zz.debug(S.L("%s: decompilation did not finish"), var14.q);
                        if (var2.getError(var14.q) == null) {
                           var2.recordError(var14.q, S.L("INCOMPLETE"));
                        }
                     }

                     var7++;
                  }

                  var11++;
               }

               return var7 == 0;
            } catch (InterruptedException var20) {
               var2.requestInterruption();
               var11 = 0;
            } finally {
               this.LL.RF(var24);
            }

            return (boolean)var11;
         } else {
            for (String var9 : var1) {
               if (var6.isInterruptionRequested()) {
                  return false;
               }

               ej.CU var10 = new ej.CU(var9, var6);

               try {
                  if (!var10.q()) {
                     var7++;
                  }
               } catch (Exception var19) {
                  var7++;
               }
            }

            return var7 == 0;
         }
      }
   }

   @Override
   public boolean decompileClasses(Collection var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         IProgressCallback var3 = var2.getCallback();
         int var4 = var2.getFlags();
         DecompilationContext var5 = var2;
         long var6 = System.currentTimeMillis();
         int var8 = 0;
         Long var9 = var2.getMaxTimeTotal();
         IdentityHashSet var10 = new IdentityHashSet();

         for (String var12 : var1) {
            bjn var13 = this.LK.za(var12);
            if (var13 != null) {
               this.q(var13, var4, var10);
            }
         }

         if (var3 != null && !var3.isInitialized()) {
            this.q(var2, var10.size());
         }

         boolean var25 = false;
         if (var25) {
            ArrayList var26 = new ArrayList(var10.size());
            var10.forEach(var1x -> var26.add(var1x.getSignature(false)));
            if (!this.decompileMethods(var26, var2)) {
               var8++;
            }

            long var30 = System.currentTimeMillis();
            zz.debug(S.L("(%d methods decompiled in %d ms)\n"), var26.size(), var30 - var6);
            if (var9 != null) {
               var9 = var9 - (var30 - var6);
            }
         }

         label173:
         if ((var4 & 4) == 0 && this.getThreadPoolSize() != 1) {
            ArrayList var28 = new ArrayList(var1.size());
            var1.forEach(var3x -> var28.add(new ej.eo(var3x, var2.fork())));
            ExecutorService var32 = (ExecutorService)this.nv.Dw();

            int var15;
            try {
               List var33;
               if (var9 == null) {
                  var33 = var32.invokeAll(var28);
               } else {
                  var33 = var32.invokeAll(var28, var9, TimeUnit.MILLISECONDS);
               }

               var15 = 0;
               Iterator var16 = var33.iterator();

               while (true) {
                  if (!var16.hasNext()) {
                     break label173;
                  }

                  Future var17 = (Future)var16.next();
                  ej.eo var18 = (ej.eo)var28.get(var15);
                  if (!var17.isDone() || var17.isCancelled() || !var18.Dw || var18.Uv != null) {
                     if (!var17.isDone() || var17.isCancelled()) {
                        zz.debug(S.L("%s: decompilation did not finish"), var18.q);
                        if (var2.getError(var18.q) == null) {
                           var2.recordError(var18.q, S.L("INCOMPLETE"));
                        }
                     }

                     var8++;
                  }

                  var15++;
               }
            } catch (InterruptedException var22) {
               var2.requestInterruption();
               var15 = 0;
            } finally {
               this.nv.RF(var32);
            }

            return (boolean)var15;
         } else {
            for (String var31 : var1) {
               if (var5.isInterruptionRequested()) {
                  return false;
               }

               ej.eo var14 = new ej.eo(var31, var2);
               if (!var14.q()) {
                  var8++;
               }
            }
         }

         long var29 = System.currentTimeMillis();
         zz.debug(S.L("(%d classes constructed in %d ms)\n"), var1.size(), var29 - var6);
         return var8 == 0;
      }
   }

   @Override
   public boolean decompileAllClasses(DecompilationContext var1) {
      ArrayList var2 = new ArrayList();
      this.LK.getClasses().forEach(var1x -> var2.add(var1x.getSignature(false)));
      return this.decompileClasses(var2, var1);
   }

   @Override
   public boolean decompileAllMethods(DecompilationContext var1) {
      ArrayList var2 = new ArrayList();

      for (bjp var4 : this.LK.getMethods()) {
         if (var4.isInternal()) {
            var2.add(var4.getSignature(false));
         }
      }

      return this.decompileMethods(var2, var1);
   }

   @Override
   public boolean decompileAll(DecompilationContext var1) {
      return this.decompileAllClasses(var1);
   }

   @Override
   public boolean decompile(Collection var1, DecompilationContext var2) {
      boolean var3 = true;
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();

      for (String var7 : var1) {
         if (var7.contains("->")) {
            bjp var8 = this.LK.Dw(var7);
            if (var8 != null) {
               var4.add(var8.getSignature(false));
            } else {
               var3 = false;
            }
         } else {
            bjn var11 = this.LK.za(var7);
            if (var11 != null) {
               var5.add(var11.getSignature(false));
            } else {
               var3 = false;
            }
         }
      }

      var2 = DecompilationContext.safe(var2);
      IProgressCallback var10 = var2.getCallback();
      if (var10 != null && !var10.isInitialized()) {
         this.q(var2, var4.size() + var5.size());
      }

      if (!this.decompileMethods(var4, var2)) {
         var3 = false;
      }

      if (!this.decompileClasses(var5, var2)) {
         var3 = false;
      }

      return var3;
   }

   @Override
   public void setCachePolicy(int var1, int var2) {
      if (var1 != 0 && var2 != 0) {
         this.Dw = new PY(var1, var2);
      } else {
         this.Dw = new qV();
      }
   }

   @Override
   public void clearCachedIRs() {
      this.Dw.RF();
   }

   @Override
   public int getCountOfCachedIRs() {
      return this.Dw.q();
   }

   @Override
   public IDMethodContext retrieveCachedIR(String var1) {
      return this.Dw.q(var1);
   }

   @Override
   public void resetAllDecompilations() {
      this.removeAllDecompilations();
   }

   @Override
   public void removeAllDecompilations() {
      throw new RuntimeException("Not implemented!");
   }

   @Override
   public void resetDecompilation(String var1) {
      this.removeDecompilation(var1);
   }

   @Override
   public void removeDecompilation(String var1) {
      boolean var2 = true;
      if (this.YN != null && !this.YN) {
         var2 = false;
      }

      String var3 = var1;
      if (!var2) {
         if (var1.contains("(")) {
            int var4 = var1.indexOf("+");
            if (var4 >= 0) {
               var3 = var1.substring(0, var4);
            }

            var3 = var3.trim();
            bno var5 = this.io.gO().q(var3);
            this.resetMethodElement(var5);
         } else {
            int var9 = var1.indexOf("->");
            if (var9 >= 0) {
               var3 = var1.substring(0, var9);
            }

            var3 = var3.trim();
            bni var11 = this.io.oW().q(var3);
            if (var11 == null) {
               return;
            }

            this.resetClassElement(var11);
         }
      } else {
         bjn var10 = this.oW(var1);
         if (var10 == null) {
            return;
         }

         var3 = var10.getSignature(false);
         bni var12 = this.io.oW().q(var3);
         if (var12 == null) {
            return;
         }

         this.q((IJavaClass)var12);
      }
   }

   private synchronized void q(IJavaClass var1) {
      for (IJavaClass var3 : var1.getInnerClasses()) {
         this.q(var3);
      }

      for (IJavaClass var7 : var1.getAnonymousClasses()) {
         this.q(var7);
      }

      for (IJavaMethod var8 : var1.getMethods()) {
         this.q(var8);
      }

      for (IJavaField var9 : var1.getFields()) {
         this.q(var9);
      }

      this.resetClassElement(var1);
   }

   private synchronized void q(IJavaMethod var1) {
      for (IJavaClass var3 : var1.getInnerClasses()) {
         this.q(var3);
      }

      for (IJavaClass var5 : var1.getAnonymousClasses()) {
         this.q(var5);
      }

      this.resetMethodElement(var1);
   }

   private synchronized void q(IJavaField var1) {
      this.resetFieldElement(var1);
   }

   @Override
   public void runGarbageCollection() {
      this.za++;
      this.removeFreeElements();
      this.Dw.RF();
   }

   @Override
   public void removeFreeElements() {
      this.io.oW.Uv();
      this.io.gO.xK();
      this.io.Uv.xK();
   }

   @Override
   public boolean resetClassElement(IJavaClass var1) {
      boolean var2 = this.io.Uv.xK(var1.getSignature());
      if (var2) {
         bls var3 = null;

         for (IUnit var5 : this.getChildren()) {
            if (var5 instanceof bls && ((bls)var5).getASTElement() == var1) {
               var3 = (bls)var5;
               break;
            }
         }

         if (var3 != null) {
            this.removeChild(var3);
         }
      }

      return var2;
   }

   @Override
   public boolean resetFieldElement(IJavaField var1) {
      boolean var2 = this.io.gO.xK(var1.getSignature());
      if (var2) {
         bls var3 = null;

         for (IUnit var5 : this.getChildren()) {
            if (var5 instanceof bls && ((bls)var5).getASTElement() == var1) {
               var3 = (bls)var5;
               break;
            }
         }

         if (var3 != null) {
            this.removeChild(var3);
         }
      }

      return var2;
   }

   @Override
   public boolean resetMethodElement(IJavaMethod var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = var1.getSignature();
         this.Dw.RF(var2);
         boolean var3 = this.io.oW.xK(var2);
         if (var3) {
            bls var4 = null;

            for (IUnit var6 : this.getChildren()) {
               if (var6 instanceof bls && ((bls)var6).getASTElement() == var1) {
                  var4 = (bls)var6;
                  break;
               }
            }

            if (var4 != null) {
               this.removeChild(var4);
            }
         }

         return var3;
      }
   }

   private List nf(int var1) {
      ArrayList var2 = new ArrayList();
      if (this.io.nf != null) {
         bjo var3 = this.LK.oW(var1);
         bjn var4 = this.LK.oQ().q(var3.Dw().getSignature(false));
         IDexAnnotationsDirectory var5 = var4.getAnnotationsDirectory();
         if (var5 != null) {
            for (IDexAnnotationForField var7 : var5.getFieldsAnnotations()) {
               if (var7.getFieldIndex() == var1) {
                  for (IDexAnnotationItem var9 : var7.getAnnotationItems()) {
                     if (!var9.isSystemLevelAnnotation()) {
                        IJavaAnnotation var10 = this.q(var9.getAnnotation());
                        var2.add(var10);
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   private List gP(int var1) {
      ArrayList var2 = new ArrayList();
      if (this.io.nf != null) {
         bjp var3 = this.LK.gO(var1);
         bjn var4 = this.LK.oQ().q(var3.getClassTypeSignature(false));
         IDexAnnotationsDirectory var5 = var4.getAnnotationsDirectory();
         if (var5 != null) {
            for (IDexAnnotationForMethod var7 : var5.getMethodsAnnotations()) {
               if (var7.getMethodIndex() == var1) {
                  for (IDexAnnotationItem var9 : var7.getAnnotationItems()) {
                     if (!var9.isSystemLevelAnnotation()) {
                        IJavaAnnotation var10 = this.q(var9.getAnnotation());
                        var2.add(var10);
                     }
                  }
                  break;
               }
            }
         }
      }

      return var2;
   }

   private List za(int var1) {
      ArrayList var2 = new ArrayList();
      if (this.io.nf != null) {
         bjp var3 = this.LK.gO(var1);
         bjn var4 = this.LK.oQ().q(var3.getClassTypeSignature(false));
         IDexAnnotationsDirectory var5 = var4.getAnnotationsDirectory();
         if (var5 != null) {
            for (IDexAnnotationForParameter var7 : var5.getParametersAnnotations()) {
               if (var7.getMethodIndex() == var1) {
                  for (List var9 : var7.getAnnotationItemSets()) {
                     ArrayList var10 = new ArrayList();
                     if (var9 != null) {
                        for (IDexAnnotationItem var12 : var9) {
                           if (!var12.isSystemLevelAnnotation()) {
                              IJavaAnnotation var13 = this.q(var12.getAnnotation());
                              var10.add(var13);
                           }
                        }
                     }

                     var2.add(var10);
                  }
                  break;
               }
            }
         }
      }

      return var2;
   }

   private List lm(int var1) {
      ArrayList var2 = new ArrayList();
      if (this.io.nf != null) {
         bjn var3 = this.LK.Dw(var1).xK();
         IDexAnnotationsDirectory var4 = var3.getAnnotationsDirectory();
         if (var4 != null) {
            for (IDexAnnotationItem var6 : var4.getClassAnnotations()) {
               if (!var6.isSystemLevelAnnotation()) {
                  IJavaAnnotation var7 = this.q(var6.getAnnotation());
                  var2.add(var7);
               }
            }
         }
      }

      return var2;
   }

   public IJavaAnnotation q(IDexAnnotation var1) {
      blz var2 = this.RF();
      int var3 = var1.getTypeIndex();
      IJavaType var4 = this.io.RF.q(this.LK.q(var3, false));
      ArrayList var5 = new ArrayList();

      for (IDexAnnotationElement var7 : var1.getElements()) {
         IJavaConstant var8 = this.io.Dw.createString(this.LK.RF(var7.getNameIndex()));
         IJavaExpression var9 = this.io.nf.q(var7.getValue());
         var5.add(var2.createAnnotationElement(var8, var9));
      }

      return var2.createAnnotation(var4, var5);
   }

   @Override
   public IMetadataManager getMetadataManager() {
      return null;
   }

   @Override
   public List getContributions() {
      ArrayList var1 = new ArrayList();
      if (this.tW == null) {
         this.tW = new com.pnfsoftware.jeb.corei.parsers.dexdec.CU(this);
      }

      var1.add(this.tW);
      var1.addAll(((IUnit)this.getParent()).getContributions());
      return var1;
   }

   @Override
   public boolean setIdentifierName(IJavaIdentifier var1, String var2) {
      return this.setIdentifierName(var1, var2, true, true);
   }

   @Override
   public boolean setIdentifierName(IJavaIdentifier var1, String var2, boolean var3, boolean var4) {
      bmx var5 = (bmx)var1.getDefinition();
      if (var5 == null) {
         return false;
      } else {
         IdentifierCoordinates var6 = var5.getCoordinates();
         return this.q().q(var6, var2, var3, var4) >= 1;
      }
   }

   @Override
   public String getIdentifierName(IJavaIdentifier var1) {
      bmx var2 = (bmx)var1.getDefinition();
      if (var2 == null) {
         return null;
      } else {
         IdentifierCoordinates var3 = var2.getCoordinates();
         return this.q().q(var3);
      }
   }

   @Override
   public void registerEventQueue(DexDecompilerEventQueue var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.ZA.add(var1);
      }
   }

   @Override
   public void unregisterEventQueue(DexDecompilerEventQueue var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.ZA.remove(var1);
      }
   }

   @Override
   public void recordDecompilationEvent(DexDecompilerEvent var1) {
      for (DexDecompilerEventQueue var3 : this.ZA) {
         var3.add(var1);
      }
   }

   @Override
   public Collection getGlobalDecompilationEvents(boolean var1) {
      return (Collection)(var1 ? new ArrayList(this.rL.readAll()) : new LinkedHashSet(this.rL.readAll()));
   }

   @Override
   public Collection getGlobalDecompilationEvents() {
      return this.getGlobalDecompilationEvents(false);
   }

   @Override
   public void resetGlobalDecompilationEvents() {
      this.rL.clear();
   }

   public DexDecompilerExporter IN() {
      return new DexDecompilerExporter(this);
   }

   public void q(List var1) {
      this.jb = var1;
   }

   public List rL() {
      return this.jb;
   }

   @Override
   public IEmulatedAndroid createEmulatedAndroid() {
      return new bld(this);
   }

   @Override
   public IGenericUnpacker createGenericUnpacker() {
      return new ble(this);
   }

   private void q(DecompilationContext var1, int var2) {
      IProgressCallback var3 = var1.getCallback();
      if (var3 != null) {
         if (!var3.isInitialized()) {
            var3.setTotal(var2);
         }
      }
   }

   private void q(DecompilationContext var1, String var2) {
      if (!var1.hasFlags(16777216)) {
         IProgressCallback var3 = var1.getCallback();
         if (var3 != null) {
            if (var1.getMap1().putIfAbsent(var2, "") == null) {
               long var4 = var3.increment();
               var3.message(0, Strings.ff(S.L("%d/%d: COMPLETED: %s"), var4, var3.getTotal(), var2));
            }
         }
      }
   }

   private void q(bvt var1, DecompilationContext var2) {
      var1.setPolicyForOptimizerTag("reorderer", true);
   }

   public boolean q(IDexItem var1) {
      return this.q(var1, null);
   }

   public boolean q(IDexItem var1, String[] var2) {
      if (this.nY == null) {
         return false;
      } else {
         String var3 = (String)this.nY.get(var1.getAddress(false));
         if (var3 != null && !var3.startsWith("//-TEMP-UNCOLL-//")) {
            if (var2 != null) {
               var2[0] = var3;
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public boolean q(IDexItem var1, boolean var2) {
      return this.q(var1, var2, null);
   }

   public boolean q(IDexItem var1, boolean var2, String var3) {
      if (var1 == null) {
         return false;
      } else {
         if (this.nY == null) {
            if (!var2) {
               return false;
            }

            synchronized (this) {
               if (this.nY == null) {
                  this.nY = new ConcurrentHashMap();
               }
            }
         }

         String var4 = var1.getAddress(false);
         if (!var2) {
            String var9 = (String)this.nY.get(var4);
            if (var9 == null || var9.startsWith("//-TEMP-UNCOLL-//")) {
               return false;
            } else if (var3 != null) {
               return this.nY.remove(var4) != null;
            } else {
               this.nY.put(var4, "//-TEMP-UNCOLL-//" + var9);
               return true;
            }
         } else {
            String var8;
            if (var3 == null) {
               var8 = (String)this.nY.get(var4);
               if (var8 != null && var8.startsWith("//-TEMP-UNCOLL-//")) {
                  var8 = var8.substring("//-TEMP-UNCOLL-//".length());
               } else {
                  var8 = "";
               }
            } else {
               var8 = var3;
            }

            String var6 = (String)this.nY.put(var4, var8);
            return var6 == null || var6.startsWith("//-TEMP-UNCOLL-//");
         }
      }
   }

   private class CU implements Callable {
      String q;
      DecompilationContext RF;
      boolean xK;
      Exception Dw;

      CU(String var2, DecompilationContext var3) {
         this.q = var2;
         this.RF = DecompilationContext.safe(var3);
      }

      public Boolean q() throws Exception {
         try {
            this.xK = ej.this.xK(this.q, this.RF);
            return this.xK;
         } catch (Exception var2) {
            this.Dw = var2;
            throw var2;
         }
      }
   }

   private class eo implements Callable {
      String q;
      DecompilationContext RF;
      Consumer xK;
      boolean Dw;
      Exception Uv;

      public eo(String var2, DecompilationContext var3) {
         this.q = var2;
         this.RF = var3;
         this.xK = var3.getOptions().getPostDecompilationCallback();
      }

      public Boolean q() {
         try {
            this.Dw = ej.this.decompileClass(this.q, this.RF);
            if (this.xK != null) {
               this.xK.accept(this.q);
            }

            return this.Dw;
         } catch (Exception var2) {
            this.Uv = var2;
            throw var2;
         }
      }
   }
}
