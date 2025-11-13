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
public class adr extends adm implements ICBlock {
   @SerId(1)
   private List A = new ArrayList();

   adr() {
   }

   adr(ICStatement var1) {
      this.add(var1);
   }

   adr(ICStatement... var1) {
      this.addAll(var1);
   }

   public adr A() {
      adr var1 = new adr();
      super.pC(var1);

      for (ICStatement var3 : this.A) {
         var1.add(var3.duplicate());
      }

      return var1;
   }

   @Override
   public void reset() {
      this.A.clear();
   }

   @Override
   public int size() {
      return this.A.size();
   }

   @Override
   public boolean isEmpty() {
      return this.A.isEmpty();
   }

   @Override
   public ICBlock add(ICStatement var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null instruction added to Cblock:" + this.toString());
      } else {
         this.A.add(var1);
         this.kS();
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
      return this.A;
   }

   @Override
   public ICStatement get(int var1) {
      if (var1 < 0) {
         var1 += this.A.size();
      }

      return (ICStatement)this.A.get(var1);
   }

   @Override
   public ICStatement getLast() {
      return (ICStatement)this.A.get(this.A.size() - 1);
   }

   @Override
   public ICStatement removeLast() {
      return this.remove(this.A.size() - 1);
   }

   @Override
   public ICStatement remove(int var1) {
      ICStatement var2 = (ICStatement)this.A.remove(var1);
      this.kS();
      return var2;
   }

   @Override
   public boolean remove(ICStatement var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Can not remove null instruction to Cblock:" + this.toString());
      } else if (!this.A.remove(var1)) {
         return false;
      } else {
         this.kS();
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
         this.A.add(var1, var2);
         this.kS();
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
      this.A.clear();
      this.kS();
   }

   @Override
   public boolean insertAt(long var1, ICStatement var3) {
      if (!pC(this.A, var1, var3)) {
         return false;
      } else {
         this.kS();
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
      var1.add(new afs(this));

      for (ICStatement var3 : this.A) {
         if (var3 instanceof adm) {
            var1.addAll(((adm)var3).generateFlatList());
         } else {
            var1.add(var3);
         }
      }

      var1.add(new aft());
      return var1;
   }

   @Override
   public Iterator iterator() {
      return this.A.iterator();
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.A);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      for (int var3 = 0; var3 < this.A.size(); var3++) {
         if (this.A.get(var3) == var1) {
            if (!(var2 instanceof ICStatement)) {
               return false;
            }

            this.A.set(var3, (ado)var2);
            this.kS();
            return true;
         }
      }

      return false;
   }

   private void kS() {
      this.b_();
      this.pC = -1L;
      if (!this.A.isEmpty()) {
         ICStatement var1 = (ICStatement)this.A.get(0);
         this.addPhysicalOffset(var1.getPhysicalOffset());
         this.pC = var1.getIntermediateOffset();
      }
   }

   @Override
   public void generateBody(COutputSink var1, boolean var2) {
      ICMethod var3 = var1.getCurrentContainingMethod();
      ICStatement var4 = null;
      boolean var5 = var2 && var1.getSpaceOutCompounds();
      adr.Av var6 = new adr.Av();
      boolean var7 = false;
      int var8 = 0;
      boolean var9 = false;

      for (ICStatement var11 : this.A) {
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
            var6 = pC(var1, var11, var6);
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

         if (!var6.A()) {
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

         if (!var6.A()) {
            if (!(var11 instanceof ICLabel) && (!(var11 instanceof ICCompound) || var11 instanceof ICDoWhileStm)) {
               var1.append(";");
            }

            var1.eol();
         }

         var4 = var11;
      }

      if (var6.A()) {
         var1.append(";");
         var1.eol();
      }
   }

   private static adr.Av pC(COutputSink var0, ICStatement var1, adr.Av var2) {
      ICDecl var3 = CUtil.getDefinition(var1);
      if (var3 != null) {
         ICElement var4 = CUtil.getDefinitionInitialValue(var1);
         if (var4 != null && !pC(var4)) {
            if (var2.A()) {
               var0.append(";");
               var0.eol();
               var2.pC();
            }
         } else if (!var2.A()) {
            var2.pC((afj)var3.getType());
         } else if (var2.pC.equals((afj)var3.getType())) {
            var0.append(", ");
            var0.setOmitTypeForNextDefinitionPrinting(true);
            var2.A++;
         } else {
            var0.append(";");
            var0.eol();
            var2.pC();
            var2.pC((afj)var3.getType());
         }
      } else if (var2.A()) {
         var0.append(";");
         var0.eol();
         var2.pC();
      }

      return var2;
   }

   private static boolean pC(ICElement var0) {
      if (var0 instanceof ICConstant) {
         return true;
      } else if (var0 instanceof ICIdentifier) {
         return true;
      } else {
         if (var0 instanceof ICExpression) {
            if (CUtil.isOperation((ICExpression)var0, COperatorType.REF) && ((aex)var0).getFirstOperand() instanceof ICIdentifier) {
               return true;
            }

            if (CUtil.isOperation((ICExpression)var0, COperatorType.PTR) && ((aex)var0).getFirstOperand() instanceof ICIdentifier) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public void generateHeader(COutputSink var1) {
      this.pC(var1);
      var1.brace();
      var1.eol();
      var1.incrementIndentationLevel();
   }

   @Override
   public void generateFooter(COutputSink var1) {
      var1.decrementIndentationLevel();
      var1.braceClose();
      this.A(var1);
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
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         adr var2 = (adr)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return true;
      }
   }

   private static class Av {
      afj pC;
      int A;

      public void pC(afj var1) {
         Assert.a(this.A == 0 && this.pC == null);
         this.pC = var1;
         this.A = 1;
      }

      public void pC() {
         this.pC = null;
         this.A = 0;
      }

      public boolean A() {
         return this.A > 0 && this.pC != null;
      }
   }
}
