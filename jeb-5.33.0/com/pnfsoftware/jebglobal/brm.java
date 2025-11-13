package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexFieldData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.BiMap;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class brm {
   private static final ILogger kS = GlobalLog.getLogger(brm.class);
   private IDGlobalContext wS;
   private IDexUnit UT;
   private IDMethodContext E;
   private int sY = 10000;
   private int ys = 30;
   private int ld = -1;
   private List gp = new ArrayList();
   private CFG oT;
   public IDInstruction pC;
   public IDInstruction A;
   private bpx fI;
   private Set WR;
   private Map NS;
   private BiMap vP;
   private brm.Av xC = new brm.Av();
   private Map ED;

   public brm(IDMethodContext var1) {
      this.wS = var1.getGlobalContext();
      this.UT = this.wS.getDex();
      this.E = var1;
   }

   public void pC(int var1) {
      this.sY = var1;
   }

   public void A(int var1) {
      this.ld = var1;
   }

   public void pC() {
      this.ED = new HashMap();
   }

   private void A() {
      this.oT = this.E.getCfg();
      this.pC = null;
      this.A = null;
      this.fI = null;
      this.WR = null;
      this.NS = null;
      this.vP = null;
      this.xC = new brm.Av();
   }

   public boolean pC(BasicBlock var1, int var2, IDCallInfo var3) {
      this.A();
      IDInstruction var4 = (IDInstruction)var1.get(var2);
      long var5 = System.currentTimeMillis();

      boolean var8;
      try {
         boolean var7 = this.A(var3) && this.pC(var1, var2, var4, var3);
         if (var7) {
            this.xC.kS = true;
            return true;
         }

         var8 = false;
      } finally {
         this.xC.UT = System.currentTimeMillis() - var5;
         this.gp.add(this.xC);
      }

      return var8;
   }

   public boolean pC(IDInstruction var1, IDCallInfo var2) {
      Couple var3 = this.E.getCfg().getInstructionLocation(var1.getOffset());
      BasicBlock var4 = (BasicBlock)var3.getFirst();
      int var5 = (Integer)var3.getSecond();
      return this.pC(var4, var5, var2);
   }

   public IDMethodContext pC(IDCallInfo var1) {
      return !this.A(var1) ? null : this.fI;
   }

   private boolean A(IDCallInfo var1) {
      long var2 = System.currentTimeMillis();
      this.xC.pC = var1.getMethodSignature();
      IDexMethod var4 = bpl.pC(var1, this.wS.getDex());
      if (var4 == null) {
         return false;
      } else if (bqh.pC(var4, this.wS).pC()) {
         return false;
      } else if (var4.getData().getCodeItem().getControlFlowGraph().getInstructionCount() >= this.sY) {
         return false;
      } else {
         String var5 = var4.getSignature(false);
         this.xC.A = var5;
         int var6 = this.ys;
         if (Boolean.FALSE.equals(((com.pnfsoftware.jeb.corei.parsers.dex.vi)this.wS.getDex()).Er().pC(var4.getIndex()))) {
            var6 *= 20;
         }

         if (var6 >= 0 && bpl.A(this.E, var5) >= var6) {
            HashMap var39 = Maps.toMap("msig1", var5);
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("too many inlines"), this.E.getMethodSignature(), -1, var39, true);
            return false;
         } else {
            boolean var7 = var4.getName(false).equals("<init>");
            if (var7 && var1.getInvokeType() == DInvokeType.DIRECT) {
               return false;
            } else {
               Object[] var10000 = new Object[]{var4};
               if (this.ED != null) {
                  this.fI = (bpx)this.ED.get(var5);
                  this.xC.wS = true;
               }

               if (this.fI == null) {
                  this.fI = ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)this.E.getGlobalContext().getDecompiler()).pC(var4);
                  this.fI.A(false);
                  this.fI.pC(this.E.isParseExceptions());
                  this.fI.pC(this.E.getWatchdog());
                  this.fI.pC(this.E.getDecompilationFlags());
                  this.fI.pC(this.E);
                  long var8 = System.currentTimeMillis();

                  label553: {
                     boolean var11;
                     try {
                        this.fI.A();
                        break label553;
                     } catch (Exception var37) {
                        com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var37, var4.getSignature(false));
                        var11 = false;
                     } finally {
                        this.xC.ys = System.currentTimeMillis() - var8;
                     }

                     return var11;
                  }

                  IDMasterOptimizer var10 = this.E.getGlobalContext().createMasterOptimizer(this.fI);
                  var10.setSafeMode(true);
                  var10.setPolicyForOptimizerTag("inliner", false);
                  var10.setPolicyForOptimizerTag("slow", false);
                  bpl.pC(var10, bxn.class, false);
                  if (this.ld >= 0) {
                     ((brk)var10).pC(this.ld);
                  }

                  long var44 = System.currentTimeMillis();

                  try {
                     int var13 = var10.perform();
                     this.fI.getCfg();
                     var10000 = new Object[]{var13};
                  } finally {
                     this.xC.ld = System.currentTimeMillis() - var44;
                  }

                  if (this.ED != null) {
                     this.ED.put(var5, this.fI);
                  }
               }

               if (this.ED != null) {
                  this.fI = (bpx)this.fI.copy();
               }

               ArrayList var40 = new ArrayList();
               if (var7) {
                  CFG var9 = this.fI.getCfg();
                  IDVar var42 = (IDVar)this.fI.getParameterVariables().get(0);
                  IDInstruction var45 = (IDInstruction)var9.getInstruction(0L);
                  if (!var45.isInvoke()
                     || !(var45.getInvokeData() instanceof IDCallInfo var12)
                     || !var12.getMethodSignature().equals("Ljava/lang/Object;-><init>()V")
                     || var12.getArgument(0) != var42) {
                     return false;
                  }

                  var45.transformToNop();
                  String var51 = var1.getMethodSignature();
                  String var14 = JvmMethodSig.parse(var51).csig;
                  IDexClass var15 = this.wS.getDex().getClass(var14);
                  if (var15 == null || !var15.getSupertypeSignature(false).equals("Ljava/lang/Object;")) {
                     return false;
                  }

                  int var16 = 0;
                  IDInstruction var17 = this.fI.createAssign(var42, this.wS.createAllocObjectInfo(var42.getType())).withOffset(var16);
                  this.pC = var17;
                  var40.add(var17);
                  var16++;

                  for (IDexFieldData var19 : var15.getData().getInstanceFields()) {
                     IDInstanceField var20 = this.wS.createInstanceField(var42, this.UT.getField(var19.getFieldIndex()));
                     IDInstruction var21 = this.fI.createAssign(var20, this.wS.createImm(0L, var20.getType())).withOffset(var16);
                     var40.add(var21);
                     var16++;
                  }

                  for (BasicBlock var73 : var9) {
                     IDInstruction var74 = (IDInstruction)var73.getLast();
                     if (var74.isReturn()) {
                        var74.setReturnExpression(var42);
                     }
                  }
               }

               List var41 = var1.getArguments();
               DUtil.insertHeaderBlock(this.fI, var41.size(), 1);
               this.fI.getCfg();
               var10000 = new Object[0];
               ArrayList var43 = new ArrayList();
               this.vP = new BiMap();

               for (int var46 = 0; var46 < var41.size(); var46++) {
                  IDExpression var48 = (IDExpression)var41.get(var46);
                  if (var48 instanceof IDVar var53) {
                     int var58 = var53.getId();
                     IDVar var63 = (IDVar)this.vP.get(var58);
                     if (var63 == null) {
                        var63 = this.fI.createVirtualVar(var53.getType());
                        this.vP.put(var58, var63);
                     }

                     var43.add(var63);
                  } else {
                     IDExpression var52 = var48.duplicate();

                     for (int var62 : DUtil.collectUniqueVarIds(var48)) {
                        IDVar var68 = this.E.getVar(var62);
                        IDVar var70 = (IDVar)this.vP.get(var62);
                        if (var70 == null) {
                           var70 = this.fI.createVirtualVar(var68.getType());
                           this.vP.put(var62, var70);
                        }

                        var52.replaceVariable(var68, var70);
                     }

                     var43.add(var52);
                  }
               }

               this.WR = this.vP.values();
               List var47 = this.fI.getParameterVariables();
               int var49 = var7 && var1.getInvokeType() == DInvokeType.NEW ? 1 : 0;
               if (var47.size() - var49 != var43.size()) {
                  RuntimeException var56 = new RuntimeException(Strings.ff("Unexpected parameter count in method inliner (inlinining:%s)", var1));
                  throw var56;
               } else {
                  int var54 = 0;

                  for (IDExpression var64 : var43) {
                     IDInstruction var69 = this.fI.createAssign((IDExpression)var47.get(var49 + var54), var64).withOffset(var54);
                     this.fI.getCfg().getBlock(0).set(var54, var69);
                     var54++;
                  }

                  this.fI.verify();
                  this.fI.getCfg();
                  var10000 = new Object[0];
                  if (var7) {
                     DUtil.insertHeaderBlock(this.fI, var40.size(), 1);
                     var54 = 0;

                     for (IDInstruction var65 : var40) {
                        this.fI.getCfg().getBlock(0).set(var54, var65);
                        var54++;
                     }

                     this.fI.verify();
                     this.fI.getCfg();
                     var10000 = new Object[0];
                  }

                  IDMasterOptimizer var61 = this.E.getGlobalContext().createMasterOptimizer(this.fI);
                  var61.setSafeMode(true);
                  var61.setPolicyForOptimizerTag("inliner", false);
                  var61.setPolicyForOptimizerTag("slow", false);
                  bpl.pC(var61, bxn.class, false);
                  if (this.ld >= 0) {
                     ((brk)var61).pC(this.ld);
                  }

                  long var66 = System.currentTimeMillis();

                  try {
                     int var71 = var61.perform();
                     this.fI.getCfg();
                     var10000 = new Object[]{var71};
                  } finally {
                     this.xC.gp = System.currentTimeMillis() - var66;
                  }

                  this.xC.E = System.currentTimeMillis() - var2;
                  return true;
               }
            }
         }
      }
   }

   private boolean pC(BasicBlock var1, int var2, IDInstruction var3, IDCallInfo var4) {
      long var5 = System.currentTimeMillis();
      new caw().perform(this.fI);
      BasicBlock var7 = this.fI.getCfg().getLast();

      for (BasicBlock var9 : this.fI.getCfg()) {
         if (((IDInstruction)var9.getLast()).isReturn() && var9 != var7) {
            return false;
         }
      }

      this.fI.verify();
      this.NS = new HashMap();

      for (IDInstruction var25 : this.fI.getCfg().instructions()) {
         for (int var11 : DUtil.collectUniqueVarIds(var25)) {
            IDVar var13 = this.fI.getVar(var11);
            IDVar var12;
            if (!this.WR.contains(var13)) {
               var12 = (IDVar)this.NS.get(var11);
               if (var12 == null) {
                  var12 = this.E.createVirtualVar(var13.getType());
                  this.NS.put(var11, var12);
               }
            } else {
               int var14 = (Integer)this.vP.getKeyForValue(var13);
               var12 = this.E.getVar(var14);
               Assert.a(var12 != null);
            }

            var25.replaceVariable(var13, var12);
         }
      }

      this.fI.getCfg();
      Object[] var10000 = new Object[0];
      DUtil.cleanGraph(this.fI);
      if (var3.getSize() == 1) {
         DUtil.modifyInstructionSizes(this.E, var1x -> var1x == var3 ? 2 : null);
      }

      IDVar var24 = null;
      if (!var4.getType().isVoid()) {
         var24 = this.E.createVirtualVar(var4.getType());
      }

      if (!var3.isInvoke() || var3.getInvokeData() != var4) {
         Couple var26 = this.oT.getInstructionLocation(var3.getOffset());
         var1 = (BasicBlock)var26.getFirst();
         var2 = (Integer)var26.getSecond();
         int var29 = (int)var3.getOffset();
         IDInstruction var31 = this.E.createAssign(var24, var4).withOffset(var29);
         Assert.a(DUtil.replaceInExpression(var3, var4, var24));
         var3.setOffset(var29 + 1);
         var3.adjustSize(-1);
         var1.add(var2, var31);
         var3 = var31;
         var26 = this.oT.getInstructionLocation(var31.getOffset());
         var1 = (BasicBlock)var26.getFirst();
         var2 = (Integer)var26.getSecond();
         var10000 = new Object[0];
      }

      BasicBlock var28;
      if (var2 != var1.size() - 1) {
         var28 = DUtil.splitBlock(this.E, var1, var2 + 1);
      } else {
         Assert.a(var1.outsize() == 1);
         var28 = var1.getOutputBlock(0);
      }

      int var30 = (int)this.oT.getEndAddress();
      DUtil.insertHeaderBlock(this.fI, 1, var30);
      this.fI.getCfg();
      var10000 = new Object[0];
      CFG var32 = this.fI.getCfg();
      BasicBlock var33 = var32.get(0);
      Assert.a(var33.insize() == 0);
      Assert.a(var33.outsize() == 1);
      Assert.a(var33.irrinsize() == 0);
      Assert.a(var33.irroutsize() == 0);
      var32.deleteOutEdges(var33);
      ArrayList var34 = new ArrayList();
      int var35 = 0;

      for (BasicBlock var16 : var32) {
         for (IDInstruction var18 : var16) {
            var18.setContext(this.E);
            var18.updateAllPhysicalMethodIndices(this.fI.getMethod().getIndex());
         }

         if (var35 >= 1) {
            var34.add(var16);
            this.oT.addBlock(var16);
         }

         var35++;
      }

      IDTryData var36 = this.E.getExceptionData();

      for (int var39 : this.fI.getExceptionData().getProtectedBlocks()) {
         var36.setBlockHandlers(var39, this.fI.getExceptionData().getBlockHandlers(var39));
      }

      List var38 = var36.getBlockHandlers((int)var1.getBase());
      if (!var38.isEmpty()) {
         for (BasicBlock var42 : var34) {
            for (IDExceptionHandler var20 : var38) {
               if (var36.protectBlock((int)var42.getBase(), var20.getTypeIndex(), var20.getAddress(), -1)) {
                  this.oT.addIrregularEdge(var42, this.oT.getBlockAt((long)var20.getAddress()), -1);
               }
            }
         }
      }

      IDInstruction var41 = this.E.createJump(var30).withOffset(var3.getOffset()).withSize(var3.getSize());
      var1.set(var2, var41);
      this.oT.deleteOutEdges(var1);
      this.oT.addEdge(var1, this.oT.getBlockAt((long)var30));
      IDInstruction var43 = (IDInstruction)var7.getLast();
      if (var43.isReturn()) {
         IDExpression var44 = var43.getReturnExpression();
         if (var44 == null) {
            Assert.a(var24 == null);
            var43.transformToNop();
         } else {
            var43.morph(DOpcodeType.IR_ASSIGN, var24, var44);
         }

         this.A = var43;
         IDInstruction var45 = this.E.createJump((int)var28.getBase()).withOffset(this.oT.getEndAddress());
         var7.add(var45);
         this.oT.addEdge(var7, var28);
      }

      var10000 = new Object[0];
      DUtil.cleanGraph(this.E);
      bpl.pC(this.E, this.fI.getMethodSignature());
      bpl.pC(this.wS, this.fI.getMethod());
      DUtil.verifyGraph(this.E);
      this.oT.invalidateDataFlowAnalysis();
      this.xC.sY = System.currentTimeMillis() - var5;
      return true;
   }

   public static class Av {
      public String pC;
      public String A;
      public boolean kS;
      public boolean wS;
      public long UT;
      public long E;
      public long sY;
      public long ys;
      public long ld;
      public long gp;

      @Override
      public String toString() {
         return Strings.ff("%s[%b]: %d ms (%d+%d) conv:%d opt1:%d, opt2:%d", this.pC, this.kS, this.UT, this.E, this.sY, this.ys, this.ld, this.gp);
      }
   }
}
