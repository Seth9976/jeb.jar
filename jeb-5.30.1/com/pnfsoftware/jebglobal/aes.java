package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTuple;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class aes {
   private static final StructuredLogger HF = aeg.q(aes.class);
   ICGlobalContext q;
   ICElementFactory RF;
   ICMethod xK;
   IERoutineContext Dw;
   CFG Uv;
   int oW;
   List gO;
   List nf;
   akv gP;
   Map za;
   Map lm;
   MultiMap zz;
   Map JY;
   private List LK = new ArrayList();
   private List io = new ArrayList();
   private int qa;
   private int Hk;
   private int Me;
   private Stack PV;
   private Stack oQ;
   private Set xW = new HashSet();
   private Set KT = new HashSet();

   public aes(ICGlobalContext var1, IERoutineContext var2) {
      this.q = var1;
      this.RF = var1.getElementFactory();
      this.Dw = var2;
      this.Uv = var2.getCfg();
      this.gO = this.Uv.getBlocks();
      this.nf = this.Uv.getInstructions();
      this.xK = ((afb)var1.getMethodFactory()).q(var2, true);
   }

   public void q(int var1) {
      this.oW = var1;
   }

   public int q() {
      return this.oW;
   }

   public ICMethod RF() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      this.Uv.getGraphRepresentation(var1, var2);
      this.gP = new akv(var1, var2);
      this.za = this.gP.xK();
      alf var3 = new alf(this.gP, this.Dw, null, null);
      var3.q(this.q());
      var3.q(new aed(this.Uv, this.za));
      alg var4 = var3.xK();
      this.lm = var4.q;
      this.zz = var4.RF;
      this.JY = var4.xK;
      if (var3.RF) {
         this.xK.setStatusCode(3);
         return this.xK;
      } else {
         this.q(this.xK.getBody());
         this.xK();
         this.Dw();
         Object[] var10000 = new Object[0];
         return this.xK;
      }
   }

   private void xK() {
      Collection var1 = this.xK.getLabelFactory().getLabels();

      for (ICLabel var3 : var1) {
         if (!this.LK.contains(var3)) {
            this.xK.insertAtOffset(var3.getOffset(), var3);
         }
      }

      for (ICLabel var8 : var1) {
         long var4 = var8.getOffset();
         Long var6 = this.Dw.convertIntermediateOffset((int)var4);
         if (var6 != null) {
            var8.addPhysicalOffset(var6);
         }
      }
   }

   private void Dw() {
      int var1 = 0;

      for (ICDecl var3 : this.xK.getIdentifierManager().getDeclarations()) {
         if (!var3.isParameterDeclaration() && !var3.getIdentifier().getIdentifierClass().isGlobal() && !this.io.contains(var3)) {
            this.xK.getBody().insert(var1, var3);
            var1++;
         }
      }
   }

   private void q(ICBlock var1) {
      this.qa = 1;
      this.Hk = this.gO.size();
      this.Me = this.Hk + 1;
      this.PV = new Stack();
      this.oQ = new Stack();

      while (true) {
         for (; this.qa < this.Me; this.qa++) {
            if (this.zz.containsKey(this.qa)) {
               ListIterator var2 = Lists.reverseIterator(this.zz.get(this.qa));

               while (var2.hasPrevious()) {
                  alb var3 = (alb)var2.previous();
                  if (var3.Dw == alc.RF) {
                     ICBlock var4 = this.RF.createBlock();
                     ICPredicate var5 = this.RF.createPredicate(this.xK.getConstantFactory().createInt32(1));
                     ICDoWhileStm var6 = this.RF.createDoWhileStm(var4, var5);
                     var1.add(var6);
                     this.PV.push(var1);
                     this.oQ.push(this.Me);
                     var1 = var4;
                     if (var3.xK > var3.RF) {
                        this.Me = Math.min(var3.xK, (Integer)this.oQ.peek());
                     } else {
                        this.Me = Math.min(var3.RF + 1, (Integer)this.oQ.peek());
                     }
                  } else {
                     ICBlock var9 = this.RF.createBlock();
                     ICPredicate var10 = this.RF.createPredicate(this.xK.getConstantFactory().createInt32(1));
                     ICWhileStm var11 = this.RF.createWhileStm(var10, var9);
                     var1.add(var11);
                     this.PV.push(var1);
                     this.oQ.push(this.Me);
                     var1 = var9;
                     if (var3.xK > var3.RF) {
                        this.Me = Math.min(var3.xK, (Integer)this.oQ.peek());
                     } else {
                        this.Me = Math.min(var3.RF + 1, (Integer)this.oQ.peek());
                     }
                  }
               }
            }

            BasicBlock var7 = (BasicBlock)this.gO.get((Integer)this.za.get(this.qa) - 1);
            this.q(var7, var1);
            if ((this.lm.containsKey(this.qa) || this.JY.containsKey(this.qa)) && var1.size() >= 1 && var1.getLast() instanceof ICSwitchStm) {
               this.xK(var7, var1);
            } else if (this.JY.containsKey(this.qa) && var1.size() >= 1 && var1.getLast() instanceof ICIfStm) {
               ICBlock[] var8 = new ICBlock[1];
               if (!this.q(var7, var1, var8)) {
                  Object[] var10000 = new Object[0];
                  this.RF(var7, var1);
               } else {
                  var1 = var8[0];
               }
            } else if (!(var1.getLast() instanceof ICGoto) && !(var1.getLast() instanceof ICReturn) && !this.xW.contains(var7.getBase())) {
               this.RF(var7, var1);
            }
         }

         if (this.PV.isEmpty()) {
            return;
         }

         var1 = (ICBlock)this.PV.pop();
         this.Me = (Integer)this.oQ.pop();
      }
   }

   private void q(BasicBlock var1, ICBlock var2) {
      Iterator var3 = var1.addressableInstructions().iterator();

      while (true) {
         long var6;
         Object var8;
         while (true) {
            if (!var3.hasNext()) {
               return;
            }

            AddressableInstruction var4 = (AddressableInstruction)var3.next();
            IEStatement var5 = (IEStatement)var4.getInstruction();
            var6 = var4.getOffset();
            if (!this.KT.contains(var6)) {
               var8 = var5.generateC(this.Dw, this.xK);
               if (var8 != null) {
                  break;
               }

               if (var6 == var1.getFirstAddress()) {
                  var8 = this.xK.getLabelFactory().create(var6);
                  break;
               }
            }
         }

         if (var8 instanceof ICDecl) {
            this.io.add((ICDecl)var8);
         } else if (var8 instanceof ICAssignment) {
            ICLeftExpression var9 = ((ICAssignment)var8).getLeft();
            if (var9 instanceof ICDecl) {
               this.io.add((ICDecl)var9);
            } else if (var9 instanceof ICTuple) {
               for (ICElement var11 : ((ICTuple)var9).getEntries()) {
                  if (var11 instanceof ICDecl) {
                     this.io.add((ICDecl)var11);
                  }
               }
            }
         }

         ((ICStatement)var8).setIntermediateOffset(var6);
         if (var8 instanceof ICLabel var12) {
            if (this.LK.contains(var12)) {
               continue;
            }

            this.LK.add(var12);
         }

         var2.add((ICStatement)var8);
      }
   }

   private void RF(BasicBlock var1, ICBlock var2) {
      if (var1.outsize() >= 1) {
         IEStatement var10000 = (IEStatement)var1.getLast();
         long var3 = var1.getEndAddress();
         ICLabel var5 = this.xK.getLabelFactory().create(var3);
         ICGoto var6 = this.RF.createGoto(var5);
         var2.add(var6);
      }
   }

   private boolean q(BasicBlock var1, ICBlock var2, ICBlock[] var3) {
      ICIfStm var4 = (ICIfStm)var2.getLast();
      akt var5 = (akt)this.JY.get(this.qa);
      List var6 = this.gP.oW(this.qa);
      int var7 = this.qa + 1;
      if (!var6.contains(var7)) {
         Object[] var24 = new Object[0];
         return false;
      } else {
         BasicBlock var8 = (BasicBlock)this.gO.get((Integer)this.za.get(var7) - 1);
         if (var1.outsize() == 2 && var1.getOutputBlocks().contains(var8)) {
            boolean var9 = var1.getOutputBlock(0) == var8;
            ICBlock var10 = var4.getBranchBody(0);
            long var11 = var1.getOutputBlock(0).getFirstAddress();
            ICLabel var13 = this.xK.getLabelFactory().create(var11);
            ICGoto var14 = this.RF.createGoto(var13);
            if (var6.contains(var5.RF)) {
               if (var9) {
                  var2.add(var10.removeLast());
                  var4.getBranchPredicate(0).reverse(this.xK.getOperatorFactory());
                  var10.add(var14);
               } else {
                  var2.add(var14);
               }

               this.PV.push(var2);
               this.oQ.push(this.Me);
               var3[0] = var10;
               this.Me = Math.min(var5.RF, (Integer)this.oQ.peek());
            } else {
               ICBlock var15 = this.RF.createBlock();
               var4.setDefaultBlock(var15);
               if (var9) {
                  var15.add(var10.removeLast());
                  var4.getBranchPredicate(0).reverse(this.xK.getOperatorFactory());
                  var10.add(var14);
               } else {
                  var15.add(var14);
               }

               BasicBlock var16 = (BasicBlock)this.gO.get((Integer)this.za.get(var5.RF) - 1);
               long var17 = var16.getFirstAddress();
               ICLabel var19 = this.xK.getLabelFactory().create(var17);
               ICGoto var20 = this.RF.createGoto(var19);
               var2.add(var20);
               int var21 = (Integer)var6.get(0);
               int var22 = (Integer)var6.get(1);
               int var23;
               if (var21 == var5.q + 1) {
                  var23 = var22;
               } else {
                  if (var22 != var5.q + 1) {
                     throw new RuntimeException();
                  }

                  var23 = var21;
               }

               this.PV.push(var2);
               this.oQ.push(this.Me);
               this.PV.push(var15);
               this.oQ.push(Math.min(var5.RF, (Integer)this.oQ.peek()));
               var3[0] = var10;
               this.Me = Math.min(var23, (Integer)this.oQ.peek());
            }

            return true;
         } else {
            Object[] var10000 = new Object[0];
            throw new RuntimeException();
         }
      }
   }

   private boolean xK(BasicBlock var1, ICBlock var2) {
      ICSwitchStm var3 = (ICSwitchStm)var2.getLast();
      akt var4 = (akt)this.lm.get(this.qa);
      if (var4 == null) {
         var4 = (akt)this.JY.get(this.qa);
      }

      List var5 = this.gP.oW(this.qa);
      Collections.sort(var5);
      if (!var5.isEmpty()) {
         int var6 = (Integer)var5.get(var5.size() - 1);
         if (var6 >= var4.RF) {
            if (var6 > var4.RF) {
               Object[] var10000 = new Object[0];
            }

            Object[] var18 = new Object[0];
         }
      }

      this.PV.push(var2);
      this.oQ.push(this.Me);
      int var16 = this.PV.size();

      for (int var7 = 0; var7 < var5.size(); var7++) {
         int var8 = (Integer)var5.get(var7);
         BasicBlock var9 = (BasicBlock)this.gO.get((Integer)this.za.get(var8) - 1);
         int var10 = (int)var9.getAddressOfInstruction(0);
         ICBlock var11 = null;

         for (ICBlock var13 : var3.getCaseBodies()) {
            ICGoto var14 = (ICGoto)var13.get(0);
            int var15 = (int)var14.getLabel().getOffset();
            if (var10 == var15) {
               var11 = var13;
               break;
            }
         }

         int var17 = var7 + 1 < var5.size() ? (Integer)var5.get(var7 + 1) : var4.RF;
         if (var11 != null) {
            this.PV.add(var16, var11);
            this.oQ.add(var16, var17);
         } else if (var3.hasDefaultBlock()) {
            this.PV.add(var16, var3.getDefaultBlock());
            this.oQ.add(var16, var17);
         }
      }

      this.Me = -1;
      return true;
   }
}
