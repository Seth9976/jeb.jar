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

public class ada {
   private static final StructuredLogger NS = aco.pC(ada.class);
   ICGlobalContext pC;
   ICElementFactory A;
   ICMethod kS;
   IERoutineContext wS;
   CFG UT;
   int E;
   List sY;
   List ys;
   aiw ld;
   Map gp;
   Map oT;
   MultiMap fI;
   Map WR;
   private List vP = new ArrayList();
   private List xC = new ArrayList();
   private int ED;
   private int Sc;
   private int ah;
   private Stack eP;
   private Stack UO;
   private Set Ab = new HashSet();
   private Set rl = new HashSet();

   public ada(ICGlobalContext var1, IERoutineContext var2) {
      this.pC = var1;
      this.A = var1.getElementFactory();
      this.wS = var2;
      this.UT = var2.getCfg();
      this.sY = this.UT.getBlocks();
      this.ys = this.UT.getInstructions();
      this.kS = ((adi)var1.getMethodFactory()).pC(var2, true);
   }

   public void pC(int var1) {
      this.E = var1;
   }

   public int pC() {
      return this.E;
   }

   public ICMethod A() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      this.UT.getGraphRepresentation(var1, var2);
      this.ld = new aiw(var1, var2);
      this.gp = this.ld.pC();
      ajd var3 = new ajd(this.ld, this.wS, null, null);
      var3.pC(this.pC());
      var3.pC(new acl(this.UT, this.gp));
      aje var4 = var3.A();
      this.oT = var4.pC;
      this.fI = var4.A;
      this.WR = var4.kS;
      if (var3.pC) {
         this.kS.setStatusCode(3);
         return this.kS;
      } else {
         this.pC(this.kS.getBody());
         this.kS();
         this.wS();
         Object[] var10000 = new Object[0];
         return this.kS;
      }
   }

   private void kS() {
      Collection var1 = this.kS.getLabelFactory().getLabels();

      for (ICLabel var3 : var1) {
         if (!this.vP.contains(var3)) {
            this.kS.insertAtOffset(var3.getOffset(), var3);
         }
      }

      for (ICLabel var8 : var1) {
         long var4 = var8.getOffset();
         Long var6 = this.wS.convertIntermediateOffset((int)var4);
         if (var6 != null) {
            var8.addPhysicalOffset(var6);
         }
      }
   }

   private void wS() {
      int var1 = 0;

      for (ICDecl var3 : this.kS.getIdentifierManager().getDeclarations()) {
         if (!var3.isParameterDeclaration() && !var3.getIdentifier().getIdentifierClass().isGlobal() && !this.xC.contains(var3)) {
            this.kS.getBody().insert(var1, var3);
            var1++;
         }
      }
   }

   private void pC(ICBlock var1) {
      this.ED = 1;
      this.Sc = this.sY.size();
      this.ah = this.Sc + 1;
      this.eP = new Stack();
      this.UO = new Stack();

      while (true) {
         for (; this.ED < this.ah; this.ED++) {
            if (this.fI.containsKey(this.ED)) {
               ListIterator var2 = Lists.reverseIterator(this.fI.get(this.ED));

               while (var2.hasPrevious()) {
                  aiz var3 = (aiz)var2.previous();
                  if (var3.wS == aja.A) {
                     ICBlock var4 = this.A.createBlock();
                     ICPredicate var5 = this.A.createPredicate(this.kS.getConstantFactory().createInt32(1));
                     ICDoWhileStm var6 = this.A.createDoWhileStm(var4, var5);
                     var1.add(var6);
                     this.eP.push(var1);
                     this.UO.push(this.ah);
                     var1 = var4;
                     if (var3.kS > var3.A) {
                        this.ah = Math.min(var3.kS, (Integer)this.UO.peek());
                     } else {
                        this.ah = Math.min(var3.A + 1, (Integer)this.UO.peek());
                     }
                  } else {
                     ICBlock var9 = this.A.createBlock();
                     ICPredicate var10 = this.A.createPredicate(this.kS.getConstantFactory().createInt32(1));
                     ICWhileStm var11 = this.A.createWhileStm(var10, var9);
                     var1.add(var11);
                     this.eP.push(var1);
                     this.UO.push(this.ah);
                     var1 = var9;
                     if (var3.kS > var3.A) {
                        this.ah = Math.min(var3.kS, (Integer)this.UO.peek());
                     } else {
                        this.ah = Math.min(var3.A + 1, (Integer)this.UO.peek());
                     }
                  }
               }
            }

            BasicBlock var7 = (BasicBlock)this.sY.get((Integer)this.gp.get(this.ED) - 1);
            this.pC(var7, var1);
            if ((this.oT.containsKey(this.ED) || this.WR.containsKey(this.ED)) && var1.size() >= 1 && var1.getLast() instanceof ICSwitchStm) {
               this.kS(var7, var1);
            } else if (this.WR.containsKey(this.ED) && var1.size() >= 1 && var1.getLast() instanceof ICIfStm) {
               ICBlock[] var8 = new ICBlock[1];
               if (!this.pC(var7, var1, var8)) {
                  Object[] var10000 = new Object[0];
                  this.A(var7, var1);
               } else {
                  var1 = var8[0];
               }
            } else if (!(var1.getLast() instanceof ICGoto) && !(var1.getLast() instanceof ICReturn) && !this.Ab.contains(var7.getBase())) {
               this.A(var7, var1);
            }
         }

         if (this.eP.isEmpty()) {
            return;
         }

         var1 = (ICBlock)this.eP.pop();
         this.ah = (Integer)this.UO.pop();
      }
   }

   private void pC(BasicBlock var1, ICBlock var2) {
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
            if (!this.rl.contains(var6)) {
               var8 = var5.generateC(this.wS, this.kS);
               if (var8 != null) {
                  break;
               }

               if (var6 == var1.getFirstAddress()) {
                  var8 = this.kS.getLabelFactory().create(var6);
                  break;
               }
            }
         }

         if (var8 instanceof ICDecl) {
            this.xC.add((ICDecl)var8);
         } else if (var8 instanceof ICAssignment) {
            ICLeftExpression var9 = ((ICAssignment)var8).getLeft();
            if (var9 instanceof ICDecl) {
               this.xC.add((ICDecl)var9);
            } else if (var9 instanceof ICTuple) {
               for (ICElement var11 : ((ICTuple)var9).getEntries()) {
                  if (var11 instanceof ICDecl) {
                     this.xC.add((ICDecl)var11);
                  }
               }
            }
         }

         ((ICStatement)var8).setIntermediateOffset(var6);
         if (var8 instanceof ICLabel var12) {
            if (this.vP.contains(var12)) {
               continue;
            }

            this.vP.add(var12);
         }

         var2.add((ICStatement)var8);
      }
   }

   private void A(BasicBlock var1, ICBlock var2) {
      if (var1.outsize() >= 1) {
         IEStatement var10000 = (IEStatement)var1.getLast();
         long var3 = var1.getEndAddress();
         ICLabel var5 = this.kS.getLabelFactory().create(var3);
         ICGoto var6 = this.A.createGoto(var5);
         var2.add(var6);
      }
   }

   private boolean pC(BasicBlock var1, ICBlock var2, ICBlock[] var3) {
      ICIfStm var4 = (ICIfStm)var2.getLast();
      aiu var5 = (aiu)this.WR.get(this.ED);
      List var6 = this.ld.UT(this.ED);
      int var7 = this.ED + 1;
      if (!var6.contains(var7)) {
         Object[] var24 = new Object[0];
         return false;
      } else {
         BasicBlock var8 = (BasicBlock)this.sY.get((Integer)this.gp.get(var7) - 1);
         if (var1.outsize() == 2 && var1.getOutputs().contains(var8)) {
            boolean var9 = var1.getOutputBlock(0) == var8;
            ICBlock var10 = var4.getBranchBody(0);
            long var11 = var1.getOutputBlock(0).getFirstAddress();
            ICLabel var13 = this.kS.getLabelFactory().create(var11);
            ICGoto var14 = this.A.createGoto(var13);
            if (var6.contains(var5.A)) {
               if (var9) {
                  var2.add(var10.removeLast());
                  var4.getBranchPredicate(0).reverse(this.kS.getOperatorFactory());
                  var10.add(var14);
               } else {
                  var2.add(var14);
               }

               this.eP.push(var2);
               this.UO.push(this.ah);
               var3[0] = var10;
               this.ah = Math.min(var5.A, (Integer)this.UO.peek());
            } else {
               ICBlock var15 = this.A.createBlock();
               var4.setDefaultBlock(var15);
               if (var9) {
                  var15.add(var10.removeLast());
                  var4.getBranchPredicate(0).reverse(this.kS.getOperatorFactory());
                  var10.add(var14);
               } else {
                  var15.add(var14);
               }

               BasicBlock var16 = (BasicBlock)this.sY.get((Integer)this.gp.get(var5.A) - 1);
               long var17 = var16.getFirstAddress();
               ICLabel var19 = this.kS.getLabelFactory().create(var17);
               ICGoto var20 = this.A.createGoto(var19);
               var2.add(var20);
               int var21 = (Integer)var6.get(0);
               int var22 = (Integer)var6.get(1);
               int var23;
               if (var21 == var5.pC + 1) {
                  var23 = var22;
               } else {
                  if (var22 != var5.pC + 1) {
                     throw new RuntimeException();
                  }

                  var23 = var21;
               }

               this.eP.push(var2);
               this.UO.push(this.ah);
               this.eP.push(var15);
               this.UO.push(Math.min(var5.A, (Integer)this.UO.peek()));
               var3[0] = var10;
               this.ah = Math.min(var23, (Integer)this.UO.peek());
            }

            return true;
         } else {
            Object[] var10000 = new Object[0];
            throw new RuntimeException();
         }
      }
   }

   private boolean kS(BasicBlock var1, ICBlock var2) {
      ICSwitchStm var3 = (ICSwitchStm)var2.getLast();
      aiu var4 = (aiu)this.oT.get(this.ED);
      if (var4 == null) {
         var4 = (aiu)this.WR.get(this.ED);
      }

      List var5 = this.ld.UT(this.ED);
      Collections.sort(var5);
      if (!var5.isEmpty()) {
         int var6 = (Integer)var5.get(var5.size() - 1);
         if (var6 >= var4.A) {
            if (var6 > var4.A) {
               Object[] var10000 = new Object[0];
            }

            Object[] var18 = new Object[0];
         }
      }

      this.eP.push(var2);
      this.UO.push(this.ah);
      int var16 = this.eP.size();

      for (int var7 = 0; var7 < var5.size(); var7++) {
         int var8 = (Integer)var5.get(var7);
         BasicBlock var9 = (BasicBlock)this.sY.get((Integer)this.gp.get(var8) - 1);
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

         int var17 = var7 + 1 < var5.size() ? (Integer)var5.get(var7 + 1) : var4.A;
         if (var11 != null) {
            this.eP.add(var16, var11);
            this.UO.add(var16, var17);
         } else if (var3.hasDefaultBlock()) {
            this.eP.add(var16, var3.getDefaultBlock());
            this.UO.add(var16, var17);
         }
      }

      this.ah = -1;
      return true;
   }
}
