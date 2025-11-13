package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugInfo;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugLine;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugVariable;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.DExecutionParameters;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecConversionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDElement;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTypeInfoProvider;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.concurrent.WeakConcurrentSet;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Integers;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedDeque;

@SerDisabled
public class bud implements IDMethodContext {
   private static final ILogger xK = GlobalLog.getLogger(bud.class);
   public final long q = System.currentTimeMillis();
   private IDGlobalContext Dw;
   private IDexMethod Uv;
   private IDMethodContext oW;
   private Watchdog gO;
   private int nf;
   private boolean gP = true;
   private boolean za = true;
   private boolean lm;
   private SortedMap zz;
   private Set JY;
   private Map HF;
   private Map LK;
   private CFG io;
   private IDTryData qa;
   private int Hk;
   private SortedMap Me = new TreeMap();
   private List PV = new ArrayList();
   private List oQ = new ArrayList();
   private Set xW;
   private Map KT;
   private int Gf;
   private Map Ef = new HashMap();
   public Deque RF = new ConcurrentLinkedDeque();
   private volatile WeakConcurrentSet cC;
   private volatile WeakConcurrentSet sH;

   public bud(IDGlobalContext var1, IDexMethod var2) {
      if (var1 != null && var2 != null) {
         this.Dw = var1;
         this.Uv = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IDMethodContext copy() {
      bud var1 = new bud(this.Dw, this.Uv);
      var1.oW = this.oW;
      var1.gO = this.gO;
      var1.nf = this.nf;
      var1.gP = this.gP;
      var1.za = this.za;
      var1.lm = this.lm;
      var1.zz = this.zz;
      var1.JY = this.JY;
      var1.HF = this.HF;
      var1.LK = this.LK;
      var1.io = DUtil.copyGraph(this.io, true, var1);
      var1.qa = this.qa.copy();
      var1.Hk = this.Hk;
      var1.Me = new TreeMap(this.Me);
      var1.PV = new ArrayList(this.PV);
      var1.oQ = new ArrayList(this.oQ);
      var1.Gf = this.Gf;
      var1.Ef = new HashMap(this.Ef);
      this.RF(var1);
      return var1;
   }

   @Override
   public void load(IDMethodContext var1) {
      bud var2 = (bud)var1;
      this.Dw = var2.Dw;
      this.Uv = var2.Uv;
      this.oW = var2.oW;
      this.gO = var2.gO;
      this.nf = var2.nf;
      this.gP = var2.gP;
      this.za = var2.za;
      this.lm = var2.lm;
      this.zz = var2.zz;
      this.JY = var2.JY;
      this.HF = var2.HF;
      this.LK = var2.LK;
      this.io = var2.io;
      this.io.setVariableInformationProvider(this);

      for (IDInstruction var4 : this.io.instructions()) {
         var4.setContext(this);
      }

      this.io.invalidateDataFlowAnalysis();
      this.qa = var2.qa;
      this.Hk = var2.Hk;
      this.Me = var2.Me;
      this.PV = var2.PV;
      this.oQ = var2.oQ;
      this.Gf = var2.Gf;
      this.Ef = var2.Ef;
   }

   public void q(Watchdog var1) {
      this.gO = var1;
   }

   @Override
   public Watchdog getWatchdog() {
      return this.gO;
   }

   public void q(int var1) {
      this.nf = var1;
   }

   @Override
   public int getDecompilationFlags() {
      return this.nf;
   }

   public void q(boolean var1) {
      this.gP = var1;
   }

   @Override
   public boolean isParseExceptions() {
      return this.gP;
   }

   public void RF(boolean var1) {
      this.za = var1;
   }

   @Override
   public boolean isParseDebugInfo() {
      return this.za;
   }

   @Override
   public CFG getCfg() {
      return this.io;
   }

   @Override
   public IDTryData getExceptionData() {
      return this.qa;
   }

   public Set q() {
      return Collections.unmodifiableSet(this.JY);
   }

   @Override
   public IDGlobalContext getGlobalContext() {
      return this.Dw;
   }

   @Override
   public IDMethodContext getParentContext() {
      return this.oW;
   }

   public void q(IDMethodContext var1) {
      if (this.oW != null) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         this.oW = var1;
         if (this.oW != null) {
            ((bud)this.oW).q(this);
         }
      }
   }

   private void q(bud var1) {
      Assert.a(var1 != null);
      Assert.a(var1 != this);
      if (this.cC == null) {
         this.cC = new WeakConcurrentSet(WeakConcurrentSet.Cleaner.INLINE);
      }

      this.cC.add(var1);
   }

   @Override
   public List getChildrenContexts() {
      ArrayList var1 = new ArrayList();
      if (this.cC != null) {
         for (bud var3 : this.cC) {
            if (var3 != null) {
               var1.add(var3);
            }
         }
      }

      return var1;
   }

   private void RF(bud var1) {
      Assert.a(var1 != null);
      Assert.a(var1 != this);
      if (this.sH == null) {
         this.sH = new WeakConcurrentSet(WeakConcurrentSet.Cleaner.INLINE);
      }

      this.sH.add(var1);
   }

   @Override
   public List getCopiesContexts() {
      ArrayList var1 = new ArrayList();
      if (this.sH != null) {
         for (bud var3 : this.sH) {
            if (var3 != null) {
               var1.add(var3);
            }
         }
      }

      return var1;
   }

   @Override
   public IDexUnit getDex() {
      return this.Dw.getDex();
   }

   public int RF() {
      return this.Uv.getIndex();
   }

   @Override
   public IDexMethod getMethod() {
      return this.Uv;
   }

   @Override
   public String getMethodSignature() {
      return this.Uv.getSignature(false);
   }

   @Override
   public IJavaTypeFactory getTypeFactory() {
      return this.Dw.getTypeFactory();
   }

   @Override
   public IJavaOperatorFactory getOperatorFactory() {
      return this.Dw.getOperatorFactory();
   }

   @Override
   public IDTypeInfoProvider getTypeInfoProvider() {
      return this.Dw.getTypeInfoProvider();
   }

   @Override
   public SortedMap getParametersTypeMap() {
      return this.zz;
   }

   public buv xK() {
      return new buv(this);
   }

   public void Dw() throws DexDecConversionException {
      if (this.lm) {
         throw new IllegalStateException();
      } else {
         this.Uv();
         this.oW();
         this.nf();
         this.lm = true;
      }
   }

   @Override
   public void verify() throws IllegalStateException {
      DUtil.verifyGraph(this);
   }

   public void Uv() throws DexDecConversionException {
      if (this.io == null) {
         if (this.getMethodSignature().equals("LSIMUFAILURE;->jebTestProcessThisMethodAndFail2001()V")) {
            throw new RuntimeException("Intentional failure!");
         } else {
            buv var1 = this.xK();
            var1.q(this.gP, this.za, true);
            this.HF = var1.xK();
            this.LK = var1.Uv();
            this.JY = var1.Dw();
            List var2 = var1.q();
            List var3 = var1.RF();
            this.io = new CFG(var2, var3);
            this.io.setVariableInformationProvider(this);
            if (this.gP) {
               TreeSet var4 = new TreeSet();

               for (BasicBlock var6 : this.io) {
                  var4.add((int)var6.getFirstAddress());
               }

               bix var7 = new bix(this.Uv.getData().getCodeItem().getExceptionItems());
               this.qa = new btn(var7, var4, this.HF);
            } else {
               this.qa = new btn();
            }

            DUtil.removeGaps(this.io);
            DUtil.simplifyJCondsAndSwitches(this);
            this.zz = Collections.unmodifiableSortedMap(JavaTypeUtil.parseMethodParameters(this.getDex(), this.Uv, this.Dw.getTypeFactory()));
         }
      }
   }

   public void oW() {
      new cen().perform(this);
      long var1 = System.currentTimeMillis();

      try {
         this.makeSSA();
      } finally {
         long var6 = System.currentTimeMillis() - var1;
         Object[] var10000 = new Object[]{com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var6)};
      }

      this.gO();
   }

   @Override
   public void makeSSA() {
      bwg var1 = new bwg(this);
      var1.RF(true);
      var1.q();
      this.Hk++;
   }

   @Override
   public boolean isSSA() {
      return this.Hk > 0;
   }

   void gO() {
      if (this.isParseDebugInfo()) {
         IDexDebugInfo var1 = this.getMethod().getData().getCodeItem().getDebugInfo();
         if (var1 != null) {
            HashMap var2 = new HashMap();

            for (IDInstruction var4 : this.io.instructions()) {
               int var5 = (int)var4.getOffsetEnd();
               Integer var6 = (Integer)this.LK.get(var5);
               if (var6 != null) {
                  IDexDebugLine var7 = var1.getLineInfo(var6 / 2);
                  if (var7 != null) {
                     for (int var9 : var7.getVariablesEnd()) {
                        bud.eo var10 = (bud.eo)var2.get(var9);
                        if (var10 != null) {
                           var10.RF = false;
                        }
                     }

                     for (int var16 : var7.getVariablesRestart()) {
                        bud.eo var19 = (bud.eo)var2.get(var16);
                        if (var19 != null) {
                           var19.RF = true;
                        }
                     }

                     for (IDexDebugVariable var17 : var7.getVariables()) {
                        var2.put(var17.getRegister(), new bud.eo(var17));
                     }

                     IDVar var15 = var4.getDefinedVariable();
                     if (var15 != null) {
                        int var18 = var15.getId();
                        int var20 = this.retrievePhysicalRegisterId(var18);
                        bud.eo var11 = (bud.eo)var2.get(var20);
                        if (var11 != null && var11.RF) {
                           String var12 = DexUtil.getStringSafe(this.Dw.getDex(), var11.q.getNameIndex(), "");
                           if (var12.length() > 0) {
                              if (var15.getPreferredName() != null) {
                                 if (!var12.equals(var15.getPreferredName())) {
                                    Object[] var10000 = new Object[]{var15, var20, var18, var12};
                                 }
                              } else {
                                 var15.setPreferredName(var12);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void nf() {
      System.currentTimeMillis();
      this.propagateTypes();
      System.currentTimeMillis();
   }

   @Override
   public void propagateTypes() {
      bwk var1 = new bwk(this);
      var1.q(true);
      var1.RF();
   }

   @Override
   public void replace(CFG var1, IDTryData var2) {
      if (var1 != null && var2 != null) {
         var1.setVariableInformationProvider(this);
         this.io = var1;
         this.qa = var2;
      } else {
         throw new IllegalArgumentException("Null CFG");
      }
   }

   @Override
   public void replaceCFG(CFG var1, Map var2) {
      if (var2 == null) {
         var2 = new HashMap();
      }

      Integers.formatIntegerCollection(var2.keySet(), 16, "0x", "");
      Object[] var10000 = new Object[0];
      Integers.formatIntegerCollection(var2.values(), 16, "0x", "");
      var10000 = new Object[0];
      this.io = var1;
      this.qa.updateTargets((Map)var2);
   }

   public Map gP() {
      HashMap var1 = new HashMap();

      for (BasicBlock var3 : this.io.getExitBlocks()) {
         IDInstruction var4 = (IDInstruction)var3.getLast();
         if (var4.isReturn() && var4.getReturnExpression() instanceof IDVar var5) {
            var1.put(var4, var5);
         }
      }

      return var1;
   }

   @Override
   public SortedMap getVariableMap() {
      return Collections.unmodifiableSortedMap(this.Me);
   }

   @Override
   public boolean isStaticMethod() {
      return this.Uv.getData().isStatic();
   }

   @Override
   public List getParameterVariables() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.getParametersTypeMap().entrySet()) {
         int var4 = (Integer)var3.getKey();
         if (var4 != -1) {
            int var5 = this.retrievePhysicalRegisterId(var4);
            if (var5 < 0) {
               throw new RuntimeException();
            }

            IDVar var6 = this.getVar(var5);
            if (var6 == null) {
               var6 = this.createRegisterVar(var4, (IJavaType)var3.getValue());
            }

            var1.add(var6);
         }
      }

      return var1;
   }

   @Override
   public IDVar getVar(int var1) {
      return (IDVar)this.Me.get(var1);
   }

   @Override
   public IDVar getVar(String var1) {
      int var2 = DUtil.parseVarIdFromStandardName(var1, true);
      if (var2 == -1) {
         for (IDVar var4 : this.Me.values()) {
            if (var1.equals(var4.getPreferredName())) {
               return var4;
            }
         }
      }

      return (IDVar)this.Me.get(var2);
   }

   @Override
   public IDVar createVar(int var1) {
      IJavaType var2;
      if (DUtil.isSingleSlotVarId(var1)) {
         var2 = this.getTypeFactory().getSingleSlotWildcard();
      } else {
         if (!DUtil.isDoubleSlotVarId(var1)) {
            throw new RuntimeException();
         }

         var2 = this.getTypeFactory().getDoubleSlotWildcard();
      }

      return this.createVar(var1, var2, false);
   }

   @Override
   public IDVar createVar(int var1, IJavaType var2) {
      return this.createVar(var1, var2, false);
   }

   @Override
   public IDVar createVar(int var1, IJavaType var2, boolean var3) {
      Object var4 = (IDVar)this.Me.get(var1);
      if (var4 == null) {
         var4 = new bum(this, var1, var2);
         this.Me.put(var1, var4);
      } else if (var3 && !((IDVar)var4).getType().equals(var2)) {
         throw new IllegalArgumentException("Type of an already existing variable mismatches the requested type");
      }

      return (IDVar)var4;
   }

   @Override
   public Collection getVars() {
      return Collections.unmodifiableCollection(this.Me.values());
   }

   @Override
   public boolean removeVar(int var1) {
      IDVar var2 = (IDVar)this.Me.remove(var1);
      if (var2 == null) {
         return false;
      } else {
         Assert.a(var2.getId() == var1);
         if (var1 < 131072) {
            return true;
         } else {
            if (var1 < 589824) {
               int var3 = var1 - 131072;
               this.PV.set(var3, -1);
            } else {
               Assert.a(var1 < 1048576);
               int var5 = var1 - 589824;
               this.oQ.set(var5, -1);
            }

            if (this.xW != null && this.xW.remove(var2)) {
               for (List var4 : this.KT.values()) {
                  var4.remove(var2);
               }
            }

            return true;
         }
      }
   }

   @Override
   public void clearVirtualVars() {
      this.Me.subMap(131072, Integer.MAX_VALUE).clear();
      this.PV.clear();
      this.oQ.clear();
      this.xW.clear();
      this.KT.clear();
   }

   @Override
   public IDVar createRegisterVar(int var1, IJavaType var2) {
      int var3 = DUtil.createRegisterVarId(var1, var2.isDoubleSlot());
      return this.createVar(var3, var2);
   }

   @Override
   public IDVar createCopyVar(IDVar var1) {
      int var2 = this.RF(var1.getId());
      IDVar var3 = this.createVar(var2, var1.getType());
      var3.setPreferredName(var1.getPreferredName());
      return var3;
   }

   private int RF(int var1) {
      if (var1 >= 0 && var1 < 65536) {
         int var4 = this.PV.size();
         Assert.a(var4 < 458752);
         int var5 = 131072 + var4;
         this.PV.add(var1);
         return var5;
      } else if (var1 >= 65536 && var1 < 131071) {
         int var2 = this.oQ.size();
         Assert.a(var2 < 1048575);
         int var3 = 589824 + var2;
         this.oQ.add(var1);
         return var3;
      } else {
         throw new RuntimeException("Attempt to create a copy-var from a non-primary id: varid=" + var1);
      }
   }

   @Override
   public IDVar createVirtualVar(IJavaType var1) {
      if (var1.isSingleSlot()) {
         int var4 = this.PV.size();
         Assert.a(var4 < 458752);
         int var5 = 131072 + var4;
         this.PV.add(var5);
         return this.createVar(var5, var1);
      } else if (var1.isDoubleSlot()) {
         int var2 = this.oQ.size();
         Assert.a(var2 < 1048575);
         int var3 = 589824 + var2;
         this.oQ.add(var3);
         return this.createVar(var3, var1);
      } else {
         throw new RuntimeException();
      }
   }

   @Override
   public IDVar retrieveTemporaryVariable(IJavaType var1) {
      return this.retrieveTemporaryVariable(var1, 0);
   }

   @Override
   public IDVar retrieveTemporaryVariable(IJavaType var1, int var2) {
      if (var1 != null && var2 >= 0) {
         if (this.xW == null) {
            this.xW = new HashSet();
            this.KT = new HashMap();
         }

         List var3 = (List)this.KT.computeIfAbsent(var1, var0 -> new ArrayList());

         for (int var4 = var3.size(); var4 <= var2; var4++) {
            var3.add(null);
         }

         IDVar var5 = (IDVar)var3.get(var2);
         if (var5 == null) {
            var5 = this.createVirtualVar(var1);
            var3.set(var2, var5);
            this.xW.add(var5);
         }

         return var5;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int retrievePhysicalRegisterId(int var1) {
      if (var1 >= 0 && var1 < 65536) {
         return var1;
      } else if (var1 >= 65536 && var1 < 131071) {
         return var1 - 65536;
      } else if (var1 >= 131072 && var1 < 589824) {
         int var4 = var1 - 131072;
         if (var4 < this.PV.size()) {
            int var5 = (Integer)this.PV.get(var4);
            if (var5 >= 0 && var5 <= 65535) {
               return var5;
            }
         }

         return -1;
      } else if (var1 >= 589824 && var1 < 1048575) {
         int var2 = var1 - 589824;
         if (var2 < this.oQ.size()) {
            int var3 = (Integer)this.oQ.get(var2);
            if (var3 >= 65536 && var3 <= 131070) {
               return var3 - 65536;
            }
         }

         return -1;
      } else {
         throw new RuntimeException();
      }
   }

   @Override
   public int retrievePrimaryVariableId(int var1) {
      if (var1 >= 0 && var1 < 65536) {
         return var1;
      } else if (var1 >= 65536 && var1 < 131071) {
         return var1;
      } else if (var1 >= 131072 && var1 < 589824) {
         int var3 = var1 - 131072;
         return var3 >= this.PV.size() ? -1 : (Integer)this.PV.get(var3);
      } else if (var1 >= 589824 && var1 < 1048575) {
         int var2 = var1 - 589824;
         return var2 >= this.oQ.size() ? -1 : (Integer)this.oQ.get(var2);
      } else {
         throw new RuntimeException();
      }
   }

   @Override
   public Set getSame(int var1) {
      return new TreeSet();
   }

   @Override
   public String getName(int var1) {
      return DUtil.formatVarId(var1);
   }

   @Override
   public String toString() {
      return "IR context: " + (this.Uv == null ? "null" : this.Uv.getSignature());
   }

   @Override
   public Object setData(String var1, Object var2) {
      return var2 == null ? this.Ef.remove(var1) : this.Ef.put(var1, var2);
   }

   @Override
   public Object getData(String var1) {
      return this.Ef.get(var1);
   }

   @Override
   public Set getDataKeys() {
      return Collections.unmodifiableSet(this.Ef.keySet());
   }

   public IDInstruction q(DOpcodeType var1, IDElement var2, IDElement var3) {
      return new bub(this, var1, var2, var3);
   }

   @Override
   public IDInstruction createNop() {
      return new bub(this, DOpcodeType.IR_NOP);
   }

   @Override
   public IDInstruction createAssign(IDExpression var1, IDExpression var2) {
      return new bub(this, DOpcodeType.IR_ASSIGN, var1, var2);
   }

   @Override
   public IDInstruction createConstruct(IDNewInfo var1) {
      return new bub(this, DOpcodeType.IR_INVOKE, null, var1);
   }

   @Override
   public IDInstruction createInvoke(IDCallInfo var1) {
      return new bub(this, DOpcodeType.IR_INVOKE, null, var1);
   }

   @Override
   public IDInstruction createNewArray(IDNewArrayInfo var1) {
      return new bub(this, DOpcodeType.IR_INVOKE, null, var1);
   }

   @Override
   public IDInstruction createJump(int var1) {
      return new bub(this, DOpcodeType.IR_JUMP, this.Dw.createTarget(var1));
   }

   @Override
   public IDInstruction createJcond(int var1, IDExpression var2) {
      return new bub(this, DOpcodeType.IR_JCOND, this.Dw.createTarget(var1), var2);
   }

   @Override
   public IDInstruction createSwitch(IDExpression var1, IDSwitchData var2) {
      return new bub(this, DOpcodeType.IR_SWITCH, var2, var1);
   }

   @Override
   public IDInstruction createReturn(IDExpression var1) {
      return new bub(this, DOpcodeType.IR_RETURN, null, var1);
   }

   @Override
   public IDInstruction createThrow(IDExpression var1) {
      return new bub(this, DOpcodeType.IR_THROW, null, var1);
   }

   @Override
   public IDInstruction createStoreException(IDVar var1) {
      return new bub(this, DOpcodeType.IR_STORE_EXCEPTION, var1);
   }

   @Override
   public IDInstruction createMonitorEnter(IDExpression var1) {
      return new bub(this, DOpcodeType.IR_MONITOR_ENTER, null, var1);
   }

   @Override
   public IDInstruction createMonitorExit(IDExpression var1) {
      return new bub(this, DOpcodeType.IR_MONITOR_EXIT, null, var1);
   }

   @Override
   public IDImm evaluate(IDImm... var1) throws DexDecEvaluationException {
      return this.evaluate(null, Arrays.asList(var1));
   }

   @Override
   public IDImm evaluate(IDState var1, List var2) throws DexDecEvaluationException {
      if (var2 == null) {
         var2 = Collections.emptyList();
      }

      boolean var3 = false;

      IDImm var11;
      try {
         if (var1 == null) {
            var1 = this.Dw.createState(true, true);
            var3 = true;
            var1.pushContext("context0");
            var1.pushFrame(this.getMethodSignature());
         }

         DExecutionParameters var4 = new DExecutionParameters(this);
         int var5 = 0;

         for (IDVar var7 : this.getParameterVariables()) {
            if (var5 >= var2.size()) {
               break;
            }

            var4.addInitialValue(var7.getId(), (IDImm)var2.get(var5));
            var5++;
         }

         var11 = var1.execute(var4);
      } finally {
         if (var3) {
            var1.dispose();
         }
      }

      return var11;
   }

   @Override
   public void incrementDeobfuscationScore(int var1) {
      this.Gf += var1;
   }

   @Override
   public int getDeobfuscationScore() {
      return this.Gf;
   }

   @Override
   public void resetDeobfuscationScore() {
      this.Gf = 0;
   }

   @Override
   public void pushWorkingOptimizer(IDOptimizer var1) {
      Assert.a(var1 != null);
      this.RF.push(var1);
   }

   @Override
   public IDOptimizer popWorkingOptimizer() {
      return this.RF.isEmpty() ? null : (IDOptimizer)this.RF.pop();
   }

   static class eo {
      IDexDebugVariable q;
      boolean RF = true;

      eo(IDexDebugVariable var1) {
         this.q = var1;
      }
   }
}
