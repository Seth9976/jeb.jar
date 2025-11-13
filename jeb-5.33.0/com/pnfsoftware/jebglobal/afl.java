package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CKeyword;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class afl extends adm implements ICWhileStm {
   @SerId(1)
   ICPredicate A;
   @SerId(2)
   ICBlock kS;

   afl(ICPredicate var1, ICBlock var2) {
      if (var1 != null && var2 != null) {
         this.A = var1;
         this.kS = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public afl A() {
      afl var1 = new afl(this.A.duplicate(), this.kS.duplicate());
      super.pC(var1);
      return var1;
   }

   @Override
   public void reset() {
      this.A = null;
      this.kS = null;
   }

   @Override
   public ICPredicate getPredicate() {
      return this.A;
   }

   @Override
   public ICBlock getBody() {
      return this.kS;
   }

   @Override
   public void setPredicate(ICPredicate var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   @Override
   public void setBody(ICBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.kS = var1;
      }
   }

   @Override
   public boolean insertAt(long var1, ICStatement var3) {
      return this.kS.insertAt(var1, var3);
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.kS);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new agi(this, this.A));
      var1.addAll(this.kS.generateFlatList());
      var1.add(new agj());
      return var1;
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.A, this.kS);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.A == var1) {
         if (!(var2 instanceof ICPredicate)) {
            return false;
         } else {
            this.A = (ICPredicate)var2;
            return true;
         }
      } else if (this.kS == var1) {
         if (!(var2 instanceof ICBlock)) {
            return false;
         } else {
            this.kS = (ICBlock)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(CKeyword.WHILE);
      var1.paren();
      this.A.generate(var1);
      var1.parenClose();
      var1.space();
      this.kS.generate(var1);
      this.A(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.While;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
         afl var2 = (afl)var1;
         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

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

   @Override
   public String toString() {
      return Strings.ff("while(%s) {...}", this.getPredicate());
   }
}
