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
public class agf extends aff implements ICForStm {
   @SerId(1)
   private ICStatement RF;
   @SerId(2)
   private ICPredicate xK;
   @SerId(3)
   private ICStatement Dw;
   @SerId(4)
   private ICBlock Uv;

   agf(ICStatement var1, ICPredicate var2, ICStatement var3, ICBlock var4) {
      if (var4 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
         this.xK = var2;
         this.Dw = var3;
         this.Uv = var4;
      }
   }

   public agf RF() {
      agf var1 = new agf(this.RF.duplicate(), this.xK.duplicate(), this.Dw.duplicate(), this.Uv.duplicate());
      super.q(var1);
      return var1;
   }

   @Override
   public void reset() {
      this.RF = null;
      this.xK = null;
      this.Dw = null;
      this.Uv = null;
   }

   @Override
   public ICStatement getPreStatement() {
      return this.RF;
   }

   @Override
   public ICPredicate getPredicate() {
      return this.xK;
   }

   @Override
   public ICStatement getPostStatement() {
      return this.Dw;
   }

   @Override
   public ICBlock getBody() {
      return this.Uv;
   }

   @Override
   public void setPreStatement(ICStatement var1) {
      this.RF = var1;
   }

   @Override
   public void setPredicate(ICPredicate var1) {
      this.xK = var1;
   }

   @Override
   public void setPostStatement(ICStatement var1) {
      this.Dw = var1;
   }

   @Override
   public void setBody(ICBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Uv = var1;
      }
   }

   @Override
   public boolean insertAt(long var1, ICStatement var3) {
      throw new RuntimeException("TBI");
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.Uv);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new ahp(this, this.RF, this.xK, this.Dw));
      var1.addAll(this.Uv.generateFlatList());
      var1.add(new ahq());
      return var1;
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.RF, this.xK, this.Dw, this.Uv);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.RF == var1) {
         if (!(var2 instanceof ICStatement)) {
            return false;
         } else {
            this.RF = (ICStatement)var2;
            return true;
         }
      } else if (this.xK == var1) {
         if (!(var2 instanceof ICPredicate)) {
            return false;
         } else {
            this.xK = (ICPredicate)var2;
            return true;
         }
      } else if (this.Dw == var1) {
         if (!(var2 instanceof ICStatement)) {
            return false;
         } else {
            this.Dw = (ICStatement)var2;
            return true;
         }
      } else if (this.Uv == var1) {
         if (!(var2 instanceof ICBlock)) {
            return false;
         } else {
            this.Uv = (ICBlock)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      var1.appendKeyword(CKeyword.FOR);
      var1.paren();
      if (this.RF != null) {
         this.RF.generate(var1);
      }

      var1.append("; ");
      if (this.xK != null) {
         this.xK.generate(var1);
      }

      var1.append("; ");
      if (this.Dw != null) {
         this.Dw.generate(var1);
      }

      var1.parenClose();
      var1.space();
      this.Uv.generate(var1);
      this.RF(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.For;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
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
         agf var2 = (agf)var1;
         if (this.Uv == null) {
            if (var2.Uv != null) {
               return false;
            }
         } else if (!this.Uv.equals(var2.Uv)) {
            return false;
         }

         if (this.xK == null) {
            if (var2.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var2.xK)) {
            return false;
         }

         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

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

   @Override
   public String toString() {
      return Strings.ff("for(%s; %s; ...) {...}", this.getPreStatement(), this.getPredicate());
   }
}
