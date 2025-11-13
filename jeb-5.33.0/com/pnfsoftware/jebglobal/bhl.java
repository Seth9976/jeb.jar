package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGlobalContext;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;

public class bhl {
   private static final ILogger pC = GlobalLog.getLogger(bhl.class);
   private int A;
   private boolean kS = true;
   private bic wS;
   private bjr UT;
   private IDMethodContext E;
   private CFG sY;
   private List ys;
   private List ld;
   private bou gp;
   private Map oT;
   private MultiMap fI;
   private Map WR;
   private TreeMap NS;
   private Set vP = new HashSet();
   private int xC;
   private int ED;
   private int Sc;
   private Stack ah;
   private Stack eP;

   public bhl(IJavaGlobalContext var1, IDMethodContext var2) {
      this.wS = (bic)var1;
      this.E = var2;
      this.UT = (bjr)var1.getMethodFactory().create(var2.getMethodSignature());
      this.sY = var2.getCfg();
      this.ys = this.sY.getBlocks();
      this.ld = this.sY.getInstructions();
   }

   public void pC(int var1) {
      this.A = var1;
   }

   public int pC() {
      return this.A;
   }

   public bjr A() {
      if (this.ld == null) {
         throw new RuntimeException();
      } else {
         ArrayList var1 = new ArrayList();
         ArrayList var2 = new ArrayList();
         this.sY.getGraphRepresentation(var1, var2);
         this.gp = new bou(var1, var2);
         bii var3 = new bii(this.E);

         try {
            if (var3.A()) {
               this.NS = var3.pC();
            }
         } catch (Exception var8) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var8);
         }

         if (this.NS == null) {
            bih var4 = new bih(this.E);
            Assert.a(var4.A());
            this.NS = var4.pC();
         }

         bpf var9 = new bpf(this.E);
         if (this.kS && var9.A()) {
            bpg var10 = var9.pC();
            this.oT = var10.kS;
            this.fI = var10.pC;
            this.WR = var10.A;
         } else {
            String var5 = this.UT.wS() + "->" + this.UT.getName();
            bpe var6 = new bpe(this.gp, this.E.getMethodSignature(), var5.getBytes(Charset.defaultCharset()));
            var6.pC(this.pC());
            var6.pC(new bhp(this.sY));
            bpg var7 = var6.A();
            this.oT = var7.kS;
            this.fI = var7.pC;
            this.WR = var7.A;
            if (var6.pC) {
            }
         }

         this.pC(this.UT.getBody());
         this.kS();
         Object[] var10000 = new Object[0];
         return this.UT;
      }
   }

   private void kS() {
      List var1 = this.UT.E().getLabels();

      for (IJavaLabel var3 : var1) {
         if (!this.vP.contains(var3)) {
            this.UT.pC(var3.getOffset(), var3);
         }
      }

      SortedMap var7 = DUtil.generateBlockOffsetMap(this.E);

      for (IJavaLabel var4 : var1) {
         int var5 = var4.getOffset();
         Integer var6 = (Integer)var7.get(var5);
         if (var6 != null) {
            var4.setPhysicalOffset(var6);
         }
      }

      for (BasicBlock var10 : this.sY) {
         int var11 = (int)var10.getFirstAddress();
         if (var10.size() == 1 && ((IDInstruction)var10.get(0)).isJump()) {
            int var12 = (int)var10.getOutputBlock(0).getFirstAddress();
            this.UT.E().recordTrampoline(var11, var12);
         }
      }
   }

   private void pC(IJavaBlock var1) {
      this.xC = 1;
      this.ED = this.ys.size();
      this.Sc = this.ED + 1;
      this.ah = new Stack();
      this.eP = new Stack();

      while (true) {
         for (; this.xC < this.Sc; this.xC++) {
            List var2 = (List)this.NS.get(this.xC);
            Object var3 = this.fI.get(this.xC);
            if (var3 != null) {
               var3 = new ArrayList((Collection)var3);
               Collections.reverse((List)var3);
            }

            if (var2 != null && var3 != null) {
               bpa var9 = null;
               if (var3 != null) {
                  for (int var12 = 0; var12 < var3.size(); var12++) {
                     bpa var6 = (bpa)var3.get(var12);
                     if (var6.A() == bpb.pC) {
                        Assert.a(var12 == var3.size() - 1);
                        var9 = var6;
                        var3.remove(var12);
                        break;
                     }
                  }
               }

               ArrayList var13 = new ArrayList();
               if (var2 != null) {
                  var13.addAll(var2);
               }

               if (var3 != null) {
                  var13.addAll((Collection)var3);
               }

               var13.sort(new bhm(this));

               for (bow var7 : var13) {
                  if (var7 instanceof bij) {
                     var1 = this.pC((bij)var7, (IJavaBlock)var1);
                  } else {
                     var1 = this.pC((bpa)var7, (IJavaBlock)var1);
                  }
               }

               if (var9 != null) {
                  var1 = this.pC(var9, (IJavaBlock)var1);
               }
            } else if (var2 != null) {
               for (bij var5 : var2) {
                  var1 = this.pC(var5, (IJavaBlock)var1);
               }
            } else if (var3 != null) {
               for (bpa var11 : var3) {
                  var1 = this.pC(var11, (IJavaBlock)var1);
               }
            }

            BasicBlock var10 = (BasicBlock)this.ys.get(this.xC - 1);
            this.pC(var10, (IJavaBlock)var1);
            if ((this.oT.containsKey(this.xC) || this.WR.containsKey(this.xC)) && ((IJavaBlock)var1).size() >= 1 && ((IJavaBlock)var1).getLast() instanceof bkc
               )
             {
               this.kS(var10, (IJavaBlock)var1);
            } else if (this.WR.containsKey(this.xC) && ((IJavaBlock)var1).size() >= 1 && ((IJavaBlock)var1).getLast() instanceof bjh) {
               bir[] var14 = new bir[1];
               if (!this.pC(var10, (IJavaBlock)var1, var14)) {
                  Object[] var10000 = new Object[0];
                  this.A(var10, (IJavaBlock)var1);
               } else {
                  var1 = var14[0];
               }
            } else {
               this.A(var10, (IJavaBlock)var1);
            }
         }

         if (this.ah.isEmpty()) {
            return;
         }

         var1 = (IJavaBlock)this.ah.pop();
         this.Sc = (Integer)this.eP.pop();
      }
   }

   private void pC(BasicBlock var1, IJavaBlock var2) {
      boolean var3 = this.UT.ys;

      try {
         this.UT.ys = true;

         for (IDInstruction var5 : var1) {
            long var6 = var5.getOffset();
            Object var8 = (bio)var5.generateAST(this.E, this.UT);
            if (var8 == null) {
               if (var6 != (int)var1.getFirstAddress()) {
                  continue;
               }

               var8 = this.UT.E().create((int)var6);
            }

            if (var8 instanceof biu) {
               var8 = this.UT.E().create((int)var6);
            }

            var2.add((IJavaStatement)var8);
         }
      } finally {
         this.UT.ys = var3;
      }
   }

   private void A(BasicBlock var1, IJavaBlock var2) {
      if (var1.outsize() >= 1) {
         IDInstruction var3 = (IDInstruction)var1.getLast();
         int var4 = (int)var3.getOffset() + var3.getSize();
         IJavaLabel var5 = this.UT.E().create(var4);
         bje var6 = new bje(var5);
         var2.add(var6);
      }
   }

   private IJavaBlock pC(bij var1, IJavaBlock var2) {
      IJavaBlock var3 = this.wS.createBlock();
      bkg var4 = (bkg)this.wS.createTry(var3);
      var2.add(var4);
      this.ah.push(var2);
      this.eP.push(this.Sc);
      boolean var5 = var1.wS;
      if (var5) {
         for (bho var7 : var1.kS) {
            if (var7.pC >= this.Sc || var7.A != -1 && var7.A >= this.Sc) {
               var5 = false;
               break;
            }
         }
      }

      if (!var5) {
         for (int var15 = 0; var15 < var1.kS.size(); var15++) {
            bho var17 = (bho)var1.kS.get(var15);
            IJavaIdentifier var8 = null;
            bja var9 = null;
            if (var17.UT != null) {
               biu var10 = (biu)var17.UT.generateAST(this.E, this.UT);
               var8 = var10.kS();
               var9 = var10.A();
            }

            IJavaBlock var21 = this.wS.createBlock();
            var4.addCatchBlock(var17.kS, var17.wS, var8, var9, var21);
            BasicBlock var11 = (BasicBlock)this.ys.get(var17.pC - 1);
            IDInstruction var12 = (IDInstruction)var11.get(0);
            int var13 = (int)var12.getOffset();
            if (var12.isStoreException()) {
               var13 += var12.getSize();
            }

            IJavaLabel var14 = this.UT.E().create(var13);
            var21.add(new bje(var14));
         }
      } else {
         for (int var16 = var1.kS.size() - 1; var16 >= 0; var16--) {
            bho var18 = (bho)var1.kS.get(var16);
            IJavaIdentifier var19 = null;
            bja var20 = null;
            if (var18.UT != null) {
               biu var22 = (biu)var18.UT.generateAST(this.E, this.UT);
               var19 = var22.kS();
               var20 = var22.A();
            }

            IJavaBlock var23 = this.wS.createBlock();
            var4.addCatchBlock(0, var18.kS, var18.wS, var19, var20, var23);
            BasicBlock var24 = (BasicBlock)this.ys.get(var18.pC - 1);
            IDInstruction var25 = (IDInstruction)var24.get(0);
            int var26 = (int)var25.getOffset();
            if (var25.isStoreException()) {
               var26 += var25.getSize();
            }

            IJavaLabel var27 = this.UT.E().create(var26);
            var23.add(new bje(var27));
            if (var18.A != -1) {
               this.ah.push(var23);
               this.eP.push(var18.A + 1);
            }
         }
      }

      this.Sc = var1.A + 1;
      return var3;
   }

   private IJavaBlock pC(bpa var1, IJavaBlock var2) {
      if (var1.pC == bpb.A) {
         IJavaBlock var3 = this.wS.createBlock();
         IJavaPredicate var4 = this.wS.createPredicate(this.wS.wS().createBoolean(true));
         IJavaDoWhile var5 = this.wS.createDoWhile(var3, var4);
         var2.add(var5);
         this.ah.push(var2);
         this.eP.push(this.Sc);
         var2 = var3;
         if (var1.wS > var1.kS) {
            this.Sc = Math.min(var1.wS, (Integer)this.eP.peek());
         } else {
            this.Sc = Math.min(var1.kS + 1, (Integer)this.eP.peek());
         }
      } else {
         IJavaBlock var7 = this.wS.createBlock();
         IJavaPredicate var8 = this.wS.createPredicate(this.wS.wS().createBoolean(true));
         IJavaWhile var9 = this.wS.createWhile(var8, var7);
         var2.add(var9);
         this.ah.push(var2);
         this.eP.push(this.Sc);
         var2 = var7;
         if (var1.wS > var1.kS) {
            this.Sc = Math.min(var1.wS, (Integer)this.eP.peek());
         } else {
            this.Sc = Math.min(var1.kS + 1, (Integer)this.eP.peek());
         }
      }

      return var2;
   }

   private boolean pC(BasicBlock var1, IJavaBlock var2, IJavaBlock[] var3) {
      bjh var4 = (bjh)var2.getLast();
      bor var5 = (bor)this.WR.get(this.xC);
      List var6 = this.gp.wS(this.xC);
      int var7 = this.xC + 1;
      if (!var6.contains(var7)) {
         Object[] var22 = new Object[0];
         return false;
      } else {
         BasicBlock var8 = (BasicBlock)this.ys.get(var7 - 1);
         if (var1.outsize() == 2 && var1.getOutputs().contains(var8)) {
            boolean var9 = var1.getOutputBlock(0) == var8;
            IJavaBlock var10 = var4.getBranchBody(0);
            int var11 = (int)((IDInstruction)var1.getOutputBlock(0).get(0)).getOffset();
            IJavaLabel var12 = this.UT.E().create(var11);
            bje var13 = new bje(var12);
            if (var6.contains(var5.kS)) {
               if (var9) {
                  var2.add(var10.removeLast());
                  var4.getBranchPredicate(0).reverse(this.wS.kS());
                  var10.add(var13);
               } else {
                  var2.add(var13);
               }

               this.ah.push(var2);
               this.eP.push(this.Sc);
               var3[0] = var10;
               this.Sc = Math.min(var5.kS, (Integer)this.eP.peek());
            } else {
               IJavaBlock var14 = this.wS.createBlock();
               var4.setDefaultBlock(var14);
               if (var9) {
                  var14.add(var10.removeLast());
                  var4.getBranchPredicate(0).reverse(this.wS.kS());
                  var10.add(var13);
               } else {
                  var14.add(var13);
               }

               BasicBlock var15 = (BasicBlock)this.ys.get(var5.kS - 1);
               int var16 = (int)((IDInstruction)var15.get(0)).getOffset();
               IJavaLabel var17 = this.UT.E().create(var16);
               bje var18 = new bje(var17);
               var2.add(var18);
               int var19 = (Integer)var6.get(0);
               int var20 = (Integer)var6.get(1);
               int var21;
               if (var19 == var5.A + 1) {
                  var21 = var20;
               } else {
                  if (var20 != var5.A + 1) {
                     throw new RuntimeException();
                  }

                  var21 = var19;
               }

               this.ah.push(var2);
               this.eP.push(this.Sc);
               this.ah.push(var14);
               this.eP.push(Math.min(var5.kS, (Integer)this.eP.peek()));
               var3[0] = var10;
               this.Sc = Math.min(var21, (Integer)this.eP.peek());
            }

            return true;
         } else {
            Object[] var10000 = new Object[0];
            throw new RuntimeException();
         }
      }
   }

   private boolean kS(BasicBlock var1, IJavaBlock var2) {
      bkc var3 = (bkc)var2.getLast();
      bor var4 = (bor)this.oT.get(this.xC);
      if (var4 == null) {
         var4 = (bor)this.WR.get(this.xC);
      }

      List var5 = this.gp.wS(this.xC);
      BasicBlock var6 = (BasicBlock)this.ys.get(var4.kS - 1);
      int var7 = -1;
      if (var4.kS == var4.A + 1 || var1.getOutputBlock(0) != var6) {
         var3.setDefaultBlock(this.wS.createBlock());
         BasicBlock var8 = var1.getOutputBlock(0);

         for (int var10 : var5) {
            if (this.ys.get(var10 - 1) == var8) {
               var7 = var10;
               break;
            }
         }
      }

      Collections.sort(var5);
      this.ah.push(var2);
      this.eP.push(this.Sc);
      int var18 = this.ah.size();

      for (int var19 = 0; var19 < var5.size(); var19++) {
         int var20 = (Integer)var5.get(var19);
         BasicBlock var11 = (BasicBlock)this.ys.get(var20 - 1);
         int var12 = (int)((IDInstruction)var11.get(0)).getOffset();
         IJavaBlock var13 = null;

         for (IJavaBlock var15 : var3.getCaseBodies()) {
            bje var16 = (bje)var15.get(0);
            int var17 = var16.getLabel().getOffset();
            if (var12 == var17) {
               var13 = var15;
               break;
            }
         }

         int var22;
         if (var19 + 1 < var5.size()) {
            var22 = (Integer)var5.get(var19 + 1);
         } else {
            var22 = var4.kS;
            if (var4.wS && var4.kS == this.ED) {
               var22++;
            }
         }

         if (var13 != null) {
            this.pC(var18, var13, var22);
         } else if (var7 == var20) {
            bir var21 = var3.A();
            this.pC(var18, var21, var22);
         }
      }

      this.Sc = -1;
      return true;
   }

   private void pC(int var1, IJavaBlock var2, int var3) {
      this.ah.add(var1, var2);
      int var4 = (Integer)Collections.min(this.eP.subList(0, var1));
      this.eP.add(var1, Math.min(var3, var4));
   }
}
