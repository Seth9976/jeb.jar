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

public class bvv {
   private static final ILogger oW = GlobalLog.getLogger(bvv.class);
   public static final int q = 10000;
   public static final int RF = 30;
   public static final int xK = -1;
   private static final boolean gO = false;
   private IDGlobalContext nf;
   private IDexUnit gP;
   private IDMethodContext za;
   private int lm = 10000;
   private int zz = 30;
   private int JY = -1;
   private List HF = new ArrayList();
   private CFG LK;
   public IDInstruction Dw;
   public IDInstruction Uv;
   private bud io;
   private Set qa;
   private Map Hk;
   private BiMap Me;
   private bvv.eo PV = new bvv.eo();
   private Map oQ;

   private static void q(String var0, Object... var1) {
   }

   private static void q(CFG var0, String var1, Object... var2) {
   }

   public bvv(IDMethodContext var1) {
      this.nf = var1.getGlobalContext();
      this.gP = this.nf.getDex();
      this.za = var1;
   }

   public void q(int var1) {
      this.lm = var1;
   }

   public int q() {
      return this.lm;
   }

   public void RF(int var1) {
      this.zz = var1;
   }

   public int RF() {
      return this.zz;
   }

   public void xK(int var1) {
      this.JY = var1;
   }

   public int xK() {
      return this.JY;
   }

   public void Dw() {
      this.oQ = new HashMap();
   }

   public boolean Uv() {
      return this.oQ != null;
   }

   public List oW() {
      return this.HF;
   }

   private void gO() {
      this.LK = this.za.getCfg();
      this.Dw = null;
      this.Uv = null;
      this.io = null;
      this.qa = null;
      this.Hk = null;
      this.Me = null;
      this.PV = new bvv.eo();
   }

   public boolean q(BasicBlock var1, int var2, IDCallInfo var3) {
      this.gO();
      IDInstruction var4 = (IDInstruction)var1.get(var2);
      long var5 = System.currentTimeMillis();

      boolean var8;
      try {
         boolean var7 = this.RF(var3) && this.q(var1, var2, var4, var3);
         if (var7) {
            this.PV.xK = true;
            return true;
         }

         var8 = false;
      } finally {
         this.PV.Uv = System.currentTimeMillis() - var5;
         this.HF.add(this.PV);
      }

      return var8;
   }

   public boolean q(IDInstruction var1, IDCallInfo var2) {
      Couple var3 = this.za.getCfg().getInstructionLocation(var1.getOffset());
      BasicBlock var4 = (BasicBlock)var3.getFirst();
      int var5 = (Integer)var3.getSecond();
      return this.q(var4, var5, var2);
   }

   public IDMethodContext q(IDCallInfo var1) {
      return !this.RF(var1) ? null : this.io;
   }

   private boolean RF(IDCallInfo var1) {
      long var2 = System.currentTimeMillis();
      this.PV.q = var1.getMethodSignature();
      IDexMethod var4 = bto.q(var1, this.nf.getDex());
      if (var4 == null) {
         return false;
      } else if (bun.q(var4, this.nf).q()) {
         return false;
      } else if (var4.getData().getCodeItem().getControlFlowGraph().getInstructionCount() >= this.lm) {
         return false;
      } else {
         String var5 = var4.getSignature(false);
         this.PV.RF = var5;
         int var6 = this.zz;
         if (Boolean.FALSE.equals(((com.pnfsoftware.jeb.corei.parsers.dex.bK)this.nf.getDex()).Rr().xK(var4.getIndex()))) {
            var6 *= 20;
         }

         if (var6 >= 0 && bto.RF(this.za, var5) >= var6) {
            HashMap var39 = Maps.toMap("msig1", var5);
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("too many inlines"), this.za.getMethodSignature(), -1, var39, true);
            return false;
         } else {
            boolean var7 = var4.getName(false).equals("<init>");
            if (var7 && var1.getInvokeType() == DInvokeType.DIRECT) {
               return false;
            } else {
               Object[] var10000 = new Object[]{var4};
               if (this.oQ != null) {
                  this.io = (bud)this.oQ.get(var5);
                  this.PV.Dw = true;
               }

               if (this.io == null) {
                  this.io = ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)this.za.getGlobalContext().getDecompiler()).q(var4);
                  this.io.RF(false);
                  this.io.q(this.za.isParseExceptions());
                  this.io.q(this.za.getWatchdog());
                  this.io.q(this.za.getDecompilationFlags());
                  this.io.q(this.za);
                  long var8 = System.currentTimeMillis();

                  label553: {
                     boolean var11;
                     try {
                        this.io.Dw();
                        break label553;
                     } catch (Exception var37) {
                        com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var37, var4.getSignature(false));
                        var11 = false;
                     } finally {
                        this.PV.nf = System.currentTimeMillis() - var8;
                     }

                     return var11;
                  }

                  IDMasterOptimizer var10 = this.za.getGlobalContext().createMasterOptimizer(this.io);
                  var10.setSafeMode(true);
                  var10.setPolicyForOptimizerTag("inliner", false);
                  var10.setPolicyForOptimizerTag("slow", false);
                  bto.q(var10, ccc.class, false);
                  if (this.JY >= 0) {
                     ((bvt)var10).q(this.JY);
                  }

                  long var44 = System.currentTimeMillis();

                  try {
                     int var13 = var10.perform();
                     this.io.getCfg();
                     var10000 = new Object[]{var13};
                  } finally {
                     this.PV.gP = System.currentTimeMillis() - var44;
                  }

                  if (this.oQ != null) {
                     this.oQ.put(var5, this.io);
                  }
               }

               if (this.oQ != null) {
                  this.io = (bud)this.io.copy();
               }

               ArrayList var40 = new ArrayList();
               if (var7) {
                  CFG var9 = this.io.getCfg();
                  IDVar var42 = (IDVar)this.io.getParameterVariables().get(0);
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
                  IDexClass var15 = this.nf.getDex().getClass(var14);
                  if (var15 == null || !var15.getSupertypeSignature(false).equals("Ljava/lang/Object;")) {
                     return false;
                  }

                  int var16 = 0;
                  IDInstruction var17 = this.io.createAssign(var42, this.nf.createAllocObjectInfo(var42.getType())).withOffset(var16);
                  this.Dw = var17;
                  var40.add(var17);
                  var16++;

                  for (IDexFieldData var19 : var15.getData().getInstanceFields()) {
                     IDInstanceField var20 = this.nf.createInstanceField(var42, this.gP.getField(var19.getFieldIndex()));
                     IDInstruction var21 = this.io.createAssign(var20, this.nf.createImm(0L, var20.getType())).withOffset(var16);
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
               DUtil.insertHeaderBlock(this.io, var41.size(), 1);
               this.io.getCfg();
               var10000 = new Object[0];
               ArrayList var43 = new ArrayList();
               this.Me = new BiMap();

               for (int var46 = 0; var46 < var41.size(); var46++) {
                  IDExpression var48 = (IDExpression)var41.get(var46);
                  if (var48 instanceof IDVar var53) {
                     int var58 = var53.getId();
                     IDVar var63 = (IDVar)this.Me.get(var58);
                     if (var63 == null) {
                        var63 = this.io.createVirtualVar(var53.getType());
                        this.Me.put(var58, var63);
                     }

                     var43.add(var63);
                  } else {
                     IDExpression var52 = var48.duplicate();

                     for (int var62 : DUtil.collectUniqueVarIds(var48)) {
                        IDVar var68 = this.za.getVar(var62);
                        IDVar var70 = (IDVar)this.Me.get(var62);
                        if (var70 == null) {
                           var70 = this.io.createVirtualVar(var68.getType());
                           this.Me.put(var62, var70);
                        }

                        var52.replaceVariable(var68, var70);
                     }

                     var43.add(var52);
                  }
               }

               this.qa = this.Me.values();
               List var47 = this.io.getParameterVariables();
               int var49 = var7 && var1.getInvokeType() == DInvokeType.NEW ? 1 : 0;
               if (var47.size() - var49 != var43.size()) {
                  RuntimeException var56 = new RuntimeException(Strings.ff("Unexpected parameter count in method inliner (inlinining:%s)", var1));
                  throw var56;
               } else {
                  int var54 = 0;

                  for (IDExpression var64 : var43) {
                     IDInstruction var69 = this.io.createAssign((IDExpression)var47.get(var49 + var54), var64).withOffset(var54);
                     this.io.getCfg().getBlock(0).set(var54, var69);
                     var54++;
                  }

                  this.io.verify();
                  this.io.getCfg();
                  var10000 = new Object[0];
                  if (var7) {
                     DUtil.insertHeaderBlock(this.io, var40.size(), 1);
                     var54 = 0;

                     for (IDInstruction var65 : var40) {
                        this.io.getCfg().getBlock(0).set(var54, var65);
                        var54++;
                     }

                     this.io.verify();
                     this.io.getCfg();
                     var10000 = new Object[0];
                  }

                  IDMasterOptimizer var61 = this.za.getGlobalContext().createMasterOptimizer(this.io);
                  var61.setSafeMode(true);
                  var61.setPolicyForOptimizerTag("inliner", false);
                  var61.setPolicyForOptimizerTag("slow", false);
                  bto.q(var61, ccc.class, false);
                  if (this.JY >= 0) {
                     ((bvt)var61).q(this.JY);
                  }

                  long var66 = System.currentTimeMillis();

                  try {
                     int var71 = var61.perform();
                     this.io.getCfg();
                     var10000 = new Object[]{var71};
                  } finally {
                     this.PV.za = System.currentTimeMillis() - var66;
                  }

                  this.PV.oW = System.currentTimeMillis() - var2;
                  return true;
               }
            }
         }
      }
   }

   private boolean q(BasicBlock var1, int var2, IDInstruction var3, IDCallInfo var4) {
      long var5 = System.currentTimeMillis();
      new cfs().perform(this.io);
      BasicBlock var7 = this.io.getCfg().getLast();

      for (BasicBlock var9 : this.io.getCfg()) {
         if (((IDInstruction)var9.getLast()).isReturn() && var9 != var7) {
            return false;
         }
      }

      this.io.verify();
      this.Hk = new HashMap();

      for (IDInstruction var25 : this.io.getCfg().instructions()) {
         for (int var11 : DUtil.collectUniqueVarIds(var25)) {
            IDVar var13 = this.io.getVar(var11);
            IDVar var12;
            if (!this.qa.contains(var13)) {
               var12 = (IDVar)this.Hk.get(var11);
               if (var12 == null) {
                  var12 = this.za.createVirtualVar(var13.getType());
                  this.Hk.put(var11, var12);
               }
            } else {
               int var14 = (Integer)this.Me.getKeyForValue(var13);
               var12 = this.za.getVar(var14);
               Assert.a(var12 != null);
            }

            var25.replaceVariable(var13, var12);
         }
      }

      this.io.getCfg();
      Object[] var10000 = new Object[0];
      DUtil.cleanGraph(this.io);
      if (var3.getSize() == 1) {
         DUtil.modifyInstructionSizes(this.za, var1x -> var1x == var3 ? 2 : null);
      }

      IDVar var24 = null;
      if (!var4.getType().isVoid()) {
         var24 = this.za.createVirtualVar(var4.getType());
      }

      if (!var3.isInvoke() || var3.getInvokeData() != var4) {
         Couple var26 = this.LK.getInstructionLocation(var3.getOffset());
         var1 = (BasicBlock)var26.getFirst();
         var2 = (Integer)var26.getSecond();
         int var29 = (int)var3.getOffset();
         IDInstruction var31 = this.za.createAssign(var24, var4).withOffset(var29);
         Assert.a(DUtil.replaceInExpression(var3, var4, var24));
         var3.setOffset(var29 + 1);
         var3.adjustSize(-1);
         var1.add(var2, var31);
         var3 = var31;
         var26 = this.LK.getInstructionLocation(var31.getOffset());
         var1 = (BasicBlock)var26.getFirst();
         var2 = (Integer)var26.getSecond();
         var10000 = new Object[0];
      }

      BasicBlock var28;
      if (var2 != var1.size() - 1) {
         var28 = DUtil.splitBlock(this.za, var1, var2 + 1);
      } else {
         Assert.a(var1.outsize() == 1);
         var28 = var1.getOutputBlock(0);
      }

      int var30 = (int)this.LK.getEndAddress();
      DUtil.insertHeaderBlock(this.io, 1, var30);
      this.io.getCfg();
      var10000 = new Object[0];
      CFG var32 = this.io.getCfg();
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
            var18.setContext(this.za);
            var18.updateAllPhysicalMethodIndices(this.io.getMethod().getIndex());
         }

         if (var35 >= 1) {
            var34.add(var16);
            this.LK.addBlock(var16);
         }

         var35++;
      }

      IDTryData var36 = this.za.getExceptionData();

      for (int var39 : this.io.getExceptionData().getProtectedBlocks()) {
         var36.setBlockHandlers(var39, this.io.getExceptionData().getBlockHandlers(var39));
      }

      List var38 = var36.getBlockHandlers((int)var1.getBase());
      if (!var38.isEmpty()) {
         for (BasicBlock var42 : var34) {
            for (IDExceptionHandler var20 : var38) {
               if (var36.protectBlock((int)var42.getBase(), var20.getTypeIndex(), var20.getAddress(), -1)) {
                  this.LK.addIrregularEdge(var42, this.LK.getBlockAt((long)var20.getAddress()), -1);
               }
            }
         }
      }

      IDInstruction var41 = this.za.createJump(var30).withOffset(var3.getOffset()).withSize(var3.getSize());
      var1.set(var2, var41);
      this.LK.deleteOutEdges(var1);
      this.LK.addEdge(var1, this.LK.getBlockAt((long)var30));
      IDInstruction var43 = (IDInstruction)var7.getLast();
      if (var43.isReturn()) {
         IDExpression var44 = var43.getReturnExpression();
         if (var44 == null) {
            Assert.a(var24 == null);
            var43.transformToNop();
         } else {
            var43.morph(DOpcodeType.IR_ASSIGN, var24, var44);
         }

         this.Uv = var43;
         IDInstruction var45 = this.za.createJump((int)var28.getBase()).withOffset(this.LK.getEndAddress());
         var7.add(var45);
         this.LK.addEdge(var7, var28);
      }

      var10000 = new Object[0];
      DUtil.cleanGraph(this.za);
      bto.q(this.za, this.io.getMethodSignature());
      bto.q(this.nf, this.io.getMethod());
      DUtil.verifyGraph(this.za);
      this.LK.invalidateDataFlowAnalysis();
      this.PV.gO = System.currentTimeMillis() - var5;
      return true;
   }

   public static class eo {
      public String q;
      public String RF;
      public boolean xK;
      public boolean Dw;
      public long Uv;
      public long oW;
      public long gO;
      public long nf;
      public long gP;
      public long za;

      @Override
      public String toString() {
         return Strings.ff("%s[%b]: %d ms (%d+%d) conv:%d opt1:%d, opt2:%d", this.q, this.xK, this.Uv, this.oW, this.gO, this.nf, this.gP, this.za);
      }
   }
}
