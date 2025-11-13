package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Ser
public class afk extends aff implements ICBlock {
   @SerId(1)
   private List RF = new ArrayList();

   afk() {
   }

   afk(ICStatement var1) {
      this.add(var1);
   }

   afk(ICStatement... var1) {
      this.addAll(var1);
   }

   public afk RF() {
      afk var1 = new afk();
      super.q(var1);

      for (ICStatement var3 : this.RF) {
         var1.add(var3.duplicate());
      }

      return var1;
   }

   @Override
   public void reset() {
      this.RF.clear();
   }

   @Override
   public int size() {
      return this.RF.size();
   }

   @Override
   public boolean isEmpty() {
      return this.RF.isEmpty();
   }

   @Override
   public ICBlock add(ICStatement var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null instruction added to Cblock:" + this.toString());
      } else {
         this.RF.add(var1);
         this.xK();
         return this;
      }
   }

   @Override
   public ICBlock addAll(ICStatement... var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null instructions added to Cblock:" + this.toString());
      } else {
         for (ICStatement var5 : var1) {
            this.add(var5);
         }

         return this;
      }
   }

   @Override
   public ICBlock addAll(ICBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null instructions added to Cblock:" + this.toString());
      } else {
         for (ICStatement var3 : var1.getAll()) {
            this.add(var3);
         }

         return this;
      }
   }

   @Override
   public List getAll() {
      return this.RF;
   }

   @Override
   public ICStatement get(int var1) {
      if (var1 < 0) {
         var1 += this.RF.size();
      }

      return (ICStatement)this.RF.get(var1);
   }

   @Override
   public ICStatement getLast() {
      return (ICStatement)this.RF.get(this.RF.size() - 1);
   }

   @Override
   public ICStatement removeLast() {
      return this.remove(this.RF.size() - 1);
   }

   @Override
   public ICStatement remove(int var1) {
      ICStatement var2 = (ICStatement)this.RF.remove(var1);
      this.xK();
      return var2;
   }

   @Override
   public boolean remove(ICStatement var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Can not remove null instruction to Cblock:" + this.toString());
      } else if (!this.RF.remove(var1)) {
         return false;
      } else {
         this.xK();
         return true;
      }
   }

   @Override
   public void set(int var1, ICStatement var2) {
      this.remove(var1);
      this.insert(var1, var2);
   }

   @Override
   public void insert(int var1, ICStatement var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Can not insert null instruction in Cblock:" + this.toString());
      } else {
         this.RF.add(var1, var2);
         this.xK();
      }
   }

   @Override
   public void insertAll(int var1, ICBlock var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Can not insert null instructions in Cblock:" + this.toString());
      } else {
         for (ICStatement var4 : var2) {
            this.insert(var1, var4);
            var1++;
         }
      }
   }

   @Override
   public void clear() {
      this.RF.clear();
      this.xK();
   }

   @Override
   public boolean insertAt(long var1, ICStatement var3) {
      if (!q(this.RF, var1, var3)) {
         return false;
      } else {
         this.xK();
         return true;
      }
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new ahl(this));

      for (ICStatement var3 : this.RF) {
         if (var3 instanceof aff) {
            var1.addAll(((aff)var3).generateFlatList());
         } else {
            var1.add(var3);
         }
      }

      var1.add(new ahm());
      return var1;
   }

   @Override
   public Iterator iterator() {
      return this.RF.iterator();
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.RF);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      for (int var3 = 0; var3 < this.RF.size(); var3++) {
         if (this.RF.get(var3) == var1) {
            if (!(var2 instanceof ICStatement)) {
               return false;
            }

            this.RF.set(var3, (afh)var2);
            this.xK();
            return true;
         }
      }

      return false;
   }

   private void xK() {
      this.b_();
      this.q = -1L;
      if (!this.RF.isEmpty()) {
         ICStatement var1 = (ICStatement)this.RF.get(0);
         this.addPhysicalOffset(var1.getPhysicalOffset());
         this.q = var1.getIntermediateOffset();
      }
   }

   @Override
   public void generateBody(COutputSink var1, boolean var2) {
      ICMethod var3 = var1.getCurrentContainingMethod();
      ICStatement var4 = null;
      boolean var5 = var2 && var1.getSpaceOutCompounds();
      afk.eo var6 = new afk.eo();
      boolean var7 = false;
      int var8 = 0;
      boolean var9 = false;

      for (ICStatement var11 : this.RF) {
         if (var5 && !var7) {
            if (!(var11 instanceof ICDecl) && !CUtil.isDeclareAndAssign(var11)) {
               var7 = true;
               if (var8 > 0) {
                  var9 = true;
               }
            } else {
               var8++;
            }
         }

         InstructionCoordinates var12 = null;
         if (var3 != null && var11.getPhysicalOffset() != null) {
            Long var13 = var3.getPhysicalOffset();
            if (var13 != null) {
               int var14 = (int)(var11.getPhysicalOffset() - var13);
               var12 = new InstructionCoordinates(var3.getIndex(), var14);
            }
         }

         var1.setEolCoordinates(var12);
         if (var1.getMergeAdjacentDefinitions()) {
            var6 = q(var1, var11, var6);
         }

         if (var9) {
            var9 = false;
            var1.eol(var12);
         } else if (var5 && var4 instanceof ICCompound) {
            var1.eol(var12);
         }

         boolean var16 = var11 instanceof ICLabel && var1.getIndentationLevel() >= 0;
         if (var16) {
            var1.decrementIndentationLevel();
         }

         if (!var6.RF()) {
            var1.renderPreComment(var12);
         }

         IDynamicContentManager var17 = var1.getDynamicContentManager();
         if (var17 != null && var12 != null) {
            String var15 = var17.getComment(var12);
            if (var15 != null && !var15.isEmpty()) {
               var1.setEolComment(var15);
            }
         }

         var1.setEolCoordinates(var12);
         var11.generate(var1);
         if (var16) {
            var1.incrementIndentationLevel();
         }

         if (!var6.RF()) {
            if (!(var11 instanceof ICLabel) && (!(var11 instanceof ICCompound) || var11 instanceof ICDoWhileStm)) {
               var1.append(";");
            }

            var1.eol();
         }

         var4 = var11;
      }

      if (var6.RF()) {
         var1.append(";");
         var1.eol();
      }
   }

   private static afk.eo q(COutputSink var0, ICStatement var1, afk.eo var2) {
      ICDecl var3 = CUtil.getDefinition(var1);
      if (var3 != null) {
         ICElement var4 = CUtil.getDefinitionInitialValue(var1);
         if (var4 != null && !q(var4)) {
            if (var2.RF()) {
               var0.append(";");
               var0.eol();
               var2.q();
            }
         } else if (!var2.RF()) {
            var2.q((ahc)var3.getType());
         } else if (var2.q.equals((ahc)var3.getType())) {
            var0.append(", ");
            var0.setOmitTypeForNextDefinitionPrinting(true);
            var2.RF++;
         } else {
            var0.append(";");
            var0.eol();
            var2.q();
            var2.q((ahc)var3.getType());
         }
      } else if (var2.RF()) {
         var0.append(";");
         var0.eol();
         var2.q();
      }

      return var2;
   }

   private static boolean q(ICElement var0) {
      if (var0 instanceof ICConstant) {
         return true;
      } else if (var0 instanceof ICIdentifier) {
         return true;
      } else {
         if (var0 instanceof ICExpression) {
            if (CUtil.isOperation((ICExpression)var0, COperatorType.REF) && ((agq)var0).getFirstOperand() instanceof ICIdentifier) {
               return true;
            }

            if (CUtil.isOperation((ICExpression)var0, COperatorType.PTR) && ((agq)var0).getFirstOperand() instanceof ICIdentifier) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public void generateHeader(COutputSink var1) {
      this.q(var1);
      var1.brace();
      var1.eol();
      var1.incrementIndentationLevel();
   }

   @Override
   public void generateFooter(COutputSink var1) {
      var1.decrementIndentationLevel();
      var1.braceClose();
      this.RF(var1);
   }

   @Override
   public void generate(COutputSink var1) {
      this.generateHeader(var1);
      this.generateBody(var1, false);
      this.generateFooter(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Block;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         afk var2 = (afk)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         return true;
      }
   }

   private static class eo {
      ahc q;
      int RF;

      public void q(ahc var1) {
         Assert.a(this.RF == 0 && this.q == null);
         this.q = var1;
         this.RF = 1;
      }

      public void q() {
         this.q = null;
         this.RF = 0;
      }

      public boolean RF() {
         return this.RF > 0 && this.q != null;
      }
   }
}
