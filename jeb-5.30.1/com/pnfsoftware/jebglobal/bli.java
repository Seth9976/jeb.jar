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

public class bli {
   private static final ILogger q = GlobalLog.getLogger(bli.class);
   private int RF;
   private boolean xK = true;
   private blz Dw;
   private bno Uv;
   private IDMethodContext oW;
   private CFG gO;
   private List nf;
   private List gP;
   private bsv za;
   private Map lm;
   private MultiMap zz;
   private Map JY;
   private TreeMap HF;
   private Set LK = new HashSet();
   private int io;
   private int qa;
   private int Hk;
   private Stack Me;
   private Stack PV;

   public bli(IJavaGlobalContext var1, IDMethodContext var2) {
      this.Dw = (blz)var1;
      this.oW = var2;
      this.Uv = (bno)var1.getMethodFactory().create(var2.getMethodSignature());
      this.gO = var2.getCfg();
      this.nf = this.gO.getBlocks();
      this.gP = this.gO.getInstructions();
   }

   public void q(int var1) {
      this.RF = var1;
   }

   public int q() {
      return this.RF;
   }

   public bno RF() {
      return this.Uv;
   }

   public bno xK() {
      if (this.gP == null) {
         throw new IllegalStateException();
      } else {
         for (IDInstruction var2 : this.gP) {
            bml var3 = (bml)var2.generateAST(this.oW, this.Uv);
            if (var3 != null) {
               this.Uv.RF(var3);
            }
         }

         this.Uv();
         Object[] var10000 = new Object[0];
         return this.Uv;
      }
   }

   public bno Dw() {
      if (this.gP == null) {
         throw new RuntimeException();
      } else {
         ArrayList var1 = new ArrayList();
         ArrayList var2 = new ArrayList();
         this.gO.getGraphRepresentation(var1, var2);
         this.za = new bsv(var1, var2);
         bmf var3 = new bmf(this.oW);

         try {
            if (var3.RF()) {
               this.HF = var3.q();
            }
         } catch (Exception var8) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var8);
         }

         if (this.HF == null) {
            bme var4 = new bme(this.oW);
            Assert.a(var4.RF());
            this.HF = var4.q();
         }

         bti var9 = new bti(this.oW);
         if (this.xK && var9.Dw()) {
            btj var10 = var9.xK();
            this.lm = var10.xK;
            this.zz = var10.q;
            this.JY = var10.RF;
         } else {
            String var5 = this.Uv.Uv() + "->" + this.Uv.getName();
            bth var6 = new bth(this.za, this.oW.getMethodSignature(), var5.getBytes(Charset.defaultCharset()));
            var6.q(this.q());
            var6.q(new blm(this.gO));
            btj var7 = var6.xK();
            this.lm = var7.xK;
            this.zz = var7.q;
            this.JY = var7.RF;
            if (var6.RF) {
            }
         }

         this.q(this.Uv.getBody());
         this.Uv();
         Object[] var10000 = new Object[0];
         return this.Uv;
      }
   }

   private static void q(IJavaBlock var0, int var1) {
      for (IJavaStatement var3 : var0) {
         if (var3 instanceof bmi) {
            for (IJavaBlock var5 : ((bmi)var3).getBlocks()) {
               q(var5, var1 + 1);
            }
         }
      }
   }

   private void Uv() {
      List var1 = this.Uv.nf().getLabels();

      for (IJavaLabel var3 : var1) {
         if (!this.LK.contains(var3)) {
            this.Uv.q(var3.getOffset(), var3);
         }
      }

      SortedMap var7 = DUtil.generateBlockOffsetMap(this.oW);

      for (IJavaLabel var4 : var1) {
         int var5 = var4.getOffset();
         Integer var6 = (Integer)var7.get(var5);
         if (var6 != null) {
            var4.setPhysicalOffset(var6);
         }
      }

      for (BasicBlock var10 : this.gO) {
         int var11 = (int)var10.getFirstAddress();
         if (var10.size() == 1 && ((IDInstruction)var10.get(0)).isJump()) {
            int var12 = (int)var10.getOutputBlock(0).getFirstAddress();
            this.Uv.nf().recordTrampoline(var11, var12);
         }
      }
   }

   private void q(IJavaBlock var1) {
      this.io = 1;
      this.qa = this.nf.size();
      this.Hk = this.qa + 1;
      this.Me = new Stack();
      this.PV = new Stack();

      while (true) {
         for (; this.io < this.Hk; this.io++) {
            List var2 = (List)this.HF.get(this.io);
            Object var3 = this.zz.get(this.io);
            if (var3 != null) {
               var3 = new ArrayList((Collection)var3);
               Collections.reverse((List)var3);
            }

            if (var2 != null && var3 != null) {
               btc var9 = null;
               if (var3 != null) {
                  for (int var12 = 0; var12 < var3.size(); var12++) {
                     btc var6 = (btc)var3.get(var12);
                     if (var6.Dw() == btd.q) {
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

               var13.sort(new blj(this));

               for (bsy var7 : var13) {
                  if (var7 instanceof bmg) {
                     var1 = this.q((bmg)var7, (IJavaBlock)var1);
                  } else {
                     var1 = this.q((btc)var7, (IJavaBlock)var1);
                  }
               }

               if (var9 != null) {
                  var1 = this.q(var9, (IJavaBlock)var1);
               }
            } else if (var2 != null) {
               for (bmg var5 : var2) {
                  var1 = this.q(var5, (IJavaBlock)var1);
               }
            } else if (var3 != null) {
               for (btc var11 : var3) {
                  var1 = this.q(var11, (IJavaBlock)var1);
               }
            }

            BasicBlock var10 = (BasicBlock)this.nf.get(this.io - 1);
            this.q(var10, (IJavaBlock)var1);
            if ((this.lm.containsKey(this.io) || this.JY.containsKey(this.io)) && ((IJavaBlock)var1).size() >= 1 && ((IJavaBlock)var1).getLast() instanceof bnz
               )
             {
               this.xK(var10, (IJavaBlock)var1);
            } else if (this.JY.containsKey(this.io) && ((IJavaBlock)var1).size() >= 1 && ((IJavaBlock)var1).getLast() instanceof bne) {
               bmo[] var14 = new bmo[1];
               if (!this.q(var10, (IJavaBlock)var1, var14)) {
                  Object[] var10000 = new Object[0];
                  this.RF(var10, (IJavaBlock)var1);
               } else {
                  var1 = var14[0];
               }
            } else {
               this.RF(var10, (IJavaBlock)var1);
            }
         }

         if (this.Me.isEmpty()) {
            return;
         }

         var1 = (IJavaBlock)this.Me.pop();
         this.Hk = (Integer)this.PV.pop();
      }
   }

   private void q(BasicBlock var1, IJavaBlock var2) {
      boolean var3 = this.Uv.JY;

      try {
         this.Uv.JY = true;

         for (IDInstruction var5 : var1) {
            long var6 = var5.getOffset();
            Object var8 = (bml)var5.generateAST(this.oW, this.Uv);
            if (var8 == null) {
               if (var6 != (int)var1.getFirstAddress()) {
                  continue;
               }

               var8 = this.Uv.nf().create((int)var6);
            }

            if (var8 instanceof bmr) {
               var8 = this.Uv.nf().create((int)var6);
            }

            var2.add((IJavaStatement)var8);
         }
      } finally {
         this.Uv.JY = var3;
      }
   }

   private void RF(BasicBlock var1, IJavaBlock var2) {
      if (var1.outsize() >= 1) {
         IDInstruction var3 = (IDInstruction)var1.getLast();
         int var4 = (int)var3.getOffset() + var3.getSize();
         IJavaLabel var5 = this.Uv.nf().create(var4);
         bnb var6 = new bnb(var5);
         var2.add(var6);
      }
   }

   private IJavaBlock q(bmg var1, IJavaBlock var2) {
      IJavaBlock var3 = this.Dw.createBlock();
      bod var4 = (bod)this.Dw.createTry(var3);
      var2.add(var4);
      this.Me.push(var2);
      this.PV.push(this.Hk);
      boolean var5 = var1.Dw;
      if (var5) {
         for (bll var7 : var1.xK) {
            if (var7.q >= this.Hk || var7.RF != -1 && var7.RF >= this.Hk) {
               var5 = false;
               break;
            }
         }
      }

      if (!var5) {
         for (int var15 = 0; var15 < var1.xK.size(); var15++) {
            bll var17 = (bll)var1.xK.get(var15);
            IJavaIdentifier var8 = null;
            bmx var9 = null;
            if (var17.Uv != null) {
               bmr var10 = (bmr)var17.Uv.generateAST(this.oW, this.Uv);
               var8 = var10.Uv();
               var9 = var10.Dw();
            }

            IJavaBlock var21 = this.Dw.createBlock();
            var4.addCatchBlock(var17.xK, var17.Dw, var8, var9, var21);
            BasicBlock var11 = (BasicBlock)this.nf.get(var17.q - 1);
            IDInstruction var12 = (IDInstruction)var11.get(0);
            int var13 = (int)var12.getOffset();
            if (var12.isStoreException()) {
               var13 += var12.getSize();
            }

            IJavaLabel var14 = this.Uv.nf().create(var13);
            var21.add(new bnb(var14));
         }
      } else {
         for (int var16 = var1.xK.size() - 1; var16 >= 0; var16--) {
            bll var18 = (bll)var1.xK.get(var16);
            IJavaIdentifier var19 = null;
            bmx var20 = null;
            if (var18.Uv != null) {
               bmr var22 = (bmr)var18.Uv.generateAST(this.oW, this.Uv);
               var19 = var22.Uv();
               var20 = var22.Dw();
            }

            IJavaBlock var23 = this.Dw.createBlock();
            var4.addCatchBlock(0, var18.xK, var18.Dw, var19, var20, var23);
            BasicBlock var24 = (BasicBlock)this.nf.get(var18.q - 1);
            IDInstruction var25 = (IDInstruction)var24.get(0);
            int var26 = (int)var25.getOffset();
            if (var25.isStoreException()) {
               var26 += var25.getSize();
            }

            IJavaLabel var27 = this.Uv.nf().create(var26);
            var23.add(new bnb(var27));
            if (var18.RF != -1) {
               this.Me.push(var23);
               this.PV.push(var18.RF + 1);
            }
         }
      }

      this.Hk = var1.RF + 1;
      return var3;
   }

   private IJavaBlock q(btc var1, IJavaBlock var2) {
      if (var1.q == btd.RF) {
         IJavaBlock var3 = this.Dw.createBlock();
         IJavaPredicate var4 = this.Dw.createPredicate(this.Dw.Uv().createBoolean(true));
         IJavaDoWhile var5 = this.Dw.createDoWhile(var3, var4);
         var2.add(var5);
         this.Me.push(var2);
         this.PV.push(this.Hk);
         var2 = var3;
         if (var1.Dw > var1.xK) {
            this.Hk = Math.min(var1.Dw, (Integer)this.PV.peek());
         } else {
            this.Hk = Math.min(var1.xK + 1, (Integer)this.PV.peek());
         }
      } else {
         IJavaBlock var7 = this.Dw.createBlock();
         IJavaPredicate var8 = this.Dw.createPredicate(this.Dw.Uv().createBoolean(true));
         IJavaWhile var9 = this.Dw.createWhile(var8, var7);
         var2.add(var9);
         this.Me.push(var2);
         this.PV.push(this.Hk);
         var2 = var7;
         if (var1.Dw > var1.xK) {
            this.Hk = Math.min(var1.Dw, (Integer)this.PV.peek());
         } else {
            this.Hk = Math.min(var1.xK + 1, (Integer)this.PV.peek());
         }
      }

      return var2;
   }

   private boolean q(BasicBlock var1, IJavaBlock var2, IJavaBlock[] var3) {
      bne var4 = (bne)var2.getLast();
      bss var5 = (bss)this.JY.get(this.io);
      List var6 = this.za.oW(this.io);
      int var7 = this.io + 1;
      if (!var6.contains(var7)) {
         Object[] var22 = new Object[0];
         return false;
      } else {
         BasicBlock var8 = (BasicBlock)this.nf.get(var7 - 1);
         if (var1.outsize() == 2 && var1.getOutputBlocks().contains(var8)) {
            boolean var9 = var1.getOutputBlock(0) == var8;
            IJavaBlock var10 = var4.getBranchBody(0);
            int var11 = (int)((IDInstruction)var1.getOutputBlock(0).get(0)).getOffset();
            IJavaLabel var12 = this.Uv.nf().create(var11);
            bnb var13 = new bnb(var12);
            if (var6.contains(var5.xK)) {
               if (var9) {
                  var2.add(var10.removeLast());
                  var4.getBranchPredicate(0).reverse(this.Dw.Dw());
                  var10.add(var13);
               } else {
                  var2.add(var13);
               }

               this.Me.push(var2);
               this.PV.push(this.Hk);
               var3[0] = var10;
               this.Hk = Math.min(var5.xK, (Integer)this.PV.peek());
            } else {
               IJavaBlock var14 = this.Dw.createBlock();
               var4.setDefaultBlock(var14);
               if (var9) {
                  var14.add(var10.removeLast());
                  var4.getBranchPredicate(0).reverse(this.Dw.Dw());
                  var10.add(var13);
               } else {
                  var14.add(var13);
               }

               BasicBlock var15 = (BasicBlock)this.nf.get(var5.xK - 1);
               int var16 = (int)((IDInstruction)var15.get(0)).getOffset();
               IJavaLabel var17 = this.Uv.nf().create(var16);
               bnb var18 = new bnb(var17);
               var2.add(var18);
               int var19 = (Integer)var6.get(0);
               int var20 = (Integer)var6.get(1);
               int var21;
               if (var19 == var5.RF + 1) {
                  var21 = var20;
               } else {
                  if (var20 != var5.RF + 1) {
                     throw new RuntimeException();
                  }

                  var21 = var19;
               }

               this.Me.push(var2);
               this.PV.push(this.Hk);
               this.Me.push(var14);
               this.PV.push(Math.min(var5.xK, (Integer)this.PV.peek()));
               var3[0] = var10;
               this.Hk = Math.min(var21, (Integer)this.PV.peek());
            }

            return true;
         } else {
            Object[] var10000 = new Object[0];
            throw new RuntimeException();
         }
      }
   }

   private boolean xK(BasicBlock var1, IJavaBlock var2) {
      bnz var3 = (bnz)var2.getLast();
      bss var4 = (bss)this.lm.get(this.io);
      if (var4 == null) {
         var4 = (bss)this.JY.get(this.io);
      }

      List var5 = this.za.oW(this.io);
      BasicBlock var6 = (BasicBlock)this.nf.get(var4.xK - 1);
      int var7 = -1;
      if (var4.xK == var4.RF + 1 || var1.getOutputBlock(0) != var6) {
         var3.setDefaultBlock(this.Dw.createBlock());
         BasicBlock var8 = var1.getOutputBlock(0);

         for (int var10 : var5) {
            if (this.nf.get(var10 - 1) == var8) {
               var7 = var10;
               break;
            }
         }
      }

      Collections.sort(var5);
      this.Me.push(var2);
      this.PV.push(this.Hk);
      int var18 = this.Me.size();

      for (int var19 = 0; var19 < var5.size(); var19++) {
         int var20 = (Integer)var5.get(var19);
         BasicBlock var11 = (BasicBlock)this.nf.get(var20 - 1);
         int var12 = (int)((IDInstruction)var11.get(0)).getOffset();
         IJavaBlock var13 = null;

         for (IJavaBlock var15 : var3.getCaseBodies()) {
            bnb var16 = (bnb)var15.get(0);
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
            var22 = var4.xK;
            if (var4.Dw && var4.xK == this.qa) {
               var22++;
            }
         }

         if (var13 != null) {
            this.q(var18, var13, var22);
         } else if (var7 == var20) {
            bmo var21 = var3.Dw();
            this.q(var18, var21, var22);
         }
      }

      this.Hk = -1;
      return true;
   }

   private void q(int var1, IJavaBlock var2, int var3) {
      this.Me.add(var1, var2);
      int var4 = (Integer)Collections.min(this.PV.subList(0, var1));
      this.PV.add(var1, Math.min(var3, var4));
   }
}
