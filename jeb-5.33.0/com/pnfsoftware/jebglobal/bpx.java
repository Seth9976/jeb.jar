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
public class bpx implements IDMethodContext {
   private static final ILogger kS = GlobalLog.getLogger(bpx.class);
   public final long pC = System.currentTimeMillis();
   private IDGlobalContext wS;
   private IDexMethod UT;
   private IDMethodContext E;
   private Watchdog sY;
   private int ys;
   private boolean ld = true;
   private boolean gp = true;
   private boolean oT;
   private SortedMap fI;
   private Set WR;
   private Map NS;
   private Map vP;
   private CFG xC;
   private IDTryData ED;
   private int Sc;
   private SortedMap ah = new TreeMap();
   private List eP = new ArrayList();
   private List UO = new ArrayList();
   private Set Ab;
   private Map rl;
   private int z;
   private Map Ek = new HashMap();
   public Deque A = new ConcurrentLinkedDeque();
   private volatile WeakConcurrentSet hK;
   private volatile WeakConcurrentSet Er;

   public bpx(IDGlobalContext var1, IDexMethod var2) {
      if (var1 != null && var2 != null) {
         this.wS = var1;
         this.UT = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IDMethodContext copy() {
      bpx var1 = new bpx(this.wS, this.UT);
      var1.E = this.E;
      var1.sY = this.sY;
      var1.ys = this.ys;
      var1.ld = this.ld;
      var1.gp = this.gp;
      var1.oT = this.oT;
      var1.fI = this.fI;
      var1.WR = this.WR;
      var1.NS = this.NS;
      var1.vP = this.vP;
      var1.xC = DUtil.copyGraph(this.xC, true, var1);
      var1.ED = this.ED.copy();
      var1.Sc = this.Sc;
      var1.ah = new TreeMap(this.ah);
      var1.eP = new ArrayList(this.eP);
      var1.UO = new ArrayList(this.UO);
      var1.z = this.z;
      var1.Ek = new HashMap(this.Ek);
      this.A(var1);
      return var1;
   }

   @Override
   public void load(IDMethodContext var1) {
      bpx var2 = (bpx)var1;
      this.wS = var2.wS;
      this.UT = var2.UT;
      this.E = var2.E;
      this.sY = var2.sY;
      this.ys = var2.ys;
      this.ld = var2.ld;
      this.gp = var2.gp;
      this.oT = var2.oT;
      this.fI = var2.fI;
      this.WR = var2.WR;
      this.NS = var2.NS;
      this.vP = var2.vP;
      this.xC = var2.xC;
      this.xC.setVariableInformationProvider(this);

      for (IDInstruction var4 : this.xC.instructions()) {
         var4.setContext(this);
      }

      this.xC.invalidateDataFlowAnalysis();
      this.ED = var2.ED;
      this.Sc = var2.Sc;
      this.ah = var2.ah;
      this.eP = var2.eP;
      this.UO = var2.UO;
      this.z = var2.z;
      this.Ek = var2.Ek;
   }

   public void pC(Watchdog var1) {
      this.sY = var1;
   }

   @Override
   public Watchdog getWatchdog() {
      return this.sY;
   }

   public void pC(int var1) {
      this.ys = var1;
   }

   @Override
   public int getDecompilationFlags() {
      return this.ys;
   }

   public void pC(boolean var1) {
      this.ld = var1;
   }

   @Override
   public boolean isParseExceptions() {
      return this.ld;
   }

   public void A(boolean var1) {
      this.gp = var1;
   }

   @Override
   public boolean isParseDebugInfo() {
      return this.gp;
   }

   @Override
   public CFG getCfg() {
      return this.xC;
   }

   @Override
   public IDTryData getExceptionData() {
      return this.ED;
   }

   @Override
   public IDGlobalContext getGlobalContext() {
      return this.wS;
   }

   @Override
   public IDMethodContext getParentContext() {
      return this.E;
   }

   public void pC(IDMethodContext var1) {
      if (this.E != null) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         this.E = var1;
         if (this.E != null) {
            ((bpx)this.E).pC(this);
         }
      }
   }

   private void pC(bpx var1) {
      Assert.a(var1 != null);
      Assert.a(var1 != this);
      if (this.hK == null) {
         this.hK = new WeakConcurrentSet(WeakConcurrentSet.Cleaner.INLINE);
      }

      this.hK.add(var1);
   }

   @Override
   public List getChildrenContexts() {
      ArrayList var1 = new ArrayList();
      if (this.hK != null) {
         for (bpx var3 : this.hK) {
            if (var3 != null) {
               var1.add(var3);
            }
         }
      }

      return var1;
   }

   private void A(bpx var1) {
      Assert.a(var1 != null);
      Assert.a(var1 != this);
      if (this.Er == null) {
         this.Er = new WeakConcurrentSet(WeakConcurrentSet.Cleaner.INLINE);
      }

      this.Er.add(var1);
   }

   @Override
   public List getCopiesContexts() {
      ArrayList var1 = new ArrayList();
      if (this.Er != null) {
         for (bpx var3 : this.Er) {
            if (var3 != null) {
               var1.add(var3);
            }
         }
      }

      return var1;
   }

   @Override
   public IDexUnit getDex() {
      return this.wS.getDex();
   }

   @Override
   public IDexMethod getMethod() {
      return this.UT;
   }

   @Override
   public String getMethodSignature() {
      return this.UT.getSignature(false);
   }

   @Override
   public IJavaTypeFactory getTypeFactory() {
      return this.wS.getTypeFactory();
   }

   @Override
   public IJavaOperatorFactory getOperatorFactory() {
      return this.wS.getOperatorFactory();
   }

   @Override
   public IDTypeInfoProvider getTypeInfoProvider() {
      return this.wS.getTypeInfoProvider();
   }

   @Override
   public SortedMap getParametersTypeMap() {
      return this.fI;
   }

   public bqp pC() {
      return new bqp(this);
   }

   public void A() throws DexDecConversionException {
      if (this.oT) {
         throw new IllegalStateException();
      } else {
         this.kS();
         this.wS();
         this.E();
         this.oT = true;
      }
   }

   @Override
   public void verify() throws IllegalStateException {
      DUtil.verifyGraph(this);
   }

   public void kS() throws DexDecConversionException {
      if (this.xC == null) {
         if (this.getMethodSignature().equals("LSIMUFAILURE;->jebTestProcessThisMethodAndFail2001()V")) {
            throw new RuntimeException("Intentional failure!");
         } else {
            bqp var1 = this.pC();
            var1.pC(this.ld, this.gp, true);
            this.NS = var1.kS();
            this.vP = var1.UT();
            this.WR = var1.wS();
            List var2 = var1.pC();
            List var3 = var1.A();
            this.xC = new CFG(var2, var3);
            this.xC.setVariableInformationProvider(this);
            if (this.ld) {
               TreeSet var4 = new TreeSet();

               for (BasicBlock var6 : this.xC) {
                  var4.add((int)var6.getFirstAddress());
               }

               bfc var7 = new bfc(this.UT.getData().getCodeItem().getExceptionItems());
               this.ED = new bpk(var7, var4, this.NS);
            } else {
               this.ED = new bpk();
            }

            DUtil.removeGaps(this.xC);
            DUtil.simplifyJCondsAndSwitches(this);
            this.fI = Collections.unmodifiableSortedMap(JavaTypeUtil.parseMethodParameters(this.getDex(), this.UT, this.wS.getTypeFactory()));
         }
      }
   }

   public void wS() {
      new bzu().perform(this);
      long var1 = System.currentTimeMillis();

      try {
         this.makeSSA();
      } finally {
         long var6 = System.currentTimeMillis() - var1;
         Object[] var10000 = new Object[]{com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var6)};
      }

      this.UT();
   }

   @Override
   public void makeSSA() {
      brv var1 = new brv(this);
      var1.pC(true);
      var1.pC();
      this.Sc++;
   }

   @Override
   public boolean isSSA() {
      return this.Sc > 0;
   }

   void UT() {
      if (this.isParseDebugInfo()) {
         IDexDebugInfo var1 = this.getMethod().getData().getCodeItem().getDebugInfo();
         if (var1 != null) {
            HashMap var2 = new HashMap();

            for (IDInstruction var4 : this.xC.instructions()) {
               int var5 = (int)var4.getOffsetEnd();
               Integer var6 = (Integer)this.vP.get(var5);
               if (var6 != null) {
                  IDexDebugLine var7 = var1.getLineInfo(var6 / 2);
                  if (var7 != null) {
                     for (int var9 : var7.getVariablesEnd()) {
                        bpx.Av var10 = (bpx.Av)var2.get(var9);
                        if (var10 != null) {
                           var10.A = false;
                        }
                     }

                     for (int var16 : var7.getVariablesRestart()) {
                        bpx.Av var19 = (bpx.Av)var2.get(var16);
                        if (var19 != null) {
                           var19.A = true;
                        }
                     }

                     for (IDexDebugVariable var17 : var7.getVariables()) {
                        var2.put(var17.getRegister(), new bpx.Av(var17));
                     }

                     IDVar var15 = var4.getDefinedVariable();
                     if (var15 != null) {
                        int var18 = var15.getId();
                        int var20 = this.retrievePhysicalRegisterId(var18);
                        bpx.Av var11 = (bpx.Av)var2.get(var20);
                        if (var11 != null && var11.A) {
                           String var12 = DexUtil.getStringSafe(this.wS.getDex(), var11.pC.getNameIndex(), "");
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

   public void E() {
      System.currentTimeMillis();
      this.propagateTypes();
      System.currentTimeMillis();
   }

   @Override
   public void propagateTypes() {
      brz var1 = new brz(this);
      var1.pC(true);
      var1.pC();
   }

   @Override
   public void replace(CFG var1, IDTryData var2) {
      if (var1 != null && var2 != null) {
         var1.setVariableInformationProvider(this);
         this.xC = var1;
         this.ED = var2;
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
      this.xC = var1;
      this.ED.updateTargets((Map)var2);
   }

   @Override
   public SortedMap getVariableMap() {
      return Collections.unmodifiableSortedMap(this.ah);
   }

   @Override
   public boolean isStaticMethod() {
      return this.UT.getData().isStatic();
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
      return (IDVar)this.ah.get(var1);
   }

   @Override
   public IDVar getVar(String var1) {
      int var2 = DUtil.parseVarIdFromStandardName(var1, true);
      if (var2 == -1) {
         for (IDVar var4 : this.ah.values()) {
            if (var1.equals(var4.getPreferredName())) {
               return var4;
            }
         }
      }

      return (IDVar)this.ah.get(var2);
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
      Object var4 = (IDVar)this.ah.get(var1);
      if (var4 == null) {
         var4 = new bqg(this, var1, var2);
         this.ah.put(var1, var4);
      } else if (var3 && !((IDVar)var4).getType().equals(var2)) {
         throw new IllegalArgumentException("Type of an already existing variable mismatches the requested type");
      }

      return (IDVar)var4;
   }

   @Override
   public Collection getVars() {
      return Collections.unmodifiableCollection(this.ah.values());
   }

   @Override
   public boolean removeVar(int var1) {
      IDVar var2 = (IDVar)this.ah.remove(var1);
      if (var2 == null) {
         return false;
      } else {
         Assert.a(var2.getId() == var1);
         if (var1 < 131072) {
            return true;
         } else {
            if (var1 < 589824) {
               int var3 = var1 - 131072;
               this.eP.set(var3, -1);
            } else {
               Assert.a(var1 < 1048576);
               int var5 = var1 - 589824;
               this.UO.set(var5, -1);
            }

            if (this.Ab != null && this.Ab.remove(var2)) {
               for (List var4 : this.rl.values()) {
                  var4.remove(var2);
               }
            }

            return true;
         }
      }
   }

   @Override
   public void clearVirtualVars() {
      this.ah.subMap(131072, Integer.MAX_VALUE).clear();
      this.eP.clear();
      this.UO.clear();
      this.Ab.clear();
      this.rl.clear();
   }

   @Override
   public IDVar createRegisterVar(int var1, IJavaType var2) {
      int var3 = DUtil.createRegisterVarId(var1, var2.isDoubleSlot());
      return this.createVar(var3, var2);
   }

   @Override
   public IDVar createCopyVar(IDVar var1) {
      int var2 = this.A(var1.getId());
      IDVar var3 = this.createVar(var2, var1.getType());
      var3.setPreferredName(var1.getPreferredName());
      return var3;
   }

   private int A(int var1) {
      if (var1 >= 0 && var1 < 65536) {
         int var4 = this.eP.size();
         Assert.a(var4 < 458752);
         int var5 = 131072 + var4;
         this.eP.add(var1);
         return var5;
      } else if (var1 >= 65536 && var1 < 131071) {
         int var2 = this.UO.size();
         Assert.a(var2 < 1048575);
         int var3 = 589824 + var2;
         this.UO.add(var1);
         return var3;
      } else {
         throw new RuntimeException("Attempt to create a copy-var from a non-primary id: varid=" + var1);
      }
   }

   @Override
   public IDVar createVirtualVar(IJavaType var1) {
      if (var1.isSingleSlot()) {
         int var4 = this.eP.size();
         Assert.a(var4 < 458752);
         int var5 = 131072 + var4;
         this.eP.add(var5);
         return this.createVar(var5, var1);
      } else if (var1.isDoubleSlot()) {
         int var2 = this.UO.size();
         Assert.a(var2 < 1048575);
         int var3 = 589824 + var2;
         this.UO.add(var3);
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
         if (this.Ab == null) {
            this.Ab = new HashSet();
            this.rl = new HashMap();
         }

         List var3 = (List)this.rl.computeIfAbsent(var1, var0 -> new ArrayList());

         for (int var4 = var3.size(); var4 <= var2; var4++) {
            var3.add(null);
         }

         IDVar var5 = (IDVar)var3.get(var2);
         if (var5 == null) {
            var5 = this.createVirtualVar(var1);
            var3.set(var2, var5);
            this.Ab.add(var5);
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
         if (var4 < this.eP.size()) {
            int var5 = (Integer)this.eP.get(var4);
            if (var5 >= 0 && var5 <= 65535) {
               return var5;
            }
         }

         return -1;
      } else if (var1 >= 589824 && var1 < 1048575) {
         int var2 = var1 - 589824;
         if (var2 < this.UO.size()) {
            int var3 = (Integer)this.UO.get(var2);
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
         return var3 >= this.eP.size() ? -1 : (Integer)this.eP.get(var3);
      } else if (var1 >= 589824 && var1 < 1048575) {
         int var2 = var1 - 589824;
         return var2 >= this.UO.size() ? -1 : (Integer)this.UO.get(var2);
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
      return "IR context: " + (this.UT == null ? "null" : this.UT.getSignature());
   }

   @Override
   public Object setData(String var1, Object var2) {
      return var2 == null ? this.Ek.remove(var1) : this.Ek.put(var1, var2);
   }

   @Override
   public Object getData(String var1) {
      return this.Ek.get(var1);
   }

   @Override
   public Set getDataKeys() {
      return Collections.unmodifiableSet(this.Ek.keySet());
   }

   public IDInstruction pC(DOpcodeType var1, IDElement var2, IDElement var3) {
      return new bpv(this, var1, var2, var3);
   }

   @Override
   public IDInstruction createNop() {
      return new bpv(this, DOpcodeType.IR_NOP);
   }

   @Override
   public IDInstruction createAssign(IDExpression var1, IDExpression var2) {
      return new bpv(this, DOpcodeType.IR_ASSIGN, var1, var2);
   }

   @Override
   public IDInstruction createConstruct(IDNewInfo var1) {
      return new bpv(this, DOpcodeType.IR_INVOKE, null, var1);
   }

   @Override
   public IDInstruction createInvoke(IDCallInfo var1) {
      return new bpv(this, DOpcodeType.IR_INVOKE, null, var1);
   }

   @Override
   public IDInstruction createNewArray(IDNewArrayInfo var1) {
      return new bpv(this, DOpcodeType.IR_INVOKE, null, var1);
   }

   @Override
   public IDInstruction createJump(int var1) {
      return new bpv(this, DOpcodeType.IR_JUMP, this.wS.createTarget(var1));
   }

   @Override
   public IDInstruction createJcond(int var1, IDExpression var2) {
      return new bpv(this, DOpcodeType.IR_JCOND, this.wS.createTarget(var1), var2);
   }

   @Override
   public IDInstruction createSwitch(IDExpression var1, IDSwitchData var2) {
      return new bpv(this, DOpcodeType.IR_SWITCH, var2, var1);
   }

   @Override
   public IDInstruction createReturn(IDExpression var1) {
      return new bpv(this, DOpcodeType.IR_RETURN, null, var1);
   }

   @Override
   public IDInstruction createThrow(IDExpression var1) {
      return new bpv(this, DOpcodeType.IR_THROW, null, var1);
   }

   @Override
   public IDInstruction createStoreException(IDVar var1) {
      return new bpv(this, DOpcodeType.IR_STORE_EXCEPTION, var1);
   }

   @Override
   public IDInstruction createMonitorEnter(IDExpression var1) {
      return new bpv(this, DOpcodeType.IR_MONITOR_ENTER, null, var1);
   }

   @Override
   public IDInstruction createMonitorExit(IDExpression var1) {
      return new bpv(this, DOpcodeType.IR_MONITOR_EXIT, null, var1);
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
            var1 = this.wS.createState(true, true);
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
      this.z += var1;
   }

   @Override
   public int getDeobfuscationScore() {
      return this.z;
   }

   @Override
   public void resetDeobfuscationScore() {
      this.z = 0;
   }

   @Override
   public void pushWorkingOptimizer(IDOptimizer var1) {
      Assert.a(var1 != null);
      this.A.push(var1);
   }

   @Override
   public IDOptimizer popWorkingOptimizer() {
      return this.A.isEmpty() ? null : (IDOptimizer)this.A.pop();
   }

   static class Av {
      IDexDebugVariable pC;
      boolean A = true;

      Av(IDexDebugVariable var1) {
         this.pC = var1;
      }
   }
}
