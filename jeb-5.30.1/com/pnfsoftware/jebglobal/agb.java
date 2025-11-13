package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CKeyword;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class agb extends aff implements ICDoWhileStm {
   @SerId(1)
   private ICBlock RF;
   @SerId(2)
   private ICPredicate xK;

   agb(ICBlock var1, ICPredicate var2) {
      if (var1 != null && var2 != null) {
         this.RF = var1;
         this.xK = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public agb RF() {
      agb var1 = new agb(this.RF.duplicate(), this.xK.duplicate());
      super.q(var1);
      return var1;
   }

   @Override
   public void reset() {
      this.RF = null;
      this.xK = null;
   }

   @Override
   public ICBlock getBody() {
      return this.RF;
   }

   @Override
   public ICPredicate getPredicate() {
      return this.xK;
   }

   @Override
   public void setBody(ICBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
      }
   }

   @Override
   public void setPredicate(ICPredicate var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.xK = var1;
      }
   }

   @Override
   public boolean insertAt(long var1, ICStatement var3) {
      return this.RF.insertAt(var1, var3);
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList();
      var1.add(this.RF);
      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new ahn(this));
      var1.addAll(this.RF.generateFlatList());
      var1.add(new aho(this.xK));
      return var1;
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.RF, this.xK);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.RF == var1) {
         if (!(var2 instanceof ICBlock)) {
            return false;
         } else {
            this.RF = (ICBlock)var2;
            return true;
         }
      } else if (this.xK == var1) {
         if (!(var2 instanceof ICPredicate)) {
            return false;
         } else {
            this.xK = (ICPredicate)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      var1.appendKeyword(CKeyword.DO);
      var1.append(" ");
      this.RF.generate(var1);
      var1.eol();
      var1.appendKeyword(CKeyword.WHILE);
      var1.paren();
      this.xK.generate(var1);
      var1.parenClose();
      this.RF(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.DoWhile;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
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
         agb var2 = (agb)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         if (this.xK == null) {
            if (var2.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var2.xK)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("do {...} while(%s)", this.getPredicate());
   }
}
