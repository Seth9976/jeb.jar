package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CKeyword;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aem extends adm implements ICForStm {
   @SerId(1)
   private ICStatement A;
   @SerId(2)
   private ICPredicate kS;
   @SerId(3)
   private ICStatement wS;
   @SerId(4)
   private ICBlock UT;

   aem(ICStatement var1, ICPredicate var2, ICStatement var3, ICBlock var4) {
      if (var4 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
         this.kS = var2;
         this.wS = var3;
         this.UT = var4;
      }
   }

   public aem A() {
      aem var1 = new aem(this.A.duplicate(), this.kS.duplicate(), this.wS.duplicate(), this.UT.duplicate());
      super.pC(var1);
      return var1;
   }

   @Override
   public void reset() {
      this.A = null;
      this.kS = null;
      this.wS = null;
      this.UT = null;
   }

   @Override
   public ICStatement getPreStatement() {
      return this.A;
   }

   @Override
   public ICPredicate getPredicate() {
      return this.kS;
   }

   @Override
   public ICStatement getPostStatement() {
      return this.wS;
   }

   @Override
   public ICBlock getBody() {
      return this.UT;
   }

   @Override
   public void setPreStatement(ICStatement var1) {
      this.A = var1;
   }

   @Override
   public void setPredicate(ICPredicate var1) {
      this.kS = var1;
   }

   @Override
   public void setPostStatement(ICStatement var1) {
      this.wS = var1;
   }

   @Override
   public void setBody(ICBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.UT = var1;
      }
   }

   @Override
   public boolean insertAt(long var1, ICStatement var3) {
      throw new RuntimeException("TBI");
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.UT);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new afw(this, this.A, this.kS, this.wS));
      var1.addAll(this.UT.generateFlatList());
      var1.add(new afx());
      return var1;
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.A, this.kS, this.wS, this.UT);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.A == var1) {
         if (!(var2 instanceof ICStatement)) {
            return false;
         } else {
            this.A = (ICStatement)var2;
            return true;
         }
      } else if (this.kS == var1) {
         if (!(var2 instanceof ICPredicate)) {
            return false;
         } else {
            this.kS = (ICPredicate)var2;
            return true;
         }
      } else if (this.wS == var1) {
         if (!(var2 instanceof ICStatement)) {
            return false;
         } else {
            this.wS = (ICStatement)var2;
            return true;
         }
      } else if (this.UT == var1) {
         if (!(var2 instanceof ICBlock)) {
            return false;
         } else {
            this.UT = (ICBlock)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(CKeyword.FOR);
      var1.paren();
      if (this.A != null) {
         this.A.generate(var1);
      }

      var1.append("; ");
      if (this.kS != null) {
         this.kS.generate(var1);
      }

      var1.append("; ");
      if (this.wS != null) {
         this.wS.generate(var1);
      }

      var1.parenClose();
      var1.space();
      this.UT.generate(var1);
      this.A(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.For;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
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
         aem var2 = (aem)var1;
         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
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
      return Strings.ff("for(%s; %s; ...) {...}", this.getPreStatement(), this.getPredicate());
   }
}
