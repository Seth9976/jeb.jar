package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCustomStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aef extends ado implements ICCustomStatement {
   @SerId(1)
   long A;
   @SerId(2)
   String kS;
   @SerId(3)
   List wS;
   @SerId(4)
   List UT;

   aef(long var1) {
      this.A = var1;
   }

   public aef A() {
      aef var1 = new aef(this.A);
      super.pC(var1);
      var1.kS = this.kS;
      if (this.wS != null) {
         var1.wS = new ArrayList(this.wS.size());

         for (ICElement var3 : this.wS) {
            var1.wS.add(var3.duplicate());
         }
      }

      if (this.UT != null) {
         var1.UT = new ArrayList(this.UT.size());

         for (ICElement var5 : this.UT) {
            var1.UT.add(var5.duplicate());
         }
      }

      return var1;
   }

   @Override
   public long getNativeAddress() {
      return this.A;
   }

   @Override
   public void setCommandName(String var1) {
      this.kS = var1;
   }

   @Override
   public String getCommandName() {
      return this.kS;
   }

   @Override
   public void setInputElements(List var1) {
      this.wS = var1 == null ? new ArrayList() : new ArrayList(var1);
   }

   @Override
   public List getInputElements() {
      return this.wS;
   }

   @Override
   public void setOutputElements(List var1) {
      this.UT = var1 == null ? new ArrayList() : new ArrayList(var1);
   }

   @Override
   public List getOutputElements() {
      return this.UT;
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      if (!var1.getSourceCustomizer().generateNativeStatement(this, var1)) {
         if (this.kS != null) {
            if (this.UT != null && !this.UT.isEmpty()) {
               if (this.UT.size() >= 2) {
                  var1.paren();
               }

               for (int var2 = 0; var2 < this.UT.size(); var2++) {
                  if (var2 >= 1) {
                     var1.append(", ");
                  }

                  ((ICElement)this.UT.get(var2)).generate(var1);
               }

               if (this.UT.size() >= 2) {
                  var1.parenClose();
               }

               var1.space();
               var1.append("=");
               var1.space();
            }

            var1.append(this.kS);
            if (this.wS != null) {
               var1.paren();

               for (int var5 = 0; var5 < this.wS.size(); var5++) {
                  if (var5 >= 1) {
                     var1.append(", ");
                  }

                  ((ICElement)this.wS.get(var5)).generate(var1);
               }

               var1.parenClose();
            }
         } else {
            String var6 = Strings.ff("instruction@%Xh", this.A);
            IDynamicContentManager var3 = var1.getDynamicContentManager();
            if (var3 != null) {
               String var4 = var3.getNativeInstructionFormat(this.A);
               if (var4 != null) {
                  var6 = Strings.ff("\"%s\"", var4);
               }
            }

            var1.append("__asm");
            var1.paren();
            var1.append(var6);
            var1.parenClose();
         }
      }

      this.A(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.CustomStatement;
   }

   @Override
   public List getSubElements() {
      List var1 = afm.pC();
      if (this.wS != null) {
         return afm.pC(var1, this.wS);
      } else {
         return this.UT != null ? afm.pC(var1, this.UT) : var1;
      }
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.wS != null) {
         for (int var3 = 0; var3 < this.wS.size(); var3++) {
            if (this.wS.get(var3) == var1) {
               if (!(var2 instanceof ICElement)) {
                  return false;
               }

               this.wS.set(var3, var2);
               return true;
            }
         }
      }

      if (this.UT != null) {
         for (int var4 = 0; var4 < this.UT.size(); var4++) {
            if (this.UT.get(var4) == var1) {
               if (!(var2 instanceof ICElement)) {
                  return false;
               }

               this.UT.set(var4, var2);
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (int)(this.A ^ this.A >>> 32);
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
         aef var2 = (aef)var1;
         return this.A == var2.A;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s", this.A, this.kS);
   }
}
