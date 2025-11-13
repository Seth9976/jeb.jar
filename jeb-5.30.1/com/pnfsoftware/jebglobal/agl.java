package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICInstanceField;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class agl extends afg implements ICInstanceField {
   @SerId(1)
   String q;
   @SerId(2)
   ICExpression RF;
   @SerId(3)
   boolean xK;

   agl(String var1, ICExpression var2, boolean var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public CElementType getElementType() {
      return CElementType.InstanceField;
   }

   @Override
   public String getFieldAddress() {
      return this.q;
   }

   @Override
   public ICField getField() {
      return afc.RF(this, this.q);
   }

   @Override
   public ICExpression getInstance() {
      return this.RF;
   }

   @Override
   public boolean isPointed() {
      return this.xK;
   }

   private void q(String var1, COutputSink var2) {
      ICField var3 = afc.RF(this, var1);
      if (var3 != null) {
         var3.generateName(var2, false);
      } else {
         long var5 = 0L;
         var2.appendAndRecord(var1, ItemClassIdentifiers.FIELD_NAME, var5);
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      this.RF.generate(var1);
      if (this.xK) {
         var1.append("->");
      } else {
         var1.append(".");
      }

      this.q(this.q, var1);
      this.RF(var1);
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.RF);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.RF == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.RF = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      return 31 * var1 + (this.xK ? 1231 : 1237);
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
         agl var2 = (agl)var1;
         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         return this.xK == var2.xK;
      }
   }

   public agl RF() {
      agl var1 = new agl(this.q, this.RF.duplicate(), this.xK);
      super.q(var1);
      return var1;
   }

   @Override
   public String toString() {
      return this.RF + "->" + this.q;
   }
}
