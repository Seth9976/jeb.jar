package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginFileEntry;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.dao.IFileStore;
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
import com.pnfsoftware.jeb.corei.parsers.dex.HE;
import com.pnfsoftware.jeb.corei.parsers.dex.qt;
import com.pnfsoftware.jeb.corei.parsers.dex.vi;
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
import com.pnfsoftware.jebglobal.baj;
import com.pnfsoftware.jebglobal.bfp;
import com.pnfsoftware.jebglobal.bfs;
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.bfu;
import com.pnfsoftware.jebglobal.bfz;
import com.pnfsoftware.jebglobal.bgd;
import com.pnfsoftware.jebglobal.bgl;
import com.pnfsoftware.jebglobal.bhg;
import com.pnfsoftware.jebglobal.bhh;
import com.pnfsoftware.jebglobal.bhl;
import com.pnfsoftware.jebglobal.bhq;
import com.pnfsoftware.jebglobal.bhs;
import com.pnfsoftware.jebglobal.bhv;
import com.pnfsoftware.jebglobal.bic;
import com.pnfsoftware.jebglobal.bim;
import com.pnfsoftware.jebglobal.bix;
import com.pnfsoftware.jebglobal.bja;
import com.pnfsoftware.jebglobal.bjl;
import com.pnfsoftware.jebglobal.bjq;
import com.pnfsoftware.jebglobal.bjr;
import com.pnfsoftware.jebglobal.bpr;
import com.pnfsoftware.jebglobal.bpx;
import com.pnfsoftware.jebglobal.bqp;
import com.pnfsoftware.jebglobal.brk;
import com.pnfsoftware.jebglobal.brx;
import com.pnfsoftware.jebglobal.bsg;
import com.pnfsoftware.jebglobal.cct;
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
public class Ws extends AbstractUnit implements IDexDecompilerUnit {
   private static final ILogger fI = GlobalLog.getLogger(Ws.class);
   @SerId(1)
   private vi WR;
   @SerId(2)
   private bic NS;
   @SerId(10)
   private boolean vP;
   @SerId(11)
   private boolean xC;
   @SerId(12)
   private int ED;
   @SerId(20)
   private boolean Sc;
   @SerId(21)
   private boolean ah;
   @SerId(22)
   private boolean eP;
   @SerId(23)
   private boolean UO;
   @SerId(24)
   private boolean Ab;
   @SerId(25)
   private boolean rl;
   @SerId(26)
   private boolean z;
   @SerId(27)
   private boolean Ek;
   @SerId(28)
   private boolean hK;
   @SerId(29)
   private boolean Er;
   @SerId(30)
   private boolean FE;
   @SerId(31)
   private boolean Aj;
   @SerId(32)
   private int EX;
   @SerId(33)
   private int LM;
   @SerId(38)
   private boolean mv;
   @SerId(39)
   private boolean sO;
   @SerId(40)
   private boolean os;
   @SerId(41)
   private int Cu;
   @SerId(42)
   private Map hZ;
   @SerId(43)
   private String UW;
   @SerId(44)
   private volatile int PR;
   @SerId(45)
   private DexDecompilerEventQueue cX;
   @SerId(46)
   private boolean DQ;
   @SerId(47)
   private Boolean ZN;
   @SerId(48)
   private Boolean OB;
   @SerId(49)
   private String pF;
   @SerId(50)
   private int Bc;
   @SerId(51)
   private int OI;
   @SerId(52)
   private boolean Bf;
   @SerId(53)
   private boolean Pe;
   @SerId(54)
   private boolean ck;
   @SerId(55)
   private int RW;
   @SerId(56)
   private boolean e;
   @SerId(57)
   private boolean xM;
   @SerId(58)
   private boolean kU;
   @SerId(59)
   private boolean Kq;
   @SerId(60)
   private volatile Map go;
   @SerId(61)
   private boolean JF;
   @SerTransient
   private IEventListener Nq;
   @SerTransient
   private bpr pg;
   @SerTransient
   private volatile bhq gj;
   @SerTransient
   Map pC;
   @SerTransient
   Map A;
   @SerTransient
   volatile Semaphore kS;
   @SerTransient
   private com.pnfsoftware.jeb.corei.parsers.dexdec.Sv ZD;
   @SerTransient
   private Set DL;
   @SerTransient
   private IEventListener UH;
   @SerTransient
   private volatile Boolean VD;
   @SerTransient
   volatile zp wS;
   @SerTransient
   Map UT;
   @SerTransient
   Map E;
   @SerTransient
   AtomicInteger sY;
   @SerTransient
   AtomicInteger ys;
   @SerTransient
   AtomicInteger ld;
   @SerTransient
   int gp;
   @SerTransient
   boolean oT = false;
   @SerTransient
   private RC Xs;
   @SerTransient
   private RC KV;
   @SerTransient
   private AtomicLong FK;
   @SerTransient
   private AtomicLong Bi;
   @SerTransient
   private bgl wQ = null;
   @SerTransient
   private List PZ;

   @SerCustomInitPostGraph
   private void xC() {
      this.Xs = this.NS(false);
      this.KV = this.NS(true);
      this.pC = new HashMap();
      this.A = new ConcurrentHashMap();
      this.kS = this.Sc();
      this.DL = new ConcurrentHashSet();
      this.wS = new yt(5000, 60);
      this.UT = new ConcurrentHashMap();
      this.E = new ConcurrentHashMap();
      this.sY = new AtomicInteger();
      this.ys = new AtomicInteger();
      this.ld = new AtomicInteger();
      this.FK = new AtomicLong();
      this.Bi = new AtomicLong();
      if (this.ZN == null) {
         this.ZN = true;
      }

      if (this.OB == null) {
         this.OB = true;
      }

      if (this.hZ == null) {
         this.hZ = new ConcurrentHashMap();
      }

      if (this.cX == null) {
         this.cX = new DexDecompilerEventQueue(100000);
      }

      this.DL.add(this.cX);
      if (this.WR != null && this.WR.Ab != null && this.go == null) {
         this.go = new ConcurrentHashMap(this.WR.Ab);
      }

      if (this.WR != null && this.UH == null) {
         this.WR.addListener(this.UH = new bO(this));
      }
   }

   private RC NS(boolean var1) {
      int var2 = var1 ? 16 : 8;
      String var3 = "jeb-dexdec-decomp-" + (var1 ? "m" : "c") + "-";
      return new cq(this, 5, var3, var2);
   }

   public Ws(String var1, IInput var2, IUnitProcessor var3, vi var4, IPropertyDefinitionManager var5) {
      super("dcmp_dex", var1, var3, var4, var5);
      this.WR = var4;
      this.xC();
      this.ED();
   }

   @Override
   public String formatOngoingDecompilations() {
      return this.pC(true);
   }

   public String pC(boolean var1) {
      if (this.A.isEmpty()) {
         return S.L("No on-going decompilation.");
      } else {
         ArrayList var2 = new ArrayList(this.A.entrySet());
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
            if (var1 && this.UT != null) {
               bpx var13 = (bpx)this.UT.get(var8);
               if (var13 != null) {
                  this.pC(var13, false, 0, var5);
               }

               var5.append('\n');
            }

            var3++;
         }

         return var5.toString();
      }
   }

   private void pC(IDMethodContext var1, boolean var2, int var3, StringBuilder var4) {
      String var5 = Strings.spaces(var3 * 4);
      bpx var6 = (bpx)var1;
      long var7 = System.currentTimeMillis() - var6.pC;
      Strings.ff(var4, "%s%s: %d ms\n", var5, var1.getMethodSignature(), var7);
      if (!var6.A.isEmpty()) {
         StringBuilder var9 = new StringBuilder();

         for (IDOptimizer var11 : var6.A) {
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
         this.pC(var16, false, var3 + 1, var4);
      }

      for (IDMethodContext var17 : var1.getCopiesContexts()) {
         this.pC(var17, true, var3 + 1, var4);
      }
   }

   @Override
   public void onPropertyChange(String var1) {
      this.ED();
   }

   @Override
   public void dispose() {
      if (this.pg != null) {
         this.pg.E();
      }

      if (this.WR != null && this.UH != null) {
         this.WR.removeListener(this.UH);
         this.UH = null;
      }

      this.Xs.wS().forEach(var0 -> var0.shutdownNow());
      this.KV.wS().forEach(var0 -> var0.shutdownNow());
      super.dispose();
   }

   private void ED() {
      IPropertyManager var1 = this.getPropertyManager();
      this.pC(1000 * var1.getInteger("MethodDecompilationTimeout"));
      this.ZN = var1.getBoolean("DecompileTopLevelContainerClass");
      this.A(var1.getBoolean("ParseExceptionBlocks"));
      this.kS(this.getPropertyManager().getBoolean("ParseDebugInformation"));
      this.setThreadPoolSize(this.getPropertyManager().getInteger("DecompilerThreadCount"));
      this.A(this.getPropertyManager().getInteger("DeferredRequestsCap"));
      this.wS(var1.getBoolean("EnableUnsafeOptimizers"));
      this.UT(var1.getBoolean("EnableDeobfuscatorOptimizers"));
      this.E(var1.getBoolean("EnableInlinerOptimizers"));
      this.sY(var1.getBoolean("EnableCollectionOptimizers"));
      this.kS(var1.getInteger("DecryptorSupport"));
      this.wS(var1.getInteger("AggressiveCodeCleanup"));
      this.ys(var1.getBoolean("EnablePredicateBreaker"));
      this.ld(var1.getBoolean("EnableCFUnflattener"));
      this.gp(var1.getBoolean("EnableUnvirtualizer"));
      this.oT(var1.getBoolean("EnableARMRebuilder"));
      this.fI(var1.getBoolean("EnableFinallyRebuilder"));
      this.pC(var1.getString("EmulatorConfigPath"));
      this.WR(var1.getBoolean("EnableCacheForStringDecryption"));
      this.OB = var1.getBoolean("EnableExternalPlugins");
      this.pF = var1.getString("ListOfDisabledExternalPlugins");
      this.UT(var1.getInteger("GenerateSpecialMetaComments"));
      this.E(var1.getInteger("StructurerUseVersion"));
      this.sY(var1.getInteger("IdentifierNamingStrategy"));
   }

   public vi pC() {
      return this.WR;
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else {
         this.NS = new bic(this);
         this.gp();
         this.vP = true;
         this.xC = true;
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
         bix var5 = this.A().wS().pC(var4);
         if (var5 != null) {
            return var5;
         }
      }

      return this.WR.getItemObject(var1);
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

   @Override
   public boolean canDecompile(String var1) {
      if (this.WR.gp(var1) != null) {
         return true;
      } else {
         bft var2;
         if ((var2 = this.WR.kS(var1)) != null) {
            return var2.isInternal();
         } else {
            bfu var3;
            return (var3 = this.WR.wS(var1)) != null ? var3.isInternal() : false;
         }
      }
   }

   @Override
   public ISourceUnit getDecompiledUnit(String var1) {
      boolean var2 = true;
      if (this.ZN != null && !this.ZN) {
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
            var3 = this.UT(var4);
         } else {
            int var9 = var1.indexOf("->");
            if (var9 >= 0) {
               var4 = var1.substring(0, var9);
            }

            var4 = var4.trim();
            var3 = this.kS(var4);
         }
      } else {
         bfs var10 = this.E(var1);
         if (var10 == null) {
            return null;
         }

         var4 = var10.getSignature(false);
         var3 = this.kS(var4);
      }

      return var3 == null ? null : this.pC((IJavaElement)var3);
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
         if (this.ZN != null && !this.ZN) {
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
               bfu var7 = this.WR.fI().pC(var5);
               if (var7 == null) {
                  return null;
               }

               var5 = var7.getSignature(false);
               if (!this.decompileMethod(var5, var2)) {
                  return null;
               }

               var4 = this.UT(var5);
            } else {
               int var12 = var1.indexOf("->");
               if (var12 >= 0) {
                  var5 = var1.substring(0, var12);
               }

               var5 = var5.trim();
               bfs var15 = this.WR.WR().pC(var5);
               if (var15 == null) {
                  return null;
               }

               var5 = var15.getSignature(false);
               if (!this.decompileClass(var5, var2)) {
                  return null;
               }

               var4 = this.kS(var5);
            }
         } else {
            bfs var13 = this.E(var1);
            if (var13 == null) {
               return null;
            }

            var5 = var13.getSignature(false);
            if (!this.decompileClass(var5, var2)) {
               return null;
            }

            var4 = this.kS(var5);
         }

         if (var4 == null) {
            return null;
         } else {
            Object var14 = this.pC((IJavaElement)var4);
            if (var14 == null) {
               var14 = new bhv(var5, this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
               ((ISourceUnit)var14).process();
               this.addChild((IUnit)var14);
               this.kS((bim)var4);
            }

            return (ISourceUnit)var14;
         }
      }
   }

   private void kS(IJavaElement var1) {
      if (var1 instanceof bjl var2) {
         var2.addFlags(256);
         var2.getMethods().forEach(var1x -> this.kS(var1x));
         var2.getFields().forEach(var1x -> this.kS(var1x));
         var2.getInnerClasses().forEach(var1x -> this.kS((IJavaElement)var1x));
         var2.getAnonymousClasses().forEach(var1x -> this.kS((IJavaElement)var1x));
      } else if (var1 instanceof bjr var3) {
         var3.addFlags(256);
         var3.getInnerClasses().forEach(var1x -> this.kS((IJavaElement)var1x));
         var3.getAnonymousClasses().forEach(var1x -> this.kS((IJavaElement)var1x));
      } else if (var1 instanceof bjq var4) {
         var4.addFlags(256);
      }
   }

   private int pC(IDexClass var1, int var2, Collection var3) {
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

            for (IDexClass var8 : DexUtil.getMemberClasses(this.WR, var6)) {
               var4 += this.pC(var8, var2, var3);
            }
         }

         for (IDexClass var11 : DexUtil.getMemberClasses(this.WR, var1)) {
            var4 += this.pC(var11, var2, var3);
         }

         return var4;
      }
   }

   private int pC(IDexMethod var1, int var2, Collection var3) {
      if ((var2 & 1) != 0) {
         if (var3 != null) {
            var3.add(var1);
         }

         return 1;
      } else {
         int var4 = 1;

         for (IDexClass var6 : DexUtil.getMemberClasses(this.WR, var1)) {
            var4 += this.pC(var6, var2, var3);
         }

         return var4;
      }
   }

   public ISourceUnit pC(IJavaElement var1) {
      for (IUnit var3 : this.getChildren()) {
         bhv var4 = (bhv)var3;
         if (var4.getASTElement() == var1) {
            return (ISourceUnit)var3;
         }
      }

      return null;
   }

   private bfs E(String var1) {
      String var2 = this.sY(var1);
      if (var2 == null) {
         return null;
      } else {
         bfs var3 = this.WR.WR().pC(var2);
         if (var3 == null) {
            return null;
         } else {
            HE var4 = this.WR.ld().pC(var3);

            for (HE var5 = var4.A(); var5 != null && !(var5.kS() instanceof qt); var5 = var5.A()) {
               var4 = var5;
            }

            return !(var4.kS() instanceof bfs) ? null : (bfs)var4.kS();
         }
      }
   }

   private String sY(String var1) {
      if (!var1.startsWith("L")) {
         return null;
      } else {
         int var2 = var1.indexOf(59);
         return var2 < 0 ? null : var1.substring(0, var2 + 1);
      }
   }

   public bic A() {
      return this.NS;
   }

   public void pC(int var1) {
      if (var1 <= 0) {
         var1 = 0;
      }

      this.ED = var1;
   }

   public void A(boolean var1) {
      this.Sc = var1;
   }

   public boolean kS() {
      return this.Sc;
   }

   public void kS(boolean var1) {
      this.ah = var1;
   }

   public boolean wS() {
      return this.ah;
   }

   public void A(int var1) {
      this.OI = var1;
   }

   public void wS(boolean var1) {
      this.os = var1;
   }

   public void UT(boolean var1) {
      this.ck = var1;
   }

   public void E(boolean var1) {
      this.Kq = var1;
   }

   public void sY(boolean var1) {
      this.JF = var1;
   }

   public void kS(int var1) {
      this.Cu = var1;
   }

   public void wS(int var1) {
      this.Bc = var1;
   }

   public void ys(boolean var1) {
      this.kU = var1;
   }

   public void ld(boolean var1) {
      this.Bf = var1;
   }

   public void gp(boolean var1) {
      this.Pe = var1;
   }

   public void oT(boolean var1) {
      this.e = var1;
   }

   public void fI(boolean var1) {
      this.xM = var1;
   }

   public void pC(String var1) {
      this.UW = var1;
   }

   public File UT() {
      if (this.UW == null) {
         return null;
      } else {
         File var1 = new File(this.UW);
         if (var1.isAbsolute()) {
            return var1;
         } else {
            IFileStore var2 = JebCoreService.getDefaultEnginesContext().getDataProvider().getPluginStore();
            if (var2 != null) {
               File var3 = new File(var2.getStoreLocation());
               return new File(var3, this.UW);
            } else {
               fI.warning("The dexdec emulator config file will be picked from the current folder: %s", IO.getCwd());
               return var1;
            }
         }
      }
   }

   public void WR(boolean var1) {
      this.DQ = var1;
   }

   public boolean E() {
      return this.DQ;
   }

   public void UT(int var1) {
      this.RW = var1;
   }

   public int sY() {
      return this.RW;
   }

   public void E(int var1) {
      this.EX = var1;
   }

   public void sY(int var1) {
      this.LM = var1;
   }

   public int ys() {
      return this.LM;
   }

   @Override
   public boolean canPerformConcurrentDecompilations() {
      return true;
   }

   @Override
   public int getThreadPoolSize() {
      if (this.PR > 0) {
         return this.PR;
      } else if (this.PR == 0) {
         return Math.max(2, Runtime.getRuntime().availableProcessors() / 2);
      } else {
         return this.PR == -1 ? Math.max(2, Runtime.getRuntime().availableProcessors()) : 1;
      }
   }

   @Override
   public synchronized void setThreadPoolSize(int var1) {
      this.PR = var1;
      this.kS = this.Sc();
   }

   private Semaphore Sc() {
      return new Semaphore(this.getThreadPoolSize(), true);
   }

   public vi ld() {
      return this.WR;
   }

   public synchronized bpr gp() {
      if (this.pg == null) {
         this.pg = new bpr(this, this.NS.A(), this.NS.kS(), this.WR());

         try {
            new bsg().pC(this.pg);
         } catch (Exception var2) {
            rQ.pC(var2);
         }
      }

      return this.pg;
   }

   public bhq oT() {
      if (this.gj == null) {
         synchronized (this) {
            if (this.gj == null) {
               this.gj = new bhq(this);
            }
         }
      }

      return this.gj;
   }

   public bpx pC(IDexMethod var1) {
      bpx var2 = (bpx)this.gp().pC(var1);
      var2.pC(this.kS());
      var2.A(this.wS());
      return var2;
   }

   @Override
   public void addSpecialComment(String var1, String var2) {
      this.WR.pC(var1, var2, 256, true);
   }

   @Override
   public void clearSpecialComments(String var1) {
      for (String var3 : this.WR.pC(var1, 256, 0)) {
         this.WR.pC(var1, var3, true);
      }
   }

   public brk pC(IDMethodContext var1) {
      return this.pC(var1, null, true, true, true);
   }

   public brk pC(IDMethodContext var1, List var2, boolean var3, boolean var4, boolean var5) {
      if (!(var1 == null ^ var2 == null)) {
         throw new IllegalArgumentException("Provide a context or a collection of contexts (not both)");
      } else {
         if (this.VD == null) {
            synchronized (this) {
               if (this.VD == null) {
                  boolean var7 = Licensing.isCommunityEdition() || Licensing.license_id == 624458483834837192L;
                  this.VD = var7;
               }
            }
         }

         if (this.VD) {
            throw new RuntimeException();
         } else {
            brk var6;
            if (var1 != null) {
               var6 = new brk(var1, var3);
            } else {
               var6 = new brk(var2, var3);
            }

            var6.setSafeMode(true);
            var6.setPolicyForOptimizerTag("reorderer", false);
            if (var4) {
               var6.setSafeMode(!this.os);
               var6.setPolicyForOptimizerTag("deobfuscator", this.ck);
               var6.setPolicyForOptimizerTag("inliner", this.Kq);
               var6.A(this.Cu);
               var6.kS(this.Bc);
               var6.wS(this.kU ? 1 : 0);
               var6.UT(this.Bf ? 1 : 0);
               var6.E(this.Pe ? 1 : 0);
               var6.sY(this.e ? 1 : 0);
               var6.ys(this.xM ? 1 : 0);
            }

            if (var5 && this.OB) {
               try {
                  if (this.getPropertyManager().getBoolean("EnableExternalPlugins")) {
                     Collection var17 = DecompilerHelper.parsePluginNamesListProperty(this.pF);
                     long var8 = System.currentTimeMillis();
                     boolean var10 = this.FK.updateAndGet(var2x -> var8 - var2x >= 1000L ? var8 : var2x) == var8;

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
                  fI.error(S.L("An error occurred while processing the list of external dexdec IR optimizer plugins: %s"), var15.getMessage());
               }
            }

            return var6;
         }
      }
   }

   public bhs pC(IJavaDecompilableElement var1, DeferredRequestsCollector var2, Watchdog var3) {
      return this.pC(var1, var2, var3, true, true, true);
   }

   public bhs pC(IJavaDecompilableElement var1, DeferredRequestsCollector var2, Watchdog var3, boolean var4, boolean var5, boolean var6) {
      if (var1 == null) {
         throw new IllegalArgumentException("Provide an AST method or class");
      } else {
         bhs var7 = new bhs(var1, var2, var3, var4);
         if (var5) {
            var7.setSafeMode(!this.os);
         }

         if (var6 && this.OB) {
            try {
               if (this.getPropertyManager().getBoolean("EnableExternalPlugins")) {
                  Collection var8 = DecompilerHelper.parsePluginNamesListProperty(this.pF);
                  long var9 = System.currentTimeMillis();
                  boolean var11 = this.Bi.updateAndGet(var2x -> var9 - var2x >= 1000L ? var9 : var2x) == var9;

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
               fI.error(S.L("An error occurred while processing the list of external dexdec AST optimizer plugins: %s"), var16.getMessage());
            }
         }

         return var7;
      }
   }

   public synchronized bgl fI() {
      if (this.wQ == null) {
         this.wQ = this.WR();
      }

      return this.wQ;
   }

   public synchronized bgl WR() {
      baj var1 = this.WR.Ek();
      return new bgl(this.WR, var1);
   }

   public Boolean A(String var1) {
      return (Boolean)this.hZ.get(var1);
   }

   public void pC(String var1, Boolean var2) {
      if (var2 == null) {
         this.hZ.remove(var1);
      } else {
         this.hZ.put(var1, var2);
      }
   }

   public bjl kS(String var1) {
      return this.pC(var1, true);
   }

   public bjl pC(String var1, boolean var2) {
      bfs var3 = this.WR.gp(var1);
      if (var3 == null) {
         return null;
      } else {
         var1 = var3.getSignature(false);
         bjl var4 = this.A().UT().pC(var1);
         if (var4 == null && var2) {
            var4 = this.A().UT().A(var1);
         }

         return var4;
      }
   }

   public bjq wS(String var1) {
      return this.A(var1, true);
   }

   public bjq A(String var1, boolean var2) {
      bft var3 = this.WR.kS(var1);
      if (var3 == null) {
         return null;
      } else {
         var1 = var3.getSignature(false);
         bjq var4 = this.A().sY().pC(var1);
         if (var4 == null && var2) {
            var4 = this.A().sY().A(var1);
         }

         return var4;
      }
   }

   public bjr UT(String var1) {
      return this.kS(var1, true);
   }

   public bjr kS(String var1, boolean var2) {
      bfu var3 = this.WR.wS(var1);
      if (var3 == null) {
         return null;
      } else {
         var1 = var3.getSignature(false);
         bjr var4 = this.A().E().pC(var1);
         if (var4 == null && var2) {
            var4 = this.A().E().A(var1);
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
               var2 = this.wS(var1);
            } else {
               var2 = this.UT(var1);
            }
         } else {
            var2 = this.kS(var1);
         }

         return var2 == null ? null : this.A((IJavaElement)var2);
      }
   }

   @Override
   public String getDecompiledClassText(String var1) {
      bjl var2 = this.kS(var1);
      return var2 == null ? null : this.A((IJavaElement)var2);
   }

   @Override
   public String getDecompiledMethodText(String var1) {
      bjr var2 = this.UT(var1);
      return var2 == null ? null : this.A((IJavaElement)var2);
   }

   @Override
   public String getDecompiledFieldText(String var1) {
      bjq var2 = this.wS(var1);
      return var2 == null ? null : this.A((IJavaElement)var2);
   }

   public String A(IJavaElement var1) {
      JavaOutputSink var2 = new JavaOutputSink(0);
      bhq var3 = new bhq(this);
      var2.setDynamicContentManager(var3);
      var1.generate(var2);
      return TextPartUtil.buildRawTextFromPart(var2);
   }

   @Override
   public boolean setIdentifierName(String var1, String var2, String var3) {
      bjr var4 = this.kS(var1, false);
      if (var4 != null && !var4.isExternal() && var4.isBuilt()) {
         IJavaIdentifier var6 = var4.UT().getIdentifier(var2);
         IdentifierCoordinates var5;
         if (var6 == null) {
            var5 = null;

            for (Entry var8 : this.WR.getRenamedIdentifiers().entrySet()) {
               if (((String)var8.getValue()).equals(var2)) {
                  var5 = (IdentifierCoordinates)var8.getKey();
                  break;
               }
            }
         } else {
            var5 = var6.getDefinition().getCoordinates();
         }

         if (var5 == null) {
            for (IJavaIdentifier var10 : var4.UT().getIdentifiers()) {
               if (var2.equals(var10.getDebugName())) {
                  var5 = var10.getDefinition().getCoordinates();
                  break;
               }
            }
         }

         return var5 == null ? false : this.pC().pC(var5, var3, true, true) >= 1;
      } else {
         return false;
      }
   }

   private boolean A(String var1, DecompilationContext var2) {
      synchronized (this.pC) {
         long var4 = System.currentTimeMillis();
         if (this.pC.putIfAbsent(var1, var4) == null) {
            return true;
         } else {
            System.currentTimeMillis();

            do {
               try {
                  this.pC.wait();
               } catch (InterruptedException var7) {
                  var2.requestInterruption();
                  return false;
               }

               var4 = System.currentTimeMillis();
            } while (this.pC.putIfAbsent(var1, var4) != null);

            return true;
         }
      }
   }

   private void ys(String var1) {
      synchronized (this.pC) {
         if (this.pC.remove(var1) == null) {
            throw new RuntimeException("Unexpected! Cannot release unlocked item: " + var1);
         } else {
            this.pC.notifyAll();
         }
      }
   }

   private Set pC(Set var1, DecompilationContext var2) {
      synchronized (this.pC) {
         HashSet var4 = new HashSet(var1.size());
         long var5 = System.currentTimeMillis();

         for (String var8 : var1) {
            if (this.pC.putIfAbsent(var8, var5) == null) {
               var4.add(var8);
            }
         }

         return var4;
      }
   }

   private void pC(Set var1) {
      synchronized (this.pC) {
         for (String var4 : var1) {
            if (this.pC.remove(var4) == null) {
               throw new RuntimeException("Unexpected! Cannot release unlocked item: " + var4);
            }
         }

         this.pC.notifyAll();
      }
   }

   private boolean kS(String var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         IProgressCallback var3 = var2.getCallback();
         bfu var4 = this.WR.wS(var1);
         if (var4 != null && var4.isInternal()) {
            var1 = var4.getSignature(false);
            boolean var5 = (var2.getOptions().getFlags() & 8) != 0;
            boolean var6 = (var2.getOptions().getFlags() & 16) != 0;
            if (var6) {
               var5 = true;
            }

            bjr var7 = null;
            if (!var5) {
               var7 = this.UT(var1);
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
            } else if (!this.A(var1, var2)) {
               return false;
            } else if (!var5 && var7.isBuilt()) {
               this.ys(var1);
               return true;
            } else {
               bpx var8 = null;
               if (var6) {
                  this.wS.A(var1);
               } else {
                  var8 = this.wS.pC(var1);
               }

               boolean var9 = var8 == null;
               if (var7 != null) {
                  var7.pC(1, S.L("Waiting for decompilation..."));
               }

               byte var10 = 0;
               this.kS.acquireUninterruptibly();
               long var11 = System.currentTimeMillis();
               this.A.put(var1, System.currentTimeMillis());
               DecompilationResult var13 = new DecompilationResult();
               DeferredRequestsCollector var14 = new DeferredRequestsCollector();

               try {
                  fI.debug(S.L("Decompiling method: %s (%s)"), var1, var7 == null ? S.L("temporary") : S.L("stored"));
                  bgd var16 = var4.A();
                  if (var16.pC() == null) {
                     if (!var5) {
                        var7 = this.NS.E().A(var1);
                     }
                  } else {
                     var13.nsize = var16.pC().getInstructions().size();
                     var13.bsize = var16.pC().getInstructionsSize();
                     Long var55 = var2.getMaxTimePerMethod();
                     if (var55 == null) {
                        var55 = (long)this.ED;
                     }

                     Watchdog var60 = new Watchdog(var55);
                     if (var9) {
                        System.currentTimeMillis();
                        this.clearSpecialComments(var1);
                        var8 = this.pC((IDexMethod)var4);
                        this.UT.put(var1, var8);
                        Integer var64 = (Integer)this.E.merge(var1, 1, (var0, var1x) -> var1x + 1);
                        if (var64 >= 2) {
                           Object[] var10000 = new Object[]{var1};
                        }

                        this.sY.incrementAndGet();

                        try {
                           var8.pC(var60);
                           var8.pC(var2.getOptions().getFlags());
                           var8.A();
                           brk var66 = this.pC(var8);
                           this.pC(var66, var2);
                           var66.perform();
                           brx var23 = new brx(var8);
                           var23.pC();
                           DUtil.normalizeGraph(var8);
                           cct var24 = new cct(var8);
                           var24.pC();
                        } finally {
                           this.UT.remove(var1);
                        }
                     }

                     if (var8 != null) {
                        this.wS.pC(var1, var8);
                     }

                     if (var7 != null) {
                        bhl var62 = new bhl(this.NS, var8);
                        var62.pC(this.getPropertyManager().getInteger("StructurerUseVersion"));
                        var7 = var62.A();
                        var7.pC(0);
                        bhs var63 = this.pC(var7, var14, var60);
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
                     List var56 = this.ld(var16.getMethodIndex());
                     var7.wS(var56);
                     List var61 = this.gp(var16.getMethodIndex());
                     var7.UT(var61);
                     if (var7 != null) {
                        var7.pC(var10);
                     }
                  }

                  long var57 = System.currentTimeMillis() - var11;
                  var13.time1 = var57;
                  var13.speedi1 = (double)var13.nsize / var57;
               } catch (Exception var50) {
                  boolean var17 = true;
                  this.ys.incrementAndGet();
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
                     } else if (var22 == null || bqp.pC(var22.getOpcode())) {
                        var21 = 2;
                        var17 = false;
                     }
                  } else if (var18 instanceof DemoLimitationException) {
                     var21 = 2;
                     var17 = false;
                  } else if (var18 instanceof OperationTimedOutException) {
                     this.ld.incrementAndGet();
                  }

                  if (var4 != null && var4.A() != null && var4.A().pC() != null && var4.A().pC().hasParsingErrors()) {
                     var20 = S.L("Bytecode errors may have prevented the decompilation of this method");
                     var17 = false;
                  }

                  if (var7 != null) {
                     var7.pC(var21, Licensing.isReleaseBuild() ? var20 : var19);
                  }

                  var2.recordError(var1, var19);
                  var13.error = var19;
                  if (var17) {
                     if (var18 instanceof OperationTimedOutException) {
                        fI.error(S.L("%s: decompilation timed-out: %s"), var1, var18.getMessage());
                     } else {
                        fI.error(S.L("%s: decompilation error: %s"), var1, var18);
                     }

                     rQ.pC(var50, var1);
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

                  this.kS.release();
                  this.A.remove(var1);
                  this.ys(var1);
               }

               int var54 = 0;
               if (!this.oT && var7 != null && !var2.hasFlags(2) && !var14.isEmpty()) {
                  if ((var54 = this.pC(var14, var2)) > 0 && this.A(var1, var2)) {
                     try {
                        var7.markNotBuilt();
                        bhs var58 = this.pC(var7, null, null);
                        var58.perform();
                        var7.markBuilt();
                     } catch (Exception var47) {
                        throw var47;
                     } finally {
                        this.ys(var1);
                     }
                  }
               } else {
                  var14.clear();
               }

               long var59 = System.currentTimeMillis() - var11;
               var13.time2 = var59;
               var13.speedi2 = (double)var13.nsize / var59;
               Object[] var70 = new Object[]{var1, rQ.pC(var59), var54};
               this.pC(var2, var1);
               return true;
            }
         } else {
            return false;
         }
      }
   }

   private int pC(DeferredRequestsCollector var1, DecompilationContext var2) {
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
                     if (var5.isEmpty() || !var2.recordAndCheckDeferredRequests(var5.size(), this.OI)) {
                        while (true) {
                           var5 = var1.pullRequestedClassDecompilations();
                           if (var5.isEmpty()) {
                              return var4;
                           }

                           for (String var16 : var5) {
                              if (!var2.recordAndCheckDeferredRequests(1, this.OI)) {
                                 break;
                              }

                              this.pC(var16, var2, 1);
                              var4++;
                           }
                        }
                     }

                     this.decompileMethods(var5, var2);
                     var4 += var5.size();
                  }
               }

               for (String var7 : var5) {
                  if (!var2.recordAndCheckDeferredRequests(1, this.OI)) {
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
         return this.pC(var1, var2);
      } catch (Exception var4) {
         fI.catchingSilent(var4);
         return false;
      }
   }

   public boolean pC(String var1, DecompilationContext var2) throws Exception {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         int var3 = var2.getFlags();
         IProgressCallback var4 = var2.getCallback();
         bfu var5 = this.WR.fI().pC(var1);
         if (var5 == null) {
            return false;
         } else {
            var1 = var5.getSignature(false);
            bjr var6 = this.UT(var1);
            Boolean var7 = this.pC(var6, var3);
            if (var7 != null) {
               return var7;
            } else {
               if (var4 != null && !var4.isInitialized()) {
                  this.pC(var2, this.pC(var5, var3, null));
               }

               Ws.Sv var8 = new Ws.Sv(var1, var2);
               if (!var8.pC()) {
                  return false;
               } else {
                  if ((var3 & 1) == 0) {
                     for (IDexClass var11 : DexUtil.getMemberClasses(this.WR, var5)) {
                        this.decompileClass(var11.getSignature(false), var2);
                     }
                  }

                  return true;
               }
            }
         }
      }
   }

   private Boolean pC(bjr var1, int var2) {
      if (var1 != null && !var1.isExternal()) {
         if (var1.isBuilt()) {
            if ((var2 & 1) != 0) {
               return true;
            }

            if (var1.A()) {
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
      return this.wS(var1, var2);
   }

   private boolean wS(String var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         var2.getCallback();
         bft var3 = this.WR.oT().pC(var1);
         if (var3 == null) {
            return false;
         } else {
            var1 = var3.getSignature(false);
            bjq var4 = this.wS(var1);
            if (var4 == null || var4.isExternal()) {
               return false;
            } else if (var4.isBuilt()) {
               return true;
            } else if (!this.A(var1, var2)) {
               return false;
            } else if (var4.isBuilt()) {
               this.ys(var1);
               return true;
            } else {
               try {
                  IDexValue var5 = this.WR.getStaticFieldInitializer(var3.getIndex());
                  if (var5 != null) {
                     IJavaExpression var6 = this.NS.ys.pC(var5);
                     var4.pC(var6);
                  }

                  List var12 = this.ys(var3.getIndex());
                  var4.wS(var12);
                  var4.markBuilt();
               } finally {
                  this.ys(var1);
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
         bfs var3 = this.WR.WR().pC(var1);
         return var3 == null ? false : this.pC(var3.getSignature(false), var2, 2);
      }
   }

   private boolean pC(String var1, DecompilationContext var2, int var3) {
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         bfs var4 = this.WR.WR().pC(var1);
         if (var4 == null) {
            return false;
         } else {
            var1 = var4.getSignature(false);
            int var5 = var2.getFlags();
            IProgressCallback var6 = var2.getCallback();
            if (var3 != 0) {
               bjl var7 = this.kS(var1);
               Boolean var8 = this.pC(var7, var5);
               if (var8 != null) {
                  return var8;
               }

               ArrayList var9 = new ArrayList();
               this.pC(var4, var5, var9);
               ArrayList var10 = new ArrayList(var9.size());
               var9.forEach(var1x -> var10.add(var1x.getSignature(false)));
               if (var3 == 1 || !this.JF) {
                  this.decompileMethods(var10, var2);
               } else if (var3 == 2) {
                  DecompilationOptions var11 = var2.addFlags(72);

                  try {
                     this.decompileMethods(var10, var2);
                  } finally {
                     var2.setOptions(var11);
                  }

                  Set var12 = this.pC(new HashSet(var10), var2);
                  if (var12 != null && !var12.isEmpty()) {
                     try {
                        ArrayList var13 = new ArrayList(var12.size());

                        for (String var15 : var12) {
                           bpx var16 = this.wS.pC(var15);
                           if (var16 != null) {
                              var13.add(var16);
                           }
                        }

                        brk var75 = this.pC(null, var13, true, true, true);
                        int var77 = var75.perform();
                        if (var77 > 0) {
                           Map var79 = var75.pC();

                           for (IDMethodContext var18 : var79.keySet()) {
                              brk var19 = this.pC(var18);
                              var19.perform();
                           }
                        }
                     } finally {
                        this.pC(var12);
                     }
                  }

                  this.decompileMethods(var10, var2);
               }
            }

            bjl var54 = this.kS(var1);
            bfp var56 = var4.sY();
            if (var56 != null) {
               for (bfz var61 : var56.pC()) {
                  String var68 = this.WR.E(var61.getFieldIndex()).getSignature(false);

                  try {
                     this.wS(var68, var2);
                  } catch (Exception var49) {
                     fI.catchingSilent(var49);
                  }
               }

               for (bgd var62 : var56.kS()) {
                  String var69 = this.WR.sY(var62.getMethodIndex()).getSignature(false);

                  try {
                     this.UT(var69, var2);
                  } catch (Exception var48) {
                     fI.catchingSilent(var48);
                  }
               }
            }

            if (!var2.hasFlags(1)) {
               for (IDexClass var70 : DexUtil.getMemberClasses(this.WR, var4)) {
                  this.pC(var70.getSignature(false), var2, 0);
               }
            }

            DeferredRequestsCollector var60 = new DeferredRequestsCollector();
            if (!this.A(var1, var2)) {
               return false;
            } else {
               try {
                  if (var54.isBuilt()) {
                     return true;
                  }

                  var54 = this.NS.UT.A(var1);
                  var54.markNotBuilt();
                  if (var4.isAnnotation()) {
                     Map var64 = var4.getAnnotationDefaults();
                     if (var64 != null && !var64.isEmpty()) {
                        for (IJavaMethod var73 : var54.getMethods()) {
                           String var74 = var73.getName();
                           IDexValue var76 = (IDexValue)var64.get(var74);
                           if (var76 != null) {
                              IJavaExpression var78 = this.NS.ys.pC(var76);
                              var73.setDefaultValue(var78);
                           }
                        }
                     }
                  }

                  List var65 = this.oT(var4.getClassTypeIndex());
                  var54.wS(var65);
                  bhs var72 = this.pC(var54, var60, null);
                  var72.perform();
                  var54.markBuilt();
               } finally {
                  this.ys(var1);
               }

               if (!this.oT && var54 != null && !var2.hasFlags(2) && !var60.isEmpty() && this.pC(var60, var2) > 0) {
                  if (!this.A(var1, var2)) {
                     return false;
                  }

                  try {
                     var54.markNotBuilt();
                     bhs var66 = this.pC(var54, null, null);
                     var66.perform();
                     var54.markBuilt();
                  } finally {
                     this.ys(var1);
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

   private boolean UT(String var1, DecompilationContext var2) throws Exception {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return false;
      } else {
         int var3 = var2.getFlags();
         IProgressCallback var4 = var2.getCallback();
         bfu var5 = this.WR.fI().pC(var1);
         if (var5 == null) {
            return false;
         } else {
            var1 = var5.getSignature(false);
            bjr var6 = this.UT(var1);
            Boolean var7 = this.pC(var6, var3);
            if (var7 != null) {
               return var7;
            } else {
               if (var4 != null && !var4.isInitialized()) {
                  this.pC(var2, this.pC(var5, var3, null));
               }

               Ws.Sv var8 = new Ws.Sv(var1, var2);
               if (!var8.pC()) {
                  return false;
               } else {
                  if ((var3 & 1) == 0) {
                     for (IDexClass var11 : DexUtil.getMemberClasses(this.WR, var5)) {
                        this.pC(var11.getSignature(false), var2, 0);
                     }
                  }

                  return true;
               }
            }
         }
      }
   }

   private Boolean pC(bjl var1, int var2) {
      if (var1 != null && !var1.isExternal()) {
         if (var1.isBuilt()) {
            if ((var2 & 1) != 0) {
               return true;
            }

            if (var1.A()) {
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
            this.pC(var2, var1.size());
         }

         int var7 = 0;
         if ((var4 & 4) == 0 && this.getThreadPoolSize() != 1) {
            ArrayList var23 = new ArrayList(var1.size());
            var1.forEach(var3x -> var23.add(new Ws.Sv(var3x, var2.fork())));
            ExecutorService var24 = (ExecutorService)this.KV.kS();

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
                  Ws.Sv var14 = (Ws.Sv)var23.get(var11);
                  if (!var13.isDone() || var13.isCancelled() || !var14.kS || var14.wS != null) {
                     if (!var13.isDone() || var13.isCancelled()) {
                        fI.debug(S.L("%s: decompilation did not finish"), var14.pC);
                        if (var2.getError(var14.pC) == null) {
                           var2.recordError(var14.pC, S.L("INCOMPLETE"));
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
               this.KV.A(var24);
            }

            return (boolean)var11;
         } else {
            for (String var9 : var1) {
               if (var6.isInterruptionRequested()) {
                  return false;
               }

               Ws.Sv var10 = new Ws.Sv(var9, var6);

               try {
                  if (!var10.pC()) {
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
            bfs var13 = this.WR.gp(var12);
            if (var13 != null) {
               this.pC(var13, var4, var10);
            }
         }

         if (var3 != null && !var3.isInitialized()) {
            this.pC(var2, var10.size());
         }

         boolean var25 = false;
         if (var25) {
            ArrayList var26 = new ArrayList(var10.size());
            var10.forEach(var1x -> var26.add(var1x.getSignature(false)));
            if (!this.decompileMethods(var26, var2)) {
               var8++;
            }

            long var30 = System.currentTimeMillis();
            fI.debug(S.L("(%d methods decompiled in %d ms)\n"), var26.size(), var30 - var6);
            if (var9 != null) {
               var9 = var9 - (var30 - var6);
            }
         }

         label173:
         if ((var4 & 4) == 0 && this.getThreadPoolSize() != 1) {
            ArrayList var28 = new ArrayList(var1.size());
            var1.forEach(var3x -> var28.add(new Ws.Av(var3x, var2.fork())));
            ExecutorService var32 = (ExecutorService)this.Xs.kS();

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
                  Ws.Av var18 = (Ws.Av)var28.get(var15);
                  if (!var17.isDone() || var17.isCancelled() || !var18.wS || var18.UT != null) {
                     if (!var17.isDone() || var17.isCancelled()) {
                        fI.debug(S.L("%s: decompilation did not finish"), var18.pC);
                        if (var2.getError(var18.pC) == null) {
                           var2.recordError(var18.pC, S.L("INCOMPLETE"));
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
               this.Xs.A(var32);
            }

            return (boolean)var15;
         } else {
            for (String var31 : var1) {
               if (var5.isInterruptionRequested()) {
                  return false;
               }

               Ws.Av var14 = new Ws.Av(var31, var2);
               if (!var14.pC()) {
                  var8++;
               }
            }
         }

         long var29 = System.currentTimeMillis();
         fI.debug(S.L("(%d classes constructed in %d ms)\n"), var1.size(), var29 - var6);
         return var8 == 0;
      }
   }

   @Override
   public boolean decompileAllClasses(DecompilationContext var1) {
      ArrayList var2 = new ArrayList();
      this.WR.getClasses().forEach(var1x -> var2.add(var1x.getSignature(false)));
      return this.decompileClasses(var2, var1);
   }

   @Override
   public boolean decompileAllMethods(DecompilationContext var1) {
      ArrayList var2 = new ArrayList();

      for (bfu var4 : this.WR.getMethods()) {
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
            bfu var8 = this.WR.wS(var7);
            if (var8 != null) {
               var4.add(var8.getSignature(false));
            } else {
               var3 = false;
            }
         } else {
            bfs var11 = this.WR.gp(var7);
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
         this.pC(var2, var4.size() + var5.size());
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
         this.wS = new yt(var1, var2);
      } else {
         this.wS = new KD();
      }
   }

   @Override
   public void clearCachedIRs() {
      this.wS.A();
   }

   @Override
   public int getCountOfCachedIRs() {
      return this.wS.pC();
   }

   @Override
   public IDMethodContext retrieveCachedIR(String var1) {
      return this.wS.pC(var1);
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
      if (this.ZN != null && !this.ZN) {
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
            bjr var5 = this.NS.E().pC(var3);
            this.resetMethodElement(var5);
         } else {
            int var9 = var1.indexOf("->");
            if (var9 >= 0) {
               var3 = var1.substring(0, var9);
            }

            var3 = var3.trim();
            bjl var11 = this.NS.UT().pC(var3);
            if (var11 == null) {
               return;
            }

            this.resetClassElement(var11);
         }
      } else {
         bfs var10 = this.E(var1);
         if (var10 == null) {
            return;
         }

         var3 = var10.getSignature(false);
         bjl var12 = this.NS.UT().pC(var3);
         if (var12 == null) {
            return;
         }

         this.pC((IJavaClass)var12);
      }
   }

   private synchronized void pC(IJavaClass var1) {
      for (IJavaClass var3 : var1.getInnerClasses()) {
         this.pC(var3);
      }

      for (IJavaClass var7 : var1.getAnonymousClasses()) {
         this.pC(var7);
      }

      for (IJavaMethod var8 : var1.getMethods()) {
         this.pC(var8);
      }

      for (IJavaField var9 : var1.getFields()) {
         this.pC(var9);
      }

      this.resetClassElement(var1);
   }

   private synchronized void pC(IJavaMethod var1) {
      for (IJavaClass var3 : var1.getInnerClasses()) {
         this.pC(var3);
      }

      for (IJavaClass var5 : var1.getAnonymousClasses()) {
         this.pC(var5);
      }

      this.resetMethodElement(var1);
   }

   private synchronized void pC(IJavaField var1) {
      this.resetFieldElement(var1);
   }

   @Override
   public void runGarbageCollection() {
      this.gp++;
      this.removeFreeElements();
      this.wS.A();
   }

   @Override
   public void removeFreeElements() {
      this.NS.E.kS();
      this.NS.sY.pC();
      this.NS.UT.pC();
   }

   @Override
   public boolean resetClassElement(IJavaClass var1) {
      boolean var2 = this.NS.UT.kS(var1.getSignature());
      if (var2) {
         bhv var3 = null;

         for (IUnit var5 : this.getChildren()) {
            if (var5 instanceof bhv && ((bhv)var5).getASTElement() == var1) {
               var3 = (bhv)var5;
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
      boolean var2 = this.NS.sY.kS(var1.getSignature());
      if (var2) {
         bhv var3 = null;

         for (IUnit var5 : this.getChildren()) {
            if (var5 instanceof bhv && ((bhv)var5).getASTElement() == var1) {
               var3 = (bhv)var5;
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
         this.wS.A(var2);
         boolean var3 = this.NS.E.kS(var2);
         if (var3) {
            bhv var4 = null;

            for (IUnit var6 : this.getChildren()) {
               if (var6 instanceof bhv && ((bhv)var6).getASTElement() == var1) {
                  var4 = (bhv)var6;
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

   private List ys(int var1) {
      ArrayList var2 = new ArrayList();
      if (this.NS.ys != null) {
         bft var3 = this.WR.E(var1);
         bfs var4 = this.WR.WR().pC(var3.wS().getSignature(false));
         IDexAnnotationsDirectory var5 = var4.getAnnotationsDirectory();
         if (var5 != null) {
            for (IDexAnnotationForField var7 : var5.getFieldsAnnotations()) {
               if (var7.getFieldIndex() == var1) {
                  for (IDexAnnotationItem var9 : var7.getAnnotationItems()) {
                     if (!var9.isSystemLevelAnnotation()) {
                        IJavaAnnotation var10 = this.pC(var9.getAnnotation());
                        var2.add(var10);
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   private List ld(int var1) {
      ArrayList var2 = new ArrayList();
      if (this.NS.ys != null) {
         bfu var3 = this.WR.sY(var1);
         bfs var4 = this.WR.WR().pC(var3.getClassTypeSignature(false));
         IDexAnnotationsDirectory var5 = var4.getAnnotationsDirectory();
         if (var5 != null) {
            for (IDexAnnotationForMethod var7 : var5.getMethodsAnnotations()) {
               if (var7.getMethodIndex() == var1) {
                  for (IDexAnnotationItem var9 : var7.getAnnotationItems()) {
                     if (!var9.isSystemLevelAnnotation()) {
                        IJavaAnnotation var10 = this.pC(var9.getAnnotation());
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

   private List gp(int var1) {
      ArrayList var2 = new ArrayList();
      if (this.NS.ys != null) {
         bfu var3 = this.WR.sY(var1);
         bfs var4 = this.WR.WR().pC(var3.getClassTypeSignature(false));
         IDexAnnotationsDirectory var5 = var4.getAnnotationsDirectory();
         if (var5 != null) {
            for (IDexAnnotationForParameter var7 : var5.getParametersAnnotations()) {
               if (var7.getMethodIndex() == var1) {
                  for (List var9 : var7.getAnnotationItemSets()) {
                     ArrayList var10 = new ArrayList();
                     if (var9 != null) {
                        for (IDexAnnotationItem var12 : var9) {
                           if (!var12.isSystemLevelAnnotation()) {
                              IJavaAnnotation var13 = this.pC(var12.getAnnotation());
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

   private List oT(int var1) {
      ArrayList var2 = new ArrayList();
      if (this.NS.ys != null) {
         bfs var3 = this.WR.wS(var1).A();
         IDexAnnotationsDirectory var4 = var3.getAnnotationsDirectory();
         if (var4 != null) {
            for (IDexAnnotationItem var6 : var4.getClassAnnotations()) {
               if (!var6.isSystemLevelAnnotation()) {
                  IJavaAnnotation var7 = this.pC(var6.getAnnotation());
                  var2.add(var7);
               }
            }
         }
      }

      return var2;
   }

   public IJavaAnnotation pC(IDexAnnotation var1) {
      bic var2 = this.A();
      int var3 = var1.getTypeIndex();
      IJavaType var4 = this.NS.A.pC(this.WR.pC(var3, false));
      ArrayList var5 = new ArrayList();

      for (IDexAnnotationElement var7 : var1.getElements()) {
         IJavaConstant var8 = this.NS.wS.createString(this.WR.A(var7.getNameIndex()));
         IJavaExpression var9 = this.NS.ys.pC(var7.getValue());
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
      if (this.ZD == null) {
         this.ZD = new com.pnfsoftware.jeb.corei.parsers.dexdec.Sv(this);
      }

      var1.add(this.ZD);
      var1.addAll(((IUnit)this.getParent()).getContributions());
      return var1;
   }

   @Override
   public boolean setIdentifierName(IJavaIdentifier var1, String var2) {
      return this.setIdentifierName(var1, var2, true, true);
   }

   @Override
   public boolean setIdentifierName(IJavaIdentifier var1, String var2, boolean var3, boolean var4) {
      bja var5 = (bja)var1.getDefinition();
      if (var5 == null) {
         return false;
      } else {
         IdentifierCoordinates var6 = var5.getCoordinates();
         return this.pC().pC(var6, var2, var3, var4) >= 1;
      }
   }

   @Override
   public String getIdentifierName(IJavaIdentifier var1) {
      bja var2 = (bja)var1.getDefinition();
      if (var2 == null) {
         return null;
      } else {
         IdentifierCoordinates var3 = var2.getCoordinates();
         return this.pC().pC(var3);
      }
   }

   @Override
   public void registerEventQueue(DexDecompilerEventQueue var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.DL.add(var1);
      }
   }

   @Override
   public void unregisterEventQueue(DexDecompilerEventQueue var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.DL.remove(var1);
      }
   }

   @Override
   public void recordDecompilationEvent(DexDecompilerEvent var1) {
      for (DexDecompilerEventQueue var3 : this.DL) {
         var3.add(var1);
      }
   }

   @Override
   public Collection getGlobalDecompilationEvents(boolean var1) {
      return (Collection)(var1 ? new ArrayList(this.cX.readAll()) : new LinkedHashSet(this.cX.readAll()));
   }

   @Override
   public Collection getGlobalDecompilationEvents() {
      return this.getGlobalDecompilationEvents(false);
   }

   @Override
   public void resetGlobalDecompilationEvents() {
      this.cX.clear();
   }

   public DexDecompilerExporter NS() {
      return new DexDecompilerExporter(this);
   }

   public List vP() {
      return this.PZ;
   }

   @Override
   public IEmulatedAndroid createEmulatedAndroid() {
      return new bhg(this);
   }

   @Override
   public IGenericUnpacker createGenericUnpacker() {
      return new bhh(this);
   }

   private void pC(DecompilationContext var1, int var2) {
      IProgressCallback var3 = var1.getCallback();
      if (var3 != null) {
         if (!var3.isInitialized()) {
            var3.setTotal(var2);
         }
      }
   }

   private void pC(DecompilationContext var1, String var2) {
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

   private void pC(brk var1, DecompilationContext var2) {
      var1.setPolicyForOptimizerTag("reorderer", true);
   }

   public boolean pC(IDexItem var1) {
      return this.pC(var1, null);
   }

   public boolean pC(IDexItem var1, String[] var2) {
      if (this.go == null) {
         return false;
      } else {
         String var3 = (String)this.go.get(var1.getAddress(false));
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

   public boolean pC(IDexItem var1, boolean var2) {
      return this.pC(var1, var2, null);
   }

   public boolean pC(IDexItem var1, boolean var2, String var3) {
      if (var1 == null) {
         return false;
      } else {
         if (this.go == null) {
            if (!var2) {
               return false;
            }

            synchronized (this) {
               if (this.go == null) {
                  this.go = new ConcurrentHashMap();
               }
            }
         }

         String var4 = var1.getAddress(false);
         if (!var2) {
            String var9 = (String)this.go.get(var4);
            if (var9 == null || var9.startsWith("//-TEMP-UNCOLL-//")) {
               return false;
            } else if (var3 != null) {
               return this.go.remove(var4) != null;
            } else {
               this.go.put(var4, "//-TEMP-UNCOLL-//" + var9);
               return true;
            }
         } else {
            String var8;
            if (var3 == null) {
               var8 = (String)this.go.get(var4);
               if (var8 != null && var8.startsWith("//-TEMP-UNCOLL-//")) {
                  var8 = var8.substring("//-TEMP-UNCOLL-//".length());
               } else {
                  var8 = "";
               }
            } else {
               var8 = var3;
            }

            String var6 = (String)this.go.put(var4, var8);
            return var6 == null || var6.startsWith("//-TEMP-UNCOLL-//");
         }
      }
   }

   private class Av implements Callable {
      String pC;
      DecompilationContext A;
      Consumer kS;
      boolean wS;
      Exception UT;

      public Av(String var2, DecompilationContext var3) {
         this.pC = var2;
         this.A = var3;
         this.kS = var3.getOptions().getPostDecompilationCallback();
      }

      public Boolean pC() {
         try {
            this.wS = Ws.this.decompileClass(this.pC, this.A);
            if (this.kS != null) {
               this.kS.accept(this.pC);
            }

            return this.wS;
         } catch (Exception var2) {
            this.UT = var2;
            throw var2;
         }
      }
   }

   private class Sv implements Callable {
      String pC;
      DecompilationContext A;
      boolean kS;
      Exception wS;

      Sv(String var2, DecompilationContext var3) {
         this.pC = var2;
         this.A = DecompilationContext.safe(var3);
      }

      public Boolean pC() throws Exception {
         try {
            this.kS = Ws.this.kS(this.pC, this.A);
            return this.kS;
         } catch (Exception var2) {
            this.wS = var2;
            throw var2;
         }
      }
   }
}
